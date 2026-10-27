package org.slf4j.impl;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.music.logger.PersistentLogger;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

/* JADX INFO: loaded from: classes6.dex */
class AmazonMusicMarketLoggerAdapter extends MarkerIgnoringBase {
    private int currentLogLevel;
    private String mTag;

    AmazonMusicMarketLoggerAdapter(String str, int i) {
        this.name = str;
        this.mTag = "AmznMusic_" + str;
        this.currentLogLevel = i;
    }

    void updateLogLevel(int i) {
        this.currentLogLevel = i;
    }

    @VisibleForTesting
    int getLogLevel() {
        return this.currentLogLevel;
    }

    boolean isLoggable(int i) {
        return i >= this.currentLogLevel;
    }

    public boolean isTraceEnabled() {
        return isLoggable(2);
    }

    @Override // org.slf4j.Logger
    public void trace(String str) {
        trace(str, (Throwable) null);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj) {
        if (isTraceEnabled()) {
            trace(format(str, obj));
        }
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Throwable th) {
        if (isTraceEnabled()) {
            if (th == null) {
                Log.v(this.mTag, str);
                PersistentLogger.log('V', this.mTag, str);
            } else {
                Log.v(this.mTag, str, th);
                PersistentLogger.log('V', this.mTag, str, th);
            }
        }
    }

    public boolean isDebugEnabled() {
        return isLoggable(3);
    }

    @Override // org.slf4j.Logger
    public void debug(String str) {
        debug(str, (Throwable) null);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj) {
        if (isDebugEnabled()) {
            debug(format(str, obj));
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj, Object obj2) {
        if (isDebugEnabled()) {
            debug(format(str, obj, obj2));
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object... objArr) {
        if (isDebugEnabled()) {
            debug(format(str, objArr));
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Throwable th) {
        if (isDebugEnabled()) {
            if (th == null) {
                Log.d(this.mTag, str);
                PersistentLogger.log('D', this.mTag, str);
            } else {
                Log.d(this.mTag, str, th);
                PersistentLogger.log('D', this.mTag, str, th);
            }
        }
    }

    public boolean isInfoEnabled() {
        return isLoggable(4);
    }

    @Override // org.slf4j.Logger
    public void info(String str) {
        info(str, (Throwable) null);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj) {
        if (isInfoEnabled()) {
            info(format(str, obj));
        }
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj, Object obj2) {
        if (isInfoEnabled()) {
            info(format(str, obj, obj2));
        }
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object... objArr) {
        if (isInfoEnabled()) {
            info(format(str, objArr));
        }
    }

    @Override // org.slf4j.Logger
    public void info(String str, Throwable th) {
        if (isInfoEnabled()) {
            if (th == null) {
                Log.i(this.mTag, str);
                PersistentLogger.log('I', this.mTag, str);
            } else {
                Log.i(this.mTag, str, th);
                PersistentLogger.log('I', this.mTag, str, th);
            }
        }
    }

    @Override // org.slf4j.Logger
    public void warn(String str) {
        warn(str, (Throwable) null);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj) {
        warn(format(str, obj));
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj, Object obj2) {
        warn(format(str, obj, obj2));
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Throwable th) {
        if (th == null) {
            Log.w(this.mTag, str);
            PersistentLogger.log('W', this.mTag, str);
        } else {
            Log.w(this.mTag, str, th);
            PersistentLogger.log('W', this.mTag, str, th);
        }
    }

    @Override // org.slf4j.Logger
    public void error(String str) {
        error(str, (Throwable) null);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj) {
        error(format(str, obj));
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj, Object obj2) {
        error(format(str, obj, obj2));
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object... objArr) {
        error(format(str, objArr));
    }

    @Override // org.slf4j.Logger
    public void error(String str, Throwable th) {
        if (th == null) {
            Log.e(this.mTag, str);
            PersistentLogger.log('E', this.mTag, str);
        } else {
            Log.e(this.mTag, str, th);
            PersistentLogger.log('E', this.mTag, str, th);
        }
    }

    private static String format(String str, Object... objArr) {
        return MessageFormatter.arrayFormat(str, objArr).getMessage();
    }
}
