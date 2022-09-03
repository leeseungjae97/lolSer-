package lolSer.util;

import com.google.gson.Gson;
import lolSer.JsonObject.*;
import org.json.JSONArray;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class Http {
    public URLConnection con;
    public HttpURLConnection http;
    public Gson gson;

    public Http() {
        this.gson = new Gson();
    }
    public Response GET(URL url, int branch) {
        if(branch == MakeUrl.CURRENT_GAME_IN_LOCAL) {
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(X509Certificate[] certs,
                                                       String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs,
                                                       String authType) {
                        }
                    } };

            SSLContext sc = null;
            try {
                sc = SSLContext.getInstance("SSL");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            try {
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
        }

        try {
            con = url.openConnection();
            http = (HttpURLConnection) con;
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type", "application/json;utf-8");
            http.setRequestProperty("Accept", "application/json");
            http.setDoOutput(true);

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            StringBuffer bf = new StringBuffer();

            while ((line = br.readLine()) != null) {
                bf.append(line);
            }
            br.close();
            switch (branch) {
//                case MakeUrl.PUUID:
//                    return gson.fromJson(bf.toString(), Account.class);
                case MakeUrl.ACCOUNT_ID:
                    return gson.fromJson(bf.toString(), Summoner.class);
                case MakeUrl.GET_EN1_MATCHES:
                case MakeUrl.GET_EN2_MATCHES:
                case MakeUrl.GET_EN3_MATCHES:
                case MakeUrl.GET_EN4_MATCHES:
                case MakeUrl.GET_EN5_MATCHES:
                    return new Matches(JsonHelper.jsonArrayToStringArray(new JSONArray(bf.toString()), branch));
                case MakeUrl.EN1_MATCH_LIST:
                case MakeUrl.EN2_MATCH_LIST:
                case MakeUrl.EN3_MATCH_LIST:
                case MakeUrl.EN4_MATCH_LIST:
                case MakeUrl.EN5_MATCH_LIST:
                    return gson.fromJson(bf.toString(), MatchList.class);
                case MakeUrl.EN1_MATCH_TIME_LINE:
                case MakeUrl.EN2_MATCH_TIME_LINE:
                case MakeUrl.EN3_MATCH_TIME_LINE:
                case MakeUrl.EN4_MATCH_TIME_LINE:
                case MakeUrl.EN5_MATCH_TIME_LINE:
                    return gson.fromJson(bf.toString(), MatchTimeLine.class);
                case MakeUrl.CURRENT_GAME:
                    return gson.fromJson(bf.toString(), CurrentGameInfo.class);
                case MakeUrl.RANK_INFO:
                    JSONArray jsonArrayLeagueEntry = new JSONArray(bf.toString());
                    if (jsonArrayLeagueEntry.length() == 0) return new Response("솔로랭크 전적이 없습니다.");
                    return new LeagueEntries(JsonHelper.jsonArrayToLeagueEntry(new JSONArray(bf.toString())));
                case MakeUrl.MATCH_DETAIL:
                    return gson.fromJson(bf.toString(), Match.class);
                case MakeUrl.CURRENT_GAME_IN_LOCAL:
                    return new LocalCurrentGameInfos(JsonHelper.jsonArrayToLocalCurrentGameInfo(new JSONArray(bf.toString())));
//                case MakeUrl.CHECK_LOCAL_GAME_START:
//                    return gson.fromJson(bf.toString(), ClientStart.class);
            }
        } catch (FileNotFoundException e) {
            if(http.getErrorStream() != null) {
                BufferedReader be = new BufferedReader(new InputStreamReader(http.getErrorStream()));
                String errorLine;
                StringBuffer bfe = new StringBuffer();
                try {
                    while ((errorLine = be.readLine()) != null) {
                        bfe.append(errorLine);
                    }
                    be.close();
                    System.out.println("ERROR : " + bfe.toString());
                }catch (IOException el) {
                    el.printStackTrace();
                }
                return gson.fromJson(bfe.toString(), ErrorStatus.class);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new Response("");
    }
}
