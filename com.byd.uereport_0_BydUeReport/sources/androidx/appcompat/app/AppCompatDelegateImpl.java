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
import f0.f;
import java.lang.Thread;
import java.util.List;
import o0.b0;
import o0.e;
import o0.x;
import o0.z;
import org.xmlpull.v1.XmlPullParser;
import p.b;
import p.f;
import q.g;
import q.m;
import r.e0;
import r.j0;
import r.k0;
import r.s;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatDelegateImpl extends l.e implements g.a, LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final v.g<String, Integer> f321g = new v.g<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final boolean f322h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int[] f323i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final boolean f324j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final boolean f325k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static boolean f326l;
    public Runnable A;
    public x B;
    public boolean C;
    public boolean D;
    public ViewGroup E;
    public TextView F;
    public View G;
    public boolean H;
    public boolean I;
    public boolean J;
    public boolean K;
    public boolean L;
    public boolean M;
    public boolean N;
    public boolean O;
    public PanelFeatureState[] P;
    public PanelFeatureState Q;
    public boolean R;
    public boolean S;
    public boolean T;
    public boolean U;
    public boolean V;
    public int W;
    public int X;
    public boolean Y;
    public boolean Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public m f327a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public m f328b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public boolean f329c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public int f330d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final Runnable f331e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public boolean f332f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public Rect f333g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public Rect f334h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public l.g f335i0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f336m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Context f337n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Window f338o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public k f339p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final l.d f340q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public l.a f341r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public MenuInflater f342s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public CharSequence f343t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public r.o f344u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public i f345v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public t f346w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public p.b f347x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public ActionBarContextView f348y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public PopupWindow f349z;

    public static final class PanelFeatureState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f350a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f351b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f352c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f353d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f354e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f355f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ViewGroup f356g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public View f357h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public View f358i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public q.g f359j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public q.e f360k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public Context f361l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public boolean f362m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public boolean f363n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public boolean f364o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public boolean f365p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public boolean f366q = false;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public boolean f367r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public Bundle f368s;

        @SuppressLint({"BanParcelableUsage"})
        public static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new a();

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f369d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public boolean f370e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public Bundle f371f;

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
                public SavedState[] newArray(int i8) {
                    return new SavedState[i8];
                }
            }

            public static SavedState f(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.f369d = parcel.readInt();
                boolean z7 = parcel.readInt() == 1;
                savedState.f370e = z7;
                if (z7) {
                    savedState.f371f = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i8) {
                parcel.writeInt(this.f369d);
                parcel.writeInt(this.f370e ? 1 : 0);
                if (this.f370e) {
                    parcel.writeBundle(this.f371f);
                }
            }
        }

        public PanelFeatureState(int i8) {
            this.f350a = i8;
        }

        public q.n a(m.a aVar) {
            if (this.f359j == null) {
                return null;
            }
            if (this.f360k == null) {
                q.e eVar = new q.e(this.f361l, R$layout.abc_list_menu_item_layout);
                this.f360k = eVar;
                eVar.n(aVar);
                this.f359j.b(this.f360k);
            }
            return this.f360k.e(this.f356g);
        }

        public boolean b() {
            if (this.f357h == null) {
                return false;
            }
            return this.f358i != null || this.f360k.c().getCount() > 0;
        }

        public void c(q.g gVar) {
            q.e eVar;
            q.g gVar2 = this.f359j;
            if (gVar == gVar2) {
                return;
            }
            if (gVar2 != null) {
                gVar2.Q(this.f360k);
            }
            this.f359j = gVar;
            if (gVar == null || (eVar = this.f360k) == null) {
                return;
            }
            gVar.b(eVar);
        }

        public void d(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme themeNewTheme = context.getResources().newTheme();
            themeNewTheme.setTo(context.getTheme());
            themeNewTheme.resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true);
            int i8 = typedValue.resourceId;
            if (i8 != 0) {
                themeNewTheme.applyStyle(i8, true);
            }
            themeNewTheme.resolveAttribute(R$attr.panelMenuListTheme, typedValue, true);
            int i9 = typedValue.resourceId;
            if (i9 != 0) {
                themeNewTheme.applyStyle(i9, true);
            } else {
                themeNewTheme.applyStyle(R$style.Theme_AppCompat_CompactMenu, true);
            }
            p.d dVar = new p.d(context, 0);
            dVar.getTheme().setTo(themeNewTheme);
            this.f361l = dVar;
            TypedArray typedArrayObtainStyledAttributes = dVar.obtainStyledAttributes(R$styleable.AppCompatTheme);
            this.f351b = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_panelBackground, 0);
            this.f355f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public class a implements Thread.UncaughtExceptionHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Thread.UncaughtExceptionHandler f372a;

        public a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.f372a = uncaughtExceptionHandler;
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
                this.f372a.uncaughtException(thread, th);
                return;
            }
            Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            notFoundException.initCause(th.getCause());
            notFoundException.setStackTrace(th.getStackTrace());
            this.f372a.uncaughtException(thread, notFoundException);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl.f330d0 & 1) != 0) {
                appCompatDelegateImpl.X(0);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl2.f330d0 & 4096) != 0) {
                appCompatDelegateImpl2.X(108);
            }
            AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl3.f329c0 = false;
            appCompatDelegateImpl3.f330d0 = 0;
        }
    }

    public class c implements o0.p {
        public c() {
        }

        @Override // o0.p
        public b0 a(View view, b0 b0Var) {
            int iH = b0Var.h();
            int iN0 = AppCompatDelegateImpl.this.N0(b0Var, null);
            if (iH != iN0) {
                b0Var = b0Var.n(b0Var.f(), iN0, b0Var.g(), b0Var.e());
            }
            return o0.t.X(view, b0Var);
        }
    }

    public class d implements s.a {
        public d() {
        }

        @Override // r.s.a
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

        public class a extends z {
            public a() {
            }

            @Override // o0.y
            public void b(View view) {
                AppCompatDelegateImpl.this.f348y.setAlpha(1.0f);
                AppCompatDelegateImpl.this.B.f(null);
                AppCompatDelegateImpl.this.B = null;
            }

            @Override // o0.z, o0.y
            public void c(View view) {
                AppCompatDelegateImpl.this.f348y.setVisibility(0);
            }
        }

        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            appCompatDelegateImpl.f349z.showAtLocation(appCompatDelegateImpl.f348y, 55, 0, 0);
            AppCompatDelegateImpl.this.Y();
            if (!AppCompatDelegateImpl.this.G0()) {
                AppCompatDelegateImpl.this.f348y.setAlpha(1.0f);
                AppCompatDelegateImpl.this.f348y.setVisibility(0);
            } else {
                AppCompatDelegateImpl.this.f348y.setAlpha(0.0f);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.B = o0.t.c(appCompatDelegateImpl2.f348y).a(1.0f);
                AppCompatDelegateImpl.this.B.f(new a());
            }
        }
    }

    public class g extends z {
        public g() {
        }

        @Override // o0.y
        public void b(View view) {
            AppCompatDelegateImpl.this.f348y.setAlpha(1.0f);
            AppCompatDelegateImpl.this.B.f(null);
            AppCompatDelegateImpl.this.B = null;
        }

        @Override // o0.z, o0.y
        public void c(View view) {
            AppCompatDelegateImpl.this.f348y.setVisibility(0);
            AppCompatDelegateImpl.this.f348y.sendAccessibilityEvent(32);
            if (AppCompatDelegateImpl.this.f348y.getParent() instanceof View) {
                o0.t.i0((View) AppCompatDelegateImpl.this.f348y.getParent());
            }
        }
    }

    public class h implements l.b {
        public h() {
        }
    }

    public final class i implements m.a {
        public i() {
        }

        @Override // q.m.a
        public void a(q.g gVar, boolean z7) {
            AppCompatDelegateImpl.this.O(gVar);
        }

        @Override // q.m.a
        public boolean b(q.g gVar) {
            Window.Callback callbackI0 = AppCompatDelegateImpl.this.i0();
            if (callbackI0 == null) {
                return true;
            }
            callbackI0.onMenuOpened(108, gVar);
            return true;
        }
    }

    public class j implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public b.a f382a;

        public class a extends z {
            public a() {
            }

            @Override // o0.y
            public void b(View view) {
                AppCompatDelegateImpl.this.f348y.setVisibility(8);
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                PopupWindow popupWindow = appCompatDelegateImpl.f349z;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (appCompatDelegateImpl.f348y.getParent() instanceof View) {
                    o0.t.i0((View) AppCompatDelegateImpl.this.f348y.getParent());
                }
                AppCompatDelegateImpl.this.f348y.removeAllViews();
                AppCompatDelegateImpl.this.B.f(null);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.B = null;
                o0.t.i0(appCompatDelegateImpl2.E);
            }
        }

        public j(b.a aVar) {
            this.f382a = aVar;
        }

        @Override // p.b.a
        public boolean a(p.b bVar, Menu menu) {
            o0.t.i0(AppCompatDelegateImpl.this.E);
            return this.f382a.a(bVar, menu);
        }

        @Override // p.b.a
        public void b(p.b bVar) {
            this.f382a.b(bVar);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.f349z != null) {
                appCompatDelegateImpl.f338o.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.A);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.f348y != null) {
                appCompatDelegateImpl2.Y();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl3.B = o0.t.c(appCompatDelegateImpl3.f348y).a(0.0f);
                AppCompatDelegateImpl.this.B.f(new a());
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            l.d dVar = appCompatDelegateImpl4.f340q;
            if (dVar != null) {
                dVar.onSupportActionModeFinished(appCompatDelegateImpl4.f347x);
            }
            AppCompatDelegateImpl appCompatDelegateImpl5 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl5.f347x = null;
            o0.t.i0(appCompatDelegateImpl5.E);
        }

        @Override // p.b.a
        public boolean c(p.b bVar, MenuItem menuItem) {
            return this.f382a.c(bVar, menuItem);
        }

        @Override // p.b.a
        public boolean d(p.b bVar, Menu menu) {
            return this.f382a.d(bVar, menu);
        }
    }

    public class l extends m {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final PowerManager f386c;

        public l(Context context) {
            super();
            this.f386c = (PowerManager) context.getApplicationContext().getSystemService("power");
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
            return (Build.VERSION.SDK_INT < 21 || !this.f386c.isPowerSaveMode()) ? 1 : 2;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public void d() {
            AppCompatDelegateImpl.this.I();
        }
    }

    public abstract class m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public BroadcastReceiver f388a;

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
            BroadcastReceiver broadcastReceiver = this.f388a;
            if (broadcastReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.f337n.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.f388a = null;
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
            if (this.f388a == null) {
                this.f388a = new a();
            }
            AppCompatDelegateImpl.this.f337n.registerReceiver(this.f388a, intentFilterB);
        }
    }

    public class n extends m {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final l.k f391c;

        public n(l.k kVar) {
            super();
            this.f391c = kVar;
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
            return this.f391c.d() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public void d() {
            AppCompatDelegateImpl.this.I();
        }
    }

    public static class o {
        public static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i8 = configuration.densityDpi;
            int i9 = configuration2.densityDpi;
            if (i8 != i9) {
                configuration3.densityDpi = i9;
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
            int i8 = configuration.colorMode & 3;
            int i9 = configuration2.colorMode;
            if (i8 != (i9 & 3)) {
                configuration3.colorMode |= i9 & 3;
            }
            int i10 = configuration.colorMode & 12;
            int i11 = configuration2.colorMode;
            if (i10 != (i11 & 12)) {
                configuration3.colorMode |= i11 & 12;
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

        public final boolean c(int i8, int i9) {
            return i8 < -5 || i9 < -5 || i8 > getWidth() + 5 || i9 > getHeight() + 5;
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
        public void setBackgroundResource(int i8) {
            setBackgroundDrawable(m.a.d(getContext(), i8));
        }
    }

    public final class t implements m.a {
        public t() {
        }

        @Override // q.m.a
        public void a(q.g gVar, boolean z7) {
            q.g gVarF = gVar.F();
            boolean z8 = gVarF != gVar;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z8) {
                gVar = gVarF;
            }
            PanelFeatureState panelFeatureStateB0 = appCompatDelegateImpl.b0(gVar);
            if (panelFeatureStateB0 != null) {
                if (!z8) {
                    AppCompatDelegateImpl.this.R(panelFeatureStateB0, z7);
                } else {
                    AppCompatDelegateImpl.this.N(panelFeatureStateB0.f350a, panelFeatureStateB0, gVarF);
                    AppCompatDelegateImpl.this.R(panelFeatureStateB0, true);
                }
            }
        }

        @Override // q.m.a
        public boolean b(q.g gVar) {
            Window.Callback callbackI0;
            if (gVar != gVar.F()) {
                return true;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (!appCompatDelegateImpl.J || (callbackI0 = appCompatDelegateImpl.i0()) == null || AppCompatDelegateImpl.this.V) {
                return true;
            }
            callbackI0.onMenuOpened(108, gVar);
            return true;
        }
    }

    static {
        int i8 = Build.VERSION.SDK_INT;
        boolean z7 = i8 < 21;
        f322h = z7;
        f323i = new int[]{R.attr.windowBackground};
        f324j = !"robolectric".equals(Build.FINGERPRINT);
        f325k = i8 >= 17;
        if (!z7 || f326l) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new a(Thread.getDefaultUncaughtExceptionHandler()));
        f326l = true;
    }

    public AppCompatDelegateImpl(Activity activity, l.d dVar) {
        this(activity, null, dVar, activity);
    }

    public static Configuration c0(Configuration configuration, Configuration configuration2) {
        Configuration configuration3 = new Configuration();
        configuration3.fontScale = 0.0f;
        if (configuration2 != null && configuration.diff(configuration2) != 0) {
            float f8 = configuration.fontScale;
            float f9 = configuration2.fontScale;
            if (f8 != f9) {
                configuration3.fontScale = f9;
            }
            int i8 = configuration.mcc;
            int i9 = configuration2.mcc;
            if (i8 != i9) {
                configuration3.mcc = i9;
            }
            int i10 = configuration.mnc;
            int i11 = configuration2.mnc;
            if (i10 != i11) {
                configuration3.mnc = i11;
            }
            int i12 = Build.VERSION.SDK_INT;
            if (i12 >= 24) {
                p.a(configuration, configuration2, configuration3);
            } else if (!n0.c.a(configuration.locale, configuration2.locale)) {
                configuration3.locale = configuration2.locale;
            }
            int i13 = configuration.touchscreen;
            int i14 = configuration2.touchscreen;
            if (i13 != i14) {
                configuration3.touchscreen = i14;
            }
            int i15 = configuration.keyboard;
            int i16 = configuration2.keyboard;
            if (i15 != i16) {
                configuration3.keyboard = i16;
            }
            int i17 = configuration.keyboardHidden;
            int i18 = configuration2.keyboardHidden;
            if (i17 != i18) {
                configuration3.keyboardHidden = i18;
            }
            int i19 = configuration.navigation;
            int i20 = configuration2.navigation;
            if (i19 != i20) {
                configuration3.navigation = i20;
            }
            int i21 = configuration.navigationHidden;
            int i22 = configuration2.navigationHidden;
            if (i21 != i22) {
                configuration3.navigationHidden = i22;
            }
            int i23 = configuration.orientation;
            int i24 = configuration2.orientation;
            if (i23 != i24) {
                configuration3.orientation = i24;
            }
            int i25 = configuration.screenLayout & 15;
            int i26 = configuration2.screenLayout;
            if (i25 != (i26 & 15)) {
                configuration3.screenLayout |= i26 & 15;
            }
            int i27 = configuration.screenLayout & 192;
            int i28 = configuration2.screenLayout;
            if (i27 != (i28 & 192)) {
                configuration3.screenLayout |= i28 & 192;
            }
            int i29 = configuration.screenLayout & 48;
            int i30 = configuration2.screenLayout;
            if (i29 != (i30 & 48)) {
                configuration3.screenLayout |= i30 & 48;
            }
            int i31 = configuration.screenLayout & 768;
            int i32 = configuration2.screenLayout;
            if (i31 != (i32 & 768)) {
                configuration3.screenLayout |= i32 & 768;
            }
            if (i12 >= 26) {
                q.a(configuration, configuration2, configuration3);
            }
            int i33 = configuration.uiMode & 15;
            int i34 = configuration2.uiMode;
            if (i33 != (i34 & 15)) {
                configuration3.uiMode |= i34 & 15;
            }
            int i35 = configuration.uiMode & 48;
            int i36 = configuration2.uiMode;
            if (i35 != (i36 & 48)) {
                configuration3.uiMode |= i36 & 48;
            }
            int i37 = configuration.screenWidthDp;
            int i38 = configuration2.screenWidthDp;
            if (i37 != i38) {
                configuration3.screenWidthDp = i38;
            }
            int i39 = configuration.screenHeightDp;
            int i40 = configuration2.screenHeightDp;
            if (i39 != i40) {
                configuration3.screenHeightDp = i40;
            }
            int i41 = configuration.smallestScreenWidthDp;
            int i42 = configuration2.smallestScreenWidthDp;
            if (i41 != i42) {
                configuration3.smallestScreenWidthDp = i42;
            }
            if (i12 >= 17) {
                o.a(configuration, configuration2, configuration3);
            }
        }
        return configuration3;
    }

    @Override // l.e
    public boolean A(int i8) {
        int iF0 = F0(i8);
        if (this.N && iF0 == 108) {
            return false;
        }
        if (this.J && iF0 == 1) {
            this.J = false;
        }
        if (iF0 == 1) {
            J0();
            this.N = true;
            return true;
        }
        if (iF0 == 2) {
            J0();
            this.H = true;
            return true;
        }
        if (iF0 == 5) {
            J0();
            this.I = true;
            return true;
        }
        if (iF0 == 10) {
            J0();
            this.L = true;
            return true;
        }
        if (iF0 == 108) {
            J0();
            this.J = true;
            return true;
        }
        if (iF0 != 109) {
            return this.f338o.requestFeature(iF0);
        }
        J0();
        this.K = true;
        return true;
    }

    public final void A0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        int i8;
        ViewGroup.LayoutParams layoutParams;
        if (panelFeatureState.f364o || this.V) {
            return;
        }
        if (panelFeatureState.f350a == 0) {
            if ((this.f337n.getResources().getConfiguration().screenLayout & 15) == 4) {
                return;
            }
        }
        Window.Callback callbackI0 = i0();
        if (callbackI0 != null && !callbackI0.onMenuOpened(panelFeatureState.f350a, panelFeatureState.f359j)) {
            R(panelFeatureState, true);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f337n.getSystemService("window");
        if (windowManager != null && D0(panelFeatureState, keyEvent)) {
            ViewGroup viewGroup = panelFeatureState.f356g;
            if (viewGroup != null && !panelFeatureState.f366q) {
                View view = panelFeatureState.f358i;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i8 = -1;
                }
                panelFeatureState.f363n = false;
                WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i8, -2, panelFeatureState.f353d, panelFeatureState.f354e, 1002, 8519680, -3);
                layoutParams2.gravity = panelFeatureState.f352c;
                layoutParams2.windowAnimations = panelFeatureState.f355f;
                windowManager.addView(panelFeatureState.f356g, layoutParams2);
                panelFeatureState.f364o = true;
            }
            if (viewGroup == null) {
                if (!l0(panelFeatureState) || panelFeatureState.f356g == null) {
                    return;
                }
            } else if (panelFeatureState.f366q && viewGroup.getChildCount() > 0) {
                panelFeatureState.f356g.removeAllViews();
            }
            if (!k0(panelFeatureState) || !panelFeatureState.b()) {
                panelFeatureState.f366q = true;
                return;
            }
            ViewGroup.LayoutParams layoutParams3 = panelFeatureState.f357h.getLayoutParams();
            if (layoutParams3 == null) {
                layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
            }
            panelFeatureState.f356g.setBackgroundResource(panelFeatureState.f351b);
            ViewParent parent = panelFeatureState.f357h.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(panelFeatureState.f357h);
            }
            panelFeatureState.f356g.addView(panelFeatureState.f357h, layoutParams3);
            if (!panelFeatureState.f357h.hasFocus()) {
                panelFeatureState.f357h.requestFocus();
            }
            i8 = -2;
            panelFeatureState.f363n = false;
            WindowManager.LayoutParams layoutParams22 = new WindowManager.LayoutParams(i8, -2, panelFeatureState.f353d, panelFeatureState.f354e, 1002, 8519680, -3);
            layoutParams22.gravity = panelFeatureState.f352c;
            layoutParams22.windowAnimations = panelFeatureState.f355f;
            windowManager.addView(panelFeatureState.f356g, layoutParams22);
            panelFeatureState.f364o = true;
        }
    }

    @Override // l.e
    public void B(int i8) {
        Z();
        ViewGroup viewGroup = (ViewGroup) this.E.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f337n).inflate(i8, viewGroup);
        this.f339p.a().onContentChanged();
    }

    public final l.a B0() {
        return this.f341r;
    }

    @Override // l.e
    public void C(View view) {
        Z();
        ViewGroup viewGroup = (ViewGroup) this.E.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.f339p.a().onContentChanged();
    }

    public final boolean C0(PanelFeatureState panelFeatureState, int i8, KeyEvent keyEvent, int i9) {
        q.g gVar;
        boolean zPerformShortcut = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.f362m || D0(panelFeatureState, keyEvent)) && (gVar = panelFeatureState.f359j) != null) {
            zPerformShortcut = gVar.performShortcut(i8, keyEvent, i9);
        }
        if (zPerformShortcut && (i9 & 1) == 0 && this.f344u == null) {
            R(panelFeatureState, true);
        }
        return zPerformShortcut;
    }

    @Override // l.e
    public void D(View view, ViewGroup.LayoutParams layoutParams) {
        Z();
        ViewGroup viewGroup = (ViewGroup) this.E.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.f339p.a().onContentChanged();
    }

    public final boolean D0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        r.o oVar;
        r.o oVar2;
        r.o oVar3;
        if (this.V) {
            return false;
        }
        if (panelFeatureState.f362m) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.Q;
        if (panelFeatureState2 != null && panelFeatureState2 != panelFeatureState) {
            R(panelFeatureState2, false);
        }
        Window.Callback callbackI0 = i0();
        if (callbackI0 != null) {
            panelFeatureState.f358i = callbackI0.onCreatePanelView(panelFeatureState.f350a);
        }
        int i8 = panelFeatureState.f350a;
        boolean z7 = i8 == 0 || i8 == 108;
        if (z7 && (oVar3 = this.f344u) != null) {
            oVar3.c();
        }
        if (panelFeatureState.f358i == null && (!z7 || !(B0() instanceof l.i))) {
            q.g gVar = panelFeatureState.f359j;
            if (gVar == null || panelFeatureState.f367r) {
                if (gVar == null && (!m0(panelFeatureState) || panelFeatureState.f359j == null)) {
                    return false;
                }
                if (z7 && this.f344u != null) {
                    if (this.f345v == null) {
                        this.f345v = new i();
                    }
                    this.f344u.a(panelFeatureState.f359j, this.f345v);
                }
                panelFeatureState.f359j.h0();
                if (!callbackI0.onCreatePanelMenu(panelFeatureState.f350a, panelFeatureState.f359j)) {
                    panelFeatureState.c(null);
                    if (z7 && (oVar = this.f344u) != null) {
                        oVar.a(null, this.f345v);
                    }
                    return false;
                }
                panelFeatureState.f367r = false;
            }
            panelFeatureState.f359j.h0();
            Bundle bundle = panelFeatureState.f368s;
            if (bundle != null) {
                panelFeatureState.f359j.R(bundle);
                panelFeatureState.f368s = null;
            }
            if (!callbackI0.onPreparePanel(0, panelFeatureState.f358i, panelFeatureState.f359j)) {
                if (z7 && (oVar2 = this.f344u) != null) {
                    oVar2.a(null, this.f345v);
                }
                panelFeatureState.f359j.g0();
                return false;
            }
            boolean z8 = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            panelFeatureState.f365p = z8;
            panelFeatureState.f359j.setQwertyMode(z8);
            panelFeatureState.f359j.g0();
        }
        panelFeatureState.f362m = true;
        panelFeatureState.f363n = false;
        this.Q = panelFeatureState;
        return true;
    }

    @Override // l.e
    public void E(Toolbar toolbar) {
        if (this.f336m instanceof Activity) {
            l.a aVarN = n();
            if (aVarN instanceof l.l) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            this.f342s = null;
            if (aVarN != null) {
                aVarN.n();
            }
            if (toolbar != null) {
                l.i iVar = new l.i(toolbar, h0(), this.f339p);
                this.f341r = iVar;
                this.f338o.setCallback(iVar.w());
            } else {
                this.f341r = null;
                this.f338o.setCallback(this.f339p);
            }
            p();
        }
    }

    public final void E0(boolean z7) {
        r.o oVar = this.f344u;
        if (oVar == null || !oVar.g() || (ViewConfiguration.get(this.f337n).hasPermanentMenuKey() && !this.f344u.d())) {
            PanelFeatureState panelFeatureStateG0 = g0(0, true);
            panelFeatureStateG0.f366q = true;
            R(panelFeatureStateG0, false);
            A0(panelFeatureStateG0, null);
            return;
        }
        Window.Callback callbackI0 = i0();
        if (this.f344u.b() && z7) {
            this.f344u.e();
            if (this.V) {
                return;
            }
            callbackI0.onPanelClosed(108, g0(0, true).f359j);
            return;
        }
        if (callbackI0 == null || this.V) {
            return;
        }
        if (this.f329c0 && (this.f330d0 & 1) != 0) {
            this.f338o.getDecorView().removeCallbacks(this.f331e0);
            this.f331e0.run();
        }
        PanelFeatureState panelFeatureStateG02 = g0(0, true);
        q.g gVar = panelFeatureStateG02.f359j;
        if (gVar == null || panelFeatureStateG02.f367r || !callbackI0.onPreparePanel(0, panelFeatureStateG02.f358i, gVar)) {
            return;
        }
        callbackI0.onMenuOpened(108, panelFeatureStateG02.f359j);
        this.f344u.f();
    }

    @Override // l.e
    public void F(int i8) {
        this.X = i8;
    }

    public final int F0(int i8) {
        if (i8 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        }
        if (i8 != 9) {
            return i8;
        }
        Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
        return 109;
    }

    @Override // l.e
    public final void G(CharSequence charSequence) {
        this.f343t = charSequence;
        r.o oVar = this.f344u;
        if (oVar != null) {
            oVar.setWindowTitle(charSequence);
            return;
        }
        if (B0() != null) {
            B0().t(charSequence);
            return;
        }
        TextView textView = this.F;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public final boolean G0() {
        ViewGroup viewGroup;
        return this.D && (viewGroup = this.E) != null && o0.t.Q(viewGroup);
    }

    @Override // l.e
    public p.b H(b.a aVar) {
        l.d dVar;
        if (aVar == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        p.b bVar = this.f347x;
        if (bVar != null) {
            bVar.c();
        }
        j jVar = new j(aVar);
        l.a aVarN = n();
        if (aVarN != null) {
            p.b bVarU = aVarN.u(jVar);
            this.f347x = bVarU;
            if (bVarU != null && (dVar = this.f340q) != null) {
                dVar.onSupportActionModeStarted(bVarU);
            }
        }
        if (this.f347x == null) {
            this.f347x = I0(jVar);
        }
        return this.f347x;
    }

    public final boolean H0(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.f338o.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || o0.t.P((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    public boolean I() {
        return J(true);
    }

    public p.b I0(b.a aVar) {
        p.b bVarOnWindowStartingSupportActionMode;
        Context dVar;
        l.d dVar2;
        Y();
        p.b bVar = this.f347x;
        if (bVar != null) {
            bVar.c();
        }
        if (!(aVar instanceof j)) {
            aVar = new j(aVar);
        }
        l.d dVar3 = this.f340q;
        if (dVar3 == null || this.V) {
            bVarOnWindowStartingSupportActionMode = null;
        } else {
            try {
                bVarOnWindowStartingSupportActionMode = dVar3.onWindowStartingSupportActionMode(aVar);
            } catch (AbstractMethodError unused) {
                bVarOnWindowStartingSupportActionMode = null;
            }
        }
        if (bVarOnWindowStartingSupportActionMode != null) {
            this.f347x = bVarOnWindowStartingSupportActionMode;
        } else {
            if (this.f348y == null) {
                if (this.M) {
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = this.f337n.getTheme();
                    theme.resolveAttribute(R$attr.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Resources.Theme themeNewTheme = this.f337n.getResources().newTheme();
                        themeNewTheme.setTo(theme);
                        themeNewTheme.applyStyle(typedValue.resourceId, true);
                        dVar = new p.d(this.f337n, 0);
                        dVar.getTheme().setTo(themeNewTheme);
                    } else {
                        dVar = this.f337n;
                    }
                    this.f348y = new ActionBarContextView(dVar);
                    PopupWindow popupWindow = new PopupWindow(dVar, (AttributeSet) null, R$attr.actionModePopupWindowStyle);
                    this.f349z = popupWindow;
                    r0.h.b(popupWindow, 2);
                    this.f349z.setContentView(this.f348y);
                    this.f349z.setWidth(-1);
                    dVar.getTheme().resolveAttribute(R$attr.actionBarSize, typedValue, true);
                    this.f348y.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, dVar.getResources().getDisplayMetrics()));
                    this.f349z.setHeight(-2);
                    this.A = new f();
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.E.findViewById(R$id.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(d0()));
                        this.f348y = (ActionBarContextView) viewStubCompat.a();
                    }
                }
            }
            if (this.f348y != null) {
                Y();
                this.f348y.k();
                p.e eVar = new p.e(this.f348y.getContext(), this.f348y, aVar, this.f349z == null);
                if (aVar.d(eVar, eVar.e())) {
                    eVar.k();
                    this.f348y.h(eVar);
                    this.f347x = eVar;
                    if (G0()) {
                        this.f348y.setAlpha(0.0f);
                        x xVarA = o0.t.c(this.f348y).a(1.0f);
                        this.B = xVarA;
                        xVarA.f(new g());
                    } else {
                        this.f348y.setAlpha(1.0f);
                        this.f348y.setVisibility(0);
                        this.f348y.sendAccessibilityEvent(32);
                        if (this.f348y.getParent() instanceof View) {
                            o0.t.i0((View) this.f348y.getParent());
                        }
                    }
                    if (this.f349z != null) {
                        this.f338o.getDecorView().post(this.A);
                    }
                } else {
                    this.f347x = null;
                }
            }
        }
        p.b bVar2 = this.f347x;
        if (bVar2 != null && (dVar2 = this.f340q) != null) {
            dVar2.onSupportActionModeStarted(bVar2);
        }
        return this.f347x;
    }

    public final boolean J(boolean z7) {
        if (this.V) {
            return false;
        }
        int iM = M();
        boolean zL0 = L0(q0(this.f337n, iM), z7);
        if (iM == 0) {
            f0(this.f337n).e();
        } else {
            m mVar = this.f327a0;
            if (mVar != null) {
                mVar.a();
            }
        }
        if (iM == 3) {
            e0(this.f337n).e();
        } else {
            m mVar2 = this.f328b0;
            if (mVar2 != null) {
                mVar2.a();
            }
        }
        return zL0;
    }

    public final void J0() {
        if (this.D) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public final void K() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.E.findViewById(R.id.content);
        View decorView = this.f338o.getDecorView();
        contentFrameLayout.b(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray typedArrayObtainStyledAttributes = this.f337n.obtainStyledAttributes(R$styleable.AppCompatTheme);
        typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        int i8 = R$styleable.AppCompatTheme_windowFixedWidthMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i8)) {
            typedArrayObtainStyledAttributes.getValue(i8, contentFrameLayout.getFixedWidthMajor());
        }
        int i9 = R$styleable.AppCompatTheme_windowFixedWidthMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i9)) {
            typedArrayObtainStyledAttributes.getValue(i9, contentFrameLayout.getFixedWidthMinor());
        }
        int i10 = R$styleable.AppCompatTheme_windowFixedHeightMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i10)) {
            typedArrayObtainStyledAttributes.getValue(i10, contentFrameLayout.getFixedHeightMajor());
        }
        int i11 = R$styleable.AppCompatTheme_windowFixedHeightMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i11)) {
            typedArrayObtainStyledAttributes.getValue(i11, contentFrameLayout.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    public final AppCompatActivity K0() {
        for (Context baseContext = this.f337n; baseContext != null; baseContext = ((ContextWrapper) baseContext).getBaseContext()) {
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
        if (this.f338o != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof k) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        k kVar = new k(callback);
        this.f339p = kVar;
        window.setCallback(kVar);
        e0 e0VarU = e0.u(this.f337n, null, f323i);
        Drawable drawableH = e0VarU.h(0);
        if (drawableH != null) {
            window.setBackgroundDrawable(drawableH);
        }
        e0VarU.w();
        this.f338o = window;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean L0(int r7, boolean r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.f337n
            r1 = 0
            android.content.res.Configuration r0 = r6.S(r0, r7, r1)
            boolean r2 = r6.o0()
            android.content.Context r3 = r6.f337n
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
            boolean r8 = r6.S
            if (r8 == 0) goto L47
            boolean r8 = androidx.appcompat.app.AppCompatDelegateImpl.f324j
            if (r8 != 0) goto L30
            boolean r8 = r6.T
            if (r8 == 0) goto L47
        L30:
            java.lang.Object r8 = r6.f336m
            boolean r5 = r8 instanceof android.app.Activity
            if (r5 == 0) goto L47
            android.app.Activity r8 = (android.app.Activity) r8
            boolean r8 = r8.isChild()
            if (r8 != 0) goto L47
            java.lang.Object r8 = r6.f336m
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
            java.lang.Object r8 = r6.f336m
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
        int i8 = this.W;
        return i8 != -100 ? i8 : l.e.j();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void M0(int i8, boolean z7, Configuration configuration) {
        Resources resources = this.f337n.getResources();
        Configuration configuration2 = new Configuration(resources.getConfiguration());
        if (configuration != null) {
            configuration2.updateFrom(configuration);
        }
        configuration2.uiMode = i8 | (resources.getConfiguration().uiMode & (-49));
        resources.updateConfiguration(configuration2, null);
        int i9 = Build.VERSION.SDK_INT;
        if (i9 < 26) {
            l.h.a(resources);
        }
        int i10 = this.X;
        if (i10 != 0) {
            this.f337n.setTheme(i10);
            if (i9 >= 23) {
                this.f337n.getTheme().applyStyle(this.X, true);
            }
        }
        if (z7) {
            Object obj = this.f336m;
            if (obj instanceof Activity) {
                Activity activity = (Activity) obj;
                if (activity instanceof x0.f) {
                    if (((x0.f) activity).getLifecycle().b().isAtLeast(Lifecycle.State.STARTED)) {
                        activity.onConfigurationChanged(configuration2);
                    }
                } else if (this.U) {
                    activity.onConfigurationChanged(configuration2);
                }
            }
        }
    }

    public void N(int i8, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i8 >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.P;
                if (i8 < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i8];
                }
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.f359j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.f364o) && !this.V) {
            this.f339p.a().onPanelClosed(i8, menu);
        }
    }

    public final int N0(b0 b0Var, Rect rect) {
        boolean z7;
        boolean z8;
        int iH = b0Var != null ? b0Var.h() : rect != null ? rect.top : 0;
        ActionBarContextView actionBarContextView = this.f348y;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z7 = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f348y.getLayoutParams();
            if (this.f348y.isShown()) {
                if (this.f333g0 == null) {
                    this.f333g0 = new Rect();
                    this.f334h0 = new Rect();
                }
                Rect rect2 = this.f333g0;
                Rect rect3 = this.f334h0;
                if (b0Var == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(b0Var.f(), b0Var.h(), b0Var.g(), b0Var.e());
                }
                k0.a(this.E, rect2, rect3);
                int i8 = rect2.top;
                int i9 = rect2.left;
                int i10 = rect2.right;
                b0 b0VarH = o0.t.H(this.E);
                int iF = b0VarH == null ? 0 : b0VarH.f();
                int iG = b0VarH == null ? 0 : b0VarH.g();
                if (marginLayoutParams.topMargin == i8 && marginLayoutParams.leftMargin == i9 && marginLayoutParams.rightMargin == i10) {
                    z8 = false;
                } else {
                    marginLayoutParams.topMargin = i8;
                    marginLayoutParams.leftMargin = i9;
                    marginLayoutParams.rightMargin = i10;
                    z8 = true;
                }
                if (i8 <= 0 || this.G != null) {
                    View view = this.G;
                    if (view != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                        int i11 = marginLayoutParams2.height;
                        int i12 = marginLayoutParams.topMargin;
                        if (i11 != i12 || marginLayoutParams2.leftMargin != iF || marginLayoutParams2.rightMargin != iG) {
                            marginLayoutParams2.height = i12;
                            marginLayoutParams2.leftMargin = iF;
                            marginLayoutParams2.rightMargin = iG;
                            this.G.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view2 = new View(this.f337n);
                    this.G = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = iF;
                    layoutParams.rightMargin = iG;
                    this.E.addView(this.G, -1, layoutParams);
                }
                View view3 = this.G;
                z = view3 != null;
                if (z && view3.getVisibility() != 0) {
                    O0(this.G);
                }
                if (!this.L && z) {
                    iH = 0;
                }
                z7 = z;
                z = z8;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z7 = false;
            } else {
                z7 = false;
                z = false;
            }
            if (z) {
                this.f348y.setLayoutParams(marginLayoutParams);
            }
        }
        View view4 = this.G;
        if (view4 != null) {
            view4.setVisibility(z7 ? 0 : 8);
        }
        return iH;
    }

    public void O(q.g gVar) {
        if (this.O) {
            return;
        }
        this.O = true;
        this.f344u.l();
        Window.Callback callbackI0 = i0();
        if (callbackI0 != null && !this.V) {
            callbackI0.onPanelClosed(108, gVar);
        }
        this.O = false;
    }

    public final void O0(View view) {
        view.setBackgroundColor((o0.t.J(view) & 8192) != 0 ? e0.a.b(this.f337n, R$color.abc_decor_view_status_guard_light) : e0.a.b(this.f337n, R$color.abc_decor_view_status_guard));
    }

    public final void P() {
        m mVar = this.f327a0;
        if (mVar != null) {
            mVar.a();
        }
        m mVar2 = this.f328b0;
        if (mVar2 != null) {
            mVar2.a();
        }
    }

    public void Q(int i8) {
        R(g0(i8, true), true);
    }

    public void R(PanelFeatureState panelFeatureState, boolean z7) {
        ViewGroup viewGroup;
        r.o oVar;
        if (z7 && panelFeatureState.f350a == 0 && (oVar = this.f344u) != null && oVar.b()) {
            O(panelFeatureState.f359j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f337n.getSystemService("window");
        if (windowManager != null && panelFeatureState.f364o && (viewGroup = panelFeatureState.f356g) != null) {
            windowManager.removeView(viewGroup);
            if (z7) {
                N(panelFeatureState.f350a, panelFeatureState, null);
            }
        }
        panelFeatureState.f362m = false;
        panelFeatureState.f363n = false;
        panelFeatureState.f364o = false;
        panelFeatureState.f357h = null;
        panelFeatureState.f366q = true;
        if (this.Q == panelFeatureState) {
            this.Q = null;
        }
    }

    public final Configuration S(Context context, int i8, Configuration configuration) {
        int i9 = i8 != 1 ? i8 != 2 ? context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i9 | (configuration2.uiMode & (-49));
        return configuration2;
    }

    public final ViewGroup T() {
        ViewGroup viewGroup;
        TypedArray typedArrayObtainStyledAttributes = this.f337n.obtainStyledAttributes(R$styleable.AppCompatTheme);
        int i8 = R$styleable.AppCompatTheme_windowActionBar;
        if (!typedArrayObtainStyledAttributes.hasValue(i8)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowNoTitle, false)) {
            A(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(i8, false)) {
            A(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            A(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            A(10);
        }
        this.M = typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_android_windowIsFloating, false);
        typedArrayObtainStyledAttributes.recycle();
        a0();
        this.f338o.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f337n);
        if (this.N) {
            viewGroup = this.L ? (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple, (ViewGroup) null);
        } else if (this.M) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_dialog_title_material, (ViewGroup) null);
            this.K = false;
            this.J = false;
        } else if (this.J) {
            TypedValue typedValue = new TypedValue();
            this.f337n.getTheme().resolveAttribute(R$attr.actionBarTheme, typedValue, true);
            int i9 = typedValue.resourceId;
            viewGroup = (ViewGroup) LayoutInflater.from(i9 != 0 ? new p.d(this.f337n, i9) : this.f337n).inflate(R$layout.abc_screen_toolbar, (ViewGroup) null);
            r.o oVar = (r.o) viewGroup.findViewById(R$id.decor_content_parent);
            this.f344u = oVar;
            oVar.setWindowCallback(i0());
            if (this.K) {
                this.f344u.k(109);
            }
            if (this.H) {
                this.f344u.k(2);
            }
            if (this.I) {
                this.f344u.k(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.J + ", windowActionBarOverlay: " + this.K + ", android:windowIsFloating: " + this.M + ", windowActionModeOverlay: " + this.L + ", windowNoTitle: " + this.N + " }");
        }
        if (Build.VERSION.SDK_INT >= 21) {
            o0.t.y0(viewGroup, new c());
        } else if (viewGroup instanceof r.s) {
            ((r.s) viewGroup).setOnFitSystemWindowsListener(new d());
        }
        if (this.f344u == null) {
            this.F = (TextView) viewGroup.findViewById(R$id.title);
        }
        k0.c(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(R$id.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.f338o.findViewById(R.id.content);
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
        this.f338o.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new e());
        return viewGroup;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View U(View view, String str, Context context, AttributeSet attributeSet) {
        boolean zH0 = false;
        if (this.f335i0 == null) {
            String string = this.f337n.obtainStyledAttributes(R$styleable.AppCompatTheme).getString(R$styleable.AppCompatTheme_viewInflaterClass);
            if (string == null) {
                this.f335i0 = new l.g();
            } else {
                try {
                    this.f335i0 = (l.g) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.f335i0 = new l.g();
                }
            }
        }
        boolean z7 = f322h;
        if (z7) {
            if (!(attributeSet instanceof XmlPullParser)) {
                zH0 = H0((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                zH0 = true;
            }
        }
        return this.f335i0.q(view, str, context, attributeSet, zH0, z7, true, j0.b());
    }

    public void V() {
        q.g gVar;
        r.o oVar = this.f344u;
        if (oVar != null) {
            oVar.l();
        }
        if (this.f349z != null) {
            this.f338o.getDecorView().removeCallbacks(this.A);
            if (this.f349z.isShowing()) {
                try {
                    this.f349z.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.f349z = null;
        }
        Y();
        PanelFeatureState panelFeatureStateG0 = g0(0, false);
        if (panelFeatureStateG0 == null || (gVar = panelFeatureStateG0.f359j) == null) {
            return;
        }
        gVar.close();
    }

    public boolean W(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.f336m;
        if (((obj instanceof e.a) || (obj instanceof l.f)) && (decorView = this.f338o.getDecorView()) != null && o0.e.d(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.f339p.a().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? s0(keyCode, keyEvent) : v0(keyCode, keyEvent);
    }

    public void X(int i8) {
        PanelFeatureState panelFeatureStateG0;
        PanelFeatureState panelFeatureStateG02 = g0(i8, true);
        if (panelFeatureStateG02.f359j != null) {
            Bundle bundle = new Bundle();
            panelFeatureStateG02.f359j.T(bundle);
            if (bundle.size() > 0) {
                panelFeatureStateG02.f368s = bundle;
            }
            panelFeatureStateG02.f359j.h0();
            panelFeatureStateG02.f359j.clear();
        }
        panelFeatureStateG02.f367r = true;
        panelFeatureStateG02.f366q = true;
        if ((i8 != 108 && i8 != 0) || this.f344u == null || (panelFeatureStateG0 = g0(0, false)) == null) {
            return;
        }
        panelFeatureStateG0.f362m = false;
        D0(panelFeatureStateG0, null);
    }

    public void Y() {
        x xVar = this.B;
        if (xVar != null) {
            xVar.b();
        }
    }

    public final void Z() {
        if (this.D) {
            return;
        }
        this.E = T();
        CharSequence charSequenceH0 = h0();
        if (!TextUtils.isEmpty(charSequenceH0)) {
            r.o oVar = this.f344u;
            if (oVar != null) {
                oVar.setWindowTitle(charSequenceH0);
            } else if (B0() != null) {
                B0().t(charSequenceH0);
            } else {
                TextView textView = this.F;
                if (textView != null) {
                    textView.setText(charSequenceH0);
                }
            }
        }
        K();
        z0(this.E);
        this.D = true;
        PanelFeatureState panelFeatureStateG0 = g0(0, false);
        if (this.V) {
            return;
        }
        if (panelFeatureStateG0 == null || panelFeatureStateG0.f359j == null) {
            n0(108);
        }
    }

    @Override // q.g.a
    public boolean a(q.g gVar, MenuItem menuItem) {
        PanelFeatureState panelFeatureStateB0;
        Window.Callback callbackI0 = i0();
        if (callbackI0 == null || this.V || (panelFeatureStateB0 = b0(gVar.F())) == null) {
            return false;
        }
        return callbackI0.onMenuItemSelected(panelFeatureStateB0.f350a, menuItem);
    }

    public final void a0() {
        if (this.f338o == null) {
            Object obj = this.f336m;
            if (obj instanceof Activity) {
                L(((Activity) obj).getWindow());
            }
        }
        if (this.f338o == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    @Override // q.g.a
    public void b(q.g gVar) {
        E0(true);
    }

    public PanelFeatureState b0(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.P;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i8 = 0; i8 < length; i8++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i8];
            if (panelFeatureState != null && panelFeatureState.f359j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    @Override // l.e
    public void d(View view, ViewGroup.LayoutParams layoutParams) {
        Z();
        ((ViewGroup) this.E.findViewById(R.id.content)).addView(view, layoutParams);
        this.f339p.a().onContentChanged();
    }

    public final Context d0() {
        l.a aVarN = n();
        Context contextK = aVarN != null ? aVarN.k() : null;
        return contextK == null ? this.f337n : contextK;
    }

    public final m e0(Context context) {
        if (this.f328b0 == null) {
            this.f328b0 = new l(context);
        }
        return this.f328b0;
    }

    @Override // l.e
    public Context f(Context context) {
        this.S = true;
        int iQ0 = q0(context, M());
        if (f325k && (context instanceof ContextThemeWrapper)) {
            try {
                r.a((ContextThemeWrapper) context, S(context, iQ0, null));
                return context;
            } catch (IllegalStateException unused) {
            }
        }
        if (context instanceof p.d) {
            try {
                ((p.d) context).a(S(context, iQ0, null));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (!f324j) {
            return super.f(context);
        }
        try {
            Configuration configuration = context.getPackageManager().getResourcesForApplication(context.getApplicationInfo()).getConfiguration();
            Configuration configuration2 = context.getResources().getConfiguration();
            Configuration configurationS = S(context, iQ0, configuration.equals(configuration2) ? null : c0(configuration, configuration2));
            p.d dVar = new p.d(context, R$style.Theme_AppCompat_Empty);
            dVar.a(configurationS);
            boolean z7 = false;
            try {
                z7 = context.getTheme() != null;
            } catch (NullPointerException unused3) {
            }
            if (z7) {
                f.b.a(dVar.getTheme());
            }
            return super.f(dVar);
        } catch (PackageManager.NameNotFoundException e8) {
            throw new RuntimeException("Application failed to obtain resources from itself", e8);
        }
    }

    public final m f0(Context context) {
        if (this.f327a0 == null) {
            this.f327a0 = new n(l.k.a(context));
        }
        return this.f327a0;
    }

    public PanelFeatureState g0(int i8, boolean z7) {
        PanelFeatureState[] panelFeatureStateArr = this.P;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i8) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[i8 + 1];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.P = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i8];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        PanelFeatureState panelFeatureState2 = new PanelFeatureState(i8);
        panelFeatureStateArr[i8] = panelFeatureState2;
        return panelFeatureState2;
    }

    public final CharSequence h0() {
        Object obj = this.f336m;
        return obj instanceof Activity ? ((Activity) obj).getTitle() : this.f343t;
    }

    @Override // l.e
    public <T extends View> T i(int i8) {
        Z();
        return (T) this.f338o.findViewById(i8);
    }

    public final Window.Callback i0() {
        return this.f338o.getCallback();
    }

    public final void j0() {
        Z();
        if (this.J && this.f341r == null) {
            Object obj = this.f336m;
            if (obj instanceof Activity) {
                this.f341r = new l.l((Activity) this.f336m, this.K);
            } else if (obj instanceof Dialog) {
                this.f341r = new l.l((Dialog) this.f336m);
            }
            l.a aVar = this.f341r;
            if (aVar != null) {
                aVar.r(this.f332f0);
            }
        }
    }

    @Override // l.e
    public final l.b k() {
        return new h();
    }

    public final boolean k0(PanelFeatureState panelFeatureState) {
        View view = panelFeatureState.f358i;
        if (view != null) {
            panelFeatureState.f357h = view;
            return true;
        }
        if (panelFeatureState.f359j == null) {
            return false;
        }
        if (this.f346w == null) {
            this.f346w = new t();
        }
        View view2 = (View) panelFeatureState.a(this.f346w);
        panelFeatureState.f357h = view2;
        return view2 != null;
    }

    @Override // l.e
    public int l() {
        return this.W;
    }

    public final boolean l0(PanelFeatureState panelFeatureState) {
        panelFeatureState.d(d0());
        panelFeatureState.f356g = new s(panelFeatureState.f361l);
        panelFeatureState.f352c = 81;
        return true;
    }

    @Override // l.e
    public MenuInflater m() {
        if (this.f342s == null) {
            j0();
            l.a aVar = this.f341r;
            this.f342s = new p.g(aVar != null ? aVar.k() : this.f337n);
        }
        return this.f342s;
    }

    public final boolean m0(PanelFeatureState panelFeatureState) {
        Context context = this.f337n;
        int i8 = panelFeatureState.f350a;
        if ((i8 == 0 || i8 == 108) && this.f344u != null) {
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
                p.d dVar = new p.d(context, 0);
                dVar.getTheme().setTo(themeNewTheme);
                context = dVar;
            }
        }
        q.g gVar = new q.g(context);
        gVar.V(this);
        panelFeatureState.c(gVar);
        return true;
    }

    @Override // l.e
    public l.a n() {
        j0();
        return this.f341r;
    }

    public final void n0(int i8) {
        this.f330d0 = (1 << i8) | this.f330d0;
        if (this.f329c0) {
            return;
        }
        o0.t.d0(this.f338o.getDecorView(), this.f331e0);
        this.f329c0 = true;
    }

    @Override // l.e
    public void o() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f337n);
        if (layoutInflaterFrom.getFactory() == null) {
            o0.f.b(layoutInflaterFrom, this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof AppCompatDelegateImpl) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public final boolean o0() {
        if (!this.Z && (this.f336m instanceof Activity)) {
            PackageManager packageManager = this.f337n.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            try {
                int i8 = Build.VERSION.SDK_INT;
                ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(this.f337n, this.f336m.getClass()), i8 >= 29 ? 269221888 : i8 >= 24 ? 786432 : 0);
                this.Y = (activityInfo == null || (activityInfo.configChanges & 512) == 0) ? false : true;
            } catch (PackageManager.NameNotFoundException e8) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e8);
                this.Y = false;
            }
        }
        this.Z = true;
        return this.Y;
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return U(view, str, context, attributeSet);
    }

    @Override // l.e
    public void p() {
        l.a aVarN = n();
        if (aVarN == null || !aVarN.l()) {
            n0(0);
        }
    }

    public boolean p0() {
        return this.C;
    }

    @Override // l.e
    public void q(Configuration configuration) {
        l.a aVarN;
        if (this.J && this.D && (aVarN = n()) != null) {
            aVarN.m(configuration);
        }
        r.f.b().g(this.f337n);
        J(false);
    }

    public int q0(Context context, int i8) {
        if (i8 == -100) {
            return -1;
        }
        if (i8 != -1) {
            if (i8 == 0) {
                if (Build.VERSION.SDK_INT < 23 || ((UiModeManager) context.getApplicationContext().getSystemService(UiModeManager.class)).getNightMode() != 0) {
                    return f0(context).c();
                }
                return -1;
            }
            if (i8 != 1 && i8 != 2) {
                if (i8 == 3) {
                    return e0(context).c();
                }
                throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
            }
        }
        return i8;
    }

    @Override // l.e
    public void r(Bundle bundle) {
        this.S = true;
        J(false);
        a0();
        Object obj = this.f336m;
        if (obj instanceof Activity) {
            String strC = null;
            try {
                strC = d0.d.c((Activity) obj);
            } catch (IllegalArgumentException unused) {
            }
            if (strC != null) {
                l.a aVarB0 = B0();
                if (aVarB0 == null) {
                    this.f332f0 = true;
                } else {
                    aVarB0.r(true);
                }
            }
            l.e.c(this);
        }
        this.T = true;
    }

    public boolean r0() {
        p.b bVar = this.f347x;
        if (bVar != null) {
            bVar.c();
            return true;
        }
        l.a aVarN = n();
        return aVarN != null && aVarN.h();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0048  */
    @Override // l.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void s() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f336m
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L9
            l.e.y(r3)
        L9:
            boolean r0 = r3.f329c0
            if (r0 == 0) goto L18
            android.view.Window r0 = r3.f338o
            android.view.View r0 = r0.getDecorView()
            java.lang.Runnable r1 = r3.f331e0
            r0.removeCallbacks(r1)
        L18:
            r0 = 0
            r3.U = r0
            r0 = 1
            r3.V = r0
            int r0 = r3.W
            r1 = -100
            if (r0 == r1) goto L48
            java.lang.Object r0 = r3.f336m
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L48
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L48
            v.g<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.f321g
            java.lang.Object r1 = r3.f336m
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.W
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L57
        L48:
            v.g<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.f321g
            java.lang.Object r1 = r3.f336m
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L57:
            l.a r0 = r3.f341r
            if (r0 == 0) goto L5e
            r0.n()
        L5e:
            r3.P()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.s():void");
    }

    public boolean s0(int i8, KeyEvent keyEvent) {
        if (i8 == 4) {
            this.R = (keyEvent.getFlags() & 128) != 0;
        } else if (i8 == 82) {
            t0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // l.e
    public void t(Bundle bundle) {
        Z();
    }

    public final boolean t0(int i8, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        PanelFeatureState panelFeatureStateG0 = g0(i8, true);
        if (panelFeatureStateG0.f364o) {
            return false;
        }
        return D0(panelFeatureStateG0, keyEvent);
    }

    @Override // l.e
    public void u() {
        l.a aVarN = n();
        if (aVarN != null) {
            aVarN.s(true);
        }
    }

    public boolean u0(int i8, KeyEvent keyEvent) {
        l.a aVarN = n();
        if (aVarN != null && aVarN.o(i8, keyEvent)) {
            return true;
        }
        PanelFeatureState panelFeatureState = this.Q;
        if (panelFeatureState != null && C0(panelFeatureState, keyEvent.getKeyCode(), keyEvent, 1)) {
            PanelFeatureState panelFeatureState2 = this.Q;
            if (panelFeatureState2 != null) {
                panelFeatureState2.f363n = true;
            }
            return true;
        }
        if (this.Q == null) {
            PanelFeatureState panelFeatureStateG0 = g0(0, true);
            D0(panelFeatureStateG0, keyEvent);
            boolean zC0 = C0(panelFeatureStateG0, keyEvent.getKeyCode(), keyEvent, 1);
            panelFeatureStateG0.f362m = false;
            if (zC0) {
                return true;
            }
        }
        return false;
    }

    @Override // l.e
    public void v(Bundle bundle) {
    }

    public boolean v0(int i8, KeyEvent keyEvent) {
        if (i8 == 4) {
            boolean z7 = this.R;
            this.R = false;
            PanelFeatureState panelFeatureStateG0 = g0(0, false);
            if (panelFeatureStateG0 != null && panelFeatureStateG0.f364o) {
                if (!z7) {
                    R(panelFeatureStateG0, true);
                }
                return true;
            }
            if (r0()) {
                return true;
            }
        } else if (i8 == 82) {
            w0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // l.e
    public void w() {
        this.U = true;
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
            p.b r0 = r4.f347x
            r1 = 0
            if (r0 == 0) goto L6
            return r1
        L6:
            r0 = 1
            androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r2 = r4.g0(r5, r0)
            if (r5 != 0) goto L43
            r.o r5 = r4.f344u
            if (r5 == 0) goto L43
            boolean r5 = r5.g()
            if (r5 == 0) goto L43
            android.content.Context r5 = r4.f337n
            android.view.ViewConfiguration r5 = android.view.ViewConfiguration.get(r5)
            boolean r5 = r5.hasPermanentMenuKey()
            if (r5 != 0) goto L43
            r.o r5 = r4.f344u
            boolean r5 = r5.b()
            if (r5 != 0) goto L3c
            boolean r5 = r4.V
            if (r5 != 0) goto L62
            boolean r5 = r4.D0(r2, r6)
            if (r5 == 0) goto L62
            r.o r5 = r4.f344u
            boolean r0 = r5.f()
            goto L68
        L3c:
            r.o r5 = r4.f344u
            boolean r0 = r5.e()
            goto L68
        L43:
            boolean r5 = r2.f364o
            if (r5 != 0) goto L64
            boolean r3 = r2.f363n
            if (r3 == 0) goto L4c
            goto L64
        L4c:
            boolean r5 = r2.f362m
            if (r5 == 0) goto L62
            boolean r5 = r2.f367r
            if (r5 == 0) goto L5b
            r2.f362m = r1
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
            android.content.Context r5 = r4.f337n
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

    @Override // l.e
    public void x() {
        this.U = false;
        l.a aVarN = n();
        if (aVarN != null) {
            aVarN.s(false);
        }
    }

    public void x0(int i8) {
        l.a aVarN;
        if (i8 != 108 || (aVarN = n()) == null) {
            return;
        }
        aVarN.i(true);
    }

    public void y0(int i8) {
        if (i8 == 108) {
            l.a aVarN = n();
            if (aVarN != null) {
                aVarN.i(false);
                return;
            }
            return;
        }
        if (i8 == 0) {
            PanelFeatureState panelFeatureStateG0 = g0(i8, true);
            if (panelFeatureStateG0.f364o) {
                R(panelFeatureStateG0, false);
            }
        }
    }

    public void z0(ViewGroup viewGroup) {
    }

    public AppCompatDelegateImpl(Dialog dialog, l.d dVar) {
        this(dialog.getContext(), dialog.getWindow(), dVar, dialog);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    public AppCompatDelegateImpl(Context context, Window window, l.d dVar, Object obj) {
        v.g<String, Integer> gVar;
        Integer num;
        AppCompatActivity appCompatActivityK0;
        this.B = null;
        this.C = true;
        this.W = -100;
        this.f331e0 = new b();
        this.f337n = context;
        this.f340q = dVar;
        this.f336m = obj;
        if (this.W == -100 && (obj instanceof Dialog) && (appCompatActivityK0 = K0()) != null) {
            this.W = appCompatActivityK0.getDelegate().l();
        }
        if (this.W == -100 && (num = (gVar = f321g).get(obj.getClass().getName())) != null) {
            this.W = num.intValue();
            gVar.remove(obj.getClass().getName());
        }
        if (window != null) {
            L(window);
        }
        r.f.h();
    }

    public class k extends p.i {
        public k(Window.Callback callback) {
            super(callback);
        }

        public final ActionMode b(ActionMode.Callback callback) {
            f.a aVar = new f.a(AppCompatDelegateImpl.this.f337n, callback);
            p.b bVarH = AppCompatDelegateImpl.this.H(aVar);
            if (bVarH != null) {
                return aVar.e(bVarH);
            }
            return null;
        }

        @Override // p.i, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.W(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // p.i, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.u0(keyEvent.getKeyCode(), keyEvent);
        }

        @Override // p.i, android.view.Window.Callback
        public void onContentChanged() {
        }

        @Override // p.i, android.view.Window.Callback
        public boolean onCreatePanelMenu(int i8, Menu menu) {
            if (i8 != 0 || (menu instanceof q.g)) {
                return super.onCreatePanelMenu(i8, menu);
            }
            return false;
        }

        @Override // p.i, android.view.Window.Callback
        public boolean onMenuOpened(int i8, Menu menu) {
            super.onMenuOpened(i8, menu);
            AppCompatDelegateImpl.this.x0(i8);
            return true;
        }

        @Override // p.i, android.view.Window.Callback
        public void onPanelClosed(int i8, Menu menu) {
            super.onPanelClosed(i8, menu);
            AppCompatDelegateImpl.this.y0(i8);
        }

        @Override // p.i, android.view.Window.Callback
        public boolean onPreparePanel(int i8, View view, Menu menu) {
            q.g gVar = menu instanceof q.g ? (q.g) menu : null;
            if (i8 == 0 && gVar == null) {
                return false;
            }
            if (gVar != null) {
                gVar.e0(true);
            }
            boolean zOnPreparePanel = super.onPreparePanel(i8, view, menu);
            if (gVar != null) {
                gVar.e0(false);
            }
            return zOnPreparePanel;
        }

        @Override // p.i, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i8) {
            q.g gVar;
            PanelFeatureState panelFeatureStateG0 = AppCompatDelegateImpl.this.g0(0, true);
            if (panelFeatureStateG0 == null || (gVar = panelFeatureStateG0.f359j) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i8);
            } else {
                super.onProvideKeyboardShortcuts(list, gVar, i8);
            }
        }

        @Override // p.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            return AppCompatDelegateImpl.this.p0() ? b(callback) : super.onWindowStartingActionMode(callback);
        }

        @Override // p.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i8) {
            if (AppCompatDelegateImpl.this.p0() && i8 == 0) {
                return b(callback);
            }
            return super.onWindowStartingActionMode(callback, i8);
        }
    }
}
