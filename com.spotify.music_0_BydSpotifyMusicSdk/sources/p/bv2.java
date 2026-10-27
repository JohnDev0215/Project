package p;

import android.os.Parcelable;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class bv2 {
    public static final com.spotify.navigation.identifier.a a;
    public static final com.spotify.navigation.identifier.a b;
    public static final com.spotify.navigation.identifier.a c;
    public static final com.spotify.navigation.identifier.a d;
    public static final com.spotify.navigation.identifier.a e;
    public static final com.spotify.navigation.identifier.a f;
    public static final com.spotify.navigation.identifier.a g;
    public static final com.spotify.navigation.identifier.a h;
    public static final com.spotify.navigation.identifier.a i;
    public static final com.spotify.navigation.identifier.a j;
    public static final Set k;
    public static final Set l;

    static {
        Parcelable.Creator<com.spotify.navigation.identifier.a> creator = com.spotify.navigation.identifier.a.CREATOR;
        com.spotify.navigation.identifier.a aVarP = zb0.p("spotify:navigation");
        a = aVarP;
        com.spotify.navigation.identifier.a aVarP2 = zb0.p("spotify:home");
        b = aVarP2;
        com.spotify.navigation.identifier.a aVarP3 = zb0.p("spotify:recently-played");
        c = aVarP3;
        com.spotify.navigation.identifier.a aVarP4 = zb0.p("spotify:browse");
        d = aVarP4;
        com.spotify.navigation.identifier.a aVarP5 = zb0.p("spotify:library");
        e = aVarP5;
        com.spotify.navigation.identifier.a aVarP6 = zb0.p("spotify:collection:playlists");
        f = aVarP6;
        com.spotify.navigation.identifier.a aVarP7 = zb0.p("spotify:collection:artists");
        g = aVarP7;
        com.spotify.navigation.identifier.a aVarP8 = zb0.p("spotify:collection:albums");
        h = aVarP8;
        com.spotify.navigation.identifier.a aVarP9 = zb0.p("spotify:collection:shows");
        i = aVarP9;
        com.spotify.navigation.identifier.a aVarP10 = zb0.p("spotify:collection:audiobooks");
        j = aVarP10;
        k = ux2.z(aVarP, aVarP2, aVarP3, aVarP4, aVarP5, aVarP6, aVarP7, aVarP8, aVarP9, aVarP10);
        l = ux2.z(aVarP6, aVarP7, aVarP8, aVarP9, aVarP10);
    }
}
