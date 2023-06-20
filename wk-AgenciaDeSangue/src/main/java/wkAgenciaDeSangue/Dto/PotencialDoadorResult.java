package wkAgenciaDeSangue.Dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wkAgenciaDeSangue.Entities.Candidato;

public class PotencialDoadorResult {
	private List<String> tiposSanguineos;
	private List<Integer> quantidades;

	public PotencialDoadorResult(List<String> tiposSanguineos, List<Integer> quantidades) {
		this.tiposSanguineos = tiposSanguineos;
		this.quantidades = quantidades;
	}

	public List<String> getTiposSanguineos() {
		return tiposSanguineos;
	}

	public List<Integer> getQuantidades() {
		return quantidades;
	}

	public PotencialDoadorResult calculatePotencialDoador(List<Candidato> candidates) {
		Map<String, Integer> potentialDonors = new HashMap<>();

		potentialDonors.put("A+", 0);
		potentialDonors.put("A-", 0);
		potentialDonors.put("B+", 0);
		potentialDonors.put("B-", 0);
		potentialDonors.put("AB+", 0);
		potentialDonors.put("AB-", 0);
		potentialDonors.put("O+", 0);
		potentialDonors.put("O-", 0);

		for (Candidato candidate : candidates) {
			String receptorType = candidate.getTipo_sanguineo();
			int count = potentialDonors.getOrDefault(receptorType, 0);
			count++;
			potentialDonors.put(receptorType, count);
		}

		List<String> tiposSanguineos = new ArrayList<>(potentialDonors.keySet());
		List<Integer> quantidades = new ArrayList<>(potentialDonors.values());

		return new PotencialDoadorResult(tiposSanguineos, quantidades);

	}
	
	
}
