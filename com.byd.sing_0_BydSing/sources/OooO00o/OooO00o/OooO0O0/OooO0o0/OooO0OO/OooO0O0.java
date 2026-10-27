package OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO0O0;
import OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0Oo.b;
import com.android.volley.BuildConfig;
import kotlin.a;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.h;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: loaded from: classes.dex */
public final class OooO0O0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final OooO0O0 f531a = new OooO0O0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f532b = d.b(OooO00o.f534OooO00o);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f533c = d.b(C0020OooO0O0.f535OooO00o);

    public static final class OooO00o extends Lambda implements kotlin.jvm.b.a<OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0Oo.OooO00o> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO00o f534OooO00o = new OooO00o();

        public OooO00o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0Oo.OooO00o invoke() {
            return OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0Oo.OooO00o.f542a.a();
        }
    }

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0O0$OooO0O0, reason: collision with other inner class name */
    public static final class C0020OooO0O0 extends Lambda implements kotlin.jvm.b.a<b> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final C0020OooO0O0 f535OooO00o = new C0020OooO0O0();

        public C0020OooO0O0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final b invoke() {
            return new b();
        }
    }

    public final OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0Oo.OooO00o a() {
        return (OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0Oo.OooO00o) f532b.getValue();
    }

    public final int[] b(OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO0O0 oooO0O0) {
        h.d(oooO0O0, "command");
        String strA = a().a(oooO0O0);
        if (strA.length() < 14 || !"1".equals(String.valueOf(strA.charAt(0)))) {
            return null;
        }
        String strSubstring = strA.substring(1, 4);
        h.c(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return new int[]{Integer.parseInt(strSubstring), Integer.parseInt(String.valueOf(strA.charAt(13)))};
    }

    public final b c() {
        return (b) f533c.getValue();
    }

    public final void d(OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO0O0 oooO0O0) {
        h.d(oooO0O0, "command");
        a().g(oooO0O0);
    }

    public final int e() {
        String strE = c().e("version");
        if (strE.length() == 0) {
            return 0;
        }
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.e("DataRead", h.i(" ------> version ", strE));
        int iW = StringsKt__StringsKt.w(strE, ".", 0, false, 6, null) - 1;
        bVar.e("DataRead", h.i(" ------> versionInt ", Integer.valueOf(Integer.parseInt(String.valueOf(strE.charAt(iW))))));
        try {
            return Integer.parseInt(String.valueOf(strE.charAt(iW)));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public final String f() {
        String strA = a().a(OooO0O0.a.d(new OooO0O0.a(), null, 1).a(1).j(0).f(OooO0O0.OooO0o.READ).e(OooO0O0.OooO0OO.NONE).i());
        if (strA.length() < 12) {
            return "Format Error";
        }
        try {
            StringBuilder sb = new StringBuilder();
            String strSubstring = strA.substring(0, 3);
            h.c(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            sb.append(Integer.parseInt(strSubstring));
            sb.append('.');
            String strSubstring2 = strA.substring(3, 6);
            h.c(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            sb.append(Integer.parseInt(strSubstring2));
            sb.append('.');
            String strSubstring3 = strA.substring(6, 9);
            h.c(strSubstring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            sb.append(Integer.parseInt(strSubstring3));
            sb.append('.');
            String strSubstring4 = strA.substring(9, 12);
            h.c(strSubstring4, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            sb.append(Integer.parseInt(strSubstring4));
            sb.append('_');
            String strSubstring5 = strA.substring(12);
            h.c(strSubstring5, "(this as java.lang.String).substring(startIndex)");
            sb.append(strSubstring5);
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public final String g() {
        return a().a(OooO0O0.a.d(new OooO0O0.a(), null, 1).a(5).j(0).f(OooO0O0.OooO0o.READ).e(OooO0O0.OooO0OO.NONE).i());
    }
}
