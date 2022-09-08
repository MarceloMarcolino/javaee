/**
 * Validar campos obrigatórios
 *
 * @author Marcelo Antonio Pereira Marcolino
 */
 
 function validar() {
	let nome = frmFuncionario.nome.value
	let pais = frmFuncionario.pais.value
	let estado = frmFuncionario.estado.value
	let cidade = frmFuncionario.cidade.value
	
	if(nome === "") {
		alert('Preencha o campo Nome')
		frmFuncionario.nome.focus()
		return false
	} else if (pais === "") {
		alert('Preencha o campo Pais')
		frmFuncionario.pais.focus()
		return false
	} else if (estado === "") {
		alert('Preencha o campo Estado')
		frmFuncionario.estado.focus()
		return false
	} else if (cidade === "") {
		alert('Preencha o campo Cidade')
		frmFuncionario.cidade.focus()
		return false
	} else {
		document.forms["frmFuncionario"].submit()
	}
}