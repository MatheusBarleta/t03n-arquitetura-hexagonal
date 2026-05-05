package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.*;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.*;
import java.util.stream.Collectors;

public class PedidoEventMapper {
    public static PedidoBO toBo(PedidoEventDTO dto) {
        PedidoBO bo = new PedidoBO();
        bo.setCep(dto.getZipCode()); // zipCode (JSON) -> cep (Sistema)

        PessoaBO pessoa = new PessoaBO();
        pessoa.setId(dto.getCustomerId());
        bo.setPessoa(pessoa);

        if (dto.getOrderItems() != null) {
            bo.setItens(dto.getOrderItems().stream().map(itemDto -> {
                PedidoProdutoBO itemBo = new PedidoProdutoBO();
                ProdutoBO produto = new ProdutoBO();

                // Mapeamento crucial: sku do JSON é o ID do seu ProdutoBO
                produto.setId(itemDto.getSku());

                itemBo.setProduto(produto);
                itemBo.setQuantidade(itemDto.getAmount());
                return itemBo;
            }).collect(Collectors.toList()));
        }
        return bo;
    }
}