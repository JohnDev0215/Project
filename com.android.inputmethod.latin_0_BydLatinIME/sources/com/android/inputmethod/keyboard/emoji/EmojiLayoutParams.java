package com.android.inputmethod.keyboard.emoji;

import android.content.res.Resources;
import android.view.View;
import android.widget.LinearLayout;
import androidx.viewpager.widget.ViewPager;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.utils.ResourceUtils;

/* JADX INFO: loaded from: classes2.dex */
final class EmojiLayoutParams {
    private static final int DEFAULT_KEYBOARD_ROWS = 4;
    private final int mBottomPadding;
    public final int mEmojiActionBarHeight;
    private final int mEmojiCategoryPageIdViewHeight;
    public final int mEmojiKeyboardHeight;
    private final int mEmojiPagerBottomMargin;
    public final int mEmojiPagerHeight;
    private final int mKeyHorizontalGap;
    public final int mKeyVerticalGap;
    private final int mTopPadding;

    public EmojiLayoutParams(final Resources res) {
        int defaultKeyboardHeight = ResourceUtils.getDefaultKeyboardHeight(res);
        int defaultKeyboardWidth = ResourceUtils.getDefaultKeyboardWidth(res);
        int fraction = (int) res.getFraction(R.fraction.config_key_vertical_gap_holo, defaultKeyboardHeight, defaultKeyboardHeight);
        this.mKeyVerticalGap = fraction;
        int fraction2 = (int) res.getFraction(R.fraction.config_keyboard_bottom_padding_holo, defaultKeyboardHeight, defaultKeyboardHeight);
        this.mBottomPadding = fraction2;
        int fraction3 = (int) res.getFraction(R.fraction.config_keyboard_top_padding_holo, defaultKeyboardHeight, defaultKeyboardHeight);
        this.mTopPadding = fraction3;
        this.mKeyHorizontalGap = (int) res.getFraction(R.fraction.config_key_horizontal_gap_holo, defaultKeyboardWidth, defaultKeyboardWidth);
        int dimension = (int) res.getDimension(R.dimen.config_emoji_category_page_id_height);
        this.mEmojiCategoryPageIdViewHeight = dimension;
        int i = ((((defaultKeyboardHeight - fraction2) - fraction3) + fraction) / 4) - ((fraction - fraction2) / 2);
        this.mEmojiActionBarHeight = i;
        this.mEmojiPagerHeight = (defaultKeyboardHeight - i) - dimension;
        this.mEmojiPagerBottomMargin = 0;
        this.mEmojiKeyboardHeight = (r0 - 0) - 1;
    }

    public void setPagerProperties(final ViewPager vp) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) vp.getLayoutParams();
        layoutParams.height = this.mEmojiKeyboardHeight;
        layoutParams.bottomMargin = this.mEmojiPagerBottomMargin;
        vp.setLayoutParams(layoutParams);
    }

    public void setCategoryPageIdViewProperties(final View v) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) v.getLayoutParams();
        layoutParams.height = this.mEmojiCategoryPageIdViewHeight;
        v.setLayoutParams(layoutParams);
    }

    public int getActionBarHeight() {
        return this.mEmojiActionBarHeight - this.mBottomPadding;
    }

    public void setActionBarProperties(final LinearLayout ll) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll.getLayoutParams();
        layoutParams.height = getActionBarHeight();
        ll.setLayoutParams(layoutParams);
    }

    public void setKeyProperties(final View v) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) v.getLayoutParams();
        layoutParams.leftMargin = this.mKeyHorizontalGap / 2;
        layoutParams.rightMargin = this.mKeyHorizontalGap / 2;
        v.setLayoutParams(layoutParams);
    }
}
