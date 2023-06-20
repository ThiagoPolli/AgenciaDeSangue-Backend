package wkAgenciaDeSangue.Services;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.Period;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import wkAgenciaDeSangue.Dto.CandidatoDto;

import wkAgenciaDeSangue.Dto.MediaSanguineoDto;

import wkAgenciaDeSangue.Dto.QtdEstadoDto;
import wkAgenciaDeSangue.Dto.QtdObesoDto;
import wkAgenciaDeSangue.Entities.Candidato;
import wkAgenciaDeSangue.Entities.Utils.IMC;
import wkAgenciaDeSangue.Entities.Utils.PotencialDoador;
import wkAgenciaDeSangue.Repositories.CandidatoRepository;
import wkAgenciaDeSangue.Services.Exceptions.ObjectNotFoundException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CandidatoService {

	@Autowired
	private CandidatoRepository repository;

	IMC imc;

	public List<Candidato> findAll() {
		return repository.findAll();
	}

	public Candidato findById(Integer id) {
		Optional<Candidato> candidato = repository.findById(id);
		return candidato.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Candidato.class.getName()));
	}

	public Page<Candidato> findPerPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);

	}

	@Transactional
	public Candidato insert(Candidato obj) {
		obj.setId(null);
		obj = repository.save(obj);
		return obj;
	}

	@Transactional
	public List<Candidato> insertList(List<Candidato> obj) {
		obj.forEach(c -> insert(c));
		return obj;
	}

	@Transactional
	public Candidato update(Candidato obj) {
		try {
			Candidato candidato = findById(obj.getId());
			updateData(candidato, obj);
			return repository.save(candidato);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		

	}

	public List<QtdEstadoDto> findByEstado() {
		List<Object[]> result = repository.findByEstado();
		List<QtdEstadoDto> estadoDtosList = new ArrayList<>();

		for (Object[] row : result) {
			String estado = (String) row[0];
			Long quantidade = (Long) row[1];

			QtdEstadoDto qtdEstadoDto = new QtdEstadoDto(estado, quantidade);
			estadoDtosList.add(qtdEstadoDto);
		}
		return estadoDtosList;
	}

	public List<MediaSanguineoDto> findByMediaSanguineo() {
		List<Object[]> result = repository.countByTipoSanguineo();
		List<MediaSanguineoDto> mediaSanguineoList = new ArrayList<>();

		for (Object[] row : result) {
			String tipo_sanguineo = (String) row[0];
			BigDecimal media = (BigDecimal) row[1];

			MediaSanguineoDto mediaSanguineoDto = new MediaSanguineoDto(tipo_sanguineo, media);
			mediaSanguineoList.add(mediaSanguineoDto);
		}
		return mediaSanguineoList;
	}

	public List<QtdObesoDto> findByObeso() {
		List<Object[]> result = repository.countByObeso();
		List<QtdObesoDto> qtdObesosList = new ArrayList<>();

		for (Object[] row : result) {
			String sexo = (String) row[0];
			Long total_pessoas = (Long) row[1];
			BigDecimal total_obesos_masculinos = (BigDecimal) row[2];
			BigDecimal total_obesos_femininos = (BigDecimal) row[3];
			BigDecimal percentual_obesos = (BigDecimal) row[4];

			QtdObesoDto qtdObesoDto = new QtdObesoDto(sexo, total_pessoas, total_obesos_masculinos,
					total_obesos_femininos, percentual_obesos);
			qtdObesosList.add(qtdObesoDto);
		}
		return qtdObesosList;
	}

	public List<Candidato> findApto() {
		List<Candidato> result = repository.findAll();

		Iterator<Candidato> iterator = result.iterator();
		while (iterator.hasNext()) {
			Candidato candidato = iterator.next();
			LocalDate dataNascimento = candidato.getData_nasc().toLocalDate();
			LocalDate dataAtual = LocalDate.now();
			Period periodo = Period.between(dataNascimento, dataAtual);
			int idade = periodo.getYears();
			Double peso = candidato.getPeso();
			if (idade < 16 || idade > 69 || peso < 50) {
				iterator.remove();
			}
		}

		return result;
	}

	public Map<String, List<Object>> findByImcIdade() {
		List<Candidato> list = repository.findAll();

		IMC imc = new IMC();
		Map<String, List<Object>> result = imc.calcularIMCMedioPorFaixaIdade(list);

		return result;
	}

	public Map<String, List<?>> potencialDoador() {
	    List<Candidato> list = repository.findAll();
	    PotencialDoador potencialDoador = new PotencialDoador();
	    Map<String, Integer> doador = potencialDoador.calculatePotencialDoador(list);

	    List<String> tiposSanguineos = new ArrayList<>();
	    List<Integer> quantidades = new ArrayList<>();

	    // Iterar sobre o mapa e preencher as listas separadas
	    for (Map.Entry<String, Integer> entry : doador.entrySet()) {
	        String tipoSanguineo = entry.getKey();
	        int quantidade = entry.getValue();

	        tiposSanguineos.add(tipoSanguineo);
	        quantidades.add(quantidade);
	    }

	    // Faça algo com as listas separadas
	    // ...

	    // Retornar as listas separadas
	    Map<String, List<?>> result = new HashMap<>();
	    result.put("tiposSanguineos", tiposSanguineos);
	    result.put("quantidades", quantidades);
	    return result;
	}


	public List<Candidato> search(String nome) {
		
		return repository.search("%"+nome+"%");

	}

	private void updateData(Candidato candidato, Candidato obj) {
		candidato.setNome(obj.getNome());
		candidato.setCpf(obj.getCpf());
		candidato.setData_nasc(obj.getData_nasc());
		candidato.setSexo(obj.getSexo());
		candidato.setMae(obj.getMae());
		candidato.setPai(obj.getPai());
		candidato.setEmail(obj.getEmail());
		candidato.setCep(obj.getCep());
		candidato.setEndereco(obj.getEndereco());
		candidato.setNumero(obj.getNumero());
		candidato.setCidade(obj.getCidade());
		candidato.setEstado(obj.getEstado());
		candidato.setTelefone_fixo(obj.getTelefone_fixo());
		candidato.setCelular(obj.getCelular());
		candidato.setAltura(obj.getAltura());
		candidato.setPeso(obj.getPeso());
		candidato.setTipo_sanguineo(obj.getTipo_sanguineo());

	}

	public Candidato fromDto(CandidatoDto objDto) {
		Candidato candidato = new Candidato(null, objDto.getNome(), objDto.getCpf(), objDto.getData_nasc(),
				objDto.getSexo(), objDto.getMae(), objDto.getPai(), objDto.getEmail(), objDto.getCep(),
				objDto.getEndereco(), objDto.getNumero(), objDto.getCidade(), objDto.getEstado(),
				objDto.getTelefone_fixo(), objDto.getCelular(), objDto.getAltura(), objDto.getPeso(),
				objDto.getTipo_sanguineo());
		return candidato;
	}

}
