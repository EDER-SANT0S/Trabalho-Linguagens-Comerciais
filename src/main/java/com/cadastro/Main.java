package com.cadastro;

import com.cadastro.controller.PessoaController;
import com.cadastro.view.PessoaView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastro de Pessoas");

        PessoaView view = new PessoaView();
        new PessoaController(view);

        Scene scene = new Scene(view.getRoot(), 860, 620);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}