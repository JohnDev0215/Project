package C3;

import B5.G;
import java.io.IOException;
import java.util.ArrayList;
import r9.z;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f971a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long[] f972b;
    public final ArrayList c;
    public final ArrayList d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f973e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f974f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public A2.c f975g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f976h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ e f977i;

    public b(e eVar, String str) {
        this.f977i = eVar;
        this.f971a = str;
        eVar.getClass();
        this.f972b = new long[2];
        eVar.getClass();
        this.c = new ArrayList(2);
        eVar.getClass();
        this.d = new ArrayList(2);
        StringBuilder sb = new StringBuilder(str);
        sb.append('.');
        int length = sb.length();
        eVar.getClass();
        for (int i7 = 0; i7 < 2; i7++) {
            sb.append(i7);
            this.c.add(this.f977i.l.e(sb.toString()));
            sb.append(".tmp");
            this.d.add(this.f977i.l.e(sb.toString()));
            sb.setLength(length);
        }
    }

    public final G a() {
        if (!this.f973e || this.f975g != null || this.f974f) {
            return null;
        }
        ArrayList arrayList = this.c;
        int size = arrayList.size();
        int i7 = 0;
        while (true) {
            e eVar = this.f977i;
            if (i7 >= size) {
                this.f976h++;
                return new G(eVar, this);
            }
            if (!eVar.f979A.e((z) arrayList.get(i7))) {
                try {
                    eVar.y(this);
                } catch (IOException unused) {
                }
                return null;
            }
            i7++;
        }
    }
}
