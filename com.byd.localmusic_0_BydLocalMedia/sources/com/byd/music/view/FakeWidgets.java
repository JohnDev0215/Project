package com.byd.music.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public class FakeWidgets extends ConstraintLayout {
    public FakeWidgets(Context context) {
        super(context);
        init();
    }

    public FakeWidgets(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public FakeWidgets(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_fake_widgets, (ViewGroup) this, true);
    }
}
