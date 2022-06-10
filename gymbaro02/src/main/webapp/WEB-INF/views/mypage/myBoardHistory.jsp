<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link rel="stylesheet" href="${contextPath}/resources/css/myPage03.css">
     <title>탭메뉴</title>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

//날짜 형식 체크 및 변경
function checkDateFormat(obj) {
	$("#searchForm [name='radioTabGuide']").attr("checked", false);
	$("#searchForm [name='period']").val('input');

	if($(obj).val() != "") {
		var only_nos = $(obj).val().replace(/[^0-9]/g, '');

		if (only_nos.length == 8) {
			var year = only_nos.substr(0, 4);
			var month = only_nos.substr(4, 2);
			var day = only_nos.substr(6, 2);
			var chg_value = year + "." + month + "." + day;
			// var hidden_chg_value = year + "-" + month + "-" + day;

			if (month > 12 || day > 31) {
				alert("날짜 형식을 잘못 입력하셨습니다.");
				chg_value = "";
			}

			$(obj).val(chg_value);
		} else {
			alert("날짜 형식을 잘못 입력하셨습니다.");
			$(obj).val("");
		}
	}
}

// 시작 기간 얻기
function setStartDate(type) {
	var time_val = "";
	var now = new Date();

	if(type == "1week") {
		time_val = now.getTime() - (7 * 24 * 60 * 60 * 1000);
	} else if(type == "1month") {
		time_val = now.getTime() - (30 * 24 * 60 * 60 * 1000);
	} else if(type == "3month") {
		time_val = now.getTime() - (91 * 24 * 60 * 60 * 1000);
	}

	if(time_val != "") {
		now.setTime(time_val);
	}

	var year= now.getFullYear();
	var mon = (now.getMonth() + 1) > 9 ? ''+(now.getMonth() + 1) : '0'+(now.getMonth() + 1);
	var day = now.getDate() > 9 ? '' + now.getDate() : '0' + now.getDate();

	return year + '.' + mon + '.' + day + "|" + year + '-' + mon + '-' + day;
}

// 기간 설정
function setPeriod(obj, type){
	// Type Setting
	$("#searchForm [name='period']").val(type);

	// 기간 검색 부분 Init
	$("#searchForm [name='dt_fr']").val('');
	$("#searchForm [name='dt_to']").val('');

	// 기간 부분 조건에 따라 Setting
	var now = new Date();

	var year= now.getFullYear();
	var mon = (now.getMonth() + 1) > 9 ? ''+(now.getMonth() + 1) : '0'+(now.getMonth() + 1);
	var day = now.getDate() > 9 ? '' + now.getDate() : '0' + now.getDate();

	var now_date = year + '.' + mon + '.' + day;

	if(type != '') {
		var start_date = setStartDate(type);
		var a_date = start_date.split('|');

		$("#searchForm [name='dt_to']").val(now_date);
		$("#searchForm [name='dt_fr']").val(a_date[0]);
	}
}

function search(){
	if($("#searchForm [name='period']").val() == 'input' && ($("#searchForm [name='dt_fr_input']").val() == '' || $("#searchForm [name='dt_to_input']").val() == '')){
		alert("검색 기간을 설정해 주세요");
		return false;
	}

	$('#searchForm').submit();
}

$(document).ready(function() {
	$("#cbx_chkAll").click(function() {
		if($("#cbx_chkAll").is(":checked")) $("input[name=chk]").prop("checked", true);
		else $("input[name=chk]").prop("checked", false);
	});

	$("input[name=chk]").click(function() {
		var total = $("input[name=chk]").length;
		var checked = $("input[name=chk]:checked").length;

		if(total != checked) $("#cbx_chkAll").prop("checked", false);
		else $("#cbx_chkAll").prop("checked", true); 
	});
});


