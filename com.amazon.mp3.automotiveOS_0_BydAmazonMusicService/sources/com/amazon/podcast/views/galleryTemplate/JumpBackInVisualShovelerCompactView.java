package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.BookmarkElementInterface.v1_0.BookmarkElement;
import Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElement;
import Podcast.Touch.WidgetElementsInterface.v1_0.JumpBackInElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.LinearLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.JumpBackIns;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.PodcastEpisode;
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
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.galleryTemplate.JumpBackInVisualShovelerCompactAdapter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class JumpBackInVisualShovelerCompactView extends LinearLayout implements ScrollableWidgetView {
    final UIContentViewWidgetIndexCache contentViewCache;
    private EmberTextView header;
    private boolean isFirstView;
    private final boolean isRedhoodPlusMetricsEnabled;
    private final LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private List<Method> onContentFirstViewed;
    private List<Method> onViewed;
    private String ownerId;
    private RecyclerView recyclerView;
    private EmberTextView seeMoreButton;
    private int verticalItemDimensions;
    private View view;
    private Integer visibleVerticalItemCount;
    private String widgetId;

    public JumpBackInVisualShovelerCompactView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner) {
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
        Resources resources = getResources();
        this.visibleVerticalItemCount = Integer.valueOf(resources.getInteger(R$integer.podcast_vertical_item_count));
        this.verticalItemDimensions = getVerticalItemDimension(resources);
        this.view = findViewById(R$id.visual_shoveler_compact_view);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.visual_shoveler_compact_header);
        this.header = emberTextView;
        emberTextView.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        this.seeMoreButton = (EmberTextView) findViewById(R$id.visual_shoveler_see_all_button);
        this.recyclerView = (RecyclerView) findViewById(R$id.visual_shoveler_compact_recycler_view);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.recyclerView.setAdapter(new JumpBackInVisualShovelerCompactAdapter(this.ownerId, this.methodsDispatcher, getResources(), this.verticalItemDimensions, this.lifecycleOwner, getContext()));
        this.view.setVisibility(8);
    }

    public void bind(final LifecycleOwner lifecycleOwner, final JumpBackInElement jumpBackInElement) {
        LiveData<List<PodcastEpisode>> podcastJumpBackInEpisodes = JumpBackIns.INSTANCE.getPodcastJumpBackInEpisodes();
        if (podcastJumpBackInEpisodes == null) {
            return;
        }
        this.widgetId = jumpBackInElement.getId();
        this.onViewed = jumpBackInElement.getOnViewed();
        this.onContentFirstViewed = jumpBackInElement.getOnContentFirstViewed();
        final boolean z = !StringUtils.isBlank(this.widgetId);
        podcastJumpBackInEpisodes.observe(lifecycleOwner, new Observer<List<PodcastEpisode>>() { // from class: com.amazon.podcast.views.galleryTemplate.JumpBackInVisualShovelerCompactView.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(final List<PodcastEpisode> jumpBackIn) {
                if (jumpBackIn == null || jumpBackIn.isEmpty()) {
                    JumpBackInVisualShovelerCompactView.this.view.setVisibility(8);
                    return;
                }
                JumpBackInVisualShovelerCompactView.this.view.setVisibility(0);
                JumpBackInVisualShovelerCompactView.this.header.setText(jumpBackInElement.getHeader());
                List<JumpBackInVisualShovelerCompactAdapter.JumpbackInVerticalItem> listJumpbackInVerticalItems = JumpBackInVisualShovelerCompactView.this.jumpbackInVerticalItems(jumpBackIn);
                if (JumpBackInVisualShovelerCompactView.this.visibleVerticalItemCount == null || listJumpbackInVerticalItems.size() > JumpBackInVisualShovelerCompactView.this.visibleVerticalItemCount.intValue() + 1) {
                    JumpBackInVisualShovelerCompactView.this.seeMoreButton.setVisibility(0);
                    JumpBackInVisualShovelerCompactView.this.seeMoreButton.setText(jumpBackInElement.getSeeMoreText());
                    JumpBackInVisualShovelerCompactView.this.seeMoreButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.JumpBackInVisualShovelerCompactView.1.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            JumpBackInVisualShovelerCompactView.this.methodsDispatcher.dispatch(JumpBackInVisualShovelerCompactView.this.ownerId, jumpBackInElement.getOnSeeMoreSelected());
                        }
                    });
                } else {
                    JumpBackInVisualShovelerCompactView.this.seeMoreButton.setVisibility(4);
                }
                ((JumpBackInVisualShovelerCompactAdapter) JumpBackInVisualShovelerCompactView.this.recyclerView.getAdapter()).bind(listJumpbackInVerticalItems);
                if (z) {
                    JumpBackInVisualShovelerCompactView jumpBackInVisualShovelerCompactView = JumpBackInVisualShovelerCompactView.this;
                    jumpBackInVisualShovelerCompactView.contentViewCache.addWidget(jumpBackInVisualShovelerCompactView.widgetId);
                    JumpBackInVisualShovelerCompactView jumpBackInVisualShovelerCompactView2 = JumpBackInVisualShovelerCompactView.this;
                    jumpBackInVisualShovelerCompactView2.contentViewCache.updateNumItemsAccessible(jumpBackInVisualShovelerCompactView2.widgetId, jumpBackIn.size());
                }
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

    private int getVerticalItemDimension(final Resources resources) {
        int i = resources.getDisplayMetrics().widthPixels;
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.podcast_spacer_giant) * 3;
        int iIntValue = this.visibleVerticalItemCount.intValue();
        int i2 = R$dimen.gutter;
        int dimensionPixelSize2 = iIntValue * resources.getDimensionPixelSize(i2);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R$dimen.margin);
        int dimensionPixelSize4 = resources.getDimensionPixelSize(i2);
        int dimensionPixelSize5 = resources.getDimensionPixelSize(R$dimen.podcast_min_vertical_item_width);
        int iIntValue2 = (i - (((dimensionPixelSize2 + dimensionPixelSize3) + dimensionPixelSize4) + dimensionPixelSize)) / this.visibleVerticalItemCount.intValue();
        return iIntValue2 < dimensionPixelSize5 ? dimensionPixelSize5 : iIntValue2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<JumpBackInVisualShovelerCompactAdapter.JumpbackInVerticalItem> jumpbackInVerticalItems(final List<PodcastEpisode> jumpBackIns) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (PodcastEpisode podcastEpisode : jumpBackIns) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(DeeplinkMethods.jumpBackInEpisodePlay(podcastEpisode.getEpisodeId(), podcastEpisode.getDeeplink(), getResources()));
            arrayList2.addAll(UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_HOME, UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, MetricsEntityIds.podcastEpisode(this.isRedhoodPlusMetricsEnabled, podcastEpisode.getEpisodeId(), podcastEpisode.getPodcastEpisodeVariantId()), Integer.valueOf(i), UiMetricAttributes$ContentName.PODCASTS_JUMP_BACK_IN_SHOVELER));
            arrayList.add(new JumpBackInVisualShovelerCompactAdapter.JumpbackInVerticalItem(VerticalItemElement.builder().withOnItemSelected(arrayList2).withImage(podcastEpisode.getImage()).withBookmark(BookmarkElement.builder().withId(podcastEpisode.getEpisodeId()).build()).withPrimaryText(podcastEpisode.getTitle()).build(), podcastEpisode.getProgress(), podcastEpisode.getTotalDurationMilliseconds(), podcastEpisode.getRemainingDurationText()));
            i++;
        }
        return arrayList;
    }
}
