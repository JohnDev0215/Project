package n2;

import android.media.AudioManager;
import android.os.Handler;

/* JADX INFO: renamed from: n2.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1903c implements AudioManager.OnAudioFocusChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Handler f19627a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C1904d f19628b;

    public C1903c(C1904d c1904d, Handler handler) {
        this.f19628b = c1904d;
        this.f19627a = handler;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public final void onAudioFocusChange(int i7) {
        this.f19627a.post(new M.g(i7, 2, this));
    }
}
