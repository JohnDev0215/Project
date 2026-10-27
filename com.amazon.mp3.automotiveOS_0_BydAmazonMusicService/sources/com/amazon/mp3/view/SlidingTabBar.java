package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.AccessibilityUtil;
import com.amazon.mp3.util.ScreenUtil;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class SlidingTabBar extends HorizontalScrollView {
    private final String VIEW_TAG;
    private int mNumberTabs;
    private SlidingTabBarChangeListener mSlidingTabBarChangeListener;
    private final View.OnClickListener mTabClickListener;
    private final SlidingTabStrip mTabStrip;
    private final int mTabTextActiveColor;
    private final int mTabTextInactiveColor;
    private float mTabViewSidePaddingDips;
    private final float mTabViewTextSizeSp;
    private final int mTitleOffset;
    private final float mTitleOffsetDips;

    public interface SlidingTabBarChangeListener {
        void onTabSelected(final int tabNumber);
    }

    public SlidingTabBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingTabBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.VIEW_TAG = "com.amazon.mp3.view.SlidingTabBar.Tab%d";
        this.mTabClickListener = new TabClickListener();
        this.mNumberTabs = 0;
        float dimension = getResources().getDimension(R.dimen.tab_strip_padding);
        this.mTitleOffsetDips = dimension;
        this.mTabViewSidePaddingDips = getResources().getDimension(R.dimen.prime_tab_side_padding);
        this.mTabTextInactiveColor = ContextCompat.getColor(context, R.color.andante_blue_80);
        this.mTabTextActiveColor = ContextCompat.getColor(context, R.color.white);
        this.mTabViewTextSizeSp = getResources().getDimension(R.dimen.prime_tab_text_size);
        setHorizontalScrollBarEnabled(false);
        setFillViewport(true);
        this.mTitleOffset = (int) (dimension + 0.5f);
        SlidingTabStrip slidingTabStrip = new SlidingTabStrip(context);
        this.mTabStrip = slidingTabStrip;
        addView(slidingTabStrip, -1, -2);
    }

    public void setSlidingTabBarChangeListener(final SlidingTabBarChangeListener listener) {
        this.mSlidingTabBarChangeListener = listener;
    }

    protected TextView createDefaultTabView(final Context context) {
        TextView textView = new TextView(context);
        textView.setTextSize(0, this.mTabViewTextSizeSp);
        textView.setAllCaps(true);
        textView.setBackgroundResource(R.drawable.ripple_background);
        textView.setTypeface(Typeface.create("sans-serif-light", 0));
        textView.setPadding(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.spacing_6dp));
        int i = (int) this.mTabViewSidePaddingDips;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(i, 0, i, 0);
        layoutParams.gravity = 80;
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    public void addToTabStrip(final String title) {
        TextView textViewCreateDefaultTabView = createDefaultTabView(getContext());
        textViewCreateDefaultTabView.setGravity(1);
        textViewCreateDefaultTabView.setText(title);
        textViewCreateDefaultTabView.setOnClickListener(this.mTabClickListener);
        String str = String.format(Locale.US, "com.amazon.mp3.view.SlidingTabBar.Tab%d", Integer.valueOf(this.mNumberTabs));
        this.mNumberTabs++;
        textViewCreateDefaultTabView.setTag(str);
        this.mTabStrip.addView(textViewCreateDefaultTabView);
    }

    @NonNull
    private CharSequence getTabContentDescription(final CharSequence title, final boolean selected, final int position, final int maxPostionCount) {
        StringBuilder sb = new StringBuilder();
        sb.append(title);
        sb.append(" ");
        sb.append(getResources().getString(R.string.tab_content_description));
        sb.append(" ");
        if (selected) {
            sb.append(getResources().getString(R.string.selected_content_description));
            sb.append(" ");
        }
        sb.append(getResources().getString(R.string.adapter_position_content_description, Integer.valueOf(position), Integer.valueOf(maxPostionCount)));
        return sb.toString();
    }

    public void scrollToTab(final int tabIndex) {
        View childAt;
        int childCount = this.mTabStrip.getChildCount();
        if (childCount == 0 || tabIndex < 0 || tabIndex >= childCount || (childAt = this.mTabStrip.getChildAt(tabIndex)) == null) {
            return;
        }
        smoothScrollTo((childAt.getLeft() - (ScreenUtil.getDeviceWidth() / 2)) + (childAt.getWidth() / 2), 0);
    }

    @Override // android.view.View
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        for (int i = 0; i < this.mTabStrip.getChildCount(); i++) {
            this.mTabStrip.getChildAt(i).setClickable(enabled);
        }
    }

    public void highlightSelectedTabText(final int position) {
        int i = 0;
        while (i < this.mNumberTabs) {
            TextView textView = (TextView) findViewWithTag(String.format(Locale.US, "com.amazon.mp3.view.SlidingTabBar.Tab%d", Integer.valueOf(i)));
            CharSequence text = textView.getText();
            boolean z = i == position;
            textView.setTextColor(z ? this.mTabTextActiveColor : this.mTabTextInactiveColor);
            i++;
            textView.setContentDescription(getTabContentDescription(text, z, i, this.mNumberTabs));
            if (AccessibilityUtil.isAccessibilityEnabled(getContext()) && z) {
                textView.sendAccessibilityEvent(8);
            }
        }
    }

    private class TabClickListener implements View.OnClickListener {
        private TabClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            for (int i = 0; i < SlidingTabBar.this.mTabStrip.getChildCount(); i++) {
                if (v == SlidingTabBar.this.mTabStrip.getChildAt(i)) {
                    SlidingTabBar.this.selectTab(i);
                }
            }
        }
    }

    public void selectTab(final int tab) {
        this.mTabStrip.onViewPagerPageChanged(tab, 0.0f);
        scrollToTab(tab);
        highlightSelectedTabText(tab);
        SlidingTabBarChangeListener slidingTabBarChangeListener = this.mSlidingTabBarChangeListener;
        if (slidingTabBarChangeListener != null) {
            slidingTabBarChangeListener.onTabSelected(tab);
        }
    }

    public void selectNextTab() {
        int selectedPosition;
        if (!isEnabled() || (selectedPosition = this.mTabStrip.getSelectedPosition() + 1) >= this.mNumberTabs) {
            return;
        }
        selectTab(selectedPosition);
    }

    public void selectPrevTab() {
        int selectedPosition;
        if (!isEnabled() || this.mTabStrip.getSelectedPosition() - 1 < 0) {
            return;
        }
        selectTab(selectedPosition);
    }
}
