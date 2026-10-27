package a.b.k;

import a.b.k.l;
import a.b.k.s;
import a.b.k.u;
import a.b.o.a;
import a.b.o.i.g;
import a.b.o.i.m;
import a.b.p.b0;
import a.b.p.b1;
import a.b.p.c0;
import a.b.p.c1;
import a.b.p.x0;
import a.f.a.i.d;
import android.R;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.util.Property;
import android.util.TypedValue;
import android.util.Xml;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RemoteViews;
import android.widget.TextView;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import b.c.a.f.w;
import b.d.a.a.e0.g;
import b.d.a.a.u.c;
import com.byd.globalnotification.notification.NotifyMsgBean;
import java.io.IOException;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class l extends a.b.k.k implements g.a, LayoutInflater.Factory2 {
    public static final boolean a0 = false;
    public static boolean e0;
    public boolean A;
    public boolean B;
    public boolean C;
    public boolean D;
    public boolean E;
    public k[] F;
    public k G;
    public boolean H;
    public boolean I;
    public boolean J;
    public boolean K;
    public boolean L;
    public int M;
    public int N;
    public boolean O;
    public boolean P;
    public g Q;
    public g R;
    public boolean S;
    public int T;
    public boolean V;
    public Rect W;
    public Rect X;
    public s Y;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f20c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f21d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Window f22e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public e f23f;
    public final a.b.k.j g;
    public a.b.k.e h;
    public MenuInflater i;
    public CharSequence j;
    public c0 k;
    public c l;
    public C0000l m;
    public a.b.o.a n;
    public ActionBarContextView o;
    public PopupWindow p;
    public Runnable q;
    public boolean t;
    public ViewGroup u;
    public TextView v;
    public View w;
    public boolean x;
    public boolean y;
    public boolean z;
    public static final a.e.h<String, Integer> Z = new a.e.h<>();
    public static final int[] b0 = {R.attr.windowBackground};
    public static final boolean c0 = !"robolectric".equals(Build.FINGERPRINT);
    public static final boolean d0 = true;
    public a.h.k.s r = null;
    public boolean s = true;
    public final Runnable U = new b();

    public class a implements Thread.UncaughtExceptionHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Thread.UncaughtExceptionHandler f24a;

        public a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.f24a = uncaughtExceptionHandler;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            String message;
            boolean z = false;
            if ((th instanceof Resources.NotFoundException) && (message = th.getMessage()) != null && (message.contains("drawable") || message.contains("Drawable"))) {
                z = true;
            }
            if (!z) {
                this.f24a.uncaughtException(thread, th);
                return;
            }
            Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            notFoundException.initCause(th.getCause());
            notFoundException.setStackTrace(th.getStackTrace());
            this.f24a.uncaughtException(thread, notFoundException);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l lVar = l.this;
            if ((lVar.T & 1) != 0) {
                lVar.y(0);
            }
            l lVar2 = l.this;
            if ((lVar2.T & 4096) != 0) {
                lVar2.y(108);
            }
            l lVar3 = l.this;
            lVar3.S = false;
            lVar3.T = 0;
        }
    }

    public final class c implements m.a {
        public c() {
        }

        @Override // a.b.o.i.m.a
        public void b(a.b.o.i.g gVar, boolean z) {
            l.this.u(gVar);
        }

        @Override // a.b.o.i.m.a
        public boolean c(a.b.o.i.g gVar) {
            Window.Callback callbackF = l.this.F();
            if (callbackF == null) {
                return true;
            }
            callbackF.onMenuOpened(108, gVar);
            return true;
        }
    }

    public class d implements a.InterfaceC0001a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public a.InterfaceC0001a f27a;

        public class a extends a.h.k.u {
            public a() {
            }

            @Override // a.h.k.t
            public void a(View view) {
                l.this.o.setVisibility(8);
                l lVar = l.this;
                PopupWindow popupWindow = lVar.p;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (lVar.o.getParent() instanceof View) {
                    a.h.k.q.S((View) l.this.o.getParent());
                }
                l.this.o.removeAllViews();
                l.this.r.d(null);
                l lVar2 = l.this;
                lVar2.r = null;
                a.h.k.q.S(lVar2.u);
            }
        }

        public d(a.InterfaceC0001a interfaceC0001a) {
            this.f27a = interfaceC0001a;
        }

        @Override // a.b.o.a.InterfaceC0001a
        public boolean a(a.b.o.a aVar, Menu menu) {
            a.h.k.q.S(l.this.u);
            return this.f27a.a(aVar, menu);
        }

        @Override // a.b.o.a.InterfaceC0001a
        public void b(a.b.o.a aVar) {
            this.f27a.b(aVar);
            l lVar = l.this;
            if (lVar.p != null) {
                lVar.f22e.getDecorView().removeCallbacks(l.this.q);
            }
            l lVar2 = l.this;
            if (lVar2.o != null) {
                lVar2.z();
                l lVar3 = l.this;
                a.h.k.s sVarB = a.h.k.q.b(lVar3.o);
                sVarB.a(0.0f);
                lVar3.r = sVarB;
                a.h.k.s sVar = l.this.r;
                a aVar2 = new a();
                View view = sVar.f835a.get();
                if (view != null) {
                    sVar.e(view, aVar2);
                }
            }
            l lVar4 = l.this;
            a.b.k.j jVar = lVar4.g;
            if (jVar != null) {
                jVar.f(lVar4.n);
            }
            l lVar5 = l.this;
            lVar5.n = null;
            a.h.k.q.S(lVar5.u);
        }

        @Override // a.b.o.a.InterfaceC0001a
        public boolean c(a.b.o.a aVar, MenuItem menuItem) {
            return this.f27a.c(aVar, menuItem);
        }

        @Override // a.b.o.a.InterfaceC0001a
        public boolean d(a.b.o.a aVar, Menu menu) {
            return this.f27a.d(aVar, menu);
        }
    }

    public class e extends a.b.o.h {
        public e(Window.Callback callback) {
            super(callback);
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x004e  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0052  */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final android.view.ActionMode a(android.view.ActionMode.Callback r9) {
            /*
                Method dump skipped, instruction units count: 441
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: a.b.k.l.e.a(android.view.ActionMode$Callback):android.view.ActionMode");
        }

        @Override // android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return l.this.x(keyEvent) || this.f131a.dispatchKeyEvent(keyEvent);
        }

        /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
        @Override // android.view.Window.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean dispatchKeyShortcutEvent(android.view.KeyEvent r5) {
            /*
                r4 = this;
                android.view.Window$Callback r0 = r4.f131a
                boolean r0 = r0.dispatchKeyShortcutEvent(r5)
                r1 = 0
                r2 = 1
                if (r0 != 0) goto L4f
                a.b.k.l r4 = a.b.k.l.this
                int r0 = r5.getKeyCode()
                r4.G()
                a.b.k.e r3 = r4.h
                if (r3 == 0) goto L1f
                boolean r0 = r3.f(r0, r5)
                if (r0 == 0) goto L1f
            L1d:
                r4 = r2
                goto L4d
            L1f:
                a.b.k.l$k r0 = r4.G
                if (r0 == 0) goto L34
                int r3 = r5.getKeyCode()
                boolean r0 = r4.K(r0, r3, r5, r2)
                if (r0 == 0) goto L34
                a.b.k.l$k r4 = r4.G
                if (r4 == 0) goto L1d
                r4.l = r2
                goto L1d
            L34:
                a.b.k.l$k r0 = r4.G
                if (r0 != 0) goto L4c
                a.b.k.l$k r0 = r4.E(r1)
                r4.L(r0, r5)
                int r3 = r5.getKeyCode()
                boolean r4 = r4.K(r0, r3, r5, r2)
                r0.k = r1
                if (r4 == 0) goto L4c
                goto L1d
            L4c:
                r4 = r1
            L4d:
                if (r4 == 0) goto L50
            L4f:
                r1 = r2
            L50:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: a.b.k.l.e.dispatchKeyShortcutEvent(android.view.KeyEvent):boolean");
        }

        @Override // android.view.Window.Callback
        public void onContentChanged() {
        }

        @Override // android.view.Window.Callback
        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof a.b.o.i.g)) {
                return this.f131a.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        @Override // android.view.Window.Callback
        public boolean onMenuOpened(int i, Menu menu) {
            this.f131a.onMenuOpened(i, menu);
            l lVar = l.this;
            if (lVar == null) {
                throw null;
            }
            if (i == 108) {
                lVar.G();
                a.b.k.e eVar = lVar.h;
                if (eVar != null) {
                    eVar.b(true);
                }
            }
            return true;
        }

        @Override // android.view.Window.Callback
        public void onPanelClosed(int i, Menu menu) {
            this.f131a.onPanelClosed(i, menu);
            l lVar = l.this;
            if (lVar == null) {
                throw null;
            }
            if (i == 108) {
                lVar.G();
                a.b.k.e eVar = lVar.h;
                if (eVar != null) {
                    eVar.b(false);
                    return;
                }
                return;
            }
            if (i == 0) {
                k kVarE = lVar.E(i);
                if (kVarE.m) {
                    lVar.v(kVarE, false);
                }
            }
        }

        @Override // android.view.Window.Callback
        public boolean onPreparePanel(int i, View view, Menu menu) {
            a.b.o.i.g gVar = menu instanceof a.b.o.i.g ? (a.b.o.i.g) menu : null;
            if (i == 0 && gVar == null) {
                return false;
            }
            if (gVar != null) {
                gVar.y = true;
            }
            boolean zOnPreparePanel = this.f131a.onPreparePanel(i, view, menu);
            if (gVar != null) {
                gVar.y = false;
            }
            return zOnPreparePanel;
        }

        @Override // android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            a.b.o.i.g gVar = l.this.E(0).h;
            if (gVar != null) {
                this.f131a.onProvideKeyboardShortcuts(list, gVar, i);
            } else {
                this.f131a.onProvideKeyboardShortcuts(list, menu, i);
            }
        }

        @Override // android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return null;
        }

        @Override // android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            return (l.this.s && i == 0) ? a(callback) : this.f131a.onWindowStartingActionMode(callback, i);
        }
    }

    public class f extends g {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final PowerManager f31c;

        public f(Context context) {
            super();
            this.f31c = (PowerManager) context.getApplicationContext().getSystemService("power");
        }

        @Override // a.b.k.l.g
        public IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        @Override // a.b.k.l.g
        public int c() {
            return this.f31c.isPowerSaveMode() ? 2 : 1;
        }

        @Override // a.b.k.l.g
        public void d() {
            l.this.q();
        }
    }

    public abstract class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public BroadcastReceiver f33a;

        public class a extends BroadcastReceiver {
            public a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                g.this.d();
            }
        }

        public g() {
        }

        public void a() {
            BroadcastReceiver broadcastReceiver = this.f33a;
            if (broadcastReceiver != null) {
                try {
                    l.this.f21d.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.f33a = null;
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
            if (this.f33a == null) {
                this.f33a = new a();
            }
            l.this.f21d.registerReceiver(this.f33a, intentFilterB);
        }
    }

    public class h extends g {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final u f36c;

        public h(u uVar) {
            super();
            this.f36c = uVar;
        }

        @Override // a.b.k.l.g
        public IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // a.b.k.l.g
        public int c() {
            boolean z;
            long j;
            u uVar = this.f36c;
            u.a aVar = uVar.f70c;
            if (aVar.f72b > System.currentTimeMillis()) {
                z = aVar.f71a;
            } else {
                Location locationA = i.j(uVar.f68a, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? uVar.a("network") : null;
                Location locationA2 = i.j(uVar.f68a, "android.permission.ACCESS_FINE_LOCATION") == 0 ? uVar.a("gps") : null;
                if (locationA2 == null || locationA == null ? locationA2 != null : locationA2.getTime() > locationA.getTime()) {
                    locationA = locationA2;
                }
                if (locationA != null) {
                    u.a aVar2 = uVar.f70c;
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (t.f63d == null) {
                        t.f63d = new t();
                    }
                    t tVar = t.f63d;
                    tVar.a(jCurrentTimeMillis - 86400000, locationA.getLatitude(), locationA.getLongitude());
                    tVar.a(jCurrentTimeMillis, locationA.getLatitude(), locationA.getLongitude());
                    boolean z2 = tVar.f66c == 1;
                    long j2 = tVar.f65b;
                    long j3 = tVar.f64a;
                    tVar.a(jCurrentTimeMillis + 86400000, locationA.getLatitude(), locationA.getLongitude());
                    long j4 = tVar.f65b;
                    if (j2 == -1 || j3 == -1) {
                        j = jCurrentTimeMillis + 43200000;
                    } else {
                        j = (jCurrentTimeMillis > j3 ? j4 + 0 : jCurrentTimeMillis > j2 ? j3 + 0 : j2 + 0) + 60000;
                    }
                    aVar2.f71a = z2;
                    aVar2.f72b = j;
                    z = aVar.f71a;
                } else {
                    Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
                    int i = Calendar.getInstance().get(11);
                    z = i < 6 || i >= 22;
                }
            }
            return z ? 2 : 1;
        }

        @Override // a.b.k.l.g
        public void d() {
            l.this.q();
        }
    }

    public static class i {
        public static Drawable A(Context context, TypedArray typedArray, int i) {
            int resourceId;
            Drawable drawableB;
            return (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (drawableB = a.b.l.a.a.b(context, resourceId)) == null) ? typedArray.getDrawable(i) : drawableB;
        }

        public static void A0(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            int i = 1;
            while (i > 0) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    i++;
                } else if (next == 3) {
                    i--;
                }
            }
        }

        public static String B(View view) {
            try {
                return view.getContext().getResources().getResourceEntryName(view.getId());
            } catch (Exception unused) {
                return "UNKNOWN";
            }
        }

        public static void B0(View view, w wVar, NotifyMsgBean notifyMsgBean, boolean z) {
            List<NotifyMsgBean> list;
            view.setVisibility((z && (list = wVar.f1758c.get(notifyMsgBean.getPkgName())) != null && list.indexOf(notifyMsgBean) == 0) ? 0 : 8);
        }

        public static Intent C(Activity activity) {
            Intent parentActivityIntent = activity.getParentActivityIntent();
            if (parentActivityIntent != null) {
                return parentActivityIntent;
            }
            try {
                String strE = E(activity, activity.getComponentName());
                if (strE == null) {
                    return null;
                }
                ComponentName componentName = new ComponentName(activity, strE);
                try {
                    return E(activity, componentName) == null ? Intent.makeMainActivity(componentName) : new Intent().setComponent(componentName);
                } catch (PackageManager.NameNotFoundException unused) {
                    Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + strE + "' in manifest");
                    return null;
                }
            } catch (PackageManager.NameNotFoundException e2) {
                throw new IllegalArgumentException(e2);
            }
        }

        public static void C0(Throwable th) {
            if (th instanceof VirtualMachineError) {
                throw ((VirtualMachineError) th);
            }
            if (th instanceof ThreadDeath) {
                throw ((ThreadDeath) th);
            }
            if (th instanceof LinkageError) {
                throw ((LinkageError) th);
            }
        }

        public static Intent D(Context context, ComponentName componentName) throws PackageManager.NameNotFoundException {
            String strE = E(context, componentName);
            if (strE == null) {
                return null;
            }
            ComponentName componentName2 = new ComponentName(componentName.getPackageName(), strE);
            return E(context, componentName2) == null ? Intent.makeMainActivity(componentName2) : new Intent().setComponent(componentName2);
        }

        public static List<byte[]> D0(String[] strArr) {
            ArrayList arrayList = new ArrayList();
            for (String str : strArr) {
                arrayList.add(Base64.decode(str, 0));
            }
            return arrayList;
        }

        public static String E(Context context, ComponentName componentName) throws PackageManager.NameNotFoundException {
            String string;
            ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(componentName, 269222528);
            String str = activityInfo.parentActivityName;
            if (str != null) {
                return str;
            }
            Bundle bundle = activityInfo.metaData;
            if (bundle == null || (string = bundle.getString("android.support.PARENT_ACTIVITY")) == null) {
                return null;
            }
            if (string.charAt(0) != '.') {
                return string;
            }
            return context.getPackageName() + string;
        }

        public static PorterDuffColorFilter E0(Drawable drawable, ColorStateList colorStateList, PorterDuff.Mode mode) {
            if (colorStateList == null || mode == null) {
                return null;
            }
            return new PorterDuffColorFilter(colorStateList.getColorForState(drawable.getState(), 0), mode);
        }

        public static String F(int i) {
            return b.c.a.h.d.b.a().getString(i);
        }

        public static boolean F0(d.a aVar, d.a aVar2, d.a aVar3, d.a aVar4) {
            d.a aVar5 = d.a.MATCH_PARENT;
            d.a aVar6 = d.a.FIXED;
            d.a aVar7 = d.a.WRAP_CONTENT;
            return (aVar3 == aVar6 || aVar3 == aVar7 || (aVar3 == aVar5 && aVar != aVar7)) || (aVar4 == aVar6 || aVar4 == aVar7 || (aVar4 == aVar5 && aVar2 != aVar7));
        }

        public static TextView G(Toolbar toolbar, CharSequence charSequence) {
            for (int i = 0; i < toolbar.getChildCount(); i++) {
                View childAt = toolbar.getChildAt(i);
                if (childAt instanceof TextView) {
                    TextView textView = (TextView) childAt;
                    if (TextUtils.equals(textView.getText(), charSequence)) {
                        return textView;
                    }
                }
            }
            return null;
        }

        public static boolean H(Drawable drawable) {
            return drawable.isAutoMirrored();
        }

        public static void I(View view, boolean z) {
            view.setTag(Boolean.valueOf(z));
        }

        public static boolean J(Rect rect, Rect rect2, int i) {
            if (i == 17) {
                int i2 = rect.right;
                int i3 = rect2.right;
                return (i2 > i3 || rect.left >= i3) && rect.left > rect2.left;
            }
            if (i == 33) {
                int i4 = rect.bottom;
                int i5 = rect2.bottom;
                return (i4 > i5 || rect.top >= i5) && rect.top > rect2.top;
            }
            if (i == 66) {
                int i6 = rect.left;
                int i7 = rect2.left;
                return (i6 < i7 || rect.right <= i7) && rect.right < rect2.right;
            }
            if (i != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            int i8 = rect.top;
            int i9 = rect2.top;
            return (i8 < i9 || rect.bottom <= i9) && rect.bottom < rect2.bottom;
        }

        public static boolean K(Context context) {
            return context.getResources().getConfiguration().fontScale >= 1.3f;
        }

        public static boolean L(Context context) {
            return context.getResources().getConfiguration().fontScale >= 2.0f;
        }

        public static boolean M(View view) {
            return a.h.k.q.p(view) == 1;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public static void N(b.c.a.h.c.b bVar, Object obj, Object obj2) {
            if (bVar != null) {
                if (obj != null) {
                    b.c.a.h.c.c<T> cVar = bVar.f1778a;
                    if (cVar == 0) {
                        return;
                    }
                    cVar.a((T) obj);
                    return;
                }
                b.c.a.h.c.a aVar = bVar.f1779b;
                if (aVar == null) {
                    return;
                }
                aVar.call();
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public static void O(b.c.a.h.c.b bVar, Object obj, Object obj2) {
            if (bVar != null) {
                if (obj != null) {
                    b.c.a.h.c.c<T> cVar = bVar.f1778a;
                    if (cVar == 0) {
                        return;
                    }
                    cVar.a((T) obj);
                    return;
                }
                b.c.a.h.c.a aVar = bVar.f1779b;
                if (aVar == null) {
                    return;
                }
                aVar.call();
            }
        }

        public static int R(int i, int i2, float f2) {
            return a.h.e.a.a(a.h.e.a.c(i2, Math.round(Color.alpha(i2) * f2)), i);
        }

        public static float S(float f2, float f3, float f4) {
            return (f4 * f3) + ((1.0f - f4) * f2);
        }

        public static a.l.d.i T(Context context, a.l.d.k kVar, Fragment fragment, boolean z) {
            Fragment.b bVar = fragment.I;
            boolean z2 = false;
            int i = bVar == null ? 0 : bVar.f1565e;
            int iO = fragment.o();
            fragment.L(0);
            View viewB = kVar.b(fragment.w);
            if (viewB != null && viewB.getTag(a.l.b.visible_removing_fragment_view_tag) != null) {
                viewB.setTag(a.l.b.visible_removing_fragment_view_tag, null);
            }
            ViewGroup viewGroup = fragment.E;
            if (viewGroup != null && viewGroup.getLayoutTransition() != null) {
                return null;
            }
            if (iO != 0) {
                boolean zEquals = "anim".equals(context.getResources().getResourceTypeName(iO));
                if (zEquals) {
                    try {
                        Animation animationLoadAnimation = AnimationUtils.loadAnimation(context, iO);
                        if (animationLoadAnimation != null) {
                            return new a.l.d.i(animationLoadAnimation);
                        }
                        z2 = true;
                    } catch (Resources.NotFoundException e2) {
                        throw e2;
                    } catch (RuntimeException unused) {
                    }
                }
                if (!z2) {
                    try {
                        Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(context, iO);
                        if (animatorLoadAnimator != null) {
                            return new a.l.d.i(animatorLoadAnimator);
                        }
                    } catch (RuntimeException e3) {
                        if (zEquals) {
                            throw e3;
                        }
                        Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(context, iO);
                        if (animationLoadAnimation2 != null) {
                            return new a.l.d.i(animationLoadAnimation2);
                        }
                    }
                }
            }
            if (i == 0) {
                return null;
            }
            int i2 = i != 4097 ? i != 4099 ? i != 8194 ? -1 : z ? a.l.a.fragment_close_enter : a.l.a.fragment_close_exit : z ? a.l.a.fragment_fade_enter : a.l.a.fragment_fade_exit : z ? a.l.a.fragment_open_enter : a.l.a.fragment_open_exit;
            if (i2 < 0) {
                return null;
            }
            return new a.l.d.i(AnimationUtils.loadAnimation(context, i2));
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x00a8  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static android.graphics.Typeface U(android.content.Context r16, int r17, android.util.TypedValue r18, int r19, a.h.d.b.e r20, android.os.Handler r21, boolean r22, boolean r23) {
            /*
                Method dump skipped, instruction units count: 250
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: a.b.k.l.i.U(android.content.Context, int, android.util.TypedValue, int, a.h.d.b.e, android.os.Handler, boolean, boolean):android.graphics.Typeface");
        }

        public static int V(int i, Rect rect, Rect rect2) {
            int i2;
            int i3;
            if (i == 17) {
                i2 = rect.left;
                i3 = rect2.right;
            } else if (i == 33) {
                i2 = rect.top;
                i3 = rect2.bottom;
            } else if (i == 66) {
                i2 = rect2.left;
                i3 = rect.right;
            } else {
                if (i != 130) {
                    throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                }
                i2 = rect2.top;
                i3 = rect.bottom;
            }
            return Math.max(0, i2 - i3);
        }

        public static int W(int i, Rect rect, Rect rect2) {
            int iHeight;
            int i2;
            int iHeight2;
            if (i == 17) {
                iHeight = (rect.height() / 2) + rect.top;
                i2 = rect2.top;
                iHeight2 = rect2.height();
            } else {
                if (i != 33) {
                    if (i != 66) {
                        if (i != 130) {
                            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                        }
                    }
                    iHeight = (rect.height() / 2) + rect.top;
                    i2 = rect2.top;
                    iHeight2 = rect2.height();
                }
                iHeight = (rect.width() / 2) + rect.left;
                i2 = rect2.left;
                iHeight2 = rect2.width();
            }
            return Math.abs(iHeight - ((iHeight2 / 2) + i2));
        }

        public static <T> ObjectAnimator X(T t, Property<T, PointF> property, Path path) {
            return ObjectAnimator.ofObject(t, property, (TypeConverter) null, path);
        }

        public static <T> void Y(View view, final b.c.a.h.c.b bVar, boolean z, final T t) {
            if (z) {
                if (view != null) {
                    new b.e.a.a.a(view).a(new c.b.g.b() { // from class: a.b.k.b
                        @Override // c.b.g.b
                        public final void accept(Object obj) {
                            l.i.N(bVar, t, obj);
                        }
                    });
                    return;
                } else {
                    d.b.a.a.b("$this$clicks");
                    throw null;
                }
            }
            if (view == null) {
                d.b.a.a.b("$this$clicks");
                throw null;
            }
            b.e.a.a.a aVar = new b.e.a.a.a(view);
            TimeUnit timeUnit = TimeUnit.SECONDS;
            c.b.c cVar = c.b.k.a.f2337a;
            c.b.h.b.b.a(timeUnit, "unit is null");
            c.b.h.b.b.a(cVar, "scheduler is null");
            new c.b.h.d.a.b(aVar, 1L, timeUnit, cVar).a(new c.b.g.b() { // from class: a.b.k.a
                @Override // c.b.g.b
                public final void accept(Object obj) {
                    l.i.O(bVar, t, obj);
                }
            });
        }

        public static InputConnection Z(InputConnection inputConnection, EditorInfo editorInfo, View view) {
            if (inputConnection != null && editorInfo.hintText == null) {
                ViewParent parent = view.getParent();
                while (true) {
                    if (!(parent instanceof View)) {
                        break;
                    }
                    if (parent instanceof c1) {
                        editorInfo.hintText = ((c1) parent).a();
                        break;
                    }
                    parent = parent.getParent();
                }
            }
            return inputConnection;
        }

        /* JADX WARN: Code restructure failed: missing block: B:26:0x0081, code lost:
        
            if (r13 != 0) goto L55;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x008b, code lost:
        
            if (r13 > 0) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00bc, code lost:
        
            if (r10.getId() == com.byd.globalnotification.R.id.divider) goto L55;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x0102, code lost:
        
            if (r10.getId() == com.byd.globalnotification.R.id.divider) goto L55;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static void a(android.view.View r10, b.c.a.f.w r11, com.byd.globalnotification.notification.NotifyMsgBean r12, boolean r13, boolean r14) {
            /*
                Method dump skipped, instruction units count: 268
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: a.b.k.l.i.a(android.view.View, b.c.a.f.w, com.byd.globalnotification.notification.NotifyMsgBean, boolean, boolean):void");
        }

        public static boolean a0(ViewParent viewParent, View view, float f2, float f3, boolean z) {
            try {
                return viewParent.onNestedFling(view, f2, f3, z);
            } catch (AbstractMethodError e2) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedFling", e2);
                return false;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:184:0x02e0  */
        /* JADX WARN: Removed duplicated region for block: B:204:0x0330  */
        /* JADX WARN: Removed duplicated region for block: B:209:0x0347  */
        /* JADX WARN: Removed duplicated region for block: B:211:0x035c  */
        /* JADX WARN: Removed duplicated region for block: B:234:0x03c7  */
        /* JADX WARN: Removed duplicated region for block: B:236:0x03e5  */
        /* JADX WARN: Removed duplicated region for block: B:315:0x057a  */
        /* JADX WARN: Removed duplicated region for block: B:316:0x057f  */
        /* JADX WARN: Removed duplicated region for block: B:322:0x0598  */
        /* JADX WARN: Removed duplicated region for block: B:325:0x05a8 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:337:0x05e7  */
        /* JADX WARN: Removed duplicated region for block: B:377:0x0670  */
        /* JADX WARN: Removed duplicated region for block: B:378:0x0678  */
        /* JADX WARN: Removed duplicated region for block: B:381:0x0685  */
        /* JADX WARN: Removed duplicated region for block: B:382:0x0688  */
        /* JADX WARN: Removed duplicated region for block: B:388:0x06a9  */
        /* JADX WARN: Removed duplicated region for block: B:405:0x071b A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:406:0x071d A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:410:0x0727  */
        /* JADX WARN: Removed duplicated region for block: B:411:0x0729  */
        /* JADX WARN: Removed duplicated region for block: B:414:0x0734  */
        /* JADX WARN: Removed duplicated region for block: B:415:0x0737  */
        /* JADX WARN: Removed duplicated region for block: B:418:0x073d  */
        /* JADX WARN: Removed duplicated region for block: B:419:0x0740  */
        /* JADX WARN: Removed duplicated region for block: B:421:0x0744  */
        /* JADX WARN: Removed duplicated region for block: B:425:0x0753  */
        /* JADX WARN: Removed duplicated region for block: B:427:0x0756  */
        /* JADX WARN: Removed duplicated region for block: B:429:0x0763 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:439:0x077d A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:46:0x00ed  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x00f0  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x0110  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x0116  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x0144  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static void b(a.f.a.i.e r37, a.f.a.d r38, java.util.ArrayList<a.f.a.i.d> r39, int r40) {
            /*
                Method dump skipped, instruction units count: 1932
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: a.b.k.l.i.b(a.f.a.i.e, a.f.a.d, java.util.ArrayList, int):void");
        }

        public static boolean b0(ViewParent viewParent, View view, float f2, float f3) {
            try {
                return viewParent.onNestedPreFling(view, f2, f3);
            } catch (AbstractMethodError e2) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedPreFling", e2);
                return false;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0043  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0045  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static boolean c(int r9, android.graphics.Rect r10, android.graphics.Rect r11, android.graphics.Rect r12) {
            /*
                boolean r0 = d(r9, r10, r11)
                boolean r1 = d(r9, r10, r12)
                r2 = 0
                if (r1 != 0) goto L7d
                if (r0 != 0) goto Lf
                goto L7d
            Lf:
                java.lang.String r0 = "direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}."
                r1 = 130(0x82, float:1.82E-43)
                r3 = 33
                r4 = 66
                r5 = 17
                r6 = 1
                if (r9 == r5) goto L3d
                if (r9 == r3) goto L36
                if (r9 == r4) goto L2f
                if (r9 != r1) goto L29
                int r7 = r10.bottom
                int r8 = r12.top
                if (r7 > r8) goto L45
                goto L43
            L29:
                java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
                r9.<init>(r0)
                throw r9
            L2f:
                int r7 = r10.right
                int r8 = r12.left
                if (r7 > r8) goto L45
                goto L43
            L36:
                int r7 = r10.top
                int r8 = r12.bottom
                if (r7 < r8) goto L45
                goto L43
            L3d:
                int r7 = r10.left
                int r8 = r12.right
                if (r7 < r8) goto L45
            L43:
                r7 = r6
                goto L46
            L45:
                r7 = r2
            L46:
                if (r7 != 0) goto L49
                return r6
            L49:
                if (r9 == r5) goto L7c
                if (r9 != r4) goto L4e
                goto L7c
            L4e:
                int r11 = V(r9, r10, r11)
                if (r9 == r5) goto L6f
                if (r9 == r3) goto L6a
                if (r9 == r4) goto L65
                if (r9 != r1) goto L5f
                int r9 = r12.bottom
                int r10 = r10.bottom
                goto L73
            L5f:
                java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
                r9.<init>(r0)
                throw r9
            L65:
                int r9 = r12.right
                int r10 = r10.right
                goto L73
            L6a:
                int r9 = r10.top
                int r10 = r12.top
                goto L73
            L6f:
                int r9 = r10.left
                int r10 = r12.left
            L73:
                int r9 = r9 - r10
                int r9 = java.lang.Math.max(r6, r9)
                if (r11 >= r9) goto L7b
                r2 = r6
            L7b:
                return r2
            L7c:
                return r6
            L7d:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: a.b.k.l.i.c(int, android.graphics.Rect, android.graphics.Rect, android.graphics.Rect):boolean");
        }

        public static void c0(ViewParent viewParent, View view, int i, int i2, int[] iArr, int i3) {
            if (viewParent instanceof a.h.k.h) {
                ((a.h.k.h) viewParent).n(view, i, i2, iArr, i3);
                return;
            }
            if (i3 == 0) {
                try {
                    viewParent.onNestedPreScroll(view, i, i2, iArr);
                } catch (AbstractMethodError e2) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedPreScroll", e2);
                }
            }
        }

        public static boolean d(int i, Rect rect, Rect rect2) {
            if (i != 17) {
                if (i != 33) {
                    if (i != 66) {
                        if (i != 130) {
                            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                        }
                    }
                }
                return rect2.right >= rect.left && rect2.left <= rect.right;
            }
            return rect2.bottom >= rect.top && rect2.top <= rect.bottom;
        }

        public static a.h.d.b.a d0(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
            int next;
            do {
                next = xmlPullParser.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next != 2) {
                throw new XmlPullParserException("No start tag found");
            }
            xmlPullParser.require(2, null, "font-family");
            if (!xmlPullParser.getName().equals("font-family")) {
                A0(xmlPullParser);
                return null;
            }
            TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), a.h.b.FontFamily);
            String string = typedArrayObtainAttributes.getString(a.h.b.FontFamily_fontProviderAuthority);
            String string2 = typedArrayObtainAttributes.getString(a.h.b.FontFamily_fontProviderPackage);
            String string3 = typedArrayObtainAttributes.getString(a.h.b.FontFamily_fontProviderQuery);
            int resourceId = typedArrayObtainAttributes.getResourceId(a.h.b.FontFamily_fontProviderCerts, 0);
            int integer = typedArrayObtainAttributes.getInteger(a.h.b.FontFamily_fontProviderFetchStrategy, 1);
            int integer2 = typedArrayObtainAttributes.getInteger(a.h.b.FontFamily_fontProviderFetchTimeout, 500);
            String string4 = typedArrayObtainAttributes.getString(a.h.b.FontFamily_fontProviderSystemFontFamily);
            typedArrayObtainAttributes.recycle();
            if (string != null && string2 != null && string3 != null) {
                while (xmlPullParser.next() != 3) {
                    A0(xmlPullParser);
                }
                return new a.h.d.b.d(new a.h.h.e(string, string2, string3, g0(resources, resourceId)), integer, integer2, string4);
            }
            ArrayList arrayList = new ArrayList();
            while (xmlPullParser.next() != 3) {
                if (xmlPullParser.getEventType() == 2) {
                    if (xmlPullParser.getName().equals("font")) {
                        TypedArray typedArrayObtainAttributes2 = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), a.h.b.FontFamilyFont);
                        int i = typedArrayObtainAttributes2.getInt(typedArrayObtainAttributes2.hasValue(a.h.b.FontFamilyFont_fontWeight) ? a.h.b.FontFamilyFont_fontWeight : a.h.b.FontFamilyFont_android_fontWeight, 400);
                        boolean z = 1 == typedArrayObtainAttributes2.getInt(typedArrayObtainAttributes2.hasValue(a.h.b.FontFamilyFont_fontStyle) ? a.h.b.FontFamilyFont_fontStyle : a.h.b.FontFamilyFont_android_fontStyle, 0);
                        int i2 = typedArrayObtainAttributes2.hasValue(a.h.b.FontFamilyFont_ttcIndex) ? a.h.b.FontFamilyFont_ttcIndex : a.h.b.FontFamilyFont_android_ttcIndex;
                        String string5 = typedArrayObtainAttributes2.getString(typedArrayObtainAttributes2.hasValue(a.h.b.FontFamilyFont_fontVariationSettings) ? a.h.b.FontFamilyFont_fontVariationSettings : a.h.b.FontFamilyFont_android_fontVariationSettings);
                        int i3 = typedArrayObtainAttributes2.getInt(i2, 0);
                        int i4 = typedArrayObtainAttributes2.hasValue(a.h.b.FontFamilyFont_font) ? a.h.b.FontFamilyFont_font : a.h.b.FontFamilyFont_android_font;
                        int resourceId2 = typedArrayObtainAttributes2.getResourceId(i4, 0);
                        String string6 = typedArrayObtainAttributes2.getString(i4);
                        typedArrayObtainAttributes2.recycle();
                        while (xmlPullParser.next() != 3) {
                            A0(xmlPullParser);
                        }
                        arrayList.add(new a.h.d.b.c(string6, i, z, string5, i3, resourceId2));
                    } else {
                        A0(xmlPullParser);
                    }
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            return new a.h.d.b.b((a.h.d.b.c[]) arrayList.toArray(new a.h.d.b.c[arrayList.size()]));
        }

        public static void e(Object obj, StringBuilder sb) {
            String hexString;
            int iLastIndexOf;
            if (obj == null) {
                hexString = "null";
            } else {
                String simpleName = obj.getClass().getSimpleName();
                if (simpleName.length() <= 0 && (iLastIndexOf = (simpleName = obj.getClass().getName()).lastIndexOf(46)) > 0) {
                    simpleName = simpleName.substring(iLastIndexOf + 1);
                }
                sb.append(simpleName);
                sb.append('{');
                hexString = Integer.toHexString(System.identityHashCode(obj));
            }
            sb.append(hexString);
        }

        public static PorterDuff.Mode e0(int i, PorterDuff.Mode mode) {
            if (i == 3) {
                return PorterDuff.Mode.SRC_OVER;
            }
            if (i == 5) {
                return PorterDuff.Mode.SRC_IN;
            }
            if (i == 9) {
                return PorterDuff.Mode.SRC_ATOP;
            }
            switch (i) {
                case 14:
                    return PorterDuff.Mode.MULTIPLY;
                case 15:
                    return PorterDuff.Mode.SCREEN;
                case 16:
                    return PorterDuff.Mode.ADD;
                default:
                    return mode;
            }
        }

        public static void f(WindowInsets windowInsets, View view) {
            View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = (View.OnApplyWindowInsetsListener) view.getTag(a.h.a.tag_window_insets_animation_callback);
            if (onApplyWindowInsetsListener != null) {
                onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsets);
            }
        }

        public static void f0(AnimatorSet animatorSet, List<Animator> list) {
            int size = list.size();
            long jMax = 0;
            for (int i = 0; i < size; i++) {
                Animator animator = list.get(i);
                jMax = Math.max(jMax, animator.getDuration() + animator.getStartDelay());
            }
            ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, 0);
            valueAnimatorOfInt.setDuration(jMax);
            list.add(0, valueAnimatorOfInt);
            animatorSet.playTogether(list);
        }

        public static int g(int i) {
            if (i >= 0) {
                return i;
            }
            throw new IllegalArgumentException();
        }

        public static List<List<byte[]>> g0(Resources resources, int i) {
            if (i == 0) {
                return Collections.emptyList();
            }
            TypedArray typedArrayObtainTypedArray = resources.obtainTypedArray(i);
            try {
                if (typedArrayObtainTypedArray.length() == 0) {
                    return Collections.emptyList();
                }
                ArrayList arrayList = new ArrayList();
                if (typedArrayObtainTypedArray.getType(0) == 1) {
                    for (int i2 = 0; i2 < typedArrayObtainTypedArray.length(); i2++) {
                        int resourceId = typedArrayObtainTypedArray.getResourceId(i2, 0);
                        if (resourceId != 0) {
                            arrayList.add(D0(resources.getStringArray(resourceId)));
                        }
                    }
                } else {
                    arrayList.add(D0(resources.getStringArray(i)));
                }
                return arrayList;
            } finally {
                typedArrayObtainTypedArray.recycle();
            }
        }

        public static <T> T h(T t) {
            if (t != null) {
                return t;
            }
            throw null;
        }

        public static TypedValue h0(Context context, int i) {
            TypedValue typedValue = new TypedValue();
            if (context.getTheme().resolveAttribute(i, typedValue, true)) {
                return typedValue;
            }
            return null;
        }

        public static <T> T i(T t, Object obj) {
            if (t != null) {
                return t;
            }
            throw new NullPointerException(String.valueOf(obj));
        }

        public static boolean i0(Context context, int i, boolean z) {
            TypedValue typedValueH0 = h0(context, i);
            return (typedValueH0 == null || typedValueH0.type != 18) ? z : typedValueH0.data != 0;
        }

        public static int j(Context context, String str) {
            int iMyPid = Process.myPid();
            int iMyUid = Process.myUid();
            String packageName = context.getPackageName();
            if (context.checkPermission(str, iMyPid, iMyUid) == -1) {
                return -1;
            }
            String strPermissionToOp = AppOpsManager.permissionToOp(str);
            if (strPermissionToOp != null) {
                if (packageName == null) {
                    String[] packagesForUid = context.getPackageManager().getPackagesForUid(iMyUid);
                    if (packagesForUid == null || packagesForUid.length <= 0) {
                        return -1;
                    }
                    packageName = packagesForUid[0];
                }
                if (((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(strPermissionToOp, packageName) != 0) {
                    return -2;
                }
            }
            return 0;
        }

        public static int j0(Context context, int i, String str) {
            TypedValue typedValueH0 = h0(context, i);
            if (typedValueH0 != null) {
                return typedValueH0.data;
            }
            throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", str, context.getResources().getResourceName(i)));
        }

        public static int k(int i, int i2, int i3) {
            return i < i2 ? i2 : i > i3 ? i3 : i;
        }

        public static void k0(Drawable drawable, boolean z) {
            drawable.setAutoMirrored(z);
        }

        public static int l(RecyclerView.y yVar, a.p.e.w wVar, View view, View view2, RecyclerView.m mVar, boolean z) {
            if (mVar.z() == 0 || yVar.b() == 0 || view == null || view2 == null) {
                return 0;
            }
            if (!z) {
                return Math.abs(mVar.R(view) - mVar.R(view2)) + 1;
            }
            return Math.min(wVar.l(), wVar.b(view2) - wVar.e(view));
        }

        public static void l0(View view, float f2) {
            Drawable background = view.getBackground();
            if (background instanceof b.d.a.a.e0.g) {
                b.d.a.a.e0.g gVar = (b.d.a.a.e0.g) background;
                g.b bVar = gVar.f1828a;
                if (bVar.o != f2) {
                    bVar.o = f2;
                    gVar.x();
                }
            }
        }

        public static int m(RecyclerView.y yVar, a.p.e.w wVar, View view, View view2, RecyclerView.m mVar, boolean z, boolean z2) {
            if (mVar.z() == 0 || yVar.b() == 0 || view == null || view2 == null) {
                return 0;
            }
            int iMax = z2 ? Math.max(0, (yVar.b() - Math.max(mVar.R(view), mVar.R(view2))) - 1) : Math.max(0, Math.min(mVar.R(view), mVar.R(view2)));
            if (z) {
                return Math.round((iMax * (Math.abs(wVar.b(view2) - wVar.e(view)) / (Math.abs(mVar.R(view) - mVar.R(view2)) + 1))) + (wVar.k() - wVar.e(view)));
            }
            return iMax;
        }

        public static void m0(Drawable drawable, float f2, float f3) {
            drawable.setHotspot(f2, f3);
        }

        public static int n(RecyclerView.y yVar, a.p.e.w wVar, View view, View view2, RecyclerView.m mVar, boolean z) {
            if (mVar.z() == 0 || yVar.b() == 0 || view == null || view2 == null) {
                return 0;
            }
            if (!z) {
                return yVar.b();
            }
            return (int) (((wVar.b(view2) - wVar.e(view)) / (Math.abs(mVar.R(view) - mVar.R(view2)) + 1)) * yVar.b());
        }

        public static void n0(Drawable drawable, int i, int i2, int i3, int i4) {
            drawable.setHotspotBounds(i, i2, i3, i4);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static Animator o(b.d.a.a.u.c cVar, float f2, float f3, float f4) {
            ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(cVar, (Property<b.d.a.a.u.c, V>) c.C0042c.f2060a, (TypeEvaluator) c.b.f2058b, (Object[]) new c.e[]{new c.e(f2, f3, f4)});
            c.e revealInfo = cVar.getRevealInfo();
            if (revealInfo == null) {
                throw new IllegalStateException("Caller must set a non-null RevealInfo before calling this.");
            }
            Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal((View) cVar, (int) f2, (int) f3, revealInfo.f2064c, f4);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(objectAnimatorOfObject, animatorCreateCircularReveal);
            return animatorSet;
        }

        public static void o0(TextView textView, int i) {
            g(i);
            Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
            int i2 = textView.getIncludeFontPadding() ? fontMetricsInt.bottom : fontMetricsInt.descent;
            if (i > Math.abs(i2)) {
                textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i - i2);
            }
        }

        public static b.d.a.a.e0.d p(int i) {
            return i != 0 ? i != 1 ? new b.d.a.a.e0.i() : new b.d.a.a.e0.e() : new b.d.a.a.e0.i();
        }

        public static void p0(TextView textView, int i) {
            g(i);
            if (i != textView.getPaint().getFontMetricsInt(null)) {
                textView.setLineSpacing(i - r0, 1.0f);
            }
        }

        public static float q(float f2, float f3, float f4, float f5) {
            return (float) Math.hypot(f4 - f2, f5 - f3);
        }

        public static void q0(ImageView imageView, NotifyMsgBean notifyMsgBean) {
            imageView.setPadding(r(2.0f), r(2.0f), r(2.0f), r(2.0f));
            if ("com.byd.uereport".equals(notifyMsgBean.getPkgName())) {
                imageView.setPadding(1, 1, 1, 1);
                imageView.setImageResource(com.byd.globalnotification.R.drawable.ic_msg_sys_error);
                return;
            }
            String pkgName = notifyMsgBean.getPkgName();
            Drawable drawableLoadIcon = null;
            if (!b.c.a.h.d.a.a(pkgName)) {
                try {
                    PackageManager packageManager = b.c.a.h.d.b.a().getPackageManager();
                    packageManager.getApplicationIcon(pkgName);
                    PackageInfo packageInfo = packageManager.getPackageInfo(pkgName, 0);
                    if (packageInfo != null) {
                        drawableLoadIcon = packageInfo.applicationInfo.loadIcon(packageManager);
                    }
                } catch (PackageManager.NameNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
            if (drawableLoadIcon != null) {
                try {
                    imageView.setImageDrawable(b.c.a.h.d.b.a().getPackageManager().getApplicationIcon(notifyMsgBean.getPkgName()));
                    return;
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            imageView.setImageDrawable(a.h.d.a.b(imageView.getContext(), R.mipmap.sym_def_app_icon));
        }

        public static int r(float f2) {
            return (int) ((f2 * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
        }

        public static void r0(View view, b.d.a.a.e0.g gVar) {
            b.d.a.a.w.a aVar = gVar.f1828a.f1836b;
            if (aVar != null && aVar.f2129a) {
                float fL = 0.0f;
                for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                    fL += a.h.k.q.l((View) parent);
                }
                g.b bVar = gVar.f1828a;
                if (bVar.n != fL) {
                    bVar.n = fL;
                    gVar.x();
                }
            }
        }

        public static String s(String str, String str2) {
            if (!str2.isEmpty() && str != null && !str.isEmpty()) {
                try {
                    return Base64.encodeToString(v(str2, 1).doFinal(str.getBytes()), 0);
                } catch (Exception e2) {
                    StringBuilder sbD = b.a.a.a.a.d("encrypt error:");
                    sbD.append(e2.getMessage());
                    b.c.a.h.b.b(sbD.toString());
                }
            }
            return str;
        }

        public static void s0(ImageView imageView, Boolean bool) {
            imageView.setImageDrawable(a.h.d.a.b(imageView.getContext(), bool.booleanValue() ? com.byd.globalnotification.R.drawable.icon_public_uparrow_s_24 : com.byd.globalnotification.R.drawable.icon_public_dowmarrow_s_24));
        }

        public static a.f.a.i.l.n t(a.f.a.i.d dVar, int i, ArrayList<a.f.a.i.l.n> arrayList, a.f.a.i.l.n nVar) {
            a.f.a.i.c cVar;
            int i2;
            int i3 = i == 0 ? dVar.t0 : dVar.u0;
            if (i3 != -1 && (nVar == null || i3 != nVar.f602b)) {
                int i4 = 0;
                while (true) {
                    if (i4 >= arrayList.size()) {
                        break;
                    }
                    a.f.a.i.l.n nVar2 = arrayList.get(i4);
                    if (nVar2.f602b == i3) {
                        if (nVar != null) {
                            nVar.d(i, nVar2);
                            arrayList.remove(nVar);
                        }
                        nVar = nVar2;
                    } else {
                        i4++;
                    }
                }
            } else if (i3 != -1) {
                return nVar;
            }
            if (nVar == null) {
                if (dVar instanceof a.f.a.i.h) {
                    a.f.a.i.h hVar = (a.f.a.i.h) dVar;
                    int i5 = 0;
                    while (true) {
                        if (i5 >= hVar.w0) {
                            i2 = -1;
                            break;
                        }
                        a.f.a.i.d dVar2 = hVar.v0[i5];
                        if ((i == 0 && (i2 = dVar2.t0) != -1) || (i == 1 && (i2 = dVar2.u0) != -1)) {
                            break;
                        }
                        i5++;
                    }
                    if (i2 != -1) {
                        int i6 = 0;
                        while (true) {
                            if (i6 >= arrayList.size()) {
                                break;
                            }
                            a.f.a.i.l.n nVar3 = arrayList.get(i6);
                            if (nVar3.f602b == i2) {
                                nVar = nVar3;
                                break;
                            }
                            i6++;
                        }
                    }
                }
                if (nVar == null) {
                    nVar = new a.f.a.i.l.n(i);
                }
                arrayList.add(nVar);
            }
            if (nVar.a(dVar)) {
                if (dVar instanceof a.f.a.i.f) {
                    a.f.a.i.f fVar = (a.f.a.i.f) dVar;
                    fVar.y0.b(fVar.z0 == 0 ? 1 : 0, arrayList, nVar);
                }
                if (i == 0) {
                    dVar.t0 = nVar.f602b;
                    dVar.L.b(i, arrayList, nVar);
                    cVar = dVar.N;
                } else {
                    dVar.u0 = nVar.f602b;
                    dVar.M.b(i, arrayList, nVar);
                    dVar.P.b(i, arrayList, nVar);
                    cVar = dVar.O;
                }
                cVar.b(i, arrayList, nVar);
                dVar.S.b(i, arrayList, nVar);
            }
            return nVar;
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x0044 A[EDGE_INSN: B:31:0x0044->B:33:0x0049 BREAK  A[LOOP:0: B:28:0x0038->B:32:0x0046]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static void t0(android.widget.TextView r7, java.lang.CharSequence r8) {
            /*
                java.lang.CharSequence r0 = r7.getText()
                if (r8 == r0) goto L4f
                if (r8 != 0) goto Lf
                int r1 = r0.length()
                if (r1 != 0) goto Lf
                goto L4f
            Lf:
                boolean r1 = r8 instanceof android.text.Spanned
                if (r1 == 0) goto L1a
                boolean r0 = r8.equals(r0)
                if (r0 == 0) goto L4c
                return
            L1a:
                r1 = 0
                r2 = 1
                if (r8 != 0) goto L20
                r3 = r2
                goto L21
            L20:
                r3 = r1
            L21:
                if (r0 != 0) goto L25
                r4 = r2
                goto L26
            L25:
                r4 = r1
            L26:
                if (r3 == r4) goto L29
                goto L44
            L29:
                if (r8 != 0) goto L2c
                goto L49
            L2c:
                int r3 = r8.length()
                int r4 = r0.length()
                if (r3 == r4) goto L37
                goto L44
            L37:
                r4 = r1
            L38:
                if (r4 >= r3) goto L49
                char r5 = r8.charAt(r4)
                char r6 = r0.charAt(r4)
                if (r5 == r6) goto L46
            L44:
                r1 = r2
                goto L49
            L46:
                int r4 = r4 + 1
                goto L38
            L49:
                if (r1 != 0) goto L4c
                return
            L4c:
                r7.setText(r8)
            L4f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: a.b.k.l.i.t0(android.widget.TextView, java.lang.CharSequence):void");
        }

        public static a.f.a.i.l.n u(ArrayList<a.f.a.i.l.n> arrayList, int i) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                a.f.a.i.l.n nVar = arrayList.get(i2);
                if (i == nVar.f602b) {
                    return nVar;
                }
            }
            return null;
        }

        public static void u0(Drawable drawable, int i) {
            drawable.setTint(i);
        }

        public static Cipher v(String str, Integer num) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(num.intValue(), secretKeySpec);
                return cipher;
            } catch (Exception e2) {
                StringBuilder sbD = b.a.a.a.a.d("getCipher e=");
                sbD.append(e2.getMessage());
                b.c.a.h.b.b(sbD.toString());
                return null;
            }
        }

        public static void v0(Drawable drawable, ColorStateList colorStateList) {
            drawable.setTintList(colorStateList);
        }

        public static int w(Context context, int i, int i2) {
            TypedValue typedValueH0 = h0(context, i);
            return typedValueH0 != null ? typedValueH0.data : i2;
        }

        public static void w0(Drawable drawable, PorterDuff.Mode mode) {
            drawable.setTintMode(mode);
        }

        public static int x(View view, int i) {
            return j0(view.getContext(), i, view.getClass().getCanonicalName());
        }

        public static void x0(View view, boolean z, int i, int i2) {
            if (z && view.getVisibility() != 0) {
                view.setVisibility(0);
                if (i != 0) {
                    view.setAnimation(AnimationUtils.loadAnimation(view.getContext(), i));
                }
            }
            if (z || view.getVisibility() != 0) {
                return;
            }
            view.setVisibility(8);
            if (i2 != 0) {
                view.setAnimation(AnimationUtils.loadAnimation(view.getContext(), i2));
            }
        }

        public static ColorStateList y(Context context, TypedArray typedArray, int i) {
            int resourceId;
            ColorStateList colorStateListA;
            return (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (colorStateListA = a.b.l.a.a.a(context, resourceId)) == null) ? typedArray.getColorStateList(i) : colorStateListA;
        }

        public static void y0(LinearLayout linearLayout, RemoteViews remoteViews, final w wVar, final NotifyMsgBean notifyMsgBean) {
            try {
                linearLayout.removeAllViews();
                View viewApply = remoteViews.apply(linearLayout.getContext().getApplicationContext(), linearLayout);
                if (viewApply.getLayoutParams().height > r(256.0f)) {
                    linearLayout.addView(viewApply, -1, r(256.0f));
                } else {
                    linearLayout.addView(viewApply, -1, -2);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                b.c.a.h.b.b("loadRemoteViewError" + th.getMessage());
                linearLayout.post(new Runnable() { // from class: a.b.k.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        wVar.s(notifyMsgBean);
                    }
                });
            }
        }

        public static ColorStateList z(Context context, x0 x0Var, int i) {
            int resourceId;
            ColorStateList colorStateListA;
            return (!x0Var.f390b.hasValue(i) || (resourceId = x0Var.f390b.getResourceId(i, 0)) == 0 || (colorStateListA = a.b.l.a.a.a(context, resourceId)) == null) ? x0Var.c(i) : colorStateListA;
        }

        public static void z0(LinearLayout linearLayout, RemoteViews remoteViews, final w wVar, final NotifyMsgBean notifyMsgBean) {
            try {
                linearLayout.removeAllViews();
                View viewApply = remoteViews.apply(linearLayout.getContext().getApplicationContext(), linearLayout);
                if (viewApply.getLayoutParams().height > r(128.0f)) {
                    linearLayout.addView(viewApply, -1, r(128.0f));
                } else {
                    linearLayout.addView(viewApply, -1, -2);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                b.c.a.h.b.b("loadRemoteViewError" + th.getMessage());
                linearLayout.post(new Runnable() { // from class: a.b.k.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        wVar.s(notifyMsgBean);
                    }
                });
            }
        }
    }

    public class j extends ContentFrameLayout {
        public j(Context context) {
            super(context, null);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return l.this.x(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (x < -5 || y < -5 || x > getWidth() + 5 || y > getHeight() + 5) {
                    l lVar = l.this;
                    lVar.v(lVar.E(0), true);
                    return true;
                }
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // android.view.View
        public void setBackgroundResource(int i) {
            setBackgroundDrawable(a.b.l.a.a.b(getContext(), i));
        }
    }

    public static final class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f38a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f39b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f40c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f41d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ViewGroup f42e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public View f43f;
        public View g;
        public a.b.o.i.g h;
        public a.b.o.i.e i;
        public Context j;
        public boolean k;
        public boolean l;
        public boolean m;
        public boolean n;
        public boolean o = false;
        public boolean p;
        public Bundle q;

        public k(int i) {
            this.f38a = i;
        }

        public void a(a.b.o.i.g gVar) {
            a.b.o.i.e eVar;
            a.b.o.i.g gVar2 = this.h;
            if (gVar == gVar2) {
                return;
            }
            if (gVar2 != null) {
                gVar2.u(this.i);
            }
            this.h = gVar;
            if (gVar == null || (eVar = this.i) == null) {
                return;
            }
            gVar.b(eVar, gVar.f176a);
        }
    }

    /* JADX INFO: renamed from: a.b.k.l$l, reason: collision with other inner class name */
    public final class C0000l implements m.a {
        public C0000l() {
        }

        @Override // a.b.o.i.m.a
        public void b(a.b.o.i.g gVar, boolean z) {
            a.b.o.i.g gVarK = gVar.k();
            boolean z2 = gVarK != gVar;
            l lVar = l.this;
            if (z2) {
                gVar = gVarK;
            }
            k kVarC = lVar.C(gVar);
            if (kVarC != null) {
                if (!z2) {
                    l.this.v(kVarC, z);
                } else {
                    l.this.t(kVarC.f38a, kVarC, gVarK);
                    l.this.v(kVarC, true);
                }
            }
        }

        @Override // a.b.o.i.m.a
        public boolean c(a.b.o.i.g gVar) {
            Window.Callback callbackF;
            if (gVar != gVar.k()) {
                return true;
            }
            l lVar = l.this;
            if (!lVar.z || (callbackF = lVar.F()) == null || l.this.L) {
                return true;
            }
            callbackF.onMenuOpened(108, gVar);
            return true;
        }
    }

    static {
        if (!a0 || e0) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new a(Thread.getDefaultUncaughtExceptionHandler()));
        e0 = true;
    }

    public l(Context context, Window window, a.b.k.j jVar, Object obj) {
        Integer orDefault;
        a.b.k.i iVar;
        this.M = -100;
        this.f21d = context;
        this.g = jVar;
        this.f20c = obj;
        if (this.M == -100 && (obj instanceof Dialog)) {
            while (context != null) {
                if (!(context instanceof a.b.k.i)) {
                    if (!(context instanceof ContextWrapper)) {
                        break;
                    } else {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                } else {
                    iVar = (a.b.k.i) context;
                    break;
                }
            }
            iVar = null;
            if (iVar != null) {
                this.M = ((l) iVar.q()).M;
            }
        }
        if (this.M == -100 && (orDefault = Z.getOrDefault(this.f20c.getClass().getName(), null)) != null) {
            this.M = orDefault.intValue();
            Z.remove(this.f20c.getClass().getName());
        }
        if (window != null) {
            s(window);
        }
        a.b.p.j.e();
    }

    public final void A() {
        ViewGroup viewGroup;
        if (this.t) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = this.f21d.obtainStyledAttributes(a.b.j.AppCompatTheme);
        if (!typedArrayObtainStyledAttributes.hasValue(a.b.j.AppCompatTheme_windowActionBar)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(a.b.j.AppCompatTheme_windowNoTitle, false)) {
            l(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(a.b.j.AppCompatTheme_windowActionBar, false)) {
            l(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(a.b.j.AppCompatTheme_windowActionBarOverlay, false)) {
            l(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(a.b.j.AppCompatTheme_windowActionModeOverlay, false)) {
            l(10);
        }
        this.C = typedArrayObtainStyledAttributes.getBoolean(a.b.j.AppCompatTheme_android_windowIsFloating, false);
        typedArrayObtainStyledAttributes.recycle();
        B();
        this.f22e.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f21d);
        if (this.D) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(this.B ? a.b.g.abc_screen_simple_overlay_action_mode : a.b.g.abc_screen_simple, (ViewGroup) null);
        } else if (this.C) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(a.b.g.abc_dialog_title_material, (ViewGroup) null);
            this.A = false;
            this.z = false;
        } else if (this.z) {
            TypedValue typedValue = new TypedValue();
            this.f21d.getTheme().resolveAttribute(a.b.a.actionBarTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            viewGroup = (ViewGroup) LayoutInflater.from(i2 != 0 ? new a.b.o.c(this.f21d, i2) : this.f21d).inflate(a.b.g.abc_screen_toolbar, (ViewGroup) null);
            c0 c0Var = (c0) viewGroup.findViewById(a.b.f.decor_content_parent);
            this.k = c0Var;
            c0Var.setWindowCallback(F());
            if (this.A) {
                this.k.h(109);
            }
            if (this.x) {
                this.k.h(2);
            }
            if (this.y) {
                this.k.h(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            StringBuilder sbD = b.a.a.a.a.d("AppCompat does not support the current theme features: { windowActionBar: ");
            sbD.append(this.z);
            sbD.append(", windowActionBarOverlay: ");
            sbD.append(this.A);
            sbD.append(", android:windowIsFloating: ");
            sbD.append(this.C);
            sbD.append(", windowActionModeOverlay: ");
            sbD.append(this.B);
            sbD.append(", windowNoTitle: ");
            sbD.append(this.D);
            sbD.append(" }");
            throw new IllegalArgumentException(sbD.toString());
        }
        a.h.k.q.c0(viewGroup, new m(this));
        if (this.k == null) {
            this.v = (TextView) viewGroup.findViewById(a.b.f.title);
        }
        b1.c(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(a.b.f.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.f22e.findViewById(R.id.content);
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
        this.f22e.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new o(this));
        this.u = viewGroup;
        Object obj = this.f20c;
        CharSequence title = obj instanceof Activity ? ((Activity) obj).getTitle() : this.j;
        if (!TextUtils.isEmpty(title)) {
            c0 c0Var2 = this.k;
            if (c0Var2 != null) {
                c0Var2.setWindowTitle(title);
            } else {
                a.b.k.e eVar = this.h;
                if (eVar != null) {
                    eVar.i(title);
                } else {
                    TextView textView = this.v;
                    if (textView != null) {
                        textView.setText(title);
                    }
                }
            }
        }
        ContentFrameLayout contentFrameLayout2 = (ContentFrameLayout) this.u.findViewById(R.id.content);
        View decorView = this.f22e.getDecorView();
        contentFrameLayout2.g.set(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        if (a.h.k.q.C(contentFrameLayout2)) {
            contentFrameLayout2.requestLayout();
        }
        TypedArray typedArrayObtainStyledAttributes2 = this.f21d.obtainStyledAttributes(a.b.j.AppCompatTheme);
        typedArrayObtainStyledAttributes2.getValue(a.b.j.AppCompatTheme_windowMinWidthMajor, contentFrameLayout2.getMinWidthMajor());
        typedArrayObtainStyledAttributes2.getValue(a.b.j.AppCompatTheme_windowMinWidthMinor, contentFrameLayout2.getMinWidthMinor());
        if (typedArrayObtainStyledAttributes2.hasValue(a.b.j.AppCompatTheme_windowFixedWidthMajor)) {
            typedArrayObtainStyledAttributes2.getValue(a.b.j.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout2.getFixedWidthMajor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(a.b.j.AppCompatTheme_windowFixedWidthMinor)) {
            typedArrayObtainStyledAttributes2.getValue(a.b.j.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout2.getFixedWidthMinor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(a.b.j.AppCompatTheme_windowFixedHeightMajor)) {
            typedArrayObtainStyledAttributes2.getValue(a.b.j.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout2.getFixedHeightMajor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(a.b.j.AppCompatTheme_windowFixedHeightMinor)) {
            typedArrayObtainStyledAttributes2.getValue(a.b.j.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout2.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes2.recycle();
        contentFrameLayout2.requestLayout();
        this.t = true;
        k kVarE = E(0);
        if (this.L || kVarE.h != null) {
            return;
        }
        H(108);
    }

    public final void B() {
        if (this.f22e == null) {
            Object obj = this.f20c;
            if (obj instanceof Activity) {
                s(((Activity) obj).getWindow());
            }
        }
        if (this.f22e == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    public k C(Menu menu) {
        k[] kVarArr = this.F;
        int length = kVarArr != null ? kVarArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            k kVar = kVarArr[i2];
            if (kVar != null && kVar.h == menu) {
                return kVar;
            }
        }
        return null;
    }

    public final g D(Context context) {
        if (this.Q == null) {
            if (u.f67d == null) {
                Context applicationContext = context.getApplicationContext();
                u.f67d = new u(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
            }
            this.Q = new h(u.f67d);
        }
        return this.Q;
    }

    public k E(int i2) {
        k[] kVarArr = this.F;
        if (kVarArr == null || kVarArr.length <= i2) {
            k[] kVarArr2 = new k[i2 + 1];
            if (kVarArr != null) {
                System.arraycopy(kVarArr, 0, kVarArr2, 0, kVarArr.length);
            }
            this.F = kVarArr2;
            kVarArr = kVarArr2;
        }
        k kVar = kVarArr[i2];
        if (kVar != null) {
            return kVar;
        }
        k kVar2 = new k(i2);
        kVarArr[i2] = kVar2;
        return kVar2;
    }

    public final Window.Callback F() {
        return this.f22e.getCallback();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void G() {
        /*
            r3 = this;
            r3.A()
            boolean r0 = r3.z
            if (r0 == 0) goto L37
            a.b.k.e r0 = r3.h
            if (r0 == 0) goto Lc
            goto L37
        Lc:
            java.lang.Object r0 = r3.f20c
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L20
            a.b.k.v r0 = new a.b.k.v
            java.lang.Object r1 = r3.f20c
            android.app.Activity r1 = (android.app.Activity) r1
            boolean r2 = r3.A
            r0.<init>(r1, r2)
        L1d:
            r3.h = r0
            goto L2e
        L20:
            boolean r0 = r0 instanceof android.app.Dialog
            if (r0 == 0) goto L2e
            a.b.k.v r0 = new a.b.k.v
            java.lang.Object r1 = r3.f20c
            android.app.Dialog r1 = (android.app.Dialog) r1
            r0.<init>(r1)
            goto L1d
        L2e:
            a.b.k.e r0 = r3.h
            if (r0 == 0) goto L37
            boolean r3 = r3.V
            r0.g(r3)
        L37:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.k.l.G():void");
    }

    public final void H(int i2) {
        this.T = (1 << i2) | this.T;
        if (this.S) {
            return;
        }
        a.h.k.q.N(this.f22e.getDecorView(), this.U);
        this.S = true;
    }

    public int I(Context context, int i2) {
        g gVarD;
        if (i2 == -100) {
            return -1;
        }
        if (i2 != -1) {
            if (i2 != 0) {
                if (i2 != 1 && i2 != 2) {
                    if (i2 != 3) {
                        throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                    }
                    if (this.R == null) {
                        this.R = new f(context);
                    }
                    gVarD = this.R;
                }
            } else {
                if (((UiModeManager) context.getApplicationContext().getSystemService(UiModeManager.class)).getNightMode() == 0) {
                    return -1;
                }
                gVarD = D(context);
            }
            return gVarD.c();
        }
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:87:0x0160  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void J(a.b.k.l.k r14, android.view.KeyEvent r15) {
        /*
            Method dump skipped, instruction units count: 475
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.k.l.J(a.b.k.l$k, android.view.KeyEvent):void");
    }

    public final boolean K(k kVar, int i2, KeyEvent keyEvent, int i3) {
        a.b.o.i.g gVar;
        boolean zPerformShortcut = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((kVar.k || L(kVar, keyEvent)) && (gVar = kVar.h) != null) {
            zPerformShortcut = gVar.performShortcut(i2, keyEvent, i3);
        }
        if (zPerformShortcut && (i3 & 1) == 0 && this.k == null) {
            v(kVar, true);
        }
        return zPerformShortcut;
    }

    public final boolean L(k kVar, KeyEvent keyEvent) {
        c0 c0Var;
        Resources.Theme themeNewTheme;
        c0 c0Var2;
        c0 c0Var3;
        if (this.L) {
            return false;
        }
        if (kVar.k) {
            return true;
        }
        k kVar2 = this.G;
        if (kVar2 != null && kVar2 != kVar) {
            v(kVar2, false);
        }
        Window.Callback callbackF = F();
        if (callbackF != null) {
            kVar.g = callbackF.onCreatePanelView(kVar.f38a);
        }
        int i2 = kVar.f38a;
        boolean z = i2 == 0 || i2 == 108;
        if (z && (c0Var3 = this.k) != null) {
            c0Var3.d();
        }
        if (kVar.g == null) {
            if (kVar.h == null || kVar.p) {
                if (kVar.h == null) {
                    Context context = this.f21d;
                    int i3 = kVar.f38a;
                    if ((i3 == 0 || i3 == 108) && this.k != null) {
                        TypedValue typedValue = new TypedValue();
                        Resources.Theme theme = context.getTheme();
                        theme.resolveAttribute(a.b.a.actionBarTheme, typedValue, true);
                        if (typedValue.resourceId != 0) {
                            themeNewTheme = context.getResources().newTheme();
                            themeNewTheme.setTo(theme);
                            themeNewTheme.applyStyle(typedValue.resourceId, true);
                            themeNewTheme.resolveAttribute(a.b.a.actionBarWidgetTheme, typedValue, true);
                        } else {
                            theme.resolveAttribute(a.b.a.actionBarWidgetTheme, typedValue, true);
                            themeNewTheme = null;
                        }
                        if (typedValue.resourceId != 0) {
                            if (themeNewTheme == null) {
                                themeNewTheme = context.getResources().newTheme();
                                themeNewTheme.setTo(theme);
                            }
                            themeNewTheme.applyStyle(typedValue.resourceId, true);
                        }
                        if (themeNewTheme != null) {
                            a.b.o.c cVar = new a.b.o.c(context, 0);
                            cVar.getTheme().setTo(themeNewTheme);
                            context = cVar;
                        }
                    }
                    a.b.o.i.g gVar = new a.b.o.i.g(context);
                    gVar.f180e = this;
                    kVar.a(gVar);
                    if (kVar.h == null) {
                        return false;
                    }
                }
                if (z && this.k != null) {
                    if (this.l == null) {
                        this.l = new c();
                    }
                    this.k.b(kVar.h, this.l);
                }
                kVar.h.z();
                if (!callbackF.onCreatePanelMenu(kVar.f38a, kVar.h)) {
                    kVar.a(null);
                    if (z && (c0Var = this.k) != null) {
                        c0Var.b(null, this.l);
                    }
                    return false;
                }
                kVar.p = false;
            }
            kVar.h.z();
            Bundle bundle = kVar.q;
            if (bundle != null) {
                kVar.h.v(bundle);
                kVar.q = null;
            }
            if (!callbackF.onPreparePanel(0, kVar.g, kVar.h)) {
                if (z && (c0Var2 = this.k) != null) {
                    c0Var2.b(null, this.l);
                }
                kVar.h.y();
                return false;
            }
            boolean z2 = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            kVar.n = z2;
            kVar.h.setQwertyMode(z2);
            kVar.h.y();
        }
        kVar.k = true;
        kVar.l = false;
        this.G = kVar;
        return true;
    }

    public final boolean M() {
        ViewGroup viewGroup;
        return this.t && (viewGroup = this.u) != null && a.h.k.q.C(viewGroup);
    }

    public final void N() {
        if (this.t) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public final int O(a.h.k.w wVar, Rect rect) {
        boolean z;
        boolean z2;
        int iD = wVar != null ? wVar.d() : rect != null ? rect.top : 0;
        ActionBarContextView actionBarContextView = this.o;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.o.getLayoutParams();
            if (this.o.isShown()) {
                if (this.W == null) {
                    this.W = new Rect();
                    this.X = new Rect();
                }
                Rect rect2 = this.W;
                Rect rect3 = this.X;
                if (wVar == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(wVar.b(), wVar.d(), wVar.c(), wVar.a());
                }
                b1.a(this.u, rect2, rect3);
                int i2 = rect2.top;
                int i3 = rect2.left;
                int i4 = rect2.right;
                a.h.k.w wVarU = a.h.k.q.u(this.u);
                int iB = wVarU == null ? 0 : wVarU.b();
                int iC = wVarU == null ? 0 : wVarU.c();
                if (marginLayoutParams.topMargin == i2 && marginLayoutParams.leftMargin == i3 && marginLayoutParams.rightMargin == i4) {
                    z2 = false;
                } else {
                    marginLayoutParams.topMargin = i2;
                    marginLayoutParams.leftMargin = i3;
                    marginLayoutParams.rightMargin = i4;
                    z2 = true;
                }
                if (i2 <= 0 || this.w != null) {
                    View view = this.w;
                    if (view != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                        if (marginLayoutParams2.height != marginLayoutParams.topMargin || marginLayoutParams2.leftMargin != iB || marginLayoutParams2.rightMargin != iC) {
                            marginLayoutParams2.height = marginLayoutParams.topMargin;
                            marginLayoutParams2.leftMargin = iB;
                            marginLayoutParams2.rightMargin = iC;
                            this.w.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view2 = new View(this.f21d);
                    this.w = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = iB;
                    layoutParams.rightMargin = iC;
                    this.u.addView(this.w, -1, layoutParams);
                }
                z = this.w != null;
                if (z && this.w.getVisibility() != 0) {
                    View view3 = this.w;
                    view3.setBackgroundColor(a.h.d.a.a(this.f21d, (a.h.k.q.w(view3) & 8192) != 0 ? a.b.c.abc_decor_view_status_guard_light : a.b.c.abc_decor_view_status_guard));
                }
                if (!this.B && z) {
                    iD = 0;
                }
                z = z2;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z = false;
            } else {
                z = false;
                z = false;
            }
            if (z) {
                this.o.setLayoutParams(marginLayoutParams);
            }
        }
        View view4 = this.w;
        if (view4 != null) {
            view4.setVisibility(z ? 0 : 8);
        }
        return iD;
    }

    @Override // a.b.o.i.g.a
    public void a(a.b.o.i.g gVar) {
        c0 c0Var = this.k;
        if (c0Var == null || !c0Var.f() || (ViewConfiguration.get(this.f21d).hasPermanentMenuKey() && !this.k.c())) {
            k kVarE = E(0);
            kVarE.o = true;
            v(kVarE, false);
            J(kVarE, null);
            return;
        }
        Window.Callback callbackF = F();
        if (this.k.e()) {
            this.k.g();
            if (this.L) {
                return;
            }
            callbackF.onPanelClosed(108, E(0).h);
            return;
        }
        if (callbackF == null || this.L) {
            return;
        }
        if (this.S && (1 & this.T) != 0) {
            this.f22e.getDecorView().removeCallbacks(this.U);
            this.U.run();
        }
        k kVarE2 = E(0);
        a.b.o.i.g gVar2 = kVarE2.h;
        if (gVar2 == null || kVarE2.p || !callbackF.onPreparePanel(0, kVarE2.g, gVar2)) {
            return;
        }
        callbackF.onMenuOpened(108, kVarE2.h);
        this.k.a();
    }

    @Override // a.b.o.i.g.a
    public boolean b(a.b.o.i.g gVar, MenuItem menuItem) {
        k kVarC;
        Window.Callback callbackF = F();
        if (callbackF == null || this.L || (kVarC = C(gVar.k())) == null) {
            return false;
        }
        return callbackF.onMenuItemSelected(kVarC.f38a, menuItem);
    }

    @Override // a.b.k.k
    public void c(View view, ViewGroup.LayoutParams layoutParams) {
        A();
        ((ViewGroup) this.u.findViewById(R.id.content)).addView(view, layoutParams);
        this.f23f.f131a.onContentChanged();
    }

    @Override // a.b.k.k
    public View f(View view, String str, Context context, AttributeSet attributeSet) {
        Context cVar;
        View viewE;
        s sVar;
        if (this.Y == null) {
            String string = this.f21d.obtainStyledAttributes(a.b.j.AppCompatTheme).getString(a.b.j.AppCompatTheme_viewInflaterClass);
            if (string == null) {
                sVar = new s();
            } else {
                try {
                    this.Y = (s) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    sVar = new s();
                    this.Y = sVar;
                }
            }
            this.Y = sVar;
        }
        s sVar2 = this.Y;
        View view2 = null;
        if (sVar2 == null) {
            throw null;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.b.j.View, 0, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(a.b.j.View_theme, 0);
        if (resourceId != 0) {
            Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        typedArrayObtainStyledAttributes.recycle();
        cVar = (resourceId == 0 || ((context instanceof a.b.o.c) && ((a.b.o.c) context).f92a == resourceId)) ? context : new a.b.o.c(context, resourceId);
        switch (str) {
            case "TextView":
                viewE = sVar2.e(cVar, attributeSet);
                sVar2.h(viewE, str);
                break;
            case "ImageView":
                viewE = new a.b.p.n(cVar, attributeSet);
                sVar2.h(viewE, str);
                break;
            case "Button":
                viewE = sVar2.b(cVar, attributeSet);
                sVar2.h(viewE, str);
                break;
            case "EditText":
                viewE = new a.b.p.k(cVar, attributeSet);
                sVar2.h(viewE, str);
                break;
            case "Spinner":
                viewE = new a.b.p.w(cVar, attributeSet, a.b.a.spinnerStyle);
                sVar2.h(viewE, str);
                break;
            case "ImageButton":
                viewE = new a.b.p.l(cVar, attributeSet);
                sVar2.h(viewE, str);
                break;
            case "CheckBox":
                viewE = sVar2.c(cVar, attributeSet);
                sVar2.h(viewE, str);
                break;
            case "RadioButton":
                viewE = sVar2.d(cVar, attributeSet);
                sVar2.h(viewE, str);
                break;
            case "CheckedTextView":
                viewE = new a.b.p.h(cVar, attributeSet);
                sVar2.h(viewE, str);
                break;
            case "AutoCompleteTextView":
                viewE = sVar2.a(cVar, attributeSet);
                sVar2.h(viewE, str);
                break;
            case "MultiAutoCompleteTextView":
                viewE = new a.b.p.o(cVar, attributeSet);
                sVar2.h(viewE, str);
                break;
            case "RatingBar":
                viewE = new a.b.p.s(cVar, attributeSet);
                sVar2.h(viewE, str);
                break;
            case "SeekBar":
                viewE = new a.b.p.t(cVar, attributeSet);
                sVar2.h(viewE, str);
                break;
            case "ToggleButton":
                viewE = new b0(cVar, attributeSet);
                sVar2.h(viewE, str);
                break;
            default:
                viewE = sVar2.f();
                break;
        }
        if (viewE != null || context == cVar) {
            view2 = viewE;
        } else {
            if (str.equals("view")) {
                str = attributeSet.getAttributeValue(null, "class");
            }
            try {
                sVar2.f58a[0] = cVar;
                sVar2.f58a[1] = attributeSet;
                if (-1 == str.indexOf(46)) {
                    for (int i2 = 0; i2 < s.f56d.length; i2++) {
                        viewE = sVar2.g(cVar, str, s.f56d[i2]);
                        if (viewE != null) {
                            view2 = viewE;
                        }
                    }
                } else {
                    View viewG = sVar2.g(cVar, str, null);
                    Object[] objArr = sVar2.f58a;
                    objArr[0] = null;
                    objArr[1] = null;
                    view2 = viewG;
                }
            } catch (Exception unused) {
            } finally {
                Object[] objArr2 = sVar2.f58a;
                objArr2[0] = null;
                objArr2[1] = null;
            }
        }
        if (view2 != null) {
            Context context2 = view2.getContext();
            if ((context2 instanceof ContextWrapper) && a.h.k.q.y(view2)) {
                TypedArray typedArrayObtainStyledAttributes2 = context2.obtainStyledAttributes(attributeSet, s.f55c);
                String string2 = typedArrayObtainStyledAttributes2.getString(0);
                if (string2 != null) {
                    view2.setOnClickListener(new s.a(view2, string2));
                }
                typedArrayObtainStyledAttributes2.recycle();
            }
        }
        return view2;
    }

    @Override // a.b.k.k
    public void g() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f21d);
        if (layoutInflaterFrom.getFactory() == null) {
            layoutInflaterFrom.setFactory2(this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof l) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    @Override // a.b.k.k
    public void h() {
        G();
        a.b.k.e eVar = this.h;
        H(0);
    }

    @Override // a.b.k.k
    public void i(Bundle bundle) {
        this.I = true;
        r(false);
        B();
        Object obj = this.f20c;
        if (obj instanceof Activity) {
            String strE = null;
            try {
                Activity activity = (Activity) obj;
                try {
                    strE = i.E(activity, activity.getComponentName());
                } catch (PackageManager.NameNotFoundException e2) {
                    throw new IllegalArgumentException(e2);
                }
            } catch (IllegalArgumentException unused) {
            }
            if (strE != null) {
                a.b.k.e eVar = this.h;
                if (eVar == null) {
                    this.V = true;
                } else {
                    eVar.g(true);
                }
            }
            synchronized (a.b.k.k.f19b) {
                a.b.k.k.k(this);
                a.b.k.k.f18a.add(new WeakReference<>(this));
            }
        }
        this.J = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0050  */
    @Override // a.b.k.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void j() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f20c
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L11
            java.lang.Object r0 = a.b.k.k.f19b
            monitor-enter(r0)
            a.b.k.k.k(r3)     // Catch: java.lang.Throwable -> Le
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            goto L11
        Le:
            r3 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            throw r3
        L11:
            boolean r0 = r3.S
            if (r0 == 0) goto L20
            android.view.Window r0 = r3.f22e
            android.view.View r0 = r0.getDecorView()
            java.lang.Runnable r1 = r3.U
            r0.removeCallbacks(r1)
        L20:
            r0 = 0
            r3.K = r0
            r0 = 1
            r3.L = r0
            int r0 = r3.M
            r1 = -100
            if (r0 == r1) goto L50
            java.lang.Object r0 = r3.f20c
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L50
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L50
            a.e.h<java.lang.String, java.lang.Integer> r0 = a.b.k.l.Z
            java.lang.Object r1 = r3.f20c
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.M
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L5f
        L50:
            a.e.h<java.lang.String, java.lang.Integer> r0 = a.b.k.l.Z
            java.lang.Object r1 = r3.f20c
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L5f:
            a.b.k.e r0 = r3.h
            if (r0 == 0) goto L68
            if (r0 == 0) goto L66
            goto L68
        L66:
            r3 = 0
            throw r3
        L68:
            a.b.k.l$g r0 = r3.Q
            if (r0 == 0) goto L6f
            r0.a()
        L6f:
            a.b.k.l$g r3 = r3.R
            if (r3 == 0) goto L76
            r3.a()
        L76:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.k.l.j():void");
    }

    @Override // a.b.k.k
    public boolean l(int i2) {
        if (i2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            i2 = 108;
        } else if (i2 == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            i2 = 109;
        }
        if (this.D && i2 == 108) {
            return false;
        }
        if (this.z && i2 == 1) {
            this.z = false;
        }
        if (i2 == 1) {
            N();
            this.D = true;
            return true;
        }
        if (i2 == 2) {
            N();
            this.x = true;
            return true;
        }
        if (i2 == 5) {
            N();
            this.y = true;
            return true;
        }
        if (i2 == 10) {
            N();
            this.B = true;
            return true;
        }
        if (i2 == 108) {
            N();
            this.z = true;
            return true;
        }
        if (i2 != 109) {
            return this.f22e.requestFeature(i2);
        }
        N();
        this.A = true;
        return true;
    }

    @Override // a.b.k.k
    public void m(int i2) {
        A();
        ViewGroup viewGroup = (ViewGroup) this.u.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f21d).inflate(i2, viewGroup);
        this.f23f.f131a.onContentChanged();
    }

    @Override // a.b.k.k
    public void n(View view) {
        A();
        ViewGroup viewGroup = (ViewGroup) this.u.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.f23f.f131a.onContentChanged();
    }

    @Override // a.b.k.k
    public void o(View view, ViewGroup.LayoutParams layoutParams) {
        A();
        ViewGroup viewGroup = (ViewGroup) this.u.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.f23f.f131a.onContentChanged();
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return f(view, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return f(null, str, context, attributeSet);
    }

    @Override // a.b.k.k
    public final void p(CharSequence charSequence) {
        this.j = charSequence;
        c0 c0Var = this.k;
        if (c0Var != null) {
            c0Var.setWindowTitle(charSequence);
            return;
        }
        a.b.k.e eVar = this.h;
        if (eVar != null) {
            eVar.i(charSequence);
            return;
        }
        TextView textView = this.v;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public boolean q() {
        return r(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean r(boolean r11) {
        /*
            Method dump skipped, instruction units count: 316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.k.l.r(boolean):boolean");
    }

    public final void s(Window window) {
        if (this.f22e != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof e) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        e eVar = new e(callback);
        this.f23f = eVar;
        window.setCallback(eVar);
        x0 x0VarP = x0.p(this.f21d, null, b0);
        Drawable drawableH = x0VarP.h(0);
        if (drawableH != null) {
            window.setBackgroundDrawable(drawableH);
        }
        x0VarP.f390b.recycle();
        this.f22e = window;
    }

    public void t(int i2, k kVar, Menu menu) {
        if (menu == null && kVar != null) {
            menu = kVar.h;
        }
        if ((kVar == null || kVar.m) && !this.L) {
            this.f23f.f131a.onPanelClosed(i2, menu);
        }
    }

    public void u(a.b.o.i.g gVar) {
        if (this.E) {
            return;
        }
        this.E = true;
        this.k.j();
        Window.Callback callbackF = F();
        if (callbackF != null && !this.L) {
            callbackF.onPanelClosed(108, gVar);
        }
        this.E = false;
    }

    public void v(k kVar, boolean z) {
        ViewGroup viewGroup;
        c0 c0Var;
        if (z && kVar.f38a == 0 && (c0Var = this.k) != null && c0Var.e()) {
            u(kVar.h);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f21d.getSystemService("window");
        if (windowManager != null && kVar.m && (viewGroup = kVar.f42e) != null) {
            windowManager.removeView(viewGroup);
            if (z) {
                t(kVar.f38a, kVar, null);
            }
        }
        kVar.k = false;
        kVar.l = false;
        kVar.m = false;
        kVar.f43f = null;
        kVar.o = true;
        if (this.G == kVar) {
            this.G = null;
        }
    }

    public final Configuration w(Context context, int i2, Configuration configuration) {
        int i3 = i2 != 1 ? i2 != 2 ? context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i3 | (configuration2.uiMode & (-49));
        return configuration2;
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:97:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean x(android.view.KeyEvent r7) {
        /*
            Method dump skipped, instruction units count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.k.l.x(android.view.KeyEvent):boolean");
    }

    public void y(int i2) {
        k kVarE = E(i2);
        if (kVarE.h != null) {
            Bundle bundle = new Bundle();
            kVarE.h.w(bundle);
            if (bundle.size() > 0) {
                kVarE.q = bundle;
            }
            kVarE.h.z();
            kVarE.h.clear();
        }
        kVarE.p = true;
        kVarE.o = true;
        if ((i2 == 108 || i2 == 0) && this.k != null) {
            k kVarE2 = E(0);
            kVarE2.k = false;
            L(kVarE2, null);
        }
    }

    public void z() {
        a.h.k.s sVar = this.r;
        if (sVar != null) {
            sVar.b();
        }
    }
}
