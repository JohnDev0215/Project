package p;

import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ba0 {
    public final String a;
    public final long[] b;
    public final ArrayList c;
    public final ArrayList d;
    public boolean e;
    public boolean f;
    public aa0 g;
    public int h;
    public final /* synthetic */ coil.disk.a i;

    public ba0(coil.disk.a aVar, String str) {
        this.i = aVar;
        this.a = str;
        aVar.getClass();
        this.b = new long[2];
        aVar.getClass();
        this.c = new ArrayList(2);
        aVar.getClass();
        this.d = new ArrayList(2);
        StringBuilder sb = new StringBuilder(str);
        sb.append('.');
        int length = sb.length();
        aVar.getClass();
        for (int i = 0; i < 2; i++) {
            sb.append(i);
            this.c.add(this.i.c.d(sb.toString()));
            sb.append(".tmp");
            this.d.add(this.i.c.d(sb.toString()));
            sb.setLength(length);
        }
    }

    public final ca0 a() {
        if (!this.e || this.g != null || this.f) {
            return null;
        }
        ArrayList arrayList = this.c;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            coil.disk.a aVar = this.i;
            if (i >= size) {
                this.h++;
                return new ca0(aVar, this);
            }
            if (!aVar.x.f((os1) arrayList.get(i))) {
                try {
                    aVar.H0(this);
                } catch (IOException unused) {
                }
                return null;
            }
            i++;
        }
    }
}
