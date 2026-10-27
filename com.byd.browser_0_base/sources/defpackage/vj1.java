package defpackage;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.Display;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.BuildInfo;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.ChromeTabbedActivity2;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.ui.display.DisplayAndroidManager;

/* JADX INFO: compiled from: MultiWindowUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vj1 implements ApplicationStatus.c {
    public static final vj1 h = new vj1();
    public final boolean e = p();
    public Boolean f;
    public WeakReference<ChromeTabbedActivity> g;

    public static boolean a(Context context) {
        boolean z = false;
        boolean z2 = false;
        for (Activity activity : ApplicationStatus.b()) {
            if (activity.getClass().equals(ChromeTabbedActivity.class)) {
                z = true;
            } else if (activity.getClass().equals(ChromeTabbedActivity2.class)) {
                z2 = true;
            }
        }
        if (z && z2) {
            return true;
        }
        Iterator<ActivityManager.AppTask> it = ((ActivityManager) context.getSystemService("activity")).getAppTasks().iterator();
        while (it.hasNext()) {
            String strE = e(it.next());
            if (TextUtils.equals(strE, ChromeTabbedActivity.class.getName())) {
                z = true;
            } else if (TextUtils.equals(strE, ChromeTabbedActivity2.class.getName())) {
                z2 = true;
            }
        }
        return z && z2;
    }

    public static boolean b() {
        if (BuildInfo.a.a.m) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        if (!(i > 31 || Build.VERSION.CODENAME.equals("Sv2"))) {
            if (!(i >= 30 && Build.MANUFACTURER.toUpperCase(Locale.ENGLISH).equals("SAMSUNG"))) {
                return false;
            }
        }
        return true;
    }

    public static Intent c(Context context, int i, boolean z, boolean z2) {
        Intent intent = new Intent(context, (Class<?>) ChromeTabbedActivity.class);
        intent.addFlags(268435456);
        intent.addFlags(134217728);
        if (i != -1) {
            intent.putExtra("org.chromium.chrome.browser.window_id", i);
        }
        if (z) {
            intent.putExtra("com.android.chrome.prefer_new", true);
        }
        if (z2) {
            intent.addFlags(4096);
        }
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        intent.putExtra("create_new_tab", true);
        q11.a(intent);
        return intent;
    }

    public static String e(ActivityManager.AppTask appTask) {
        ComponentName componentName;
        ActivityManager.RecentTaskInfo recentTaskInfoC = k5.c(appTask);
        if (recentTaskInfoC == null || (componentName = recentTaskInfoC.baseActivity) == null) {
            return "";
        }
        String className = componentName.getClassName();
        return TextUtils.equals(className, "com.google.android.apps.chrome.Main") ? ChromeTabbedActivity.class.getName() : className;
    }

    public static Activity f(Activity activity) {
        ArrayList<Activity> arrayListB = ApplicationStatus.b();
        int taskId = activity.getTaskId();
        for (Activity activity2 : arrayListB) {
            int taskId2 = activity2.getTaskId();
            if (taskId2 != taskId && m(activity2)) {
                for (Activity activity3 : arrayListB) {
                    if ((activity3 instanceof ChromeTabbedActivity) && activity3.getTaskId() == taskId2) {
                        return activity3;
                    }
                }
                return null;
            }
        }
        return null;
    }

    public static int g() {
        int i = 0;
        for (int i2 = 0; i2 < h(); i2++) {
            if (nj1.v(i2)) {
                if ((SharedPreferencesManager.getInstance().d(0, ar.h.b(String.valueOf(i2))) == 0 && nj1.u(i2) == -1) ? false : true) {
                    i++;
                }
            }
        }
        return i;
    }

    public static int h() {
        return p() ? 5 : 3;
    }

    public static Bundle j(Activity activity) {
        int iIntValue;
        h.getClass();
        if (!n(activity)) {
            return null;
        }
        ArrayList arrayListD = c6.d(activity);
        Display displayB = DisplayAndroidManager.b(activity);
        if (arrayListD.size() != 0) {
            Iterator it = arrayListD.iterator();
            while (it.hasNext()) {
                iIntValue = ((Integer) it.next()).intValue();
                if (iIntValue != displayB.getDisplayId()) {
                    break;
                }
            }
            iIntValue = -1;
        } else {
            iIntValue = -1;
        }
        if (iIntValue == -1) {
            throw new IllegalStateException("Attempting to open window in other display, but one is not found");
        }
        ActivityOptions activityOptionsMakeBasic = ActivityOptions.makeBasic();
        activityOptionsMakeBasic.setLaunchDisplayId(iIntValue);
        return activityOptionsMakeBasic.toBundle();
    }

    public static SparseBooleanArray k() {
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        for (Activity activity : ApplicationStatus.b()) {
            if (m(activity)) {
                sparseBooleanArray.put(activity.getTaskId(), true);
            }
        }
        return sparseBooleanArray;
    }

    public static boolean l(Activity activity, String str) {
        Iterator<ActivityManager.AppTask> it = ((ActivityManager) activity.getSystemService("activity")).getAppTasks().iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(e(it.next()), str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean m(Activity activity) {
        if (activity == null) {
            return false;
        }
        int iC = ApplicationStatus.c(activity);
        return iC == 3 || iC == 4;
    }

    public static boolean n(Activity activity) {
        return c6.d(activity).size() == 2;
    }

    public static boolean o(Activity activity) {
        if (activity == null) {
            return false;
        }
        return activity.isInMultiWindowMode();
    }

    public static boolean p() {
        if (Build.VERSION.SDK_INT < 30) {
            return false;
        }
        Context context = xz.a;
        try {
            return context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), ChromeTabbedActivity.class.getCanonicalName()), 0).launchMode == 4;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static long r(int i) {
        return SharedPreferencesManager.getInstance().f(ar.j.b(String.valueOf(i)));
    }

    public static void s(Intent intent, Activity activity, Class<? extends Activity> cls) {
        boolean z;
        intent.setClass(activity, cls);
        intent.addFlags(4096);
        if (!p() && cls.equals(ChromeTabbedActivity.class)) {
            Iterator it = ApplicationStatus.b().iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                } else if (((Activity) it.next()).getClass().equals(ChromeTabbedActivity.class)) {
                    z = true;
                    break;
                }
            }
            if (z) {
                intent.setFlags(intent.getFlags() & (-4097));
            }
        }
        intent.putExtra("com.android.browser.application_id", activity.getPackageName());
        intent.putExtra("create_new_tab", true);
    }

    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        if (i == 3 && (activity instanceof ChromeTabbedActivity)) {
            this.g = new WeakReference<>((ChromeTabbedActivity) activity);
        }
    }

    public final Class<? extends Activity> i(Activity activity) {
        if (this.e) {
            return ChromeTabbedActivity.class;
        }
        boolean z = activity instanceof ChromeTabbedActivity2;
        vj1 vj1Var = h;
        if (z) {
            ApplicationStatus.g(vj1Var);
            return ChromeTabbedActivity.class;
        }
        if (!(activity instanceof ChromeTabbedActivity)) {
            return null;
        }
        this.f = Boolean.TRUE;
        ApplicationStatus.g(vj1Var);
        return ChromeTabbedActivity2.class;
    }

    public final boolean q(Activity activity) {
        return ((!o(activity) && !n(activity)) || BuildInfo.a.a.m || i(activity) == null) ? false : true;
    }
}
