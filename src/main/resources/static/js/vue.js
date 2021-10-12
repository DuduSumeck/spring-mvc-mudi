function onLoad() {
	var app = new Vue({
		  el: '#pedidos',
		  data: {
		    pedidos : []
		  },
		  mounted () {
			    axios
			      .get('http://localhost:8080/api/pedidos/aguardando')
			      .then(response => {
			    	  		response.data.forEach(pedido => {
			    	  			pedido.ofertaEnviada = false,
			    	  			pedido.errors = {
			    	  				valor: '',
			    	  				dataEntrega: ''
			    	  			}
			    	  		}
			    	  	)
			    	  	this.pedidos = response.data
			    	})
		  },
		  methods: {
			  enviarOferta: function(pedido) {
    	  			pedido.errors = {
	    	  			valor: '',
	    	  			dataEntrega: ''
	    	  		};
			    axios
			      	.post('http://localhost:8080/api/ofertas', {
			    	  idPedido: pedido.id,
			    	  valor: pedido.valor,
			    	  dataEntrega: pedido.dataEntrega,
			    	  comentario: pedido.comentario
			      	})
			      	.then(response => pedido.ofertaEnviada = true)
			    	.catch(error => {
			    		error.response.data.errors.forEach(error => {
			    			pedido.errors[error.field] = error.defaultMessage;
			    		})
			    	})
			  }
		  }
	})
}