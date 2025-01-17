<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<title>Insert title here</title>
<style type="text/css">
body {
	font-family: 'Noto Sans KR', sans-serif;
}

.main_container {
	width:730px;
}

.first_content {
	margin-top:70px;
	border-top:2px solid #C4C4C4;
}

.first_text {
	position:relative;
	top:-15px;
	margin-bottom:50px;
}

.first_text span {

	font-size: 21px;
	background:white;
	padding: 0 15px;
}

.first_text span b {
	color: #184798;
}

.step_bar {
	display:flex;
	justify-content:space-between;
}

.bars {
	width:989px;
	height:43px;
	display:flex;
	justify-content:center;
	align-items:center;
}

.bars span {
	font-size:18px;
}

.step_bar_01 {
	background: #184798;
}

.step_bar_01 span {
	color:white;
}

.step_bar_02, .step_bar_03 {
	background: #f0f0f0;
}

.content_first_text {
	display:flex;
	align-items:center;
	margin-top:50px;
	margin-bottom:10px;
}

.content_first_text span {
	margin-left:10px;
}

.content_first_text div {
	width:10px;
	height:10px;
	border-radius:3px;
	background: #184798;
}

.content_bar {
	overflow-y:scroll;
	height:140px;
	background: #f0f0f0;
	border: 1px solid #8f8f8f;
}

.content_bar div {
	height:170px;
}

.agree_checkbox {
	display:flex;
	flex-direction:column;
	align-items:start;
	margin-top:40px;
}

.checkbox_02 {
	margin-top:20px;
}

.button_box {
	display:flex;
	justify-content:space-between;
	margin-top:40px;
	height:50px;
}

.button_box button {
	border:none;
	border-radius:2px;
	width:360px;
   height:50px;
}

.button_box button span {
	font-size:20px;
	color:white;
}

#button_01 {
	background: #c4c4c4;
}

#button_02 {
	background: #184798;
}
</style>
<script type="text/javascript">
function agreeCheck(type){
	var checkItem1 = $("#checkItem1").is(":checked");
	var checkItem2 = $("#checkItem2").is(":checked");
	
	if(checkItem1 == false || checkItem2 == false){
		alert("모든 약관에 동의를 해주세요!");
		return;
	}else{
		if(type=='common'){
			location.href = "${contextPath}/member/userJoinForm.do?";
		}else if(type=='gym'){
			location.href = "${contextPath}/member/gymJoinForm.do?";
		}
	}
	
}
</script>
</head>
<body>
<div class="main_container">
  <div class="first_content">
	<div class="first_text">
		<span><b>약관 내용</b></span>
	</div>
	<div class="step_bar">
		<div class="step_bar_01 bars"><span>약관 동의</span></div>
		<div class="step_bar_02 bars"><span>본인인증/정보입력</span></div>
		<div class="step_bar_03 bars"><span>가입완료</span></div>
	</div>
	<div class="content_first_text">
		<div></div>
		<span>개인정보 수집 · 이용 동의</span>
	</div>
	<div class="content_bar">
		<div>
			<span style="text-align:start;">"짐바로"는 정보주체의 동의, 「전자정부법」 및 「개인정보 보호법」 등 관련 법령상의 개인정보 보호 규정을 준수하여 이용자의 개인정보 보호 및 권익을 보호하고 개인정보와 관련한 이용자의 고충을 원활하게 처리할 수 있도록 다음과 같은 처리방침을 두고 있습니다.

제1조(개인정보의 처리 목적)
"짐바로"는 다음 각 호에서 열거한 목적을 위하여 최소한으로 개인정보를 처리하고 있습니다. 처리한 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며, 이용 목적이 변경되는 경우에는 「개인정보 보호법」 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행하고 있습니다.

1. 회원가입 및 관리
회원가입, 회원제 서비스 이용 및 제한적 본인 확인절차에 따른 본인확인, 개인식별, 부정이용방지, 비인가 사용방지, 가입 의사 확인, 만 14세 미만 아동 개인정보 수집 시 법정대리인 동의여부 확인, 추후 법정대리인 본인확인, 분쟁 조정을 위한 기록보존, 불만처리 등 민원처리, 고지사항 전달 등
2. 민원사무 처리 및 발급·열람서비스 제공
민원 신청서에 포함된 개인정보는 전자정부법 제9조에 의한 민원사무 처리를 위한 목적으로 민원 접수기관 및 처리기관에서 이용 (민원발급서비스, 민원열람서비스, 최근 신청이력)
3. 생활정보 열람서비스 제공
짐바로 회원의 경우, 전자정부법 제9조의2에 의한 본인의 생활정보 열람
4. 국가보조금 맞춤형서비스 제공
짐바로 회원의 경우, 행정·공공기관에서 관리하고 있는 자격정보를 연계하여 받을 수 있는 혜택, 수급정보를 연계하여 이미 받고 있는 혜택 안내 등
5. 타 행정기관 시스템 연계정보의 처리
다른 행정기관 등이 보유한 자료를 활용하여 민원인이 청구한 행정서비스 및 민원사무 등의 전자적 처리
6. 짐바로 서비스 향상 및 정책평가
신규 서비스 및 원스톱ㆍ맞춤형ㆍ선제적 서비스 개발, 인구통계학적 특성에 따른 서비스 분석 및 서비스의 유효성 및 이용 통계 확인 등

