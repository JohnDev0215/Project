package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: loaded from: classes2.dex */
public class MoreProgressButton extends FrameLayout implements View.OnClickListener {
    public View e;
    public ButtonCompat f;
    public Runnable g;
    public int h;

    public MoreProgressButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = -1;
    }

    public int getStateForTest() {
        return this.h;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        setState(2);
        Runnable runnable = this.g;
        if (runnable != null) {
            runnable.run();
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        ButtonCompat buttonCompat = (ButtonCompat) findViewById(R$id.action_button);
        this.f = buttonCompat;
        buttonCompat.setOnClickListener(this);
        this.e = findViewById(R$id.progress_spinner);
        setState(0);
    }

    public void setButtonText(String str) {
        this.f.setText(str);
    }

    public void setOnClickRunnable(Runnable runnable) {
        this.g = runnable;
    }

    public void setState(int i) {
        if (i == this.h) {
            return;
        }
        this.h = i;
        this.f.setVisibility(1 == i ? 0 : 8);
        this.e.setVisibility(2 != i ? 8 : 0);
    }
}
