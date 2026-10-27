package com.stingray.kca.model;

import E5.o;
import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000b\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\r"}, d2 = {"Lcom/stingray/kca/model/AssetTypeV2;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "SONG", "ARTIST", "UnknownDefaultOpenApi", "toString", "Companion", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AssetTypeV2 {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ AssetTypeV2[] $VALUES;
    private final String value;

    @o(name = "SONG")
    public static final AssetTypeV2 SONG = new AssetTypeV2("SONG", 0, "SONG");

    @o(name = "ARTIST")
    public static final AssetTypeV2 ARTIST = new AssetTypeV2("ARTIST", 1, "ARTIST");

    @o(name = "unknown_default_open_api")
    public static final AssetTypeV2 UnknownDefaultOpenApi = new AssetTypeV2("UnknownDefaultOpenApi", 2, "unknown_default_open_api");

    private static final /* synthetic */ AssetTypeV2[] $values() {
        return new AssetTypeV2[]{SONG, ARTIST, UnknownDefaultOpenApi};
    }

    static {
        AssetTypeV2[] assetTypeV2Arr$values = $values();
        $VALUES = assetTypeV2Arr$values;
        $ENTRIES = r.e(assetTypeV2Arr$values);
        INSTANCE = new Companion(0);
    }

    private AssetTypeV2(String str, int i7, String str2) {
        this.value = str2;
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static AssetTypeV2 valueOf(String str) {
        return (AssetTypeV2) Enum.valueOf(AssetTypeV2.class, str);
    }

    public static AssetTypeV2[] values() {
        return (AssetTypeV2[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
