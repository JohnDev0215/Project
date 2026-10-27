package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;
import defpackage.ao3;
import java.util.ArrayList;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;

/* JADX INFO: loaded from: classes2.dex */
public final class RadioButtonWithDescriptionLayout extends RadioGroup implements RadioButtonWithDescription.a {
    public final ArrayList e;
    public RadioGroup.OnCheckedChangeListener f;

    public RadioButtonWithDescriptionLayout(Context context) {
        this(context, null);
    }

    private void setupButton(RadioButtonWithDescription radioButtonWithDescription) {
        radioButtonWithDescription.setOnCheckedChangeListener(this);
        if (radioButtonWithDescription.getId() == -1) {
            radioButtonWithDescription.setId(View.generateViewId());
        }
        ArrayList arrayList = this.e;
        radioButtonWithDescription.setRadioButtonGroup(arrayList);
        arrayList.add(radioButtonWithDescription);
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription.a
    public final void j(RadioButtonWithDescription radioButtonWithDescription) {
        RadioGroup.OnCheckedChangeListener onCheckedChangeListener = this.f;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(this, radioButtonWithDescription.getId());
        }
    }

    @Override // android.widget.RadioGroup, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            setupButton((RadioButtonWithDescription) getChildAt(i));
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        for (int i = 0; i < getChildCount(); i++) {
            ao3.g(getChildAt(i), z);
        }
    }

    @Override // android.widget.RadioGroup
    public void setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener onCheckedChangeListener) {
        this.f = onCheckedChangeListener;
    }

    public RadioButtonWithDescriptionLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new ArrayList();
    }
}
