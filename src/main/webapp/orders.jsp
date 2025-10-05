<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My Orders</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin: 20px auto; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
        th { background-color: #f0f0f0; }
        h2 { text-align: center; }
        a { display: block; text-align: center; margin-top: 20px; }
    </style>
</head>
<body>
<h2>My Orders</h2>

<c:if test="${empty orders}">
    <p style="text-align:center;">Du har inga beställningar ännu.</p>
</c:if>

<c:if test="${not empty orders}">
    <table>
        <tr>
            <th>Order ID</th>
            <th>Produkt</th>
            <th>Beskrivning</th>
            <th>Pris</th>
            <th>Antal</th>
            <th>Totalt</th>
        </tr>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.id}</td>
                <td>${order.name}</td>
                <td>${order.descr}</td>
                <td>${order.price} kr</td>
                <td>${order.quantity}</td>
                <td>${order.total} kr</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<a href="webshop.jsp">← Tillbaka till Webshop</a>
</body>
</html>
