package wkAgenciaDeSangue.Dto;

import java.math.BigDecimal;


public class MediaSanguineoDto  {

	
	private String tipo_sanguineo;
	
	private BigDecimal media;
	
	

	public MediaSanguineoDto(String tipo_sanguineo, BigDecimal media) {
		super();
		this.tipo_sanguineo = tipo_sanguineo;
		this.media = media;
	}

	public String getTipo_sanguineo() {
		return tipo_sanguineo;
	}

	public void setTipo_sanguineo(String tipo_sanguineo) {
		this.tipo_sanguineo = tipo_sanguineo;
	}

	public BigDecimal getMedia() {
		return media;
	}

	public void setMedia(BigDecimal media) {
		this.media = media;
	}
	
	
}
