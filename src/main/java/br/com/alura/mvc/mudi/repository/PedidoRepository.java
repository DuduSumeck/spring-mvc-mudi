package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	List<Pedido> findAllByStatus(StatusPedido status, Pageable pageable);

	@Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username")
	Page<Pedido> findAllByUser(String username, Pageable pageable);

	@Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username AND p.status = :status")
	Page<Pedido> findAllByUserAndStatus(String username, StatusPedido status, Pageable pageable);

	@Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username != :username AND p.status = 'AGUARDANDO'")
	List<Pedido> findAllAguardandoOferta(String username, Pageable pageable);
	
}
