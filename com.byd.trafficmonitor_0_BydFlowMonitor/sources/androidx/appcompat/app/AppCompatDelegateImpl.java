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
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.lifecycle.Lifecycle;
import b.b.p.b;
import b.b.p.f;
import b.b.p.j.g;
import b.b.p.j.m;
import b.b.q.e0;
import b.b.q.j0;
import b.b.q.k0;
import b.b.q.s;
import b.h.e.c.f;
import b.h.m.b0;
import b.h.m.e;
import b.h.m.x;
import b.h.m.z;
import java.lang.Thread;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatDelegateImpl extends b.b.k.e implements g.a, LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b.e.g<String, Integer> f290e = new b.e.g<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final boolean f291f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f292g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final boolean f293h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final boolean f294i;
    public static boolean j;
    public boolean A;
    public boolean B;
    public ViewGroup C;
    public TextView D;
    public View E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean J;
    public boolean K;
    public boolean L;
    public boolean M;
    public PanelFeatureState[] N;
    public PanelFeatureState O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public boolean S;
    public boolean T;
    public int U;
    public int V;
    public boolean W;
    public boolean X;
    public m Y;
    public m Z;
    public boolean a0;
    public int b0;
    public final Runnable c0;
    public boolean d0;
    public Rect e0;
    public Rect f0;
    public b.b.k.g g0;
    public final Object k;
    public final Context l;
    public Window m;
    public k n;
    public final b.b.k.d o;
    public b.b.k.a p;
    public MenuInflater q;
    public CharSequence r;
    public b.b.q.o s;
    public i t;
    public t u;
    public b.b.p.b v;
    public ActionBarContextView w;
    public PopupWindow x;
    public Runnable y;
    public x z;

    public static final class PanelFeatureState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f295a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f296b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f297c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f298d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f299e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f300f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ViewGroup f301g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public View f302h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public View f303i;
        public b.b.p.j.g j;
        public b.b.p.j.e k;
        public Context l;
        public boolean m;
        public boolean n;
        public boolean o;
        public boolean p;
        public boolean q = false;
        public boolean r;
        public Bundle s;

        @SuppressLint({"BanParcelableUsage"})
        public static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new a();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f304b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public boolean f305c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public Bundle f306d;

            public class a implements Parcelable.ClassLoaderCreator<SavedState> {
                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public SavedState createFromParcel(Parcel parcel) {
                    return SavedState.j(parcel, null);
                }

                @Override // android.os.Parcelable.ClassLoaderCreator
                /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
                public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.j(parcel, classLoader);
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
                public SavedState[] newArray(int i2) {
                    return new SavedState[i2];
                }
            }

            public static SavedState j(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.f304b = parcel.readInt();
                boolean z = parcel.readInt() == 1;
                savedState.f305c = z;
                if (z) {
                    savedState.f306d = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i2) {
                parcel.writeInt(this.f304b);
                parcel.writeInt(this.f305c ? 1 : 0);
                if (this.f305c) {
                    parcel.writeBundle(this.f306d);
                }
            }
        }

        public PanelFeatureState(int i2) {
            this.f295a = i2;
        }

        public b.b.p.j.n a(m.a aVar) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                b.b.p.j.e eVar = new b.b.p.j.e(this.l, b.b.g.abc_list_menu_item_layout);
                this.k = eVar;
                eVar.k(aVar);
                this.j.b(this.k);
            }
            return this.k.g(this.f301g);
        }

        public boolean b() {
            if (this.f302h == null) {
                return false;
            }
            return this.f303i != null || this.k.b().getCount() > 0;
        }

        public void c(b.b.p.j.g gVar) {
            b.b.p.j.e eVar;
            b.b.p.j.g gVar2 = this.j;
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

        public void d(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme themeNewTheme = context.getResources().newTheme();
            themeNewTheme.setTo(context.getTheme());
            themeNewTheme.resolveAttribute(b.b.a.actionBarPopupTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                themeNewTheme.applyStyle(i2, true);
            }
            themeNewTheme.resolveAttribute(b.b.a.panelMenuListTheme, typedValue, true);
            int i3 = typedValue.resourceId;
            if (i3 != 0) {
                themeNewTheme.applyStyle(i3, true);
            } else {
                themeNewTheme.applyStyle(b.b.i.Theme_AppCompat_CompactMenu, true);
            }
            b.b.p.d dVar = new b.b.p.d(context, 0);
            dVar.getTheme().setTo(themeNewTheme);
            this.l = dVar;
            TypedArray typedArrayObtainStyledAttributes = dVar.obtainStyledAttributes(b.b.j.AppCompatTheme);
            this.f296b = typedArrayObtainStyledAttributes.getResourceId(b.b.j.AppCompatTheme_panelBackground, 0);
            this.f300f = typedArrayObtainStyledAttributes.getResourceId(b.b.j.AppCompatTheme_android_windowAnimationStyle, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public class a implements Thread.UncaughtExceptionHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Thread.UncaughtExceptionHandler f307a;

        public a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.f307a = uncaughtExceptionHandler;
        }

        public final boolean a(Throwable th) {
            String message;
            if (!(th instanceof Resources.NotFoundException) || (message = th.getMessage()) == null) {
                return false;
            }
            return message.contains("drawable") || message.contains("Drawable");
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            if (!a(th)) {
                this.f307a.uncaughtException(thread, th);
                return;
            }
            Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            notFoundException.initCause(th.getCause());
            notFoundException.setStackTrace(th.getStackTrace());
            this.f307a.uncaughtException(thread, notFoundException);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl.b0 & 1) != 0) {
                appCompatDelegateImpl.Y(0);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl2.b0 & 4096) != 0) {
                appCompatDelegateImpl2.Y(108);
            }
            AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl3.a0 = false;
            appCompatDelegateImpl3.b0 = 0;
        }
    }

    public class c implements b.h.m.p {
        public c() {
        }

        @Override // b.h.m.p
        public b0 a(View view, b0 b0Var) {
            int iH = b0Var.h();
            int iO0 = AppCompatDelegateImpl.this.O0(b0Var, null);
            if (iH != iO0) {
                b0Var = b0Var.n(b0Var.f(), iO0, b0Var.g(), b0Var.e());
            }
            return b.h.m.t.W(view, b0Var);
        }
    }

    public class d implements s.a {
        public d() {
        }

        @Override // b.b.q.s.a
        public void a(Rect rect) {
            rect.top = AppCompatDelegateImpl.this.O0(null, rect);
        }
    }

    public class e implements ContentFrameLayout.a {
        public e() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void a() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void onDetachedFromWindow() {
            AppCompatDelegateImpl.this.W();
        }
    }

    public class f implements Runnable {

        public class a extends z {
            public a() {
            }

            @Override // b.h.m.y
            public void a(View view) {
                AppCompatDelegateImpl.this.w.setAlpha(1.0f);
                AppCompatDelegateImpl.this.z.f(null);
                AppCompatDelegateImpl.this.z = null;
            }

            @Override // b.h.m.z, b.h.m.y
            public void b(View view) {
                AppCompatDelegateImpl.this.w.setVisibility(0);
            }
        }

        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            appCompatDelegateImpl.x.showAtLocation(appCompatDelegateImpl.w, 55, 0, 0);
            AppCompatDelegateImpl.this.Z();
            if (!AppCompatDelegateImpl.this.H0()) {
                AppCompatDelegateImpl.this.w.setAlpha(1.0f);
                AppCompatDelegateImpl.this.w.setVisibility(0);
            } else {
                AppCompatDelegateImpl.this.w.setAlpha(0.0f);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.z = b.h.m.t.c(appCompatDelegateImpl2.w).a(1.0f);
                AppCompatDelegateImpl.this.z.f(new a());
            }
        }
    }

    public class g extends z {
        public g() {
        }

        @Override // b.h.m.y
        public void a(View view) {
            AppCompatDelegateImpl.this.w.setAlpha(1.0f);
            AppCompatDelegateImpl.this.z.f(null);
            AppCompatDelegateImpl.this.z = null;
        }

        @Override // b.h.m.z, b.h.m.y
        public void b(View view) {
            AppCompatDelegateImpl.this.w.setVisibility(0);
            AppCompatDelegateImpl.this.w.sendAccessibilityEvent(32);
            if (AppCompatDelegateImpl.this.w.getParent() instanceof View) {
                b.h.m.t.h0((View) AppCompatDelegateImpl.this.w.getParent());
            }
        }
    }

    public class h implements b.b.k.b {
        public h() {
        }
    }

    public final class i implements m.a {
        public i() {
        }

        @Override // b.b.p.j.m.a
        public void a(b.b.p.j.g gVar, boolean z) {
            AppCompatDelegateImpl.this.P(gVar);
        }

        @Override // b.b.p.j.m.a
        public boolean b(b.b.p.j.g gVar) {
            Window.Callback callbackJ0 = AppCompatDelegateImpl.this.j0();
            if (callbackJ0 == null) {
                return true;
            }
            callbackJ0.onMenuOpened(108, gVar);
            return true;
        }
    }

    public class j implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public b.a f317a;

        public class a extends z {
            public a() {
            }

            @Override // b.h.m.y
            public void a(View view) {
                AppCompatDelegateImpl.this.w.setVisibility(8);
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                PopupWindow popupWindow = appCompatDelegateImpl.x;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (appCompatDelegateImpl.w.getParent() instanceof View) {
                    b.h.m.t.h0((View) AppCompatDelegateImpl.this.w.getParent());
                }
                AppCompatDelegateImpl.this.w.removeAllViews();
                AppCompatDelegateImpl.this.z.f(null);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.z = null;
                b.h.m.t.h0(appCompatDelegateImpl2.C);
            }
        }

        public j(b.a aVar) {
            this.f317a = aVar;
        }

        @Override // b.b.p.b.a
        public boolean a(b.b.p.b bVar, Menu menu) {
            b.h.m.t.h0(AppCompatDelegateImpl.this.C);
            return this.f317a.a(bVar, menu);
        }

        @Override // b.b.p.b.a
        public boolean b(b.b.p.b bVar, MenuItem menuItem) {
            return this.f317a.b(bVar, menuItem);
        }

        @Override // b.b.p.b.a
        public boolean c(b.b.p.b bVar, Menu menu) {
            return this.f317a.c(bVar, menu);
        }

        @Override // b.b.p.b.a
        public void d(b.b.p.b bVar) {
            this.f317a.d(bVar);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.x != null) {
                appCompatDelegateImpl.m.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.y);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.w != null) {
                appCompatDelegateImpl2.Z();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl3.z = b.h.m.t.c(appCompatDelegateImpl3.w).a(0.0f);
                AppCompatDelegateImpl.this.z.f(new a());
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            b.b.k.d dVar = appCompatDelegateImpl4.o;
            if (dVar != null) {
                dVar.onSupportActionModeFinished(appCompatDelegateImpl4.v);
            }
            AppCompatDelegateImpl appCompatDelegateImpl5 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl5.v = null;
            b.h.m.t.h0(appCompatDelegateImpl5.C);
        }
    }

    public class l extends m {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final PowerManager f321c;

        public l(Context context) {
            super();
            this.f321c = (PowerManager) context.getApplicationContext().getSystemService("power");
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public IntentFilter b() {
            if (Build.VERSION.SDK_INT < 21) {
                return null;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public int c() {
            return (Build.VERSION.SDK_INT < 21 || !this.f321c.isPowerSaveMode()) ? 1 : 2;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public void d() {
            AppCompatDelegateImpl.this.J();
        }
    }

    public abstract class m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public BroadcastReceiver f323a;

        public class a extends BroadcastReceiver {
            public a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                m.this.d();
            }
        }

        public m() {
        }

        public void a() {
            BroadcastReceiver broadcastReceiver = this.f323a;
            if (broadcastReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.l.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.f323a = null;
            }
        }

        public abstract IntentFilter b();

        public abstract int c();

        public abstract void d();

        public void e() {
            a();
            IntentFilter intentFilterB = b();
            if (intentFilterB == null || intentFilterB.countActions() == 0) {
                return;
            }
            if (this.f323a == null) {
                this.f323a = new a();
            }
            AppCompatDelegateImpl.this.l.registerReceiver(this.f323a, intentFilterB);
        }
    }

    public class n extends m {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final b.b.k.k f326c;

        public n(b.b.k.k kVar) {
            super();
            this.f326c = kVar;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public int c() {
            return this.f326c.d() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public void d() {
            AppCompatDelegateImpl.this.J();
        }
    }

    public static class o {
        public static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i2 = configuration.densityDpi;
            int i3 = configuration2.densityDpi;
            if (i2 != i3) {
                configuration3.densityDpi = i3;
            }
        }
    }

    public static class p {
        public static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            LocaleList locales = configuration.getLocales();
            LocaleList locales2 = configuration2.getLocales();
            if (locales.equals(locales2)) {
                return;
            }
            configuration3.setLocales(locales2);
            configuration3.locale = configuration2.locale;
        }
    }

    public static class q {
        public static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
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

    public static class r {
        public static void a(ContextThemeWrapper contextThemeWrapper, Configuration configuration) {
            contextThemeWrapper.applyOverrideConfiguration(configuration);
        }
    }

    public class s extends ContentFrameLayout {
        public s(Context context) {
            super(context);
        }

        public final boolean c(int i2, int i3) {
            return i2 < -5 || i3 < -5 || i2 > getWidth() + 5 || i3 > getHeight() + 5;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.X(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !c((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            AppCompatDelegateImpl.this.R(0);
            return true;
        }

        @Override // android.view.View
        public void setBackgroundResource(int i2) {
            setBackgroundDrawable(b.b.l.a.a.d(getContext(), i2));
        }
    }

    public final class t implements m.a {
        public t() {
        }

        @Override // b.b.p.j.m.a
        public void a(b.b.p.j.g gVar, boolean z) {
            b.b.p.j.g gVarF = gVar.F();
            boolean z2 = gVarF != gVar;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z2) {
                gVar = gVarF;
            }
            PanelFeatureState panelFeatureStateC0 = appCompatDelegateImpl.c0(gVar);
            if (panelFeatureStateC0 != null) {
                if (!z2) {
                    AppCompatDelegateImpl.this.S(panelFeatureStateC0, z);
                } else {
                    AppCompatDelegateImpl.this.O(panelFeatureStateC0.f295a, panelFeatureStateC0, gVarF);
                    AppCompatDelegateImpl.this.S(panelFeatureStateC0, true);
                }
            }
        }

        @Override // b.b.p.j.m.a
        public boolean b(b.b.p.j.g gVar) {
            Window.Callback callbackJ0;
            if (gVar != gVar.F()) {
                return true;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (!appCompatDelegateImpl.H || (callbackJ0 = appCompatDelegateImpl.j0()) == null || AppCompatDelegateImpl.this.T) {
                return true;
            }
            callbackJ0.onMenuOpened(108, gVar);
            return true;
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        boolean z = i2 < 21;
        f291f = z;
        f292g = new int[]{R.attr.windowBackground};
        f293h = !"robolectric".equals(Build.FINGERPRINT);
        f294i = i2 >= 17;
        if (!z || j) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new a(Thread.getDefaultUncaughtExceptionHandler()));
        j = true;
    }

    public AppCompatDelegateImpl(Activity activity, b.b.k.d dVar) {
        this(activity, null, dVar, activity);
    }

    public static Configuration d0(Configuration configuration, Configuration configuration2) {
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
                p.a(configuration, configuration2, configuration3);
            } else if (!b.h.l.b.a(configuration.locale, configuration2.locale)) {
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
                q.a(configuration, configuration2, configuration3);
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
                o.a(configuration, configuration2, configuration3);
            }
        }
        return configuration3;
    }

    @Override // b.b.k.e
    public boolean A(int i2) {
        int iG0 = G0(i2);
        if (this.L && iG0 == 108) {
            return false;
        }
        if (this.H && iG0 == 1) {
            this.H = false;
        }
        if (iG0 == 1) {
            K0();
            this.L = true;
            return true;
        }
        if (iG0 == 2) {
            K0();
            this.F = true;
            return true;
        }
        if (iG0 == 5) {
            K0();
            this.G = true;
            return true;
        }
        if (iG0 == 10) {
            K0();
            this.J = true;
            return true;
        }
        if (iG0 == 108) {
            K0();
            this.H = true;
            return true;
        }
        if (iG0 != 109) {
            return this.m.requestFeature(iG0);
        }
        K0();
        this.I = true;
        return true;
    }

    public void A0(ViewGroup viewGroup) {
    }

    @Override // b.b.k.e
    public void B(int i2) {
        a0();
        ViewGroup viewGroup = (ViewGroup) this.C.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.l).inflate(i2, viewGroup);
        this.n.a().onContentChanged();
    }

    public final void B0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        int i2;
        ViewGroup.LayoutParams layoutParams;
        if (panelFeatureState.o || this.T) {
            return;
        }
        if (panelFeatureState.f295a == 0) {
            if ((this.l.getResources().getConfiguration().screenLayout & 15) == 4) {
                return;
            }
        }
        Window.Callback callbackJ0 = j0();
        if (callbackJ0 != null && !callbackJ0.onMenuOpened(panelFeatureState.f295a, panelFeatureState.j)) {
            S(panelFeatureState, true);
            return;
        }
        WindowManager windowManager = (WindowManager) this.l.getSystemService("window");
        if (windowManager != null && E0(panelFeatureState, keyEvent)) {
            ViewGroup viewGroup = panelFeatureState.f301g;
            if (viewGroup != null && !panelFeatureState.q) {
                View view = panelFeatureState.f303i;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i2 = -1;
                }
                panelFeatureState.n = false;
                WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i2, -2, panelFeatureState.f298d, panelFeatureState.f299e, 1002, 8519680, -3);
                layoutParams2.gravity = panelFeatureState.f297c;
                layoutParams2.windowAnimations = panelFeatureState.f300f;
                windowManager.addView(panelFeatureState.f301g, layoutParams2);
                panelFeatureState.o = true;
            }
            if (viewGroup == null) {
                if (!m0(panelFeatureState) || panelFeatureState.f301g == null) {
                    return;
                }
            } else if (panelFeatureState.q && viewGroup.getChildCount() > 0) {
                panelFeatureState.f301g.removeAllViews();
            }
            if (!l0(panelFeatureState) || !panelFeatureState.b()) {
                panelFeatureState.q = true;
                return;
            }
            ViewGroup.LayoutParams layoutParams3 = panelFeatureState.f302h.getLayoutParams();
            if (layoutParams3 == null) {
                layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
            }
            panelFeatureState.f301g.setBackgroundResource(panelFeatureState.f296b);
            ViewParent parent = panelFeatureState.f302h.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(panelFeatureState.f302h);
            }
            panelFeatureState.f301g.addView(panelFeatureState.f302h, layoutParams3);
            if (!panelFeatureState.f302h.hasFocus()) {
                panelFeatureState.f302h.requestFocus();
            }
            i2 = -2;
            panelFeatureState.n = false;
            WindowManager.LayoutParams layoutParams22 = new WindowManager.LayoutParams(i2, -2, panelFeatureState.f298d, panelFeatureState.f299e, 1002, 8519680, -3);
            layoutParams22.gravity = panelFeatureState.f297c;
            layoutParams22.windowAnimations = panelFeatureState.f300f;
            windowManager.addView(panelFeatureState.f301g, layoutParams22);
            panelFeatureState.o = true;
        }
    }

    @Override // b.b.k.e
    public void C(View view) {
        a0();
        ViewGroup viewGroup = (ViewGroup) this.C.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.n.a().onContentChanged();
    }

    public final b.b.k.a C0() {
        return this.p;
    }

    @Override // b.b.k.e
    public void D(View view, ViewGroup.LayoutParams layoutParams) {
        a0();
        ViewGroup viewGroup = (ViewGroup) this.C.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.n.a().onContentChanged();
    }

    public final boolean D0(PanelFeatureState panelFeatureState, int i2, KeyEvent keyEvent, int i3) {
        b.b.p.j.g gVar;
        boolean zPerformShortcut = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.m || E0(panelFeatureState, keyEvent)) && (gVar = panelFeatureState.j) != null) {
            zPerformShortcut = gVar.performShortcut(i2, keyEvent, i3);
        }
        if (zPerformShortcut && (i3 & 1) == 0 && this.s == null) {
            S(panelFeatureState, true);
        }
        return zPerformShortcut;
    }

    @Override // b.b.k.e
    public void E(int i2) {
        if (this.U != i2) {
            this.U = i2;
            if (this.Q) {
                J();
            }
        }
    }

    public final boolean E0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        b.b.q.o oVar;
        b.b.q.o oVar2;
        b.b.q.o oVar3;
        if (this.T) {
            return false;
        }
        if (panelFeatureState.m) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.O;
        if (panelFeatureState2 != null && panelFeatureState2 != panelFeatureState) {
            S(panelFeatureState2, false);
        }
        Window.Callback callbackJ0 = j0();
        if (callbackJ0 != null) {
            panelFeatureState.f303i = callbackJ0.onCreatePanelView(panelFeatureState.f295a);
        }
        int i2 = panelFeatureState.f295a;
        boolean z = i2 == 0 || i2 == 108;
        if (z && (oVar3 = this.s) != null) {
            oVar3.f();
        }
        if (panelFeatureState.f303i == null && (!z || !(C0() instanceof b.b.k.i))) {
            b.b.p.j.g gVar = panelFeatureState.j;
            if (gVar == null || panelFeatureState.r) {
                if (gVar == null && (!n0(panelFeatureState) || panelFeatureState.j == null)) {
                    return false;
                }
                if (z && this.s != null) {
                    if (this.t == null) {
                        this.t = new i();
                    }
                    this.s.a(panelFeatureState.j, this.t);
                }
                panelFeatureState.j.h0();
                if (!callbackJ0.onCreatePanelMenu(panelFeatureState.f295a, panelFeatureState.j)) {
                    panelFeatureState.c(null);
                    if (z && (oVar = this.s) != null) {
                        oVar.a(null, this.t);
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
            if (!callbackJ0.onPreparePanel(0, panelFeatureState.f303i, panelFeatureState.j)) {
                if (z && (oVar2 = this.s) != null) {
                    oVar2.a(null, this.t);
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

    @Override // b.b.k.e
    public void F(Toolbar toolbar) {
        if (this.k instanceof Activity) {
            b.b.k.a aVarN = n();
            if (aVarN instanceof b.b.k.l) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            this.q = null;
            if (aVarN != null) {
                aVarN.n();
            }
            if (toolbar != null) {
                b.b.k.i iVar = new b.b.k.i(toolbar, i0(), this.n);
                this.p = iVar;
                this.m.setCallback(iVar.w());
            } else {
                this.p = null;
                this.m.setCallback(this.n);
            }
            p();
        }
    }

    public final void F0(boolean z) {
        b.b.q.o oVar = this.s;
        if (oVar == null || !oVar.g() || (ViewConfiguration.get(this.l).hasPermanentMenuKey() && !this.s.b())) {
            PanelFeatureState panelFeatureStateH0 = h0(0, true);
            panelFeatureStateH0.q = true;
            S(panelFeatureStateH0, false);
            B0(panelFeatureStateH0, null);
            return;
        }
        Window.Callback callbackJ0 = j0();
        if (this.s.c() && z) {
            this.s.d();
            if (this.T) {
                return;
            }
            callbackJ0.onPanelClosed(108, h0(0, true).j);
            return;
        }
        if (callbackJ0 == null || this.T) {
            return;
        }
        if (this.a0 && (this.b0 & 1) != 0) {
            this.m.getDecorView().removeCallbacks(this.c0);
            this.c0.run();
        }
        PanelFeatureState panelFeatureStateH02 = h0(0, true);
        b.b.p.j.g gVar = panelFeatureStateH02.j;
        if (gVar == null || panelFeatureStateH02.r || !callbackJ0.onPreparePanel(0, panelFeatureStateH02.f303i, gVar)) {
            return;
        }
        callbackJ0.onMenuOpened(108, panelFeatureStateH02.j);
        this.s.e();
    }

    @Override // b.b.k.e
    public void G(int i2) {
        this.V = i2;
    }

    public final int G0(int i2) {
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

    @Override // b.b.k.e
    public final void H(CharSequence charSequence) {
        this.r = charSequence;
        b.b.q.o oVar = this.s;
        if (oVar != null) {
            oVar.setWindowTitle(charSequence);
            return;
        }
        if (C0() != null) {
            C0().t(charSequence);
            return;
        }
        TextView textView = this.D;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public final boolean H0() {
        ViewGroup viewGroup;
        return this.B && (viewGroup = this.C) != null && b.h.m.t.P(viewGroup);
    }

    @Override // b.b.k.e
    public b.b.p.b I(b.a aVar) {
        b.b.k.d dVar;
        if (aVar == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        b.b.p.b bVar = this.v;
        if (bVar != null) {
            bVar.c();
        }
        j jVar = new j(aVar);
        b.b.k.a aVarN = n();
        if (aVarN != null) {
            b.b.p.b bVarU = aVarN.u(jVar);
            this.v = bVarU;
            if (bVarU != null && (dVar = this.o) != null) {
                dVar.onSupportActionModeStarted(bVarU);
            }
        }
        if (this.v == null) {
            this.v = J0(jVar);
        }
        return this.v;
    }

    public final boolean I0(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.m.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || b.h.m.t.O((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    public boolean J() {
        return K(true);
    }

    public b.b.p.b J0(b.a aVar) {
        b.b.p.b bVarOnWindowStartingSupportActionMode;
        Context dVar;
        b.b.k.d dVar2;
        Z();
        b.b.p.b bVar = this.v;
        if (bVar != null) {
            bVar.c();
        }
        if (!(aVar instanceof j)) {
            aVar = new j(aVar);
        }
        b.b.k.d dVar3 = this.o;
        if (dVar3 == null || this.T) {
            bVarOnWindowStartingSupportActionMode = null;
        } else {
            try {
                bVarOnWindowStartingSupportActionMode = dVar3.onWindowStartingSupportActionMode(aVar);
            } catch (AbstractMethodError unused) {
                bVarOnWindowStartingSupportActionMode = null;
            }
        }
        if (bVarOnWindowStartingSupportActionMode != null) {
            this.v = bVarOnWindowStartingSupportActionMode;
        } else {
            if (this.w == null) {
                if (this.K) {
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = this.l.getTheme();
                    theme.resolveAttribute(b.b.a.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Resources.Theme themeNewTheme = this.l.getResources().newTheme();
                        themeNewTheme.setTo(theme);
                        themeNewTheme.applyStyle(typedValue.resourceId, true);
                        dVar = new b.b.p.d(this.l, 0);
                        dVar.getTheme().setTo(themeNewTheme);
                    } else {
                        dVar = this.l;
                    }
                    this.w = new ActionBarContextView(dVar);
                    PopupWindow popupWindow = new PopupWindow(dVar, (AttributeSet) null, b.b.a.actionModePopupWindowStyle);
                    this.x = popupWindow;
                    b.h.n.h.b(popupWindow, 2);
                    this.x.setContentView(this.w);
                    this.x.setWidth(-1);
                    dVar.getTheme().resolveAttribute(b.b.a.actionBarSize, typedValue, true);
                    this.w.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, dVar.getResources().getDisplayMetrics()));
                    this.x.setHeight(-2);
                    this.y = new f();
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.C.findViewById(b.b.f.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(e0()));
                        this.w = (ActionBarContextView) viewStubCompat.a();
                    }
                }
            }
            if (this.w != null) {
                Z();
                this.w.k();
                b.b.p.e eVar = new b.b.p.e(this.w.getContext(), this.w, aVar, this.x == null);
                if (aVar.c(eVar, eVar.e())) {
                    eVar.k();
                    this.w.h(eVar);
                    this.v = eVar;
                    if (H0()) {
                        this.w.setAlpha(0.0f);
                        x xVarA = b.h.m.t.c(this.w).a(1.0f);
                        this.z = xVarA;
                        xVarA.f(new g());
                    } else {
                        this.w.setAlpha(1.0f);
                        this.w.setVisibility(0);
                        this.w.sendAccessibilityEvent(32);
                        if (this.w.getParent() instanceof View) {
                            b.h.m.t.h0((View) this.w.getParent());
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
        b.b.p.b bVar2 = this.v;
        if (bVar2 != null && (dVar2 = this.o) != null) {
            dVar2.onSupportActionModeStarted(bVar2);
        }
        return this.v;
    }

    public final boolean K(boolean z) {
        if (this.T) {
            return false;
        }
        int iN = N();
        boolean zM0 = M0(r0(this.l, iN), z);
        if (iN == 0) {
            g0(this.l).e();
        } else {
            m mVar = this.Y;
            if (mVar != null) {
                mVar.a();
            }
        }
        if (iN == 3) {
            f0(this.l).e();
        } else {
            m mVar2 = this.Z;
            if (mVar2 != null) {
                mVar2.a();
            }
        }
        return zM0;
    }

    public final void K0() {
        if (this.B) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public final void L() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.C.findViewById(R.id.content);
        View decorView = this.m.getDecorView();
        contentFrameLayout.b(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray typedArrayObtainStyledAttributes = this.l.obtainStyledAttributes(b.b.j.AppCompatTheme);
        typedArrayObtainStyledAttributes.getValue(b.b.j.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        typedArrayObtainStyledAttributes.getValue(b.b.j.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        int i2 = b.b.j.AppCompatTheme_windowFixedWidthMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            typedArrayObtainStyledAttributes.getValue(i2, contentFrameLayout.getFixedWidthMajor());
        }
        int i3 = b.b.j.AppCompatTheme_windowFixedWidthMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            typedArrayObtainStyledAttributes.getValue(i3, contentFrameLayout.getFixedWidthMinor());
        }
        int i4 = b.b.j.AppCompatTheme_windowFixedHeightMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            typedArrayObtainStyledAttributes.getValue(i4, contentFrameLayout.getFixedHeightMajor());
        }
        int i5 = b.b.j.AppCompatTheme_windowFixedHeightMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i5)) {
            typedArrayObtainStyledAttributes.getValue(i5, contentFrameLayout.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    public final AppCompatActivity L0() {
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

    public final void M(Window window) {
        if (this.m != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof k) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        k kVar = new k(callback);
        this.n = kVar;
        window.setCallback(kVar);
        e0 e0VarU = e0.u(this.l, null, f292g);
        Drawable drawableH = e0VarU.h(0);
        if (drawableH != null) {
            window.setBackgroundDrawable(drawableH);
        }
        e0VarU.w();
        this.m = window;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean M0(int r7, boolean r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.l
            r1 = 0
            android.content.res.Configuration r0 = r6.T(r0, r7, r1)
            boolean r2 = r6.p0()
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
            boolean r8 = androidx.appcompat.app.AppCompatDelegateImpl.f293h
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
            b.h.d.a.l(r8)
            r8 = r4
            goto L48
        L47:
            r8 = 0
        L48:
            if (r8 != 0) goto L50
            if (r3 == r0) goto L50
            r6.N0(r0, r2, r1)
            goto L51
        L50:
            r4 = r8
        L51:
            if (r4 == 0) goto L5e
            java.lang.Object r8 = r6.k
            boolean r0 = r8 instanceof androidx.appcompat.app.AppCompatActivity
            if (r0 == 0) goto L5e
            androidx.appcompat.app.AppCompatActivity r8 = (androidx.appcompat.app.AppCompatActivity) r8
            r8.onNightModeChanged(r7)
        L5e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.M0(int, boolean):boolean");
    }

    public final int N() {
        int i2 = this.U;
        return i2 != -100 ? i2 : b.b.k.e.j();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void N0(int i2, boolean z, Configuration configuration) {
        Resources resources = this.l.getResources();
        Configuration configuration2 = new Configuration(resources.getConfiguration());
        if (configuration != null) {
            configuration2.updateFrom(configuration);
        }
        configuration2.uiMode = i2 | (resources.getConfiguration().uiMode & (-49));
        resources.updateConfiguration(configuration2, null);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 26) {
            b.b.k.h.a(resources);
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
                if (activity instanceof b.n.g) {
                    if (((b.n.g) activity).getLifecycle().b().isAtLeast(Lifecycle.State.STARTED)) {
                        activity.onConfigurationChanged(configuration2);
                    }
                } else if (this.S) {
                    activity.onConfigurationChanged(configuration2);
                }
            }
        }
    }

    public void O(int i2, PanelFeatureState panelFeatureState, Menu menu) {
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

    public final int O0(b0 b0Var, Rect rect) {
        boolean z;
        boolean z2;
        int iH = b0Var != null ? b0Var.h() : rect != null ? rect.top : 0;
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
                    rect2.set(b0Var.f(), b0Var.h(), b0Var.g(), b0Var.e());
                }
                k0.a(this.C, rect2, rect3);
                int i2 = rect2.top;
                int i3 = rect2.left;
                int i4 = rect2.right;
                b0 b0VarG = b.h.m.t.G(this.C);
                int iF = b0VarG == null ? 0 : b0VarG.f();
                int iG = b0VarG == null ? 0 : b0VarG.g();
                if (marginLayoutParams.topMargin == i2 && marginLayoutParams.leftMargin == i3 && marginLayoutParams.rightMargin == i4) {
                    z2 = false;
                } else {
                    marginLayoutParams.topMargin = i2;
                    marginLayoutParams.leftMargin = i3;
                    marginLayoutParams.rightMargin = i4;
                    z2 = true;
                }
                if (i2 <= 0 || this.E != null) {
                    View view = this.E;
                    if (view != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                        int i5 = marginLayoutParams2.height;
                        int i6 = marginLayoutParams.topMargin;
                        if (i5 != i6 || marginLayoutParams2.leftMargin != iF || marginLayoutParams2.rightMargin != iG) {
                            marginLayoutParams2.height = i6;
                            marginLayoutParams2.leftMargin = iF;
                            marginLayoutParams2.rightMargin = iG;
                            this.E.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view2 = new View(this.l);
                    this.E = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = iF;
                    layoutParams.rightMargin = iG;
                    this.C.addView(this.E, -1, layoutParams);
                }
                View view3 = this.E;
                z = view3 != null;
                if (z && view3.getVisibility() != 0) {
                    P0(this.E);
                }
                if (!this.J && z) {
                    iH = 0;
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
        return iH;
    }

    public void P(b.b.p.j.g gVar) {
        if (this.M) {
            return;
        }
        this.M = true;
        this.s.l();
        Window.Callback callbackJ0 = j0();
        if (callbackJ0 != null && !this.T) {
            callbackJ0.onPanelClosed(108, gVar);
        }
        this.M = false;
    }

    public final void P0(View view) {
        view.setBackgroundColor((b.h.m.t.I(view) & 8192) != 0 ? b.h.e.a.b(this.l, b.b.c.abc_decor_view_status_guard_light) : b.h.e.a.b(this.l, b.b.c.abc_decor_view_status_guard));
    }

    public final void Q() {
        m mVar = this.Y;
        if (mVar != null) {
            mVar.a();
        }
        m mVar2 = this.Z;
        if (mVar2 != null) {
            mVar2.a();
        }
    }

    public void R(int i2) {
        S(h0(i2, true), true);
    }

    public void S(PanelFeatureState panelFeatureState, boolean z) {
        ViewGroup viewGroup;
        b.b.q.o oVar;
        if (z && panelFeatureState.f295a == 0 && (oVar = this.s) != null && oVar.c()) {
            P(panelFeatureState.j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.l.getSystemService("window");
        if (windowManager != null && panelFeatureState.o && (viewGroup = panelFeatureState.f301g) != null) {
            windowManager.removeView(viewGroup);
            if (z) {
                O(panelFeatureState.f295a, panelFeatureState, null);
            }
        }
        panelFeatureState.m = false;
        panelFeatureState.n = false;
        panelFeatureState.o = false;
        panelFeatureState.f302h = null;
        panelFeatureState.q = true;
        if (this.O == panelFeatureState) {
            this.O = null;
        }
    }

    public final Configuration T(Context context, int i2, Configuration configuration) {
        int i3 = i2 != 1 ? i2 != 2 ? context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i3 | (configuration2.uiMode & (-49));
        return configuration2;
    }

    public final ViewGroup U() {
        ViewGroup viewGroup;
        TypedArray typedArrayObtainStyledAttributes = this.l.obtainStyledAttributes(b.b.j.AppCompatTheme);
        int i2 = b.b.j.AppCompatTheme_windowActionBar;
        if (!typedArrayObtainStyledAttributes.hasValue(i2)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(b.b.j.AppCompatTheme_windowNoTitle, false)) {
            A(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(i2, false)) {
            A(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(b.b.j.AppCompatTheme_windowActionBarOverlay, false)) {
            A(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(b.b.j.AppCompatTheme_windowActionModeOverlay, false)) {
            A(10);
        }
        this.K = typedArrayObtainStyledAttributes.getBoolean(b.b.j.AppCompatTheme_android_windowIsFloating, false);
        typedArrayObtainStyledAttributes.recycle();
        b0();
        this.m.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.l);
        if (this.L) {
            viewGroup = this.J ? (ViewGroup) layoutInflaterFrom.inflate(b.b.g.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(b.b.g.abc_screen_simple, (ViewGroup) null);
        } else if (this.K) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(b.b.g.abc_dialog_title_material, (ViewGroup) null);
            this.I = false;
            this.H = false;
        } else if (this.H) {
            TypedValue typedValue = new TypedValue();
            this.l.getTheme().resolveAttribute(b.b.a.actionBarTheme, typedValue, true);
            int i3 = typedValue.resourceId;
            viewGroup = (ViewGroup) LayoutInflater.from(i3 != 0 ? new b.b.p.d(this.l, i3) : this.l).inflate(b.b.g.abc_screen_toolbar, (ViewGroup) null);
            b.b.q.o oVar = (b.b.q.o) viewGroup.findViewById(b.b.f.decor_content_parent);
            this.s = oVar;
            oVar.setWindowCallback(j0());
            if (this.I) {
                this.s.k(109);
            }
            if (this.F) {
                this.s.k(2);
            }
            if (this.G) {
                this.s.k(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.H + ", windowActionBarOverlay: " + this.I + ", android:windowIsFloating: " + this.K + ", windowActionModeOverlay: " + this.J + ", windowNoTitle: " + this.L + " }");
        }
        if (Build.VERSION.SDK_INT >= 21) {
            b.h.m.t.x0(viewGroup, new c());
        } else if (viewGroup instanceof b.b.q.s) {
            ((b.b.q.s) viewGroup).setOnFitSystemWindowsListener(new d());
        }
        if (this.s == null) {
            this.D = (TextView) viewGroup.findViewById(b.b.f.title);
        }
        k0.c(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(b.b.f.action_bar_activity_content);
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

    /* JADX WARN: Multi-variable type inference failed */
    public View V(View view, String str, Context context, AttributeSet attributeSet) {
        boolean zI0 = false;
        if (this.g0 == null) {
            String string = this.l.obtainStyledAttributes(b.b.j.AppCompatTheme).getString(b.b.j.AppCompatTheme_viewInflaterClass);
            if (string == null) {
                this.g0 = new b.b.k.g();
            } else {
                try {
                    this.g0 = (b.b.k.g) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.g0 = new b.b.k.g();
                }
            }
        }
        boolean z = f291f;
        if (z) {
            if (!(attributeSet instanceof XmlPullParser)) {
                zI0 = I0((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                zI0 = true;
            }
        }
        return this.g0.q(view, str, context, attributeSet, zI0, z, true, j0.b());
    }

    public void W() {
        b.b.p.j.g gVar;
        b.b.q.o oVar = this.s;
        if (oVar != null) {
            oVar.l();
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
        Z();
        PanelFeatureState panelFeatureStateH0 = h0(0, false);
        if (panelFeatureStateH0 == null || (gVar = panelFeatureStateH0.j) == null) {
            return;
        }
        gVar.close();
    }

    public boolean X(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.k;
        if (((obj instanceof e.a) || (obj instanceof b.b.k.f)) && (decorView = this.m.getDecorView()) != null && b.h.m.e.d(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.n.a().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? t0(keyCode, keyEvent) : w0(keyCode, keyEvent);
    }

    public void Y(int i2) {
        PanelFeatureState panelFeatureStateH0;
        PanelFeatureState panelFeatureStateH02 = h0(i2, true);
        if (panelFeatureStateH02.j != null) {
            Bundle bundle = new Bundle();
            panelFeatureStateH02.j.T(bundle);
            if (bundle.size() > 0) {
                panelFeatureStateH02.s = bundle;
            }
            panelFeatureStateH02.j.h0();
            panelFeatureStateH02.j.clear();
        }
        panelFeatureStateH02.r = true;
        panelFeatureStateH02.q = true;
        if ((i2 != 108 && i2 != 0) || this.s == null || (panelFeatureStateH0 = h0(0, false)) == null) {
            return;
        }
        panelFeatureStateH0.m = false;
        E0(panelFeatureStateH0, null);
    }

    public void Z() {
        x xVar = this.z;
        if (xVar != null) {
            xVar.b();
        }
    }

    @Override // b.b.p.j.g.a
    public boolean a(b.b.p.j.g gVar, MenuItem menuItem) {
        PanelFeatureState panelFeatureStateC0;
        Window.Callback callbackJ0 = j0();
        if (callbackJ0 == null || this.T || (panelFeatureStateC0 = c0(gVar.F())) == null) {
            return false;
        }
        return callbackJ0.onMenuItemSelected(panelFeatureStateC0.f295a, menuItem);
    }

    public final void a0() {
        if (this.B) {
            return;
        }
        this.C = U();
        CharSequence charSequenceI0 = i0();
        if (!TextUtils.isEmpty(charSequenceI0)) {
            b.b.q.o oVar = this.s;
            if (oVar != null) {
                oVar.setWindowTitle(charSequenceI0);
            } else if (C0() != null) {
                C0().t(charSequenceI0);
            } else {
                TextView textView = this.D;
                if (textView != null) {
                    textView.setText(charSequenceI0);
                }
            }
        }
        L();
        A0(this.C);
        this.B = true;
        PanelFeatureState panelFeatureStateH0 = h0(0, false);
        if (this.T) {
            return;
        }
        if (panelFeatureStateH0 == null || panelFeatureStateH0.j == null) {
            o0(108);
        }
    }

    @Override // b.b.p.j.g.a
    public void b(b.b.p.j.g gVar) {
        F0(true);
    }

    public final void b0() {
        if (this.m == null) {
            Object obj = this.k;
            if (obj instanceof Activity) {
                M(((Activity) obj).getWindow());
            }
        }
        if (this.m == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    public PanelFeatureState c0(Menu menu) {
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

    @Override // b.b.k.e
    public void d(View view, ViewGroup.LayoutParams layoutParams) {
        a0();
        ((ViewGroup) this.C.findViewById(R.id.content)).addView(view, layoutParams);
        this.n.a().onContentChanged();
    }

    public final Context e0() {
        b.b.k.a aVarN = n();
        Context contextK = aVarN != null ? aVarN.k() : null;
        return contextK == null ? this.l : contextK;
    }

    @Override // b.b.k.e
    public Context f(Context context) {
        this.Q = true;
        int iR0 = r0(context, N());
        if (f294i && (context instanceof ContextThemeWrapper)) {
            try {
                r.a((ContextThemeWrapper) context, T(context, iR0, null));
                return context;
            } catch (IllegalStateException unused) {
            }
        }
        if (context instanceof b.b.p.d) {
            try {
                ((b.b.p.d) context).a(T(context, iR0, null));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (!f293h) {
            return super.f(context);
        }
        try {
            Configuration configuration = context.getPackageManager().getResourcesForApplication(context.getApplicationInfo()).getConfiguration();
            Configuration configuration2 = context.getResources().getConfiguration();
            Configuration configurationT = T(context, iR0, configuration.equals(configuration2) ? null : d0(configuration, configuration2));
            b.b.p.d dVar = new b.b.p.d(context, b.b.i.Theme_AppCompat_Empty);
            dVar.a(configurationT);
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

    public final m f0(Context context) {
        if (this.Z == null) {
            this.Z = new l(context);
        }
        return this.Z;
    }

    public final m g0(Context context) {
        if (this.Y == null) {
            this.Y = new n(b.b.k.k.a(context));
        }
        return this.Y;
    }

    public PanelFeatureState h0(int i2, boolean z) {
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

    @Override // b.b.k.e
    public <T extends View> T i(int i2) {
        a0();
        return (T) this.m.findViewById(i2);
    }

    public final CharSequence i0() {
        Object obj = this.k;
        return obj instanceof Activity ? ((Activity) obj).getTitle() : this.r;
    }

    public final Window.Callback j0() {
        return this.m.getCallback();
    }

    @Override // b.b.k.e
    public final b.b.k.b k() {
        return new h();
    }

    public final void k0() {
        a0();
        if (this.H && this.p == null) {
            Object obj = this.k;
            if (obj instanceof Activity) {
                this.p = new b.b.k.l((Activity) this.k, this.I);
            } else if (obj instanceof Dialog) {
                this.p = new b.b.k.l((Dialog) this.k);
            }
            b.b.k.a aVar = this.p;
            if (aVar != null) {
                aVar.r(this.d0);
            }
        }
    }

    @Override // b.b.k.e
    public int l() {
        return this.U;
    }

    public final boolean l0(PanelFeatureState panelFeatureState) {
        View view = panelFeatureState.f303i;
        if (view != null) {
            panelFeatureState.f302h = view;
            return true;
        }
        if (panelFeatureState.j == null) {
            return false;
        }
        if (this.u == null) {
            this.u = new t();
        }
        View view2 = (View) panelFeatureState.a(this.u);
        panelFeatureState.f302h = view2;
        return view2 != null;
    }

    @Override // b.b.k.e
    public MenuInflater m() {
        if (this.q == null) {
            k0();
            b.b.k.a aVar = this.p;
            this.q = new b.b.p.g(aVar != null ? aVar.k() : this.l);
        }
        return this.q;
    }

    public final boolean m0(PanelFeatureState panelFeatureState) {
        panelFeatureState.d(e0());
        panelFeatureState.f301g = new s(panelFeatureState.l);
        panelFeatureState.f297c = 81;
        return true;
    }

    @Override // b.b.k.e
    public b.b.k.a n() {
        k0();
        return this.p;
    }

    public final boolean n0(PanelFeatureState panelFeatureState) {
        Context context = this.l;
        int i2 = panelFeatureState.f295a;
        if ((i2 == 0 || i2 == 108) && this.s != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(b.b.a.actionBarTheme, typedValue, true);
            Resources.Theme themeNewTheme = null;
            if (typedValue.resourceId != 0) {
                themeNewTheme = context.getResources().newTheme();
                themeNewTheme.setTo(theme);
                themeNewTheme.applyStyle(typedValue.resourceId, true);
                themeNewTheme.resolveAttribute(b.b.a.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(b.b.a.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (themeNewTheme == null) {
                    themeNewTheme = context.getResources().newTheme();
                    themeNewTheme.setTo(theme);
                }
                themeNewTheme.applyStyle(typedValue.resourceId, true);
            }
            if (themeNewTheme != null) {
                b.b.p.d dVar = new b.b.p.d(context, 0);
                dVar.getTheme().setTo(themeNewTheme);
                context = dVar;
            }
        }
        b.b.p.j.g gVar = new b.b.p.j.g(context);
        gVar.V(this);
        panelFeatureState.c(gVar);
        return true;
    }

    @Override // b.b.k.e
    public void o() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.l);
        if (layoutInflaterFrom.getFactory() == null) {
            b.h.m.f.b(layoutInflaterFrom, this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof AppCompatDelegateImpl) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public final void o0(int i2) {
        this.b0 = (1 << i2) | this.b0;
        if (this.a0) {
            return;
        }
        b.h.m.t.c0(this.m.getDecorView(), this.c0);
        this.a0 = true;
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return V(view, str, context, attributeSet);
    }

    @Override // b.b.k.e
    public void p() {
        b.b.k.a aVarN = n();
        if (aVarN == null || !aVarN.l()) {
            o0(0);
        }
    }

    public final boolean p0() {
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

    @Override // b.b.k.e
    public void q(Configuration configuration) {
        b.b.k.a aVarN;
        if (this.H && this.B && (aVarN = n()) != null) {
            aVarN.m(configuration);
        }
        b.b.q.f.b().g(this.l);
        K(false);
    }

    public boolean q0() {
        return this.A;
    }

    @Override // b.b.k.e
    public void r(Bundle bundle) {
        this.Q = true;
        K(false);
        b0();
        Object obj = this.k;
        if (obj instanceof Activity) {
            String strC = null;
            try {
                strC = b.h.d.f.c((Activity) obj);
            } catch (IllegalArgumentException unused) {
            }
            if (strC != null) {
                b.b.k.a aVarC0 = C0();
                if (aVarC0 == null) {
                    this.d0 = true;
                } else {
                    aVarC0.r(true);
                }
            }
            b.b.k.e.c(this);
        }
        this.R = true;
    }

    public int r0(Context context, int i2) {
        if (i2 == -100) {
            return -1;
        }
        if (i2 != -1) {
            if (i2 == 0) {
                if (Build.VERSION.SDK_INT < 23 || ((UiModeManager) context.getApplicationContext().getSystemService(UiModeManager.class)).getNightMode() != 0) {
                    return g0(context).c();
                }
                return -1;
            }
            if (i2 != 1 && i2 != 2) {
                if (i2 == 3) {
                    return f0(context).c();
                }
                throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
            }
        }
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0048  */
    @Override // b.b.k.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void s() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.k
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L9
            b.b.k.e.y(r3)
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
            b.e.g<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.f290e
            java.lang.Object r1 = r3.k
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.U
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L57
        L48:
            b.e.g<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.f290e
            java.lang.Object r1 = r3.k
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L57:
            b.b.k.a r0 = r3.p
            if (r0 == 0) goto L5e
            r0.n()
        L5e:
            r3.Q()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.s():void");
    }

    public boolean s0() {
        b.b.p.b bVar = this.v;
        if (bVar != null) {
            bVar.c();
            return true;
        }
        b.b.k.a aVarN = n();
        return aVarN != null && aVarN.h();
    }

    @Override // b.b.k.e
    public void t(Bundle bundle) {
        a0();
    }

    public boolean t0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            this.P = (keyEvent.getFlags() & 128) != 0;
        } else if (i2 == 82) {
            u0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // b.b.k.e
    public void u() {
        b.b.k.a aVarN = n();
        if (aVarN != null) {
            aVarN.s(true);
        }
    }

    public final boolean u0(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        PanelFeatureState panelFeatureStateH0 = h0(i2, true);
        if (panelFeatureStateH0.o) {
            return false;
        }
        return E0(panelFeatureStateH0, keyEvent);
    }

    @Override // b.b.k.e
    public void v(Bundle bundle) {
    }

    public boolean v0(int i2, KeyEvent keyEvent) {
        b.b.k.a aVarN = n();
        if (aVarN != null && aVarN.o(i2, keyEvent)) {
            return true;
        }
        PanelFeatureState panelFeatureState = this.O;
        if (panelFeatureState != null && D0(panelFeatureState, keyEvent.getKeyCode(), keyEvent, 1)) {
            PanelFeatureState panelFeatureState2 = this.O;
            if (panelFeatureState2 != null) {
                panelFeatureState2.n = true;
            }
            return true;
        }
        if (this.O == null) {
            PanelFeatureState panelFeatureStateH0 = h0(0, true);
            E0(panelFeatureStateH0, keyEvent);
            boolean zD0 = D0(panelFeatureStateH0, keyEvent.getKeyCode(), keyEvent, 1);
            panelFeatureStateH0.m = false;
            if (zD0) {
                return true;
            }
        }
        return false;
    }

    @Override // b.b.k.e
    public void w() {
        this.S = true;
        J();
    }

    public boolean w0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            boolean z = this.P;
            this.P = false;
            PanelFeatureState panelFeatureStateH0 = h0(0, false);
            if (panelFeatureStateH0 != null && panelFeatureStateH0.o) {
                if (!z) {
                    S(panelFeatureStateH0, true);
                }
                return true;
            }
            if (s0()) {
                return true;
            }
        } else if (i2 == 82) {
            x0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // b.b.k.e
    public void x() {
        this.S = false;
        b.b.k.a aVarN = n();
        if (aVarN != null) {
            aVarN.s(false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean x0(int r5, android.view.KeyEvent r6) {
        /*
            r4 = this;
            b.b.p.b r0 = r4.v
            r1 = 0
            if (r0 == 0) goto L6
            return r1
        L6:
            r0 = 1
            androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r2 = r4.h0(r5, r0)
            if (r5 != 0) goto L43
            b.b.q.o r5 = r4.s
            if (r5 == 0) goto L43
            boolean r5 = r5.g()
            if (r5 == 0) goto L43
            android.content.Context r5 = r4.l
            android.view.ViewConfiguration r5 = android.view.ViewConfiguration.get(r5)
            boolean r5 = r5.hasPermanentMenuKey()
            if (r5 != 0) goto L43
            b.b.q.o r5 = r4.s
            boolean r5 = r5.c()
            if (r5 != 0) goto L3c
            boolean r5 = r4.T
            if (r5 != 0) goto L62
            boolean r5 = r4.E0(r2, r6)
            if (r5 == 0) goto L62
            b.b.q.o r5 = r4.s
            boolean r0 = r5.e()
            goto L68
        L3c:
            b.b.q.o r5 = r4.s
            boolean r0 = r5.d()
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
            boolean r5 = r4.E0(r2, r6)
            goto L5c
        L5b:
            r5 = r0
        L5c:
            if (r5 == 0) goto L62
            r4.B0(r2, r6)
            goto L68
        L62:
            r0 = r1
            goto L68
        L64:
            r4.S(r2, r0)
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.x0(int, android.view.KeyEvent):boolean");
    }

    public void y0(int i2) {
        b.b.k.a aVarN;
        if (i2 != 108 || (aVarN = n()) == null) {
            return;
        }
        aVarN.i(true);
    }

    public void z0(int i2) {
        if (i2 == 108) {
            b.b.k.a aVarN = n();
            if (aVarN != null) {
                aVarN.i(false);
                return;
            }
            return;
        }
        if (i2 == 0) {
            PanelFeatureState panelFeatureStateH0 = h0(i2, true);
            if (panelFeatureStateH0.o) {
                S(panelFeatureStateH0, false);
            }
        }
    }

    public AppCompatDelegateImpl(Dialog dialog, b.b.k.d dVar) {
        this(dialog.getContext(), dialog.getWindow(), dVar, dialog);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    public AppCompatDelegateImpl(Context context, Window window, b.b.k.d dVar, Object obj) {
        b.e.g<String, Integer> gVar;
        Integer num;
        AppCompatActivity appCompatActivityL0;
        this.z = null;
        this.A = true;
        this.U = -100;
        this.c0 = new b();
        this.l = context;
        this.o = dVar;
        this.k = obj;
        if (this.U == -100 && (obj instanceof Dialog) && (appCompatActivityL0 = L0()) != null) {
            this.U = appCompatActivityL0.getDelegate().l();
        }
        if (this.U == -100 && (num = (gVar = f290e).get(obj.getClass().getName())) != null) {
            this.U = num.intValue();
            gVar.remove(obj.getClass().getName());
        }
        if (window != null) {
            M(window);
        }
        b.b.q.f.h();
    }

    public class k extends b.b.p.i {
        public k(Window.Callback callback) {
            super(callback);
        }

        public final ActionMode b(ActionMode.Callback callback) {
            f.a aVar = new f.a(AppCompatDelegateImpl.this.l, callback);
            b.b.p.b bVarI = AppCompatDelegateImpl.this.I(aVar);
            if (bVarI != null) {
                return aVar.e(bVarI);
            }
            return null;
        }

        @Override // b.b.p.i, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.X(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // b.b.p.i, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.v0(keyEvent.getKeyCode(), keyEvent);
        }

        @Override // b.b.p.i, android.view.Window.Callback
        public void onContentChanged() {
        }

        @Override // b.b.p.i, android.view.Window.Callback
        public boolean onCreatePanelMenu(int i2, Menu menu) {
            if (i2 != 0 || (menu instanceof b.b.p.j.g)) {
                return super.onCreatePanelMenu(i2, menu);
            }
            return false;
        }

        @Override // b.b.p.i, android.view.Window.Callback
        public boolean onMenuOpened(int i2, Menu menu) {
            super.onMenuOpened(i2, menu);
            AppCompatDelegateImpl.this.y0(i2);
            return true;
        }

        @Override // b.b.p.i, android.view.Window.Callback
        public void onPanelClosed(int i2, Menu menu) {
            super.onPanelClosed(i2, menu);
            AppCompatDelegateImpl.this.z0(i2);
        }

        @Override // b.b.p.i, android.view.Window.Callback
        public boolean onPreparePanel(int i2, View view, Menu menu) {
            b.b.p.j.g gVar = menu instanceof b.b.p.j.g ? (b.b.p.j.g) menu : null;
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

        @Override // b.b.p.i, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i2) {
            b.b.p.j.g gVar;
            PanelFeatureState panelFeatureStateH0 = AppCompatDelegateImpl.this.h0(0, true);
            if (panelFeatureStateH0 == null || (gVar = panelFeatureStateH0.j) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i2);
            } else {
                super.onProvideKeyboardShortcuts(list, gVar, i2);
            }
        }

        @Override // b.b.p.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            return AppCompatDelegateImpl.this.q0() ? b(callback) : super.onWindowStartingActionMode(callback);
        }

        @Override // b.b.p.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i2) {
            if (AppCompatDelegateImpl.this.q0() && i2 == 0) {
                return b(callback);
            }
            return super.onWindowStartingActionMode(callback, i2);
        }
    }
}
