package spi;

import android.app.Dialog;
import android.car.drivingstate.BydCarDriverType;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.car.media.main.R$color;
import com.android.car.media.main.R$drawable;
import com.android.car.media.main.R$id;
import com.android.car.media.main.R$layout;
import com.android.car.media.main.R$string;
import com.android.car.media.main.R$style;

/* JADX INFO: compiled from: BydTrafficNoticeDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class z0 extends Dialog {
    public static final /* synthetic */ int g = 0;
    public CheckBox a;
    public TextView b;
    public ConstraintLayout c;
    public ConstraintLayout d;
    public y0 e;
    public long f;

    public z0(@NonNull Context context) {
        super(context, R$style.CountDownDialogStyle);
        this.f = -1L;
    }

    public final void a(long j) {
        TextView textView;
        long jMin = Math.min((j / 1000) + 1, 10L);
        String string = getContext().getString(R$string.dialog_traffic_notice_know);
        String str = string + " ( " + jMin + "s )";
        int iIndexOf = str.indexOf(string);
        int length = string.length();
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(getContext().getColor(R$color.dialog_button_text_cancel_color)), iIndexOf, length, 17);
        if (TextUtils.isEmpty(spannableString) || (textView = this.b) == null) {
            return;
        }
        textView.setText(spannableString);
        this.b.setVisibility(0);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public final void dismiss() {
        super.dismiss();
        y0 y0Var = this.e;
        if (y0Var != null) {
            y0Var.cancel();
            this.e = null;
        }
    }

    @Override // android.app.Dialog
    public final void onBackPressed() {
        if (isShowing()) {
            return;
        }
        super.onBackPressed();
    }

    @Override // android.app.Dialog
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        final int i = 0;
        setCanceledOnTouchOutside(false);
        if (a1.a()) {
            setContentView(R$layout.layout_traffic_notice_dialog_denza);
        } else {
            setContentView(R$layout.layout_traffic_notice_dialog);
        }
        CheckBox checkBox = (CheckBox) findViewById(R$id.checkbox);
        this.a = checkBox;
        checkBox.setOnCheckedChangeListener(new x0(this));
        this.b = (TextView) findViewById(R$id.tv_dialog_confirm_know);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R$id.cl_dialog_bottom_enough);
        this.c = constraintLayout;
        constraintLayout.setOnClickListener(new View.OnClickListener(this) { // from class: spi.w0
            public final /* synthetic */ z0 b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i) {
                    case 0:
                        z0 z0Var = this.b;
                        if (z0Var.a.isChecked()) {
                            t9.a().b(1L);
                        } else {
                            t9.a().b(System.currentTimeMillis());
                        }
                        z0Var.dismiss();
                        break;
                    default:
                        z0 z0Var2 = this.b;
                        if (z0Var2.a.isChecked()) {
                            t9.a().b(1L);
                        } else {
                            t9.a().b(0L);
                        }
                        z0Var2.dismiss();
                        break;
                }
            }
        });
        ConstraintLayout constraintLayout2 = (ConstraintLayout) findViewById(R$id.cl_dialog_bottom_know);
        this.d = constraintLayout2;
        final int i2 = 1;
        constraintLayout2.setOnClickListener(new View.OnClickListener(this) { // from class: spi.w0
            public final /* synthetic */ z0 b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i2) {
                    case 0:
                        z0 z0Var = this.b;
                        if (z0Var.a.isChecked()) {
                            t9.a().b(1L);
                        } else {
                            t9.a().b(System.currentTimeMillis());
                        }
                        z0Var.dismiss();
                        break;
                    default:
                        z0 z0Var2 = this.b;
                        if (z0Var2.a.isChecked()) {
                            t9.a().b(1L);
                        } else {
                            t9.a().b(0L);
                        }
                        z0Var2.dismiss();
                        break;
                }
            }
        });
        if (BydCarDriverType.isRightDriver(getContext())) {
            if (a1.a()) {
                this.d.setBackgroundResource(R$drawable.bg_dialog_button_cancel_denza);
                this.c.setBackgroundResource(R$drawable.bg_dialog_button_confirm_denza);
            } else {
                this.d.setBackgroundResource(R$drawable.bg_dialog_button_cancel_right);
                this.c.setBackgroundResource(R$drawable.bg_dialog_button_confirm_right);
            }
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.c.getLayoutParams();
            layoutParams.startToEnd = this.d.getId();
            layoutParams.endToEnd = 0;
            layoutParams.startToStart = -1;
            layoutParams.endToStart = -1;
            this.c.setLayoutParams(layoutParams);
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.d.getLayoutParams();
            layoutParams2.startToStart = 0;
            layoutParams2.endToStart = this.c.getId();
            layoutParams2.endToEnd = -1;
            layoutParams2.startToEnd = -1;
            this.d.setLayoutParams(layoutParams2);
        }
    }

    @Override // android.app.Dialog
    public final void show() {
        if (isShowing()) {
            dismiss();
        }
        super.show();
        y0 y0Var = this.e;
        if (y0Var != null) {
            y0Var.cancel();
            this.e = null;
        }
        if (this.e == null) {
            if (this.f == -1) {
                this.f = 10000L;
            }
            a(this.f);
            y0 y0Var2 = new y0(this, this.f, 1000L);
            this.e = y0Var2;
            y0Var2.start();
        }
    }
}
