package OooO00o.OooO00o.OooO0O0.OooO0o0;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.ArrayMap;
import com.android.volley.BuildConfig;
import com.blankj.utilcode.util.e;
import com.blankj.utilcode.util.r;
import com.blankj.utilcode.util.t;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import kotlin.collections.i;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.h;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.o;
import kotlin.text.q;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: loaded from: classes.dex */
public final class OooO00o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final OooO00o f18a = new OooO00o();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f19b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f20c = BuildConfig.FLAVOR;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Map<String, String> f21d = new LinkedHashMap();
    public static final kotlin.a e = kotlin.d.b(OooO0O0.f23OooO00o);
    public static final kotlin.a f = kotlin.d.b(C0000OooO00o.f22OooO00o);
    public static final ArrayMap<String, boolean[]> g = new ArrayMap<>();
    public static boolean[] h = {false, false, false, false};

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o$OooO00o, reason: collision with other inner class name */
    public static final class C0000OooO00o extends Lambda implements kotlin.jvm.b.a<kotlin.q.c> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final C0000OooO00o f22OooO00o = new C0000OooO00o();

        public C0000OooO00o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final kotlin.q.c invoke() {
            return new kotlin.q.c('A', 'F');
        }
    }

    public static final class OooO0O0 extends Lambda implements kotlin.jvm.b.a<kotlin.q.c> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO0O0 f23OooO00o = new OooO0O0();

        public OooO0O0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final kotlin.q.c invoke() {
            return new kotlin.q.c('1', '9');
        }
    }

    public final void a(String str) {
        h.d(str, "<set-?>");
        f20c = str;
    }

    public final int b(int i, int i2, int i3, int i4, int i5) {
        int i6 = ((int) ((((i - i2) * 1.0f) * (i5 - i4)) / (i3 - i2))) + i4;
        return i6 > i5 ? i5 : i6 < i4 ? i4 : i6;
    }

    public final UsbInterface c(UsbDevice usbDevice) {
        h.d(usbDevice, "usbDevice");
        if (!OooO00o.OooO00o.OooO0O0.OooO0OO.INTANCE.OooO00o().hasPermission(usbDevice)) {
            OooO00o.OooO00o.OooO0O0.b.f41a.b("BaseCommonUtil", "no usb device permission");
            return null;
        }
        int interfaceCount = usbDevice.getInterfaceCount();
        if (interfaceCount > 0) {
            int i = 0;
            if (interfaceCount > 0) {
                while (true) {
                    int i2 = i + 1;
                    UsbInterface usbInterface = usbDevice.getInterface(i);
                    h.c(usbInterface, "usbDevice.getInterface(index)");
                    if (usbInterface.getInterfaceClass() == 3) {
                        OooO00o.OooO00o.OooO0O0.b.f41a.b("BaseCommonUtil", h.i("usbInterface:", usbInterface));
                        if (usbInterface.getEndpointCount() == 0) {
                            return usbInterface;
                        }
                    }
                    if (i2 >= interfaceCount) {
                        break;
                    }
                    i = i2;
                }
            }
        }
        return null;
    }

    public final String d(Context context) {
        h.d(context, "context");
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            h.c(applicationInfo, "context.packageManager\n                .getApplicationInfo(\n                    context.packageName,\n                    PackageManager.GET_META_DATA\n                )");
            Bundle bundle = applicationInfo.metaData;
            if (bundle == null) {
                return BuildConfig.FLAVOR;
            }
            String string = bundle.getString("APP_CHANNEL");
            return string == null ? BuildConfig.FLAVOR : string;
        } catch (Exception e2) {
            e2.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public final String e(String str) {
        h.d(str, "path");
        t.a aVarA = t.a(h.i("md5sum ", str), false, true);
        h.c(aVarA, "execCmd(\n                \"md5sum $path\",\n                false, true\n            )");
        if (aVarA.f2619a != 0 || h.a(aVarA.f2620b, BuildConfig.FLAVOR)) {
            return BuildConfig.FLAVOR;
        }
        String strD = b.d(aVarA.f2620b);
        h.c(strD, "{\n            OldCommonUtil.trim(value.successMsg)\n        }");
        return strD;
    }

    public final kotlin.q.c f() {
        return (kotlin.q.c) f.getValue();
    }

    public final String g(String str) {
        h.d(str, "fileName");
        String packageCodePath = OooO00o.OooO00o.OooO0O0.a.f39a.a().getPackageCodePath();
        h.c(packageCodePath, "path");
        String strSubstring = packageCodePath.substring(0, StringsKt__StringsKt.A(packageCodePath, InternalZipConstants.ZIP_FILE_SEPARATOR, 0, false, 6, null) + 1);
        h.c(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return h.i(strSubstring, str);
    }

    public final kotlin.q.c h() {
        return (kotlin.q.c) e.getValue();
    }

    public final boolean i(Context context) {
        h.d(context, "context");
        Object systemService = context.getSystemService("usb");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.hardware.usb.UsbManager");
        HashMap<String, UsbDevice> deviceList = ((UsbManager) systemService).getDeviceList();
        if (deviceList == null || deviceList.isEmpty()) {
            return false;
        }
        for (UsbDevice usbDevice : deviceList.values()) {
            h.c(usbDevice, "usbDevice");
            if (j(usbDevice) && !r(usbDevice.getProductName())) {
                return true;
            }
        }
        return b.a("puremic") != -1;
    }

    public final boolean j(UsbDevice usbDevice) {
        h.d(usbDevice, "usbDevice");
        boolean z = false;
        if (o(usbDevice)) {
            return false;
        }
        String manufacturerName = usbDevice.getManufacturerName();
        String productName = usbDevice.getProductName();
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.b("BaseCommonUtil", "vendorName = " + ((Object) manufacturerName) + ", productName = " + ((Object) productName));
        if (manufacturerName == null || productName == null) {
            boolean zM = m(usbDevice);
            bVar.b("BaseCommonUtil", h.i("isPuremicDeviceFix end, productName = ", f18a.q()));
            return zM;
        }
        Locale locale = Locale.getDefault();
        h.c(locale, "getDefault()");
        String lowerCase = manufacturerName.toLowerCase(locale);
        h.c(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
        String strD = b.d(lowerCase);
        Locale locale2 = Locale.getDefault();
        h.c(locale2, "getDefault()");
        String lowerCase2 = productName.toLowerCase(locale2);
        h.c(lowerCase2, "(this as java.lang.String).toLowerCase(locale)");
        String strD2 = b.d(lowerCase2);
        if (h.a(strD, "www.loostone.com")) {
            h.c(strD2, "productLowerCase");
            if (StringsKt__StringsKt.o(strD2, "puremic", false, 2, null) || StringsKt__StringsKt.o(strD2, "byd-mic ls", false, 2, null)) {
                z = true;
            }
        }
        Boolean boolValueOf = Boolean.valueOf(z);
        Boolean bool = boolValueOf.booleanValue() ? boolValueOf : null;
        if (bool == null) {
            return z;
        }
        bool.booleanValue();
        r rVarK = f18a.k();
        if (rVarK == null) {
            return z;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(usbDevice.getVendorId());
        sb.append(usbDevice.getProductId());
        String string = sb.toString();
        String productName2 = usbDevice.getProductName();
        if (productName2 == null) {
            productName2 = BuildConfig.FLAVOR;
        }
        rVarK.h(string, productName2);
        return z;
    }

    public final r k() {
        try {
            return r.b("cache");
        } catch (Exception e2) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("BaseCommonUtil", "getDongleSharedPref error");
            e2.printStackTrace();
            return null;
        }
    }

    public final String l(String str) {
        return str == null ? f20c : str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean m(UsbDevice usbDevice) {
        String strF;
        String str = BuildConfig.FLAVOR;
        f20c = BuildConfig.FLAVOR;
        StringBuilder sb = new StringBuilder();
        sb.append(usbDevice.getVendorId());
        sb.append(usbDevice.getProductId());
        String string = sb.toString();
        String productName = usbDevice.getProductName();
        if (productName != null) {
            Locale locale = Locale.getDefault();
            h.c(locale, "getDefault()");
            String lowerCase = productName.toLowerCase(locale);
            h.c(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            String strD = b.d(lowerCase);
            h.c(strD, "productLowerCase");
            boolean z = StringsKt__StringsKt.o(strD, "puremic", false, 2, null) || StringsKt__StringsKt.o(strD, "byd-mic ls", false, 2, null);
            Boolean boolValueOf = Boolean.valueOf(z);
            Boolean bool = boolValueOf.booleanValue() ? boolValueOf : null;
            if (bool != null) {
                bool.booleanValue();
                OooO00o oooO00o = f18a;
                oooO00o.a(productName);
                r rVarK = oooO00o.k();
                if (rVarK != null) {
                    rVarK.h(string, productName);
                }
            }
            return z;
        }
        String manufacturerName = usbDevice.getManufacturerName();
        if (manufacturerName != null) {
            Locale locale2 = Locale.getDefault();
            h.c(locale2, "getDefault()");
            String lowerCase2 = manufacturerName.toLowerCase(locale2);
            h.c(lowerCase2, "(this as java.lang.String).toLowerCase(locale)");
            boolean zA = h.a(b.d(lowerCase2), "www.loostone.com");
            Boolean boolValueOf2 = Boolean.valueOf(zA);
            Boolean bool2 = boolValueOf2.booleanValue() ? boolValueOf2 : null;
            if (bool2 != null) {
                bool2.booleanValue();
                OooO00o oooO00o2 = f18a;
                r rVarK2 = oooO00o2.k();
                if (rVarK2 != null) {
                    if (rVarK2.a(string)) {
                        String strF2 = rVarK2.f(string, BuildConfig.FLAVOR);
                        h.c(strF2, "spUtils.getString(vidPid, \"\")");
                        oooO00o2.a(strF2);
                    } else {
                        rVarK2.h(string, BuildConfig.FLAVOR);
                    }
                }
            }
            return zA;
        }
        r rVarK3 = k();
        Boolean boolValueOf3 = rVarK3 == null ? null : Boolean.valueOf(rVarK3.a(string));
        if (!h.a(boolValueOf3, Boolean.TRUE)) {
            boolValueOf3 = null;
        }
        if (boolValueOf3 != null) {
            boolValueOf3.booleanValue();
            OooO00o oooO00o3 = f18a;
            r rVarK4 = oooO00o3.k();
            if (rVarK4 != null && (strF = rVarK4.f(string, BuildConfig.FLAVOR)) != null) {
                str = strF;
            }
            oooO00o3.a(str);
            return true;
        }
        String strS = s();
        if (strS != null) {
            Integer numValueOf = Integer.valueOf(StringsKt__StringsKt.w(strS, "Loostone", 0, false, 6, null));
            Integer num = (numValueOf.intValue() > 0) == true ? numValueOf : null;
            if (num != null) {
                int iIntValue = num.intValue();
                OooO00o oooO00o4 = f18a;
                String strSubstring = strS.substring(iIntValue);
                h.c(strSubstring, "(this as java.lang.String).substring(startIndex)");
                oooO00o4.a(strSubstring);
                return true;
            }
        }
        if (f21d.isEmpty()) {
            f21d.put("31905904", "Loostone PureMic Q1-K");
            f21d.put("31908961", "Loostone PureMic DC1-K");
            f21d.put("31908962", "Loostone PureMic DC2-KE");
            f21d.put("31908963", "Loostone PureMic DC3-KE");
            f21d.put("319016641", "Loostone PureMic U11-KE");
        }
        if (!f21d.containsKey(string)) {
            return b.a("puremic") != -1;
        }
        String str2 = f21d.get(string);
        if (str2 != null) {
            str = str2;
        }
        f20c = str;
        return true;
    }

    public final Object n() {
        return f19b;
    }

    public final boolean o(UsbDevice usbDevice) {
        String productName;
        if (usbDevice == null || (productName = usbDevice.getProductName()) == null) {
            return false;
        }
        return StringsKt__StringsKt.o(productName, "Loostone PureMic Tuning", false, 2, null);
    }

    public final boolean p(String str) {
        boolean z = v(str)[1];
        OooO00o.OooO00o.OooO0O0.b.f41a.b("BaseCommonUtil", h.i("isAdvancePair = ", Boolean.valueOf(z)));
        return z;
    }

    public final String q() {
        return f20c;
    }

    public final boolean r(String str) {
        boolean z = v(str)[0];
        OooO00o.OooO00o.OooO0O0.b.f41a.b("BaseCommonUtil", h.i("isMicDevice = ", Boolean.valueOf(z)));
        return z;
    }

    public final String s() {
        String str;
        t.a aVarA = t.a("cat /proc/asound/cards", false, true);
        if (aVarA == null || (str = aVarA.f2620b) == null) {
            return null;
        }
        for (String str2 : StringsKt__StringsKt.J(StringsKt__StringsKt.P(str).toString(), new String[]{"\n"}, false, 0, 6, null)) {
            if (StringsKt__StringsKt.n(str2, "puremic", true) || StringsKt__StringsKt.n(str2, "byd-mic ls", true)) {
                return str2;
            }
        }
        return null;
    }

    public final boolean t(String str) {
        boolean z = v(str)[2];
        OooO00o.OooO00o.OooO0O0.b.f41a.b("BaseCommonUtil", h.i("isBuiltIn = ", Boolean.valueOf(z)));
        return z;
    }

    public final boolean u(String str) {
        boolean z = v(str)[3];
        OooO00o.OooO00o.OooO0O0.b.f41a.b("BaseCommonUtil", h.i("isSupportHard = ", Boolean.valueOf(z)));
        return z;
    }

    public final boolean[] v(String str) {
        String strL = l(str);
        boolean z = true;
        if (strL.length() == 0) {
            return new boolean[]{false, false, false, false};
        }
        ArrayMap<String, boolean[]> arrayMap = g;
        if ((arrayMap.containsKey(strL) ? this : null) != null) {
            boolean[] zArr = arrayMap.get(strL);
            return zArr == null ? new boolean[]{false, false, false, false} : zArr;
        }
        h = new boolean[]{false, false, false, false};
        OooO00o.OooO00o.OooO0O0.b.f41a.b("BaseCommonUtil", h.i("parseDeviceName = ", strL));
        List listJ = StringsKt__StringsKt.J(strL, new String[]{"-"}, false, 0, 6, null);
        OooO00o oooO00o = f18a;
        if (!(listJ.size() > 1)) {
            oooO00o = null;
        }
        if (oooO00o != null) {
            String str2 = (String) i.j(listJ);
            Objects.requireNonNull(str2, "null cannot be cast to non-null type java.lang.String");
            String upperCase = str2.toUpperCase(Locale.ROOT);
            h.c(upperCase, "(this as java.lang.Strin….toUpperCase(Locale.ROOT)");
            Objects.requireNonNull(upperCase, "null cannot be cast to non-null type kotlin.CharSequence");
            String string = q.Q(upperCase).toString();
            loop0: for (int i = 0; i < string.length(); i++) {
                char cCharAt = string.charAt(i);
                OooO00o oooO00o2 = f18a;
                if (oooO00o2.h().d(cCharAt) || oooO00o2.f().d(cCharAt)) {
                    try {
                        long j = Long.parseLong(String.valueOf(cCharAt), kotlin.text.b.a(16));
                        String strA = e.a(new byte[]{(byte) j});
                        OooO00o.OooO00o.OooO0O0.b.f41a.b("BaseCommonUtil", "parseDeviceName " + cCharAt + ", flagsLong = " + j + ", bytes2Bits = " + ((Object) strA));
                        int i2 = 0;
                        while (true) {
                            int i3 = i2 + 1;
                            h[i2] = h.a(String.valueOf(strA.charAt(i2 + 4)), "0");
                            if (i3 > 3) {
                                break loop0;
                            }
                            i2 = i3;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        OooO00o oooO00o3 = f18a;
        boolean[] zArr2 = h;
        if ((zArr2[3] ^ true ? oooO00o3 : null) != null) {
            zArr2[3] = StringsKt__StringsKt.o((CharSequence) i.j(listJ), "E", false, 2, null);
        }
        boolean[] zArr3 = h;
        if ((zArr3[2] ^ true ? oooO00o3 : null) != null) {
            zArr3[2] = StringsKt__StringsKt.o(strL, "DC", false, 2, null) || StringsKt__StringsKt.o((CharSequence) i.j(listJ), "D", false, 2, null);
        }
        boolean[] zArr4 = h;
        if ((zArr4[1] ^ true ? oooO00o3 : null) != null) {
            zArr4[1] = o.l(strL, "Loostone PureMic DC3", false, 2, null) || o.l(strL, "BYD-mic LS-U11Pro", false, 2, null);
        }
        boolean[] zArr5 = h;
        if ((zArr5[0] ^ true ? oooO00o3 : null) != null) {
            if (!o.f(strL, "1W", false, 2, null) && !o.f(strL, "2F", false, 2, null)) {
                z = false;
            }
            zArr5[0] = z;
        }
        if ((o.l(strL, "BYD-mic LS-U11Pro", false, 2, null) ? oooO00o3 : null) != null) {
            h[0] = false;
        }
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        String string2 = Arrays.toString(h);
        h.c(string2, "java.util.Arrays.toString(this)");
        bVar.b("BaseCommonUtil", h.i("cached device name flags=", string2));
        g.put(strL, h);
        return h;
    }
}
