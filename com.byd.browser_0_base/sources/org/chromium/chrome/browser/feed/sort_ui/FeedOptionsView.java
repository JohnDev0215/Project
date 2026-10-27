package org.chromium.chrome.browser.feed.sort_ui;

import android.content.Context;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import defpackage.fl0;
import defpackage.gl0;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$style;
import org.chromium.components.browser_ui.widget.chips.ChipView;

/* JADX INFO: loaded from: classes.dex */
public class FeedOptionsView extends LinearLayout {
    public LinearLayout e;

    public FeedOptionsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final ChipView a() {
        ChipView chipView = new ChipView(getContext(), null, 0, R$style.SuggestionChip);
        this.e.addView(chipView);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) chipView.getLayoutParams();
        marginLayoutParams.setMarginEnd(getContext().getResources().getDimensionPixelSize(R$dimen.feed_options_chip_margin));
        chipView.setLayoutParams(marginLayoutParams);
        return chipView;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (LinearLayout) findViewById(R$id.chips_container);
    }

    public void setVisibility(boolean z) {
        if ((getVisibility() == 0) == z) {
            return;
        }
        if (!z) {
            if (getParent() == null || !((View) getParent()).isShown()) {
                setVisibility(8);
                return;
            }
            gl0 gl0Var = new gl0(this, getMeasuredHeight());
            gl0Var.setDuration(200L);
            startAnimation(gl0Var);
            return;
        }
        if (getParent() == null || !((View) getParent()).isShown()) {
            setVisibility(0);
            setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            return;
        }
        measure(View.MeasureSpec.makeMeasureSpec(((ViewGroup) getParent()).getWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredHeight = getMeasuredHeight();
        getLayoutParams().height = 1;
        setVisibility(0);
        fl0 fl0Var = new fl0(this, measuredHeight);
        fl0Var.setDuration(200L);
        startAnimation(fl0Var);
    }
}