제2조(개인정보의 처리 및 보유기간)
"짐바로"에서 처리하는 개인정보는 수집·이용 목적으로 명시한 범위 내에서 처리하며, 개인정보보호법 및 관련 법령에서 정하는 보유기간을 준용하여 이행하고 있습니다.

1. 짐바로 회원정보
   - 수집근거 : 정보주체의 동의, 전자정부법 시행령 제90조
   - 보유기간 : 탈퇴 후 5일까지
2. 전자민원 신청이력(상담이력 포함)
   - 수집근거 : 정보주체의 동의, 전자정부법 시행령 제90조
   - 보유기간 : 3년
3. 전자민원 증명서(신청서 및 발급물)
   - 수집근거 : 정보주체의 동의, 전자정부법 시행령 제90조
   - 보유기간 : 180일
4. 생활정보
   - 수집근거 : 정보주체의 동의, 전자정부법 제9조의2
   - 보유기간 : 1일 이내
5. 국가보조금 맞춤형서비스
   - 수집근거 : 정보주체의 동의, 전자정부법 시행령 제43조
   - 보유기간 : 정보 조회 후 즉시 파기

제3조(개인정보의 제3자 제공)
"짐바로"는 원칙적으로 정보주체의 개인정보를 수집·이용 목적으로 명시한 범위 내에서 처리하며, 다음의 경우를 제외하고는 정보주체의 사전 동의 없이는 본래의 목적 범위를 초과하여 처리하거나 제3자에게 제공하지 않습니다. 다만, 제5호부터 제9호까지는 공공기관의 경우로 한정합니다.

1. 정보주체로부터 별도의 동의를 받는 경우
2. 다른 법률에 특별한 규정이 있는 경우
3. 정보주체 또는 그 법정대리인이 의사표시를 할 수 없는 상태에 있거나 주소불명 등으로 사전 동의를 받을 수 없는 경우로서 명백히 정보주체 또는 제3자의 급박한 생명, 신체, 재산의 이익을 위하여 필요하다고 인정되는 경우
4. 통계작성 및 학술연구 등의 목적을 위하여 필요한 경우로서 특정 개인을 알아볼 수 없는 형태로 개인정보를 제공하는 경우
5. 개인정보를 목적 외의 용도로 이용하거나 이를 제3자에게 제공하지 아니하면 다른 법률에서 정하는 소관 업무를 수행할 수 없는 경우로서 보호위원회의 심의·의결을 거친 경우
6. 조약, 그 밖의 국제협정의 이행을 위하여 외국정부 또는 국제기구에 제공하기 위하여 필요한 경우
7. 범죄의 수사와 공소의 제기 및 유지를 위하여 필요한 경우
8. 법원의 재판업무 수행을 위하여 필요한 경우
9. 형 및 감호, 보호처분의 집행을 위하여 필요한 경우


제4조(개인정보 처리 위탁)
1. "정부24"는 원활한 개인정보 업무처리 서비스를 위해 다음과 같이 개인정보 처리업무를 위탁 운영하고 있습니다.
가. 위탁받는 자(수탁자) : (주)솔리데오시스템즈
   - 위탁하는 업무의 내용 : 시스템 개발 및 유지보수 수행
   - 위탁기간 : 1년
   - 연락처 : 070-7825-4470
   - 근무시간 : 09:00 ~ 18:00
나. 위탁받는 자(수탁자) : (주)피씨엔
   - 위탁하는 업무의 내용 : 시스템 개발 및 유지보수 수행
   - 위탁기간 : 1년
   - 연락처 : 070-7825-0156
   - 근무시간 : 09:00 ~ 18:00
다. 위탁받는 자(수탁자) : (주)씨케이인포
   - 위탁하는 업무의 내용 : 시스템 개발 및 유지보수 수행
   - 위탁기간 : 1년
   - 연락처 : 070-7825-4483
   - 근무시간 : 09:00 ~ 18:00
