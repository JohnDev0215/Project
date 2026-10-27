package c.a.e;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import c.a.d.f;
import com.bydux.api.R;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import com.bydux.api.common.views.window.DampingScrollView;

/* JADX INFO: compiled from: CommonDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient TextView f1638b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public transient TextView f1639c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient TextView f1640d;
    public transient TextView e;
    public transient LinearLayout f;
    public transient ViewGroup g;
    public transient ViewGroup h;
    public transient String i;
    public transient String j;
    public transient int k;
    public transient int l;
    public transient String m;
    public transient String n;
    public transient View.OnClickListener o;
    public transient View.OnClickListener p;
    public View q;
    public transient Dialog r;
    public transient DampingScrollView s;
    public transient DampingScrollView t;
    public transient Context z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1637a = a.class.getSimpleName();
    public transient boolean u = true;
    public transient boolean v = true;
    public transient boolean w = true;
    public String x = "name";
    public transient int y = 200;

    /* JADX INFO: renamed from: c.a.e.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CommonDialog.java */
    public class ViewOnClickListenerC0047a implements View.OnClickListener {
        public ViewOnClickListenerC0047a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (a.this.w) {
                if (a.this.o != null) {
                    a.this.o.onClick(view);
                }
                a.this.e();
            }
        }
    }

    /* JADX INFO: compiled from: CommonDialog.java */
    public class b implements DialogInterface.OnKeyListener {
        public b() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return a.this.m(i, keyEvent);
        }
    }

    /* JADX INFO: compiled from: CommonDialog.java */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.this.f1639c.getLineCount() < 3) {
                a.this.f1639c.setGravity(17);
            } else {
                a.this.f1639c.setGravity(8388611);
            }
        }
    }

    /* JADX INFO: compiled from: CommonDialog.java */
    public class d implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View.OnClickListener f1644b;

        public d(View.OnClickListener onClickListener) {
            this.f1644b = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            View.OnClickListener onClickListener = this.f1644b;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
            if (a.this.v) {
                a.this.e();
            }
        }
    }

    /* JADX INFO: compiled from: CommonDialog.java */
    public class e implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View.OnClickListener f1646b;

        public e(View.OnClickListener onClickListener) {
            this.f1646b = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            View.OnClickListener onClickListener = this.f1646b;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
            if (a.this.v) {
                a.this.e();
            }
        }
    }

    public a(Context context) {
        this.z = context;
        this.k = context.getResources().getDimensionPixelSize(R.dimen.sp_20);
    }

    public void e() {
        Dialog dialog = this.r;
        if (dialog != null) {
            dialog.dismiss();
        }
        this.r = null;
        f.f1596a.remove(this);
    }

    public String f() {
        return this.x;
    }

    public void g() {
        Window window = this.r.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        attributes.gravity = 17;
        attributes.dimAmount = 0.0f;
        attributes.windowAnimations = R.style.byd_dialog_common_anim;
        this.r.getWindow().setAttributes(attributes);
        this.r.setCanceledOnTouchOutside(this.w);
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1792);
    }

    public void h() {
        Dialog dialog = this.r;
        if (dialog != null) {
            dialog.setOnKeyListener(new b());
        }
    }

    public void i() {
        this.f1638b = (TextView) this.r.findViewById(R.id.tv_title);
        this.f1639c = (TextView) this.r.findViewById(R.id.tv_message);
        this.f = (LinearLayout) this.r.findViewById(R.id.ll_content);
        this.f1640d = (TextView) this.r.findViewById(R.id.btn_cancel);
        this.e = (TextView) this.r.findViewById(R.id.btn_ok);
        this.g = (ViewGroup) this.r.findViewById(R.id.ll_dialog_masks);
        this.h = (ViewGroup) this.r.findViewById(R.id.ll_dialog_content);
        this.s = (DampingScrollView) this.r.findViewById(R.id.dampingScrollView_dialog);
        this.t = (DampingScrollView) this.r.findViewById(R.id.dampingScrollView_dialog2);
        this.g.setOnClickListener(new ViewOnClickListenerC0047a());
    }

    public void j(boolean z) {
        this.v = z;
    }

    public void k(boolean z) {
        this.w = z;
    }

    public boolean l() {
        Dialog dialog = this.r;
        if (dialog != null) {
            return dialog.isShowing();
        }
        return false;
    }

    public boolean m(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 1) {
            return false;
        }
        if (this.r != null) {
            e();
        }
        return true;
    }

    public void n(View.OnClickListener onClickListener) {
        this.o = onClickListener;
        TextView textView = this.f1640d;
        if (textView != null) {
            textView.setOnClickListener(new d(onClickListener));
        }
    }

    public void o(String str) {
        this.n = str;
        TextView textView = this.f1640d;
        if (textView != null) {
            textView.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
            this.f1640d.setText(str);
        }
    }

    public void p(View.OnClickListener onClickListener) {
        this.p = onClickListener;
        TextView textView = this.e;
        if (textView != null) {
            textView.setOnClickListener(new e(onClickListener));
        }
    }

    public void q(String str) {
        this.m = str;
        TextView textView = this.e;
        if (textView != null) {
            textView.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
            this.e.setText(str);
        }
    }

    public void r(String str, int i, int i2) {
        this.j = str;
        this.l = i;
        this.k = i2;
        TextView textView = this.f1639c;
        if (textView == null || this.q != null) {
            return;
        }
        textView.setText(str);
        this.f1639c.setGravity(i);
        this.f1639c.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.f1639c.setTextSize(0, i2);
        this.f1639c.post(new c());
    }

    public void s(String str) {
        this.x = str;
    }

    public void t(boolean z) {
        this.u = z;
        DampingScrollView dampingScrollView = this.s;
        if (dampingScrollView != null) {
            dampingScrollView.setScroll(z);
        }
        DampingScrollView dampingScrollView2 = this.t;
        if (dampingScrollView2 != null) {
            dampingScrollView2.setScroll(z);
        }
    }

    public void u(String str) {
        this.i = str;
        TextView textView = this.f1638b;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void v(View view) {
        ViewGroup viewGroup;
        this.q = view;
        DampingScrollView dampingScrollView = this.t;
        if (dampingScrollView != null && view != null) {
            dampingScrollView.setVisibility(0);
        }
        DampingScrollView dampingScrollView2 = this.s;
        if (dampingScrollView2 != null && view != null) {
            dampingScrollView2.setVisibility(8);
        }
        if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
            viewGroup.removeView(view);
        }
        LinearLayout linearLayout = this.f;
        if (linearLayout == null || view == null) {
            return;
        }
        linearLayout.removeAllViews();
        this.f.addView(view);
    }

    public void w() {
        try {
            Dialog dialog = new Dialog(this.z, R.style.byd_dialog);
            this.r = dialog;
            dialog.setContentView(c.a.d.c.a(R.layout.common_dialog_layout));
            i();
            h();
            g();
            u(this.i);
            r(this.j, this.l, this.k);
            v(this.q);
            q(this.m);
            o(this.n);
            p(this.p);
            n(this.o);
            t(this.u);
            this.r.show();
            this.h.animate().scaleX(1.0f).scaleY(1.0f).setDuration(this.y).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
            this.h.animate().alpha(1.0f).setDuration(this.y).setInterpolator(PathInterpolatorUtils.getStandard()).start();
            this.g.animate().alpha(1.0f).setDuration(this.y).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        } catch (Exception e2) {
            Log.e("smart_charge", e2.getMessage());
        }
    }
}
