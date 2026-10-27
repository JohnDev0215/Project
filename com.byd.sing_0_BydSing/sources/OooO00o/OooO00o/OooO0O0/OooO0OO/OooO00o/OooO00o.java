package OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o;

import OooO00o.OooO00o.OooO0O0.OooO0o0.b;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import com.android.volley.BuildConfig;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;
import kotlin.text.o;

/* JADX INFO: loaded from: classes.dex */
public final class OooO00o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final UsbDevice f2a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f3b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f4c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f5d;
    public String e;
    public String f;
    public String g;
    public String h;
    public boolean i;
    public boolean j;
    public boolean k;
    public String l;
    public String m;
    public String n;
    public String o;
    public int p;
    public boolean q;
    public OooO0O0 r;

    public enum OooO0O0 {
        M,
        U,
        Q,
        X,
        S,
        NONE
    }

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    static {
        new a(null);
    }

    public OooO00o(Context context, UsbDevice usbDevice) {
        h.d(context, "mContext");
        this.f2a = usbDevice;
        this.f5d = BuildConfig.FLAVOR;
        this.e = BuildConfig.FLAVOR;
        this.r = OooO0O0.NONE;
        g();
        if (usbDevice == null) {
            return;
        }
        m(OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o.f18a.j(usbDevice));
    }

    public final boolean A() {
        return this.j;
    }

    public final boolean B() {
        return this.i;
    }

    public final boolean C() {
        return this.q;
    }

    public final String a() {
        return this.h;
    }

    public final void b() {
        UsbDevice usbDevice = this.f2a;
        if (usbDevice == null) {
            return;
        }
        OooO0O0 oooO0O0 = OooO0O0.NONE;
        this.r = oooO0O0;
        this.i = false;
        this.j = false;
        OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o oooO00o = OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o.f18a;
        String strL = oooO00o.l(usbDevice.getProductName());
        if (strL.length() >= 18 && o.k(strL, "Loostone PureMic", true)) {
            Locale locale = Locale.getDefault();
            h.c(locale, "getDefault()");
            String upperCase = strL.toUpperCase(locale);
            h.c(upperCase, "(this as java.lang.String).toUpperCase(locale)");
            char cCharAt = upperCase.charAt(17);
            if (cCharAt == 'U') {
                oooO0O0 = OooO0O0.U;
            } else if (cCharAt == 'Q') {
                oooO0O0 = OooO0O0.Q;
            } else if (cCharAt == 'X') {
                oooO0O0 = OooO0O0.X;
            } else if (cCharAt == 'M') {
                oooO0O0 = OooO0O0.M;
            } else if (cCharAt == 'S') {
                oooO0O0 = OooO0O0.S;
            }
        }
        d(oooO0O0);
        j(oooO00o.t(strL));
        p(oooO00o.u(strL));
        f(oooO00o.p(strL));
        this.f3b = String.valueOf(this.f2a.getVendorId());
        this.f4c = String.valueOf(this.f2a.getProductId());
        String str = String.format("%04x", Arrays.copyOf(new Object[]{Integer.valueOf(this.f2a.getVendorId())}, 1));
        h.c(str, "java.lang.String.format(format, *args)");
        Locale locale2 = Locale.getDefault();
        h.c(locale2, "getDefault()");
        Objects.requireNonNull(str, "null cannot be cast to non-null type java.lang.String");
        String lowerCase = str.toLowerCase(locale2);
        h.c(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
        this.f5d = lowerCase;
        String str2 = String.format("%04x", Arrays.copyOf(new Object[]{Integer.valueOf(this.f2a.getProductId())}, 1));
        h.c(str2, "java.lang.String.format(format, *args)");
        Locale locale3 = Locale.getDefault();
        h.c(locale3, "getDefault()");
        Objects.requireNonNull(str2, "null cannot be cast to non-null type java.lang.String");
        String lowerCase2 = str2.toLowerCase(locale3);
        h.c(lowerCase2, "(this as java.lang.String).toLowerCase(locale)");
        this.e = lowerCase2;
        try {
            this.h = b.d(this.f2a.getSerialNumber());
            this.f = b.d(this.f2a.getManufacturerName());
            this.g = b.d(oooO00o.l(this.f2a.getProductName()));
        } catch (NullPointerException e) {
            e.printStackTrace();
            OooO00o.OooO00o.OooO0O0.b.f41a.d("CachedDongleDevice", " ------> 拿不到设备信息");
        }
    }

    public final void c(int i) {
    }

    public final void d(OooO0O0 oooO0O0) {
        h.d(oooO0O0, "<set-?>");
        this.r = oooO0O0;
    }

    public final void e(String str) {
        this.n = str;
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        String str3;
        String str4;
        if (obj == null || !(obj instanceof OooO00o)) {
            return false;
        }
        OooO00o oooO00o = (OooO00o) obj;
        String str5 = this.f3b;
        return str5 != null && h.a(str5, oooO00o.f3b) && (str = this.f4c) != null && h.a(str, oooO00o.f4c) && (str2 = this.f) != null && h.a(str2, oooO00o.f) && (str3 = this.g) != null && h.a(str3, oooO00o.g) && (str4 = this.h) != null && h.a(str4, oooO00o.h);
    }

    public final void f(boolean z) {
        this.k = z;
    }

    public final void g() {
        b();
    }

    public final void h(int i) {
        this.p = i;
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append((Object) this.f3b);
        sb.append((Object) this.f4c);
        sb.append((Object) this.f);
        sb.append((Object) this.g);
        sb.append((Object) this.h);
        return sb.toString().hashCode();
    }

    public final void i(String str) {
        this.o = str;
    }

    public final void j(boolean z) {
        this.j = z;
    }

    public final String k() {
        return this.n;
    }

    public final void l(String str) {
        this.l = str;
    }

    public final void m(boolean z) {
        this.q = z;
    }

    public final String n() {
        return this.o;
    }

    public final void o(String str) {
        this.m = str;
    }

    public final void p(boolean z) {
        this.i = z;
    }

    public final String q() {
        return this.m;
    }

    public final String r() {
        return this.l;
    }

    public final String s() {
        return this.e;
    }

    public final String t() {
        return this.g;
    }

    public final int u() {
        return this.p;
    }

    public final OooO0O0 v() {
        return this.r;
    }

    public final UsbDevice w() {
        return this.f2a;
    }

    public final String x() {
        return this.f5d;
    }

    public final String y() {
        return this.f;
    }

    public final boolean z() {
        return this.k;
    }
}
