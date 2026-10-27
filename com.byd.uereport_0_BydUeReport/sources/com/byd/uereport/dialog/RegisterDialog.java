package com.byd.uereport.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.Group;
import com.byd.uereport.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import g5.c;
import g5.k;

/* JADX INFO: loaded from: classes2.dex */
public class RegisterDialog extends BydFullScreenDialog {
    public boolean A;
    public boolean B;
    public boolean C;
    public String D;
    public String E;
    public a F;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ViewGroup f3288d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ViewGroup f3289e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ViewGroup f3290f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ViewGroup f3291g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ViewGroup f3292h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Group f3293i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Group f3294j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public View f3295k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public AppCompatImageView f3296l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public AppCompatImageView f3297m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public AppCompatImageView f3298n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public AppCompatImageView f3299o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public AppCompatImageView f3300p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public AppCompatTextView f3301q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public AppCompatTextView f3302r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public AppCompatTextView f3303s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public AppCompatTextView f3304t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public AppCompatTextView f3305u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public AppCompatTextView f3306v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public AppCompatTextView f3307w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public AppCompatTextView f3308x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f3309y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f3310z;

    public interface a {
        void a();
    }

    public RegisterDialog(Context context) {
        super(context);
        this.f3309y = 0;
        this.f3310z = 0;
    }

    public String c() {
        return this.D;
    }

    public int d() {
        return this.f3309y;
    }

