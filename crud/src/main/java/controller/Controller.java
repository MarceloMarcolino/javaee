package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The funcionario. */
	JavaBeans funcionario = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/main")) {
			funcionarios(request, response);
		} else if (action.equals("/insert")) {
			adicionarFuncionario(request, response);
		} else if (action.equals("/select")) {
			listarFuncionario(request, response);
		} else if (action.equals("/update")) {
			editarFuncionario(request, response);
		} else if (action.equals("/delete")) {
			removerFuncionario(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	/**
	 * Funcionarios.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar funcionarios
	protected void funcionarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarFuncionarios();
		// Encaminhar a lista ao documento goodyear.jsp
		request.setAttribute("funcionarios", lista);
		RequestDispatcher rd = request.getRequestDispatcher("goodyear.jsp");
		rd.forward(request, response);
	}

	/**
	 * Adicionar funcionario.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Novo funcionario
	protected void adicionarFuncionario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setar as variáveis JavaBeans
		funcionario.setNome(request.getParameter("nome"));
		funcionario.setPais(request.getParameter("pais"));
		funcionario.setEstado(request.getParameter("estado"));
		funcionario.setCidade(request.getParameter("cidade"));
		// invocar o método inserirFuncionario passando o objeto funcionario
		dao.inserirFuncionario(funcionario);
		// redirecionar para o documento goodyear.jsp
		response.sendRedirect("main");
	}

	/**
	 * Listar funcionario.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Editar funcionario
	protected void listarFuncionario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do contato que será editado
		String id = request.getParameter("id");
		// Setar a variável JavaBeans
		funcionario.setId(id);
		// Executar o método selecionarFuncionario (DAO)
		dao.selecionarFuncionario(funcionario);
		// Setar os atributos do formulário com o conteúdo JavaBeans
		request.setAttribute("id", funcionario.getId());
		request.setAttribute("nome", funcionario.getNome());
		request.setAttribute("pais", funcionario.getPais());
		request.setAttribute("estado", funcionario.getEstado());
		request.setAttribute("cidade", funcionario.getCidade());
		// Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar funcionario.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarFuncionario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setar as variáveis JavaBeans
		funcionario.setId(request.getParameter("id"));
		funcionario.setNome(request.getParameter("nome"));
		funcionario.setPais(request.getParameter("pais"));
		funcionario.setEstado(request.getParameter("estado"));
		funcionario.setCidade(request.getParameter("cidade"));
		// executar o método alterarFuncionario
		dao.alterarFuncionario(funcionario);
		// redirecionar para o documento goodyear.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}

	/**
	 * Remover funcionario.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Remover um funcionario
	protected void removerFuncionario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do funcionário a ser excluído (validador.js)
		String id = request.getParameter("id");
		// Setar a variável id JavaBeans
		funcionario.setId(id);
		// Executar o método deletarFuncionario (DAO) passando o objeto funcionario
		dao.deletarFuncionario(funcionario);
		// redirecionar para o documento goodyear.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}

	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Gerar relatório em PDF
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
			// Tipo de conteúdo
			response.setContentType("application/pdf");
			// Nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" + "funcionarios.pdf");
			// Criar o documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			// Abrir o documento -> conteúdo
			documento.open();
			documento.add(new Paragraph("Registro de funcionários:"));
			documento.add(new Paragraph(" "));
			// Criar uma tabela
			PdfPTable tabela = new PdfPTable(4);
			// Cabeçalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("País"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Estado"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Cidade"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Popular a tabela com os contatos
			ArrayList<JavaBeans> lista = dao.listarFuncionarios();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getPais());
				tabela.addCell(lista.get(i).getEstado());
				tabela.addCell(lista.get(i).getCidade());
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}
