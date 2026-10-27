package B8;

import Q7.N;
import j8.C1565j;
import l8.AbstractC1783a;
import l8.InterfaceC1788f;

/* JADX INFO: renamed from: B8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0092d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC1788f f755a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1565j f756b;
    public final AbstractC1783a c;
    public final N d;

    public C0092d(InterfaceC1788f interfaceC1788f, C1565j c1565j, AbstractC1783a abstractC1783a, N n10) {
        A7.m.f(interfaceC1788f, "nameResolver");
        A7.m.f(c1565j, "classProto");
        A7.m.f(n10, "sourceElement");
        this.f755a = interfaceC1788f;
        this.f756b = c1565j;
        this.c = abstractC1783a;
        this.d = n10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0092d)) {
            return false;
        }
        C0092d c0092d = (C0092d) obj;
        return A7.m.a(this.f755a, c0092d.f755a) && A7.m.a(this.f756b, c0092d.f756b) && A7.m.a(this.c, c0092d.c) && A7.m.a(this.d, c0092d.d);
    }

    public final int hashCode() {
        return this.d.hashCode() + ((this.c.hashCode() + ((this.f756b.hashCode() + (this.f755a.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "ClassData(nameResolver=" + this.f755a + ", classProto=" + this.f756b + ", metadataVersion=" + this.c + ", sourceElement=" + this.d + ')';
    }
}