    @Override // b5.g, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
    }

    public String e() {
        return this.E;
    }

    public int f() {
        return this.f3310z;
    }

    public void g() {
        if (isShowing()) {
            this.B = false;
            this.C = false;
            int i8 = this.f3309y;
            if (i8 == 0) {
                this.f3289e.setVisibility(8);
            } else if (i8 == 3) {
                this.f3290f.setVisibility(8);
            }
        }
    }

    public final void h(int i8) {
        if (i8 == 0) {
            this.f3288d.setVisibility(0);
            this.f3291g.setVisibility(8);
            this.f3292h.setVisibility(8);
            this.f3305u.setText(this.mContext.getString(R.string.register_bind_qr_tip));
            q();
            u(this.D, this.f3297m);
            u(this.E, this.f3299o);
            t();
            return;
        }
        if (i8 == 1) {
            this.f3288d.setVisibility(0);
            this.f3291g.setVisibility(8);
            this.f3292h.setVisibility(8);
            this.f3305u.setText(this.mContext.getString(R.string.register_download_qr_tip));
            r();
            u(this.E, this.f3297m);
            t();
            return;
        }
        if (i8 != 2) {
            if (i8 != 3) {
                if (i8 != 4) {
                    return;
                }
                this.f3288d.setVisibility(8);
                this.f3291g.setVisibility(8);
                this.f3292h.setVisibility(0);
                this.f3306v.setText(this.mContext.getString(R.string.register_download_qr_tip));
                r();
                u(this.E, this.f3298n);
                return;
            }
            this.f3288d.setVisibility(8);
            this.f3291g.setVisibility(8);
            this.f3292h.setVisibility(0);
            this.f3306v.setText(this.mContext.getString(R.string.register_bind_qr_tip));
            this.f3293i.setVisibility(0);
            q();
            u(this.D, this.f3298n);
            u(this.E, this.f3299o);
            return;
        }
        this.f3288d.setVisibility(8);
        this.f3291g.setVisibility(0);
        this.f3292h.setVisibility(8);
        this.f3293i.setVisibility(4);
        v();
        int i9 = this.f3310z;
        if (i9 == 1) {
            this.f3295k.setVisibility(8);
            this.f3296l.setVisibility(0);
            this.f3296l.setImageResource(R.drawable.icon_register_error);
            this.f3301q.setText(this.mContext.getString(R.string.register_status_error));
            return;
        }
        if (i9 == 2) {
            this.f3295k.setVisibility(8);
            this.f3296l.setVisibility(0);
            this.f3296l.setImageResource(R.drawable.icon_register_success);
            this.f3301q.setText(this.mContext.getString(R.string.register_status_complete));
        }
    }

    public boolean i() {
        return this.C;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        this.f3288d = (ViewGroup) view.findViewById(R.id.container_bind);
        this.f3291g = (ViewGroup) view.findViewById(R.id.container_setting_process);
        this.f3292h = (ViewGroup) view.findViewById(R.id.container_setting_bind);
        this.f3289e = (ViewGroup) view.findViewById(R.id.container_qr_expire);
        this.f3308x = (AppCompatTextView) view.findViewById(R.id.tv_refresh_qr_tip);
        this.f3290f = (ViewGroup) view.findViewById(R.id.container_qr3_expire);
        this.f3293i = (Group) view.findViewById(R.id.group_bottom_download_tip);
        this.f3295k = view.findViewById(R.id.pb);
        this.f3296l = (AppCompatImageView) view.findViewById(R.id.iv_status);
        this.f3301q = (AppCompatTextView) view.findViewById(R.id.tv_status);
        this.f3297m = (AppCompatImageView) view.findViewById(R.id.iv_qr);
        this.f3305u = (AppCompatTextView) view.findViewById(R.id.tv_qr_tip);
        this.f3298n = (AppCompatImageView) view.findViewById(R.id.iv_qr3);
        this.f3306v = (AppCompatTextView) view.findViewById(R.id.tv_qr3_tip);
        this.f3299o = (AppCompatImageView) view.findViewById(R.id.iv_bottom_qr2);
        this.f3302r = (AppCompatTextView) view.findViewById(R.id.tv_step1_content);
        this.f3303s = (AppCompatTextView) view.findViewById(R.id.tv_step2_content);
        this.f3304t = (AppCompatTextView) view.findViewById(R.id.tv_step3_content);
        this.f3294j = (Group) view.findViewById(R.id.group_step3);
        this.f3300p = (AppCompatImageView) view.findViewById(R.id.iv_check);
        this.f3307w = (AppCompatTextView) view.findViewById(R.id.tv_title);
        ((AppCompatImageView) view.findViewById(R.id.iv_close)).setOnClickListener(this);
        this.f3300p.setOnClickListener(this);
        h(this.f3309y);
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return c.e() == 1 ? R.layout.dialog_register_right_rudder : R.layout.dialog_register;
    }

    public final boolean j(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public boolean k() {
        return this.B;
    }

    public void l(String str) {
        this.D = str;
    }

    public void m(int i8) {
        this.f3309y = i8;
    }

    public void n(String str) {
        this.E = str;
    }

    public void o(a aVar) {
        this.F = aVar;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.container_qr3_expire /* 2131296437 */:
            case R.id.container_qr_expire /* 2131296438 */:
                a aVar = this.F;
                if (aVar != null) {
                    aVar.a();
                }
                break;
            case R.id.iv_check /* 2131296581 */:
                boolean z7 = !this.A;
                this.A = z7;
                if (!z7) {
                    ((ImageView) view).setImageResource(R.mipmap.check_normal_regular);
                    k.g(this.mContext, "register_not_remind", false);
                    k.j(this.mContext, "register_start_time", 0L);
                } else {
                    ((ImageView) view).setImageResource(R.mipmap.check_pitch_regular);
                    k.g(this.mContext, "register_not_remind", true);
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    BydLogHelper.logD("save not remind start time :" + jCurrentTimeMillis);
                    k.j(this.mContext, "register_start_time", jCurrentTimeMillis);
                }
                break;
            case R.id.iv_close /* 2131296583 */:
                dismiss();
                break;
        }
    }

    public void p(int i8) {
        this.f3310z = i8;
    }

    public final void q() {
        this.f3307w.setText(this.mContext.getString(R.string.register_title));
        this.f3302r.setText(this.mContext.getString(R.string.register_step1_bind_text));
        this.f3303s.setText(this.mContext.getString(R.string.register_step2_bind_text));
        if (j(this.mContext)) {
            this.f3304t.setText(this.mContext.getString(R.string.register_step3_landscape_bind_text));
        } else {
            this.f3304t.setText(this.mContext.getString(R.string.register_step3_portrait_bind_text));
        }
    }

    public final void r() {
        this.f3294j.setVisibility(4);
        this.f3293i.setVisibility(4);
        this.f3307w.setText(this.mContext.getString(R.string.register_title));
        this.f3302r.setText(this.mContext.getString(R.string.register_step1_download_bind_text));
        this.f3303s.setText(this.mContext.getString(R.string.register_step2_bind_text));
    }

    public void s(boolean z7) {
        if (isShowing()) {
            this.B = true;
            int i8 = this.f3309y;
            if (i8 != 0) {
                if (i8 == 3) {
                    this.f3290f.setVisibility(0);
                    this.f3290f.setOnClickListener(this);
                    return;
                }
                return;
            }
            if (z7) {
                this.C = true;
                this.f3308x.setText(this.mContext.getString(R.string.register_bind_qr_show_tip));
            } else {
                this.C = false;
                this.f3308x.setText(this.mContext.getString(R.string.register_bind_qr_refresh_tip));
            }
            this.f3289e.setVisibility(0);
            this.f3289e.setOnClickListener(this);
        }
    }

    public final void t() {
        if (k.b(this.mContext, "register_not_remind", false)) {
            this.f3300p.setImageResource(R.mipmap.check_pitch_regular);
        } else {
            this.f3300p.setImageResource(R.mipmap.check_normal_regular);
        }
    }

    public final void u(String str, ImageView imageView) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        imageView.setImageBitmap(o4.a.a(str, x4.a.a(this.mContext, 160.0f), x4.a.a(this.mContext, 160.0f)));
    }

    public final void v() {
        this.f3294j.setVisibility(0);
        this.f3307w.setText(this.mContext.getString(R.string.register_title_remote_control));
        this.f3302r.setText(this.mContext.getString(R.string.register_step1_remote_control_tip));
        this.f3303s.setText(this.mContext.getString(R.string.register_step2_remote_control_tip));
        this.f3304t.setText(this.mContext.getString(R.string.register_step3_remote_control_tip));
    }

    public void w(String str) {
        if (this.B) {
            int i8 = this.f3309y;
            if (i8 == 0) {
                this.f3289e.setVisibility(8);
            } else if (i8 == 3) {
                this.f3290f.setVisibility(8);
            }
        }
        int i9 = this.f3309y;
        if (i9 == 0) {
            u(str, this.f3297m);
        } else if (i9 == 3) {
            u(str, this.f3298n);
        }
    }

    public void x(int i8) {
        this.f3309y = i8;
        h(i8);
    }

    public RegisterDialog(Context context, int i8) {
        super(context);
        this.f3309y = 0;
        this.f3310z = 0;
        this.f3309y = i8;
    }
}
