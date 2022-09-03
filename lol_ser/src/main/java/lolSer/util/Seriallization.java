package lolSer.util;

import lolSer.JsonObject.CurrentGameInfo;
import lolSer.JsonObject.LeagueEntries;
import lolSer.JsonObject.Response;

import java.io.*;

public class Seriallization {
    public static byte[] serializeData(Object object) {
        byte[] serializedCurrentGameInfo = {};
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(object);
                serializedCurrentGameInfo = baos.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serializedCurrentGameInfo;
    }
    public static Response deserializeData(byte[] serialData){
        try (ByteArrayInputStream bais = new ByteArrayInputStream(serialData)) {
            try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                Object objectMember = ois.readObject();
                return objectToDTO(objectMember.getClass().getName(), objectMember);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("정보가 없습니다.");
        }

        return null;
    }

    public static Response objectToDTO(String className, Object ob) {
        String name = StringUtil.classNameWithOutPackage(className);
        if(name.equals("CurrentGameInfo")) {
            return (CurrentGameInfo)ob;
        }
        if(name.equals("LeagueEntries")) {
            return (LeagueEntries)ob;
        }
        return (Response) ob;
    }
}
