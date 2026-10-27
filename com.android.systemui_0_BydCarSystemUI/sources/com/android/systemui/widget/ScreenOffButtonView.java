package com.android.systemui.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.android.systemui.R;
import com.android.systemui.util.StyleUtil;

/* JADX INFO: loaded from: classes.dex */
public class ScreenOffButtonView extends KeyButtonView {
    public ScreenOffButtonView(Context context) {
        super(context);
    }

    public ScreenOffButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScreenOffButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // com.android.systemui.widget.KeyButtonView
    public int getImageResId() {
        return StyleUtil.isDenza() ? R.mipmap.denza_icon_control_screen_off : R.mipmap.icon_control_screen_off_navi;
    }
}
