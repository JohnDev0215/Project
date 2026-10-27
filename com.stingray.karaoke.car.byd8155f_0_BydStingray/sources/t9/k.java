package t9;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class k {
    public StringBuilder d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f22562e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f22563f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public char f22564g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public StringBuilder f22565h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f22560a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final StringBuilder f22561b = new StringBuilder();
    public final ArrayList c = new ArrayList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f22566i = false;

    public final void a() {
        if (this.f22566i) {
            String strA = v9.a.a(this.f22563f);
            StringBuilder sb = this.f22565h;
            String strA2 = sb != null ? v9.a.a(sb.toString()) : null;
            ArrayList arrayList = this.c;
            String str = this.f22562e;
            w9.n nVar = new w9.n();
            nVar.f23378g = str;
            nVar.f23379h = strA;
            nVar.f23380i = strA2;
            arrayList.add(nVar);
            this.d = null;
            this.f22566i = false;
            this.f22562e = null;
            this.f22563f = null;
            this.f22565h = null;
        }
    }
}
