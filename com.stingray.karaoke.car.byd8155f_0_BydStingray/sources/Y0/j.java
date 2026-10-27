package Y0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final j f10619b = new j(0);
    public static final j c = new j(1);
    public static final j d = new j(2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f10620a;

    public j(int i7) {
        this.f10620a = i7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j) {
            return this.f10620a == ((j) obj).f10620a;
        }
        return false;
    }

    public final int hashCode() {
        return this.f10620a;
    }

    public final String toString() {
        int i7 = this.f10620a;
        if (i7 == 0) {
            return "TextDecoration.None";
        }
        ArrayList arrayList = new ArrayList();
        if ((i7 & 1) != 0) {
            arrayList.add("Underline");
        }
        if ((i7 & 2) != 0) {
            arrayList.add("LineThrough");
        }
        if (arrayList.size() != 1) {
            return android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder("TextDecoration["), w0.c.L(arrayList, ", ", null, 62), ']');
        }
        return "TextDecoration." + ((String) arrayList.get(0));
    }
}
