package com.amazon.podcast.views.horizontalRowTemplate;

import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.AddHorizontalItemsMethod;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalRowTemplate;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.views.ActionBarView;
import com.amazon.podcast.views.BaseView;
import com.amazon.podcast.views.LoadingView;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.UiMetrics;
import com.amazon.podcast.views.horizontalRowItemsListView.HorizontalRowTemplateAdapter;
import com.google.android.gms.common.util.CollectionUtils;
import com.squareup.picasso.Picasso;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class HorizontalRowTemplateView extends BaseView<HorizontalRowTemplate> {
    private static final String TAG;
    private static final Logger logger;
    private ActionBarView actionBarView;
    private HorizontalRowTemplateAdapter adapter;
    private ImageView background;
    private Group backgroundOverlay;
    private View horizontalRowView;
    private boolean isEndOfListTriggered;
    private LinearLayoutManager layoutManager;
    private LoadingView loadingView;
    private List<Method> onEndOfList;
    private List<Method> onViewed;
    private RecyclerView recyclerView;

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
    }

    static {
        String simpleName = HorizontalRowTemplateView.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public HorizontalRowTemplateView(final TemplateContext templateContext) {
        super(templateContext);
        init();
    }

    private View init() {
        Context context = getContext();
        this.horizontalRowView = View.inflate(context, R$layout.podcast_horizontal_row_template_view, this);
        setBackgroundColor(Podcast.getRuntimeStyleSheet().getBaseColor());
        this.recyclerView = (RecyclerView) findViewById(R$id.row_template_recyclerview);
        this.background = (ImageView) findViewById(R$id.podcast_horizontal_row_template_view_background);
        this.backgroundOverlay = (Group) findViewById(R$id.background_overlay);
        LoadingView loadingView = (LoadingView) findViewById(R$id.horizontal_row_template_loading_view);
        this.loadingView = loadingView;
        loadingView.setVisibility(0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setAdapter(new HorizontalRowTemplateAdapter(context, getOwnerId(), getMethodsDispatcher(), getLifecycleOwner()));
        this.recyclerView.setLayoutManager(this.layoutManager);
        ActionBarView actionBarView = (ActionBarView) findViewById(R$id.action_bar_view);
        this.actionBarView = actionBarView;
        actionBarView.bindBackButton(new View.OnClickListener() { // from class: com.amazon.podcast.views.horizontalRowTemplate.HorizontalRowTemplateView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HorizontalRowTemplateView.this.handleBackPressed();
            }
        });
        addOnViewedListener(this.horizontalRowView);
        return this.horizontalRowView;
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final HorizontalRowTemplate template, final boolean hideActionBar) {
        if (hideActionBar) {
            this.actionBarView.setVisibility(8);
        }
        if (template != null && !template.getItems().isEmpty()) {
            this.loadingView.setVisibility(8);
        }
        this.actionBarView.bindTitle(template.getHeader());
        if (template.getBackgroundImage() != null) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            Picasso.get().load(template.getBackgroundImage()).resize(displayMetrics.widthPixels, displayMetrics.heightPixels).into(this.background);
            this.backgroundOverlay.setVisibility(0);
        }
        bindOnViewed(template);
        this.onEndOfList = template.getOnEndOfList();
        this.isEndOfListTriggered = false;
        this.adapter = (HorizontalRowTemplateAdapter) this.recyclerView.getAdapter();
        this.adapter.bind(template.isItemIndexingEnabled() == null ? true : template.isItemIndexingEnabled().booleanValue(), template.getButton(), template.getItems());
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.podcast.views.horizontalRowTemplate.HorizontalRowTemplateView.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (HorizontalRowTemplateView.this.layoutManager.getItemCount() - HorizontalRowTemplateView.this.layoutManager.findLastVisibleItemPosition() > 4 || HorizontalRowTemplateView.this.isEndOfListTriggered) {
                    return;
                }
                HorizontalRowTemplateView.this.getMethodsDispatcher().dispatch(HorizontalRowTemplateView.this.getOwnerId(), HorizontalRowTemplateView.this.onEndOfList);
                HorizontalRowTemplateView.this.isEndOfListTriggered = true;
            }
        });
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
        if (method instanceof AddHorizontalItemsMethod) {
            addListItemsMethod((AddHorizontalItemsMethod) method);
        }
    }

    @Override // com.amazon.podcast.views.UiMetrics
    public void handleOnViewed() {
        getMethodsDispatcher().dispatch(getOwnerId(), this.onViewed);
    }

    private void bindOnViewed(HorizontalRowTemplate template) {
        if (CollectionUtils.isEmpty(this.onViewed)) {
            this.onViewed = template.getOnViewed();
            if (UiMetrics.isOnScreen(this.horizontalRowView)) {
                handleOnViewed();
            }
        }
    }

    private void addListItemsMethod(final AddHorizontalItemsMethod method) {
        this.onEndOfList = method.getOnEndOfList();
        this.onViewed = method.getOnViewed();
        this.adapter.addItems(method.getItems());
        handleOnViewed();
        this.isEndOfListTriggered = false;
    }
}
