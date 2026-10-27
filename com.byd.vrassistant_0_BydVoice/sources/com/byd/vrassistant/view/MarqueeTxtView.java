package com.byd.vrassistant.view;

import android.R;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

/* JADX INFO: loaded from: classes2.dex */
public class MarqueeTxtView extends AppCompatTextView {
    @Override // android.view.View
    public int getFocusable() {
        return 1;
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    public MarqueeTxtView(Context context) {
        this(context, null);
    }

    public MarqueeTxtView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public MarqueeTxtView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setMarqueeRepeatLimit(-1);
        setSingleLine(true);
        setHorizontallyScrolling(true);
        setSelected(true);
    }
}
