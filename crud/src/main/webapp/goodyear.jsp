<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
@ SuppressWarnings ("unchecked")
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("funcionarios");

%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Registro de funcionários</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Registro de Funcionários</h1>
	<a href="novo.html" class="Botao1">Novo funcionário</a>
	<a href="report" class="Botao2">Relatório</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>País</th>
				<th>Estado</th>
				<th>Cidade</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%for (int i = 0; i < lista.size(); i++) { %>
				<tr>
					<td><%=lista.get(i).getId()%></td>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getPais()%></td>
					<td><%=lista.get(i).getEstado()%></td>
					<td><%=lista.get(i).getCidade()%></td>
					<td><a href="select?id=<%=lista.get(i).getId() %>" class="Botao1">Editar</a>
					<a href="javascript: confirmar(<%=lista.get(i).getId() %>)" class="Botao2">Excluir</a>
					</td>
				</tr>
			<%} %>
		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>