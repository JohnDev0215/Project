package com.byd.engineermode.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;

/* JADX INFO: compiled from: BydCustomDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends Dialog {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static BaseActivity f2370b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static b f2371c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Context f2372d;
    private int e;
    private int f;
    HorizontalProgressView g;

    /* JADX INFO: compiled from: BydCustomDialog.java */
    class a implements DialogInterface.OnCancelListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ AlertDialog f2373b;

        a(AlertDialog alertDialog) {
            this.f2373b = alertDialog;
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            this.f2373b.dismiss();
        }
    }

    /* JADX INFO: renamed from: com.byd.engineermode.ui.dialog.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydCustomDialog.java */
    class ViewOnClickListenerC0069b implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ e f2375b;

        ViewOnClickListenerC0069b(e eVar) {
            this.f2375b = eVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f2375b.onFinish();
        }
    }

    /* JADX INFO: compiled from: BydCustomDialog.java */
    class c implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f2377b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ AlertDialog f2378c;

        c(int i, AlertDialog alertDialog) {
            this.f2377b = i;
            this.f2378c = alertDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f2377b == 0) {
                b.f2370b.J();
            } else {
                b.f2370b.G(this.f2377b);
            }
            this.f2378c.dismiss();
        }
    }

    /* JADX INFO: compiled from: BydCustomDialog.java */
    class d implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f2380b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ AlertDialog f2381c;

        d(int i, AlertDialog alertDialog) {
            this.f2380b = i;
            this.f2381c = alertDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f2380b == 0) {
                b.f2370b.I();
            }
            this.f2381c.dismiss();
        }
    }

    /* JADX INFO: compiled from: BydCustomDialog.java */
    class e extends CountDownTimer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Dialog f2383a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Button f2384b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2385c;

        public e(long j, long j2, Dialog dialog, Button button, boolean z) {
            super(j, j2);
            this.f2383a = dialog;
            this.f2384b = button;
            this.f2385c = z;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            Dialog dialog = this.f2383a;
            if (dialog != null) {
                dialog.dismiss();
            }
            cancel();
            if (this.f2385c) {
                b.f2370b.J();
            }
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            this.f2384b.setText(b.f2370b.getString(R.string.str_upgrade_exit, new Object[]{String.valueOf(j / 1000)}));
        }
    }

    private b(Context context) {
        super(context);
        this.e = 20000;
        this.f = 1000;
        this.f2372d = context;
    }

    public static b b(Context context) {
        b bVar = new b(context);
        f2371c = bVar;
        f2370b = (BaseActivity) context;
        return bVar;
    }

    public void c(int i) {
        HorizontalProgressView horizontalProgressView = this.g;
        if (horizontalProgressView != null) {
            horizontalProgressView.setFinishedTextId(i);
        }
    }

    public void d(int i) {
        HorizontalProgressView horizontalProgressView = this.g;
        if (horizontalProgressView != null) {
            horizontalProgressView.setProgress(i);
        }
    }

    public Dialog e(int i) {
        View viewInflate = LayoutInflater.from(this.f2372d).inflate(R.layout.upload_wait_progress_dialog, (ViewGroup) null);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this.f2372d, R.style.MyDialog).setCancelable(false).create();
        alertDialogCreate.show();
        ((TextView) viewInflate.findViewById(R.id.text_view)).setText(i);
        this.g = (HorizontalProgressView) viewInflate.findViewById(R.id.progress_bar);
        alertDialogCreate.setContentView(viewInflate);
        alertDialogCreate.setCanceledOnTouchOutside(false);
        Window window = alertDialogCreate.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        window.setAttributes(attributes);
        alertDialogCreate.setOnCancelListener(new a(alertDialogCreate));
        return alertDialogCreate;
    }

    public AlertDialog f(String str, String str2, int i) {
        View viewInflate = LayoutInflater.from(this.f2372d).inflate(R.layout.dialog_test_pass, (ViewGroup) null);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this.f2372d, R.style.MyDialog).setCancelable(false).create();
        alertDialogCreate.show();
        alertDialogCreate.setCanceledOnTouchOutside(false);
        Button button = (Button) viewInflate.findViewById(R.id.btn_confirm);
        Button button2 = (Button) viewInflate.findViewById(R.id.btn_cancel);
        View viewFindViewById = viewInflate.findViewById(R.id.divider_center_line);
        button2.setVisibility(0);
        viewFindViewById.setVisibility(4);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_message);
        textView.setText(str);
        textView2.setText(str2);
        if (i == 0 || i == 2) {
            textView2.setTextColor(f2370b.getResources().getColor(R.color.red));
        }
        if (i == 0) {
            button.setText(R.string.dialog_test_pass);
            button2.setText(R.string.dialog_test_fail);
        } else if (i == 1 || i == 3 || i == 5) {
            button.setText(R.string.str_dialog_upgrade_begin);
            button2.setText(R.string.str_dialog_upgrade_cancel);
        } else if (i == 2 || i == 4) {
            button.setText(R.string.str_dialog_upgrade_again);
            button2.setText(R.string.str_dialog_upgrade_cancel);
        }
        alertDialogCreate.setContentView(viewInflate);
        alertDialogCreate.getWindow().setBackgroundDrawable(this.f2372d.getResources().getDrawable(R.drawable.dialog_bg_shape));
        button.setOnClickListener(new c(i, alertDialogCreate));
        button2.setOnClickListener(new d(i, alertDialogCreate));
        return alertDialogCreate;
    }

    public AlertDialog g(String str, String str2, boolean z) {
        View viewInflate = LayoutInflater.from(this.f2372d).inflate(R.layout.dialog_test_pass, (ViewGroup) null);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this.f2372d, R.style.MyDialog).setCancelable(false).create();
        alertDialogCreate.show();
        alertDialogCreate.setCanceledOnTouchOutside(true);
        Button button = (Button) viewInflate.findViewById(R.id.btn_confirm);
        Button button2 = (Button) viewInflate.findViewById(R.id.btn_cancel);
        View viewFindViewById = viewInflate.findViewById(R.id.divider_center_line);
        button2.setVisibility(8);
        viewFindViewById.setVisibility(8);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_message);
        textView.setText(str);
        textView2.setText(str2);
        e eVar = new e(this.e, this.f, alertDialogCreate, button, z);
        eVar.start();
        alertDialogCreate.setContentView(viewInflate);
        alertDialogCreate.getWindow().setBackgroundDrawable(this.f2372d.getResources().getDrawable(R.drawable.dialog_bg_shape));
        button.setOnClickListener(new ViewOnClickListenerC0069b(eVar));
        return alertDialogCreate;
    }
}
