package com.vivid.spotify;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.vivid.spotify.b;
import com.vivid.spotify.nowplaying.NowPlayingViewModel;
import com.vivid.spotify.rootui.MainUIViewModel;
import f0.c;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends com.vivid.spotify.c {
    MainViewModel C;
    MainUIViewModel D;
    NowPlayingViewModel E;
    d4.a F;
    f4.m G;
    i3.m H;
    y3.b I;
    c0 J;
    z K;
    com.vivid.spotify.b L;
    private final Application.ActivityLifecycleCallbacks M = new a();
    private int N;
    private long O;

    class a implements Application.ActivityLifecycleCallbacks {
        a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (activity instanceof MainActivity) {
                MainActivity.this.getApplication().unregisterActivityLifecycleCallbacks(this);
                ((MainUIViewModel) androidx.lifecycle.c0.e(MainActivity.this).a(MainUIViewModel.class)).needLogin.r(false);
                h3.i.b("MainActivity").b("set needLogin is false", new Object[0]);
            }
        }
    }

    class b implements androidx.lifecycle.u<b.a> {
        b() {
        }

        @Override // androidx.lifecycle.u
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void c(b.a aVar) {
            h3.i.b("MainActivity").b("command " + aVar.getValue(), new Object[0]);
            if (e.f5188a[aVar.ordinal()] != 1) {
                return;
            }
            MainActivity.this.E.showPage(true);
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            com.vivid.spotify.nowplaying.g gVar = (com.vivid.spotify.nowplaying.g) MainActivity.this.v().g0(t.now_playing_page);
            if (gVar != null) {
                gVar.U1();
            }
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h3.i.b("MainActivity").i("after toggleDriveMode isEnableDriveMode:%s", Boolean.valueOf(!MainActivity.this.K.a()));
            Process.killProcess(Process.myPid());
        }
    }

    static /* synthetic */ class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f5188a;

        static {
            int[] iArr = new int[b.a.values().length];
            f5188a = iArr;
            try {
                iArr[b.a.COMMAND_OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5188a[b.a.COMMAND_CLOSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void T() {
        if (this.J.a()) {
            h3.i.b("MainActivity").b("autoplay the user has inputted", new Object[0]);
            return;
        }
        Application application = getApplication();
        if (application instanceof com.vivid.spotify.common.base.e) {
            boolean zH = ((com.vivid.spotify.common.base.e) application).h();
            h3.i.b("MainActivity").b("a " + zH, new Object[0]);
            if (zH) {
                if (TextUtils.isEmpty(this.H.n().m("android.media.metadata.MEDIA_URI"))) {
                    h3.i.b("MainActivity").b("media is invalid ", new Object[0]);
                    return;
                }
                boolean zX = this.H.x();
                h3.i.b("MainActivity").b("autoplay isPlaying " + zX, new Object[0]);
                if (zX) {
                    return;
                }
                boolean zI = this.H.I();
                h3.i.b("MainActivity").b("excute  " + zI, new Object[0]);
            }
        }
    }

    private void U() {
        try {
            getPackageManager().getServiceInfo(new ComponentName(getApplication().getPackageName(), "com.vivid.aaosaudioconnect.VividNotificationListenerService"), 131072);
            h3.i.b("MainActivity").b("checkPermission:%s", Boolean.valueOf(V()));
        } catch (Throwable th) {
            h3.i.b("MainActivity").e(th, "get VividNotificationListenerService error", new Object[0]);
        }
    }

    private boolean V() {
        if (!Y()) {
            boolean z6 = !Z();
            h3.i.b("MainActivity").b("needNotificationServicePermission:%s", Boolean.valueOf(z6));
            if (z6) {
                startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
                return false;
            }
        }
        return true;
    }

    private void W() {
        ComponentCallbacks2 application = getApplication();
        if (!(application instanceof com.vivid.spotify.common.base.l) || ((com.vivid.spotify.common.base.l) application).d()) {
            return;
        }
        h3.i.b("MainActivity").b("checkPermission startActivity", new Object[0]);
        startActivity(new Intent(this, (Class<?>) PermissionActivity.class));
    }

    private void X() {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        Application application = getApplication();
        if (application instanceof com.vivid.spotify.common.base.e) {
            ((com.vivid.spotify.common.base.e) application).p(point.x);
        }
    }

    private boolean Y() {
        boolean z6 = x.a.a(this, "android.permission.MEDIA_CONTENT_CONTROL") == 0;
        h3.i.b("MainActivity").g("a isPermissionGranted : " + z6);
        return z6;
    }

    private boolean Z() {
        String string = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        if (!TextUtils.isEmpty(string)) {
            for (String str : string.split(":")) {
                ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
                h3.i.b("MainActivity").i("ComponentName:%s", componentNameUnflattenFromString);
                if (componentNameUnflattenFromString != null) {
                    h3.i.b("MainActivity").i("cn class name:%s", componentNameUnflattenFromString.getClassName());
                    if (TextUtils.equals("com.vivid.aaosaudioconnect.VividNotificationListenerService", componentNameUnflattenFromString.getClassName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean a0(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i7 = iArr[0];
        int i8 = iArr[1];
        int height = view.getHeight() + i8;
        int width = view.getWidth() + i7;
        if (motionEvent.getY() > i8 && motionEvent.getY() < height && motionEvent.getX() > i7 && motionEvent.getX() < width) {
            return false;
        }
        view.clearFocus();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean b0() {
        return this.D.needShowSplash.q();
    }

    private void d0() {
        Application application = getApplication();
        if ((application instanceof com.vivid.spotify.common.base.e) && ((com.vivid.spotify.common.base.e) application).m()) {
            this.I.d(getString(x.wifi_notification), 1);
        }
    }

    public void c0(boolean z6) {
        View viewFindViewById = findViewById(t.rl_container);
        if (viewFindViewById != null) {
            BottomSheetBehavior.c0(viewFindViewById).k0(z6);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        super.dispatchTouchEvent(motionEvent);
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (a0(currentFocus, motionEvent)) {
                ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void exitNowPlayingPage(View view) {
        this.E.showPage(false);
        s4.a.a().c(new c(), 200L, TimeUnit.MILLISECONDS);
    }

    public void gotoNowPlayingFragment(View view) {
        this.E.showPage(true);
    }

    public void login(View view) {
        h3.i.b("MainActivity").g("on 2");
        if (this.K.b()) {
            this.I.c(getString(x.safety_subtitle));
            return;
        }
        if (!this.G.p(this)) {
            h3.i.b("MainActivity").g("2 failed");
            this.D.refreshRoot(true);
        } else {
            d0();
            getApplication().unregisterActivityLifecycleCallbacks(this.M);
            getApplication().registerActivityLifecycleCallbacks(this.M);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Fragment fragmentG0 = v().g0(t.now_playing_page);
        h3.l lVarB = h3.i.b("MainActivity");
        StringBuilder sb = new StringBuilder();
        sb.append("nowPlayingRouteFragment:");
        sb.append(fragmentG0);
        sb.append(", a :");
        sb.append(fragmentG0 == null || fragmentG0.d0());
        sb.append("  show flag  : ");
        sb.append(this.E.observeShowPage().e());
        lVarB.b(sb.toString(), new Object[0]);
        if (fragmentG0 instanceof com.vivid.spotify.nowplaying.g) {
            com.vivid.spotify.nowplaying.g gVar = (com.vivid.spotify.nowplaying.g) fragmentG0;
            if (this.E.observeShowPage().e().booleanValue()) {
                gVar.X1();
                return;
            }
        }
        if (c().c()) {
            c().d();
        } else {
            h3.i.b("MainActivity").b("current is root", new Object[0]);
            moveTaskToBack(true);
        }
    }

    public void onClickSetting(View view) {
        h3.i.b("MainActivity").g("on 1");
        if (this.K.b()) {
            this.I.c(getString(x.safety_subtitle));
        } else {
            this.G.q(this);
        }
    }

    @Override // androidx.appcompat.app.c, androidx.fragment.app.d, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        X();
    }

    @Override // androidx.fragment.app.d, androidx.activity.ComponentActivity, w.f, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        X();
        h3.i.b("MainActivity").b("MainActivity onCreate", new Object[0]);
        this.C = (MainViewModel) new androidx.lifecycle.b0(this).a(MainViewModel.class);
        this.D = (MainUIViewModel) new androidx.lifecycle.b0(this).a(MainUIViewModel.class);
        this.E = (NowPlayingViewModel) new androidx.lifecycle.b0(this).a(NowPlayingViewModel.class);
        d4.a aVarD0 = d4.a.d0(getLayoutInflater());
        this.F = aVarD0;
        aVarD0.W(this);
        setContentView(this.F.G());
        FragmentManager fragmentManagerV = v();
        int i7 = t.now_playing_page;
        if (fragmentManagerV.g0(i7) == null) {
            com.vivid.spotify.nowplaying.g gVarW1 = com.vivid.spotify.nowplaying.g.W1();
            androidx.fragment.app.r rVarL = fragmentManagerV.l();
            rVarL.b(i7, gVarW1);
            rVarL.w(gVarW1);
            rVarL.i();
        }
        this.F.f0((MainUIViewModel) new androidx.lifecycle.b0(this).a(MainUIViewModel.class));
        if (bundle == null) {
            h3.i.b("MainActivity").b("savedInstanceState is null", new Object[0]);
            T();
        }
        this.L.a(this, new b());
        if (Build.VERSION.SDK_INT >= 31) {
            f0.c.c(this).d(new c.d() { // from class: com.vivid.spotify.g
                @Override // f0.c.d
                public final boolean a() {
                    return this.f5311a.b0();
                }
            });
        }
    }

    @Override // androidx.appcompat.app.c, androidx.fragment.app.d, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        getApplication().unregisterActivityLifecycleCallbacks(this.M);
    }

    public void onPlaylistClicked(View view) {
        h3.i.b("MainActivity").g("onPlaylistClicked");
        androidx.navigation.q.a(view).o(t.action_global_queueFragment, new Bundle());
    }

    @Override // androidx.fragment.app.d, android.app.Activity
    protected void onResume() {
        super.onResume();
        U();
        W();
        d0();
    }

    public void toggleDriveMode(View view) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.O >= 1000) {
            this.N = 0;
        }
        this.O = jCurrentTimeMillis;
        this.N++;
        h3.i.b("MainActivity").i("toggleDriveMode count:%s", Integer.valueOf(this.N));
        if (this.N == 4) {
            boolean zA = this.K.a();
            this.K.f(!zA);
            h3.i.b("MainActivity").i("toggleDriveMode isEnableDriveMode:%s", Boolean.valueOf(true ^ zA));
            s4.a.a().c(new d(), 600L, TimeUnit.MILLISECONDS);
        }
    }

    public void onBackPressed(View view) {
        onBackPressed();
    }
}
