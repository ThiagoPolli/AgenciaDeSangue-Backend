package wkAgenciaDeSangue.Dto;

import java.math.BigDecimal;

public class QtdObesoDto {
	
	private String sexo;
	private Long total_pessoas;
	private BigDecimal total_obesos_masculinos;
	private BigDecimal total_obesos_femininos;
	private BigDecimal percentual_obesos;
	


	public QtdObesoDto(String sexo, Long total_pessoas, BigDecimal total_obesos_masculinos, BigDecimal total_obesos_femininos,
			BigDecimal percentual_obesos) {
		super();
		this.sexo = sexo;
		this.total_pessoas = total_pessoas;
		this.total_obesos_masculinos = total_obesos_masculinos;
		this.total_obesos_femininos = total_obesos_femininos;
		this.percentual_obesos = percentual_obesos;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Long getTotal_pessoas() {
		return total_pessoas;
	}

	public void setTotal_pessoas(Long total_pessoas) {
		this.total_pessoas = total_pessoas;
	}



	public BigDecimal getTotal_obesos_masculinos() {
		return total_obesos_masculinos;
	}

	public void setTotal_obesos_masculinos(BigDecimal total_obesos_masculinos) {
		this.total_obesos_masculinos = total_obesos_masculinos;
	}

	public BigDecimal getTotal_obesos_femininos() {
		return total_obesos_femininos;
	}

	public void setTotal_obesos_femininos(BigDecimal total_obesos_femininos) {
		this.total_obesos_femininos = total_obesos_femininos;
	}

	public BigDecimal getPercentual_obesos() {
		return percentual_obesos;
	}

	public void setPercentual_obesos(BigDecimal percentual_obesos) {
		this.percentual_obesos = percentual_obesos;
	}
	
	

}
