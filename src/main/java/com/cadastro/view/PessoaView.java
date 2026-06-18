package com.cadastro.view;

import com.cadastro.model.Pessoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Optional;

public class PessoaView {

    private final VBox root;

    private TextField campoNome;
    private TextField campoCpf;
    private TextField campoEmail;
    private TextField campoTelefone;

    private Label labelMensagem;
    private TableView<Pessoa> tabela;
    private final ObservableList<Pessoa> dadosTabela = FXCollections.observableArrayList();

    private Button btnSalvar;
    private Button btnCancelar;
    private Button btnListar;
    private Button btnExcluirUnico;
    private Button btnLimparTudo;

    public PessoaView() {
        root = new VBox(16);
        root.setPadding(new Insets(24));

        root.getChildren().addAll(
                criarCabecalho(),
                criarFormulario(),
                criarBarraBotoes(),
                criarLabelMensagem(),
                criarTabela()
        );
    }

    private VBox criarCabecalho() {
        Text titulo = new Text("Cadastro de Pessoas");
        Text subtitulo = new Text("Preencha os campos e clique em Salvar.");
        return new VBox(4, titulo, subtitulo);
    }

    private GridPane criarFormulario() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(12);
        grid.setPadding(new Insets(16));

        campoNome = new TextField();
        campoCpf = new TextField();
        campoEmail = new TextField();
        campoTelefone = new TextField();

        grid.add(new Label("Nome *"), 0, 0);
        grid.add(campoNome, 0, 1);
        grid.add(new Label("CPF *"), 1, 0);
        grid.add(campoCpf, 1, 1);

        grid.add(new Label("E-mail"), 0, 2);
        grid.add(campoEmail, 0, 3);
        grid.add(new Label("Telefone"), 1, 2);
        grid.add(campoTelefone, 1, 3);

        return grid;
    }

    private HBox criarBarraBotoes() {
        btnSalvar = new Button("Salvar");
        btnCancelar = new Button("Cancelar");
        btnListar = new Button("Listar");
        btnExcluirUnico = new Button("Excluir Selecionado");
        btnLimparTudo = new Button("Limpar Tudo");

        HBox barra = new HBox(10, btnSalvar, btnCancelar, btnListar, btnExcluirUnico, btnLimparTudo);
        barra.setAlignment(Pos.CENTER_LEFT);

        return barra;
    }

    private Label criarLabelMensagem() {
        labelMensagem = new Label("");
        return labelMensagem;
    }

    private VBox criarTabela() {
        tabela = new TableView<>(dadosTabela);

        TableColumn<Pessoa, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Pessoa, String> colCpf = new TableColumn<>("CPF");
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        TableColumn<Pessoa, String> colEmail = new TableColumn<>("E-mail");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Pessoa, String> colTelefone = new TableColumn<>("Telefone");
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        tabela.getColumns().addAll(colNome, colCpf, colEmail, colTelefone);

        return new VBox(10, new Label("Pessoas Cadastradas"), tabela);
    }

    public String getNomeDigitado() {
        return campoNome.getText();
    }

    public String getCpfDigitado() {
        return campoCpf.getText();
    }

    public String getEmailDigitado() {
        return campoEmail.getText();
    }

    public String getTelefoneDigitado() {
        return campoTelefone.getText();
    }

    public Pessoa getPessoaSelecionada() {
        return tabela.getSelectionModel().getSelectedItem();
    }

    public void atualizarTabela(List<Pessoa> pessoas) {
        dadosTabela.setAll(pessoas);
    }

    public void exibirMensagem(String mensagem) {
        labelMensagem.setText(mensagem);
    }

    public void limparCampos() {
        campoNome.clear();
        campoCpf.clear();
        campoEmail.clear();
        campoTelefone.clear();
    }

    public Optional<ButtonType> confirmar(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(mensagem);
        return alert.showAndWait();
    }

    public VBox getRoot() {
        return root;
    }

    public Button getBtnSalvar() {
        return btnSalvar;
    }

    public Button getBtnCancelar() {
        return btnCancelar;
    }

    public Button getBtnListar() {
        return btnListar;
    }

    public Button getBtnExcluirUnico() {
        return btnExcluirUnico;
    }

    public Button getBtnLimparTudo() {
        return btnLimparTudo;
    }
}