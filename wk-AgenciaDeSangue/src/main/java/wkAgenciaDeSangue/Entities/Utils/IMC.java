package wkAgenciaDeSangue.Entities.Utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wkAgenciaDeSangue.Entities.Candidato;

public class IMC {
	
	
	public  IMC() {
		
	}
	  public static  Double calcularIMC(double peso, double altura) {

	        double imc = peso / (altura * altura);
	       
	        return imc;
	    }
	  
	public static String resultado(double peso, double altura) {
		Double result =calcularIMC(peso,altura);
		if(result <= 18.5) {
			return "Magreza";
		}
		else if(result > 18.5 && result < 24.9) {
			return "Normal";
		}
		else if(result > 25.0 && result < 29.9) {
			return "Sobrepeso";
		}
		else if(result > 30.0 && result < 39.9) {			
			return "Obeso";
		}
		else if (result > 40.0) {
			return "Obesidade Grave";
		}
	
		return null;
		
	}
	
	public Map<String, List<Object>> calcularIMCMedioPorFaixaIdade(List<Candidato> candidatos) {
	    Map<String, List<Object>> imcMedioPorFaixaIdade = new HashMap<>();

	    for (int idadeInicial = 0; idadeInicial <= 100; idadeInicial += 10) {
	        int idadeFinal = idadeInicial + 9;

	        double somaIMC = 0;
	        int contador = 0;

	        for (Candidato candidato : candidatos) {
	            LocalDate dataNascimento = candidato.getData_nasc().toLocalDate();
	            int idade = calcularIdade(dataNascimento);

	            if (idade >= idadeInicial && idade <= idadeFinal) {
	                double imc = calcularIMC(candidato.getPeso(), candidato.getAltura());
	                somaIMC += imc;
	                contador++;
	            }
	        }

	        if (contador > 0) {
	            double imcMedio = somaIMC / contador;
	            String faixaIdade = idadeInicial + "-" + idadeFinal;
	            
	            imcMedioPorFaixaIdade.putIfAbsent("faixasIdade", new ArrayList<>());
	            imcMedioPorFaixaIdade.putIfAbsent("mediasIMC", new ArrayList<>());
	            
	            imcMedioPorFaixaIdade.get("faixasIdade").add(faixaIdade);
	            imcMedioPorFaixaIdade.get("mediasIMC").add(imcMedio);
	        }
	    }

	    return imcMedioPorFaixaIdade;
	}


	    private int calcularIdade(LocalDate dataNascimento) {
	        LocalDate dataAtual = LocalDate.now();
	        Period periodo = Period.between(dataNascimento, dataAtual);
	        return periodo.getYears();
	    }

	   

}
