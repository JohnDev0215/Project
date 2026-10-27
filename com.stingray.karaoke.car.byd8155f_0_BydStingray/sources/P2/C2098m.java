package p2;

import android.media.AudioTrack;

/* JADX INFO: renamed from: p2.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2098m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2097l f20721a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f20722b;
    public long c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f20723e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f20724f;

    public C2098m(AudioTrack audioTrack) {
        this.f20721a = new C2097l(audioTrack);
        a();
    }

    public final void a() {
        if (this.f20721a != null) {
            b(0);
        }
    }

    public final void b(int i7) {
        this.f20722b = i7;
        if (i7 == 0) {
            this.f20723e = 0L;
            this.f20724f = -1L;
            this.c = System.nanoTime() / 1000;
            this.d = 10000L;
            return;
        }
        if (i7 == 1) {
            this.d = 10000L;
            return;
        }
        if (i7 == 2 || i7 == 3) {
            this.d = 10000000L;
        } else {
            if (i7 != 4) {
                throw new IllegalStateException();
            }
            this.d = 500000L;
        }
    }
}
