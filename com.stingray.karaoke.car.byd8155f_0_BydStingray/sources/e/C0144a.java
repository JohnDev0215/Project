package E;

import G.U;

/* JADX INFO: renamed from: E.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0144a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1326a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1327b;
    public boolean c;
    public Object d;

    public C0144a(y9.a... aVarArr) {
        this.f1326a = -1;
        this.f1327b = -1;
        this.c = false;
        this.d = aVarArr;
    }

    public C0144a(int i7, int i10) {
        switch (i10) {
            case 1:
                this.f1326a = i7;
                this.f1327b = -1;
                this.d = new V.d(new U[16]);
                break;
            default:
                this.f1326a = i7;
                this.f1327b = -1;
                break;
        }
    }
}
