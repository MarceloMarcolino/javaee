/**
 * Confirmar a exclusão de um funcionário
 *
 * @author Marcelo Antonio Pereira Marcolino
 * @param id
 */
 
 function confirmar(id){
	let resposta = confirm("Confirma a exclusão deste funcionário ?")
	if (resposta === true){
		window.location.href = "delete?id=" + id
	}
}