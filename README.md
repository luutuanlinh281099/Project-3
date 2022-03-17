# TKXDPM.20201-Group10
Bổ sung thêm 1 thuộc tính nữa trong thông tin xe: Tiền đặt cọc (40% giá trị chiếc xe)
Ở bước 8 trong use case thuê xe, hệ thống chưa áp dụng thực tế nên chỉ cần in ra thông báo: "Đã kích hoạt mở khóa xe"
## Interface Design:
- Bản Moqup là tổng quan về giao diện thể hiện chi tiết từng màn hình đồng thời thể hiện sự dịch chuyển màn hình
- Các mũi tên thể hiện sự dịch chuyển khi các nút, các đối tượng được chọn.
## Data Modeling
- MySql
# Bài tập RequirementAnalysis
## Tasks
- Task 1: Use case "Xem thông tin bãi xe, loại xe"
- Task 2: Use case "Thuê xe"
- Task 3: Use case "Trả xe"
- Task 4: Hoàn thành tài liệu

## Contributions
- Liên: Hoàn thành use case "Thuê xe","Thanh toán tiền cọc".
- Lan: Hoàn thành use case " Xem thông tin bãi xe, loại xe".
- Linh: Hoàn thành use case "Trả xe"
- Lâm: Chỉnh sửa, phân công nhiệm vụ, hoàn thành,tổng hợp các phần còn lại của tài liệu.

## Revisions:
- Lâm review task 2: đầy đủ các phần, cơ bản chính xác 
- Liên review task 1: cần chỉnh sửa phần dữ liệu đầu ra
- Linh review task 4: cơ bản hoàn thành tài liệu 
- Lan review task 3: đầy đủ, chính xác
# Bài tập ArchitecturalDesign
## Tasks
- Thiết kế biểu đồ tương tác, biểu đồ lớp phân tích, biểu đồ lớp phân tích gộp cho từng use case đã xây dựng
- Kiểm tra chỉnh sửa phần đã làm của bài tập cũ
## Contributions
- Liên: use case "Thuê xe","Thanh toán tiền cọc".
- Lan: use case " Xem thông tin bãi xe, loại xe".
- Linh: use case "Trả xe".
- Lâm: Chỉnh sửa, phân công nhiệm vụ, hoàn thành, tổng hợp các phần còn lại, kiểm tra phần đã làm.
## Revisions:
- Lâm review Liên: Thể hiện rõ quá trình thuê xe và thanh toán tiền cọc, có cả trình tự khi xảy ra lỗi đúng theo đặc tả
- Liên review task Lan: Cơ bản đầy đủ, thêm cho sự kiện alt, break,
- Linh review task Lâm: Hoàn thành nhiện vụ, chỉnh sửa thêm phần còn thiếu cho các phần thiết kế khác, update RequirementAnalysis chính xác hơn.
- Lan review task Linh: Chưa xử lý cho alt, break, còn sai so với đặc tả.
=======
# Bài tập DetailedDesign
## Tasks
- Mỗi thành viên thiết kế màn hình, đặc tả màn hình, biểu đồ dịch chuyển, class design cho từng use case đã xây dựng.
- Tạo cơ sở dữ liệu.
- Kiểm tra chỉnh sử phần đã làm của bài tập cũ
## Contributions
- Liên, Lan, Linh: đặc tả màn hình, class design cho use case đã phân công từ những tuần trước.
- Cả nhóm: thống nhất thiết kế màn hình.
- Lâm: Chỉnh sửa, phân công nhiệm vụ, hoàn thành, tổng hợp các phần còn lại, kiểm tra phần đã làm, thiết kế cơ sở dữ liệu
## Revisions:
- Cả nhóm hoàn thành được phần thiết kế.
- Chưa xây hoàn thành đặc tả màn hình, class design.
- Linh review task Lâm: Tạo hoàn thiện cơ sở dữ liệu ban đầu.
=======
# Bài tập Lập trình
## Tasks
- Xây dựng mã nguồn
- Kiểm tra chỉnh sử phần đã làm của bài tập cũ
## Contributions
- Liên: code phần liên quan đến thuê xe thanh toán (checkId, payment,...)
- Lan: code phần liên quan đến xem xe, bãi xe (allParking, inforParking, infoBike,...)
- Linh: code phần liên quan đến trả xe (rentBike, viewInvoice...)
- Lâm: Chỉnh sửa, phân công nhiệm vụ, hoàn thành, tổng hợp các phần còn lại, kiểm tra phần đã làm, code giao diện, tạo ra các lớp, code chức năng còn lại.
## Revisions:
- Lâm review Liên: Hoàn thành checkId, test thành công quá trình giao dịch.
- Liên review task Lan: Chưa hoàn thiện hiển thị loại xe
- Lan review task Linh: Chưa có kỹ năng code mới query dữ liệu liên quan trả xe
- Linh review task Lâm: hoàn thành code giao diện, tạo cơ bản các lớp cần thiết để triển khai chức năng, hoàn thiện đưa dư liệu từ phần các bạn đã làm lên màn hình.
=======
# Bài tập Good Design
## Tasks
- Hoàn thành Design Concepts và Design Principles
- Kiểm tra chỉnh sử phần đã làm của bài tập cũ
## Contributions
- Liên: Hoàn thành Design Concepts và class design
- Lan: Hoàn thành đặc tả màn hình 
- Linh: Chỉnh sửa code theo Good Design
- Lâm: Chỉnh sửa, phân công nhiệm vụ, hoàn thành Design Principles, tổng hợp các phần còn lại, kiểm tra phần đã làm.
## Revisions:
- Lâm review Liên: Hoàn thành đầy đủ nhiệm vụ được giao, sửa thiết kế theo Good design.
- Liên review task Lan: Đặc tả đầy đủ các màn hình và đúng chức năng
- Lan review task Linh: Đã có chỉnh sửa chưa hoàn thiện, Lâm làm nốt
- Linh review task Lâm: Hoàn thành SOLID, sửa cơ bản code theo Good design.
=======
# Trang web thuê xe:
- Trang web được xây dựng bằng JDK14, spring, sử dụng IDE intellij để code
- CSDL dung mySQL với server có username = root, password = 123456 
- Để chạy trước hết cần load maven sau đó config server database cho đúng username, password trên máy tại application.properties
- Truy cập trang web tại cổng 8080.
