package com.amazon.music.logger;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.impl.StaticLoggerBinder;

/* JADX INFO: loaded from: classes4.dex */
public class AmazonMusicMarketLogger {
    private static int sLogLevel = 5;
    private static Map<String, Integer> sLogLevelTagMap = null;
    private static boolean sNonCriticalLoggingEnabled = false;

    public static void initialize(AmazonMusicMarketLoggerConfig amazonMusicMarketLoggerConfig, boolean z) {
        PersistentLogger.initialize(amazonMusicMarketLoggerConfig);
        setNonCriticalLoggingEnabled(z);
    }

    public static synchronized void setNonCriticalLoggingEnabled(boolean z) {
        if (sNonCriticalLoggingEnabled != z) {
            sNonCriticalLoggingEnabled = z;
            StaticLoggerBinder.getSingleton().onLogLevelUpdated();
        }
    }

    public static boolean getNonCriticalLoggingEnabled() {
        return sNonCriticalLoggingEnabled;
    }

    public static synchronized void setSystemLogLevel(String str) {
        int logLevel = LogLevel.getLogLevel(str);
        if (sLogLevel != logLevel) {
            sLogLevel = logLevel;
            StaticLoggerBinder.getSingleton().onLogLevelUpdated();
        }
    }

    public static int getSystemLogLevel() {
        if (sNonCriticalLoggingEnabled) {
            return LogLevel.getNonCriticalLoggingEnabledLevel();
        }
        return sLogLevel;
    }

    public static void setLogLevelTagMap(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            if (sLogLevelTagMap != null) {
                sLogLevelTagMap = null;
                StaticLoggerBinder.getSingleton().onLogLevelUpdated();
                return;
            }
            return;
        }
        boolean z = sLogLevelTagMap == null;
        HashMap map2 = new HashMap(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            int logLevel = LogLevel.getLogLevel(entry.getValue());
            if (!z && !matchesLogLevelInMap(key, logLevel)) {
                z = true;
            }
            map2.put(entry.getKey(), Integer.valueOf(logLevel));
        }
        if (z) {
            sLogLevelTagMap = map2;
            StaticLoggerBinder.getSingleton().onLogLevelUpdated();
        }
    }

    static boolean matchesLogLevelInMap(String str, int i) {
        return sLogLevelTagMap.get(str) != null && sLogLevelTagMap.get(str).intValue() == i;
    }

    public static int getLogLevel(String str) {
        Map<String, Integer> map;
        Integer num;
        int systemLogLevel = getSystemLogLevel();
        return (str == null || (map = sLogLevelTagMap) == null || (num = map.get(str)) == null || num.intValue() > systemLogLevel) ? systemLogLevel : num.intValue();
    }
}
