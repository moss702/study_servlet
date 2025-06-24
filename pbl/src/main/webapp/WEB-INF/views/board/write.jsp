<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp" %>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/nav.jsp" %>
    <div class="container p-0">
        <main>
            <form method="post">
                <div class="small border-bottom border-3 border-secondary p-0 pb-2"><a href="#" class="small"><span class="text-primary">자유게시판</span> 카테고리</a></div>
                <div class="small p-0 py-2">
                    <input placeholder="글 제목 입력" class="form-control" name="title" id="title">
                </div>
                <div class="p-0 py-2 ps-1 border-top border-1 border-muted">
                    <textarea name="content" id="editor1" class="form-control resize-none">
                        
                    </textarea>
                </div>
               
				<div class="d-grid my-2 attach-area">
					<div class="small my-1 border-bottom border-1 border-muted p-0 pb-2"><i class="fa-solid fa-paperclip"></i> 첨부파일</div>
					<label class="btn btn-info">파일을 첨부하려면 클릭<input type="file" multiple class="d-none" id="f1"></label>
				</div>
                
                <div class="my-2">
                    <button class="btn btn-secondary btn-sm"><i class="fa-solid fa-list-ul"></i> 목록</button>
                    <div class="float-end">
                        <button class="btn btn-outline-secondary btn-sm"><i class="fa-solid fa-pen"></i>  글 등록</button>
                    </div>
                </div>
                <input type="hidden" name="id" value="${member.id}" />
                <input type="hidden" name="cno" value="2" />
            </form>
        </main>
    </div>
    <script>
        $(function() {
            CKEDITOR.replace('editor1', {
                height:400
            });
        });        
    </script>
   	<script>
	$(function() {

		//return true / false
		function validateFiles(files) {
			const MAX_COUNT = 5;
			const MAX_FILE_SIZE = 10 * 1024 * 1024; //10mb
			const MAX_TOTAL_SIZE = 50 * 1024 * 1024; //50mb
			const BLOCK_EXT = ['exe', 'sh', 'jsp', 'java', 'class', 'html', 'js'];		

			//파일 개수 체크
			if(files.length > MAX_COUNT){
				alert('파일은 최대 5개만 업로드 가능합니다');
				return false;
			}
			 
			//파일 확장자 체크 & 파일들의 총 용량제한 체크
			let totalSize = 0;
			const isValid = files.every(f => {
				const ext = f.name.split(".").pop().toLowerCase(); //확장자 추출 및 소문자 변환
				totalSize += f.size;
				return !BLOCK_EXT.includes(ext) && f.size <= MAX_FILE_SIZE; //위에서 명시한 확장자가 아닐경우
			}) && totalSize <= MAX_TOTAL_SIZE

			if(!isValid) {
				alert('다음 파일 확장자는 업로드가 불가합니다.\n - [exe, sh, jsp, java, class, html, js] \n 또한 각 파일은 10MB 이하, 전체 합계는 50MB 이하여야 합니다.')
			}
			return isValid;
		}
		
		$("#f1").change(function() {
 			event.preventDefault();
			const formData = new FormData();
		
			const files = this.files; //여기서 this는 input
			for(let i = 0; i < files.length; i++){
				formData.append("f1", files[i]);
			}
		
 		/* $("#uploadForm").submit(function() { //파일 업로드 버튼 클릭시
 			event.preventDefault();
			const formData = new FormData(this);
			const files = this.f1.files; */

			const valid = validateFiles([...files]);
			/* console.log(formData, valid); */
			if(!valid) {
				return;
			}
			
			$.ajax({
				url : '${cp}/upload',
				method : 'POST',
				data : formData,
				processData : false, //data를 queryString으로 쓰지 않겠다
				contentType : false, 
				//multipart/form-data;가 들어가야 하지만, false:내가 정의하지않겠다.
				//이후에 나오게 될 브라우저 정보도 포함시키겠다. == 즉 기본 브라우저 설정을 따르겠다는 옵션
				success : function(data){
					console.log(data);
					
					//확인용
					for(let a in data){
						$(".container").append("<p>" + data[a].origin + "</p>");
					}
				}
			})
		})
	})
	</script>
<%@ include file="../common/footer.jsp" %>
</body>
</html>