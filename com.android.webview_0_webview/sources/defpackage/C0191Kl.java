package defpackage;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: renamed from: Kl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0191Kl extends AbstractC0351Tk implements InterfaceC0209Ll, RandomAccess {
    public static final C0191Kl u;
    public final List t;

    static {
        C0191Kl c0191Kl = new C0191Kl(10);
        u = c0191Kl;
        c0191Kl.s = false;
    }

    public C0191Kl(int i) {
        this.t = new ArrayList(i);
    }

    public static String f(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof AbstractC0731el ? ((AbstractC0731el) obj).m() : AbstractC0119Gl.h((byte[]) obj);
    }

    @Override // defpackage.InterfaceC0209Ll
    public InterfaceC0209Ll a() {
        return this.s ? new C1782xm(this) : this;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, Object obj) {
        e();
        this.t.add(i, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }

    @Override // defpackage.InterfaceC0209Ll
    public Object b(int i) {
        return this.t.get(i);
    }

    @Override // defpackage.InterfaceC0209Ll
    public void c(AbstractC0731el abstractC0731el) {
        e();
        this.t.add(abstractC0731el);
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        e();
        this.t.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // defpackage.InterfaceC0209Ll
    public List d() {
        return Collections.unmodifiableList(this.t);
    }

    public InterfaceC0101Fl g(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.t);
        return new C0191Kl(arrayList);
    }

    @Override // java.util.AbstractList, java.util.List
    public Object get(int i) {
        String strH;
        Object obj = this.t.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof AbstractC0731el) {
            AbstractC0731el abstractC0731el = (AbstractC0731el) obj;
            strH = abstractC0731el.m();
            C0620cl c0620cl = (C0620cl) abstractC0731el;
            int iO = c0620cl.o();
            if (AbstractC0174Jm.a.c(0, c0620cl.v, iO, c0620cl.size() + iO) == 0) {
                this.t.set(i, strH);
            }
        } else {
            byte[] bArr = (byte[]) obj;
            strH = AbstractC0119Gl.h(bArr);
            if (AbstractC0174Jm.a.c(0, bArr, 0, bArr.length) == 0) {
                this.t.set(i, strH);
            }
        }
        return strH;
    }

    @Override // java.util.AbstractList, java.util.List
    public Object remove(int i) {
        e();
        Object objRemove = this.t.remove(i);
        ((AbstractList) this).modCount++;
        return f(objRemove);
    }

    @Override // java.util.AbstractList, java.util.List
    public Object set(int i, Object obj) {
        e();
        return f(this.t.set(i, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.t.size();
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i, Collection collection) {
        e();
        if (collection instanceof InterfaceC0209Ll) {
            collection = ((InterfaceC0209Ll) collection).d();
        }
        boolean zAddAll = this.t.addAll(i, collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }

    public C0191Kl(ArrayList arrayList) {
        this.t = arrayList;
    }
}
