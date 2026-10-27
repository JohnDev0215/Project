package android.support.v4.media.session;

import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class h {
    public final String a;
    public final CharSequence b;
    public final int c;

    public h(String str, String str2, int i) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("You must specify an action to build a CustomAction");
        }
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("You must specify a name to build a CustomAction");
        }
        if (i == 0) {
            throw new IllegalArgumentException("You must specify an icon resource id to build a CustomAction");
        }
        this.a = str;
        this.b = str2;
        this.c = i;
    }

    public final PlaybackStateCompat.CustomAction a() {
        return new PlaybackStateCompat.CustomAction(this.a, this.b, this.c, null);
    }
}
