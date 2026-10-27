package org.chromium.chrome.browser.jni;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.text.TextUtils;
import android.util.Log;
import com.wrsapi.wtp.jni.WtpJni;
import defpackage.C0175b;
import defpackage.a23;
import defpackage.d73;
import defpackage.gq2;
import defpackage.i02;
import defpackage.n3;
import defpackage.oj2;
import defpackage.vl1;
import defpackage.xz;
import defpackage.y13;
import defpackage.z7;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class NetworkBlock {
    public static final HashMap a = new HashMap();
    public static boolean b = false;
    public static final String[] c = {BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON, BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET};
    public static final String[] d = {"www.netflix.com", "www.disneyplus.com", "www.foxtel.com.au", "www.amazon.com/amazonprime", "www.primevideo.com", "www.amazon.com/Prime-Video", "www.amazon.com/Amazon-Video", "www.amazon.com/gp/video"};
    public static volatile int e = 1;

    public class a implements Runnable {
        @Override // java.lang.Runnable
        public final void run() {
            Log.i("NetworkBlock", "initWebReputation");
            try {
                Context context = xz.a;
                String autoVIN = BYDAutoBodyworkDevice.getInstance(context).getAutoVIN();
                int autoType = BYDAutoBodyworkDevice.getInstance(context).getAutoType();
                WtpJni.ConvertDirData(context.getFilesDir().getAbsolutePath());
                Log.i("NetworkBlock", autoVIN + "======" + autoType + "=======" + NetworkBlock.b());
                oj2.u(autoType, autoVIN, NetworkBlock.b());
                NetworkBlock.b = true;
            } catch (Exception e) {
                Log.e("NetworkBlock", "initWebReputation failed: " + e);
            }
        }
    }

    public class b implements Callable<Integer> {
        public final /* synthetic */ String a;
        public final /* synthetic */ String b;
        public final /* synthetic */ AtomicInteger c;

        public b(String str, String str2, AtomicInteger atomicInteger) {
            this.a = str;
            this.b = str2;
            this.c = atomicInteger;
        }

        /* JADX WARN: Removed duplicated region for block: B:48:0x00b9  */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Integer call() {
            /*
                Method dump skipped, instruction units count: 304
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.jni.NetworkBlock.b.call():java.lang.Object");
        }
    }

    public static int a(String str, String str2) {
        String strB;
        Map map;
        Integer num;
        Log.i("NetworkBlock", "webAddress: " + str + "====== allWebAddressString: " + str2);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (str.startsWith("chrome://download") || str.startsWith("chrome://local-state")) {
            return 5;
        }
        if (TextUtils.isEmpty(str) || str.startsWith("chrome")) {
            return 1;
        }
        AtomicInteger atomicInteger = new AtomicInteger(1);
        try {
            strB = b();
            map = (Map) a.get(strB);
        } catch (Exception e2) {
            Log.e("NetworkBlock", "Exception msg:" + e2.getMessage());
            atomicInteger.set(1);
        }
        if (map != null && (num = (Integer) map.get(str)) != null) {
            Log.i("NetworkBlock", "cache checkCode: " + num);
            return num.intValue();
        }
        atomicInteger.set(((Integer) Executors.newFixedThreadPool(10).submit(new b(str, strB, atomicInteger)).get(1L, TimeUnit.SECONDS)).intValue());
        Log.i("NetworkBlock", "request time: " + (System.currentTimeMillis() - jCurrentTimeMillis));
        return atomicInteger.get();
    }

    public static String b() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getMethod("get", String.class).invoke(cls, BYDAutoSysPropertiesDevice.COUNTRY_CODE);
            return !TextUtils.isEmpty(str) ? str : "47";
        } catch (Exception e2) {
            StringBuilder sbN = C0175b.n("libVehicle getCountryCode error use default:", "47", " msg:");
            sbN.append(e2.getMessage());
            Log.e("getCountryCode error", sbN.toString());
            return "47";
        }
    }

    public static void c() {
        boolean zEquals = false;
        try {
            Context context = xz.a;
            String processName = Application.getProcessName();
            if (!TextUtils.isEmpty(processName) && context != null) {
                zEquals = context.getPackageName().equals(processName);
            }
        } catch (Exception unused) {
        }
        if (zEquals) {
            try {
                if (!i02.a(c)) {
                    Log.i("NetworkBlock", "no PERMISSION_P");
                } else if (b) {
                    Log.i("NetworkBlock", "init == true");
                } else {
                    PostTask.c(d73.l, new vl1());
                    Executors.newSingleThreadExecutor().execute(new a());
                }
            } catch (Exception e2) {
                Log.e("NetworkBlock", "initWebReputation Exception msg:" + e2.getMessage());
            }
        }
    }

    @CalledByNative
    public static int checkCode(String str, String str2) {
        return a(str, str2);
    }

    @CalledByNative
    public static int checkWidevineLevel(String str, String str2) {
        Log.i("NetworkBlock", "checkWidevineLevel: " + e);
        if (e == 0) {
            GURL gurl = new GURL(str2);
            String strF = gq2.f(gurl.e(), gurl.g());
            int i = 0;
            while (true) {
                String[] strArr = d;
                if (i >= strArr.length) {
                    break;
                }
                String str3 = strArr[i];
                if (!TextUtils.isEmpty(strF) && strF.startsWith(str3)) {
                    return n3.Q0() ? -3002 : -3001;
                }
                i++;
            }
        }
        return a(str, str2);
    }

    @CalledByNative
    public static void closeCurrentTab() {
        Tab tabI;
        Activity activityD = xz.d();
        if (activityD instanceof ChromeTabbedActivity) {
            ChromeTabbedActivity chromeTabbedActivity = (ChromeTabbedActivity) activityD;
            y13 y13VarG2 = chromeTabbedActivity.G2();
            g gVar = chromeTabbedActivity.N0.x;
            if (y13VarG2 == null || gVar == null) {
                return;
            }
            a23 a23Var = (a23) y13VarG2;
            TabModel tabModelZ = a23Var.z();
            if (tabModelZ.getCount() == 1) {
                if (gVar.c0.a() || (tabI = a23Var.i()) == null) {
                    return;
                }
                tabI.b(new LoadUrlParams("chrome-native://newtab/", 0));
                return;
            }
            Tab tabP = z7.p(tabModelZ);
            if (tabP == null) {
                return;
            }
            tabModelZ.a(tabP);
        }
    }

    @CalledByNative
    public static void continueLoadClick(String str) {
        String strB = b();
        HashMap map = a;
        Map map2 = (Map) map.get(strB);
        if (map2 == null) {
            HashMap map3 = new HashMap();
            map3.put(str, 1);
            map.put(strB, map3);
        } else {
            Integer num = (Integer) map2.get(str);
            if (num == null || num.intValue() == 4 || num.intValue() == -3000) {
                map2.put(str, 1);
            }
        }
    }
}
