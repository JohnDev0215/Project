package androidx.navigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.navigation.j;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: NavGraph.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends j implements Iterable<j> {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    final n.h<j> f2717o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f2718p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private String f2719q;

    /* JADX INFO: compiled from: NavGraph.java */
    class a implements Iterator<j> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f2720e = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f2721g = false;

        a() {
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.f2721g = true;
            n.h<j> hVar = k.this.f2717o;
            int i7 = this.f2720e + 1;
            this.f2720e = i7;
            return hVar.l(i7);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f2720e + 1 < k.this.f2717o.k();
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.f2721g) {
                throw new IllegalStateException("You must call next() before you can remove an element");
            }
            k.this.f2717o.l(this.f2720e).t(null);
            k.this.f2717o.j(this.f2720e);
            this.f2720e--;
            this.f2721g = false;
        }
    }

    public k(r<? extends k> rVar) {
        super(rVar);
        this.f2717o = new n.h<>();
    }

    public final void A(int i7) {
        if (i7 != k()) {
            this.f2718p = i7;
            this.f2719q = null;
            return;
        }
        throw new IllegalArgumentException("Start destination " + i7 + " cannot use the same id as the graph " + this);
    }

    @Override // androidx.navigation.j
    public String i() {
        return k() != 0 ? super.i() : "the root navigation";
    }

    @Override // java.lang.Iterable
    public final Iterator<j> iterator() {
        return new a();
    }

    @Override // androidx.navigation.j
    j.a o(i iVar) {
        j.a aVarO = super.o(iVar);
        Iterator<j> it = iterator();
        while (it.hasNext()) {
            j.a aVarO2 = it.next().o(iVar);
            if (aVarO2 != null && (aVarO == null || aVarO2.compareTo(aVarO) > 0)) {
                aVarO = aVarO2;
            }
        }
        return aVarO;
    }

    @Override // androidx.navigation.j
    public void p(Context context, AttributeSet attributeSet) {
        super.p(context, attributeSet);
        TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attributeSet, w0.a.NavGraphNavigator);
        A(typedArrayObtainAttributes.getResourceId(w0.a.NavGraphNavigator_startDestination, 0));
        this.f2719q = j.j(context, this.f2718p);
        typedArrayObtainAttributes.recycle();
    }

    @Override // androidx.navigation.j
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" startDestination=");
        j jVarW = w(z());
        if (jVarW == null) {
            String str = this.f2719q;
            if (str == null) {
                sb.append("0x");
                sb.append(Integer.toHexString(this.f2718p));
            } else {
                sb.append(str);
            }
        } else {
            sb.append("{");
            sb.append(jVarW.toString());
            sb.append("}");
        }
        return sb.toString();
    }

    public final void v(j jVar) {
        int iK = jVar.k();
        if (iK == 0) {
            throw new IllegalArgumentException("Destinations must have an id. Call setId() or include an android:id in your navigation XML.");
        }
        if (iK == k()) {
            throw new IllegalArgumentException("Destination " + jVar + " cannot have the same id as graph " + this);
        }
        j jVarE = this.f2717o.e(iK);
        if (jVarE == jVar) {
            return;
        }
        if (jVar.n() != null) {
            throw new IllegalStateException("Destination already has a parent set. Call NavGraph.remove() to remove the previous parent.");
        }
        if (jVarE != null) {
            jVarE.t(null);
        }
        jVar.t(this);
        this.f2717o.i(jVar.k(), jVar);
    }

    public final j w(int i7) {
        return x(i7, true);
    }

    final j x(int i7, boolean z6) {
        j jVarE = this.f2717o.e(i7);
        if (jVarE != null) {
            return jVarE;
        }
        if (!z6 || n() == null) {
            return null;
        }
        return n().w(i7);
    }

    String y() {
        if (this.f2719q == null) {
            this.f2719q = Integer.toString(this.f2718p);
        }
        return this.f2719q;
    }

    public final int z() {
        return this.f2718p;
    }
}