2. 위탁계약 체결시 개인정보 보호법 제26조에 따라 위탁업무 수행목적 외 개인정보 처리금지, 기술적ㆍ관리적 보호조치, 재위탁 제한, 수탁자에 대한 관리ㆍ감독, 손해배상 등 책임에 관한 사항을 계약서 등 문서에 명시하고, 수탁자가 개인정보를 안전하게 처리하는지를 감독하고 있습니다.
3. 위탁업무의 내용이나 수탁자가 변경될 경우에는 본 개인정보 처리방침을 통하여 공개하고 있습니다.

제5조(정보주체와 법정대리인의 권리·의무 및 그 행사방법에 관한 사항)
1. 정보주체(만 14세 미만인 경우에는 법정대리인을 말함)는 다음 각 호의 개인정보보호 관련 권리를 행사할 수 있습니다.
   가. 개인정보 열람요구
   나. 오류 등이 있을 경우 정정 요구
   다. 삭제 요구
   라. 처리정지 요구
2. 제1항에 따른 권리 행사는 "정부24"에 대해 「개인정보 처리 방법에 관한 고시」 별지 제8호 서식에 따라 작성 후 서면, 전자우편, 모사전송(FAX) 등을 통하여 하실 수 있으며, "정부24"는 이에 대해 지체 없이 조치하겠습니다.
3. 정보주체가 개인정보의 오류 등에 대한 정정 또는 삭제를 요구한 경우에는 정정 또는 삭제를 완료할 때까지 해당 개인정보를 이용하거나 제공하지 않습니다.
4. 제1항에 따른 권리 행사는 정보주체의 법정대리인이나 위임을 받은 자 등 대리인을 통하여 하실 수 있습니다. 이 경우 개인정보 처리 방법에 관한 고시 별지 제11호 서식에 따른 위임장을 제출하셔야 합니다.
5. 개인정보 열람 및 처리정지 요구는 「개인정보 보호법」 제35조 제4항, 제37조 제2항에 의하여 정보주체의 권리가 제한될 수 있습니다.
6. 개인정보의 정정 및 삭제 요구는 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.
7. 정보주체 권리에 따른 열람의 요구·정정·삭제의 요구·처리정지의 요구 시 열람 등 요구를 한 자가 본인이거나 정당한 대리인인지를 확인합니다.

제6조(처리하는 개인정보 항목)
"정부24"가 처리하는 개인정보 항목은 다음 각 호와 같습니다.

1. 정부24 회원정보
  ① 회원정보
   - 필수항목 : 아이디, 비밀번호, 성명(법인명), 주민등록번호(외국인등록번호, 법인등록번호·사업자등록번호(법인인 경우)), 주소, 이메일 주소
   - 선택항목 : 휴대전화번호
     ·민원처리 SMS 수신동의 시 : 휴대전화번호
     ·생체인증 서비스 이용 시 : 휴대전화번호, 휴대폰 운영체제 정보, 통신사, 제조사 정보, 비밀번호
     ·민원 알림수신 선택 시 : 이메일 또는 SMS 수신동의여부
     ·생활정보서비스 이용동의 시 : 연령, 성별, 관심지역
  ② 비회원정보
   - 필수항목 : 성명(법인명), 주민등록번호(외국인등록번호, 법인등록번호·사업자등록번호(법인인 경우))
   - 선택항목 : 주소, 상세주소, 연락처, 휴대전화번호
2. 전자민원 신청이력(상담이력 포함)
   - 필수항목 : 아이디, 성명(법인명), 주민등록번호(외국인등록번호, 법인등록번호), 주소, 휴대전화번호
   - 선택항목 : 전화번호, 이메일주소, 계좌번호, 상담정보(녹취자료 포함)
3. 전자민원 증명서(신청서 및 발급물)
   - 필수항목 : 성명, 주민등록번호(외국인등록번호, 법인등록번호), 주소
   - 선택항목 : 전화번호, 휴대전화번호, 이메일주소
4. 생활정보
   - 필수항목 : 주민등록번호(외국인등록번호), 서비스코드(기관), 일련번호, 생활정보(전자정부법 제9조의2)
   - 선택항목 : 자동차등록번호, 주소(동/호수), 공적연금 가입 여부
5. 국가보조금 맞춤형서비스
   - 필수항목 : 성명, 주민등록번호(외국인등록번호), 자격정보, 수급정보

제7조(개인정보의 파기 절차 및 방법)
"정부24"는 원칙적으로 개인정보 보존기간이 경과하거나, 처리목적이 달성된 경우에는 지체 없이 해당 개인정보를 파기합니다. 다만, 다른 법령에 따라 보존하여야 하는 경우에는 그러하지 않을 수 있습니다. 파기 절차, 기한 및 방법은 다음과 같습니다.

