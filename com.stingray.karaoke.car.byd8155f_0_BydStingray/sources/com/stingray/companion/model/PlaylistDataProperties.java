package com.stingray.companion.model;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/stingray/companion/model/PlaylistDataProperties;", "", "dataName", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getDataName", "()Ljava/lang/String;", "PARTY_MIX_ID", "ARTIST_ID", "LENGTH", "companion_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PlaylistDataProperties {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ PlaylistDataProperties[] $VALUES;
    private final String dataName;
    public static final PlaylistDataProperties PARTY_MIX_ID = new PlaylistDataProperties("PARTY_MIX_ID", 0, "partyMixId");
    public static final PlaylistDataProperties ARTIST_ID = new PlaylistDataProperties("ARTIST_ID", 1, "artistId");
    public static final PlaylistDataProperties LENGTH = new PlaylistDataProperties("LENGTH", 2, "length");

    private static final /* synthetic */ PlaylistDataProperties[] $values() {
        return new PlaylistDataProperties[]{PARTY_MIX_ID, ARTIST_ID, LENGTH};
    }

    static {
        PlaylistDataProperties[] playlistDataPropertiesArr$values = $values();
        $VALUES = playlistDataPropertiesArr$values;
        $ENTRIES = r.e(playlistDataPropertiesArr$values);
    }

    private PlaylistDataProperties(String str, int i7, String str2) {
        this.dataName = str2;
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static PlaylistDataProperties valueOf(String str) {
        return (PlaylistDataProperties) Enum.valueOf(PlaylistDataProperties.class, str);
    }

    public static PlaylistDataProperties[] values() {
        return (PlaylistDataProperties[]) $VALUES.clone();
    }

    public final String getDataName() {
        return this.dataName;
    }
}
