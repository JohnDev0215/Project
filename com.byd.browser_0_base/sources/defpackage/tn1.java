package defpackage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatButton;
import org.chromium.base.Callback;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$style;

/* JADX INFO: compiled from: NoProtectionConfirmationDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tn1 extends Dialog implements View.OnClickListener {
    public final Callback<Boolean> e;

    public tn1(Context context, on0 on0Var) {
        super(context, R$style.Dialog_Style);
        this.e = on0Var;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int id = view.getId();
        int i = R$id.btn_sure;
        Callback<Boolean> callback = this.e;
        if (id == i) {
            callback.onResult(Boolean.TRUE);
        } else if (id == R$id.btn_cancel) {
            callback.onResult(Boolean.FALSE);
        }
        dismiss();
    }

    @Override // android.app.Dialog
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.dialog_no_protection_confirmation);
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.ll_sure_cancel);
        if (n3.Q0()) {
            linearLayout.setLayoutDirection(1);
        } else {
            linearLayout.setLayoutDirection(0);
        }
        ((AppCompatButton) findViewById(R$id.btn_sure)).setOnClickListener(this);
        ((AppCompatButton) findViewById(R$id.btn_cancel)).setOnClickListener(this);
        Window window = getWindow();
        window.setGravity(17);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = getContext().getResources().getDimensionPixelSize(R$dimen.alert_dialog_max_width);
        attributes.height = -2;
        window.setAttributes(attributes);
    }

    @Override // android.app.Dialog
    public final void show() {
        super.show();
    }
}
