
package com.companyz.app;
import javafx.application.Application;
import javafx.stage.Stage;
import com.companyz.app.controller.LoginController;
public class MainApp extends Application{
    @Override public void start(Stage stage){
        new LoginController().show(stage);
    }
    public static void main(String[] args){launch(args);}
}
