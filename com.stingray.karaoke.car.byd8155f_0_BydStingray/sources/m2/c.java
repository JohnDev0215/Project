package m2;

import android.media.MediaCodec;
import h2.AbstractC1343A;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f18946a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f18947b;
    public int c;
    public int[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f18948e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f18949f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f18950g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f18951h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final MediaCodec.CryptoInfo f18952i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final b f18953j;

    public c() {
        MediaCodec.CryptoInfo cryptoInfo = new MediaCodec.CryptoInfo();
        this.f18952i = cryptoInfo;
        this.f18953j = AbstractC1343A.f15195a >= 24 ? new b(cryptoInfo) : null;
    }
}
