package org.chromium.components.browser_ui.site_settings;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import androidx.appcompat.content.res.AppCompatResources;
import org.chromium.components.browser_ui.widget.CheckBoxWithDescription;

/* JADX INFO: loaded from: classes2.dex */
public class ChromeCheckBoxWithDescription extends CheckBoxWithDescription {
    public final CheckBox h;

    public ChromeCheckBoxWithDescription(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = getCheckBoxView();
        a();
    }

    private CheckBox getCheckBoxView() {
        return (CheckBox) findViewById(R$id.checkbox);
    }

    public final void a() {
        CheckBox checkBox = this.h;
        if (checkBox != null) {
            checkBox.setButtonTintMode(PorterDuff.Mode.DST);
            if (this.e.isChecked()) {
                checkBox.setButtonDrawable(AppCompatResources.a(getContext(), R$drawable.check_box_select));
            } else {
                checkBox.setButtonDrawable(AppCompatResources.a(getContext(), R$drawable.check_box_no_select));
            }
        }
    }

    @Override // org.chromium.components.browser_ui.widget.CheckBoxWithDescription, android.view.View.OnClickListener
    public final void onClick(View view) {
        super.onClick(view);
        a();
    }

    @Override // org.chromium.components.browser_ui.widget.CheckBoxWithDescription
    public void setChecked(boolean z) {
        this.h.setChecked(z);
        a();
    }
}
