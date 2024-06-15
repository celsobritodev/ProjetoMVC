package persistency.collection;

import java.util.List;

import exception.ColecaoException;
import model.Atendente;

public interface ColecaoDeAtendente extends Colecao<Atendente> {
	
	public List<Atendente> porNome(String nome) throws ColecaoException;
	public Atendente porCodAtend(int codAtend) throws ColecaoException;

}
