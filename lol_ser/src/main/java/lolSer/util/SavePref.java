package lolSer.util;

import lolSer.JsonObject.*;

import java.awt.*;
import java.util.Arrays;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import static lolSer.util.StringResources.*;

public class SavePref {
    public final static String NO_VALUE = "NO_VALUE";
    public final static Long NO_VALUE_LONG = Long.MIN_VALUE;
    public final static int NO_VALUE_INT = -1;
    public final static byte[] NO_VALUE_BYTE = {0};
    public final static String[] NO_VALUE_STRING_ARRAY = {"null"};
    public final static boolean NO_VALUE_BOOLEAN = false;

    private static Preferences pf = Preferences.userRoot();

    public static void setGameName(String gameName) {
        pf.put(GAME_NAME, gameName);
    }
    public static String getGameName() {
        return pf.get(GAME_NAME, NO_VALUE);
    }
    public static void setGameId(long gameId) {
        pf.putLong(GAME_ID, gameId);
    }
    public static long getGameId() {
        return pf.getLong(GAME_ID, NO_VALUE_LONG);
    }

    public static String getPuuid(int branch) {
        switch (branch) {
            case MakeUrl.GET_EN1_MATCHES:
                return pf.get(ENEMY1_PUUID, NO_VALUE);
            case MakeUrl.GET_EN2_MATCHES:
                return pf.get(ENEMY2_PUUID, NO_VALUE);
            case MakeUrl.GET_EN3_MATCHES:
                return pf.get(ENEMY3_PUUID, NO_VALUE);
            case MakeUrl.GET_EN4_MATCHES:
                return pf.get(ENEMY4_PUUID, NO_VALUE);
            case MakeUrl.GET_EN5_MATCHES:
                return pf.get(ENEMY5_PUUID, NO_VALUE);
            case MakeUrl.CURRENT_GAME:
                return pf.get(PUUID, NO_VALUE);
        }
        return NO_VALUE;
    }
    public static void setPuuid(String puuid, int branch) {
        switch (branch) {
            case MakeUrl.GET_EN1_MATCHES:
                pf.put(ENEMY1_PUUID, puuid);
                break;
            case MakeUrl.GET_EN2_MATCHES:
                pf.put(ENEMY2_PUUID, puuid);
                break;
            case MakeUrl.GET_EN3_MATCHES:
                pf.put(ENEMY3_PUUID, puuid);
                break;
            case MakeUrl.GET_EN4_MATCHES:
                pf.put(ENEMY4_PUUID, puuid);
                break;
            case MakeUrl.GET_EN5_MATCHES:
                pf.put(ENEMY5_PUUID, puuid);
                break;
            case MakeUrl.CURRENT_GAME:
                pf.put(PUUID, NO_VALUE);
                break;
        }
    }
    public static String getId() {
        return pf.get(ID, NO_VALUE);
    }


    public static long getTeamId() {
        return pf.getLong(TEAM_ID, NO_VALUE_LONG);
    }
    public static void setTeamId(long teamId) {
        pf.putLong(TEAM_ID, teamId);
    }


    public static void setAccount(String puuid, String gameName) {
        pf.put(PUUID, puuid);
        pf.put(GAME_NAME, gameName);
    }
    public static Account getAccount() {
        String puuid = pf.get(PUUID, NO_VALUE);
        String gameName = pf.get(GAME_NAME, NO_VALUE);

        return new Account(puuid, gameName);
    }

