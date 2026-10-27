package a.j.b;

import a.j.b.a;
import android.graphics.Rect;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class d<T> implements Comparator<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Rect f897a = new Rect();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Rect f898b = new Rect();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f899c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b<T> f900d;

    public d(boolean z, b<T> bVar) {
        this.f899c = z;
        this.f900d = bVar;
    }

    @Override // java.util.Comparator
    public int compare(T t, T t2) {
        Rect rect = this.f897a;
        Rect rect2 = this.f898b;
        ((a.C0021a) this.f900d).a(t, rect);
        ((a.C0021a) this.f900d).a(t2, rect2);
        int i = rect.top;
        int i2 = rect2.top;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        int i3 = rect.left;
        int i4 = rect2.left;
        if (i3 < i4) {
            return this.f899c ? 1 : -1;
        }
        if (i3 > i4) {
            return this.f899c ? -1 : 1;
        }
        int i5 = rect.bottom;
        int i6 = rect2.bottom;
        if (i5 < i6) {
            return -1;
        }
        if (i5 > i6) {
            return 1;
        }
        int i7 = rect.right;
        int i8 = rect2.right;
        if (i7 < i8) {
            return this.f899c ? 1 : -1;
        }
        if (i7 > i8) {
            return this.f899c ? -1 : 1;
        }
        return 0;
    }
}
