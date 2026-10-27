package c.a.d.i.c1;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.byd.ota.R;
import com.byd.ota.view.OtaUiService;

/* JADX INFO: compiled from: BaseDialog.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class n extends Dialog {
    public FrameLayout E3;
    public TextView F3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2530c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2531d;
    public Context s;
    public Resources x;
    public RelativeLayout y;

    public n(Context context) {
        super(context, R.style.DialogFullScreenTheme);
        c.a.d.h.e.c(context);
        this.s = context;
        this.x = context.getResources();
        this.f2530c = c.a.b.d.h.b() == 1;
        this.f2531d = context.getResources().getConfiguration().getLayoutDirection() == 1;
        requestWindowFeature(1);
        g(true);
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.addFlags(131072);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        attributes.width = -1;
        attributes.height = -1;
        attributes.format = 1;
        attributes.dimAmount = 0.0f;
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1280);
        window.setAttributes(attributes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void c(View view) {
        if (BYDAutoBodyworkDevice.getInstance(getContext()).getPowerLevel() == 0 || OtaUiService.f3076d == 9) {
            return;
        }
        dismissDialog();
    }

    public static /* synthetic */ boolean d(View view, MotionEvent motionEvent) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void f() {
        this.F3.setVisibility(4);
    }

    public void dismissDialog() {
        OtaUiService.s = OtaUiService.f3076d;
        OtaUiService.f3076d = 0;
        dismiss();
    }

    public void g(boolean z) {
        setCancelable(z);
        setCanceledOnTouchOutside(z);
    }

    public void h(int i2) {
        OtaUiService.f3076d = i2;
        if (BYDAutoBodyworkDevice.getInstance(getContext()).getPowerLevel() == 0) {
            getWindow().setType(2044);
        } else {
            getWindow().setType(2038);
            if (i2 == 9) {
                getWindow().setType(2044);
            }
        }
        k();
        show();
    }

    public void i(int i2, int i3) {
        OtaUiService.f3076d = i2;
        getWindow().setType(i3);
        k();
        show();
    }

    public void j(String str, long j) {
        if (this.F3.getVisibility() == 0) {
            return;
        }
        this.F3.setText(str);
        this.F3.setGravity(17);
        this.F3.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200L).setInterpolator(c.a.d.h.b.a()).start();
        this.F3.animate().alpha(1.0f).setDuration(200L).setInterpolator(c.a.d.h.b.c()).start();
        this.F3.setVisibility(0);
        c.a.b.d.l.d().c(new Runnable() { // from class: c.a.d.i.c1.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f2517c.f();
            }
        }, j);
    }

    public void k() {
        this.y.animate().alpha(1.0f).setDuration(200L).setInterpolator(c.a.d.h.b.b()).start();
        this.E3.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200L).setInterpolator(c.a.d.h.b.c()).start();
        this.E3.animate().alpha(1.0f).setDuration(200L).setInterpolator(c.a.d.h.b.b()).start();
    }

    @Override // android.app.Dialog
    public void setContentView(int i2) {
        super.setContentView(R.layout.dialog_base);
        this.y = (RelativeLayout) findViewById(R.id.base_dialog_mask);
        this.E3 = (FrameLayout) findViewById(R.id.base_dialog_frame);
        this.F3 = (TextView) findViewById(R.id.base_dialog_tips);
        LayoutInflater.from(this.s).inflate(i2, this.E3);
        this.y.setOnClickListener(new View.OnClickListener() { // from class: c.a.d.i.c1.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2519c.c(view);
            }
        });
        this.E3.setSoundEffectsEnabled(false);
        this.E3.setOnTouchListener(new View.OnTouchListener() { // from class: c.a.d.i.c1.b
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return n.d(view, motionEvent);
            }
        });
    }
}
