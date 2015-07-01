var upids = '';
function getNodeParents(nodeId){
	var upid = null;
	
	for(var i in ChineseDistricts){
		//console.log(obj);
		for(var key in ChineseDistricts[i] ){
			//console.log(key);
			if(key == nodeId){
				//console.log(upid);
				upid = i;
				break;
			}					
		}
	}
	
	if(upid!=null && upid!=0){
		 getNodeParents(upid);
		
	}
	upids = upids+upid+",";
}
function getNodeName(nodeId){

	for(var i in ChineseDistricts){
		var tmp = ChineseDistricts[i] ;
		//console.log(obj);
		for(var key in tmp){
			//console.log(key);
			if(key == nodeId){
				return tmp[key];
				
			}					
		}
	}
	return '';
}
function initTree(district,selector){
	var _selector = '.distpicker3';
	if(selector){
		_selector = selector;
	}
	upids = '';
	getNodeParents(district);
	upids +=district;
	var nodes = upids.split(',');
	var div = $(_selector);
	for(var i in nodes){
		var node = nodes[i];
		if(node!='' && node!=0){
			var name = getNodeName(node);
			//console.log(name+i);
			var select = $(div).find('select:eq('+(i-1)+')');
			//console.log( select.size()+name+i);
			if(i ==1){
				$(select).attr("data-province",name);
				
			}else if(i == 2){
				$(select).attr('data-city',name);
			}else {
				$(select).attr('data-district',name);
			}
		}
	}			
}

//0,3,76, 1173 河北省 邯郸市 大名县 



/*$(document).ready(function() {
	initTree(1173);
	$(".distpicker3").distpicker();
});*/