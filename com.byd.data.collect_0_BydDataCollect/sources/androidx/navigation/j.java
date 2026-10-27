package androidx.navigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.navigation.i;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: NavGraph.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends i implements Iterable<i> {
    final b.d.h<i> k;
    private int l;
    private String m;

    /* JADX INFO: compiled from: NavGraph.java */
    class a implements Iterator<i> {

        /* JADX INFO: renamed from: b */
        private int f1712b = -1;

        /* JADX INFO: renamed from: c */
        private boolean f1713c = false;

        a() {
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: a */
        public i next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.f1713c = true;
            b.d.h<i> hVar = j.this.k;
            int i2 = this.f1712b + 1;
            this.f1712b = i2;
            return hVar.p(i2);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f1712b + 1 < j.this.k.o();
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.f1713c) {
                throw new IllegalStateException("You must call next() before you can remove an element");
            }
            j.this.k.p(this.f1712b).q(null);
            j.this.k.n(this.f1712b);
            this.f1712b--;
            this.f1713c = false;
        }
    }

    public j(q<? extends j> qVar) {
        super(qVar);
        this.k = new b.d.h<>();
    }

    @Override // androidx.navigation.i
    public String g() {
        return i() != 0 ? super.g() : "the root navigation";
    }

    @Override // java.lang.Iterable
    public final Iterator<i> iterator() {
        return new a();
    }

    @Override // androidx.navigation.i
    i.a l(h hVar) {
        i.a aVarL = super.l(hVar);
        Iterator<i> it = iterator();
        while (it.hasNext()) {
            i.a aVarL2 = it.next().l(hVar);
            if (aVarL2 != null && (aVarL == null || aVarL2.compareTo(aVarL) > 0)) {
                aVarL = aVarL2;
            }
        }
        return aVarL;
    }

    @Override // androidx.navigation.i
    public void m(Context context, AttributeSet attributeSet) {
        super.m(context, attributeSet);
        TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attributeSet, androidx.navigation.u.a.NavGraphNavigator);
        x(typedArrayObtainAttributes.getResourceId(androidx.navigation.u.a.NavGraphNavigator_startDestination, 0));
        this.m = i.h(context, this.l);
        typedArrayObtainAttributes.recycle();
    }

    public final void s(i iVar) {
        int i2 = iVar.i();
        if (i2 == 0) {
            throw new IllegalArgumentException("Destinations must have an id. Call setId() or include an android:id in your navigation XML.");
        }
        if (i2 == i()) {
            throw new IllegalArgumentException("Destination " + iVar + " cannot have the same id as graph " + this);
        }
        i iVarG = this.k.g(i2);
        if (iVarG == iVar) {
            return;
        }
        if (iVar.k() != null) {
            throw new IllegalStateException("Destination already has a parent set. Call NavGraph.remove() to remove the previous parent.");
        }
        if (iVarG != null) {
            iVarG.q(null);
        }
        iVar.q(this);
        this.k.l(iVar.i(), iVar);
    }

    public final i t(int i2) {
        return u(i2, true);
    }

    @Override // androidx.navigation.i
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" startDestination=");
        i iVarT = t(w());
        if (iVarT == null) {
            String str = this.m;
            if (str == null) {
                sb.append("0x");
                sb.append(Integer.toHexString(this.l));
            } else {
                sb.append(str);
            }
        } else {
            sb.append("{");
            sb.append(iVarT.toString());
            sb.append("}");
        }
        return sb.toString();
    }

    final i u(int i2, boolean z) {
        i iVarG = this.k.g(i2);
        if (iVarG != null) {
            return iVarG;
        }
        if (!z || k() == null) {
            return null;
        }
        return k().t(i2);
    }

    String v() {
        if (this.m == null) {
            this.m = Integer.toString(this.l);
        }
        return this.m;
    }

    public final int w() {
        return this.l;
    }

    public final void x(int i2) {
        if (i2 != i()) {
            this.l = i2;
            this.m = null;
            return;
        }
        throw new IllegalArgumentException("Start destination " + i2 + " cannot use the same id as the graph " + this);
    }
}
