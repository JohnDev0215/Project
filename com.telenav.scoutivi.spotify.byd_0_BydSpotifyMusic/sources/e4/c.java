package e4;

import android.widget.SeekBar;
import n0.d;

/* JADX INFO: compiled from: OnStopTrackingTouch.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements d.InterfaceC0132d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final a f6301a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int f6302b;

    /* JADX INFO: compiled from: OnStopTrackingTouch.java */
    public interface a {
        void j(int i7, SeekBar seekBar);
    }

    public c(a aVar, int i7) {
        this.f6301a = aVar;
        this.f6302b = i7;
    }

    @Override // n0.d.InterfaceC0132d
    public void onStopTrackingTouch(SeekBar seekBar) {
        this.f6301a.j(this.f6302b, seekBar);
    }
}