    public static void setCurrentGameInfo(CurrentGameInfo currentGameInfo) {
        pf.putByteArray(CURRENT_GAME_INFO, Seriallization.serializeData(currentGameInfo));
    }
    public static CurrentGameInfo getCurrentGameInfo() {
        return (CurrentGameInfo)Seriallization
                .deserializeData(pf.getByteArray(CURRENT_GAME_INFO, NO_VALUE_BYTE));
    }
    public static void setClientStart(boolean value) {
        pf.putBoolean(CLIENT_STR, value);
    }
    public static boolean getClientStart() {
        return pf.getBoolean(CLIENT_STR, NO_VALUE_BOOLEAN);
    }
    public static void setLeagueEntries(LeagueEntries leagueEntries, int branch) {
        switch (branch) {
            case MakeUrl.EN1_RANK_INFO:
                pf.putByteArray(ENEMY1_LEAGUE_ENTRY, Seriallization.serializeData(leagueEntries));
                break;
            case MakeUrl.EN2_RANK_INFO:
                pf.putByteArray(ENEMY2_LEAGUE_ENTRY, Seriallization.serializeData(leagueEntries));
                break;
            case MakeUrl.EN3_RANK_INFO:
                pf.putByteArray(ENEMY3_LEAGUE_ENTRY, Seriallization.serializeData(leagueEntries));
                break;
            case MakeUrl.EN4_RANK_INFO:
                pf.putByteArray(ENEMY4_LEAGUE_ENTRY, Seriallization.serializeData(leagueEntries));
                break;
            case MakeUrl.EN5_RANK_INFO:
                pf.putByteArray(ENEMY5_LEAGUE_ENTRY, Seriallization.serializeData(leagueEntries));
                break;
            case MakeUrl.RANK_INFO:
                pf.putByteArray(LEAGUE_ENTRY, Seriallization.serializeData(leagueEntries));
                break;
        }

    }
    public static LeagueEntries getLeagueEntries(int branch) {
        switch (branch) {
            case MakeUrl.EN1_RANK_INFO:
                return (LeagueEntries)Seriallization
                        .deserializeData(pf.getByteArray(ENEMY1_LEAGUE_ENTRY, NO_VALUE_BYTE));
            case MakeUrl.EN2_RANK_INFO:
                return (LeagueEntries)Seriallization
                        .deserializeData(pf.getByteArray(ENEMY2_LEAGUE_ENTRY, NO_VALUE_BYTE));
            case MakeUrl.EN3_RANK_INFO:
                return (LeagueEntries)Seriallization
                        .deserializeData(pf.getByteArray(ENEMY3_LEAGUE_ENTRY, NO_VALUE_BYTE));
            case MakeUrl.EN4_RANK_INFO:
                return (LeagueEntries)Seriallization
                        .deserializeData(pf.getByteArray(ENEMY4_LEAGUE_ENTRY, NO_VALUE_BYTE));
            case MakeUrl.EN5_RANK_INFO:
                return (LeagueEntries)Seriallization
                        .deserializeData(pf.getByteArray(ENEMY5_LEAGUE_ENTRY, NO_VALUE_BYTE));
            case MakeUrl.RANK_INFO:
                return (LeagueEntries)Seriallization
                        .deserializeData(pf.getByteArray(LEAGUE_ENTRY, NO_VALUE_BYTE));
        }
        return null;
    }
    
    public static void setSummoner(String accountId, int profileIconId, double revisionDate,
                           String name, String id, String puuid, double summonerLevel) {
        pf.put(         ACCOUNT_ID, accountId);
        pf.putInt(      PROFILE_ICON_ID, profileIconId);
        pf.putDouble(   REVISION_DATE, revisionDate);
        pf.put(         GAME_NAME, name);
        pf.put(         ID, id);
        pf.put(         PUUID, puuid);
        pf.putDouble(   SUMMONER_LEVEL, summonerLevel);
    }
    public static Summoner getSummoner() {
        String ac   =   pf.get(ACCOUNT_ID, NO_VALUE);
        String gn   =   pf.get(GAME_NAME, NO_VALUE);
        String id   =   pf.get(ID, NO_VALUE);
        String pi   =   pf.get(PUUID, NO_VALUE);
        int pii     =   pf.getInt(PROFILE_ICON_ID, NO_VALUE_INT);
        double rd   =   pf.getLong(REVISION_DATE, NO_VALUE_LONG);
        double sl   =   pf.getLong(SUMMONER_LEVEL, NO_VALUE_LONG);

        return new Summoner(ac, pii, rd, gn, id, pi, sl);
    }
    public static void clearSummoner() {
        pf.remove(ACCOUNT_ID);
        pf.remove(GAME_NAME);
        pf.remove(ID);
        pf.remove(PUUID);
        pf.remove(PROFILE_ICON_ID);
        pf.remove(REVISION_DATE);
        pf.remove(SUMMONER_LEVEL);
    }
    public static void setEnemy1MatchesNumber(int number) {
        pf.putInt(ENEMY1_MATCHES_NUMBER, number);
    }
    public static void setEnemy2MatchesNumber(int number) {
        pf.putInt(ENEMY2_MATCHES_NUMBER, number);
    }
    public static void setEnemy3MatchesNumber(int number) {
        pf.putInt(ENEMY3_MATCHES_NUMBER, number);
    }
    public static void setEnemy4MatchesNumber(int number) {
        pf.putInt(ENEMY4_MATCHES_NUMBER, number);
    }
    public static void setEnemy5MatchesNumber(int number) {
        pf.putInt(ENEMY5_MATCHES_NUMBER, number);
    }
    
