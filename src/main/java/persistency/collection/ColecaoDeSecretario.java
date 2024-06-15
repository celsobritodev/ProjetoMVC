package persistency.collection;

import java.util.List;

import exception.ColecaoException;
import model.Secretario;


public interface ColecaoDeSecretario extends Colecao<Secretario> {
	
	public List<Secretario> porNome(String nome) throws ColecaoException;
	public Secretario porCodSecret(int codSecret) throws ColecaoException;

}
