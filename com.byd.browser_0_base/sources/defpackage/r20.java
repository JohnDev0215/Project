package defpackage;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import org.chromium.components.browser_ui.site_settings.R$dimen;
import org.chromium.components.browser_ui.site_settings.R$id;
import org.chromium.components.browser_ui.site_settings.R$layout;
import org.chromium.components.browser_ui.site_settings.R$style;

/* JADX INFO: compiled from: CustomProgressDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r20 extends Dialog {
    public r20(Context context, String str) {
        super(context, R$style.ProgressBarDialogStyle);
        setCanceledOnTouchOutside(false);
        setContentView(R$layout.custom_progress_dialog);
        Window window = getWindow();
        window.setGravity(17);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = getContext().getResources().getDimensionPixelSize(R$dimen.progress_dialog_width);
        attributes.height = -2;
        window.setAttributes(attributes);
        ((TextView) findViewById(R$id.progress_message)).setText(str);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public final void dismiss() {
        super.dismiss();
    }
}
