package defpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import defpackage.z92;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.ui.R$style;

/* JADX INFO: compiled from: AnchoredPopupWindow.java */
/* JADX INFO: loaded from: classes3.dex */
public final class p4 implements View.OnTouchListener, z92.a {
    public int A;
    public boolean B;
    public final Rect e;
    public d f;
    public final Handler g;
    public final View h;
    public final z92 i;
    public final PopupWindow j;
    public final z92 k;
    public final a l;
    public boolean m;
    public final org.chromium.base.b<PopupWindow.OnDismissListener> n;
    public View.OnTouchListener o;
    public c p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public boolean z;

    /* JADX INFO: compiled from: AnchoredPopupWindow.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            p4 p4Var = p4.this;
            if (p4Var.j.isShowing()) {
                p4Var.a();
            }
        }
    }

    /* JADX INFO: compiled from: AnchoredPopupWindow.java */
    public class b implements PopupWindow.OnDismissListener {
        public b() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public final void onDismiss() {
            p4 p4Var = p4.this;
            if (p4Var.v) {
                return;
            }
            p4Var.g.removeCallbacks(p4Var.l);
            Iterator<PopupWindow.OnDismissListener> it = p4Var.n.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    p4Var.k.b();
                    p4Var.i.b();
                    return;
                }
                ((PopupWindow.OnDismissListener) aVar.next()).onDismiss();
            }
        }
    }

    /* JADX INFO: compiled from: AnchoredPopupWindow.java */
    public interface c {
        void a(boolean z, int i, int i2, Rect rect);
    }

    /* JADX INFO: compiled from: AnchoredPopupWindow.java */
    public static class d {
        public final boolean a;
        public final boolean b;
        public final Rect c;

        public d(Rect rect, boolean z, boolean z2) {
            this.c = rect;
            this.a = z;
            this.b = z2;
        }
    }

    /* JADX INFO: compiled from: AnchoredPopupWindow.java */
    public static class e extends z92 implements View.OnLayoutChangeListener {
        public final View g;

        public e(View view) {
            this.g = view;
            view.addOnLayoutChangeListener(this);
            Rect rect = this.e;
            view.getWindowVisibleDisplayFrame(rect);
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            rect.offset(-iArr[0], -iArr[1]);
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            View view2 = this.g;
            Rect rect = this.e;
            view2.getWindowVisibleDisplayFrame(rect);
            int[] iArr = new int[2];
            view2.getLocationOnScreen(iArr);
            rect.offset(-iArr[0], -iArr[1]);
        }
    }

    public p4(Context context, View view, Drawable drawable, View view2, z92 z92Var) {
        this(context, view, drawable, view2, z92Var, null);
    }

    public final void a() {
        this.j.dismiss();
    }

    public void addOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.n.a(onDismissListener);
    }

    public final boolean b() {
        float f = this.h.getResources().getDisplayMetrics().density * 50.0f;
        return ((float) this.f.c.height()) >= f && ((float) this.f.c.width()) >= f;
    }

    public final boolean c() {
        return this.j.isShowing();
    }

    public final void d() {
        if (this.j.isShowing()) {
            return;
        }
        this.k.a(this);
        this.i.a(this);
        g();
        if (b()) {
            f();
        }
    }

    public final void f() {
        boolean z = this.B;
        PopupWindow popupWindow = this.j;
        if (z && this.A == 0) {
            d dVar = this.f;
            boolean z2 = dVar.a;
            popupWindow.setAnimationStyle(dVar.b ? z2 ? R$style.AnchoredPopupAnimEndTop : R$style.AnchoredPopupAnimEndBottom : z2 ? R$style.AnchoredPopupAnimStartTop : R$style.AnchoredPopupAnimStartBottom);
        }
        try {
            View view = this.h;
            Rect rect = this.f.c;
            popupWindow.showAtLocation(view, 8388659, rect.left, rect.top);
        } catch (WindowManager.BadTokenException unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:165:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00c1 A[PHI: r2
  0x00c1: PHI (r2v18 boolean) = (r2v17 boolean), (r2v17 boolean), (r2v20 boolean), (r2v20 boolean) binds: [B:35:0x00b2, B:36:0x00b4, B:40:0x00bb, B:41:0x00bd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0116 A[PHI: r11
  0x0116: PHI (r11v3 boolean) = (r11v2 boolean), (r11v2 boolean), (r11v9 boolean), (r11v9 boolean) binds: [B:72:0x0105, B:73:0x0107, B:77:0x010e, B:78:0x0110] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0152  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g() {
        /*
            Method dump skipped, instruction units count: 563
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.p4.g():void");
    }

    @Override // android.view.View.OnTouchListener
    @SuppressLint({"ClickableViewAccessibility"})
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        View.OnTouchListener onTouchListener = this.o;
        boolean z = onTouchListener != null && onTouchListener.onTouch(view, motionEvent);
        n3.X0(motionEvent.getAction() == 4 ? "InProductHelp.OutsideTouch" : "InProductHelp.InsideTouch");
        if (this.m) {
            if (!(!z && this.j.getContentView().dispatchTouchEvent(motionEvent))) {
                a();
            }
        }
        return z;
    }

    public void removeOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.n.c(onDismissListener);
    }

    public void setTouchInterceptor(View.OnTouchListener onTouchListener) {
        this.o = onTouchListener;
    }

    public p4(Context context, View view, Drawable drawable, View view2, z92 z92Var, sq3 sq3Var) {
        this.e = new Rect();
        this.l = new a();
        b bVar = new b();
        this.n = new org.chromium.base.b<>();
        this.t = 0;
        this.u = 0;
        View rootView = view.getRootView();
        this.h = rootView;
        this.i = sq3Var == null ? new e(rootView) : sq3Var;
        if (wh3.a == null) {
            wh3.a = new wh3();
        }
        wh3.a.getClass();
        PopupWindow popupWindow = new PopupWindow(context);
        this.j = popupWindow;
        this.g = new Handler();
        this.k = z92Var;
        this.f = new d(new Rect(), false, false);
        popupWindow.setWidth(-2);
        popupWindow.setHeight(-2);
        popupWindow.setBackgroundDrawable(drawable);
        popupWindow.setContentView(view2);
        popupWindow.setTouchInterceptor(this);
        popupWindow.setOnDismissListener(bVar);
    }
}
