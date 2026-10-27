package com.amazon.music.skyfire.ui.widgets.adapters;

import Touch.WidgetsInterface.v1_0.SpotlightCarouselSlideElement;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.widgets.views.SpotlightCarouselSlideView;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class SpotlightCarouselAdapter extends PagerAdapter {
    private static final int LOOPS = 1000;
    private final int FIRST_SLIDE;
    private final int NUM_OF_SLIDES;
    private final Context context;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final List<SpotlightCarouselSlideElement> slides;

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public SpotlightCarouselAdapter(Context context, String str, MethodsDispatcher methodsDispatcher, List<SpotlightCarouselSlideElement> list) {
        this.context = context;
        this.ownerId = str;
        this.methodsDispatcher = methodsDispatcher;
        this.slides = list;
        int size = list.size();
        this.NUM_OF_SLIDES = size;
        this.FIRST_SLIDE = (size * 1000) / 2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.NUM_OF_SLIDES * 1000;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        return createSlide(viewGroup, i % this.NUM_OF_SLIDES);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((SpotlightCarouselSlideView) obj);
    }

    public Object createSlide(ViewGroup viewGroup, int i) {
        SpotlightCarouselSlideView spotlightCarouselSlideView = new SpotlightCarouselSlideView(this.context);
        SpotlightCarouselSlideElement spotlightCarouselSlideElement = this.slides.get(i);
        spotlightCarouselSlideView.setPrimaryText(spotlightCarouselSlideElement.primaryText());
        spotlightCarouselSlideView.setSecondaryText(spotlightCarouselSlideElement.secondaryText());
        spotlightCarouselSlideView.setTertiaryText(spotlightCarouselSlideElement.tertiaryText());
        spotlightCarouselSlideView.setPrimaryCallToAction(this.ownerId, this.methodsDispatcher, spotlightCarouselSlideElement.callToAction());
        spotlightCarouselSlideView.setSecondaryCallToAction(this.ownerId, this.methodsDispatcher, spotlightCarouselSlideElement.secondaryCallToAction());
        viewGroup.addView(spotlightCarouselSlideView);
        return spotlightCarouselSlideView;
    }

    public int getFirstPage() {
        return this.FIRST_SLIDE;
    }
}
