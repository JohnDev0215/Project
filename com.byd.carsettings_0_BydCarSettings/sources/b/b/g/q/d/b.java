package b.b.g.q.d;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.byd.carsettings.R;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;

/* JADX INFO: compiled from: BydCarSettingDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class b extends b.b.g.q.a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Context f1438f;
    public Dialog g;
    public int h;

    /* JADX INFO: compiled from: BydCarSettingDialog.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    /* JADX INFO: renamed from: b.b.g.q.d.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydCarSettingDialog.java */
    public class ViewOnClickListenerC0067b implements View.OnClickListener {
        public ViewOnClickListenerC0067b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            b.this.l();
            b.this.x();
        }
    }

    /* JADX INFO: compiled from: BydCarSettingDialog.java */
    public class c implements View.OnAttachStateChangeListener {
        public c() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            b.this.w();
        }
    }

    /* JADX INFO: compiled from: BydCarSettingDialog.java */
    public class d implements DialogInterface.OnKeyListener {
        public d() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return b.this.v(i, keyEvent);
        }
    }

    public b(Context context) {
        super(context);
        this.h = 1000;
        this.f1438f = context;
    }

    public abstract void s(Dialog dialog);

    public abstract void t(Dialog dialog);

    public abstract void u(Dialog dialog);

    public boolean v(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 1) {
            return false;
        }
        x();
        Dialog dialog = this.g;
        if (dialog != null) {
            dialog.dismiss();
        }
        return true;
    }

    public void w() {
    }

    public void x() {
    }

    public Dialog y(int i) {
        Dialog dialogQ = q(R.layout.carseting_dialog_container);
        this.g = dialogQ;
        RelativeLayout relativeLayout = (RelativeLayout) dialogQ.findViewById(R.id.dialog_container_rl);
        Context context = this.f1438f;
        b.b.a.h.l.b(relativeLayout, context, b.b.a.h.b.a(context, R.attr.dialog_masks));
        relativeLayout.setGravity(17);
        relativeLayout.removeAllViews();
        View viewInflate = LayoutInflater.from(this.f1438f).inflate(i, (ViewGroup) relativeLayout, false);
        relativeLayout.addView(viewInflate);
        viewInflate.setOnClickListener(new a());
        viewInflate.setSoundEffectsEnabled(false);
        relativeLayout.getChildAt(0).setScaleX(0.9f);
        relativeLayout.getChildAt(0).setScaleY(0.9f);
        relativeLayout.getChildAt(0).setAlpha(0.0f);
        relativeLayout.getChildAt(0).animate().scaleX(1.0f).scaleY(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        relativeLayout.getChildAt(0).animate().alpha(1.0f).setDuration(200L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        relativeLayout.setOnClickListener(new ViewOnClickListenerC0067b());
        relativeLayout.addOnAttachStateChangeListener(new c());
        WindowManager.LayoutParams attributes = this.g.getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        attributes.gravity = 17;
        attributes.dimAmount = 0.0f;
        attributes.windowAnimations = R.style.byd_dialog_common_anim;
        this.g.getWindow().setAttributes(attributes);
        this.g.setCanceledOnTouchOutside(true);
        this.g.getWindow().addFlags(Integer.MIN_VALUE);
        this.g.getWindow().clearFlags(67108864);
        this.g.getWindow().setStatusBarColor(0);
        this.g.getWindow().getDecorView().setSystemUiVisibility(1792);
        this.g.setOnKeyListener(new d());
        u(this.g);
        t(this.g);
        s(this.g);
        return this.g;
    }
}
