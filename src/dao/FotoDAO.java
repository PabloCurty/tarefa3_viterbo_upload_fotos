package dao;

import java.util.List;

import model.Photo;


public interface FotoDAO {
	public void inserir(Photo foto);
	public void remover(int id);
	public List<Photo> listar();
	public Photo buscar(int id);
	public void editar(Photo foto); 
	
}
