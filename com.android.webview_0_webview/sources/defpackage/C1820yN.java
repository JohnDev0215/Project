package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.PopupWindow;

/* JADX INFO: renamed from: yN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1820yN implements InterfaceC1655vN {
    public final View a;
    public final Context b;
    public boolean c;
    public int d = -1;
    public View.OnLayoutChangeListener e;
    public PopupWindow.OnDismissListener f;
    public CharSequence g;
    public ListPopupWindow h;
    public View i;
    public ListAdapter j;

    public C1820yN(Context context, View view) {
        this.h = new ListPopupWindow(context, null, 0, AbstractC0622cn.a3);
        this.a = view;
        view.setId(AbstractC0371Um.b1);
        this.a.setTag(this);
        this.b = context;
        ViewOnLayoutChangeListenerC1710wN viewOnLayoutChangeListenerC1710wN = new ViewOnLayoutChangeListenerC1710wN(this);
        this.e = viewOnLayoutChangeListenerC1710wN;
        this.a.addOnLayoutChangeListener(viewOnLayoutChangeListenerC1710wN);
        this.h.setOnDismissListener(new C1765xN(this));
        this.h.setAnchorView(this.a);
        Rect rect = new Rect();
        this.h.getBackground().getPadding(rect);
        this.h.setVerticalOffset(-rect.top);
    }

    @Override // defpackage.InterfaceC1655vN
    public void a() {
        this.h.setInputMethodMode(1);
        int iA = BN.a(this.j);
        View view = this.i;
        if (view != null) {
            if (view.getLayoutParams() == null) {
                this.i.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            this.i.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            iA = Math.max(this.i.getMeasuredWidth(), iA);
        }
        float f = this.a.getLayoutParams().width;
        this.h.getBackground().getPadding(new Rect());
        if (r4.left + iA + r4.right > f) {
            this.h.setContentWidth(iA);
            Rect rect = new Rect();
            this.a.getWindowVisibleDisplayFrame(rect);
            if (this.h.getWidth() > rect.width()) {
                this.h.setWidth(rect.width());
            }
        } else {
            this.h.setWidth(-2);
        }
        boolean zIsShowing = this.h.isShowing();
        this.h.show();
        this.h.getListView().setDividerHeight(0);
        this.h.getListView().setLayoutDirection(this.c ? 1 : 0);
        if (!zIsShowing) {
            this.h.getListView().setContentDescription(this.g);
            this.h.getListView().sendAccessibilityEvent(32);
        }
        if (this.d >= 0) {
            this.h.getListView().setSelection(this.d);
            this.d = -1;
        }
    }

    @Override // defpackage.InterfaceC1655vN
    public void b(int i) {
        this.d = i;
    }

    @Override // defpackage.InterfaceC1655vN
    public void c() {
        try {
            ListPopupWindow.class.getMethod("setForceIgnoreOutsideTouch", Boolean.TYPE).invoke(this.h, Boolean.TRUE);
        } catch (Exception e) {
            Ts.a("AutofillPopup", "ListPopupWindow.setForceIgnoreOutsideTouch not found", e);
        }
    }

    @Override // defpackage.InterfaceC1655vN
    public void d(CharSequence charSequence) {
        this.g = charSequence;
    }

    @Override // defpackage.InterfaceC1655vN
    public void dismiss() {
        this.h.dismiss();
    }

    @Override // defpackage.InterfaceC1655vN
    public void e() {
        this.h.postShow();
    }

    @Override // defpackage.InterfaceC1655vN
    public void f(AdapterView.OnItemClickListener onItemClickListener) {
        this.h.setOnItemClickListener(onItemClickListener);
    }

    @Override // defpackage.InterfaceC1655vN
    public void g(boolean z) {
        this.c = z;
    }

    @Override // defpackage.InterfaceC1655vN
    public boolean h() {
        return this.h.isShowing();
    }

    @Override // defpackage.InterfaceC1655vN
    public ListView i() {
        return this.h.getListView();
    }

    @Override // defpackage.InterfaceC1655vN
    public void j(PopupWindow.OnDismissListener onDismissListener) {
        this.f = onDismissListener;
    }

    @Override // defpackage.InterfaceC1655vN
    public void k(View view) {
        this.h.setPromptPosition(1);
        if (view != null) {
            view.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            View viewInflate = LayoutInflater.from(this.b).inflate(AbstractC0425Xm.f0, (ViewGroup) null);
            this.i = viewInflate;
            ((FrameLayout) viewInflate.findViewById(AbstractC0371Um.W0)).addView(view);
        } else {
            this.i = null;
        }
        this.h.setPromptView(this.i);
    }

    @Override // defpackage.InterfaceC1655vN
    public void l(ListAdapter listAdapter) {
        this.j = listAdapter;
        this.h.setAdapter(listAdapter);
    }
}
