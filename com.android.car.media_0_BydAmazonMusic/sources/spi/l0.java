package spi;

import android.app.Dialog;
import android.car.drivingstate.BydCarDriverType;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.car.media.main.R$drawable;
import com.android.car.media.main.R$id;
import com.android.car.media.main.R$layout;
import com.android.car.media.main.R$style;
import spi.l0;

/* JADX INFO: compiled from: BydAlertDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class l0 extends Dialog {
    public AppCompatTextView a;
    public AppCompatTextView b;
    public AppCompatTextView c;
    public CharSequence d;
    public CharSequence e;
    public CharSequence f;
    public a g;
    public a h;

    /* JADX INFO: compiled from: BydAlertDialog.java */
    public interface a {
    }

    public l0(@NonNull Context context) {
        super(context, R$style.CountDownDialogStyle);
    }

    @Override // android.app.Dialog
    public final void onCreate(Bundle bundle) {
        AppCompatTextView appCompatTextView;
        super.onCreate(bundle);
        int i = R$layout.layout_alert_dialog;
        setContentView(i);
        if (a1.a()) {
            setContentView(R$layout.layout_alert_dialog_denza);
        } else {
            setContentView(i);
        }
        this.a = (AppCompatTextView) findViewById(R$id.tv_dialog_message);
        this.c = (AppCompatTextView) findViewById(R$id.tv_dialog_confirm);
        this.b = (AppCompatTextView) findViewById(R$id.tv_dialog_cancel);
        CharSequence charSequence = this.d;
        if (charSequence != null && (appCompatTextView = this.a) != null) {
            appCompatTextView.setText(charSequence);
        }
        AppCompatTextView appCompatTextView2 = this.c;
        final int i2 = 0;
        if (appCompatTextView2 != null) {
            appCompatTextView2.setVisibility(this.f != null ? 0 : 8);
            CharSequence charSequence2 = this.f;
            if (charSequence2 != null) {
                this.c.setText(charSequence2);
                this.c.setOnClickListener(new View.OnClickListener(this) { // from class: spi.k0
                    public final /* synthetic */ l0 b;

                    {
                        this.b = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        switch (i2) {
                            case 0:
                                l0.a aVar = this.b.h;
                                if (aVar != null) {
                                    ((b6) aVar).c();
                                }
                                break;
                            default:
                                l0 l0Var = this.b;
                                l0Var.dismiss();
                                l0.a aVar2 = l0Var.g;
                                if (aVar2 != null) {
                                    ((b6) aVar2).c();
                                }
                                break;
                        }
                    }
                });
            }
        }
        AppCompatTextView appCompatTextView3 = this.b;
        if (appCompatTextView3 != null) {
            appCompatTextView3.setVisibility(this.e != null ? 0 : 8);
            CharSequence charSequence3 = this.e;
            if (charSequence3 != null) {
                this.b.setText(charSequence3);
                final int i3 = 1;
                this.b.setOnClickListener(new View.OnClickListener(this) { // from class: spi.k0
                    public final /* synthetic */ l0 b;

                    {
                        this.b = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        switch (i3) {
                            case 0:
                                l0.a aVar = this.b.h;
                                if (aVar != null) {
                                    ((b6) aVar).c();
                                }
                                break;
                            default:
                                l0 l0Var = this.b;
                                l0Var.dismiss();
                                l0.a aVar2 = l0Var.g;
                                if (aVar2 != null) {
                                    ((b6) aVar2).c();
                                }
                                break;
                        }
                    }
                });
            }
        }
        AppCompatTextView appCompatTextView4 = this.b;
        if (appCompatTextView4 != null && this.c != null) {
            appCompatTextView4.setBackgroundResource((appCompatTextView4.getVisibility() == 0 && this.c.getVisibility() == 8) ? a1.a() ? R$drawable.bg_dialog_bottom_all_denza : R$drawable.bg_dialog_bottom_all : a1.a() ? R$drawable.bg_dialog_button_cancel_denza : R$drawable.bg_dialog_button_cancel);
            AppCompatTextView appCompatTextView5 = this.c;
            appCompatTextView5.setBackgroundResource((appCompatTextView5.getVisibility() == 0 && this.b.getVisibility() == 8) ? a1.a() ? R$drawable.bg_dialog_bottom_all_denza : R$drawable.bg_dialog_bottom_all : a1.a() ? R$drawable.bg_dialog_button_confirm_denza : R$drawable.bg_dialog_button_confirm);
        }
        if (BydCarDriverType.isRightDriver(getContext())) {
            if (a1.a()) {
                this.b.setBackgroundResource(R$drawable.bg_dialog_button_cancel_denza);
                this.c.setBackgroundResource(R$drawable.bg_dialog_button_confirm_denza);
            } else {
                this.b.setBackgroundResource(R$drawable.bg_dialog_button_cancel_right);
                this.c.setBackgroundResource(R$drawable.bg_dialog_button_confirm_right);
            }
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.c.getLayoutParams();
            layoutParams.startToEnd = this.b.getId();
            layoutParams.endToEnd = 0;
            layoutParams.startToStart = -1;
            layoutParams.endToStart = -1;
            this.c.setLayoutParams(layoutParams);
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.b.getLayoutParams();
            layoutParams2.startToStart = 0;
            layoutParams2.endToStart = this.c.getId();
            layoutParams2.endToEnd = -1;
            layoutParams2.startToEnd = -1;
            this.b.setLayoutParams(layoutParams2);
        }
    }

    public l0(@NonNull Context context, int i) {
        super(context, i);
    }

    public l0(@NonNull Context context, boolean z, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }
}
