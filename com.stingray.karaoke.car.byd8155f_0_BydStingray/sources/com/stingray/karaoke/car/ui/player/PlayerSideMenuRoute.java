package com.stingray.karaoke.car.ui.player;

import X5.N;
import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\bj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/stingray/karaoke/car/ui/player/PlayerSideMenuRoute;", "", "", "value", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "Companion", "X5/N", "SongQueue", "SongDetails", "Songs", "Artists", "Explore", "Scoring", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PlayerSideMenuRoute {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ PlayerSideMenuRoute[] $VALUES;
    public static final N Companion;
    private final String value;
    public static final PlayerSideMenuRoute SongQueue = new PlayerSideMenuRoute("SongQueue", 0, "player/song_queue");
    public static final PlayerSideMenuRoute SongDetails = new PlayerSideMenuRoute("SongDetails", 1, "player/song_detail");
    public static final PlayerSideMenuRoute Songs = new PlayerSideMenuRoute("Songs", 2, "player/songs");
    public static final PlayerSideMenuRoute Artists = new PlayerSideMenuRoute("Artists", 3, "player/artists");
    public static final PlayerSideMenuRoute Explore = new PlayerSideMenuRoute("Explore", 4, "player/explore");
    public static final PlayerSideMenuRoute Scoring = new PlayerSideMenuRoute("Scoring", 5, "player/scoring");

    private static final /* synthetic */ PlayerSideMenuRoute[] $values() {
        return new PlayerSideMenuRoute[]{SongQueue, SongDetails, Songs, Artists, Explore, Scoring};
    }

    static {
        PlayerSideMenuRoute[] playerSideMenuRouteArr$values = $values();
        $VALUES = playerSideMenuRouteArr$values;
        $ENTRIES = r.e(playerSideMenuRouteArr$values);
        Companion = new N();
    }

    private PlayerSideMenuRoute(String str, int i7, String str2) {
        this.value = str2;
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static PlayerSideMenuRoute valueOf(String str) {
        return (PlayerSideMenuRoute) Enum.valueOf(PlayerSideMenuRoute.class, str);
    }

    public static PlayerSideMenuRoute[] values() {
        return (PlayerSideMenuRoute[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }
}
