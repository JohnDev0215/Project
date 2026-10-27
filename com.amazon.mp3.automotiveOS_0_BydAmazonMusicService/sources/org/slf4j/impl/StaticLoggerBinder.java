package org.slf4j.impl;

import org.slf4j.ILoggerFactory;

/* JADX INFO: loaded from: classes6.dex */
public class StaticLoggerBinder {
    private final AmazonMusicMarketLoggerFactory loggerFactory = new AmazonMusicMarketLoggerFactory();
    private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();
    public static String REQUESTED_API_VERSION = "1.7.99";
    private static final String loggerFactoryClassStr = AmazonMusicMarketLoggerFactory.class.getName();

    public static StaticLoggerBinder getSingleton() {
        return SINGLETON;
    }

    private StaticLoggerBinder() {
    }

    public ILoggerFactory getLoggerFactory() {
        return this.loggerFactory;
    }

    public String getLoggerFactoryClassStr() {
        return loggerFactoryClassStr;
    }

    public void onLogLevelUpdated() {
        this.loggerFactory.onLogLevelUpdated();
    }
}
