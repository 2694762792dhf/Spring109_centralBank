//自定义全新校验规则
$.validator.addMethod("chinese",function (value,element){
    let pattern=/^[\u4e00-\u9fa5]{2,25}$/;
    return this.optional(element)||(pattern.test(value));
},"请输入中文名字")

$.validator.addMethod("password",function (value,element){
    let pattern=/^[1-9]\d{5}(18|19|20|(3\d))\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
    return this.optional(element)||(pattern.test(value));
},"请输入正确的证件号")

$.validator.addMethod("statekey",function (value,element){
    let pattern = value;
    return this.optional(element)|| value!="0";
},"状态为必选项")

$.validator.addMethod("gradekey",function (value,element){
    let pattern = value;
    return this.optional(element)|| value!="0";
},"职级为必选项")

$.validator.addMethod("str",function (value,element){
    let len_check=$('input:checked').length;
    return  len_check!=0;
}, "补贴为必选项")

$.validator.addMethod('validateAmount', function (value, element) {
    var regExp = /^(0\.\d{1,2}|[1-9]\d{0,3}(\.\d{1,2})?|9999(\.00)?)$/;
    return this.optional(element) || regExp.test(value);
}, '请输入有效的金额（最小0.01，最大9999.99）');

$('#subsidyForm').validate({
    errorPlacement : function(error, element) {
        if (element.is(":checkbox")){
            error.appendTo(element.parent().parent());
        } else{
            error.insertAfter(element);
        }
    }
})
