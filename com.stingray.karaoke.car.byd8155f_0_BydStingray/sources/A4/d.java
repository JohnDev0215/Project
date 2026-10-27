package A4;

import u.C2465e;

/* JADX INFO: loaded from: classes.dex */
public final class d extends C2465e {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f415r;

    @Override // u.C2458F, java.util.Map
    public final void clear() {
        this.f415r = 0;
        super.clear();
    }

    @Override // u.C2458F
    public final void h(C2465e c2465e) {
        this.f415r = 0;
        super.h(c2465e);
    }

    @Override // u.C2458F, java.util.Map
    public final int hashCode() {
        if (this.f415r == 0) {
            this.f415r = super.hashCode();
        }
        return this.f415r;
    }

    @Override // u.C2458F
    public final Object i(int i7) {
        this.f415r = 0;
        return super.i(i7);
    }

    @Override // u.C2458F
    public final Object j(int i7, Object obj) {
        this.f415r = 0;
        return super.j(i7, obj);
    }

    @Override // u.C2458F, java.util.Map
    public final Object put(Object obj, Object obj2) {
        this.f415r = 0;
        return super.put(obj, obj2);
    }
}
