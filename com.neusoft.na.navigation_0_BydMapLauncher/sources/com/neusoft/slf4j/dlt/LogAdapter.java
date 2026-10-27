package com.neusoft.slf4j.dlt;

import android.util.Log;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes.dex */
final class LogAdapter implements Logger {
    private static final int MAX_LOGTAG_LENGTH = 23;
    private static final boolean MAX_LOG_LENGTH_RESTRICTED;
    private boolean DEBUG;
    private boolean ERROR;
    private boolean INFO;
    private boolean TRACE;
    private boolean WARN;
    private final PublishSubject<LogMessage> logSubject;
    private final String name;
    private final String tag;

    static {
        boolean z;
        try {
            Log.isLoggable("_123456789_123456789_123456789_123456789", 6);
            z = false;
        } catch (IllegalArgumentException unused) {
            z = true;
        }
        MAX_LOG_LENGTH_RESTRICTED = z;
    }

    LogAdapter(String str) {
        PublishSubject<LogMessage> publishSubjectCreate = PublishSubject.create();
        this.logSubject = publishSubjectCreate;
        if (str == null) {
            throw new IllegalArgumentException("Logger name must not be null");
        }
        this.name = str;
        int iLastIndexOf = str.lastIndexOf(46);
        String strReplace = iLastIndexOf > -1 ? str.substring(iLastIndexOf).replace(".", "") : str;
        if (MAX_LOG_LENGTH_RESTRICTED) {
            int length = strReplace.length() - 23;
            this.tag = length > 0 ? strReplace.substring(length) : strReplace;
        } else {
            this.tag = strReplace;
        }
        SlfLogLevel slfLogLevel = LogConfig.getInstance().map.get(str);
        slfLogLevel = slfLogLevel == null ? SlfLogLevel.DEBUG : slfLogLevel;
        this.TRACE = slfLogLevel.isAtLeast(SlfLogLevel.TRACE);
        this.DEBUG = slfLogLevel.isAtLeast(SlfLogLevel.DEBUG);
        this.INFO = slfLogLevel.isAtLeast(SlfLogLevel.INFO);
        this.WARN = slfLogLevel.isAtLeast(SlfLogLevel.WARN);
        this.ERROR = slfLogLevel.isAtLeast(SlfLogLevel.ERROR);
        publishSubjectCreate.onBackpressureBuffer().subscribe(new Action1<LogMessage>() { // from class: com.neusoft.slf4j.dlt.LogAdapter.1
            @Override // rx.functions.Action1
            public void call(LogMessage logMessage) {
                LogAdapter.this.writeLog(logMessage);
            }
        });
    }

    @Override // org.slf4j.Logger
    public final String getName() {
        return this.name;
    }

    private void __log(SlfLogLevel slfLogLevel, String str, Throwable th) {
        this.logSubject.onNext(new LogMessage(slfLogLevel, str, th));
    }

    private void __dlt(String str, DltLogLevel dltLogLevel) {
        DLTSocketController dLTSocketController = DLTSocketController.getInstance();
        if (dLTSocketController != null) {
            dLTSocketController.log(this.name, str, dltLogLevel);
        }
    }