    public static void clear() throws BackingStoreException {
        pf.clear();
    }
    public static void setResolution() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int height =  (int)size.getHeight();
        pf.putInt(RESOLUTION_HEIGHT, width);
        pf.putInt(RESOLUTION_WIDTH, height);
    }

    public static int getResolutionHeight() {
        return pf.getInt(RESOLUTION_HEIGHT, NO_VALUE_INT);
    }
    public static int getResolutionWidth() {
        return pf.getInt(RESOLUTION_WIDTH, NO_VALUE_INT);
    }

    public static void setMatches(String[] matches, int branch) {
        switch (branch) {
            case MakeUrl.GET_EN1_MATCHES:
                pf.put(ENEMY1_MATCHES, Arrays.toString(matches));
                break;
            case MakeUrl.GET_EN2_MATCHES:
                pf.put(ENEMY2_MATCHES, Arrays.toString(matches));
                break;
            case MakeUrl.GET_EN3_MATCHES:
                pf.put(ENEMY3_MATCHES, Arrays.toString(matches));
                break;
            case MakeUrl.GET_EN4_MATCHES:
                pf.put(ENEMY4_MATCHES, Arrays.toString(matches));
                break;
            case MakeUrl.GET_EN5_MATCHES:
                pf.put(ENEMY5_MATCHES, Arrays.toString(matches));
                break;
        }
    }

    public static int getMatchesNumber(int branch) {
        switch (branch) {
            case MakeUrl.GET_EN1_MATCHES:
                return pf.getInt(ENEMY1_MATCHES_NUMBER, NO_VALUE_INT);
            case MakeUrl.GET_EN2_MATCHES:
                return pf.getInt(ENEMY2_MATCHES_NUMBER, NO_VALUE_INT);
            case MakeUrl.GET_EN3_MATCHES:
                return pf.getInt(ENEMY3_MATCHES_NUMBER, NO_VALUE_INT);
            case MakeUrl.GET_EN4_MATCHES:
                return pf.getInt(ENEMY4_MATCHES_NUMBER, NO_VALUE_INT);
            case MakeUrl.GET_EN5_MATCHES:
                return pf.getInt(ENEMY5_MATCHES_NUMBER, NO_VALUE_INT);
        }
        return NO_VALUE_INT;
    }

    public static String[] getMatches(int branch) {
        switch (branch) {
            case MakeUrl.GET_EN1_MATCHES:
                return StringUtil.stringToStringArray(pf.get(ENEMY1_MATCHES, NO_VALUE), getMatchesNumber(branch));
            case MakeUrl.GET_EN2_MATCHES:
                return StringUtil.stringToStringArray(pf.get(ENEMY2_MATCHES, NO_VALUE), getMatchesNumber(branch));
            case MakeUrl.GET_EN3_MATCHES:
                return StringUtil.stringToStringArray(pf.get(ENEMY3_MATCHES, NO_VALUE), getMatchesNumber(branch));
            case MakeUrl.GET_EN4_MATCHES:
                return StringUtil.stringToStringArray(pf.get(ENEMY4_MATCHES, NO_VALUE), getMatchesNumber(branch));
            case MakeUrl.GET_EN5_MATCHES:
                return StringUtil.stringToStringArray(pf.get(ENEMY5_MATCHES, NO_VALUE), getMatchesNumber(branch));
        }
        return NO_VALUE_STRING_ARRAY;
    }

    public static void setEnemy1Name(String name) { pf.put(ENEMY1_NAME, name);}
    public static void setEnemy2Name(String name) { pf.put(ENEMY2_NAME, name);}
    public static void setEnemy3Name(String name) { pf.put(ENEMY3_NAME, name);}
    public static void setEnemy4Name(String name) { pf.put(ENEMY4_NAME, name);}
    public static void setEnemy5Name(String name) { pf.put(ENEMY5_NAME, name);}

    public static String getEnemy1Name() { return pf.get(ENEMY1_NAME, NO_VALUE);}
    public static String getEnemy2Name() { return pf.get(ENEMY2_NAME, NO_VALUE);}
    public static String getEnemy3Name() { return pf.get(ENEMY3_NAME, NO_VALUE);}
    public static String getEnemy4Name() { return pf.get(ENEMY4_NAME, NO_VALUE);}
    public static String getEnemy5Name() { return pf.get(ENEMY5_NAME, NO_VALUE);}

    public static void setEnemy1Id() { pf.put(ENEMY1_ID, NO_VALUE);}
    public static void setEnemy2Id() { pf.put(ENEMY2_ID, NO_VALUE);}
    public static void setEnemy3Id() { pf.put(ENEMY3_ID, NO_VALUE);}
    public static void setEnemy4Id() { pf.put(ENEMY4_ID, NO_VALUE);}
    public static void setEnemy5Id() { pf.put(ENEMY5_ID, NO_VALUE);}

    public static String getEnemy1Id() { return pf.get(ENEMY1_ID, NO_VALUE);}
    public static String getEnemy2Id() { return pf.get(ENEMY2_ID, NO_VALUE);}
    public static String getEnemy3Id() { return pf.get(ENEMY3_ID, NO_VALUE);}
    public static String getEnemy4Id() { return pf.get(ENEMY4_ID, NO_VALUE);}
    public static String getEnemy5Id() { return pf.get(ENEMY5_ID, NO_VALUE);}

    public static void setEnemyMatchId(String matchId, int branch) {
        switch (branch) {
            case MakeUrl.EN1_MATCH_DETAIL:
                pf.put(ENEMY1_MATCH_ID, matchId);
                break;
            case MakeUrl.EN2_MATCH_DETAIL:
                pf.put(ENEMY2_MATCH_ID, matchId);
                break;
            case MakeUrl.EN3_MATCH_DETAIL:
                pf.put(ENEMY3_MATCH_ID, matchId);
                break;
            case MakeUrl.EN4_MATCH_DETAIL:
                pf.put(ENEMY4_MATCH_ID, matchId);
                break;
            case MakeUrl.EN5_MATCH_DETAIL:
                pf.put(ENEMY5_MATCH_ID, matchId);
                break;
        }
    }
    public static String getEnemyMatchId(int branch) {
        switch (branch) {
            case MakeUrl.EN1_MATCH_DETAIL:
                return pf.get(ENEMY1_MATCH_ID, NO_VALUE);
            case MakeUrl.EN2_MATCH_DETAIL:
                return pf.get(ENEMY2_MATCH_ID, NO_VALUE);
            case MakeUrl.EN3_MATCH_DETAIL:
                return pf.get(ENEMY3_MATCH_ID, NO_VALUE);
            case MakeUrl.EN4_MATCH_DETAIL:
                return pf.get(ENEMY4_MATCH_ID, NO_VALUE);
            case MakeUrl.EN5_MATCH_DETAIL:
                return pf.get(ENEMY5_MATCH_ID, NO_VALUE);
        }
        return NO_VALUE;
    }

    public static void setEnemy1LeagueEntry(LeagueEntry enemyLeagueEntry) {
        pf.putByteArray(ENEMY1_LEAGUE_ENTRY, Seriallization.serializeData(enemyLeagueEntry));
    }
    public static void setEnemy2LeagueEntry(LeagueEntry enemyLeagueEntry) {
        pf.putByteArray(ENEMY2_LEAGUE_ENTRY, Seriallization.serializeData(enemyLeagueEntry));
    }
    public static void setEnemy3LeagueEntry(LeagueEntry enemyLeagueEntry) {
        pf.putByteArray(ENEMY3_LEAGUE_ENTRY, Seriallization.serializeData(enemyLeagueEntry));
    }
    public static void setEnemy4LeagueEntry(LeagueEntry enemyLeagueEntry) {
        pf.putByteArray(ENEMY4_LEAGUE_ENTRY, Seriallization.serializeData(enemyLeagueEntry));
    }
    public static void setEnemy5LeagueEntry(LeagueEntry enemyLeagueEntry) {
        pf.putByteArray(ENEMY5_LEAGUE_ENTRY, Seriallization.serializeData(enemyLeagueEntry));
    }

    public static LeagueEntry getEnemy1LeagueEntry() {
        return (LeagueEntry)Seriallization
                .deserializeData(pf.getByteArray(ENEMY1_LEAGUE_ENTRY, NO_VALUE_BYTE));
    }
    public static LeagueEntry getEnemy2LeagueEntry() {
        return (LeagueEntry)Seriallization
                .deserializeData(pf.getByteArray(ENEMY2_LEAGUE_ENTRY, NO_VALUE_BYTE));
    }
    public static LeagueEntry getEnemy3LeagueEntry() {
        return (LeagueEntry)Seriallization
                .deserializeData(pf.getByteArray(ENEMY3_LEAGUE_ENTRY, NO_VALUE_BYTE));
    }
    public static LeagueEntry getEnemy4LeagueEntry() {
        return (LeagueEntry)Seriallization
                .deserializeData(pf.getByteArray(ENEMY4_LEAGUE_ENTRY, NO_VALUE_BYTE));
    }
    public static LeagueEntry getEnemy5LeagueEntry() {
        return (LeagueEntry)Seriallization
                .deserializeData(pf.getByteArray(ENEMY5_LEAGUE_ENTRY, NO_VALUE_BYTE));
    }


}
