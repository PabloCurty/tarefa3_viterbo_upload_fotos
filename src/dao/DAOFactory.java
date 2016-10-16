package dao;

public class DAOFactory {

	public static FotoDAO createFotoDAO(){
		return new JDBCFotoDAO();
	}
}
