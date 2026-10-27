package e4;

import android.widget.SeekBar;
import n0.d;

/* JADX INFO: compiled from: OnStartTrackingTouch.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements d.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final a f6299a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int f6300b;

    /* JADX INFO: compiled from: OnStartTrackingTouch.java */
    public interface a {
        void h(int i7, SeekBar seekBar);
    }

    public b(a aVar, int i7) {
        this.f6299a = aVar;
        this.f6300b = i7;
    }

    @Override // n0.d.c
    public void onStartTrackingTouch(SeekBar seekBar) {
        this.f6299a.h(this.f6300b, seekBar);
    }
}
