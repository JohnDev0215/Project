package com.android.launcher3.home.banner;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.android.launcher3.R;
import com.android.launcher3.graphics.ShaderBitmapCache;
import com.android.launcher3.home.listener.OnViewPageChangeListener;
import com.android.launcher3.home.viewpage.BannerViewPager;
import com.android.launcher3.home.viewpage.OnPageItemClickListener;
import com.android.launcher3.home.viewpage.WallPage;
import com.android.launcher3.util.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Banner extends RelativeLayout implements ViewPager.OnPageChangeListener {
    public static final long DEFAULT_AUTO_TIME = 86400000;
    private static final int NORMAL_COUNT = 2;
    private static final String TAG = "Banner";
    private PagerAdapter adapter;
    private long autoTurningTime;
    private int currentPage;
    private HolderCreator holderCreator;
    private Indicator indicator;
    private boolean isAutoPlay;
    private boolean isTaskPostDelayed;
    private int needCount;
    private int needPage;
    private OnPageItemClickListener onPageClickListener;
    private OnViewPageChangeListener onViewPageChangeListener;
    private ViewPager.OnPageChangeListener outerPageChangeListener;
    private int realCount;
    private int sidePage;
    private final Runnable task;
    private BannerViewPager viewPager;
    private List<View> views;

    static /* synthetic */ int access$008(Banner banner) {
        int i = banner.currentPage;
        banner.currentPage = i + 1;
        return i;
    }

    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.isAutoPlay = true;
        this.autoTurningTime = 86400000L;
        this.needPage = 2;
        this.task = new Runnable() { // from class: com.android.launcher3.home.banner.Banner.1
            @Override // java.lang.Runnable
            public void run() {
                if (Banner.this.isAutoPlay()) {
                    Banner.access$008(Banner.this);
                    if (Banner.this.currentPage == Banner.this.realCount + Banner.this.sidePage + 1) {
                        Banner.this.viewPager.setCurrentItem(Banner.this.sidePage, false);
                        Banner banner = Banner.this;
                        banner.post(banner.task);
                    } else {
                        Banner.this.viewPager.setCurrentItem(Banner.this.currentPage);
                        Banner banner2 = Banner.this;
                        banner2.postDelayed(banner2.task, Banner.this.autoTurningTime);
                    }
                }
            }
        };
        this.views = new ArrayList();
        initViews(context);
    }

    private void initViews(Context context) {
        BannerViewPager bannerViewPager = new BannerViewPager(context.getApplicationContext());
        this.viewPager = bannerViewPager;
        bannerViewPager.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.viewPager.setClipToPadding(false);
        this.viewPager.addOnPageChangeListener(this);
        addView(this.viewPager);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isAutoPlay()) {
            startTurning();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (isAutoPlay()) {
            stopTurning();
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int realPosition = toRealPosition(position);
        ViewPager.OnPageChangeListener onPageChangeListener = this.outerPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(realPosition, positionOffset, positionOffsetPixels);
        }
        Indicator indicator = this.indicator;
        if (indicator != null) {
            indicator.onPageScrolled(realPosition, positionOffset, positionOffsetPixels);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0018  */
    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onPageSelected(int r5) {
        /*
            r4 = this;
            int r0 = r4.currentPage
            int r1 = r4.sidePage
            int r2 = r1 + (-1)
            if (r0 == r2) goto L18
            int r2 = r4.needCount
            int r3 = r1 + (-1)
            int r3 = r2 - r3
            if (r0 == r3) goto L18
            if (r5 == r0) goto L16
            int r2 = r2 - r0
            if (r2 != r1) goto L16
            goto L18
        L16:
            r0 = 0
            goto L19
        L18:
            r0 = 1
        L19:
            r4.currentPage = r5
            if (r0 == 0) goto L1e
            return
        L1e:
            int r5 = r4.toRealPosition(r5)
            androidx.viewpager.widget.ViewPager$OnPageChangeListener r0 = r4.outerPageChangeListener
            if (r0 == 0) goto L29
            r0.onPageSelected(r5)
        L29:
            com.android.launcher3.home.banner.Indicator r0 = r4.indicator
            if (r0 == 0) goto L30
            r0.onPageSelected(r5)
        L30:
            com.android.launcher3.home.listener.OnViewPageChangeListener r4 = r4.onViewPageChangeListener
            if (r4 == 0) goto L37
            r4.OnViewPageChange()
        L37:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.home.banner.Banner.onPageSelected(int):void");
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int state) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.outerPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(state);
        }
        Indicator indicator = this.indicator;
        if (indicator != null) {
            indicator.onPageScrollStateChanged(state);
        }
        if (state == 1) {
            int i = this.currentPage;
            int i2 = this.sidePage;
            if (i == i2 - 1) {
                this.viewPager.setCurrentItem(this.realCount + i, false);
            } else if (i == this.needCount - i2) {
                this.viewPager.setCurrentItem(i2, false);
            }
        }
    }

    public class BannerAdapter extends PagerAdapter {
        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public BannerAdapter() {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            int i = Banner.this.realCount;
            Banner banner = Banner.this;
            return i > 1 ? banner.needCount : banner.realCount;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = (View) Banner.this.views.get(position);
            if (Banner.this.onPageClickListener != null) {
                view.setOnClickListener(new View.OnClickListener() { // from class: com.android.launcher3.home.banner.Banner.BannerAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        Banner.this.onPageClickListener.onPageItemClick(v, Banner.this.toRealPosition(position));
                    }
                });
            }
            container.addView(view);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_wallpager);
            imageView.setImageBitmap(((WallPage) imageView.getTag()).getViewPageBitmap());
            return view;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_wallpager);
            Drawable drawable = imageView.getDrawable();
            try {
                if (drawable instanceof BitmapDrawable) {
                    ((WallPage) imageView.getTag()).onRecycle(((BitmapDrawable) drawable).getBitmap());
                }
            } catch (Exception unused) {
            }
            imageView.setImageDrawable(null);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isAutoPlay()) {
            int action = ev.getAction();
            if (action == 1 || action == 3 || action == 4) {
                startTurning();
            } else if (action == 0) {
                stopTurning();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public void startPager(int startPosition) {
        if (this.adapter == null) {
            this.adapter = new BannerAdapter();
        }
        this.viewPager.setAdapter(this.adapter);
        this.currentPage = startPosition + this.sidePage;
        this.viewPager.setScrollable(this.realCount > 1);
        this.viewPager.setFirstLayoutToField(false);
        this.viewPager.setFocusable(true);
        this.viewPager.setCurrentItem(this.currentPage);
        Indicator indicator = this.indicator;
        if (indicator != null) {
            indicator.initIndicatorCount(this.realCount);
        }
        if (isAutoPlay()) {
            startTurning();
        }
    }

    private void createImages(List<?> items) {
        this.views.clear();
        ShaderBitmapCache.getInstance().clear();
        if (items == null || items.size() == 0 || this.holderCreator == null) {
            this.realCount = 0;
            this.needCount = 0;
            return;
        }
        int size = items.size();
        this.realCount = size;
        int i = this.needPage;
        this.sidePage = i / 2;
        this.needCount = size + i;
        for (int i2 = 0; i2 < this.needCount; i2++) {
            int realPosition = toRealPosition(i2);
            this.views.add(this.holderCreator.createView(getContext(), realPosition, (WallPage) items.get(realPosition)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int toRealPosition(int position) {
        int i = this.realCount;
        int i2 = i != 0 ? (position - this.sidePage) % i : 0;
        return i2 < 0 ? i2 + i : i2;
    }

    public Banner setPageMargin(int multiWidth, int pageMargin) {
        return setPageMargin(multiWidth, multiWidth, pageMargin);
    }

    public Banner setPageMargin(int leftWidth, int rightWidth, int pageMargin) {
        this.viewPager.setPageMargin(pageMargin);
        this.viewPager.setOverlapStyle(pageMargin < 0);
        this.viewPager.setPadding(leftWidth + Math.abs(pageMargin), this.viewPager.getPaddingTop(), rightWidth + Math.abs(pageMargin), this.viewPager.getPaddingBottom());
        this.viewPager.setOffscreenPageLimit(2);
        this.needPage = 4;
        return this;
    }

    public Banner setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer) {
        this.viewPager.setPageTransformer(reverseDrawingOrder, transformer);
        return this;
    }

    public Banner setAutoTurningTime(long autoTurningTime) {
        this.autoTurningTime = autoTurningTime;
        return this;
    }

    public Banner setOuterPageChangeListener(ViewPager.OnPageChangeListener outerPageChangeListener) {
        this.outerPageChangeListener = outerPageChangeListener;
        return this;
    }

    public Banner setPagerScrollDuration(int pagerScrollDuration) {
        this.viewPager.setPagerScrollDuration(pagerScrollDuration);
        return this;
    }

    public Banner setOffscreenPageLimit(int limit) {
        this.viewPager.setOffscreenPageLimit(limit);
        return this;
    }

    public Banner setRoundCorners(final float radius) {
        setOutlineProvider(new ViewOutlineProvider() { // from class: com.android.launcher3.home.banner.Banner.2
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), radius);
            }
        });
        setClipToOutline(true);
        return this;
    }

    public Banner setAutoPlay(boolean autoPlay) {
        this.isAutoPlay = autoPlay;
        if (autoPlay && this.realCount > 1) {
            startTurning();
        } else {
            stopTurning();
        }
        return this;
    }

    public Banner setIndicator(Indicator indicator) {
        return setIndicator(indicator, true);
    }

    public Banner setIndicator(Indicator indicator, boolean attachToRoot) {
        Indicator indicator2 = this.indicator;
        if (indicator2 != null) {
            removeView(indicator2.getView());
        }
        if (indicator != null) {
            this.indicator = indicator;
            if (attachToRoot) {
                addView(indicator.getView(), this.indicator.getParams());
            }
        }
        return this;
    }

    public Banner setHolderCreator(HolderCreator holderCreator) {
        this.holderCreator = holderCreator;
        return this;
    }

    public Banner setOnPageClickListener(OnPageItemClickListener onPageClickListener) {
        this.onPageClickListener = onPageClickListener;
        return this;
    }

    public void setPages(List<?> items, int startPosition) {
        createImages(items);
        startPager(startPosition);
    }

    public void setPages(List<?> items) {
        setPages(items, 0);
    }

    public void setOnViewPageChangeListener(OnViewPageChangeListener onViewPageChangeListener) {
        this.onViewPageChangeListener = onViewPageChangeListener;
    }

    public boolean isAutoPlay() {
        return this.isAutoPlay && this.realCount > 1;
    }

    public int getCurrentPager() {
        return Math.max(toRealPosition(this.currentPage), 0);
    }

    public int getLastPager() {
        return this.currentPage;
    }

    public void startTurning() {
        stopTurning();
        postDelayed(this.task, this.autoTurningTime);
        this.isTaskPostDelayed = true;
    }

    public void stopTurning() {
        removeCallbacks(this.task);
        this.isTaskPostDelayed = false;
    }

    public void setViewPageCurrentPage(int currentPage) {
        if (this.viewPager != null) {
            Logger.e("pengbin", "setViewPageCurrentPage ==== " + currentPage);
            this.viewPager.setCurrentItem(currentPage);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        List<View> list = this.views;
        if (list != null) {
            Iterator<View> it = list.iterator();
            while (it.hasNext()) {
                ImageView imageView = (ImageView) it.next().getTag(R.id.iv_wallpager);
                Bitmap bitmap = imageView != null ? ((BitmapDrawable) imageView.getDrawable()).getBitmap() : null;
                if (bitmap != null) {
                    bitmap.recycle();
                }
            }
        }
    }
}
