package b.b.g.q.d;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import com.byd.carsettings.R;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;

/* JADX INFO: compiled from: LowPowerDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f1488b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Dialog f1490d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ViewGroup f1491e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ViewGroup f1492f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1487a = o.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f1489c = "";
    public boolean g = true;
    public int h = 200;

    /* JADX INFO: compiled from: LowPowerDialog.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (o.this.g) {
                o.this.b();
            }
        }
    }

    /* JADX INFO: compiled from: LowPowerDialog.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            o.this.b();
        }
    }

    /* JADX INFO: compiled from: LowPowerDialog.java */
    public class c implements DialogInterface.OnDismissListener {
        public c() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            m.f1479a.remove(o.this);
        }
    }

    public o(@NonNull Context context) {
        this.f1488b = context;
    }

    public void b() {
        if (this.f1490d.findViewById(R.id.btn_ok) != null) {
            this.f1490d.findViewById(R.id.btn_ok).setOnClickListener(null);
        }
        this.f1491e = null;
        this.f1492f = null;
        this.f1490d.dismiss();
        Dialog dialog = this.f1490d;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.f1490d.setOnCancelListener(null);
            this.f1490d.setOnShowListener(null);
        }
        this.f1490d = null;
        this.f1488b = null;
    }

    public String c() {
        return this.f1489c;
    }

    public void d() {
        Window window = this.f1490d.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        attributes.gravity = 17;
        attributes.dimAmount = 0.0f;
        attributes.windowAnimations = R.style.byd_dialog_common_anim;
        this.f1490d.getWindow().setAttributes(attributes);
        this.f1490d.setCanceledOnTouchOutside(this.g);
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1792);
    }

    public void e() {
        this.f1490d.findViewById(R.id.btn_ok).setOnClickListener(new b());
        this.f1490d.setOnDismissListener(new c());
    }

    public void f() {
        this.f1491e = (ViewGroup) this.f1490d.findViewById(R.id.ll_dialog_masks);
        this.f1492f = (ViewGroup) this.f1490d.findViewById(R.id.ll_dialog_content);
        this.f1491e.setOnClickListener(new a());
    }

    public boolean g() {
        Dialog dialog = this.f1490d;
        if (dialog != null) {
            return dialog.isShowing();
        }
        return false;
    }

    public void h(String str) {
        this.f1489c = str;
    }

    public void i() {
        try {
            Context context = this.f1488b;
            if (context == null) {
                return;
            }
            Dialog dialog = new Dialog(context, R.style.byd_dialog);
            this.f1490d = dialog;
            dialog.setContentView(R.layout.dialog_low_power_hint);
            f();
            e();
            d();
            this.f1490d.show();
            this.f1492f.animate().scaleX(1.0f).scaleY(1.0f).setDuration(this.h).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
            this.f1492f.animate().alpha(1.0f).setDuration(this.h).setInterpolator(PathInterpolatorUtils.getStandard()).start();
            this.f1491e.animate().alpha(1.0f).setDuration(this.h).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        } catch (Exception e2) {
            e2.printStackTrace();
            b.b.m.c.a.c(this.f1487a, "[CommonDialog]----" + e2.getMessage());
        }
    }
}
