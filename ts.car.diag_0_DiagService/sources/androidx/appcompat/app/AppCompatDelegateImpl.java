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
import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$style;
import androidx.appcompat.R$styleable;
import androidx.appcompat.c.b;
import androidx.appcompat.c.f;
import androidx.appcompat.view.menu.f;
import androidx.appcompat.view.menu.l;
import androidx.appcompat.widget.A;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.C0063i;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.E;
import androidx.appcompat.widget.U;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.appcompat.widget.Z;
import androidx.appcompat.widget.a0;
import androidx.core.content.res.e;
import androidx.core.view.ViewCompat;
import androidx.core.view.e;
import androidx.core.view.v;
import androidx.core.view.x;
import androidx.core.view.z;
import androidx.lifecycle.Lifecycle;
import java.lang.Thread;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
class AppCompatDelegateImpl extends AppCompatDelegate implements f.a, LayoutInflater.Factory2 {
    private static final b.a.g<String, Integer> c0;
    private static final boolean d0;
    private static final int[] e0;
    private static final boolean f0;
    private static final boolean g0;
    private static boolean h0;
    private boolean A;
    private boolean B;
    boolean C;
    boolean D;
    boolean E;
    boolean F;
    boolean G;
    private boolean H;
    private PanelFeatureState[] I;
    private PanelFeatureState J;
    private boolean K;
    private boolean L;
    private boolean M;
    private boolean N;
    boolean O;
    private int P;
    private int Q;
    private boolean R;
    private boolean S;
    private AutoNightModeManager T;
    private AutoNightModeManager U;
    boolean V;
    int W;
    private final Runnable X;
    private boolean Y;
    private Rect Z;
    private Rect a0;
    private androidx.appcompat.app.d b0;
    final Object f;
    final Context g;
    Window h;
    private j i;
    final androidx.appcompat.app.b j;
    ActionBar k;
    MenuInflater l;
    private CharSequence m;
    private A n;
    private h o;
    private r p;
    androidx.appcompat.c.b q;
    ActionBarContextView r;
    PopupWindow s;
    Runnable t;
    v u;
    private boolean v;
    private boolean w;
    ViewGroup x;
    private TextView y;
    private View z;

    /* JADX INFO: compiled from: caradapter-bug */
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @VisibleForTesting
    abstract class AutoNightModeManager {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private BroadcastReceiver f116a;

        /* JADX INFO: compiled from: caradapter-bug */
        class a extends BroadcastReceiver {
            a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                AutoNightModeManager.this.d();
            }
        }

        AutoNightModeManager() {
        }

        void a() {
            BroadcastReceiver broadcastReceiver = this.f116a;
            if (broadcastReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.g.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.f116a = null;
            }
        }

        @Nullable
        abstract IntentFilter b();

        abstract int c();

        abstract void d();

