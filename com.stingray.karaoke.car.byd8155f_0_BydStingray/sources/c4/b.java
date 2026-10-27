package c4;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f12378a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long[] f12379b;
    public final File[] c;
    public final File[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f12380e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public A2.c f12381f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ c f12382g;

    public b(c cVar, String str) {
        this.f12382g = cVar;
        this.f12378a = str;
        int i7 = cVar.f12387r;
        this.f12379b = new long[i7];
        this.c = new File[i7];
        this.d = new File[i7];
        StringBuilder sb = new StringBuilder(str);
        sb.append('.');
        int length = sb.length();
        for (int i10 = 0; i10 < cVar.f12387r; i10++) {
            sb.append(i10);
            File[] fileArr = this.c;
            String string = sb.toString();
            File file = cVar.l;
            fileArr[i10] = new File(file, string);
            sb.append(".tmp");
            this.d[i10] = new File(file, sb.toString());
            sb.setLength(length);
        }
    }

    public final String a() {
        StringBuilder sb = new StringBuilder();
        for (long j3 : this.f12379b) {
            sb.append(' ');
            sb.append(j3);
        }
        return sb.toString();
    }
}
