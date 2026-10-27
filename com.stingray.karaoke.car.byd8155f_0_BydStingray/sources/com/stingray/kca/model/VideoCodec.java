package com.stingray.kca.model;

import E5.o;
import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000b\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\r"}, d2 = {"Lcom/stingray/kca/model/VideoCodec;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "H264", "H265", "UnknownDefaultOpenApi", "toString", "Companion", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class VideoCodec {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ VideoCodec[] $VALUES;

    @o(name = "H264")
    public static final VideoCodec H264 = new VideoCodec("H264", 0, "H264");

    @o(name = "H265")
    public static final VideoCodec H265 = new VideoCodec("H265", 1, "H265");

    @o(name = "unknown_default_open_api")
    public static final VideoCodec UnknownDefaultOpenApi = new VideoCodec("UnknownDefaultOpenApi", 2, "unknown_default_open_api");
    private final String value;

    private static final /* synthetic */ VideoCodec[] $values() {
        return new VideoCodec[]{H264, H265, UnknownDefaultOpenApi};
    }

    static {
        VideoCodec[] videoCodecArr$values = $values();
        $VALUES = videoCodecArr$values;
        $ENTRIES = r.e(videoCodecArr$values);
        INSTANCE = new Companion(0);
    }

    private VideoCodec(String str, int i7, String str2) {
        this.value = str2;
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static VideoCodec valueOf(String str) {
        return (VideoCodec) Enum.valueOf(VideoCodec.class, str);
    }

    public static VideoCodec[] values() {
        return (VideoCodec[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
