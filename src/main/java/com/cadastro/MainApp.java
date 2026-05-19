package com.cadastro;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

public class MainApp extends Application {

    private final ArrayList<Pessoa> listaPessoas = new ArrayList<>();
    private final ObservableList<Pessoa> dadosTabela = FXCollections.observableArrayList();

    private TextField campoNome;
    private TextField campoCpf;
    private TextField campoEmail;
    private TextField campoTelefone;

    private Label labelMensagem;
    private TableView<Pessoa> tabela; 

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastro de Pessoas");

        VBox root = new VBox(16);
        root.setPadding(new Insets(24));

        root.getChildren().addAll(
                criarCabecalho(),
                criarFormulario(),
                criarBarraBotoes(),
                criarLabelMensagem(),
                criarTabela()
        );

        Scene scene = new Scene(root, 860, 620);
        primaryStage.setScene(scene);
        primaryStage.show();
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
        Button btnSalvar = new Button("Salvar");
        Button btnCancelar = new Button("Cancelar");
        Button btnListar = new Button("Listar");
        Button btnExcluirUnico = new Button("Excluir Selecionado");
        Button btnLimparTudo = new Button("Limpar Tudo");

        btnSalvar.setOnAction(e -> salvarPessoa());
        btnCancelar.setOnAction(e -> limparCampos());
        btnListar.setOnAction(e -> listarPessoas());
        btnExcluirUnico.setOnAction(e -> excluirSelecionado());
        btnLimparTudo.setOnAction(e -> limparRegistros());

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

    private void salvarPessoa() {
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();

        if (nome.isEmpty() || cpf.isEmpty()) {
            exibirMensagem("Nome e CPF são obrigatórios");
            return;
        }

        Pessoa p = new Pessoa(nome, cpf, campoEmail.getText(), campoTelefone.getText());
        listaPessoas.add(p);
        dadosTabela.setAll(listaPessoas);

        exibirMensagem("Pessoa cadastrada!");
        limparCampos();
    }

    private void limparCampos() {
        campoNome.clear();
        campoCpf.clear();
        campoEmail.clear();
        campoTelefone.clear();
    }

    private void listarPessoas() {
        for (Pessoa p : listaPessoas) {
            System.out.println(p);
        }
    }

    private void excluirSelecionado() {
        Pessoa pessoaSelecionada = tabela.getSelectionModel().getSelectedItem();

        if (pessoaSelecionada == null) {
            exibirMensagem("Selecione uma pessoa na tabela para excluir.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Deseja excluir " + pessoaSelecionada.getNome() + "?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            listaPessoas.remove(pessoaSelecionada);
            dadosTabela.remove(pessoaSelecionada);
            exibirMensagem("Registro excluído com sucesso!");
        }
    }

    private void limparRegistros() {
        if (listaPessoas.isEmpty()) {
            exibirMensagem("Nenhum registro para limpar");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Deseja apagar todos os registros?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            listaPessoas.clear();
            dadosTabela.clear();
            exibirMensagem("Registros apagados!");
        }
    }

    private void exibirMensagem(String msg) {
        labelMensagem.setText(msg);
    }

    public static void main(String[] args) {
        launch(args);
    }
}