package org.slf4j.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/* JADX INFO: loaded from: classes6.dex */
public class SubstituteLoggerFactory implements ILoggerFactory {
    final ConcurrentMap<String, SubstituteLogger> loggers = new ConcurrentHashMap();

    @Override // org.slf4j.ILoggerFactory
    public Logger getLogger(String str) {
        SubstituteLogger substituteLogger = this.loggers.get(str);
        if (substituteLogger != null) {
            return substituteLogger;
        }
        SubstituteLogger substituteLogger2 = new SubstituteLogger(str);
        SubstituteLogger substituteLoggerPutIfAbsent = this.loggers.putIfAbsent(str, substituteLogger2);
        return substituteLoggerPutIfAbsent != null ? substituteLoggerPutIfAbsent : substituteLogger2;
    }

    public List<SubstituteLogger> getLoggers() {
        return new ArrayList(this.loggers.values());
    }

    public void clear() {
        this.loggers.clear();
    }
}
