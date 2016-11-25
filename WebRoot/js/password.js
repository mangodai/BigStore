/**
 * 检测密码
 */
function check() {
	var pass1=document.getElementById("oldpassword").value;
	var pass2=document.getElementById("password").value;
	alert(pass1.length);
	if (pass1.length =='0') {
		alert("新密码不能空");
		return;
	}
	if (pass1 != pass2) {
		alert("两次输入的密码不一致");
		return;
	}
}