    /* JADX INFO: renamed from: com.neusoft.slf4j.dlt.LogAdapter$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$neusoft$slf4j$dlt$SlfLogLevel;

        static {
            int[] iArr = new int[SlfLogLevel.values().length];
            $SwitchMap$com$neusoft$slf4j$dlt$SlfLogLevel = iArr;
            try {
                iArr[SlfLogLevel.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$neusoft$slf4j$dlt$SlfLogLevel[SlfLogLevel.WARN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$neusoft$slf4j$dlt$SlfLogLevel[SlfLogLevel.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$neusoft$slf4j$dlt$SlfLogLevel[SlfLogLevel.DEBUG.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$neusoft$slf4j$dlt$SlfLogLevel[SlfLogLevel.TRACE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeLog(LogMessage logMessage) {
        if (logMessage.throwable == null) {
            int i = AnonymousClass2.$SwitchMap$com$neusoft$slf4j$dlt$SlfLogLevel[logMessage.slfLogLevel.ordinal()];
            if (i == 1) {
                Log.e(this.tag, logMessage.message);
                __dlt(logMessage.message, DltLogLevel.DLT_LOG_ERROR);
                return;
            }
            if (i == 2) {
                Log.w(this.tag, logMessage.message);
                __dlt(logMessage.message, DltLogLevel.DLT_LOG_WARN);
                return;
            }
            if (i == 3) {
                Log.i(this.tag, logMessage.message);
                __dlt(logMessage.message, DltLogLevel.DLT_LOG_INFO);
                return;
            } else if (i == 4) {
                Log.d(this.tag, logMessage.message);
                __dlt(logMessage.message, DltLogLevel.DLT_LOG_DEBUG);
                return;
            } else {
                if (i != 5) {
                    return;
                }
                Log.v(this.tag, logMessage.message);
                __dlt(logMessage.message, DltLogLevel.DLT_LOG_VERBOSE);
                return;
            }
        }
        int i2 = AnonymousClass2.$SwitchMap$com$neusoft$slf4j$dlt$SlfLogLevel[logMessage.slfLogLevel.ordinal()];
        if (i2 == 1) {
            Log.e(this.tag, logMessage.message, logMessage.throwable);
            __dlt(logMessage.message, DltLogLevel.DLT_LOG_ERROR);
            return;
        }
        if (i2 == 2) {
            Log.w(this.tag, logMessage.message, logMessage.throwable);
            __dlt(logMessage.message, DltLogLevel.DLT_LOG_WARN);
            return;
        }
        if (i2 == 3) {
            Log.i(this.tag, logMessage.message, logMessage.throwable);
            __dlt(logMessage.message, DltLogLevel.DLT_LOG_INFO);
        } else if (i2 == 4) {
            Log.d(this.tag, logMessage.message, logMessage.throwable);
            __dlt(logMessage.message, DltLogLevel.DLT_LOG_DEBUG);
        } else {
            if (i2 != 5) {
                return;
            }
            Log.v(this.tag, logMessage.message, logMessage.throwable);
            __dlt(logMessage.message, DltLogLevel.DLT_LOG_VERBOSE);
        }
    }

    private void __logFormat(SlfLogLevel slfLogLevel, String str, Object... objArr) {
        FormattingTuple formattingTupleArrayFormat = MessageFormatter.arrayFormat(str, objArr);
        __log(slfLogLevel, formattingTupleArrayFormat.getMessage(), formattingTupleArrayFormat.getThrowable());
    }

    @Override // org.slf4j.Logger
    public final boolean isTraceEnabled() {
        return this.TRACE;
    }

    @Override // org.slf4j.Logger
    public final void trace(String str) {
        if (this.TRACE) {
            __log(SlfLogLevel.TRACE, str, null);
        }
    }

    @Override // org.slf4j.Logger
    public final void trace(String str, Object obj) {
        if (this.TRACE) {
            __logFormat(SlfLogLevel.TRACE, str, obj);
        }
    }

    @Override // org.slf4j.Logger
    public final void trace(String str, Object obj, Object obj2) {
        if (this.TRACE) {
            __logFormat(SlfLogLevel.TRACE, str, obj, obj2);
        }
    }

    @Override // org.slf4j.Logger
    public final void trace(String str, Object... objArr) {
        if (this.TRACE) {
            __logFormat(SlfLogLevel.TRACE, str, objArr);
        }
    }

    @Override // org.slf4j.Logger
    public final void trace(String str, Throwable th) {
        if (this.TRACE) {
            __log(SlfLogLevel.TRACE, str, th);
        }
    }

    @Override // org.slf4j.Logger
    public final boolean isTraceEnabled(Marker marker) {
        return this.TRACE;
    }

    @Override // org.slf4j.Logger
    public final void trace(Marker marker, String str) {
        if (this.TRACE) {
            __log(SlfLogLevel.TRACE, str, null);
        }
    }

    @Override // org.slf4j.Logger
    public final void trace(Marker marker, String str, Object obj) {
        if (this.TRACE) {
            __logFormat(SlfLogLevel.TRACE, str, obj);
        }
    }

    @Override // org.slf4j.Logger
    public final void trace(Marker marker, String str, Object obj, Object obj2) {
        if (this.TRACE) {
            __logFormat(SlfLogLevel.TRACE, str, obj, obj2);
        }
    }

    @Override // org.slf4j.Logger
    public final void trace(Marker marker, String str, Object... objArr) {
        if (this.TRACE) {
            __logFormat(SlfLogLevel.TRACE, str, objArr);
        }
    }

    @Override // org.slf4j.Logger
    public final void trace(Marker marker, String str, Throwable th) {
        if (this.TRACE) {
            __log(SlfLogLevel.TRACE, str, th);
        }
    }

    @Override // org.slf4j.Logger
    public final boolean isDebugEnabled() {
        return this.DEBUG;
    }

    @Override // org.slf4j.Logger
    public final void debug(String str) {
        if (this.DEBUG) {
            __log(SlfLogLevel.DEBUG, str, null);
        }
    }

    @Override // org.slf4j.Logger
    public final void debug(String str, Object obj) {
        if (this.DEBUG) {
            __logFormat(SlfLogLevel.DEBUG, str, obj);
        }
    }

    @Override // org.slf4j.Logger
    public final void debug(String str, Object obj, Object obj2) {
        if (this.DEBUG) {
            __logFormat(SlfLogLevel.DEBUG, str, obj, obj2);
        }
    }

    @Override // org.slf4j.Logger
    public final void debug(String str, Object... objArr) {
        if (this.DEBUG) {
            __logFormat(SlfLogLevel.DEBUG, str, objArr);
        }
    }

    @Override // org.slf4j.Logger
    public final void debug(String str, Throwable th) {
        if (this.DEBUG) {
            __log(SlfLogLevel.DEBUG, str, th);
        }
    }

    @Override // org.slf4j.Logger
    public final boolean isDebugEnabled(Marker marker) {
        return this.DEBUG;
    }

    @Override // org.slf4j.Logger
    public final void debug(Marker marker, String str) {
        if (this.DEBUG) {
            __log(SlfLogLevel.DEBUG, str, null);
        }
    }

    @Override // org.slf4j.Logger
    public final void debug(Marker marker, String str, Object obj) {
        if (this.DEBUG) {
            __logFormat(SlfLogLevel.DEBUG, str, obj);
        }
    }

    @Override // org.slf4j.Logger
    public final void debug(Marker marker, String str, Object obj, Object obj2) {
        if (this.DEBUG) {
            __logFormat(SlfLogLevel.DEBUG, str, obj, obj2);
        }
    }

    @Override // org.slf4j.Logger
    public final void debug(Marker marker, String str, Object... objArr) {
        if (this.DEBUG) {
            __logFormat(SlfLogLevel.DEBUG, str, objArr);
        }
    }

    @Override // org.slf4j.Logger
    public final void debug(Marker marker, String str, Throwable th) {
        if (this.DEBUG) {
            __log(SlfLogLevel.DEBUG, str, th);
        }
    }

    @Override // org.slf4j.Logger
    public final boolean isInfoEnabled() {
        return this.INFO;
    }

    @Override // org.slf4j.Logger
    public final void info(String str) {
        if (this.INFO) {
            __log(SlfLogLevel.INFO, str, null);
        }
    }

    @Override // org.slf4j.Logger
    public final void info(String str, Object obj) {
        if (this.INFO) {
            __logFormat(SlfLogLevel.INFO, str, obj);
        }
    }

    @Override // org.slf4j.Logger
    public final void info(String str, Object obj, Object obj2) {
        if (this.INFO) {
            __logFormat(SlfLogLevel.INFO, str, obj, obj2);
        }
    }

    @Override // org.slf4j.Logger
    public final void info(String str, Object... objArr) {
        if (this.INFO) {
            __logFormat(SlfLogLevel.INFO, str, objArr);
        }
    }

    @Override // org.slf4j.Logger
    public final void info(String str, Throwable th) {
        if (this.INFO) {
            __log(SlfLogLevel.INFO, str, th);
        }
    }

    @Override // org.slf4j.Logger
    public final boolean isInfoEnabled(Marker marker) {
        return this.INFO;
    }

    @Override // org.slf4j.Logger
    public final void info(Marker marker, String str) {
        if (this.INFO) {
            __log(SlfLogLevel.INFO, str, null);
        }
    }

    @Override // org.slf4j.Logger
    public final void info(Marker marker, String str, Object obj) {
        if (this.INFO) {
            __logFormat(SlfLogLevel.INFO, str, obj);
        }
    }

    @Override // org.slf4j.Logger
    public final void info(Marker marker, String str, Object obj, Object obj2) {
        if (this.INFO) {
            __logFormat(SlfLogLevel.INFO, str, obj, obj2);
        }
    }

    @Override // org.slf4j.Logger
    public final void info(Marker marker, String str, Object... objArr) {
        if (this.INFO) {
            __logFormat(SlfLogLevel.INFO, str, objArr);
        }
    }

    @Override // org.slf4j.Logger
    public final void info(Marker marker, String str, Throwable th) {
        if (this.INFO) {
            __log(SlfLogLevel.INFO, str, th);
        }
    }

    @Override // org.slf4j.Logger
    public final boolean isWarnEnabled() {
        return this.WARN;
    }

    @Override // org.slf4j.Logger
    public final void warn(String str) {
        if (this.WARN) {
            __log(SlfLogLevel.WARN, str, null);
        }
    }

    @Override // org.slf4j.Logger
    public final void warn(String str, Object obj) {
        if (this.WARN) {
            __logFormat(SlfLogLevel.WARN, str, obj);
        }
    }

    @Override // org.slf4j.Logger
    public final void warn(String str, Object obj, Object obj2) {
        if (this.WARN) {
            __logFormat(SlfLogLevel.WARN, str, obj, obj2);
        }
    }

    @Override // org.slf4j.Logger
    public final void warn(String str, Object... objArr) {
        if (this.WARN) {
            __logFormat(SlfLogLevel.WARN, str, objArr);
        }
    }

    @Override // org.slf4j.Logger
    public final void warn(String str, Throwable th) {
        if (this.WARN) {
            __log(SlfLogLevel.WARN, str, th);
        }
    }

    @Override // org.slf4j.Logger
    public final boolean isWarnEnabled(Marker marker) {
        return this.WARN;
    }

    @Override // org.slf4j.Logger
    public final void warn(Marker marker, String str) {
        if (this.WARN) {
            __log(SlfLogLevel.WARN, str, null);
        }
    }

    @Override // org.slf4j.Logger
    public final void warn(Marker marker, String str, Object obj) {
        if (this.WARN) {
            __logFormat(SlfLogLevel.WARN, str, obj);
        }
    }

    @Override // org.slf4j.Logger
    public final void warn(Marker marker, String str, Object obj, Object obj2) {
        if (this.WARN) {
            __logFormat(SlfLogLevel.WARN, str, obj, obj2);
        }
    }

    @Override // org.slf4j.Logger
    public final void warn(Marker marker, String str, Object... objArr) {
        if (this.WARN) {
            __logFormat(SlfLogLevel.WARN, str, objArr);
        }
    }

    @Override // org.slf4j.Logger
    public final void warn(Marker marker, String str, Throwable th) {
        if (this.WARN) {
            __log(SlfLogLevel.WARN, str, th);
        }
    }

    @Override // org.slf4j.Logger
    public final boolean isErrorEnabled() {
        return this.ERROR;
    }

    @Override // org.slf4j.Logger
    public final void error(String str) {
        if (this.ERROR) {
            __log(SlfLogLevel.ERROR, str, null);
        }
    }

    @Override // org.slf4j.Logger
    public final void error(String str, Object obj) {
        if (this.ERROR) {
            __logFormat(SlfLogLevel.ERROR, str, obj);
        }
    }

    @Override // org.slf4j.Logger
    public final void error(String str, Object obj, Object obj2) {
        if (this.ERROR) {
            __logFormat(SlfLogLevel.ERROR, str, obj, obj2);
        }
    }

    @Override // org.slf4j.Logger
    public final void error(String str, Object... objArr) {
        if (this.ERROR) {
            __logFormat(SlfLogLevel.ERROR, str, objArr);
        }
    }

    @Override // org.slf4j.Logger
    public final void error(String str, Throwable th) {
        if (this.ERROR) {
            __log(SlfLogLevel.ERROR, str, th);
        }
    }

    @Override // org.slf4j.Logger
    public final boolean isErrorEnabled(Marker marker) {
        return this.ERROR;
    }

    @Override // org.slf4j.Logger
    public final void error(Marker marker, String str) {
        if (this.ERROR) {
            __log(SlfLogLevel.ERROR, str, null);
        }
    }

    @Override // org.slf4j.Logger
    public final void error(Marker marker, String str, Object obj) {
        if (this.ERROR) {
            __logFormat(SlfLogLevel.ERROR, str, obj);
        }
    }

    @Override // org.slf4j.Logger
    public final void error(Marker marker, String str, Object obj, Object obj2) {
        if (this.ERROR) {
            __logFormat(SlfLogLevel.ERROR, str, obj, obj2);
        }
    }

    @Override // org.slf4j.Logger
    public final void error(Marker marker, String str, Object... objArr) {
        if (this.ERROR) {
            __logFormat(SlfLogLevel.ERROR, str, objArr);
        }
    }

    @Override // org.slf4j.Logger
    public final void error(Marker marker, String str, Throwable th) {
        if (this.ERROR) {
            __log(SlfLogLevel.ERROR, str, th);
        }
    }
}
