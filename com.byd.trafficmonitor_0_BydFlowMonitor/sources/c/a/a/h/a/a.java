package c.a.a.h.a;

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

/* JADX INFO: compiled from: CommonUseDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends Dialog {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f3422b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f3423c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f3424d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f3425e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f3426f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TextView f3427g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public TextView f3428h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public LinearLayout f3429i;
    public LinearLayout j;
    public ImageView k;
    public View l;
    public f m;
    public d n;
    public e o;
    public boolean p;

    /* JADX INFO: renamed from: c.a.a.h.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CommonUseDialog.java */
    public class ViewOnClickListenerC0069a implements View.OnClickListener {
        public ViewOnClickListenerC0069a() {
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
    public interface d {
        void a(View view);
    }

    /* JADX INFO: compiled from: CommonUseDialog.java */
    public interface e {
        void a(View view);
    }

    /* JADX INFO: compiled from: CommonUseDialog.java */
    public interface f {
        void a(View view);
    }

    public a(Context context) {
        this(context, 0, c.a.a.e.customDialog);
    }

    public final void e() {
        this.f3427g.setOnClickListener(new ViewOnClickListenerC0069a());
        this.f3428h.setOnClickListener(new b());
        this.k.setOnClickListener(new c());
    }

    public final int f() {
        return c.a.a.c.layout_common_dialog_horizontal;
    }

    public final int g() {
        return c.a.a.c.layout_common_dialog_vertical;
    }

    public a h() {
        this.f3429i.setVisibility(8);
        return this;
    }

    public final void i(View view) {
        this.f3424d = (TextView) view.findViewById(c.a.a.b.tv_dialog_title);
        this.f3425e = view.findViewById(c.a.a.b.divider_line);
        this.f3426f = (TextView) view.findViewById(c.a.a.b.tv_dialog_message);
        this.f3427g = (TextView) view.findViewById(c.a.a.b.tv_dialog_confirm);
        this.f3428h = (TextView) view.findViewById(c.a.a.b.tv_dialog_cancel);
        this.j = (LinearLayout) view.findViewById(c.a.a.b.ll_dialog_center_group);
        this.k = (ImageView) view.findViewById(c.a.a.b.iv_dialog_close);
        this.f3429i = (LinearLayout) view.findViewById(c.a.a.b.ll_dialog_button);
        this.l = view.findViewById(c.a.a.b.divider_view);
    }

    public a j(int i2) {
        this.k.setVisibility(i2);
        return this;
    }

    public a k(View view) {
        this.j.removeAllViews();
        this.f3426f.setVisibility(8);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.j.addView(view, layoutParams);
        return this;
    }

    public void l(int i2, int i3) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = i2;
        attributes.height = i3;
        getWindow().setAttributes(attributes);
    }

    public a m(int i2, int i3, int i4, int i5) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.j.getLayoutParams();
        layoutParams.leftMargin = i2;
        layoutParams.topMargin = i3;
        layoutParams.rightMargin = i4;
        layoutParams.bottomMargin = i5;
        this.j.setLayoutParams(layoutParams);
        return this;
    }

    public a n(String str) {
        this.f3424d.setVisibility(0);
        this.f3424d.setText(str);
        this.f3425e.setVisibility(0);
        return this;
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(this.f3422b);
    }

    @Override // android.app.Dialog
    public void setTitle(int i2) {
        n(getContext().getString(i2));
    }

    public a(Context context, int i2, int i3) {
        super(context, i3);
        this.p = true;
        if (i2 == 1) {
            this.f3422b = LayoutInflater.from(context).inflate(g(), (ViewGroup) null);
        } else {
            this.f3422b = LayoutInflater.from(context).inflate(f(), (ViewGroup) null);
        }
        setCancelable(false);
        this.f3423c = context;
        i(this.f3422b);
        e();
    }
}
