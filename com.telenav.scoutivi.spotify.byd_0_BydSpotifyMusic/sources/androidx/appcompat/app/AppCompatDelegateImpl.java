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
import androidx.appcompat.view.menu.e;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.appcompat.widget.s0;
import androidx.appcompat.widget.v0;
import androidx.appcompat.widget.w0;
import androidx.appcompat.widget.z;
import androidx.lifecycle.i;
import i.b;
import i.f;
import i0.a0;
import i0.c0;
import i0.f;
import i0.u;
import i0.y;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import y.b;

/* JADX INFO: loaded from: classes.dex */
class AppCompatDelegateImpl extends androidx.appcompat.app.e implements e.a, LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private static final n.g<String, Integer> f430g0 = new n.g<>();

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private static final boolean f431h0 = false;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private static final int[] f432i0 = {R.attr.windowBackground};

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private static final boolean f433j0 = !"robolectric".equals(Build.FINGERPRINT);

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    private static final boolean f434k0 = true;
    ViewGroup A;
    private TextView B;
    private View C;
    private boolean D;
    private boolean E;
    boolean F;
    boolean G;
    boolean H;
    boolean I;
    boolean J;
    private boolean K;
    private PanelFeatureState[] L;
    private PanelFeatureState M;
    private boolean N;
    private boolean O;
    private boolean P;
    private boolean Q;
    boolean R;
    private int S;
    private int T;
    private boolean U;
    private boolean V;
    private n W;
    private n X;
    boolean Y;
    int Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    private final Runnable f435a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    private boolean f436b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    private Rect f437c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    private Rect f438d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    private androidx.appcompat.app.g f439e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    private androidx.appcompat.app.h f440f0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final Object f441i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    final Context f442j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    Window f443k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private l f444l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    final androidx.appcompat.app.d f445m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    androidx.appcompat.app.a f446n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    MenuInflater f447o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private CharSequence f448p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private z f449q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private f f450r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private r f451s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    i.b f452t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    ActionBarContextView f453u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    PopupWindow f454v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    Runnable f455w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    y f456x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private boolean f457y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private boolean f458z;

    protected static final class PanelFeatureState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f459a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f460b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f461c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f462d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f463e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f464f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        ViewGroup f465g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        View f466h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        View f467i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        androidx.appcompat.view.menu.e f468j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        androidx.appcompat.view.menu.c f469k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        Context f470l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        boolean f471m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        boolean f472n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        boolean f473o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public boolean f474p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        boolean f475q = false;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        boolean f476r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        Bundle f477s;

        @SuppressLint({"BanParcelableUsage"})
        private static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new a();

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            int f478e;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            boolean f479g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            Bundle f480h;

            class a implements Parcelable.ClassLoaderCreator<SavedState> {
                a() {
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public SavedState createFromParcel(Parcel parcel) {
                    return SavedState.b(parcel, null);
                }

                @Override // android.os.Parcelable.ClassLoaderCreator
                /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
                public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.b(parcel, classLoader);
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
                public SavedState[] newArray(int i7) {
                    return new SavedState[i7];
                }
            }

            SavedState() {
            }

            static SavedState b(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.f478e = parcel.readInt();
                boolean z6 = parcel.readInt() == 1;
                savedState.f479g = z6;
                if (z6) {
                    savedState.f480h = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i7) {
                parcel.writeInt(this.f478e);
                parcel.writeInt(this.f479g ? 1 : 0);
                if (this.f479g) {
                    parcel.writeBundle(this.f480h);
                }
            }
        }

        PanelFeatureState(int i7) {
            this.f459a = i7;
        }

        androidx.appcompat.view.menu.k a(j.a aVar) {
            if (this.f468j == null) {
                return null;
            }
            if (this.f469k == null) {
                androidx.appcompat.view.menu.c cVar = new androidx.appcompat.view.menu.c(this.f470l, e.g.abc_list_menu_item_layout);
                this.f469k = cVar;
                cVar.h(aVar);
                this.f468j.b(this.f469k);
            }
            return this.f469k.g(this.f465g);
        }

        public boolean b() {
            if (this.f466h == null) {
                return false;
            }
            return this.f467i != null || this.f469k.b().getCount() > 0;
        }

        void c(androidx.appcompat.view.menu.e eVar) {
            androidx.appcompat.view.menu.c cVar;
            androidx.appcompat.view.menu.e eVar2 = this.f468j;
            if (eVar == eVar2) {
                return;
            }
            if (eVar2 != null) {
                eVar2.O(this.f469k);
            }
            this.f468j = eVar;
            if (eVar == null || (cVar = this.f469k) == null) {
                return;
            }
            eVar.b(cVar);
        }

        void d(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme themeNewTheme = context.getResources().newTheme();
            themeNewTheme.setTo(context.getTheme());
            themeNewTheme.resolveAttribute(e.a.actionBarPopupTheme, typedValue, true);
            int i7 = typedValue.resourceId;
            if (i7 != 0) {
                themeNewTheme.applyStyle(i7, true);
            }
            themeNewTheme.resolveAttribute(e.a.panelMenuListTheme, typedValue, true);
            int i8 = typedValue.resourceId;
            if (i8 != 0) {
                themeNewTheme.applyStyle(i8, true);
            } else {
                themeNewTheme.applyStyle(e.i.Theme_AppCompat_CompactMenu, true);
            }
            i.d dVar = new i.d(context, 0);
            dVar.getTheme().setTo(themeNewTheme);
            this.f470l = dVar;
            TypedArray typedArrayObtainStyledAttributes = dVar.obtainStyledAttributes(e.j.AppCompatTheme);
            this.f460b = typedArrayObtainStyledAttributes.getResourceId(e.j.AppCompatTheme_panelBackground, 0);
            this.f464f = typedArrayObtainStyledAttributes.getResourceId(e.j.AppCompatTheme_android_windowAnimationStyle, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl.Z & 1) != 0) {
                appCompatDelegateImpl.U(0);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl2.Z & 4096) != 0) {
                appCompatDelegateImpl2.U(108);
            }
            AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl3.Y = false;
            appCompatDelegateImpl3.Z = 0;
        }
    }

    class b implements i0.p {
        b() {
        }

        @Override // i0.p
        public c0 a(View view, c0 c0Var) {
            int i7 = c0Var.i();
            int iL0 = AppCompatDelegateImpl.this.L0(c0Var, null);
            if (i7 != iL0) {
                c0Var = c0Var.m(c0Var.g(), iL0, c0Var.h(), c0Var.f());
            }
            return u.W(view, c0Var);
        }
    }

    class c implements ContentFrameLayout.a {
        c() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void a() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void onDetachedFromWindow() {
            AppCompatDelegateImpl.this.S();
        }
    }

    class d implements Runnable {

        class a extends a0 {
            a() {
            }

            @Override // i0.z
            public void a(View view) {
                AppCompatDelegateImpl.this.f453u.setAlpha(1.0f);
                AppCompatDelegateImpl.this.f456x.f(null);
                AppCompatDelegateImpl.this.f456x = null;
            }

            @Override // i0.a0, i0.z
            public void b(View view) {
                AppCompatDelegateImpl.this.f453u.setVisibility(0);
            }
        }

        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            appCompatDelegateImpl.f454v.showAtLocation(appCompatDelegateImpl.f453u, 55, 0, 0);
            AppCompatDelegateImpl.this.V();
            if (!AppCompatDelegateImpl.this.D0()) {
                AppCompatDelegateImpl.this.f453u.setAlpha(1.0f);
                AppCompatDelegateImpl.this.f453u.setVisibility(0);
            } else {
                AppCompatDelegateImpl.this.f453u.setAlpha(0.0f);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.f456x = u.d(appCompatDelegateImpl2.f453u).a(1.0f);
                AppCompatDelegateImpl.this.f456x.f(new a());
            }
        }
    }

    class e extends a0 {
        e() {
        }

        @Override // i0.z
        public void a(View view) {
            AppCompatDelegateImpl.this.f453u.setAlpha(1.0f);
            AppCompatDelegateImpl.this.f456x.f(null);
            AppCompatDelegateImpl.this.f456x = null;
        }

        @Override // i0.a0, i0.z
        public void b(View view) {
            AppCompatDelegateImpl.this.f453u.setVisibility(0);
            AppCompatDelegateImpl.this.f453u.sendAccessibilityEvent(32);
            if (AppCompatDelegateImpl.this.f453u.getParent() instanceof View) {
                u.h0((View) AppCompatDelegateImpl.this.f453u.getParent());
            }
        }
    }

    private final class f implements j.a {
        f() {
        }

        @Override // androidx.appcompat.view.menu.j.a
        public void a(androidx.appcompat.view.menu.e eVar, boolean z6) {
            AppCompatDelegateImpl.this.L(eVar);
        }

        @Override // androidx.appcompat.view.menu.j.a
        public boolean b(androidx.appcompat.view.menu.e eVar) {
            Window.Callback callbackF0 = AppCompatDelegateImpl.this.f0();
            if (callbackF0 == null) {
                return true;
            }
            callbackF0.onMenuOpened(108, eVar);
            return true;
        }
    }

    class g implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private b.a f488a;

        class a extends a0 {
            a() {
            }

            @Override // i0.z
            public void a(View view) {
                AppCompatDelegateImpl.this.f453u.setVisibility(8);
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                PopupWindow popupWindow = appCompatDelegateImpl.f454v;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (appCompatDelegateImpl.f453u.getParent() instanceof View) {
                    u.h0((View) AppCompatDelegateImpl.this.f453u.getParent());
                }
                AppCompatDelegateImpl.this.f453u.k();
                AppCompatDelegateImpl.this.f456x.f(null);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.f456x = null;
                u.h0(appCompatDelegateImpl2.A);
            }
        }

        public g(b.a aVar) {
            this.f488a = aVar;
        }

        @Override // i.b.a
        public boolean a(i.b bVar, MenuItem menuItem) {
            return this.f488a.a(bVar, menuItem);
        }

        @Override // i.b.a
        public boolean b(i.b bVar, Menu menu) {
            u.h0(AppCompatDelegateImpl.this.A);
            return this.f488a.b(bVar, menu);
        }

        @Override // i.b.a
        public void c(i.b bVar) {
            this.f488a.c(bVar);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.f454v != null) {
                appCompatDelegateImpl.f443k.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.f455w);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.f453u != null) {
                appCompatDelegateImpl2.V();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl3.f456x = u.d(appCompatDelegateImpl3.f453u).a(0.0f);
                AppCompatDelegateImpl.this.f456x.f(new a());
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            androidx.appcompat.app.d dVar = appCompatDelegateImpl4.f445m;
            if (dVar != null) {
                dVar.e(appCompatDelegateImpl4.f452t);
            }
            AppCompatDelegateImpl appCompatDelegateImpl5 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl5.f452t = null;
            u.h0(appCompatDelegateImpl5.A);
        }

        @Override // i.b.a
        public boolean d(i.b bVar, Menu menu) {
            return this.f488a.d(bVar, menu);
        }
    }

    static class h {
        static Context a(Context context, Configuration configuration) {
            return context.createConfigurationContext(configuration);
        }

        static void b(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i7 = configuration.densityDpi;
            int i8 = configuration2.densityDpi;
            if (i7 != i8) {
                configuration3.densityDpi = i8;
            }
        }
    }

    static class i {
        static boolean a(PowerManager powerManager) {
            return powerManager.isPowerSaveMode();
        }
    }

    static class j {
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

    static class k {
        static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i7 = configuration.colorMode & 3;
            int i8 = configuration2.colorMode;
            if (i7 != (i8 & 3)) {
                configuration3.colorMode |= i8 & 3;
            }
            int i9 = configuration.colorMode & 12;
            int i10 = configuration2.colorMode;
            if (i9 != (i10 & 12)) {
                configuration3.colorMode |= i10 & 12;
            }
        }
    }

    class l extends i.i {
        l(Window.Callback callback) {
            super(callback);
        }

        final ActionMode b(ActionMode.Callback callback) {
            f.a aVar = new f.a(AppCompatDelegateImpl.this.f442j, callback);
            i.b bVarF0 = AppCompatDelegateImpl.this.F0(aVar);
            if (bVarF0 != null) {
                return aVar.e(bVarF0);
            }
            return null;
        }

        @Override // i.i, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.T(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // i.i, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.r0(keyEvent.getKeyCode(), keyEvent);
        }

        @Override // android.view.Window.Callback
        public void onContentChanged() {
        }

        @Override // i.i, android.view.Window.Callback
        public boolean onCreatePanelMenu(int i7, Menu menu) {
            if (i7 != 0 || (menu instanceof androidx.appcompat.view.menu.e)) {
                return super.onCreatePanelMenu(i7, menu);
            }
            return false;
        }

        @Override // i.i, android.view.Window.Callback
        public boolean onMenuOpened(int i7, Menu menu) {
            super.onMenuOpened(i7, menu);
            AppCompatDelegateImpl.this.u0(i7);
            return true;
        }

        @Override // i.i, android.view.Window.Callback
        public void onPanelClosed(int i7, Menu menu) {
            super.onPanelClosed(i7, menu);
            AppCompatDelegateImpl.this.v0(i7);
        }

        @Override // i.i, android.view.Window.Callback
        public boolean onPreparePanel(int i7, View view, Menu menu) {
            androidx.appcompat.view.menu.e eVar = menu instanceof androidx.appcompat.view.menu.e ? (androidx.appcompat.view.menu.e) menu : null;
            if (i7 == 0 && eVar == null) {
                return false;
            }
            if (eVar != null) {
                eVar.a0(true);
            }
            boolean zOnPreparePanel = super.onPreparePanel(i7, view, menu);
            if (eVar != null) {
                eVar.a0(false);
            }
            return zOnPreparePanel;
        }

        @Override // i.i, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i7) {
            androidx.appcompat.view.menu.e eVar;
            PanelFeatureState panelFeatureStateD0 = AppCompatDelegateImpl.this.d0(0, true);
            if (panelFeatureStateD0 == null || (eVar = panelFeatureStateD0.f468j) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i7);
            } else {
                super.onProvideKeyboardShortcuts(list, eVar, i7);
            }
        }

        @Override // android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return null;
        }

        @Override // i.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i7) {
            return (AppCompatDelegateImpl.this.m0() && i7 == 0) ? b(callback) : super.onWindowStartingActionMode(callback, i7);
        }
    }

    private class m extends n {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final PowerManager f492c;

        m(Context context) {
            super();
            this.f492c = (PowerManager) context.getApplicationContext().getSystemService("power");
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.n
        IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.n
        public int c() {
            return i.a(this.f492c) ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.n
        public void d() {
            AppCompatDelegateImpl.this.F();
        }
    }

    abstract class n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private BroadcastReceiver f494a;

        class a extends BroadcastReceiver {
            a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                n.this.d();
            }
        }

        n() {
        }

        void a() {
            BroadcastReceiver broadcastReceiver = this.f494a;
            if (broadcastReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.f442j.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.f494a = null;
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
            if (this.f494a == null) {
                this.f494a = new a();
            }
            AppCompatDelegateImpl.this.f442j.registerReceiver(this.f494a, intentFilterB);
        }
    }

    private class o extends n {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final androidx.appcompat.app.j f497c;

        o(androidx.appcompat.app.j jVar) {
            super();
            this.f497c = jVar;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.n
        IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.n
        public int c() {
            return this.f497c.d() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.n
        public void d() {
            AppCompatDelegateImpl.this.F();
        }
    }

    private static class p {
        static void a(ContextThemeWrapper contextThemeWrapper, Configuration configuration) {
            contextThemeWrapper.applyOverrideConfiguration(configuration);
        }
    }

    private class q extends ContentFrameLayout {
        public q(Context context) {
            super(context);
        }

        private boolean b(int i7, int i8) {
            return i7 < -5 || i8 < -5 || i7 > getWidth() + 5 || i8 > getHeight() + 5;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.T(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !b((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            AppCompatDelegateImpl.this.N(0);
            return true;
        }

        @Override // android.view.View
        public void setBackgroundResource(int i7) {
            setBackgroundDrawable(f.a.b(getContext(), i7));
        }
    }

    private final class r implements j.a {
        r() {
        }

        @Override // androidx.appcompat.view.menu.j.a
        public void a(androidx.appcompat.view.menu.e eVar, boolean z6) {
            androidx.appcompat.view.menu.e eVarD = eVar.D();
            boolean z7 = eVarD != eVar;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z7) {
                eVar = eVarD;
            }
            PanelFeatureState panelFeatureStateY = appCompatDelegateImpl.Y(eVar);
            if (panelFeatureStateY != null) {
                if (!z7) {
                    AppCompatDelegateImpl.this.O(panelFeatureStateY, z6);
                } else {
                    AppCompatDelegateImpl.this.K(panelFeatureStateY.f459a, panelFeatureStateY, eVarD);
                    AppCompatDelegateImpl.this.O(panelFeatureStateY, true);
                }
            }
        }

        @Override // androidx.appcompat.view.menu.j.a
        public boolean b(androidx.appcompat.view.menu.e eVar) {
            Window.Callback callbackF0;
            if (eVar != eVar.D()) {
                return true;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (!appCompatDelegateImpl.F || (callbackF0 = appCompatDelegateImpl.f0()) == null || AppCompatDelegateImpl.this.R) {
                return true;
            }
            callbackF0.onMenuOpened(108, eVar);
            return true;
        }
    }

    AppCompatDelegateImpl(Activity activity, androidx.appcompat.app.d dVar) {
        this(activity, null, dVar, activity);
    }

    private boolean A0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        z zVar;
        z zVar2;
        z zVar3;
        if (this.R) {
            return false;
        }
        if (panelFeatureState.f471m) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.M;
        if (panelFeatureState2 != null && panelFeatureState2 != panelFeatureState) {
            O(panelFeatureState2, false);
        }
        Window.Callback callbackF0 = f0();
        if (callbackF0 != null) {
            panelFeatureState.f467i = callbackF0.onCreatePanelView(panelFeatureState.f459a);
        }
        int i7 = panelFeatureState.f459a;
        boolean z6 = i7 == 0 || i7 == 108;
        if (z6 && (zVar3 = this.f449q) != null) {
            zVar3.f();
        }
        if (panelFeatureState.f467i == null) {
            if (z6) {
                y0();
            }
            androidx.appcompat.view.menu.e eVar = panelFeatureState.f468j;
            if (eVar == null || panelFeatureState.f476r) {
                if (eVar == null && (!j0(panelFeatureState) || panelFeatureState.f468j == null)) {
                    return false;
                }
                if (z6 && this.f449q != null) {
                    if (this.f450r == null) {
                        this.f450r = new f();
                    }
                    this.f449q.a(panelFeatureState.f468j, this.f450r);
                }
                panelFeatureState.f468j.d0();
                if (!callbackF0.onCreatePanelMenu(panelFeatureState.f459a, panelFeatureState.f468j)) {
                    panelFeatureState.c(null);
                    if (z6 && (zVar = this.f449q) != null) {
                        zVar.a(null, this.f450r);
                    }
                    return false;
                }
                panelFeatureState.f476r = false;
            }
            panelFeatureState.f468j.d0();
            Bundle bundle = panelFeatureState.f477s;
            if (bundle != null) {
                panelFeatureState.f468j.P(bundle);
                panelFeatureState.f477s = null;
            }
            if (!callbackF0.onPreparePanel(0, panelFeatureState.f467i, panelFeatureState.f468j)) {
                if (z6 && (zVar2 = this.f449q) != null) {
                    zVar2.a(null, this.f450r);
                }
                panelFeatureState.f468j.c0();
                return false;
            }
            boolean z7 = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            panelFeatureState.f474p = z7;
            panelFeatureState.f468j.setQwertyMode(z7);
            panelFeatureState.f468j.c0();
        }
        panelFeatureState.f471m = true;
        panelFeatureState.f472n = false;
        this.M = panelFeatureState;
        return true;
    }

    private void B0(boolean z6) {
        z zVar = this.f449q;
        if (zVar == null || !zVar.g() || (ViewConfiguration.get(this.f442j).hasPermanentMenuKey() && !this.f449q.b())) {
            PanelFeatureState panelFeatureStateD0 = d0(0, true);
            panelFeatureStateD0.f475q = true;
            O(panelFeatureStateD0, false);
            x0(panelFeatureStateD0, null);
            return;
        }
        Window.Callback callbackF0 = f0();
        if (this.f449q.c() && z6) {
            this.f449q.d();
            if (this.R) {
                return;
            }
            callbackF0.onPanelClosed(108, d0(0, true).f468j);
            return;
        }
        if (callbackF0 == null || this.R) {
            return;
        }
        if (this.Y && (this.Z & 1) != 0) {
            this.f443k.getDecorView().removeCallbacks(this.f435a0);
            this.f435a0.run();
        }
        PanelFeatureState panelFeatureStateD02 = d0(0, true);
        androidx.appcompat.view.menu.e eVar = panelFeatureStateD02.f468j;
        if (eVar == null || panelFeatureStateD02.f476r || !callbackF0.onPreparePanel(0, panelFeatureStateD02.f467i, eVar)) {
            return;
        }
        callbackF0.onMenuOpened(108, panelFeatureStateD02.f468j);
        this.f449q.e();
    }

    private int C0(int i7) {
        if (i7 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        }
        if (i7 != 9) {
            return i7;
        }
        Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
        return 109;
    }

    private boolean E0(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.f443k.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || u.O((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    private boolean G(boolean z6) {
        if (this.R) {
            return false;
        }
        int iJ = J();
        boolean zJ0 = J0(n0(this.f442j, iJ), z6);
        if (iJ == 0) {
            c0(this.f442j).e();
        } else {
            n nVar = this.W;
            if (nVar != null) {
                nVar.a();
            }
        }
        if (iJ == 3) {
            b0(this.f442j).e();
        } else {
            n nVar2 = this.X;
            if (nVar2 != null) {
                nVar2.a();
            }
        }
        return zJ0;
    }

    private void H() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.A.findViewById(R.id.content);
        View decorView = this.f443k.getDecorView();
        contentFrameLayout.a(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray typedArrayObtainStyledAttributes = this.f442j.obtainStyledAttributes(e.j.AppCompatTheme);
        typedArrayObtainStyledAttributes.getValue(e.j.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        typedArrayObtainStyledAttributes.getValue(e.j.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        int i7 = e.j.AppCompatTheme_windowFixedWidthMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i7)) {
            typedArrayObtainStyledAttributes.getValue(i7, contentFrameLayout.getFixedWidthMajor());
        }
        int i8 = e.j.AppCompatTheme_windowFixedWidthMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i8)) {
            typedArrayObtainStyledAttributes.getValue(i8, contentFrameLayout.getFixedWidthMinor());
        }
        int i9 = e.j.AppCompatTheme_windowFixedHeightMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i9)) {
            typedArrayObtainStyledAttributes.getValue(i9, contentFrameLayout.getFixedHeightMajor());
        }
        int i10 = e.j.AppCompatTheme_windowFixedHeightMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i10)) {
            typedArrayObtainStyledAttributes.getValue(i10, contentFrameLayout.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    private void H0() {
        if (this.f458z) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private void I(Window window) {
        if (this.f443k != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof l) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        l lVar = new l(callback);
        this.f444l = lVar;
        window.setCallback(lVar);
        s0 s0VarU = s0.u(this.f442j, null, f432i0);
        Drawable drawableH = s0VarU.h(0);
        if (drawableH != null) {
            window.setBackgroundDrawable(drawableH);
        }
        s0VarU.w();
        this.f443k = window;
    }

    private androidx.appcompat.app.c I0() {
        for (Context baseContext = this.f442j; baseContext != null; baseContext = ((ContextWrapper) baseContext).getBaseContext()) {
            if (baseContext instanceof androidx.appcompat.app.c) {
                return (androidx.appcompat.app.c) baseContext;
            }
            if (!(baseContext instanceof ContextWrapper)) {
                break;
            }
        }
        return null;
    }

    private int J() {
        int i7 = this.S;
        return i7 != -100 ? i7 : androidx.appcompat.app.e.j();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean J0(int r7, boolean r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.f442j
            r1 = 0
            android.content.res.Configuration r0 = r6.P(r0, r7, r1)
            boolean r2 = r6.l0()
            android.content.Context r3 = r6.f442j
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
            boolean r8 = r6.O
            if (r8 == 0) goto L47
            boolean r8 = androidx.appcompat.app.AppCompatDelegateImpl.f433j0
            if (r8 != 0) goto L30
            boolean r8 = r6.P
            if (r8 == 0) goto L47
        L30:
            java.lang.Object r8 = r6.f441i
            boolean r5 = r8 instanceof android.app.Activity
            if (r5 == 0) goto L47
            android.app.Activity r8 = (android.app.Activity) r8
            boolean r8 = r8.isChild()
            if (r8 != 0) goto L47
            java.lang.Object r8 = r6.f441i
            android.app.Activity r8 = (android.app.Activity) r8
            w.a.f(r8)
            r8 = r4
            goto L48
        L47:
            r8 = 0
        L48:
            if (r8 != 0) goto L50
            if (r3 == r0) goto L50
            r6.K0(r0, r2, r1)
            goto L51
        L50:
            r4 = r8
        L51:
            if (r4 == 0) goto L5e
            java.lang.Object r8 = r6.f441i
            boolean r0 = r8 instanceof androidx.appcompat.app.c
            if (r0 == 0) goto L5e
            androidx.appcompat.app.c r8 = (androidx.appcompat.app.c) r8
            r8.H(r7)
        L5e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.J0(int, boolean):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void K0(int i7, boolean z6, Configuration configuration) {
        Resources resources = this.f442j.getResources();
        Configuration configuration2 = new Configuration(resources.getConfiguration());
        if (configuration != null) {
            configuration2.updateFrom(configuration);
        }
        configuration2.uiMode = i7 | (resources.getConfiguration().uiMode & (-49));
        resources.updateConfiguration(configuration2, null);
        int i8 = this.T;
        if (i8 != 0) {
            this.f442j.setTheme(i8);
            this.f442j.getTheme().applyStyle(this.T, true);
        }
        if (z6) {
            Object obj = this.f441i;
            if (obj instanceof Activity) {
                Activity activity = (Activity) obj;
                if (activity instanceof androidx.lifecycle.o) {
                    if (((androidx.lifecycle.o) activity).a().b().isAtLeast(i.c.STARTED)) {
                        activity.onConfigurationChanged(configuration2);
                    }
                } else if (this.Q) {
                    activity.onConfigurationChanged(configuration2);
                }
            }
        }
    }

    private void M() {
        n nVar = this.W;
        if (nVar != null) {
            nVar.a();
        }
        n nVar2 = this.X;
        if (nVar2 != null) {
            nVar2.a();
        }
    }

    private void M0(View view) {
        view.setBackgroundColor((u.I(view) & 8192) != 0 ? x.a.b(this.f442j, e.c.abc_decor_view_status_guard_light) : x.a.b(this.f442j, e.c.abc_decor_view_status_guard));
    }

    private Configuration P(Context context, int i7, Configuration configuration) {
        int i8 = i7 != 1 ? i7 != 2 ? context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i8 | (configuration2.uiMode & (-49));
        return configuration2;
    }

    private ViewGroup Q() {
        ViewGroup viewGroup;
        TypedArray typedArrayObtainStyledAttributes = this.f442j.obtainStyledAttributes(e.j.AppCompatTheme);
        int i7 = e.j.AppCompatTheme_windowActionBar;
        if (!typedArrayObtainStyledAttributes.hasValue(i7)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(e.j.AppCompatTheme_windowNoTitle, false)) {
            z(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(i7, false)) {
            z(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(e.j.AppCompatTheme_windowActionBarOverlay, false)) {
            z(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(e.j.AppCompatTheme_windowActionModeOverlay, false)) {
            z(10);
        }
        this.I = typedArrayObtainStyledAttributes.getBoolean(e.j.AppCompatTheme_android_windowIsFloating, false);
        typedArrayObtainStyledAttributes.recycle();
        X();
        this.f443k.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f442j);
        if (this.J) {
            viewGroup = this.H ? (ViewGroup) layoutInflaterFrom.inflate(e.g.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(e.g.abc_screen_simple, (ViewGroup) null);
        } else if (this.I) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(e.g.abc_dialog_title_material, (ViewGroup) null);
            this.G = false;
            this.F = false;
        } else if (this.F) {
            TypedValue typedValue = new TypedValue();
            this.f442j.getTheme().resolveAttribute(e.a.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new i.d(this.f442j, typedValue.resourceId) : this.f442j).inflate(e.g.abc_screen_toolbar, (ViewGroup) null);
            z zVar = (z) viewGroup.findViewById(e.f.decor_content_parent);
            this.f449q = zVar;
            zVar.setWindowCallback(f0());
            if (this.G) {
                this.f449q.k(109);
            }
            if (this.D) {
                this.f449q.k(2);
            }
            if (this.E) {
                this.f449q.k(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.F + ", windowActionBarOverlay: " + this.G + ", android:windowIsFloating: " + this.I + ", windowActionModeOverlay: " + this.H + ", windowNoTitle: " + this.J + " }");
        }
        u.v0(viewGroup, new b());
        if (this.f449q == null) {
            this.B = (TextView) viewGroup.findViewById(e.f.title);
        }
        w0.c(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(e.f.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.f443k.findViewById(R.id.content);
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
        this.f443k.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new c());
        return viewGroup;
    }

    private void W() {
        if (this.f458z) {
            return;
        }
        this.A = Q();
        CharSequence charSequenceE0 = e0();
        if (!TextUtils.isEmpty(charSequenceE0)) {
            z zVar = this.f449q;
            if (zVar != null) {
                zVar.setWindowTitle(charSequenceE0);
            } else if (y0() != null) {
                y0().t(charSequenceE0);
            } else {
                TextView textView = this.B;
                if (textView != null) {
                    textView.setText(charSequenceE0);
                }
            }
        }
        H();
        w0(this.A);
        this.f458z = true;
        PanelFeatureState panelFeatureStateD0 = d0(0, false);
        if (this.R) {
            return;
        }
        if (panelFeatureStateD0 == null || panelFeatureStateD0.f468j == null) {
            k0(108);
        }
    }

    private void X() {
        if (this.f443k == null) {
            Object obj = this.f441i;
            if (obj instanceof Activity) {
                I(((Activity) obj).getWindow());
            }
        }
        if (this.f443k == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    private static Configuration Z(Configuration configuration, Configuration configuration2) {
        Configuration configuration3 = new Configuration();
        configuration3.fontScale = 0.0f;
        if (configuration2 != null && configuration.diff(configuration2) != 0) {
            float f7 = configuration.fontScale;
            float f8 = configuration2.fontScale;
            if (f7 != f8) {
                configuration3.fontScale = f8;
            }
            int i7 = configuration.mcc;
            int i8 = configuration2.mcc;
            if (i7 != i8) {
                configuration3.mcc = i8;
            }
            int i9 = configuration.mnc;
            int i10 = configuration2.mnc;
            if (i9 != i10) {
                configuration3.mnc = i10;
            }
            j.a(configuration, configuration2, configuration3);
            int i11 = configuration.touchscreen;
            int i12 = configuration2.touchscreen;
            if (i11 != i12) {
                configuration3.touchscreen = i12;
            }
            int i13 = configuration.keyboard;
            int i14 = configuration2.keyboard;
            if (i13 != i14) {
                configuration3.keyboard = i14;
            }
            int i15 = configuration.keyboardHidden;
            int i16 = configuration2.keyboardHidden;
            if (i15 != i16) {
                configuration3.keyboardHidden = i16;
            }
            int i17 = configuration.navigation;
            int i18 = configuration2.navigation;
            if (i17 != i18) {
                configuration3.navigation = i18;
            }
            int i19 = configuration.navigationHidden;
            int i20 = configuration2.navigationHidden;
            if (i19 != i20) {
                configuration3.navigationHidden = i20;
            }
            int i21 = configuration.orientation;
            int i22 = configuration2.orientation;
            if (i21 != i22) {
                configuration3.orientation = i22;
            }
            int i23 = configuration.screenLayout & 15;
            int i24 = configuration2.screenLayout;
            if (i23 != (i24 & 15)) {
                configuration3.screenLayout |= i24 & 15;
            }
            int i25 = configuration.screenLayout & 192;
            int i26 = configuration2.screenLayout;
            if (i25 != (i26 & 192)) {
                configuration3.screenLayout |= i26 & 192;
            }
            int i27 = configuration.screenLayout & 48;
            int i28 = configuration2.screenLayout;
            if (i27 != (i28 & 48)) {
                configuration3.screenLayout |= i28 & 48;
            }
            int i29 = configuration.screenLayout & 768;
            int i30 = configuration2.screenLayout;
            if (i29 != (i30 & 768)) {
                configuration3.screenLayout |= i30 & 768;
            }
            k.a(configuration, configuration2, configuration3);
            int i31 = configuration.uiMode & 15;
            int i32 = configuration2.uiMode;
            if (i31 != (i32 & 15)) {
                configuration3.uiMode |= i32 & 15;
            }
            int i33 = configuration.uiMode & 48;
            int i34 = configuration2.uiMode;
            if (i33 != (i34 & 48)) {
                configuration3.uiMode |= i34 & 48;
            }
            int i35 = configuration.screenWidthDp;
            int i36 = configuration2.screenWidthDp;
            if (i35 != i36) {
                configuration3.screenWidthDp = i36;
            }
            int i37 = configuration.screenHeightDp;
            int i38 = configuration2.screenHeightDp;
            if (i37 != i38) {
                configuration3.screenHeightDp = i38;
            }
            int i39 = configuration.smallestScreenWidthDp;
            int i40 = configuration2.smallestScreenWidthDp;
            if (i39 != i40) {
                configuration3.smallestScreenWidthDp = i40;
            }
            h.b(configuration, configuration2, configuration3);
        }
        return configuration3;
    }

    private n b0(Context context) {
        if (this.X == null) {
            this.X = new m(context);
        }
        return this.X;
    }

    private n c0(Context context) {
        if (this.W == null) {
            this.W = new o(androidx.appcompat.app.j.a(context));
        }
        return this.W;
    }

    private void g0() {
        W();
        if (this.F && this.f446n == null) {
            Object obj = this.f441i;
            if (obj instanceof Activity) {
                this.f446n = new androidx.appcompat.app.k((Activity) this.f441i, this.G);
            } else if (obj instanceof Dialog) {
                this.f446n = new androidx.appcompat.app.k((Dialog) this.f441i);
            }
            androidx.appcompat.app.a aVar = this.f446n;
            if (aVar != null) {
                aVar.r(this.f436b0);
            }
        }
    }

    private boolean h0(PanelFeatureState panelFeatureState) {
        View view = panelFeatureState.f467i;
        if (view != null) {
            panelFeatureState.f466h = view;
            return true;
        }
        if (panelFeatureState.f468j == null) {
            return false;
        }
        if (this.f451s == null) {
            this.f451s = new r();
        }
        View view2 = (View) panelFeatureState.a(this.f451s);
        panelFeatureState.f466h = view2;
        return view2 != null;
    }

    private boolean i0(PanelFeatureState panelFeatureState) {
        panelFeatureState.d(a0());
        panelFeatureState.f465g = new q(panelFeatureState.f470l);
        panelFeatureState.f461c = 81;
        return true;
    }

    private boolean j0(PanelFeatureState panelFeatureState) {
        Context context = this.f442j;
        int i7 = panelFeatureState.f459a;
        if ((i7 == 0 || i7 == 108) && this.f449q != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(e.a.actionBarTheme, typedValue, true);
            Resources.Theme themeNewTheme = null;
            if (typedValue.resourceId != 0) {
                themeNewTheme = context.getResources().newTheme();
                themeNewTheme.setTo(theme);
                themeNewTheme.applyStyle(typedValue.resourceId, true);
                themeNewTheme.resolveAttribute(e.a.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(e.a.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (themeNewTheme == null) {
                    themeNewTheme = context.getResources().newTheme();
                    themeNewTheme.setTo(theme);
                }
                themeNewTheme.applyStyle(typedValue.resourceId, true);
            }
            if (themeNewTheme != null) {
                i.d dVar = new i.d(context, 0);
                dVar.getTheme().setTo(themeNewTheme);
                context = dVar;
            }
        }
        androidx.appcompat.view.menu.e eVar = new androidx.appcompat.view.menu.e(context);
        eVar.R(this);
        panelFeatureState.c(eVar);
        return true;
    }

    private void k0(int i7) {
        this.Z = (1 << i7) | this.Z;
        if (this.Y) {
            return;
        }
        u.c0(this.f443k.getDecorView(), this.f435a0);
        this.Y = true;
    }

    private boolean l0() {
        if (!this.V && (this.f441i instanceof Activity)) {
            PackageManager packageManager = this.f442j.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            try {
                ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(this.f442j, this.f441i.getClass()), Build.VERSION.SDK_INT >= 29 ? 269221888 : 786432);
                this.U = (activityInfo == null || (activityInfo.configChanges & 512) == 0) ? false : true;
            } catch (PackageManager.NameNotFoundException e7) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e7);
                this.U = false;
            }
        }
        this.V = true;
        return this.U;
    }

    private boolean q0(int i7, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        PanelFeatureState panelFeatureStateD0 = d0(i7, true);
        if (panelFeatureStateD0.f473o) {
            return false;
        }
        return A0(panelFeatureStateD0, keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean t0(int r5, android.view.KeyEvent r6) {
        /*
            r4 = this;
            i.b r0 = r4.f452t
            r1 = 0
            if (r0 == 0) goto L6
            return r1
        L6:
            r0 = 1
            androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r2 = r4.d0(r5, r0)
            if (r5 != 0) goto L43
            androidx.appcompat.widget.z r5 = r4.f449q
            if (r5 == 0) goto L43
            boolean r5 = r5.g()
            if (r5 == 0) goto L43
            android.content.Context r5 = r4.f442j
            android.view.ViewConfiguration r5 = android.view.ViewConfiguration.get(r5)
            boolean r5 = r5.hasPermanentMenuKey()
            if (r5 != 0) goto L43
            androidx.appcompat.widget.z r5 = r4.f449q
            boolean r5 = r5.c()
            if (r5 != 0) goto L3c
            boolean r5 = r4.R
            if (r5 != 0) goto L62
            boolean r5 = r4.A0(r2, r6)
            if (r5 == 0) goto L62
            androidx.appcompat.widget.z r5 = r4.f449q
            boolean r0 = r5.e()
            goto L68
        L3c:
            androidx.appcompat.widget.z r5 = r4.f449q
            boolean r0 = r5.d()
            goto L68
        L43:
            boolean r5 = r2.f473o
            if (r5 != 0) goto L64
            boolean r3 = r2.f472n
            if (r3 == 0) goto L4c
            goto L64
        L4c:
            boolean r5 = r2.f471m
            if (r5 == 0) goto L62
            boolean r5 = r2.f476r
            if (r5 == 0) goto L5b
            r2.f471m = r1
            boolean r5 = r4.A0(r2, r6)
            goto L5c
        L5b:
            r5 = r0
        L5c:
            if (r5 == 0) goto L62
            r4.x0(r2, r6)
            goto L68
        L62:
            r0 = r1
            goto L68
        L64:
            r4.O(r2, r0)
            r0 = r5
        L68:
            if (r0 == 0) goto L85
            android.content.Context r5 = r4.f442j
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.t0(int, android.view.KeyEvent):boolean");
    }

    private void x0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        int i7;
        ViewGroup.LayoutParams layoutParams;
        if (panelFeatureState.f473o || this.R) {
            return;
        }
        if (panelFeatureState.f459a == 0) {
            if ((this.f442j.getResources().getConfiguration().screenLayout & 15) == 4) {
                return;
            }
        }
        Window.Callback callbackF0 = f0();
        if (callbackF0 != null && !callbackF0.onMenuOpened(panelFeatureState.f459a, panelFeatureState.f468j)) {
            O(panelFeatureState, true);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f442j.getSystemService("window");
        if (windowManager != null && A0(panelFeatureState, keyEvent)) {
            ViewGroup viewGroup = panelFeatureState.f465g;
            if (viewGroup != null && !panelFeatureState.f475q) {
                View view = panelFeatureState.f467i;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i7 = -1;
                }
                panelFeatureState.f472n = false;
                WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i7, -2, panelFeatureState.f462d, panelFeatureState.f463e, 1002, 8519680, -3);
                layoutParams2.gravity = panelFeatureState.f461c;
                layoutParams2.windowAnimations = panelFeatureState.f464f;
                windowManager.addView(panelFeatureState.f465g, layoutParams2);
                panelFeatureState.f473o = true;
            }
            if (viewGroup == null) {
                if (!i0(panelFeatureState) || panelFeatureState.f465g == null) {
                    return;
                }
            } else if (panelFeatureState.f475q && viewGroup.getChildCount() > 0) {
                panelFeatureState.f465g.removeAllViews();
            }
            if (!h0(panelFeatureState) || !panelFeatureState.b()) {
                panelFeatureState.f475q = true;
                return;
            }
            ViewGroup.LayoutParams layoutParams3 = panelFeatureState.f466h.getLayoutParams();
            if (layoutParams3 == null) {
                layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
            }
            panelFeatureState.f465g.setBackgroundResource(panelFeatureState.f460b);
            ViewParent parent = panelFeatureState.f466h.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(panelFeatureState.f466h);
            }
            panelFeatureState.f465g.addView(panelFeatureState.f466h, layoutParams3);
            if (!panelFeatureState.f466h.hasFocus()) {
                panelFeatureState.f466h.requestFocus();
            }
            i7 = -2;
            panelFeatureState.f472n = false;
            WindowManager.LayoutParams layoutParams22 = new WindowManager.LayoutParams(i7, -2, panelFeatureState.f462d, panelFeatureState.f463e, 1002, 8519680, -3);
            layoutParams22.gravity = panelFeatureState.f461c;
            layoutParams22.windowAnimations = panelFeatureState.f464f;
            windowManager.addView(panelFeatureState.f465g, layoutParams22);
            panelFeatureState.f473o = true;
        }
    }

    private boolean z0(PanelFeatureState panelFeatureState, int i7, KeyEvent keyEvent, int i8) {
        androidx.appcompat.view.menu.e eVar;
        boolean zPerformShortcut = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.f471m || A0(panelFeatureState, keyEvent)) && (eVar = panelFeatureState.f468j) != null) {
            zPerformShortcut = eVar.performShortcut(i7, keyEvent, i8);
        }
        if (zPerformShortcut && (i8 & 1) == 0 && this.f449q == null) {
            O(panelFeatureState, true);
        }
        return zPerformShortcut;
    }

    @Override // androidx.appcompat.app.e
    public void A(int i7) {
        W();
        ViewGroup viewGroup = (ViewGroup) this.A.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f442j).inflate(i7, viewGroup);
        this.f444l.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.e
    public void B(View view) {
        W();
        ViewGroup viewGroup = (ViewGroup) this.A.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.f444l.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.e
    public void C(View view, ViewGroup.LayoutParams layoutParams) {
        W();
        ViewGroup viewGroup = (ViewGroup) this.A.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.f444l.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.e
    public void D(int i7) {
        this.T = i7;
    }

    final boolean D0() {
        ViewGroup viewGroup;
        return this.f458z && (viewGroup = this.A) != null && u.P(viewGroup);
    }

    @Override // androidx.appcompat.app.e
    public final void E(CharSequence charSequence) {
        this.f448p = charSequence;
        z zVar = this.f449q;
        if (zVar != null) {
            zVar.setWindowTitle(charSequence);
            return;
        }
        if (y0() != null) {
            y0().t(charSequence);
            return;
        }
        TextView textView = this.B;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public boolean F() {
        return G(true);
    }

    public i.b F0(b.a aVar) {
        androidx.appcompat.app.d dVar;
        if (aVar == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        i.b bVar = this.f452t;
        if (bVar != null) {
            bVar.c();
        }
        g gVar = new g(aVar);
        androidx.appcompat.app.a aVarM = m();
        if (aVarM != null) {
            i.b bVarU = aVarM.u(gVar);
            this.f452t = bVarU;
            if (bVarU != null && (dVar = this.f445m) != null) {
                dVar.j(bVarU);
            }
        }
        if (this.f452t == null) {
            this.f452t = G0(gVar);
        }
        return this.f452t;
    }

    i.b G0(b.a aVar) {
        i.b bVarL;
        Context dVar;
        androidx.appcompat.app.d dVar2;
        V();
        i.b bVar = this.f452t;
        if (bVar != null) {
            bVar.c();
        }
        if (!(aVar instanceof g)) {
            aVar = new g(aVar);
        }
        androidx.appcompat.app.d dVar3 = this.f445m;
        if (dVar3 == null || this.R) {
            bVarL = null;
        } else {
            try {
                bVarL = dVar3.l(aVar);
            } catch (AbstractMethodError unused) {
                bVarL = null;
            }
        }
        if (bVarL != null) {
            this.f452t = bVarL;
        } else {
            if (this.f453u == null) {
                if (this.I) {
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = this.f442j.getTheme();
                    theme.resolveAttribute(e.a.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Resources.Theme themeNewTheme = this.f442j.getResources().newTheme();
                        themeNewTheme.setTo(theme);
                        themeNewTheme.applyStyle(typedValue.resourceId, true);
                        dVar = new i.d(this.f442j, 0);
                        dVar.getTheme().setTo(themeNewTheme);
                    } else {
                        dVar = this.f442j;
                    }
                    this.f453u = new ActionBarContextView(dVar);
                    PopupWindow popupWindow = new PopupWindow(dVar, (AttributeSet) null, e.a.actionModePopupWindowStyle);
                    this.f454v = popupWindow;
                    androidx.core.widget.h.b(popupWindow, 2);
                    this.f454v.setContentView(this.f453u);
                    this.f454v.setWidth(-1);
                    dVar.getTheme().resolveAttribute(e.a.actionBarSize, typedValue, true);
                    this.f453u.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, dVar.getResources().getDisplayMetrics()));
                    this.f454v.setHeight(-2);
                    this.f455w = new d();
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.A.findViewById(e.f.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(a0()));
                        this.f453u = (ActionBarContextView) viewStubCompat.a();
                    }
                }
            }
            if (this.f453u != null) {
                V();
                this.f453u.k();
                i.e eVar = new i.e(this.f453u.getContext(), this.f453u, aVar, this.f454v == null);
                if (aVar.d(eVar, eVar.e())) {
                    eVar.k();
                    this.f453u.h(eVar);
                    this.f452t = eVar;
                    if (D0()) {
                        this.f453u.setAlpha(0.0f);
                        y yVarA = u.d(this.f453u).a(1.0f);
                        this.f456x = yVarA;
                        yVarA.f(new e());
                    } else {
                        this.f453u.setAlpha(1.0f);
                        this.f453u.setVisibility(0);
                        this.f453u.sendAccessibilityEvent(32);
                        if (this.f453u.getParent() instanceof View) {
                            u.h0((View) this.f453u.getParent());
                        }
                    }
                    if (this.f454v != null) {
                        this.f443k.getDecorView().post(this.f455w);
                    }
                } else {
                    this.f452t = null;
                }
            }
        }
        i.b bVar2 = this.f452t;
        if (bVar2 != null && (dVar2 = this.f445m) != null) {
            dVar2.j(bVar2);
        }
        return this.f452t;
    }

    void K(int i7, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i7 >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.L;
                if (i7 < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i7];
                }
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.f468j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.f473o) && !this.R) {
            this.f444l.a().onPanelClosed(i7, menu);
        }
    }

    void L(androidx.appcompat.view.menu.e eVar) {
        if (this.K) {
            return;
        }
        this.K = true;
        this.f449q.l();
        Window.Callback callbackF0 = f0();
        if (callbackF0 != null && !this.R) {
            callbackF0.onPanelClosed(108, eVar);
        }
        this.K = false;
    }

    final int L0(c0 c0Var, Rect rect) {
        boolean z6;
        boolean z7;
        int i7 = c0Var != null ? c0Var.i() : rect != null ? rect.top : 0;
        ActionBarContextView actionBarContextView = this.f453u;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z6 = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f453u.getLayoutParams();
            if (this.f453u.isShown()) {
                if (this.f437c0 == null) {
                    this.f437c0 = new Rect();
                    this.f438d0 = new Rect();
                }
                Rect rect2 = this.f437c0;
                Rect rect3 = this.f438d0;
                if (c0Var == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(c0Var.g(), c0Var.i(), c0Var.h(), c0Var.f());
                }
                w0.a(this.A, rect2, rect3);
                int i8 = rect2.top;
                int i9 = rect2.left;
                int i10 = rect2.right;
                c0 c0VarF = u.F(this.A);
                int iG = c0VarF == null ? 0 : c0VarF.g();
                int iH = c0VarF == null ? 0 : c0VarF.h();
                if (marginLayoutParams.topMargin == i8 && marginLayoutParams.leftMargin == i9 && marginLayoutParams.rightMargin == i10) {
                    z7 = false;
                } else {
                    marginLayoutParams.topMargin = i8;
                    marginLayoutParams.leftMargin = i9;
                    marginLayoutParams.rightMargin = i10;
                    z7 = true;
                }
                if (i8 <= 0 || this.C != null) {
                    View view = this.C;
                    if (view != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                        int i11 = marginLayoutParams2.height;
                        int i12 = marginLayoutParams.topMargin;
                        if (i11 != i12 || marginLayoutParams2.leftMargin != iG || marginLayoutParams2.rightMargin != iH) {
                            marginLayoutParams2.height = i12;
                            marginLayoutParams2.leftMargin = iG;
                            marginLayoutParams2.rightMargin = iH;
                            this.C.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view2 = new View(this.f442j);
                    this.C = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = iG;
                    layoutParams.rightMargin = iH;
                    this.A.addView(this.C, -1, layoutParams);
                }
                View view3 = this.C;
                z = view3 != null;
                if (z && view3.getVisibility() != 0) {
                    M0(this.C);
                }
                if (!this.H && z) {
                    i7 = 0;
                }
                z6 = z;
                z = z7;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z6 = false;
            } else {
                z6 = false;
                z = false;
            }
            if (z) {
                this.f453u.setLayoutParams(marginLayoutParams);
            }
        }
        View view4 = this.C;
        if (view4 != null) {
            view4.setVisibility(z6 ? 0 : 8);
        }
        return i7;
    }

    void N(int i7) {
        O(d0(i7, true), true);
    }

    void O(PanelFeatureState panelFeatureState, boolean z6) {
        ViewGroup viewGroup;
        z zVar;
        if (z6 && panelFeatureState.f459a == 0 && (zVar = this.f449q) != null && zVar.c()) {
            L(panelFeatureState.f468j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f442j.getSystemService("window");
        if (windowManager != null && panelFeatureState.f473o && (viewGroup = panelFeatureState.f465g) != null) {
            windowManager.removeView(viewGroup);
            if (z6) {
                K(panelFeatureState.f459a, panelFeatureState, null);
            }
        }
        panelFeatureState.f471m = false;
        panelFeatureState.f472n = false;
        panelFeatureState.f473o = false;
        panelFeatureState.f466h = null;
        panelFeatureState.f475q = true;
        if (this.M == panelFeatureState) {
            this.M = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View R(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z6;
        boolean z7 = false;
        if (this.f439e0 == null) {
            String string = this.f442j.obtainStyledAttributes(e.j.AppCompatTheme).getString(e.j.AppCompatTheme_viewInflaterClass);
            if (string == null) {
                this.f439e0 = new androidx.appcompat.app.g();
            } else {
                try {
                    this.f439e0 = (androidx.appcompat.app.g) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.f439e0 = new androidx.appcompat.app.g();
                }
            }
        }
        boolean z8 = f431h0;
        boolean zE0 = true;
        if (z8) {
            if (this.f440f0 == null) {
                this.f440f0 = new androidx.appcompat.app.h();
            }
            if (this.f440f0.a(attributeSet)) {
                z6 = true;
            } else {
                if (attributeSet instanceof XmlPullParser) {
                    if (((XmlPullParser) attributeSet).getDepth() > 1) {
                    }
                    z6 = z7;
                } else {
                    zE0 = E0((ViewParent) view);
                }
                z7 = zE0;
                z6 = z7;
            }
        } else {
            z6 = z7;
        }
        return this.f439e0.q(view, str, context, attributeSet, z6, z8, true, v0.b());
    }

    void S() {
        androidx.appcompat.view.menu.e eVar;
        z zVar = this.f449q;
        if (zVar != null) {
            zVar.l();
        }
        if (this.f454v != null) {
            this.f443k.getDecorView().removeCallbacks(this.f455w);
            if (this.f454v.isShowing()) {
                try {
                    this.f454v.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.f454v = null;
        }
        V();
        PanelFeatureState panelFeatureStateD0 = d0(0, false);
        if (panelFeatureStateD0 == null || (eVar = panelFeatureStateD0.f468j) == null) {
            return;
        }
        eVar.close();
    }

    boolean T(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.f441i;
        if (((obj instanceof f.a) || (obj instanceof androidx.appcompat.app.f)) && (decorView = this.f443k.getDecorView()) != null && i0.f.a(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.f444l.a().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? p0(keyCode, keyEvent) : s0(keyCode, keyEvent);
    }

    void U(int i7) {
        PanelFeatureState panelFeatureStateD0;
        PanelFeatureState panelFeatureStateD02 = d0(i7, true);
        if (panelFeatureStateD02.f468j != null) {
            Bundle bundle = new Bundle();
            panelFeatureStateD02.f468j.Q(bundle);
            if (bundle.size() > 0) {
                panelFeatureStateD02.f477s = bundle;
            }
            panelFeatureStateD02.f468j.d0();
            panelFeatureStateD02.f468j.clear();
        }
        panelFeatureStateD02.f476r = true;
        panelFeatureStateD02.f475q = true;
        if ((i7 != 108 && i7 != 0) || this.f449q == null || (panelFeatureStateD0 = d0(0, false)) == null) {
            return;
        }
        panelFeatureStateD0.f471m = false;
        A0(panelFeatureStateD0, null);
    }

    void V() {
        y yVar = this.f456x;
        if (yVar != null) {
            yVar.b();
        }
    }

    PanelFeatureState Y(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.L;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i7 = 0; i7 < length; i7++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i7];
            if (panelFeatureState != null && panelFeatureState.f468j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    @Override // androidx.appcompat.view.menu.e.a
    public boolean a(androidx.appcompat.view.menu.e eVar, MenuItem menuItem) {
        PanelFeatureState panelFeatureStateY;
        Window.Callback callbackF0 = f0();
        if (callbackF0 == null || this.R || (panelFeatureStateY = Y(eVar.D())) == null) {
            return false;
        }
        return callbackF0.onMenuItemSelected(panelFeatureStateY.f459a, menuItem);
    }

    final Context a0() {
        androidx.appcompat.app.a aVarM = m();
        Context contextK = aVarM != null ? aVarM.k() : null;
        return contextK == null ? this.f442j : contextK;
    }

    @Override // androidx.appcompat.view.menu.e.a
    public void b(androidx.appcompat.view.menu.e eVar) {
        B0(true);
    }

    @Override // androidx.appcompat.app.e
    public void d(View view, ViewGroup.LayoutParams layoutParams) {
        W();
        ((ViewGroup) this.A.findViewById(R.id.content)).addView(view, layoutParams);
        this.f444l.a().onContentChanged();
    }

    protected PanelFeatureState d0(int i7, boolean z6) {
        PanelFeatureState[] panelFeatureStateArr = this.L;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i7) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[i7 + 1];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.L = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i7];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        PanelFeatureState panelFeatureState2 = new PanelFeatureState(i7);
        panelFeatureStateArr[i7] = panelFeatureState2;
        return panelFeatureState2;
    }

    final CharSequence e0() {
        Object obj = this.f441i;
        return obj instanceof Activity ? ((Activity) obj).getTitle() : this.f448p;
    }

    @Override // androidx.appcompat.app.e
    public Context f(Context context) {
        this.O = true;
        int iN0 = n0(context, J());
        if (f434k0 && (context instanceof ContextThemeWrapper)) {
            try {
                p.a((ContextThemeWrapper) context, P(context, iN0, null));
                return context;
            } catch (IllegalStateException unused) {
            }
        }
        if (context instanceof i.d) {
            try {
                ((i.d) context).a(P(context, iN0, null));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (!f433j0) {
            return super.f(context);
        }
        Configuration configuration = new Configuration();
        configuration.uiMode = -1;
        configuration.fontScale = 0.0f;
        Configuration configuration2 = h.a(context, configuration).getResources().getConfiguration();
        Configuration configuration3 = context.getResources().getConfiguration();
        configuration2.uiMode = configuration3.uiMode;
        Configuration configurationP = P(context, iN0, configuration2.equals(configuration3) ? null : Z(configuration2, configuration3));
        i.d dVar = new i.d(context, e.i.Theme_AppCompat_Empty);
        dVar.a(configurationP);
        boolean z6 = false;
        try {
            z6 = context.getTheme() != null;
        } catch (NullPointerException unused3) {
        }
        if (z6) {
            b.C0176b.a(dVar.getTheme());
        }
        return super.f(dVar);
    }

    final Window.Callback f0() {
        return this.f443k.getCallback();
    }

    @Override // androidx.appcompat.app.e
    public <T extends View> T i(int i7) {
        W();
        return (T) this.f443k.findViewById(i7);
    }

    @Override // androidx.appcompat.app.e
    public int k() {
        return this.S;
    }

    @Override // androidx.appcompat.app.e
    public MenuInflater l() {
        if (this.f447o == null) {
            g0();
            androidx.appcompat.app.a aVar = this.f446n;
            this.f447o = new i.g(aVar != null ? aVar.k() : this.f442j);
        }
        return this.f447o;
    }

    @Override // androidx.appcompat.app.e
    public androidx.appcompat.app.a m() {
        g0();
        return this.f446n;
    }

    public boolean m0() {
        return this.f457y;
    }

    @Override // androidx.appcompat.app.e
    public void n() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f442j);
        if (layoutInflaterFrom.getFactory() == null) {
            i0.g.a(layoutInflaterFrom, this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof AppCompatDelegateImpl) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    int n0(Context context, int i7) {
        if (i7 == -100) {
            return -1;
        }
        if (i7 != -1) {
            if (i7 == 0) {
                if (((UiModeManager) context.getApplicationContext().getSystemService("uimode")).getNightMode() == 0) {
                    return -1;
                }
                return c0(context).c();
            }
            if (i7 != 1 && i7 != 2) {
                if (i7 == 3) {
                    return b0(context).c();
                }
                throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
            }
        }
        return i7;
    }

    @Override // androidx.appcompat.app.e
    public void o() {
        androidx.appcompat.app.a aVarM = m();
        if (aVarM == null || !aVarM.l()) {
            k0(0);
        }
    }

    boolean o0() {
        i.b bVar = this.f452t;
        if (bVar != null) {
            bVar.c();
            return true;
        }
        androidx.appcompat.app.a aVarM = m();
        return aVarM != null && aVarM.h();
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return R(view, str, context, attributeSet);
    }

    @Override // androidx.appcompat.app.e
    public void p(Configuration configuration) {
        androidx.appcompat.app.a aVarM;
        if (this.F && this.f458z && (aVarM = m()) != null) {
            aVarM.m(configuration);
        }
        androidx.appcompat.widget.i.b().g(this.f442j);
        G(false);
    }

    boolean p0(int i7, KeyEvent keyEvent) {
        if (i7 == 4) {
            this.N = (keyEvent.getFlags() & 128) != 0;
        } else if (i7 == 82) {
            q0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.e
    public void q(Bundle bundle) {
        this.O = true;
        G(false);
        X();
        Object obj = this.f441i;
        if (obj instanceof Activity) {
            String strC = null;
            try {
                strC = w.g.c((Activity) obj);
            } catch (IllegalArgumentException unused) {
            }
            if (strC != null) {
                androidx.appcompat.app.a aVarY0 = y0();
                if (aVarY0 == null) {
                    this.f436b0 = true;
                } else {
                    aVarY0.r(true);
                }
            }
            androidx.appcompat.app.e.c(this);
        }
        this.P = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0048  */
    @Override // androidx.appcompat.app.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void r() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f441i
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L9
            androidx.appcompat.app.e.x(r3)
        L9:
            boolean r0 = r3.Y
            if (r0 == 0) goto L18
            android.view.Window r0 = r3.f443k
            android.view.View r0 = r0.getDecorView()
            java.lang.Runnable r1 = r3.f435a0
            r0.removeCallbacks(r1)
        L18:
            r0 = 0
            r3.Q = r0
            r0 = 1
            r3.R = r0
            int r0 = r3.S
            r1 = -100
            if (r0 == r1) goto L48
            java.lang.Object r0 = r3.f441i
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L48
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L48
            n.g<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.f430g0
            java.lang.Object r1 = r3.f441i
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.S
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L57
        L48:
            n.g<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.f430g0
            java.lang.Object r1 = r3.f441i
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L57:
            androidx.appcompat.app.a r0 = r3.f446n
            if (r0 == 0) goto L5e
            r0.n()
        L5e:
            r3.M()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.r():void");
    }

    boolean r0(int i7, KeyEvent keyEvent) {
        androidx.appcompat.app.a aVarM = m();
        if (aVarM != null && aVarM.o(i7, keyEvent)) {
            return true;
        }
        PanelFeatureState panelFeatureState = this.M;
        if (panelFeatureState != null && z0(panelFeatureState, keyEvent.getKeyCode(), keyEvent, 1)) {
            PanelFeatureState panelFeatureState2 = this.M;
            if (panelFeatureState2 != null) {
                panelFeatureState2.f472n = true;
            }
            return true;
        }
        if (this.M == null) {
            PanelFeatureState panelFeatureStateD0 = d0(0, true);
            A0(panelFeatureStateD0, keyEvent);
            boolean zZ0 = z0(panelFeatureStateD0, keyEvent.getKeyCode(), keyEvent, 1);
            panelFeatureStateD0.f471m = false;
            if (zZ0) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.app.e
    public void s(Bundle bundle) {
        W();
    }

    boolean s0(int i7, KeyEvent keyEvent) {
        if (i7 == 4) {
            boolean z6 = this.N;
            this.N = false;
            PanelFeatureState panelFeatureStateD0 = d0(0, false);
            if (panelFeatureStateD0 != null && panelFeatureStateD0.f473o) {
                if (!z6) {
                    O(panelFeatureStateD0, true);
                }
                return true;
            }
            if (o0()) {
                return true;
            }
        } else if (i7 == 82) {
            t0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.e
    public void t() {
        androidx.appcompat.app.a aVarM = m();
        if (aVarM != null) {
            aVarM.s(true);
        }
    }

    @Override // androidx.appcompat.app.e
    public void u(Bundle bundle) {
    }

    void u0(int i7) {
        androidx.appcompat.app.a aVarM;
        if (i7 != 108 || (aVarM = m()) == null) {
            return;
        }
        aVarM.i(true);
    }

    @Override // androidx.appcompat.app.e
    public void v() {
        this.Q = true;
        F();
    }

    void v0(int i7) {
        if (i7 == 108) {
            androidx.appcompat.app.a aVarM = m();
            if (aVarM != null) {
                aVarM.i(false);
                return;
            }
            return;
        }
        if (i7 == 0) {
            PanelFeatureState panelFeatureStateD0 = d0(i7, true);
            if (panelFeatureStateD0.f473o) {
                O(panelFeatureStateD0, false);
            }
        }
    }

    @Override // androidx.appcompat.app.e
    public void w() {
        this.Q = false;
        androidx.appcompat.app.a aVarM = m();
        if (aVarM != null) {
            aVarM.s(false);
        }
    }

    void w0(ViewGroup viewGroup) {
    }

    final androidx.appcompat.app.a y0() {
        return this.f446n;
    }

    @Override // androidx.appcompat.app.e
    public boolean z(int i7) {
        int iC0 = C0(i7);
        if (this.J && iC0 == 108) {
            return false;
        }
        if (this.F && iC0 == 1) {
            this.F = false;
        }
        if (iC0 == 1) {
            H0();
            this.J = true;
            return true;
        }
        if (iC0 == 2) {
            H0();
            this.D = true;
            return true;
        }
        if (iC0 == 5) {
            H0();
            this.E = true;
            return true;
        }
        if (iC0 == 10) {
            H0();
            this.H = true;
            return true;
        }
        if (iC0 == 108) {
            H0();
            this.F = true;
            return true;
        }
        if (iC0 != 109) {
            return this.f443k.requestFeature(iC0);
        }
        H0();
        this.G = true;
        return true;
    }

    AppCompatDelegateImpl(Dialog dialog, androidx.appcompat.app.d dVar) {
        this(dialog.getContext(), dialog.getWindow(), dVar, dialog);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    private AppCompatDelegateImpl(Context context, Window window, androidx.appcompat.app.d dVar, Object obj) {
        n.g<String, Integer> gVar;
        Integer num;
        androidx.appcompat.app.c cVarI0;
        this.f456x = null;
        this.f457y = true;
        this.S = -100;
        this.f435a0 = new a();
        this.f442j = context;
        this.f445m = dVar;
        this.f441i = obj;
        if (this.S == -100 && (obj instanceof Dialog) && (cVarI0 = I0()) != null) {
            this.S = cVarI0.D().k();
        }
        if (this.S == -100 && (num = (gVar = f430g0).get(obj.getClass().getName())) != null) {
            this.S = num.intValue();
            gVar.remove(obj.getClass().getName());
        }
        if (window != null) {
            I(window);
        }
        androidx.appcompat.widget.i.h();
    }
}
