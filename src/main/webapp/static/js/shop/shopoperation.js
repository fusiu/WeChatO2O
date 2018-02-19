/**
 *
 */
$(function(){
    var initUrl ='/shopadmin/getshopinitinfo';
    var registerShopUrl ='/shopadmin/registershop';
    getShopInitInfo();
    function getShopInitInfo() {
        /**
         * 动态获取店铺初始化信息，区域和店铺类型
         * 第一次执行$.getJSON() 方法失败，是因为该方法中存在错误，调用的map() 方法写成了 mapp()
         * 方法中只要存在错误就不会执行，因此得不到预期的效果
         */
        $.getJSON(initUrl,function(data){
            if(data.success){
                var tempHtml = '';
                var tempAreaHtml = '';
                /**
                 * map 是 jquery 中的新方法
                 * 以 map 的形式遍历数据
                 */
                data.shopCategoryList.map(function(item,index){
                    tempHtml += '<option data-id="'+item.shopCategoryId+'">'+item.shopCategoryName+'</option>';
                });
                data.areaList.map(function(item,index){
                    tempAreaHtml += '<option data-id="'+item.areaId+'">'+item.areaName+'</option>';
                });
                $('#shop-category').html(tempHtml);
                $('#area').html(tempAreaHtml);
            }
        });
        /**
         * 表单提交时候执行的放啊
         */
        $('#submit').click(function () {
            var shop = {};
            shop.shopName = $('#shop-name').val();
            shop.shopAddr = $('#shop-addr').val();
            shop.phone = $('#shop-phone').val();
            shop.shopDesc = $('#shop-desc').val();
            shop.shopCategory = {
                shopCategoryId : $('#shop-category').find('option').not(function(){
                    return !this.selected;
                }).data('id')
            };
            shop.area = {
                areaId:$('#area').find('option').not(function(){
                    return !this.selected;
                }).data('id')
            };
            var shopImg = $('#shop-img')[0].files[0];
            var formData = new FormData();
            formData.append('shopImg',shopImg);
            formData.append('shopStr',JSON.stringify(shop));
            var verifyCodeActual=$('#j_kaptcha').val();
            if (!verifyCodeActual){
                $.toast('请输入验证码！');
                return;
            }
            formData.append('verifyCodeActual',verifyCodeActual);
            /**
             *TypeError: 'append' called on an object that does not implement interface FormData.
             * 原因：将 processData 写成了 proceessData
             */
            $.ajax({
                url:registerShopUrl,
                type:'POST',
                data:formData,
                contentType:false,
                processData:false,
                cache:false,
                success:function(data){
                    if (data.success){
                        $.toast('提交成功！');
                        registerShopUrl = "#";
                        $('#submit').click(function () {
                            $.toast('已经注册成功，请不要重复提交');
                        })
                    }else{
                        $.toast('提交失败！'+data.errMsg);
                    }
                    /**
                     * 每次提交，无论成功还是失败都要刷新验证码
                     */
                    $('#kaptcha-img').click();
                }
            });

        });
    }

})