1. 파기절차
이용자가 입력한 정보는 회원탈퇴 등 목적 달성 후 내부방침 및 기타 관련 법령에 따라 일정기간 저장된 후 파기됩니다. 이 때, DB로 옮겨진 개인정보는 법률에 의한 경우가 아닌 다른 목적으로 이용되지 않습니다.
   가. 개인정보의 파기
      보유기간이 경과한 개인정보는 종료일로부터 지체 없이 파기합니다.
   나. 개인정보파일의 파기
      개인정보파일의 처리 목적 달성, 해당 서비스의 폐지, 사업의 종료 등 그 개인정보파일이 불필요하게 되었을 때에는 개인정보의 처리가 불필요한 것으로 인정되는 날로부터 지체 없이 그 개인정보파일을 파기합니다.
2. 파기방법
해당 개인정보는 전자적인 방법으로 복구 또는 재생이 불가능하도록 영구적으로 파기합니다.

제8조(개인정보 자동 수집 장치의 설치ㆍ운영 및 거부에 관한 사항)
1. "정부24"는 이용자에게 개인형 서비스를 제공하기 위해 이용정보를 저장하고 수시로 불러오는 '쿠키(cookie)'를 사용합니다.
2. 쿠키는 웹사이트를 운영하는데 이용되는 서버(http)가 이용자의 컴퓨터 브라우저에게 보내는 소량의 정보이며 이용자들의 PC 컴퓨터내의 하드디스크에 저장되기도 합니다.
   가. 쿠키의 사용 목적 : 이용자가 방문한 각 서비스와 웹 사이트들에 대한 방문 및 이용형태, 내가 찾은 검색어, 초기화면 설정 등을 파악하여 이용자에게 최적화된 정보 제공을 위해 사용됩니다.
   나. 쿠키의 설치ㆍ운영 및 거부 : 브라우저 옵션 설정을 통해 쿠키 허용, 쿠키 차단 등의 설정을 할 수 있습니다.
   Internet Explorer : 웹브라우저 상단의 도구 메뉴 > 인터넷 옵션 > 개인정보 > 설정 > 고급
   Chrome : 웹브라우저 상단의 설정 메뉴 > 개인정보 및 보안 > 쿠키 및 기타 사이트 데이터
   다. 쿠키 저장을 거부 또는 차단할 경우 개인 맞춤형 서비스 이용에 어려움이 발생할 수 있습니다.


제9조(개인정보의 안전성 확보 조치)
개인정보의 안전성 확보를 위해 다음과 같은 조치를 취하고 있습니다.

1. 개인정보 취급직원의 최소화 및 교육
개인정보를 취급하는 직원은 반드시 필요한 인원에 한하여 지정·관리하고 있으며 취급직원을 대상으로 안전한 관리를 위한 교육을 실시하고 있습니다.
2. 개인정보에 대한 접근 제한
개인정보를 처리하는 개인정보처리시스템에 대한 접근권한의 부여·변경·말소를 통하여 개인정보에 대한 접근통제를 위한 필요한 조치를 하고 있으며 침입차단시스템을 이용하여 외부로부터의 무단 접근을 통제하고 있습니다.
3. 접속기록의 보관 및 점검
가. 개인정보의 접속 기록 보관 기간
     개인정보처리시스템에 접속한 기록을 1년 이상 보관ㆍ관리하고 있습니다. 다만, 5만명 이상의 정보주체에 관하여 개인정보를 처리하거나, 고유식별 또는 민감정보를 처리하는 경우에는 2년 이상 보관ㆍ관리하고 있습니다.
나. 개인정보의 점검
     개인정보의 오ㆍ남용, 분실ㆍ도난ㆍ유출ㆍ위조ㆍ변조 또는 훼손 등에 대응하여 개인정보처리시스템의 접속기록 등을 월 1회 이상 점검하고 있습니다. 특히 개인정보를 다운로드한 것이 발견되었을 경우에 내부관리 계획으로 정하는 바에 따라 그 사유를 반드시 확인하고 있습니다.
다. 개인정보의 보관
     개인정보취급자의 접속기록이 위ㆍ변조 및 도난, 분실되지 않도록 해당 접속 기록을 보안기능을 사용하여 안전하게 보관하고 있습니다.
4. 개인정보의 암호화
개인정보는 암호화 등을 통해 안전하게 저장 및 관리되고 있습니다. 또한, 중요한 데이터는 저장 및 전송 시 암호화하여 사용하는 등의 별도 보안기능을 사용하고 있습니다.
5. 보안프로그램 설치 및 주기적 점검·갱신
해킹이나 컴퓨터 바이러스 등에 의한 개인정보 유출 및 훼손을 막기 위하여 보안프로그램을 설치하고 주기적으로 갱신·점검하고 있습니다.
6. 비인가자에 대한 출입 통제
개인정보를 보관하고 있는 개인정보처리시스템의 물리적 보관 장소를 별도로 두고 이에 대해 출입통제 절차를 수립·운영하고 있습니다.

