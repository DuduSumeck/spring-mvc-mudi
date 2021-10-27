package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusOferta;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {

	@Query("SELECT o FROM Oferta o WHERE o.pedido = :pedido")
	List<Oferta> findAllByPedido(Pedido pedido);

	@Query("SELECT o FROM Oferta o JOIN o.user u WHERE u.username = :username")
	Page<Oferta> findAllByUser(String username, Pageable pageable);

	@Query("SELECT o FROM Oferta o JOIN o.user u WHERE u.username = :username AND o.status = :status")
	Page<Oferta> findAllByUserAndStatus(String username, StatusOferta status, Pageable pageable);
}
