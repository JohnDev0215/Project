package b.b.a.j.b;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.byd.base.R$drawable;
import com.byd.base.R$id;
import com.byd.base.R$layout;
import com.byd.base.R$style;

/* JADX INFO: compiled from: CommonUseDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends Dialog {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f540a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f541b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f542c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f543d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f544e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f545f;
    public TextView g;
    public LinearLayout h;
    public LinearLayout i;
    public ImageView j;
    public View k;
    public View l;
    public g m;
    public e n;
    public f o;
    public boolean p;

    /* JADX INFO: renamed from: b.b.a.j.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CommonUseDialog.java */
    public class ViewOnClickListenerC0022a implements View.OnClickListener {
        public ViewOnClickListenerC0022a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (a.this.m != null) {
                a.this.m.a(view);
            }
            if (a.this.p) {
                a.this.dismiss();
            }
        }
    }

    /* JADX INFO: compiled from: CommonUseDialog.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (a.this.n != null) {
                a.this.n.a(view);
            }
            a.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: CommonUseDialog.java */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (a.this.o != null) {
                a.this.o.a(view);
            }
            a.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: CommonUseDialog.java */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.this.f544e.getLineCount() < 3) {
                a.this.f544e.setGravity(17);
            } else {
                a.this.f544e.setGravity(GravityCompat.START);
            }
        }
    }

    /* JADX INFO: compiled from: CommonUseDialog.java */
    public interface e {
        void a(View view);
    }

    /* JADX INFO: compiled from: CommonUseDialog.java */
    public interface f {
        void a(View view);
    }

    /* JADX INFO: compiled from: CommonUseDialog.java */
    public interface g {
        void a(View view);
    }

    public a(Context context) {
        this(context, 0, R$style.customDialog);
    }

    public final void f() {
        this.f545f.setOnClickListener(new ViewOnClickListenerC0022a());
        this.g.setOnClickListener(new b());
        this.j.setOnClickListener(new c());
    }

    public int g() {
        return R$layout.layout_common_dialog_horizontal;
    }

    public View h() {
        return this.f540a;
    }

    public int i() {
        return R$layout.layout_common_dialog_horizontal;
    }

    public a j() {
        this.f542c.setVisibility(8);
        this.f543d.setVisibility(8);
        return this;
    }

    public final void k(View view) {
        this.l = view.findViewById(R$id.rcrl_root_layout);
        this.f542c = (TextView) view.findViewById(R$id.tv_dialog_title);
        this.f543d = view.findViewById(R$id.divider_line);
        this.f544e = (TextView) view.findViewById(R$id.tv_dialog_message);
        this.f545f = (TextView) view.findViewById(R$id.tv_dialog_confirm);
        this.g = (TextView) view.findViewById(R$id.tv_dialog_cancel);
        this.i = (LinearLayout) view.findViewById(R$id.ll_dialog_center_group);
        this.j = (ImageView) view.findViewById(R$id.iv_dialog_close);
        this.h = (LinearLayout) view.findViewById(R$id.ll_dialog_button);
        this.k = view.findViewById(R$id.divider_view);
        w();
        x(this.h, b.b.a.i.a.c(getContext()));
    }

    public a l() {
        this.f545f.setVisibility(0);
        this.g.setVisibility(8);
        this.k.setVisibility(8);
        return this;
    }

    public a m(int i, int i2, int i3, int i4) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.h.getLayoutParams();
        layoutParams.leftMargin = i;
        layoutParams.topMargin = i2;
        layoutParams.rightMargin = i3;
        layoutParams.bottomMargin = i4;
        this.h.setLayoutParams(layoutParams);
        return this;
    }

    public a n(e eVar) {
        this.n = eVar;
        return this;
    }

    public a o(int i) {
        this.f545f.setText(i);
        return this;
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(this.f540a);
    }

    public a p(String str) {
        this.f545f.setText(str);
        return this;
    }

    public a q(g gVar) {
        this.m = gVar;
        return this;
    }

    public a r(View view) {
        this.i.removeAllViews();
        this.f544e.setVisibility(8);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.i.addView(view, layoutParams);
        return this;
    }

    public void s(int i, int i2) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = i;
        attributes.height = i2;
        getWindow().setAttributes(attributes);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        v(getContext().getString(i));
    }

    public a t(int i) {
        this.f544e.setText(i);
        this.f544e.post(new d());
        return this;
    }

    public a u(int i, int i2, int i3, int i4) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.i.getLayoutParams();
        layoutParams.leftMargin = i;
        layoutParams.topMargin = i2;
        layoutParams.rightMargin = i3;
        layoutParams.bottomMargin = i4;
        this.i.setLayoutParams(layoutParams);
        return this;
    }

    public a v(String str) {
        this.f542c.setVisibility(0);
        this.f542c.setText(str);
        this.f543d.setVisibility(0);
        return this;
    }

    public final void w() {
        if (b.b.a.i.a.f533c == 1) {
            this.f545f.setBackgroundResource(R$drawable.base_selector_dialog_blue_denz);
            this.g.setBackgroundResource(R$drawable.base_selector_dialog_white_denz);
            this.l.setBackgroundResource(R$drawable.base_bg_frame_dialog_gradient_denz);
        }
    }

    public final void x(View view, boolean z) {
        if (view == null) {
            return;
        }
        view.setLayoutDirection(z ? 1 : 0);
    }

    public a(Context context, int i, int i2) {
        super(context, i2);
        this.p = true;
        if (i == 1) {
            this.f540a = LayoutInflater.from(context).inflate(i(), (ViewGroup) null);
        } else {
            this.f540a = LayoutInflater.from(context).inflate(g(), (ViewGroup) null);
        }
        setCancelable(true);
        this.f541b = context;
        k(this.f540a);
        f();
    }
}
