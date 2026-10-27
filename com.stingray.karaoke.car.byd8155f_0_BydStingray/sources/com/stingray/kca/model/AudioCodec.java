package com.stingray.kca.model;

import E5.o;
import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\n\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\f"}, d2 = {"Lcom/stingray/kca/model/AudioCodec;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "AAC", "UnknownDefaultOpenApi", "toString", "Companion", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AudioCodec {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ AudioCodec[] $VALUES;
    private final String value;

    @o(name = "AAC")
    public static final AudioCodec AAC = new AudioCodec("AAC", 0, "AAC");

    @o(name = "unknown_default_open_api")
    public static final AudioCodec UnknownDefaultOpenApi = new AudioCodec("UnknownDefaultOpenApi", 1, "unknown_default_open_api");

    private static final /* synthetic */ AudioCodec[] $values() {
        return new AudioCodec[]{AAC, UnknownDefaultOpenApi};
    }

    static {
        AudioCodec[] audioCodecArr$values = $values();
        $VALUES = audioCodecArr$values;
        $ENTRIES = r.e(audioCodecArr$values);
        INSTANCE = new Companion(0);
    }

    private AudioCodec(String str, int i7, String str2) {
        this.value = str2;
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static AudioCodec valueOf(String str) {
        return (AudioCodec) Enum.valueOf(AudioCodec.class, str);
    }

    public static AudioCodec[] values() {
        return (AudioCodec[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
