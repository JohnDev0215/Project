package e1;

import D.C0130l;
import F0.AbstractC0191a;
import F0.X0;
import H7.H;
import T.AbstractC0764s;
import T.C0735d;
import T.C0738e0;
import T.C0754m0;
import T.C0761q;
import T.D;
import T.S;
import android.graphics.Rect;
import android.os.Build;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import androidx.lifecycle.a0;
import com.stingray.drivemodedetector.R;
import e0.C1183w;
import e0.C1184x;
import java.util.UUID;
import n7.AbstractC1999n;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class x extends AbstractC0191a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public z f13821A;
    public a1.k B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final C0738e0 f13822C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final C0738e0 f13823D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public a1.i f13824E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final D f13825F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final Rect f13826G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final C1184x f13827H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public Object f13828I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final C0738e0 f13829J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public boolean f13830K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final int[] f13831L;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public InterfaceC2772a f13832t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public C1187A f13833u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f13834v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final View f13835w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final y f13836x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final WindowManager f13837y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final WindowManager.LayoutParams f13838z;

    public x(InterfaceC2772a interfaceC2772a, C1187A c1187a, String str, View view, a1.b bVar, z zVar, UUID uuid) {
        y yVar = new y();
        super(view.getContext());
        this.f13832t = interfaceC2772a;
        this.f13833u = c1187a;
        this.f13834v = str;
        this.f13835w = view;
        this.f13836x = yVar;
        Object systemService = view.getContext().getSystemService("window");
        A7.m.d(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        this.f13837y = (WindowManager) systemService;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = 8388659;
        C1187A c1187a2 = this.f13833u;
        boolean zC = m.c(view);
        boolean z6 = c1187a2.f13760b;
        int i7 = c1187a2.f13759a;
        if (z6 && zC) {
            i7 |= 8192;
        } else if (z6 && !zC) {
            i7 &= -8193;
        }
        layoutParams.flags = i7;
        layoutParams.type = 1002;
        layoutParams.token = view.getApplicationWindowToken();
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.setTitle(view.getContext().getResources().getString(R.string.default_popup_window_title));
        this.f13838z = layoutParams;
        this.f13821A = zVar;
        this.B = a1.k.l;
        S s6 = S.f8224q;
        this.f13822C = C0735d.O(null, s6);
        this.f13823D = C0735d.O(null, s6);
        this.f13825F = C0735d.G(new C1183w(this, 1));
        this.f13826G = new Rect();
        this.f13827H = new C1184x(new k(this, 2));
        setId(android.R.id.content);
        a0.j(this, a0.f(view));
        a0.k(this, a0.g(view));
        h6.q.z(this, h6.q.s(view));
        setTag(R.id.compose_view_saveable_id_tag, "Popup:" + uuid);
        setClipChildren(false);
        setElevation(bVar.y((float) 8));
        setOutlineProvider(new X0(2));
        this.f13829J = C0735d.O(r.f13804a, s6);
        this.f13831L = new int[2];
    }

    private final InterfaceC2785n getContent() {
        return (InterfaceC2785n) this.f13829J.getValue();
    }

    private final int getDisplayHeight() {
        return Math.round(getContext().getResources().getConfiguration().screenHeightDp * getContext().getResources().getDisplayMetrics().density);
    }

    private final int getDisplayWidth() {
        return Math.round(getContext().getResources().getConfiguration().screenWidthDp * getContext().getResources().getDisplayMetrics().density);
    }

    public static /* synthetic */ void getParams$ui_release$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final C0.r getParentLayoutCoordinates() {
        return (C0.r) this.f13823D.getValue();
    }

    private final void setContent(InterfaceC2785n interfaceC2785n) {
        this.f13829J.setValue(interfaceC2785n);
    }

    private final void setParentLayoutCoordinates(C0.r rVar) {
        this.f13823D.setValue(rVar);
    }

    @Override // F0.AbstractC0191a
    public final void a(int i7, C0761q c0761q) {
        int i10;
        c0761q.S(-857613600);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(this) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            getContent().invoke(c0761q, 0);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0130l(i7, 8, this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        KeyEvent.DispatcherState keyDispatcherState;
        if (keyEvent.getKeyCode() == 4 && this.f13833u.c) {
            if (getKeyDispatcherState() == null) {
                return super.dispatchKeyEvent(keyEvent);
            }
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                if (keyDispatcherState2 != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                }
                return true;
            }
            if (keyEvent.getAction() == 1 && (keyDispatcherState = getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent) && !keyEvent.isCanceled()) {
                InterfaceC2772a interfaceC2772a = this.f13832t;
                if (interfaceC2772a != null) {
                    interfaceC2772a.a();
                }
                return true;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // F0.AbstractC0191a
    public final void e(boolean z6, int i7, int i10, int i11, int i12) {
        super.e(z6, i7, i10, i11, i12);
        this.f13833u.getClass();
        View childAt = getChildAt(0);
        if (childAt == null) {
            return;
        }
        WindowManager.LayoutParams layoutParams = this.f13838z;
        layoutParams.width = childAt.getMeasuredWidth();
        layoutParams.height = childAt.getMeasuredHeight();
        this.f13836x.getClass();
        this.f13837y.updateViewLayout(this, layoutParams);
    }

    @Override // F0.AbstractC0191a
    public final void f(int i7, int i10) {
        this.f13833u.getClass();
        super.f(View.MeasureSpec.makeMeasureSpec(getDisplayWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getDisplayHeight(), Integer.MIN_VALUE));
    }

    public final boolean getCanCalculatePosition() {
        return ((Boolean) this.f13825F.getValue()).booleanValue();
    }

    public final WindowManager.LayoutParams getParams$ui_release() {
        return this.f13838z;
    }

    public final a1.k getParentLayoutDirection() {
        return this.B;
    }

    /* JADX INFO: renamed from: getPopupContentSize-bOM6tXw, reason: not valid java name */
    public final a1.j m1getPopupContentSizebOM6tXw() {
        return (a1.j) this.f13822C.getValue();
    }

    public final z getPositionProvider() {
        return this.f13821A;
    }

    @Override // F0.AbstractC0191a
    public boolean getShouldCreateCompositionOnAttachedToWindow() {
        return this.f13830K;
    }

    public AbstractC0191a getSubCompositionView() {
        return this;
    }

    public final String getTestTag() {
        return this.f13834v;
    }

    public /* bridge */ /* synthetic */ View getViewRoot() {
        return null;
    }

    public final void i(AbstractC0764s abstractC0764s, InterfaceC2785n interfaceC2785n) {
        setParentCompositionContext(abstractC0764s);
        setContent(interfaceC2785n);
        this.f13830K = true;
    }

    public final void j(InterfaceC2772a interfaceC2772a, C1187A c1187a, String str, a1.k kVar) {
        int i7;
        this.f13832t = interfaceC2772a;
        this.f13834v = str;
        if (!A7.m.a(this.f13833u, c1187a)) {
            c1187a.getClass();
            WindowManager.LayoutParams layoutParams = this.f13838z;
            this.f13833u = c1187a;
            boolean zC = m.c(this.f13835w);
            boolean z6 = c1187a.f13760b;
            int i10 = c1187a.f13759a;
            if (z6 && zC) {
                i10 |= 8192;
            } else if (z6 && !zC) {
                i10 &= -8193;
            }
            layoutParams.flags = i10;
            this.f13836x.getClass();
            this.f13837y.updateViewLayout(this, layoutParams);
        }
        int iOrdinal = kVar.ordinal();
        if (iOrdinal != 0) {
            i7 = 1;
            if (iOrdinal != 1) {
                throw new A9.k();
            }
        } else {
            i7 = 0;
        }
        super.setLayoutDirection(i7);
    }

    public final void k() {
        C0.r parentLayoutCoordinates = getParentLayoutCoordinates();
        if (parentLayoutCoordinates != null) {
            if (!parentLayoutCoordinates.D()) {
                parentLayoutCoordinates = null;
            }
            if (parentLayoutCoordinates == null) {
                return;
            }
            long jL = parentLayoutCoordinates.L();
            long jG = parentLayoutCoordinates.g(0L);
            long jD = AbstractC2673a.d(Math.round(m0.c.d(jG)), Math.round(m0.c.e(jG)));
            int i7 = (int) (jD >> 32);
            int i10 = (int) (jD & 4294967295L);
            a1.i iVar = new a1.i(i7, i10, ((int) (jL >> 32)) + i7, ((int) (jL & 4294967295L)) + i10);
            if (iVar.equals(this.f13824E)) {
                return;
            }
            this.f13824E = iVar;
            m();
        }
    }

    public final void l(C0.r rVar) {
        setParentLayoutCoordinates(rVar);
        k();
    }

    public final void m() {
        a1.j jVarM1getPopupContentSizebOM6tXw;
        a1.i iVar = this.f13824E;
        if (iVar == null || (jVarM1getPopupContentSizebOM6tXw = m1getPopupContentSizebOM6tXw()) == null) {
            return;
        }
        y yVar = this.f13836x;
        yVar.getClass();
        View view = this.f13835w;
        Rect rect = this.f13826G;
        view.getWindowVisibleDisplayFrame(rect);
        long jC = H.c(rect.right - rect.left, rect.bottom - rect.top);
        A7.y yVar2 = new A7.y();
        yVar2.l = 0L;
        this.f13827H.d(this, C1190c.f13770s, new w(yVar2, this, iVar, jC, jVarM1getPopupContentSizebOM6tXw.f11037a));
        WindowManager.LayoutParams layoutParams = this.f13838z;
        long j3 = yVar2.l;
        layoutParams.x = (int) (j3 >> 32);
        layoutParams.y = (int) (j3 & 4294967295L);
        if (this.f13833u.f13761e) {
            yVar.getClass();
            setSystemGestureExclusionRects(AbstractC1999n.F(new Rect(0, 0, (int) (jC >> 32), (int) (jC & 4294967295L))));
        }
        this.f13837y.updateViewLayout(this, layoutParams);
    }

    @Override // F0.AbstractC0191a, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f13827H.e();
        if (!this.f13833u.c || Build.VERSION.SDK_INT < 33) {
            return;
        }
        if (this.f13828I == null) {
            this.f13828I = o.a(this.f13832t);
        }
        o.b(this, this.f13828I);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C1184x c1184x = this.f13827H;
        G2.a aVar = c1184x.f13750g;
        if (aVar != null) {
            aVar.k();
        }
        c1184x.b();
        if (Build.VERSION.SDK_INT >= 33) {
            o.c(this, this.f13828I);
        }
        this.f13828I = null;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f13833u.d) {
            return super.onTouchEvent(motionEvent);
        }
        if (motionEvent != null && motionEvent.getAction() == 0 && (motionEvent.getX() < 0.0f || motionEvent.getX() >= getWidth() || motionEvent.getY() < 0.0f || motionEvent.getY() >= getHeight())) {
            InterfaceC2772a interfaceC2772a = this.f13832t;
            if (interfaceC2772a != null) {
                interfaceC2772a.a();
            }
            return true;
        }
        if (motionEvent == null || motionEvent.getAction() != 4) {
            return super.onTouchEvent(motionEvent);
        }
        InterfaceC2772a interfaceC2772a2 = this.f13832t;
        if (interfaceC2772a2 != null) {
            interfaceC2772a2.a();
        }
        return true;
    }

    @Override // android.view.View
    public void setLayoutDirection(int i7) {
    }

    public final void setParentLayoutDirection(a1.k kVar) {
        this.B = kVar;
    }

    /* JADX INFO: renamed from: setPopupContentSize-fhxjrPA, reason: not valid java name */
    public final void m2setPopupContentSizefhxjrPA(a1.j jVar) {
        this.f13822C.setValue(jVar);
    }

    public final void setPositionProvider(z zVar) {
        this.f13821A = zVar;
    }

    public final void setTestTag(String str) {
        this.f13834v = str;
    }
}
