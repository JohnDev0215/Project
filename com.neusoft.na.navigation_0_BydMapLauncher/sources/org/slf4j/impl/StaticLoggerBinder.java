package org.slf4j.impl;

import com.neusoft.slf4j.dlt.LoggerFactory;
import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

/* JADX INFO: loaded from: classes4.dex */
public class StaticLoggerBinder implements LoggerFactoryBinder {
    private final ILoggerFactory loggerFactory = new LoggerFactory();
    private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();
    public static final String REQUESTED_API_VERSION = "1.6.99".intern();
    private static final String loggerFactoryClassStr = LoggerFactory.class.getName();

    public static StaticLoggerBinder getSingleton() {
        return SINGLETON;
    }

    private StaticLoggerBinder() {
    }

    @Override // org.slf4j.spi.LoggerFactoryBinder
    public ILoggerFactory getLoggerFactory() {
        return this.loggerFactory;
    }

    @Override // org.slf4j.spi.LoggerFactoryBinder
    public String getLoggerFactoryClassStr() {
        return loggerFactoryClassStr;
    }
}
