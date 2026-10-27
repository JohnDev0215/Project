package com.stingray.kca.model;

import E5.o;
import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000b\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\r"}, d2 = {"Lcom/stingray/kca/model/Resolution;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "_1920X1080", "_1280X720", "UnknownDefaultOpenApi", "toString", "Companion", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class Resolution {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ Resolution[] $VALUES;
    private final String value;

    @o(name = "_1920X1080")
    public static final Resolution _1920X1080 = new Resolution("_1920X1080", 0, "_1920X1080");

    @o(name = "_1280X720")
    public static final Resolution _1280X720 = new Resolution("_1280X720", 1, "_1280X720");

    @o(name = "unknown_default_open_api")
    public static final Resolution UnknownDefaultOpenApi = new Resolution("UnknownDefaultOpenApi", 2, "unknown_default_open_api");

    private static final /* synthetic */ Resolution[] $values() {
        return new Resolution[]{_1920X1080, _1280X720, UnknownDefaultOpenApi};
    }

    static {
        Resolution[] resolutionArr$values = $values();
        $VALUES = resolutionArr$values;
        $ENTRIES = r.e(resolutionArr$values);
        INSTANCE = new Companion(0);
    }

    private Resolution(String str, int i7, String str2) {
        this.value = str2;
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static Resolution valueOf(String str) {
        return (Resolution) Enum.valueOf(Resolution.class, str);
    }

    public static Resolution[] values() {
        return (Resolution[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
