package wkAgenciaDeSangue.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

import wkAgenciaDeSangue.Entities.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {

	@Transactional
	@Query(value = "SELECT estado, count(estado)FROM Candidato group by estado order by count(estado) desc", nativeQuery = true)
	public List<Object[]> findByEstado();

	@Transactional
	@Query(value = "SELECT tipo_sanguineo, avg(TIMESTAMPDIFF(YEAR, data_nasc, CURDATE())) as mediaIdade FROM Candidato  group by tipo_sanguineo", nativeQuery = true)
	List<Object[]> countByTipoSanguineo();
	
	@Transactional
	@Query( value = "SELECT sexo,"
			+ " COUNT(id) AS total_pessoas,"
			+ " SUM(CASE WHEN (peso / (altura * altura)) > 30 AND sexo = 'Masculino' THEN 1 ELSE 0 END) AS total_obesos_masculinos,"
			+ " SUM(CASE WHEN (peso / (altura * altura)) > 30 AND sexo = 'Feminino' THEN 1 ELSE 0 END) AS total_obesos_femininos,"
			+ " (SUM(CASE WHEN (peso / (altura * altura)) > 30 AND sexo = 'Masculino' THEN 1 ELSE 0 END)+"
			+ " SUM(CASE WHEN (peso / (altura * altura)) > 30 AND sexo = 'Feminino' THEN 1 ELSE 0 END)) / COUNT(*) * 100 AS percentual_obesos"
			+ " FROM Candidato "
			+ " GROUP BY sexo;", nativeQuery = true)
			
	List<Object[]> countByObeso();

}
