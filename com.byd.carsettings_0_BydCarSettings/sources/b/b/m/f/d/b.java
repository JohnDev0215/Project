package b.b.m.f.d;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.appcompat.app.AlertDialog;
import b.b.m.f.d.a;
import b.b.m.g.h;
import com.byd.mvp.R$color;
import com.byd.mvp.R$id;
import com.byd.mvp.R$layout;
import com.byd.mvp.R$style;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import com.bydux.api.common.views.skin.listener.ISkinUpdate;
import com.bydux.api.common.views.skin.loader.SkinInflaterFactory;
import com.bydux.api.common.views.skin.loader.SkinManager;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: CarDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class b implements ISkinUpdate {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile Dialog f2129b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f2130c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public FrameLayout f2131d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile View f2132e;
    public DialogInterface.OnDismissListener g;
    public int h;
    public String i;
    public String j;
    public String k;
    public String l;
    public int s;
    public int t;
    public f u;
    public ViewOnAttachStateChangeListenerC0087b v;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2128a = 200;
    public String m = "CarDialog";
    public boolean n = false;
    public boolean o = false;
    public volatile boolean p = false;
    public boolean q = true;
    public boolean r = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public SkinInflaterFactory f2133f = new SkinInflaterFactory();

    /* JADX INFO: compiled from: CarDialog.java */
    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f2134a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Dialog f2135b;

        public a(boolean z, Dialog dialog) {
            this.f2134a = z;
            this.f2135b = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f2134a) {
                this.f2135b.dismiss();
            }
        }
    }

    /* JADX INFO: renamed from: b.b.m.f.d.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CarDialog.java */
    public class ViewOnAttachStateChangeListenerC0087b implements View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f2137a;

        public ViewOnAttachStateChangeListenerC0087b(b bVar) {
            this.f2137a = new WeakReference<>(bVar);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            b.b.m.c.a.a("CarDialog", "---onViewAttachedToWindow--");
            WeakReference<b> weakReference = this.f2137a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f2137a.get().n();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            b.b.m.c.a.a("CarDialog", "---onViewDetachedFromWindow--");
            WeakReference<b> weakReference = this.f2137a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f2137a.get().o();
        }
    }

    public Dialog A(Context context, View view, int i, int i2) {
        return B(context, view, i, i2);
    }

    public Dialog B(Context context, View view, int i, int i2) {
        if (this.f2129b == null) {
            this.f2129b = new Dialog(context, R$style.dialog);
            Window window = this.f2129b.getWindow();
            window.setWindowAnimations(R$style.base_dialog_anim);
            if (window.getLayoutInflater().getFactory() == null) {
                window.getLayoutInflater().setFactory(this.f2133f);
                SkinManager.getInstance().attach(this);
            }
        }
        if (this.f2129b != null && this.f2129b.isShowing()) {
            this.f2129b.dismiss();
        }
        G(context, this.f2129b, view, i, i2, true, -1);
        return this.f2129b;
    }

    public void C(Activity activity, View view, int i, int i2, boolean z) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, h.d());
        builder.setView(view);
        this.f2129b = builder.create();
        this.f2129b.getWindow().setWindowAnimations(R$style.base_dialog_anim);
        this.f2129b.setCanceledOnTouchOutside(true);
        this.f2129b.setCancelable(true);
        if (this.f2129b != null && this.f2129b.isShowing()) {
            this.f2129b.dismiss();
        }
        if (this.v == null) {
            this.v = new ViewOnAttachStateChangeListenerC0087b(this);
        }
        view.addOnAttachStateChangeListener(this.v);
        this.f2129b.show();
        this.s = i;
        this.t = i2;
    }

    public Dialog D(Context context, View view, int i, int i2, int i3, boolean z) {
        if (this.f2129b == null) {
            this.f2129b = new e(context, h.c());
        }
        if (this.f2129b != null && this.f2129b.isShowing()) {
            this.f2129b.dismiss();
        }
        H(context, this.f2129b, view, i, i2, false, i3, z, false);
        return this.f2129b;
    }

    public Dialog E(Context context, View view, int i, int i2, boolean z) {
        return I(context, view, i, i2, z, 2001);
    }

    public Dialog F(Context context, View view, int i, int i2) {
        return D(context, view, i, i2, 2001, true);
    }

    public final void G(Context context, Dialog dialog, View view, int i, int i2, boolean z, int i3) {
        H(context, dialog, view, i, i2, z, i3, true, false);
    }

    public final void H(Context context, Dialog dialog, View view, int i, int i2, boolean z, int i3, boolean z2, boolean z3) {
        if (context == null || dialog == null || view == null) {
            return;
        }
        p(view);
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.dialog_bg_container, (ViewGroup) null);
        this.f2131d = (FrameLayout) viewInflate.findViewById(R$id.fl_dialog_container);
        View viewFindViewById = viewInflate.findViewById(R$id.bg_car_dialog);
        this.f2130c = viewFindViewById;
        if (z3) {
            viewFindViewById.setBackgroundColor(0);
        } else {
            viewFindViewById.setBackgroundColor(context.getColor(R$color.bg_dialog_outside));
        }
        this.f2130c.setOnClickListener(new a(z, dialog));
        if (this.v == null) {
            this.v = new ViewOnAttachStateChangeListenerC0087b(this);
        }
        this.f2131d.addOnAttachStateChangeListener(this.v);
        if (z2) {
            q(this.f2130c, this.f2131d);
        } else {
            this.f2130c.setAlpha(1.0f);
            this.f2131d.setAlpha(1.0f);
            this.f2131d.setScaleX(1.0f);
            this.f2131d.setScaleY(1.0f);
        }
        dialog.setContentView(viewInflate);
        Window window = dialog.getWindow();
        h(context);
        window.getAttributes().setTitle(this.l);
        if (i3 > 0) {
            window.setType(i3);
        }
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
        window.setLayout(-1, -1);
        window.setGravity(17);
        b.b.m.c.a.d("CarDialog", "---showWithBackgroundOutside---dialog showed. Identity: " + this.l);
        window.setDimAmount(0.0f);
        if (z2) {
            window.setWindowAnimations(R$style.base_dialog_anim);
        }
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setNavigationBarColor(0);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(5889);
        if (z3) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.flags = 787752;
            window.setAttributes(attributes);
        }
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        if (this.f2131d.getChildCount() > 0) {
            this.f2131d.removeAllViews();
        }
        this.f2131d.addView(view, new FrameLayout.LayoutParams(i, i2));
        this.f2132e = window.getDecorView().findFocus();
        this.s = i;
        this.t = i2;
        a.b.a().p();
    }

    public Dialog I(Context context, View view, int i, int i2, boolean z, int i3) {
        return J(context, view, i, i2, z, i3, true);
    }

    public Dialog J(Context context, View view, int i, int i2, boolean z, int i3, boolean z2) {
        if (this.f2129b == null) {
            this.f2129b = new Dialog(context, h.c());
        }
        if (this.f2129b != null && this.f2129b.isShowing()) {
            this.f2129b.dismiss();
        }
        H(context, this.f2129b, view, i, i2, z, i3, z2, false);
        return this.f2129b;
    }

    public void c() {
        b.b.m.c.a.d("CarDialog", "---dismiss---before dismiss. Identity: " + this.l);
        try {
            if (this.f2129b != null && this.f2129b.isShowing()) {
                this.f2129b.dismiss();
                b.b.m.c.a.d("CarDialog", "---dismiss---after dismiss. Identity: " + this.l);
            }
        } catch (RuntimeException e2) {
            b.b.m.c.a.c("CarDialog", "---dismiss---e = " + e2.toString());
        }
        b.b.m.c.a.d("CarDialog", "---dismiss---dismiss completed. Identity: " + this.l);
        FrameLayout frameLayout = this.f2131d;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
            ViewOnAttachStateChangeListenerC0087b viewOnAttachStateChangeListenerC0087b = this.v;
            if (viewOnAttachStateChangeListenerC0087b != null) {
                this.f2131d.removeOnAttachStateChangeListener(viewOnAttachStateChangeListenerC0087b);
                this.v = null;
            }
        }
        f fVar = this.u;
        if (fVar != null) {
            fVar.onDialogDismiss();
        }
        this.f2131d = null;
        this.f2130c = null;
        this.f2129b = null;
        SkinManager.getInstance().detach(this);
        SkinInflaterFactory skinInflaterFactory = this.f2133f;
        if (skinInflaterFactory != null) {
            skinInflaterFactory.clean();
        }
    }

    public String d() {
        return this.j;
    }

    public int e() {
        return this.h;
    }

    public String f() {
        return this.i;
    }

    public String g() {
        return this.k;
    }

    public final void h(Context context) {
        StringBuilder sb = new StringBuilder();
        if (context != null) {
            sb.append(context.getPackageName());
            sb.append("/");
        }
        sb.append(this.k);
        sb.append("-->");
        sb.append(this.m);
        this.l = sb.toString();
    }

    public boolean i() {
        return this.r;
    }

    public boolean j() {
        return this.p;
    }

    public boolean k() {
        return this.o;
    }

    public boolean l() {
        return this.q;
    }

    public boolean m() {
        return this.n;
    }

    public final void n() {
        this.p = false;
        b.b.m.c.a.d("CarDialog", "---onAttachedToWindow---clazzName: " + this.j + ", Identity: " + this.l);
        if (this.f2132e == null) {
            p(this.f2131d);
            if (this.f2129b != null) {
                this.f2132e = this.f2129b.getWindow().getDecorView().findFocus();
            }
            b.b.m.c.a.c("CarDialog", "---onAttachedToWindow--1--focusView: " + this.f2132e);
        }
    }

    public final void o() {
        b.b.m.c.a.d("CarDialog", "---onDetachedFromWindow--Identity: " + this.l);
        this.p = true;
        f fVar = this.u;
        if (fVar != null) {
            fVar.onDialogDismiss();
        }
        DialogInterface.OnDismissListener onDismissListener = this.g;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(this.f2129b);
        }
        this.u = null;
        this.f2132e = null;
    }

    @Override // com.bydux.api.common.views.skin.listener.ISkinUpdate
    public void onSkinUpdate() {
        SkinInflaterFactory skinInflaterFactory = this.f2133f;
        if (skinInflaterFactory != null) {
            skinInflaterFactory.applySkin();
        }
    }

    public final void p(View view) {
        if (view == null) {
            return;
        }
        view.setEnabled(true);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.requestFocusFromTouch();
    }

    public void q(View view, View view2) {
        view.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        view2.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        view2.animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getStandard()).start();
    }

    public void r(boolean z) {
        this.r = z;
    }

    public void s(String str) {
        this.j = str;
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.g = onDismissListener;
    }

    public void t(String str) {
        this.m = str;
    }

    public String toString() {
        return "CarDialog{mDialog=" + this.f2129b + ", dialogTag=" + this.m + ", detached=" + this.p + ", level=" + this.h + ", mWidth=" + this.s + ", mHeight=" + this.t + ", clazzName='" + this.j + "', subClazzName='" + this.k + "', method='" + this.i + "', staticMethod='" + this.n + "'}";
    }

    public void u(boolean z) {
        this.o = z;
    }

    public void v(int i) {
        this.h = i;
    }

    public void w(String str) {
        this.i = str;
    }

    public void x(f fVar) {
        this.u = fVar;
    }

    public void y(boolean z) {
        this.n = z;
    }

    public void z(String str) {
        this.k = str;
    }
}
