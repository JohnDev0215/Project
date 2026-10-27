package e9;

import A7.m;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import r9.AbstractC2312b;
import r9.J;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f14495a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long[] f14496b;
    public final ArrayList c;
    public final ArrayList d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f14497e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f14498f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public A2.c f14499g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f14500h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f14501i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ g f14502j;

    public d(g gVar, String str) {
        m.f(str, "key");
        this.f14502j = gVar;
        this.f14495a = str;
        gVar.getClass();
        this.f14496b = new long[2];
        this.c = new ArrayList();
        this.d = new ArrayList();
        StringBuilder sb = new StringBuilder(str);
        sb.append('.');
        int length = sb.length();
        for (int i7 = 0; i7 < 2; i7++) {
            sb.append(i7);
            this.c.add(new File(this.f14502j.l, sb.toString()));
            sb.append(".tmp");
            this.d.add(new File(this.f14502j.l, sb.toString()));
            sb.setLength(length);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v5, types: [r9.d] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.lang.Object] */
    public final e a() {
        byte[] bArr = d9.b.f13645a;
        if (!this.f14497e) {
            return null;
        }
        g gVar = this.f14502j;
        if (!gVar.f14522v && (this.f14499g != null || this.f14498f)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        long[] jArr = (long[]) this.f14496b.clone();
        for (int i7 = 0; i7 < 2; i7++) {
            try {
                File file = (File) this.c.get(i7);
                m.f(file, "file");
                ?? I2 = AbstractC2312b.i(file);
                if (!gVar.f14522v) {
                    this.f14500h++;
                    I2 = new c(I2, gVar, this);
                }
                arrayList.add(I2);
            } catch (FileNotFoundException unused) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    d9.b.c((J) it.next());
                }
                try {
                    gVar.C(this);
                } catch (IOException unused2) {
                }
                return null;
            }
        }
        return new e(this.f14502j, this.f14495a, this.f14501i, arrayList, jArr);
    }
}
