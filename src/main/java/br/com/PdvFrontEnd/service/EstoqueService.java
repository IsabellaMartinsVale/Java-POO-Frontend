package br.com.PdvFrontEnd.service;

import br.com.PdvFrontEnd.dto.EstoqueRequest;
import br.com.PdvFrontEnd.dto.EstoqueResponse;
import br.com.PdvFrontEnd.model.Estoque;
import br.com.PdvFrontEnd.util.HttpClient;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueService {

    public EstoqueService() {
    }

    public void addEstoque(Estoque estoque) {
        try {
            EstoqueRequest request = new EstoqueRequest(
                estoque.getQuantidade(),
                estoque.getLocalTanque(),
                estoque.getLocalEndereco(),
                estoque.getLoteFabricacao(),
                estoque.getDataValidade()
            );

            HttpClient.post("/estoques", request, EstoqueResponse.class);
            JOptionPane.showMessageDialog(null,
                "Estoque adicionado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao adicionar estoque: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Estoque> getAllEstoques() {
        try {
            EstoqueResponse[] responses = HttpClient.getArray("/estoques", EstoqueResponse[].class);
            List<Estoque> estoques = new ArrayList<>();
            for (EstoqueResponse response : responses) {
                Estoque estoque = new Estoque(
                    response.getId(), // PASSAR ID NO CONSTRUTOR
                    response.getQuantidade(),
                    response.getLocalTanque(),
                    response.getLocalEndereco(),
                    response.getLoteFabricacao(),
                    response.getDataValidade()
                );
                estoques.add(estoque);
            }
            return estoques;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao listar estoques: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }

    public void removeEstoque(Long id) {
        try {
            HttpClient.delete("/estoques/" + id);
            JOptionPane.showMessageDialog(null,
                "Estoque removido com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao remover estoque: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateEstoque(Long id, Estoque estoque) {
        try {
            EstoqueRequest request = new EstoqueRequest(
                estoque.getQuantidade(),
                estoque.getLocalTanque(),
                estoque.getLocalEndereco(),
                estoque.getLoteFabricacao(),
                estoque.getDataValidade()
            );

            HttpClient.put("/estoques/" + id, request, EstoqueResponse.class);
            JOptionPane.showMessageDialog(null,
                "Estoque atualizado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao atualizar estoque: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
