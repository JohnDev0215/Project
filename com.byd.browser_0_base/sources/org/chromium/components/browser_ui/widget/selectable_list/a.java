package org.chromium.components.browser_ui.widget.selectable_list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.chromium.base.b;

/* JADX INFO: compiled from: SelectionDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public class a<E> {
    public boolean a;
    public boolean b;
    public Set<E> c = new LinkedHashSet();
    public final b<InterfaceC0128a<E>> d = new b<>();

    /* JADX INFO: renamed from: org.chromium.components.browser_ui.widget.selectable_list.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SelectionDelegate.java */
    public interface InterfaceC0128a<E> {
        void f(ArrayList arrayList);
    }

    public final void a(InterfaceC0128a<E> interfaceC0128a) {
        this.d.a(interfaceC0128a);
    }

    public final void b() {
        this.b = false;
        this.c.clear();
        f();
    }

    public final ArrayList c() {
        return new ArrayList(this.c);
    }

    public boolean d(E e) {
        return this.c.contains(e);
    }

    public boolean e() {
        return !this.c.isEmpty() || this.b;
    }

    public final void f() {
        ArrayList arrayListC = c();
        Iterator<InterfaceC0128a<E>> it = this.d.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((InterfaceC0128a) aVar.next()).f(arrayListC);
            }
        }
    }

    public final void g(InterfaceC0128a<E> interfaceC0128a) {
        this.d.c(interfaceC0128a);
    }

    public final void h(Set<E> set) {
        this.c = set;
        f();
    }

    public boolean i(E e) {
        if (this.c.contains(e)) {
            this.c.remove(e);
        } else {
            if (this.a) {
                this.c.clear();
            }
            this.c.add(e);
        }
        if (this.c.isEmpty()) {
            this.b = false;
        }
        f();
        return d(e);
    }
}
