package org.chromium.content.browser.selection;

import J.N;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ParagraphStyle;
import android.text.style.UpdateAppearance;
import android.view.ActionMode;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.android.webview.R;
import defpackage.AbstractC0371Um;
import defpackage.AbstractC0443Ym;
import defpackage.AbstractC0566bn;
import defpackage.AbstractC0983jD;
import defpackage.BD;
import defpackage.C0522az;
import defpackage.C0571bs;
import defpackage.C0578bz;
import defpackage.C0590cA;
import defpackage.C0593cD;
import defpackage.C0704eD;
import defpackage.C0760fD;
import defpackage.C0872hD;
import defpackage.C1039kD;
import defpackage.C1151mD;
import defpackage.C1185mt;
import defpackage.C1206nD;
import defpackage.C1905zz;
import defpackage.HC;
import defpackage.InterfaceC0482aE;
import defpackage.InterfaceC0537bD;
import defpackage.InterfaceC1074kt;
import defpackage.InterfaceC1740wz;
import defpackage.JC;
import defpackage.Jt;
import defpackage.LD;
import defpackage.NC;
import defpackage.RunnableC0649dD;
import defpackage.SC;
import defpackage.TC;
import defpackage.Ts;
import defpackage.VC;
import defpackage.WN;
import defpackage.XC;
import defpackage.YD;
import defpackage.Yz;
import defpackage.Z7;
import defpackage.ZC;
import defpackage.ZD;
import defpackage.Zs;
import java.util.List;
import java.util.Objects;
import org.chromium.base.BuildInfo;
import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content.browser.RenderWidgetHostViewImpl;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SelectionPopupControllerImpl extends BD implements LD, Yz, InterfaceC1740wz, WN, InterfaceC1074kt {
    public static boolean Z;
    public ActionMode.Callback A;
    public Runnable C;
    public View D;
    public ActionMode E;
    public int F;
    public boolean G;
    public boolean H;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f31J;
    public boolean K;
    public boolean L;
    public String M;
    public int N;
    public boolean O;
    public boolean P;
    public ZC Q;
    public boolean R;
    public InterfaceC0482aE S;
    public C1039kD T;
    public YD V;
    public boolean W;
    public InterfaceC0537bD X;
    public HC Y;
    public Context u;
    public WindowAndroid v;
    public WebContentsImpl w;
    public ActionMode.Callback x;
    public long y;
    public ZD z;
    public final Rect B = new Rect();
    public final Handler t = new Handler();
    public C1905zz U = null;

    public SelectionPopupControllerImpl(WebContents webContents, C1905zz c1905zz, boolean z) {
        WebContentsImpl webContentsImpl = (WebContentsImpl) webContents;
        this.w = webContentsImpl;
        this.u = webContentsImpl.R();
        this.v = this.w.H();
        ViewAndroidDelegate viewAndroidDelegateD = this.w.D();
        if (viewAndroidDelegateD != null) {
            this.D = viewAndroidDelegateD.getContainerView();
            viewAndroidDelegateD.c.f(this);
        }
        this.F = 7;
        this.C = new RunnableC0649dD(this);
        C0590cA c0590cAP = C0590cA.P(this.w);
        if (c0590cAP != null) {
            c0590cAP.s.f(this);
            if (c0590cAP.v) {
                N(true);
            }
        }
        if (z) {
            this.y = N.MJHXNa8U(this, this.w);
            ImeAdapterImpl imeAdapterImplP = ImeAdapterImpl.P(this.w);
            if (imeAdapterImplP != null) {
                imeAdapterImplP.A.add(this);
            }
        }
        this.z = new C0872hD(this, null);
        this.M = "";
        B();
        if (C0578bz.a == null) {
            C0578bz.a = new C0578bz();
        }
        Objects.requireNonNull(C0578bz.a);
        this.Y = Build.VERSION.SDK_INT >= 28 ? new JC() : null;
        w().s.add(this);
    }

    public static void D(Context context, ActionMode actionMode, Menu menu) {
        if (!Z) {
            try {
                actionMode.getMenuInflater().inflate(AbstractC0443Ym.c, menu);
                return;
            } catch (Resources.NotFoundException unused) {
            }
        }
        new MenuInflater(context).inflate(AbstractC0443Ym.c, menu);
    }

    public static String I(String str, int i) {
        if (TextUtils.isEmpty(str) || str.length() < i) {
            return str;
        }
        StringBuilder sbF = Z7.f("Truncating oversized query (");
        sbF.append(str.length());
        sbF.append(").");
        Ts.f("SelectionPopupCtlr", sbF.toString(), new Object[0]);
        return str.substring(0, i) + "…";
    }

    private Context getContext() {
        return this.u;
    }

    private void nativeSelectionPopupControllerDestroyed() {
        this.y = 0L;
    }

    private void onSelectWordAroundCaretAck(boolean z, int i, int i2) {
        InterfaceC0482aE interfaceC0482aE = this.S;
        if (interfaceC0482aE != null) {
            Objects.requireNonNull((SmartSelectionClient) interfaceC0482aE);
        }
    }

    public static SelectionPopupControllerImpl t(WebContents webContents) {
        return (SelectionPopupControllerImpl) ((WebContentsImpl) webContents).S(SelectionPopupControllerImpl.class, AbstractC0983jD.a);
    }

    public final void A(boolean z) {
        if (E() && this.G != z) {
            this.G = z;
            if (z) {
                this.C.run();
                return;
            }
            this.t.removeCallbacks(this.C);
            if (Build.VERSION.SDK_INT < 23 || !g()) {
                return;
            }
            C1185mt.g(this.E, 300L);
        }
    }

    public final void B() {
        ThreadUtils.b();
        if (C0578bz.a == null) {
            C0578bz.a = new C0578bz();
        }
        C0578bz c0578bz = C0578bz.a;
        C0593cD c0593cD = new C0593cD(this);
        Objects.requireNonNull(c0578bz);
        this.X = Build.VERSION.SDK_INT < 28 ? null : new VC(new XC(c0593cD));
    }

    @Override // defpackage.InterfaceC0771fO
    public void C(Display.Mode mode) {
    }

    public final boolean E() {
        return M() && g() && C1185mt.a(this.E) == 1;
    }

    public boolean F() {
        return this.Q != null;
    }

    public final boolean G(int i) {
        boolean z = (this.F & i) != 0;
        if (i != 1) {
            return z;
        }
        if (!z) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        return Zs.b(intent, R.anim.abc_fade_in).isEmpty() ^ true;
    }

    public void H(int i, Intent intent) {
        CharSequence charSequenceExtra;
        if (this.w == null || i != -1 || intent == null || !this.P || !this.H || (charSequenceExtra = intent.getCharSequenceExtra("android.intent.extra.PROCESS_TEXT")) == null) {
            return;
        }
        WebContentsImpl webContentsImpl = this.w;
        String string = charSequenceExtra.toString();
        webContentsImpl.O();
        N.MevqfbP8(webContentsImpl.t, webContentsImpl, string);
    }

    public void J() {
        WebContentsImpl webContentsImpl = this.w;
        webContentsImpl.O();
        N.MNvj1u1S(webContentsImpl.t, webContentsImpl);
        this.V = null;
        if (this.H) {
            Jt.a("MobileActionMode.SelectAllWasEditable");
        } else {
            Jt.a("MobileActionMode.SelectAllWasNonEditable");
        }
    }

    public void K() {
        if ((this.x != BD.s) && this.P && this.D != null) {
            if (g() && !E()) {
                try {
                    this.E.invalidate();
                } catch (NullPointerException e) {
                    Ts.f("SelectionPopupCtlr", "Ignoring NPE from ActionMode.invalidate() as workaround for L", e);
                }
                A(false);
                return;
            }
            q();
            ActionMode actionModeA = M() ? C0522az.a(this.D, this, this.x) : this.D.startActionMode(this.x);
            if (actionModeA != null) {
                SC.b(this.u, actionModeA);
            }
            this.E = actionModeA;
            this.L = true;
            if (g()) {
                return;
            }
            p();
        }
    }

    public final void L(int i, int i2) {
        if (this.w.T() != null) {
            RenderWidgetHostViewImpl renderWidgetHostViewImplT = this.w.T();
            long j = renderWidgetHostViewImplT.a;
            if (j == 0) {
                throw new IllegalStateException("Native RenderWidgetHostViewAndroid already destroyed", renderWidgetHostViewImplT.b);
            }
            N.McU85DFE(j, renderWidgetHostViewImplT, i, i2);
        }
    }

    public boolean M() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public void N(boolean z) {
        boolean z2 = !z;
        long j = this.y;
        if (j != 0) {
            N.M01adZlM(j, this, z2);
        }
        if (z) {
            restoreSelectionPopupsIfNecessary();
        } else {
            q();
            w().b();
        }
    }

    @Override // defpackage.InterfaceC1740wz
    public void a() {
        r();
    }

    @Override // defpackage.Yz
    public void b(boolean z, boolean z2) {
        C1905zz c1905zzA;
        if (z) {
            restoreSelectionPopupsIfNecessary();
            return;
        }
        ImeAdapterImpl.P(this.w).H.setEmpty();
        if (this.W) {
            this.W = false;
            hidePopupsAndPreserveSelection();
            return;
        }
        this.L = true;
        s();
        if (this.w.T() != null) {
            this.w.T().a();
        }
        WebContentsImpl webContentsImpl = this.w;
        if (webContentsImpl != null && (c1905zzA = C1905zz.a(webContentsImpl)) != null) {
            c1905zzA.b();
        }
        p();
    }

    @Override // defpackage.LD
    public void c() {
    }

    @Override // defpackage.LD
    public void d(KeyEvent keyEvent) {
    }

    @Override // defpackage.InterfaceC0771fO
    public void e(float f) {
    }

    @Override // defpackage.LD
    public void f(boolean z, boolean z2) {
        if (!z) {
            r();
        }
        if (z == this.H && z2 == this.I) {
            return;
        }
        this.H = z;
        this.I = z2;
        if (g()) {
            this.E.invalidate();
        }
    }

    @Override // defpackage.BD
    public boolean g() {
        return this.E != null;
    }

    @Override // defpackage.BD
    public boolean h(ActionMode actionMode, MenuItem menuItem) {
        Context context;
        C1039kD c1039kD;
        if (!g()) {
            return true;
        }
        int itemId = menuItem.getItemId();
        int groupId = menuItem.getGroupId();
        if (this.P && (c1039kD = this.T) != null) {
            String str = this.M;
            int i = this.N;
            int i2 = 105;
            if (groupId != 16908353) {
                if (itemId == AbstractC0371Um.U3) {
                    i2 = 200;
                } else if (itemId == AbstractC0371Um.Q3) {
                    i2 = 103;
                } else if (itemId == AbstractC0371Um.P3) {
                    i2 = 101;
                } else if (itemId == AbstractC0371Um.S3 || itemId == AbstractC0371Um.T3) {
                    i2 = 102;
                } else if (itemId == AbstractC0371Um.V3) {
                    i2 = 104;
                } else if (itemId != 16908353) {
                    i2 = 108;
                }
            }
            c1039kD.e(str, i, i2, this.V);
        }
        if (groupId == AbstractC0371Um.O3 && itemId == 16908353) {
            YD yd = this.V;
            if (yd != null && yd.a()) {
                YD yd2 = this.V;
                View.OnClickListener onClickListener = yd2.f;
                if (onClickListener != null) {
                    onClickListener.onClick(this.D);
                } else if (yd2.e != null && (context = (Context) this.v.S().get()) != null) {
                    context.startActivity(this.V.e);
                }
            }
            actionMode.finish();
        } else if (itemId == AbstractC0371Um.U3) {
            J();
        } else if (itemId == AbstractC0371Um.Q3) {
            this.w.Q();
            actionMode.finish();
        } else if (itemId == AbstractC0371Um.P3) {
            this.w.P();
            actionMode.finish();
        } else if (itemId == AbstractC0371Um.S3) {
            this.w.V();
            actionMode.finish();
        } else if (Build.VERSION.SDK_INT >= 26 && itemId == AbstractC0371Um.T3) {
            WebContentsImpl webContentsImpl = this.w;
            webContentsImpl.O();
            N.MdSkKRWg(webContentsImpl.t, webContentsImpl);
            actionMode.finish();
        } else if (itemId == AbstractC0371Um.V3) {
            Jt.a("MobileActionMode.Share");
            String strI = I(this.M, 100000);
            if (!TextUtils.isEmpty(strI)) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", strI);
                try {
                    Intent intentCreateChooser = Intent.createChooser(intent, this.u.getString(AbstractC0566bn.k0));
                    intentCreateChooser.setFlags(268435456);
                    this.u.startActivity(intentCreateChooser);
                } catch (ActivityNotFoundException unused) {
                }
            }
            actionMode.finish();
        } else if (itemId == AbstractC0371Um.X3) {
            Jt.a("MobileActionMode.WebSearch");
            String strI2 = I(this.M, 1000);
            if (!TextUtils.isEmpty(strI2)) {
                Intent intent2 = new Intent("android.intent.action.WEB_SEARCH");
                intent2.putExtra("new_search", true);
                intent2.putExtra("query", strI2);
                intent2.putExtra("com.android.browser.application_id", this.u.getPackageName());
                intent2.addFlags(268435456);
                try {
                    this.u.startActivity(intent2);
                } catch (ActivityNotFoundException unused2) {
                }
            }
            actionMode.finish();
        } else if (groupId == AbstractC0371Um.W3) {
            Intent intent3 = menuItem.getIntent();
            Jt.a("MobileActionMode.ProcessTextIntent");
            String strI3 = I(this.M, 1000);
            if (!TextUtils.isEmpty(strI3)) {
                intent3.putExtra("android.intent.extra.PROCESS_TEXT", strI3);
                try {
                    this.v.X(intent3, new C0760fD(this), null);
                } catch (ActivityNotFoundException unused3) {
                }
            }
        } else {
            if (groupId != 16908353) {
                return false;
            }
            HC hc = this.Y;
            if (hc != null) {
                hc.c(menuItem, this.D);
                actionMode.finish();
            }
        }
        return true;
    }

    public void hidePopupsAndPreserveSelection() {
        q();
        w().b();
    }

    @Override // defpackage.BD
    public void i(ActionMode actionMode, Menu menu) {
        actionMode.setTitle(DeviceFormFactor.a(this.v) ? this.u.getString(AbstractC0566bn.l0) : null);
        actionMode.setSubtitle((CharSequence) null);
    }

    @Override // defpackage.InterfaceC0771fO
    public void j(float f) {
    }

    @Override // defpackage.BD
    public boolean k(ActionMode actionMode, Menu menu) {
        HC hc;
        YD yd;
        HC hc2 = this.Y;
        if (hc2 != null) {
            hc2.b();
        }
        menu.removeGroup(AbstractC0371Um.R3);
        menu.removeGroup(AbstractC0371Um.O3);
        menu.removeGroup(AbstractC0371Um.W3);
        menu.removeGroup(android.R.id.textAssist);
        int i = Build.VERSION.SDK_INT;
        D(this.u, actionMode, menu);
        if (i >= 26 && (yd = this.V) != null && yd.a()) {
            menu.add(AbstractC0371Um.O3, android.R.id.textAssist, 1, this.V.c).setIcon(this.V.d);
        }
        if (!this.H || !n()) {
            menu.removeItem(AbstractC0371Um.S3);
            menu.removeItem(AbstractC0371Um.T3);
        }
        if (!o()) {
            menu.removeItem(AbstractC0371Um.T3);
        }
        if (this.P) {
            if (!this.H) {
                menu.removeItem(AbstractC0371Um.Q3);
            }
            if (this.H || !G(1)) {
                menu.removeItem(AbstractC0371Um.V3);
            }
            if (this.H || this.w.l() || !G(2)) {
                menu.removeItem(AbstractC0371Um.X3);
            }
            if (this.I) {
                menu.removeItem(AbstractC0371Um.P3);
                menu.removeItem(AbstractC0371Um.Q3);
            }
        } else {
            menu.removeItem(AbstractC0371Um.U3);
            menu.removeItem(AbstractC0371Um.Q3);
            menu.removeItem(AbstractC0371Um.P3);
            menu.removeItem(AbstractC0371Um.V3);
            menu.removeItem(AbstractC0371Um.X3);
        }
        MenuItem menuItemFindItem = menu.findItem(AbstractC0371Um.T3);
        if (menuItemFindItem != null) {
            menuItemFindItem.setTitle(android.R.string.paste_as_plain_text);
        }
        Context context = (Context) this.v.S().get();
        YD yd2 = this.V;
        if (yd2 != null && (hc = this.Y) != null && context != null) {
            hc.a(context, menu, yd2.g);
        }
        if (this.P && !this.I && i >= 23 && G(4)) {
            List listB = Zs.b(new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain"), 0);
            for (int i2 = 0; i2 < listB.size(); i2++) {
                ResolveInfo resolveInfo = (ResolveInfo) listB.get(i2);
                MenuItem menuItemAdd = menu.add(AbstractC0371Um.W3, 0, i2 + 100, resolveInfo.loadLabel(this.u.getPackageManager()));
                Intent intentPutExtra = new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain").putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !this.H);
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                menuItemAdd.setIntent(intentPutExtra.setClassName(activityInfo.packageName, activityInfo.name)).setShowAsAction(1);
            }
        }
        return true;
    }

    @Override // defpackage.WN
    public void m(ViewGroup viewGroup) {
        if (g()) {
            s();
        }
        this.L = true;
        r();
        if (viewGroup != null) {
            viewGroup.setClickable(true);
        }
        this.D = viewGroup;
        B();
    }

    public final boolean n() {
        return ((ClipboardManager) this.u.getSystemService("clipboard")).hasPrimaryClip();
    }

    public boolean o() {
        boolean z = false;
        if (Build.VERSION.SDK_INT < 26 || !this.K) {
            return false;
        }
        ClipboardManager clipboardManager = (ClipboardManager) this.u.getSystemService("clipboard");
        if (!clipboardManager.hasPrimaryClip()) {
            return false;
        }
        ClipData primaryClip = clipboardManager.getPrimaryClip();
        ClipDescription description = primaryClip.getDescription();
        CharSequence text = primaryClip.getItemAt(0).getText();
        if (description.hasMimeType("text/plain") && (text instanceof Spanned)) {
            Spanned spanned = (Spanned) text;
            Class[] clsArr = {CharacterStyle.class, ParagraphStyle.class, UpdateAppearance.class};
            int i = 0;
            while (true) {
                if (i >= 3) {
                    break;
                }
                if (spanned.nextSpanTransition(-1, spanned.length(), clsArr[i]) < spanned.length()) {
                    z = true;
                    break;
                }
                i++;
            }
            if (z) {
                return true;
            }
        }
        return description.hasMimeType("text/html");
    }

    @Override // defpackage.Yz
    public void onAttachedToWindow() {
        N(true);
    }

    @Override // defpackage.Yz
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // defpackage.Yz
    public void onDetachedFromWindow() {
        N(false);
    }

    public void onDragUpdate(float f, float f2) {
        if (this.X != null) {
            float fV = v();
            float f3 = f * fV;
            float f4 = (f2 * fV) + this.w.y.k;
            VC vc = (VC) this.X;
            if (((C0593cD) ((XC) vc.a).b).a.D != null) {
                if (vc.c && f4 != vc.i) {
                    if (vc.b.isRunning()) {
                        vc.b.cancel();
                        vc.a();
                        vc.f = vc.d;
                        vc.g = vc.e;
                    } else {
                        vc.f = vc.h;
                        vc.g = vc.i;
                    }
                    vc.b.start();
                } else if (!vc.b.isRunning()) {
                    ((XC) vc.a).a(f3, f4);
                }
                vc.h = f3;
                vc.i = f4;
                vc.c = true;
            }
        }
    }

    public void onSelectionChanged(String str) {
        if (TextUtils.isEmpty(str) && this.P) {
            C1039kD c1039kD = this.T;
            if (c1039kD != null) {
                c1039kD.e(this.M, this.N, 107, null);
            }
            q();
        }
        this.M = str;
        InterfaceC0482aE interfaceC0482aE = this.S;
        if (interfaceC0482aE != null) {
            Objects.requireNonNull((SmartSelectionClient) interfaceC0482aE);
        }
    }

    public void onSelectionEvent(int i, int i2, int i3, int i4, int i5) {
        View view;
        View view2;
        if (i2 == i4) {
            i4++;
        }
        if (i3 == i5) {
            i5++;
        }
        switch (i) {
            case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                this.B.set(i2, i3, i4, i5);
                break;
            case 1:
                this.B.set(i2, i3, i4, i5);
                if (M() && g()) {
                    C1185mt.h(this.E);
                }
                if (this.O && BuildInfo.a() && (view = this.D) != null) {
                    view.performHapticFeedback(9);
                }
                break;
            case 2:
                this.M = "";
                this.N = 0;
                this.P = false;
                this.L = false;
                this.B.setEmpty();
                InterfaceC0482aE interfaceC0482aE = this.S;
                if (interfaceC0482aE != null) {
                    SmartSelectionClient smartSelectionClient = (SmartSelectionClient) interfaceC0482aE;
                    long j = smartSelectionClient.a;
                    if (j != 0) {
                        N.MVHq2mA2(j, smartSelectionClient);
                    }
                    C1206nD c1206nD = smartSelectionClient.b;
                    C1151mD c1151mD = c1206nD.c;
                    if (c1151mD != null) {
                        c1151mD.b(false);
                        c1206nD.c = null;
                    }
                }
                s();
                break;
            case 3:
                A(true);
                this.O = true;
                break;
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                L(i2, i5);
                InterfaceC0537bD interfaceC0537bD = this.X;
                if (interfaceC0537bD != null) {
                    ((VC) interfaceC0537bD).b();
                }
                this.O = false;
                break;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                this.B.set(i2, i3, i4, i5);
                break;
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                this.B.set(i2, i3, i4, i5);
                if (GestureListenerManagerImpl.O(this.w).isScrollInProgress() || !F()) {
                    r();
                } else {
                    try {
                        this.Q.b(z());
                        break;
                    } catch (WindowManager.BadTokenException unused) {
                    }
                }
                if (this.O && BuildInfo.a() && (view2 = this.D) != null) {
                    view2.performHapticFeedback(9);
                }
                break;
            case 7:
                if (this.R) {
                    r();
                } else {
                    Rect rect = this.B;
                    L(rect.left, rect.bottom);
                }
                this.R = false;
                break;
            case 8:
                r();
                if (!this.P) {
                    this.B.setEmpty();
                }
                break;
            case 9:
                this.R = F();
                r();
                this.O = true;
                break;
            case 10:
                if (this.R) {
                    Rect rect2 = this.B;
                    L(rect2.left, rect2.bottom);
                }
                this.R = false;
                InterfaceC0537bD interfaceC0537bD2 = this.X;
                if (interfaceC0537bD2 != null) {
                    ((VC) interfaceC0537bD2).b();
                }
                this.O = false;
                break;
        }
        if (this.S != null) {
            v();
            int i6 = this.B.left;
            Objects.requireNonNull((SmartSelectionClient) this.S);
        }
    }

    @Override // defpackage.Yz
    public void onWindowFocusChanged(boolean z) {
        if (M() && g()) {
            C1185mt.n(this.E, z);
        }
    }

    public void p() {
        if (this.w != null) {
            if (this.x != BD.s) {
                WebContentsImpl webContentsImpl = this.w;
                if (!webContentsImpl.v()) {
                    N.MDK_KK0z(webContentsImpl.t, webContentsImpl);
                }
                this.V = null;
            }
        }
    }

    public void q() {
        this.L = false;
        s();
    }

    public void r() {
        if (F()) {
            this.Q.a();
            this.Q = null;
        }
    }

    public void restoreSelectionPopupsIfNecessary() {
        if (!this.P || g()) {
            return;
        }
        K();
    }

    public void s() {
        this.G = false;
        this.t.removeCallbacks(this.C);
        if (g()) {
            this.E.finish();
            this.E = null;
        }
    }

    public void showSelectionMenu(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, String str, int i6, boolean z3, boolean z4, boolean z5, int i7) {
        if (M()) {
            i4 += i5;
        }
        this.B.set(i, i2, i3, i4);
        this.H = z;
        this.M = str;
        this.N = i6;
        boolean z6 = str.length() != 0;
        this.P = z6;
        this.I = z2;
        this.f31J = z3;
        this.K = z4;
        this.L = true;
        if (z6) {
            C1039kD c1039kD = this.T;
            if (c1039kD != null && i7 != 7) {
                if (i7 == 9) {
                    c1039kD.f(this.M, this.N, this.V);
                } else if (i7 != 10) {
                    c1039kD.g(this.M, this.N, z);
                } else {
                    c1039kD.e(this.M, this.N, 201, null);
                }
            }
            if (i7 == 9) {
                K();
                return;
            }
            InterfaceC0482aE interfaceC0482aE = this.S;
            if (interfaceC0482aE != null) {
                ((SmartSelectionClient) interfaceC0482aE).a(z5);
                return;
            } else {
                K();
                return;
            }
        }
        View view = this.D;
        if (view == null || view.getParent() == null || this.D.getVisibility() != 0) {
            return;
        }
        if (M() || n() || this.A != null) {
            r();
            C0704eD c0704eD = new C0704eD(this);
            Context context = (Context) this.v.S().get();
            if (context == null) {
                return;
            }
            if (M()) {
                this.Q = new NC(context, this.D, c0704eD, this.A);
            } else {
                this.Q = new TC(context, this.D, c0704eD);
            }
            try {
                this.Q.b(z());
            } catch (WindowManager.BadTokenException unused) {
            }
        }
    }

    @Override // defpackage.Yz
    public void u(WindowAndroid windowAndroid) {
        this.v = windowAndroid;
        this.u = this.w.R();
        B();
        r();
    }

    public final float v() {
        return this.w.y.j;
    }

    public final C1905zz w() {
        if (this.U == null) {
            this.U = C1905zz.a(this.w);
        }
        return this.U;
    }

    @Override // defpackage.InterfaceC0771fO
    public void x(int i) {
        if (Build.VERSION.SDK_INT < 23 || !g()) {
            return;
        }
        hidePopupsAndPreserveSelection();
        K();
    }

    @Override // defpackage.InterfaceC0771fO
    public void y(List list) {
    }

    public final Rect z() {
        float fV = v();
        Rect rect = this.B;
        Rect rect2 = new Rect((int) (rect.left * fV), (int) (rect.top * fV), (int) (rect.right * fV), (int) (rect.bottom * fV));
        rect2.offset(0, (int) this.w.y.k);
        return rect2;
    }
}
