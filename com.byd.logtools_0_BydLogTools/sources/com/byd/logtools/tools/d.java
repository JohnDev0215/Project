package com.byd.logtools.tools;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.bydauto.TsNormalNum;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.byd.logtools.R;
import com.byd.logtools.activity.FaultUploadActivity;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: BydCustomDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends Dialog {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f3227b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f3228c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f3229d;

    /* JADX INFO: compiled from: BydCustomDialog.java */
    class a implements DialogInterface.OnCancelListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Dialog f3230b;

        a(Dialog dialog) {
            this.f3230b = dialog;
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            this.f3230b.dismiss();
        }
    }

    /* JADX INFO: compiled from: BydCustomDialog.java */
    class b implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Dialog f3232b;

        b(Dialog dialog) {
            this.f3232b = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3232b.dismiss();
        }
    }

    /* JADX INFO: compiled from: BydCustomDialog.java */
    class c implements DialogInterface.OnDismissListener {
        c() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
        }
    }

    /* JADX INFO: renamed from: com.byd.logtools.tools.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydCustomDialog.java */
    class ViewOnClickListenerC0060d implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ FaultUploadActivity f3235b;

        ViewOnClickListenerC0060d(FaultUploadActivity faultUploadActivity) {
            this.f3235b = faultUploadActivity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3235b.w();
        }
    }

    /* JADX INFO: compiled from: BydCustomDialog.java */
    class e implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Dialog f3237b;

        e(Dialog dialog) {
            this.f3237b = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3237b.dismiss();
        }
    }

    /* JADX INFO: compiled from: BydCustomDialog.java */
    class f implements DialogInterface.OnDismissListener {
        f() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
        }
    }

    /* JADX INFO: compiled from: BydCustomDialog.java */
    class g extends CountDownTimer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Dialog f3240a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Button f3241b;

        public g(long j, long j2, Dialog dialog, Button button) {
            super(j, j2);
            this.f3240a = dialog;
            this.f3241b = button;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            Dialog dialog = this.f3240a;
            if (dialog != null) {
                dialog.cancel();
            }
            cancel();
        }

        @Override // android.os.CountDownTimer
        @SuppressLint({"SetTextI18n"})
        public void onTick(long j) {
            this.f3241b.setText(d.this.f3227b.getString(R.string.logtools_upload_exit, (j / 1000) + HttpUrl.FRAGMENT_ENCODE_SET));
        }
    }

    public d(Context context) {
        super(context);
        this.f3228c = TsNormalNum.THOUSAND_FIVE;
        this.f3229d = 1000;
        this.f3227b = context;
    }

    public Dialog b(int i) {
        Dialog dialog = new Dialog(this.f3227b, R.style.NormalDialogStyle);
        View viewInflate = View.inflate(this.f3227b, R.layout.upload_wait_progress_dialog, null);
        ((TextView) viewInflate.findViewById(R.id.text_view)).setText(i);
        dialog.setContentView(viewInflate);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        window.setAttributes(attributes);
        dialog.show();
        dialog.setOnCancelListener(new a(dialog));
        return dialog;
    }

    public Dialog c(FaultUploadActivity faultUploadActivity) {
        Dialog dialog = new Dialog(this.f3227b, R.style.NormalDialogStyle);
        View viewInflate = View.inflate(this.f3227b, R.layout.upload_fail_dialog, null);
        Button button = (Button) viewInflate.findViewById(R.id.btn_confirm);
        Button button2 = (Button) viewInflate.findViewById(R.id.btn_cancel);
        dialog.setContentView(viewInflate);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        window.setAttributes(attributes);
        button.setOnClickListener(new ViewOnClickListenerC0060d(faultUploadActivity));
        button2.setOnClickListener(new e(dialog));
        dialog.setOnDismissListener(new f());
        dialog.show();
        new g(5000L, 1000L, dialog, button2).start();
        return dialog;
    }

    public Dialog d() {
        Dialog dialog = new Dialog(this.f3227b, R.style.NormalDialogStyle);
        View viewInflate = View.inflate(this.f3227b, R.layout.upload_success_dialog, null);
        Button button = (Button) viewInflate.findViewById(R.id.btn_confirm);
        dialog.setContentView(viewInflate);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        window.setAttributes(attributes);
        button.setOnClickListener(new b(dialog));
        dialog.setOnDismissListener(new c());
        dialog.show();
        new g(5000L, 1000L, dialog, button).start();
        return dialog;
    }
}
