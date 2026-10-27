package p;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.ContentFrameLayout;
import com.spotify.webapi.service.models.Search;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class s8 extends e8 implements qi1, LayoutInflater.Factory2 {
    public static final kd2 p0 = new kd2(0);
    public static final int[] q0 = {R.attr.windowBackground};
    public static final boolean r0 = !"robolectric".equals(Build.FINGERPRINT);
    public static final boolean s0 = true;
    public g8 A;
    public h8 B;
    public l4 C;
    public ActionBarContextView D;
    public PopupWindow E;
    public f8 F;
    public boolean H;
    public ViewGroup I;
    public TextView J;
    public View K;
    public boolean L;
    public boolean M;
    public boolean N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public boolean S;
    public r8[] T;
    public r8 U;
    public boolean V;
    public boolean W;
    public boolean X;
    public boolean Y;
    public Configuration Z;
    public final int a0;
    public int b0;
    public int c0;
    public boolean d0;
    public n8 e0;
    public n8 f0;
    public boolean g0;
    public int h0;
    public boolean j0;
    public Rect k0;
    public Rect l0;
    public pa m0;
    public OnBackInvokedDispatcher n0;
    public OnBackInvokedCallback o0;
    public final Object r;
    public final Context s;
    public Window t;
    public m8 u;
    public final w7 v;
    public ow2 w;
    public lh2 x;
    public CharSequence y;
    public i60 z;
    public tu2 G = null;
    public final f8 i0 = new f8(this, 0);

    public s8(Context context, Window window, w7 w7Var, Object obj) {
        t7 t7Var = null;
        this.a0 = -100;
        this.s = context;
        this.v = w7Var;
        this.r = obj;
        if (obj instanceof Dialog) {
            while (true) {
                if (context != null) {
                    if (!(context instanceof t7)) {
                        if (!(context instanceof ContextWrapper)) {
                            break;
                        } else {
                            context = ((ContextWrapper) context).getBaseContext();
                        }
                    } else {
                        t7Var = (t7) context;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (t7Var != null) {
                this.a0 = ((s8) t7Var.u()).a0;
            }
        }
        if (this.a0 == -100) {
            kd2 kd2Var = p0;
            Integer num = (Integer) kd2Var.get(this.r.getClass().getName());
            if (num != null) {
                this.a0 = num.intValue();
                kd2Var.remove(this.r.getClass().getName());
            }
        }
        if (window != null) {
            m(window);
        }
        u8.c();
    }

    public static ac1 n(Context context) {
        ac1 ac1Var;
        ac1 ac1Var2;
        if (Build.VERSION.SDK_INT >= 33 || (ac1Var = e8.k) == null) {
            return null;
        }
        ac1 ac1VarB = k8.b(context.getApplicationContext().getResources().getConfiguration());
        bc1 bc1Var = ac1Var.a;
        if (((cc1) bc1Var).a.isEmpty()) {
            ac1Var2 = ac1.b;
        } else {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int i = 0;
            while (i < ((cc1) ac1VarB.a).a.size() + ((cc1) bc1Var).a.size()) {
                Locale localeB = i < ((cc1) bc1Var).a.size() ? ac1Var.b(i) : ac1VarB.b(i - ((cc1) bc1Var).a.size());
                if (localeB != null) {
                    linkedHashSet.add(localeB);
                }
                i++;
            }
            ac1Var2 = new ac1(new cc1(zb1.a((Locale[]) linkedHashSet.toArray(new Locale[linkedHashSet.size()]))));
        }
        return ((cc1) ac1Var2.a).a.isEmpty() ? ac1VarB : ac1Var2;
    }

    public static Configuration r(Context context, int i, ac1 ac1Var, Configuration configuration, boolean z) {
        int i2 = i != 1 ? i != 2 ? z ? 0 : context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i2 | (configuration2.uiMode & (-49));
        if (ac1Var != null) {
            k8.d(configuration2, ac1Var);
        }
        return configuration2;
    }

    public final int A(Context context, int i) {
        if (i == -100) {
            return -1;
        }
        if (i != -1) {
            if (i == 0) {
                if (((UiModeManager) context.getApplicationContext().getSystemService("uimode")).getNightMode() == 0) {
                    return -1;
                }
                return w(context).d();
            }
            if (i != 1 && i != 2) {
                if (i != 3) {
                    throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                }
                if (this.f0 == null) {
                    this.f0 = new n8(this, context);
                }
                return this.f0.d();
            }
        }
        return i;
    }

    public final boolean B() {
        j60 j60Var;
        dm2 dm2Var;
        boolean z = this.V;
        this.V = false;
        r8 r8VarX = x(0);
        if (r8VarX.m) {
            if (!z) {
                q(r8VarX, true);
            }
            return true;
        }
        l4 l4Var = this.C;
        if (l4Var != null) {
            l4Var.a();
            return true;
        }
        y();
        ow2 ow2Var = this.w;
        if (ow2Var == null || (j60Var = ow2Var.m) == null || (dm2Var = ((hm2) j60Var).a.T) == null || dm2Var.e == null) {
            return false;
        }
        dm2 dm2Var2 = ((hm2) j60Var).a.T;
        xi1 xi1Var = dm2Var2 == null ? null : dm2Var2.e;
        if (xi1Var != null) {
            xi1Var.collapseActionView();
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:87:0x0172, code lost:
    
        if (r3.n.getCount() > 0) goto L88;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:104:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void C(p.r8 r18, android.view.KeyEvent r19) {
        /*
            Method dump skipped, instruction units count: 471
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.s8.C(p.r8, android.view.KeyEvent):void");
    }

    public final boolean D(r8 r8Var, int i, KeyEvent keyEvent) {
        si1 si1Var;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((r8Var.k || E(r8Var, keyEvent)) && (si1Var = r8Var.h) != null) {
            return si1Var.performShortcut(i, keyEvent, 1);
        }
        return false;
    }

    public final boolean E(r8 r8Var, KeyEvent keyEvent) {
        i60 i60Var;
        i60 i60Var2;
        Resources.Theme themeNewTheme;
        i60 i60Var3;
        i60 i60Var4;
        if (this.Y) {
            return false;
        }
        if (r8Var.k) {
            return true;
        }
        r8 r8Var2 = this.U;
        if (r8Var2 != null && r8Var2 != r8Var) {
            q(r8Var2, false);
        }
        Window.Callback callback = this.t.getCallback();
        int i = r8Var.a;
        if (callback != null) {
            r8Var.g = callback.onCreatePanelView(i);
        }
        boolean z = i == 0 || i == 108;
        if (z && (i60Var4 = this.z) != null) {
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) i60Var4;
            actionBarOverlayLayout.k();
            ((hm2) actionBarOverlayLayout.m).l = true;
        }
        if (r8Var.g == null) {
            si1 si1Var = r8Var.h;
            if (si1Var == null || r8Var.o) {
                if (si1Var == null) {
                    Context context = this.s;
                    if ((i == 0 || i == 108) && this.z != null) {
                        TypedValue typedValue = new TypedValue();
                        Resources.Theme theme = context.getTheme();
                        theme.resolveAttribute(com.spotify.music.R.attr.actionBarTheme, typedValue, true);
                        if (typedValue.resourceId != 0) {
                            themeNewTheme = context.getResources().newTheme();
                            themeNewTheme.setTo(theme);
                            themeNewTheme.applyStyle(typedValue.resourceId, true);
                            themeNewTheme.resolveAttribute(com.spotify.music.R.attr.actionBarWidgetTheme, typedValue, true);
                        } else {
                            theme.resolveAttribute(com.spotify.music.R.attr.actionBarWidgetTheme, typedValue, true);
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
                            a00 a00Var = new a00(context, 0);
                            a00Var.getTheme().setTo(themeNewTheme);
                            context = a00Var;
                        }
                    }
                    si1 si1Var2 = new si1(context);
                    si1Var2.e = this;
                    si1 si1Var3 = r8Var.h;
                    if (si1Var2 != si1Var3) {
                        if (si1Var3 != null) {
                            si1Var3.r(r8Var.i);
                        }
                        r8Var.h = si1Var2;
                        ab1 ab1Var = r8Var.i;
                        if (ab1Var != null) {
                            si1Var2.b(ab1Var, si1Var2.a);
                        }
                    }
                    if (r8Var.h == null) {
                        return false;
                    }
                }
                if (z && (i60Var2 = this.z) != null) {
                    if (this.A == null) {
                        this.A = new g8(this);
                    }
                    ((ActionBarOverlayLayout) i60Var2).l(r8Var.h, this.A);
                }
                r8Var.h.w();
                if (!callback.onCreatePanelMenu(i, r8Var.h)) {
                    si1 si1Var4 = r8Var.h;
                    if (si1Var4 != null) {
                        if (si1Var4 != null) {
                            si1Var4.r(r8Var.i);
                        }
                        r8Var.h = null;
                    }
                    if (z && (i60Var = this.z) != null) {
                        ((ActionBarOverlayLayout) i60Var).l(null, this.A);
                    }
                    return false;
                }
                r8Var.o = false;
            }
            r8Var.h.w();
            Bundle bundle = r8Var.f177p;
            if (bundle != null) {
                r8Var.h.s(bundle);
                r8Var.f177p = null;
            }
            if (!callback.onPreparePanel(0, r8Var.g, r8Var.h)) {
                if (z && (i60Var3 = this.z) != null) {
                    ((ActionBarOverlayLayout) i60Var3).l(null, this.A);
                }
                r8Var.h.v();
                return false;
            }
            r8Var.h.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
            r8Var.h.v();
        }
        r8Var.k = true;
        r8Var.l = false;
        this.U = r8Var;
        return true;
    }

    public final void F() {
        if (this.H) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public final void G() {
        OnBackInvokedCallback onBackInvokedCallback;
        if (Build.VERSION.SDK_INT >= 33) {
            boolean z = false;
            if (this.n0 != null && (x(0).m || this.C != null)) {
                z = true;
            }
            if (z && this.o0 == null) {
                this.o0 = l8.b(this.n0, this);
            } else {
                if (z || (onBackInvokedCallback = this.o0) == null) {
                    return;
                }
                l8.c(this.n0, onBackInvokedCallback);
            }
        }
    }

    public final int H(fx2 fx2Var, Rect rect) {
        boolean z;
        boolean z2;
        int iA;
        int iD = fx2Var != null ? fx2Var.d() : rect != null ? rect.top : 0;
        ActionBarContextView actionBarContextView = this.D;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.D.getLayoutParams();
            if (this.D.isShown()) {
                if (this.k0 == null) {
                    this.k0 = new Rect();
                    this.l0 = new Rect();
                }
                Rect rect2 = this.k0;
                Rect rect3 = this.l0;
                if (fx2Var == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(fx2Var.b(), fx2Var.d(), fx2Var.c(), fx2Var.a());
                }
                ViewGroup viewGroup = this.I;
                Method method = ev2.a;
                if (method != null) {
                    try {
                        method.invoke(viewGroup, rect2, rect3);
                    } catch (Exception unused) {
                    }
                }
                int i = rect2.top;
                int i2 = rect2.left;
                int i3 = rect2.right;
                ViewGroup viewGroup2 = this.I;
                WeakHashMap weakHashMap = zt2.a;
                fx2 fx2VarA = pt2.a(viewGroup2);
                int iB = fx2VarA == null ? 0 : fx2VarA.b();
                int iC = fx2VarA == null ? 0 : fx2VarA.c();
                if (marginLayoutParams.topMargin == i && marginLayoutParams.leftMargin == i2 && marginLayoutParams.rightMargin == i3) {
                    z2 = false;
                } else {
                    marginLayoutParams.topMargin = i;
                    marginLayoutParams.leftMargin = i2;
                    marginLayoutParams.rightMargin = i3;
                    z2 = true;
                }
                Context context = this.s;
                if (i <= 0 || this.K != null) {
                    View view = this.K;
                    if (view != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                        int i4 = marginLayoutParams2.height;
                        int i5 = marginLayoutParams.topMargin;
                        if (i4 != i5 || marginLayoutParams2.leftMargin != iB || marginLayoutParams2.rightMargin != iC) {
                            marginLayoutParams2.height = i5;
                            marginLayoutParams2.leftMargin = iB;
                            marginLayoutParams2.rightMargin = iC;
                            this.K.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view2 = new View(context);
                    this.K = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = iB;
                    layoutParams.rightMargin = iC;
                    this.I.addView(this.K, -1, layoutParams);
                }
                View view3 = this.K;
                z = view3 != null;
                if (z && view3.getVisibility() != 0) {
                    View view4 = this.K;
                    if ((it2.g(view4) & 8192) != 0) {
                        Object obj = sz.a;
                        iA = pz.a(context, com.spotify.music.R.color.abc_decor_view_status_guard_light);
                    } else {
                        Object obj2 = sz.a;
                        iA = pz.a(context, com.spotify.music.R.color.abc_decor_view_status_guard);
                    }
                    view4.setBackgroundColor(iA);
                }
                if (!this.P && z) {
                    iD = 0;
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
                this.D.setLayoutParams(marginLayoutParams);
            }
        }
        View view5 = this.K;
        if (view5 != null) {
            view5.setVisibility(z ? 0 : 8);
        }
        return iD;
    }

    @Override // p.e8
    public final void b() {
        String strP;
        this.W = true;
        l(false, true);
        v();
        Object obj = this.r;
        if (obj instanceof Activity) {
            try {
                Activity activity = (Activity) obj;
                try {
                    strP = ln1.p(activity, activity.getComponentName());
                } catch (PackageManager.NameNotFoundException e) {
                    throw new IllegalArgumentException(e);
                }
            } catch (IllegalArgumentException unused) {
                strP = null;
            }
            if (strP != null) {
                ow2 ow2Var = this.w;
                if (ow2Var == null) {
                    this.j0 = true;
                } else {
                    ow2Var.K0(true);
                }
            }
            synchronized (e8.f119p) {
                e8.d(this);
                e8.o.add(new WeakReference(this));
            }
        }
        this.Z = new Configuration(this.s.getResources().getConfiguration());
        this.X = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004d  */
    @Override // p.e8
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.r
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L11
            java.lang.Object r0 = p.e8.f119p
            monitor-enter(r0)
            p.e8.d(r3)     // Catch: java.lang.Throwable -> Le
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            goto L11
        Le:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            throw r1
        L11:
            boolean r0 = r3.g0
            if (r0 == 0) goto L20
            android.view.Window r0 = r3.t
            android.view.View r0 = r0.getDecorView()
            p.f8 r1 = r3.i0
            r0.removeCallbacks(r1)
        L20:
            r0 = 1
            r3.Y = r0
            int r0 = r3.a0
            r1 = -100
            if (r0 == r1) goto L4d
            java.lang.Object r0 = r3.r
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L4d
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L4d
            p.kd2 r0 = p.s8.p0
            java.lang.Object r1 = r3.r
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.a0
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L5c
        L4d:
            p.kd2 r0 = p.s8.p0
            java.lang.Object r1 = r3.r
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L5c:
            p.n8 r0 = r3.e0
            if (r0 == 0) goto L63
            r0.a()
        L63:
            p.n8 r0 = r3.f0
            if (r0 == 0) goto L6a
            r0.a()
        L6a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p.s8.c():void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0048, code lost:
    
        if (r6.e() != false) goto L20;
     */
    @Override // p.qi1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(p.si1 r6) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.s8.e(p.si1):void");
    }

    @Override // p.e8
    public final boolean f(int i) {
        if (i == 8) {
            i = 108;
        } else if (i == 9) {
            i = 109;
        }
        if (this.R && i == 108) {
            return false;
        }
        if (this.N && i == 1) {
            this.N = false;
        }
        if (i == 1) {
            F();
            this.R = true;
            return true;
        }
        if (i == 2) {
            F();
            this.L = true;
            return true;
        }
        if (i == 5) {
            F();
            this.M = true;
            return true;
        }
        if (i == 10) {
            F();
            this.P = true;
            return true;
        }
        if (i == 108) {
            F();
            this.N = true;
            return true;
        }
        if (i != 109) {
            return this.t.requestFeature(i);
        }
        F();
        this.O = true;
        return true;
    }

    @Override // p.e8
    public final void g(int i) {
        u();
        ViewGroup viewGroup = (ViewGroup) this.I.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.s).inflate(i, viewGroup);
        this.u.a(this.t.getCallback());
    }

    @Override // p.qi1
    public final boolean h(si1 si1Var, MenuItem menuItem) {
        r8 r8Var;
        Window.Callback callback = this.t.getCallback();
        if (callback != null && !this.Y) {
            si1 si1VarK = si1Var.k();
            r8[] r8VarArr = this.T;
            int length = r8VarArr != null ? r8VarArr.length : 0;
            int i = 0;
            while (true) {
                if (i < length) {
                    r8Var = r8VarArr[i];
                    if (r8Var != null && r8Var.h == si1VarK) {
                        break;
                    }
                    i++;
                } else {
                    r8Var = null;
                    break;
                }
            }
            if (r8Var != null) {
                return callback.onMenuItemSelected(r8Var.a, menuItem);
            }
        }
        return false;
    }

    @Override // p.e8
    public final void i(View view) {
        u();
        ViewGroup viewGroup = (ViewGroup) this.I.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.u.a(this.t.getCallback());
    }

    @Override // p.e8
    public final void j(View view, ViewGroup.LayoutParams layoutParams) {
        u();
        ViewGroup viewGroup = (ViewGroup) this.I.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.u.a(this.t.getCallback());
    }

    @Override // p.e8
    public final void k(CharSequence charSequence) {
        this.y = charSequence;
        i60 i60Var = this.z;
        if (i60Var != null) {
            i60Var.setWindowTitle(charSequence);
            return;
        }
        ow2 ow2Var = this.w;
        if (ow2Var != null) {
            ow2Var.M0(charSequence);
            return;
        }
        TextView textView = this.J;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00db  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean l(boolean r13, boolean r14) {
        /*
            Method dump skipped, instruction units count: 415
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.s8.l(boolean, boolean):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m(android.view.Window r5) {
        /*
            r4 = this;
            android.view.Window r0 = r4.t
            java.lang.String r1 = "AppCompat has already installed itself into the Window"
            if (r0 != 0) goto L6b
            android.view.Window$Callback r0 = r5.getCallback()
            boolean r2 = r0 instanceof p.m8
            if (r2 != 0) goto L65
            p.m8 r1 = new p.m8
            r1.<init>(r4, r0)
            r4.u = r1
            r5.setCallback(r1)
            int[] r0 = p.s8.q0
            p.v4 r1 = new p.v4
            android.content.Context r2 = r4.s
            r3 = 0
            android.content.res.TypedArray r0 = r2.obtainStyledAttributes(r3, r0)
            r1.<init>(r2, r0)
            r0 = 0
            android.graphics.drawable.Drawable r0 = r1.u(r0)
            if (r0 == 0) goto L30
            r5.setBackgroundDrawable(r0)
        L30:
            r1.L()
            r4.t = r5
            int r5 = android.os.Build.VERSION.SDK_INT
            r0 = 33
            if (r5 < r0) goto L64
            android.window.OnBackInvokedDispatcher r5 = r4.n0
            if (r5 != 0) goto L64
            if (r5 == 0) goto L4a
            android.window.OnBackInvokedCallback r0 = r4.o0
            if (r0 == 0) goto L4a
            p.l8.c(r5, r0)
            r4.o0 = r3
        L4a:
            java.lang.Object r5 = r4.r
            boolean r0 = r5 instanceof android.app.Activity
            if (r0 == 0) goto L5f
            android.app.Activity r5 = (android.app.Activity) r5
            android.view.Window r0 = r5.getWindow()
            if (r0 == 0) goto L5f
            android.window.OnBackInvokedDispatcher r5 = p.l8.a(r5)
            r4.n0 = r5
            goto L61
        L5f:
            r4.n0 = r3
        L61:
            r4.G()
        L64:
            return
        L65:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            r5.<init>(r1)
            throw r5
        L6b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            r5.<init>(r1)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p.s8.m(android.view.Window):void");
    }

    public final void o(int i, r8 r8Var, si1 si1Var) {
        if (si1Var == null) {
            if (r8Var == null && i >= 0) {
                r8[] r8VarArr = this.T;
                if (i < r8VarArr.length) {
                    r8Var = r8VarArr[i];
                }
            }
            if (r8Var != null) {
                si1Var = r8Var.h;
            }
        }
        if ((r8Var == null || r8Var.m) && !this.Y) {
            m8 m8Var = this.u;
            Window.Callback callback = this.t.getCallback();
            m8Var.getClass();
            try {
                m8Var.l = true;
                callback.onPanelClosed(i, si1Var);
            } finally {
                m8Var.l = false;
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
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
    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View g9Var;
        if (this.m0 == null) {
            int[] iArr = yz1.j;
            Context context2 = this.s;
            String string = context2.obtainStyledAttributes(iArr).getString(116);
            if (string == null) {
                this.m0 = new pa();
            } else {
                try {
                    this.m0 = (pa) context2.getClassLoader().loadClass(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable unused) {
                    this.m0 = new pa();
                }
            }
        }
        pa paVar = this.m0;
        int i = ws2.a;
        paVar.getClass();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, yz1.y, 0, 0);
        byte b = 4;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(4, 0);
        typedArrayObtainStyledAttributes.recycle();
        Context a00Var = (resourceId == 0 || ((context instanceof a00) && ((a00) context).a == resourceId)) ? context : new a00(context, resourceId);
        str.getClass();
        switch (str.hashCode()) {
            case -1946472170:
                b = !str.equals("RatingBar") ? (byte) -1 : (byte) 0;
                break;
            case -1455429095:
                b = !str.equals("CheckedTextView") ? (byte) -1 : (byte) 1;
                break;
            case -1346021293:
                b = !str.equals("MultiAutoCompleteTextView") ? (byte) -1 : (byte) 2;
                break;
            case -938935918:
                b = !str.equals("TextView") ? (byte) -1 : (byte) 3;
                break;
            case -937446323:
                if (!str.equals("ImageButton")) {
                    b = -1;
                }
                break;
            case -658531749:
                b = !str.equals("SeekBar") ? (byte) -1 : (byte) 5;
                break;
            case -339785223:
                b = !str.equals("Spinner") ? (byte) -1 : (byte) 6;
                break;
            case 776382189:
                b = !str.equals("RadioButton") ? (byte) -1 : (byte) 7;
                break;
            case 799298502:
                b = !str.equals("ToggleButton") ? (byte) -1 : (byte) 8;
                break;
            case 1125864064:
                b = !str.equals("ImageView") ? (byte) -1 : (byte) 9;
                break;
            case 1413872058:
                b = !str.equals("AutoCompleteTextView") ? (byte) -1 : (byte) 10;
                break;
            case 1601505219:
                b = !str.equals("CheckBox") ? (byte) -1 : (byte) 11;
                break;
            case 1666676343:
                b = !str.equals("EditText") ? (byte) -1 : (byte) 12;
                break;
            case 2001146706:
                b = !str.equals("Button") ? (byte) -1 : (byte) 13;
                break;
            default:
                b = -1;
                break;
        }
        View view2 = null;
        switch (b) {
            case 0:
                g9Var = new g9(a00Var, attributeSet);
                break;
            case 1:
                g9Var = new y7(a00Var, attributeSet);
                break;
            case 2:
                g9Var = new c9(a00Var, attributeSet);
                break;
            case 3:
                fa faVarE = paVar.e(a00Var, attributeSet);
                paVar.g(faVarE, str);
                g9Var = faVarE;
                break;
            case 4:
                g9Var = new z8(a00Var, attributeSet, com.spotify.music.R.attr.imageButtonStyle);
                break;
            case 5:
                g9Var = new i9(a00Var, attributeSet);
                break;
            case 6:
                g9Var = new t9(a00Var, attributeSet);
                break;
            case 7:
                f9 f9VarD = paVar.d(a00Var, attributeSet);
                paVar.g(f9VarD, str);
                g9Var = f9VarD;
                break;
            case 8:
                g9Var = new na(a00Var, attributeSet);
                break;
            case 9:
                g9Var = new b9(a00Var, attributeSet, 0);
                break;
            case 10:
                u7 u7VarA = paVar.a(a00Var, attributeSet);
                paVar.g(u7VarA, str);
                g9Var = u7VarA;
                break;
            case 11:
                x7 x7VarC = paVar.c(a00Var, attributeSet);
                paVar.g(x7VarC, str);
                g9Var = x7VarC;
                break;
            case 12:
                g9Var = new w8(a00Var, attributeSet);
                break;
            case 13:
                AppCompatButton appCompatButtonB = paVar.b(a00Var, attributeSet);
                paVar.g(appCompatButtonB, str);
                g9Var = appCompatButtonB;
                break;
            default:
                g9Var = null;
                break;
        }
        if (g9Var == null && context != a00Var) {
            Object[] objArr = paVar.a;
            if (str.equals(Search.Type.VIEW)) {
                str = attributeSet.getAttributeValue(null, "class");
            }
            try {
                objArr[0] = a00Var;
                objArr[1] = attributeSet;
                if (-1 == str.indexOf(46)) {
                    int i2 = 0;
                    while (true) {
                        String[] strArr = pa.g;
                        if (i2 < 3) {
                            View viewF = paVar.f(a00Var, str, strArr[i2]);
                            if (viewF != null) {
                                objArr[0] = null;
                                objArr[1] = null;
                                view2 = viewF;
                            } else {
                                i2++;
                            }
                        }
                    }
                } else {
                    View viewF2 = paVar.f(a00Var, str, null);
                    objArr[0] = null;
                    objArr[1] = null;
                    view2 = viewF2;
                }
            } catch (Exception unused2) {
            } finally {
                objArr[0] = null;
                objArr[1] = null;
            }
            g9Var = view2;
        }
        if (g9Var != null) {
            Context context3 = g9Var.getContext();
            if (context3 instanceof ContextWrapper) {
                WeakHashMap weakHashMap = zt2.a;
                if (ht2.a(g9Var)) {
                    TypedArray typedArrayObtainStyledAttributes2 = context3.obtainStyledAttributes(attributeSet, pa.c);
                    String string2 = typedArrayObtainStyledAttributes2.getString(0);
                    if (string2 != null) {
                        g9Var.setOnClickListener(new oa(g9Var, string2));
                    }
                    typedArrayObtainStyledAttributes2.recycle();
                }
            }
            if (Build.VERSION.SDK_INT <= 28) {
                TypedArray typedArrayObtainStyledAttributes3 = a00Var.obtainStyledAttributes(attributeSet, pa.d);
                if (typedArrayObtainStyledAttributes3.hasValue(0)) {
                    boolean z = typedArrayObtainStyledAttributes3.getBoolean(0, false);
                    WeakHashMap weakHashMap2 = zt2.a;
                    new et2(com.spotify.music.R.id.tag_accessibility_heading, 3).b(g9Var, Boolean.valueOf(z));
                }
                typedArrayObtainStyledAttributes3.recycle();
                TypedArray typedArrayObtainStyledAttributes4 = a00Var.obtainStyledAttributes(attributeSet, pa.e);
                if (typedArrayObtainStyledAttributes4.hasValue(0)) {
                    zt2.l(g9Var, typedArrayObtainStyledAttributes4.getString(0));
                }
                typedArrayObtainStyledAttributes4.recycle();
                TypedArray typedArrayObtainStyledAttributes5 = a00Var.obtainStyledAttributes(attributeSet, pa.f);
                if (typedArrayObtainStyledAttributes5.hasValue(0)) {
                    boolean z2 = typedArrayObtainStyledAttributes5.getBoolean(0, false);
                    WeakHashMap weakHashMap3 = zt2.a;
                    new et2(com.spotify.music.R.id.tag_screen_reader_focusable, 0).b(g9Var, Boolean.valueOf(z2));
                }
                typedArrayObtainStyledAttributes5.recycle();
            }
        }
        return g9Var;
    }

    public final void p(si1 si1Var) {
        g4 g4Var;
        if (this.S) {
            return;
        }
        this.S = true;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.z;
        actionBarOverlayLayout.k();
        ActionMenuView actionMenuView = ((hm2) actionBarOverlayLayout.m).a.c;
        if (actionMenuView != null && (g4Var = actionMenuView.B) != null) {
            g4Var.c();
            c4 c4Var = g4Var.B;
            if (c4Var != null && c4Var.b()) {
                c4Var.j.dismiss();
            }
        }
        Window.Callback callback = this.t.getCallback();
        if (callback != null && !this.Y) {
            callback.onPanelClosed(108, si1Var);
        }
        this.S = false;
    }

    public final void q(r8 r8Var, boolean z) {
        q8 q8Var;
        i60 i60Var;
        g4 g4Var;
        if (z && r8Var.a == 0 && (i60Var = this.z) != null) {
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) i60Var;
            actionBarOverlayLayout.k();
            ActionMenuView actionMenuView = ((hm2) actionBarOverlayLayout.m).a.c;
            if (actionMenuView != null && (g4Var = actionMenuView.B) != null && g4Var.e()) {
                p(r8Var.h);
                return;
            }
        }
        WindowManager windowManager = (WindowManager) this.s.getSystemService("window");
        if (windowManager != null && r8Var.m && (q8Var = r8Var.e) != null) {
            windowManager.removeView(q8Var);
            if (z) {
                o(r8Var.a, r8Var, null);
            }
        }
        r8Var.k = false;
        r8Var.l = false;
        r8Var.m = false;
        r8Var.f = null;
        r8Var.n = true;
        if (this.U == r8Var) {
            this.U = null;
        }
        if (r8Var.a == 0) {
            G();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x0104  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean s(android.view.KeyEvent r7) {
        /*
            Method dump skipped, instruction units count: 319
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.s8.s(android.view.KeyEvent):boolean");
    }

    public final void t(int i) {
        r8 r8VarX = x(i);
        if (r8VarX.h != null) {
            Bundle bundle = new Bundle();
            r8VarX.h.t(bundle);
            if (bundle.size() > 0) {
                r8VarX.f177p = bundle;
            }
            r8VarX.h.w();
            r8VarX.h.clear();
        }
        r8VarX.o = true;
        r8VarX.n = true;
        if ((i == 108 || i == 0) && this.z != null) {
            r8 r8VarX2 = x(0);
            r8VarX2.k = false;
            E(r8VarX2, null);
        }
    }

    public final void u() {
        ViewGroup viewGroup;
        if (this.H) {
            return;
        }
        int[] iArr = yz1.j;
        Context context = this.s;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(iArr);
        if (!typedArrayObtainStyledAttributes.hasValue(117)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(126, false)) {
            f(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(117, false)) {
            f(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(118, false)) {
            f(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(119, false)) {
            f(10);
        }
        this.Q = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        v();
        this.t.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        int i = 2;
        if (this.R) {
            viewGroup = this.P ? (ViewGroup) layoutInflaterFrom.inflate(com.spotify.music.R.layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(com.spotify.music.R.layout.abc_screen_simple, (ViewGroup) null);
        } else if (this.Q) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(com.spotify.music.R.layout.abc_dialog_title_material, (ViewGroup) null);
            this.O = false;
            this.N = false;
        } else if (this.N) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(com.spotify.music.R.attr.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new a00(context, typedValue.resourceId) : context).inflate(com.spotify.music.R.layout.abc_screen_toolbar, (ViewGroup) null);
            i60 i60Var = (i60) viewGroup.findViewById(com.spotify.music.R.id.decor_content_parent);
            this.z = i60Var;
            i60Var.setWindowCallback(this.t.getCallback());
            if (this.O) {
                ((ActionBarOverlayLayout) this.z).j(109);
            }
            if (this.L) {
                ((ActionBarOverlayLayout) this.z).j(2);
            }
            if (this.M) {
                ((ActionBarOverlayLayout) this.z).j(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.N + ", windowActionBarOverlay: " + this.O + ", android:windowIsFloating: " + this.Q + ", windowActionModeOverlay: " + this.P + ", windowNoTitle: " + this.R + " }");
        }
        g8 g8Var = new g8(this);
        WeakHashMap weakHashMap = zt2.a;
        ot2.u(viewGroup, g8Var);
        if (this.z == null) {
            this.J = (TextView) viewGroup.findViewById(com.spotify.music.R.id.title);
        }
        Method method = ev2.a;
        try {
            Method method2 = viewGroup.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
            if (!method2.isAccessible()) {
                method2.setAccessible(true);
            }
            method2.invoke(viewGroup, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(com.spotify.music.R.id.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.t.findViewById(R.id.content);
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
        this.t.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new hk0(i, this));
        this.I = viewGroup;
        Object obj = this.r;
        CharSequence title = obj instanceof Activity ? ((Activity) obj).getTitle() : this.y;
        if (!TextUtils.isEmpty(title)) {
            i60 i60Var2 = this.z;
            if (i60Var2 != null) {
                i60Var2.setWindowTitle(title);
            } else {
                ow2 ow2Var = this.w;
                if (ow2Var != null) {
                    ow2Var.M0(title);
                } else {
                    TextView textView = this.J;
                    if (textView != null) {
                        textView.setText(title);
                    }
                }
            }
        }
        ContentFrameLayout contentFrameLayout2 = (ContentFrameLayout) this.I.findViewById(R.id.content);
        View decorView = this.t.getDecorView();
        contentFrameLayout2.o.set(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        WeakHashMap weakHashMap2 = zt2.a;
        if (lt2.c(contentFrameLayout2)) {
            contentFrameLayout2.requestLayout();
        }
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(iArr);
        typedArrayObtainStyledAttributes2.getValue(124, contentFrameLayout2.getMinWidthMajor());
        typedArrayObtainStyledAttributes2.getValue(125, contentFrameLayout2.getMinWidthMinor());
        if (typedArrayObtainStyledAttributes2.hasValue(122)) {
            typedArrayObtainStyledAttributes2.getValue(122, contentFrameLayout2.getFixedWidthMajor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(123)) {
            typedArrayObtainStyledAttributes2.getValue(123, contentFrameLayout2.getFixedWidthMinor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(120)) {
            typedArrayObtainStyledAttributes2.getValue(120, contentFrameLayout2.getFixedHeightMajor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(121)) {
            typedArrayObtainStyledAttributes2.getValue(121, contentFrameLayout2.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes2.recycle();
        contentFrameLayout2.requestLayout();
        this.H = true;
        r8 r8VarX = x(0);
        if (this.Y || r8VarX.h != null) {
            return;
        }
        z(108);
    }

    public final void v() {
        if (this.t == null) {
            Object obj = this.r;
            if (obj instanceof Activity) {
                m(((Activity) obj).getWindow());
            }
        }
        if (this.t == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    public final p8 w(Context context) {
        if (this.e0 == null) {
            if (v4.m == null) {
                Context applicationContext = context.getApplicationContext();
                v4.m = new v4(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
            }
            this.e0 = new n8(this, v4.m);
        }
        return this.e0;
    }

    public final r8 x(int i) {
        r8[] r8VarArr = this.T;
        if (r8VarArr == null || r8VarArr.length <= i) {
            r8[] r8VarArr2 = new r8[i + 1];
            if (r8VarArr != null) {
                System.arraycopy(r8VarArr, 0, r8VarArr2, 0, r8VarArr.length);
            }
            this.T = r8VarArr2;
            r8VarArr = r8VarArr2;
        }
        r8 r8Var = r8VarArr[i];
        if (r8Var != null) {
            return r8Var;
        }
        r8 r8Var2 = new r8();
        r8Var2.a = i;
        r8Var2.n = false;
        r8VarArr[i] = r8Var2;
        return r8Var2;
    }

    public final void y() {
        u();
        if (this.N && this.w == null) {
            Object obj = this.r;
            if (obj instanceof Activity) {
                this.w = new ow2((Activity) obj, this.O);
            } else if (obj instanceof Dialog) {
                this.w = new ow2((Dialog) obj);
            }
            ow2 ow2Var = this.w;
            if (ow2Var != null) {
                ow2Var.K0(this.j0);
            }
        }
    }

    public final void z(int i) {
        this.h0 = (1 << i) | this.h0;
        if (this.g0) {
            return;
        }
        View decorView = this.t.getDecorView();
        WeakHashMap weakHashMap = zt2.a;
        it2.m(decorView, this.i0);
        this.g0 = true;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
