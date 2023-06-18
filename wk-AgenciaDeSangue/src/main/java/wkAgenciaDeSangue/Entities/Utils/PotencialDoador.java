package wkAgenciaDeSangue.Entities.Utils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wkAgenciaDeSangue.Entities.Candidato;

public class PotencialDoador {

	public Map<String, Integer> calculatePotencialDoador(List<Candidato> candidates) {
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

		return potentialDonors;
	}

}
