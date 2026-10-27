package io.reactivex.rxjava3.internal.util;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class ErrorMode {
    public static final ErrorMode c;
    public static final ErrorMode e;
    public static final ErrorMode k;
    public static final /* synthetic */ ErrorMode[] l;

    static {
        ErrorMode errorMode = new ErrorMode("IMMEDIATE", 0);
        c = errorMode;
        ErrorMode errorMode2 = new ErrorMode("BOUNDARY", 1);
        e = errorMode2;
        ErrorMode errorMode3 = new ErrorMode("END", 2);
        k = errorMode3;
        l = new ErrorMode[]{errorMode, errorMode2, errorMode3};
    }

    public static ErrorMode valueOf(String str) {
        return (ErrorMode) Enum.valueOf(ErrorMode.class, str);
    }

    public static ErrorMode[] values() {
        return (ErrorMode[]) l.clone();
    }
}
