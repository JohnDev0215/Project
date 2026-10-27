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
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$style;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.lifecycle.Lifecycle;
import defpackage.b1;
import defpackage.ba;
import defpackage.d1;
import defpackage.e1;
import defpackage.e7;
import defpackage.e9;
import defpackage.ec;
import defpackage.f1;
import defpackage.f9;
import defpackage.g1;
import defpackage.g3;
import defpackage.g4;
import defpackage.h2;
import defpackage.i0;
import defpackage.i1;
import defpackage.j0;
import defpackage.l0;
import defpackage.l3;
import defpackage.l7;
import defpackage.m0;
import defpackage.m3;
import defpackage.n0;
import defpackage.n1;
import defpackage.o0;
import defpackage.p0;
import defpackage.p1;
import defpackage.p9;
import defpackage.pa;
import defpackage.q0;
import defpackage.q2;
import defpackage.s0;
import defpackage.t0;
import defpackage.t9;
import defpackage.u0;
import defpackage.u2;
import defpackage.u8;
import defpackage.v1;
import defpackage.w1;
import defpackage.x6;
import defpackage.x9;
import defpackage.z9;
import java.lang.Thread;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatDelegateImpl extends m0 implements p1.a, LayoutInflater.Factory2 {
    public static final g4<String, Integer> e = new g4<>();
    public static final boolean f;
    public static final int[] g;
    public static final boolean h;
    public static final boolean i;
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
    public o0 g0;
    public final Object k;
    public final Context l;
    public Window m;
    public k n;
    public final l0 o;
    public i0 p;
    public MenuInflater q;
    public CharSequence r;
    public q2 s;
    public i t;
    public t u;
    public b1 v;
    public ActionBarContextView w;
    public PopupWindow x;
    public Runnable y;
    public x9 z;

    public static final class PanelFeatureState {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public ViewGroup g;
        public View h;
        public View i;
        public p1 j;
        public n1 k;
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
            public int b;
            public boolean c;
            public Bundle d;

            public class a implements Parcelable.ClassLoaderCreator<SavedState> {
                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public SavedState createFromParcel(Parcel parcel) {
                    return SavedState.f(parcel, null);
                }

                @Override // android.os.Parcelable.ClassLoaderCreator
                /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
                public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.f(parcel, classLoader);
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
                public SavedState[] newArray(int i) {
                    return new SavedState[i];
                }
            }

            public static SavedState f(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.b = parcel.readInt();
                boolean z = parcel.readInt() == 1;
                savedState.c = z;
                if (z) {
                    savedState.d = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.b);
                parcel.writeInt(this.c ? 1 : 0);
                if (this.c) {
                    parcel.writeBundle(this.d);
                }
            }
        }

        public PanelFeatureState(int i) {
            this.a = i;
        }

        public w1 a(v1.a aVar) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                n1 n1Var = new n1(this.l, R$layout.abc_list_menu_item_layout);
                this.k = n1Var;
                n1Var.k(aVar);
                this.j.b(this.k);
            }
            return this.k.g(this.g);
        }

        public boolean b() {
            if (this.h == null) {
                return false;
            }
            return this.i != null || this.k.b().getCount() > 0;
        }

        public void c(p1 p1Var) {
            n1 n1Var;
            p1 p1Var2 = this.j;
            if (p1Var == p1Var2) {
                return;
            }
            if (p1Var2 != null) {
                p1Var2.Q(this.k);
            }
            this.j = p1Var;
            if (p1Var == null || (n1Var = this.k) == null) {
                return;
            }
            p1Var.b(n1Var);
        }

        public void d(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme themeNewTheme = context.getResources().newTheme();
            themeNewTheme.setTo(context.getTheme());
            themeNewTheme.resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                themeNewTheme.applyStyle(i, true);
            }
            themeNewTheme.resolveAttribute(R$attr.panelMenuListTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                themeNewTheme.applyStyle(i2, true);
            } else {
                themeNewTheme.applyStyle(R$style.Theme_AppCompat_CompactMenu, true);
            }
            d1 d1Var = new d1(context, 0);
            d1Var.getTheme().setTo(themeNewTheme);
            this.l = d1Var;
            TypedArray typedArrayObtainStyledAttributes = d1Var.obtainStyledAttributes(R$styleable.AppCompatTheme);
            this.b = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_panelBackground, 0);
            this.f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public class a implements Thread.UncaughtExceptionHandler {
        public final /* synthetic */ Thread.UncaughtExceptionHandler a;

        public a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.a = uncaughtExceptionHandler;
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
                this.a.uncaughtException(thread, th);
                return;
            }
            Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            notFoundException.initCause(th.getCause());
            notFoundException.setStackTrace(th.getStackTrace());
            this.a.uncaughtException(thread, notFoundException);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl.b0 & 1) != 0) {
                appCompatDelegateImpl.X(0);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl2.b0 & 4096) != 0) {
                appCompatDelegateImpl2.X(108);
            }
            AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl3.a0 = false;
            appCompatDelegateImpl3.b0 = 0;
        }
    }

    public class c implements p9 {
        public c() {
        }

        @Override // defpackage.p9
        public ba a(View view, ba baVar) {
            int iH = baVar.h();
            int iN0 = AppCompatDelegateImpl.this.N0(baVar, null);
            if (iH != iN0) {
                baVar = baVar.n(baVar.f(), iN0, baVar.g(), baVar.e());
            }
            return t9.W(view, baVar);
        }
    }

    public class d implements u2.a {
        public d() {
        }

        @Override // u2.a
        public void a(Rect rect) {
            rect.top = AppCompatDelegateImpl.this.N0(null, rect);
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
            AppCompatDelegateImpl.this.V();
        }
    }

    public class f implements Runnable {

        public class a extends z9 {
            public a() {
            }

            @Override // defpackage.y9
            public void a(View view) {
                AppCompatDelegateImpl.this.w.setAlpha(1.0f);
                AppCompatDelegateImpl.this.z.f(null);
                AppCompatDelegateImpl.this.z = null;
            }

            @Override // defpackage.z9, defpackage.y9
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
            AppCompatDelegateImpl.this.Y();
            if (!AppCompatDelegateImpl.this.G0()) {
                AppCompatDelegateImpl.this.w.setAlpha(1.0f);
                AppCompatDelegateImpl.this.w.setVisibility(0);
            } else {
                AppCompatDelegateImpl.this.w.setAlpha(0.0f);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.z = t9.c(appCompatDelegateImpl2.w).a(1.0f);
                AppCompatDelegateImpl.this.z.f(new a());
            }
        }
    }

    public class g extends z9 {
        public g() {
        }

        @Override // defpackage.y9
        public void a(View view) {
            AppCompatDelegateImpl.this.w.setAlpha(1.0f);
            AppCompatDelegateImpl.this.z.f(null);
            AppCompatDelegateImpl.this.z = null;
        }

        @Override // defpackage.z9, defpackage.y9
        public void b(View view) {
            AppCompatDelegateImpl.this.w.setVisibility(0);
            AppCompatDelegateImpl.this.w.sendAccessibilityEvent(32);
            if (AppCompatDelegateImpl.this.w.getParent() instanceof View) {
                t9.g0((View) AppCompatDelegateImpl.this.w.getParent());
            }
        }
    }

    public class h implements j0 {
        public h() {
        }
    }

    public final class i implements v1.a {
        public i() {
        }

        @Override // v1.a
        public void a(p1 p1Var, boolean z) {
            AppCompatDelegateImpl.this.O(p1Var);
        }

        @Override // v1.a
        public boolean b(p1 p1Var) {
            Window.Callback callbackI0 = AppCompatDelegateImpl.this.i0();
            if (callbackI0 == null) {
                return true;
            }
            callbackI0.onMenuOpened(108, p1Var);
            return true;
        }
    }

    public class j implements b1.a {
        public b1.a a;

        public class a extends z9 {
            public a() {
            }

            @Override // defpackage.y9
            public void a(View view) {
                AppCompatDelegateImpl.this.w.setVisibility(8);
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                PopupWindow popupWindow = appCompatDelegateImpl.x;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (appCompatDelegateImpl.w.getParent() instanceof View) {
                    t9.g0((View) AppCompatDelegateImpl.this.w.getParent());
                }
                AppCompatDelegateImpl.this.w.removeAllViews();
                AppCompatDelegateImpl.this.z.f(null);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.z = null;
                t9.g0(appCompatDelegateImpl2.C);
            }
        }

        public j(b1.a aVar) {
            this.a = aVar;
        }

        @Override // b1.a
        public boolean a(b1 b1Var, Menu menu) {
            t9.g0(AppCompatDelegateImpl.this.C);
            return this.a.a(b1Var, menu);
        }

        @Override // b1.a
        public boolean b(b1 b1Var, MenuItem menuItem) {
            return this.a.b(b1Var, menuItem);
        }

        @Override // b1.a
        public boolean c(b1 b1Var, Menu menu) {
            return this.a.c(b1Var, menu);
        }

        @Override // b1.a
        public void d(b1 b1Var) {
            this.a.d(b1Var);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.x != null) {
                appCompatDelegateImpl.m.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.y);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.w != null) {
                appCompatDelegateImpl2.Y();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl3.z = t9.c(appCompatDelegateImpl3.w).a(0.0f);
                AppCompatDelegateImpl.this.z.f(new a());
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            l0 l0Var = appCompatDelegateImpl4.o;
            if (l0Var != null) {
                l0Var.onSupportActionModeFinished(appCompatDelegateImpl4.v);
            }
            AppCompatDelegateImpl appCompatDelegateImpl5 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl5.v = null;
            t9.g0(appCompatDelegateImpl5.C);
        }
    }

    public class l extends m {
        public final PowerManager c;

        public l(Context context) {
            super();
            this.c = (PowerManager) context.getApplicationContext().getSystemService("power");
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
            return (Build.VERSION.SDK_INT < 21 || !this.c.isPowerSaveMode()) ? 1 : 2;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public void d() {
            AppCompatDelegateImpl.this.I();
        }
    }

    public abstract class m {
        public BroadcastReceiver a;

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
            BroadcastReceiver broadcastReceiver = this.a;
            if (broadcastReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.l.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.a = null;
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
            if (this.a == null) {
                this.a = new a();
            }
            AppCompatDelegateImpl.this.l.registerReceiver(this.a, intentFilterB);
        }
    }

    public class n extends m {
        public final s0 c;

        public n(s0 s0Var) {
            super();
            this.c = s0Var;
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
            return this.c.d() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public void d() {
            AppCompatDelegateImpl.this.I();
        }
    }

    public static class o {
        public static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i = configuration.densityDpi;
            int i2 = configuration2.densityDpi;
            if (i != i2) {
                configuration3.densityDpi = i2;
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
            int i = configuration.colorMode & 3;
            int i2 = configuration2.colorMode;
            if (i != (i2 & 3)) {
                configuration3.colorMode |= i2 & 3;
            }
            int i3 = configuration.colorMode & 12;
            int i4 = configuration2.colorMode;
            if (i3 != (i4 & 12)) {
                configuration3.colorMode |= i4 & 12;
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

        public final boolean c(int i, int i2) {
            return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.W(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !c((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            AppCompatDelegateImpl.this.Q(0);
            return true;
        }

        @Override // android.view.View
        public void setBackgroundResource(int i) {
            setBackgroundDrawable(u0.d(getContext(), i));
        }
    }

    public final class t implements v1.a {
        public t() {
        }

        @Override // v1.a
        public void a(p1 p1Var, boolean z) {
            p1 p1VarF = p1Var.F();
            boolean z2 = p1VarF != p1Var;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z2) {
                p1Var = p1VarF;
            }
            PanelFeatureState panelFeatureStateB0 = appCompatDelegateImpl.b0(p1Var);
            if (panelFeatureStateB0 != null) {
                if (!z2) {
                    AppCompatDelegateImpl.this.R(panelFeatureStateB0, z);
                } else {
                    AppCompatDelegateImpl.this.N(panelFeatureStateB0.a, panelFeatureStateB0, p1VarF);
                    AppCompatDelegateImpl.this.R(panelFeatureStateB0, true);
                }
            }
        }

        @Override // v1.a
        public boolean b(p1 p1Var) {
            Window.Callback callbackI0;
            if (p1Var != p1Var.F()) {
                return true;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (!appCompatDelegateImpl.H || (callbackI0 = appCompatDelegateImpl.i0()) == null || AppCompatDelegateImpl.this.T) {
                return true;
            }
            callbackI0.onMenuOpened(108, p1Var);
            return true;
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        boolean z = i2 < 21;
        f = z;
        g = new int[]{R.attr.windowBackground};
        h = !"robolectric".equals(Build.FINGERPRINT);
        i = i2 >= 17;
        if (!z || j) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new a(Thread.getDefaultUncaughtExceptionHandler()));
        j = true;
    }

    public AppCompatDelegateImpl(Activity activity, l0 l0Var) {
        this(activity, null, l0Var, activity);
    }

    public static Configuration c0(Configuration configuration, Configuration configuration2) {
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
            } else if (!u8.a(configuration.locale, configuration2.locale)) {
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

    @Override // defpackage.m0
    public boolean A(int i2) {
        int iF0 = F0(i2);
        if (this.L && iF0 == 108) {
            return false;
        }
        if (this.H && iF0 == 1) {
            this.H = false;
        }
        if (iF0 == 1) {
            J0();
            this.L = true;
            return true;
        }
        if (iF0 == 2) {
            J0();
            this.F = true;
            return true;
        }
        if (iF0 == 5) {
            J0();
            this.G = true;
            return true;
        }
        if (iF0 == 10) {
            J0();
            this.J = true;
            return true;
        }
        if (iF0 == 108) {
            J0();
            this.H = true;
            return true;
        }
        if (iF0 != 109) {
            return this.m.requestFeature(iF0);
        }
        J0();
        this.I = true;
        return true;
    }

    public final void A0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        int i2;
        ViewGroup.LayoutParams layoutParams;
        if (panelFeatureState.o || this.T) {
            return;
        }
        if (panelFeatureState.a == 0) {
            if ((this.l.getResources().getConfiguration().screenLayout & 15) == 4) {
                return;
            }
        }
        Window.Callback callbackI0 = i0();
        if (callbackI0 != null && !callbackI0.onMenuOpened(panelFeatureState.a, panelFeatureState.j)) {
            R(panelFeatureState, true);
            return;
        }
        WindowManager windowManager = (WindowManager) this.l.getSystemService("window");
        if (windowManager != null && D0(panelFeatureState, keyEvent)) {
            ViewGroup viewGroup = panelFeatureState.g;
            if (viewGroup != null && !panelFeatureState.q) {
                View view = panelFeatureState.i;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i2 = -1;
                }
                panelFeatureState.n = false;
                WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i2, -2, panelFeatureState.d, panelFeatureState.e, 1002, 8519680, -3);
                layoutParams2.gravity = panelFeatureState.c;
                layoutParams2.windowAnimations = panelFeatureState.f;
                windowManager.addView(panelFeatureState.g, layoutParams2);
                panelFeatureState.o = true;
            }
            if (viewGroup == null) {
                if (!l0(panelFeatureState) || panelFeatureState.g == null) {
                    return;
                }
            } else if (panelFeatureState.q && viewGroup.getChildCount() > 0) {
                panelFeatureState.g.removeAllViews();
            }
            if (!k0(panelFeatureState) || !panelFeatureState.b()) {
                panelFeatureState.q = true;
                return;
            }
            ViewGroup.LayoutParams layoutParams3 = panelFeatureState.h.getLayoutParams();
            if (layoutParams3 == null) {
                layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
            }
            panelFeatureState.g.setBackgroundResource(panelFeatureState.b);
            ViewParent parent = panelFeatureState.h.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(panelFeatureState.h);
            }
            panelFeatureState.g.addView(panelFeatureState.h, layoutParams3);
            if (!panelFeatureState.h.hasFocus()) {
                panelFeatureState.h.requestFocus();
            }
            i2 = -2;
            panelFeatureState.n = false;
            WindowManager.LayoutParams layoutParams22 = new WindowManager.LayoutParams(i2, -2, panelFeatureState.d, panelFeatureState.e, 1002, 8519680, -3);
            layoutParams22.gravity = panelFeatureState.c;
            layoutParams22.windowAnimations = panelFeatureState.f;
            windowManager.addView(panelFeatureState.g, layoutParams22);
            panelFeatureState.o = true;
        }
    }

    @Override // defpackage.m0
    public void B(int i2) {
        Z();
        ViewGroup viewGroup = (ViewGroup) this.C.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.l).inflate(i2, viewGroup);
        this.n.a().onContentChanged();
    }

    public final i0 B0() {
        return this.p;
    }

    @Override // defpackage.m0
    public void C(View view) {
        Z();
        ViewGroup viewGroup = (ViewGroup) this.C.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.n.a().onContentChanged();
    }

    public final boolean C0(PanelFeatureState panelFeatureState, int i2, KeyEvent keyEvent, int i3) {
        p1 p1Var;
        boolean zPerformShortcut = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.m || D0(panelFeatureState, keyEvent)) && (p1Var = panelFeatureState.j) != null) {
            zPerformShortcut = p1Var.performShortcut(i2, keyEvent, i3);
        }
        if (zPerformShortcut && (i3 & 1) == 0 && this.s == null) {
            R(panelFeatureState, true);
        }
        return zPerformShortcut;
    }

    @Override // defpackage.m0
    public void D(View view, ViewGroup.LayoutParams layoutParams) {
        Z();
        ViewGroup viewGroup = (ViewGroup) this.C.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.n.a().onContentChanged();
    }

    public final boolean D0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        q2 q2Var;
        q2 q2Var2;
        q2 q2Var3;
        if (this.T) {
            return false;
        }
        if (panelFeatureState.m) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.O;
        if (panelFeatureState2 != null && panelFeatureState2 != panelFeatureState) {
            R(panelFeatureState2, false);
        }
        Window.Callback callbackI0 = i0();
        if (callbackI0 != null) {
            panelFeatureState.i = callbackI0.onCreatePanelView(panelFeatureState.a);
        }
        int i2 = panelFeatureState.a;
        boolean z = i2 == 0 || i2 == 108;
        if (z && (q2Var3 = this.s) != null) {
            q2Var3.f();
        }
        if (panelFeatureState.i == null && (!z || !(B0() instanceof q0))) {
            p1 p1Var = panelFeatureState.j;
            if (p1Var == null || panelFeatureState.r) {
                if (p1Var == null && (!m0(panelFeatureState) || panelFeatureState.j == null)) {
                    return false;
                }
                if (z && this.s != null) {
                    if (this.t == null) {
                        this.t = new i();
                    }
                    this.s.a(panelFeatureState.j, this.t);
                }
                panelFeatureState.j.h0();
                if (!callbackI0.onCreatePanelMenu(panelFeatureState.a, panelFeatureState.j)) {
                    panelFeatureState.c(null);
                    if (z && (q2Var = this.s) != null) {
                        q2Var.a(null, this.t);
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
            if (!callbackI0.onPreparePanel(0, panelFeatureState.i, panelFeatureState.j)) {
                if (z && (q2Var2 = this.s) != null) {
                    q2Var2.a(null, this.t);
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

    @Override // defpackage.m0
    public void E(Toolbar toolbar) {
        if (this.k instanceof Activity) {
            i0 i0VarN = n();
            if (i0VarN instanceof t0) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            this.q = null;
            if (i0VarN != null) {
                i0VarN.n();
            }
            if (toolbar != null) {
                q0 q0Var = new q0(toolbar, h0(), this.n);
                this.p = q0Var;
                this.m.setCallback(q0Var.w());
            } else {
                this.p = null;
                this.m.setCallback(this.n);
            }
            p();
        }
    }

    public final void E0(boolean z) {
        q2 q2Var = this.s;
        if (q2Var == null || !q2Var.g() || (ViewConfiguration.get(this.l).hasPermanentMenuKey() && !this.s.b())) {
            PanelFeatureState panelFeatureStateG0 = g0(0, true);
            panelFeatureStateG0.q = true;
            R(panelFeatureStateG0, false);
            A0(panelFeatureStateG0, null);
            return;
        }
        Window.Callback callbackI0 = i0();
        if (this.s.c() && z) {
            this.s.d();
            if (this.T) {
                return;
            }
            callbackI0.onPanelClosed(108, g0(0, true).j);
            return;
        }
        if (callbackI0 == null || this.T) {
            return;
        }
        if (this.a0 && (this.b0 & 1) != 0) {
            this.m.getDecorView().removeCallbacks(this.c0);
            this.c0.run();
        }
        PanelFeatureState panelFeatureStateG02 = g0(0, true);
        p1 p1Var = panelFeatureStateG02.j;
        if (p1Var == null || panelFeatureStateG02.r || !callbackI0.onPreparePanel(0, panelFeatureStateG02.i, p1Var)) {
            return;
        }
        callbackI0.onMenuOpened(108, panelFeatureStateG02.j);
        this.s.e();
    }

    @Override // defpackage.m0
    public void F(int i2) {
        this.V = i2;
    }

    public final int F0(int i2) {
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

    @Override // defpackage.m0
    public final void G(CharSequence charSequence) {
        this.r = charSequence;
        q2 q2Var = this.s;
        if (q2Var != null) {
            q2Var.setWindowTitle(charSequence);
            return;
        }
        if (B0() != null) {
            B0().t(charSequence);
            return;
        }
        TextView textView = this.D;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public final boolean G0() {
        ViewGroup viewGroup;
        return this.B && (viewGroup = this.C) != null && t9.P(viewGroup);
    }

    @Override // defpackage.m0
    public b1 H(b1.a aVar) {
        l0 l0Var;
        if (aVar == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        b1 b1Var = this.v;
        if (b1Var != null) {
            b1Var.c();
        }
        j jVar = new j(aVar);
        i0 i0VarN = n();
        if (i0VarN != null) {
            b1 b1VarU = i0VarN.u(jVar);
            this.v = b1VarU;
            if (b1VarU != null && (l0Var = this.o) != null) {
                l0Var.onSupportActionModeStarted(b1VarU);
            }
        }
        if (this.v == null) {
            this.v = I0(jVar);
        }
        return this.v;
    }

    public final boolean H0(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.m.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || t9.O((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    public boolean I() {
        return J(true);
    }

    public b1 I0(b1.a aVar) {
        b1 b1VarOnWindowStartingSupportActionMode;
        Context d1Var;
        l0 l0Var;
        Y();
        b1 b1Var = this.v;
        if (b1Var != null) {
            b1Var.c();
        }
        if (!(aVar instanceof j)) {
            aVar = new j(aVar);
        }
        l0 l0Var2 = this.o;
        if (l0Var2 == null || this.T) {
            b1VarOnWindowStartingSupportActionMode = null;
        } else {
            try {
                b1VarOnWindowStartingSupportActionMode = l0Var2.onWindowStartingSupportActionMode(aVar);
            } catch (AbstractMethodError unused) {
                b1VarOnWindowStartingSupportActionMode = null;
            }
        }
        if (b1VarOnWindowStartingSupportActionMode != null) {
            this.v = b1VarOnWindowStartingSupportActionMode;
        } else {
            if (this.w == null) {
                if (this.K) {
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = this.l.getTheme();
                    theme.resolveAttribute(R$attr.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Resources.Theme themeNewTheme = this.l.getResources().newTheme();
                        themeNewTheme.setTo(theme);
                        themeNewTheme.applyStyle(typedValue.resourceId, true);
                        d1Var = new d1(this.l, 0);
                        d1Var.getTheme().setTo(themeNewTheme);
                    } else {
                        d1Var = this.l;
                    }
                    this.w = new ActionBarContextView(d1Var);
                    PopupWindow popupWindow = new PopupWindow(d1Var, (AttributeSet) null, R$attr.actionModePopupWindowStyle);
                    this.x = popupWindow;
                    pa.b(popupWindow, 2);
                    this.x.setContentView(this.w);
                    this.x.setWidth(-1);
                    d1Var.getTheme().resolveAttribute(R$attr.actionBarSize, typedValue, true);
                    this.w.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, d1Var.getResources().getDisplayMetrics()));
                    this.x.setHeight(-2);
                    this.y = new f();
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.C.findViewById(R$id.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(d0()));
                        this.w = (ActionBarContextView) viewStubCompat.a();
                    }
                }
            }
            if (this.w != null) {
                Y();
                this.w.k();
                e1 e1Var = new e1(this.w.getContext(), this.w, aVar, this.x == null);
                if (aVar.c(e1Var, e1Var.e())) {
                    e1Var.k();
                    this.w.h(e1Var);
                    this.v = e1Var;
                    if (G0()) {
                        this.w.setAlpha(0.0f);
                        x9 x9VarA = t9.c(this.w).a(1.0f);
                        this.z = x9VarA;
                        x9VarA.f(new g());
                    } else {
                        this.w.setAlpha(1.0f);
                        this.w.setVisibility(0);
                        this.w.sendAccessibilityEvent(32);
                        if (this.w.getParent() instanceof View) {
                            t9.g0((View) this.w.getParent());
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
        b1 b1Var2 = this.v;
        if (b1Var2 != null && (l0Var = this.o) != null) {
            l0Var.onSupportActionModeStarted(b1Var2);
        }
        return this.v;
    }

    public final boolean J(boolean z) {
        if (this.T) {
            return false;
        }
        int iM = M();
        boolean zL0 = L0(q0(this.l, iM), z);
        if (iM == 0) {
            f0(this.l).e();
        } else {
            m mVar = this.Y;
            if (mVar != null) {
                mVar.a();
            }
        }
        if (iM == 3) {
            e0(this.l).e();
        } else {
            m mVar2 = this.Z;
            if (mVar2 != null) {
                mVar2.a();
            }
        }
        return zL0;
    }

    public final void J0() {
        if (this.B) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public final void K() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.C.findViewById(R.id.content);
        View decorView = this.m.getDecorView();
        contentFrameLayout.b(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray typedArrayObtainStyledAttributes = this.l.obtainStyledAttributes(R$styleable.AppCompatTheme);
        typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        int i2 = R$styleable.AppCompatTheme_windowFixedWidthMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            typedArrayObtainStyledAttributes.getValue(i2, contentFrameLayout.getFixedWidthMajor());
        }
        int i3 = R$styleable.AppCompatTheme_windowFixedWidthMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            typedArrayObtainStyledAttributes.getValue(i3, contentFrameLayout.getFixedWidthMinor());
        }
        int i4 = R$styleable.AppCompatTheme_windowFixedHeightMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            typedArrayObtainStyledAttributes.getValue(i4, contentFrameLayout.getFixedHeightMajor());
        }
        int i5 = R$styleable.AppCompatTheme_windowFixedHeightMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i5)) {
            typedArrayObtainStyledAttributes.getValue(i5, contentFrameLayout.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    public final AppCompatActivity K0() {
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

    public final void L(Window window) {
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
        g3 g3VarU = g3.u(this.l, null, g);
        Drawable drawableH = g3VarU.h(0);
        if (drawableH != null) {
            window.setBackgroundDrawable(drawableH);
        }
        g3VarU.w();
        this.m = window;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean L0(int r7, boolean r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.l
            r1 = 0
            android.content.res.Configuration r0 = r6.S(r0, r7, r1)
            boolean r2 = r6.o0()
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
            boolean r8 = androidx.appcompat.app.AppCompatDelegateImpl.h
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
            androidx.core.app.ActivityCompat.l(r8)
            r8 = r4
            goto L48
        L47:
            r8 = 0
        L48:
            if (r8 != 0) goto L50
            if (r3 == r0) goto L50
            r6.M0(r0, r2, r1)
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.L0(int, boolean):boolean");
    }

    public final int M() {
        int i2 = this.U;
        return i2 != -100 ? i2 : m0.j();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void M0(int i2, boolean z, Configuration configuration) {
        Resources resources = this.l.getResources();
        Configuration configuration2 = new Configuration(resources.getConfiguration());
        if (configuration != null) {
            configuration2.updateFrom(configuration);
        }
        configuration2.uiMode = i2 | (resources.getConfiguration().uiMode & (-49));
        resources.updateConfiguration(configuration2, null);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 26) {
            p0.a(resources);
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
                if (activity instanceof ec) {
                    if (((ec) activity).getLifecycle().b().isAtLeast(Lifecycle.State.STARTED)) {
                        activity.onConfigurationChanged(configuration2);
                    }
                } else if (this.S) {
                    activity.onConfigurationChanged(configuration2);
                }
            }
        }
    }

    public void N(int i2, PanelFeatureState panelFeatureState, Menu menu) {
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

    public final int N0(ba baVar, Rect rect) {
        boolean z;
        boolean z2;
        int iH = baVar != null ? baVar.h() : rect != null ? rect.top : 0;
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
                if (baVar == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(baVar.f(), baVar.h(), baVar.g(), baVar.e());
                }
                m3.a(this.C, rect2, rect3);
                int i2 = rect2.top;
                int i3 = rect2.left;
                int i4 = rect2.right;
                ba baVarG = t9.G(this.C);
                int iF = baVarG == null ? 0 : baVarG.f();
                int iG = baVarG == null ? 0 : baVarG.g();
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
                    O0(this.E);
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

    public void O(p1 p1Var) {
        if (this.M) {
            return;
        }
        this.M = true;
        this.s.l();
        Window.Callback callbackI0 = i0();
        if (callbackI0 != null && !this.T) {
            callbackI0.onPanelClosed(108, p1Var);
        }
        this.M = false;
    }

    public final void O0(View view) {
        view.setBackgroundColor((t9.I(view) & 8192) != 0 ? e7.b(this.l, R$color.abc_decor_view_status_guard_light) : e7.b(this.l, R$color.abc_decor_view_status_guard));
    }

    public final void P() {
        m mVar = this.Y;
        if (mVar != null) {
            mVar.a();
        }
        m mVar2 = this.Z;
        if (mVar2 != null) {
            mVar2.a();
        }
    }

    public void Q(int i2) {
        R(g0(i2, true), true);
    }

    public void R(PanelFeatureState panelFeatureState, boolean z) {
        ViewGroup viewGroup;
        q2 q2Var;
        if (z && panelFeatureState.a == 0 && (q2Var = this.s) != null && q2Var.c()) {
            O(panelFeatureState.j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.l.getSystemService("window");
        if (windowManager != null && panelFeatureState.o && (viewGroup = panelFeatureState.g) != null) {
            windowManager.removeView(viewGroup);
            if (z) {
                N(panelFeatureState.a, panelFeatureState, null);
            }
        }
        panelFeatureState.m = false;
        panelFeatureState.n = false;
        panelFeatureState.o = false;
        panelFeatureState.h = null;
        panelFeatureState.q = true;
        if (this.O == panelFeatureState) {
            this.O = null;
        }
    }

    public final Configuration S(Context context, int i2, Configuration configuration) {
        int i3 = i2 != 1 ? i2 != 2 ? context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i3 | (configuration2.uiMode & (-49));
        return configuration2;
    }

    public final ViewGroup T() {
        ViewGroup viewGroup;
        TypedArray typedArrayObtainStyledAttributes = this.l.obtainStyledAttributes(R$styleable.AppCompatTheme);
        int i2 = R$styleable.AppCompatTheme_windowActionBar;
        if (!typedArrayObtainStyledAttributes.hasValue(i2)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowNoTitle, false)) {
            A(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(i2, false)) {
            A(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            A(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            A(10);
        }
        this.K = typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_android_windowIsFloating, false);
        typedArrayObtainStyledAttributes.recycle();
        a0();
        this.m.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.l);
        if (this.L) {
            viewGroup = this.J ? (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple, (ViewGroup) null);
        } else if (this.K) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_dialog_title_material, (ViewGroup) null);
            this.I = false;
            this.H = false;
        } else if (this.H) {
            TypedValue typedValue = new TypedValue();
            this.l.getTheme().resolveAttribute(R$attr.actionBarTheme, typedValue, true);
            int i3 = typedValue.resourceId;
            viewGroup = (ViewGroup) LayoutInflater.from(i3 != 0 ? new d1(this.l, i3) : this.l).inflate(R$layout.abc_screen_toolbar, (ViewGroup) null);
            q2 q2Var = (q2) viewGroup.findViewById(R$id.decor_content_parent);
            this.s = q2Var;
            q2Var.setWindowCallback(i0());
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
            t9.v0(viewGroup, new c());
        } else if (viewGroup instanceof u2) {
            ((u2) viewGroup).setOnFitSystemWindowsListener(new d());
        }
        if (this.s == null) {
            this.D = (TextView) viewGroup.findViewById(R$id.title);
        }
        m3.c(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(R$id.action_bar_activity_content);
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
    public View U(View view, String str, Context context, AttributeSet attributeSet) {
        boolean zH0 = false;
        if (this.g0 == null) {
            String string = this.l.obtainStyledAttributes(R$styleable.AppCompatTheme).getString(R$styleable.AppCompatTheme_viewInflaterClass);
            if (string == null) {
                this.g0 = new o0();
            } else {
                try {
                    this.g0 = (o0) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.g0 = new o0();
                }
            }
        }
        boolean z = f;
        if (z) {
            if (!(attributeSet instanceof XmlPullParser)) {
                zH0 = H0((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                zH0 = true;
            }
        }
        return this.g0.q(view, str, context, attributeSet, zH0, z, true, l3.b());
    }

    public void V() {
        p1 p1Var;
        q2 q2Var = this.s;
        if (q2Var != null) {
            q2Var.l();
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
        Y();
        PanelFeatureState panelFeatureStateG0 = g0(0, false);
        if (panelFeatureStateG0 == null || (p1Var = panelFeatureStateG0.j) == null) {
            return;
        }
        p1Var.close();
    }

    public boolean W(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.k;
        if (((obj instanceof e9.a) || (obj instanceof n0)) && (decorView = this.m.getDecorView()) != null && e9.d(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.n.a().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? s0(keyCode, keyEvent) : v0(keyCode, keyEvent);
    }

    public void X(int i2) {
        PanelFeatureState panelFeatureStateG0;
        PanelFeatureState panelFeatureStateG02 = g0(i2, true);
        if (panelFeatureStateG02.j != null) {
            Bundle bundle = new Bundle();
            panelFeatureStateG02.j.T(bundle);
            if (bundle.size() > 0) {
                panelFeatureStateG02.s = bundle;
            }
            panelFeatureStateG02.j.h0();
            panelFeatureStateG02.j.clear();
        }
        panelFeatureStateG02.r = true;
        panelFeatureStateG02.q = true;
        if ((i2 != 108 && i2 != 0) || this.s == null || (panelFeatureStateG0 = g0(0, false)) == null) {
            return;
        }
        panelFeatureStateG0.m = false;
        D0(panelFeatureStateG0, null);
    }

    public void Y() {
        x9 x9Var = this.z;
        if (x9Var != null) {
            x9Var.b();
        }
    }

    public final void Z() {
        if (this.B) {
            return;
        }
        this.C = T();
        CharSequence charSequenceH0 = h0();
        if (!TextUtils.isEmpty(charSequenceH0)) {
            q2 q2Var = this.s;
            if (q2Var != null) {
                q2Var.setWindowTitle(charSequenceH0);
            } else if (B0() != null) {
                B0().t(charSequenceH0);
            } else {
                TextView textView = this.D;
                if (textView != null) {
                    textView.setText(charSequenceH0);
                }
            }
        }
        K();
        z0(this.C);
        this.B = true;
        PanelFeatureState panelFeatureStateG0 = g0(0, false);
        if (this.T) {
            return;
        }
        if (panelFeatureStateG0 == null || panelFeatureStateG0.j == null) {
            n0(108);
        }
    }

    @Override // p1.a
    public boolean a(p1 p1Var, MenuItem menuItem) {
        PanelFeatureState panelFeatureStateB0;
        Window.Callback callbackI0 = i0();
        if (callbackI0 == null || this.T || (panelFeatureStateB0 = b0(p1Var.F())) == null) {
            return false;
        }
        return callbackI0.onMenuItemSelected(panelFeatureStateB0.a, menuItem);
    }

    public final void a0() {
        if (this.m == null) {
            Object obj = this.k;
            if (obj instanceof Activity) {
                L(((Activity) obj).getWindow());
            }
        }
        if (this.m == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    @Override // p1.a
    public void b(p1 p1Var) {
        E0(true);
    }

    public PanelFeatureState b0(Menu menu) {
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

    @Override // defpackage.m0
    public void d(View view, ViewGroup.LayoutParams layoutParams) {
        Z();
        ((ViewGroup) this.C.findViewById(R.id.content)).addView(view, layoutParams);
        this.n.a().onContentChanged();
    }

    public final Context d0() {
        i0 i0VarN = n();
        Context contextK = i0VarN != null ? i0VarN.k() : null;
        return contextK == null ? this.l : contextK;
    }

    public final m e0(Context context) {
        if (this.Z == null) {
            this.Z = new l(context);
        }
        return this.Z;
    }

    @Override // defpackage.m0
    public Context f(Context context) {
        this.Q = true;
        int iQ0 = q0(context, M());
        if (i && (context instanceof ContextThemeWrapper)) {
            try {
                r.a((ContextThemeWrapper) context, S(context, iQ0, null));
                return context;
            } catch (IllegalStateException unused) {
            }
        }
        if (context instanceof d1) {
            try {
                ((d1) context).a(S(context, iQ0, null));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (!h) {
            return super.f(context);
        }
        try {
            Configuration configuration = context.getPackageManager().getResourcesForApplication(context.getApplicationInfo()).getConfiguration();
            Configuration configuration2 = context.getResources().getConfiguration();
            Configuration configurationS = S(context, iQ0, configuration.equals(configuration2) ? null : c0(configuration, configuration2));
            d1 d1Var = new d1(context, R$style.Theme_AppCompat_Empty);
            d1Var.a(configurationS);
            boolean z = false;
            try {
                z = context.getTheme() != null;
            } catch (NullPointerException unused3) {
            }
            if (z) {
                l7.b.a(d1Var.getTheme());
            }
            return super.f(d1Var);
        } catch (PackageManager.NameNotFoundException e2) {
            throw new RuntimeException("Application failed to obtain resources from itself", e2);
        }
    }

    public final m f0(Context context) {
        if (this.Y == null) {
            this.Y = new n(s0.a(context));
        }
        return this.Y;
    }

    public PanelFeatureState g0(int i2, boolean z) {
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

    public final CharSequence h0() {
        Object obj = this.k;
        return obj instanceof Activity ? ((Activity) obj).getTitle() : this.r;
    }

    @Override // defpackage.m0
    public <T extends View> T i(int i2) {
        Z();
        return (T) this.m.findViewById(i2);
    }

    public final Window.Callback i0() {
        return this.m.getCallback();
    }

    public final void j0() {
        Z();
        if (this.H && this.p == null) {
            Object obj = this.k;
            if (obj instanceof Activity) {
                this.p = new t0((Activity) this.k, this.I);
            } else if (obj instanceof Dialog) {
                this.p = new t0((Dialog) this.k);
            }
            i0 i0Var = this.p;
            if (i0Var != null) {
                i0Var.r(this.d0);
            }
        }
    }

    @Override // defpackage.m0
    public final j0 k() {
        return new h();
    }

    public final boolean k0(PanelFeatureState panelFeatureState) {
        View view = panelFeatureState.i;
        if (view != null) {
            panelFeatureState.h = view;
            return true;
        }
        if (panelFeatureState.j == null) {
            return false;
        }
        if (this.u == null) {
            this.u = new t();
        }
        View view2 = (View) panelFeatureState.a(this.u);
        panelFeatureState.h = view2;
        return view2 != null;
    }

    @Override // defpackage.m0
    public int l() {
        return this.U;
    }

    public final boolean l0(PanelFeatureState panelFeatureState) {
        panelFeatureState.d(d0());
        panelFeatureState.g = new s(panelFeatureState.l);
        panelFeatureState.c = 81;
        return true;
    }

    @Override // defpackage.m0
    public MenuInflater m() {
        if (this.q == null) {
            j0();
            i0 i0Var = this.p;
            this.q = new g1(i0Var != null ? i0Var.k() : this.l);
        }
        return this.q;
    }

    public final boolean m0(PanelFeatureState panelFeatureState) {
        Context context = this.l;
        int i2 = panelFeatureState.a;
        if ((i2 == 0 || i2 == 108) && this.s != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(R$attr.actionBarTheme, typedValue, true);
            Resources.Theme themeNewTheme = null;
            if (typedValue.resourceId != 0) {
                themeNewTheme = context.getResources().newTheme();
                themeNewTheme.setTo(theme);
                themeNewTheme.applyStyle(typedValue.resourceId, true);
                themeNewTheme.resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (themeNewTheme == null) {
                    themeNewTheme = context.getResources().newTheme();
                    themeNewTheme.setTo(theme);
                }
                themeNewTheme.applyStyle(typedValue.resourceId, true);
            }
            if (themeNewTheme != null) {
                d1 d1Var = new d1(context, 0);
                d1Var.getTheme().setTo(themeNewTheme);
                context = d1Var;
            }
        }
        p1 p1Var = new p1(context);
        p1Var.V(this);
        panelFeatureState.c(p1Var);
        return true;
    }

    @Override // defpackage.m0
    public i0 n() {
        j0();
        return this.p;
    }

    public final void n0(int i2) {
        this.b0 = (1 << i2) | this.b0;
        if (this.a0) {
            return;
        }
        t9.b0(this.m.getDecorView(), this.c0);
        this.a0 = true;
    }

    @Override // defpackage.m0
    public void o() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.l);
        if (layoutInflaterFrom.getFactory() == null) {
            f9.b(layoutInflaterFrom, this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof AppCompatDelegateImpl) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public final boolean o0() {
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

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return U(view, str, context, attributeSet);
    }

    @Override // defpackage.m0
    public void p() {
        i0 i0VarN = n();
        if (i0VarN == null || !i0VarN.l()) {
            n0(0);
        }
    }

    public boolean p0() {
        return this.A;
    }

    @Override // defpackage.m0
    public void q(Configuration configuration) {
        i0 i0VarN;
        if (this.H && this.B && (i0VarN = n()) != null) {
            i0VarN.m(configuration);
        }
        h2.b().g(this.l);
        J(false);
    }

    public int q0(Context context, int i2) {
        if (i2 == -100) {
            return -1;
        }
        if (i2 != -1) {
            if (i2 == 0) {
                if (Build.VERSION.SDK_INT < 23 || ((UiModeManager) context.getApplicationContext().getSystemService(UiModeManager.class)).getNightMode() != 0) {
                    return f0(context).c();
                }
                return -1;
            }
            if (i2 != 1 && i2 != 2) {
                if (i2 == 3) {
                    return e0(context).c();
                }
                throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
            }
        }
        return i2;
    }

    @Override // defpackage.m0
    public void r(Bundle bundle) {
        this.Q = true;
        J(false);
        a0();
        Object obj = this.k;
        if (obj instanceof Activity) {
            String strC = null;
            try {
                strC = x6.c((Activity) obj);
            } catch (IllegalArgumentException unused) {
            }
            if (strC != null) {
                i0 i0VarB0 = B0();
                if (i0VarB0 == null) {
                    this.d0 = true;
                } else {
                    i0VarB0.r(true);
                }
            }
            m0.c(this);
        }
        this.R = true;
    }

    public boolean r0() {
        b1 b1Var = this.v;
        if (b1Var != null) {
            b1Var.c();
            return true;
        }
        i0 i0VarN = n();
        return i0VarN != null && i0VarN.h();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0048  */
    @Override // defpackage.m0
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
            defpackage.m0.y(r3)
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
            g4<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.e
            java.lang.Object r1 = r3.k
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.U
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L57
        L48:
            g4<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.e
            java.lang.Object r1 = r3.k
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L57:
            i0 r0 = r3.p
            if (r0 == 0) goto L5e
            r0.n()
        L5e:
            r3.P()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.s():void");
    }

    public boolean s0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            this.P = (keyEvent.getFlags() & 128) != 0;
        } else if (i2 == 82) {
            t0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // defpackage.m0
    public void t(Bundle bundle) {
        Z();
    }

    public final boolean t0(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        PanelFeatureState panelFeatureStateG0 = g0(i2, true);
        if (panelFeatureStateG0.o) {
            return false;
        }
        return D0(panelFeatureStateG0, keyEvent);
    }

    @Override // defpackage.m0
    public void u() {
        i0 i0VarN = n();
        if (i0VarN != null) {
            i0VarN.s(true);
        }
    }

    public boolean u0(int i2, KeyEvent keyEvent) {
        i0 i0VarN = n();
        if (i0VarN != null && i0VarN.o(i2, keyEvent)) {
            return true;
        }
        PanelFeatureState panelFeatureState = this.O;
        if (panelFeatureState != null && C0(panelFeatureState, keyEvent.getKeyCode(), keyEvent, 1)) {
            PanelFeatureState panelFeatureState2 = this.O;
            if (panelFeatureState2 != null) {
                panelFeatureState2.n = true;
            }
            return true;
        }
        if (this.O == null) {
            PanelFeatureState panelFeatureStateG0 = g0(0, true);
            D0(panelFeatureStateG0, keyEvent);
            boolean zC0 = C0(panelFeatureStateG0, keyEvent.getKeyCode(), keyEvent, 1);
            panelFeatureStateG0.m = false;
            if (zC0) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.m0
    public void v(Bundle bundle) {
    }

    public boolean v0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            boolean z = this.P;
            this.P = false;
            PanelFeatureState panelFeatureStateG0 = g0(0, false);
            if (panelFeatureStateG0 != null && panelFeatureStateG0.o) {
                if (!z) {
                    R(panelFeatureStateG0, true);
                }
                return true;
            }
            if (r0()) {
                return true;
            }
        } else if (i2 == 82) {
            w0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // defpackage.m0
    public void w() {
        this.S = true;
        I();
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean w0(int r5, android.view.KeyEvent r6) {
        /*
            r4 = this;
            b1 r0 = r4.v
            r1 = 0
            if (r0 == 0) goto L6
            return r1
        L6:
            r0 = 1
            androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r2 = r4.g0(r5, r0)
            if (r5 != 0) goto L43
            q2 r5 = r4.s
            if (r5 == 0) goto L43
            boolean r5 = r5.g()
            if (r5 == 0) goto L43
            android.content.Context r5 = r4.l
            android.view.ViewConfiguration r5 = android.view.ViewConfiguration.get(r5)
            boolean r5 = r5.hasPermanentMenuKey()
            if (r5 != 0) goto L43
            q2 r5 = r4.s
            boolean r5 = r5.c()
            if (r5 != 0) goto L3c
            boolean r5 = r4.T
            if (r5 != 0) goto L62
            boolean r5 = r4.D0(r2, r6)
            if (r5 == 0) goto L62
            q2 r5 = r4.s
            boolean r0 = r5.e()
            goto L68
        L3c:
            q2 r5 = r4.s
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
            boolean r5 = r4.D0(r2, r6)
            goto L5c
        L5b:
            r5 = r0
        L5c:
            if (r5 == 0) goto L62
            r4.A0(r2, r6)
            goto L68
        L62:
            r0 = r1
            goto L68
        L64:
            r4.R(r2, r0)
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.w0(int, android.view.KeyEvent):boolean");
    }

    @Override // defpackage.m0
    public void x() {
        this.S = false;
        i0 i0VarN = n();
        if (i0VarN != null) {
            i0VarN.s(false);
        }
    }

    public void x0(int i2) {
        i0 i0VarN;
        if (i2 != 108 || (i0VarN = n()) == null) {
            return;
        }
        i0VarN.i(true);
    }

    public void y0(int i2) {
        if (i2 == 108) {
            i0 i0VarN = n();
            if (i0VarN != null) {
                i0VarN.i(false);
                return;
            }
            return;
        }
        if (i2 == 0) {
            PanelFeatureState panelFeatureStateG0 = g0(i2, true);
            if (panelFeatureStateG0.o) {
                R(panelFeatureStateG0, false);
            }
        }
    }

    public void z0(ViewGroup viewGroup) {
    }

    public AppCompatDelegateImpl(Dialog dialog, l0 l0Var) {
        this(dialog.getContext(), dialog.getWindow(), l0Var, dialog);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    public AppCompatDelegateImpl(Context context, Window window, l0 l0Var, Object obj) {
        g4<String, Integer> g4Var;
        Integer num;
        AppCompatActivity appCompatActivityK0;
        this.z = null;
        this.A = true;
        this.U = -100;
        this.c0 = new b();
        this.l = context;
        this.o = l0Var;
        this.k = obj;
        if (this.U == -100 && (obj instanceof Dialog) && (appCompatActivityK0 = K0()) != null) {
            this.U = appCompatActivityK0.getDelegate().l();
        }
        if (this.U == -100 && (num = (g4Var = e).get(obj.getClass().getName())) != null) {
            this.U = num.intValue();
            g4Var.remove(obj.getClass().getName());
        }
        if (window != null) {
            L(window);
        }
        h2.h();
    }

    public class k extends i1 {
        public k(Window.Callback callback) {
            super(callback);
        }

        public final ActionMode b(ActionMode.Callback callback) {
            f1.a aVar = new f1.a(AppCompatDelegateImpl.this.l, callback);
            b1 b1VarH = AppCompatDelegateImpl.this.H(aVar);
            if (b1VarH != null) {
                return aVar.e(b1VarH);
            }
            return null;
        }

        @Override // defpackage.i1, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.W(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // defpackage.i1, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.u0(keyEvent.getKeyCode(), keyEvent);
        }

        @Override // defpackage.i1, android.view.Window.Callback
        public void onContentChanged() {
        }

        @Override // defpackage.i1, android.view.Window.Callback
        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof p1)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        @Override // defpackage.i1, android.view.Window.Callback
        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            AppCompatDelegateImpl.this.x0(i);
            return true;
        }

        @Override // defpackage.i1, android.view.Window.Callback
        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            AppCompatDelegateImpl.this.y0(i);
        }

        @Override // defpackage.i1, android.view.Window.Callback
        public boolean onPreparePanel(int i, View view, Menu menu) {
            p1 p1Var = menu instanceof p1 ? (p1) menu : null;
            if (i == 0 && p1Var == null) {
                return false;
            }
            if (p1Var != null) {
                p1Var.e0(true);
            }
            boolean zOnPreparePanel = super.onPreparePanel(i, view, menu);
            if (p1Var != null) {
                p1Var.e0(false);
            }
            return zOnPreparePanel;
        }

        @Override // defpackage.i1, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            p1 p1Var;
            PanelFeatureState panelFeatureStateG0 = AppCompatDelegateImpl.this.g0(0, true);
            if (panelFeatureStateG0 == null || (p1Var = panelFeatureStateG0.j) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i);
            } else {
                super.onProvideKeyboardShortcuts(list, p1Var, i);
            }
        }

        @Override // defpackage.i1, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            return AppCompatDelegateImpl.this.p0() ? b(callback) : super.onWindowStartingActionMode(callback);
        }

        @Override // defpackage.i1, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (AppCompatDelegateImpl.this.p0() && i == 0) {
                return b(callback);
            }
            return super.onWindowStartingActionMode(callback, i);
        }
    }
}
