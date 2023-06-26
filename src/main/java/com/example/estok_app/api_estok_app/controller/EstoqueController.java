package com.example.estok_app.api_estok_app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.estok_app.api_estok_app.json.Response;
import com.example.estok_app.api_estok_app.jwt.JwtController;
import com.example.estok_app.api_estok_app.models.LoginModel;
import com.example.estok_app.api_estok_app.models.ProductModel;
import com.example.estok_app.api_estok_app.models.StockModel;
import com.example.estok_app.api_estok_app.repository.ProductRepository;
import com.example.estok_app.api_estok_app.repository.StockRepository;
import com.example.estok_app.api_estok_app.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<?> lista(HttpServletRequest request) {

        String token = JwtController.pegaTokenHeader(request);

        LoginModel user = userRepository.findByToken(token);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(401, "Acesso negado"));
        }

        
        List<StockModel> stockList = stockRepository.findAll();
        return ResponseEntity.ok(stockList);
    
    }

    @PostMapping
    public ResponseEntity<?> register(HttpServletRequest request, @RequestBody StockModel stock) {
        String token = JwtController.pegaTokenHeader(request);

        LoginModel user = userRepository.findByToken(token);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(401, "Acesso negado"));
        }

        stockRepository.save(stock);
        return ResponseEntity.status(200).body(new Response(200, "Cadastro feito com Sucesso"));

    }

    @PutMapping
    public ResponseEntity<?> atualizar(HttpServletRequest request, @RequestBody StockModel stock) {
        String token = JwtController.pegaTokenHeader(request);

        LoginModel user = userRepository.findByToken(token);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(401, "Acesso negado"));
        }

        Optional<StockModel> estoqueOptional = stockRepository.findById(stock.getId());

        if (estoqueOptional.isPresent()) {
            StockModel estoque = estoqueOptional.get();
            estoque.setDescricao(stock.getDescricao());
            estoque.setQuantidade_total(stock.getQuantidade_total());
            estoque.setData_entrada(stock.getData_entrada());
            estoque.setData_validade(stock.getData_validade());
            estoque.setTipo(stock.getTipo());

            if (estoque.getQuantidade_total() == 0) {
                estoque.setStatus_estoque("EM FALTA");
            } else if (estoque.getQuantidade_total() > 1 && estoque.getQuantidade_total() < 5) {
                estoque.setStatus_estoque("EM AVISO");
            } else {
                estoque.setStatus_estoque("EM ESTOQUE");
            }

            stockRepository.save(estoque);
            return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Estoque atualizado com sucesso"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(404, "Estoque não encontrado"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(HttpServletRequest request, @PathVariable Long id) {
        Optional<StockModel> estoqueOptional = stockRepository.findById(id);
        String token = JwtController.pegaTokenHeader(request);

        LoginModel user = userRepository.findByToken(token);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(401, "Acesso negado"));
        }

        if (estoqueOptional.isPresent()) {
            stockRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Estoque deletado com sucesso"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(404, "Estoque não encontrado"));
        }
    }

    @GetMapping("/{id}/produtos")
    public ResponseEntity<?> pegarEstoqueProduto(HttpServletRequest request, @PathVariable Long id) {
        String token = JwtController.pegaTokenHeader(request);

        LoginModel user = userRepository.findByToken(token);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(401, "Acesso negado"));
        }

        List<ProductModel> productList = productRepository.findAll();
        return ResponseEntity.ok(productList);
      
    }

    @PostMapping("/{id}/produtos")
    public ResponseEntity<?> registraProduto(HttpServletRequest request, @PathVariable Long id,
            @RequestBody ProductModel product) {
        String token = JwtController.pegaTokenHeader(request);

        LoginModel user = userRepository.findByToken(token);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(401, "Acesso negado"));
        }

        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Produto adicionado ao estoque com sucesso"));

    }

    @PutMapping("/{estoqueId}/produtos")
    public ResponseEntity<?> updateProduto(HttpServletRequest request, @PathVariable Long estoqueId,
            @RequestBody ProductModel produto) {
        Optional<ProductModel> productOptional = productRepository.findById(produto.getId());

         if (productOptional.isPresent()) {
            ProductModel product = productOptional.get();
            product.setNome(produto.getNome());
            product.setDescricao(product.getDescricao());
            product.setImagem(product.getImagem());
            product.setValor_item(product.getValor_item());
            product.setValor_unitario(product.getValor_unitario());
            product.setQuantidade(product.getQuantidade());
            product.setSite(product.getSite());
 
            productRepository.save(produto);
            return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Estoque atualizado com sucesso"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(404, "Estoque não encontrado"));
        }

    }

    @DeleteMapping("/{estoqueId}/produtos/{produtoId}")
    public ResponseEntity<?> removeProduto(HttpServletRequest request, @PathVariable Long estoqueId, @PathVariable Long produtoId) {
        Optional<ProductModel> estoqueOptional = productRepository.findById(produtoId);
        String token = JwtController.pegaTokenHeader(request);

        LoginModel user = userRepository.findByToken(token);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(401, "Acesso negado"));
        }

        if (estoqueOptional.isPresent()) {
            productRepository.deleteById(produtoId);
            return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Estoque deletado com sucesso"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(404, "Estoque não encontrado"));
        }
    }
}
