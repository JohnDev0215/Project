package com.amazon.podcast.views.episodeRowTemplate;

import Podcast.Touch.EpisodeRowTemplateInterface.v1_0.AddEpisodeRowItemsMethod;
import Podcast.Touch.EpisodeRowTemplateInterface.v1_0.EpisodeRowTemplate;
import SOACoreInterface.v1_0.Method;
import android.view.View;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.views.ActionBarView;
import com.amazon.podcast.views.BaseView;
import com.amazon.podcast.views.LoadingView;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemsListView;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class EpisodeRowTemplateView extends BaseView<EpisodeRowTemplate> {
    private static final String TAG;
    private static final Logger logger;
    private ActionBarView actionBarView;
    private EpisodeRowItemsListView episodeRowItemsListView;
    private boolean isEndOfListTriggered;
    private LoadingView loadingView;
    private List<Method> onEndOfList;
    private List<Method> onViewed;
    private final TemplateContext templateContext;

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
    }

    static {
        String simpleName = EpisodeRowTemplateView.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public EpisodeRowTemplateView(final TemplateContext templateContext) {
        super(templateContext);
        this.templateContext = templateContext;
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_episode_row_template, this);
        setBackgroundColor(Podcast.getRuntimeStyleSheet().getBaseColor());
        this.episodeRowItemsListView = (EpisodeRowItemsListView) findViewById(R$id.podcast_episode_row_template_list_view);
        this.actionBarView = (ActionBarView) findViewById(R$id.action_bar_view);
        LoadingView loadingView = (LoadingView) findViewById(R$id.podcast_episode_row_template_list_loading_view);
        this.loadingView = loadingView;
        loadingView.setVisibility(0);
        this.actionBarView.bindBackButton(new View.OnClickListener() { // from class: com.amazon.podcast.views.episodeRowTemplate.EpisodeRowTemplateView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EpisodeRowTemplateView.this.handleBackPressed();
            }
        });
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final EpisodeRowTemplate template, final boolean hideActionBar) {
        if (template != null && !template.getItems().isEmpty()) {
            this.loadingView.setVisibility(8);
        }
        if (hideActionBar) {
            this.actionBarView.setVisibility(8);
        }
        this.actionBarView.bindTitle(template.getHeader());
        this.onViewed = template.getOnViewed();
        this.onEndOfList = template.getOnEndOfList();
        this.isEndOfListTriggered = false;
        this.episodeRowItemsListView.create(getOwnerId(), getMethodsDispatcher(), getLifecycleOwner(), getTemplateContext(), UiMetricAttributes$PageType.PODCASTS_SEE_ALL_EPISODES, template.getHeader());
        this.episodeRowItemsListView.bind(template.getItems(), template.getOnEndOfList(), template.getOnViewed());
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
        if (method instanceof AddEpisodeRowItemsMethod) {
            AddEpisodeRowItemsMethod addEpisodeRowItemsMethod = (AddEpisodeRowItemsMethod) method;
            this.episodeRowItemsListView.addItems(addEpisodeRowItemsMethod.getOnEndOfList(), addEpisodeRowItemsMethod.getOnViewed(), addEpisodeRowItemsMethod.getItems());
        }
    }

    @Override // com.amazon.podcast.views.UiMetrics
    public void handleOnViewed() {
        getMethodsDispatcher().dispatch(getOwnerId(), this.onViewed);
    }
}
