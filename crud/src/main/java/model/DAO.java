package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	/**  M�dulo de conex�o *. */
	// Par�metros de conex�o
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/goodyear?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "0p(h@p73r1057$u(k$";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	// M�todo de conex�o
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 *  CRUD CREATE *.
	 *
	 * @param funcionario the funcionario
	 */
	public void inserirFuncionario(JavaBeans funcionario) {
		String create = "insert into funcionarios (nome,pais,estado,cidade) values (?,?,?,?)";
		try {
			// abrir a conex�o
			Connection con = conectar();
			// Preparar a query para execu��o no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os par�metros (?) pelo conte�do das vari�veis JavaBeans
			pst.setString(1, funcionario.getNome());
			pst.setString(2, funcionario.getPais());
			pst.setString(3, funcionario.getEstado());
			pst.setString(4, funcionario.getCidade());
			// Executar a query
			pst.executeUpdate();
			// Encerrar a conex�o com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 *  CRUD READ *.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarFuncionarios() {
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> funcionarios = new ArrayList<>();
		String read = "select * from funcionarios order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// o la�o abaixo ser� executado enquanto houver funcionarios
			while (rs.next()) {
				// vari�veis de apoio que recebem os dados do banco
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String pais = rs.getString(3);
				String estado = rs.getString(4);
				String cidade = rs.getString(5);
				// populando o ArrayList
				funcionarios.add(new JavaBeans(id, nome, pais, estado, cidade));
			}
			con.close();
			return funcionarios;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 *  CRUD UPDATE *.
	 *
	 * @param funcionario the funcionario
	 */
	//selecionar o funcionario
	public void selecionarFuncionario(JavaBeans funcionario) {
		String read2 = "select * from funcionarios where id = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, funcionario.getId());
			ResultSet rs = pst.executeQuery();
			//setar as vari�veis JavaBeans
			while (rs.next()) {
				funcionario.setId(rs.getString(1));
				funcionario.setNome(rs.getString(2));
				funcionario.setPais(rs.getString(5));
				funcionario.setEstado(rs.getString(4));
				funcionario.setCidade(rs.getString(3));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Alterar funcionario.
	 *
	 * @param funcionario the funcionario
	 */
	// editar o funcionario
	public void alterarFuncionario(JavaBeans funcionario) {
		String update = "update funcionarios set nome=?,pais=?,estado=?,cidade=? where id=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, funcionario.getNome());
			pst.setString(2, funcionario.getPais());
			pst.setString(3, funcionario.getEstado());
			pst.setString(4, funcionario.getCidade());
			pst.setString(5, funcionario.getId());
			pst.executeUpdate();
			con.close();
;		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 *  CRUD DELETE *.
	 *
	 * @param funcionario the funcionario
	 */
	public void deletarFuncionario(JavaBeans funcionario) {
		String delete = "delete from funcionarios where id=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, funcionario.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
