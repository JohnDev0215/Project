package com.amazon.podcast.views.galleryTemplate;

import Podcast.SaveInterface.v1_0.SavesTemplate;
import Podcast.Touch.BookmarkElementInterface.v1_0.BookmarkElement;
import Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElement;
import Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerCompactElement;
import Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerSeeMoreItemElement;
import Podcast.Touch.WidgetElementsInterface.v1_0.SavesElement;
import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.CreateAndBindTemplateMethod;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.LinearLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.Queues;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$integer;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.metrics.ui.UIContentViewCache.UIContentViewWidgetIndexCache;
import com.amazon.podcast.redhoodPlus.MetricsEntityIds;
import com.amazon.podcast.save.Save;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class SavesVisualShovelerCompactView extends LinearLayout implements ScrollableWidgetView {
    final UIContentViewWidgetIndexCache contentViewCache;
    private EmberTextView header;
    private boolean isFirstView;
    private final boolean isRedhoodPlusMetricsEnabled;
    private LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private List<Method> onContentFirstViewed;
    private List<Method> onViewed;
    private String ownerId;
    private RecyclerView recyclerView;
    private EmberTextView seeMoreButton;
    private int verticalItemDimensions;
    private View view;
    private String widgetId;

    public SavesVisualShovelerCompactView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner) {
        super(context);
        this.contentViewCache = UIContentViewWidgetIndexCache.INSTANCE;
        this.isFirstView = true;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.isRedhoodPlusMetricsEnabled = Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled();
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_visual_shoveler_compact, this);
        this.verticalItemDimensions = getVerticalItemDimension();
        this.view = findViewById(R$id.visual_shoveler_compact_view);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.visual_shoveler_compact_header);
        this.header = emberTextView;
        emberTextView.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        this.seeMoreButton = (EmberTextView) findViewById(R$id.visual_shoveler_see_all_button);
        this.recyclerView = (RecyclerView) findViewById(R$id.visual_shoveler_compact_recycler_view);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.recyclerView.setAdapter(new SavesVisualShovelerCompactAdapter(this.ownerId, this.methodsDispatcher, getResources(), this.verticalItemDimensions, this.lifecycleOwner, getContext()));
    }

    public void bind(final LifecycleOwner lifecycleOwner, final SavesElement savesElement) {
        this.widgetId = savesElement.getId();
        this.onViewed = savesElement.getOnViewed();
        this.onContentFirstViewed = savesElement.getOnContentFirstViewed();
        LiveData<List<Save>> all = Podcast.getAppSync().save().readAll(getContext());
        if (all == null) {
            return;
        }
        final boolean z = !StringUtils.isBlank(this.widgetId);
        all.observe(lifecycleOwner, new Observer<List<Save>>() { // from class: com.amazon.podcast.views.galleryTemplate.SavesVisualShovelerCompactView.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(final List<Save> saves) {
                SavesVisualShovelerCompactView.this.bindSaves(saves, savesElement, z);
            }
        });
        if (z) {
            handleOnContentFirstViewed();
        }
    }

    public void handleOnViewed() {
        this.methodsDispatcher.dispatch(this.ownerId, this.onViewed);
    }

    private void handleOnContentFirstViewed() {
        if (this.isFirstView) {
            addOnScrollListener(this.recyclerView, this.widgetId, this.methodsDispatcher, this.ownerId, this.onContentFirstViewed);
            handleFirstOnContentFirstViewed(this.recyclerView, this.widgetId, this.methodsDispatcher, this.ownerId, this.onContentFirstViewed);
            this.isFirstView = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindSaves(final List<Save> saves, final SavesElement savesElement, final boolean isWidgetIdPresent) {
        if (saves == null || saves.isEmpty()) {
            this.view.setVisibility(8);
            return;
        }
        this.view.setVisibility(0);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Save save : saves) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(DeeplinkMethods.savedEpisodePlay(save.getId(), getResources()));
            arrayList2.addAll(UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_HOME, UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, MetricsEntityIds.podcastEpisode(this.isRedhoodPlusMetricsEnabled, save.getId(), save.getPodcastEpisodeVariantId()), Integer.valueOf(i), UiMetricAttributes$ContentName.PODCASTS_SAVES_SHOVELER));
            arrayList.add(VerticalItemElement.builder().withOnItemSelected(arrayList2).withImage(save.getImage()).withBookmark(BookmarkElement.builder().withId(save.getId()).build()).withPrimaryText(save.getTitle()).withSecondaryText(Strings.getDurationString(getResources(), save.getTotalDurationMilliseconds())).build());
            i++;
        }
        final VisualShovelerCompactElement visualShovelerCompactElementBuild = VisualShovelerCompactElement.builder().withHeader(savesElement.getHeader()).withItems(arrayList).withSeeMore(VisualShovelerSeeMoreItemElement.builder().withText(savesElement.getSeeMoreText()).withOnItemSelected(Arrays.asList(CreateAndBindTemplateMethod.builder().withTemplate(SavesTemplate.builder().withHeader(savesElement.getHeader()).withLaunchMode("standard").withOnBound(Collections.emptyList()).withOnCreated(Collections.emptyList()).withOnViewed(Collections.emptyList()).build()).withQueue(Queues.template()).withScreenMode("standard").withForced(Boolean.FALSE).build())).build()).build();
        this.header.setText(visualShovelerCompactElementBuild.getHeader());
        this.seeMoreButton.setText(visualShovelerCompactElementBuild.getSeeMore().getText());
        this.seeMoreButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.SavesVisualShovelerCompactView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SavesVisualShovelerCompactView.this.methodsDispatcher.dispatch(SavesVisualShovelerCompactView.this.ownerId, visualShovelerCompactElementBuild.getSeeMore().getOnItemSelected());
            }
        });
        ((SavesVisualShovelerCompactAdapter) this.recyclerView.getAdapter()).bind(visualShovelerCompactElementBuild.getItems());
        if (isWidgetIdPresent) {
            this.contentViewCache.addWidget(this.widgetId);
            this.contentViewCache.updateNumItemsAccessible(this.widgetId, saves.size());
        }
    }

    private int getVerticalItemDimension() {
        Resources resources = getResources();
        int i = resources.getDisplayMetrics().widthPixels;
        int integer = resources.getInteger(R$integer.podcast_vertical_item_count);
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.podcast_spacer_giant) * 3;
        int i2 = R$dimen.gutter;
        int dimensionPixelSize2 = resources.getDimensionPixelSize(i2) * integer;
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R$dimen.margin);
        int dimensionPixelSize4 = resources.getDimensionPixelSize(i2);
        int dimensionPixelSize5 = resources.getDimensionPixelSize(R$dimen.podcast_min_vertical_item_width);
        int i3 = (i - (((dimensionPixelSize2 + dimensionPixelSize3) + dimensionPixelSize4) + dimensionPixelSize)) / integer;
        return i3 < dimensionPixelSize5 ? dimensionPixelSize5 : i3;
    }
}
