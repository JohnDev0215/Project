package d5;

import java.util.Comparator;

/* JADX INFO: renamed from: d5.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1100w extends AbstractC1102y {
    public static AbstractC1102y f(int i7) {
        return i7 < 0 ? AbstractC1102y.f13465b : i7 > 0 ? AbstractC1102y.c : AbstractC1102y.f13464a;
    }

    @Override // d5.AbstractC1102y
    public final AbstractC1102y a(int i7, int i10) {
        return f(Integer.compare(i7, i10));
    }

    @Override // d5.AbstractC1102y
    public final AbstractC1102y b(Object obj, Object obj2, Comparator comparator) {
        return f(comparator.compare(obj, obj2));
    }

    @Override // d5.AbstractC1102y
    public final AbstractC1102y c(boolean z6, boolean z10) {
        return f(Boolean.compare(z6, z10));
    }

    @Override // d5.AbstractC1102y
    public final AbstractC1102y d(boolean z6, boolean z10) {
        return f(Boolean.compare(z10, z6));
    }

    @Override // d5.AbstractC1102y
    public final int e() {
        return 0;
    }
}
