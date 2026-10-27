package b.b.g.q.d;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import com.byd.carsettings.R;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import com.bydux.api.common.views.ctrl.BydTimer;
import com.bydux.api.common.views.window.DampingScrollView;

/* JADX INFO: compiled from: CommonDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class l implements b.b.o.b.a.a {
    public Context D;
    public BydTimer E;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f1466b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f1467c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f1468d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f1469e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public LinearLayout f1470f;
    public LinearLayout g;
    public ViewGroup h;
    public ViewGroup i;
    public String j;
    public String k;
    public int l;
    public int m;
    public String n;
    public String o;
    public View.OnClickListener p;
    public View.OnClickListener q;
    public View.OnClickListener r;
    public View s;
    public View t;
    public Dialog u;
    public DampingScrollView v;
    public DampingScrollView w;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1465a = l.class.getSimpleName();
    public boolean x = true;
    public boolean y = true;
    public boolean z = true;
    public String A = "name";
    public int B = 200;

    /* JADX INFO: compiled from: CommonDialog.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (l.this.z) {
                if (l.this.p != null) {
                    l.this.p.onClick(view);
                }
                l.this.f();
            }
        }
    }

    /* JADX INFO: compiled from: CommonDialog.java */
    public class b implements DialogInterface.OnKeyListener {
        public b() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return l.this.q(i, keyEvent);
        }
    }

    /* JADX INFO: compiled from: CommonDialog.java */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (l.this.f1467c.getLineCount() < 3) {
                l.this.f1467c.setGravity(17);
            } else {
                l.this.f1467c.setGravity(GravityCompat.START);
            }
        }
    }

    /* JADX INFO: compiled from: CommonDialog.java */
    public class d implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View.OnClickListener f1474a;

        public d(View.OnClickListener onClickListener) {
            this.f1474a = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            View.OnClickListener onClickListener = this.f1474a;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
            if (l.this.y) {
                l.this.f();
            }
        }
    }

    /* JADX INFO: compiled from: CommonDialog.java */
    public class e implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View.OnClickListener f1476a;

        public e(View.OnClickListener onClickListener) {
            this.f1476a = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            View.OnClickListener onClickListener = this.f1476a;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
            if (l.this.y) {
                l.this.f();
            }
        }
    }

    /* JADX INFO: compiled from: CommonDialog.java */
    public class f implements BydTimer.BydTimerListener {
        public f() {
        }

        @Override // com.bydux.api.common.views.ctrl.BydTimer.BydTimerListener
        public void onTimeUp(View view) {
            l.this.f();
        }
    }

    public l(@NonNull Context context) {
        this.D = context;
        this.l = context.getResources().getDimensionPixelSize(R.dimen.sp_20);
    }

    public void A(View view) {
        ViewGroup viewGroup;
        this.s = view;
        if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
            viewGroup.removeView(view);
        }
        LinearLayout linearLayout = this.f1470f;
        if (linearLayout == null || view == null) {
            return;
        }
        linearLayout.removeAllViews();
        this.f1470f.addView(view);
        DampingScrollView dampingScrollView = this.w;
        if (dampingScrollView != null) {
            dampingScrollView.setVisibility(8);
        }
        DampingScrollView dampingScrollView2 = this.v;
        if (dampingScrollView2 != null) {
            dampingScrollView2.setVisibility(0);
        }
    }

    public void B(View view) {
        ViewGroup viewGroup;
        this.t = view;
        if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
            viewGroup.removeView(view);
        }
        LinearLayout linearLayout = this.g;
        if (linearLayout == null || view == null) {
            return;
        }
        linearLayout.removeAllViews();
        this.g.addView(view);
    }

    public void C() {
        try {
            Dialog dialog = new Dialog(this.D, R.style.byd_dialog);
            this.u = dialog;
            dialog.setContentView(b.b.o.b.b.a.a(this));
            l();
            k();
            j();
            z(this.j);
            w(this.k, this.m, this.l);
            A(this.s);
            t(this.n);
            r(this.o);
            setBtnOKListener(this.q);
            setBtnCancelListener(this.p);
            y(this.x);
            this.u.show();
            this.i.animate().scaleX(1.0f).scaleY(1.0f).setDuration(this.B).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
            this.i.animate().alpha(1.0f).setDuration(this.B).setInterpolator(PathInterpolatorUtils.getStandard()).start();
            this.h.animate().alpha(1.0f).setDuration(this.B).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        } catch (Exception e2) {
            b.b.m.c.a.c(this.f1465a, "[CommonDialog]----" + e2.getMessage());
        }
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.common_dialog_layout;
    }

    public void f() {
        BydTimer bydTimer = this.E;
        if (bydTimer != null) {
            bydTimer.stopTimer();
        }
        View.OnClickListener onClickListener = this.r;
        if (onClickListener != null) {
            onClickListener.onClick(null);
        }
        Dialog dialog = this.u;
        if (dialog != null) {
            dialog.dismiss();
        }
        this.u = null;
        m.f1479a.remove(this);
    }

    public String g() {
        return this.A;
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.common_dialog_layout;
    }

    public View i() {
        return this.s;
    }

    public void j() {
        Window window = this.u.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        attributes.gravity = 17;
        attributes.dimAmount = 0.0f;
        attributes.windowAnimations = R.style.byd_dialog_common_anim;
        this.u.getWindow().setAttributes(attributes);
        this.u.setCanceledOnTouchOutside(this.z);
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1792);
    }

    public void k() {
        Dialog dialog = this.u;
        if (dialog != null) {
            dialog.setOnKeyListener(new b());
        }
    }

    public void l() {
        this.f1466b = (TextView) this.u.findViewById(R.id.tv_title);
        this.f1467c = (TextView) this.u.findViewById(R.id.tv_message);
        this.f1470f = (LinearLayout) this.u.findViewById(R.id.ll_content);
        this.g = (LinearLayout) this.u.findViewById(R.id.ll_foot);
        this.f1468d = (TextView) this.u.findViewById(R.id.btn_cancel);
        this.f1469e = (TextView) this.u.findViewById(R.id.btn_ok);
        this.h = (ViewGroup) this.u.findViewById(R.id.ll_dialog_masks);
        this.i = (ViewGroup) this.u.findViewById(R.id.ll_dialog_content);
        this.v = (DampingScrollView) this.u.findViewById(R.id.dampingScrollView_dialog);
        this.w = (DampingScrollView) this.u.findViewById(R.id.dampingScrollView_dialog_text);
        this.h.setSoundEffectsEnabled(true);
        this.h.setOnClickListener(new a());
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.common_dialog_layout_byd;
    }

    public void n(boolean z) {
        this.y = z;
    }

    public void o(boolean z) {
        this.z = z;
    }

    public boolean p() {
        Dialog dialog = this.u;
        if (dialog != null) {
            return dialog.isShowing();
        }
        return false;
    }

    public boolean q(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 1) {
            return false;
        }
        View.OnClickListener onClickListener = this.p;
        if (onClickListener != null) {
            onClickListener.onClick(this.f1468d);
        }
        if (this.u != null) {
            f();
        }
        return true;
    }

    public void r(String str) {
        this.o = str;
        TextView textView = this.f1468d;
        if (textView != null) {
            textView.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
            this.f1468d.setText(str);
        }
    }

    public void s(boolean z) {
        TextView textView = this.f1469e;
        if (textView != null) {
            textView.setEnabled(z);
        }
    }

    public void setBtnCancelListener(View.OnClickListener onClickListener) {
        this.p = onClickListener;
        TextView textView = this.f1468d;
        if (textView != null) {
            textView.setOnClickListener(new d(onClickListener));
        }
    }

    public void setBtnOKListener(View.OnClickListener onClickListener) {
        this.q = onClickListener;
        TextView textView = this.f1469e;
        if (textView != null) {
            textView.setOnClickListener(new e(onClickListener));
        }
    }

    public void setDismissListener(View.OnClickListener onClickListener) {
        this.r = onClickListener;
    }

    public void t(String str) {
        this.n = str;
        TextView textView = this.f1469e;
        if (textView != null) {
            textView.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
            this.f1469e.setText(str);
        }
    }

    public void u(boolean z) {
        Dialog dialog = this.u;
        if (dialog != null) {
            dialog.setCancelable(z);
        }
    }

    public void v(long j) {
        BydTimer bydTimer = this.E;
        if (bydTimer != null && bydTimer.isTimerWorking()) {
            this.E.stopTimer();
        }
        BydTimer bydTimer2 = new BydTimer(this.D);
        this.E = bydTimer2;
        if (bydTimer2 != null) {
            bydTimer2.setBydTimerListener(new f());
            this.E.startTimer(j);
        }
    }

    public void w(String str, int i, int i2) {
        this.k = str;
        this.m = i;
        this.l = i2;
        TextView textView = this.f1467c;
        if (textView == null || this.s != null) {
            return;
        }
        textView.setText(str);
        this.f1467c.setGravity(i);
        this.f1467c.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.f1467c.setTextSize(0, i2);
        DampingScrollView dampingScrollView = this.w;
        if (dampingScrollView != null) {
            dampingScrollView.setVisibility(0);
        }
        DampingScrollView dampingScrollView2 = this.v;
        if (dampingScrollView2 != null) {
            dampingScrollView2.setVisibility(8);
        }
        this.f1467c.post(new c());
    }

    public void x(String str) {
        this.A = str;
    }

    public void y(boolean z) {
        this.x = z;
        DampingScrollView dampingScrollView = this.v;
        if (dampingScrollView != null) {
            dampingScrollView.setScroll(z);
        }
        DampingScrollView dampingScrollView2 = this.w;
        if (dampingScrollView2 != null) {
            dampingScrollView2.setScroll(z);
        }
    }

    public void z(String str) {
        this.j = str;
        TextView textView = this.f1466b;
        if (textView != null) {
            textView.setText(str);
        }
    }
}