제10조(권익침해 구제방법)
개인정보 주체는 개인정보침해로 인한 피해를 구제 받기 위하여 다음과 같이 개인정보 분쟁조정위원회, 한국인터넷진흥원 개인정보 침해-신고센터 등에 분쟁해결이나 상담 등을 신청할 수 있습니다.

   - 개인정보 분쟁조정위원회 : 1833-6972 (www.kopico.go.kr)
   - 개인정보 침해신고센터 : (국번없이) 118 (privacy.kisa.or.kr)
   - 대검찰청 사이버수사과 : (국번없이) 1301, cid@spo.go.kr (www.spo.go.kr)
   - 경찰청 사이버수사국 : (국번없이) 182 (cyberbureau.police.go.kr)

또한, 개인정보의 열람, 정정·삭제, 처리정지 등에 대한 정보주체자의 요구에 대하여 공공기관의 장이 행한 처분 또는 부작위로 인하여 권리 또는 이익을 침해 받은 자는 행정심판법이 정하는 바에 따라 행정심판을 청구할 수 있습니다.
   - 중앙행정심판위원회(www.simpan.go.kr)의 전화번호 안내 참조

제11조(개인정보 분야별 책임관 및 담당자 연락처)

1. 개인정보 보호책임관 : 행정안전부 정책기획관 김하균
2. 개인정보보호 분야별 책임관 : 행정안전부 행정서비스통합추진단 정부24운영팀장 전명주 (044-205-6460)
3. 개인정보취급자
    - 담당자 : 행정안전부 행정서비스통합추진단 정부24운영팀 손영준
    - 연락처 : 044-205-6457 (Fax 044-204-8983) 전자메일 : joonyson@korea.kr

제12조(개인정보 열람청구)
1. 정보주체는 개인정보 보호법 제35조에 따른 개인정보의 열람청구를 아래의 부서에 할 수 있습니다. 행정안전부 행정서비스통합추진단 정부24운영팀은 정보주체의 열람청구가 신속하게 처리되도록 노력하겠습니다.
   -부서명 : 행정안전부 행정서비스통합추진단 정부24 운영팀
   -담당자 : 손영준
   -연락처 : 044-205-6457 (Fax 044-204-8983) 전자메일 : joonyson@korea.kr
2. 정보주체께서는 제1항의 열람청구 접수·처리부서 이외에, 개인정보보호위원회의 ‘개인정보보호 포털’ 웹사이트(www.privacy.go.kr)를 통하여서도 개인정보 열람청구를 하실 수 있습니다.
   - 개인정보보호위원회 개인정보보호 포털 → 개인정보 민원 → 개인정보 열람 등 요구(본인확인 필요)

제13조(정보주체의 동의없이 추가적인 이용 또는 제공할 때 판단기준)
정보주체의 동의없이 추가적인 이용 또는 제공할 때 판단기준은 다음과 같습니다.
1. 당초 수집 목적과 관련성이 있는지 여부
2. 개인정보를 수집한 정황 또는 처리 관행에 비추어 볼 때 개인정보의 추가적인 이용 또는 제공에 대한 예측 가능성이 있는지 여부
3. 정보주체의 이익을 부당하게 침해하는지 여부
4. 가명처리 또는 암호화 등 안전성 확보에 필요한 조치를 하였는지 여부

제14조(개인정보처리방침 변경)
이 개인정보 처리방침은 2021. 10. 6. 부터 적용됩니다. 이전의 개인정보 처리방침은 아래에서 확인하실 수 있습니다.</span>
		</div>
	</div>
	<div class="content_first_text">
		<div></div>
		<span>위치정보 이용약관 동의</span>
	</div>
	<div class="content_bar">
		<div>
			<span>제1조. 목적
본 약관은 회원(서비스 약관에 동의한 자를 말합니다. 이하 “회원”이라고 합니다.)이 주식회사 그린카 (이하 “회사”라고 합니다.)가 제공하는 사용자 위치 기반을 통한 차량예약서비스(이하 “서비스”라고 합니다)를 이용함에 있어 회사와 회원의 권리•의무 및 책임사항을 규정함을 목적으로 합니다.

제2조. 약관 외 준칙
본 약관에 명시되지 않은 사항에 대해서는 위치정보의 보호 및 이용 등에 관한 법률, 전기통신사업법, 정보통신망 이용촉진 및 정보보호 등에 관한 법률 등 관계 법령과 회사가 별도로 정한 서비스의 세부이용지침 등의 규정에 따릅니다.

