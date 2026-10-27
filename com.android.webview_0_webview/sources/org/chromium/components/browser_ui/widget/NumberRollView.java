package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import defpackage.AbstractC0371Um;
import defpackage.Uw;
import java.text.NumberFormat;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NumberRollView extends FrameLayout {
    public TextView s;
    public TextView t;
    public float u;

    static {
        new Uw(Float.class, "");
    }

    public NumberRollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void b(float f) {
        this.u = f;
        int i = (int) f;
        NumberFormat integerInstance = NumberFormat.getIntegerInstance();
        String str = integerInstance.format(i + 1);
        if (!str.equals(this.s.getText().toString())) {
            this.s.setText(str);
        }
        String str2 = integerInstance.format(i);
        if (!str2.equals(this.t.getText().toString())) {
            this.t.setText(str2);
        }
        float f2 = f % 1.0f;
        this.s.setTranslationY((f2 - 1.0f) * r1.getHeight());
        this.t.setTranslationY(r1.getHeight() * f2);
        this.s.setAlpha(f2);
        this.t.setAlpha(1.0f - f2);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.s = (TextView) findViewById(AbstractC0371Um.i5);
        this.t = (TextView) findViewById(AbstractC0371Um.T0);
        b(this.u);
    }
}
