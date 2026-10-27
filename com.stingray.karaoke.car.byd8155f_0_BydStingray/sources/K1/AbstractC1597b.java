package k1;

import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: renamed from: k1.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1597b extends AbstractC1598c {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList f17619p;

    public AbstractC1597b(char[] cArr) {
        super(cArr);
        this.f17619p = new ArrayList();
    }

    public final AbstractC1598c A(String str) {
        Iterator it = this.f17619p.iterator();
        while (it.hasNext()) {
            d dVar = (d) ((AbstractC1598c) it.next());
            if (dVar.d().equals(str)) {
                if (dVar.f17619p.size() > 0) {
                    return (AbstractC1598c) dVar.f17619p.get(0);
                }
                return null;
            }
        }
        return null;
    }

    public final String B(int i7) throws h {
        AbstractC1598c abstractC1598cU = u(i7);
        if (abstractC1598cU instanceof i) {
            return abstractC1598cU.d();
        }
        throw new h(android.hardware.bydauto.ac.tsimpl.a.e(i7, "no string at index "), this);
    }

    public final String C(String str) throws h {
        AbstractC1598c abstractC1598cV = v(str);
        if (abstractC1598cV instanceof i) {
            return abstractC1598cV.d();
        }
        throw new h("no string found for key <" + str + ">, found [" + (abstractC1598cV != null ? abstractC1598cV.q() : null) + "] : " + abstractC1598cV, this);
    }

    public final String D(String str) {
        AbstractC1598c abstractC1598cA = A(str);
        if (abstractC1598cA instanceof i) {
            return abstractC1598cA.d();
        }
        return null;
    }

    public final boolean E(String str) {
        for (AbstractC1598c abstractC1598c : this.f17619p) {
            if ((abstractC1598c instanceof d) && ((d) abstractC1598c).d().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public final ArrayList F() {
        ArrayList arrayList = new ArrayList();
        for (AbstractC1598c abstractC1598c : this.f17619p) {
            if (abstractC1598c instanceof d) {
                arrayList.add(((d) abstractC1598c).d());
            }
        }
        return arrayList;
    }

    public final void G(String str, AbstractC1598c abstractC1598c) {
        Iterator it = this.f17619p.iterator();
        while (it.hasNext()) {
            d dVar = (d) ((AbstractC1598c) it.next());
            if (dVar.d().equals(str)) {
                if (dVar.f17619p.size() > 0) {
                    dVar.f17619p.set(0, abstractC1598c);
                    return;
                } else {
                    dVar.f17619p.add(abstractC1598c);
                    return;
                }
            }
        }
        d dVar2 = new d(str.toCharArray());
        dVar2.f17620m = 0L;
        dVar2.r(str.length() - 1);
        if (dVar2.f17619p.size() > 0) {
            dVar2.f17619p.set(0, abstractC1598c);
        } else {
            dVar2.f17619p.add(abstractC1598c);
        }
        this.f17619p.add(dVar2);
    }

    @Override // k1.AbstractC1598c
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AbstractC1597b) {
            return this.f17619p.equals(((AbstractC1597b) obj).f17619p);
        }
        return false;
    }

    @Override // k1.AbstractC1598c
    public int hashCode() {
        return Objects.hash(this.f17619p, Integer.valueOf(super.hashCode()));
    }

    public final void s(AbstractC1598c abstractC1598c) {
        this.f17619p.add(abstractC1598c);
    }

    @Override // k1.AbstractC1598c
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public AbstractC1597b clone() {
        AbstractC1597b abstractC1597b = (AbstractC1597b) super.clone();
        ArrayList arrayList = new ArrayList(this.f17619p.size());
        Iterator it = this.f17619p.iterator();
        while (it.hasNext()) {
            AbstractC1598c abstractC1598cClone = ((AbstractC1598c) it.next()).clone();
            abstractC1598cClone.o = abstractC1597b;
            arrayList.add(abstractC1598cClone);
        }
        abstractC1597b.f17619p = arrayList;
        return abstractC1597b;
    }

    @Override // k1.AbstractC1598c
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (AbstractC1598c abstractC1598c : this.f17619p) {
            if (sb.length() > 0) {
                sb.append("; ");
            }
            sb.append(abstractC1598c);
        }
        return super.toString() + " = <" + ((Object) sb) + " >";
    }

    public final AbstractC1598c u(int i7) throws h {
        if (i7 < 0 || i7 >= this.f17619p.size()) {
            throw new h(android.hardware.bydauto.ac.tsimpl.a.e(i7, "no element at index "), this);
        }
        return (AbstractC1598c) this.f17619p.get(i7);
    }

    public final AbstractC1598c v(String str) throws h {
        Iterator it = this.f17619p.iterator();
        while (it.hasNext()) {
            d dVar = (d) ((AbstractC1598c) it.next());
            if (dVar.d().equals(str)) {
                if (dVar.f17619p.size() > 0) {
                    return (AbstractC1598c) dVar.f17619p.get(0);
                }
                return null;
            }
        }
        throw new h(android.hardware.bydauto.ac.tsimpl.a.i("no element for key <", str, ">"), this);
    }

    public final float w(int i7) throws h {
        AbstractC1598c abstractC1598cU = u(i7);
        if (abstractC1598cU != null) {
            return abstractC1598cU.l();
        }
        throw new h(android.hardware.bydauto.ac.tsimpl.a.e(i7, "no float at index "), this);
    }

    public final float x(String str) throws h {
        AbstractC1598c abstractC1598cV = v(str);
        if (abstractC1598cV != null) {
            return abstractC1598cV.l();
        }
        StringBuilder sbJ = AbstractC1347d.j("no float found for key <", str, ">, found [");
        sbJ.append(abstractC1598cV.q());
        sbJ.append("] : ");
        sbJ.append(abstractC1598cV);
        throw new h(sbJ.toString(), this);
    }

    public final int y(int i7) throws h {
        AbstractC1598c abstractC1598cU = u(i7);
        if (abstractC1598cU != null) {
            return abstractC1598cU.m();
        }
        throw new h(android.hardware.bydauto.ac.tsimpl.a.e(i7, "no int at index "), this);
    }

    public final AbstractC1598c z(int i7) {
        if (i7 < 0 || i7 >= this.f17619p.size()) {
            return null;
        }
        return (AbstractC1598c) this.f17619p.get(i7);
    }
}
