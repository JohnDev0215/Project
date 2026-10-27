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
import c0.f;
import java.lang.Thread;
import java.util.List;
import l0.b0;
import l0.e;
import l0.x;
import l0.z;
import m.b;
import m.f;
import n.g;
import n.m;
import o.e0;
import o.j0;
import o.k0;
import o.s;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatDelegateImpl extends i.e implements g.a, LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final s.g<String, Integer> f214e = new s.g<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final boolean f215f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f216g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final boolean f217h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final boolean f218i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static boolean f219j;
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

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public boolean f220a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public int f221b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public final Runnable f222c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public boolean f223d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public Rect f224e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public Rect f225f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public i.g f226g0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Object f227k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Context f228l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Window f229m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public k f230n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final i.d f231o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public i.a f232p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public MenuInflater f233q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public CharSequence f234r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public o.o f235s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public i f236t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public t f237u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public m.b f238v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ActionBarContextView f239w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public PopupWindow f240x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Runnable f241y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public x f242z;

    public static final class PanelFeatureState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f243a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f244b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f245c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f246d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f247e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f248f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ViewGroup f249g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public View f250h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public View f251i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public n.g f252j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public n.e f253k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public Context f254l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public boolean f255m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public boolean f256n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public boolean f257o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public boolean f258p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public boolean f259q = false;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public boolean f260r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public Bundle f261s;

        @SuppressLint({"BanParcelableUsage"})
        public static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new a();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f262b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public boolean f263c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public Bundle f264d;

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
                public SavedState[] newArray(int i3) {
                    return new SavedState[i3];
                }
            }

            public static SavedState f(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.f262b = parcel.readInt();
                boolean z3 = parcel.readInt() == 1;
                savedState.f263c = z3;
                if (z3) {
                    savedState.f264d = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i3) {
                parcel.writeInt(this.f262b);
                parcel.writeInt(this.f263c ? 1 : 0);
                if (this.f263c) {
                    parcel.writeBundle(this.f264d);
                }
            }
        }

        public PanelFeatureState(int i3) {
            this.f243a = i3;
        }

        public n.n a(m.a aVar) {
            if (this.f252j == null) {
                return null;
            }
            if (this.f253k == null) {
                n.e eVar = new n.e(this.f254l, R$layout.abc_list_menu_item_layout);
                this.f253k = eVar;
                eVar.k(aVar);
                this.f252j.b(this.f253k);
            }
            return this.f253k.g(this.f249g);
        }

        public boolean b() {
            if (this.f250h == null) {
                return false;
            }
            return this.f251i != null || this.f253k.b().getCount() > 0;
        }

        public void c(n.g gVar) {
            n.e eVar;
            n.g gVar2 = this.f252j;
            if (gVar == gVar2) {
                return;
            }
            if (gVar2 != null) {
                gVar2.Q(this.f253k);
            }
            this.f252j = gVar;
            if (gVar == null || (eVar = this.f253k) == null) {
                return;
            }
            gVar.b(eVar);
        }

        public void d(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme themeNewTheme = context.getResources().newTheme();
            themeNewTheme.setTo(context.getTheme());
            themeNewTheme.resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true);
            int i3 = typedValue.resourceId;
            if (i3 != 0) {
                themeNewTheme.applyStyle(i3, true);
            }
            themeNewTheme.resolveAttribute(R$attr.panelMenuListTheme, typedValue, true);
            int i4 = typedValue.resourceId;
            if (i4 != 0) {
                themeNewTheme.applyStyle(i4, true);
            } else {
                themeNewTheme.applyStyle(R$style.Theme_AppCompat_CompactMenu, true);
            }
            m.d dVar = new m.d(context, 0);
            dVar.getTheme().setTo(themeNewTheme);
            this.f254l = dVar;
            TypedArray typedArrayObtainStyledAttributes = dVar.obtainStyledAttributes(R$styleable.AppCompatTheme);
            this.f244b = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_panelBackground, 0);
            this.f248f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public class a implements Thread.UncaughtExceptionHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Thread.UncaughtExceptionHandler f265a;

        public a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.f265a = uncaughtExceptionHandler;
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
                this.f265a.uncaughtException(thread, th);
                return;
            }
            Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            notFoundException.initCause(th.getCause());
            notFoundException.setStackTrace(th.getStackTrace());
            this.f265a.uncaughtException(thread, notFoundException);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl.f221b0 & 1) != 0) {
                appCompatDelegateImpl.X(0);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl2.f221b0 & 4096) != 0) {
                appCompatDelegateImpl2.X(108);
            }
            AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl3.f220a0 = false;
            appCompatDelegateImpl3.f221b0 = 0;
        }
    }

    public class c implements l0.p {
        public c() {
        }

        @Override // l0.p
        public b0 a(View view, b0 b0Var) {
            int iH = b0Var.h();
            int iN0 = AppCompatDelegateImpl.this.N0(b0Var, null);
            if (iH != iN0) {
                b0Var = b0Var.n(b0Var.f(), iN0, b0Var.g(), b0Var.e());
            }
            return l0.t.W(view, b0Var);
        }
    }

    public class d implements s.a {
        public d() {
        }

        @Override // o.s.a
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

            @Override // l0.y
            public void a(View view) {
                AppCompatDelegateImpl.this.f239w.setAlpha(1.0f);
                AppCompatDelegateImpl.this.f242z.f(null);
                AppCompatDelegateImpl.this.f242z = null;
            }

            @Override // l0.z, l0.y
            public void b(View view) {
                AppCompatDelegateImpl.this.f239w.setVisibility(0);
            }
        }

        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            appCompatDelegateImpl.f240x.showAtLocation(appCompatDelegateImpl.f239w, 55, 0, 0);
            AppCompatDelegateImpl.this.Y();
            if (!AppCompatDelegateImpl.this.G0()) {
                AppCompatDelegateImpl.this.f239w.setAlpha(1.0f);
                AppCompatDelegateImpl.this.f239w.setVisibility(0);
            } else {
                AppCompatDelegateImpl.this.f239w.setAlpha(0.0f);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.f242z = l0.t.c(appCompatDelegateImpl2.f239w).a(1.0f);
                AppCompatDelegateImpl.this.f242z.f(new a());
            }
        }
    }

    public class g extends z {
        public g() {
        }

        @Override // l0.y
        public void a(View view) {
            AppCompatDelegateImpl.this.f239w.setAlpha(1.0f);
            AppCompatDelegateImpl.this.f242z.f(null);
            AppCompatDelegateImpl.this.f242z = null;
        }

        @Override // l0.z, l0.y
        public void b(View view) {
            AppCompatDelegateImpl.this.f239w.setVisibility(0);
            AppCompatDelegateImpl.this.f239w.sendAccessibilityEvent(32);
            if (AppCompatDelegateImpl.this.f239w.getParent() instanceof View) {
                l0.t.g0((View) AppCompatDelegateImpl.this.f239w.getParent());
            }
        }
    }

    public class h implements i.b {
        public h() {
        }
    }

    public final class i implements m.a {
        public i() {
        }

        @Override // n.m.a
        public void a(n.g gVar, boolean z3) {
            AppCompatDelegateImpl.this.O(gVar);
        }

        @Override // n.m.a
        public boolean b(n.g gVar) {
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
        public b.a f275a;

        public class a extends z {
            public a() {
            }

            @Override // l0.y
            public void a(View view) {
                AppCompatDelegateImpl.this.f239w.setVisibility(8);
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                PopupWindow popupWindow = appCompatDelegateImpl.f240x;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (appCompatDelegateImpl.f239w.getParent() instanceof View) {
                    l0.t.g0((View) AppCompatDelegateImpl.this.f239w.getParent());
                }
                AppCompatDelegateImpl.this.f239w.removeAllViews();
                AppCompatDelegateImpl.this.f242z.f(null);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.f242z = null;
                l0.t.g0(appCompatDelegateImpl2.C);
            }
        }

        public j(b.a aVar) {
            this.f275a = aVar;
        }

        @Override // m.b.a
        public boolean a(m.b bVar, Menu menu) {
            l0.t.g0(AppCompatDelegateImpl.this.C);
            return this.f275a.a(bVar, menu);
        }

        @Override // m.b.a
        public boolean b(m.b bVar, MenuItem menuItem) {
            return this.f275a.b(bVar, menuItem);
        }

        @Override // m.b.a
        public boolean c(m.b bVar, Menu menu) {
            return this.f275a.c(bVar, menu);
        }

        @Override // m.b.a
        public void d(m.b bVar) {
            this.f275a.d(bVar);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.f240x != null) {
                appCompatDelegateImpl.f229m.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.f241y);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.f239w != null) {
                appCompatDelegateImpl2.Y();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl3.f242z = l0.t.c(appCompatDelegateImpl3.f239w).a(0.0f);
                AppCompatDelegateImpl.this.f242z.f(new a());
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            i.d dVar = appCompatDelegateImpl4.f231o;
            if (dVar != null) {
                dVar.onSupportActionModeFinished(appCompatDelegateImpl4.f238v);
            }
            AppCompatDelegateImpl appCompatDelegateImpl5 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl5.f238v = null;
            l0.t.g0(appCompatDelegateImpl5.C);
        }
    }

    public class l extends m {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final PowerManager f279c;

        public l(Context context) {
            super();
            this.f279c = (PowerManager) context.getApplicationContext().getSystemService("power");
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
            return (Build.VERSION.SDK_INT < 21 || !this.f279c.isPowerSaveMode()) ? 1 : 2;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public void d() {
            AppCompatDelegateImpl.this.I();
        }
    }

    public abstract class m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public BroadcastReceiver f281a;

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
            BroadcastReceiver broadcastReceiver = this.f281a;
            if (broadcastReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.f228l.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.f281a = null;
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
            if (this.f281a == null) {
                this.f281a = new a();
            }
            AppCompatDelegateImpl.this.f228l.registerReceiver(this.f281a, intentFilterB);
        }
    }

    public class n extends m {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final i.k f284c;

        public n(i.k kVar) {
            super();
            this.f284c = kVar;
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
            return this.f284c.d() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public void d() {
            AppCompatDelegateImpl.this.I();
        }
    }

    public static class o {
        public static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i3 = configuration.densityDpi;
            int i4 = configuration2.densityDpi;
            if (i3 != i4) {
                configuration3.densityDpi = i4;
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
            int i3 = configuration.colorMode & 3;
            int i4 = configuration2.colorMode;
            if (i3 != (i4 & 3)) {
                configuration3.colorMode |= i4 & 3;
            }
            int i5 = configuration.colorMode & 12;
            int i6 = configuration2.colorMode;
            if (i5 != (i6 & 12)) {
                configuration3.colorMode |= i6 & 12;
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

        public final boolean c(int i3, int i4) {
            return i3 < -5 || i4 < -5 || i3 > getWidth() + 5 || i4 > getHeight() + 5;
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
        public void setBackgroundResource(int i3) {
            setBackgroundDrawable(j.a.d(getContext(), i3));
        }
    }

    public final class t implements m.a {
        public t() {
        }

        @Override // n.m.a
        public void a(n.g gVar, boolean z3) {
            n.g gVarF = gVar.F();
            boolean z4 = gVarF != gVar;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z4) {
                gVar = gVarF;
            }
            PanelFeatureState panelFeatureStateB0 = appCompatDelegateImpl.b0(gVar);
            if (panelFeatureStateB0 != null) {
                if (!z4) {
                    AppCompatDelegateImpl.this.R(panelFeatureStateB0, z3);
                } else {
                    AppCompatDelegateImpl.this.N(panelFeatureStateB0.f243a, panelFeatureStateB0, gVarF);
                    AppCompatDelegateImpl.this.R(panelFeatureStateB0, true);
                }
            }
        }

        @Override // n.m.a
        public boolean b(n.g gVar) {
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
        int i3 = Build.VERSION.SDK_INT;
        boolean z3 = i3 < 21;
        f215f = z3;
        f216g = new int[]{R.attr.windowBackground};
        f217h = !"robolectric".equals(Build.FINGERPRINT);
        f218i = i3 >= 17;
        if (!z3 || f219j) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new a(Thread.getDefaultUncaughtExceptionHandler()));
        f219j = true;
    }

    public AppCompatDelegateImpl(Activity activity, i.d dVar) {
        this(activity, null, dVar, activity);
    }

    public static Configuration c0(Configuration configuration, Configuration configuration2) {
        Configuration configuration3 = new Configuration();
        configuration3.fontScale = 0.0f;
        if (configuration2 != null && configuration.diff(configuration2) != 0) {
            float f3 = configuration.fontScale;
            float f4 = configuration2.fontScale;
            if (f3 != f4) {
                configuration3.fontScale = f4;
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
            int i7 = Build.VERSION.SDK_INT;
            if (i7 >= 24) {
                p.a(configuration, configuration2, configuration3);
            } else if (!k0.c.a(configuration.locale, configuration2.locale)) {
                configuration3.locale = configuration2.locale;
            }
            int i8 = configuration.touchscreen;
            int i9 = configuration2.touchscreen;
            if (i8 != i9) {
                configuration3.touchscreen = i9;
            }
            int i10 = configuration.keyboard;
            int i11 = configuration2.keyboard;
            if (i10 != i11) {
                configuration3.keyboard = i11;
            }
            int i12 = configuration.keyboardHidden;
            int i13 = configuration2.keyboardHidden;
            if (i12 != i13) {
                configuration3.keyboardHidden = i13;
            }
            int i14 = configuration.navigation;
            int i15 = configuration2.navigation;
            if (i14 != i15) {
                configuration3.navigation = i15;
            }
            int i16 = configuration.navigationHidden;
            int i17 = configuration2.navigationHidden;
            if (i16 != i17) {
                configuration3.navigationHidden = i17;
            }
            int i18 = configuration.orientation;
            int i19 = configuration2.orientation;
            if (i18 != i19) {
                configuration3.orientation = i19;
            }
            int i20 = configuration.screenLayout & 15;
            int i21 = configuration2.screenLayout;
            if (i20 != (i21 & 15)) {
                configuration3.screenLayout |= i21 & 15;
            }
            int i22 = configuration.screenLayout & 192;
            int i23 = configuration2.screenLayout;
            if (i22 != (i23 & 192)) {
                configuration3.screenLayout |= i23 & 192;
            }
            int i24 = configuration.screenLayout & 48;
            int i25 = configuration2.screenLayout;
            if (i24 != (i25 & 48)) {
                configuration3.screenLayout |= i25 & 48;
            }
            int i26 = configuration.screenLayout & 768;
            int i27 = configuration2.screenLayout;
            if (i26 != (i27 & 768)) {
                configuration3.screenLayout |= i27 & 768;
            }
            if (i7 >= 26) {
                q.a(configuration, configuration2, configuration3);
            }
            int i28 = configuration.uiMode & 15;
            int i29 = configuration2.uiMode;
            if (i28 != (i29 & 15)) {
                configuration3.uiMode |= i29 & 15;
            }
            int i30 = configuration.uiMode & 48;
            int i31 = configuration2.uiMode;
            if (i30 != (i31 & 48)) {
                configuration3.uiMode |= i31 & 48;
            }
            int i32 = configuration.screenWidthDp;
            int i33 = configuration2.screenWidthDp;
            if (i32 != i33) {
                configuration3.screenWidthDp = i33;
            }
            int i34 = configuration.screenHeightDp;
            int i35 = configuration2.screenHeightDp;
            if (i34 != i35) {
                configuration3.screenHeightDp = i35;
            }
            int i36 = configuration.smallestScreenWidthDp;
            int i37 = configuration2.smallestScreenWidthDp;
            if (i36 != i37) {
                configuration3.smallestScreenWidthDp = i37;
            }
            if (i7 >= 17) {
                o.a(configuration, configuration2, configuration3);
            }
        }
        return configuration3;
    }

    @Override // i.e
    public boolean A(int i3) {
        int iF0 = F0(i3);
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
            return this.f229m.requestFeature(iF0);
        }
        J0();
        this.I = true;
        return true;
    }

    public final void A0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        int i3;
        ViewGroup.LayoutParams layoutParams;
        if (panelFeatureState.f257o || this.T) {
            return;
        }
        if (panelFeatureState.f243a == 0) {
            if ((this.f228l.getResources().getConfiguration().screenLayout & 15) == 4) {
                return;
            }
        }
        Window.Callback callbackI0 = i0();
        if (callbackI0 != null && !callbackI0.onMenuOpened(panelFeatureState.f243a, panelFeatureState.f252j)) {
            R(panelFeatureState, true);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f228l.getSystemService("window");
        if (windowManager != null && D0(panelFeatureState, keyEvent)) {
            ViewGroup viewGroup = panelFeatureState.f249g;
            if (viewGroup != null && !panelFeatureState.f259q) {
                View view = panelFeatureState.f251i;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i3 = -1;
                }
                panelFeatureState.f256n = false;
                WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i3, -2, panelFeatureState.f246d, panelFeatureState.f247e, 1002, 8519680, -3);
                layoutParams2.gravity = panelFeatureState.f245c;
                layoutParams2.windowAnimations = panelFeatureState.f248f;
                windowManager.addView(panelFeatureState.f249g, layoutParams2);
                panelFeatureState.f257o = true;
            }
            if (viewGroup == null) {
                if (!l0(panelFeatureState) || panelFeatureState.f249g == null) {
                    return;
                }
            } else if (panelFeatureState.f259q && viewGroup.getChildCount() > 0) {
                panelFeatureState.f249g.removeAllViews();
            }
            if (!k0(panelFeatureState) || !panelFeatureState.b()) {
                panelFeatureState.f259q = true;
                return;
            }
            ViewGroup.LayoutParams layoutParams3 = panelFeatureState.f250h.getLayoutParams();
            if (layoutParams3 == null) {
                layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
            }
            panelFeatureState.f249g.setBackgroundResource(panelFeatureState.f244b);
            ViewParent parent = panelFeatureState.f250h.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(panelFeatureState.f250h);
            }
            panelFeatureState.f249g.addView(panelFeatureState.f250h, layoutParams3);
            if (!panelFeatureState.f250h.hasFocus()) {
                panelFeatureState.f250h.requestFocus();
            }
            i3 = -2;
            panelFeatureState.f256n = false;
            WindowManager.LayoutParams layoutParams22 = new WindowManager.LayoutParams(i3, -2, panelFeatureState.f246d, panelFeatureState.f247e, 1002, 8519680, -3);
            layoutParams22.gravity = panelFeatureState.f245c;
            layoutParams22.windowAnimations = panelFeatureState.f248f;
            windowManager.addView(panelFeatureState.f249g, layoutParams22);
            panelFeatureState.f257o = true;
        }
    }

    @Override // i.e
    public void B(int i3) {
        Z();
        ViewGroup viewGroup = (ViewGroup) this.C.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f228l).inflate(i3, viewGroup);
        this.f230n.a().onContentChanged();
    }

    public final i.a B0() {
        return this.f232p;
    }

    @Override // i.e
    public void C(View view) {
        Z();
        ViewGroup viewGroup = (ViewGroup) this.C.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.f230n.a().onContentChanged();
    }

    public final boolean C0(PanelFeatureState panelFeatureState, int i3, KeyEvent keyEvent, int i4) {
        n.g gVar;
        boolean zPerformShortcut = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.f255m || D0(panelFeatureState, keyEvent)) && (gVar = panelFeatureState.f252j) != null) {
            zPerformShortcut = gVar.performShortcut(i3, keyEvent, i4);
        }
        if (zPerformShortcut && (i4 & 1) == 0 && this.f235s == null) {
            R(panelFeatureState, true);
        }
        return zPerformShortcut;
    }

    @Override // i.e
    public void D(View view, ViewGroup.LayoutParams layoutParams) {
        Z();
        ViewGroup viewGroup = (ViewGroup) this.C.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.f230n.a().onContentChanged();
    }

    public final boolean D0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        o.o oVar;
        o.o oVar2;
        o.o oVar3;
        if (this.T) {
            return false;
        }
        if (panelFeatureState.f255m) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.O;
        if (panelFeatureState2 != null && panelFeatureState2 != panelFeatureState) {
            R(panelFeatureState2, false);
        }
        Window.Callback callbackI0 = i0();
        if (callbackI0 != null) {
            panelFeatureState.f251i = callbackI0.onCreatePanelView(panelFeatureState.f243a);
        }
        int i3 = panelFeatureState.f243a;
        boolean z3 = i3 == 0 || i3 == 108;
        if (z3 && (oVar3 = this.f235s) != null) {
            oVar3.f();
        }
        if (panelFeatureState.f251i == null && (!z3 || !(B0() instanceof i.i))) {
            n.g gVar = panelFeatureState.f252j;
            if (gVar == null || panelFeatureState.f260r) {
                if (gVar == null && (!m0(panelFeatureState) || panelFeatureState.f252j == null)) {
                    return false;
                }
                if (z3 && this.f235s != null) {
                    if (this.f236t == null) {
                        this.f236t = new i();
                    }
                    this.f235s.a(panelFeatureState.f252j, this.f236t);
                }
                panelFeatureState.f252j.h0();
                if (!callbackI0.onCreatePanelMenu(panelFeatureState.f243a, panelFeatureState.f252j)) {
                    panelFeatureState.c(null);
                    if (z3 && (oVar = this.f235s) != null) {
                        oVar.a(null, this.f236t);
                    }
                    return false;
                }
                panelFeatureState.f260r = false;
            }
            panelFeatureState.f252j.h0();
            Bundle bundle = panelFeatureState.f261s;
            if (bundle != null) {
                panelFeatureState.f252j.R(bundle);
                panelFeatureState.f261s = null;
            }
            if (!callbackI0.onPreparePanel(0, panelFeatureState.f251i, panelFeatureState.f252j)) {
                if (z3 && (oVar2 = this.f235s) != null) {
                    oVar2.a(null, this.f236t);
                }
                panelFeatureState.f252j.g0();
                return false;
            }
            boolean z4 = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            panelFeatureState.f258p = z4;
            panelFeatureState.f252j.setQwertyMode(z4);
            panelFeatureState.f252j.g0();
        }
        panelFeatureState.f255m = true;
        panelFeatureState.f256n = false;
        this.O = panelFeatureState;
        return true;
    }

    @Override // i.e
    public void E(Toolbar toolbar) {
        if (this.f227k instanceof Activity) {
            i.a aVarN = n();
            if (aVarN instanceof i.l) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            this.f233q = null;
            if (aVarN != null) {
                aVarN.n();
            }
            if (toolbar != null) {
                i.i iVar = new i.i(toolbar, h0(), this.f230n);
                this.f232p = iVar;
                this.f229m.setCallback(iVar.w());
            } else {
                this.f232p = null;
                this.f229m.setCallback(this.f230n);
            }
            p();
        }
    }

    public final void E0(boolean z3) {
        o.o oVar = this.f235s;
        if (oVar == null || !oVar.g() || (ViewConfiguration.get(this.f228l).hasPermanentMenuKey() && !this.f235s.b())) {
            PanelFeatureState panelFeatureStateG0 = g0(0, true);
            panelFeatureStateG0.f259q = true;
            R(panelFeatureStateG0, false);
            A0(panelFeatureStateG0, null);
            return;
        }
        Window.Callback callbackI0 = i0();
        if (this.f235s.c() && z3) {
            this.f235s.d();
            if (this.T) {
                return;
            }
            callbackI0.onPanelClosed(108, g0(0, true).f252j);
            return;
        }
        if (callbackI0 == null || this.T) {
            return;
        }
        if (this.f220a0 && (this.f221b0 & 1) != 0) {
            this.f229m.getDecorView().removeCallbacks(this.f222c0);
            this.f222c0.run();
        }
        PanelFeatureState panelFeatureStateG02 = g0(0, true);
        n.g gVar = panelFeatureStateG02.f252j;
        if (gVar == null || panelFeatureStateG02.f260r || !callbackI0.onPreparePanel(0, panelFeatureStateG02.f251i, gVar)) {
            return;
        }
        callbackI0.onMenuOpened(108, panelFeatureStateG02.f252j);
        this.f235s.e();
    }

    @Override // i.e
    public void F(int i3) {
        this.V = i3;
    }

    public final int F0(int i3) {
        if (i3 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        }
        if (i3 != 9) {
            return i3;
        }
        Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
        return 109;
    }

    @Override // i.e
    public final void G(CharSequence charSequence) {
        this.f234r = charSequence;
        o.o oVar = this.f235s;
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

    public final boolean G0() {
        ViewGroup viewGroup;
        return this.B && (viewGroup = this.C) != null && l0.t.P(viewGroup);
    }

    @Override // i.e
    public m.b H(b.a aVar) {
        i.d dVar;
        if (aVar == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        m.b bVar = this.f238v;
        if (bVar != null) {
            bVar.c();
        }
        j jVar = new j(aVar);
        i.a aVarN = n();
        if (aVarN != null) {
            m.b bVarU = aVarN.u(jVar);
            this.f238v = bVarU;
            if (bVarU != null && (dVar = this.f231o) != null) {
                dVar.onSupportActionModeStarted(bVarU);
            }
        }
        if (this.f238v == null) {
            this.f238v = I0(jVar);
        }
        return this.f238v;
    }

    public final boolean H0(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.f229m.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || l0.t.O((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    public boolean I() {
        return J(true);
    }

    public m.b I0(b.a aVar) {
        m.b bVarOnWindowStartingSupportActionMode;
        Context dVar;
        i.d dVar2;
        Y();
        m.b bVar = this.f238v;
        if (bVar != null) {
            bVar.c();
        }
        if (!(aVar instanceof j)) {
            aVar = new j(aVar);
        }
        i.d dVar3 = this.f231o;
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
            this.f238v = bVarOnWindowStartingSupportActionMode;
        } else {
            if (this.f239w == null) {
                if (this.K) {
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = this.f228l.getTheme();
                    theme.resolveAttribute(R$attr.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Resources.Theme themeNewTheme = this.f228l.getResources().newTheme();
                        themeNewTheme.setTo(theme);
                        themeNewTheme.applyStyle(typedValue.resourceId, true);
                        dVar = new m.d(this.f228l, 0);
                        dVar.getTheme().setTo(themeNewTheme);
                    } else {
                        dVar = this.f228l;
                    }
                    this.f239w = new ActionBarContextView(dVar);
                    PopupWindow popupWindow = new PopupWindow(dVar, (AttributeSet) null, R$attr.actionModePopupWindowStyle);
                    this.f240x = popupWindow;
                    n0.h.b(popupWindow, 2);
                    this.f240x.setContentView(this.f239w);
                    this.f240x.setWidth(-1);
                    dVar.getTheme().resolveAttribute(R$attr.actionBarSize, typedValue, true);
                    this.f239w.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, dVar.getResources().getDisplayMetrics()));
                    this.f240x.setHeight(-2);
                    this.f241y = new f();
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.C.findViewById(R$id.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(d0()));
                        this.f239w = (ActionBarContextView) viewStubCompat.a();
                    }
                }
            }
            if (this.f239w != null) {
                Y();
                this.f239w.k();
                m.e eVar = new m.e(this.f239w.getContext(), this.f239w, aVar, this.f240x == null);
                if (aVar.c(eVar, eVar.e())) {
                    eVar.k();
                    this.f239w.h(eVar);
                    this.f238v = eVar;
                    if (G0()) {
                        this.f239w.setAlpha(0.0f);
                        x xVarA = l0.t.c(this.f239w).a(1.0f);
                        this.f242z = xVarA;
                        xVarA.f(new g());
                    } else {
                        this.f239w.setAlpha(1.0f);
                        this.f239w.setVisibility(0);
                        this.f239w.sendAccessibilityEvent(32);
                        if (this.f239w.getParent() instanceof View) {
                            l0.t.g0((View) this.f239w.getParent());
                        }
                    }
                    if (this.f240x != null) {
                        this.f229m.getDecorView().post(this.f241y);
                    }
                } else {
                    this.f238v = null;
                }
            }
        }
        m.b bVar2 = this.f238v;
        if (bVar2 != null && (dVar2 = this.f231o) != null) {
            dVar2.onSupportActionModeStarted(bVar2);
        }
        return this.f238v;
    }

    public final boolean J(boolean z3) {
        if (this.T) {
            return false;
        }
        int iM = M();
        boolean zL0 = L0(q0(this.f228l, iM), z3);
        if (iM == 0) {
            f0(this.f228l).e();
        } else {
            m mVar = this.Y;
            if (mVar != null) {
                mVar.a();
            }
        }
        if (iM == 3) {
            e0(this.f228l).e();
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
        View decorView = this.f229m.getDecorView();
        contentFrameLayout.b(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray typedArrayObtainStyledAttributes = this.f228l.obtainStyledAttributes(R$styleable.AppCompatTheme);
        typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        typedArrayObtainStyledAttributes.getValue(R$styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        int i3 = R$styleable.AppCompatTheme_windowFixedWidthMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            typedArrayObtainStyledAttributes.getValue(i3, contentFrameLayout.getFixedWidthMajor());
        }
        int i4 = R$styleable.AppCompatTheme_windowFixedWidthMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            typedArrayObtainStyledAttributes.getValue(i4, contentFrameLayout.getFixedWidthMinor());
        }
        int i5 = R$styleable.AppCompatTheme_windowFixedHeightMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i5)) {
            typedArrayObtainStyledAttributes.getValue(i5, contentFrameLayout.getFixedHeightMajor());
        }
        int i6 = R$styleable.AppCompatTheme_windowFixedHeightMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i6)) {
            typedArrayObtainStyledAttributes.getValue(i6, contentFrameLayout.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    public final AppCompatActivity K0() {
        for (Context baseContext = this.f228l; baseContext != null; baseContext = ((ContextWrapper) baseContext).getBaseContext()) {
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
        if (this.f229m != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof k) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        k kVar = new k(callback);
        this.f230n = kVar;
        window.setCallback(kVar);
        e0 e0VarU = e0.u(this.f228l, null, f216g);
        Drawable drawableH = e0VarU.h(0);
        if (drawableH != null) {
            window.setBackgroundDrawable(drawableH);
        }
        e0VarU.w();
        this.f229m = window;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean L0(int r7, boolean r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.f228l
            r1 = 0
            android.content.res.Configuration r0 = r6.S(r0, r7, r1)
            boolean r2 = r6.o0()
            android.content.Context r3 = r6.f228l
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
            boolean r8 = androidx.appcompat.app.AppCompatDelegateImpl.f217h
            if (r8 != 0) goto L30
            boolean r8 = r6.R
            if (r8 == 0) goto L47
        L30:
            java.lang.Object r8 = r6.f227k
            boolean r5 = r8 instanceof android.app.Activity
            if (r5 == 0) goto L47
            android.app.Activity r8 = (android.app.Activity) r8
            boolean r8 = r8.isChild()
            if (r8 != 0) goto L47
            java.lang.Object r8 = r6.f227k
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
            java.lang.Object r8 = r6.f227k
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
        int i3 = this.U;
        return i3 != -100 ? i3 : i.e.j();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void M0(int i3, boolean z3, Configuration configuration) {
        Resources resources = this.f228l.getResources();
        Configuration configuration2 = new Configuration(resources.getConfiguration());
        if (configuration != null) {
            configuration2.updateFrom(configuration);
        }
        configuration2.uiMode = i3 | (resources.getConfiguration().uiMode & (-49));
        resources.updateConfiguration(configuration2, null);
        int i4 = Build.VERSION.SDK_INT;
        if (i4 < 26) {
            i.h.a(resources);
        }
        int i5 = this.V;
        if (i5 != 0) {
            this.f228l.setTheme(i5);
            if (i4 >= 23) {
                this.f228l.getTheme().applyStyle(this.V, true);
            }
        }
        if (z3) {
            Object obj = this.f227k;
            if (obj instanceof Activity) {
                Activity activity = (Activity) obj;
                if (activity instanceof t0.f) {
                    if (((t0.f) activity).getLifecycle().b().isAtLeast(Lifecycle.State.STARTED)) {
                        activity.onConfigurationChanged(configuration2);
                    }
                } else if (this.S) {
                    activity.onConfigurationChanged(configuration2);
                }
            }
        }
    }

    public void N(int i3, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i3 >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.N;
                if (i3 < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i3];
                }
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.f252j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.f257o) && !this.T) {
            this.f230n.a().onPanelClosed(i3, menu);
        }
    }

    public final int N0(b0 b0Var, Rect rect) {
        boolean z3;
        boolean z4;
        int iH = b0Var != null ? b0Var.h() : rect != null ? rect.top : 0;
        ActionBarContextView actionBarContextView = this.f239w;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z3 = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f239w.getLayoutParams();
            if (this.f239w.isShown()) {
                if (this.f224e0 == null) {
                    this.f224e0 = new Rect();
                    this.f225f0 = new Rect();
                }
                Rect rect2 = this.f224e0;
                Rect rect3 = this.f225f0;
                if (b0Var == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(b0Var.f(), b0Var.h(), b0Var.g(), b0Var.e());
                }
                k0.a(this.C, rect2, rect3);
                int i3 = rect2.top;
                int i4 = rect2.left;
                int i5 = rect2.right;
                b0 b0VarG = l0.t.G(this.C);
                int iF = b0VarG == null ? 0 : b0VarG.f();
                int iG = b0VarG == null ? 0 : b0VarG.g();
                if (marginLayoutParams.topMargin == i3 && marginLayoutParams.leftMargin == i4 && marginLayoutParams.rightMargin == i5) {
                    z4 = false;
                } else {
                    marginLayoutParams.topMargin = i3;
                    marginLayoutParams.leftMargin = i4;
                    marginLayoutParams.rightMargin = i5;
                    z4 = true;
                }
                if (i3 <= 0 || this.E != null) {
                    View view = this.E;
                    if (view != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                        int i6 = marginLayoutParams2.height;
                        int i7 = marginLayoutParams.topMargin;
                        if (i6 != i7 || marginLayoutParams2.leftMargin != iF || marginLayoutParams2.rightMargin != iG) {
                            marginLayoutParams2.height = i7;
                            marginLayoutParams2.leftMargin = iF;
                            marginLayoutParams2.rightMargin = iG;
                            this.E.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view2 = new View(this.f228l);
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
                z3 = z;
                z = z4;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z3 = false;
            } else {
                z3 = false;
                z = false;
            }
            if (z) {
                this.f239w.setLayoutParams(marginLayoutParams);
            }
        }
        View view4 = this.E;
        if (view4 != null) {
            view4.setVisibility(z3 ? 0 : 8);
        }
        return iH;
    }

    public void O(n.g gVar) {
        if (this.M) {
            return;
        }
        this.M = true;
        this.f235s.l();
        Window.Callback callbackI0 = i0();
        if (callbackI0 != null && !this.T) {
            callbackI0.onPanelClosed(108, gVar);
        }
        this.M = false;
    }

    public final void O0(View view) {
        view.setBackgroundColor((l0.t.I(view) & 8192) != 0 ? b0.a.b(this.f228l, R$color.abc_decor_view_status_guard_light) : b0.a.b(this.f228l, R$color.abc_decor_view_status_guard));
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

    public void Q(int i3) {
        R(g0(i3, true), true);
    }

    public void R(PanelFeatureState panelFeatureState, boolean z3) {
        ViewGroup viewGroup;
        o.o oVar;
        if (z3 && panelFeatureState.f243a == 0 && (oVar = this.f235s) != null && oVar.c()) {
            O(panelFeatureState.f252j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f228l.getSystemService("window");
        if (windowManager != null && panelFeatureState.f257o && (viewGroup = panelFeatureState.f249g) != null) {
            windowManager.removeView(viewGroup);
            if (z3) {
                N(panelFeatureState.f243a, panelFeatureState, null);
            }
        }
        panelFeatureState.f255m = false;
        panelFeatureState.f256n = false;
        panelFeatureState.f257o = false;
        panelFeatureState.f250h = null;
        panelFeatureState.f259q = true;
        if (this.O == panelFeatureState) {
            this.O = null;
        }
    }

    public final Configuration S(Context context, int i3, Configuration configuration) {
        int i4 = i3 != 1 ? i3 != 2 ? context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i4 | (configuration2.uiMode & (-49));
        return configuration2;
    }

    public final ViewGroup T() {
        ViewGroup viewGroup;
        TypedArray typedArrayObtainStyledAttributes = this.f228l.obtainStyledAttributes(R$styleable.AppCompatTheme);
        int i3 = R$styleable.AppCompatTheme_windowActionBar;
        if (!typedArrayObtainStyledAttributes.hasValue(i3)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowNoTitle, false)) {
            A(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(i3, false)) {
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
        this.f229m.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f228l);
        if (this.L) {
            viewGroup = this.J ? (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple, (ViewGroup) null);
        } else if (this.K) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_dialog_title_material, (ViewGroup) null);
            this.I = false;
            this.H = false;
        } else if (this.H) {
            TypedValue typedValue = new TypedValue();
            this.f228l.getTheme().resolveAttribute(R$attr.actionBarTheme, typedValue, true);
            int i4 = typedValue.resourceId;
            viewGroup = (ViewGroup) LayoutInflater.from(i4 != 0 ? new m.d(this.f228l, i4) : this.f228l).inflate(R$layout.abc_screen_toolbar, (ViewGroup) null);
            o.o oVar = (o.o) viewGroup.findViewById(R$id.decor_content_parent);
            this.f235s = oVar;
            oVar.setWindowCallback(i0());
            if (this.I) {
                this.f235s.k(109);
            }
            if (this.F) {
                this.f235s.k(2);
            }
            if (this.G) {
                this.f235s.k(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.H + ", windowActionBarOverlay: " + this.I + ", android:windowIsFloating: " + this.K + ", windowActionModeOverlay: " + this.J + ", windowNoTitle: " + this.L + " }");
        }
        if (Build.VERSION.SDK_INT >= 21) {
            l0.t.v0(viewGroup, new c());
        } else if (viewGroup instanceof o.s) {
            ((o.s) viewGroup).setOnFitSystemWindowsListener(new d());
        }
        if (this.f235s == null) {
            this.D = (TextView) viewGroup.findViewById(R$id.title);
        }
        k0.c(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(R$id.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.f229m.findViewById(R.id.content);
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
        this.f229m.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new e());
        return viewGroup;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View U(View view, String str, Context context, AttributeSet attributeSet) {
        boolean zH0 = false;
        if (this.f226g0 == null) {
            String string = this.f228l.obtainStyledAttributes(R$styleable.AppCompatTheme).getString(R$styleable.AppCompatTheme_viewInflaterClass);
            if (string == null) {
                this.f226g0 = new i.g();
            } else {
                try {
                    this.f226g0 = (i.g) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.f226g0 = new i.g();
                }
            }
        }
        boolean z3 = f215f;
        if (z3) {
            if (!(attributeSet instanceof XmlPullParser)) {
                zH0 = H0((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                zH0 = true;
            }
        }
        return this.f226g0.q(view, str, context, attributeSet, zH0, z3, true, j0.b());
    }

    public void V() {
        n.g gVar;
        o.o oVar = this.f235s;
        if (oVar != null) {
            oVar.l();
        }
        if (this.f240x != null) {
            this.f229m.getDecorView().removeCallbacks(this.f241y);
            if (this.f240x.isShowing()) {
                try {
                    this.f240x.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.f240x = null;
        }
        Y();
        PanelFeatureState panelFeatureStateG0 = g0(0, false);
        if (panelFeatureStateG0 == null || (gVar = panelFeatureStateG0.f252j) == null) {
            return;
        }
        gVar.close();
    }

    public boolean W(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.f227k;
        if (((obj instanceof e.a) || (obj instanceof i.f)) && (decorView = this.f229m.getDecorView()) != null && l0.e.d(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.f230n.a().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? s0(keyCode, keyEvent) : v0(keyCode, keyEvent);
    }

    public void X(int i3) {
        PanelFeatureState panelFeatureStateG0;
        PanelFeatureState panelFeatureStateG02 = g0(i3, true);
        if (panelFeatureStateG02.f252j != null) {
            Bundle bundle = new Bundle();
            panelFeatureStateG02.f252j.T(bundle);
            if (bundle.size() > 0) {
                panelFeatureStateG02.f261s = bundle;
            }
            panelFeatureStateG02.f252j.h0();
            panelFeatureStateG02.f252j.clear();
        }
        panelFeatureStateG02.f260r = true;
        panelFeatureStateG02.f259q = true;
        if ((i3 != 108 && i3 != 0) || this.f235s == null || (panelFeatureStateG0 = g0(0, false)) == null) {
            return;
        }
        panelFeatureStateG0.f255m = false;
        D0(panelFeatureStateG0, null);
    }

    public void Y() {
        x xVar = this.f242z;
        if (xVar != null) {
            xVar.b();
        }
    }

    public final void Z() {
        if (this.B) {
            return;
        }
        this.C = T();
        CharSequence charSequenceH0 = h0();
        if (!TextUtils.isEmpty(charSequenceH0)) {
            o.o oVar = this.f235s;
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
        if (panelFeatureStateG0 == null || panelFeatureStateG0.f252j == null) {
            n0(108);
        }
    }

    @Override // n.g.a
    public boolean a(n.g gVar, MenuItem menuItem) {
        PanelFeatureState panelFeatureStateB0;
        Window.Callback callbackI0 = i0();
        if (callbackI0 == null || this.T || (panelFeatureStateB0 = b0(gVar.F())) == null) {
            return false;
        }
        return callbackI0.onMenuItemSelected(panelFeatureStateB0.f243a, menuItem);
    }

    public final void a0() {
        if (this.f229m == null) {
            Object obj = this.f227k;
            if (obj instanceof Activity) {
                L(((Activity) obj).getWindow());
            }
        }
        if (this.f229m == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    @Override // n.g.a
    public void b(n.g gVar) {
        E0(true);
    }

    public PanelFeatureState b0(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.N;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i3 = 0; i3 < length; i3++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i3];
            if (panelFeatureState != null && panelFeatureState.f252j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    @Override // i.e
    public void d(View view, ViewGroup.LayoutParams layoutParams) {
        Z();
        ((ViewGroup) this.C.findViewById(R.id.content)).addView(view, layoutParams);
        this.f230n.a().onContentChanged();
    }

    public final Context d0() {
        i.a aVarN = n();
        Context contextK = aVarN != null ? aVarN.k() : null;
        return contextK == null ? this.f228l : contextK;
    }

    public final m e0(Context context) {
        if (this.Z == null) {
            this.Z = new l(context);
        }
        return this.Z;
    }

    @Override // i.e
    public Context f(Context context) {
        this.Q = true;
        int iQ0 = q0(context, M());
        if (f218i && (context instanceof ContextThemeWrapper)) {
            try {
                r.a((ContextThemeWrapper) context, S(context, iQ0, null));
                return context;
            } catch (IllegalStateException unused) {
            }
        }
        if (context instanceof m.d) {
            try {
                ((m.d) context).a(S(context, iQ0, null));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (!f217h) {
            return super.f(context);
        }
        try {
            Configuration configuration = context.getPackageManager().getResourcesForApplication(context.getApplicationInfo()).getConfiguration();
            Configuration configuration2 = context.getResources().getConfiguration();
            Configuration configurationS = S(context, iQ0, configuration.equals(configuration2) ? null : c0(configuration, configuration2));
            m.d dVar = new m.d(context, R$style.Theme_AppCompat_Empty);
            dVar.a(configurationS);
            boolean z3 = false;
            try {
                z3 = context.getTheme() != null;
            } catch (NullPointerException unused3) {
            }
            if (z3) {
                f.b.a(dVar.getTheme());
            }
            return super.f(dVar);
        } catch (PackageManager.NameNotFoundException e3) {
            throw new RuntimeException("Application failed to obtain resources from itself", e3);
        }
    }

    public final m f0(Context context) {
        if (this.Y == null) {
            this.Y = new n(i.k.a(context));
        }
        return this.Y;
    }

    public PanelFeatureState g0(int i3, boolean z3) {
        PanelFeatureState[] panelFeatureStateArr = this.N;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i3) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[i3 + 1];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.N = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i3];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        PanelFeatureState panelFeatureState2 = new PanelFeatureState(i3);
        panelFeatureStateArr[i3] = panelFeatureState2;
        return panelFeatureState2;
    }

    public final CharSequence h0() {
        Object obj = this.f227k;
        return obj instanceof Activity ? ((Activity) obj).getTitle() : this.f234r;
    }

    @Override // i.e
    public <T extends View> T i(int i3) {
        Z();
        return (T) this.f229m.findViewById(i3);
    }

    public final Window.Callback i0() {
        return this.f229m.getCallback();
    }

    public final void j0() {
        Z();
        if (this.H && this.f232p == null) {
            Object obj = this.f227k;
            if (obj instanceof Activity) {
                this.f232p = new i.l((Activity) this.f227k, this.I);
            } else if (obj instanceof Dialog) {
                this.f232p = new i.l((Dialog) this.f227k);
            }
            i.a aVar = this.f232p;
            if (aVar != null) {
                aVar.r(this.f223d0);
            }
        }
    }

    @Override // i.e
    public final i.b k() {
        return new h();
    }

    public final boolean k0(PanelFeatureState panelFeatureState) {
        View view = panelFeatureState.f251i;
        if (view != null) {
            panelFeatureState.f250h = view;
            return true;
        }
        if (panelFeatureState.f252j == null) {
            return false;
        }
        if (this.f237u == null) {
            this.f237u = new t();
        }
        View view2 = (View) panelFeatureState.a(this.f237u);
        panelFeatureState.f250h = view2;
        return view2 != null;
    }

    @Override // i.e
    public int l() {
        return this.U;
    }

    public final boolean l0(PanelFeatureState panelFeatureState) {
        panelFeatureState.d(d0());
        panelFeatureState.f249g = new s(panelFeatureState.f254l);
        panelFeatureState.f245c = 81;
        return true;
    }

    @Override // i.e
    public MenuInflater m() {
        if (this.f233q == null) {
            j0();
            i.a aVar = this.f232p;
            this.f233q = new m.g(aVar != null ? aVar.k() : this.f228l);
        }
        return this.f233q;
    }

    public final boolean m0(PanelFeatureState panelFeatureState) {
        Context context = this.f228l;
        int i3 = panelFeatureState.f243a;
        if ((i3 == 0 || i3 == 108) && this.f235s != null) {
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
                m.d dVar = new m.d(context, 0);
                dVar.getTheme().setTo(themeNewTheme);
                context = dVar;
            }
        }
        n.g gVar = new n.g(context);
        gVar.V(this);
        panelFeatureState.c(gVar);
        return true;
    }

    @Override // i.e
    public i.a n() {
        j0();
        return this.f232p;
    }

    public final void n0(int i3) {
        this.f221b0 = (1 << i3) | this.f221b0;
        if (this.f220a0) {
            return;
        }
        l0.t.b0(this.f229m.getDecorView(), this.f222c0);
        this.f220a0 = true;
    }

    @Override // i.e
    public void o() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f228l);
        if (layoutInflaterFrom.getFactory() == null) {
            l0.f.b(layoutInflaterFrom, this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof AppCompatDelegateImpl) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public final boolean o0() {
        if (!this.X && (this.f227k instanceof Activity)) {
            PackageManager packageManager = this.f228l.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            try {
                int i3 = Build.VERSION.SDK_INT;
                ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(this.f228l, this.f227k.getClass()), i3 >= 29 ? 269221888 : i3 >= 24 ? 786432 : 0);
                this.W = (activityInfo == null || (activityInfo.configChanges & 512) == 0) ? false : true;
            } catch (PackageManager.NameNotFoundException e3) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e3);
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

    @Override // i.e
    public void p() {
        i.a aVarN = n();
        if (aVarN == null || !aVarN.l()) {
            n0(0);
        }
    }

    public boolean p0() {
        return this.A;
    }

    @Override // i.e
    public void q(Configuration configuration) {
        i.a aVarN;
        if (this.H && this.B && (aVarN = n()) != null) {
            aVarN.m(configuration);
        }
        o.f.b().g(this.f228l);
        J(false);
    }

    public int q0(Context context, int i3) {
        if (i3 == -100) {
            return -1;
        }
        if (i3 != -1) {
            if (i3 == 0) {
                if (Build.VERSION.SDK_INT < 23 || ((UiModeManager) context.getApplicationContext().getSystemService(UiModeManager.class)).getNightMode() != 0) {
                    return f0(context).c();
                }
                return -1;
            }
            if (i3 != 1 && i3 != 2) {
                if (i3 == 3) {
                    return e0(context).c();
                }
                throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
            }
        }
        return i3;
    }

    @Override // i.e
    public void r(Bundle bundle) {
        this.Q = true;
        J(false);
        a0();
        Object obj = this.f227k;
        if (obj instanceof Activity) {
            String strC = null;
            try {
                strC = a0.c.c((Activity) obj);
            } catch (IllegalArgumentException unused) {
            }
            if (strC != null) {
                i.a aVarB0 = B0();
                if (aVarB0 == null) {
                    this.f223d0 = true;
                } else {
                    aVarB0.r(true);
                }
            }
            i.e.c(this);
        }
        this.R = true;
    }

    public boolean r0() {
        m.b bVar = this.f238v;
        if (bVar != null) {
            bVar.c();
            return true;
        }
        i.a aVarN = n();
        return aVarN != null && aVarN.h();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0048  */
    @Override // i.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void s() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f227k
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L9
            i.e.y(r3)
        L9:
            boolean r0 = r3.f220a0
            if (r0 == 0) goto L18
            android.view.Window r0 = r3.f229m
            android.view.View r0 = r0.getDecorView()
            java.lang.Runnable r1 = r3.f222c0
            r0.removeCallbacks(r1)
        L18:
            r0 = 0
            r3.S = r0
            r0 = 1
            r3.T = r0
            int r0 = r3.U
            r1 = -100
            if (r0 == r1) goto L48
            java.lang.Object r0 = r3.f227k
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L48
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L48
            s.g<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.f214e
            java.lang.Object r1 = r3.f227k
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.U
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L57
        L48:
            s.g<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.f214e
            java.lang.Object r1 = r3.f227k
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L57:
            i.a r0 = r3.f232p
            if (r0 == 0) goto L5e
            r0.n()
        L5e:
            r3.P()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.s():void");
    }

    public boolean s0(int i3, KeyEvent keyEvent) {
        if (i3 == 4) {
            this.P = (keyEvent.getFlags() & 128) != 0;
        } else if (i3 == 82) {
            t0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // i.e
    public void t(Bundle bundle) {
        Z();
    }

    public final boolean t0(int i3, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        PanelFeatureState panelFeatureStateG0 = g0(i3, true);
        if (panelFeatureStateG0.f257o) {
            return false;
        }
        return D0(panelFeatureStateG0, keyEvent);
    }

    @Override // i.e
    public void u() {
        i.a aVarN = n();
        if (aVarN != null) {
            aVarN.s(true);
        }
    }

    public boolean u0(int i3, KeyEvent keyEvent) {
        i.a aVarN = n();
        if (aVarN != null && aVarN.o(i3, keyEvent)) {
            return true;
        }
        PanelFeatureState panelFeatureState = this.O;
        if (panelFeatureState != null && C0(panelFeatureState, keyEvent.getKeyCode(), keyEvent, 1)) {
            PanelFeatureState panelFeatureState2 = this.O;
            if (panelFeatureState2 != null) {
                panelFeatureState2.f256n = true;
            }
            return true;
        }
        if (this.O == null) {
            PanelFeatureState panelFeatureStateG0 = g0(0, true);
            D0(panelFeatureStateG0, keyEvent);
            boolean zC0 = C0(panelFeatureStateG0, keyEvent.getKeyCode(), keyEvent, 1);
            panelFeatureStateG0.f255m = false;
            if (zC0) {
                return true;
            }
        }
        return false;
    }

    @Override // i.e
    public void v(Bundle bundle) {
    }

    public boolean v0(int i3, KeyEvent keyEvent) {
        if (i3 == 4) {
            boolean z3 = this.P;
            this.P = false;
            PanelFeatureState panelFeatureStateG0 = g0(0, false);
            if (panelFeatureStateG0 != null && panelFeatureStateG0.f257o) {
                if (!z3) {
                    R(panelFeatureStateG0, true);
                }
                return true;
            }
            if (r0()) {
                return true;
            }
        } else if (i3 == 82) {
            w0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // i.e
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
            m.b r0 = r4.f238v
            r1 = 0
            if (r0 == 0) goto L6
            return r1
        L6:
            r0 = 1
            androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r2 = r4.g0(r5, r0)
            if (r5 != 0) goto L43
            o.o r5 = r4.f235s
            if (r5 == 0) goto L43
            boolean r5 = r5.g()
            if (r5 == 0) goto L43
            android.content.Context r5 = r4.f228l
            android.view.ViewConfiguration r5 = android.view.ViewConfiguration.get(r5)
            boolean r5 = r5.hasPermanentMenuKey()
            if (r5 != 0) goto L43
            o.o r5 = r4.f235s
            boolean r5 = r5.c()
            if (r5 != 0) goto L3c
            boolean r5 = r4.T
            if (r5 != 0) goto L62
            boolean r5 = r4.D0(r2, r6)
            if (r5 == 0) goto L62
            o.o r5 = r4.f235s
            boolean r0 = r5.e()
            goto L68
        L3c:
            o.o r5 = r4.f235s
            boolean r0 = r5.d()
            goto L68
        L43:
            boolean r5 = r2.f257o
            if (r5 != 0) goto L64
            boolean r3 = r2.f256n
            if (r3 == 0) goto L4c
            goto L64
        L4c:
            boolean r5 = r2.f255m
            if (r5 == 0) goto L62
            boolean r5 = r2.f260r
            if (r5 == 0) goto L5b
            r2.f255m = r1
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
            android.content.Context r5 = r4.f228l
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

    @Override // i.e
    public void x() {
        this.S = false;
        i.a aVarN = n();
        if (aVarN != null) {
            aVarN.s(false);
        }
    }

    public void x0(int i3) {
        i.a aVarN;
        if (i3 != 108 || (aVarN = n()) == null) {
            return;
        }
        aVarN.i(true);
    }

    public void y0(int i3) {
        if (i3 == 108) {
            i.a aVarN = n();
            if (aVarN != null) {
                aVarN.i(false);
                return;
            }
            return;
        }
        if (i3 == 0) {
            PanelFeatureState panelFeatureStateG0 = g0(i3, true);
            if (panelFeatureStateG0.f257o) {
                R(panelFeatureStateG0, false);
            }
        }
    }

    public void z0(ViewGroup viewGroup) {
    }

    public AppCompatDelegateImpl(Dialog dialog, i.d dVar) {
        this(dialog.getContext(), dialog.getWindow(), dVar, dialog);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    public AppCompatDelegateImpl(Context context, Window window, i.d dVar, Object obj) {
        s.g<String, Integer> gVar;
        Integer num;
        AppCompatActivity appCompatActivityK0;
        this.f242z = null;
        this.A = true;
        this.U = -100;
        this.f222c0 = new b();
        this.f228l = context;
        this.f231o = dVar;
        this.f227k = obj;
        if (this.U == -100 && (obj instanceof Dialog) && (appCompatActivityK0 = K0()) != null) {
            this.U = appCompatActivityK0.getDelegate().l();
        }
        if (this.U == -100 && (num = (gVar = f214e).get(obj.getClass().getName())) != null) {
            this.U = num.intValue();
            gVar.remove(obj.getClass().getName());
        }
        if (window != null) {
            L(window);
        }
        o.f.h();
    }

    public class k extends m.i {
        public k(Window.Callback callback) {
            super(callback);
        }

        public final ActionMode b(ActionMode.Callback callback) {
            f.a aVar = new f.a(AppCompatDelegateImpl.this.f228l, callback);
            m.b bVarH = AppCompatDelegateImpl.this.H(aVar);
            if (bVarH != null) {
                return aVar.e(bVarH);
            }
            return null;
        }

        @Override // m.i, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.W(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // m.i, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.u0(keyEvent.getKeyCode(), keyEvent);
        }

        @Override // m.i, android.view.Window.Callback
        public void onContentChanged() {
        }

        @Override // m.i, android.view.Window.Callback
        public boolean onCreatePanelMenu(int i3, Menu menu) {
            if (i3 != 0 || (menu instanceof n.g)) {
                return super.onCreatePanelMenu(i3, menu);
            }
            return false;
        }

        @Override // m.i, android.view.Window.Callback
        public boolean onMenuOpened(int i3, Menu menu) {
            super.onMenuOpened(i3, menu);
            AppCompatDelegateImpl.this.x0(i3);
            return true;
        }

        @Override // m.i, android.view.Window.Callback
        public void onPanelClosed(int i3, Menu menu) {
            super.onPanelClosed(i3, menu);
            AppCompatDelegateImpl.this.y0(i3);
        }

        @Override // m.i, android.view.Window.Callback
        public boolean onPreparePanel(int i3, View view, Menu menu) {
            n.g gVar = menu instanceof n.g ? (n.g) menu : null;
            if (i3 == 0 && gVar == null) {
                return false;
            }
            if (gVar != null) {
                gVar.e0(true);
            }
            boolean zOnPreparePanel = super.onPreparePanel(i3, view, menu);
            if (gVar != null) {
                gVar.e0(false);
            }
            return zOnPreparePanel;
        }

        @Override // m.i, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i3) {
            n.g gVar;
            PanelFeatureState panelFeatureStateG0 = AppCompatDelegateImpl.this.g0(0, true);
            if (panelFeatureStateG0 == null || (gVar = panelFeatureStateG0.f252j) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i3);
            } else {
                super.onProvideKeyboardShortcuts(list, gVar, i3);
            }
        }

        @Override // m.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            return AppCompatDelegateImpl.this.p0() ? b(callback) : super.onWindowStartingActionMode(callback);
        }

        @Override // m.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i3) {
            if (AppCompatDelegateImpl.this.p0() && i3 == 0) {
                return b(callback);
            }
            return super.onWindowStartingActionMode(callback, i3);
        }
    }
}
