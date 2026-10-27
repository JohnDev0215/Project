package p7;

import A7.m;
import java.util.Comparator;

/* JADX INFO: renamed from: p7.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2112b implements Comparator {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C2112b f20944m = new C2112b(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C2112b f20945n = new C2112b(1);
    public final /* synthetic */ int l;

    public /* synthetic */ C2112b(int i7) {
        this.l = i7;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                Comparable comparable = (Comparable) obj;
                Comparable comparable2 = (Comparable) obj2;
                m.f(comparable, "a");
                m.f(comparable2, "b");
                return comparable.compareTo(comparable2);
            default:
                Comparable comparable3 = (Comparable) obj;
                Comparable comparable4 = (Comparable) obj2;
                m.f(comparable3, "a");
                m.f(comparable4, "b");
                return comparable4.compareTo(comparable3);
        }
    }

    @Override // java.util.Comparator
    public final Comparator reversed() {
        switch (this.l) {
            case 0:
                return f20945n;
            default:
                return f20944m;
        }
    }
}
