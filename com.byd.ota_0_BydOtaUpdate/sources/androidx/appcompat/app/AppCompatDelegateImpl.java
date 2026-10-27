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
import b.f.f.c.f;
import b.f.m.a0;
import b.f.m.e;
import b.f.m.w;
import b.f.m.y;
import java.lang.Thread;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatDelegateImpl extends b.b.k.d implements g.a, LayoutInflater.Factory2 {
    public static final int[] E3;
    public static final boolean F3;
    public static final boolean G3;
    public static boolean H3;
    public static final b.d.g<String, Integer> x = new b.d.g<>();
    public static final boolean y;
    public final Runnable A4;
    public boolean B4;
    public Rect C4;
    public Rect D4;
    public b.b.k.f E4;
    public final Object I3;
    public final Context J3;
    public Window K3;
    public j L3;
    public final b.b.k.c M3;
    public b.b.k.a N3;
    public MenuInflater O3;
    public CharSequence P3;
    public b.b.q.o Q3;
    public h R3;
    public s S3;
    public b.b.p.b T3;
    public ActionBarContextView U3;
    public PopupWindow V3;
    public Runnable W3;
    public w X3;
    public boolean Y3;
    public boolean Z3;
    public ViewGroup a4;
    public TextView b4;
    public View c4;
    public boolean d4;
    public boolean e4;
    public boolean f4;
    public boolean g4;
    public boolean h4;
    public boolean i4;
    public boolean j4;
    public boolean k4;
    public PanelFeatureState[] l4;
    public PanelFeatureState m4;
    public boolean n4;
    public boolean o4;
    public boolean p4;
    public boolean q4;
    public boolean r4;
    public int s4;
    public int t4;
    public boolean u4;
    public boolean v4;
    public l w4;
    public l x4;
    public boolean y4;
    public int z4;

    public static final class PanelFeatureState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f119a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f120b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f121c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f122d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f123e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f124f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ViewGroup f125g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public View f126h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public View f127i;
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

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f128c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public boolean f129d;
            public Bundle s;

            public class a implements Parcelable.ClassLoaderCreator<SavedState> {
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
                public SavedState[] newArray(int i2) {
                    return new SavedState[i2];
                }
            }

            public static SavedState a(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.f128c = parcel.readInt();
                boolean z = parcel.readInt() == 1;
                savedState.f129d = z;
                if (z) {
                    savedState.s = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i2) {
                parcel.writeInt(this.f128c);
                parcel.writeInt(this.f129d ? 1 : 0);
                if (this.f129d) {
                    parcel.writeBundle(this.s);
                }
            }
        }

        public PanelFeatureState(int i2) {
            this.f119a = i2;
        }

        public b.b.p.j.n a(m.a aVar) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                b.b.p.j.e eVar = new b.b.p.j.e(this.l, b.b.g.l);
                this.k = eVar;
                eVar.h(aVar);
                this.j.b(this.k);
            }
            return this.k.f(this.f125g);
        }

        public boolean b() {
            if (this.f126h == null) {
                return false;
            }
            return this.f127i != null || this.k.b().getCount() > 0;
        }

        public void c(b.b.p.j.g gVar) {
            b.b.p.j.e eVar;
            b.b.p.j.g gVar2 = this.j;
            if (gVar == gVar2) {
                return;
            }
            if (gVar2 != null) {
                gVar2.O(this.k);
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
            themeNewTheme.resolveAttribute(b.b.a.f704a, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                themeNewTheme.applyStyle(i2, true);
            }
            themeNewTheme.resolveAttribute(b.b.a.F, typedValue, true);
            int i3 = typedValue.resourceId;
            if (i3 != 0) {
                themeNewTheme.applyStyle(i3, true);
            } else {
                themeNewTheme.applyStyle(b.b.i.f768b, true);
            }
            b.b.p.d dVar = new b.b.p.d(context, 0);
            dVar.getTheme().setTo(themeNewTheme);
            this.l = dVar;
            TypedArray typedArrayObtainStyledAttributes = dVar.obtainStyledAttributes(b.b.j.B0);
            this.f120b = typedArrayObtainStyledAttributes.getResourceId(b.b.j.E0, 0);
            this.f124f = typedArrayObtainStyledAttributes.getResourceId(b.b.j.D0, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public class a implements Thread.UncaughtExceptionHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Thread.UncaughtExceptionHandler f130a;

        public a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.f130a = uncaughtExceptionHandler;
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
                this.f130a.uncaughtException(thread, th);
                return;
            }
            Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            notFoundException.initCause(th.getCause());
            notFoundException.setStackTrace(th.getStackTrace());
            this.f130a.uncaughtException(thread, notFoundException);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl.z4 & 1) != 0) {
                appCompatDelegateImpl.U(0);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl2.z4 & 4096) != 0) {
                appCompatDelegateImpl2.U(108);
            }
            AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl3.y4 = false;
            appCompatDelegateImpl3.z4 = 0;
        }
    }

    public class c implements b.f.m.p {
        public c() {
        }

        @Override // b.f.m.p
        public a0 a(View view, a0 a0Var) {
            int iG = a0Var.g();
            int iL0 = AppCompatDelegateImpl.this.L0(a0Var, null);
            if (iG != iL0) {
                a0Var = a0Var.l(a0Var.e(), iL0, a0Var.f(), a0Var.d());
            }
            return b.f.m.s.L(view, a0Var);
        }
    }

    public class d implements s.a {
        public d() {
        }

        @Override // b.b.q.s.a
        public void a(Rect rect) {
            rect.top = AppCompatDelegateImpl.this.L0(null, rect);
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
            AppCompatDelegateImpl.this.S();
        }
    }

    public class f implements Runnable {

        public class a extends y {
            public a() {
            }

            @Override // b.f.m.x
            public void a(View view) {
                AppCompatDelegateImpl.this.U3.setAlpha(1.0f);
                AppCompatDelegateImpl.this.X3.f(null);
                AppCompatDelegateImpl.this.X3 = null;
            }

            @Override // b.f.m.y, b.f.m.x
            public void b(View view) {
                AppCompatDelegateImpl.this.U3.setVisibility(0);
            }
        }

        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            appCompatDelegateImpl.V3.showAtLocation(appCompatDelegateImpl.U3, 55, 0, 0);
            AppCompatDelegateImpl.this.V();
            if (!AppCompatDelegateImpl.this.D0()) {
                AppCompatDelegateImpl.this.U3.setAlpha(1.0f);
                AppCompatDelegateImpl.this.U3.setVisibility(0);
            } else {
                AppCompatDelegateImpl.this.U3.setAlpha(0.0f);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.X3 = b.f.m.s.b(appCompatDelegateImpl2.U3).a(1.0f);
                AppCompatDelegateImpl.this.X3.f(new a());
            }
        }
    }

    public class g extends y {
        public g() {
        }

        @Override // b.f.m.x
        public void a(View view) {
            AppCompatDelegateImpl.this.U3.setAlpha(1.0f);
            AppCompatDelegateImpl.this.X3.f(null);
            AppCompatDelegateImpl.this.X3 = null;
        }

        @Override // b.f.m.y, b.f.m.x
        public void b(View view) {
            AppCompatDelegateImpl.this.U3.setVisibility(0);
            AppCompatDelegateImpl.this.U3.sendAccessibilityEvent(32);
            if (AppCompatDelegateImpl.this.U3.getParent() instanceof View) {
                b.f.m.s.Q((View) AppCompatDelegateImpl.this.U3.getParent());
            }
        }
    }

    public final class h implements m.a {
        public h() {
        }

        @Override // b.b.p.j.m.a
        public void a(b.b.p.j.g gVar, boolean z) {
            AppCompatDelegateImpl.this.L(gVar);
        }

        @Override // b.b.p.j.m.a
        public boolean b(b.b.p.j.g gVar) {
            Window.Callback callbackF0 = AppCompatDelegateImpl.this.f0();
            if (callbackF0 == null) {
                return true;
            }
            callbackF0.onMenuOpened(108, gVar);
            return true;
        }
    }

    public class i implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public b.a f139a;

        public class a extends y {
            public a() {
            }

            @Override // b.f.m.x
            public void a(View view) {
                AppCompatDelegateImpl.this.U3.setVisibility(8);
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                PopupWindow popupWindow = appCompatDelegateImpl.V3;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (appCompatDelegateImpl.U3.getParent() instanceof View) {
                    b.f.m.s.Q((View) AppCompatDelegateImpl.this.U3.getParent());
                }
                AppCompatDelegateImpl.this.U3.removeAllViews();
                AppCompatDelegateImpl.this.X3.f(null);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.X3 = null;
                b.f.m.s.Q(appCompatDelegateImpl2.a4);
            }
        }

        public i(b.a aVar) {
            this.f139a = aVar;
        }

        @Override // b.b.p.b.a
        public boolean a(b.b.p.b bVar, Menu menu) {
            b.f.m.s.Q(AppCompatDelegateImpl.this.a4);
            return this.f139a.a(bVar, menu);
        }

        @Override // b.b.p.b.a
        public boolean b(b.b.p.b bVar, MenuItem menuItem) {
            return this.f139a.b(bVar, menuItem);
        }

        @Override // b.b.p.b.a
        public boolean c(b.b.p.b bVar, Menu menu) {
            return this.f139a.c(bVar, menu);
        }

        @Override // b.b.p.b.a
        public void d(b.b.p.b bVar) {
            this.f139a.d(bVar);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.V3 != null) {
                appCompatDelegateImpl.K3.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.W3);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.U3 != null) {
                appCompatDelegateImpl2.V();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl3.X3 = b.f.m.s.b(appCompatDelegateImpl3.U3).a(0.0f);
                AppCompatDelegateImpl.this.X3.f(new a());
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            b.b.k.c cVar = appCompatDelegateImpl4.M3;
            if (cVar != null) {
                cVar.i(appCompatDelegateImpl4.T3);
            }
            AppCompatDelegateImpl appCompatDelegateImpl5 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl5.T3 = null;
            b.f.m.s.Q(appCompatDelegateImpl5.a4);
        }
    }

    public class k extends l {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final PowerManager f143c;

        public k(Context context) {
            super();
            this.f143c = (PowerManager) context.getApplicationContext().getSystemService("power");
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        public IntentFilter b() {
            if (Build.VERSION.SDK_INT < 21) {
                return null;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        public int c() {
            return (Build.VERSION.SDK_INT < 21 || !this.f143c.isPowerSaveMode()) ? 1 : 2;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        public void d() {
            AppCompatDelegateImpl.this.F();
        }
    }

    public abstract class l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public BroadcastReceiver f145a;

        public class a extends BroadcastReceiver {
            public a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                l.this.d();
            }
        }

        public l() {
        }

        public void a() {
            BroadcastReceiver broadcastReceiver = this.f145a;
            if (broadcastReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.J3.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.f145a = null;
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
            if (this.f145a == null) {
                this.f145a = new a();
            }
            AppCompatDelegateImpl.this.J3.registerReceiver(this.f145a, intentFilterB);
        }
    }

    public class m extends l {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final b.b.k.i f148c;

        public m(b.b.k.i iVar) {
            super();
            this.f148c = iVar;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        public IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        public int c() {
            return this.f148c.d() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.l
        public void d() {
            AppCompatDelegateImpl.this.F();
        }
    }

    public static class n {
        public static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i2 = configuration.densityDpi;
            int i3 = configuration2.densityDpi;
            if (i2 != i3) {
                configuration3.densityDpi = i3;
            }
        }
    }

    public static class o {
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

    public static class p {
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

    public static class q {
        public static void a(ContextThemeWrapper contextThemeWrapper, Configuration configuration) {
            contextThemeWrapper.applyOverrideConfiguration(configuration);
        }
    }

    public class r extends ContentFrameLayout {
        public r(Context context) {
            super(context);
        }

        public final boolean c(int i2, int i3) {
            return i2 < -5 || i3 < -5 || i2 > getWidth() + 5 || i3 > getHeight() + 5;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.T(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !c((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            AppCompatDelegateImpl.this.N(0);
            return true;
        }

        @Override // android.view.View
        public void setBackgroundResource(int i2) {
            setBackgroundDrawable(b.b.l.a.a.d(getContext(), i2));
        }
    }

    public final class s implements m.a {
        public s() {
        }

        @Override // b.b.p.j.m.a
        public void a(b.b.p.j.g gVar, boolean z) {
            b.b.p.j.g gVarD = gVar.D();
            boolean z2 = gVarD != gVar;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z2) {
                gVar = gVarD;
            }
            PanelFeatureState panelFeatureStateY = appCompatDelegateImpl.Y(gVar);
            if (panelFeatureStateY != null) {
                if (!z2) {
                    AppCompatDelegateImpl.this.O(panelFeatureStateY, z);
                } else {
                    AppCompatDelegateImpl.this.K(panelFeatureStateY.f119a, panelFeatureStateY, gVarD);
                    AppCompatDelegateImpl.this.O(panelFeatureStateY, true);
                }
            }
        }

        @Override // b.b.p.j.m.a
        public boolean b(b.b.p.j.g gVar) {
            Window.Callback callbackF0;
            if (gVar != gVar.D()) {
                return true;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (!appCompatDelegateImpl.f4 || (callbackF0 = appCompatDelegateImpl.f0()) == null || AppCompatDelegateImpl.this.r4) {
                return true;
            }
            callbackF0.onMenuOpened(108, gVar);
            return true;
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        boolean z = i2 < 21;
        y = z;
        E3 = new int[]{R.attr.windowBackground};
        F3 = !"robolectric".equals(Build.FINGERPRINT);
        G3 = i2 >= 17;
        if (!z || H3) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new a(Thread.getDefaultUncaughtExceptionHandler()));
        H3 = true;
    }

    public AppCompatDelegateImpl(Activity activity, b.b.k.c cVar) {
        this(activity, null, cVar, activity);
    }

    public static Configuration Z(Configuration configuration, Configuration configuration2) {
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
            } else if (!b.f.l.c.a(configuration.locale, configuration2.locale)) {
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

    @Override // b.b.k.d
    public void A(int i2) {
        W();
        ViewGroup viewGroup = (ViewGroup) this.a4.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.J3).inflate(i2, viewGroup);
        this.L3.a().onContentChanged();
    }

    public final boolean A0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        b.b.q.o oVar;
        b.b.q.o oVar2;
        b.b.q.o oVar3;
        if (this.r4) {
            return false;
        }
        if (panelFeatureState.m) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.m4;
        if (panelFeatureState2 != null && panelFeatureState2 != panelFeatureState) {
            O(panelFeatureState2, false);
        }
        Window.Callback callbackF0 = f0();
        if (callbackF0 != null) {
            panelFeatureState.f127i = callbackF0.onCreatePanelView(panelFeatureState.f119a);
        }
        int i2 = panelFeatureState.f119a;
        boolean z = i2 == 0 || i2 == 108;
        if (z && (oVar3 = this.Q3) != null) {
            oVar3.f();
        }
        if (panelFeatureState.f127i == null) {
            if (z) {
                y0();
            }
            b.b.p.j.g gVar = panelFeatureState.j;
            if (gVar == null || panelFeatureState.r) {
                if (gVar == null && (!j0(panelFeatureState) || panelFeatureState.j == null)) {
                    return false;
                }
                if (z && this.Q3 != null) {
                    if (this.R3 == null) {
                        this.R3 = new h();
                    }
                    this.Q3.a(panelFeatureState.j, this.R3);
                }
                panelFeatureState.j.d0();
                if (!callbackF0.onCreatePanelMenu(panelFeatureState.f119a, panelFeatureState.j)) {
                    panelFeatureState.c(null);
                    if (z && (oVar = this.Q3) != null) {
                        oVar.a(null, this.R3);
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
            if (!callbackF0.onPreparePanel(0, panelFeatureState.f127i, panelFeatureState.j)) {
                if (z && (oVar2 = this.Q3) != null) {
                    oVar2.a(null, this.R3);
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
        this.m4 = panelFeatureState;
        return true;
    }

    @Override // b.b.k.d
    public void B(View view) {
        W();
        ViewGroup viewGroup = (ViewGroup) this.a4.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.L3.a().onContentChanged();
    }

    public final void B0(boolean z) {
        b.b.q.o oVar = this.Q3;
        if (oVar == null || !oVar.g() || (ViewConfiguration.get(this.J3).hasPermanentMenuKey() && !this.Q3.b())) {
            PanelFeatureState panelFeatureStateD0 = d0(0, true);
            panelFeatureStateD0.q = true;
            O(panelFeatureStateD0, false);
            x0(panelFeatureStateD0, null);
            return;
        }
        Window.Callback callbackF0 = f0();
        if (this.Q3.c() && z) {
            this.Q3.d();
            if (this.r4) {
                return;
            }
            callbackF0.onPanelClosed(108, d0(0, true).j);
            return;
        }
        if (callbackF0 == null || this.r4) {
            return;
        }
        if (this.y4 && (this.z4 & 1) != 0) {
            this.K3.getDecorView().removeCallbacks(this.A4);
            this.A4.run();
        }
        PanelFeatureState panelFeatureStateD02 = d0(0, true);
        b.b.p.j.g gVar = panelFeatureStateD02.j;
        if (gVar == null || panelFeatureStateD02.r || !callbackF0.onPreparePanel(0, panelFeatureStateD02.f127i, gVar)) {
            return;
        }
        callbackF0.onMenuOpened(108, panelFeatureStateD02.j);
        this.Q3.e();
    }

    @Override // b.b.k.d
    public void C(View view, ViewGroup.LayoutParams layoutParams) {
        W();
        ViewGroup viewGroup = (ViewGroup) this.a4.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.L3.a().onContentChanged();
    }

    public final int C0(int i2) {
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

    @Override // b.b.k.d
    public void D(int i2) {
        this.t4 = i2;
    }

    public final boolean D0() {
        ViewGroup viewGroup;
        return this.Z3 && (viewGroup = this.a4) != null && b.f.m.s.G(viewGroup);
    }

    @Override // b.b.k.d
    public final void E(CharSequence charSequence) {
        this.P3 = charSequence;
        b.b.q.o oVar = this.Q3;
        if (oVar != null) {
            oVar.setWindowTitle(charSequence);
            return;
        }
        if (y0() != null) {
            y0().u(charSequence);
            return;
        }
        TextView textView = this.b4;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public final boolean E0(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.K3.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || b.f.m.s.F((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    public boolean F() {
        return G(true);
    }

    public b.b.p.b F0(b.a aVar) {
        b.b.k.c cVar;
        if (aVar == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        b.b.p.b bVar = this.T3;
        if (bVar != null) {
            bVar.c();
        }
        i iVar = new i(aVar);
        b.b.k.a aVarM = m();
        if (aVarM != null) {
            b.b.p.b bVarV = aVarM.v(iVar);
            this.T3 = bVarV;
            if (bVarV != null && (cVar = this.M3) != null) {
                cVar.g(bVarV);
            }
        }
        if (this.T3 == null) {
            this.T3 = G0(iVar);
        }
        return this.T3;
    }

    public final boolean G(boolean z) {
        if (this.r4) {
            return false;
        }
        int iJ = J();
        boolean zJ0 = J0(n0(this.J3, iJ), z);
        if (iJ == 0) {
            c0(this.J3).e();
        } else {
            l lVar = this.w4;
            if (lVar != null) {
                lVar.a();
            }
        }
        if (iJ == 3) {
            b0(this.J3).e();
        } else {
            l lVar2 = this.x4;
            if (lVar2 != null) {
                lVar2.a();
            }
        }
        return zJ0;
    }

    public b.b.p.b G0(b.a aVar) {
        b.b.p.b bVarJ;
        Context dVar;
        b.b.k.c cVar;
        V();
        b.b.p.b bVar = this.T3;
        if (bVar != null) {
            bVar.c();
        }
        if (!(aVar instanceof i)) {
            aVar = new i(aVar);
        }
        b.b.k.c cVar2 = this.M3;
        if (cVar2 == null || this.r4) {
            bVarJ = null;
        } else {
            try {
                bVarJ = cVar2.j(aVar);
            } catch (AbstractMethodError unused) {
                bVarJ = null;
            }
        }
        if (bVarJ != null) {
            this.T3 = bVarJ;
        } else {
            if (this.U3 == null) {
                if (this.i4) {
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = this.J3.getTheme();
                    theme.resolveAttribute(b.b.a.f710g, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Resources.Theme themeNewTheme = this.J3.getResources().newTheme();
                        themeNewTheme.setTo(theme);
                        themeNewTheme.applyStyle(typedValue.resourceId, true);
                        dVar = new b.b.p.d(this.J3, 0);
                        dVar.getTheme().setTo(themeNewTheme);
                    } else {
                        dVar = this.J3;
                    }
                    this.U3 = new ActionBarContextView(dVar);
                    PopupWindow popupWindow = new PopupWindow(dVar, (AttributeSet) null, b.b.a.j);
                    this.V3 = popupWindow;
                    b.f.n.h.b(popupWindow, 2);
                    this.V3.setContentView(this.U3);
                    this.V3.setWidth(-1);
                    dVar.getTheme().resolveAttribute(b.b.a.f705b, typedValue, true);
                    this.U3.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, dVar.getResources().getDisplayMetrics()));
                    this.V3.setHeight(-2);
                    this.W3 = new f();
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.a4.findViewById(b.b.f.f747h);
                    if (viewStubCompat != null) {
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(a0()));
                        this.U3 = (ActionBarContextView) viewStubCompat.a();
                    }
                }
            }
            if (this.U3 != null) {
                V();
                this.U3.k();
                b.b.p.e eVar = new b.b.p.e(this.U3.getContext(), this.U3, aVar, this.V3 == null);
                if (aVar.c(eVar, eVar.e())) {
                    eVar.k();
                    this.U3.h(eVar);
                    this.T3 = eVar;
                    if (D0()) {
                        this.U3.setAlpha(0.0f);
                        w wVarA = b.f.m.s.b(this.U3).a(1.0f);
                        this.X3 = wVarA;
                        wVarA.f(new g());
                    } else {
                        this.U3.setAlpha(1.0f);
                        this.U3.setVisibility(0);
                        this.U3.sendAccessibilityEvent(32);
                        if (this.U3.getParent() instanceof View) {
                            b.f.m.s.Q((View) this.U3.getParent());
                        }
                    }
                    if (this.V3 != null) {
                        this.K3.getDecorView().post(this.W3);
                    }
                } else {
                    this.T3 = null;
                }
            }
        }
        b.b.p.b bVar2 = this.T3;
        if (bVar2 != null && (cVar = this.M3) != null) {
            cVar.g(bVar2);
        }
        return this.T3;
    }

    public final void H() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.a4.findViewById(R.id.content);
        View decorView = this.K3.getDecorView();
        contentFrameLayout.b(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray typedArrayObtainStyledAttributes = this.J3.obtainStyledAttributes(b.b.j.B0);
        typedArrayObtainStyledAttributes.getValue(b.b.j.N0, contentFrameLayout.getMinWidthMajor());
        typedArrayObtainStyledAttributes.getValue(b.b.j.O0, contentFrameLayout.getMinWidthMinor());
        int i2 = b.b.j.L0;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            typedArrayObtainStyledAttributes.getValue(i2, contentFrameLayout.getFixedWidthMajor());
        }
        int i3 = b.b.j.M0;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            typedArrayObtainStyledAttributes.getValue(i3, contentFrameLayout.getFixedWidthMinor());
        }
        int i4 = b.b.j.J0;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            typedArrayObtainStyledAttributes.getValue(i4, contentFrameLayout.getFixedHeightMajor());
        }
        int i5 = b.b.j.K0;
        if (typedArrayObtainStyledAttributes.hasValue(i5)) {
            typedArrayObtainStyledAttributes.getValue(i5, contentFrameLayout.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    public final void H0() {
        if (this.Z3) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public final void I(Window window) {
        if (this.K3 != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof j) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        j jVar = new j(callback);
        this.L3 = jVar;
        window.setCallback(jVar);
        e0 e0VarT = e0.t(this.J3, null, E3);
        Drawable drawableG = e0VarT.g(0);
        if (drawableG != null) {
            window.setBackgroundDrawable(drawableG);
        }
        e0VarT.v();
        this.K3 = window;
    }

    public final AppCompatActivity I0() {
        for (Context baseContext = this.J3; baseContext != null; baseContext = ((ContextWrapper) baseContext).getBaseContext()) {
            if (baseContext instanceof AppCompatActivity) {
                return (AppCompatActivity) baseContext;
            }
            if (!(baseContext instanceof ContextWrapper)) {
                break;
            }
        }
        return null;
    }

    public final int J() {
        int i2 = this.s4;
        return i2 != -100 ? i2 : b.b.k.d.j();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean J0(int r7, boolean r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.J3
            r1 = 0
            android.content.res.Configuration r0 = r6.P(r0, r7, r1)
            boolean r2 = r6.l0()
            android.content.Context r3 = r6.J3
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
            boolean r8 = r6.o4
            if (r8 == 0) goto L47
            boolean r8 = androidx.appcompat.app.AppCompatDelegateImpl.F3
            if (r8 != 0) goto L30
            boolean r8 = r6.p4
            if (r8 == 0) goto L47
        L30:
            java.lang.Object r8 = r6.I3
            boolean r5 = r8 instanceof android.app.Activity
            if (r5 == 0) goto L47
            android.app.Activity r8 = (android.app.Activity) r8
            boolean r8 = r8.isChild()
            if (r8 != 0) goto L47
            java.lang.Object r8 = r6.I3
            android.app.Activity r8 = (android.app.Activity) r8
            b.f.e.a.i(r8)
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
            java.lang.Object r8 = r6.I3
            boolean r0 = r8 instanceof androidx.appcompat.app.AppCompatActivity
            if (r0 == 0) goto L5e
            androidx.appcompat.app.AppCompatActivity r8 = (androidx.appcompat.app.AppCompatActivity) r8
            r8.y(r7)
        L5e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.J0(int, boolean):boolean");
    }

    public void K(int i2, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i2 >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.l4;
                if (i2 < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i2];
                }
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.o) && !this.r4) {
            this.L3.a().onPanelClosed(i2, menu);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void K0(int i2, boolean z, Configuration configuration) {
        Resources resources = this.J3.getResources();
        Configuration configuration2 = new Configuration(resources.getConfiguration());
        if (configuration != null) {
            configuration2.updateFrom(configuration);
        }
        configuration2.uiMode = i2 | (resources.getConfiguration().uiMode & (-49));
        resources.updateConfiguration(configuration2, null);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 26) {
            b.b.k.g.a(resources);
        }
        int i4 = this.t4;
        if (i4 != 0) {
            this.J3.setTheme(i4);
            if (i3 >= 23) {
                this.J3.getTheme().applyStyle(this.t4, true);
            }
        }
        if (z) {
            Object obj = this.I3;
            if (obj instanceof Activity) {
                Activity activity = (Activity) obj;
                if (activity instanceof b.l.f) {
                    if (((b.l.f) activity).a().b().isAtLeast(Lifecycle.State.STARTED)) {
                        activity.onConfigurationChanged(configuration2);
                    }
                } else if (this.q4) {
                    activity.onConfigurationChanged(configuration2);
                }
            }
        }
    }

    public void L(b.b.p.j.g gVar) {
        if (this.k4) {
            return;
        }
        this.k4 = true;
        this.Q3.l();
        Window.Callback callbackF0 = f0();
        if (callbackF0 != null && !this.r4) {
            callbackF0.onPanelClosed(108, gVar);
        }
        this.k4 = false;
    }

    public final int L0(a0 a0Var, Rect rect) {
        boolean z;
        boolean z2;
        int iG = a0Var != null ? a0Var.g() : rect != null ? rect.top : 0;
        ActionBarContextView actionBarContextView = this.U3;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.U3.getLayoutParams();
            if (this.U3.isShown()) {
                if (this.C4 == null) {
                    this.C4 = new Rect();
                    this.D4 = new Rect();
                }
                Rect rect2 = this.C4;
                Rect rect3 = this.D4;
                if (a0Var == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(a0Var.e(), a0Var.g(), a0Var.f(), a0Var.d());
                }
                k0.a(this.a4, rect2, rect3);
                int i2 = rect2.top;
                int i3 = rect2.left;
                int i4 = rect2.right;
                a0 a0VarZ = b.f.m.s.z(this.a4);
                int iE = a0VarZ == null ? 0 : a0VarZ.e();
                int iF = a0VarZ == null ? 0 : a0VarZ.f();
                if (marginLayoutParams.topMargin == i2 && marginLayoutParams.leftMargin == i3 && marginLayoutParams.rightMargin == i4) {
                    z2 = false;
                } else {
                    marginLayoutParams.topMargin = i2;
                    marginLayoutParams.leftMargin = i3;
                    marginLayoutParams.rightMargin = i4;
                    z2 = true;
                }
                if (i2 <= 0 || this.c4 != null) {
                    View view = this.c4;
                    if (view != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                        int i5 = marginLayoutParams2.height;
                        int i6 = marginLayoutParams.topMargin;
                        if (i5 != i6 || marginLayoutParams2.leftMargin != iE || marginLayoutParams2.rightMargin != iF) {
                            marginLayoutParams2.height = i6;
                            marginLayoutParams2.leftMargin = iE;
                            marginLayoutParams2.rightMargin = iF;
                            this.c4.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view2 = new View(this.J3);
                    this.c4 = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = iE;
                    layoutParams.rightMargin = iF;
                    this.a4.addView(this.c4, -1, layoutParams);
                }
                View view3 = this.c4;
                z = view3 != null;
                if (z && view3.getVisibility() != 0) {
                    M0(this.c4);
                }
                if (!this.h4 && z) {
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
                this.U3.setLayoutParams(marginLayoutParams);
            }
        }
        View view4 = this.c4;
        if (view4 != null) {
            view4.setVisibility(z ? 0 : 8);
        }
        return iG;
    }

    public final void M() {
        l lVar = this.w4;
        if (lVar != null) {
            lVar.a();
        }
        l lVar2 = this.x4;
        if (lVar2 != null) {
            lVar2.a();
        }
    }

    public final void M0(View view) {
        view.setBackgroundColor((b.f.m.s.B(view) & 8192) != 0 ? b.f.f.a.a(this.J3, b.b.c.f715b) : b.f.f.a.a(this.J3, b.b.c.f714a));
    }

    public void N(int i2) {
        O(d0(i2, true), true);
    }

    public void O(PanelFeatureState panelFeatureState, boolean z) {
        ViewGroup viewGroup;
        b.b.q.o oVar;
        if (z && panelFeatureState.f119a == 0 && (oVar = this.Q3) != null && oVar.c()) {
            L(panelFeatureState.j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.J3.getSystemService("window");
        if (windowManager != null && panelFeatureState.o && (viewGroup = panelFeatureState.f125g) != null) {
            windowManager.removeView(viewGroup);
            if (z) {
                K(panelFeatureState.f119a, panelFeatureState, null);
            }
        }
        panelFeatureState.m = false;
        panelFeatureState.n = false;
        panelFeatureState.o = false;
        panelFeatureState.f126h = null;
        panelFeatureState.q = true;
        if (this.m4 == panelFeatureState) {
            this.m4 = null;
        }
    }

    public final Configuration P(Context context, int i2, Configuration configuration) {
        int i3 = i2 != 1 ? i2 != 2 ? context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i3 | (configuration2.uiMode & (-49));
        return configuration2;
    }

    public final ViewGroup Q() {
        ViewGroup viewGroup;
        TypedArray typedArrayObtainStyledAttributes = this.J3.obtainStyledAttributes(b.b.j.B0);
        int i2 = b.b.j.G0;
        if (!typedArrayObtainStyledAttributes.hasValue(i2)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(b.b.j.P0, false)) {
            z(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(i2, false)) {
            z(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(b.b.j.H0, false)) {
            z(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(b.b.j.I0, false)) {
            z(10);
        }
        this.i4 = typedArrayObtainStyledAttributes.getBoolean(b.b.j.C0, false);
        typedArrayObtainStyledAttributes.recycle();
        X();
        this.K3.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.J3);
        if (this.j4) {
            viewGroup = this.h4 ? (ViewGroup) layoutInflaterFrom.inflate(b.b.g.q, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(b.b.g.p, (ViewGroup) null);
        } else if (this.i4) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(b.b.g.f756h, (ViewGroup) null);
            this.g4 = false;
            this.f4 = false;
        } else if (this.f4) {
            TypedValue typedValue = new TypedValue();
            this.J3.getTheme().resolveAttribute(b.b.a.f710g, typedValue, true);
            int i3 = typedValue.resourceId;
            viewGroup = (ViewGroup) LayoutInflater.from(i3 != 0 ? new b.b.p.d(this.J3, i3) : this.J3).inflate(b.b.g.r, (ViewGroup) null);
            b.b.q.o oVar = (b.b.q.o) viewGroup.findViewById(b.b.f.q);
            this.Q3 = oVar;
            oVar.setWindowCallback(f0());
            if (this.g4) {
                this.Q3.k(109);
            }
            if (this.d4) {
                this.Q3.k(2);
            }
            if (this.e4) {
                this.Q3.k(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.f4 + ", windowActionBarOverlay: " + this.g4 + ", android:windowIsFloating: " + this.i4 + ", windowActionModeOverlay: " + this.h4 + ", windowNoTitle: " + this.j4 + " }");
        }
        if (Build.VERSION.SDK_INT >= 21) {
            b.f.m.s.a0(viewGroup, new c());
        } else if (viewGroup instanceof b.b.q.s) {
            ((b.b.q.s) viewGroup).setOnFitSystemWindowsListener(new d());
        }
        if (this.Q3 == null) {
            this.b4 = (TextView) viewGroup.findViewById(b.b.f.S);
        }
        k0.c(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(b.b.f.f741b);
        ViewGroup viewGroup2 = (ViewGroup) this.K3.findViewById(R.id.content);
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
        this.K3.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new e());
        return viewGroup;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View R(View view, String str, Context context, AttributeSet attributeSet) {
        boolean zE0 = false;
        if (this.E4 == null) {
            String string = this.J3.obtainStyledAttributes(b.b.j.B0).getString(b.b.j.F0);
            if (string == null) {
                this.E4 = new b.b.k.f();
            } else {
                try {
                    this.E4 = (b.b.k.f) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.E4 = new b.b.k.f();
                }
            }
        }
        boolean z = y;
        if (z) {
            if (!(attributeSet instanceof XmlPullParser)) {
                zE0 = E0((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                zE0 = true;
            }
        }
        return this.E4.q(view, str, context, attributeSet, zE0, z, true, j0.b());
    }

    public void S() {
        b.b.p.j.g gVar;
        b.b.q.o oVar = this.Q3;
        if (oVar != null) {
            oVar.l();
        }
        if (this.V3 != null) {
            this.K3.getDecorView().removeCallbacks(this.W3);
            if (this.V3.isShowing()) {
                try {
                    this.V3.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.V3 = null;
        }
        V();
        PanelFeatureState panelFeatureStateD0 = d0(0, false);
        if (panelFeatureStateD0 == null || (gVar = panelFeatureStateD0.j) == null) {
            return;
        }
        gVar.close();
    }

    public boolean T(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.I3;
        if (((obj instanceof e.a) || (obj instanceof b.b.k.e)) && (decorView = this.K3.getDecorView()) != null && b.f.m.e.d(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.L3.a().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? p0(keyCode, keyEvent) : s0(keyCode, keyEvent);
    }

    public void U(int i2) {
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
        if ((i2 != 108 && i2 != 0) || this.Q3 == null || (panelFeatureStateD0 = d0(0, false)) == null) {
            return;
        }
        panelFeatureStateD0.m = false;
        A0(panelFeatureStateD0, null);
    }

    public void V() {
        w wVar = this.X3;
        if (wVar != null) {
            wVar.b();
        }
    }

    public final void W() {
        if (this.Z3) {
            return;
        }
        this.a4 = Q();
        CharSequence charSequenceE0 = e0();
        if (!TextUtils.isEmpty(charSequenceE0)) {
            b.b.q.o oVar = this.Q3;
            if (oVar != null) {
                oVar.setWindowTitle(charSequenceE0);
            } else if (y0() != null) {
                y0().u(charSequenceE0);
            } else {
                TextView textView = this.b4;
                if (textView != null) {
                    textView.setText(charSequenceE0);
                }
            }
        }
        H();
        w0(this.a4);
        this.Z3 = true;
        PanelFeatureState panelFeatureStateD0 = d0(0, false);
        if (this.r4) {
            return;
        }
        if (panelFeatureStateD0 == null || panelFeatureStateD0.j == null) {
            k0(108);
        }
    }

    public final void X() {
        if (this.K3 == null) {
            Object obj = this.I3;
            if (obj instanceof Activity) {
                I(((Activity) obj).getWindow());
            }
        }
        if (this.K3 == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    public PanelFeatureState Y(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.l4;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
            if (panelFeatureState != null && panelFeatureState.j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    @Override // b.b.p.j.g.a
    public boolean a(b.b.p.j.g gVar, MenuItem menuItem) {
        PanelFeatureState panelFeatureStateY;
        Window.Callback callbackF0 = f0();
        if (callbackF0 == null || this.r4 || (panelFeatureStateY = Y(gVar.D())) == null) {
            return false;
        }
        return callbackF0.onMenuItemSelected(panelFeatureStateY.f119a, menuItem);
    }

    public final Context a0() {
        b.b.k.a aVarM = m();
        Context contextK = aVarM != null ? aVarM.k() : null;
        return contextK == null ? this.J3 : contextK;
    }

    @Override // b.b.p.j.g.a
    public void b(b.b.p.j.g gVar) {
        B0(true);
    }

    public final l b0(Context context) {
        if (this.x4 == null) {
            this.x4 = new k(context);
        }
        return this.x4;
    }

    public final l c0(Context context) {
        if (this.w4 == null) {
            this.w4 = new m(b.b.k.i.a(context));
        }
        return this.w4;
    }

    @Override // b.b.k.d
    public void d(View view, ViewGroup.LayoutParams layoutParams) {
        W();
        ((ViewGroup) this.a4.findViewById(R.id.content)).addView(view, layoutParams);
        this.L3.a().onContentChanged();
    }

    public PanelFeatureState d0(int i2, boolean z) {
        PanelFeatureState[] panelFeatureStateArr = this.l4;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i2) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[i2 + 1];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.l4 = panelFeatureStateArr2;
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

    public final CharSequence e0() {
        Object obj = this.I3;
        return obj instanceof Activity ? ((Activity) obj).getTitle() : this.P3;
    }

    @Override // b.b.k.d
    public Context f(Context context) {
        this.o4 = true;
        int iN0 = n0(context, J());
        if (G3 && (context instanceof ContextThemeWrapper)) {
            try {
                q.a((ContextThemeWrapper) context, P(context, iN0, null));
                return context;
            } catch (IllegalStateException unused) {
            }
        }
        if (context instanceof b.b.p.d) {
            try {
                ((b.b.p.d) context).a(P(context, iN0, null));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (!F3) {
            return super.f(context);
        }
        try {
            Configuration configuration = context.getPackageManager().getResourcesForApplication(context.getApplicationInfo()).getConfiguration();
            Configuration configuration2 = context.getResources().getConfiguration();
            Configuration configurationP = P(context, iN0, configuration.equals(configuration2) ? null : Z(configuration, configuration2));
            b.b.p.d dVar = new b.b.p.d(context, b.b.i.f769c);
            dVar.a(configurationP);
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

    public final Window.Callback f0() {
        return this.K3.getCallback();
    }

    public final void g0() {
        W();
        if (this.f4 && this.N3 == null) {
            Object obj = this.I3;
            if (obj instanceof Activity) {
                this.N3 = new b.b.k.j((Activity) this.I3, this.g4);
            } else if (obj instanceof Dialog) {
                this.N3 = new b.b.k.j((Dialog) this.I3);
            }
            b.b.k.a aVar = this.N3;
            if (aVar != null) {
                aVar.s(this.B4);
            }
        }
    }

    public final boolean h0(PanelFeatureState panelFeatureState) {
        View view = panelFeatureState.f127i;
        if (view != null) {
            panelFeatureState.f126h = view;
            return true;
        }
        if (panelFeatureState.j == null) {
            return false;
        }
        if (this.S3 == null) {
            this.S3 = new s();
        }
        View view2 = (View) panelFeatureState.a(this.S3);
        panelFeatureState.f126h = view2;
        return view2 != null;
    }

    @Override // b.b.k.d
    public <T extends View> T i(int i2) {
        W();
        return (T) this.K3.findViewById(i2);
    }

    public final boolean i0(PanelFeatureState panelFeatureState) {
        panelFeatureState.d(a0());
        panelFeatureState.f125g = new r(panelFeatureState.l);
        panelFeatureState.f121c = 81;
        return true;
    }

    public final boolean j0(PanelFeatureState panelFeatureState) {
        Context context = this.J3;
        int i2 = panelFeatureState.f119a;
        if ((i2 == 0 || i2 == 108) && this.Q3 != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(b.b.a.f710g, typedValue, true);
            Resources.Theme themeNewTheme = null;
            if (typedValue.resourceId != 0) {
                themeNewTheme = context.getResources().newTheme();
                themeNewTheme.setTo(theme);
                themeNewTheme.applyStyle(typedValue.resourceId, true);
                themeNewTheme.resolveAttribute(b.b.a.f711h, typedValue, true);
            } else {
                theme.resolveAttribute(b.b.a.f711h, typedValue, true);
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
        gVar.R(this);
        panelFeatureState.c(gVar);
        return true;
    }

    @Override // b.b.k.d
    public int k() {
        return this.s4;
    }

    public final void k0(int i2) {
        this.z4 = (1 << i2) | this.z4;
        if (this.y4) {
            return;
        }
        b.f.m.s.O(this.K3.getDecorView(), this.A4);
        this.y4 = true;
    }

    @Override // b.b.k.d
    public MenuInflater l() {
        if (this.O3 == null) {
            g0();
            b.b.k.a aVar = this.N3;
            this.O3 = new b.b.p.g(aVar != null ? aVar.k() : this.J3);
        }
        return this.O3;
    }

    public final boolean l0() {
        if (!this.v4 && (this.I3 instanceof Activity)) {
            PackageManager packageManager = this.J3.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            try {
                int i2 = Build.VERSION.SDK_INT;
                ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(this.J3, this.I3.getClass()), i2 >= 29 ? 269221888 : i2 >= 24 ? 786432 : 0);
                this.u4 = (activityInfo == null || (activityInfo.configChanges & 512) == 0) ? false : true;
            } catch (PackageManager.NameNotFoundException e2) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e2);
                this.u4 = false;
            }
        }
        this.v4 = true;
        return this.u4;
    }

    @Override // b.b.k.d
    public b.b.k.a m() {
        g0();
        return this.N3;
    }

    public boolean m0() {
        return this.Y3;
    }

    @Override // b.b.k.d
    public void n() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.J3);
        if (layoutInflaterFrom.getFactory() == null) {
            b.f.m.f.b(layoutInflaterFrom, this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof AppCompatDelegateImpl) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public int n0(Context context, int i2) {
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

    @Override // b.b.k.d
    public void o() {
        b.b.k.a aVarM = m();
        if (aVarM == null || !aVarM.m()) {
            k0(0);
        }
    }

    public boolean o0() {
        b.b.p.b bVar = this.T3;
        if (bVar != null) {
            bVar.c();
            return true;
        }
        b.b.k.a aVarM = m();
        return aVarM != null && aVarM.h();
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return R(view, str, context, attributeSet);
    }

    @Override // b.b.k.d
    public void p(Configuration configuration) {
        b.b.k.a aVarM;
        if (this.f4 && this.Z3 && (aVarM = m()) != null) {
            aVarM.n(configuration);
        }
        b.b.q.f.b().g(this.J3);
        G(false);
    }

    public boolean p0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            this.n4 = (keyEvent.getFlags() & 128) != 0;
        } else if (i2 == 82) {
            q0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // b.b.k.d
    public void q(Bundle bundle) {
        this.o4 = true;
        G(false);
        X();
        Object obj = this.I3;
        if (obj instanceof Activity) {
            String strC = null;
            try {
                strC = b.f.e.d.c((Activity) obj);
            } catch (IllegalArgumentException unused) {
            }
            if (strC != null) {
                b.b.k.a aVarY0 = y0();
                if (aVarY0 == null) {
                    this.B4 = true;
                } else {
                    aVarY0.s(true);
                }
            }
            b.b.k.d.c(this);
        }
        this.p4 = true;
    }

    public final boolean q0(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        PanelFeatureState panelFeatureStateD0 = d0(i2, true);
        if (panelFeatureStateD0.o) {
            return false;
        }
        return A0(panelFeatureStateD0, keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0048  */
    @Override // b.b.k.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void r() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.I3
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L9
            b.b.k.d.x(r3)
        L9:
            boolean r0 = r3.y4
            if (r0 == 0) goto L18
            android.view.Window r0 = r3.K3
            android.view.View r0 = r0.getDecorView()
            java.lang.Runnable r1 = r3.A4
            r0.removeCallbacks(r1)
        L18:
            r0 = 0
            r3.q4 = r0
            r0 = 1
            r3.r4 = r0
            int r0 = r3.s4
            r1 = -100
            if (r0 == r1) goto L48
            java.lang.Object r0 = r3.I3
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L48
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L48
            b.d.g<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.x
            java.lang.Object r1 = r3.I3
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.s4
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L57
        L48:
            b.d.g<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.x
            java.lang.Object r1 = r3.I3
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L57:
            b.b.k.a r0 = r3.N3
            if (r0 == 0) goto L5e
            r0.o()
        L5e:
            r3.M()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.r():void");
    }

    public boolean r0(int i2, KeyEvent keyEvent) {
        b.b.k.a aVarM = m();
        if (aVarM != null && aVarM.p(i2, keyEvent)) {
            return true;
        }
        PanelFeatureState panelFeatureState = this.m4;
        if (panelFeatureState != null && z0(panelFeatureState, keyEvent.getKeyCode(), keyEvent, 1)) {
            PanelFeatureState panelFeatureState2 = this.m4;
            if (panelFeatureState2 != null) {
                panelFeatureState2.n = true;
            }
            return true;
        }
        if (this.m4 == null) {
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

    @Override // b.b.k.d
    public void s(Bundle bundle) {
        W();
    }

    public boolean s0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            boolean z = this.n4;
            this.n4 = false;
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

    @Override // b.b.k.d
    public void t() {
        b.b.k.a aVarM = m();
        if (aVarM != null) {
            aVarM.t(true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean t0(int r5, android.view.KeyEvent r6) {
        /*
            r4 = this;
            b.b.p.b r0 = r4.T3
            r1 = 0
            if (r0 == 0) goto L6
            return r1
        L6:
            r0 = 1
            androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r2 = r4.d0(r5, r0)
            if (r5 != 0) goto L43
            b.b.q.o r5 = r4.Q3
            if (r5 == 0) goto L43
            boolean r5 = r5.g()
            if (r5 == 0) goto L43
            android.content.Context r5 = r4.J3
            android.view.ViewConfiguration r5 = android.view.ViewConfiguration.get(r5)
            boolean r5 = r5.hasPermanentMenuKey()
            if (r5 != 0) goto L43
            b.b.q.o r5 = r4.Q3
            boolean r5 = r5.c()
            if (r5 != 0) goto L3c
            boolean r5 = r4.r4
            if (r5 != 0) goto L62
            boolean r5 = r4.A0(r2, r6)
            if (r5 == 0) goto L62
            b.b.q.o r5 = r4.Q3
            boolean r0 = r5.e()
            goto L68
        L3c:
            b.b.q.o r5 = r4.Q3
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
            android.content.Context r5 = r4.J3
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

    @Override // b.b.k.d
    public void u(Bundle bundle) {
    }

    public void u0(int i2) {
        b.b.k.a aVarM;
        if (i2 != 108 || (aVarM = m()) == null) {
            return;
        }
        aVarM.i(true);
    }

    @Override // b.b.k.d
    public void v() {
        this.q4 = true;
        F();
    }

    public void v0(int i2) {
        if (i2 == 108) {
            b.b.k.a aVarM = m();
            if (aVarM != null) {
                aVarM.i(false);
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

    @Override // b.b.k.d
    public void w() {
        this.q4 = false;
        b.b.k.a aVarM = m();
        if (aVarM != null) {
            aVarM.t(false);
        }
    }

    public void w0(ViewGroup viewGroup) {
    }

    public final void x0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        int i2;
        ViewGroup.LayoutParams layoutParams;
        if (panelFeatureState.o || this.r4) {
            return;
        }
        if (panelFeatureState.f119a == 0) {
            if ((this.J3.getResources().getConfiguration().screenLayout & 15) == 4) {
                return;
            }
        }
        Window.Callback callbackF0 = f0();
        if (callbackF0 != null && !callbackF0.onMenuOpened(panelFeatureState.f119a, panelFeatureState.j)) {
            O(panelFeatureState, true);
            return;
        }
        WindowManager windowManager = (WindowManager) this.J3.getSystemService("window");
        if (windowManager != null && A0(panelFeatureState, keyEvent)) {
            ViewGroup viewGroup = panelFeatureState.f125g;
            if (viewGroup != null && !panelFeatureState.q) {
                View view = panelFeatureState.f127i;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i2 = -1;
                }
                panelFeatureState.n = false;
                WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i2, -2, panelFeatureState.f122d, panelFeatureState.f123e, 1002, 8519680, -3);
                layoutParams2.gravity = panelFeatureState.f121c;
                layoutParams2.windowAnimations = panelFeatureState.f124f;
                windowManager.addView(panelFeatureState.f125g, layoutParams2);
                panelFeatureState.o = true;
            }
            if (viewGroup == null) {
                if (!i0(panelFeatureState) || panelFeatureState.f125g == null) {
                    return;
                }
            } else if (panelFeatureState.q && viewGroup.getChildCount() > 0) {
                panelFeatureState.f125g.removeAllViews();
            }
            if (!h0(panelFeatureState) || !panelFeatureState.b()) {
                panelFeatureState.q = true;
                return;
            }
            ViewGroup.LayoutParams layoutParams3 = panelFeatureState.f126h.getLayoutParams();
            if (layoutParams3 == null) {
                layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
            }
            panelFeatureState.f125g.setBackgroundResource(panelFeatureState.f120b);
            ViewParent parent = panelFeatureState.f126h.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(panelFeatureState.f126h);
            }
            panelFeatureState.f125g.addView(panelFeatureState.f126h, layoutParams3);
            if (!panelFeatureState.f126h.hasFocus()) {
                panelFeatureState.f126h.requestFocus();
            }
            i2 = -2;
            panelFeatureState.n = false;
            WindowManager.LayoutParams layoutParams22 = new WindowManager.LayoutParams(i2, -2, panelFeatureState.f122d, panelFeatureState.f123e, 1002, 8519680, -3);
            layoutParams22.gravity = panelFeatureState.f121c;
            layoutParams22.windowAnimations = panelFeatureState.f124f;
            windowManager.addView(panelFeatureState.f125g, layoutParams22);
            panelFeatureState.o = true;
        }
    }

    public final b.b.k.a y0() {
        return this.N3;
    }

    @Override // b.b.k.d
    public boolean z(int i2) {
        int iC0 = C0(i2);
        if (this.j4 && iC0 == 108) {
            return false;
        }
        if (this.f4 && iC0 == 1) {
            this.f4 = false;
        }
        if (iC0 == 1) {
            H0();
            this.j4 = true;
            return true;
        }
        if (iC0 == 2) {
            H0();
            this.d4 = true;
            return true;
        }
        if (iC0 == 5) {
            H0();
            this.e4 = true;
            return true;
        }
        if (iC0 == 10) {
            H0();
            this.h4 = true;
            return true;
        }
        if (iC0 == 108) {
            H0();
            this.f4 = true;
            return true;
        }
        if (iC0 != 109) {
            return this.K3.requestFeature(iC0);
        }
        H0();
        this.g4 = true;
        return true;
    }

    public final boolean z0(PanelFeatureState panelFeatureState, int i2, KeyEvent keyEvent, int i3) {
        b.b.p.j.g gVar;
        boolean zPerformShortcut = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.m || A0(panelFeatureState, keyEvent)) && (gVar = panelFeatureState.j) != null) {
            zPerformShortcut = gVar.performShortcut(i2, keyEvent, i3);
        }
        if (zPerformShortcut && (i3 & 1) == 0 && this.Q3 == null) {
            O(panelFeatureState, true);
        }
        return zPerformShortcut;
    }

    public AppCompatDelegateImpl(Dialog dialog, b.b.k.c cVar) {
        this(dialog.getContext(), dialog.getWindow(), cVar, dialog);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    public AppCompatDelegateImpl(Context context, Window window, b.b.k.c cVar, Object obj) {
        b.d.g<String, Integer> gVar;
        Integer num;
        AppCompatActivity appCompatActivityI0;
        this.X3 = null;
        this.Y3 = true;
        this.s4 = -100;
        this.A4 = new b();
        this.J3 = context;
        this.M3 = cVar;
        this.I3 = obj;
        if (this.s4 == -100 && (obj instanceof Dialog) && (appCompatActivityI0 = I0()) != null) {
            this.s4 = appCompatActivityI0.v().k();
        }
        if (this.s4 == -100 && (num = (gVar = x).get(obj.getClass().getName())) != null) {
            this.s4 = num.intValue();
            gVar.remove(obj.getClass().getName());
        }
        if (window != null) {
            I(window);
        }
        b.b.q.f.h();
    }

    public class j extends b.b.p.i {
        public j(Window.Callback callback) {
            super(callback);
        }

        public final ActionMode b(ActionMode.Callback callback) {
            f.a aVar = new f.a(AppCompatDelegateImpl.this.J3, callback);
            b.b.p.b bVarF0 = AppCompatDelegateImpl.this.F0(aVar);
            if (bVarF0 != null) {
                return aVar.e(bVarF0);
            }
            return null;
        }

        @Override // b.b.p.i, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.T(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // b.b.p.i, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.r0(keyEvent.getKeyCode(), keyEvent);
        }

        @Override // android.view.Window.Callback
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
            AppCompatDelegateImpl.this.u0(i2);
            return true;
        }

        @Override // b.b.p.i, android.view.Window.Callback
        public void onPanelClosed(int i2, Menu menu) {
            super.onPanelClosed(i2, menu);
            AppCompatDelegateImpl.this.v0(i2);
        }

        @Override // b.b.p.i, android.view.Window.Callback
        public boolean onPreparePanel(int i2, View view, Menu menu) {
            b.b.p.j.g gVar = menu instanceof b.b.p.j.g ? (b.b.p.j.g) menu : null;
            if (i2 == 0 && gVar == null) {
                return false;
            }
            if (gVar != null) {
                gVar.a0(true);
            }
            boolean zOnPreparePanel = super.onPreparePanel(i2, view, menu);
            if (gVar != null) {
                gVar.a0(false);
            }
            return zOnPreparePanel;
        }

        @Override // b.b.p.i, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i2) {
            b.b.p.j.g gVar;
            PanelFeatureState panelFeatureStateD0 = AppCompatDelegateImpl.this.d0(0, true);
            if (panelFeatureStateD0 == null || (gVar = panelFeatureStateD0.j) == null) {
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
            return AppCompatDelegateImpl.this.m0() ? b(callback) : super.onWindowStartingActionMode(callback);
        }

        @Override // b.b.p.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i2) {
            if (AppCompatDelegateImpl.this.m0() && i2 == 0) {
                return b(callback);
            }
            return super.onWindowStartingActionMode(callback, i2);
        }
    }
}
