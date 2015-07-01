(function($) {
    $(function() {
    	var $ = jQuery,
    	serverPath = basePath+'filemanager/clientupload',
        $list = $('#thelist'),
        $btn = $('#ctlBtn'),
        state = 'pending',
        uploader=null,
        md5=null,
        current_file = null;
    	var formData = {};
        var log = (function() {
            var dom = $('#log');

            return function( str ) {
                dom.html('<p>' + str + '</p>')
            }
        })();
        
        uploader = WebUploader.create({
            pick: '#filePicker',
            //runtimeOrder: 'flash',
            swf: './Uploader.swf',
            fileNumLimit : 1,
            compress:null,
            server: serverPath
            	
        });
        // 当有文件被添加进队列的时候
        uploader.on( 'fileQueued', function( file ) {
            $list.html( '<div id="' + file.id + '" class="item">' +
                '<h4 class="info">' + file.name + '</h4>' +
                '<p class="state">等待上传...</p>' +
            '</div>' );
           // console.log(uploader.getFiles());
        });
        uploader.on('beforeFileQueued',function(file ){
        	uploader.reset();
        	
        });
        // 文件上传过程中创建进度条实时显示。
        uploader.on( 'uploadProgress', function( file, percentage ) {
            var $li = $( '#'+file.id ),
                $percent = $li.find('.progress .progress-bar');

            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<div class="progress progress-striped active">' +
                  '<div class="progress-bar" role="progressbar" style="width: 0%;height:5px">' +
                  '</div>' +
                '</div>').appendTo( $li ).find('.progress-bar');
            }
            console.log($li.html());
            $li.find('p.state').text('上传中');

            $percent.css( 'width', percentage * 100 + '%' );
        });
        //文件成功、失败处理
        uploader.on( 'uploadSuccess', function( file ) {
            $( '#'+file.id ).find('p.state').text('已上传');
        });

        uploader.on( 'uploadError', function( file ) {
            $( '#'+file.id ).find('p.state').text('上传出错');
        });

        uploader.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').fadeOut();
        });
        uploader.on('fileQueued', function( file ) {
            var start =  +new Date();
            current_file = file;
            // 返回的是 promise 对象
            this.md5File(file)
                // 可以用来监听进度
                .progress(function(percentage) {
                    // console.log('Percentage:', percentage);
                })
                // 处理完成后触发
                .then(function(ret) {
                    // console.log('md5:', ret);
                	md5 = ret;
                	
//                    var end = +new Date();
//                    log('HTML5: md5 ' + file.name + ' cost ' + (end - start) + 'ms get value: ' + ret);
                });
        });
        $btn.on( 'click', function() {
        	
        	if(uploader.getFiles().length<1 ){
        		$("#msg").empty().html('请选择文件！').fadeIn(50).fadeOut(5000);
        		return;
        	}
        	if ( state === 'uploading' ) {
                uploader.stop();
            } else {
            	
            	 checkMd5(md5);

            }
        });
        function checkMd5(md5){
        	if(md5!=null){
        		$('#md5').val(md5);
        		$('#form1').attr('action',serverPath).ajaxSubmit(function(msg){
        			msg = eval('('+msg+')');
        			if(  1 == msg.exist){
        				uploader.trigger("uploadSuccess",current_file);
        				return ;
				    }else{
				    	formData["md5"] = md5;
				    	
			        	uploader.option('formData',formData) ;
			        	
			            uploader.upload();
				    }
                });
	    	}
        	
        }
        uploader.on( 'all', function( type ) {
            if ( type === 'startUpload' ) {
                state = 'uploading';
            } else if ( type === 'stopUpload' ) {
                state = 'paused';
            } else if ( type === 'uploadFinished' ) {
                state = 'done';
            }

            if ( state === 'uploading' ) {
                $btn.text('暂停上传');
            } else {
                $btn.text('开始上传');
            }
        });
        /*WebUploader.create({
            pick: '#filePicker2',
            swf: './Uploader.swf',
            runtimeOrder: 'flash'
        }).on('fileQueued', function( file ) {
            var start =  +new Date();

            // 返回的是 promise 对象
            this.md5File(file, 0, 1 * 1024 * 1024)

                // 可以用来监听进度
                .progress(function(percentage) {
                    // console.log('Percentage:', percentage);
                })

                // 处理完成后触发
                .then(function(ret) {
                    // console.log('md5:', ret);
                    
                    var end = +new Date();
                    log('Flash: md5 ' + file.name + ' cost ' + (end - start) + 'ms get value: ' + ret);
                });
        });*/
    });
})(jQuery);