package org.chromium.components.offline_items_collection;

import defpackage.ny;
import java.util.Objects;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public final class OfflineItem implements Cloneable {
    public boolean B;
    public boolean C;
    public long D;
    public a E;
    public long F;
    public boolean G;
    public int H;
    public int I;
    public String f;
    public String g;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public long o;
    public boolean p;
    public long q;
    public long r;
    public long s;
    public boolean t;
    public String u;
    public String v;
    public GURL w;
    public GURL x;
    public boolean y;
    public String z;
    public ny e = new ny();
    public int h = 5;
    public int A = 2;

    public static class a {
        public final long a;
        public final Long b;
        public final int c;

        public a(long j, Long l, int i) {
            this.a = j;
            this.b = l;
            this.c = i;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && this.c == aVar.c && Objects.equals(this.b, aVar.b);
        }

        public final int hashCode() {
            int i = ((int) this.a) * 31;
            Long l = this.b;
            return ((i + (l == null ? 0 : l.hashCode())) * 31) + this.c;
        }
    }

    public final Object clone() {
        OfflineItem offlineItem = new OfflineItem();
        ny nyVar = this.e;
        offlineItem.e = nyVar == null ? null : new ny(nyVar.a, nyVar.b);
        offlineItem.f = this.f;
        offlineItem.g = this.g;
        offlineItem.h = this.h;
        offlineItem.i = this.i;
        offlineItem.j = this.j;
        offlineItem.k = this.k;
        offlineItem.l = this.l;
        offlineItem.o = this.o;
        offlineItem.p = this.p;
        offlineItem.q = this.q;
        offlineItem.r = this.r;
        offlineItem.s = this.s;
        offlineItem.t = this.t;
        offlineItem.u = this.u;
        offlineItem.v = this.v;
        offlineItem.m = this.m;
        offlineItem.n = this.n;
        offlineItem.w = this.w;
        offlineItem.x = this.x;
        offlineItem.y = this.y;
        offlineItem.z = this.z;
        offlineItem.A = this.A;
        offlineItem.B = this.B;
        offlineItem.C = this.C;
        offlineItem.D = this.D;
        offlineItem.F = this.F;
        offlineItem.H = this.H;
        offlineItem.I = this.I;
        a aVar = this.E;
        if (aVar != null) {
            offlineItem.E = new a(aVar.a, aVar.b, aVar.c);
        }
        return offlineItem;
    }
}
