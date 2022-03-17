package com.example.demo.api;

import static com.example.demo.constant.Constant.*;
import com.example.demo.entity.*;
import java.io.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.digest.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;
import org.json.*;

/**
 * class xử lý api giao dịch
 * @author nguyễn duy hoài lâm
 */
public class TransactionApi {

    public static CloseableHttpClient httpClient;

    /**
     * phương thức xử lý giao dịch
     * @param transactionApiRequest: thông tin giao dịch
     * @param command: mã api sử dụng giao dịch
     * @param version: phiên bản API
     * @param appCode: mã app sử dụng hệ thống thanh toán
     * @return apiOutput: chuỗi kết quả giao dịch gửi về từ api
     * @throws IOException
     * @throws JSONException
     */
    public static String processTransaction (Transaction transactionApiRequest, String command, String version, String appCode) throws IOException, JSONException {
        httpClient = HttpClients.createDefault();

        HttpPatch httpPatch = new HttpPatch("https://ecopark-system-api.herokuapp.com/api/card/processTransaction");
        /**
         * body request
         */
        String bodyRequest = getBodyRequest(transactionApiRequest, command, version, appCode);
        httpPatch.setEntity(new StringEntity(bodyRequest, ContentType.APPLICATION_JSON));

        CloseableHttpResponse httpResponse = httpClient.execute(httpPatch);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
        }
        HttpEntity httpEntity = httpResponse.getEntity();
        String apiOutput = EntityUtils.toString(httpEntity);
        return apiOutput;
    }

    /**
     * phưng thức sử dụng mã băm md5 băm chuỗi json gồm mã bí mật và thông tin giao dịch thành mã kiểm tra để đảm bảo dữ liệu không bị thay đổi khi chuyển từ app lên server thanh toán
     * @param jsonString: chuỗi json gồm mã bí mật và thông tin giao dịch
     * @return mã kiểm tra sau khi băm
     */
    public static String getHashCode(String jsonString){
        return DigestUtils.md5Hex(jsonString);
    }

    /**
     * phương thức tạo body cho yêu cầu xử lý giao dịch bao gồm chuỗi json lưu thông tin giao dịch, phiên bản API, mã app sử dụng hệ thống thanh toán, mã kiểm tra
     * @param transactionApiRequest: thông tin giao dịch
     * @param command: mã api sử dụng giao dịch
     * @param version: phiên bản API
     * @param appCode: mã app sử dụng hệ thống thanh toán
     * @return body request
     * @throws JSONException
     */
    public static String getBodyRequest(Transaction transactionApiRequest, String command, String version, String appCode) throws JSONException {
        /**
         * Chuỗi json lưu thông tin giao dịch
         */
        JSONObject transaction = getJSONTransaction(transactionApiRequest, command);
        /**
         * mã kiểm tra
         */
        String hashCode = getHashCode(getJsonToHashCode(transaction).toString());
        JSONObject bodyRequest = new JSONObject();

        bodyRequest.put("version", version);
        bodyRequest.put("transaction", transaction);
        bodyRequest.put("appCode",appCode);
        bodyRequest.put("hashCode", hashCode);
        return bodyRequest.toString();
    }

    /**
     * phương thức chuyển thông tin giao dịch và mã api sử dụng giao dịch thành chuỗi json lưu thông tin giao dịch
     * @param transactionApiRequest: thông tin giao dịch
     * @param command: mã api sử dụng giao dịch
     * @return obj: chuỗi json lưu thông tin giao dịch
     * @throws JSONException
     */
    public static JSONObject getJSONTransaction(Transaction transactionApiRequest, String command) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("command", command);
        obj.put("cardCode", transactionApiRequest.getCardCode());
        obj.put("owner", transactionApiRequest.getOwner());
        obj.put("cvvCode",transactionApiRequest.getCvvCode());
        obj.put("dateExpired", transactionApiRequest.getDateExpired());
        obj.put("transactionContent", transactionApiRequest.getTransactionContent());
        obj.put("amount", transactionApiRequest.getAmount());
        obj.put("createdAt",format(transactionApiRequest.getCreatedAt()));
        return obj;
    }

    /**
     * phương thức tạo một chuỗi json gồm chuỗi json lưu thông tin giao dịch và mã bí mật để thực tiến hành băm
     * @param jsonTransaction: chuỗi json lưu thông tin giao dịch
     * @return obj: chuỗi json gồm mã bí mật và thông tin giao dịch
     * @throws JSONException
     */
    public static JSONObject getJsonToHashCode(JSONObject jsonTransaction) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("secretKey", SECRET_KET);
        obj.put("transaction", jsonTransaction);
        return obj;
    }

    /**
     * Phương thức giúp format ngày tháng đúng theo định dạng yêu cầu
     * @param date: ngày cần format
     * @return strDate: Chuỗi là ngày sau khi format
     */
    public static String format(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = formatter.format(date);
        return strDate;
    }
}
