package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageButton;
import defpackage.vt;

/* JADX INFO: loaded from: classes2.dex */
public class RadioButtonWithDescriptionAndAuxButton extends RadioButtonWithDescription {
    public a k;
    public ImageButton l;

    public interface a {
        void k(int i);
    }

    public RadioButtonWithDescriptionAndAuxButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPaddingRelative(getPaddingStart(), getPaddingTop(), 0, getPaddingBottom());
        View viewFindViewById = findViewById(R$id.radio_container);
        viewFindViewById.setPaddingRelative(viewFindViewById.getPaddingStart(), viewFindViewById.getPaddingTop(), getResources().getDimensionPixelSize(R$dimen.radio_button_with_description_and_aux_button_spacing), viewFindViewById.getPaddingBottom());
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription
    public final void c() {
        super.c();
        this.l = (ImageButton) findViewById(R$id.expand_arrow);
    }

    public ImageButton getAuxButtonForTests() {
        return this.l;
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription
    public int getEndStubLayoutResourceId() {
        return R$layout.expand_arrow_with_separator;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setLabeledBy(this.l);
    }

    public void setAuxButtonClickedListener(a aVar) {
        this.k = aVar;
        this.l.setOnClickListener(new vt(27, this));
    }

    public void setAuxButtonContentDescription(String str) {
        this.l.setContentDescription(str);
    }

    public void setAuxButtonEnabled(boolean z) {
        this.l.setEnabled(z);
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription, android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setAuxButtonEnabled(z);
    }
}
