package v3;

import androidx.recyclerview.widget.f;
import java.util.List;

/* JADX INFO: compiled from: DefaultDiffCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class b<T> extends f.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final List<T> f9676a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final List<T> f9677b;

    public b(List<T> list, List<T> list2) {
        this.f9676a = list;
        this.f9677b = list2;
    }

    @Override // androidx.recyclerview.widget.f.b
    public boolean a(int i7, int i8) {
        if (f(this.f9676a, i7) || f(this.f9677b, i8)) {
            return false;
        }
        return this.f9677b.get(i8).equals(this.f9676a.get(i7));
    }

    @Override // androidx.recyclerview.widget.f.b
    public boolean b(int i7, int i8) {
        if (f(this.f9676a, i7) || f(this.f9677b, i8)) {
            return false;
        }
        return this.f9677b.get(i8).equals(this.f9676a.get(i7));
    }

    @Override // androidx.recyclerview.widget.f.b
    public int d() {
        List<T> list = this.f9677b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.f.b
    public int e() {
        List<T> list = this.f9676a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    protected boolean f(List list, int i7) {
        return list == null || list.isEmpty() || i7 >= list.size() || i7 < 0;
    }
}
