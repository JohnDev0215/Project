package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import defpackage.p4;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.components.browser_ui.widget.R$dimen;
import org.chromium.components.browser_ui.widget.R$id;
import org.chromium.components.browser_ui.widget.R$layout;
import org.chromium.components.browser_ui.widget.R$style;

/* JADX INFO: compiled from: TextBubble.java */
/* JADX INFO: loaded from: classes2.dex */
public class u73 implements p4.c {
    public static final HashSet o = new HashSet();
    public static final aq1<Integer> p = new aq1<>();
    public final Handler e;
    public final p4 f;
    public s8 g;
    public final Runnable h;
    public final Runnable i;
    public long j;
    public final a k;
    public long l;
    public final boolean m;
    public final View n;

    /* JADX INFO: compiled from: TextBubble.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            u73 u73Var = u73.this;
            if (u73Var.f.c()) {
                u73Var.b();
            }
        }
    }

    /* JADX INFO: compiled from: TextBubble.java */
    public class b implements PopupWindow.OnDismissListener {
        public b() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public final void onDismiss() {
            HashSet hashSet = u73.o;
            hashSet.remove(u73.this);
            u73.p.r(Integer.valueOf(hashSet.size()));
        }
    }

    public u73(Context context, View view, int i, int i2, nn3 nn3Var, boolean z) {
        this(context, view, context.getString(i), context.getString(i2), true, nn3Var, null, false, z);
    }

    public static void c() {
        Iterator it = new HashSet(o).iterator();
        while (it.hasNext()) {
            ((u73) it.next()).b();
        }
    }

    @Override // p4.c
    public final void a(boolean z, int i, int i2, Rect rect) {
        int iQ;
        s8 s8Var = this.g;
        if (s8Var == null) {
            return;
        }
        if (s8Var.p) {
            int iCenterX = rect.centerX() - i;
            s8 s8Var2 = this.g;
            ShapeDrawable shapeDrawable = s8Var2.j;
            Rect rect2 = s8Var2.e;
            shapeDrawable.getPadding(rect2);
            int i3 = (s8Var2.f / 2) + s8Var2.m + rect2.left;
            s8 s8Var3 = this.g;
            ShapeDrawable shapeDrawable2 = s8Var3.j;
            Rect rect3 = s8Var3.e;
            shapeDrawable2.getPadding(rect3);
            iQ = a8.q(iCenterX, i3, i2 - ((s8Var3.f / 2) + (s8Var3.m + rect3.right)));
        } else {
            iQ = 0;
        }
        s8 s8Var4 = this.g;
        if (iQ == s8Var4.n && z == s8Var4.o) {
            return;
        }
        s8Var4.n = iQ;
        s8Var4.o = z;
        s8Var4.onBoundsChange(s8Var4.getBounds());
        s8Var4.invalidateSelf();
    }

    public void addOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.f.addOnDismissListener(onDismissListener);
    }

    public final void b() {
        this.f.a();
        if (this.j != 0) {
            oj2.b0(System.currentTimeMillis() - this.j, "InProductHelp.TextBubble.ShownTime");
            this.j = 0L;
        }
    }

    public final void d(long j) {
        if (this.m) {
            return;
        }
        this.l = j;
        Handler handler = this.e;
        a aVar = this.k;
        handler.removeCallbacks(aVar);
        if (this.f.c()) {
            long j2 = this.l;
            if (j2 != 0) {
                handler.postDelayed(aVar, j2);
            }
        }
    }

    public final void e(boolean z) {
        boolean z2 = this.m || z;
        p4 p4Var = this.f;
        p4Var.m = z2;
        p4Var.j.setOutsideTouchable(z2);
    }

    public final void f() {
        p4 p4Var = this.f;
        if (p4Var.c()) {
            return;
        }
        if (!p4Var.c()) {
            long j = this.l;
            if (j != 0) {
                this.e.postDelayed(this.k, j);
            }
        }
        p4Var.d();
        HashSet hashSet = o;
        hashSet.add(this);
        p.r(Integer.valueOf(hashSet.size()));
        this.j = System.currentTimeMillis();
    }

    public void g(TextView textView, boolean z) {
        if (z) {
            textView.setTextAppearance(textView.getContext(), R$style.TextAppearance_TextMediumThick_Accent1);
        }
    }

    public void removeOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.f.removeOnDismissListener(onDismissListener);
    }

    public void setTouchInterceptor(View.OnTouchListener onTouchListener) {
        this.f.setTouchInterceptor(onTouchListener);
    }

    public u73(Context context, View view, String str, String str2, boolean z, z92 z92Var, Drawable drawable, boolean z2, boolean z3) {
        View view2;
        this.k = new a();
        b bVar = new b();
        this.l = 0L;
        this.m = z3;
        this.h = null;
        this.i = null;
        s8 s8Var = new s8(context, z2);
        this.g = s8Var;
        s8Var.p = z;
        s8Var.invalidateSelf();
        s8 s8Var2 = this.g;
        int iB = ii2.b(context);
        s8Var2.j.setTint(iB);
        s8Var2.i.setColor(iB);
        s8Var2.invalidateSelf();
        s8 s8Var3 = this.g;
        if (drawable == null) {
            View viewInflate = LayoutInflater.from(context).inflate(R$layout.textbubble_text, (ViewGroup) null);
            TextView textView = (TextView) viewInflate.findViewById(R$id.message);
            textView.setText(z3 ? str2 : str);
            g(textView, false);
            view2 = viewInflate;
        } else {
            View viewInflate2 = LayoutInflater.from(context).inflate(R$layout.textbubble_text_with_image, (ViewGroup) null);
            ((ImageView) viewInflate2.findViewById(R$id.image)).setImageDrawable(drawable);
            TextView textView2 = (TextView) viewInflate2.findViewById(R$id.message);
            textView2.setText(z3 ? str2 : str);
            g(textView2, false);
            view2 = viewInflate2;
        }
        this.n = view2;
        view2.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        p4 p4Var = new p4(context, view, s8Var3, view2, z92Var);
        this.f = p4Var;
        p4Var.q = context.getResources().getDimensionPixelSize(R$dimen.text_bubble_margin);
        p4Var.u = 1;
        p4Var.p = this;
        this.e = new Handler();
        int i = R$style.TextBubbleAnimation;
        p4Var.A = i;
        p4Var.j.setAnimationStyle(i);
        addOnDismissListener(bVar);
        if (z3) {
            e(true);
        }
    }
}
