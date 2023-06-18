package wkAgenciaDeSangue.Dto;

import wkAgenciaDeSangue.Entities.Candidato;

public class FaixaIdadeCandidato extends Candidato{

	private static final long serialVersionUID = 1L;
	
	private String faixaIdade;
	private Double imcMedio;
	
	
	public String getFaixaIdade() {
		return faixaIdade;
	}
	public void setFaixaIdade(String faixaIdade) {
		this.faixaIdade = faixaIdade;
	}
	public Double getImcMedio() {
		return imcMedio;
	}
	public void setImcMedio(Double imcMedio) {
		this.imcMedio = imcMedio;
	}
	
	

}
