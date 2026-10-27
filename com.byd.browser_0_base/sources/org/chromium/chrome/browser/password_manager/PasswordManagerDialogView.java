package org.chromium.chrome.browser.password_manager;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import org.chromium.chrome.R$id;
import org.chromium.ui.widget.ChromeImageButton;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordManagerDialogView extends ScrollView {
    public static final /* synthetic */ int j = 0;
    public ChromeImageButton e;
    public ChromeImageButton f;
    public ImageView g;
    public TextView h;
    public TextView i;

    public PasswordManagerDialogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (ChromeImageButton) findViewById(R$id.password_dialog_help_button);
        this.f = (ChromeImageButton) findViewById(R$id.password_dialog_inline_help_button);
        this.g = (ImageView) findViewById(R$id.password_manager_dialog_illustration);
        this.h = (TextView) findViewById(R$id.password_manager_dialog_title);
        this.i = (TextView) findViewById(R$id.password_manager_dialog_details);
    }

    public void setDetails(CharSequence charSequence) {
        this.i.setText(charSequence);
    }

    public void setIllustration(int i) {
        this.g.setImageResource(i);
    }

    public void setTitle(String str) {
        this.h.setText(str);
    }
}
