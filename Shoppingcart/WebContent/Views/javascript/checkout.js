$(document).ready(function() {

    $('#apply-coupon-btn').on('click',function(){
        var amount=$('#amount').text();
        var coupon=$('#couponcode').val();
        console.log(coupon);
        $.ajax({
            method:'POST',
            url: 'http://localhost:8080/Shoppingcart/DiscountServlet',
            data: {"amount":amount,"coupon":coupon},
             success: function(response) {
                console.log(response);
                var amount=$('#amount').text();
                var i=amount-response;
                $('#amount').text(i);
                $('#discount').removeAttr('hidden');
                $('#discountamount').text(response);
            },
            error: function(xhr, status, error) {
                console.error('AJAX request failed:', error);
            }
    });
    });
});


