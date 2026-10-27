package com.amazon.mp3.util;

import com.amazon.music.logger.AmazonMusicMarketLogger;
import java.util.Locale;
import org.slf4j.ILoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

/* JADX INFO: loaded from: classes4.dex */
public final class Log {
    public static final ILoggerFactory LOGGER_FACTORY = StaticLoggerBinder.getSingleton().getLoggerFactory();

    public static void debugBuildOnly(String tag, String format, Object... args) {
    }

    public static void setNonCriticalLoggingEnabled(final boolean enableLogging) {
        AmazonMusicMarketLogger.setNonCriticalLoggingEnabled(enableLogging);
    }

    public static boolean getNonCriticalLoggingEnabled() {
        return AmazonMusicMarketLogger.getNonCriticalLoggingEnabled();
    }

    public static void error(String tag, String msg) {
        LOGGER_FACTORY.getLogger(tag).error(msg);
    }

    public static void error(String tag, String msg, Throwable tr) {
        LOGGER_FACTORY.getLogger(tag).error(msg, tr);
    }

    public static void error(String tag, String format, Object... args) {
        LOGGER_FACTORY.getLogger(tag).error(String.format(Locale.US, format, args));
    }

    public static void warning(String tag, String msg) {
        LOGGER_FACTORY.getLogger(tag).warn(msg);
    }

    public static void warning(String tag, String msg, Throwable tr) {
        LOGGER_FACTORY.getLogger(tag).warn(msg, tr);
    }

    public static void warning(String tag, String format, Object... args) {
        LOGGER_FACTORY.getLogger(tag).warn(String.format(Locale.US, format, args));
    }

    public static void info(String tag, String msg) {
        LOGGER_FACTORY.getLogger(tag).info(msg);
    }

    public static void info(String tag, String msg, Throwable tr) {
        LOGGER_FACTORY.getLogger(tag).info(msg, tr);
    }

    public static void info(String tag, String format, Object... args) {
        LOGGER_FACTORY.getLogger(tag).info(String.format(Locale.US, format, args));
    }

    public static void debug(String tag, String msg) {
        if (AmazonMusicMarketLogger.getNonCriticalLoggingEnabled()) {
            LOGGER_FACTORY.getLogger(tag).debug(msg);
        }
    }

    public static void debug(String tag, String msg, Throwable tr) {
        if (AmazonMusicMarketLogger.getNonCriticalLoggingEnabled()) {
            LOGGER_FACTORY.getLogger(tag).debug(msg, tr);
        }
    }

    public static void debug(String tag, String format, Object... args) {
        if (AmazonMusicMarketLogger.getNonCriticalLoggingEnabled()) {
            LOGGER_FACTORY.getLogger(tag).debug(String.format(Locale.US, format, args));
        }
    }

    public static void verbose(String tag, String msg) {
        if (AmazonMusicMarketLogger.getNonCriticalLoggingEnabled()) {
            LOGGER_FACTORY.getLogger(tag).trace(msg);
        }
    }

    public static void verbose(String tag, String format, Object... args) {
        if (AmazonMusicMarketLogger.getNonCriticalLoggingEnabled()) {
            LOGGER_FACTORY.getLogger(tag).trace(String.format(Locale.US, format, args));
        }
    }
}
