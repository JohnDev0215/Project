package e2;

import B2.C0074n;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import d5.AbstractC1095q;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: renamed from: e2.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1208p {

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public static final C1208p f14259N = new C1208p(new C1207o());

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public static final String f14260O = Integer.toString(0, 36);

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public static final String f14261P = Integer.toString(1, 36);

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public static final String f14262Q = Integer.toString(2, 36);

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public static final String f14263R = Integer.toString(3, 36);

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public static final String f14264S = Integer.toString(4, 36);

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public static final String f14265T = Integer.toString(5, 36);

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public static final String f14266U = Integer.toString(6, 36);

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public static final String f14267V = Integer.toString(7, 36);

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public static final String f14268W = Integer.toString(8, 36);

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public static final String f14269X = Integer.toString(9, 36);

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public static final String f14270Y = Integer.toString(10, 36);

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public static final String f14271Z = Integer.toString(11, 36);

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public static final String f14272a0 = Integer.toString(12, 36);

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public static final String f14273b0 = Integer.toString(13, 36);

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public static final String f14274c0 = Integer.toString(14, 36);

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public static final String f14275d0 = Integer.toString(15, 36);

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public static final String f14276e0 = Integer.toString(16, 36);

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public static final String f14277f0 = Integer.toString(17, 36);

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public static final String f14278g0 = Integer.toString(18, 36);

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public static final String f14279h0 = Integer.toString(19, 36);

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public static final String f14280i0 = Integer.toString(20, 36);

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public static final String f14281j0 = Integer.toString(21, 36);

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public static final String f14282k0 = Integer.toString(22, 36);

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public static final String f14283l0 = Integer.toString(23, 36);

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public static final String f14284m0 = Integer.toString(24, 36);

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public static final String f14285n0 = Integer.toString(25, 36);

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public static final String f14286o0 = Integer.toString(26, 36);

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public static final String f14287p0 = Integer.toString(27, 36);

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public static final String f14288q0 = Integer.toString(28, 36);

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public static final String f14289r0 = Integer.toString(29, 36);

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public static final String f14290s0 = Integer.toString(30, 36);

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public static final String f14291t0 = Integer.toString(31, 36);

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public static final String f14292u0 = Integer.toString(32, 36);

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public static final String f14293v0 = Integer.toString(33, 36);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final int f14294A;
    public final C1200h B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final int f14295C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final int f14296D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final int f14297E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final int f14298F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final int f14299G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final int f14300H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final int f14301I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final int f14302J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final int f14303K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final int f14304L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public int f14305M;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f14306a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f14307b;
    public final d5.I c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f14308e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f14309f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f14310g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f14311h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f14312i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f14313j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final String f14314k;
    public final K l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f14315m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f14316n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f14317p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final List f14318q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C1204l f14319r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final long f14320s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final boolean f14321t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f14322u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f14323v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final float f14324w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f14325x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final float f14326y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final byte[] f14327z;

    public C1208p(C1207o c1207o) {
        boolean z6;
        String str;
        this.f14306a = c1207o.f14237a;
        String strI = AbstractC1343A.I(c1207o.d);
        this.d = strI;
        if (c1207o.c.isEmpty() && c1207o.f14238b != null) {
            this.c = d5.I.B(new C1210s(strI, c1207o.f14238b));
            this.f14307b = c1207o.f14238b;
        } else if (!c1207o.c.isEmpty() && c1207o.f14238b == null) {
            d5.I i7 = c1207o.c;
            this.c = i7;
            Iterator it = i7.iterator();
            while (true) {
                if (!it.hasNext()) {
                    str = ((C1210s) i7.get(0)).f14337b;
                    break;
                }
                C1210s c1210s = (C1210s) it.next();
                if (TextUtils.equals(c1210s.f14336a, strI)) {
                    str = c1210s.f14337b;
                    break;
                }
            }
            this.f14307b = str;
        } else if (c1207o.c.isEmpty() && c1207o.f14238b == null) {
            z6 = true;
            AbstractC1346c.j(z6);
            this.c = c1207o.c;
            this.f14307b = c1207o.f14238b;
        } else {
            for (int i10 = 0; i10 < c1207o.c.size(); i10++) {
                if (((C1210s) c1207o.c.get(i10)).f14337b.equals(c1207o.f14238b)) {
                    z6 = true;
                    break;
                }
            }
            z6 = false;
            AbstractC1346c.j(z6);
            this.c = c1207o.c;
            this.f14307b = c1207o.f14238b;
        }
        this.f14308e = c1207o.f14239e;
        AbstractC1346c.i("Auxiliary track type must only be set to a value other than AUXILIARY_TRACK_TYPE_UNDEFINED only when ROLE_FLAG_AUXILIARY is set", c1207o.f14241g == 0 || (c1207o.f14240f & 32768) != 0);
        this.f14309f = c1207o.f14240f;
        this.f14310g = c1207o.f14241g;
        int i11 = c1207o.f14242h;
        this.f14311h = i11;
        int i12 = c1207o.f14243i;
        this.f14312i = i12;
        this.f14313j = i12 != -1 ? i12 : i11;
        this.f14314k = c1207o.f14244j;
        this.l = c1207o.f14245k;
        this.f14315m = c1207o.l;
        this.f14316n = c1207o.f14246m;
        this.o = c1207o.f14247n;
        this.f14317p = c1207o.o;
        List list = c1207o.f14248p;
        this.f14318q = list == null ? Collections.emptyList() : list;
        C1204l c1204l = c1207o.f14249q;
        this.f14319r = c1204l;
        this.f14320s = c1207o.f14250r;
        this.f14321t = c1207o.f14251s;
        this.f14322u = c1207o.f14252t;
        this.f14323v = c1207o.f14253u;
        this.f14324w = c1207o.f14254v;
        int i13 = c1207o.f14255w;
        this.f14325x = i13 == -1 ? 0 : i13;
        float f10 = c1207o.f14256x;
        this.f14326y = f10 == -1.0f ? 1.0f : f10;
        this.f14327z = c1207o.f14257y;
        this.f14294A = c1207o.f14258z;
        this.B = c1207o.f14227A;
        this.f14295C = c1207o.B;
        this.f14296D = c1207o.f14228C;
        this.f14297E = c1207o.f14229D;
        int i14 = c1207o.f14230E;
        this.f14298F = i14 == -1 ? 0 : i14;
        int i15 = c1207o.f14231F;
        this.f14299G = i15 != -1 ? i15 : 0;
        this.f14300H = c1207o.f14232G;
        this.f14301I = c1207o.f14233H;
        this.f14302J = c1207o.f14234I;
        this.f14303K = c1207o.f14235J;
        int i16 = c1207o.f14236K;
        if (i16 != 0 || c1204l == null) {
            this.f14304L = i16;
        } else {
            this.f14304L = 1;
        }
    }

    public static String e(C1208p c1208p) {
        String str;
        String str2;
        int i7;
        if (c1208p == null) {
            return "null";
        }
        B2.q qVar = new B2.q(String.valueOf(','));
        StringBuilder sbI = M5.b.i("id=");
        sbI.append(c1208p.f14306a);
        sbI.append(", mimeType=");
        sbI.append(c1208p.f14316n);
        String str3 = c1208p.f14315m;
        if (str3 != null) {
            sbI.append(", container=");
            sbI.append(str3);
        }
        int i10 = c1208p.f14313j;
        if (i10 != -1) {
            sbI.append(", bitrate=");
            sbI.append(i10);
        }
        String str4 = c1208p.f14314k;
        if (str4 != null) {
            sbI.append(", codecs=");
            sbI.append(str4);
        }
        C1204l c1204l = c1208p.f14319r;
        if (c1204l != null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (int i11 = 0; i11 < c1204l.o; i11++) {
                UUID uuid = c1204l.l[i11].f14134m;
                if (uuid.equals(AbstractC1199g.f14100b)) {
                    linkedHashSet.add("cenc");
                } else if (uuid.equals(AbstractC1199g.c)) {
                    linkedHashSet.add("clearkey");
                } else if (uuid.equals(AbstractC1199g.f14101e)) {
                    linkedHashSet.add("playready");
                } else if (uuid.equals(AbstractC1199g.d)) {
                    linkedHashSet.add("widevine");
                } else if (uuid.equals(AbstractC1199g.f14099a)) {
                    linkedHashSet.add("universal");
                } else {
                    linkedHashSet.add("unknown (" + uuid + ")");
                }
            }
            sbI.append(", drm=[");
            qVar.b(sbI, linkedHashSet.iterator());
            sbI.append(']');
        }
        int i12 = c1208p.f14322u;
        if (i12 != -1 && (i7 = c1208p.f14323v) != -1) {
            sbI.append(", res=");
            sbI.append(i12);
            sbI.append("x");
            sbI.append(i7);
        }
        C1200h c1200h = c1208p.B;
        if (c1200h != null) {
            int i13 = c1200h.f14116f;
            int i14 = c1200h.f14115e;
            if ((i14 != -1 && i13 != -1) || c1200h.d()) {
                sbI.append(", color=");
                if (c1200h.d()) {
                    String strB = C1200h.b(c1200h.f14113a);
                    String strA = C1200h.a(c1200h.f14114b);
                    String strC = C1200h.c(c1200h.c);
                    int i15 = AbstractC1343A.f15195a;
                    Locale locale = Locale.US;
                    str2 = strB + "/" + strA + "/" + strC;
                } else {
                    str2 = "NA/NA/NA";
                }
                sbI.append(str2 + "/" + ((i14 == -1 || i13 == -1) ? "NA/NA" : i14 + "/" + i13));
            }
        }
        float f10 = c1208p.f14324w;
        if (f10 != -1.0f) {
            sbI.append(", fps=");
            sbI.append(f10);
        }
        int i16 = c1208p.f14295C;
        if (i16 != -1) {
            sbI.append(", channels=");
            sbI.append(i16);
        }
        int i17 = c1208p.f14296D;
        if (i17 != -1) {
            sbI.append(", sample_rate=");
            sbI.append(i17);
        }
        String str5 = c1208p.d;
        if (str5 != null) {
            sbI.append(", language=");
            sbI.append(str5);
        }
        d5.I i18 = c1208p.c;
        if (!i18.isEmpty()) {
            sbI.append(", labels=[");
            qVar.b(sbI, AbstractC1095q.x(new C0074n(21), i18).iterator());
            sbI.append("]");
        }
        int i19 = c1208p.f14308e;
        if (i19 != 0) {
            sbI.append(", selectionFlags=[");
            int i20 = AbstractC1343A.f15195a;
            ArrayList arrayList = new ArrayList();
            if ((i19 & 4) != 0) {
                arrayList.add("auto");
            }
            if ((i19 & 1) != 0) {
                arrayList.add("default");
            }
            if ((i19 & 2) != 0) {
                arrayList.add("forced");
            }
            qVar.b(sbI, arrayList.iterator());
            sbI.append("]");
        }
        int i21 = c1208p.f14309f;
        if (i21 != 0) {
            sbI.append(", roleFlags=[");
            int i22 = AbstractC1343A.f15195a;
            ArrayList arrayList2 = new ArrayList();
            if ((i21 & 1) != 0) {
                arrayList2.add("main");
            }
            if ((i21 & 2) != 0) {
                arrayList2.add("alt");
            }
            if ((i21 & 4) != 0) {
                arrayList2.add("supplementary");
            }
            if ((i21 & 8) != 0) {
                arrayList2.add("commentary");
            }
            if ((i21 & 16) != 0) {
                arrayList2.add("dub");
            }
            if ((i21 & 32) != 0) {
                arrayList2.add("emergency");
            }
            if ((i21 & 64) != 0) {
                arrayList2.add("caption");
            }
            if ((i21 & 128) != 0) {
                arrayList2.add("subtitle");
            }
            if ((i21 & 256) != 0) {
                arrayList2.add("sign");
            }
            if ((i21 & 512) != 0) {
                arrayList2.add("describes-video");
            }
            if ((i21 & 1024) != 0) {
                arrayList2.add("describes-music");
            }
            if ((i21 & 2048) != 0) {
                arrayList2.add("enhanced-intelligibility");
            }
            if ((i21 & 4096) != 0) {
                arrayList2.add("transcribes-dialog");
            }
            if ((i21 & 8192) != 0) {
                arrayList2.add("easy-read");
            }
            if ((i21 & 16384) != 0) {
                arrayList2.add("trick-play");
            }
            if ((i21 & 32768) != 0) {
                arrayList2.add("auxiliary");
            }
            qVar.b(sbI, arrayList2.iterator());
            sbI.append("]");
        }
        if ((i21 & 32768) != 0) {
            sbI.append(", auxiliaryTrackType=");
            int i23 = AbstractC1343A.f15195a;
            int i24 = c1208p.f14310g;
            if (i24 == 0) {
                str = "undefined";
            } else if (i24 == 1) {
                str = "original";
            } else if (i24 == 2) {
                str = "depth-linear";
            } else if (i24 == 3) {
                str = "depth-inverse";
            } else {
                if (i24 != 4) {
                    throw new IllegalStateException("Unsupported auxiliary track type");
                }
                str = "depth metadata";
            }
            sbI.append(str);
        }
        return sbI.toString();
    }

    public final C1207o a() {
        C1207o c1207o = new C1207o();
        c1207o.f14237a = this.f14306a;
        c1207o.f14238b = this.f14307b;
        c1207o.c = this.c;
        c1207o.d = this.d;
        c1207o.f14239e = this.f14308e;
        c1207o.f14240f = this.f14309f;
        c1207o.f14242h = this.f14311h;
        c1207o.f14243i = this.f14312i;
        c1207o.f14244j = this.f14314k;
        c1207o.f14245k = this.l;
        c1207o.l = this.f14315m;
        c1207o.f14246m = this.f14316n;
        c1207o.f14247n = this.o;
        c1207o.o = this.f14317p;
        c1207o.f14248p = this.f14318q;
        c1207o.f14249q = this.f14319r;
        c1207o.f14250r = this.f14320s;
        c1207o.f14251s = this.f14321t;
        c1207o.f14252t = this.f14322u;
        c1207o.f14253u = this.f14323v;
        c1207o.f14254v = this.f14324w;
        c1207o.f14255w = this.f14325x;
        c1207o.f14256x = this.f14326y;
        c1207o.f14257y = this.f14327z;
        c1207o.f14258z = this.f14294A;
        c1207o.f14227A = this.B;
        c1207o.B = this.f14295C;
        c1207o.f14228C = this.f14296D;
        c1207o.f14229D = this.f14297E;
        c1207o.f14230E = this.f14298F;
        c1207o.f14231F = this.f14299G;
        c1207o.f14232G = this.f14300H;
        c1207o.f14233H = this.f14301I;
        c1207o.f14234I = this.f14302J;
        c1207o.f14235J = this.f14303K;
        c1207o.f14236K = this.f14304L;
        return c1207o;
    }

    public final int b() {
        int i7;
        int i10 = this.f14322u;
        if (i10 == -1 || (i7 = this.f14323v) == -1) {
            return -1;
        }
        return i10 * i7;
    }

    public final boolean c(C1208p c1208p) {
        List list = this.f14318q;
        if (list.size() != c1208p.f14318q.size()) {
            return false;
        }
        for (int i7 = 0; i7 < list.size(); i7++) {
            if (!Arrays.equals((byte[]) list.get(i7), (byte[]) c1208p.f14318q.get(i7))) {
                return false;
            }
        }
        return true;
    }

    public final Bundle d(boolean z6) {
        Bundle bundle = new Bundle();
        bundle.putString(f14260O, this.f14306a);
        bundle.putString(f14261P, this.f14307b);
        d5.I<C1210s> i7 = this.c;
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(i7.size());
        for (C1210s c1210s : i7) {
            c1210s.getClass();
            Bundle bundle2 = new Bundle();
            String str = c1210s.f14336a;
            if (str != null) {
                bundle2.putString(C1210s.c, str);
            }
            bundle2.putString(C1210s.d, c1210s.f14337b);
            arrayList.add(bundle2);
        }
        bundle.putParcelableArrayList(f14292u0, arrayList);
        bundle.putString(f14262Q, this.d);
        bundle.putInt(f14263R, this.f14308e);
        bundle.putInt(f14264S, this.f14309f);
        int i10 = f14259N.f14310g;
        int i11 = this.f14310g;
        if (i11 != i10) {
            bundle.putInt(f14293v0, i11);
        }
        bundle.putInt(f14265T, this.f14311h);
        bundle.putInt(f14266U, this.f14312i);
        bundle.putString(f14267V, this.f14314k);
        if (!z6) {
            bundle.putParcelable(f14268W, this.l);
        }
        bundle.putString(f14269X, this.f14315m);
        bundle.putString(f14270Y, this.f14316n);
        bundle.putInt(f14271Z, this.o);
        int i12 = 0;
        while (true) {
            List list = this.f14318q;
            if (i12 >= list.size()) {
                break;
            }
            bundle.putByteArray(f14272a0 + "_" + Integer.toString(i12, 36), (byte[]) list.get(i12));
            i12++;
        }
        bundle.putParcelable(f14273b0, this.f14319r);
        bundle.putLong(f14274c0, this.f14320s);
        bundle.putInt(f14275d0, this.f14322u);
        bundle.putInt(f14276e0, this.f14323v);
        bundle.putFloat(f14277f0, this.f14324w);
        bundle.putInt(f14278g0, this.f14325x);
        bundle.putFloat(f14279h0, this.f14326y);
        bundle.putByteArray(f14280i0, this.f14327z);
        bundle.putInt(f14281j0, this.f14294A);
        C1200h c1200h = this.B;
        if (c1200h != null) {
            Bundle bundle3 = new Bundle();
            bundle3.putInt(C1200h.f14108i, c1200h.f14113a);
            bundle3.putInt(C1200h.f14109j, c1200h.f14114b);
            bundle3.putInt(C1200h.f14110k, c1200h.c);
            bundle3.putByteArray(C1200h.l, c1200h.d);
            bundle3.putInt(C1200h.f14111m, c1200h.f14115e);
            bundle3.putInt(C1200h.f14112n, c1200h.f14116f);
            bundle.putBundle(f14282k0, bundle3);
        }
        bundle.putInt(f14283l0, this.f14295C);
        bundle.putInt(f14284m0, this.f14296D);
        bundle.putInt(f14285n0, this.f14297E);
        bundle.putInt(f14286o0, this.f14298F);
        bundle.putInt(f14287p0, this.f14299G);
        bundle.putInt(f14288q0, this.f14300H);
        bundle.putInt(f14290s0, this.f14302J);
        bundle.putInt(f14291t0, this.f14303K);
        bundle.putInt(f14289r0, this.f14304L);
        return bundle;
    }

    public final boolean equals(Object obj) {
        int i7;
        if (this == obj) {
            return true;
        }
        if (obj == null || C1208p.class != obj.getClass()) {
            return false;
        }
        C1208p c1208p = (C1208p) obj;
        int i10 = this.f14305M;
        if (i10 == 0 || (i7 = c1208p.f14305M) == 0 || i10 == i7) {
            return this.f14308e == c1208p.f14308e && this.f14309f == c1208p.f14309f && this.f14310g == c1208p.f14310g && this.f14311h == c1208p.f14311h && this.f14312i == c1208p.f14312i && this.o == c1208p.o && this.f14320s == c1208p.f14320s && this.f14322u == c1208p.f14322u && this.f14323v == c1208p.f14323v && this.f14325x == c1208p.f14325x && this.f14294A == c1208p.f14294A && this.f14295C == c1208p.f14295C && this.f14296D == c1208p.f14296D && this.f14297E == c1208p.f14297E && this.f14298F == c1208p.f14298F && this.f14299G == c1208p.f14299G && this.f14300H == c1208p.f14300H && this.f14302J == c1208p.f14302J && this.f14303K == c1208p.f14303K && this.f14304L == c1208p.f14304L && Float.compare(this.f14324w, c1208p.f14324w) == 0 && Float.compare(this.f14326y, c1208p.f14326y) == 0 && Objects.equals(this.f14306a, c1208p.f14306a) && Objects.equals(this.f14307b, c1208p.f14307b) && this.c.equals(c1208p.c) && Objects.equals(this.f14314k, c1208p.f14314k) && Objects.equals(this.f14315m, c1208p.f14315m) && Objects.equals(this.f14316n, c1208p.f14316n) && Objects.equals(this.d, c1208p.d) && Arrays.equals(this.f14327z, c1208p.f14327z) && Objects.equals(this.l, c1208p.l) && Objects.equals(this.B, c1208p.B) && Objects.equals(this.f14319r, c1208p.f14319r) && c(c1208p);
        }
        return false;
    }

    public final int hashCode() {
        if (this.f14305M == 0) {
            String str = this.f14306a;
            int iHashCode = (527 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.f14307b;
            int iHashCode2 = (this.c.hashCode() + ((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31)) * 31;
            String str3 = this.d;
            int iHashCode3 = (((((((((((iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.f14308e) * 31) + this.f14309f) * 31) + this.f14310g) * 31) + this.f14311h) * 31) + this.f14312i) * 31;
            String str4 = this.f14314k;
            int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            K k10 = this.l;
            int iHashCode5 = (iHashCode4 + (k10 == null ? 0 : k10.hashCode())) * 961;
            String str5 = this.f14315m;
            int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.f14316n;
            this.f14305M = ((((((((((((((((((((Float.floatToIntBits(this.f14326y) + ((((Float.floatToIntBits(this.f14324w) + ((((((((((iHashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31) + this.o) * 31) + ((int) this.f14320s)) * 31) + this.f14322u) * 31) + this.f14323v) * 31)) * 31) + this.f14325x) * 31)) * 31) + this.f14294A) * 31) + this.f14295C) * 31) + this.f14296D) * 31) + this.f14297E) * 31) + this.f14298F) * 31) + this.f14299G) * 31) + this.f14300H) * 31) + this.f14302J) * 31) + this.f14303K) * 31) + this.f14304L;
        }
        return this.f14305M;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Format(");
        sb.append(this.f14306a);
        sb.append(", ");
        sb.append(this.f14307b);
        sb.append(", ");
        sb.append(this.f14315m);
        sb.append(", ");
        sb.append(this.f14316n);
        sb.append(", ");
        sb.append(this.f14314k);
        sb.append(", ");
        sb.append(this.f14313j);
        sb.append(", ");
        sb.append(this.d);
        sb.append(", [");
        sb.append(this.f14322u);
        sb.append(", ");
        sb.append(this.f14323v);
        sb.append(", ");
        sb.append(this.f14324w);
        sb.append(", ");
        sb.append(this.B);
        sb.append("], [");
        sb.append(this.f14295C);
        sb.append(", ");
        return M5.b.h(sb, this.f14296D, "])");
    }
}
