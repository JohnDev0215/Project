package s2;

import e2.C1208p;

/* JADX INFO: loaded from: classes.dex */
public final class o extends Exception {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f22102m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final n f22103n;
    public final String o;

    public o(C1208p c1208p, s sVar, boolean z6, int i7) {
        this("Decoder init failed: [" + i7 + "], " + c1208p, sVar, c1208p.f14316n, z6, null, "androidx.media3.exoplayer.mediacodec.MediaCodecRenderer_" + (i7 < 0 ? "neg_" : "") + Math.abs(i7));
    }

    public o(String str, Throwable th, String str2, boolean z6, n nVar, String str3) {
        super(str, th);
        this.l = str2;
        this.f22102m = z6;
        this.f22103n = nVar;
        this.o = str3;
    }
}
