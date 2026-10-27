package p8;

import java.io.UnsupportedEncodingException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: renamed from: p8.r, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2143r extends AbstractList implements RandomAccess, s {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C2119G f20990m = new C2119G(new C2143r());
    public final ArrayList l;

    public C2143r() {
        this.l = new ArrayList();
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i7, Object obj) {
        this.l.add(i7, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(this.l.size(), collection);
    }

    @Override // p8.s
    public final C2119G b() {
        return new C2119G(this);
    }

    @Override // p8.s
    public final List c() {
        return Collections.unmodifiableList(this.l);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        this.l.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i7) {
        String str;
        ArrayList arrayList = this.l;
        Object obj = arrayList.get(i7);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof AbstractC2129d) {
            AbstractC2129d abstractC2129d = (AbstractC2129d) obj;
            str = abstractC2129d.y();
            if (abstractC2129d.s()) {
                arrayList.set(i7, str);
            }
        } else {
            byte[] bArr = (byte[]) obj;
            byte[] bArr2 = AbstractC2141p.f20989a;
            try {
                str = new String(bArr, "UTF-8");
                if (AbstractC2115C.c(0, bArr.length, bArr) == 0) {
                    arrayList.set(i7, str);
                }
            } catch (UnsupportedEncodingException e10) {
                throw new RuntimeException("UTF-8 not supported?", e10);
            }
        }
        return str;
    }

    @Override // p8.s
    public final AbstractC2129d i(int i7) {
        AbstractC2129d tVar;
        ArrayList arrayList = this.l;
        Object obj = arrayList.get(i7);
        if (obj instanceof AbstractC2129d) {
            tVar = (AbstractC2129d) obj;
        } else if (obj instanceof String) {
            try {
                tVar = new t(((String) obj).getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e10) {
                throw new RuntimeException("UTF-8 not supported?", e10);
            }
        } else {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, length);
            tVar = new t(bArr2);
        }
        if (tVar != obj) {
            arrayList.set(i7, tVar);
        }
        return tVar;
    }

    @Override // p8.s
    public final void o(t tVar) {
        this.l.add(tVar);
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object remove(int i7) {
        Object objRemove = this.l.remove(i7);
        ((AbstractList) this).modCount++;
        if (objRemove instanceof String) {
            return (String) objRemove;
        }
        if (objRemove instanceof AbstractC2129d) {
            return ((AbstractC2129d) objRemove).y();
        }
        byte[] bArr = (byte[]) objRemove;
        byte[] bArr2 = AbstractC2141p.f20989a;
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e10) {
            throw new RuntimeException("UTF-8 not supported?", e10);
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i7, Object obj) {
        Object obj2 = this.l.set(i7, (String) obj);
        if (obj2 instanceof String) {
            return (String) obj2;
        }
        if (obj2 instanceof AbstractC2129d) {
            return ((AbstractC2129d) obj2).y();
        }
        byte[] bArr = (byte[]) obj2;
        byte[] bArr2 = AbstractC2141p.f20989a;
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e10) {
            throw new RuntimeException("UTF-8 not supported?", e10);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.l.size();
    }

    public C2143r(s sVar) {
        this.l = new ArrayList(sVar.size());
        addAll(sVar);
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i7, Collection collection) {
        if (collection instanceof s) {
            collection = ((s) collection).c();
        }
        boolean zAddAll = this.l.addAll(i7, collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }
}
