package e2;

import B2.C0074n;
import F0.E0;
import android.net.Uri;
import android.os.Bundle;
import d5.C1077F;
import d5.C1078G;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class F {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final F f13872g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f13873h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f13874i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f13875j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f13876k;
    public static final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f13877m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13878a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1192A f13879b;
    public final C1217z c;
    public final I d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C1214w f13880e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final B f13881f;

    static {
        C1212u c1212u = new C1212u();
        C1078G c1078g = d5.I.f13384m;
        d5.d0 d0Var = d5.d0.f13415p;
        Collections.emptyList();
        d5.d0 d0Var2 = d5.d0.f13415p;
        C1216y c1216y = new C1216y();
        f13872g = new F("", new C1214w(c1212u), null, new C1217z(c1216y), I.f13914K, B.d);
        f13873h = Integer.toString(0, 36);
        f13874i = Integer.toString(1, 36);
        f13875j = Integer.toString(2, 36);
        f13876k = Integer.toString(3, 36);
        l = Integer.toString(4, 36);
        f13877m = Integer.toString(5, 36);
    }

    public F(String str, C1214w c1214w, C1192A c1192a, C1217z c1217z, I i7, B b10) {
        this.f13878a = str;
        this.f13879b = c1192a;
        this.c = c1217z;
        this.d = i7;
        this.f13880e = c1214w;
        this.f13881f = b10;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static F a(Bundle bundle) {
        C1217z c1217z;
        C1214w c1214w;
        B b10;
        Map mapB;
        C1215x c1215x;
        d5.d0 d0VarH;
        d5.d0 d0VarR;
        String string = bundle.getString(f13873h, "");
        string.getClass();
        Bundle bundle2 = bundle.getBundle(f13874i);
        C1217z c1217zB = bundle2 == null ? C1217z.f14370f : C1217z.b(bundle2);
        Bundle bundle3 = bundle.getBundle(f13875j);
        I iB = bundle3 == null ? I.f13914K : I.b(bundle3);
        Bundle bundle4 = bundle.getBundle(f13876k);
        if (bundle4 == null) {
            c1214w = C1214w.f14354p;
            c1217z = c1217zB;
        } else {
            C1212u c1212u = new C1212u();
            C1213v c1213v = C1213v.f14343h;
            long jH = AbstractC1343A.H(bundle4.getLong(C1213v.f14344i, c1213v.f14349a));
            AbstractC1346c.c(jH >= 0);
            c1212u.f14340a = jH;
            long jH2 = AbstractC1343A.H(bundle4.getLong(C1213v.f14345j, c1213v.c));
            AbstractC1346c.c(jH2 == Long.MIN_VALUE || jH2 >= 0);
            c1212u.f14341b = jH2;
            c1212u.c = bundle4.getBoolean(C1213v.f14346k, c1213v.f14351e);
            c1212u.d = bundle4.getBoolean(C1213v.l, c1213v.f14352f);
            c1212u.f14342e = bundle4.getBoolean(C1213v.f14347m, c1213v.f14353g);
            String str = C1213v.f14348n;
            long j3 = c1213v.f14350b;
            c1217z = c1217zB;
            long j10 = bundle4.getLong(str, j3);
            if (j10 != j3) {
                AbstractC1346c.c(j10 >= 0);
                c1212u.f14340a = j10;
            }
            String str2 = C1213v.o;
            long j11 = c1213v.d;
            long j12 = bundle4.getLong(str2, j11);
            if (j12 != j11) {
                AbstractC1346c.c(j12 == Long.MIN_VALUE || j12 >= 0);
                c1212u.f14341b = j12;
            }
            c1214w = new C1214w(c1212u);
        }
        Bundle bundle5 = bundle.getBundle(l);
        if (bundle5 == null) {
            b10 = B.d;
        } else {
            l3.P p10 = new l3.P(22, false);
            p10.f18537m = (Uri) bundle5.getParcelable(B.f13851e);
            p10.f18538n = bundle5.getString(B.f13852f);
            p10.o = bundle5.getBundle(B.f13853g);
            b10 = new B(p10);
        }
        B b11 = b10;
        Bundle bundle6 = bundle.getBundle(f13877m);
        Object c1211t = null;
        if (bundle6 != null) {
            Bundle bundle7 = bundle6.getBundle(C1192A.f13841k);
            if (bundle7 == null) {
                c1215x = null;
            } else {
                String string2 = bundle7.getString(C1215x.f14355i);
                string2.getClass();
                UUID uuidFromString = UUID.fromString(string2);
                Uri uri = (Uri) bundle7.getParcelable(C1215x.f14356j);
                Bundle bundle8 = Bundle.EMPTY;
                Bundle bundle9 = bundle7.getBundle(C1215x.f14357k);
                if (bundle9 == null) {
                    bundle9 = bundle8;
                }
                if (bundle9 == bundle8) {
                    mapB = d5.i0.f13428r;
                } else {
                    HashMap map = new HashMap();
                    if (bundle9 != bundle8) {
                        for (String str3 : bundle9.keySet()) {
                            String string3 = bundle9.getString(str3);
                            if (string3 != null) {
                                map.put(str3, string3);
                            }
                        }
                    }
                    mapB = d5.K.b(map);
                }
                boolean z6 = bundle7.getBoolean(C1215x.l, false);
                boolean z10 = bundle7.getBoolean(C1215x.f14358m, false);
                boolean z11 = bundle7.getBoolean(C1215x.f14359n, false);
                ArrayList<Integer> arrayList = new ArrayList<>();
                ArrayList<Integer> integerArrayList = bundle7.getIntegerArrayList(C1215x.o);
                if (integerArrayList != null) {
                    arrayList = integerArrayList;
                }
                d5.I iW = d5.I.w(arrayList);
                byte[] byteArray = bundle7.getByteArray(C1215x.f14360p);
                E0 e02 = new E0();
                e02.d = uuidFromString;
                e02.f2016e = uri;
                e02.f2017f = d5.K.b(mapB);
                e02.f2014a = z6;
                e02.c = z11;
                e02.f2015b = z10;
                e02.f2018g = d5.I.w(iW);
                e02.f2019h = byteArray != null ? Arrays.copyOf(byteArray, byteArray.length) : null;
                c1215x = new C1215x(e02);
            }
            Bundle bundle10 = bundle6.getBundle(C1192A.l);
            if (bundle10 != null) {
                Uri uri2 = (Uri) bundle10.getParcelable(C1211t.f14338b);
                uri2.getClass();
                Q q4 = new Q();
                q4.f13998a = uri2;
                c1211t = new C1211t(q4);
            }
            C1211t c1211t2 = c1211t;
            ArrayList parcelableArrayList = bundle6.getParcelableArrayList(C1192A.f13842m);
            if (parcelableArrayList == null) {
                C1078G c1078g = d5.I.f13384m;
                d0VarH = d5.d0.f13415p;
            } else {
                C1077F c1077fU = d5.I.u();
                for (int i7 = 0; i7 < parcelableArrayList.size(); i7++) {
                    Bundle bundle11 = (Bundle) parcelableArrayList.get(i7);
                    bundle11.getClass();
                    c1077fU.a(new Z(bundle11.getInt(Z.o, 0), bundle11.getInt(Z.f14017p, 0), bundle11.getInt(Z.f14018q, 0)));
                }
                d0VarH = c1077fU.h();
            }
            d5.d0 d0Var = d0VarH;
            ArrayList parcelableArrayList2 = bundle6.getParcelableArrayList(C1192A.o);
            if (parcelableArrayList2 == null) {
                C1078G c1078g2 = d5.I.f13384m;
                d0VarR = d5.d0.f13415p;
            } else {
                d0VarR = AbstractC1346c.r(new C0074n(24), parcelableArrayList2);
            }
            d5.d0 d0Var2 = d0VarR;
            long j13 = bundle6.getLong(C1192A.f13844p, -9223372036854775807L);
            Uri uri3 = (Uri) bundle6.getParcelable(C1192A.f13839i);
            uri3.getClass();
            c1211t = new C1192A(uri3, bundle6.getString(C1192A.f13840j), c1215x, c1211t2, d0Var, bundle6.getString(C1192A.f13843n), d0Var2, j13);
        }
        return new F(string, c1214w, c1211t, c1217z, iB, b11);
    }

    public static F b(Uri uri) {
        C1192A c1192a;
        C1212u c1212u = new C1212u();
        E0 e02 = new E0();
        List listEmptyList = Collections.emptyList();
        d5.d0 d0Var = d5.d0.f13415p;
        C1216y c1216y = new C1216y();
        B b10 = B.d;
        AbstractC1346c.j(((Uri) e02.f2016e) == null || ((UUID) e02.d) != null);
        if (uri != null) {
            c1192a = new C1192A(uri, null, ((UUID) e02.d) != null ? new C1215x(e02) : null, null, listEmptyList, null, d0Var, -9223372036854775807L);
        } else {
            c1192a = null;
        }
        return new F("", new C1214w(c1212u), c1192a, new C1217z(c1216y), I.f13914K, b10);
    }

    public static F c(String str) {
        C1192A c1192a;
        C1212u c1212u = new C1212u();
        E0 e02 = new E0();
        List listEmptyList = Collections.emptyList();
        d5.d0 d0Var = d5.d0.f13415p;
        C1216y c1216y = new C1216y();
        B b10 = B.d;
        Uri uri = str == null ? null : Uri.parse(str);
        AbstractC1346c.j(((Uri) e02.f2016e) == null || ((UUID) e02.d) != null);
        if (uri != null) {
            c1192a = new C1192A(uri, null, ((UUID) e02.d) != null ? new C1215x(e02) : null, null, listEmptyList, null, d0Var, -9223372036854775807L);
        } else {
            c1192a = null;
        }
        return new F("", new C1214w(c1212u), c1192a, new C1217z(c1216y), I.f13914K, b10);
    }

    public final Bundle d(boolean z6) {
        C1192A c1192a;
        Bundle bundle = new Bundle();
        String str = this.f13878a;
        if (!str.equals("")) {
            bundle.putString(f13873h, str);
        }
        C1217z c1217z = C1217z.f14370f;
        C1217z c1217z2 = this.c;
        if (!c1217z2.equals(c1217z)) {
            bundle.putBundle(f13874i, c1217z2.c());
        }
        I i7 = I.f13914K;
        I i10 = this.d;
        if (!i10.equals(i7)) {
            bundle.putBundle(f13875j, i10.c());
        }
        C1213v c1213v = C1213v.f14343h;
        C1214w c1214w = this.f13880e;
        if (!c1214w.equals(c1213v)) {
            Bundle bundle2 = new Bundle();
            long j3 = c1213v.f14349a;
            long j10 = c1214w.f14349a;
            if (j10 != j3) {
                bundle2.putLong(C1213v.f14344i, j10);
            }
            long j11 = c1213v.c;
            long j12 = c1214w.c;
            if (j12 != j11) {
                bundle2.putLong(C1213v.f14345j, j12);
            }
            long j13 = c1213v.f14350b;
            long j14 = c1214w.f14350b;
            if (j14 != j13) {
                bundle2.putLong(C1213v.f14348n, j14);
            }
            long j15 = c1213v.d;
            long j16 = c1214w.d;
            if (j16 != j15) {
                bundle2.putLong(C1213v.o, j16);
            }
            boolean z10 = c1213v.f14351e;
            boolean z11 = c1214w.f14351e;
            if (z11 != z10) {
                bundle2.putBoolean(C1213v.f14346k, z11);
            }
            boolean z12 = c1213v.f14352f;
            boolean z13 = c1214w.f14352f;
            if (z13 != z12) {
                bundle2.putBoolean(C1213v.l, z13);
            }
            boolean z14 = c1213v.f14353g;
            boolean z15 = c1214w.f14353g;
            if (z15 != z14) {
                bundle2.putBoolean(C1213v.f14347m, z15);
            }
            bundle.putBundle(f13876k, bundle2);
        }
        B b10 = B.d;
        B b11 = this.f13881f;
        if (!b11.equals(b10)) {
            Bundle bundle3 = new Bundle();
            Uri uri = b11.f13854a;
            if (uri != null) {
                bundle3.putParcelable(B.f13851e, uri);
            }
            String str2 = b11.f13855b;
            if (str2 != null) {
                bundle3.putString(B.f13852f, str2);
            }
            Bundle bundle4 = b11.c;
            if (bundle4 != null) {
                bundle3.putBundle(B.f13853g, bundle4);
            }
            bundle.putBundle(l, bundle3);
        }
        if (z6 && (c1192a = this.f13879b) != null) {
            Bundle bundle5 = new Bundle();
            bundle5.putParcelable(C1192A.f13839i, c1192a.f13845a);
            String str3 = c1192a.f13846b;
            if (str3 != null) {
                bundle5.putString(C1192A.f13840j, str3);
            }
            C1215x c1215x = c1192a.c;
            if (c1215x != null) {
                Bundle bundle6 = new Bundle();
                bundle6.putString(C1215x.f14355i, c1215x.f14361a.toString());
                Uri uri2 = c1215x.f14362b;
                if (uri2 != null) {
                    bundle6.putParcelable(C1215x.f14356j, uri2);
                }
                d5.K k10 = c1215x.c;
                if (!k10.isEmpty()) {
                    Bundle bundle7 = new Bundle();
                    for (Map.Entry entry : k10.entrySet()) {
                        bundle7.putString((String) entry.getKey(), (String) entry.getValue());
                    }
                    bundle6.putBundle(C1215x.f14357k, bundle7);
                }
                boolean z16 = c1215x.d;
                if (z16) {
                    bundle6.putBoolean(C1215x.l, z16);
                }
                boolean z17 = c1215x.f14363e;
                if (z17) {
                    bundle6.putBoolean(C1215x.f14358m, z17);
                }
                boolean z18 = c1215x.f14364f;
                if (z18) {
                    bundle6.putBoolean(C1215x.f14359n, z18);
                }
                d5.I i11 = c1215x.f14365g;
                if (!i11.isEmpty()) {
                    bundle6.putIntegerArrayList(C1215x.o, new ArrayList<>(i11));
                }
                byte[] bArr = c1215x.f14366h;
                if (bArr != null) {
                    bundle6.putByteArray(C1215x.f14360p, bArr);
                }
                bundle5.putBundle(C1192A.f13841k, bundle6);
            }
            C1211t c1211t = c1192a.d;
            if (c1211t != null) {
                Bundle bundle8 = new Bundle();
                bundle8.putParcelable(C1211t.f14338b, c1211t.f14339a);
                bundle5.putBundle(C1192A.l, bundle8);
            }
            List list = c1192a.f13847e;
            if (!list.isEmpty()) {
                bundle5.putParcelableArrayList(C1192A.f13842m, AbstractC1346c.y(list, new C0074n(22)));
            }
            String str4 = c1192a.f13848f;
            if (str4 != null) {
                bundle5.putString(C1192A.f13843n, str4);
            }
            d5.I i12 = c1192a.f13849g;
            if (!i12.isEmpty()) {
                bundle5.putParcelableArrayList(C1192A.o, AbstractC1346c.y(i12, new C0074n(23)));
            }
            long j17 = c1192a.f13850h;
            if (j17 != -9223372036854775807L) {
                bundle5.putLong(C1192A.f13844p, j17);
            }
            bundle.putBundle(f13877m, bundle5);
        }
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof F)) {
            return false;
        }
        F f10 = (F) obj;
        String str = f10.f13878a;
        int i7 = AbstractC1343A.f15195a;
        return Objects.equals(this.f13878a, str) && this.f13880e.equals(f10.f13880e) && Objects.equals(this.f13879b, f10.f13879b) && Objects.equals(this.c, f10.c) && Objects.equals(this.d, f10.d) && Objects.equals(this.f13881f, f10.f13881f);
    }

    public final int hashCode() {
        int iHashCode = this.f13878a.hashCode() * 31;
        C1192A c1192a = this.f13879b;
        return this.f13881f.hashCode() + ((this.d.hashCode() + ((this.f13880e.hashCode() + ((this.c.hashCode() + ((iHashCode + (c1192a != null ? c1192a.hashCode() : 0)) * 31)) * 31)) * 31)) * 31);
    }
}
