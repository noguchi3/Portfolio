<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>TODOタスクの詳細画面</title>
<jsp:include page="header.jsp" />
<script type="text/javascript">
window.onload = function() {
	var status = document.getElementById("status");
	status.selectedIndex = ${dto.status};
};
</script>
</head>
<body>
<jsp:include page="nav.jsp" />
<div class="container">
<form id="sender" action="register" method="POST">
<table class="table">
	<tr>
		<th>番号</th>
		<td>
			<c:choose>
				<c:when test="${dto.id > 0}">
					<c:out value="${dto.id}" />
				</c:when>
				<c:otherwise>
					(新規)
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<th>タイトル</th>
		<td><input type="text" name="title" value="<c:out value="${dto.title}" />" size="20"/></td>
	</tr>
	<tr>
		<th>タスク内容</th>
		<td><input type="text" name="task" value="<c:out value="${dto.task}" />" maxlength="128" size="60"/></td>
	</tr>
	<tr>
		<th>期限</th>
		<td>
			<select name="year">
				<option value="2020">2020年</option>
				<option value="2021">2021年</option>
				<option value="2022">2022年</option>
				<option value="2023">2023年</option>
				<option value="2024">2024年</option>
				<option value="2025">2025年</option>
				<option value="2026">2026年</option>
				<option value="2027">2027年</option>
				<option value="2028">2028年</option>
				<option value="2029">2029年</option>
				<option value="2030">2030年</option>
			</select>
			<select name="month">
				<option value="-01">1月</option>
				<option value="-02">2月</option>
				<option value="-03">3月</option>
				<option value="-04">4月</option>
				<option value="-05">5月</option>
				<option value="-06">6月</option>
				<option value="-07">7月</option>
				<option value="-08">8月</option>
				<option value="-09">9月</option>
				<option value="-10">10月</option>
				<option value="-11">11月</option>
				<option value="-12">12月</option>
			</select>
			<select name="day">
				<option value="-01">1日</option>
				<option value="-02">2日</option>
				<option value="-03">3日</option>
				<option value="-04">4日</option>
				<option value="-05">5日</option>
				<option value="-06">6日</option>
				<option value="-07">7日</option>
				<option value="-08">8日</option>
				<option value="-09">9日</option>
				<option value="-10">10日</option>
				<option value="-11">11日</option>
				<option value="-12">12日</option>
				<option value="-13">13日</option>
				<option value="-14">14日</option>
				<option value="-15">15日</option>
				<option value="-16">16日</option>
				<option value="-17">17日</option>
				<option value="-18">18日</option>
				<option value="-19">19日</option>
				<option value="-20">20日</option>
				<option value="-21">21日</option>
				<option value="-22">22日</option>
				<option value="-23">23日</option>
				<option value="-24">24日</option>
				<option value="-25">25日</option>
				<option value="-26">26日</option>
				<option value="-27">27日</option>
				<option value="-28">28日</option>
				<option value="-29">29日</option>
				<option value="-30">30日</option>
				<option value="-31">31日</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>ユーザID</th>
		<td><input type="text" name="userid" value="<c:out value="${dto.userid}" />" size="16"/></td>
	</tr>
	<tr>
		<th>状況</th>
		<td>
			<select name="status" id="status">
				<option value="0">未着手</option>
				<option value="1">着手</option>
				<option value="2">完了</option>
				<option value="3">凍結</option>
			</select>
		</td>
	</tr>
</table>
<input type="hidden" name="id" value="<c:out value="${ dto.id }" />" />
<input type="hidden" name="token" value="<c:out value="${ token }" />" />
<input type="submit" class="btn btn-success" value="登録する" />
</form>
<c:if test="${dto.id > 0}">
<br />
<form id="delete" action="delete" method="POST">
	<input type="hidden" name="id" value="<c:out value="${ dto.id }" />" />
	<input type="hidden" name="token" value="<c:out value="${ token }" />" />
	<input type="submit" class="btn btn-warning" value="削除する" />
</form>
</c:if>
<br />
<div class="alert alert-danger" role="alert">
	<c:forEach items="${errorMessages}" var="errorMessage">
	・${errorMessage }<br />
	</c:forEach>
</div>
</div>
</body>
</html>