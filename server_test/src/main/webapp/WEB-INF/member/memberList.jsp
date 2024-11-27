<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 27.
  Time: 오후 4:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Member List<h2>

<ul>
    <c:forEach var="dto" items="${list}">
        <li>
            <span>${dto.mno}</span>
            <span><a href="/member/mread?mno=${dto.mno}">${dto.title}</a></span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished? "완료": "미완료"}</span>
        </li>
    </c:forEach>
</ul>

<button>
    <a href="/member/mReg">회원 등록</a>
</button>


</body>
</html>