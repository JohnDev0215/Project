package com.vivid.spotify.nowplaying;

import android.text.TextUtils;
import java.util.HashMap;

/* JADX INFO: compiled from: SpotifyCustomAction.java */
/* JADX INFO: loaded from: classes.dex */
public enum n {
    Shuffle("shuffle"),
    DisableShuffle("disable-shuffle"),
    LikeTrack("likeTrack"),
    UnlikeTrack("unlikeTrack"),
    LikeEpisode("likeEpisode"),
    UnlikeEpisode("unlikeEpisode"),
    SeekBackward("seek-backward"),
    SeekForward("seek-forward"),
    PlaybackSpeed("playbackSpeed");


    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final HashMap<String, n> f5353g = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f5355e;

    n(String str) {
        this.f5355e = str;
    }

    public static HashMap<String, n> getCollectCustomAction() {
        HashMap<String, n> map = f5353g;
        if (map.isEmpty()) {
            n nVar = LikeTrack;
            map.put(nVar.getAction(), nVar);
            n nVar2 = UnlikeTrack;
            map.put(nVar2.getAction(), nVar2);
            n nVar3 = LikeEpisode;
            map.put(nVar3.getAction(), nVar3);
            n nVar4 = UnlikeEpisode;
            map.put(nVar4.getAction(), nVar4);
        }
        return map;
    }

    public static n getSpotifyCustomAction(String str) {
        for (n nVar : values()) {
            if (TextUtils.equals(str, nVar.f5355e)) {
                return nVar;
            }
        }
        return null;
    }

    public String getAction() {
        return this.f5355e;
    }
}