제3조. 서비스 가입
① 고객은 ‘그린카 회원 이용 약관 제 6조’에 따라 서비스를 가입 할 수 있으며 회사가 정한 본 약관에 고객이 동의하므로써 서비스 가입의 효력이 발생합니다.
② 단 회사는 다음 각 호의 고객 가입신청에 대해서는 이를 승낙하지 아니할 수 있습니다. 1. 실명이 아니거나 다른 사람의 명의를 사용하는 등 허위로 신청하는 경우
2. 고객 등록 사항을 누락하거나 오기하여 신청하는 경우
3. 공공질서 또는 미풍양속을 저해하거나 저해할 목적으로 신청한 경우
4. 기타 회사가 정한 이용신청 요건이 충족되지 않을 경우
5. 고객은 그 자격에 따라 서비스 이용의 일부가 제한될 수 있으며 사용기록 및 가입정보, 진위여부에 따라 고객 자격 및 서비스 이용 가능 범위가 변동될 수 있습니다.

제4조. 서비스 해지
① 고객은 ‘그린카 회원 이용 약관 제 8조’에 따라 서비스를 해지(탈퇴)할 수 있습니다.

제5조. 개인위치정보 이용·제공에 관한 동의·조건
① 회사는 위치기반서비스를 제공하기 위하여 고객의 개인위치정보를 이용·제공하며, 고객은 서비스 가입 및 기타 방법으로 개인위치정보주체 본인을 인증하여 동의를 획득합니다.
② 8세 이하 아동의 경우, 아동, 치매, 정신질환, 중증 정신지체 등으로 사리를 분별할 능력이 없는 자 (이하 '의사무능력자등'이라 한다.)의 친권자, 후견인, 부양의무자 그 밖에 의사무능력자등을 사실상 보호하는 자로서 대통령령이 정하는 자(이하 '친권자등'이라 한다)가 의사무능력자등의 생명 또는 신체의 보호를 위하여 의사무능력자 등의 개인위치정보의 이용 및 제공에 동의하는 경우, 친권자등임을 증명하는 증빙서류를 제출하는 건에 한하여 동의획득으로 인정합니다.
③ 8세 이하의 아동 등의 생명 또는 신체의 보호를 위하여 개인위치정보의 수집‧이용 또는 제공에 동의를 하고자 하는 자는 서면동의서에 8세 이하의 아동 등의 보호의무자임을 증명하는 서면을 첨부하여 회사에 제출하여야 하며 서면동의서에는 다음 사항을 기재하고 그 보호의무자가 기명날인 또는 서명하여야 합니다. 1. 8세 이하의 아동 등의 성명, 주소 및 생년월일
2. 보호의무자의 성명, 주소 및 연락처
3. 개인위치정보 수집, 이용 또는 제공의 목적이 8세 이하의 아동 등의 생명 또는 신체의 보호에 한정된다는 사실
4. 동의의 연월일

제6조. 개인위치정보 이용·제공에 관한 동의의 철회
고객은 서비스 해지 등의 방법을 통하여 개인위치정보의 이용 또는 제공에 대한 동의의 전부 또는 일부를 철회 할 수 있습니다.

제7조. 서비스 내용 및 이용요금
① 회사는 직접 위치정보를 수집하거나 위치정보사업자로부터 위치정보를 전달받아 아래와 같은 위치기반서비스를 제공합니다. 1. 인근 그린존 위치 및 할인 정보
2. 차량 반납 위치 반경 확인 및 이상 결과 통지
3. 이용자 보호 및 부정 이용 방지 목적으로 개인위치정보주체 또는 이동성 있는 기기의 위치를 이용하여 권한 없는 자의 비정상적인 서비스 이용 시도 등을 차단 ② 제1항 위치기반서비스 이용 요금은 원칙적으로 무료입니다. 만약 별도의 유료 서비스의 경우 해당 서비스에 명시합니다.

제8조. 개인 위치정보의 제공
회사는 고객의 개인위치정보를 고객이 지정하는 제3자에게 제공하는 경우에는 개인위치정보를 수집한 당해 통신 단말장치로 매회 고객에게 제공받는 자, 제공일시 및 제공목적을 즉시 통보합니다. 단, 다음에 해당하는 경우에는 고객이 미리 특정하여 지정한 연락처 및 이메일 주소로 통보합니다. 1. 개인위치정보를 수집한 당해 통신단말장치가 문자, 음성 또는 영상의 수신기능을 갖추지 아니한 경우
2. 회원이 온라인 게시 등의 방법으로 통보할 것을 미리 요청한 경우

제9조. 고객의 개인위치정보 보호
회사는 관련법령이 정하는 바에 따라서 고객의 개인위치정보를 보호하기 위하여 노력합니다.

