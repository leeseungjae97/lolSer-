package lolSer.JsonObject;

import com.google.gson.Gson;
import lolSer.util.MakeUrl;
import lolSer.util.SavePref;
import org.json.JSONArray;

public class JsonHelper {
    public static String[] jsonArrayToStringArray(JSONArray ja, int branch) {
        if(branch == MakeUrl.GET_EN1_MATCHES) SavePref.setEnemy1MatchesNumber(ja.length());
        if(branch == MakeUrl.GET_EN2_MATCHES) SavePref.setEnemy2MatchesNumber(ja.length());
        if(branch == MakeUrl.GET_EN3_MATCHES) SavePref.setEnemy3MatchesNumber(ja.length());
        if(branch == MakeUrl.GET_EN4_MATCHES) SavePref.setEnemy4MatchesNumber(ja.length());
        if(branch == MakeUrl.GET_EN5_MATCHES) SavePref.setEnemy5MatchesNumber(ja.length());

        String[] st = new String[ja.length()];
        for (int i = 0; i < ja.length(); i++) {
            st[i] = ja.getString(i);
        }
        return st;
    }
    public static LocalCurrentGameInfo[] jsonArrayToLocalCurrentGameInfo(JSONArray ja) {
        LocalCurrentGameInfo[] st = new LocalCurrentGameInfo[ja.length()];
        for (int i = 0; i < ja.length(); i++) {
            st[i] = new Gson().fromJson(ja.getJSONObject(i).toString(), LocalCurrentGameInfo.class);;
        }
        return st;
    }
    public static LeagueEntry[] jsonArrayToLeagueEntry(JSONArray ja) {
        LeagueEntry[] les = new LeagueEntry[ja.length()];
        for (int i = 0; i < ja.length(); i++) {
            les[i] = new Gson().fromJson(ja.getJSONObject(i).toString(), LeagueEntry.class);
        }
        return les;
    }
}
