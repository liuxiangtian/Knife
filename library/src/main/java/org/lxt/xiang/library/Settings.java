package org.lxt.xiang.library;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;


public class Settings {

    private static String PREFS_FILE_NAME = "SHARE_PREFS";

    private static final String GET = "get";
    private static final String LOAD = "load";
    private static final String PUT = "put";
    private static final String SAVE = "save";
    private static final String SET = "set";

    private static List<String> PREFIX_GET = new ArrayList<>();
    private static List<String> PREFIX_PUT = new ArrayList<>();

    static {
        PREFIX_GET.add(GET);
        PREFIX_GET.add(LOAD);
        PREFIX_PUT.add(PUT);
        PREFIX_PUT.add(SAVE);
        PREFIX_PUT.add(SET);
    }

    private static Settings instance = new Settings();

    public static Settings getInstance() {
        return instance;
    }

    public static final void addGetPrefix(final String prefix){
        if(TextUtils.isEmpty(prefix)) return;
        PREFIX_GET.add(prefix);
    }

    public static final void addPutPrefix(final String prefix){
        if(TextUtils.isEmpty(prefix)) return;
        PREFIX_PUT.add(prefix);
    }

    public static String getPutKey(String rawKey) {
        if(rawKey==null || rawKey.trim().equals("")) return null;
            for (String prefix : PREFIX_PUT) {
                if(rawKey.startsWith(prefix)){
                    return rawKey.replaceFirst(prefix,"").toUpperCase();
                }
            }
        return rawKey;
    }

    public static String getGetKey(String rawKey) {
        if(rawKey==null || rawKey.trim().equals("")) return null;
        for (String prefix : PREFIX_GET) {
            if(rawKey.startsWith(prefix)){
                return rawKey.replaceFirst(prefix,"").toUpperCase();
            }
        }
        return rawKey;
    }


    public String getFileName() {
        return PREFS_FILE_NAME;
    }
}
