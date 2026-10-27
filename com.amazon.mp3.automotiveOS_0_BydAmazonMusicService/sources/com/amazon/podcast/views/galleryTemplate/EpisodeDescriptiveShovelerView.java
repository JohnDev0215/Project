package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveShovelerElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$integer;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.RuntimeStyleSheet;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.ui.UIContentViewCache.UIContentViewWidgetIndexCache;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.browseHomeWidgets.PodcastEpisodeDescriptiveShovelerModel;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemsAdapter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class EpisodeDescriptiveShovelerView extends LinearLayout implements ScrollableWidgetView {
    final UIContentViewWidgetIndexCache contentViewCache;
    private int episodeRowItemWidth;
    private FragmentManager fragmentManager;
    private EmberTextView header;
    private boolean isFirstView;
    private LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private List<Method> onContentFirstViewed;
    private List<Method> onViewed;
    private String ownerId;
    private UiMetricAttributes$PageType pageType;
    private RecyclerView recyclerView;
    private RuntimeStyleSheet runtimeStyleSheet;
    private EmberTextView seeMoreButton;
    private TemplateContext templateContext;
    private int visibleEpisodeRowItemCount;
    private String widgetId;

    public EpisodeDescriptiveShovelerView(final Context context, final TemplateContext templateContext, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner) {
        super(context);
        this.contentViewCache = UIContentViewWidgetIndexCache.INSTANCE;
        this.isFirstView = true;
        this.ownerId = ownerId;
        this.templateContext = templateContext;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.pageType = UiMetricAttributes$PageType.PODCASTS_HOME;
        if (Podcast.getRuntimeStyleSheet() == null) {
            Podcast.initializeRuntimeStyleSheet(context);
        }
        this.runtimeStyleSheet = Podcast.getRuntimeStyleSheet();
        init();
    }

    public EpisodeDescriptiveShovelerView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final FragmentManager fragmentManager, final UiMetricAttributes$PageType pageType) {
        super(context);
        this.contentViewCache = UIContentViewWidgetIndexCache.INSTANCE;
        this.isFirstView = true;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.fragmentManager = fragmentManager;
        this.pageType = pageType;
        if (Podcast.getRuntimeStyleSheet() == null) {
            Podcast.initializeRuntimeStyleSheet(context);
        }
        this.runtimeStyleSheet = Podcast.getRuntimeStyleSheet();
        initForBrowseHome();
    }

    private void initForBrowseHome() {
        View.inflate(getContext(), R$layout.podcast_episode_descriptive_shoveler, this);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.descriptive_shoveler_header);
        this.header = emberTextView;
        RuntimeStyleSheet runtimeStyleSheet = this.runtimeStyleSheet;
        if (runtimeStyleSheet != null) {
            emberTextView.setTypeface(runtimeStyleSheet.getHeadline4TypeFace());
        }
        this.seeMoreButton = (EmberTextView) findViewById(R$id.descriptive_shoveler_see_all_button);
        Resources resources = getResources();
        this.visibleEpisodeRowItemCount = resources.getInteger(R$integer.podcast_descriptive_shoveler_column_count);
        this.episodeRowItemWidth = getEpisodeRowItemWidth(resources);
        this.recyclerView = (RecyclerView) findViewById(R$id.descriptive_shoveler_recycler_view);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, 0, false));
        this.recyclerView.setAdapter(new EpisodeRowItemsAdapter(this.ownerId, this.methodsDispatcher, this.lifecycleOwner, getContext(), this.fragmentManager, this.episodeRowItemWidth, UiMetricAttributes$PageType.BROWSE_HOME));
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_episode_descriptive_shoveler, this);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.descriptive_shoveler_header);
        this.header = emberTextView;
        RuntimeStyleSheet runtimeStyleSheet = this.runtimeStyleSheet;
        if (runtimeStyleSheet != null) {
            emberTextView.setTypeface(runtimeStyleSheet.getHeadline4TypeFace());
        }
        this.seeMoreButton = (EmberTextView) findViewById(R$id.descriptive_shoveler_see_all_button);
        Resources resources = getResources();
        this.visibleEpisodeRowItemCount = resources.getInteger(R$integer.podcast_descriptive_shoveler_column_count);
        this.episodeRowItemWidth = getEpisodeRowItemWidth(resources);
        this.recyclerView = (RecyclerView) findViewById(R$id.descriptive_shoveler_recycler_view);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, 0, false));
        this.recyclerView.setAdapter(new EpisodeRowItemsAdapter(this.ownerId, this.methodsDispatcher, this.lifecycleOwner, getContext(), this.templateContext, this.episodeRowItemWidth, UiMetricAttributes$PageType.PODCASTS_HOME));
    }

    public void bind(final EpisodeDescriptiveShovelerElement element, final PodcastEpisodeDescriptiveShovelerModel model, final SeeMoreProvider seeMoreProvider) {
        this.header.setText(element.getHeader());
        this.seeMoreButton.setText(element.getSeeMore().getText());
        this.seeMoreButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.EpisodeDescriptiveShovelerView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EpisodeDescriptiveShovelerView.this.onClickSeeMoreButton(element, model, seeMoreProvider);
            }
        });
        List<EpisodeRowItemElement> items = element.getItems();
        EpisodeRowItemsAdapter episodeRowItemsAdapter = (EpisodeRowItemsAdapter) this.recyclerView.getAdapter();
        this.onViewed = element.getOnViewed();
        if (items == null || items.isEmpty()) {
            return;
        }
        this.widgetId = element.getId();
        episodeRowItemsAdapter.bind(items.subList(0, Math.min(items.size(), 20)));
        this.onContentFirstViewed = element.getOnContentFirstViewed();
        if (StringUtils.isBlank(this.widgetId)) {
            return;
        }
        this.contentViewCache.addWidget(this.widgetId);
        this.contentViewCache.updateNumItemsAccessible(this.widgetId, items.size());
        handleOnContentFirstViewed();
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

    private int getEpisodeRowItemWidth(final Resources resources) {
        int i = resources.getDisplayMetrics().widthPixels;
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.gutter);
        int i2 = this.visibleEpisodeRowItemCount;
        return (i - ((i2 * dimensionPixelSize) + (dimensionPixelSize * 2))) / i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickSeeMoreButton(final EpisodeDescriptiveShovelerElement element, final PodcastEpisodeDescriptiveShovelerModel model, final SeeMoreProvider seeMoreProvider) {
        if (seeMoreProvider == null || model == null) {
            this.methodsDispatcher.dispatch(this.ownerId, element.getSeeMore().getOnItemSelected());
        } else {
            seeMoreProvider.onSeeMoreClick(model.getSeeMoreUri(), model.getViewReferenceId(), null);
        }
    }
}
