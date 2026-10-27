package a.e.b;

import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f189a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f190b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f191c;
    public float g;
    a k;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f192d = -1;
    int e = -1;
    public int f = 0;
    public boolean h = false;
    float[] i = new float[9];
    float[] j = new float[9];
    b[] l = new b[16];
    int m = 0;
    public int n = 0;
    HashSet<b> o = null;

    public enum a {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public i(a aVar, String str) {
        this.k = aVar;
    }

    static void b() {
        f189a++;
    }

    public final void a(b bVar) {
        int i = 0;
        while (true) {
            int i2 = this.m;
            if (i >= i2) {
                b[] bVarArr = this.l;
                if (i2 >= bVarArr.length) {
                    this.l = (b[]) Arrays.copyOf(bVarArr, bVarArr.length * 2);
                }
                b[] bVarArr2 = this.l;
                int i3 = this.m;
                bVarArr2[i3] = bVar;
                this.m = i3 + 1;
                return;
            }
            if (this.l[i] == bVar) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void c(b bVar) {
        int i = this.m;
        int i2 = 0;
        while (i2 < i) {
            if (this.l[i2] == bVar) {
                while (i2 < i - 1) {
                    b[] bVarArr = this.l;
                    int i3 = i2 + 1;
                    bVarArr[i2] = bVarArr[i3];
                    i2 = i3;
                }
                this.m--;
                return;
            }
            i2++;
        }
    }

    public void d() {
        this.f191c = null;
        this.k = a.UNKNOWN;
        this.f = 0;
        this.f192d = -1;
        this.e = -1;
        this.g = 0.0f;
        this.h = false;
        int i = this.m;
        for (int i2 = 0; i2 < i; i2++) {
            this.l[i2] = null;
        }
        this.m = 0;
        this.n = 0;
        this.f190b = false;
        Arrays.fill(this.j, 0.0f);
    }

    public void e(d dVar, float f) {
        this.g = f;
        this.h = true;
        int i = this.m;
        for (int i2 = 0; i2 < i; i2++) {
            this.l[i2].B(dVar, this, false);
        }
        this.m = 0;
    }

    public void f(a aVar, String str) {
        this.k = aVar;
    }

    public final void g(b bVar) {
        int i = this.m;
        for (int i2 = 0; i2 < i; i2++) {
            this.l[i2].C(bVar, false);
        }
        this.m = 0;
    }

    public String toString() {
        StringBuilder sb;
        if (this.f191c != null) {
            sb = new StringBuilder();
            sb.append("");
            sb.append(this.f191c);
        } else {
            sb = new StringBuilder();
            sb.append("");
            sb.append(this.f192d);
        }
        return sb.toString();
    }
}
