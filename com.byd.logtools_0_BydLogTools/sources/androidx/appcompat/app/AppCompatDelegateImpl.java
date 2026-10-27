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
import androidx.appcompat.widget.e0;
import androidx.appcompat.widget.j0;
import androidx.appcompat.widget.k0;
import androidx.appcompat.widget.s;
import androidx.core.content.c.f;
import androidx.lifecycle.Lifecycle;
import b.a.o.b;
import b.a.o.f;
import b.g.k.b0;
import b.g.k.e;
import b.g.k.x;
import b.g.k.z;
import com.bydux.api.common.views.skin.entity.AttrFactory;
import java.lang.Thread;
import java.util.List;
import okio.Segment;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
class AppCompatDelegateImpl extends androidx.appcompat.app.e implements g.a, LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final b.d.g<String, Integer> f126e = new b.d.g<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final boolean f127f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final int[] f128g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final boolean f129h;
    private static final boolean i;
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
    private m Y;
    private m Z;
    boolean a0;
    int b0;
    private final Runnable c0;
    private boolean d0;
    private Rect e0;
    private Rect f0;
    private androidx.appcompat.app.g g0;
    final Object k;
    final Context l;
    Window m;
    private k n;
    final androidx.appcompat.app.d o;
    androidx.appcompat.app.a p;
    MenuInflater q;
    private CharSequence r;
    private androidx.appcompat.widget.o s;
    private i t;
    private t u;
    b.a.o.b v;
    ActionBarContextView w;
    PopupWindow x;
    Runnable y;
    x z;

    protected static final class PanelFeatureState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f130a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f131b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f132c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f133d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f134e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f135f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        ViewGroup f136g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        View f137h;
        View i;
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

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            int f138b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            boolean f139c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            Bundle f140d;

            class a implements Parcelable.ClassLoaderCreator<SavedState> {
                a() {
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public SavedState createFromParcel(Parcel parcel) {
                    return SavedState.e(parcel, null);
                }

                @Override // android.os.Parcelable.ClassLoaderCreator
                /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
                public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.e(parcel, classLoader);
                }

                @Override // android.os.Parcelable.Creator
                /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
                public SavedState[] newArray(int i) {
                    return new SavedState[i];
                }
            }

            SavedState() {
            }

            static SavedState e(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.f138b = parcel.readInt();
                boolean z = parcel.readInt() == 1;
                savedState.f139c = z;
                if (z) {
                    savedState.f140d = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.f138b);
                parcel.writeInt(this.f139c ? 1 : 0);
                if (this.f139c) {
                    parcel.writeBundle(this.f140d);
                }
            }
        }

        PanelFeatureState(int i) {
            this.f130a = i;
        }

        androidx.appcompat.view.menu.n a(m.a aVar) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                androidx.appcompat.view.menu.e eVar = new androidx.appcompat.view.menu.e(this.l, b.a.g.l);
                this.k = eVar;
                eVar.k(aVar);
                this.j.b(this.k);
            }
            return this.k.g(this.f136g);
        }

        public boolean b() {
            if (this.f137h == null) {
                return false;
            }
            return this.i != null || this.k.b().getCount() > 0;
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
            themeNewTheme.resolveAttribute(b.a.a.f2058a, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                themeNewTheme.applyStyle(i, true);
            }
            themeNewTheme.resolveAttribute(b.a.a.G, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                themeNewTheme.applyStyle(i2, true);
            } else {
                themeNewTheme.applyStyle(b.a.i.f2117c, true);
            }
            b.a.o.d dVar = new b.a.o.d(context, 0);
            dVar.getTheme().setTo(themeNewTheme);
            this.l = dVar;
            TypedArray typedArrayObtainStyledAttributes = dVar.obtainStyledAttributes(b.a.j.B0);
            this.f131b = typedArrayObtainStyledAttributes.getResourceId(b.a.j.E0, 0);
            this.f135f = typedArrayObtainStyledAttributes.getResourceId(b.a.j.D0, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    class a implements Thread.UncaughtExceptionHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Thread.UncaughtExceptionHandler f141a;

        a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.f141a = uncaughtExceptionHandler;
        }

        private boolean a(Throwable th) {
            String message;
            if (!(th instanceof Resources.NotFoundException) || (message = th.getMessage()) == null) {
                return false;
            }
            return message.contains(AttrFactory.DRAWABLE) || message.contains("Drawable");
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            if (!a(th)) {
                this.f141a.uncaughtException(thread, th);
                return;
            }
            Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            notFoundException.initCause(th.getCause());
            notFoundException.setStackTrace(th.getStackTrace());
            this.f141a.uncaughtException(thread, notFoundException);
        }
    }

    class b implements Runnable {
        b() {
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

    class c implements b.g.k.p {
        c() {
        }

        @Override // b.g.k.p
        public b0 a(View view, b0 b0Var) {
            int iH = b0Var.h();
            int iN0 = AppCompatDelegateImpl.this.N0(b0Var, null);
            if (iH != iN0) {
                b0Var = b0Var.n(b0Var.f(), iN0, b0Var.g(), b0Var.e());
            }
            return b.g.k.t.W(view, b0Var);
        }
    }

    class d implements s.a {
        d() {
        }

        @Override // androidx.appcompat.widget.s.a
        public void a(Rect rect) {
            rect.top = AppCompatDelegateImpl.this.N0(null, rect);
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
            AppCompatDelegateImpl.this.V();
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
            AppCompatDelegateImpl.this.Y();
            if (!AppCompatDelegateImpl.this.G0()) {
                AppCompatDelegateImpl.this.w.setAlpha(1.0f);
                AppCompatDelegateImpl.this.w.setVisibility(0);
            } else {
                AppCompatDelegateImpl.this.w.setAlpha(0.0f);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.z = b.g.k.t.c(appCompatDelegateImpl2.w).a(1.0f);
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
                b.g.k.t.g0((View) AppCompatDelegateImpl.this.w.getParent());
            }
        }
    }

    private class h implements androidx.appcompat.app.b {
        h() {
        }
    }

    private final class i implements m.a {
        i() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
            AppCompatDelegateImpl.this.O(gVar);
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean b(androidx.appcompat.view.menu.g gVar) {
            Window.Callback callbackI0 = AppCompatDelegateImpl.this.i0();
            if (callbackI0 == null) {
                return true;
            }
            callbackI0.onMenuOpened(108, gVar);
            return true;
        }
    }

    class j implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private b.a f151a;

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
                    b.g.k.t.g0((View) AppCompatDelegateImpl.this.w.getParent());
                }
                AppCompatDelegateImpl.this.w.removeAllViews();
                AppCompatDelegateImpl.this.z.f(null);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.z = null;
                b.g.k.t.g0(appCompatDelegateImpl2.C);
            }
        }

        public j(b.a aVar) {
            this.f151a = aVar;
        }

        @Override // b.a.o.b.a
        public boolean a(b.a.o.b bVar, Menu menu) {
            b.g.k.t.g0(AppCompatDelegateImpl.this.C);
            return this.f151a.a(bVar, menu);
        }

        @Override // b.a.o.b.a
        public boolean b(b.a.o.b bVar, MenuItem menuItem) {
            return this.f151a.b(bVar, menuItem);
        }

        @Override // b.a.o.b.a
        public boolean c(b.a.o.b bVar, Menu menu) {
            return this.f151a.c(bVar, menu);
        }

        @Override // b.a.o.b.a
        public void d(b.a.o.b bVar) {
            this.f151a.d(bVar);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.x != null) {
                appCompatDelegateImpl.m.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.y);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.w != null) {
                appCompatDelegateImpl2.Y();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl3.z = b.g.k.t.c(appCompatDelegateImpl3.w).a(0.0f);
                AppCompatDelegateImpl.this.z.f(new a());
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            androidx.appcompat.app.d dVar = appCompatDelegateImpl4.o;
            if (dVar != null) {
                dVar.onSupportActionModeFinished(appCompatDelegateImpl4.v);
            }
            AppCompatDelegateImpl appCompatDelegateImpl5 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl5.v = null;
            b.g.k.t.g0(appCompatDelegateImpl5.C);
        }
    }

    private class l extends m {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final PowerManager f155c;

        l(Context context) {
            super();
            this.f155c = (PowerManager) context.getApplicationContext().getSystemService("power");
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        IntentFilter b() {
            if (Build.VERSION.SDK_INT < 21) {
                return null;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public int c() {
            return (Build.VERSION.SDK_INT < 21 || !this.f155c.isPowerSaveMode()) ? 1 : 2;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public void d() {
            AppCompatDelegateImpl.this.I();
        }
    }

    abstract class m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private BroadcastReceiver f157a;

        class a extends BroadcastReceiver {
            a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                m.this.d();
            }
        }

        m() {
        }

        void a() {
            BroadcastReceiver broadcastReceiver = this.f157a;
            if (broadcastReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.l.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.f157a = null;
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
            if (this.f157a == null) {
                this.f157a = new a();
            }
            AppCompatDelegateImpl.this.l.registerReceiver(this.f157a, intentFilterB);
        }
    }

    private class n extends m {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final androidx.appcompat.app.k f160c;

        n(androidx.appcompat.app.k kVar) {
            super();
            this.f160c = kVar;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public int c() {
            return this.f160c.d() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public void d() {
            AppCompatDelegateImpl.this.I();
        }
    }

    static class o {
        static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i = configuration.densityDpi;
            int i2 = configuration2.densityDpi;
            if (i != i2) {
                configuration3.densityDpi = i2;
            }
        }
    }

    static class p {
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

    static class q {
        static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
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

    private static class r {
        static void a(ContextThemeWrapper contextThemeWrapper, Configuration configuration) {
            contextThemeWrapper.applyOverrideConfiguration(configuration);
        }
    }

    private class s extends ContentFrameLayout {
        public s(Context context) {
            super(context);
        }

        private boolean c(int i, int i2) {
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
            setBackgroundDrawable(b.a.k.a.a.d(getContext(), i));
        }
    }

    private final class t implements m.a {
        t() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
            androidx.appcompat.view.menu.g gVarF = gVar.F();
            boolean z2 = gVarF != gVar;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z2) {
                gVar = gVarF;
            }
            PanelFeatureState panelFeatureStateB0 = appCompatDelegateImpl.b0(gVar);
            if (panelFeatureStateB0 != null) {
                if (!z2) {
                    AppCompatDelegateImpl.this.R(panelFeatureStateB0, z);
                } else {
                    AppCompatDelegateImpl.this.N(panelFeatureStateB0.f130a, panelFeatureStateB0, gVarF);
                    AppCompatDelegateImpl.this.R(panelFeatureStateB0, true);
                }
            }
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean b(androidx.appcompat.view.menu.g gVar) {
            Window.Callback callbackI0;
            if (gVar != gVar.F()) {
                return true;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (!appCompatDelegateImpl.H || (callbackI0 = appCompatDelegateImpl.i0()) == null || AppCompatDelegateImpl.this.T) {
                return true;
            }
            callbackI0.onMenuOpened(108, gVar);
            return true;
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        boolean z = i2 < 21;
        f127f = z;
        f128g = new int[]{R.attr.windowBackground};
        f129h = !"robolectric".equals(Build.FINGERPRINT);
        i = i2 >= 17;
        if (!z || j) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new a(Thread.getDefaultUncaughtExceptionHandler()));
        j = true;
    }

    AppCompatDelegateImpl(Activity activity, androidx.appcompat.app.d dVar) {
        this(activity, null, dVar, activity);
    }

    private void A0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        int i2;
        ViewGroup.LayoutParams layoutParams;
        if (panelFeatureState.o || this.T) {
            return;
        }
        if (panelFeatureState.f130a == 0) {
            if ((this.l.getResources().getConfiguration().screenLayout & 15) == 4) {
                return;
            }
        }
        Window.Callback callbackI0 = i0();
        if (callbackI0 != null && !callbackI0.onMenuOpened(panelFeatureState.f130a, panelFeatureState.j)) {
            R(panelFeatureState, true);
            return;
        }
        WindowManager windowManager = (WindowManager) this.l.getSystemService("window");
        if (windowManager != null && D0(panelFeatureState, keyEvent)) {
            ViewGroup viewGroup = panelFeatureState.f136g;
            if (viewGroup != null && !panelFeatureState.q) {
                View view = panelFeatureState.i;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i2 = -1;
                }
                panelFeatureState.n = false;
                WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i2, -2, panelFeatureState.f133d, panelFeatureState.f134e, 1002, 8519680, -3);
                layoutParams2.gravity = panelFeatureState.f132c;
                layoutParams2.windowAnimations = panelFeatureState.f135f;
                windowManager.addView(panelFeatureState.f136g, layoutParams2);
                panelFeatureState.o = true;
            }
            if (viewGroup == null) {
                if (!l0(panelFeatureState) || panelFeatureState.f136g == null) {
                    return;
                }
            } else if (panelFeatureState.q && viewGroup.getChildCount() > 0) {
                panelFeatureState.f136g.removeAllViews();
            }
            if (!k0(panelFeatureState) || !panelFeatureState.b()) {
                panelFeatureState.q = true;
                return;
            }
            ViewGroup.LayoutParams layoutParams3 = panelFeatureState.f137h.getLayoutParams();
            if (layoutParams3 == null) {
                layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
            }
            panelFeatureState.f136g.setBackgroundResource(panelFeatureState.f131b);
            ViewParent parent = panelFeatureState.f137h.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(panelFeatureState.f137h);
            }
            panelFeatureState.f136g.addView(panelFeatureState.f137h, layoutParams3);
            if (!panelFeatureState.f137h.hasFocus()) {
                panelFeatureState.f137h.requestFocus();
            }
            i2 = -2;
            panelFeatureState.n = false;
            WindowManager.LayoutParams layoutParams22 = new WindowManager.LayoutParams(i2, -2, panelFeatureState.f133d, panelFeatureState.f134e, 1002, 8519680, -3);
            layoutParams22.gravity = panelFeatureState.f132c;
            layoutParams22.windowAnimations = panelFeatureState.f135f;
            windowManager.addView(panelFeatureState.f136g, layoutParams22);
            panelFeatureState.o = true;
        }
    }

    private boolean C0(PanelFeatureState panelFeatureState, int i2, KeyEvent keyEvent, int i3) {
        androidx.appcompat.view.menu.g gVar;
        boolean zPerformShortcut = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.m || D0(panelFeatureState, keyEvent)) && (gVar = panelFeatureState.j) != null) {
            zPerformShortcut = gVar.performShortcut(i2, keyEvent, i3);
        }
        if (zPerformShortcut && (i3 & 1) == 0 && this.s == null) {
            R(panelFeatureState, true);
        }
        return zPerformShortcut;
    }

    private boolean D0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
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
            R(panelFeatureState2, false);
        }
        Window.Callback callbackI0 = i0();
        if (callbackI0 != null) {
            panelFeatureState.i = callbackI0.onCreatePanelView(panelFeatureState.f130a);
        }
        int i2 = panelFeatureState.f130a;
        boolean z = i2 == 0 || i2 == 108;
        if (z && (oVar3 = this.s) != null) {
            oVar3.f();
        }
        if (panelFeatureState.i == null && (!z || !(B0() instanceof androidx.appcompat.app.i))) {
            androidx.appcompat.view.menu.g gVar = panelFeatureState.j;
            if (gVar == null || panelFeatureState.r) {
                if (gVar == null && (!m0(panelFeatureState) || panelFeatureState.j == null)) {
                    return false;
                }
                if (z && this.s != null) {
                    if (this.t == null) {
                        this.t = new i();
                    }
                    this.s.a(panelFeatureState.j, this.t);
                }
                panelFeatureState.j.h0();
                if (!callbackI0.onCreatePanelMenu(panelFeatureState.f130a, panelFeatureState.j)) {
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
            if (!callbackI0.onPreparePanel(0, panelFeatureState.i, panelFeatureState.j)) {
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

    private void E0(boolean z) {
        androidx.appcompat.widget.o oVar = this.s;
        if (oVar == null || !oVar.g() || (ViewConfiguration.get(this.l).hasPermanentMenuKey() && !this.s.b())) {
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
        androidx.appcompat.view.menu.g gVar = panelFeatureStateG02.j;
        if (gVar == null || panelFeatureStateG02.r || !callbackI0.onPreparePanel(0, panelFeatureStateG02.i, gVar)) {
            return;
        }
        callbackI0.onMenuOpened(108, panelFeatureStateG02.j);
        this.s.e();
    }

    private int F0(int i2) {
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

    private boolean H0(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.m.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || b.g.k.t.O((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    private boolean J(boolean z) {
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

    private void J0() {
        if (this.B) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private void K() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.C.findViewById(R.id.content);
        View decorView = this.m.getDecorView();
        contentFrameLayout.b(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray typedArrayObtainStyledAttributes = this.l.obtainStyledAttributes(b.a.j.B0);
        typedArrayObtainStyledAttributes.getValue(b.a.j.N0, contentFrameLayout.getMinWidthMajor());
        typedArrayObtainStyledAttributes.getValue(b.a.j.O0, contentFrameLayout.getMinWidthMinor());
        int i2 = b.a.j.L0;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            typedArrayObtainStyledAttributes.getValue(i2, contentFrameLayout.getFixedWidthMajor());
        }
        int i3 = b.a.j.M0;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            typedArrayObtainStyledAttributes.getValue(i3, contentFrameLayout.getFixedWidthMinor());
        }
        int i4 = b.a.j.J0;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            typedArrayObtainStyledAttributes.getValue(i4, contentFrameLayout.getFixedHeightMajor());
        }
        int i5 = b.a.j.K0;
        if (typedArrayObtainStyledAttributes.hasValue(i5)) {
            typedArrayObtainStyledAttributes.getValue(i5, contentFrameLayout.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    private AppCompatActivity K0() {
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

    private void L(Window window) {
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
        e0 e0VarU = e0.u(this.l, null, f128g);
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
    private boolean L0(int r7, boolean r8) {
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
            boolean r8 = androidx.appcompat.app.AppCompatDelegateImpl.f129h
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
            androidx.core.app.a.l(r8)
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

    private int M() {
        int i2 = this.U;
        return i2 != -100 ? i2 : androidx.appcompat.app.e.j();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void M0(int i2, boolean z, Configuration configuration) {
        Resources resources = this.l.getResources();
        Configuration configuration2 = new Configuration(resources.getConfiguration());
        if (configuration != null) {
            configuration2.updateFrom(configuration);
        }
        configuration2.uiMode = i2 | (resources.getConfiguration().uiMode & (-49));
        resources.updateConfiguration(configuration2, null);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 26) {
            androidx.appcompat.app.h.a(resources);
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
                if (activity instanceof androidx.lifecycle.f) {
                    if (((androidx.lifecycle.f) activity).getLifecycle().b().isAtLeast(Lifecycle.State.STARTED)) {
                        activity.onConfigurationChanged(configuration2);
                    }
                } else if (this.S) {
                    activity.onConfigurationChanged(configuration2);
                }
            }
        }
    }

    private void O0(View view) {
        view.setBackgroundColor((b.g.k.t.I(view) & Segment.SIZE) != 0 ? androidx.core.content.a.b(this.l, b.a.c.f2068b) : androidx.core.content.a.b(this.l, b.a.c.f2067a));
    }

    private void P() {
        m mVar = this.Y;
        if (mVar != null) {
            mVar.a();
        }
        m mVar2 = this.Z;
        if (mVar2 != null) {
            mVar2.a();
        }
    }

    private Configuration S(Context context, int i2, Configuration configuration) {
        int i3 = i2 != 1 ? i2 != 2 ? context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i3 | (configuration2.uiMode & (-49));
        return configuration2;
    }

    private ViewGroup T() {
        ViewGroup viewGroup;
        TypedArray typedArrayObtainStyledAttributes = this.l.obtainStyledAttributes(b.a.j.B0);
        int i2 = b.a.j.G0;
        if (!typedArrayObtainStyledAttributes.hasValue(i2)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(b.a.j.P0, false)) {
            A(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(i2, false)) {
            A(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(b.a.j.H0, false)) {
            A(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(b.a.j.I0, false)) {
            A(10);
        }
        this.K = typedArrayObtainStyledAttributes.getBoolean(b.a.j.C0, false);
        typedArrayObtainStyledAttributes.recycle();
        a0();
        this.m.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.l);
        if (this.L) {
            viewGroup = this.J ? (ViewGroup) layoutInflaterFrom.inflate(b.a.g.q, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(b.a.g.p, (ViewGroup) null);
        } else if (this.K) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(b.a.g.f2106h, (ViewGroup) null);
            this.I = false;
            this.H = false;
        } else if (this.H) {
            TypedValue typedValue = new TypedValue();
            this.l.getTheme().resolveAttribute(b.a.a.f2064g, typedValue, true);
            int i3 = typedValue.resourceId;
            viewGroup = (ViewGroup) LayoutInflater.from(i3 != 0 ? new b.a.o.d(this.l, i3) : this.l).inflate(b.a.g.r, (ViewGroup) null);
            androidx.appcompat.widget.o oVar = (androidx.appcompat.widget.o) viewGroup.findViewById(b.a.f.q);
            this.s = oVar;
            oVar.setWindowCallback(i0());
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
            b.g.k.t.v0(viewGroup, new c());
        } else if (viewGroup instanceof androidx.appcompat.widget.s) {
            ((androidx.appcompat.widget.s) viewGroup).setOnFitSystemWindowsListener(new d());
        }
        if (this.s == null) {
            this.D = (TextView) viewGroup.findViewById(b.a.f.S);
        }
        k0.c(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(b.a.f.f2092b);
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

    private void Z() {
        if (this.B) {
            return;
        }
        this.C = T();
        CharSequence charSequenceH0 = h0();
        if (!TextUtils.isEmpty(charSequenceH0)) {
            androidx.appcompat.widget.o oVar = this.s;
            if (oVar != null) {
                oVar.setWindowTitle(charSequenceH0);
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

    private void a0() {
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

    private static Configuration c0(Configuration configuration, Configuration configuration2) {
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

    private m e0(Context context) {
        if (this.Z == null) {
            this.Z = new l(context);
        }
        return this.Z;
    }

    private m f0(Context context) {
        if (this.Y == null) {
            this.Y = new n(androidx.appcompat.app.k.a(context));
        }
        return this.Y;
    }

    private void j0() {
        Z();
        if (this.H && this.p == null) {
            Object obj = this.k;
            if (obj instanceof Activity) {
                this.p = new androidx.appcompat.app.l((Activity) this.k, this.I);
            } else if (obj instanceof Dialog) {
                this.p = new androidx.appcompat.app.l((Dialog) this.k);
            }
            androidx.appcompat.app.a aVar = this.p;
            if (aVar != null) {
                aVar.r(this.d0);
            }
        }
    }

    private boolean k0(PanelFeatureState panelFeatureState) {
        View view = panelFeatureState.i;
        if (view != null) {
            panelFeatureState.f137h = view;
            return true;
        }
        if (panelFeatureState.j == null) {
            return false;
        }
        if (this.u == null) {
            this.u = new t();
        }
        View view2 = (View) panelFeatureState.a(this.u);
        panelFeatureState.f137h = view2;
        return view2 != null;
    }

    private boolean l0(PanelFeatureState panelFeatureState) {
        panelFeatureState.d(d0());
        panelFeatureState.f136g = new s(panelFeatureState.l);
        panelFeatureState.f132c = 81;
        return true;
    }

    private boolean m0(PanelFeatureState panelFeatureState) {
        Context context = this.l;
        int i2 = panelFeatureState.f130a;
        if ((i2 == 0 || i2 == 108) && this.s != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(b.a.a.f2064g, typedValue, true);
            Resources.Theme themeNewTheme = null;
            if (typedValue.resourceId != 0) {
                themeNewTheme = context.getResources().newTheme();
                themeNewTheme.setTo(theme);
                themeNewTheme.applyStyle(typedValue.resourceId, true);
                themeNewTheme.resolveAttribute(b.a.a.f2065h, typedValue, true);
            } else {
                theme.resolveAttribute(b.a.a.f2065h, typedValue, true);
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

    private void n0(int i2) {
        this.b0 = (1 << i2) | this.b0;
        if (this.a0) {
            return;
        }
        b.g.k.t.b0(this.m.getDecorView(), this.c0);
        this.a0 = true;
    }

    private boolean o0() {
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

    private boolean t0(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        PanelFeatureState panelFeatureStateG0 = g0(i2, true);
        if (panelFeatureStateG0.o) {
            return false;
        }
        return D0(panelFeatureStateG0, keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean w0(int r5, android.view.KeyEvent r6) {
        /*
            r4 = this;
            b.a.o.b r0 = r4.v
            r1 = 0
            if (r0 == 0) goto L6
            return r1
        L6:
            r0 = 1
            androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r2 = r4.g0(r5, r0)
            if (r5 != 0) goto L43
            androidx.appcompat.widget.o r5 = r4.s
            if (r5 == 0) goto L43
            boolean r5 = r5.g()
            if (r5 == 0) goto L43
            android.content.Context r5 = r4.l
            android.view.ViewConfiguration r5 = android.view.ViewConfiguration.get(r5)
            boolean r5 = r5.hasPermanentMenuKey()
            if (r5 != 0) goto L43
            androidx.appcompat.widget.o r5 = r4.s
            boolean r5 = r5.c()
            if (r5 != 0) goto L3c
            boolean r5 = r4.T
            if (r5 != 0) goto L62
            boolean r5 = r4.D0(r2, r6)
            if (r5 == 0) goto L62
            androidx.appcompat.widget.o r5 = r4.s
            boolean r0 = r5.e()
            goto L68
        L3c:
            androidx.appcompat.widget.o r5 = r4.s
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

    @Override // androidx.appcompat.app.e
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

    @Override // androidx.appcompat.app.e
    public void B(int i2) {
        Z();
        ViewGroup viewGroup = (ViewGroup) this.C.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.l).inflate(i2, viewGroup);
        this.n.a().onContentChanged();
    }

    final androidx.appcompat.app.a B0() {
        return this.p;
    }

    @Override // androidx.appcompat.app.e
    public void C(View view) {
        Z();
        ViewGroup viewGroup = (ViewGroup) this.C.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.n.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.e
    public void D(View view, ViewGroup.LayoutParams layoutParams) {
        Z();
        ViewGroup viewGroup = (ViewGroup) this.C.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.n.a().onContentChanged();
    }

    @Override // androidx.appcompat.app.e
    public void E(Toolbar toolbar) {
        if (this.k instanceof Activity) {
            androidx.appcompat.app.a aVarN = n();
            if (aVarN instanceof androidx.appcompat.app.l) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            this.q = null;
            if (aVarN != null) {
                aVarN.n();
            }
            if (toolbar != null) {
                androidx.appcompat.app.i iVar = new androidx.appcompat.app.i(toolbar, h0(), this.n);
                this.p = iVar;
                this.m.setCallback(iVar.w());
            } else {
                this.p = null;
                this.m.setCallback(this.n);
            }
            p();
        }
    }

    @Override // androidx.appcompat.app.e
    public void F(int i2) {
        this.V = i2;
    }

    @Override // androidx.appcompat.app.e
    public final void G(CharSequence charSequence) {
        this.r = charSequence;
        androidx.appcompat.widget.o oVar = this.s;
        if (oVar != null) {
            oVar.setWindowTitle(charSequence);
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

    final boolean G0() {
        ViewGroup viewGroup;
        return this.B && (viewGroup = this.C) != null && b.g.k.t.P(viewGroup);
    }

    @Override // androidx.appcompat.app.e
    public b.a.o.b H(b.a aVar) {
        androidx.appcompat.app.d dVar;
        if (aVar == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        b.a.o.b bVar = this.v;
        if (bVar != null) {
            bVar.c();
        }
        j jVar = new j(aVar);
        androidx.appcompat.app.a aVarN = n();
        if (aVarN != null) {
            b.a.o.b bVarU = aVarN.u(jVar);
            this.v = bVarU;
            if (bVarU != null && (dVar = this.o) != null) {
                dVar.onSupportActionModeStarted(bVarU);
            }
        }
        if (this.v == null) {
            this.v = I0(jVar);
        }
        return this.v;
    }

    public boolean I() {
        return J(true);
    }

    b.a.o.b I0(b.a aVar) {
        b.a.o.b bVarOnWindowStartingSupportActionMode;
        Context dVar;
        androidx.appcompat.app.d dVar2;
        Y();
        b.a.o.b bVar = this.v;
        if (bVar != null) {
            bVar.c();
        }
        if (!(aVar instanceof j)) {
            aVar = new j(aVar);
        }
        androidx.appcompat.app.d dVar3 = this.o;
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
                    theme.resolveAttribute(b.a.a.f2064g, typedValue, true);
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
                    PopupWindow popupWindow = new PopupWindow(dVar, (AttributeSet) null, b.a.a.j);
                    this.x = popupWindow;
                    androidx.core.widget.h.b(popupWindow, 2);
                    this.x.setContentView(this.w);
                    this.x.setWidth(-1);
                    dVar.getTheme().resolveAttribute(b.a.a.f2059b, typedValue, true);
                    this.w.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, dVar.getResources().getDisplayMetrics()));
                    this.x.setHeight(-2);
                    this.y = new f();
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.C.findViewById(b.a.f.f2098h);
                    if (viewStubCompat != null) {
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(d0()));
                        this.w = (ActionBarContextView) viewStubCompat.a();
                    }
                }
            }
            if (this.w != null) {
                Y();
                this.w.k();
                b.a.o.e eVar = new b.a.o.e(this.w.getContext(), this.w, aVar, this.x == null);
                if (aVar.c(eVar, eVar.e())) {
                    eVar.k();
                    this.w.h(eVar);
                    this.v = eVar;
                    if (G0()) {
                        this.w.setAlpha(0.0f);
                        x xVarA = b.g.k.t.c(this.w).a(1.0f);
                        this.z = xVarA;
                        xVarA.f(new g());
                    } else {
                        this.w.setAlpha(1.0f);
                        this.w.setVisibility(0);
                        this.w.sendAccessibilityEvent(32);
                        if (this.w.getParent() instanceof View) {
                            b.g.k.t.g0((View) this.w.getParent());
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
        if (bVar2 != null && (dVar2 = this.o) != null) {
            dVar2.onSupportActionModeStarted(bVar2);
        }
        return this.v;
    }

    void N(int i2, PanelFeatureState panelFeatureState, Menu menu) {
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

    final int N0(b0 b0Var, Rect rect) {
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
                b0 b0VarG = b.g.k.t.G(this.C);
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

    void O(androidx.appcompat.view.menu.g gVar) {
        if (this.M) {
            return;
        }
        this.M = true;
        this.s.l();
        Window.Callback callbackI0 = i0();
        if (callbackI0 != null && !this.T) {
            callbackI0.onPanelClosed(108, gVar);
        }
        this.M = false;
    }

    void Q(int i2) {
        R(g0(i2, true), true);
    }

    void R(PanelFeatureState panelFeatureState, boolean z) {
        ViewGroup viewGroup;
        androidx.appcompat.widget.o oVar;
        if (z && panelFeatureState.f130a == 0 && (oVar = this.s) != null && oVar.c()) {
            O(panelFeatureState.j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.l.getSystemService("window");
        if (windowManager != null && panelFeatureState.o && (viewGroup = panelFeatureState.f136g) != null) {
            windowManager.removeView(viewGroup);
            if (z) {
                N(panelFeatureState.f130a, panelFeatureState, null);
            }
        }
        panelFeatureState.m = false;
        panelFeatureState.n = false;
        panelFeatureState.o = false;
        panelFeatureState.f137h = null;
        panelFeatureState.q = true;
        if (this.O == panelFeatureState) {
            this.O = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View U(View view, String str, Context context, AttributeSet attributeSet) {
        boolean zH0 = false;
        if (this.g0 == null) {
            String string = this.l.obtainStyledAttributes(b.a.j.B0).getString(b.a.j.F0);
            if (string == null) {
                this.g0 = new androidx.appcompat.app.g();
            } else {
                try {
                    this.g0 = (androidx.appcompat.app.g) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.g0 = new androidx.appcompat.app.g();
                }
            }
        }
        boolean z = f127f;
        if (z) {
            if (!(attributeSet instanceof XmlPullParser)) {
                zH0 = H0((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                zH0 = true;
            }
        }
        return this.g0.q(view, str, context, attributeSet, zH0, z, true, j0.b());
    }

    void V() {
        androidx.appcompat.view.menu.g gVar;
        androidx.appcompat.widget.o oVar = this.s;
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
        Y();
        PanelFeatureState panelFeatureStateG0 = g0(0, false);
        if (panelFeatureStateG0 == null || (gVar = panelFeatureStateG0.j) == null) {
            return;
        }
        gVar.close();
    }

    boolean W(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.k;
        if (((obj instanceof e.a) || (obj instanceof androidx.appcompat.app.f)) && (decorView = this.m.getDecorView()) != null && b.g.k.e.d(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.n.a().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? s0(keyCode, keyEvent) : v0(keyCode, keyEvent);
    }

    void X(int i2) {
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

    void Y() {
        x xVar = this.z;
        if (xVar != null) {
            xVar.b();
        }
    }

    @Override // androidx.appcompat.view.menu.g.a
    public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        PanelFeatureState panelFeatureStateB0;
        Window.Callback callbackI0 = i0();
        if (callbackI0 == null || this.T || (panelFeatureStateB0 = b0(gVar.F())) == null) {
            return false;
        }
        return callbackI0.onMenuItemSelected(panelFeatureStateB0.f130a, menuItem);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public void b(androidx.appcompat.view.menu.g gVar) {
        E0(true);
    }

    PanelFeatureState b0(Menu menu) {
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

    @Override // androidx.appcompat.app.e
    public void d(View view, ViewGroup.LayoutParams layoutParams) {
        Z();
        ((ViewGroup) this.C.findViewById(R.id.content)).addView(view, layoutParams);
        this.n.a().onContentChanged();
    }

    final Context d0() {
        androidx.appcompat.app.a aVarN = n();
        Context contextK = aVarN != null ? aVarN.k() : null;
        return contextK == null ? this.l : contextK;
    }

    @Override // androidx.appcompat.app.e
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
        if (context instanceof b.a.o.d) {
            try {
                ((b.a.o.d) context).a(S(context, iQ0, null));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (!f129h) {
            return super.f(context);
        }
        try {
            Configuration configuration = context.getPackageManager().getResourcesForApplication(context.getApplicationInfo()).getConfiguration();
            Configuration configuration2 = context.getResources().getConfiguration();
            Configuration configurationS = S(context, iQ0, configuration.equals(configuration2) ? null : c0(configuration, configuration2));
            b.a.o.d dVar = new b.a.o.d(context, b.a.i.f2118d);
            dVar.a(configurationS);
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

    protected PanelFeatureState g0(int i2, boolean z) {
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

    final CharSequence h0() {
        Object obj = this.k;
        return obj instanceof Activity ? ((Activity) obj).getTitle() : this.r;
    }

    @Override // androidx.appcompat.app.e
    public <T extends View> T i(int i2) {
        Z();
        return (T) this.m.findViewById(i2);
    }

    final Window.Callback i0() {
        return this.m.getCallback();
    }

    @Override // androidx.appcompat.app.e
    public final androidx.appcompat.app.b k() {
        return new h();
    }

    @Override // androidx.appcompat.app.e
    public int l() {
        return this.U;
    }

    @Override // androidx.appcompat.app.e
    public MenuInflater m() {
        if (this.q == null) {
            j0();
            androidx.appcompat.app.a aVar = this.p;
            this.q = new b.a.o.g(aVar != null ? aVar.k() : this.l);
        }
        return this.q;
    }

    @Override // androidx.appcompat.app.e
    public androidx.appcompat.app.a n() {
        j0();
        return this.p;
    }

    @Override // androidx.appcompat.app.e
    public void o() {
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

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return U(view, str, context, attributeSet);
    }

    @Override // androidx.appcompat.app.e
    public void p() {
        androidx.appcompat.app.a aVarN = n();
        if (aVarN == null || !aVarN.l()) {
            n0(0);
        }
    }

    public boolean p0() {
        return this.A;
    }

    @Override // androidx.appcompat.app.e
    public void q(Configuration configuration) {
        androidx.appcompat.app.a aVarN;
        if (this.H && this.B && (aVarN = n()) != null) {
            aVarN.m(configuration);
        }
        androidx.appcompat.widget.f.b().g(this.l);
        J(false);
    }

    int q0(Context context, int i2) {
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

    @Override // androidx.appcompat.app.e
    public void r(Bundle bundle) {
        this.Q = true;
        J(false);
        a0();
        Object obj = this.k;
        if (obj instanceof Activity) {
            String strC = null;
            try {
                strC = androidx.core.app.d.c((Activity) obj);
            } catch (IllegalArgumentException unused) {
            }
            if (strC != null) {
                androidx.appcompat.app.a aVarB0 = B0();
                if (aVarB0 == null) {
                    this.d0 = true;
                } else {
                    aVarB0.r(true);
                }
            }
            androidx.appcompat.app.e.c(this);
        }
        this.R = true;
    }

    boolean r0() {
        b.a.o.b bVar = this.v;
        if (bVar != null) {
            bVar.c();
            return true;
        }
        androidx.appcompat.app.a aVarN = n();
        return aVarN != null && aVarN.h();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0048  */
    @Override // androidx.appcompat.app.e
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
            androidx.appcompat.app.e.y(r3)
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
            b.d.g<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.f126e
            java.lang.Object r1 = r3.k
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.U
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L57
        L48:
            b.d.g<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.f126e
            java.lang.Object r1 = r3.k
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L57:
            androidx.appcompat.app.a r0 = r3.p
            if (r0 == 0) goto L5e
            r0.n()
        L5e:
            r3.P()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.s():void");
    }

    boolean s0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            this.P = (keyEvent.getFlags() & 128) != 0;
        } else if (i2 == 82) {
            t0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.e
    public void t(Bundle bundle) {
        Z();
    }

    @Override // androidx.appcompat.app.e
    public void u() {
        androidx.appcompat.app.a aVarN = n();
        if (aVarN != null) {
            aVarN.s(true);
        }
    }

    boolean u0(int i2, KeyEvent keyEvent) {
        androidx.appcompat.app.a aVarN = n();
        if (aVarN != null && aVarN.o(i2, keyEvent)) {
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

    @Override // androidx.appcompat.app.e
    public void v(Bundle bundle) {
    }

    boolean v0(int i2, KeyEvent keyEvent) {
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

    @Override // androidx.appcompat.app.e
    public void w() {
        this.S = true;
        I();
    }

    @Override // androidx.appcompat.app.e
    public void x() {
        this.S = false;
        androidx.appcompat.app.a aVarN = n();
        if (aVarN != null) {
            aVarN.s(false);
        }
    }

    void x0(int i2) {
        androidx.appcompat.app.a aVarN;
        if (i2 != 108 || (aVarN = n()) == null) {
            return;
        }
        aVarN.i(true);
    }

    void y0(int i2) {
        if (i2 == 108) {
            androidx.appcompat.app.a aVarN = n();
            if (aVarN != null) {
                aVarN.i(false);
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

    void z0(ViewGroup viewGroup) {
    }

    AppCompatDelegateImpl(Dialog dialog, androidx.appcompat.app.d dVar) {
        this(dialog.getContext(), dialog.getWindow(), dVar, dialog);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    private AppCompatDelegateImpl(Context context, Window window, androidx.appcompat.app.d dVar, Object obj) {
        b.d.g<String, Integer> gVar;
        Integer num;
        AppCompatActivity appCompatActivityK0;
        this.z = null;
        this.A = true;
        this.U = -100;
        this.c0 = new b();
        this.l = context;
        this.o = dVar;
        this.k = obj;
        if (this.U == -100 && (obj instanceof Dialog) && (appCompatActivityK0 = K0()) != null) {
            this.U = appCompatActivityK0.getDelegate().l();
        }
        if (this.U == -100 && (num = (gVar = f126e).get(obj.getClass().getName())) != null) {
            this.U = num.intValue();
            gVar.remove(obj.getClass().getName());
        }
        if (window != null) {
            L(window);
        }
        androidx.appcompat.widget.f.h();
    }

    class k extends b.a.o.i {
        k(Window.Callback callback) {
            super(callback);
        }

        final ActionMode b(ActionMode.Callback callback) {
            f.a aVar = new f.a(AppCompatDelegateImpl.this.l, callback);
            b.a.o.b bVarH = AppCompatDelegateImpl.this.H(aVar);
            if (bVarH != null) {
                return aVar.e(bVarH);
            }
            return null;
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.W(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.u0(keyEvent.getKeyCode(), keyEvent);
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public void onContentChanged() {
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof androidx.appcompat.view.menu.g)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            AppCompatDelegateImpl.this.x0(i);
            return true;
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            AppCompatDelegateImpl.this.y0(i);
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public boolean onPreparePanel(int i, View view, Menu menu) {
            androidx.appcompat.view.menu.g gVar = menu instanceof androidx.appcompat.view.menu.g ? (androidx.appcompat.view.menu.g) menu : null;
            if (i == 0 && gVar == null) {
                return false;
            }
            if (gVar != null) {
                gVar.e0(true);
            }
            boolean zOnPreparePanel = super.onPreparePanel(i, view, menu);
            if (gVar != null) {
                gVar.e0(false);
            }
            return zOnPreparePanel;
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            androidx.appcompat.view.menu.g gVar;
            PanelFeatureState panelFeatureStateG0 = AppCompatDelegateImpl.this.g0(0, true);
            if (panelFeatureStateG0 == null || (gVar = panelFeatureStateG0.j) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i);
            } else {
                super.onProvideKeyboardShortcuts(list, gVar, i);
            }
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            return AppCompatDelegateImpl.this.p0() ? b(callback) : super.onWindowStartingActionMode(callback);
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (AppCompatDelegateImpl.this.p0() && i == 0) {
                return b(callback);
            }
            return super.onWindowStartingActionMode(callback, i);
        }
    }
}
