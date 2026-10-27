package com.amazon.music.logger;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class LogLevel {
    private static final Map<String, Integer> logLevelMap;

    public static int getNonCriticalLoggingEnabledLevel() {
        return 2;
    }

    static {
        HashMap map = new HashMap();
        logLevelMap = map;
        map.put("DEFAULT", 5);
        map.put("INFO", 4);
        map.put("DEBUG", 3);
        map.put("VERBOSE", 2);
    }

    public static int getLogLevel(String str) {
        Integer num;
        if (str == null || (num = logLevelMap.get(str)) == null) {
            return 5;
        }
        return num.intValue();
    }
}