//테이블내 페이징
function pagination() {
	  var req_num_row = 5;
	  var $tr = jQuery("tbody tr");
	  var total_num_row = $tr.length;
	  var num_pages = 0;
	  if (total_num_row % req_num_row == 0) {
	    num_pages = total_num_row / req_num_row;
	  }
	  if (total_num_row % req_num_row >= 1) {
	    num_pages = total_num_row / req_num_row;
	    num_pages++;
	    num_pages = Math.floor(num_pages++);
	  }

	  jQuery(".pagination").append('<li><a class="prev">Previous</a></li>');

	  for (var i = 1; i <= num_pages; i++) {
	    jQuery(".pagination").append("<li><a>" + i + "</a></li>");
	    jQuery(".pagination li:nth-child(2)").addClass("active");
	    jQuery(".pagination a").addClass("pagination-link");
	  }

	  jQuery(".pagination").append('<li><a class="next">Next</a></li>');

	  $tr.each(function (i) {
	    jQuery(this).hide();
	    if (i + 1 <= req_num_row) {
	      $tr.eq(i).show();
	    }
	  });

	  jQuery(".pagination a").click(".pagination-link", function (e) {
	    e.preventDefault();
	    $tr.hide();
	    var page = jQuery(this).text();
	    var temp = page - 1;
	    var start = temp * req_num_row;
	    var current_link = temp;

	    jQuery(".pagination li").removeClass("active");
	    jQuery(this).parent().addClass("active");

	    for (var i = 0; i < req_num_row; i++) {
	      $tr.eq(start + i).show();
	    }

	    if (temp >= 1) {
	      jQuery(".pagination li:first-child").removeClass("disabled");
	    } else {
	      jQuery(".pagination li:first-child").addClass("disabled");
	    }
	  });

	  jQuery(".prev").click(function (e) {
	    e.preventDefault();
	    jQuery(".pagination li:first-child").removeClass("active");
	  });

	  jQuery(".next").click(function (e) {
	    e.preventDefault();
	    jQuery(".pagination li:last-child").removeClass("active");
	  });
	}

	jQuery("document").ready(function () {
	  pagination();

	  jQuery(".pagination li:first-child").addClass("disabled");
	});
</script>
<style type="text/css">
#articleNo_label {
    color: #878787;
    font-size: 11px;
    font-weight:lighter;
}

#comment_cnt_label {
	color:tomato;
	font-weight: lighter;
}

.date_and_cnt_label {
	font-size:12px;
	color: #666666;
	font-weight: lighter;
}

#article_title_atag {
	font-size:14px;
	display: inline-block;
}

.comment_div_box {
	display: flex;
    flex-direction: column;
}

.comment_div_box label, .comment_span {
	font-size:13px;
	font-weight:lighter;
	color: #878787;
}

