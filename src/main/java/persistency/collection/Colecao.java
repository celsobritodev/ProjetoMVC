package persistency.collection;

import java.util.List;

import exception.ColecaoException;

public interface Colecao<T> {
	
	public List<T> todos() throws ColecaoException;
	public T porId(int id) throws ColecaoException;
	public void inserir(T objeto) throws ColecaoException;
	public void alterar(T objeto) throws ColecaoException;
	public void remover(T objeto) throws ColecaoException;

}
