package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {

	@Query("SELECT o FROM Oferta o WHERE o.pedido = :pedido AND o.status != 'RECUSADA' ")
	List<Oferta> findAllByPedido(Pedido pedido);
	
}
