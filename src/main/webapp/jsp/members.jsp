<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="spring.springmaster03.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page import="spring.springmaster03.domain.member.MemberRepository" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/index.html">main</a>
<table>
    <thead>
        <tr>
            <th>id</th>
            <th>username</th>
            <th>age</th>
        </tr>
    </thead>
    <tbody>
<%
    for (Member member : members) {
        out.write(" <tr>\n");
        out.write("     <td>" + member.getId() + "</td>\n");
        out.write("     <td>" + member.getUsername() + "</td>\n");
        out.write("     <td>" + member.getAge() + "</td>\n");
        out.write(" </tr>\n");
    }
%>
    </tbody>
</table>
</body>
</html>
