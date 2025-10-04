<%@ page import="bo.Cart, bo.CartItem, bo.Item" %>
<%@ page session="true" %>
<html>
<head>
    <title>WebShop</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h2, h3 {
            color: #333;
        }
        .products {
            display: flex;
            gap: 20px;
            flex-wrap: wrap;
        }
        .product-card {
            border: 1px solid #ccc;
            border-radius: 10px;
            padding: 15px;
            width: 200px;
            text-align: center;
            box-shadow: 2px 2px 6px rgba(0,0,0,0.1);
        }
        .product-card h4 {
            margin: 10px 0 5px 0;
        }
        .product-card p {
            margin: 5px 0;
        }
        .product-card button {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 8px 12px;
            border-radius: 5px;
            cursor: pointer;
        }
        .product-card button:hover {
            background-color: #218838;
        }
        .cart-table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }
        .cart-table th, .cart-table td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: center;
        }
        .cart-table th {
            background-color: #f8f8f8;
        }
        .remove-button {
            background-color:#dc3545;
            color:white;
            border:none;
            padding:5px 10px;
            border-radius:5px;
            cursor:pointer;
        }
        .remove-button:hover {
            background-color:#c82333;
        }
    </style>
</head>
<body>
<h2>WebShop</h2>
<p style="text-align:right;"><a href="login.jsp">Login</a></p>
<hr>

<h3>Products</h3>
<%
    Item[] products = new Item[] {
        new Item(1, "T-shirt", "Cool T-shirt", 199.0),
        new Item(2, "Jeans", "Blue Jeans", 499.0),
        new Item(3, "Sneakers", "Sporty Sneakers", 799.0)
    };

    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
        cart = new Cart();
        session.setAttribute("cart", cart);
    }
%>

<div class="products">
<%
    for (Item product : products) {
%>
    <div class="product-card">
        <h4><%= product.getName() %></h4>
        <p><%= product.getDescr() %></p>
        <p><b>Pris: <%= product.getPrice() %> kr</b></p>
        <form action="cart" method="post">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="id" value="<%= product.getId() %>">
            <input type="hidden" name="name" value="<%= product.getName() %>">
            <input type="hidden" name="price" value="<%= product.getPrice() %>">
            <button type="submit">Add to Cart</button>
        </form>
    </div>
<%
    }
%>
</div>

<hr>

<h3>Shopping Cart</h3>
<%
    if (cart.getItems().isEmpty()) {
%>
    <p>Your cart is empty.</p>
<%
    } else {
%>
    <table class="cart-table">
        <tr>
            <th>Product</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Total</th>
            <th>Action</th>
        </tr>
<%
        for (CartItem item : cart.getItems()) {
%>
        <tr>
            <td><%= item.getProduct().getName() %></td>
            <td><%= item.getQuantity() %></td>
            <td><%= item.getProduct().getPrice() %> kr</td>
            <td><%= item.getProduct().getPrice() * item.getQuantity() %> kr</td>
            <td>
                <form action="cart" method="post">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="id" value="<%= item.getProduct().getId() %>">
                    <button type="submit" class="remove-button">Remove</button>
                </form>
            </td>
        </tr>
<%
        }
%>
        <tr>
            <td colspan="3"><b>Total:</b></td>
            <td colspan="2"><b><%= cart.getTotal() %> kr</b></td>
        </tr>
    </table>
<%
    }
%>
<%
    if (!cart.getItems().isEmpty()) {
%>
    <form action="checkout" method="post">
        <input type="submit" value="Checkout" style="padding:10px 20px; font-size:16px; background-color:#007bff; color:white; border:none; border-radius:5px; cursor:pointer;">
    </form>
<%
    }
%>

</body>
</html>
