package k2;

import android.net.Uri;
import h2.AbstractC1346c;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final /* synthetic */ int f17674j = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f17675a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f17676b;
    public final int c;
    public final byte[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map f17677e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f17678f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f17679g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f17680h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f17681i;

    static {
        e2.G.a("media3.datasource");
    }

    public m(Uri uri, long j3, int i7, byte[] bArr, Map map, long j10, long j11, String str, int i10) {
        byte[] bArr2 = bArr;
        AbstractC1346c.c(j3 + j10 >= 0);
        AbstractC1346c.c(j10 >= 0);
        AbstractC1346c.c(j11 > 0 || j11 == -1);
        uri.getClass();
        this.f17675a = uri;
        this.f17676b = j3;
        this.c = i7;
        this.d = (bArr2 == null || bArr2.length == 0) ? null : bArr2;
        this.f17677e = Collections.unmodifiableMap(new HashMap(map));
        this.f17678f = j10;
        this.f17679g = j11;
        this.f17680h = str;
        this.f17681i = i10;
    }

    public final l a() {
        l lVar = new l();
        lVar.f17667a = this.f17675a;
        lVar.f17668b = this.f17676b;
        lVar.c = this.c;
        lVar.d = this.d;
        lVar.f17669e = this.f17677e;
        lVar.f17670f = this.f17678f;
        lVar.f17671g = this.f17679g;
        lVar.f17672h = this.f17680h;
        lVar.f17673i = this.f17681i;
        return lVar;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("DataSpec[");
        int i7 = this.c;
        if (i7 == 1) {
            str = "GET";
        } else if (i7 == 2) {
            str = "POST";
        } else {
            if (i7 != 3) {
                throw new IllegalStateException();
            }
            str = "HEAD";
        }
        sb.append(str);
        sb.append(" ");
        sb.append(this.f17675a);
        sb.append(", ");
        sb.append(this.f17678f);
        sb.append(", ");
        sb.append(this.f17679g);
        sb.append(", ");
        sb.append(this.f17680h);
        sb.append(", ");
        return M5.b.h(sb, this.f17681i, "]");
    }
}
