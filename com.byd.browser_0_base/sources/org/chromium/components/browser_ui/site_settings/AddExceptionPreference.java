package org.chromium.components.browser_ui.site_settings;

import J.N;
import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.preference.Preference;
import defpackage.bz;
import defpackage.c6;
import defpackage.n3;
import defpackage.n32;
import defpackage.q3;
import defpackage.r3;
import defpackage.s3;
import defpackage.yn2;

/* JADX INFO: loaded from: classes2.dex */
public class AddExceptionPreference extends Preference implements Preference.d {
    public final int S;
    public final a T;
    public final int U;
    public final String V;
    public final yn2 W;
    public final int X;
    public final int Y;

    public interface a {
    }

    public AddExceptionPreference(Context context, String str, yn2 yn2Var, a aVar) {
        super(context);
        this.V = str;
        this.W = yn2Var;
        this.T = aVar;
        setOnPreferenceClickListener(this);
        M("add_exception");
        Resources resources = this.e.getResources();
        int color = this.e.getColor(R$color.add_exception_color);
        this.U = color;
        this.X = resources.getColor(R$color.default_red);
        this.Y = bz.b(this.e, R$color.default_text_color_list).getDefaultColor();
        Drawable drawableC = c6.c(resources, R$drawable.plus, 0);
        drawableC.mutate();
        drawableC.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        L(drawableC);
        R(resources.getString(R$string.website_settings_add_site));
        this.S = context.getResources().getDimensionPixelSize(R$dimen.add_exception_min_height);
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        int i = R$style.Dialog_Style;
        Context context = this.e;
        Dialog dialog = new Dialog(context, i);
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R$layout.add_site_dialog, (ViewGroup) null);
        EditText editText = (EditText) viewInflate.findViewById(R$id.site);
        ChromeCheckBoxWithDescription chromeCheckBoxWithDescription = (ChromeCheckBoxWithDescription) viewInflate.findViewById(R$id.add_site_dialog_checkbox);
        Button button = (Button) viewInflate.findViewById(R$id.btn_sure);
        Button button2 = (Button) viewInflate.findViewById(R$id.btn_cancel);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R$id.ll_sure_cancel);
        if (n3.Q0()) {
            linearLayout.setLayoutDirection(1);
        } else {
            linearLayout.setLayoutDirection(0);
        }
        TextView textView = (TextView) viewInflate.findViewById(R$id.tv_message);
        if (textView != null) {
            String str = this.V;
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
        }
        int i2 = this.W.b;
        if (i2 == 8) {
            chromeCheckBoxWithDescription.setVisibility(0);
            chromeCheckBoxWithDescription.setPrimaryText(context.getString(R$string.website_settings_third_party_cookies_exception_label));
        } else if (i2 == 24) {
            chromeCheckBoxWithDescription.setChecked(true);
            if (N.MIXz$64w("RequestDesktopSiteExceptions", "SubdomainSettings", true)) {
                chromeCheckBoxWithDescription.setVisibility(0);
                chromeCheckBoxWithDescription.setPrimaryText(context.getString(R$string.website_settings_domain_desktop_site_exception_checkbox_primary));
                chromeCheckBoxWithDescription.setDescriptionText(context.getString(R$string.website_settings_domain_desktop_site_exception_checkbox_description));
            }
        }
        button.setOnClickListener(new org.chromium.components.browser_ui.site_settings.a(this, chromeCheckBoxWithDescription, editText, dialog));
        button2.setOnClickListener(new q3(dialog));
        dialog.setOnShowListener(new r3(editText));
        dialog.getWindow().setContentView(viewInflate);
        Window window = dialog.getWindow();
        window.setGravity(17);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = context.getResources().getDimensionPixelSize(R$dimen.alert_dialog_max_width);
        attributes.height = -2;
        window.setAttributes(attributes);
        dialog.show();
        button.setEnabled(false);
        editText.addTextChangedListener(new s3(this, button, editText));
        return true;
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        n32Var.e.setMinimumHeight(this.S);
        TextView textView = (TextView) n32Var.u(R.id.summary);
        textView.setAllCaps(true);
        textView.setTextColor(this.U);
    }
}