제10조. 위치정보 이용·제공사실 확인자료의 보유
① 회사는 위치정보의 보호 및 이용 등에 관한 법률 제16조 제2항에 근거하여 고객에 대한 위치정보의 수집‧이용‧제공 사실 확인자료를 위치정보시스템에 자동으로 기록하며, 관련 자료는 위치정보서비스 제공을 위해 고객의 서비스 가입 시점부터 서비스 해지 시점까지 보유합니다.
② 회사는 위치정보의 보호 및 이용 등에 관한 법률 제24조 제4항의 규정에 의하여 회사는 고객이 동의의 전부 또는 일부를 철회한 경우에는 지체 없이 수집된 개인위치정보 및 위치정보의 수집·이용·제공사실 확인자료(동의의 일부를 철회하는 경우에는 철회하는 부분의 개인위치정보 및 위치정보 이용·제공사실 확인자료에 한합니다) 를 지체없이 파기합니다. 다만, 고객이 별도로 동의하거나 고객의 불만/분쟁 처리 또는 국세기본법, 법인세법, 부가가치세법 기타 관계법령의 규정에 의하여 보존할 필요성이 있는 경우에는 관계법령에 따라 보존합니다.

제11조. 개인위치정보의 보유 및 이용기간
회사는 고객의 개인위치정보의 이용 또는 제공 목적을 달성하거나, 개인위치정보의 이용 또는 제공 목적을 달성하거나, 고객이 개인위치정보의 이용에 대한 동의의 전부 또는 일부에 대하여 철회한 때에는 당해 개인위치정보를 지체 없이 파기합니다.

제12조. 법정대리인의 권리
① 회사는 14세 미만의 아동으로부터 위치정보의 보호 및 이용 등에 관한 법률 제18조 제1항, 제19조 제1항‧제2항 또는 제21조의 규정에 의하여 개인위치정보를 수집‧이용 또는 제공하고자 하는 경우에는 아동 본인 및 그 법정대리인의 동의를 얻어야 합니다.
② 본 약관 제14조(고객의 권리)의 규정은 위치정보의 보호 및 이용 등에 관한 법률 제25조 제2항의 규정에 의하여 법정대리인이 동의를 하는 경우에 이를 준용합니다. 이 경우, '고객'은 '법정대리인'으로 봅니다.

제13조. 회사의 의무
① 회사는 고객의 개인위치정보 이용·제공과 관련한 고객의 불만사항이 접수되는 경우 이를 신속하게 처리하여야 하며, 신속한 처리가 곤란한 경우 그 사유와 처리 일정을 고객에게 통지합니다.
② 회사는 위치정보의보호및이용등에관한법률, 정보통신망이용촉진및보호등에관한법률 등 고객의 개인위치정보 이용·제공과 관련이 있는 법규를 준수합니다.

제14조. 고객의 권리
① 고객은 회사의 고객 개인위치정보의 수집에 대한 동의의 전부 또는 일부에 대하여 철회 할 수 있습니다.
② 고객은 회사의 고객 개인위치정보 수집의 일시적인 중지를 요구 할 수 있습니다.
③ 고객은 제1항 및 제2항에 의한 개인위치정보 수집에 대한 동의철회 및 중지를 전화, 우편, APP를 통해 요청 할 수 있습니다.
④ 고객이 제1항 또는 제2항에 따라 개인위치정보 수집에 대한 동의를 철회 또는 중지하는 경우 고객에 대한 회사의 각종 서비스의 제공이 불가능하게 될 수 있습니다.
⑤ 고객은 회사를 방문하여 다음과 같은 자료 등의 열람 또는 고지를 요구 할 수 있고 당해 자료 등에 오류가 있는 경우에는 그 정정을 요구 할 수 있습니다. 1. 고객에 대한 위치정보 수집‧이용‧제공 사실 확인자료
2. 고객의 개인위치정보가 위치정보의 보호 및 이용 등에 관한 법률 또는 다른 법률의 규정에 의하여 제3자에게 제공된 이유 및 내용

제15조. 고객의 의무
① 고객은 서비스의 제공을 위하여 회사가 개인위치정보를 수집하는데 필요한 제반 정보를 제공, 등록 할 경우 현재의 사실과 일치하는 완전한 정보를 제공‧등록 하여야 하며, 변경사항이 발생할 경우 즉시 갱신하여야 합니다.
② 고객은 회사가 위치정보를 수집하여 제공하는 서비스를 원활하게 이용하기 위하여 운행 정보 수집장치 등 위치정보수집 단말장치(이하 '대상 단말기'라 합니다)가 정상 동작을 유지하도록 관리하여야 하며, 대상 단말기의 정상 동작이 유지되지 않아 회사의 개인위치정보 수집에 지장이 발생한 경우에는 해당 대상 단말기의 보수, 교환 등의 처리를 하여 회사의 서비스 제공을 위한 위치정보 수집이 정상적으로 이루어지도록 합니다.
③ 고객은 다음 각 호의 어느 하나에 해당하는 행위를 하여서는 아니 됩니다. 1. 개인위치정보 수집과 관련된 설비의 오동작이나 정보 등의 파괴 및 혼란을 유발시키는 컴퓨터 바이러스 감염 자료를 등록 또는 유포하는 행위
2. 타인으로 가장하는 행위 및 타인과의 관계를 허위로 명시하는 행위
3. 자기 또는 타인에게 재산상의 이익을 주거나 손해를 가할 목적으로 허위의 정보를 유통시키는 행위
4. 타인의 개인위치정보를 무단으로 유용 또는 유출하는 행위
5. 기타 불법적이거나 부당한 행위 ④ 고객은 관계 법령, 이 약관의 규정, 이용안내 및 서비스 상에 공지한 주의사항, 회사가 통지하는 사항 등을 준수하여야 하며, 기타 회사의 업무에 방해되는 행위를 하여서는 아니 됩니다.