</style>
</head>
<body>
 <div class="myPage_box wrap show">
  <div id="content" class="sub_wrap">
  	<div class="align_rt">
      <div class="myPage03_box tab_menu">
     	<div class="first_content">
     	<c:if test="${search_type eq 'article'}">
          <div class="tab_btn">
               <ul>
               		<li class="active"><a href="${contextPath}/mypage/listMyBoardHistory.do?search_type=article">작성 글</a></li>
                    <li><a href="${contextPath}/mypage/listMyBoardHistory.do?search_type=comment">작성 댓글</a></li>
                    <li><a href="${contextPath}/mypage/listMyBoardHistory.do?search_type=review">작성 리뷰</a></li>
               </ul>
               
                <form name="searchForm" id="searchForm"  method="get" action="${contextPath}/mypage/listMyBoardHistory.do">
				<input type="hidden" name="period" value=""/>
				<input type="hidden" name="page" value="1"/>
				<div class="n-table-filter">
					<div class="n-radio-tab">
						<input type="radio" id="radioTabGuide0" name="radioTabGuide" onClick="setPeriod(this,'1week');" >
						<label for="radioTabGuide0">1주일</label>

						<input type="radio" id="radioTabGuide1" name="radioTabGuide" onClick="setPeriod(this,'1month');" >
						<label for="radioTabGuide1">1개월</label>

						<input type="radio" id="radioTabGuide2" name="radioTabGuide" onClick="setPeriod(this,'3month');" >
						<label for="radioTabGuide2">3개월</label>

						<input type="radio" id="radioTabGuide3" name="radioTabGuide" onClick="setPeriod(this,'');" checked>
						<label for="radioTabGuide3">전체 시기</label>
					</div>
						<input type="hidden" name="search_type" value="article" />
					<div class="n-datepicker sb">
						<input type="text" class="n-input" name="dt_fr" value="" placeholder="-" onblur="checkDateFormat(this);">
						<img class="ui-datepicker-trigger" src="//image.msscdn.net/skin/musinsa/images/ico_calendar.png?20200528" alt="날짜 선택" title="날짜 선택">
					</div>
					<div class="n-datepicker">
						<input type="text" class="n-input" name="dt_to" value="" placeholder="-" onblur="checkDateFormat(this);">
						<img class="ui-datepicker-trigger" src="//image.msscdn.net/skin/musinsa/images/ico_calendar.png?20200528" alt="날짜 선택" title="날짜 선택">
					</div>
					<div class="n-select">
						<select name="state" id="ui-id-1" style="display: none;">
							<option value="">전체 보기</option>
														<option value="1">답변 대기</option>
														<option value="10">접수</option>
														<option value="20">업체 문의 중</option>
														<option value="30">물품 도착 확인</option>
														<option value="40">교환 상품 발송</option>
														<option value="50">답변 완료</option>
						</select>
						<span class="ui-selectmenu-button ui-widget ui-state-default ui-corner-top" tabindex="0" id="ui-id-1-button" role="combobox" aria-expanded="true" aria-autocomplete="list" aria-owns="ui-id-1-menu" aria-haspopup="true" style="width: 100%;" aria-activedescendant="ui-id-2" aria-labelledby="ui-id-2" aria-disabled="false">
							<span class="ui-icon ico ico-sm ico-ar00"></span>
							<span class="ui-selectmenu-text">전체 보기</span>
						</span>
					</div>
					<button type="button" class="n-btn btn-sm btn-accent" onclick="search();">조회</button>
				</div>
			</form>   
          </div>
          <div class="tab_cont">
               <table class="myPage03_table">
               	<thead>
                   <tr class="notice_board_first_tr">
                   		<td width="5%"><input type="checkbox" id="cbx_chkAll"></td>
                   		<td width="5%"></td>
                    	<td width=60%>제목</td>
                    	<td width=15%>작성일</td>
                    	<td width=5%>조회</td>
                    </tr>
                </thead>
                <tbody>
                  <c:choose>
                  <c:when test="${empty myBoardItem}">
                  	<tr>
                    	<td colspan="3">게시글 목록이 없습니다.</td>
                    </tr>
                  </c:when>
                  <c:otherwise>
                	<c:forEach var="item" items="${myBoardItem}">
                    <tr>
                    	<td><input type="checkbox" name="chk" value="${item.articleNo}"></td>
                    	<td><label id="articleNo_label">${item.articleNo}</label></td>
                    	<td style="text-align:left;text-overflow: ellipsis;">
                    		<a id="article_title_atag" href="${contextPath}/community/communityDetail.do?articleNo=${item.articleNo}">${item.title}</a>
                    		<label id="comment_cnt_label">[${item.comment_cnt}]</label>
                    	</td>
                    	<td><label class="date_and_cnt_label"><fmt:formatDate value="${item.writeDate}" pattern="yyyy.MM.dd"/></label></td>
                    	<td><label class="date_and_cnt_label">${item.view_cnt}</label></td>
                    </tr>
                    </c:forEach>
                  </c:otherwise>
                  </c:choose>
				</tbody>
               </table>
               <ul class="pagination">
    
  				</ul>
          </div>
          </c:if> 
     	<c:if test="${search_type eq 'comment'}">
          <div class="tab_btn">
               <ul>
               		<li><a href="${contextPath}/mypage/listMyBoardHistory.do?search_type=article">작성 글</a></li>
                    <li class="active"><a href="${contextPath}/mypage/listMyBoardHistory.do?search_type=comment">작성 댓글</a></li>
                    <li><a href="${contextPath}/mypage/listMyBoardHistory.do?search_type=review">작성 리뷰</a></li>
               </ul>
               
                <form name="searchForm" id="searchForm"  method="get" action="${contextPath}/mypage/listMyBoardHistory.do?search_type=comment">
				<input type="hidden" name="period" value=""/>
				<input type="hidden" name="page" value="1"/>
				<div class="n-table-filter">
					<div class="n-radio-tab">
						<input type="radio" id="radioTabGuide0" name="radioTabGuide" onClick="setPeriod(this,'1week');" >
						<label for="radioTabGuide0">1주일</label>

						<input type="radio" id="radioTabGuide1" name="radioTabGuide" onClick="setPeriod(this,'1month');" >
						<label for="radioTabGuide1">1개월</label>

						<input type="radio" id="radioTabGuide2" name="radioTabGuide" onClick="setPeriod(this,'3month');" >
						<label for="radioTabGuide2">3개월</label>

						<input type="radio" id="radioTabGuide3" name="radioTabGuide" onClick="setPeriod(this,'');" checked>
						<label for="radioTabGuide3">전체 시기</label>
					</div>
						<input type="hidden" name="search_type" value="comment" />
					<div class="n-datepicker sb">
						<input type="text" class="n-input" name="dt_fr" value="" placeholder="-" onblur="checkDateFormat(this);">
						<img class="ui-datepicker-trigger" src="//image.msscdn.net/skin/musinsa/images/ico_calendar.png?20200528" alt="날짜 선택" title="날짜 선택">
					</div>
					<div class="n-datepicker">
						<input type="text" class="n-input" name="dt_to" value="" placeholder="-" onblur="checkDateFormat(this);">
						<img class="ui-datepicker-trigger" src="//image.msscdn.net/skin/musinsa/images/ico_calendar.png?20200528" alt="날짜 선택" title="날짜 선택">
					</div>
					<div class="n-select">
						<select name="state" id="ui-id-1" style="display: none;">
							<option value="">전체 보기</option>
														<option value="1">답변 대기</option>
														<option value="10">접수</option>
														<option value="20">업체 문의 중</option>
														<option value="30">물품 도착 확인</option>
														<option value="40">교환 상품 발송</option>
														<option value="50">답변 완료</option>
						</select>
						<span class="ui-selectmenu-button ui-widget ui-state-default ui-corner-top" tabindex="0" id="ui-id-1-button" role="combobox" aria-expanded="true" aria-autocomplete="list" aria-owns="ui-id-1-menu" aria-haspopup="true" style="width: 100%;" aria-activedescendant="ui-id-2" aria-labelledby="ui-id-2" aria-disabled="false">
							<span class="ui-icon ico ico-sm ico-ar00"></span>
							<span class="ui-selectmenu-text">전체 보기</span>
						</span>
					</div>
					<button type="button" class="n-btn btn-sm btn-accent" onclick="search();">조회</button>
				</div>
			</form>   
          </div>
          <div class="tab_cont">
               <table class="active myPage03_table">
               	<thead>
                    <tr class="notice_board_first_tr">
                    	<td width="5%" style="padding: 10px 0;"><input type="checkbox" id="cbx_chkAll"></td>
                    	<td width="85%">댓글</td>
                    </tr>
                </thead>
                <tbody>
                  <c:choose>
                   <c:when test="${empty myBoardItem}">
                  	<tr>
                    	<td colspan="2">댓글 목록이 없습니다.</td>
                    </tr>
                  </c:when>
                  <c:otherwise>
                    <c:forEach var="item" items="${myBoardItem}">
                    <tr>
                    	<td style="vertical-align: baseline; padding: 10px 0;"><input type="checkbox" name="chk" value="${item.commentNo}"></td>
                    	<td style="text-align:left; padding: 10px 0;">
                    		<a id="article_title_atag" href="${contextPath}/community/communityDetail.do?articleNo=${item.articleNo}">
                    		<div class="comment_div_box">
                    			<label style="color:#333333;text-overflow: ellipsis;">${item.comment_content }</label>
                    			<label><fmt:formatDate value="${item.regDate}" pattern="yyyy.MM.dd"/></label>
                    			<span class="comment_span">
                    				${item.title}<label id="comment_cnt_label">&nbsp;[${item.comment_cnt}]</label>
                    			</span>
                    		</div>
                    		</a>
                    	</td>
                    </tr>
                    </c:forEach>
                  </c:otherwise>
                 </c:choose>
                </tbody>
               </table>
               <ul class="pagination">
    
  			  </ul>
          </div>
          </c:if> 
 		  <c:if test="${search_type eq 'review'}">
          <div class="tab_btn">
               <ul>
               		<li><a href="${contextPath}/mypage/listMyBoardHistory.do?search_type=article">작성 글</a></li>
                    <li><a href="${contextPath}/mypage/listMyBoardHistory.do?search_type=comment">작성 댓글</a></li>
                    <li class="active"><a href="${contextPath}/mypage/listMyBoardHistory.do?search_type=review">작성 리뷰</a></li>
               </ul>
               
                <form name="searchForm" id="searchForm"  method="get" action="${contextPath}/mypage/listMyBoardHistory.do?search_type=review">
				<input type="hidden" name="period" value=""/>
				<input type="hidden" name="page" value="1"/>
				<div class="n-table-filter">
					<div class="n-radio-tab">
						<input type="radio" id="radioTabGuide0" name="radioTabGuide" onClick="setPeriod(this,'1week');" >
						<label for="radioTabGuide0">1주일</label>

						<input type="radio" id="radioTabGuide1" name="radioTabGuide" onClick="setPeriod(this,'1month');" >
						<label for="radioTabGuide1">1개월</label>

						<input type="radio" id="radioTabGuide2" name="radioTabGuide" onClick="setPeriod(this,'3month');" >
						<label for="radioTabGuide2">3개월</label>

						<input type="radio" id="radioTabGuide3" name="radioTabGuide" onClick="setPeriod(this,'');" checked>
						<label for="radioTabGuide3">전체 시기</label>
					</div>

					<div class="n-datepicker sb">
						<input type="text" class="n-input" name="dt_fr" value="" placeholder="-" onblur="checkDateFormat(this);">
						<img class="ui-datepicker-trigger" src="//image.msscdn.net/skin/musinsa/images/ico_calendar.png?20200528" alt="날짜 선택" title="날짜 선택">
					</div>
					<div class="n-datepicker">
						<input type="text" class="n-input" name="dt_to" value="" placeholder="-" onblur="checkDateFormat(this);">
						<img class="ui-datepicker-trigger" src="//image.msscdn.net/skin/musinsa/images/ico_calendar.png?20200528" alt="날짜 선택" title="날짜 선택">
					</div>
					<div class="n-select">
						<select name="state" id="ui-id-1" style="display: none;">
							<option value="">전체 보기</option>
														<option value="1">답변 대기</option>
														<option value="10">접수</option>
														<option value="20">업체 문의 중</option>
														<option value="30">물품 도착 확인</option>
														<option value="40">교환 상품 발송</option>
														<option value="50">답변 완료</option>
						</select>
						<span class="ui-selectmenu-button ui-widget ui-state-default ui-corner-top" tabindex="0" id="ui-id-1-button" role="combobox" aria-expanded="true" aria-autocomplete="list" aria-owns="ui-id-1-menu" aria-haspopup="true" style="width: 100%;" aria-activedescendant="ui-id-2" aria-labelledby="ui-id-2" aria-disabled="false">
							<span class="ui-icon ico ico-sm ico-ar00"></span>
							<span class="ui-selectmenu-text">전체 보기</span>
						</span>
					</div>
					<button type="button" class="n-btn btn-sm btn-accent" onclick="search();">조회</button>
				</div>
			</form>   
          </div>
          <div class="tab_cont">
                <table class="myPage03_table">
                   <tr class="notice_board_first_tr">
                    	<td width=20%>정보</td>
                    	<td width=40%>내용</td>
                    	<td width=20%>작성일</td>
                    	<td width=20%>후기 종류</td>
                    </tr>
                    <tr>
                    	<td colspan="4">리뷰 내역이 없습니다.</td>
                    </tr>
               </table>
          </div>
          </c:if> 
          </div>
     </div>
    </div>
   </div>
</div>

</body>
</html>