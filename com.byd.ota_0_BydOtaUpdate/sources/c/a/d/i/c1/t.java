package c.a.d.i.c1;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.byd.ota.R;

/* JADX INFO: compiled from: UpdateInfoDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class t extends n {
    public static final String G3 = t.class.getSimpleName();
    public TextView H3;
    public TextView I3;
    public TextView J3;
    public TextView K3;
    public TextView L3;
    public TextView M3;
    public Button N3;
    public Button O3;
    public Button P3;
    public LinearLayout Q3;
    public RelativeLayout R3;
    public a S3;

    /* JADX INFO: compiled from: UpdateInfoDialog.java */
    public interface a {
        void a();

        void b();

        void c();

        void d();
    }

    public t(Context context) {
        super(context);
        c.a.b.d.e.c(G3, "steeringWheelPositionRight = " + this.f2530c + ", layoutDirectionRtl = " + this.f2531d);
        if (c.a.d.h.e.b()) {
            setContentView(R.layout.dialog_update_info_rh);
        } else {
            setContentView(R.layout.dialog_update_info);
        }
        this.H3 = (TextView) findViewById(R.id.dialog_title);
        this.I3 = (TextView) findViewById(R.id.tv_tip1);
        this.J3 = (TextView) findViewById(R.id.tv_tip2);
        this.K3 = (TextView) findViewById(R.id.tv_tip3);
        this.L3 = (TextView) findViewById(R.id.tv_thanks);
        this.M3 = (TextView) findViewById(R.id.tv_thanks_detail);
        this.N3 = (Button) findViewById(R.id.upgrade_immediately);
        this.O3 = (Button) findViewById(R.id.upgrade_order);
        this.P3 = (Button) findViewById(R.id.not_update_now);
        this.K3.setOnClickListener(new View.OnClickListener() { // from class: c.a.d.i.c1.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2524c.m(view);
            }
        });
        this.N3.setOnClickListener(new View.OnClickListener() { // from class: c.a.d.i.c1.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2525c.o(view);
            }
        });
        this.O3.setOnClickListener(new View.OnClickListener() { // from class: c.a.d.i.c1.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2523c.q(view);
            }
        });
        this.P3.setOnClickListener(new View.OnClickListener() { // from class: c.a.d.i.c1.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2526c.s(view);
            }
        });
        this.Q3 = (LinearLayout) findViewById(R.id.content_ll);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.container_btn_rl);
        this.R3 = relativeLayout;
        if (this.f2531d) {
            if (this.f2530c) {
                this.Q3.setLayoutDirection(0);
                return;
            }
            relativeLayout.setLayoutDirection(0);
            this.I3.setGravity(8388613);
            this.J3.setGravity(8388613);
            this.K3.setGravity(8388613);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void m(View view) {
        dismissDialog();
        a aVar = this.S3;
        if (aVar != null) {
            aVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void o(View view) {
        dismissDialog();
        a aVar = this.S3;
        if (aVar != null) {
            aVar.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void q(View view) {
        dismissDialog();
        a aVar = this.S3;
        if (aVar != null) {
            aVar.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void s(View view) {
        dismissDialog();
        a aVar = this.S3;
        if (aVar != null) {
            aVar.d();
        }
    }

    public void t(a aVar) {
        this.S3 = aVar;
    }

    public void u(String str) {
        if (TextUtils.isEmpty(str)) {
            this.L3.setVisibility(8);
            this.M3.setVisibility(8);
        } else {
            this.L3.setVisibility(0);
            this.M3.setVisibility(0);
            this.M3.setText(str);
        }
    }

    public void v(String str) {
        this.I3.setText(str);
    }

    public void w(String str) {
        this.K3.setText(str);
    }

    public void x(String str) {
        this.H3.setText(str);
    }
}
