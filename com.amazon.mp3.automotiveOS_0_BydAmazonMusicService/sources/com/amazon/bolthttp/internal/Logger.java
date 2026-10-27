package com.amazon.bolthttp.internal;

import com.amazon.bolthttp.BoltConfig;
import com.amazon.bolthttp.LogEmitter;
import com.google.android.gms.cast.MediaError;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class Logger {
    private final int mLevel;
    private final LogEmitter mLogEmitter;
    private final String mTag;

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'ERROR' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class Type {
        private static final /* synthetic */ Type[] $VALUES;
        public static final Type DEBUG;
        public static final Type ERROR;
        public static final Type INFO;
        public static final Type VERBOSE;
        public static final Type WARN;
        private final int androidPriority;
        private final int level;

        public static Type valueOf(String str) {
            return (Type) Enum.valueOf(Type.class, str);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }

        static {
            BoltConfig.LoggingLevel loggingLevel = BoltConfig.LoggingLevel.MINIMAL;
            Type type = new Type(MediaError.ERROR_TYPE_ERROR, 0, loggingLevel.level, 6);
            ERROR = type;
            Type type2 = new Type("WARN", 1, loggingLevel.level, 5);
            WARN = type2;
            BoltConfig.LoggingLevel loggingLevel2 = BoltConfig.LoggingLevel.STANDARD;
            Type type3 = new Type("INFO", 2, loggingLevel2.level, 4);
            INFO = type3;
            Type type4 = new Type("DEBUG", 3, loggingLevel2.level, 3);
            DEBUG = type4;
            Type type5 = new Type("VERBOSE", 4, BoltConfig.LoggingLevel.VERBOSE.level, 2);
            VERBOSE = type5;
            $VALUES = new Type[]{type, type2, type3, type4, type5};
        }

        private Type(String str, int i, int i2, int i3) {
            this.level = i2;
            this.androidPriority = i3;
        }

        public int getAndroidPriority() {
            return this.androidPriority;
        }
    }

    public Logger(@Nonnull BoltConfig boltConfig) {
        Preconditions.checkNotNull(boltConfig, "config");
        this.mLevel = boltConfig.getLoggingLevel().level;
        this.mTag = boltConfig.getLogTag();
        this.mLogEmitter = boltConfig.getLogEmitter();
    }

    public boolean isLoggable(@Nonnull Type type) {
        return type.level <= this.mLevel;
    }

    public void log(@Nonnull Type type, @Nonnull String str) {
        if (isLoggable(type)) {
            this.mLogEmitter.log(type, this.mTag, str);
        }
    }

    public void log(@Nonnull Type type, @Nonnull String str, Object obj) {
        if (isLoggable(type)) {
            this.mLogEmitter.log(type, this.mTag, String.format(str, obj));
        }
    }

    public void log(@Nonnull Type type, @Nonnull String str, Object obj, Object obj2) {
        if (isLoggable(type)) {
            this.mLogEmitter.log(type, this.mTag, String.format(str, obj, obj2));
        }
    }

    public void log(@Nonnull Type type, @Nonnull String str, Object obj, Object obj2, Object obj3) {
        if (isLoggable(type)) {
            this.mLogEmitter.log(type, this.mTag, String.format(str, obj, obj2, obj3));
        }
    }

    public void log(@Nonnull Type type, @Nonnull String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (isLoggable(type)) {
            this.mLogEmitter.log(type, this.mTag, String.format(str, obj, obj2, obj3, obj4));
        }
    }

    public String getStackTraceString(Throwable th) {
        return this.mLogEmitter.getStackTraceString(th);
    }
}
