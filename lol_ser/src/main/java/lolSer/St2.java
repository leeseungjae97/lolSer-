package lolSer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.lang.reflect.Field;
import java.nio.charset.Charset;

import static lolSer.util.StringResources.MAIN;

public class St2 extends Application {
    public static Stage pStage;
    @Override
    public void init() throws Exception {
        super.init();
        System.setProperty("prism.lcdtext", "false");
        Font.loadFont(getClass().getResourceAsStream("/fonts/IBMPlexSansKR-Bold.woff"), 10);
        Font.loadFont(getClass().getResourceAsStream("/fonts/IBMPlexSansKR-Regular.woff"), 10);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        pStage = primaryStage;
        System.setProperty("file.encoding","UTF-8");
        Field charset = Charset.class.getDeclaredField("defaultCharset");
        charset.setAccessible(true);
        charset.set(null,null);

        Parent root = FXMLLoader.load(getClass().getResource(MAIN));
        primaryStage.setScene(new Scene(root, 560, 540));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static Stage getPrimaryStage() {
        return pStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
