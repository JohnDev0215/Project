package com.android.inputmethod.keyboard.internal;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.latin.R;
import java.util.HashSet;

/* JADX INFO: loaded from: classes2.dex */
public class KeyPreviewView extends TextView {
    public static final int POSITION_LEFT = 1;
    public static final int POSITION_MIDDLE = 0;
    public static final int POSITION_RIGHT = 2;
    private static final int STATE_HAS_MOREKEYS = 1;
    private static final int STATE_NORMAL = 0;
    private final Rect mBackgroundPadding;
    private static final HashSet<String> sNoScaleXTextSet = new HashSet<>();
    private static final int[][][] KEY_PREVIEW_BACKGROUND_STATE_TABLE = {new int[][]{new int[0], new int[]{R.attr.state_has_morekeys}}, new int[][]{new int[]{R.attr.state_left_edge}, new int[]{R.attr.state_left_edge, R.attr.state_has_morekeys}}, new int[][]{new int[]{R.attr.state_right_edge}, new int[]{R.attr.state_right_edge, R.attr.state_has_morekeys}}};

    public KeyPreviewView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyPreviewView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mBackgroundPadding = new Rect();
        setGravity(17);
    }

    public void setPreviewVisual(final Key key, final KeyboardIconsSet iconsSet, final KeyDrawParams drawParams) {
        if (key.getIconId() != 0) {
            setCompoundDrawables(null, null, null, key.getPreviewIcon(iconsSet));
            setText((CharSequence) null);
            return;
        }
        setCompoundDrawables(null, null, null, null);
        setTextColor(drawParams.mPreviewTextColor);
        setTextSize(0, key.selectPreviewTextSize(drawParams));
        setTypeface(key.selectPreviewTypeface(drawParams));
        setTextAndScaleX(key.getPreviewLabel());
    }

    private void setTextAndScaleX(final String text) {
        Drawable background;
        setTextScaleX(1.0f);
        setText(text);
        HashSet<String> hashSet = sNoScaleXTextSet;
        if (hashSet.contains(text) || (background = getBackground()) == null) {
            return;
        }
        background.getPadding(this.mBackgroundPadding);
        int intrinsicWidth = (background.getIntrinsicWidth() - this.mBackgroundPadding.left) - this.mBackgroundPadding.right;
        float textWidth = getTextWidth(text, getPaint());
        float f = intrinsicWidth;
        if (textWidth <= f) {
            hashSet.add(text);
        } else {
            setTextScaleX(f / textWidth);
        }
    }

    public static void clearTextCache() {
        sNoScaleXTextSet.clear();
    }

    private static float getTextWidth(final String text, final TextPaint paint) {
        float f = 0.0f;
        if (TextUtils.isEmpty(text)) {
            return 0.0f;
        }
        int length = text.length();
        float[] fArr = new float[length];
        int textWidths = paint.getTextWidths(text, 0, length, fArr);
        for (int i = 0; i < textWidths; i++) {
            f += fArr[i];
        }
        return f;
    }

    public void setPreviewBackground(boolean z, int i) {
        Drawable background = getBackground();
        if (background == null) {
            return;
        }
        background.setState(KEY_PREVIEW_BACKGROUND_STATE_TABLE[i][z ? 1 : 0]);
    }
}
