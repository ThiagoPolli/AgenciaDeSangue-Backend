package wkAgenciaDeSangue.Entities;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import wkAgenciaDeSangue.Entities.Utils.IMC;

@Entity
public class Candidato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;

	@CPF(message = "{campo.cpf.invalido}")
	@NotNull(message = "{campo.cpf.obrigatorio}")
	private String cpf;

	@NotNull(message = "{campo.data_nasc.obrigatorio}")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data_nasc;
	@NotNull(message = "{campo.sexo.obrigatorio}")
	private String sexo;

	private String mae;

	private String pai;

	private String email;

	@NotNull(message = "{campo.cep.obrigatorio}")
	private String cep;

	@NotNull(message = "{campo.endereco.obrigatorio}")
	private String endereco;
	@NotNull(message = "{campo.numero.obrigatorio}")
	private Integer numero;
	@NotNull(message = "{campo.cidade.obrigatorio}")
	private String cidade;
	@Size(min = 2, max = 2, message = "{campo.estado.invalido}")
	@NotNull(message = "{campo.estado.obrigatorio}")
	private String estado;

	private String telefone_fixo;
	private String celular;

	@NotNull(message = "{campo.altura.obrigatorio}")
	private Double altura;

	@NotNull(message = "{campo.peso.obrigatorio}")
	private Double peso;

	@NotNull(message = "{campo.tipo_sanguineo.obrigatorio}")
	private String tipo_sanguineo;

	public Candidato() {

	}

	public Double getImc() {
		return IMC.calcularIMC(peso, altura);

	}

	public String getImcResultado() {
		return IMC.resultado(peso, altura);

	}

	public Integer getIdade() {
		LocalDate dataNascimento = getData_nasc().toLocalDate();
		LocalDate dataAtual = LocalDate.now();
		Period periodo = Period.between(dataNascimento, dataAtual);
		int idade = periodo.getYears();
		return idade;
	}

	public Candidato(Integer id, String nome, String cpf, Date data_nasc, String sexo, String mae, String pai,
			String email, String cep, String endereco, Integer numero, String cidade, String estado,
			String telefone_fixo, String celular, Double altura, Double peso, String tipo_sanguineo) {

		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.data_nasc = data_nasc;
		this.sexo = sexo;
		this.mae = mae;
		this.pai = pai;
		this.email = email;
		this.cep = cep;
		this.endereco = endereco;
		this.numero = numero;
		this.cidade = cidade;
		this.estado = estado;
		this.telefone_fixo = telefone_fixo;
		this.celular = celular;
		this.altura = altura;
		this.peso = peso;
		this.tipo_sanguineo = tipo_sanguineo;
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
