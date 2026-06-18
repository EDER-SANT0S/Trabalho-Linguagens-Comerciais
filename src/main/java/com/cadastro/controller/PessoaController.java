package com.cadastro.controller;

import com.cadastro.model.Pessoa;
import com.cadastro.view.PessoaView;
import javafx.scene.control.ButtonType;

import java.util.ArrayList;
import java.util.Optional;

public class PessoaController {

    private final PessoaView view;
    private final ArrayList<Pessoa> listaPessoas = new ArrayList<>();

    public PessoaController(PessoaView view) {
        this.view = view;
        registrarEventos();
    }

    private void registrarEventos() {
        view.getBtnSalvar().setOnAction(e -> salvarPessoa());
        view.getBtnCancelar().setOnAction(e -> view.limparCampos());
        view.getBtnListar().setOnAction(e -> listarPessoas());
        view.getBtnExcluirUnico().setOnAction(e -> excluirSelecionado());
        view.getBtnLimparTudo().setOnAction(e -> limparRegistros());
    }

    private void salvarPessoa() {
        String nome = view.getNomeDigitado();
        String cpf = view.getCpfDigitado();

        if (nome.isEmpty() || cpf.isEmpty()) {
            view.exibirMensagem("Nome e CPF são obrigatórios");
            return;
        }

        Pessoa pessoa = new Pessoa(nome, cpf, view.getEmailDigitado(), view.getTelefoneDigitado());
        listaPessoas.add(pessoa);
        view.atualizarTabela(listaPessoas);

        view.exibirMensagem("Pessoa cadastrada!");
        view.limparCampos();
    }

    private void listarPessoas() {
        for (Pessoa pessoa : listaPessoas) {
            System.out.println(pessoa);
        }
    }

    private void excluirSelecionado() {
        Pessoa pessoaSelecionada = view.getPessoaSelecionada();

        if (pessoaSelecionada == null) {
            view.exibirMensagem("Selecione uma pessoa na tabela para excluir.");
            return;
        }

        Optional<ButtonType> resultado = view.confirmar("Deseja excluir " + pessoaSelecionada.getNome() + "?");

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            listaPessoas.remove(pessoaSelecionada);
            view.atualizarTabela(listaPessoas);
            view.exibirMensagem("Registro excluído com sucesso!");
        }
    }

    private void limparRegistros() {
        if (listaPessoas.isEmpty()) {
            view.exibirMensagem("Nenhum registro para limpar");
            return;
        }

        Optional<ButtonType> resultado = view.confirmar("Deseja apagar todos os registros?");

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            listaPessoas.clear();
            view.atualizarTabela(listaPessoas);
            view.exibirMensagem("Registros apagados!");
        }
    }
}