package p;

import com.spotify.common.uri.SpotifyUri$Kind;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class v40 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[SpotifyUri$Kind.values().length];
        try {
            iArr[SpotifyUri$Kind.ALBUM.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[SpotifyUri$Kind.PLAYLIST.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[SpotifyUri$Kind.PLAYLIST_V2.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[SpotifyUri$Kind.ARTIST.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        a = iArr;
    }
}
