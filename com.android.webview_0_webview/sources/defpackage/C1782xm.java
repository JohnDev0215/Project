package defpackage;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: renamed from: xm, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1782xm extends AbstractList implements InterfaceC0209Ll, RandomAccess {
    public final InterfaceC0209Ll s;

    public C1782xm(InterfaceC0209Ll interfaceC0209Ll) {
        this.s = interfaceC0209Ll;
    }

    @Override // defpackage.InterfaceC0209Ll
    public InterfaceC0209Ll a() {
        return this;
    }

    @Override // defpackage.InterfaceC0209Ll
    public Object b(int i) {
        return this.s.b(i);
    }

    @Override // defpackage.InterfaceC0209Ll
    public void c(AbstractC0731el abstractC0731el) {
        throw new UnsupportedOperationException();
    }

    @Override // defpackage.InterfaceC0209Ll
    public List d() {
        return this.s.d();
    }

    @Override // java.util.AbstractList, java.util.List
    public Object get(int i) {
        return (String) this.s.get(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator iterator() {
        return new C1727wm(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator listIterator(int i) {
        return new C1672vm(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.s.size();
    }
}
