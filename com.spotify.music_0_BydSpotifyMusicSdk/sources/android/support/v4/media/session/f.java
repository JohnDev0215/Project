package android.support.v4.media.session;

import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    public int b;
    public long c;
    public float d;
    public long e;
    public long f;
    public Bundle h;
    public final ArrayList a = new ArrayList();
    public long g = -1;

    public final void a(PlaybackStateCompat.CustomAction customAction) {
        this.a.add(customAction);
    }

    public final PlaybackStateCompat b() {
        return new PlaybackStateCompat(this.b, this.c, 0L, this.d, this.e, 0, null, this.f, this.a, this.g, this.h);
    }
}
