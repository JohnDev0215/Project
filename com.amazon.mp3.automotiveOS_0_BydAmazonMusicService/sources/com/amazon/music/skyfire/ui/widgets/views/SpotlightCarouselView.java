package com.amazon.music.skyfire.ui.widgets.views;

import Touch.WidgetsInterface.v1_0.SpotlightCarouselElement;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.widgets.helpers.TouchEventHandler;
import com.amazon.music.skyfire.ui.widgets.views.spotlightCarousel.SpotlightCarouselViewPager;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.ui.foundations.styles.GridSize;
import com.squareup.picasso.Picasso;

/* JADX INFO: loaded from: classes4.dex */
public class SpotlightCarouselView extends RelativeLayout {
    private static final int MIN_SWIPING_DISTANCE = 50;
    private final Context context;
    private ImageView image;
    private boolean isViewBound;
    private ImageButton leftNavigation;
    private ImageView overlay;
    private LinearLayout pageIndicator;
    private ImageView[] paginationDots;
    private float releasedXPosition;
    private ImageButton rightNavigation;
    private float startingXPosition;
    private final StyleSheet styleSheet;
    private SpotlightCarouselViewPager viewPager;

    public SpotlightCarouselView(Context context, StyleSheet styleSheet) {
        super(context);
        this.context = context;
        this.styleSheet = styleSheet;
        init();
    }

    private void init() {
        View.inflate(this.context, R.layout.spotlight_carousel, this);
        this.image = (ImageView) findViewById(R.id.spotlight_carousel_static_slide_image);
        this.overlay = (ImageView) findViewById(R.id.spotlight_carousel_overlay);
        this.leftNavigation = (ImageButton) findViewById(R.id.spotlight_carousel_left_navigation);
        this.rightNavigation = (ImageButton) findViewById(R.id.spotlight_carousel_right_navigation);
        this.pageIndicator = (LinearLayout) findViewById(R.id.spotlight_carousel_page_indicator);
        this.viewPager = (SpotlightCarouselViewPager) findViewById(R.id.spotlight_carousel_view_pager);
        setViewMargins();
        applyCornerRadius();
        post(new Runnable() { // from class: com.amazon.music.skyfire.ui.widgets.views.SpotlightCarouselView.1
            @Override // java.lang.Runnable
            public void run() {
                SpotlightCarouselView spotlightCarouselView = SpotlightCarouselView.this;
                spotlightCarouselView.setAspectRatio(spotlightCarouselView.image);
                SpotlightCarouselView spotlightCarouselView2 = SpotlightCarouselView.this;
                spotlightCarouselView2.setAspectRatio(spotlightCarouselView2.overlay);
                SpotlightCarouselView spotlightCarouselView3 = SpotlightCarouselView.this;
                spotlightCarouselView3.setAspectRatio(spotlightCarouselView3.viewPager);
            }
        });
        this.isViewBound = false;
    }

    public boolean isViewBound() {
        return this.isViewBound;
    }