        void e() {
            a();
            IntentFilter intentFilterB = b();
            if (intentFilterB == null || intentFilterB.countActions() == 0) {
                return;
            }
            if (this.f116a == null) {
                this.f116a = new a();
            }
            AppCompatDelegateImpl.this.g.registerReceiver(this.f116a, intentFilterB);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    protected static final class PanelFeatureState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f119a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f120b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f121c;
        int d;
        int e;
        int f;
        ViewGroup g;
        View h;
        View i;
        androidx.appcompat.view.menu.f j;
        androidx.appcompat.view.menu.d k;
        Context l;
        boolean m;
        boolean n;
        boolean o;
        public boolean p;
        boolean q = false;
        boolean r;
        Bundle s;

        /* JADX INFO: compiled from: caradapter-bug */
        @SuppressLint({"BanParcelableUsage"})
        private static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new a();

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            int f122c;
            boolean d;
            Bundle e;

            /* JADX INFO: compiled from: caradapter-bug */
            class a implements Parcelable.ClassLoaderCreator<SavedState> {
                a() {
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public SavedState createFromParcel(Parcel parcel) {
                    return SavedState.a(parcel, null);
                }

                @Override // android.os.Parcelable.ClassLoaderCreator
                /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
                public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.a(parcel, classLoader);
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
                public SavedState[] newArray(int i) {
                    return new SavedState[i];
                }
            }

            SavedState() {
            }

            static SavedState a(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.f122c = parcel.readInt();
                boolean z = parcel.readInt() == 1;
                savedState.d = z;
                if (z) {
                    savedState.e = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.f122c);
                parcel.writeInt(this.d ? 1 : 0);
                if (this.d) {
                    parcel.writeBundle(this.e);
                }
            }
        }

        PanelFeatureState(int i) {
            this.f119a = i;
        }

        androidx.appcompat.view.menu.m a(l.a aVar) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                androidx.appcompat.view.menu.d dVar = new androidx.appcompat.view.menu.d(this.l, R$layout.abc_list_menu_item_layout);
                this.k = dVar;
                dVar.h(aVar);
                this.j.b(this.k);
            }
            return this.k.f(this.g);
        }

        public boolean b() {
            if (this.h == null) {
                return false;
            }
            return this.i != null || this.k.b().getCount() > 0;
        }

        void c(androidx.appcompat.view.menu.f fVar) {
            androidx.appcompat.view.menu.d dVar;
            androidx.appcompat.view.menu.f fVar2 = this.j;
            if (fVar == fVar2) {
                return;
            }
            if (fVar2 != null) {
                fVar2.O(this.k);
            }
            this.j = fVar;
            if (fVar == null || (dVar = this.k) == null) {
                return;
            }
            fVar.b(dVar);
        }

        void d(Context context) {
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
            androidx.appcompat.c.d dVar = new androidx.appcompat.c.d(context, 0);
            dVar.getTheme().setTo(themeNewTheme);
            this.l = dVar;
            TypedArray typedArrayObtainStyledAttributes = dVar.obtainStyledAttributes(R$styleable.AppCompatTheme);
            this.f120b = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_panelBackground, 0);
            this.f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Thread.UncaughtExceptionHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Thread.UncaughtExceptionHandler f123a;

        a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.f123a = uncaughtExceptionHandler;
        }

        private boolean a(Throwable th) {
            String message;
            if (!(th instanceof Resources.NotFoundException) || (message = th.getMessage()) == null) {
                return false;
            }
            return message.contains("drawable") || message.contains("Drawable");
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(@NonNull Thread thread, @NonNull Throwable th) {
            if (!a(th)) {
                this.f123a.uncaughtException(thread, th);
                return;
            }
            Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            notFoundException.initCause(th.getCause());
            notFoundException.setStackTrace(th.getStackTrace());
            this.f123a.uncaughtException(thread, notFoundException);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl.W & 1) != 0) {
                appCompatDelegateImpl.U(0);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl2.W & 4096) != 0) {
                appCompatDelegateImpl2.U(108);
            }
            AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl3.V = false;
            appCompatDelegateImpl3.W = 0;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements androidx.core.view.p {
        c() {
        }

        @Override // androidx.core.view.p
        public z a(View view, z zVar) {
            int iG = zVar.g();
            int iL0 = AppCompatDelegateImpl.this.L0(zVar, null);
            if (iG != iL0) {
                zVar = zVar.k(zVar.e(), iL0, zVar.f(), zVar.d());
            }
            return ViewCompat.E(view, zVar);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class d implements E.a {
        d() {
        }

        @Override // androidx.appcompat.widget.E.a
        public void a(Rect rect) {
            rect.top = AppCompatDelegateImpl.this.L0(null, rect);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class e implements ContentFrameLayout.a {
        e() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void a() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void onDetachedFromWindow() {
            AppCompatDelegateImpl.this.S();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class f implements Runnable {

        /* JADX INFO: compiled from: caradapter-bug */
        class a extends x {
            a() {
            }

            @Override // androidx.core.view.w
            public void a(View view) {
                AppCompatDelegateImpl.this.r.setAlpha(1.0f);
                AppCompatDelegateImpl.this.u.f(null);
                AppCompatDelegateImpl.this.u = null;
            }

            @Override // androidx.core.view.x, androidx.core.view.w
            public void b(View view) {
                AppCompatDelegateImpl.this.r.setVisibility(0);
            }
        }

        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            appCompatDelegateImpl.s.showAtLocation(appCompatDelegateImpl.r, 55, 0, 0);
            AppCompatDelegateImpl.this.V();
            if (!AppCompatDelegateImpl.this.D0()) {
                AppCompatDelegateImpl.this.r.setAlpha(1.0f);
                AppCompatDelegateImpl.this.r.setVisibility(0);
                return;
            }
            AppCompatDelegateImpl.this.r.setAlpha(0.0f);
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            v vVarB = ViewCompat.b(appCompatDelegateImpl2.r);
            vVarB.a(1.0f);
            appCompatDelegateImpl2.u = vVarB;
            AppCompatDelegateImpl.this.u.f(new a());
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class g extends x {
        g() {
        }

        @Override // androidx.core.view.w
        public void a(View view) {
            AppCompatDelegateImpl.this.r.setAlpha(1.0f);
            AppCompatDelegateImpl.this.u.f(null);
            AppCompatDelegateImpl.this.u = null;
        }

        @Override // androidx.core.view.x, androidx.core.view.w
        public void b(View view) {
            AppCompatDelegateImpl.this.r.setVisibility(0);
            AppCompatDelegateImpl.this.r.sendAccessibilityEvent(32);
            if (AppCompatDelegateImpl.this.r.getParent() instanceof View) {
                ViewCompat.J((View) AppCompatDelegateImpl.this.r.getParent());
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class h implements l.a {
        h() {
        }

        @Override // androidx.appcompat.view.menu.l.a
        public void a(@NonNull androidx.appcompat.view.menu.f fVar, boolean z) {
            AppCompatDelegateImpl.this.L(fVar);
        }

        @Override // androidx.appcompat.view.menu.l.a
        public boolean b(@NonNull androidx.appcompat.view.menu.f fVar) {
            Window.Callback callbackF0 = AppCompatDelegateImpl.this.f0();
            if (callbackF0 == null) {
                return true;
            }
            callbackF0.onMenuOpened(108, fVar);
            return true;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class i implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private b.a f132a;

        /* JADX INFO: compiled from: caradapter-bug */
        class a extends x {
            a() {
            }

            @Override // androidx.core.view.w
            public void a(View view) {
                AppCompatDelegateImpl.this.r.setVisibility(8);
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                PopupWindow popupWindow = appCompatDelegateImpl.s;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (appCompatDelegateImpl.r.getParent() instanceof View) {
                    ViewCompat.J((View) AppCompatDelegateImpl.this.r.getParent());
                }
                AppCompatDelegateImpl.this.r.removeAllViews();
                AppCompatDelegateImpl.this.u.f(null);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.u = null;
                ViewCompat.J(appCompatDelegateImpl2.x);
            }
        }

        public i(b.a aVar) {
            this.f132a = aVar;
        }

        @Override // androidx.appcompat.c.b.a
        public boolean a(androidx.appcompat.c.b bVar, Menu menu) {
            ViewCompat.J(AppCompatDelegateImpl.this.x);
            return this.f132a.a(bVar, menu);
        }

        @Override // androidx.appcompat.c.b.a
        public boolean b(androidx.appcompat.c.b bVar, MenuItem menuItem) {
            return this.f132a.b(bVar, menuItem);
        }

        @Override // androidx.appcompat.c.b.a
        public boolean c(androidx.appcompat.c.b bVar, Menu menu) {
            return this.f132a.c(bVar, menu);
        }

        @Override // androidx.appcompat.c.b.a
        public void d(androidx.appcompat.c.b bVar) {
            this.f132a.d(bVar);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.s != null) {
                appCompatDelegateImpl.h.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.t);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.r != null) {
                appCompatDelegateImpl2.V();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                v vVarB = ViewCompat.b(appCompatDelegateImpl3.r);
                vVarB.a(0.0f);
                appCompatDelegateImpl3.u = vVarB;
                AppCompatDelegateImpl.this.u.f(new a());
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            androidx.appcompat.app.b bVar2 = appCompatDelegateImpl4.j;
            if (bVar2 != null) {
                bVar2.i(appCompatDelegateImpl4.q);
            }
            AppCompatDelegateImpl appCompatDelegateImpl5 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl5.q = null;
            ViewCompat.J(appCompatDelegateImpl5.x);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class k extends AutoNightModeManager {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final PowerManager f135c;

        k(@NonNull Context context) {
            super();
            this.f135c = (PowerManager) context.getApplicationContext().getSystemService("power");
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        IntentFilter b() {
            if (Build.VERSION.SDK_INT < 21) {
                return null;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public int c() {
            return (Build.VERSION.SDK_INT < 21 || !this.f135c.isPowerSaveMode()) ? 1 : 2;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public void d() {
            AppCompatDelegateImpl.this.F();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class l extends AutoNightModeManager {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final androidx.appcompat.app.g f136c;

        l(@NonNull androidx.appcompat.app.g gVar) {
            super();
            this.f136c = gVar;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public int c() {
            return this.f136c.d() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AutoNightModeManager
        public void d() {
            AppCompatDelegateImpl.this.F();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(17)
    static class m {
        static void a(@NonNull Configuration configuration, @NonNull Configuration configuration2, @NonNull Configuration configuration3) {
            int i = configuration.densityDpi;
            int i2 = configuration2.densityDpi;
            if (i != i2) {
                configuration3.densityDpi = i2;
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(24)
    static class n {
        static void a(@NonNull Configuration configuration, @NonNull Configuration configuration2, @NonNull Configuration configuration3) {
            LocaleList locales = configuration.getLocales();
            LocaleList locales2 = configuration2.getLocales();
            if (locales.equals(locales2)) {
                return;
            }
            configuration3.setLocales(locales2);
            configuration3.locale = configuration2.locale;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(26)
    static class o {
        static void a(@NonNull Configuration configuration, @NonNull Configuration configuration2, @NonNull Configuration configuration3) {
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

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(17)
    private static class p {
        static void a(ContextThemeWrapper contextThemeWrapper, Configuration configuration) {
            contextThemeWrapper.applyOverrideConfiguration(configuration);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class q extends ContentFrameLayout {
        public q(Context context) {
            super(context);
        }

        private boolean j(int i, int i2) {
            return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.T(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !j((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            AppCompatDelegateImpl.this.N(0);
            return true;
        }

        @Override // android.view.View
        public void setBackgroundResource(int i) {
            setBackgroundDrawable(androidx.appcompat.a.a.a.b(getContext(), i));
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class r implements l.a {
        r() {
        }

        @Override // androidx.appcompat.view.menu.l.a
        public void a(@NonNull androidx.appcompat.view.menu.f fVar, boolean z) {
            androidx.appcompat.view.menu.f fVarD = fVar.D();
            boolean z2 = fVarD != fVar;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z2) {
                fVar = fVarD;
            }
            PanelFeatureState panelFeatureStateY = appCompatDelegateImpl.Y(fVar);
            if (panelFeatureStateY != null) {
                if (!z2) {
                    AppCompatDelegateImpl.this.O(panelFeatureStateY, z);
                } else {
                    AppCompatDelegateImpl.this.K(panelFeatureStateY.f119a, panelFeatureStateY, fVarD);
                    AppCompatDelegateImpl.this.O(panelFeatureStateY, true);
                }
            }
        }

        @Override // androidx.appcompat.view.menu.l.a
        public boolean b(@NonNull androidx.appcompat.view.menu.f fVar) {
            Window.Callback callbackF0;
            if (fVar != fVar.D()) {
                return true;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (!appCompatDelegateImpl.C || (callbackF0 = appCompatDelegateImpl.f0()) == null || AppCompatDelegateImpl.this.O) {
                return true;
            }
            callbackF0.onMenuOpened(108, fVar);
            return true;
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        c0 = new b.a.g<>();
        d0 = i2 < 21;
        e0 = new int[]{R.attr.windowBackground};
        f0 = !"robolectric".equals(Build.FINGERPRINT);
        g0 = i2 >= 17;
        if (!d0 || h0) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new a(Thread.getDefaultUncaughtExceptionHandler()));
        h0 = true;
    }

    AppCompatDelegateImpl(Activity activity, androidx.appcompat.app.b bVar) {
        this(activity, null, bVar, activity);
    }

    private boolean A0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        A a2;
        A a3;
        A a4;
        if (this.O) {
            return false;
        }
        if (panelFeatureState.m) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.J;
        if (panelFeatureState2 != null && panelFeatureState2 != panelFeatureState) {
            O(panelFeatureState2, false);
        }
        Window.Callback callbackF0 = f0();
        if (callbackF0 != null) {
            panelFeatureState.i = callbackF0.onCreatePanelView(panelFeatureState.f119a);
        }
        int i2 = panelFeatureState.f119a;
        boolean z = i2 == 0 || i2 == 108;
        if (z && (a4 = this.n) != null) {
            a4.h();
        }
        if (panelFeatureState.i == null) {
            if (z) {
                y0();
            }
            if (panelFeatureState.j == null || panelFeatureState.r) {
                if (panelFeatureState.j == null && (!j0(panelFeatureState) || panelFeatureState.j == null)) {
                    return false;
                }
                if (z && this.n != null) {
                    if (this.o == null) {
                        this.o = new h();
                    }
                    this.n.a(panelFeatureState.j, this.o);
                }
                panelFeatureState.j.d0();
                if (!callbackF0.onCreatePanelMenu(panelFeatureState.f119a, panelFeatureState.j)) {
                    panelFeatureState.c(null);
                    if (z && (a2 = this.n) != null) {
                        a2.a(null, this.o);
                    }
                    return false;
                }
                panelFeatureState.r = false;
            }
            panelFeatureState.j.d0();
            Bundle bundle = panelFeatureState.s;
            if (bundle != null) {
                panelFeatureState.j.P(bundle);
                panelFeatureState.s = null;
            }
            if (!callbackF0.onPreparePanel(0, panelFeatureState.i, panelFeatureState.j)) {
                if (z && (a3 = this.n) != null) {
                    a3.a(null, this.o);
                }
                panelFeatureState.j.c0();
                return false;
            }
            boolean z2 = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            panelFeatureState.p = z2;
            panelFeatureState.j.setQwertyMode(z2);
            panelFeatureState.j.c0();
        }
        panelFeatureState.m = true;
        panelFeatureState.n = false;
        this.J = panelFeatureState;
        return true;
    }

    private void B0(boolean z) {
        A a2 = this.n;
        if (a2 == null || !a2.i() || (ViewConfiguration.get(this.g).hasPermanentMenuKey() && !this.n.b())) {
            PanelFeatureState panelFeatureStateD0 = d0(0, true);
            panelFeatureStateD0.q = true;
            O(panelFeatureStateD0, false);
            x0(panelFeatureStateD0, null);
            return;
        }
        Window.Callback callbackF0 = f0();
        if (this.n.d() && z) {
            this.n.f();
            if (this.O) {
                return;
            }
            callbackF0.onPanelClosed(108, d0(0, true).j);
            return;
        }
        if (callbackF0 == null || this.O) {
            return;
        }
        if (this.V && (this.W & 1) != 0) {
            this.h.getDecorView().removeCallbacks(this.X);
            this.X.run();
        }
        PanelFeatureState panelFeatureStateD02 = d0(0, true);
        androidx.appcompat.view.menu.f fVar = panelFeatureStateD02.j;
        if (fVar == null || panelFeatureStateD02.r || !callbackF0.onPreparePanel(0, panelFeatureStateD02.i, fVar)) {
            return;
        }
        callbackF0.onMenuOpened(108, panelFeatureStateD02.j);
        this.n.g();
    }

    private int C0(int i2) {
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

    private boolean E0(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.h.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || ViewCompat.A((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    private boolean G(boolean z) {
        if (this.O) {
            return false;
        }
        int iJ = J();
        boolean zJ0 = J0(n0(this.g, iJ), z);
        if (iJ == 0) {
            c0(this.g).e();
        } else {
            AutoNightModeManager autoNightModeManager = this.T;
            if (autoNightModeManager != null) {
                autoNightModeManager.a();
            }
        }
        if (iJ == 3) {
            b0(this.g).e();
        } else {
            AutoNightModeManager autoNightModeManager2 = this.U;
            if (autoNightModeManager2 != null) {
                autoNightModeManager2.a();
            }
        }
        return zJ0;
    }

    private void H() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.x.findViewById(R.id.content);
        View decorView = this.h.getDecorView();
        contentFrameLayout.i(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray typedArrayObtainStyledAttributes = this.g.obtainStyledAttributes(R$styleable.AppCompatTheme);
        typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.f());
        typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.g());
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowFixedWidthMajor)) {
            typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.d());
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowFixedWidthMinor)) {
            typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.e());
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowFixedHeightMajor)) {
            typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.b());
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowFixedHeightMinor)) {
            typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.c());
        }
        typedArrayObtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    private void H0() {
        if (this.w) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private void I(@NonNull Window window) {
        if (this.h != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof j) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        j jVar = new j(callback);
        this.i = jVar;
        window.setCallback(jVar);
        U uT = U.t(this.g, null, e0);
        Drawable drawableG = uT.g(0);
        if (drawableG != null) {
            window.setBackgroundDrawable(drawableG);
        }
        uT.v();
        this.h = window;
    }

    @Nullable
    private AppCompatActivity I0() {
        for (Context baseContext = this.g; baseContext != null; baseContext = ((ContextWrapper) baseContext).getBaseContext()) {
            if (baseContext instanceof AppCompatActivity) {
                return (AppCompatActivity) baseContext;
            }
            if (!(baseContext instanceof ContextWrapper)) {
                break;
            }
        }
        return null;
    }

    private int J() {
        int i2 = this.P;
        return i2 != -100 ? i2 : AppCompatDelegate.j();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean J0(int r7, boolean r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.g
            r1 = 0
            android.content.res.Configuration r0 = r6.P(r0, r7, r1)
            boolean r2 = r6.l0()
            android.content.Context r3 = r6.g
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
            boolean r8 = r6.L
            if (r8 == 0) goto L47
            boolean r8 = androidx.appcompat.app.AppCompatDelegateImpl.f0
            if (r8 != 0) goto L30
            boolean r8 = r6.M
            if (r8 == 0) goto L47
        L30:
            java.lang.Object r8 = r6.f
            boolean r5 = r8 instanceof android.app.Activity
            if (r5 == 0) goto L47
            android.app.Activity r8 = (android.app.Activity) r8
            boolean r8 = r8.isChild()
            if (r8 != 0) goto L47
            java.lang.Object r8 = r6.f
            android.app.Activity r8 = (android.app.Activity) r8
            androidx.core.app.a.j(r8)
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
            java.lang.Object r6 = r6.f
            boolean r8 = r6 instanceof androidx.appcompat.app.AppCompatActivity
            if (r8 == 0) goto L5e
            androidx.appcompat.app.AppCompatActivity r6 = (androidx.appcompat.app.AppCompatActivity) r6
            r6.y(r7)
        L5e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.J0(int, boolean):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void K0(int i2, boolean z, @Nullable Configuration configuration) {
        int i3 = Build.VERSION.SDK_INT;
        Resources resources = this.g.getResources();
        Configuration configuration2 = new Configuration(resources.getConfiguration());
        if (configuration != null) {
            configuration2.updateFrom(configuration);
        }
        configuration2.uiMode = i2 | (resources.getConfiguration().uiMode & (-49));
        resources.updateConfiguration(configuration2, null);
        if (i3 < 26) {
            androidx.appcompat.app.e.a(resources);
        }
        int i4 = this.Q;
        if (i4 != 0) {
            this.g.setTheme(i4);
            if (i3 >= 23) {
                this.g.getTheme().applyStyle(this.Q, true);
            }
        }
        if (z) {
            Object obj = this.f;
            if (obj instanceof Activity) {
                Activity activity = (Activity) obj;
                if (activity instanceof androidx.lifecycle.f) {
                    if (((androidx.lifecycle.f) activity).a().b().isAtLeast(Lifecycle.State.STARTED)) {
                        activity.onConfigurationChanged(configuration2);
                    }
                } else if (this.N) {
                    activity.onConfigurationChanged(configuration2);
                }
            }
        }
    }

    private void M() {
        AutoNightModeManager autoNightModeManager = this.T;
        if (autoNightModeManager != null) {
            autoNightModeManager.a();
        }
        AutoNightModeManager autoNightModeManager2 = this.U;
        if (autoNightModeManager2 != null) {
            autoNightModeManager2.a();
        }
    }

    private void M0(View view) {
        view.setBackgroundColor((ViewCompat.w(view) & 8192) != 0 ? androidx.core.content.a.b(this.g, R$color.abc_decor_view_status_guard_light) : androidx.core.content.a.b(this.g, R$color.abc_decor_view_status_guard));
    }

    @NonNull
    private Configuration P(@NonNull Context context, int i2, @Nullable Configuration configuration) {
        int i3 = i2 != 1 ? i2 != 2 ? context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i3 | (configuration2.uiMode & (-49));
        return configuration2;
    }

    private ViewGroup Q() {
        ViewGroup viewGroup;
        TypedArray typedArrayObtainStyledAttributes = this.g.obtainStyledAttributes(R$styleable.AppCompatTheme);
        if (!typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowActionBar)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowNoTitle, false)) {
            z(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionBar, false)) {
            z(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            z(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            z(10);
        }
        this.F = typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_android_windowIsFloating, false);
        typedArrayObtainStyledAttributes.recycle();
        X();
        this.h.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.g);
        if (this.G) {
            viewGroup = this.E ? (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple, (ViewGroup) null);
        } else if (this.F) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_dialog_title_material, (ViewGroup) null);
            this.D = false;
            this.C = false;
        } else if (this.C) {
            TypedValue typedValue = new TypedValue();
            this.g.getTheme().resolveAttribute(R$attr.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new androidx.appcompat.c.d(this.g, typedValue.resourceId) : this.g).inflate(R$layout.abc_screen_toolbar, (ViewGroup) null);
            A a2 = (A) viewGroup.findViewById(R$id.decor_content_parent);
            this.n = a2;
            a2.e(f0());
            if (this.D) {
                this.n.m(109);
            }
            if (this.A) {
                this.n.m(2);
            }
            if (this.B) {
                this.n.m(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.C + ", windowActionBarOverlay: " + this.D + ", android:windowIsFloating: " + this.F + ", windowActionModeOverlay: " + this.E + ", windowNoTitle: " + this.G + " }");
        }
        if (Build.VERSION.SDK_INT >= 21) {
            ViewCompat.T(viewGroup, new c());
        } else if (viewGroup instanceof E) {
            ((E) viewGroup).a(new d());
        }
        if (this.n == null) {
            this.y = (TextView) viewGroup.findViewById(R$id.title);
        }
        a0.c(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(R$id.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.h.findViewById(R.id.content);
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
        this.h.setContentView(viewGroup);
        contentFrameLayout.h(new e());
        return viewGroup;
    }

    private void W() {
        if (this.w) {
            return;
        }
        this.x = Q();
        CharSequence charSequenceE0 = e0();
        if (!TextUtils.isEmpty(charSequenceE0)) {
            A a2 = this.n;
            if (a2 != null) {
                a2.c(charSequenceE0);
            } else if (y0() != null) {
                y0().t(charSequenceE0);
            } else {
                TextView textView = this.y;
                if (textView != null) {
                    textView.setText(charSequenceE0);
                }
            }
        }
        H();
        w0(this.x);
        this.w = true;
        PanelFeatureState panelFeatureStateD0 = d0(0, false);
        if (this.O) {
            return;
        }
        if (panelFeatureStateD0 == null || panelFeatureStateD0.j == null) {
            k0(108);
        }
    }

    private void X() {
        if (this.h == null) {
            Object obj = this.f;
            if (obj instanceof Activity) {
                I(((Activity) obj).getWindow());
            }
        }
        if (this.h == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    @NonNull
    private static Configuration Z(@NonNull Configuration configuration, @Nullable Configuration configuration2) {
        int i2 = Build.VERSION.SDK_INT;
        Configuration configuration3 = new Configuration();
        configuration3.fontScale = 0.0f;
        if (configuration2 != null && configuration.diff(configuration2) != 0) {
            float f2 = configuration.fontScale;
            float f3 = configuration2.fontScale;
            if (f2 != f3) {
                configuration3.fontScale = f3;
            }
            int i3 = configuration.mcc;
            int i4 = configuration2.mcc;
            if (i3 != i4) {
                configuration3.mcc = i4;
            }
            int i5 = configuration.mnc;
            int i6 = configuration2.mnc;
            if (i5 != i6) {
                configuration3.mnc = i6;
            }
            if (i2 >= 24) {
                n.a(configuration, configuration2, configuration3);
            } else if (!androidx.core.d.a.a(configuration.locale, configuration2.locale)) {
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
            if (i2 >= 26) {
                o.a(configuration, configuration2, configuration3);
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
            if (i2 >= 17) {
                m.a(configuration, configuration2, configuration3);
            }
        }
        return configuration3;
    }

    private AutoNightModeManager b0(@NonNull Context context) {
        if (this.U == null) {
            this.U = new k(context);
        }
        return this.U;
    }

    private AutoNightModeManager c0(@NonNull Context context) {
        if (this.T == null) {
            this.T = new l(androidx.appcompat.app.g.a(context));
        }
        return this.T;
    }

    private void g0() {
        W();
        if (this.C && this.k == null) {
            Object obj = this.f;
            if (obj instanceof Activity) {
                this.k = new androidx.appcompat.app.h((Activity) this.f, this.D);
            } else if (obj instanceof Dialog) {
                this.k = new androidx.appcompat.app.h((Dialog) this.f);
            }
            ActionBar actionBar = this.k;
            if (actionBar != null) {
                actionBar.r(this.Y);
            }
        }
    }

    private boolean h0(PanelFeatureState panelFeatureState) {
        View view = panelFeatureState.i;
        if (view != null) {
            panelFeatureState.h = view;
            return true;
        }
        if (panelFeatureState.j == null) {
            return false;
        }
        if (this.p == null) {
            this.p = new r();
        }
        View view2 = (View) panelFeatureState.a(this.p);
        panelFeatureState.h = view2;
        return view2 != null;
    }

    private boolean i0(PanelFeatureState panelFeatureState) {
        panelFeatureState.d(a0());
        panelFeatureState.g = new q(panelFeatureState.l);
        panelFeatureState.f121c = 81;
        return true;
    }

    private boolean j0(PanelFeatureState panelFeatureState) {
        Context context = this.g;
        int i2 = panelFeatureState.f119a;
        if ((i2 == 0 || i2 == 108) && this.n != null) {
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
                androidx.appcompat.c.d dVar = new androidx.appcompat.c.d(context, 0);
                dVar.getTheme().setTo(themeNewTheme);
                context = dVar;
            }
        }
        androidx.appcompat.view.menu.f fVar = new androidx.appcompat.view.menu.f(context);
        fVar.R(this);
        panelFeatureState.c(fVar);
        return true;
    }

    private void k0(int i2) {
        this.W = (1 << i2) | this.W;
        if (this.V) {
            return;
        }
        ViewCompat.H(this.h.getDecorView(), this.X);
        this.V = true;
    }

    private boolean l0() {
        int i2 = Build.VERSION.SDK_INT;
        if (!this.S && (this.f instanceof Activity)) {
            PackageManager packageManager = this.g.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            try {
                ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(this.g, this.f.getClass()), i2 >= 29 ? 269221888 : i2 >= 24 ? 786432 : 0);
                this.R = (activityInfo == null || (activityInfo.configChanges & 512) == 0) ? false : true;
            } catch (PackageManager.NameNotFoundException e2) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e2);
                this.R = false;
            }
        }
        this.S = true;
        return this.R;
    }

    private boolean q0(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        PanelFeatureState panelFeatureStateD0 = d0(i2, true);
        if (panelFeatureStateD0.o) {
            return false;
        }
        return A0(panelFeatureStateD0, keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean t0(int r4, android.view.KeyEvent r5) {
        /*
            r3 = this;
            androidx.appcompat.c.b r0 = r3.q
            r1 = 0
            if (r0 == 0) goto L6
            return r1
        L6:
            r0 = 1
            androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r2 = r3.d0(r4, r0)
            if (r4 != 0) goto L43
            androidx.appcompat.widget.A r4 = r3.n
            if (r4 == 0) goto L43
            boolean r4 = r4.i()
            if (r4 == 0) goto L43
            android.content.Context r4 = r3.g
            android.view.ViewConfiguration r4 = android.view.ViewConfiguration.get(r4)
            boolean r4 = r4.hasPermanentMenuKey()
            if (r4 != 0) goto L43
            androidx.appcompat.widget.A r4 = r3.n
            boolean r4 = r4.d()
            if (r4 != 0) goto L3c
            boolean r4 = r3.O
            if (r4 != 0) goto L62
            boolean r4 = r3.A0(r2, r5)
            if (r4 == 0) goto L62
            androidx.appcompat.widget.A r4 = r3.n
            boolean r0 = r4.g()
            goto L6a
        L3c:
            androidx.appcompat.widget.A r4 = r3.n
            boolean r0 = r4.f()
            goto L6a
        L43:
            boolean r4 = r2.o
            if (r4 != 0) goto L64
            boolean r4 = r2.n
            if (r4 == 0) goto L4c
            goto L64
        L4c:
            boolean r4 = r2.m
            if (r4 == 0) goto L62
            boolean r4 = r2.r
            if (r4 == 0) goto L5b
            r2.m = r1
            boolean r4 = r3.A0(r2, r5)
            goto L5c
        L5b:
            r4 = r0
        L5c:
            if (r4 == 0) goto L62
            r3.x0(r2, r5)
            goto L6a
        L62:
            r0 = r1
            goto L6a
        L64:
            boolean r4 = r2.o
            r3.O(r2, r0)
            r0 = r4
        L6a:
            if (r0 == 0) goto L87
            android.content.Context r3 = r3.g
            android.content.Context r3 = r3.getApplicationContext()
            java.lang.String r4 = "audio"
            java.lang.Object r3 = r3.getSystemService(r4)
            android.media.AudioManager r3 = (android.media.AudioManager) r3
            if (r3 == 0) goto L80
            r3.playSoundEffect(r1)
            goto L87
        L80:
            java.lang.String r3 = "AppCompatDelegate"
            java.lang.String r4 = "Couldn't get audio manager"
            android.util.Log.w(r3, r4)
        L87:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.t0(int, android.view.KeyEvent):boolean");
    }

    private void x0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        int i2;
        ViewGroup.LayoutParams layoutParams;
        if (panelFeatureState.o || this.O) {
            return;
        }
        if (panelFeatureState.f119a == 0) {
            if ((this.g.getResources().getConfiguration().screenLayout & 15) == 4) {
                return;
            }
        }
        Window.Callback callbackF0 = f0();
        if (callbackF0 != null && !callbackF0.onMenuOpened(panelFeatureState.f119a, panelFeatureState.j)) {
            O(panelFeatureState, true);
            return;
        }
        WindowManager windowManager = (WindowManager) this.g.getSystemService("window");
        if (windowManager != null && A0(panelFeatureState, keyEvent)) {
            if (panelFeatureState.g != null && !panelFeatureState.q) {
                View view = panelFeatureState.i;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i2 = -1;
                }
                panelFeatureState.n = false;
                WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i2, -2, panelFeatureState.d, panelFeatureState.e, 1002, 8519680, -3);
                layoutParams2.gravity = panelFeatureState.f121c;
                layoutParams2.windowAnimations = panelFeatureState.f;
                windowManager.addView(panelFeatureState.g, layoutParams2);
                panelFeatureState.o = true;
            }
            ViewGroup viewGroup = panelFeatureState.g;
            if (viewGroup == null) {
                if (!i0(panelFeatureState) || panelFeatureState.g == null) {
                    return;
                }
            } else if (panelFeatureState.q && viewGroup.getChildCount() > 0) {
                panelFeatureState.g.removeAllViews();
            }
            if (!h0(panelFeatureState) || !panelFeatureState.b()) {
                panelFeatureState.q = true;
                return;
            }
            ViewGroup.LayoutParams layoutParams3 = panelFeatureState.h.getLayoutParams();
            if (layoutParams3 == null) {
                layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
            }
            panelFeatureState.g.setBackgroundResource(panelFeatureState.f120b);
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
            layoutParams22.gravity = panelFeatureState.f121c;
            layoutParams22.windowAnimations = panelFeatureState.f;
            windowManager.addView(panelFeatureState.g, layoutParams22);
            panelFeatureState.o = true;
        }
    }

    private boolean z0(PanelFeatureState panelFeatureState, int i2, KeyEvent keyEvent, int i3) {
        androidx.appcompat.view.menu.f fVar;
        boolean zPerformShortcut = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.m || A0(panelFeatureState, keyEvent)) && (fVar = panelFeatureState.j) != null) {
            zPerformShortcut = fVar.performShortcut(i2, keyEvent, i3);
        }
        if (zPerformShortcut && (i3 & 1) == 0 && this.n == null) {
            O(panelFeatureState, true);
        }
        return zPerformShortcut;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void A(int i2) {
        W();
        ViewGroup viewGroup = (ViewGroup) this.x.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.g).inflate(i2, viewGroup);
        this.i.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void B(View view) {
        W();
        ViewGroup viewGroup = (ViewGroup) this.x.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.i.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void C(View view, ViewGroup.LayoutParams layoutParams) {
        W();
        ViewGroup viewGroup = (ViewGroup) this.x.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.i.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void D(@StyleRes int i2) {
        this.Q = i2;
    }

    final boolean D0() {
        ViewGroup viewGroup;
        return this.w && (viewGroup = this.x) != null && ViewCompat.B(viewGroup);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void E(CharSequence charSequence) {
        this.m = charSequence;
        A a2 = this.n;
        if (a2 != null) {
            a2.c(charSequence);
            return;
        }
        if (y0() != null) {
            y0().t(charSequence);
            return;
        }
        TextView textView = this.y;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public boolean F() {
        return G(true);
    }

    public androidx.appcompat.c.b F0(@NonNull b.a aVar) {
        androidx.appcompat.app.b bVar;
        if (aVar == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        androidx.appcompat.c.b bVar2 = this.q;
        if (bVar2 != null) {
            bVar2.c();
        }
        i iVar = new i(aVar);
        ActionBar actionBarM = m();
        if (actionBarM != null) {
            androidx.appcompat.c.b bVarU = actionBarM.u(iVar);
            this.q = bVarU;
            if (bVarU != null && (bVar = this.j) != null) {
                bVar.g(bVarU);
            }
        }
        if (this.q == null) {
            this.q = G0(iVar);
        }
        return this.q;
    }

    androidx.appcompat.c.b G0(@NonNull b.a aVar) {
        androidx.appcompat.c.b bVarJ;
        Context dVar;
        androidx.appcompat.app.b bVar;
        V();
        androidx.appcompat.c.b bVar2 = this.q;
        if (bVar2 != null) {
            bVar2.c();
        }
        if (!(aVar instanceof i)) {
            aVar = new i(aVar);
        }
        androidx.appcompat.app.b bVar3 = this.j;
        if (bVar3 == null || this.O) {
            bVarJ = null;
        } else {
            try {
                bVarJ = bVar3.j(aVar);
            } catch (AbstractMethodError unused) {
                bVarJ = null;
            }
        }
        if (bVarJ != null) {
            this.q = bVarJ;
        } else {
            if (this.r == null) {
                if (this.F) {
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = this.g.getTheme();
                    theme.resolveAttribute(R$attr.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Resources.Theme themeNewTheme = this.g.getResources().newTheme();
                        themeNewTheme.setTo(theme);
                        themeNewTheme.applyStyle(typedValue.resourceId, true);
                        dVar = new androidx.appcompat.c.d(this.g, 0);
                        dVar.getTheme().setTo(themeNewTheme);
                    } else {
                        dVar = this.g;
                    }
                    this.r = new ActionBarContextView(dVar);
                    PopupWindow popupWindow = new PopupWindow(dVar, (AttributeSet) null, R$attr.actionModePopupWindowStyle);
                    this.s = popupWindow;
                    androidx.core.widget.h.b(popupWindow, 2);
                    this.s.setContentView(this.r);
                    this.s.setWidth(-1);
                    dVar.getTheme().resolveAttribute(R$attr.actionBarSize, typedValue, true);
                    this.r.f(TypedValue.complexToDimensionPixelSize(typedValue.data, dVar.getResources().getDisplayMetrics()));
                    this.s.setHeight(-2);
                    this.t = new f();
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.x.findViewById(R$id.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        viewStubCompat.b(LayoutInflater.from(a0()));
                        this.r = (ActionBarContextView) viewStubCompat.a();
                    }
                }
            }
            if (this.r != null) {
                V();
                this.r.n();
                androidx.appcompat.c.e eVar = new androidx.appcompat.c.e(this.r.getContext(), this.r, aVar, this.s == null);
                if (aVar.c(eVar, eVar.e())) {
                    eVar.k();
                    this.r.k(eVar);
                    this.q = eVar;
                    if (D0()) {
                        this.r.setAlpha(0.0f);
                        v vVarB = ViewCompat.b(this.r);
                        vVarB.a(1.0f);
                        this.u = vVarB;
                        vVarB.f(new g());
                    } else {
                        this.r.setAlpha(1.0f);
                        this.r.setVisibility(0);
                        this.r.sendAccessibilityEvent(32);
                        if (this.r.getParent() instanceof View) {
                            ViewCompat.J((View) this.r.getParent());
                        }
                    }
                    if (this.s != null) {
                        this.h.getDecorView().post(this.t);
                    }
                } else {
                    this.q = null;
                }
            }
        }
        androidx.appcompat.c.b bVar4 = this.q;
        if (bVar4 != null && (bVar = this.j) != null) {
            bVar.g(bVar4);
        }
        return this.q;
    }

    void K(int i2, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i2 >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.I;
                if (i2 < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i2];
                }
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.o) && !this.O) {
            this.i.a().onPanelClosed(i2, menu);
        }
    }

    void L(@NonNull androidx.appcompat.view.menu.f fVar) {
        if (this.H) {
            return;
        }
        this.H = true;
        this.n.n();
        Window.Callback callbackF0 = f0();
        if (callbackF0 != null && !this.O) {
            callbackF0.onPanelClosed(108, fVar);
        }
        this.H = false;
    }

    final int L0(@Nullable z zVar, @Nullable Rect rect) {
        boolean z;
        boolean z2;
        int iG = zVar != null ? zVar.g() : rect != null ? rect.top : 0;
        ActionBarContextView actionBarContextView = this.r;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.r.getLayoutParams();
            if (this.r.isShown()) {
                if (this.Z == null) {
                    this.Z = new Rect();
                    this.a0 = new Rect();
                }
                Rect rect2 = this.Z;
                Rect rect3 = this.a0;
                if (zVar == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(zVar.e(), zVar.g(), zVar.f(), zVar.d());
                }
                a0.a(this.x, rect2, rect3);
                int i2 = rect2.top;
                int i3 = rect2.left;
                int i4 = rect2.right;
                z zVarT = ViewCompat.t(this.x);
                int iE = zVarT == null ? 0 : zVarT.e();
                int iF = zVarT == null ? 0 : zVarT.f();
                if (marginLayoutParams.topMargin == i2 && marginLayoutParams.leftMargin == i3 && marginLayoutParams.rightMargin == i4) {
                    z2 = false;
                } else {
                    marginLayoutParams.topMargin = i2;
                    marginLayoutParams.leftMargin = i3;
                    marginLayoutParams.rightMargin = i4;
                    z2 = true;
                }
                if (i2 <= 0 || this.z != null) {
                    View view = this.z;
                    if (view != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                        if (marginLayoutParams2.height != marginLayoutParams.topMargin || marginLayoutParams2.leftMargin != iE || marginLayoutParams2.rightMargin != iF) {
                            marginLayoutParams2.height = marginLayoutParams.topMargin;
                            marginLayoutParams2.leftMargin = iE;
                            marginLayoutParams2.rightMargin = iF;
                            this.z.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view2 = new View(this.g);
                    this.z = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = iE;
                    layoutParams.rightMargin = iF;
                    this.x.addView(this.z, -1, layoutParams);
                }
                z = this.z != null;
                if (z && this.z.getVisibility() != 0) {
                    M0(this.z);
                }
                if (!this.E && z) {
                    iG = 0;
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
                this.r.setLayoutParams(marginLayoutParams);
            }
        }
        View view3 = this.z;
        if (view3 != null) {
            view3.setVisibility(z ? 0 : 8);
        }
        return iG;
    }

    void N(int i2) {
        O(d0(i2, true), true);
    }

    void O(PanelFeatureState panelFeatureState, boolean z) {
        ViewGroup viewGroup;
        A a2;
        if (z && panelFeatureState.f119a == 0 && (a2 = this.n) != null && a2.d()) {
            L(panelFeatureState.j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.g.getSystemService("window");
        if (windowManager != null && panelFeatureState.o && (viewGroup = panelFeatureState.g) != null) {
            windowManager.removeView(viewGroup);
            if (z) {
                K(panelFeatureState.f119a, panelFeatureState, null);
            }
        }
        panelFeatureState.m = false;
        panelFeatureState.n = false;
        panelFeatureState.o = false;
        panelFeatureState.h = null;
        panelFeatureState.q = true;
        if (this.J == panelFeatureState) {
            this.J = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View R(View view, String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        boolean zE0 = false;
        if (this.b0 == null) {
            String string = this.g.obtainStyledAttributes(R$styleable.AppCompatTheme).getString(R$styleable.AppCompatTheme_viewInflaterClass);
            if (string == null) {
                this.b0 = new androidx.appcompat.app.d();
            } else {
                try {
                    this.b0 = (androidx.appcompat.app.d) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.b0 = new androidx.appcompat.app.d();
                }
            }
        }
        if (d0) {
            if (!(attributeSet instanceof XmlPullParser)) {
                zE0 = E0((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                zE0 = true;
            }
        }
        return this.b0.q(view, str, context, attributeSet, zE0, d0, true, Z.b());
    }

    void S() {
        androidx.appcompat.view.menu.f fVar;
        A a2 = this.n;
        if (a2 != null) {
            a2.n();
        }
        if (this.s != null) {
            this.h.getDecorView().removeCallbacks(this.t);
            if (this.s.isShowing()) {
                try {
                    this.s.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.s = null;
        }
        V();
        PanelFeatureState panelFeatureStateD0 = d0(0, false);
        if (panelFeatureStateD0 == null || (fVar = panelFeatureStateD0.j) == null) {
            return;
        }
        fVar.close();
    }

    boolean T(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.f;
        if (((obj instanceof e.a) || (obj instanceof androidx.appcompat.app.c)) && (decorView = this.h.getDecorView()) != null && androidx.core.view.e.d(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.i.a().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? p0(keyCode, keyEvent) : s0(keyCode, keyEvent);
    }

    void U(int i2) {
        PanelFeatureState panelFeatureStateD0;
        PanelFeatureState panelFeatureStateD02 = d0(i2, true);
        if (panelFeatureStateD02.j != null) {
            Bundle bundle = new Bundle();
            panelFeatureStateD02.j.Q(bundle);
            if (bundle.size() > 0) {
                panelFeatureStateD02.s = bundle;
            }
            panelFeatureStateD02.j.d0();
            panelFeatureStateD02.j.clear();
        }
        panelFeatureStateD02.r = true;
        panelFeatureStateD02.q = true;
        if ((i2 != 108 && i2 != 0) || this.n == null || (panelFeatureStateD0 = d0(0, false)) == null) {
            return;
        }
        panelFeatureStateD0.m = false;
        A0(panelFeatureStateD0, null);
    }

    void V() {
        v vVar = this.u;
        if (vVar != null) {
            vVar.b();
        }
    }

    PanelFeatureState Y(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.I;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
            if (panelFeatureState != null && panelFeatureState.j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    @Override // androidx.appcompat.view.menu.f.a
    public boolean a(@NonNull androidx.appcompat.view.menu.f fVar, @NonNull MenuItem menuItem) {
        PanelFeatureState panelFeatureStateY;
        Window.Callback callbackF0 = f0();
        if (callbackF0 == null || this.O || (panelFeatureStateY = Y(fVar.D())) == null) {
            return false;
        }
        return callbackF0.onMenuItemSelected(panelFeatureStateY.f119a, menuItem);
    }

    final Context a0() {
        ActionBar actionBarM = m();
        Context contextK = actionBarM != null ? actionBarM.k() : null;
        return contextK == null ? this.g : contextK;
    }

    @Override // androidx.appcompat.view.menu.f.a
    public void b(@NonNull androidx.appcompat.view.menu.f fVar) {
        B0(true);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void d(View view, ViewGroup.LayoutParams layoutParams) {
        W();
        ((ViewGroup) this.x.findViewById(R.id.content)).addView(view, layoutParams);
        this.i.a().onContentChanged();
    }

    protected PanelFeatureState d0(int i2, boolean z) {
        PanelFeatureState[] panelFeatureStateArr = this.I;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i2) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[i2 + 1];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.I = panelFeatureStateArr2;
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

    final CharSequence e0() {
        Object obj = this.f;
        return obj instanceof Activity ? ((Activity) obj).getTitle() : this.m;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    @NonNull
    @CallSuper
    public Context f(@NonNull Context context) {
        this.L = true;
        int iN0 = n0(context, J());
        if (g0 && (context instanceof ContextThemeWrapper)) {
            try {
                p.a((ContextThemeWrapper) context, P(context, iN0, null));
                return context;
            } catch (IllegalStateException unused) {
            }
        }
        if (context instanceof androidx.appcompat.c.d) {
            try {
                ((androidx.appcompat.c.d) context).a(P(context, iN0, null));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (!f0) {
            super.f(context);
            return context;
        }
        try {
            Configuration configuration = context.getPackageManager().getResourcesForApplication(context.getApplicationInfo()).getConfiguration();
            Configuration configuration2 = context.getResources().getConfiguration();
            Configuration configurationP = P(context, iN0, configuration.equals(configuration2) ? null : Z(configuration, configuration2));
            androidx.appcompat.c.d dVar = new androidx.appcompat.c.d(context, R$style.Theme_AppCompat_Empty);
            dVar.a(configurationP);
            boolean z = false;
            try {
                z = context.getTheme() != null;
            } catch (NullPointerException unused3) {
            }
            if (z) {
                e.c.a(dVar.getTheme());
            }
            super.f(dVar);
            return dVar;
        } catch (PackageManager.NameNotFoundException e2) {
            throw new RuntimeException("Application failed to obtain resources from itself", e2);
        }
    }

    final Window.Callback f0() {
        return this.h.getCallback();
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @VisibleForTesting
    final AutoNightModeManager getAutoTimeNightModeManager() {
        return c0(this.g);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    @Nullable
    public <T extends View> T i(@IdRes int i2) {
        W();
        return (T) this.h.findViewById(i2);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public int k() {
        return this.P;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public MenuInflater l() {
        if (this.l == null) {
            g0();
            ActionBar actionBar = this.k;
            this.l = new androidx.appcompat.c.g(actionBar != null ? actionBar.k() : this.g);
        }
        return this.l;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public ActionBar m() {
        g0();
        return this.k;
    }

    public boolean m0() {
        return this.v;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void n() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.g);
        if (layoutInflaterFrom.getFactory() == null) {
            androidx.core.view.f.b(layoutInflaterFrom, this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof AppCompatDelegateImpl) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    int n0(@NonNull Context context, int i2) {
        if (i2 == -100) {
            return -1;
        }
        if (i2 != -1) {
            if (i2 == 0) {
                if (Build.VERSION.SDK_INT < 23 || ((UiModeManager) context.getApplicationContext().getSystemService(UiModeManager.class)).getNightMode() != 0) {
                    return c0(context).c();
                }
                return -1;
            }
            if (i2 != 1 && i2 != 2) {
                if (i2 == 3) {
                    return b0(context).c();
                }
                throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
            }
        }
        return i2;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void o() {
        ActionBar actionBarM = m();
        if (actionBarM == null || !actionBarM.l()) {
            k0(0);
        }
    }

    boolean o0() {
        androidx.appcompat.c.b bVar = this.q;
        if (bVar != null) {
            bVar.c();
            return true;
        }
        ActionBar actionBarM = m();
        return actionBarM != null && actionBarM.h();
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return R(view, str, context, attributeSet);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void p(Configuration configuration) {
        ActionBar actionBarM;
        if (this.C && this.w && (actionBarM = m()) != null) {
            actionBarM.m(configuration);
        }
        C0063i.b().g(this.g);
        G(false);
    }

    boolean p0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            this.K = (keyEvent.getFlags() & 128) != 0;
        } else if (i2 == 82) {
            q0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void q(Bundle bundle) {
        this.L = true;
        G(false);
        X();
        Object obj = this.f;
        if (obj instanceof Activity) {
            String strC = null;
            try {
                strC = androidx.core.app.f.c((Activity) obj);
            } catch (IllegalArgumentException unused) {
            }
            if (strC != null) {
                ActionBar actionBarY0 = y0();
                if (actionBarY0 == null) {
                    this.Y = true;
                } else {
                    actionBarY0.r(true);
                }
            }
            AppCompatDelegate.c(this);
        }
        this.M = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0048  */
    @Override // androidx.appcompat.app.AppCompatDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void r() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L9
            androidx.appcompat.app.AppCompatDelegate.x(r3)
        L9:
            boolean r0 = r3.V
            if (r0 == 0) goto L18
            android.view.Window r0 = r3.h
            android.view.View r0 = r0.getDecorView()
            java.lang.Runnable r1 = r3.X
            r0.removeCallbacks(r1)
        L18:
            r0 = 0
            r3.N = r0
            r0 = 1
            r3.O = r0
            int r0 = r3.P
            r1 = -100
            if (r0 == r1) goto L48
            java.lang.Object r0 = r3.f
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L48
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L48
            b.a.g<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.c0
            java.lang.Object r1 = r3.f
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.P
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L57
        L48:
            b.a.g<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.c0
            java.lang.Object r1 = r3.f
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L57:
            androidx.appcompat.app.ActionBar r0 = r3.k
            if (r0 == 0) goto L5e
            r0.n()
        L5e:
            r3.M()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.r():void");
    }

    boolean r0(int i2, KeyEvent keyEvent) {
        ActionBar actionBarM = m();
        if (actionBarM != null && actionBarM.o(i2, keyEvent)) {
            return true;
        }
        PanelFeatureState panelFeatureState = this.J;
        if (panelFeatureState != null && z0(panelFeatureState, keyEvent.getKeyCode(), keyEvent, 1)) {
            PanelFeatureState panelFeatureState2 = this.J;
            if (panelFeatureState2 != null) {
                panelFeatureState2.n = true;
            }
            return true;
        }
        if (this.J == null) {
            PanelFeatureState panelFeatureStateD0 = d0(0, true);
            A0(panelFeatureStateD0, keyEvent);
            boolean zZ0 = z0(panelFeatureStateD0, keyEvent.getKeyCode(), keyEvent, 1);
            panelFeatureStateD0.m = false;
            if (zZ0) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void s(Bundle bundle) {
        W();
    }

    boolean s0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            boolean z = this.K;
            this.K = false;
            PanelFeatureState panelFeatureStateD0 = d0(0, false);
            if (panelFeatureStateD0 != null && panelFeatureStateD0.o) {
                if (!z) {
                    O(panelFeatureStateD0, true);
                }
                return true;
            }
            if (o0()) {
                return true;
            }
        } else if (i2 == 82) {
            t0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void t() {
        ActionBar actionBarM = m();
        if (actionBarM != null) {
            actionBarM.s(true);
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void u(Bundle bundle) {
    }

    void u0(int i2) {
        ActionBar actionBarM;
        if (i2 != 108 || (actionBarM = m()) == null) {
            return;
        }
        actionBarM.i(true);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void v() {
        this.N = true;
        F();
    }

    void v0(int i2) {
        if (i2 == 108) {
            ActionBar actionBarM = m();
            if (actionBarM != null) {
                actionBarM.i(false);
                return;
            }
            return;
        }
        if (i2 == 0) {
            PanelFeatureState panelFeatureStateD0 = d0(i2, true);
            if (panelFeatureStateD0.o) {
                O(panelFeatureStateD0, false);
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void w() {
        this.N = false;
        ActionBar actionBarM = m();
        if (actionBarM != null) {
            actionBarM.s(false);
        }
    }

    void w0(ViewGroup viewGroup) {
    }

    final ActionBar y0() {
        return this.k;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public boolean z(int i2) {
        int iC0 = C0(i2);
        if (this.G && iC0 == 108) {
            return false;
        }
        if (this.C && iC0 == 1) {
            this.C = false;
        }
        if (iC0 == 1) {
            H0();
            this.G = true;
            return true;
        }
        if (iC0 == 2) {
            H0();
            this.A = true;
            return true;
        }
        if (iC0 == 5) {
            H0();
            this.B = true;
            return true;
        }
        if (iC0 == 10) {
            H0();
            this.E = true;
            return true;
        }
        if (iC0 == 108) {
            H0();
            this.C = true;
            return true;
        }
        if (iC0 != 109) {
            return this.h.requestFeature(iC0);
        }
        H0();
        this.D = true;
        return true;
    }

    AppCompatDelegateImpl(Dialog dialog, androidx.appcompat.app.b bVar) {
        this(dialog.getContext(), dialog.getWindow(), bVar, dialog);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    private AppCompatDelegateImpl(Context context, Window window, androidx.appcompat.app.b bVar, Object obj) {
        Integer num;
        AppCompatActivity appCompatActivityI0;
        this.u = null;
        this.v = true;
        this.P = -100;
        this.X = new b();
        this.g = context;
        this.j = bVar;
        this.f = obj;
        if (this.P == -100 && (obj instanceof Dialog) && (appCompatActivityI0 = I0()) != null) {
            this.P = appCompatActivityI0.v().k();
        }
        if (this.P == -100 && (num = c0.get(this.f.getClass().getName())) != null) {
            this.P = num.intValue();
            c0.remove(this.f.getClass().getName());
        }
        if (window != null) {
            I(window);
        }
        C0063i.h();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class j extends androidx.appcompat.c.i {
        j(Window.Callback callback) {
            super(callback);
        }

        final ActionMode b(ActionMode.Callback callback) {
            f.a aVar = new f.a(AppCompatDelegateImpl.this.g, callback);
            androidx.appcompat.c.b bVarF0 = AppCompatDelegateImpl.this.F0(aVar);
            if (bVarF0 != null) {
                return aVar.e(bVarF0);
            }
            return null;
        }

        @Override // androidx.appcompat.c.i, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.T(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // androidx.appcompat.c.i, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.r0(keyEvent.getKeyCode(), keyEvent);
        }

        @Override // android.view.Window.Callback
        public void onContentChanged() {
        }

        @Override // androidx.appcompat.c.i, android.view.Window.Callback
        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof androidx.appcompat.view.menu.f)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        @Override // androidx.appcompat.c.i, android.view.Window.Callback
        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            AppCompatDelegateImpl.this.u0(i);
            return true;
        }

        @Override // androidx.appcompat.c.i, android.view.Window.Callback
        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            AppCompatDelegateImpl.this.v0(i);
        }

        @Override // androidx.appcompat.c.i, android.view.Window.Callback
        public boolean onPreparePanel(int i, View view, Menu menu) {
            androidx.appcompat.view.menu.f fVar = menu instanceof androidx.appcompat.view.menu.f ? (androidx.appcompat.view.menu.f) menu : null;
            if (i == 0 && fVar == null) {
                return false;
            }
            if (fVar != null) {
                fVar.a0(true);
            }
            boolean zOnPreparePanel = super.onPreparePanel(i, view, menu);
            if (fVar != null) {
                fVar.a0(false);
            }
            return zOnPreparePanel;
        }

        @Override // androidx.appcompat.c.i, android.view.Window.Callback
        @RequiresApi(24)
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            androidx.appcompat.view.menu.f fVar;
            PanelFeatureState panelFeatureStateD0 = AppCompatDelegateImpl.this.d0(0, true);
            if (panelFeatureStateD0 == null || (fVar = panelFeatureStateD0.j) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i);
            } else {
                super.onProvideKeyboardShortcuts(list, fVar, i);
            }
        }

        @Override // androidx.appcompat.c.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            return AppCompatDelegateImpl.this.m0() ? b(callback) : super.onWindowStartingActionMode(callback);
        }

        @Override // androidx.appcompat.c.i, android.view.Window.Callback
        @RequiresApi(23)
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (AppCompatDelegateImpl.this.m0() && i == 0) {
                return b(callback);
            }
            return super.onWindowStartingActionMode(callback, i);
        }
    }
}
