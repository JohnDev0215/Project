package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.adapter.ScrollableFragmentPagerAdapter;
import com.amazon.mp3.util.AccessibilityUtil;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class SlidingTabLayout extends HorizontalScrollView {
    private final SlidingTabStrip mTabStrip;
    private final int mTabTextActiveColor;
    private final int mTabTextInactiveColor;
    private float mTabViewSidePaddingDips;
    private final float mTabViewTextSizeSp;
    private final int mTitleOffset;
    private final float mTitleOffsetDips;
    private boolean mUseFixedWidth;
    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mViewPagerPageChangeListener;

    public interface TabColorizer {
        int getIndicatorColor(int position);
    }

    private int getIdForTabIndex(int index) {
        if (index == 0) {
            return R.id.tab_view_0;
        }
        if (index == 1) {
            return R.id.tab_view_1;
        }
        if (index == 2) {
            return R.id.tab_view_2;
        }
        if (index == 3) {
            return R.id.tab_view_3;
        }
        if (index == 4) {
            return R.id.tab_view_4;
        }
        if (index != 5) {
            return -1;
        }
        return R.id.tab_view_5;
    }

    public SlidingTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mUseFixedWidth = false;
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

    public void setCustomTabColorizer(TabColorizer tabColorizer) {
        this.mTabStrip.setCustomTabColorizer(tabColorizer);
    }

    public void setSelectedIndicatorColors(int... colors) {
        this.mTabStrip.setSelectedIndicatorColors(colors);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        this.mViewPagerPageChangeListener = listener;
    }

    public void setUseFixedWidthTabs(boolean useFixedWidth) {
        this.mUseFixedWidth = useFixedWidth;
    }

    public void setViewPager(ViewPager viewPager) {
        this.mTabStrip.removeAllViews();
        this.mViewPager = viewPager;
        if (viewPager != null) {
            viewPager.setOnPageChangeListener(new InternalViewPagerListener());
            populateTabStrip();
        }
    }

    protected TextView createDefaultTabView(Context context, int index) {
        TextView textView = new TextView(context);
        textView.setId(getIdForTabIndex(index));
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

    private void populateTabStrip() {
        ViewPager viewPager = this.mViewPager;
        if (viewPager == null || viewPager.getAdapter() == null) {
            return;
        }
        PagerAdapter adapter = this.mViewPager.getAdapter();
        TabClickListener tabClickListener = new TabClickListener();
        if (this.mUseFixedWidth) {
            this.mTabStrip.setPadding(0, 0, 0, 0);
            this.mTabViewSidePaddingDips = 0.0f;
            this.mTabStrip.setLayoutParams(new FrameLayout.LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.search_tab_bar_height)));
        }
        int i = 0;
        while (i < adapter.getCount()) {
            CharSequence pageTitle = adapter.getPageTitle(i);
            TextView textViewCreateDefaultTabView = createDefaultTabView(getContext(), i);
            textViewCreateDefaultTabView.setGravity(1);
            textViewCreateDefaultTabView.setText(pageTitle);
            int i2 = i + 1;
            textViewCreateDefaultTabView.setContentDescription(getTabContentDescription(pageTitle, false, i2, adapter.getCount()));
            textViewCreateDefaultTabView.setOnClickListener(tabClickListener);
            textViewCreateDefaultTabView.setTag(String.format(Locale.US, "com.amazon.mp3.view.HideableSlidingTabLayout.Tab%d", Integer.valueOf(i)));
            if (this.mUseFixedWidth) {
                textViewCreateDefaultTabView.setWidth(getResources().getDisplayMetrics().widthPixels / this.mViewPager.getAdapter().getCount());
            }
            this.mTabStrip.addView(textViewCreateDefaultTabView);
            i = i2;
        }
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

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewPager viewPager = this.mViewPager;
        if (viewPager != null) {
            scrollToTab(viewPager.getCurrentItem(), 0);
        }
    }

    public void scrollToTab(int tabIndex, int positionOffset) {
        View childAt;
        int childCount = this.mTabStrip.getChildCount();
        if (childCount == 0 || tabIndex < 0 || tabIndex >= childCount || (childAt = this.mTabStrip.getChildAt(tabIndex)) == null) {
            return;
        }
        scrollTo((childAt.getLeft() + positionOffset) - this.mTitleOffset, 0);
    }

    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        private int mScrollState;

        private InternalViewPagerListener() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int childCount = SlidingTabLayout.this.mTabStrip.getChildCount();
            if (childCount == 0 || position < 0 || position >= childCount) {
                return;
            }
            SlidingTabLayout.this.mTabStrip.onViewPagerPageChanged(position, positionOffset);
            SlidingTabLayout.this.scrollToTab(position, SlidingTabLayout.this.mTabStrip.getChildAt(position) != null ? (int) (r0.getWidth() * positionOffset) : 0);
            if (SlidingTabLayout.this.mViewPagerPageChangeListener != null) {
                SlidingTabLayout.this.mViewPagerPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int state) {
            this.mScrollState = state;
            if (SlidingTabLayout.this.mViewPagerPageChangeListener != null) {
                SlidingTabLayout.this.mViewPagerPageChangeListener.onPageScrollStateChanged(state);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int position) {
            if (this.mScrollState == 0) {
                SlidingTabLayout.this.mTabStrip.onViewPagerPageChanged(position, 0.0f);
                SlidingTabLayout.this.scrollToTab(position, 0);
            }
            if (SlidingTabLayout.this.mViewPagerPageChangeListener != null) {
                SlidingTabLayout.this.mViewPagerPageChangeListener.onPageSelected(position);
            }
            SlidingTabLayout.this.highlightSelectedTabText(position);
        }
    }

    public void highlightSelectedTabText(final int position) {
        PagerAdapter adapter;
        ViewPager viewPager = this.mViewPager;
        if (viewPager == null || (adapter = viewPager.getAdapter()) == null) {
            return;
        }
        int count = adapter.getCount();
        int i = 0;
        while (i < count) {
            TextView textView = (TextView) findViewWithTag(String.format(Locale.US, "com.amazon.mp3.view.HideableSlidingTabLayout.Tab%d", Integer.valueOf(i)));
            CharSequence pageTitle = adapter.getPageTitle(i);
            boolean z = i == position;
            textView.setTextColor(z ? this.mTabTextActiveColor : this.mTabTextInactiveColor);
            i++;
            textView.setContentDescription(getTabContentDescription(pageTitle, z, i, count));
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
            if (SlidingTabLayout.this.mViewPager == null || SlidingTabLayout.this.mViewPager.getAdapter() == null) {
                return;
            }
            for (int i = 0; i < SlidingTabLayout.this.mTabStrip.getChildCount(); i++) {
                if (v == SlidingTabLayout.this.mTabStrip.getChildAt(i)) {
                    if (SlidingTabLayout.this.mViewPager.getCurrentItem() == i) {
                        ((ScrollableFragmentPagerAdapter) SlidingTabLayout.this.mViewPager.getAdapter()).scrollFragmentToTop(i);
                    }
                    SlidingTabLayout.this.mViewPager.setCurrentItem(i);
                    SlidingTabLayout.this.sendUiClickMetricsOnTab(i);
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUiClickMetricsOnTab(final int position) {
        ActionType actionType;
        if (position == 0) {
            actionType = ActionType.GO_RECENTS;
        } else if (position == 1) {
            actionType = ActionType.GO_LIBRARY_PLAYLISTS;
        } else if (position == 2) {
            actionType = ActionType.GO_LIBRARY_ARTISTS;
        } else if (position == 3) {
            actionType = ActionType.GO_LIBRARY_ALBUMS;
        } else if (position == 4) {
            actionType = ActionType.GO_LIBRARY_SONGS;
        } else {
            actionType = position != 5 ? null : ActionType.GO_LIBRARY_GENRES;
        }
        if (actionType != null) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withPageType(PageType.CLOUD_LIBRARY).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
        }
    }
}
