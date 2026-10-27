package org.slf4j.impl;

import androidx.annotation.VisibleForTesting;
import com.amazon.music.logger.AmazonMusicMarketLogger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/* JADX INFO: loaded from: classes6.dex */
class AmazonMusicMarketLoggerFactory implements ILoggerFactory {

    @VisibleForTesting
    final ConcurrentMap<String, AmazonMusicMarketLoggerAdapter> loggerMap = new ConcurrentHashMap();

    AmazonMusicMarketLoggerFactory() {
    }

    @Override // org.slf4j.ILoggerFactory
    public Logger getLogger(String str) {
        AmazonMusicMarketLoggerAdapter amazonMusicMarketLoggerAdapter = this.loggerMap.get(str);
        if (amazonMusicMarketLoggerAdapter != null) {
            return amazonMusicMarketLoggerAdapter;
        }
        AmazonMusicMarketLoggerAdapter amazonMusicMarketLoggerAdapter2 = new AmazonMusicMarketLoggerAdapter(str, AmazonMusicMarketLogger.getLogLevel(str));
        this.loggerMap.put(str, amazonMusicMarketLoggerAdapter2);
        return amazonMusicMarketLoggerAdapter2;
    }

    void onLogLevelUpdated() {
        for (Map.Entry<String, AmazonMusicMarketLoggerAdapter> entry : this.loggerMap.entrySet()) {
            entry.getValue().updateLogLevel(AmazonMusicMarketLogger.getLogLevel(entry.getKey()));
        }
    }
}
