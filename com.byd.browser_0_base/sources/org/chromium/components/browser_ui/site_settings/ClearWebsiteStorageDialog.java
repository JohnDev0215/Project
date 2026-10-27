package org.chromium.components.browser_ui.site_settings;

import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.preference.Preference;
import defpackage.n3;
import defpackage.ve3;
import org.chromium.base.Callback;

/* JADX INFO: loaded from: classes2.dex */
public class ClearWebsiteStorageDialog extends androidx.preference.c {
    public static Callback<Boolean> z0;
    public View x0;
    public Dialog y0;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            ClearWebsiteStorageDialog clearWebsiteStorageDialog = ClearWebsiteStorageDialog.this;
            Dialog dialog = clearWebsiteStorageDialog.y0;
            if (dialog != null) {
                dialog.dismiss();
                clearWebsiteStorageDialog.P0(true);
            }
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Dialog dialog = ClearWebsiteStorageDialog.this.y0;
            if (dialog != null) {
                dialog.dismiss();
            }
        }
    }

    public static ClearWebsiteStorageDialog S0(Preference preference, Callback<Boolean> callback, boolean z, boolean z2) {
        ClearWebsiteStorageDialog clearWebsiteStorageDialog = new ClearWebsiteStorageDialog();
        z0 = callback;
        Bundle bundle = new Bundle(3);
        bundle.putString("key", preference.p);
        bundle.putBoolean("should_show_ad_personalization_row", z);
        bundle.putBoolean("is_group", z2);
        clearWebsiteStorageDialog.C0(bundle);
        return clearWebsiteStorageDialog;
    }

    @Override // androidx.preference.c, defpackage.r90
    public final Dialog J0(Bundle bundle) {
        View viewInflate = LayoutInflater.from(x0()).inflate(R$layout.clear_data_dialog, (ViewGroup) null);
        if (viewInflate != null) {
            O0(viewInflate);
        }
        Dialog dialog = new Dialog(x0(), R$style.Dialog_Style);
        this.y0 = dialog;
        dialog.setContentView(viewInflate);
        this.y0.setCancelable(true);
        Window window = this.y0.getWindow();
        window.setGravity(17);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = K().getResources().getDimensionPixelSize(R$dimen.alert_dialog_max_width);
        attributes.height = -2;
        window.setAttributes(attributes);
        return this.y0;
    }

    @Override // androidx.preference.c
    public final void O0(View view) {
        this.x0 = view;
        TextView textView = (TextView) view.findViewById(R$id.signed_out_text);
        TextView textView2 = (TextView) view.findViewById(R$id.offline_text);
        textView.setText(this.j.getBoolean("is_group", false) ? R$string.webstorage_clear_data_dialog_sign_out_group_message : R$string.webstorage_clear_data_dialog_sign_out_message);
        textView2.setText(R$string.webstorage_clear_data_dialog_offline_message);
        if (this.j.getBoolean("should_show_ad_personalization_row", false)) {
            ((RelativeLayout) this.x0.findViewById(R$id.ad_personalization)).setVisibility(0);
        }
        Button button = (Button) view.findViewById(R$id.btn_sure);
        Button button2 = (Button) view.findViewById(R$id.btn_cancel);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R$id.ll_sure_cancel);
        if (n3.Q0()) {
            linearLayout.setLayoutDirection(1);
        } else {
            linearLayout.setLayoutDirection(0);
        }
        button.setOnClickListener(new a());
        button2.setOnClickListener(new b());
        super.O0(view);
    }

    @Override // androidx.preference.c
    public final void P0(boolean z) {
        z0.onResult(Boolean.valueOf(z));
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        this.H = true;
        View view = this.x0;
        if (view != null) {
            view.getHandler().post(new ve3(10, this));
        }
    }
}
