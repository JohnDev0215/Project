package spi;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.car.media.main.R$color;
import com.android.car.media.main.R$id;
import com.android.car.media.main.R$layout;
import com.android.car.media.main.R$string;
import com.android.car.media.main.R$style;

/* JADX INFO: compiled from: BydCountDownDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class q0 extends Dialog {
    public a a;
    public long b;
    public TextView c;
    public TextView d;
    public b e;

    /* JADX INFO: compiled from: BydCountDownDialog.java */
    public class a extends CountDownTimer {
        public a(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public final void onFinish() {
            q0 q0Var = q0.this;
            q0Var.b = -1L;
            b bVar = q0Var.e;
            if (bVar != null) {
                ((q6) bVar).c();
            }
        }

        @Override // android.os.CountDownTimer
        public final void onTick(long j) {
            q0 q0Var = q0.this;
            q0Var.b = j;
            q0Var.a(j);
        }
    }

    /* JADX INFO: compiled from: BydCountDownDialog.java */
    public interface b {
    }

    public q0(@NonNull Context context) {
        super(context, R$style.CountDownDialogStyle);
        this.b = -1L;
    }

    public final void a(long j) {
        TextView textView;
        long jMin = Math.min((j / 1000) + 1, 10L);
        String string = getContext().getString(R$string.dialog_confirm);
        String str = string + " (" + jMin + "s)";
        int iIndexOf = str.indexOf(string);
        int length = string.length();
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(getContext().getColor(R$color.dialog_button_text_confirm_color)), iIndexOf, length, 17);
        if (TextUtils.isEmpty(spannableString) || (textView = this.c) == null) {
            return;
        }
        textView.setText(spannableString);
        this.c.setVisibility(0);
    }

    public final void b() {
        if (this.a == null) {
            if (this.b == -1) {
                this.b = 10000L;
            }
            a(this.b);
            a aVar = new a(this.b, 1000L);
            this.a = aVar;
            aVar.start();
        }
    }

    public final void c() {
        a aVar = this.a;
        if (aVar != null) {
            aVar.cancel();
            this.a = null;
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public final void dismiss() {
        super.dismiss();
        c();
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
        if (a1.a()) {
            setContentView(R$layout.layout_count_down_dialog_denza);
        } else {
            setContentView(R$layout.layout_count_down_dialog);
        }
        setCanceledOnTouchOutside(false);
        this.c = (TextView) findViewById(R$id.tv_dialog_confirm_seconds);
        this.d = (TextView) findViewById(R$id.tv_dialog_message);
        ((ConstraintLayout) findViewById(R$id.cl_dialog_bottom)).setOnClickListener(new k8(this, 1));
    }

    @Override // android.app.Dialog
    public final void show() {
        if (isShowing()) {
            dismiss();
        }
        super.show();
        c();
        b();
    }
}
