package com.amazon.podcast.views.ptcTemplate;

import Podcast.Touch.PTCTemplateInterface.v1_0.GridItemElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplate;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplateShardsElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.PillItemElement;
import SOAAppSyncInterface.v1_0.WriteElement;
import SOAAppSyncInterface.v1_0.WriteMethod;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOATemplateListInterface.v1_0.BindTemplateMethod;
import SOATemplateListInterface.v1_0.Template;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.OwnerRegistration;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.TemplateCache;
import com.amazon.podcast.TemplateCacheEntry;
import com.amazon.podcast.bootstrap.ActionBarProvider;
import com.amazon.podcast.ptc.PTCPreferences;
import com.amazon.podcast.views.TemplateActivity;
import com.amazon.podcast.views.TemplateFragment;
import com.google.android.gms.common.util.CollectionUtils;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public class PTCTemplateActivity extends TemplateActivity<PTCTemplate> {
    private static final Logger logger = LoggerFactory.getLogger("PTCTemplateActivity");
    private ActionBarProvider actionBarProvider;
    private ImageView background;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private OwnerRegistration ownerRegistration;
    private PTCShardsHolder shardsHolder;
    private PTCTemplate template;
    private ViewPager viewPager;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        String string;
        super.onCreate(savedInstanceState);
        initViews();
        Intent intent = getIntent();
        if (intent != null) {
            string = intent.getStringExtra("ownerId");
        } else {
            string = savedInstanceState != null ? savedInstanceState.getString("ownerId") : null;
        }
        if (string == null) {
            return;
        }
        bindFromCache(string);
        this.ownerRegistration.registerOwner(string);
        this.methodsDispatcher.dispatch(string, this.template.getOnCreated());
        bind(this.template, true);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        String str = this.ownerId;
        if (str == null) {
            return;
        }
        outState.putString("ownerId", str);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        String str = this.ownerId;
        if (str == null) {
            return;
        }
        TemplateCache.INSTANCE.delete(str);
        clearPTCSelections();
        OwnerRegistration ownerRegistration = this.ownerRegistration;
        if (ownerRegistration == null) {
            return;
        }
        ownerRegistration.deregisterOwner(this.ownerId);
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public final void handleTemplateMethod(final String ownerId, final Method method) {
        TemplateFragment templateFragment = (TemplateFragment) this.shardsHolder.fragment(ownerId);
        if (templateFragment == null) {
            return;
        }
        if (method instanceof BindTemplateMethod) {
            templateFragment.bindTemplate(ownerId, (BindTemplateMethod) method);
        } else {
            templateFragment.handleTemplateMethod(ownerId, method);
        }
    }

    @Override // com.amazon.podcast.views.TemplateActivity
    public final String getOwnerId() {
        return this.ownerId;
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final PTCTemplate template, final boolean hideActionBar) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Picasso.get().load(template.getBackgroundImage()).resize(displayMetrics.widthPixels, displayMetrics.heightPixels).into(this.background);
        bindShards(template.getShards());
    }

    @Override // com.amazon.podcast.views.TemplateActivity
    public final Template getTemplate() {
        return this.template;
    }

    public final void handleNextSelected() {
        this.viewPager.setCurrentItem(this.viewPager.getCurrentItem() + 1);
    }

    public final String getNextOwnerId() {
        return ((TemplateFragment) this.shardsHolder.fragment(Math.min(this.viewPager.getCurrentItem() + 1, this.shardsHolder.count() - 1))).getOwnerId();
    }

    public final void cacheCategorySelections(final List<PillItemElement> categories) {
        ArrayList arrayList = new ArrayList();
        Iterator<PillItemElement> it = categories.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getId());
        }
        PTCPreferences.INSTANCE.cachePTCCategorySelections(arrayList);
    }

    public final void cachePodcastSelections(final Map<String, GridItemElement> podcasts) {
        PTCPreferences.INSTANCE.cachePTCPodcastSelections(podcasts);
    }

    public final List<String> getCachedCategorySelections() {
        return PTCPreferences.INSTANCE.getPTCCategorySelections();
    }

    public final Map<String, GridItemElement> getCachedPodcastSelections() {
        return PTCPreferences.INSTANCE.getPTCPodcastSelections();
    }

    public final void buildAndDispatchWriteMethod(final List<WriteElement> elements, final Queue queue) {
        this.methodsDispatcher.dispatch(this.ownerId, Collections.singletonList(WriteMethod.builder().withQueue(queue).withElement(elements).build()));
    }

    private void initViews() {
        setContentView(R$layout.ptc_template_view);
        getWindow().getDecorView().setBackgroundColor(Podcast.getRuntimeStyleSheet().getBaseColor());
        this.viewPager = (ViewPager) findViewById(R$id.ptc_view_pager);
        this.background = (ImageView) findViewById(R$id.ptc_template_background);
    }

    private void bindFromCache(final String cacheId) {
        if (cacheId == null) {
            return;
        }
        TemplateCacheEntry templateCacheEntry = TemplateCache.INSTANCE.read(cacheId);
        if (templateCacheEntry == null) {
            logger.debug("cache is null for {}", cacheId);
            return;
        }
        Template template = templateCacheEntry.getTemplate();
        logger.debug("cache restored for template {}", template.getClass().getSimpleName());
        this.ownerId = cacheId;
        this.template = (PTCTemplate) template;
        this.methodsDispatcher = templateCacheEntry.getMethodsDispatcher();
        this.ownerRegistration = templateCacheEntry.getOwnerRegistration();
        this.actionBarProvider = templateCacheEntry.getActionBarProvider();
    }

    private void bindShards(final PTCTemplateShardsElement shardElement) {
        if (shardElement == null || CollectionUtils.isEmpty(shardElement.getItems())) {
            return;
        }
        this.shardsHolder = new PTCShardsHolder(this.ownerId, this.methodsDispatcher, this.ownerRegistration, shardElement.getItems());
        this.viewPager.setAdapter(new PTCPagerAdapter(getSupportFragmentManager(), this.shardsHolder));
        bindOnPageListener();
        this.viewPager.setCurrentItem(0);
    }

    private void bindOnPageListener() {
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.amazon.podcast.views.ptcTemplate.PTCTemplateActivity.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(final int state) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(final int position) {
                ((TemplateFragment) PTCTemplateActivity.this.shardsHolder.fragment(position)).handleOnViewed();
            }
        });
    }

    private void clearPTCSelections() {
        PTCPreferences.INSTANCE.clearSelections();
    }
}
