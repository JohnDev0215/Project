package org.chromium.components.browser_ui.settings;

import android.R;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import defpackage.n32;

/* JADX INFO: loaded from: classes2.dex */
public class TextMessagePreference extends ChromeBasePreference {
    public TextView Y;
    public MovementMethod Z;

    public TextMessagePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.Z = LinkMovementMethod.getInstance();
        O();
        P();
    }

    @Override // org.chromium.components.browser_ui.settings.ChromeBasePreference, androidx.preference.Preference
    public void y(n32 n32Var) {
        super.y(n32Var);
        TextView textView = (TextView) n32Var.u(R.id.summary);
        this.Y = textView;
        MovementMethod movementMethod = this.Z;
        this.Z = movementMethod;
        if (textView != null) {
            textView.setMovementMethod(movementMethod);
        }
    }
}
