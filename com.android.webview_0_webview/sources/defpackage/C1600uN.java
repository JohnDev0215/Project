package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

/* JADX INFO: renamed from: uN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1600uN implements InterfaceC1163mP, InterfaceC1655vN {
    public final Context a;
    public final View b;
    public boolean c;
    public int d = -1;
    public View.OnLayoutChangeListener e;
    public CharSequence f;
    public ViewOnTouchListenerC1218nP g;
    public ListAdapter h;
    public final LinearLayout i;
    public final ListView j;
    public final FrameLayout k;
    public Drawable l;
    public int m;

    public C1600uN(Context context, View view) {
        this.a = context;
        this.b = view;
        view.setId(AbstractC0371Um.b1);
        this.b.setTag(this);
        ViewOnLayoutChangeListenerC1490sN viewOnLayoutChangeListenerC1490sN = new ViewOnLayoutChangeListenerC1490sN(this);
        this.e = viewOnLayoutChangeListenerC1490sN;
        this.b.addOnLayoutChangeListener(viewOnLayoutChangeListenerC1490sN);
        C1545tN c1545tN = new C1545tN(this);
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(AbstractC0425Xm.h0, (ViewGroup) null);
        this.i = linearLayout;
        this.j = (ListView) linearLayout.findViewById(AbstractC0371Um.V0);
        this.k = (FrameLayout) this.i.findViewById(AbstractC0371Um.W0);
        ViewTreeObserverOnGlobalLayoutListenerC1712wP viewTreeObserverOnGlobalLayoutListenerC1712wP = new ViewTreeObserverOnGlobalLayoutListenerC1712wP(this.b);
        viewTreeObserverOnGlobalLayoutListenerC1712wP.y = true;
        Drawable drawableC = AbstractC1623us.c(context.getResources(), AbstractC0317Rm.Z1);
        this.l = drawableC;
        ViewOnTouchListenerC1218nP viewOnTouchListenerC1218nP = new ViewOnTouchListenerC1218nP(context, this.b, drawableC, this.i, viewTreeObserverOnGlobalLayoutListenerC1712wP);
        this.g = viewOnTouchListenerC1218nP;
        viewOnTouchListenerC1218nP.k.f(c1545tN);
        ViewOnTouchListenerC1218nP viewOnTouchListenerC1218nP2 = this.g;
        viewOnTouchListenerC1218nP2.l = this;
        viewOnTouchListenerC1218nP2.f.setElevation(context.getResources().getDimensionPixelSize(AbstractC0299Qm.y2));
        Rect rect = new Rect();
        this.l.getPadding(rect);
        viewTreeObserverOnGlobalLayoutListenerC1712wP.v.set(0, rect.bottom, 0, rect.top);
        viewTreeObserverOnGlobalLayoutListenerC1712wP.b();
        this.m = rect.right + rect.left;
        ViewOnTouchListenerC1218nP viewOnTouchListenerC1218nP3 = this.g;
        viewOnTouchListenerC1218nP3.s = 1;
        viewOnTouchListenerC1218nP3.y = true;
        viewOnTouchListenerC1218nP3.f.setOutsideTouchable(true);
    }

    @Override // defpackage.InterfaceC1655vN
    public void a() {
        boolean zIsShowing = this.g.f.isShowing();
        ViewOnTouchListenerC1218nP viewOnTouchListenerC1218nP = this.g;
        viewOnTouchListenerC1218nP.w = false;
        viewOnTouchListenerC1218nP.x = true;
        int i = this.a.getResources().getDisplayMetrics().widthPixels;
        int iA = BN.a(this.h);
        if (this.k.getChildCount() > 0) {
            if (this.k.getLayoutParams() == null) {
                this.k.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            }
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            this.k.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            iA = Math.max(this.k.getMeasuredWidth(), iA);
        }
        int i2 = this.m;
        if (i < iA + i2) {
            this.g.q = i - i2;
        } else if (this.b.getWidth() < iA) {
            this.g.q = iA + this.m;
        } else {
            this.g.q = this.b.getWidth() + this.m;
        }
        this.g.a();
        this.j.setDividerHeight(0);
        this.j.setLayoutDirection(this.c ? 1 : 0);
        if (!zIsShowing) {
            this.j.setContentDescription(this.f);
            this.j.sendAccessibilityEvent(32);
        }
        int i3 = this.d;
        if (i3 >= 0) {
            this.j.setSelection(i3);
            this.d = -1;
        }
    }

    @Override // defpackage.InterfaceC1655vN
    public void b(int i) {
        this.d = i;
    }

    @Override // defpackage.InterfaceC1655vN
    public void c() {
        ViewOnTouchListenerC1218nP viewOnTouchListenerC1218nP = this.g;
        viewOnTouchListenerC1218nP.j = false;
        viewOnTouchListenerC1218nP.f.setOutsideTouchable(false);
    }

    @Override // defpackage.InterfaceC1655vN
    public void d(CharSequence charSequence) {
        this.f = charSequence;
    }

    @Override // defpackage.InterfaceC1655vN
    public void dismiss() {
        this.g.f.dismiss();
    }

    @Override // defpackage.InterfaceC1655vN
    public void e() {
        this.g.a();
    }

    @Override // defpackage.InterfaceC1655vN
    public void f(AdapterView.OnItemClickListener onItemClickListener) {
        this.j.setOnItemClickListener(onItemClickListener);
    }

    @Override // defpackage.InterfaceC1655vN
    public void g(boolean z) {
        this.c = z;
    }

    @Override // defpackage.InterfaceC1655vN
    public boolean h() {
        return this.g.f.isShowing();
    }

    @Override // defpackage.InterfaceC1655vN
    public ListView i() {
        return this.j;
    }

    @Override // defpackage.InterfaceC1655vN
    public void j(PopupWindow.OnDismissListener onDismissListener) {
        this.g.k.f(onDismissListener);
    }

    @Override // defpackage.InterfaceC1655vN
    public void k(View view) {
        boolean z = view != null;
        this.i.findViewById(AbstractC0371Um.U0).setVisibility(z ? 0 : 8);
        this.k.removeAllViews();
        if (z) {
            this.k.addView(view);
        }
    }

    @Override // defpackage.InterfaceC1655vN
    public void l(ListAdapter listAdapter) {
        this.h = listAdapter;
        this.j.setAdapter(listAdapter);
        this.g.b();
    }
}
