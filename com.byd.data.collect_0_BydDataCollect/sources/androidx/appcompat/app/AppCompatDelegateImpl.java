package androidx.appcompat.app;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.appcompat.widget.f0;
import androidx.appcompat.widget.k0;
import androidx.appcompat.widget.l0;
import androidx.appcompat.widget.s;
import androidx.core.content.c.f;
import androidx.lifecycle.d;
import b.a.o.b;
import b.a.o.f;
import b.g.k.b0;
import b.g.k.e;
import b.g.k.t;
import b.g.k.x;
import b.g.k.z;
import java.lang.Thread;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
class AppCompatDelegateImpl extends androidx.appcompat.app.d implements g.a, LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: e */
    private static final b.d.g<String, Integer> f128e = new b.d.g<>();

    /* JADX INFO: renamed from: f */
    private static final boolean f129f;

    /* JADX INFO: renamed from: g */
    private static final int[] f130g;

    /* JADX INFO: renamed from: h */
    private static final boolean f131h;

    /* JADX INFO: renamed from: i */
    private static final boolean f132i;
    private static boolean j;
    private boolean A;
    private boolean B;
    ViewGroup C;
    private TextView D;
    private View E;
    private boolean F;
    private boolean G;
    boolean H;
    boolean I;
    boolean J;
    boolean K;
    boolean L;
    private boolean M;
    private PanelFeatureState[] N;
    private PanelFeatureState O;
    private boolean P;
    private boolean Q;
    private boolean R;
    private boolean S;
    boolean T;
    private int U;
    private int V;
    private boolean W;
    private boolean X;
    private l Y;
    private l Z;
    boolean a0;
    int b0;
    private final Runnable c0;
    private boolean d0;
    private Rect e0;
    private Rect f0;
    private androidx.appcompat.app.f g0;
    final Object k;
    final Context l;
    Window m;
    private j n;
    final androidx.appcompat.app.c o;
    androidx.appcompat.app.a p;
    MenuInflater q;
    private CharSequence r;
    private androidx.appcompat.widget.o s;
    private h t;
    private s u;
    b.a.o.b v;
    ActionBarContextView w;
    PopupWindow x;
    Runnable y;
    x z;

    protected static final class PanelFeatureState {

        /* JADX INFO: renamed from: a */
        int f133a;

        /* JADX INFO: renamed from: b */
        int f134b;

        /* JADX INFO: renamed from: c */
        int f135c;

        /* JADX INFO: renamed from: d */
        int f136d;

        /* JADX INFO: renamed from: e */
        int f137e;

        /* JADX INFO: renamed from: f */
        int f138f;

        /* JADX INFO: renamed from: g */
        ViewGroup f139g;

        /* JADX INFO: renamed from: h */
        View f140h;

        /* JADX INFO: renamed from: i */
        View f141i;
        androidx.appcompat.view.menu.g j;
        androidx.appcompat.view.menu.e k;
        Context l;
        boolean m;
        boolean n;
        boolean o;
        public boolean p;
        boolean q = false;
        boolean r;
        Bundle s;

        @SuppressLint({"BanParcelableUsage"})
        private static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new a();

            /* JADX INFO: renamed from: b */
            int f142b;

            /* JADX INFO: renamed from: c */
            boolean f143c;

            /* JADX INFO: renamed from: d */
            Bundle f144d;

            class a implements Parcelable.ClassLoaderCreator<SavedState> {
                a() {
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: a */
                public SavedState createFromParcel(Parcel parcel) {
                    return SavedState.a(parcel, null);
                }

                @Override // android.os.Parcelable.ClassLoaderCreator
                /* JADX INFO: renamed from: b */
                public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.a(parcel, classLoader);
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: c */
                public SavedState[] newArray(int i2) {
                    return new SavedState[i2];
                }
            }

            SavedState() {
            }

            static SavedState a(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.f142b = parcel.readInt();
                boolean z = parcel.readInt() == 1;
                savedState.f143c = z;
                if (z) {
                    savedState.f144d = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i2) {
                parcel.writeInt(this.f142b);
                parcel.writeInt(this.f143c ? 1 : 0);
                if (this.f143c) {
                    parcel.writeBundle(this.f144d);
                }
            }
        }

        PanelFeatureState(int i2) {
            this.f133a = i2;
        }

        androidx.appcompat.view.menu.n a(m.a aVar) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                androidx.appcompat.view.menu.e eVar = new androidx.appcompat.view.menu.e(this.l, b.a.g.abc_list_menu_item_layout);
                this.k = eVar;
                eVar.k(aVar);
                this.j.b(this.k);
            }
            return this.k.g(this.f139g);
        }

        public boolean b() {
            if (this.f140h == null) {
                return false;
            }
            return this.f141i != null || this.k.b().getCount() > 0;
        }

        void c(androidx.appcompat.view.menu.g gVar) {
            androidx.appcompat.view.menu.e eVar;
            androidx.appcompat.view.menu.g gVar2 = this.j;
            if (gVar == gVar2) {
                return;
            }
            if (gVar2 != null) {
                gVar2.Q(this.k);
            }
            this.j = gVar;
            if (gVar == null || (eVar = this.k) == null) {
                return;
            }
            gVar.b(eVar);
        }

        void d(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme themeNewTheme = context.getResources().newTheme();
            themeNewTheme.setTo(context.getTheme());
            themeNewTheme.resolveAttribute(b.a.a.actionBarPopupTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                themeNewTheme.applyStyle(i2, true);
            }
            themeNewTheme.resolveAttribute(b.a.a.panelMenuListTheme, typedValue, true);
            int i3 = typedValue.resourceId;
            if (i3 != 0) {
                themeNewTheme.applyStyle(i3, true);
            } else {
                themeNewTheme.applyStyle(b.a.i.Theme_AppCompat_CompactMenu, true);
            }
            b.a.o.d dVar = new b.a.o.d(context, 0);
            dVar.getTheme().setTo(themeNewTheme);
            this.l = dVar;
            TypedArray typedArrayObtainStyledAttributes = dVar.obtainStyledAttributes(b.a.j.AppCompatTheme);
            this.f134b = typedArrayObtainStyledAttributes.getResourceId(b.a.j.AppCompatTheme_panelBackground, 0);
            this.f138f = typedArrayObtainStyledAttributes.getResourceId(b.a.j.AppCompatTheme_android_windowAnimationStyle, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    class a implements Thread.UncaughtExceptionHandler {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ Thread.UncaughtExceptionHandler f145a;

        a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.f145a = uncaughtExceptionHandler;
        }

        private boolean a(Throwable th) {
            String message;
            if (!(th instanceof Resources.NotFoundException) || (message = th.getMessage()) == null) {
                return false;
            }
            return message.contains("drawable") || message.contains("Drawable");
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            if (!a(th)) {
                this.f145a.uncaughtException(thread, th);
                return;
            }
            Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            notFoundException.initCause(th.getCause());
            notFoundException.setStackTrace(th.getStackTrace());
            this.f145a.uncaughtException(thread, notFoundException);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl.b0 & 1) != 0) {
                appCompatDelegateImpl.V(0);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl2.b0 & 4096) != 0) {
                appCompatDelegateImpl2.V(108);
            }
            AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl3.a0 = false;
            appCompatDelegateImpl3.b0 = 0;
        }
    }

    class c implements b.g.k.p {
        c() {
        }

        @Override // b.g.k.p
        public b0 a(View view, b0 b0Var) {
            int i2 = b0Var.i();
            int iM0 = AppCompatDelegateImpl.this.M0(b0Var, null);
            if (i2 != iM0) {
                b0Var = b0Var.o(b0Var.g(), iM0, b0Var.h(), b0Var.f());
            }
            return t.W(view, b0Var);
        }
    }

    class d implements s.a {
        d() {
        }

        @Override // androidx.appcompat.widget.s.a
        public void a(Rect rect) {
            rect.top = AppCompatDelegateImpl.this.M0(null, rect);
        }
    }

    class e implements ContentFrameLayout.a {
        e() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void a() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void onDetachedFromWindow() {
            AppCompatDelegateImpl.this.T();
        }
    }

    class f implements Runnable {

        class a extends z {
            a() {
            }

            @Override // b.g.k.y
            public void a(View view) {
                AppCompatDelegateImpl.this.w.setAlpha(1.0f);
                AppCompatDelegateImpl.this.z.f(null);
                AppCompatDelegateImpl.this.z = null;
            }

            @Override // b.g.k.z, b.g.k.y
            public void b(View view) {
                AppCompatDelegateImpl.this.w.setVisibility(0);
            }
        }

        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            appCompatDelegateImpl.x.showAtLocation(appCompatDelegateImpl.w, 55, 0, 0);
            AppCompatDelegateImpl.this.W();
            if (!AppCompatDelegateImpl.this.E0()) {
                AppCompatDelegateImpl.this.w.setAlpha(1.0f);
                AppCompatDelegateImpl.this.w.setVisibility(0);
            } else {
                AppCompatDelegateImpl.this.w.setAlpha(0.0f);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.z = t.c(appCompatDelegateImpl2.w).a(1.0f);
                AppCompatDelegateImpl.this.z.f(new a());
            }
        }
    }

    class g extends z {
        g() {
        }

        @Override // b.g.k.y
        public void a(View view) {
            AppCompatDelegateImpl.this.w.setAlpha(1.0f);
            AppCompatDelegateImpl.this.z.f(null);
            AppCompatDelegateImpl.this.z = null;
        }

        @Override // b.g.k.z, b.g.k.y
        public void b(View view) {
            AppCompatDelegateImpl.this.w.setVisibility(0);
            AppCompatDelegateImpl.this.w.sendAccessibilityEvent(32);
            if (AppCompatDelegateImpl.this.w.getParent() instanceof View) {
                t.g0((View) AppCompatDelegateImpl.this.w.getParent());
            }
        }
    }

    private final class h implements m.a {
        h() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
            AppCompatDelegateImpl.this.M(gVar);
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean b(androidx.appcompat.view.menu.g gVar) {
            Window.Callback callbackG0 = AppCompatDelegateImpl.this.g0();
            if (callbackG0 == null) {
                return true;
            }
            callbackG0.onMenuOpened(108, gVar);
            return true;
        }
    }

    class i implements b.a {

        /* JADX INFO: renamed from: a */
        private b.a f154a;

        class a extends z {
            a() {
            }

            @Override // b.g.k.y
            public void a(View view) {
                AppCompatDelegateImpl.this.w.setVisibility(8);
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                PopupWindow popupWindow = appCompatDelegateImpl.x;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (appCompatDelegateImpl.w.getParent() instanceof View) {
                    t.g0((View) AppCompatDelegateImpl.this.w.getParent());
                }
                AppCompatDelegateImpl.this.w.removeAllViews();
                AppCompatDelegateImpl.this.z.f(null);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.z = null;
                t.g0(appCompatDelegateImpl2.C);
            }
        }

        public i(b.a aVar) {
            this.f154a = aVar;
        }

        @Override // b.a.o.b.a
        public boolean a(b.a.o.b bVar, Menu menu) {
            t.g0(AppCompatDelegateImpl.this.C);
            return this.f154a.a(bVar, menu);
        }

        @Override // b.a.o.b.a
        public boolean b(b.a.o.b bVar, MenuItem menuItem) {
            return this.f154a.b(bVar, menuItem);
        }

        @Override // b.a.o.b.a
        public boolean c(b.a.o.b bVar, Menu menu) {
            return this.f154a.c(bVar, menu);
        }

        @Override // b.a.o.b.a
        public void d(b.a.o.b bVar) {
            this.f154a.d(bVar);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.x != null) {
                appCompatDelegateImpl.m.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.y);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.w != null) {
                appCompatDelegateImpl2.W();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl3.z = t.c(appCompatDelegateImpl3.w).a(0.0f);
                AppCompatDelegateImpl.this.z.f(new a());
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            androidx.appcompat.app.c cVar = appCompatDelegateImpl4.o;
            if (cVar != null) {
                cVar.n(appCompatDelegateImpl4.v);
            }
            AppCompatDelegateImpl appCompatDelegateImpl5 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl5.v = null;
            t.g0(appCompatDelegateImpl5.C);
        }
    }

    private class k extends l {

        /* JADX INFO: renamed from: c */
        private final PowerManager f158c;

        k(Context context) {
            super();
            this.f158c = (PowerManager) context.getApplicationContext().getSystemService("power");
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        IntentFilter b() {
            if (Build.VERSION.SDK_INT < 21) {
                return null;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        public int c() {
            return (Build.VERSION.SDK_INT < 21 || !this.f158c.isPowerSaveMode()) ? 1 : 2;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        public void d() {
            AppCompatDelegateImpl.this.G();
        }
    }

    abstract class l {

        /* JADX INFO: renamed from: a */
        private BroadcastReceiver f160a;

        class a extends BroadcastReceiver {
            a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                l.this.d();
            }
        }

        l() {
        }

        void a() {
            BroadcastReceiver broadcastReceiver = this.f160a;
            if (broadcastReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.l.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.f160a = null;
            }
        }

        abstract IntentFilter b();

        abstract int c();

        abstract void d();

        void e() {
            a();
            IntentFilter intentFilterB = b();
            if (intentFilterB == null || intentFilterB.countActions() == 0) {
                return;
            }
            if (this.f160a == null) {
                this.f160a = new a();
            }
            AppCompatDelegateImpl.this.l.registerReceiver(this.f160a, intentFilterB);
        }
    }

    private class m extends l {

        /* JADX INFO: renamed from: c */
        private final androidx.appcompat.app.j f163c;

        m(androidx.appcompat.app.j jVar) {
            super();
            this.f163c = jVar;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        public int c() {
            return this.f163c.d() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        public void d() {
            AppCompatDelegateImpl.this.G();
        }
    }

    static class n {
        static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i2 = configuration.densityDpi;
            int i3 = configuration2.densityDpi;
            if (i2 != i3) {
                configuration3.densityDpi = i3;
            }
        }
    }

    static class o {
        static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            LocaleList locales = configuration.getLocales();
            LocaleList locales2 = configuration2.getLocales();
            if (locales.equals(locales2)) {
                return;
            }
            configuration3.setLocales(locales2);
            configuration3.locale = configuration2.locale;
        }
    }

    static class p {
        static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i2 = configuration.colorMode & 3;
            int i3 = configuration2.colorMode;
            if (i2 != (i3 & 3)) {
                configuration3.colorMode |= i3 & 3;
            }
            int i4 = configuration.colorMode & 12;
            int i5 = configuration2.colorMode;
            if (i4 != (i5 & 12)) {
                configuration3.colorMode |= i5 & 12;
            }
        }
    }

    private static class q {
        static void a(ContextThemeWrapper contextThemeWrapper, Configuration configuration) {
            contextThemeWrapper.applyOverrideConfiguration(configuration);
        }
    }

    private class r extends ContentFrameLayout {
        public r(Context context) {
            super(context);
        }

        private boolean c(int i2, int i3) {
            return i2 < -5 || i3 < -5 || i2 > getWidth() + 5 || i3 > getHeight() + 5;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.U(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !c((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            AppCompatDelegateImpl.this.O(0);
            return true;
        }

        @Override // android.view.View
        public void setBackgroundResource(int i2) {
            setBackgroundDrawable(b.a.k.a.a.d(getContext(), i2));
        }
    }

    private final class s implements m.a {
        s() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
            androidx.appcompat.view.menu.g gVarF = gVar.F();
            boolean z2 = gVarF != gVar;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z2) {
                gVar = gVarF;
            }
            PanelFeatureState panelFeatureStateZ = appCompatDelegateImpl.Z(gVar);
            if (panelFeatureStateZ != null) {
                if (!z2) {
                    AppCompatDelegateImpl.this.P(panelFeatureStateZ, z);
                } else {
                    AppCompatDelegateImpl.this.L(panelFeatureStateZ.f133a, panelFeatureStateZ, gVarF);
                    AppCompatDelegateImpl.this.P(panelFeatureStateZ, true);
                }
            }
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean b(androidx.appcompat.view.menu.g gVar) {
            Window.Callback callbackG0;
            if (gVar != gVar.F()) {
                return true;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (!appCompatDelegateImpl.H || (callbackG0 = appCompatDelegateImpl.g0()) == null || AppCompatDelegateImpl.this.T) {
                return true;
            }
            callbackG0.onMenuOpened(108, gVar);
            return true;
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        boolean z = i2 < 21;
        f129f = z;
        f130g = new int[]{R.attr.windowBackground};
        f131h = !"robolectric".equals(Build.FINGERPRINT);
        f132i = i2 >= 17;
        if (!z || j) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new a(Thread.getDefaultUncaughtExceptionHandler()));
        j = true;
    }

    AppCompatDelegateImpl(Activity activity, androidx.appcompat.app.c cVar) {
        this(activity, null, cVar, activity);
    }

    private boolean A0(PanelFeatureState panelFeatureState, int i2, KeyEvent keyEvent, int i3) {
        androidx.appcompat.view.menu.g gVar;
        boolean zPerformShortcut = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.m || B0(panelFeatureState, keyEvent)) && (gVar = panelFeatureState.j) != null) {
            zPerformShortcut = gVar.performShortcut(i2, keyEvent, i3);
        }
        if (zPerformShortcut && (i3 & 1) == 0 && this.s == null) {
            P(panelFeatureState, true);
        }
        return zPerformShortcut;
    }

    private boolean B0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        androidx.appcompat.widget.o oVar;
        androidx.appcompat.widget.o oVar2;
        androidx.appcompat.widget.o oVar3;
        if (this.T) {
            return false;
        }
        if (panelFeatureState.m) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.O;
        if (panelFeatureState2 != null && panelFeatureState2 != panelFeatureState) {
            P(panelFeatureState2, false);
        }
        Window.Callback callbackG0 = g0();
        if (callbackG0 != null) {
            panelFeatureState.f141i = callbackG0.onCreatePanelView(panelFeatureState.f133a);
        }
        int i2 = panelFeatureState.f133a;
        boolean z = i2 == 0 || i2 == 108;
        if (z && (oVar3 = this.s) != null) {
            oVar3.g();
        }
        if (panelFeatureState.f141i == null && (!z || !(z0() instanceof androidx.appcompat.app.h))) {
            androidx.appcompat.view.menu.g gVar = panelFeatureState.j;
            if (gVar == null || panelFeatureState.r) {
                if (gVar == null && (!k0(panelFeatureState) || panelFeatureState.j == null)) {
                    return false;
                }
                if (z && this.s != null) {
                    if (this.t == null) {
                        this.t = new h();
                    }
                    this.s.b(panelFeatureState.j, this.t);
                }
                panelFeatureState.j.h0();
                if (!callbackG0.onCreatePanelMenu(panelFeatureState.f133a, panelFeatureState.j)) {
                    panelFeatureState.c(null);
                    if (z && (oVar = this.s) != null) {
                        oVar.b(null, this.t);
                    }
                    return false;
                }
                panelFeatureState.r = false;
            }
            panelFeatureState.j.h0();
            Bundle bundle = panelFeatureState.s;
            if (bundle != null) {
                panelFeatureState.j.R(bundle);
                panelFeatureState.s = null;
            }
            if (!callbackG0.onPreparePanel(0, panelFeatureState.f141i, panelFeatureState.j)) {
                if (z && (oVar2 = this.s) != null) {
                    oVar2.b(null, this.t);
                }
                panelFeatureState.j.g0();
                return false;
            }
            boolean z2 = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            panelFeatureState.p = z2;
            panelFeatureState.j.setQwertyMode(z2);
            panelFeatureState.j.g0();
        }
        panelFeatureState.m = true;
        panelFeatureState.n = false;
        this.O = panelFeatureState;
        return true;
    }

    private void C0(boolean z) {
        androidx.appcompat.widget.o oVar = this.s;
        if (oVar == null || !oVar.h() || (ViewConfiguration.get(this.l).hasPermanentMenuKey() && !this.s.c())) {
            PanelFeatureState panelFeatureStateE0 = e0(0, true);
            panelFeatureStateE0.q = true;
            P(panelFeatureStateE0, false);
            y0(panelFeatureStateE0, null);
            return;
        }
        Window.Callback callbackG0 = g0();
        if (this.s.d() && z) {
            this.s.e();
            if (this.T) {
                return;
            }
            callbackG0.onPanelClosed(108, e0(0, true).j);
            return;
        }
        if (callbackG0 == null || this.T) {
            return;
        }
        if (this.a0 && (this.b0 & 1) != 0) {
            this.m.getDecorView().removeCallbacks(this.c0);
            this.c0.run();
        }
        PanelFeatureState panelFeatureStateE02 = e0(0, true);
        androidx.appcompat.view.menu.g gVar = panelFeatureStateE02.j;
        if (gVar == null || panelFeatureStateE02.r || !callbackG0.onPreparePanel(0, panelFeatureStateE02.f141i, gVar)) {
            return;
        }
        callbackG0.onMenuOpened(108, panelFeatureStateE02.j);
        this.s.f();
    }

    private int D0(int i2) {
        if (i2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        }
        if (i2 != 9) {
            return i2;
        }
        Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
        return 109;
    }

    private boolean F0(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.m.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || t.O((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    private boolean H(boolean z) {
        if (this.T) {
            return false;
        }
        int iK = K();
        boolean zK0 = K0(o0(this.l, iK), z);
        if (iK == 0) {
            d0(this.l).e();
        } else {
            l lVar = this.Y;
            if (lVar != null) {
                lVar.a();
            }
        }
        if (iK == 3) {
            c0(this.l).e();
        } else {
            l lVar2 = this.Z;
            if (lVar2 != null) {
                lVar2.a();
            }
        }
        return zK0;
    }

    private void I() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.C.findViewById(R.id.content);
        View decorView = this.m.getDecorView();
        contentFrameLayout.b(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray typedArrayObtainStyledAttributes = this.l.obtainStyledAttributes(b.a.j.AppCompatTheme);
        typedArrayObtainStyledAttributes.getValue(b.a.j.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        typedArrayObtainStyledAttributes.getValue(b.a.j.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        int i2 = b.a.j.AppCompatTheme_windowFixedWidthMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            typedArrayObtainStyledAttributes.getValue(i2, contentFrameLayout.getFixedWidthMajor());
        }
        int i3 = b.a.j.AppCompatTheme_windowFixedWidthMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            typedArrayObtainStyledAttributes.getValue(i3, contentFrameLayout.getFixedWidthMinor());
        }
        int i4 = b.a.j.AppCompatTheme_windowFixedHeightMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            typedArrayObtainStyledAttributes.getValue(i4, contentFrameLayout.getFixedHeightMajor());
        }
        int i5 = b.a.j.AppCompatTheme_windowFixedHeightMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i5)) {
            typedArrayObtainStyledAttributes.getValue(i5, contentFrameLayout.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    private void I0() {
        if (this.B) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private void J(Window window) {
        if (this.m != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof j) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        j jVar = new j(callback);
        this.n = jVar;
        window.setCallback(jVar);
        f0 f0VarU = f0.u(this.l, null, f130g);
        Drawable drawableH = f0VarU.h(0);
        if (drawableH != null) {
            window.setBackgroundDrawable(drawableH);
        }
        f0VarU.w();
        this.m = window;
    }

    private AppCompatActivity J0() {
        for (Context baseContext = this.l; baseContext != null; baseContext = ((ContextWrapper) baseContext).getBaseContext()) {
            if (baseContext instanceof AppCompatActivity) {
                return (AppCompatActivity) baseContext;
            }
            if (!(baseContext instanceof ContextWrapper)) {
                break;
            }
        }
        return null;
    }

    private int K() {
        int i2 = this.U;
        return i2 != -100 ? i2 : androidx.appcompat.app.d.j();
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean K0(int r7, boolean r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.l
            r1 = 0
            android.content.res.Configuration r0 = r6.Q(r0, r7, r1)
            boolean r2 = r6.m0()
            android.content.Context r3 = r6.l
            android.content.res.Resources r3 = r3.getResources()
            android.content.res.Configuration r3 = r3.getConfiguration()
            int r3 = r3.uiMode
            r3 = r3 & 48
            int r0 = r0.uiMode
            r0 = r0 & 48
            r4 = 1
            if (r3 == r0) goto L47
            if (r8 == 0) goto L47
            if (r2 != 0) goto L47
            boolean r8 = r6.Q
            if (r8 == 0) goto L47
            boolean r8 = androidx.appcompat.app.AppCompatDelegateImpl.f131h
            if (r8 != 0) goto L30
            boolean r8 = r6.R
            if (r8 == 0) goto L47
        L30:
            java.lang.Object r8 = r6.k
            boolean r5 = r8 instanceof android.app.Activity
            if (r5 == 0) goto L47
            android.app.Activity r8 = (android.app.Activity) r8
            boolean r8 = r8.isChild()
            if (r8 != 0) goto L47
            java.lang.Object r8 = r6.k
            android.app.Activity r8 = (android.app.Activity) r8
            androidx.core.app.a.j(r8)
            r8 = r4
            goto L48
        L47:
            r8 = 0
        L48:
            if (r8 != 0) goto L50
            if (r3 == r0) goto L50
            r6.L0(r0, r2, r1)
            goto L51
        L50:
            r4 = r8
        L51:
            if (r4 == 0) goto L5e
            java.lang.Object r8 = r6.k
            boolean r0 = r8 instanceof androidx.appcompat.app.AppCompatActivity
            if (r0 == 0) goto L5e
            androidx.appcompat.app.AppCompatActivity r8 = (androidx.appcompat.app.AppCompatActivity) r8
            r8.E(r7)
        L5e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.K0(int, boolean):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void L0(int i2, boolean z, Configuration configuration) {
        Resources resources = this.l.getResources();
        Configuration configuration2 = new Configuration(resources.getConfiguration());
        if (configuration != null) {
            configuration2.updateFrom(configuration);
        }
        configuration2.uiMode = i2 | (resources.getConfiguration().uiMode & (-49));
        resources.updateConfiguration(configuration2, null);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 26) {
            androidx.appcompat.app.g.a(resources);
        }
        int i4 = this.V;
        if (i4 != 0) {
            this.l.setTheme(i4);
            if (i3 >= 23) {
                this.l.getTheme().applyStyle(this.V, true);
            }
        }
        if (z) {
            Object obj = this.k;
            if (obj instanceof Activity) {
                Activity activity = (Activity) obj;
                if (activity instanceof androidx.lifecycle.g) {
                    if (((androidx.lifecycle.g) activity).a().b().a(d.b.STARTED)) {
                        activity.onConfigurationChanged(configuration2);
                    }
                } else if (this.S) {
                    activity.onConfigurationChanged(configuration2);
                }
            }
        }
    }

    private void N() {
        l lVar = this.Y;
        if (lVar != null) {
            lVar.a();
        }
        l lVar2 = this.Z;
        if (lVar2 != null) {
            lVar2.a();
        }
    }

    private void N0(View view) {
        view.setBackgroundColor((t.I(view) & 8192) != 0 ? androidx.core.content.a.b(this.l, b.a.c.abc_decor_view_status_guard_light) : androidx.core.content.a.b(this.l, b.a.c.abc_decor_view_status_guard));
    }

    private Configuration Q(Context context, int i2, Configuration configuration) {
        int i3 = i2 != 1 ? i2 != 2 ? context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i3 | (configuration2.uiMode & (-49));
        return configuration2;
    }

    private ViewGroup R() {
        ViewGroup viewGroup;
        TypedArray typedArrayObtainStyledAttributes = this.l.obtainStyledAttributes(b.a.j.AppCompatTheme);
        int i2 = b.a.j.AppCompatTheme_windowActionBar;
        if (!typedArrayObtainStyledAttributes.hasValue(i2)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(b.a.j.AppCompatTheme_windowNoTitle, false)) {
            z(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(i2, false)) {
            z(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(b.a.j.AppCompatTheme_windowActionBarOverlay, false)) {
            z(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(b.a.j.AppCompatTheme_windowActionModeOverlay, false)) {
            z(10);
        }
        this.K = typedArrayObtainStyledAttributes.getBoolean(b.a.j.AppCompatTheme_android_windowIsFloating, false);
        typedArrayObtainStyledAttributes.recycle();
        Y();
        this.m.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.l);
        if (this.L) {
            viewGroup = this.J ? (ViewGroup) layoutInflaterFrom.inflate(b.a.g.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(b.a.g.abc_screen_simple, (ViewGroup) null);
        } else if (this.K) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(b.a.g.abc_dialog_title_material, (ViewGroup) null);
            this.I = false;
            this.H = false;
        } else if (this.H) {
            TypedValue typedValue = new TypedValue();
            this.l.getTheme().resolveAttribute(b.a.a.actionBarTheme, typedValue, true);
            int i3 = typedValue.resourceId;
            viewGroup = (ViewGroup) LayoutInflater.from(i3 != 0 ? new b.a.o.d(this.l, i3) : this.l).inflate(b.a.g.abc_screen_toolbar, (ViewGroup) null);
            androidx.appcompat.widget.o oVar = (androidx.appcompat.widget.o) viewGroup.findViewById(b.a.f.decor_content_parent);
            this.s = oVar;
            oVar.setWindowCallback(g0());
            if (this.I) {
                this.s.l(109);
            }
            if (this.F) {
                this.s.l(2);
            }
            if (this.G) {
                this.s.l(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.H + ", windowActionBarOverlay: " + this.I + ", android:windowIsFloating: " + this.K + ", windowActionModeOverlay: " + this.J + ", windowNoTitle: " + this.L + " }");
        }
        if (Build.VERSION.SDK_INT >= 21) {
            t.v0(viewGroup, new c());
        } else if (viewGroup instanceof androidx.appcompat.widget.s) {
            ((androidx.appcompat.widget.s) viewGroup).setOnFitSystemWindowsListener(new d());
        }
        if (this.s == null) {
            this.D = (TextView) viewGroup.findViewById(b.a.f.title);
        }
        l0.c(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(b.a.f.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.m.findViewById(R.id.content);
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup2.setId(-1);
            contentFrameLayout.setId(R.id.content);
            if (viewGroup2 instanceof FrameLayout) {
                ((FrameLayout) viewGroup2).setForeground(null);
            }
        }
        this.m.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new e());
        return viewGroup;
    }

    private void X() {
        if (this.B) {
            return;
        }
        this.C = R();
        CharSequence charSequenceF0 = f0();
        if (!TextUtils.isEmpty(charSequenceF0)) {
            androidx.appcompat.widget.o oVar = this.s;
            if (oVar != null) {
                oVar.setWindowTitle(charSequenceF0);
            } else if (z0() != null) {
                z0().t(charSequenceF0);
            } else {
                TextView textView = this.D;
                if (textView != null) {
                    textView.setText(charSequenceF0);
                }
            }
        }
        I();
        x0(this.C);
        this.B = true;
        PanelFeatureState panelFeatureStateE0 = e0(0, false);
        if (this.T) {
            return;
        }
        if (panelFeatureStateE0 == null || panelFeatureStateE0.j == null) {
            l0(108);
        }
    }

    private void Y() {
        if (this.m == null) {
            Object obj = this.k;
            if (obj instanceof Activity) {
                J(((Activity) obj).getWindow());
            }
        }
        if (this.m == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    private static Configuration a0(Configuration configuration, Configuration configuration2) {
        Configuration configuration3 = new Configuration();
        configuration3.fontScale = 0.0f;
        if (configuration2 != null && configuration.diff(configuration2) != 0) {
            float f2 = configuration.fontScale;
            float f3 = configuration2.fontScale;
            if (f2 != f3) {
                configuration3.fontScale = f3;
            }
            int i2 = configuration.mcc;
            int i3 = configuration2.mcc;
            if (i2 != i3) {
                configuration3.mcc = i3;
            }
            int i4 = configuration.mnc;
            int i5 = configuration2.mnc;
            if (i4 != i5) {
                configuration3.mnc = i5;
            }
            int i6 = Build.VERSION.SDK_INT;
            if (i6 >= 24) {
                o.a(configuration, configuration2, configuration3);
            } else if (!b.g.j.c.a(configuration.locale, configuration2.locale)) {
                configuration3.locale = configuration2.locale;
            }
            int i7 = configuration.touchscreen;
            int i8 = configuration2.touchscreen;
            if (i7 != i8) {
                configuration3.touchscreen = i8;
            }
            int i9 = configuration.keyboard;
            int i10 = configuration2.keyboard;
            if (i9 != i10) {
                configuration3.keyboard = i10;
            }
            int i11 = configuration.keyboardHidden;
            int i12 = configuration2.keyboardHidden;
            if (i11 != i12) {
                configuration3.keyboardHidden = i12;
            }
            int i13 = configuration.navigation;
            int i14 = configuration2.navigation;
            if (i13 != i14) {
                configuration3.navigation = i14;
            }
            int i15 = configuration.navigationHidden;
            int i16 = configuration2.navigationHidden;
            if (i15 != i16) {
                configuration3.navigationHidden = i16;
            }
            int i17 = configuration.orientation;
            int i18 = configuration2.orientation;
            if (i17 != i18) {
                configuration3.orientation = i18;
            }
            int i19 = configuration.screenLayout & 15;
            int i20 = configuration2.screenLayout;
            if (i19 != (i20 & 15)) {
                configuration3.screenLayout |= i20 & 15;
            }
            int i21 = configuration.screenLayout & 192;
            int i22 = configuration2.screenLayout;
            if (i21 != (i22 & 192)) {
                configuration3.screenLayout |= i22 & 192;
            }
            int i23 = configuration.screenLayout & 48;
            int i24 = configuration2.screenLayout;
            if (i23 != (i24 & 48)) {
                configuration3.screenLayout |= i24 & 48;
            }
            int i25 = configuration.screenLayout & 768;
            int i26 = configuration2.screenLayout;
            if (i25 != (i26 & 768)) {
                configuration3.screenLayout |= i26 & 768;
            }
            if (i6 >= 26) {
                p.a(configuration, configuration2, configuration3);
            }
            int i27 = configuration.uiMode & 15;
            int i28 = configuration2.uiMode;
            if (i27 != (i28 & 15)) {
                configuration3.uiMode |= i28 & 15;
            }
            int i29 = configuration.uiMode & 48;
            int i30 = configuration2.uiMode;
            if (i29 != (i30 & 48)) {
                configuration3.uiMode |= i30 & 48;
            }
            int i31 = configuration.screenWidthDp;
            int i32 = configuration2.screenWidthDp;
            if (i31 != i32) {
                configuration3.screenWidthDp = i32;
            }
            int i33 = configuration.screenHeightDp;
            int i34 = configuration2.screenHeightDp;
            if (i33 != i34) {
                configuration3.screenHeightDp = i34;
            }
            int i35 = configuration.smallestScreenWidthDp;
            int i36 = configuration2.smallestScreenWidthDp;
            if (i35 != i36) {
                configuration3.smallestScreenWidthDp = i36;
            }
            if (i6 >= 17) {
                n.a(configuration, configuration2, configuration3);
            }
        }
        return configuration3;
    }

    private l c0(Context context) {
        if (this.Z == null) {
            this.Z = new k(context);
        }
        return this.Z;
    }

    private l d0(Context context) {
        if (this.Y == null) {
            this.Y = new m(androidx.appcompat.app.j.a(context));
        }
        return this.Y;
    }

    private void h0() {
        X();
        if (this.H && this.p == null) {
            Object obj = this.k;
            if (obj instanceof Activity) {
                this.p = new androidx.appcompat.app.k((Activity) this.k, this.I);
            } else if (obj instanceof Dialog) {
                this.p = new androidx.appcompat.app.k((Dialog) this.k);
            }
            androidx.appcompat.app.a aVar = this.p;
            if (aVar != null) {
                aVar.r(this.d0);
            }
        }
    }

    private boolean i0(PanelFeatureState panelFeatureState) {
        View view = panelFeatureState.f141i;
        if (view != null) {
            panelFeatureState.f140h = view;
            return true;
        }
        if (panelFeatureState.j == null) {
            return false;
        }
        if (this.u == null) {
            this.u = new s();
        }
        View view2 = (View) panelFeatureState.a(this.u);
        panelFeatureState.f140h = view2;
        return view2 != null;
    }

    private boolean j0(PanelFeatureState panelFeatureState) {
        panelFeatureState.d(b0());
        panelFeatureState.f139g = new r(panelFeatureState.l);
        panelFeatureState.f135c = 81;
        return true;
    }

    private boolean k0(PanelFeatureState panelFeatureState) {
        Context context = this.l;
        int i2 = panelFeatureState.f133a;
        if ((i2 == 0 || i2 == 108) && this.s != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(b.a.a.actionBarTheme, typedValue, true);
            Resources.Theme themeNewTheme = null;
            if (typedValue.resourceId != 0) {
                themeNewTheme = context.getResources().newTheme();
                themeNewTheme.setTo(theme);
                themeNewTheme.applyStyle(typedValue.resourceId, true);
                themeNewTheme.resolveAttribute(b.a.a.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(b.a.a.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (themeNewTheme == null) {
                    themeNewTheme = context.getResources().newTheme();
                    themeNewTheme.setTo(theme);
                }
                themeNewTheme.applyStyle(typedValue.resourceId, true);
            }
            if (themeNewTheme != null) {
                b.a.o.d dVar = new b.a.o.d(context, 0);
                dVar.getTheme().setTo(themeNewTheme);
                context = dVar;
            }
        }
        androidx.appcompat.view.menu.g gVar = new androidx.appcompat.view.menu.g(context);
        gVar.V(this);
        panelFeatureState.c(gVar);
        return true;
    }

    private void l0(int i2) {
        this.b0 = (1 << i2) | this.b0;
        if (this.a0) {
            return;
        }
        t.b0(this.m.getDecorView(), this.c0);
        this.a0 = true;
    }

    private boolean m0() {
        if (!this.X && (this.k instanceof Activity)) {
            PackageManager packageManager = this.l.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            try {
                int i2 = Build.VERSION.SDK_INT;
                ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(this.l, this.k.getClass()), i2 >= 29 ? 269221888 : i2 >= 24 ? 786432 : 0);
                this.W = (activityInfo == null || (activityInfo.configChanges & 512) == 0) ? false : true;
            } catch (PackageManager.NameNotFoundException e2) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e2);
                this.W = false;
            }
        }
        this.X = true;
        return this.W;
    }

    private boolean r0(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        PanelFeatureState panelFeatureStateE0 = e0(i2, true);
        if (panelFeatureStateE0.o) {
            return false;
        }
        return B0(panelFeatureStateE0, keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:76:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean u0(int r5, android.view.KeyEvent r6) {
        /*
            r4 = this;
            b.a.o.b r0 = r4.v
            r1 = 0
            if (r0 == 0) goto L6
            return r1
        L6:
            r0 = 1
            androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r2 = r4.e0(r5, r0)
            if (r5 != 0) goto L43
            androidx.appcompat.widget.o r5 = r4.s
            if (r5 == 0) goto L43
            boolean r5 = r5.h()
            if (r5 == 0) goto L43
            android.content.Context r5 = r4.l
            android.view.ViewConfiguration r5 = android.view.ViewConfiguration.get(r5)
            boolean r5 = r5.hasPermanentMenuKey()
            if (r5 != 0) goto L43
            androidx.appcompat.widget.o r5 = r4.s
            boolean r5 = r5.d()
            if (r5 != 0) goto L3c
            boolean r5 = r4.T
            if (r5 != 0) goto L62
            boolean r5 = r4.B0(r2, r6)
            if (r5 == 0) goto L62
            androidx.appcompat.widget.o r5 = r4.s
            boolean r0 = r5.f()
            goto L68
        L3c:
            androidx.appcompat.widget.o r5 = r4.s
            boolean r0 = r5.e()
            goto L68
        L43:
            boolean r5 = r2.o
            if (r5 != 0) goto L64
            boolean r3 = r2.n
            if (r3 == 0) goto L4c
            goto L64
        L4c:
            boolean r5 = r2.m
            if (r5 == 0) goto L62
            boolean r5 = r2.r
            if (r5 == 0) goto L5b
            r2.m = r1
            boolean r5 = r4.B0(r2, r6)
            goto L5c
        L5b:
            r5 = r0
        L5c:
            if (r5 == 0) goto L62
            r4.y0(r2, r6)
            goto L68
        L62:
            r0 = r1
            goto L68
        L64:
            r4.P(r2, r0)
            r0 = r5
        L68:
            if (r0 == 0) goto L85
            android.content.Context r5 = r4.l
            android.content.Context r5 = r5.getApplicationContext()
            java.lang.String r6 = "audio"
            java.lang.Object r5 = r5.getSystemService(r6)
            android.media.AudioManager r5 = (android.media.AudioManager) r5
            if (r5 == 0) goto L7e
            r5.playSoundEffect(r1)
            goto L85
        L7e:
            java.lang.String r5 = "AppCompatDelegate"
            java.lang.String r6 = "Couldn't get audio manager"
            android.util.Log.w(r5, r6)
        L85:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.u0(int, android.view.KeyEvent):boolean");
    }

    private void y0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        int i2;
        ViewGroup.LayoutParams layoutParams;
        if (panelFeatureState.o || this.T) {
            return;
        }
        if (panelFeatureState.f133a == 0) {
            if ((this.l.getResources().getConfiguration().screenLayout & 15) == 4) {
                return;
            }
        }
        Window.Callback callbackG0 = g0();
        if (callbackG0 != null && !callbackG0.onMenuOpened(panelFeatureState.f133a, panelFeatureState.j)) {
            P(panelFeatureState, true);
            return;
        }
        WindowManager windowManager = (WindowManager) this.l.getSystemService("window");
        if (windowManager != null && B0(panelFeatureState, keyEvent)) {
            ViewGroup viewGroup = panelFeatureState.f139g;
            if (viewGroup != null && !panelFeatureState.q) {
                View view = panelFeatureState.f141i;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i2 = -1;
                }
                panelFeatureState.n = false;
                WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i2, -2, panelFeatureState.f136d, panelFeatureState.f137e, 1002, 8519680, -3);
                layoutParams2.gravity = panelFeatureState.f135c;
                layoutParams2.windowAnimations = panelFeatureState.f138f;
                windowManager.addView(panelFeatureState.f139g, layoutParams2);
                panelFeatureState.o = true;
            }
            if (viewGroup == null) {
                if (!j0(panelFeatureState) || panelFeatureState.f139g == null) {
                    return;
                }
            } else if (panelFeatureState.q && viewGroup.getChildCount() > 0) {
                panelFeatureState.f139g.removeAllViews();
            }
            if (!i0(panelFeatureState) || !panelFeatureState.b()) {
                panelFeatureState.q = true;
                return;
            }
            ViewGroup.LayoutParams layoutParams3 = panelFeatureState.f140h.getLayoutParams();
            if (layoutParams3 == null) {
                layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
            }
            panelFeatureState.f139g.setBackgroundResource(panelFeatureState.f134b);
            ViewParent parent = panelFeatureState.f140h.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(panelFeatureState.f140h);
            }
            panelFeatureState.f139g.addView(panelFeatureState.f140h, layoutParams3);
            if (!panelFeatureState.f140h.hasFocus()) {
                panelFeatureState.f140h.requestFocus();
            }
            i2 = -2;
            panelFeatureState.n = false;
            WindowManager.LayoutParams layoutParams22 = new WindowManager.LayoutParams(i2, -2, panelFeatureState.f136d, panelFeatureState.f137e, 1002, 8519680, -3);
            layoutParams22.gravity = panelFeatureState.f135c;
            layoutParams22.windowAnimations = panelFeatureState.f138f;
            windowManager.addView(panelFeatureState.f139g, layoutParams22);
            panelFeatureState.o = true;
        }
    }

    @Override // androidx.appcompat.app.d
    public void A(int i2) {
        X();
        ViewGroup viewGroup = (ViewGroup) this.C.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.l).inflate(i2, viewGroup);
        this.n.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.d
    public void B(View view) {
        X();
        ViewGroup viewGroup = (ViewGroup) this.C.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.n.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.d
    public void C(View view, ViewGroup.LayoutParams layoutParams) {
        X();
        ViewGroup viewGroup = (ViewGroup) this.C.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.n.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.d
    public void D(Toolbar toolbar) {
        if (this.k instanceof Activity) {
            androidx.appcompat.app.a aVarM = m();
            if (aVarM instanceof androidx.appcompat.app.k) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            this.q = null;
            if (aVarM != null) {
                aVarM.n();
            }
            if (toolbar != null) {
                androidx.appcompat.app.h hVar = new androidx.appcompat.app.h(toolbar, f0(), this.n);
                this.p = hVar;
                this.m.setCallback(hVar.w());
            } else {
                this.p = null;
                this.m.setCallback(this.n);
            }
            o();
        }
    }

    @Override // androidx.appcompat.app.d
    public void E(int i2) {
        this.V = i2;
    }

    final boolean E0() {
        ViewGroup viewGroup;
        return this.B && (viewGroup = this.C) != null && t.P(viewGroup);
    }

    @Override // androidx.appcompat.app.d
    public final void F(CharSequence charSequence) {
        this.r = charSequence;
        androidx.appcompat.widget.o oVar = this.s;
        if (oVar != null) {
            oVar.setWindowTitle(charSequence);
            return;
        }
        if (z0() != null) {
            z0().t(charSequence);
            return;
        }
        TextView textView = this.D;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public boolean G() {
        return H(true);
    }

    public b.a.o.b G0(b.a aVar) {
        androidx.appcompat.app.c cVar;
        if (aVar == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        b.a.o.b bVar = this.v;
        if (bVar != null) {
            bVar.c();
        }
        i iVar = new i(aVar);
        androidx.appcompat.app.a aVarM = m();
        if (aVarM != null) {
            b.a.o.b bVarU = aVarM.u(iVar);
            this.v = bVarU;
            if (bVarU != null && (cVar = this.o) != null) {
                cVar.i(bVarU);
            }
        }
        if (this.v == null) {
            this.v = H0(iVar);
        }
        return this.v;
    }

    b.a.o.b H0(b.a aVar) {
        b.a.o.b bVarP;
        Context dVar;
        androidx.appcompat.app.c cVar;
        W();
        b.a.o.b bVar = this.v;
        if (bVar != null) {
            bVar.c();
        }
        if (!(aVar instanceof i)) {
            aVar = new i(aVar);
        }
        androidx.appcompat.app.c cVar2 = this.o;
        if (cVar2 == null || this.T) {
            bVarP = null;
        } else {
            try {
                bVarP = cVar2.p(aVar);
            } catch (AbstractMethodError unused) {
                bVarP = null;
            }
        }
        if (bVarP != null) {
            this.v = bVarP;
        } else {
            if (this.w == null) {
                if (this.K) {
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = this.l.getTheme();
                    theme.resolveAttribute(b.a.a.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Resources.Theme themeNewTheme = this.l.getResources().newTheme();
                        themeNewTheme.setTo(theme);
                        themeNewTheme.applyStyle(typedValue.resourceId, true);
                        dVar = new b.a.o.d(this.l, 0);
                        dVar.getTheme().setTo(themeNewTheme);
                    } else {
                        dVar = this.l;
                    }
                    this.w = new ActionBarContextView(dVar);
                    PopupWindow popupWindow = new PopupWindow(dVar, (AttributeSet) null, b.a.a.actionModePopupWindowStyle);
                    this.x = popupWindow;
                    androidx.core.widget.h.b(popupWindow, 2);
                    this.x.setContentView(this.w);
                    this.x.setWidth(-1);
                    dVar.getTheme().resolveAttribute(b.a.a.actionBarSize, typedValue, true);
                    this.w.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, dVar.getResources().getDisplayMetrics()));
                    this.x.setHeight(-2);
                    this.y = new f();
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.C.findViewById(b.a.f.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(b0()));
                        this.w = (ActionBarContextView) viewStubCompat.a();
                    }
                }
            }
            if (this.w != null) {
                W();
                this.w.k();
                b.a.o.e eVar = new b.a.o.e(this.w.getContext(), this.w, aVar, this.x == null);
                if (aVar.c(eVar, eVar.e())) {
                    eVar.k();
                    this.w.h(eVar);
                    this.v = eVar;
                    if (E0()) {
                        this.w.setAlpha(0.0f);
                        x xVarA = t.c(this.w).a(1.0f);
                        this.z = xVarA;
                        xVarA.f(new g());
                    } else {
                        this.w.setAlpha(1.0f);
                        this.w.setVisibility(0);
                        this.w.sendAccessibilityEvent(32);
                        if (this.w.getParent() instanceof View) {
                            t.g0((View) this.w.getParent());
                        }
                    }
                    if (this.x != null) {
                        this.m.getDecorView().post(this.y);
                    }
                } else {
                    this.v = null;
                }
            }
        }
        b.a.o.b bVar2 = this.v;
        if (bVar2 != null && (cVar = this.o) != null) {
            cVar.i(bVar2);
        }
        return this.v;
    }

    void L(int i2, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i2 >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.N;
                if (i2 < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i2];
                }
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.o) && !this.T) {
            this.n.a().onPanelClosed(i2, menu);
        }
    }

    void M(androidx.appcompat.view.menu.g gVar) {
        if (this.M) {
            return;
        }
        this.M = true;
        this.s.m();
        Window.Callback callbackG0 = g0();
        if (callbackG0 != null && !this.T) {
            callbackG0.onPanelClosed(108, gVar);
        }
        this.M = false;
    }

    final int M0(b0 b0Var, Rect rect) {
        boolean z;
        boolean z2;
        int i2 = b0Var != null ? b0Var.i() : rect != null ? rect.top : 0;
        ActionBarContextView actionBarContextView = this.w;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.w.getLayoutParams();
            if (this.w.isShown()) {
                if (this.e0 == null) {
                    this.e0 = new Rect();
                    this.f0 = new Rect();
                }
                Rect rect2 = this.e0;
                Rect rect3 = this.f0;
                if (b0Var == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(b0Var.g(), b0Var.i(), b0Var.h(), b0Var.f());
                }
                l0.a(this.C, rect2, rect3);
                int i3 = rect2.top;
                int i4 = rect2.left;
                int i5 = rect2.right;
                b0 b0VarG = t.G(this.C);
                int iG = b0VarG == null ? 0 : b0VarG.g();
                int iH = b0VarG == null ? 0 : b0VarG.h();
                if (marginLayoutParams.topMargin == i3 && marginLayoutParams.leftMargin == i4 && marginLayoutParams.rightMargin == i5) {
                    z2 = false;
                } else {
                    marginLayoutParams.topMargin = i3;
                    marginLayoutParams.leftMargin = i4;
                    marginLayoutParams.rightMargin = i5;
                    z2 = true;
                }
                if (i3 <= 0 || this.E != null) {
                    View view = this.E;
                    if (view != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                        int i6 = marginLayoutParams2.height;
                        int i7 = marginLayoutParams.topMargin;
                        if (i6 != i7 || marginLayoutParams2.leftMargin != iG || marginLayoutParams2.rightMargin != iH) {
                            marginLayoutParams2.height = i7;
                            marginLayoutParams2.leftMargin = iG;
                            marginLayoutParams2.rightMargin = iH;
                            this.E.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view2 = new View(this.l);
                    this.E = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = iG;
                    layoutParams.rightMargin = iH;
                    this.C.addView(this.E, -1, layoutParams);
                }
                View view3 = this.E;
                z = view3 != null;
                if (z && view3.getVisibility() != 0) {
                    N0(this.E);
                }
                if (!this.J && z) {
                    i2 = 0;
                }
                z = z;
                z = z2;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z = false;
            } else {
                z = false;
                z = false;
            }
            if (z) {
                this.w.setLayoutParams(marginLayoutParams);
            }
        }
        View view4 = this.E;
        if (view4 != null) {
            view4.setVisibility(z ? 0 : 8);
        }
        return i2;
    }

    void O(int i2) {
        P(e0(i2, true), true);
    }

    void P(PanelFeatureState panelFeatureState, boolean z) {
        ViewGroup viewGroup;
        androidx.appcompat.widget.o oVar;
        if (z && panelFeatureState.f133a == 0 && (oVar = this.s) != null && oVar.d()) {
            M(panelFeatureState.j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.l.getSystemService("window");
        if (windowManager != null && panelFeatureState.o && (viewGroup = panelFeatureState.f139g) != null) {
            windowManager.removeView(viewGroup);
            if (z) {
                L(panelFeatureState.f133a, panelFeatureState, null);
            }
        }
        panelFeatureState.m = false;
        panelFeatureState.n = false;
        panelFeatureState.o = false;
        panelFeatureState.f140h = null;
        panelFeatureState.q = true;
        if (this.O == panelFeatureState) {
            this.O = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View S(View view, String str, Context context, AttributeSet attributeSet) {
        boolean zF0 = false;
        if (this.g0 == null) {
            String string = this.l.obtainStyledAttributes(b.a.j.AppCompatTheme).getString(b.a.j.AppCompatTheme_viewInflaterClass);
            if (string == null) {
                this.g0 = new androidx.appcompat.app.f();
            } else {
                try {
                    this.g0 = (androidx.appcompat.app.f) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.g0 = new androidx.appcompat.app.f();
                }
            }
        }
        boolean z = f129f;
        if (z) {
            if (!(attributeSet instanceof XmlPullParser)) {
                zF0 = F0((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                zF0 = true;
            }
        }
        return this.g0.q(view, str, context, attributeSet, zF0, z, true, k0.b());
    }

    void T() {
        androidx.appcompat.view.menu.g gVar;
        androidx.appcompat.widget.o oVar = this.s;
        if (oVar != null) {
            oVar.m();
        }
        if (this.x != null) {
            this.m.getDecorView().removeCallbacks(this.y);
            if (this.x.isShowing()) {
                try {
                    this.x.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.x = null;
        }
        W();
        PanelFeatureState panelFeatureStateE0 = e0(0, false);
        if (panelFeatureStateE0 == null || (gVar = panelFeatureStateE0.j) == null) {
            return;
        }
        gVar.close();
    }

    boolean U(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.k;
        if (((obj instanceof e.a) || (obj instanceof androidx.appcompat.app.e)) && (decorView = this.m.getDecorView()) != null && b.g.k.e.d(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.n.a().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? q0(keyCode, keyEvent) : t0(keyCode, keyEvent);
    }

    void V(int i2) {
        PanelFeatureState panelFeatureStateE0;
        PanelFeatureState panelFeatureStateE02 = e0(i2, true);
        if (panelFeatureStateE02.j != null) {
            Bundle bundle = new Bundle();
            panelFeatureStateE02.j.T(bundle);
            if (bundle.size() > 0) {
                panelFeatureStateE02.s = bundle;
            }
            panelFeatureStateE02.j.h0();
            panelFeatureStateE02.j.clear();
        }
        panelFeatureStateE02.r = true;
        panelFeatureStateE02.q = true;
        if ((i2 != 108 && i2 != 0) || this.s == null || (panelFeatureStateE0 = e0(0, false)) == null) {
            return;
        }
        panelFeatureStateE0.m = false;
        B0(panelFeatureStateE0, null);
    }

    void W() {
        x xVar = this.z;
        if (xVar != null) {
            xVar.b();
        }
    }

    PanelFeatureState Z(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.N;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
            if (panelFeatureState != null && panelFeatureState.j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    @Override // androidx.appcompat.view.menu.g.a
    public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        PanelFeatureState panelFeatureStateZ;
        Window.Callback callbackG0 = g0();
        if (callbackG0 == null || this.T || (panelFeatureStateZ = Z(gVar.F())) == null) {
            return false;
        }
        return callbackG0.onMenuItemSelected(panelFeatureStateZ.f133a, menuItem);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public void b(androidx.appcompat.view.menu.g gVar) {
        C0(true);
    }

    final Context b0() {
        androidx.appcompat.app.a aVarM = m();
        Context contextK = aVarM != null ? aVarM.k() : null;
        return contextK == null ? this.l : contextK;
    }

    @Override // androidx.appcompat.app.d
    public void d(View view, ViewGroup.LayoutParams layoutParams) {
        X();
        ((ViewGroup) this.C.findViewById(R.id.content)).addView(view, layoutParams);
        this.n.a().onContentChanged();
    }

    protected PanelFeatureState e0(int i2, boolean z) {
        PanelFeatureState[] panelFeatureStateArr = this.N;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i2) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[i2 + 1];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.N = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        PanelFeatureState panelFeatureState2 = new PanelFeatureState(i2);
        panelFeatureStateArr[i2] = panelFeatureState2;
        return panelFeatureState2;
    }

    @Override // androidx.appcompat.app.d
    public Context f(Context context) {
        this.Q = true;
        int iO0 = o0(context, K());
        if (f132i && (context instanceof ContextThemeWrapper)) {
            try {
                q.a((ContextThemeWrapper) context, Q(context, iO0, null));
                return context;
            } catch (IllegalStateException unused) {
            }
        }
        if (context instanceof b.a.o.d) {
            try {
                ((b.a.o.d) context).a(Q(context, iO0, null));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (!f131h) {
            return super.f(context);
        }
        try {
            Configuration configuration = context.getPackageManager().getResourcesForApplication(context.getApplicationInfo()).getConfiguration();
            Configuration configuration2 = context.getResources().getConfiguration();
            Configuration configurationQ = Q(context, iO0, configuration.equals(configuration2) ? null : a0(configuration, configuration2));
            b.a.o.d dVar = new b.a.o.d(context, b.a.i.Theme_AppCompat_Empty);
            dVar.a(configurationQ);
            boolean z = false;
            try {
                z = context.getTheme() != null;
            } catch (NullPointerException unused3) {
            }
            if (z) {
                f.b.a(dVar.getTheme());
            }
            return super.f(dVar);
        } catch (PackageManager.NameNotFoundException e2) {
            throw new RuntimeException("Application failed to obtain resources from itself", e2);
        }
    }

    final CharSequence f0() {
        Object obj = this.k;
        return obj instanceof Activity ? ((Activity) obj).getTitle() : this.r;
    }

    final Window.Callback g0() {
        return this.m.getCallback();
    }

    @Override // androidx.appcompat.app.d
    public <T extends View> T i(int i2) {
        X();
        return (T) this.m.findViewById(i2);
    }

    @Override // androidx.appcompat.app.d
    public int k() {
        return this.U;
    }

    @Override // androidx.appcompat.app.d
    public MenuInflater l() {
        if (this.q == null) {
            h0();
            androidx.appcompat.app.a aVar = this.p;
            this.q = new b.a.o.g(aVar != null ? aVar.k() : this.l);
        }
        return this.q;
    }

    @Override // androidx.appcompat.app.d
    public androidx.appcompat.app.a m() {
        h0();
        return this.p;
    }

    @Override // androidx.appcompat.app.d
    public void n() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.l);
        if (layoutInflaterFrom.getFactory() == null) {
            b.g.k.f.b(layoutInflaterFrom, this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof AppCompatDelegateImpl) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public boolean n0() {
        return this.A;
    }

    @Override // androidx.appcompat.app.d
    public void o() {
        androidx.appcompat.app.a aVarM = m();
        if (aVarM == null || !aVarM.l()) {
            l0(0);
        }
    }

    int o0(Context context, int i2) {
        if (i2 == -100) {
            return -1;
        }
        if (i2 != -1) {
            if (i2 == 0) {
                if (Build.VERSION.SDK_INT < 23 || ((UiModeManager) context.getApplicationContext().getSystemService(UiModeManager.class)).getNightMode() != 0) {
                    return d0(context).c();
                }
                return -1;
            }
            if (i2 != 1 && i2 != 2) {
                if (i2 == 3) {
                    return c0(context).c();
                }
                throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
            }
        }
        return i2;
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return S(view, str, context, attributeSet);
    }

    @Override // androidx.appcompat.app.d
    public void p(Configuration configuration) {
        androidx.appcompat.app.a aVarM;
        if (this.H && this.B && (aVarM = m()) != null) {
            aVarM.m(configuration);
        }
        androidx.appcompat.widget.f.b().g(this.l);
        H(false);
    }

    boolean p0() {
        b.a.o.b bVar = this.v;
        if (bVar != null) {
            bVar.c();
            return true;
        }
        androidx.appcompat.app.a aVarM = m();
        return aVarM != null && aVarM.h();
    }

    @Override // androidx.appcompat.app.d
    public void q(Bundle bundle) {
        this.Q = true;
        H(false);
        Y();
        Object obj = this.k;
        if (obj instanceof Activity) {
            String strC = null;
            try {
                strC = androidx.core.app.e.c((Activity) obj);
            } catch (IllegalArgumentException unused) {
            }
            if (strC != null) {
                androidx.appcompat.app.a aVarZ0 = z0();
                if (aVarZ0 == null) {
                    this.d0 = true;
                } else {
                    aVarZ0.r(true);
                }
            }
            androidx.appcompat.app.d.c(this);
        }
        this.R = true;
    }

    boolean q0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            this.P = (keyEvent.getFlags() & 128) != 0;
        } else if (i2 == 82) {
            r0(0, keyEvent);
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0048  */
    @Override // androidx.appcompat.app.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void r() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.k
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L9
            androidx.appcompat.app.d.x(r3)
        L9:
            boolean r0 = r3.a0
            if (r0 == 0) goto L18
            android.view.Window r0 = r3.m
            android.view.View r0 = r0.getDecorView()
            java.lang.Runnable r1 = r3.c0
            r0.removeCallbacks(r1)
        L18:
            r0 = 0
            r3.S = r0
            r0 = 1
            r3.T = r0
            int r0 = r3.U
            r1 = -100
            if (r0 == r1) goto L48
            java.lang.Object r0 = r3.k
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L48
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L48
            b.d.g<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.f128e
            java.lang.Object r1 = r3.k
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.U
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L57
        L48:
            b.d.g<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.f128e
            java.lang.Object r1 = r3.k
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L57:
            androidx.appcompat.app.a r0 = r3.p
            if (r0 == 0) goto L5e
            r0.n()
        L5e:
            r3.N()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.r():void");
    }

    @Override // androidx.appcompat.app.d
    public void s(Bundle bundle) {
        X();
    }

    boolean s0(int i2, KeyEvent keyEvent) {
        androidx.appcompat.app.a aVarM = m();
        if (aVarM != null && aVarM.o(i2, keyEvent)) {
            return true;
        }
        PanelFeatureState panelFeatureState = this.O;
        if (panelFeatureState != null && A0(panelFeatureState, keyEvent.getKeyCode(), keyEvent, 1)) {
            PanelFeatureState panelFeatureState2 = this.O;
            if (panelFeatureState2 != null) {
                panelFeatureState2.n = true;
            }
            return true;
        }
        if (this.O == null) {
            PanelFeatureState panelFeatureStateE0 = e0(0, true);
            B0(panelFeatureStateE0, keyEvent);
            boolean zA0 = A0(panelFeatureStateE0, keyEvent.getKeyCode(), keyEvent, 1);
            panelFeatureStateE0.m = false;
            if (zA0) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.app.d
    public void t() {
        androidx.appcompat.app.a aVarM = m();
        if (aVarM != null) {
            aVarM.s(true);
        }
    }

    boolean t0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            boolean z = this.P;
            this.P = false;
            PanelFeatureState panelFeatureStateE0 = e0(0, false);
            if (panelFeatureStateE0 != null && panelFeatureStateE0.o) {
                if (!z) {
                    P(panelFeatureStateE0, true);
                }
                return true;
            }
            if (p0()) {
                return true;
            }
        } else if (i2 == 82) {
            u0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.d
    public void u(Bundle bundle) {
    }

    @Override // androidx.appcompat.app.d
    public void v() {
        this.S = true;
        G();
    }

    void v0(int i2) {
        androidx.appcompat.app.a aVarM;
        if (i2 != 108 || (aVarM = m()) == null) {
            return;
        }
        aVarM.i(true);
    }

    @Override // androidx.appcompat.app.d
    public void w() {
        this.S = false;
        androidx.appcompat.app.a aVarM = m();
        if (aVarM != null) {
            aVarM.s(false);
        }
    }

    void w0(int i2) {
        if (i2 == 108) {
            androidx.appcompat.app.a aVarM = m();
            if (aVarM != null) {
                aVarM.i(false);
                return;
            }
            return;
        }
        if (i2 == 0) {
            PanelFeatureState panelFeatureStateE0 = e0(i2, true);
            if (panelFeatureStateE0.o) {
                P(panelFeatureStateE0, false);
            }
        }
    }

    void x0(ViewGroup viewGroup) {
    }

    @Override // androidx.appcompat.app.d
    public boolean z(int i2) {
        int iD0 = D0(i2);
        if (this.L && iD0 == 108) {
            return false;
        }
        if (this.H && iD0 == 1) {
            this.H = false;
        }
        if (iD0 == 1) {
            I0();
            this.L = true;
            return true;
        }
        if (iD0 == 2) {
            I0();
            this.F = true;
            return true;
        }
        if (iD0 == 5) {
            I0();
            this.G = true;
            return true;
        }
        if (iD0 == 10) {
            I0();
            this.J = true;
            return true;
        }
        if (iD0 == 108) {
            I0();
            this.H = true;
            return true;
        }
        if (iD0 != 109) {
            return this.m.requestFeature(iD0);
        }
        I0();
        this.I = true;
        return true;
    }

    final androidx.appcompat.app.a z0() {
        return this.p;
    }

    AppCompatDelegateImpl(Dialog dialog, androidx.appcompat.app.c cVar) {
        this(dialog.getContext(), dialog.getWindow(), cVar, dialog);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    private AppCompatDelegateImpl(Context context, Window window, androidx.appcompat.app.c cVar, Object obj) {
        b.d.g<String, Integer> gVar;
        Integer num;
        AppCompatActivity appCompatActivityJ0;
        this.z = null;
        this.A = true;
        this.U = -100;
        this.c0 = new b();
        this.l = context;
        this.o = cVar;
        this.k = obj;
        if (this.U == -100 && (obj instanceof Dialog) && (appCompatActivityJ0 = J0()) != null) {
            this.U = appCompatActivityJ0.B().k();
        }
        if (this.U == -100 && (num = (gVar = f128e).get(obj.getClass().getName())) != null) {
            this.U = num.intValue();
            gVar.remove(obj.getClass().getName());
        }
        if (window != null) {
            J(window);
        }
        androidx.appcompat.widget.f.h();
    }

    class j extends b.a.o.i {
        j(Window.Callback callback) {
            super(callback);
        }

        final ActionMode b(ActionMode.Callback callback) {
            f.a aVar = new f.a(AppCompatDelegateImpl.this.l, callback);
            b.a.o.b bVarG0 = AppCompatDelegateImpl.this.G0(aVar);
            if (bVarG0 != null) {
                return aVar.e(bVarG0);
            }
            return null;
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.U(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.s0(keyEvent.getKeyCode(), keyEvent);
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public void onContentChanged() {
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public boolean onCreatePanelMenu(int i2, Menu menu) {
            if (i2 != 0 || (menu instanceof androidx.appcompat.view.menu.g)) {
                return super.onCreatePanelMenu(i2, menu);
            }
            return false;
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public boolean onMenuOpened(int i2, Menu menu) {
            super.onMenuOpened(i2, menu);
            AppCompatDelegateImpl.this.v0(i2);
            return true;
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public void onPanelClosed(int i2, Menu menu) {
            super.onPanelClosed(i2, menu);
            AppCompatDelegateImpl.this.w0(i2);
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public boolean onPreparePanel(int i2, View view, Menu menu) {
            androidx.appcompat.view.menu.g gVar = menu instanceof androidx.appcompat.view.menu.g ? (androidx.appcompat.view.menu.g) menu : null;
            if (i2 == 0 && gVar == null) {
                return false;
            }
            if (gVar != null) {
                gVar.e0(true);
            }
            boolean zOnPreparePanel = super.onPreparePanel(i2, view, menu);
            if (gVar != null) {
                gVar.e0(false);
            }
            return zOnPreparePanel;
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i2) {
            androidx.appcompat.view.menu.g gVar;
            PanelFeatureState panelFeatureStateE0 = AppCompatDelegateImpl.this.e0(0, true);
            if (panelFeatureStateE0 == null || (gVar = panelFeatureStateE0.j) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i2);
            } else {
                super.onProvideKeyboardShortcuts(list, gVar, i2);
            }
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            return AppCompatDelegateImpl.this.n0() ? b(callback) : super.onWindowStartingActionMode(callback);
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i2) {
            if (AppCompatDelegateImpl.this.n0() && i2 == 0) {
                return b(callback);
            }
            return super.onWindowStartingActionMode(callback, i2);
        }
    }
}
