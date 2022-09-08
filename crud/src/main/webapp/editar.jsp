<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Registro de funcionários - Editar</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar funcionário</h1>
	<form name="frmFuncionario" action="update">
		<table>
			<tr>
				<td><input type="text" name="id" id="caixa3" readonly value="<%out.print(request.getAttribute("id"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="Caixa1" value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="pais" class="Caixa2" value="<%out.print(request.getAttribute("pais"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="estado" class="Caixa2" value="<%out.print(request.getAttribute("estado"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="cidade" class="Caixa1" value="<%out.print(request.getAttribute("cidade"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Botao1" onclick="validar()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>