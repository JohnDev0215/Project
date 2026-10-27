package org.chromium.chrome.browser;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.SkinAppCompatDelegateImpl;
import defpackage.aq1;
import defpackage.at0;
import defpackage.bf0;
import defpackage.bi1;
import defpackage.bq;
import defpackage.ci1;
import defpackage.fa1;
import defpackage.g80;
import defpackage.h02;
import defpackage.i02;
import defpackage.ii2;
import defpackage.ik;
import defpackage.n3;
import defpackage.on1;
import defpackage.qe3;
import defpackage.uh3;
import defpackage.xs0;
import defpackage.xz;
import defpackage.z7;
import defpackage.zi2;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.BundleUtils;
import org.chromium.chrome.R$bool;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.app.bookmarks.BookmarkAddEditFolderActivity;
import org.chromium.chrome.browser.base.SplitChromeApplication;
import org.chromium.chrome.browser.jni.NetworkBlock;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: loaded from: classes.dex */
public class ChromeBaseAppCompatActivity extends AppCompatActivity implements on1.a, ci1 {
    public static final /* synthetic */ int H = 0;
    public on1 E;
    public i02.a G;
    public final aq1<bi1> D = new aq1<>();
    public final LinkedHashSet<Integer> F = new LinkedHashSet<>();

    @Override // androidx.appcompat.app.AppCompatActivity
    public final AppCompatDelegate A1() {
        WeakHashMap weakHashMap = SkinAppCompatDelegateImpl.q0;
        WeakReference weakReference = (WeakReference) weakHashMap.get(this);
        AppCompatDelegate appCompatDelegate = weakReference == null ? null : (AppCompatDelegate) weakReference.get();
        if (appCompatDelegate != null) {
            return appCompatDelegate;
        }
        SkinAppCompatDelegateImpl skinAppCompatDelegateImpl = new SkinAppCompatDelegateImpl(this, getWindow(), this);
        weakHashMap.put(this, new WeakReference(skinAppCompatDelegateImpl));
        return skinAppCompatDelegateImpl;
    }

    public boolean D1(Context context, Configuration configuration) {
        on1 on1Var = this.E;
        if (!on1Var.c()) {
            return false;
        }
        configuration.uiMode = (on1Var.d() ? 32 : 16) | (configuration.uiMode & (-49));
        return true;
    }

    public bi1 E1() {
        return null;
    }

    public on1 F1() {
        return at0.a();
    }

    public boolean H1() {
        return this instanceof BookmarkAddEditFolderActivity;
    }

    public void I1() {
        setTaskDescription(new ActivityManager.TaskDescription((String) null, (Bitmap) null, getColor(R$color.default_task_description_color)));
    }

    public void J1() {
        if (DeviceFormFactor.a(this)) {
            if ((!DeviceFormFactor.a(this) || bq.f0.a()) && !uh3.j()) {
                Window window = getWindow();
                int iA = ii2.a(this);
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(iA);
                View rootView = getWindow().getDecorView().getRootView();
                boolean z = getResources().getBoolean(R$bool.window_light_status_bar);
                int systemUiVisibility = rootView.getSystemUiVisibility();
                rootView.setSystemUiVisibility(z ? systemUiVisibility | 8192 : systemUiVisibility & (-8193));
            }
        }
    }

    @Override // defpackage.ci1
    public bi1 P0() {
        return this.D.f;
    }

