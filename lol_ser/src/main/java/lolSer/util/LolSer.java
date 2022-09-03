package lolSer.util;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lolSer.Controller.InfoPopUpController;
import lolSer.St2;

import java.io.IOException;

import static lolSer.util.StringResources.IN_GAME_INFO_POP_UP;

public class LolSer implements InfoPopUpController.InfoPopUpDelegate {
    private Thread LolSer;
    private int THREAD_RUN_NUM = 0;
    private int THREAD_SLEEP_MIL = 1000;
    public boolean GAME_START = false;
    private HBox element;
    private Label logoBackground;
    private int logoBranch = 0;

    private Label title;

    public Popup popup;
    public InfoPopUpController infoPopUp;

    public LolSer() {
        LolSer = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!GAME_START) {
                    System.out.println("LolSer running");
                    System.out.println(THREAD_RUN_NUM);
                    if(THREAD_RUN_NUM > 30) THREAD_SLEEP_MIL = 2000;
                    if(THREAD_RUN_NUM > 100) {
                        THREAD_RUN_NUM = 0;
                        THREAD_SLEEP_MIL = 1000;
                    }
                    if(logoBranch > 10) {
                        logoBranch = 1;
                    }
                    setLogo(logoBackground, logoBranch);
                    logoBranch++;
                    if(SavePref.getGameId() == Long.MIN_VALUE) {
                        new MakeUrl().HTTP_GET(MakeUrl.CURRENT_GAME);
                        new MakeUrl().HTTP_GET(MakeUrl.CHECK_LOCAL_GAME_START);
                    }else {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                gameStartMinimum();
                            }
                        });
                        if(SavePref.getClientStart()) {
                            infoPopUp.infoPopUpDelegate.hide();
                        }
                        GAME_START = true;
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            title.setText(StringUtil.loadingString(title.getText()));
                        }
                    });
                    try {
                        Thread.sleep(THREAD_SLEEP_MIL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    THREAD_RUN_NUM++;
                }
                System.out.println("THREAD STOP");
            }
        });
    }
    public void lolSerStart(Label title, Label logoBackground) {
        if(LolSer != null) {
            GAME_START = false;
            LolSer.start();
            this.title = title;
            this.logoBackground = logoBackground;
        }
    }
    public void stop() {
        GAME_START = true;
    }
    public void setWindow(HBox element) {
        this.element = element;
    }
    public void gameStartMinimum() {
        Stage stage = (Stage)element.getScene().getWindow();
        stage.setIconified(true);
        try {
            popup = new Popup();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(IN_GAME_INFO_POP_UP));
            AnchorPane parent = loader.load();
            infoPopUp = loader.<InfoPopUpController>getController();

            popup.getContent().add(parent);
            popup.setX(Screen.getScreens().get(0).getBounds().getMaxX()-popup.getWidth()-1);
            popup.setY(Screen.getScreens().get(0).getBounds().getMaxY()-popup.getHeight()-1);
            infoPopUp.popup = popup;
            infoPopUp.infoPopUpDelegate = this;
            infoPopUp.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setLogo(Label logo, int branch) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                switch (branch) {
                    case 0:
                        logo.setGraphic(new ImageView("image/loading_0.png"));
                        break;
                    case 1:
                        logo.setGraphic(new ImageView("image/loading_1.png"));
                        break;
                    case 2:
                        logo.setGraphic(new ImageView("image/loading_2.png"));
                        break;
                    case 3:
                        logo.setGraphic(new ImageView("image/loading_3.png"));
                        break;
                    case 4:
                        logo.setGraphic(new ImageView("image/loading_4.png"));
                        break;
                    case 5:
                        logo.setGraphic(new ImageView("image/loading_5.png"));
                        break;
                    case 6:
                        logo.setGraphic(new ImageView("image/loading_6.png"));
                        break;
                    case 7:
                        logo.setGraphic(new ImageView("image/loading_7.png"));
                        break;
                    case 8:
                        logo.setGraphic(new ImageView("image/loading_8.png"));
                        break;
                    case 9:
                        logo.setGraphic(new ImageView("image/loading_9.png"));
                        break;
                    case 10:
                        logo.setGraphic(new ImageView("image/loading_10.png"));
                        break;

                }
            }
        });
    }

    @Override
    public void show() {
        popup.show(St2.getPrimaryStage());
    }

    @Override
    public void hide() {
        popup.hide();
        popup.getContent().clear();
    }
}
