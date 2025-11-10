package br.com.PdvFrontEnd.service;

import br.com.PdvFrontEnd.dto.ContatoRequest;
import br.com.PdvFrontEnd.dto.ContatoResponse;
import br.com.PdvFrontEnd.model.Contato;
import br.com.PdvFrontEnd.util.HttpClient;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoService {

    public ContatoService() {
    }

    public void addContato(Contato contato) {
        try {
            ContatoRequest request = new ContatoRequest(
                contato.getTelefone(),
                contato.getEmail(),
                contato.getEndereco()
            );

            HttpClient.post("/v1/contatos", request, ContatoResponse.class);
            JOptionPane.showMessageDialog(null,
                "Contato adicionado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao adicionar contato: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Contato> getAllContatos() {
        try {
            ContatoResponse[] responses = HttpClient.getArray("/v1/contatos", ContatoResponse[].class);
            List<Contato> contatos = new ArrayList<>();
            for (ContatoResponse response : responses) {
                Contato contato = new Contato(
                    response.getId(), // PASSAR ID NO CONSTRUTOR
                    response.getTelefone(),
                    response.getEmail(),
                    response.getEndereco()
                );
                contatos.add(contato);
            }
            return contatos;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao listar contatos: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }

    public void removeContato(Long id) {
        try {
            HttpClient.delete("/v1/contatos/" + id);
            JOptionPane.showMessageDialog(null,
                "Contato removido com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao remover contato: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateContato(Long id, Contato contato) {
        try {
            ContatoRequest request = new ContatoRequest(
                contato.getTelefone(),
                contato.getEmail(),
                contato.getEndereco()
            );

            HttpClient.put("/v1/contatos/" + id, request, ContatoResponse.class);
            JOptionPane.showMessageDialog(null,
                "Contato atualizado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao atualizar contato: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
