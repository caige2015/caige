/*jQuery(function($) {
	// 备份jquery的ajax方法
	var _ajax = $.ajax;
	// 重写ajax方法，先判断登录在执行success函数
	$.ajax = function(opt) {
		var _success = opt && opt.success || function(a, b) {
		};
		var _opt = $.extend(opt, {
			success : function(data, textStatus) {
				// 如果后台将请求重定向到了登录页，则data里面存放的就是登录页的源码，这里需要找到data是登录页的证据(标记)
				if (data && data.indexOf('login_flag') != -1) {
					window.location.href = getRootPath() + "/login";
					return;
				}
				_success(data, textStatus);
			}
		});
		_ajax(_opt);
	};
});*/

//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}