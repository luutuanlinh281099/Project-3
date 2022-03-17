$(document).ready(function(){
    $("body").on("click", ".viewBikeBtn", function(){
        event.preventDefault();
        var href = $(this).attr('href');
        console.log(href);
        $.get(href,function(bike,status){
            document.getElementById('inforBikeForm').action = "/transaction?id=" + bike.id;
            document.getElementById("idB").innerHTML = "Thông tin xe " + bike.id;
            document.getElementById("idBike").innerHTML = "Mã xe: " + bike.id;
            if(bike.status==0) document.getElementById("addBike").innerHTML = "Vị trí hiện tại: " + bike.parking.nameParking;
            else document.getElementById("addBike").innerHTML = "Vị trí cũ: " + bike.parking.nameParking;
            document.getElementById("priceBike").innerHTML = "Giá xe: " + bike.categoryBike.priceBike + " vnd";
            document.getElementById("typeBike").innerHTML = "Loại xe: " + bike.categoryBike.categoryBike;
            document.getElementById("energyBike").innerHTML = "Dung lượng pin: " + bike.energy + " mA";
            document.getElementById("image").src = "/image/" + bike.categoryBike.image;
            if(bike.status){
                document.getElementById("statusBike").innerHTML = "Trạng thái: Đã thuê";
            }
            else {
                document.getElementById("statusBike").innerHTML = "Trạng thái: Chưa thuê";
            }
        });
        $('.inforBike #inforBike').modal();
    });
});
