package e4;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.byd.mvp.R$color;
import com.byd.mvp.R$id;
import com.byd.mvp.R$layout;
import com.byd.mvp.R$style;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import com.bydux.api.common.views.skin.listener.ISkinUpdate;
import com.bydux.api.common.views.skin.loader.SkinInflaterFactory;
import com.bydux.api.common.views.skin.loader.SkinManager;
import e4.a;
import java.lang.ref.WeakReference;
import m4.f;

/* JADX INFO: compiled from: CarDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class b implements ISkinUpdate, k4.e, k4.a, k4.c {
    public k4.c A;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Dialog f4595e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f4596f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public FrameLayout f4597g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public DialogInterface.OnDismissListener f4599i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4600j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f4601k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f4602l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f4603m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f4604n;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f4612v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f4613w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public e f4614x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public k4.e f4615y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public k4.a f4616z;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f4594d = 200;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f4605o = "CarDialog";

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f4606p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f4607q = false;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public volatile boolean f4608r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f4609s = true;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f4610t = true;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f4611u = false;
    public boolean B = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public SkinInflaterFactory f4598h = new SkinInflaterFactory();

    /* JADX INFO: compiled from: CarDialog.java */
    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f4617d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Dialog f4618e;

        public a(boolean z7, Dialog dialog) {
            this.f4617d = z7;
            this.f4618e = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            x3.a.c("CarDialog", "---showWithBackgroundOutside--onClick--cancelOutside: " + this.f4617d + ", keyboardShowing: " + b.this.B + ", Identity: " + b.this.f4604n);
            if (b.this.B) {
                m4.e.a(b.this.f4597g);
            } else if (this.f4617d) {
                this.f4618e.dismiss();
            }
        }
    }

    /* JADX INFO: renamed from: e4.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CarDialog.java */
    public class ViewOnApplyWindowInsetsListenerC0031b implements View.OnApplyWindowInsetsListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f4620a;

        public ViewOnApplyWindowInsetsListenerC0031b(b bVar) {
            this.f4620a = new WeakReference<>(bVar);
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            WeakReference<b> weakReference = this.f4620a;
            if (weakReference != null && weakReference.get() != null) {
                b bVar = this.f4620a.get();
                if (Build.VERSION.SDK_INT >= 30) {
                    int i8 = windowInsets.getInsets(WindowInsets.Type.ime()).bottom;
                    x3.a.c("CarDialog", "---onApplyWindowInsets---imeHeight: " + i8 + ", Identity: " + bVar.f4604n);
                    bVar.onKeyboardStateChanged(i8 > 90);
                }
            }
            return windowInsets;
        }
    }

    public void A(int i8) {
        this.f4600j = i8;
    }

    public void B(String str) {
        this.f4601k = str;
    }

    public void C(e eVar) {
        this.f4614x = eVar;
    }

    public void D(DialogInterface.OnDismissListener onDismissListener) {
        this.f4599i = onDismissListener;
    }

    public void E(k4.e eVar) {
        this.f4615y = eVar;
    }

    public void F(boolean z7) {
        this.f4611u = z7;
    }

    public void G(boolean z7) {
        this.f4606p = z7;
    }

    public void H(String str) {
        this.f4603m = str;
    }

    public Dialog I(Context context, View view, int i8, int i9, boolean z7) {
        this.f4607q = z7;
        return J(context, view, i8, i9);
    }

    public Dialog J(Context context, View view, int i8, int i9) {
        if (this.f4595e == null) {
            this.f4595e = new Dialog(context, R$style.dialog);
            Window window = this.f4595e.getWindow();
            window.setWindowAnimations(R$style.base_dialog_anim);
            if (window.getLayoutInflater().getFactory() == null) {
                window.getLayoutInflater().setFactory(this.f4598h);
                SkinManager.getInstance().attach(this);
            }
        }
        if (this.f4595e != null && this.f4595e.isShowing()) {
            this.f4595e.dismiss();
        }
        O(context, this.f4595e, view, i8, i9, true, -1);
        return this.f4595e;
    }

    public Dialog K(Context context, View view, int i8, int i9, boolean z7, boolean z8) {
        return R(context, view, i8, i9, z7, 2036, z8);
    }

    public Dialog L(Context context, View view, int i8, int i9, boolean z7) {
        return Q(context, view, i8, i9, z7, 2001);
    }

    public Dialog M(Context context, View view, int i8, int i9, boolean z7, boolean z8) {
        return R(context, view, i8, i9, z7, 2020, z8);
    }

    public Dialog N(Context context, View view, int i8, int i9, boolean z7) {
        return S(context, view, i8, i9, z7, 2001, true);
    }

    public final void O(Context context, Dialog dialog, View view, int i8, int i9, boolean z7, int i10) {
        P(context, dialog, view, i8, i9, z7, i10, true, false);
    }

    public final void P(Context context, Dialog dialog, View view, int i8, int i9, boolean z7, int i10, boolean z8, boolean z9) {
        if (context == null || dialog == null || view == null) {
            return;
        }
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.dialog_bg_container, (ViewGroup) null);
        this.f4597g = (FrameLayout) viewInflate.findViewById(R$id.fl_dialog_container);
        View viewFindViewById = viewInflate.findViewById(R$id.bg_car_dialog);
        this.f4596f = viewFindViewById;
        if (z9) {
            viewFindViewById.setBackgroundColor(0);
        } else {
            viewFindViewById.setBackgroundColor(context.getColor(R$color.bg_dialog_outside));
        }
        this.f4596f.setOnClickListener(new a(z7, dialog));
        if (z8) {
            r(this.f4596f, this.f4597g);
        } else {
            this.f4597g.setScaleX(1.0f);
            this.f4597g.setScaleY(1.0f);
            if (this.f4611u) {
                q(this.f4596f, this.f4597g, 0.0f);
            } else {
                q(this.f4596f, this.f4597g, 0.2f);
            }
        }
        dialog.setContentView(viewInflate);
        Window window = dialog.getWindow();
        i(context);
        window.getAttributes().setTitle(this.f4604n);
        if (i10 > 0) {
            window.setType(i10);
        }
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        Window.Callback callback = window.getCallback();
        x3.a.c("CarDialog", "---showWithBackgroundOutside---callback: " + callback + ", Identity: " + this.f4604n);
        window.setCallback(new k4.b(callback, this));
        if (this.A != null) {
            window.getDecorView().setOnApplyWindowInsetsListener(new ViewOnApplyWindowInsetsListenerC0031b(this));
        }
        dialog.show();
        window.setLayout(-1, -1);
        window.setGravity(17);
        x3.a.c("CarDialog", "---showWithBackgroundOutside---dialog showed. Identity: " + this.f4604n);
        window.setDimAmount(0.0f);
        if (z8) {
            window.setWindowAnimations(R$style.base_dialog_anim);
        }
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setNavigationBarColor(0);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(5889);
        if (z9) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.flags = 787752;
            window.setAttributes(attributes);
        }
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        if (this.f4597g.getChildCount() > 0) {
            this.f4597g.removeAllViews();
        }
        this.f4597g.addView(view, new FrameLayout.LayoutParams(i8, i9));
        this.f4612v = i8;
        this.f4613w = i9;
        a.b.a().q();
    }

    public Dialog Q(Context context, View view, int i8, int i9, boolean z7, int i10) {
        return R(context, view, i8, i9, z7, i10, true);
    }

    public Dialog R(Context context, View view, int i8, int i9, boolean z7, int i10, boolean z8) {
        if (this.f4595e == null) {
            this.f4595e = new Dialog(context, f.b());
        }
        if (this.f4595e != null && this.f4595e.isShowing()) {
            this.f4595e.dismiss();
        }
        P(context, this.f4595e, view, i8, i9, z7, i10, z8, false);
        return this.f4595e;
    }

    public Dialog S(Context context, View view, int i8, int i9, boolean z7, int i10, boolean z8) {
        if (this.f4595e == null) {
            this.f4595e = new Dialog(context, f.b());
        }
        if (this.f4595e != null && this.f4595e.isShowing()) {
            this.f4595e.dismiss();
        }
        P(context, this.f4595e, view, i8, i9, z7, i10, z8, true);
        return this.f4595e;
    }

    public void d() {
        x3.a.c("CarDialog", "---dismiss---before dismiss. Identity: " + this.f4604n);
        e eVar = this.f4614x;
        if (eVar != null) {
            eVar.a();
        }
        try {
            if (this.f4595e != null && this.f4595e.isShowing()) {
                this.f4595e.dismiss();
                x3.a.c("CarDialog", "---dismiss---after dismiss. Identity: " + this.f4604n);
            }
        } catch (RuntimeException e8) {
            x3.a.b("CarDialog", "---dismiss---e = " + e8.toString());
        }
        x3.a.c("CarDialog", "---dismiss---dismiss completed. Identity: " + this.f4604n);
        FrameLayout frameLayout = this.f4597g;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
        e eVar2 = this.f4614x;
        if (eVar2 != null) {
            eVar2.onDialogDismiss();
        }
        this.f4597g = null;
        this.f4596f = null;
        this.f4595e = null;
        SkinManager.getInstance().detach(this);
        SkinInflaterFactory skinInflaterFactory = this.f4598h;
        if (skinInflaterFactory != null) {
            skinInflaterFactory.clean();
        }
    }

    public String e() {
        return this.f4602l;
    }

    public int f() {
        return this.f4600j;
    }

    public String g() {
        return this.f4601k;
    }

    public String h() {
        return this.f4603m;
    }

    public final void i(Context context) {
        StringBuilder sb = new StringBuilder();
        if (context != null) {
            sb.append(context.getPackageName());
            sb.append("/");
        }
        sb.append(this.f4603m);
        sb.append("-->");
        sb.append(this.f4605o);
        this.f4604n = sb.toString();
    }

    public boolean j() {
        return this.f4610t;
    }

    public boolean k() {
        return this.f4608r;
    }

    public boolean l() {
        return this.f4607q;
    }

    public boolean m() {
        return this.f4609s;
    }

    public boolean n() {
        return this.f4611u;
    }

    public boolean o() {
        return this.f4606p;
    }

    @Override // k4.a, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        x3.a.c("CarDialog", "---onAnimationEnd---Identity: " + this.f4604n);
        k4.a aVar = this.f4616z;
        if (aVar != null) {
            aVar.onAnimationEnd(animator);
        }
    }

    @Override // k4.a, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        x3.a.c("CarDialog", "---onAnimationStart---Identity: " + this.f4604n);
        k4.a aVar = this.f4616z;
        if (aVar != null) {
            aVar.onAnimationStart(animator);
        }
    }

    @Override // k4.e, android.view.Window.Callback
    public void onAttachedToWindow() {
        this.f4608r = false;
        x3.a.c("CarDialog", "---onAttachedToWindow---clazzName: " + this.f4602l + ", Identity: " + this.f4604n);
        View viewFindFocus = this.f4595e != null ? this.f4595e.getWindow().getDecorView().findFocus() : null;
        x3.a.c("CarDialog", "---onAttachedToWindow---focusView: " + viewFindFocus);
        if (viewFindFocus == null) {
            p(this.f4597g);
        }
        k4.e eVar = this.f4615y;
        if (eVar != null) {
            eVar.onAttachedToWindow();
        }
    }

    @Override // k4.e, android.view.Window.Callback
    public void onDetachedFromWindow() {
        x3.a.c("CarDialog", "---onDetachedFromWindow---clazzName: " + this.f4602l + ", Identity: " + this.f4604n);
        k4.e eVar = this.f4615y;
        if (eVar != null) {
            eVar.onDetachedFromWindow();
        }
        this.f4608r = true;
        e eVar2 = this.f4614x;
        if (eVar2 != null) {
            eVar2.onDialogDismiss();
        }
        DialogInterface.OnDismissListener onDismissListener = this.f4599i;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(this.f4595e);
        }
        this.f4614x = null;
        this.f4615y = null;
        this.f4616z = null;
        this.A = null;
    }

    @Override // k4.c
    public void onKeyboardStateChanged(boolean z7) {
        x3.a.c("CarDialog", "---onKeyboardStateChanged---showing: " + z7 + ", Identity: " + this.f4604n);
        this.B = z7;
        k4.c cVar = this.A;
        if (cVar != null) {
            cVar.onKeyboardStateChanged(z7);
        }
    }

    @Override // com.bydux.api.common.views.skin.listener.ISkinUpdate
    public void onSkinUpdate() {
        SkinInflaterFactory skinInflaterFactory = this.f4598h;
        if (skinInflaterFactory != null) {
            skinInflaterFactory.applySkin();
        }
    }

    @Override // k4.e, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z7) {
        x3.a.c("CarDialog", "---onDialogWindowFocusChanged---hasFocus: " + z7 + ", Identity: " + this.f4604n);
        k4.e eVar = this.f4615y;
        if (eVar != null) {
            eVar.onWindowFocusChanged(z7);
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

    public void q(View view, View view2, float f8) {
        view.setAlpha(f8);
        view2.setAlpha(f8);
        view.animate().alpha(1.0f).setDuration(this.f4594d).start();
        view2.animate().setListener(this);
        view2.animate().alpha(1.0f).setDuration(this.f4594d).start();
    }

    public void r(View view, View view2) {
        view.animate().alpha(1.0f).setDuration(this.f4594d).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        view2.animate().scaleX(1.0f).scaleY(1.0f).setDuration(this.f4594d).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        view2.animate().alpha(1.0f).setDuration(this.f4594d).setInterpolator(PathInterpolatorUtils.getStandard()).start();
    }

    public void s(long j8) {
        this.f4594d = j8;
    }

    public void t(k4.a aVar) {
        this.f4616z = aVar;
    }

    public String toString() {
        return "CarDialog{mDialog=" + this.f4595e + ", dialogTag=" + this.f4605o + ", detached=" + this.f4608r + ", level=" + this.f4600j + ", mWidth=" + this.f4612v + ", mHeight=" + this.f4613w + ", clazzName='" + this.f4602l + "', subClazzName='" + this.f4603m + "', method='" + this.f4601k + "', staticMethod='" + this.f4606p + "'}";
    }

    public void u(int i8) {
        View view = this.f4596f;
        if (view == null) {
            return;
        }
        try {
            this.f4596f.setBackgroundColor(view.getResources().getColor(i8));
        } catch (RuntimeException unused) {
        }
    }

    public void v(boolean z7) {
        this.f4610t = z7;
    }

    public void w(String str) {
        this.f4602l = str;
    }

    public void x(String str) {
        this.f4605o = str;
    }

    public void y(boolean z7) {
        this.f4607q = z7;
    }

    public void z(k4.c cVar) {
        this.A = cVar;
    }
}
