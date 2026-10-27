package p;

import com.spotify.prefs.prefsimpl.NativePrefs;

/* JADX INFO: loaded from: classes.dex */
public final class o10 implements n10, sb2 {
    public final NativePrefs a = NativePrefs.Companion.create();

    @Override // p.sb2
    public final void shutdown() {
        this.a.destroy();
    }
}
