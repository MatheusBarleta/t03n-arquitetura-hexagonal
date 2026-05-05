package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.listener;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PedidoServicePort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoEventDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.mapper.PedidoEventMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsPedidoAdapter {
    private final PedidoServicePort pedidoServicePort;

    public SqsPedidoAdapter(PedidoServicePort pedidoServicePort) {
        this.pedidoServicePort = pedidoServicePort;
    }

    @SqsListener("${queue.order-events}")
    public void ouvir(PedidoEventDTO evento) {
        System.out.println("### Nova mensagem recebida ###");
        System.out.println("Cliente ID: " + evento.getCustomerId());
        System.out.println("CEP Destino: " + evento.getZipCode());

        PedidoBO bo = PedidoEventMapper.toBo(evento);
        pedidoServicePort.criarPedido(bo);

        // Log de sucesso
        System.out.println("### Pedido processado e salvo no banco com sucesso ###");
    }
}