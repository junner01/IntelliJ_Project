<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 21.
  Time: 오후 4:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Member 정보수정 및 삭제. </h1>
<form action="/member/mupdate?mno=${dto.mno}" method="post">
    <div>
        <input type="text" name="mno" value="${dto.mno}" readonly>
    </div>
    <div>
        <input type="text" name="title" value="${dto.title}" placeholder="제목 입력 해주세요." >
    </div>
    <div>
        <input type="date" name="dueDate" value="${dto.dueDate}">
    </div>
    <div>
        <input type="checkbox" name="finished" ${dto.finished ? "checked" : ""} >
    </div>
    <div>
        <button type="submit">회원정보수정</button>
    </div>
</form>

<form action="/member/mdelete?mno=${dto.mno}" method="post">
    <button type="submit">회원삭제</button>
</form>
<a href="/member/mlist">회원목록</a>
</body>
</html>
