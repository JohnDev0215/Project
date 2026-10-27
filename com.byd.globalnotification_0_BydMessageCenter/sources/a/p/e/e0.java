package a.p.e;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f1157a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f1158b = new a();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1159a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1160b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1161c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1162d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1163e;

        public boolean a() {
            int i = this.f1159a;
            if ((i & 7) != 0 && (i & (b(this.f1162d, this.f1160b) << 0)) == 0) {
                return false;
            }
            int i2 = this.f1159a;
            if ((i2 & 112) != 0 && (i2 & (b(this.f1162d, this.f1161c) << 4)) == 0) {
                return false;
            }
            int i3 = this.f1159a;
            if ((i3 & 1792) != 0 && (i3 & (b(this.f1163e, this.f1160b) << 8)) == 0) {
                return false;
            }
            int i4 = this.f1159a;
            return (i4 & 28672) == 0 || ((b(this.f1163e, this.f1161c) << 12) & i4) != 0;
        }

        public int b(int i, int i2) {
            if (i > i2) {
                return 1;
            }
            return i == i2 ? 2 : 4;
        }
    }

    public interface b {
        int a();

        int b();

        int c(View view);

        int d(View view);

        View e(int i);
    }

    public e0(b bVar) {
        this.f1157a = bVar;
    }

    public View a(int i, int i2, int i3, int i4) {
        int iB = this.f1157a.b();
        int iA = this.f1157a.a();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View viewE = this.f1157a.e(i);
            int iD = this.f1157a.d(viewE);
            int iC = this.f1157a.c(viewE);
            a aVar = this.f1158b;
            aVar.f1160b = iB;
            aVar.f1161c = iA;
            aVar.f1162d = iD;
            aVar.f1163e = iC;
            if (i3 != 0) {
                aVar.f1159a = 0;
                aVar.f1159a = i3 | 0;
                if (aVar.a()) {
                    return viewE;
                }
            }
            if (i4 != 0) {
                a aVar2 = this.f1158b;
                aVar2.f1159a = 0;
                aVar2.f1159a = i4 | 0;
                if (aVar2.a()) {
                    view = viewE;
                }
            }
            i += i5;
        }
        return view;
    }

    public boolean b(View view, int i) {
        a aVar = this.f1158b;
        int iB = this.f1157a.b();
        int iA = this.f1157a.a();
        int iD = this.f1157a.d(view);
        int iC = this.f1157a.c(view);
        aVar.f1160b = iB;
        aVar.f1161c = iA;
        aVar.f1162d = iD;
        aVar.f1163e = iC;
        if (i == 0) {
            return false;
        }
        a aVar2 = this.f1158b;
        aVar2.f1159a = 0;
        aVar2.f1159a = 0 | i;
        return aVar2.a();
    }
}
