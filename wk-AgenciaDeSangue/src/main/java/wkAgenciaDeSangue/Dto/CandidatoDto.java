package wkAgenciaDeSangue.Dto;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import wkAgenciaDeSangue.Entities.Candidato;
import wkAgenciaDeSangue.Entities.Utils.IMC;

public class CandidatoDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String cpf;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data_nasc;
	private String sexo;
	private String mae;
	private String pai;
	private String email;
	private String cep;
	private String endereco;
	private Integer numero;
	private String cidade;
	private String estado;
	private String telefone_fixo;
	private String celular;
	private Double altura;
	private Double peso;
	private String tipo_sanguineo;
	
	
	public CandidatoDto() {
		
	}
	public Double getImc() {
		return IMC.calcularIMC(peso, altura);
		 
	}
	public String getImcResultado() {
		return IMC.resultado(peso, altura);
		 
	}
	
	public CandidatoDto(Candidato obj) {
		id = obj.getId();
		nome = obj.getNome();
		cpf = obj.getCpf();
		data_nasc = obj.getData_nasc();
		sexo = obj.getSexo();
		mae = obj.getMae();
		pai = obj.getPai();
		email = obj.getEmail();
		cep = obj.getCep();
		endereco = obj.getEndereco();
		numero = obj.getNumero();
		cidade = obj.getCidade();
		estado = obj.getEstado();
		telefone_fixo = obj.getTelefone_fixo();
		celular = obj.getCelular();
		altura = obj.getAltura();
		peso = obj.getPeso();
		tipo_sanguineo = obj.getTipo_sanguineo();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefone_fixo() {
		return telefone_fixo;
	}

	public void setTelefone_fixo(String telefone_fixo) {
		this.telefone_fixo = telefone_fixo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getTipo_sanguineo() {
		return tipo_sanguineo;
	}

	public void setTipo_sanguineo(String tipo_sanguineo) {
		this.tipo_sanguineo = tipo_sanguineo;
	}
	

}
