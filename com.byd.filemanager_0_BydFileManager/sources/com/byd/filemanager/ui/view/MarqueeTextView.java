package com.byd.filemanager.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

/* JADX INFO: loaded from: classes.dex */
public class MarqueeTextView extends AppCompatTextView {
    private int f;

    public MarqueeTextView(Context context) {
        super(context);
        this.f = -1;
        f();
    }

    private void f() {
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(this.f);
        setSingleLine(true);
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    public void setMarqueeNum(int i) {
        this.f = i;
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = -1;
        f();
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = -1;
        f();
    }
}
