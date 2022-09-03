package lolSer.Dialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lolSer.util.CustomTopBar;
import lolSer.util.LayoutSize;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static lolSer.util.StringResources.NO_DATA_DIALOG;

public class NoDataDialog implements Initializable {
    public HBox top_bar;
    public Label dialog_content;
    public Button okay;
    public AnchorPane dialog_base;

    public String content;

    public NoDataDialog() {
        super();
    }
    public NoDataDialog(String content) {
        super();
        this.content = content;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dialog_base.setPrefWidth(LayoutSize.DIALOG_WIDTH);
        dialog_base.setPrefHeight(LayoutSize.DIALOG_HEIGHT);
        top_bar.setPrefHeight(LayoutSize.TOP_BAR_HEIGHT);
        CustomTopBar.configurationTopBar(top_bar, null, null);
    }
    public void call(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(NO_DATA_DIALOG));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            NoDataDialog noDataDialog = loader.getController();
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            noDataDialog.configureBottom();
            stage.setWidth(LayoutSize.DIALOG_WIDTH);
            stage.setHeight(LayoutSize.DIALOG_HEIGHT);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public void call(String content){
//
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/no_data_dialog.fxml"));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//
//            NoDataDialog noDataDialog = loader.getController();
//            Stage stage = new Stage(StageStyle.UNDECORATED);
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.setResizable(false);
//            stage.setScene(scene);
//            noDataDialog.content = content;
//            noDataDialog.configureBottom();
//            stage.setWidth(LayoutSize.DIALOG_WIDTH);
//            stage.setHeight(LayoutSize.DIALOG_HEIGHT);
//
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    private void configureTopBar() {
//        final double[] initialX = new double[0];
//        final double[] initialY = new double[0];
//
//        top_bar.setPrefHeight(LayoutSize.DIALOG_TOP_BAR_HEIGHT);
//        top_bar.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent me) {
//                if (me.getButton() != MouseButton.MIDDLE) {
//                    initialX[0] = me.getSceneX();
//                    initialY[0] = me.getSceneY();
//                }
//            }
//        });
//
//        top_bar.setOnMouseDragged(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent me) {
//                if (me.getButton() != MouseButton.MIDDLE) {
//                    top_bar.getScene().getWindow().setX(me.getScreenX() - initialX[0]);
//                    top_bar.getScene().getWindow().setY(me.getScreenY() - initialY[0]);
//                }
//            }
//        });
//    }
    public void configureBottom() {
        if(content == null || content.equals("")) {
            dialog_content.setText("해당 이름의 아이디는 존재하지 않습니다.");
        }
        okay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage)top_bar.getScene().getWindow();
                stage.close();
            }
        });
    }
}
