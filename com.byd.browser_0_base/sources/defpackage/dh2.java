package defpackage;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: SectionInformation.java */
/* JADX INFO: loaded from: classes2.dex */
public class dh2 {
    public final ArrayList<lf0> a;
    public final int b;
    public int c;
    public boolean d;
    public String e;

    public dh2(int i) {
        this(i, 0, null);
    }

    public final lf0 a(int i) {
        ArrayList<lf0> arrayList = this.a;
        if (arrayList.isEmpty() || i < 0 || i >= arrayList.size()) {
            return null;
        }
        return arrayList.get(i);
    }

    public final lf0 b() {
        return a(this.c);
    }

    public final int c() {
        return this.a.size();
    }

    public final void d(lf0 lf0Var) {
        int i = 0;
        while (true) {
            ArrayList<lf0> arrayList = this.a;
            if (i >= arrayList.size()) {
                return;
            }
            if (arrayList.get(i) == lf0Var) {
                this.c = i;
                return;
            }
            i++;
        }
    }

    public dh2(int i, int i2, List list) {
        ArrayList<lf0> arrayList = new ArrayList<>();
        this.a = arrayList;
        this.d = true;
        this.b = i;
        arrayList.clear();
        if (list == null || list.isEmpty()) {
            this.c = -1;
        } else {
            this.c = i2;
            arrayList.addAll(list);
        }
    }
}
