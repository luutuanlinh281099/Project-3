$(document).ready(function(){
    $("#rentalBikeForm").submit(function(event) {
        event.preventDefault();
        checkIdBike();
    });
    function checkIdBike() {
        var formData = {
            id : $("#idCodeBike").val()
        }
        console.log(formData)
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/bike?id="+formData.id,
            data : JSON.stringify(formData),
            dataType : 'json',
            success : function(result) {
                 document.getElementById('inforBikeForm').action = "/transaction?id=" + result.bike.id;
                 document.getElementById("idB").innerHTML = "Thông tin xe " + result.bike.id;
                 document.getElementById("idBike").innerHTML = "Mã xe: " + result.bike.id;
                 document.getElementById("typeBike").innerHTML = "Loại xe: " + result.bike.categoryBike.categoryBike;
                 if(result.bike.status==0) document.getElementById("addBike").innerHTML = "Vị trí hiện tại: " + result.bike.parking.nameParking;
                 else document.getElementById("addBike").innerHTML = "Vị trí cũ: " + result.bike.parking.nameParking;
                 document.getElementById("priceBike").innerHTML = "Giá xe: " + result.bike.categoryBike.priceBike + " vnd";
                 document.getElementById("energyBike").innerHTML = "Dung lượng pin: " + result.bike.energy + " mA";
                 document.getElementById("image").src = "/image/" + result.bike.categoryBike.image;
                 var status="";
                if(result.bike.status==true){
                   status = "Trạng thái: Đã thuê";
                   document.getElementById("rentalBike").style.display = "none";
                }
                else {
                    document.getElementById("rentalBike").style.display = "block";
                    status= "Trạng thái: Chưa thuê";
                }
                document.getElementById("statusBike").innerHTML = status ;
                $('.inforBike #inforBike').modal();
                console.log(result);
            },
            error : function(e) {
                $('#errorId').modal();
                console.log(e);
            }
        });
    }
});