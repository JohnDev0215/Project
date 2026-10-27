package com.byd.music.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.StringUtils;
import com.byd.music.R;
import com.byd.music.utils.LogUtil;

/* JADX INFO: loaded from: classes5.dex */
public class SearchEditText extends AppCompatEditText {
    private Drawable clearDrawable;
    private Drawable searchDrawable;

    public SearchEditText(Context context) {
        super(context);
    }

    public SearchEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LogUtil.d(getClass().toString(), "initSearchEditText");
    }

    public SearchEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initIcon(context);
    }

    private void initIcon(Context context) {
        Drawable drawable = context.getResources().getDrawable(R.drawable.icon_search);
        this.searchDrawable = drawable;
        drawable.setBounds((int) getResources().getDimension(R.dimen.dp_8), 0, ((int) getResources().getDimension(R.dimen.dp_48)) + ((int) getResources().getDimension(R.dimen.dp_8)), (int) getResources().getDimension(R.dimen.dp_48));
        setCompoundDrawables(this.searchDrawable, null, null, null);
        LogUtil.d(getClass().toString(), "The Intrinsic Width is: " + this.searchDrawable.getIntrinsicWidth());
        setBackground(context.getResources().getDrawable(R.drawable.bg_search_et));
        setHint(StringUtils.getString(R.string.search_hint));
        setHintTextColor(getResources().getColor(R.color.text_color_search_hint));
        Drawable drawable2 = context.getResources().getDrawable(R.drawable.icon_clear);
        this.clearDrawable = drawable2;
        drawable2.setBounds(((int) getResources().getDimension(R.dimen.dp_8)) * (-1), 0, (int) getResources().getDimension(R.dimen.dp_40), (int) getResources().getDimension(R.dimen.dp_48));
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            Drawable drawable = getCompoundDrawables()[2];
            if (motionEvent.getRawX() > ((ConstraintLayout) getParent().getParent()).getWidth()) {
                if (drawable != null && motionEvent.getRawX() - (ScreenUtils.getAppScreenWidth() / 2.0f) >= getRight() - drawable.getBounds().width() && motionEvent.getRawX() > ScreenUtils.getAppScreenWidth() / 2.0f) {
                    LogUtil.d(getClass().toString(), "Delete Clicked!");
                    setText("");
                    return true;
                }
            } else if (drawable != null && motionEvent.getRawX() >= getRight() - drawable.getBounds().width()) {
                LogUtil.d(getClass().toString(), "Delete Clicked!");
                setText("");
                return true;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        LogUtil.d(getClass().toString(), "The Text Length is: " + charSequence.length());
        if (charSequence.length() > 0) {
            setCompoundDrawables(this.searchDrawable, null, this.clearDrawable, null);
        } else {
            setCompoundDrawables(this.searchDrawable, null, null, null);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        initIcon(getContext());
    }
}
