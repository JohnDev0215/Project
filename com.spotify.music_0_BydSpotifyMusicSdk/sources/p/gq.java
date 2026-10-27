package p;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.spotify.music.R;

/* JADX INFO: loaded from: classes.dex */
public final class gq extends Dialog {
    public final CharSequence c;
    public final Drawable e;
    public final CharSequence k;
    public final CharSequence l;
    public final DialogInterface.OnClickListener m;
    public final String n;
    public final DialogInterface.OnClickListener o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f126p;
    public final int q;
    public final int r;
    public View s;
    public View t;
    public TextView u;
    public ImageView v;
    public TextView w;
    public View x;
    public Button y;
    public Button z;

    public gq(Context context, m90 m90Var) {
        super(context, R.style.CarAlertDialogTheme);
        this.c = (CharSequence) m90Var.d;
        this.e = (Drawable) m90Var.c;
        this.k = (CharSequence) m90Var.e;
        this.l = (CharSequence) m90Var.f;
        this.m = (DialogInterface.OnClickListener) m90Var.g;
        this.n = m90Var.h;
        this.o = (DialogInterface.OnClickListener) m90Var.i;
        Resources resources = context.getResources();
        this.f126p = resources.getDimensionPixelSize(R.dimen.dialog_inner_padding);
        this.q = resources.getDimensionPixelSize(R.dimen.dialog_button_vertical_padding);
        this.r = resources.getDimensionPixelSize(R.dimen.dialog_inner_padding);
    }

    public final void a() {
        boolean z = this.y.getVisibility() == 0 || this.z.getVisibility() == 0;
        int i = z ? 0 : 8;
        if (this.x.getVisibility() == i) {
            return;
        }
        this.x.setVisibility(i);
        int i2 = z ? 0 : this.r;
        View view = this.s;
        view.setPaddingRelative(view.getPaddingStart(), this.s.getPaddingTop(), this.s.getPaddingEnd(), i2);
    }

    @Override // android.app.Dialog
    public final void onCreate(Bundle bundle) {
        float fA;
        super.onCreate(bundle);
        final Window window = getWindow();
        window.setContentView(R.layout.car_alert_dialog);
        Window window2 = getWindow();
        this.s = window2.findViewById(R.id.content_view);
        this.t = window2.findViewById(R.id.header_view);
        this.v = (ImageView) window2.findViewById(R.id.icon_view);
        this.u = (TextView) window2.findViewById(R.id.title);
        this.w = (TextView) window2.findViewById(R.id.body);
        this.x = window2.findViewById(R.id.button_panel);
        this.y = (Button) window2.findViewById(R.id.positive_button);
        this.z = (Button) window2.findViewById(R.id.negative_button);
        final int i = 0;
        this.y.setOnClickListener(new View.OnClickListener(this) { // from class: p.fq
            public final /* synthetic */ gq e;

            {
                this.e = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i2 = i;
                gq gqVar = this.e;
                switch (i2) {
                    case 0:
                        DialogInterface.OnClickListener onClickListener = gqVar.m;
                        if (onClickListener == null) {
                            gqVar.dismiss();
                        } else {
                            onClickListener.onClick(gqVar, -1);
                        }
                        break;
                    default:
                        DialogInterface.OnClickListener onClickListener2 = gqVar.o;
                        if (onClickListener2 == null) {
                            gqVar.dismiss();
                        } else {
                            onClickListener2.onClick(gqVar, -2);
                        }
                        break;
                }
            }
        });
        final int i2 = 1;
        this.z.setOnClickListener(new View.OnClickListener(this) { // from class: p.fq
            public final /* synthetic */ gq e;

            {
                this.e = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i22 = i2;
                gq gqVar = this.e;
                switch (i22) {
                    case 0:
                        DialogInterface.OnClickListener onClickListener = gqVar.m;
                        if (onClickListener == null) {
                            gqVar.dismiss();
                        } else {
                            onClickListener.onClick(gqVar, -1);
                        }
                        break;
                    default:
                        DialogInterface.OnClickListener onClickListener2 = gqVar.o;
                        if (onClickListener2 == null) {
                            gqVar.dismiss();
                        } else {
                            onClickListener2.onClick(gqVar, -2);
                        }
                        break;
                }
            }
        });
        TextView textView = this.w;
        CharSequence charSequence = this.k;
        textView.setText(charSequence);
        this.w.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        ((ViewGroup.MarginLayoutParams) this.x.getLayoutParams()).topMargin = this.w.getVisibility() == 0 ? this.q : 0;
        this.x.requestLayout();
        Button button = this.y;
        CharSequence charSequence2 = this.l;
        button.setText(charSequence2);
        this.y.setVisibility(TextUtils.isEmpty(charSequence2) ? 8 : 0);
        a();
        Button button2 = this.z;
        String str = this.n;
        button2.setText(str);
        this.z.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        a();
        Drawable drawable = this.e;
        if (drawable != null) {
            this.v.setImageDrawable(drawable);
            this.v.setVisibility(0);
        } else {
            this.v.setVisibility(8);
        }
        CharSequence charSequence3 = this.c;
        boolean z = !TextUtils.isEmpty(charSequence3);
        this.u.setText(charSequence3);
        this.u.setVisibility(z ? 0 : 8);
        boolean z2 = this.u.getVisibility() == 0;
        boolean z3 = this.v.getVisibility() == 0;
        boolean z4 = (z3 || (this.x.getVisibility() == 0) || (this.w.getVisibility() == 0)) ? false : true;
        int i3 = (z2 || z3) ? 0 : this.f126p;
        int paddingBottom = z4 ? 0 : this.s.getPaddingBottom();
        View view = this.s;
        view.setPaddingRelative(view.getPaddingStart(), i3, this.s.getPaddingEnd(), paddingBottom);
        int paddingTop = z3 ? this.t.getPaddingTop() : 0;
        int paddingBottom2 = z3 ? this.t.getPaddingBottom() : 0;
        View view2 = this.t;
        view2.setPaddingRelative(view2.getPaddingStart(), paddingTop, this.t.getPaddingEnd(), paddingBottom2);
        Resources resources = getContext().getResources();
        if (Build.VERSION.SDK_INT >= 29) {
            ThreadLocal threadLocal = s42.a;
            fA = m42.a(resources, R.dimen.dialog_background_dim);
        } else {
            ThreadLocal threadLocal2 = s42.a;
            TypedValue typedValue = (TypedValue) threadLocal2.get();
            if (typedValue == null) {
                typedValue = new TypedValue();
                threadLocal2.set(typedValue);
            }
            resources.getValue(R.dimen.dialog_background_dim, typedValue, true);
            if (typedValue.type != 4) {
                throw new Resources.NotFoundException("Resource ID #0x" + Integer.toHexString(R.dimen.dialog_background_dim) + " type #0x" + Integer.toHexString(typedValue.type) + " is not valid");
            }
            fA = typedValue.getFloat();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, fA);
        valueAnimatorOfFloat.setDuration(250L);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: p.eq
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                window.setDimAmount(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        valueAnimatorOfFloat.start();
    }

    @Override // android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        throw new UnsupportedOperationException("Title should only be set from the Builder");
    }
}
