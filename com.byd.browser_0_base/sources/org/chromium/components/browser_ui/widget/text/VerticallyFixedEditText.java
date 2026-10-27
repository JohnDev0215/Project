package org.chromium.components.browser_ui.widget.text;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;

/* JADX INFO: loaded from: classes2.dex */
public class VerticallyFixedEditText extends AppCompatEditText {
    public boolean j;

    public VerticallyFixedEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.TextView
    public boolean bringPointIntoView(int i) {
        try {
            this.j = true;
            return super.bringPointIntoView(i);
        } finally {
            this.j = false;
        }
    }

    @Override // android.view.View
    public final void scrollTo(int i, int i2) {
        if (!this.j) {
            i2 = getScrollY();
        }
        super.scrollTo(i, i2);
    }
}