    @Override // on1.a
    public void X0() {
        if (isFinishing()) {
            return;
        }
        recreate();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        SplitChromeApplication.f("chrome");
        ClassLoader classLoader = ChromeBaseAppCompatActivity.class.getClassLoader();
        Context context2 = xz.a;
        if (!classLoader.equals(context2.getClassLoader())) {
            throw new IllegalStateException("ClassLoader mismatch detected.\nA: " + classLoader + "\nB: " + context2.getClassLoader() + "\nC: " + classLoader.getParent() + "\nD: " + context2.getClassLoader().getParent() + "\nE: " + context2);
        }
        Object obj = BundleUtils.a;
        ClassLoader classLoader2 = getClass().getClassLoader();
        if (classLoader2 != context.getClassLoader()) {
            fa1.k("BundleUtils", "Mismatched ClassLoaders between Activity and context (fixing): %s", getClass());
            BundleUtils.f(context, classLoader2);
        }
        int i = zi2.a;
        this.E = F1();
        Configuration configuration = new Configuration();
        configuration.fontScale = 0.0f;
        if (D1(context, configuration)) {
            applyOverrideConfiguration(configuration);
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final ClassLoader getClassLoader() {
        if (BundleUtils.d == null) {
            BundleUtils.d = new BundleUtils.a();
        }
        return BundleUtils.d;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final SharedPreferences getSharedPreferences(String str, int i) {
        return xz.a.getSharedPreferences(str, i);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final Object getSystemService(String str) {
        return super.getSystemService(str);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if ((this.E.d() ? 32 : 16) != (configuration.uiMode & 48)) {
            getTheme().rebase();
        }
        xs0 xs0Var = xs0.d;
        if (xs0Var.c) {
            Configuration configurationA = xs0Var.a(this);
            Resources resources = getResources();
            resources.updateConfiguration(configurationA, resources.getDisplayMetrics());
            LocaleList.setDefault(configurationA.getLocales());
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Object obj = BundleUtils.a;
        if (bundle != null) {
            BundleUtils.e = bundle.getStringArrayList("split_compat_loaded_splits");
        }
        this.D.r(E1());
        G1();
        this.E.b(this);
        super.onCreate(bundle);
        int i = 1;
        if (bq.d.a()) {
            Resources.Theme theme = getTheme();
            int i2 = R$style.SurfaceColorsThemeOverlay;
            theme.applyStyle(i2, true);
            this.F.add(Integer.valueOf(i2));
        }
        bf0.a(this);
        g80.b().a(new qe3(i));
        xs0 xs0Var = xs0.d;
        if (xs0Var.c) {
            Configuration configurationA = xs0Var.a(this);
            Resources resources = getResources();
            resources.updateConfiguration(configurationA, resources.getDisplayMetrics());
            LocaleList.setDefault(configurationA.getLocales());
        }
        I1();
        Log.i(getClass().getSimpleName(), "  onCreate: ");
        J1();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.E.e(this);
        aq1<bi1> aq1Var = this.D;
        bi1 bi1Var = aq1Var.f;
        if (bi1Var != null) {
            bi1Var.a();
            aq1Var.r(null);
        }
        super.onDestroy();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        super.onMultiWindowModeChanged(z, configuration);
        onMultiWindowModeChanged(z);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        i02.a aVar = this.G;
        if (aVar != null && i == 100 && strArr.length != 0) {
            if (i02.a(strArr)) {
                ik.c cVar = (ik.c) aVar;
                NetworkBlock.c();
                ik.this.c();
                cVar.a.G = null;
            } else {
                View viewInflate = LayoutInflater.from(this).inflate(R$layout.dialog_quest_permission_fail, (ViewGroup) null);
                Button button = (Button) viewInflate.findViewById(R$id.btn_cancel);
                Button button2 = (Button) viewInflate.findViewById(R$id.btn_sure);
                LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R$id.ll_sure_cancel);
                int i2 = 0;
                if (n3.Q0()) {
                    linearLayout.setLayoutDirection(1);
                } else {
                    linearLayout.setLayoutDirection(0);
                }
                Dialog dialog = new Dialog(this, R$style.Dialog_Style);
                i02.a = dialog;
                dialog.setContentView(viewInflate);
                i02.a.setCancelable(false);
                Window window = i02.a.getWindow();
                window.setGravity(17);
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.width = getResources().getDimensionPixelSize(R$dimen.alert_dialog_max_width);
                attributes.height = -2;
                window.setAttributes(attributes);
                i02.a.setCanceledOnTouchOutside(false);
                i02.a.show();
                button.setOnClickListener(new View.OnClickListener() { // from class: g02
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        xz.b(true);
                    }
                });
                button2.setOnClickListener(new h02(this, i2));
            }
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            if (BundleUtils.d == null) {
                BundleUtils.d = new BundleUtils.a();
            }
            BundleUtils.a aVar = BundleUtils.d;
            bundle.setClassLoader(aVar);
            Bundle bundle2 = bundle.getBundle("android:viewHierarchyState");
            if (bundle2 != null) {
                bundle2.setClassLoader(aVar);
            }
        }
        super.onRestoreInstanceState(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        i02.a(ik.q);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Object obj = BundleUtils.a;
        bundle.putStringArrayList("split_compat_loaded_splits", new ArrayList<>(BundleUtils.c.keySet()));
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        ik.g().d(this, false);
        super.onStart();
        ik.g().m();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        ik.g().d(this, true);
        super.onStop();
        if (H1()) {
            boolean zHasVisibleActivities = ApplicationStatus.hasVisibleActivities();
            if (isInMultiWindowMode() || zHasVisibleActivities || z7.M(this)) {
                return;
            }
            Log.e("activity ", getClass().getSimpleName().concat("  onStop hasVisibleActivities=false  +finish();"));
            finish();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i) {
        super.setTheme(i);
        this.F.add(Integer.valueOf(i));
    }

    public void G1() {
    }
}
