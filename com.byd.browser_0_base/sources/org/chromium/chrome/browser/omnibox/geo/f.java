package org.chromium.chrome.browser.omnibox.geo;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import org.chromium.chrome.browser.omnibox.geo.c;

/* JADX INFO: compiled from: VisibleNetworks.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f {
    public final b a;
    public final a b;
    public final Set<b> c;
    public final Set<a> d;

    /* JADX INFO: compiled from: VisibleNetworks.java */
    public static class a {
        public static final a j = a(0).a();
        public static final a k = a(1).a();
        public final int a;
        public final Integer b;
        public final Integer c;
        public final Integer d;
        public final Integer e;
        public final Integer f;
        public final Integer g;
        public final Integer h;
        public final Long i;

        /* JADX INFO: renamed from: org.chromium.chrome.browser.omnibox.geo.f$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: VisibleNetworks.java */
        public static class C0100a {
            public int a;
            public Integer b;
            public Integer c;
            public Integer d;
            public Integer e;
            public Integer f;
            public Integer g;
            public Integer h;
            public Long i;

            public final a a() {
                return new a(this);
            }
        }

        public a(C0100a c0100a) {
            this.a = c0100a.a;
            this.b = c0100a.b;
            this.c = c0100a.c;
            this.d = c0100a.d;
            this.e = c0100a.e;
            this.f = c0100a.f;
            this.g = c0100a.g;
            this.h = c0100a.h;
            this.i = c0100a.i;
        }

        public static C0100a a(int i) {
            C0100a c0100a = new C0100a();
            c0100a.a = i;
            return c0100a;
        }

        public final c b(boolean z) {
            c.a aVarU = c.U();
            c.b.a aVarY = c.b.Y();
            int i = this.a;
            if (i == 2) {
                aVarY.B(4);
            } else if (i == 3) {
                aVarY.B(2);
            } else if (i == 4) {
                aVarY.B(3);
            } else if (i != 5) {
                aVarY.B(1);
            } else {
                aVarY.B(5);
            }
            Integer num = this.b;
            if (num != null) {
                int iIntValue = num.intValue();
                aVarY.y();
                c.b.O((c.b) aVarY.f, iIntValue);
            }
            Integer num2 = this.c;
            if (num2 != null) {
                int iIntValue2 = num2.intValue();
                aVarY.y();
                c.b.P((c.b) aVarY.f, iIntValue2);
            }
            Integer num3 = this.d;
            if (num3 != null) {
                int iIntValue3 = num3.intValue();
                aVarY.y();
                c.b.Q((c.b) aVarY.f, iIntValue3);
            }
            Integer num4 = this.e;
            if (num4 != null) {
                int iIntValue4 = num4.intValue();
                aVarY.y();
                c.b.S((c.b) aVarY.f, iIntValue4);
            }
            Integer num5 = this.f;
            if (num5 != null) {
                int iIntValue5 = num5.intValue();
                aVarY.y();
                c.b.U((c.b) aVarY.f, iIntValue5);
            }
            Integer num6 = this.g;
            if (num6 != null) {
                int iIntValue6 = num6.intValue();
                aVarY.y();
                c.b.T((c.b) aVarY.f, iIntValue6);
            }
            Integer num7 = this.h;
            if (num7 != null) {
                int iIntValue7 = num7.intValue();
                aVarY.y();
                c.b.V((c.b) aVarY.f, iIntValue7);
            }
            c.b bVarBuild = aVarY.build();
            aVarU.y();
            c.O((c) aVarU.f, bVarBuild);
            Long l = this.i;
            if (l != null) {
                long jLongValue = l.longValue();
                aVarU.y();
                c.Q((c) aVarU.f, jLongValue);
            }
            aVarU.y();
            c.P((c) aVarU.f, z);
            return aVarU.build();
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return Objects.equals(Integer.valueOf(this.a), Integer.valueOf(aVar.a)) && Objects.equals(this.b, aVar.b) && Objects.equals(this.c, aVar.c) && Objects.equals(this.d, aVar.d) && Objects.equals(this.e, aVar.e) && Objects.equals(this.f, aVar.f) && Objects.equals(this.g, aVar.g) && Objects.equals(this.h, aVar.h);
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{Integer.valueOf(this.a), this.b, this.c, this.d, this.e, this.f, this.g, this.h});
        }
    }

    /* JADX INFO: compiled from: VisibleNetworks.java */
    public static class b {
        public static final b e = new b(null, null, null, null);
        public final String a;
        public final String b;
        public final Integer c;
        public final Long d;

        public b(String str, String str2, Integer num, Long l) {
            this.a = str;
            this.b = str2;
            this.c = num;
            this.d = l;
        }

        public final c a(boolean z) {
            c.a aVarU = c.U();
            c.C0099c.a aVarS = c.C0099c.S();
            String str = this.b;
            if (str != null) {
                aVarS.y();
                c.C0099c.O((c.C0099c) aVarS.f, str);
            }
            Integer num = this.c;
            if (num != null) {
                int iIntValue = num.intValue();
                aVarS.y();
                c.C0099c.P((c.C0099c) aVarS.f, iIntValue);
            }
            c.C0099c c0099cBuild = aVarS.build();
            aVarU.y();
            c.S((c) aVarU.f, c0099cBuild);
            Long l = this.d;
            if (l != null) {
                long jLongValue = l.longValue();
                aVarU.y();
                c.Q((c) aVarU.f, jLongValue);
            }
            aVarU.y();
            c.P((c) aVarU.f, z);
            return aVarU.build();
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return Objects.equals(this.a, bVar.a) && Objects.equals(this.b, bVar.b);
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.a, this.b});
        }
    }

    public f(b bVar, a aVar, Set<b> set, Set<a> set2) {
        this.a = bVar;
        this.b = aVar;
        this.c = set;
        this.d = set2;
    }

    public final boolean a() {
        Set<b> set;
        Set<a> set2;
        return this.a == null && this.b == null && ((set = this.c) == null || set.size() == 0) && ((set2 = this.d) == null || set2.size() == 0);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return Objects.equals(this.a, fVar.a) && Objects.equals(this.b, fVar.b) && Objects.equals(this.c, fVar.c) && Objects.equals(this.d, fVar.d);
    }

    public final int hashCode() {
        Object[] objArr = new Object[4];
        objArr[0] = this.a;
        objArr[1] = this.b;
        Set<b> set = this.c;
        objArr[2] = Integer.valueOf(set != null ? set.hashCode() : 0);
        Set<a> set2 = this.d;
        objArr[3] = Integer.valueOf(set2 != null ? set2.hashCode() : 0);
        return Arrays.hashCode(objArr);
    }
}