    public void setIsViewBound(boolean z) {
        this.isViewBound = z;
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void setNavigationOnTouchListener(final SpotlightCarouselElement spotlightCarouselElement, final SpotlightCarouselViewPager spotlightCarouselViewPager, final String str, final MethodsDispatcher methodsDispatcher) {
        this.leftNavigation.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.music.skyfire.ui.widgets.views.SpotlightCarouselView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$setNavigationOnTouchListener$0(spotlightCarouselViewPager, methodsDispatcher, str, spotlightCarouselElement, view, motionEvent);
            }
        });
        this.rightNavigation.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.music.skyfire.ui.widgets.views.SpotlightCarouselView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$setNavigationOnTouchListener$1(spotlightCarouselViewPager, methodsDispatcher, str, spotlightCarouselElement, view, motionEvent);
            }
        });
        spotlightCarouselViewPager.setOnTouchListener(new TouchEventHandler(this.context) { // from class: com.amazon.music.skyfire.ui.widgets.views.SpotlightCarouselView.2
            @Override // com.amazon.music.skyfire.ui.widgets.helpers.TouchEventHandler, android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    SpotlightCarouselView.this.startingXPosition = motionEvent.getX();
                } else if (action == 1) {
                    SpotlightCarouselView.this.releasedXPosition = motionEvent.getX();
                    if (Math.abs(SpotlightCarouselView.this.releasedXPosition - SpotlightCarouselView.this.startingXPosition) > 50.0f) {
                        if (SpotlightCarouselView.this.startingXPosition > SpotlightCarouselView.this.releasedXPosition) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                }
                return true;
            }

            @Override // com.amazon.music.skyfire.ui.widgets.helpers.TouchEventHandler
            public void onSwipeLeft() {
                methodsDispatcher.dispatchMethods(str, spotlightCarouselElement.onSwipeLeft());
                spotlightCarouselViewPager.setCurrentItem(r0.getCurrentItem() - 1);
            }

            @Override // com.amazon.music.skyfire.ui.widgets.helpers.TouchEventHandler
            public void onSwipeRight() {
                methodsDispatcher.dispatchMethods(str, spotlightCarouselElement.onSwipeRight());
                SpotlightCarouselViewPager spotlightCarouselViewPager2 = spotlightCarouselViewPager;
                spotlightCarouselViewPager2.setCurrentItem(spotlightCarouselViewPager2.getCurrentItem() + 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setNavigationOnTouchListener$0(SpotlightCarouselViewPager spotlightCarouselViewPager, MethodsDispatcher methodsDispatcher, String str, SpotlightCarouselElement spotlightCarouselElement, View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.leftNavigation.setColorFilter(ContextCompat.getColor(this.context, R.color.primary_glass_1), PorterDuff.Mode.MULTIPLY);
        } else if (action == 1) {
            this.leftNavigation.clearColorFilter();
            spotlightCarouselViewPager.setCurrentItem(spotlightCarouselViewPager.getCurrentItem() - 1);
            methodsDispatcher.dispatchMethods(str, spotlightCarouselElement.leftNavigation().onItemSelected());
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setNavigationOnTouchListener$1(SpotlightCarouselViewPager spotlightCarouselViewPager, MethodsDispatcher methodsDispatcher, String str, SpotlightCarouselElement spotlightCarouselElement, View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.rightNavigation.setColorFilter(ContextCompat.getColor(this.context, R.color.primary_glass_1), PorterDuff.Mode.MULTIPLY);
        } else if (action == 1) {
            this.rightNavigation.clearColorFilter();
            spotlightCarouselViewPager.setCurrentItem(spotlightCarouselViewPager.getCurrentItem() + 1);
            methodsDispatcher.dispatchMethods(str, spotlightCarouselElement.rightNavigation().onItemSelected());
        }
        return true;
    }

    public void setPageChangeListener() {
        this.viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() { // from class: com.amazon.music.skyfire.ui.widgets.views.SpotlightCarouselView.3
            @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                SpotlightCarouselView spotlightCarouselView = SpotlightCarouselView.this;
                spotlightCarouselView.setPageIndicator(i % spotlightCarouselView.paginationDots.length);
            }
        });
    }

    public SpotlightCarouselViewPager getViewPager() {
        return this.viewPager;
    }

    public void setImage(String str) {
        bindImage(this.image, str);
    }

    public void initPageIndicator(int i) {
        if (this.paginationDots == null) {
            setPaginationDots(i);
        }
        setPageIndicator(0);
    }

    private void setPaginationDots(int i) {
        this.paginationDots = new ImageView[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.paginationDots[i2] = new ImageView(getContext());
            this.paginationDots[i2].setImageDrawable(getResources().getDrawable(R.drawable.spotlight_carousel_default_dot));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            int dimension = (int) getResources().getDimension(R.dimen.spotlight_carousel_pagination_dot_half_margin);
            layoutParams.setMargins(dimension, 0, dimension, 0);
            this.pageIndicator.addView(this.paginationDots[i2], layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPageIndicator(int i) {
        ImageView[] imageViewArr = this.paginationDots;
        if (i < imageViewArr.length) {
            int length = i == 0 ? imageViewArr.length - 1 : i - 1;
            int i2 = i == imageViewArr.length + (-1) ? 0 : i + 1;
            ImageView imageView = (ImageView) this.pageIndicator.getChildAt(length);
            Resources resources = getResources();
            int i3 = R.drawable.spotlight_carousel_default_dot;
            imageView.setImageDrawable(resources.getDrawable(i3));
            ((ImageView) this.pageIndicator.getChildAt(i2)).setImageDrawable(getResources().getDrawable(i3));
            ((ImageView) this.pageIndicator.getChildAt(i)).setImageDrawable(getResources().getDrawable(R.drawable.spotlight_carousel_selected_dot));
        }
    }

    public void setLeftNavigationImage(String str) {
        bindImage(this.leftNavigation, str);
    }

    public void setRightNavigationImage(String str) {
        bindImage(this.rightNavigation, str);
    }

    private void setViewMargins() {
        GridSize gridSize = this.styleSheet.getGridSize();
        int marginSpace = gridSize.getMarginSpace() + gridSize.getColumnSpace();
        int dimension = (int) getResources().getDimension(R.dimen.gutter_half);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(marginSpace, dimension, marginSpace, dimension);
        setLayoutParams(layoutParams);
    }

    private void applyCornerRadius() {
        this.image.setClipToOutline(true);
        this.overlay.setClipToOutline(true);
    }

    private void bindImage(ImageView imageView, String str) {
        if (str == null || str.length() == 0) {
            imageView.setVisibility(8);
        } else {
            imageView.setVisibility(0);
            Picasso.get().load(str).into(imageView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAspectRatio(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int measuredWidth = view.getMeasuredWidth();
        int i = this.context.getResources().getConfiguration().orientation;
        int i2 = this.context.getResources().getConfiguration().screenLayout & 15;
        if (i2 == 3) {
            layoutParams.height = measuredWidth / 2;
        } else if (i2 == 4) {
            layoutParams.height = measuredWidth / 3;
        } else {
            layoutParams.height = measuredWidth;
        }
        view.setLayoutParams(layoutParams);
    }
}
