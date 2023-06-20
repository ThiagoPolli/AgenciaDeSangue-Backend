package wkAgenciaDeSangue.Controller;

import java.net.URI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import wkAgenciaDeSangue.Dto.CandidatoDto;
import wkAgenciaDeSangue.Dto.MediaSanguineoDto;
import wkAgenciaDeSangue.Dto.QtdEstadoDto;
import wkAgenciaDeSangue.Dto.QtdObesoDto;
import wkAgenciaDeSangue.Entities.Candidato;

import wkAgenciaDeSangue.Services.CandidatoService;

@RestController
@RequestMapping(value = "api/candidato")
public class CandidatoController {

	@Autowired
	private CandidatoService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CandidatoDto>> findAll() {
		List<Candidato> candidatos = service.findAll();
		List<CandidatoDto> candidatosDTO = candidatos.stream().map(obj -> new CandidatoDto(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(candidatosDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Candidato> findById(@PathVariable Integer id) {
		Candidato candidato = service.findById(id);
		return ResponseEntity.ok().body(candidato);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CandidatoDto>> findPerPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Candidato> candidatos = service.findPerPage(page, linesPerPage, orderBy, direction);
		Page<CandidatoDto> candidatosDto = candidatos.map(c -> new CandidatoDto(c));

		return ResponseEntity.ok().body(candidatosDto);

	}

	@RequestMapping(value = "/estado", method = RequestMethod.GET)
	public ResponseEntity<List<QtdEstadoDto>> findByEstados() {
		List<QtdEstadoDto> candidatos = service.findByEstado();
		return ResponseEntity.ok().body(candidatos);
	}

	@RequestMapping(value = "/mediasanguineo", method = RequestMethod.GET)
	public ResponseEntity<List<MediaSanguineoDto>> findByMediaSanguineo() {

		List<MediaSanguineoDto> mediaSanguineoDtos = service.findByMediaSanguineo();
		return ResponseEntity.ok().body(mediaSanguineoDtos);
	}

	@RequestMapping(value = "/percentualobeso", method = RequestMethod.GET)
	public ResponseEntity<List<QtdObesoDto>> findByObeso() {

		List<QtdObesoDto> qtdObesoDtos = service.findByObeso();
		return ResponseEntity.ok().body(qtdObesoDtos);
	}

	@RequestMapping(value = "/candidatoapt", method = RequestMethod.GET)
	public ResponseEntity<List<CandidatoDto>> findByApt() {

		List<Candidato> candidatos = service.findApto();
		List<CandidatoDto> candidatosDTO = candidatos.stream().map(obj -> new CandidatoDto(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(candidatosDTO);
	}

	@RequestMapping(value = "/imcporidade", method = RequestMethod.GET)
	public ResponseEntity<Map<String, List<Object>>> findByImcporIdade() {
		Map<String, List<Object>> imcMedioPorFaixaIdade = service.findByImcIdade();

		return ResponseEntity.ok().body(imcMedioPorFaixaIdade);
	}

	@RequestMapping(value = "/potencial", method = RequestMethod.GET)
	public ResponseEntity<Map<String, List<?>>> potencialDoador() {
		Map<String, List<?>> potencial = service.potencialDoador();

		return ResponseEntity.ok().body(potencial);
	}
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<List<Candidato>> search(@RequestParam(value = "nome", defaultValue = "") String nome){
	
		List<Candidato> candidatos = service.search(nome);
		return ResponseEntity.ok().body(candidatos);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody @Valid CandidatoDto candidatoDTO) {
		Candidato candidato = service.fromDto(candidatoDTO);
		candidato = service.insert(candidato);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(candidato.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/lista", method = RequestMethod.POST)
	public ResponseEntity<Void> insertList(@RequestBody List<Candidato> obj) {
		List<Candidato> candidato = new ArrayList<>();
		candidato = service.insertList(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(candidato.toArray())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Candidato> update(@RequestBody @Valid CandidatoDto candidatoDto, @PathVariable Integer id) {
		Candidato candidato = service.fromDto(candidatoDto);
		candidato.setId(id);
		candidato = service.update(candidato);
		return ResponseEntity.noContent().build();
	}

}
