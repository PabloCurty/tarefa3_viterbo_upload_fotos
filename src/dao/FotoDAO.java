package dao;

import java.util.List;

import model.Photo;


public interface FotoDAO {
	public void inserir(Photo foto);
	public Boolean remover(Long id);
	public List<Photo> listar();
	public Photo buscar(int id);
	public Boolean editar(Photo foto); 
	
}
