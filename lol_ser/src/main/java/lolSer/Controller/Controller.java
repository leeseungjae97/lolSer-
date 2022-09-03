package lolSer.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import lolSer.Dialog.NoDataDialog;
import lolSer.JsonObject.ErrorStatus;
import lolSer.JsonObject.LeagueEntries;
import lolSer.JsonObject.LeagueEntry;
import lolSer.JsonObject.Response;
import lolSer.util.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;

import static lolSer.util.StringResources.INSERT_NAME;
import static lolSer.util.StringResources.SEARCHING;

public class Controller implements Initializable {

    public TextField gameName;
    public HBox top_bar;
    public Label minimum;
    public Label close;
    public Label logo;
    public LolSer lolSer;
    public Label title;
    public StackPane searchBar;
    public Label lsLogo;
    public Button cancel;
    public Button getAccount;
    public HBox startInfo;
    public Label name;
    public Label tier;
    public Label logoBackground;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomTopBar.configurationTopBar(top_bar, minimum, close);
        try {
            SavePref.clear();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
        SavePref.setResolution();
        lolSer = new LolSer();
        lolSer.setWindow(top_bar);
        lolSer.setLogo(logoBackground, 0);
        startInfo.setMaxWidth(LayoutSize.INFO_BOX_WIDTH);
        name.setMinHeight(LayoutSize.WHEN_NAME_LENGTH_OVER_15);
        tier.setMinHeight(LayoutSize.WHEN_NAME_LENGTH_OVER_15);

        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                searchBar.setVisible(true);
                cancel.setVisible(false);
                cancel.setManaged(false);
                startInfo.setVisible(false);
                startInfo.setManaged(false);
                title.setText(INSERT_NAME);
                lolSer.stop();
                lolSer = null;
                lolSer = new LolSer();
                lolSer.setWindow(top_bar);
                lolSer.setLogo(logoBackground, 0);
                try {
                    SavePref.clear();
                    SavePref.setResolution();
                } catch (BackingStoreException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getAccount(ActionEvent actionEvent) {
        startLolSer();
    }
    public void startLolSer() {
        SavePref.setGameName(StringUtil.checkSpace(gameName.getText()));
        Response response = new MakeUrl().HTTP_GET(MakeUrl.ACCOUNT_ID);

        if(!SavePref.getId().equals(SavePref.NO_VALUE)) {
            lolSer.lolSerStart(title, logoBackground);
            title.setText(SEARCHING);
            searchBar.setVisible(false);
            cancel.setVisible(true);
            cancel.setManaged(true);
            startInfo.setVisible(true);
            startInfo.setManaged(true);

            int textSize = StringUtil.stringLengthReturnFontSize(SavePref.getGameName());
            name.setText(SavePref.getGameName());
            name.setStyle("-fx-font-size: " + textSize);
            LeagueEntries les = SavePref.getLeagueEntries(MakeUrl.RANK_INFO);
            if(les == null) tier.setText("Unranked");
            else {
                LeagueEntry le;
                for (int i = 0; i < les.getLeagueEntries().length; i++) {
                    if(les.getLeagueEntries()[i].getQueueType().equals("RANKED_SOLO_5x5")) {
                        le = les.getLeagueEntries()[i];
                        if(le != null) {
                            String tierStr = le.getTier() + " " + le.getRank() + " " + le.getLeaguePoints();
                            int textSize2 = StringUtil.stringLengthReturnFontSize(tierStr);
                            tier.setStyle("-fx-font-size: "+textSize2);
                            tier.setText(tierStr);
                        }else {
                            tier.setText("Unranked");
                        }
                    }
                }
            }
        }
        if(response.getClass() == ErrorStatus.class) {
            if(!((ErrorStatus) response).getStatus().message.equals("Data not found")) {
                NoDataDialog noDataDialog = new NoDataDialog();
                noDataDialog.call();
            }
        }
    }

    public void onEnter(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER) {
            startLolSer();
        }
    }
}
