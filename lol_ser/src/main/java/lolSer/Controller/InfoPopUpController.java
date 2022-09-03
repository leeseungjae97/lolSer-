package lolSer.Controller;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import lolSer.JsonObject.*;
import lolSer.util.*;

import java.util.HashMap;

public class InfoPopUpController {
    public interface InfoPopUpDelegate{
        void show();
        void hide();
    }

    public final double INFO_WIDTH_BRANCH = 4.2;
    public final double INFO_HEIGHT_BRANCH = 10.5;
    public final double INFO_VGAP = 41;

    public final int LOADING_WINDOWS_NUMBER = 5;

    public final double IF_TEAM_DOWN_START = 3.3;
    public final double IS_TEAM_DOWN = 100.0;

    public AnchorPane info_background;
    public Popup popup;
    public GridPane infoWindows;

    private final String[] otherTeamName;
    private final String[] otherTeamId;
    private HashMap<String, Integer>otherTeamChampId;
    private MatchTotalInfo matchTotalInfo;
    private CurrentGameInfo cgi;

    public InfoPopUpDelegate infoPopUpDelegate;

    private int GET_ENEMY_MATCHES_BRANCH_VALUE = MakeUrl.EN1_MATCH_LIST;

    public InfoPopUpController() {
        cgi = SavePref.getCurrentGameInfo();
        otherTeamName = new String[cgi.getParticipants().size() / 2];
        otherTeamId = new String[cgi.getParticipants().size() / 2];
        otherTeamChampId = new HashMap<>();
    }
    public void getOtherTeam() {
        int otherTeam = 0;
        for (int i = 0; i < cgi.getParticipants().size(); i++) {
            if(cgi.getParticipants().get(i).getTeamId() != SavePref.getTeamId()) {
                otherTeamName[otherTeam] = cgi.getParticipants().get(i).getSummonerName();
                otherTeamId[otherTeam] = cgi.getParticipants().get(i).getSummonerId();
                matchTotalInfo = new MakeUrl().getTimeLineByAccount(StringUtil.checkSpace(otherTeamName[otherTeam])
                        , MakeUrl.ACCOUNT_ID, GET_ENEMY_MATCHES_BRANCH_VALUE);
                otherTeamChampId.put(otherTeamId[otherTeam], cgi.getParticipants().get(i).getChampionId());
                setInfoWindows(infoWindows, otherTeam);

                GET_ENEMY_MATCHES_BRANCH_VALUE++;
                otherTeam++;
            }
        }
        GET_ENEMY_MATCHES_BRANCH_VALUE = MakeUrl.GET_EN1_MATCHES;
    }

    public void load() {
        getOtherTeam();
        info_background.setPrefWidth(SavePref.getResolutionHeight());
        info_background.setPrefHeight(SavePref.getResolutionWidth());

        if(SavePref.getTeamId() == IS_TEAM_DOWN) {
            AnchorPane.setTopAnchor(infoWindows, (SavePref.getResolutionHeight() / IF_TEAM_DOWN_START));
        }

        closePopUp(info_background);
        show();
    }
    private void setInfoWindows(GridPane infoWindows, int index) {
        infoWindows.setHgap(INFO_VGAP);
        VBox background = new VBox();
        ColorAdjust adj = new ColorAdjust(0,10,0,0);
        GaussianBlur blur = new GaussianBlur(45);
        adj.setInput(blur);
        background.setStyle("-fx-background-color: rgb(0,10,21,0.9); " +
            "-fx-background-radius: 5");
        background.setEffect(adj);

        VBox vBox = new VBox();
//        vBox.setStyle("-fx-background-color: rgb(0,10,21,0.9); " +
//                "-fx-background-radius: 5");
        vBox.setStyle("-fx-background-color: #FFFFFF00");
        vBox.setSpacing(15);
        vBox.setPrefWidth(SavePref.getResolutionWidth() / INFO_WIDTH_BRANCH);
        vBox.setPrefHeight(SavePref.getResolutionHeight() / INFO_HEIGHT_BRANCH);

        LolInfoStatistics lis = new LolInfoStatistics(matchTotalInfo, otherTeamId[index], otherTeamChampId, index);

        setInfoContent(vBox, otherTeamName[index], lis);
        infoWindows.add(background, index, 0);
        infoWindows.add(vBox, index, 0);
    }

    private void setInfoContent(VBox vBox, String otherTeamName, LolInfoStatistics lis) {
        Label name = new Label(otherTeamName);
        Label rankPosition = new Label(lis.getRankPostion());
        Label rankWindRate = new Label(lis.getRankWinRate());
        Label teamContribution = new Label(lis.getBeforeGameTeamContribution());
        Label wardPlaced = new Label("평균 와드 설치 수 : "+lis.getWardPlaced() + "(제어와드 + 일반와드)");

        name.setStyle("-fx-font-size: 18; -fx-text-fill: white;-fx-font-family: 'IBM Plex Sans KR';-fx-pref-width:"+ vBox.getPrefWidth());
        rankPosition.setStyle("-fx-font-size: 22; -fx-text-fill: white;-fx-font-family: 'IBM Plex Sans KR';-fx-pref-width:"+ vBox.getPrefWidth());
        teamContribution.setStyle("-fx-font-size: 15; -fx-text-fill: white;-fx-font-family: 'IBM Plex Sans KR';");
        wardPlaced.setStyle("-fx-font-size: 15; -fx-text-fill: white;-fx-font-family: 'IBM Plex Sans KR';");
        rankWindRate.setStyle("-fx-font-size: 15; -fx-text-fill: white;-fx-font-family: 'IBM Plex Sans KR';");

        name.setAlignment(Pos.BASELINE_CENTER);
        rankPosition.setAlignment(Pos.BASELINE_CENTER);

        vBox.getChildren().addAll(name, rankPosition, rankWindRate, teamContribution, wardPlaced);
    }


    private void show() {
        infoPopUpDelegate.show();
    }
    public void closePopUp(AnchorPane info_background) {
        info_background.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                infoPopUpDelegate.hide();
            }
        });
    }
}
