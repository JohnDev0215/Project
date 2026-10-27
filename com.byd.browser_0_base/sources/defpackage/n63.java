package defpackage;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$style;

/* JADX INFO: compiled from: TabsPageHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class n63 {
    public final y13 a;
    public final Context b;

    public n63(Context context, a23 a23Var) {
        this.b = context;
        this.a = a23Var;
    }

    public final void a() {
        if (this.a.p() <= 0) {
            return;
        }
        Context context = this.b;
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.custom_tabs_dialog_view, (ViewGroup) null);
        Dialog dialog = new Dialog(context, R$style.Dialog_Style);
        dialog.setContentView(viewInflate);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setGravity(17);
        WindowManager.LayoutParams attributes = window.getAttributes();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        attributes.width = (Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) * 4) / 5;
        attributes.height = -2;
        window.setAttributes(attributes);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        Button button = (Button) viewInflate.findViewById(R$id.btn_delete_dialog_confirm);
        Button button2 = (Button) viewInflate.findViewById(R$id.btn_delete_dialog_cancle);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R$id.ll_sure_cancel);
        if (n3.Q0()) {
            linearLayout.setLayoutDirection(1);
        } else {
            linearLayout.setLayoutDirection(0);
        }
        button.setOnClickListener(new c30(this, 6, dialog));
        button2.setOnClickListener(new ie0(23, dialog));
    }
}
