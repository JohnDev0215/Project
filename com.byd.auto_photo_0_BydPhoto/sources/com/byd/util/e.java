package com.byd.util;

import android.app.ActivityManager;
import android.car.Car;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.location.Address;
import android.location.Geocoder;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.byd.bean.AudioFocusStateInfo;
import com.byd.common.R$integer;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static a f2947b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static b.a.c f2948c;
    private static b.a.b d;
    private static Uri e;
    private static boolean f;
    private static Handler g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ThreadLocal<Integer> f2946a = new ThreadLocal<>();
    private static int h = -1;
    private static int i = -1;

    private static class a implements MediaScannerConnection.MediaScannerConnectionClient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private MediaScannerConnection f2949a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private LinkedList<String> f2950b = new LinkedList<>();

        public a(Context context) {
            this.f2949a = new MediaScannerConnection(context, this);
        }

        private synchronized void c() {
            this.f2949a.scanFile(this.f2950b.pop(), null);
        }

        public synchronized void a(String str) {
            this.f2950b.add(str);
            if (!this.f2949a.isConnected()) {
                this.f2949a.connect();
            }
        }

        public void b() {
            if (this.f2949a.isConnected()) {
                this.f2949a.disconnect();
            }
        }

        @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
        public void onMediaScannerConnected() {
            c();
        }

        @Override // android.media.MediaScannerConnection.OnScanCompletedListener
        public void onScanCompleted(String str, Uri uri) {
            w.c("syncMediaStore onScanComplete : " + str);
            if (this.f2950b.size() == 0) {
                this.f2949a.disconnect();
            } else {
                c();
            }
        }
    }

    public static boolean A() {
        String strW = w();
        return strW != null && strW.contains("DiLink300");
    }

    public static boolean B() {
        String strW = w();
        return strW != null && strW.contains("RUI");
    }

    public static boolean C(String str, int i2) {
        try {
            StatFs statFs = new StatFs(new File(str).getPath());
            float availableBlocksLong = (float) ((statFs.getAvailableBlocksLong() * 100.0d) / statFs.getBlockCountLong());
            w.c("disk available : " + availableBlocksLong + "%");
            return availableBlocksLong > ((float) i2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean D() {
        String strB = v.d().b("sys.isincall", "false");
        w.c("is in call : " + strB);
        if (strB == null) {
            return false;
        }
        try {
            return Boolean.parseBoolean(strB);
        } catch (Exception e2) {
            w.c("isInCall check exception : " + e2.getMessage());
            return false;
        }
    }

    public static boolean E() {
        return ActivityManager.isUserAMonkey();
    }

    public static boolean F() {
        String strB = v.d().b("ro.build.multi_display_user", "0");
        w.c("ro.build.multi_display_user : " + strB);
        return "1".equals(strB);
    }

    public static boolean G(Context context) {
        if (context.getResources().getInteger(R$integer.force_oversea) == 1) {
            return true;
        }
        String strA = v.d().a(BYDAutoSysPropertiesDevice.BUILD_REGION);
        String strA2 = v.d().a("ro.build.car.region");
        w.c("ro.build.region : " + strA + " ro.build.car.region : " + strA2);
        return "ROW".equals(strA) || "oversea".equals(strA2);
    }

    public static boolean H(Context context) {
        if (h == -1) {
            try {
                Class<?> cls = Class.forName("android.widget.CustomVehicleConfig");
                boolean zBooleanValue = ((Boolean) cls.getMethod("isRightDriver", new Class[0]).invoke(cls.getMethod("getInstance", Context.class).invoke(cls, context), new Object[0])).booleanValue();
                w.c("isRightDriver : " + zBooleanValue);
                h = zBooleanValue ? 1 : 0;
                return zBooleanValue;
            } catch (Exception e2) {
                e2.printStackTrace();
                h = 0;
                int iE = v.d().e("persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
                w.c("persist.vendor.byd.cfg.huc.steering.wheel.position : " + iE);
                h = iE;
            }
        }
        return h == 1;
    }

    public static boolean I(String str) {
        return str == null || "com.byd.autovoice".equals(str) || "com.byd.autovoice.aispeech".equals(str) || str.startsWith("com.byd.vrassistant") || "com.byd.oms".equals(str);
    }

    public static double J(String str, String str2) {
        String[] strArrSplit = str.split(",");
        String[] strArrSplit2 = strArrSplit[0].split("/");
        double d2 = Double.parseDouble(strArrSplit2[0].trim()) / Double.parseDouble(strArrSplit2[1].trim());
        String[] strArrSplit3 = strArrSplit[1].split("/");
        double d3 = Double.parseDouble(strArrSplit3[0].trim()) / Double.parseDouble(strArrSplit3[1].trim());
        String[] strArrSplit4 = strArrSplit[2].split("/");
        double d4 = d2 + (d3 / 60.0d) + ((Double.parseDouble(strArrSplit4[0].trim()) / Double.parseDouble(strArrSplit4[1].trim())) / 3600.0d);
        return ("S".equals(str2) || "W".equals(str2)) ? -d4 : d4;
    }

    public static void K() {
        b.a.c cVar = f2948c;
        if (cVar != null) {
            if (cVar.isConnected()) {
                f2948c.disconnect();
            }
            f2948c = null;
            d = null;
        }
        a aVar = f2947b;
        if (aVar != null) {
            aVar.b();
            f2947b = null;
        }
    }

    public static AudioFocusStateInfo L(Context context, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, int i2, int i3, boolean z) {
        return M(context, onAudioFocusChangeListener, i2, i3, z, 1);
    }

    public static AudioFocusStateInfo M(Context context, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, int i2, int i3, boolean z, int i4) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Car.AUDIO_SERVICE);
        if (Build.VERSION.SDK_INT >= 28 && z) {
            try {
                AudioFocusRequest.Builder builder = new AudioFocusRequest.Builder(i3);
                builder.setOnAudioFocusChangeListener(onAudioFocusChangeListener);
                AudioAttributes.Builder builder2 = new AudioAttributes.Builder();
                builder2.setUsage(i4);
                builder2.setContentType(i2);
                builder.setAudioAttributes(builder2.build());
                AudioFocusRequest audioFocusRequestBuild = builder.build();
                return new AudioFocusStateInfo(audioManager.requestAudioFocus(audioFocusRequestBuild), audioFocusRequestBuild);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return new AudioFocusStateInfo(audioManager.requestAudioFocus(onAudioFocusChangeListener, i2, i3), onAudioFocusChangeListener);
    }

    public static synchronized void N(Context context, File file) {
        O(context, file, 0L);
    }

    public static void O(Context context, File file, long j) {
        if (!file.exists() || file.length() == 0) {
            w.c("saveFileToContent but not exist : " + file.getPath());
            return;
        }
        if (d == null) {
            d = new b.a.b(context.getApplicationContext());
        }
        if (f2948c == null) {
            f2948c = new b.a.c(context.getApplicationContext(), d);
        }
        f2948c.a(file.getPath(), null);
    }

    public static void P(Context context, File file) {
        if (f2947b == null) {
            f2947b = new a(context.getApplicationContext());
        }
        f2947b.a(file.getPath());
    }

    public static void a(Context context, String str, int i2) {
        b(context, str, i2, null);
    }

    public static void b(Context context, String str, int i2, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("path", str);
        contentValues.put("opt", Integer.valueOf(i2));
        if (str2 != null) {
            contentValues.put("extras", str2);
        }
        try {
            context.getApplicationContext().getContentResolver().insert(t(context), contentValues);
            w.c("addMediaAndNotifyPhoto : " + str + " " + i2 + " " + str2);
        } catch (Exception e2) {
            e2.printStackTrace();
            w.c("addMediaAndNotifyPhoto exception:" + e2.getMessage());
        }
    }

    public static void c(Context context) {
        if (Build.VERSION.SDK_INT >= 30) {
            w.c(!Environment.isExternalStorageManager() ? "ExternalStorageManager permission -> false" : "ExternalStorageManager permission -> true");
        }
    }

    public static void d(Context context) {
        if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(context.getApplicationContext())) {
            try {
                context.startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + context.getApplicationInfo().packageName)));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void e() {
        f2946a.set(-1);
    }

    public static String f(Context context, double d2, double d3) {
        List<Address> fromLocation;
        try {
            fromLocation = new Geocoder(context).getFromLocation(d2, d3, 1);
        } catch (Exception e2) {
            e2.printStackTrace();
            fromLocation = null;
        }
        if (fromLocation == null || fromLocation.isEmpty()) {
            w.c("no address name for " + String.valueOf(d2).replaceAll("0", "x"));
            return null;
        }
        Address address = fromLocation.get(0);
        StringBuilder sb = new StringBuilder();
        String addressLine = address.getAddressLine(0);
        if (TextUtils.isEmpty(addressLine)) {
            sb.append(address.getAdminArea());
            if (!TextUtils.isEmpty(address.getLocality())) {
                sb.append(",");
                sb.append(address.getLocality());
            }
            if (!TextUtils.isEmpty(address.getSubAdminArea())) {
                sb.append(",");
                sb.append(address.getSubAdminArea());
            }
            if (!TextUtils.isEmpty(address.getFeatureName())) {
                sb.append(",");
                addressLine = address.getFeatureName();
                sb.append(addressLine);
            }
        } else {
            sb.append(addressLine);
        }
        return sb.toString();
    }

    public static String g(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getApplicationInfo().packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static int h(Context context) {
        int i2 = i;
        if (i2 != -1) {
            return i2;
        }
        try {
            Class.forName("android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice");
            BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(context);
            if (bYDAutoBodyworkDevice != null) {
                i = bYDAutoBodyworkDevice.getAutoType();
                w.c("getAutoType: " + i);
                return i;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            w.c("getAutoType exception : " + e2.getMessage());
        }
        return i;
    }

    public static String i(Context context) {
        try {
            Class.forName("android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice");
            BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(context);
            if (bYDAutoBodyworkDevice == null) {
                return "";
            }
            try {
                return (String) bYDAutoBodyworkDevice.getClass().getMethod("getRealAutoVIN", new Class[0]).invoke(bYDAutoBodyworkDevice, new Object[0]);
            } catch (Exception e2) {
                e2.printStackTrace();
                return bYDAutoBodyworkDevice.getAutoVIN();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            w.c("getAutoVIN exception : " + e3.getMessage());
            return "";
        }
    }

    public static int j(Class<?> cls, String str) {
        return k(cls, str, 0);
    }

    public static int k(Class<?> cls, String str, int i2) throws NoSuchFieldException {
        Field field = cls.getField(str);
        field.setAccessible(true);
        return Modifier.isStatic(field.getModifiers()) ? ((Integer) field.get(cls)).intValue() : i2;
    }

    public static int l(Context context, int i2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{i2});
        int color = typedArrayObtainStyledAttributes.getColor(0, -1);
        typedArrayObtainStyledAttributes.recycle();
        return color;
    }

    public static synchronized String m(File file) {
        String str;
        String parent = file.getParent();
        String name = file.getName();
        int iLastIndexOf = name.lastIndexOf(".");
        String strSubstring = "";
        if (iLastIndexOf > -1) {
            String strSubstring2 = name.substring(0, iLastIndexOf);
            strSubstring = name.substring(iLastIndexOf);
            name = strSubstring2;
        }
        if (z(name)) {
            name = name.substring(0, name.lastIndexOf("("));
        }
        str = null;
        int i2 = 1;
        while (file.exists()) {
            str = parent + File.separatorChar + name + "(" + i2 + ")" + strSubstring;
            file = new File(str);
            i2++;
        }
        return str;
    }

    public static String n() {
        String strB = v.d().b(BYDAutoSysPropertiesDevice.COUNTRY_CODE, "");
        w.c("countryCode : " + strB);
        return strB;
    }

    public static String o() {
        try {
            return v.d().b("apps.setting.product.outswver", null);
        } catch (Exception e2) {
            e2.printStackTrace();
            w.c("getSystemVersion exception : " + e2.getMessage());
            return "1.0.0";
        }
    }

    public static Drawable p(Context context, int i2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{i2});
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        typedArrayObtainStyledAttributes.recycle();
        return drawable;
    }

    public static String[] q(int i2) {
        return r(i2, false);
    }

    public static String[] r(int i2, boolean z) {
        if (i2 >= 33) {
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 34) {
                return new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO", "android.permission.READ_MEDIA_VISUAL_USER_SELECTED"};
            }
            if (i3 >= 33) {
                return new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO"};
            }
        }
        return z ? new String[]{"android.permission.READ_EXTERNAL_STORAGE"} : new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    }

    public static int s(Context context) {
        int i2;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return 0;
        }
        if (activeNetworkInfo.getType() == 1) {
            return 1;
        }
        if (activeNetworkInfo.getType() != 0) {
            return 5;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                i2 = 4;
                break;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return 2;
            case 13:
            case 18:
                i2 = 3;
                break;
            case 19:
            default:
                String subtypeName = activeNetworkInfo.getSubtypeName();
                return (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) ? 2 : 5;
            case 20:
                i2 = 6;
                break;
        }
        return i2;
    }

    private static Uri t(Context context) {
        if (e == null) {
            if (f) {
                Uri uri = Uri.parse("content://com.byd.auto_photo.fse/opt");
                e = uri;
                return uri;
            }
            e = Uri.parse("content://com.byd.auto_photo/opt");
        }
        return e;
    }

    public static int u(Context context, int i2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{i2});
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId;
    }

    public static int v(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public static String w() {
        String strB = v.d().b("ro.vehicle.type", null);
        w.c("sys type : " + strB);
        return strB;
    }

    public static boolean x(Context context, String str) {
        PackageInfo packageInfo;
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (Exception e2) {
            e2.printStackTrace();
            w.c("check isAppInstalled exception : " + e2.getMessage());
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static synchronized boolean y() {
        boolean z;
        if (g == null) {
            g = new Handler(Looper.getMainLooper());
        }
        if (g.hasMessages(256)) {
            w.c("click too fast!!!");
            z = true;
        } else {
            g.sendEmptyMessageDelayed(256, 250L);
            z = false;
        }
        return z;
    }

    private static boolean z(String str) {
        return Pattern.compile(".*\\([0-9]+\\)$").matcher(str).matches();
    }
}
