package wkAgenciaDeSangue.Dto;

public class QtdEstadoDto {
	
	private String estado;
	private Long quantidade;
	
	
	public QtdEstadoDto(String estado, Long quantidade) {
		super();
		this.estado = estado;
		this.quantidade = quantidade;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Long getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	

}
