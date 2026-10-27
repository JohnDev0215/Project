package com.neusoft.slf4j.dlt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/* JADX INFO: loaded from: classes.dex */
public class LoggerFactory implements ILoggerFactory {
    private final ConcurrentMap<String, Logger> loggerMap = new ConcurrentHashMap();

    public Logger getLogger(Class<?> cls) {
        return getLogger(cls.getName());
    }

    @Override // org.slf4j.ILoggerFactory
    public Logger getLogger(String str) {
        Logger logger = this.loggerMap.get(str);
        if (logger != null) {
            return logger;
        }
        LogAdapter logAdapter = new LogAdapter(str);
        Logger loggerPutIfAbsent = this.loggerMap.putIfAbsent(str, logAdapter);
        return loggerPutIfAbsent == null ? logAdapter : loggerPutIfAbsent;
    }
}
