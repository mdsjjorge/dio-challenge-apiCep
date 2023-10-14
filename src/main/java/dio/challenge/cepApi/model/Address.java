package dio.challenge.cepApi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * possibility of automatic generation of these attributes via the following website:
 * 
 * @see <a href="https://www.jsonschema2pojo.org">jsonschema2pojo.org</a>
 * @see <a href="https://viacep.com.br">ViaCEP</a>
 *
 * @author falvojr
 * @author <a href="https://github.com/mdsjjorge"> mdsjjorge </a>
 */

@Entity
@Data
public class Address {

	@Id
	private String cep;
	private String street;
	private String complement;
	private String district;
	private String locale;
	private String uf;
	private String ibge;
	private String gia;
	private String ddd;
	private String siafi;

}
