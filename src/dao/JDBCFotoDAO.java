package dao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Photo;


public class JDBCFotoDAO implements FotoDAO{
	
	Connection connection;
	public JDBCFotoDAO() {
		connection = ConnectionFactory.getConnection();
	}

	@Override
	public void inserir(Photo foto) {
		
		try {
			String SQL = "INSERT INTO foto (author, place, subtitle, foto)"
					+ "VALUES(?, ?, ?, ? )";
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setString(1, foto.getAuthor());
			ps.setString(2, foto.getLocal());
			ps.setString(3, foto.getSubtitle());
			ps.setBinaryStream(4, new ByteArrayInputStream(foto.getData()), foto.getData().length);
			
			ps.executeUpdate();
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			Logger.getLogger(JDBCFotoDAO.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		
	}

	@Override
	public void remover(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Photo> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Photo buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editar(Photo foto) {
		// TODO Auto-generated method stub
		
	}

}