제16조. 회사의 연락처
회사의 상호, 주소, 전화 번호 그 밖의 연락처는 다음과 같습니다. 1. 상호: 주식회사 그린카
2. 주소: 서울특별시 강남구 테헤란로 70길 16(대치동)
3. 대표전화: 1899-1313

제17조. 위치정보 관리책임자
회사의 위치정보관리책임자는 다음과 같습니다. 1. 직위: 서비스 운영 실장
2. 전화번호: 1899-1313

제18조. 손해배상
① 고객 또는 위치기반서비스사업자가 고의나 과실에 의해 이 약관의 규정을 위반함으로 인하여 회사에 손해가 발생하게 되는 경우, 이 약관을 위반한 당사자는 회사에 발생한 손해를 배상하여야 합니다.
② 고객의 불법 행위나 약관 위반행위로 인하여 회사가 제3자로부터 손해배상청구 또는 소송을 비롯한 각종 이의제기를 받는 경우 당해 불법행위 또는 약관 위반행위를 한 자는 자신의 책임과 비용으로 회사를 면책시켜야 하며, 회사가 면책되지 못한 경우 그로 인하여 회사에 발생한 손해를 배상하여야 합니다.
③ 회사가 위치정보의보호및이용등에관한법률 제15조 내지 제26조의 규정을 위반하여 고객에게 손해가 발생한 경우 회사가 고의 또는 과실이 없음을 입증하지 아니하면 고객의 손해에 대해 책임을 집니다.

제19조. 면책사항
① 회사는 천재지변 또는 이에 준하는 불가항력으로 인하여 개인위치정보를 수집할 수 없는 경우 이에 관한 책임이 면제됩니다.
② 회사는 고객의 귀책사유로 인하여 회사가 개인위치정보를 수집하지 못하거나 잘못 수집하여 발생하는 서비스의 이용 장애에 대하여 책임을 지지 않습니다.

제20조. 분쟁의 해결
① 개인위치정보의 수집과 관련하여 회사와 고객 사이에 분쟁이 발생한 경우, 회사와 고객은 분쟁의 해결을 위해 성실히 협의합니다.
② 제1항의 협의에도 불구하고 회사와 고객간 분쟁이 해결되지 않을 경우 양 당사자는 개인정보보호법 제43조의 규정에 의한 개인정보분쟁조정위원회에 조정을 신청할 수 있습니다.

부칙
위치정보관리책임자는 2017년 3월을 기준으로 다음과 같이 지정합니다. 1.소속 : (주)그린카 플랫폼본부
2.연락처 : 02-3456-7701

부칙 (2019. 02. 22)
(시행일) 이 약관은 2019년 2월 22일부터 시행한다.

부칙 (2021. 01. 21)
(시행일) 이 약관은 2021년 1월 21일부터 시행한다.

부칙 (2021. 07. 23)
(시행일) 이 약관은 2021년 7월 23일부터 시행한다.

그린카 페이스북 그린카 블로그 그린카 인스타그램 그린카 네이버 포스트 그린카 유튜브</span>
		</div>
	</div>
	<div class="agree_checkbox">
	   <div>
		<input type="checkbox" id="checkItem1">
		<label>서비스 이용약관 및 개인정보 처리 방침에 동의합니다.</label>
	   </div>
	   <div class="checkbox_02">
		<input type="checkbox" id="checkItem2">
		<label>위치기반서비스 이용약관에 동의합니다.</label>
	   </div>
	</div>
	<div class="button_box">
		<a><button id="button_01" onclick="javascript:history.back();"><span>이전 단계</span></button></a>
		<c:if test="${join_type=='common'}">
			<button type="button" id="button_02" onclick="agreeCheck('common')"><span>가입하기</span></button>
		</c:if>
		<c:if test="${join_type=='gym'}">
			<button type="button" id="button_02" onclick="agreeCheck('gym')"><span>가입하기</span></button>
		</c:if>
	</div>
  </div>
 </div>
</body>
</html>