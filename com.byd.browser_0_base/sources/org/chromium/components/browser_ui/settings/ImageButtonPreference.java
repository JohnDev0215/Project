package org.chromium.components.browser_ui.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.Preference;
import defpackage.n32;
import org.chromium.ui.widget.ChromeImageButton;

/* JADX INFO: loaded from: classes2.dex */
public class ImageButtonPreference extends ChromeBasePreference implements View.OnClickListener {
    public int Y;
    public String Z;

    public ImageButtonPreference(Context context) {
        super(context);
        O();
        this.K = R$layout.image_button_widget;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Preference.d dVar = this.j;
        if (dVar != null) {
            dVar.r(this);
        }
    }

    @Override // org.chromium.components.browser_ui.settings.ChromeBasePreference, androidx.preference.Preference
    public void y(n32 n32Var) {
        super.y(n32Var);
        ChromeImageButton chromeImageButton = (ChromeImageButton) n32Var.u(R$id.image_button);
        chromeImageButton.setImageResource(this.Y);
        String str = this.Z;
        if (str != null) {
            chromeImageButton.setContentDescription(str);
        }
        chromeImageButton.setOnClickListener(this);
    }

    public ImageButtonPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        O();
        this.K = R$layout.image_button_widget;
    }
}
