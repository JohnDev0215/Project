package com.amazon.podcast.views.chromeTemplate;

import Podcast.Touch.ChromeTemplateInterface.v1_0.ChromeTemplate;
import Podcast.Touch.ChromeTemplateInterface.v1_0.ChromeTemplateShardElement;
import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.BindTemplateMethod;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.RuntimeStyleSheet;
import com.amazon.podcast.UserPreferences;
import com.amazon.podcast.views.ActionBarView;
import com.amazon.podcast.views.BaseView;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.TemplateFragment;
import com.google.android.material.tabs.TabLayout;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class ChromeTemplateView extends BaseView<ChromeTemplate> {
    private ActionBarView actionBarView;
    private List<Method> onViewed;
    private ChromeShardsHolder shardsHolder;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
    }

    public ChromeTemplateView(final TemplateContext templateContext) {
        super(templateContext);
        init();
    }

    private final void init() {
        View.inflate(getContext(), R$layout.podcast_chrome_template_view, this);
        RuntimeStyleSheet runtimeStyleSheet = Podcast.getRuntimeStyleSheet();
        ActionBarView actionBarView = (ActionBarView) findViewById(R$id.action_bar_view);
        this.actionBarView = actionBarView;
        actionBarView.bindBackButton(new View.OnClickListener() { // from class: com.amazon.podcast.views.chromeTemplate.ChromeTemplateView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChromeTemplateView.this.handleBackPressed();
            }
        });
        this.actionBarView.bindTitle(getContext().getString(R$string.podcast_chrome_template_view_header));
        TabLayout tabLayout = (TabLayout) findViewById(R$id.chrome_template_view_tab_layout);
        this.tabLayout = tabLayout;
        tabLayout.setSelectedTabIndicator(getResources().getDrawable(runtimeStyleSheet.getPodcastTabIndicatorDrawableId()));
        this.tabLayout.setSelectedTabIndicatorColor(runtimeStyleSheet.getAccentColor());
        this.viewPager = (ViewPager) findViewById(R$id.chrome_template_view_view_pager);
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final ChromeTemplate chromeTemplate, final boolean hideActionBar) {
        this.onViewed = chromeTemplate.getOnViewed();
        if (chromeTemplate.getItems() == null) {
            return;
        }
        if (hideActionBar) {
            this.actionBarView.setVisibility(8);
        }
        bindShards(chromeTemplate.getItems(), Integer.valueOf(chromeTemplate.getSelectedShardPosition()));
    }

    private void bindShards(final List<ChromeTemplateShardElement> shards, final Integer selectedShardPosition) {
        this.shardsHolder = new ChromeShardsHolder(getOwnerId(), getMethodsDispatcher(), getOwnerRegistration(), shards);
        this.viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), this.shardsHolder));
        this.tabLayout.setupWithViewPager(this.viewPager);
        setCustomFontOnTabs();
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.amazon.podcast.views.chromeTemplate.ChromeTemplateView.2
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(final int state) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(final int position) {
                TemplateFragment templateFragment;
                UserPreferences.get(ChromeTemplateView.this.getContext()).writeLastVisitedLibraryTab(position);
                if (ChromeTemplateView.this.shardsHolder == null || (templateFragment = (TemplateFragment) ChromeTemplateView.this.shardsHolder.fragment(position)) == null) {
                    return;
                }
                templateFragment.handleOnViewed();
            }
        });
        if (selectedShardPosition == null) {
            this.viewPager.setCurrentItem(0, false);
        } else {
            this.viewPager.setCurrentItem(selectedShardPosition.intValue(), false);
        }
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
        TemplateFragment templateFragment;
        ChromeShardsHolder chromeShardsHolder = this.shardsHolder;
        if (chromeShardsHolder == null || (templateFragment = (TemplateFragment) chromeShardsHolder.fragment(ownerId)) == null) {
            return;
        }
        if (method instanceof BindTemplateMethod) {
            templateFragment.bindTemplate(ownerId, (BindTemplateMethod) method);
        } else {
            templateFragment.handleTemplateMethod(ownerId, method);
        }
    }

    @Override // com.amazon.podcast.views.UiMetrics
    public void handleOnViewed() {
        getMethodsDispatcher().dispatch(getOwnerId(), this.onViewed);
    }

    private void setCustomFontOnTabs() {
        ViewGroup viewGroup = (ViewGroup) this.tabLayout.getChildAt(0);
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewGroup viewGroup2 = (ViewGroup) viewGroup.getChildAt(i);
            int childCount2 = viewGroup2.getChildCount();
            for (int i2 = 0; i2 < childCount2; i2++) {
                View childAt = viewGroup2.getChildAt(i2);
                if (childAt instanceof TextView) {
                    ((TextView) childAt).setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/AmazonEmber_Bd.ttf"));
                }
            }
        }
    }
}
