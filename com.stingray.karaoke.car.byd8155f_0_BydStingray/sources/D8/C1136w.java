package d8;

import F8.AbstractC0287w;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: d8.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1136w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC0287w f13628a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f13629b;
    public final ArrayList c;
    public final List d;

    public C1136w(AbstractC0287w abstractC0287w, List list, ArrayList arrayList, List list2) {
        this.f13628a = abstractC0287w;
        this.f13629b = list;
        this.c = arrayList;
        this.d = list2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1136w)) {
            return false;
        }
        C1136w c1136w = (C1136w) obj;
        return this.f13628a.equals(c1136w.f13628a) && A7.m.a(null, null) && this.f13629b.equals(c1136w.f13629b) && this.c.equals(c1136w.c) && this.d.equals(c1136w.d);
    }

    public final int hashCode() {
        return this.d.hashCode() + ((this.c.hashCode() + ((this.f13629b.hashCode() + (this.f13628a.hashCode() * 961)) * 31)) * 961);
    }

    public final String toString() {
        return "MethodSignatureData(returnType=" + this.f13628a + ", receiverType=null, valueParameters=" + this.f13629b + ", typeParameters=" + this.c + ", hasStableParameterNames=false, errors=" + this.d + ')';
    }
}
