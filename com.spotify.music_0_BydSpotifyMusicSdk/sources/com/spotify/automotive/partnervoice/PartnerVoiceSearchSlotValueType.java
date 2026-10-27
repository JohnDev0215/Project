package com.spotify.automotive.partnervoice;

import com.spotify.common.uri.SpotifyUri$Kind;
import java.util.List;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public enum PartnerVoiceSearchSlotValueType {
    SLOT_ALBUM(v03.B(SpotifyUri$Kind.ALBUM)),
    SLOT_ARTIST(v03.B(SpotifyUri$Kind.ARTIST)),
    SLOT_EPISODE(v03.B(SpotifyUri$Kind.EPISODE)),
    SLOT_GENRE(v03.B(SpotifyUri$Kind.GENRE)),
    SLOT_PLAYLIST(v03.C(SpotifyUri$Kind.PLAYLIST, SpotifyUri$Kind.PLAYLIST_V2)),
    SLOT_SHOW(v03.B(SpotifyUri$Kind.SHOW)),
    SLOT_TRACK(v03.B(SpotifyUri$Kind.TRACK));

    private final List<SpotifyUri$Kind> spotifyKinds;

    PartnerVoiceSearchSlotValueType(List list) {
        this.spotifyKinds = list;
    }

    public final List a() {
        return this.spotifyKinds;
    }
}
