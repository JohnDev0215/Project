package org.chromium.components.browser_ui.modaldialog;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import defpackage.AbstractC0371Um;
import defpackage.Ts;
import org.chromium.base.Callback;
import org.chromium.components.browser_ui.modaldialog.ModalDialogView;
import org.chromium.components.browser_ui.widget.BoundedLinearLayout;
import org.chromium.components.browser_ui.widget.FadingEdgeScrollView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ModalDialogView extends BoundedLinearLayout implements View.OnClickListener {
    public ViewGroup A;
    public View B;
    public Button C;
    public Button D;
    public Callback E;
    public boolean F;
    public boolean G;
    public FadingEdgeScrollView v;
    public ViewGroup w;
    public TextView x;
    public ImageView y;
    public TextView z;

    public ModalDialogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static final /* synthetic */ void b(View view) {
        boolean z = true;
        if (!view.canScrollVertically(-1) && !view.canScrollVertically(1)) {
            z = false;
        }
        view.setFocusable(z);
    }

    public static final /* synthetic */ boolean c(MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        try {
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Ts.a("ModalDialogView", "Reflection failure: " + e, new Object[0]);
        }
        return (motionEvent.getFlags() & MotionEvent.class.getField("FLAG_WINDOW_IS_PARTIALLY_OBSCURED").getInt(null)) != 0;
    }

    public final Button a(int i) {
        if (i == 0) {
            return this.C;
        }
        if (i != 1) {
            return null;
        }
        return this.D;
    }

    public final void d() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.C.getText());
        boolean z3 = !TextUtils.isEmpty(this.D.getText());
        if (!z2 && !z3) {
            z = false;
        }
        this.C.setVisibility(z2 ? 0 : 8);
        this.D.setVisibility(z3 ? 0 : 8);
        this.B.setVisibility(z ? 0 : 8);
    }

    public final void e() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.x.getText());
        boolean z3 = this.y.getDrawable() != null;
        boolean z4 = z2 || z3;
        boolean z5 = !TextUtils.isEmpty(this.z.getText());
        if ((!this.F || !z4) && !z5) {
            z = false;
        }
        this.x.setVisibility(z2 ? 0 : 8);
        this.y.setVisibility(z3 ? 0 : 8);
        this.w.setVisibility(z4 ? 0 : 8);
        this.z.setVisibility(z5 ? 0 : 8);
        this.v.setVisibility(z ? 0 : 8);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.C) {
            this.E.a(0);
        } else if (view == this.D) {
            this.E.a(1);
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.v = (FadingEdgeScrollView) findViewById(AbstractC0371Um.v2);
        ViewGroup viewGroup = (ViewGroup) findViewById(AbstractC0371Um.U4);
        this.w = viewGroup;
        this.x = (TextView) viewGroup.findViewById(AbstractC0371Um.S4);
        this.y = (ImageView) this.w.findViewById(AbstractC0371Um.V4);
        this.z = (TextView) findViewById(AbstractC0371Um.q2);
        this.A = (ViewGroup) findViewById(AbstractC0371Um.A0);
        this.B = findViewById(AbstractC0371Um.X);
        this.C = (Button) findViewById(AbstractC0371Um.X2);
        this.D = (Button) findViewById(AbstractC0371Um.G2);
        this.C.setOnClickListener(this);
        this.D.setOnClickListener(this);
        e();
        d();
        this.v.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: Iw
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                ModalDialogView.b(view);
            }
        });
    }
}
