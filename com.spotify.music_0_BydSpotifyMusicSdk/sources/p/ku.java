package p;

import android.text.TextUtils;
import com.spotify.common.uri.SpotifyUri$Kind;

/* JADX INFO: loaded from: classes.dex */
public final class ku {
    public final vv2 a;

    public ku(vv2 vv2Var) {
        this.a = vv2Var;
    }

    public static Boolean a(Boolean[] boolArr) {
        boolean z = false;
        if (boolArr != null && boolArr.length > 0 && boolArr[0].booleanValue()) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public static String b(if2 if2Var) {
        SpotifyUri$Kind spotifyUri$Kind = if2Var.e;
        if (spotifyUri$Kind != SpotifyUri$Kind.PLAYLIST && spotifyUri$Kind != SpotifyUri$Kind.PLAYLIST_V2) {
            throw new IllegalArgumentException(String.format("Got URI of type %s, expected PLAYLIST.", if2Var.e));
        }
        String str = if2Var.l;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("URI is missing id.");
        }
        return str;
    }

    public static String c(SpotifyUri$Kind spotifyUri$Kind, if2 if2Var) {
        if (if2Var.e != spotifyUri$Kind) {
            throw new IllegalArgumentException(String.format("Got URI of type %s, expected %s.", if2Var.e, spotifyUri$Kind));
        }
        String str = if2Var.l;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("URI is missing id.");
        }
        return str;
    }
}
