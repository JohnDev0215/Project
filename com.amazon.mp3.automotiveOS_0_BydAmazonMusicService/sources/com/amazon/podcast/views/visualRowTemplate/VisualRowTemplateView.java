package com.amazon.podcast.views.visualRowTemplate;

import Podcast.Touch.VisualRowTemplateInterface.v1_0.AddVisualRowItemsMethod;
import Podcast.Touch.VisualRowTemplateInterface.v1_0.VisualRowTemplate;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.views.BaseView;
import com.amazon.podcast.views.TemplateContext;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class VisualRowTemplateView extends BaseView<VisualRowTemplate> {
    private static final String TAG;
    private static final Logger logger;
    private VisualRowItemsAdapter adapter;
    private DividerItemDecoration divider;
    private boolean isEndOfListTriggered;
    private LinearLayoutManager layoutManager;
    private List<Method> onEndOfList;
    private List<Method> onViewed;
    private RecyclerView recyclerView;
    private final TemplateContext templateContext;

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
    }

    static {
        String simpleName = VisualRowTemplateView.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public VisualRowTemplateView(final TemplateContext templateContext) {
        super(templateContext);
        this.templateContext = templateContext;
        init();
    }

    private View init() {
        Context context = getContext();
        View viewInflate = View.inflate(context, R$layout.podcast_visual_row_template, this);
        this.recyclerView = (RecyclerView) findViewById(R$id.podcast_visual_row_template_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        this.layoutManager = linearLayoutManager;
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setAdapter(new VisualRowItemsAdapter(getResources(), getOwnerId(), getMethodsDispatcher(), getLifecycleOwner(), getContext(), this.templateContext, UiMetricAttributes$PageType.PODCAST_SHOW_DETAIL));
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.setItemViewCacheSize(10);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, 1);
        this.divider = dividerItemDecoration;
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(context, R$drawable.visual_row_items_divider_view));
        this.recyclerView.addItemDecoration(this.divider);
        return viewInflate;
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final VisualRowTemplate template, final boolean hideActionBar) {
        this.onViewed = template.getOnViewed();
        this.onEndOfList = template.getOnEndOfList();
        this.isEndOfListTriggered = false;
        VisualRowItemsAdapter visualRowItemsAdapter = (VisualRowItemsAdapter) this.recyclerView.getAdapter();
        this.adapter = visualRowItemsAdapter;
        visualRowItemsAdapter.bind(template.getItems());
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.podcast.views.visualRowTemplate.VisualRowTemplateView.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (VisualRowTemplateView.this.layoutManager.getItemCount() - VisualRowTemplateView.this.layoutManager.findLastVisibleItemPosition() > 4 || VisualRowTemplateView.this.isEndOfListTriggered) {
                    return;
                }
                VisualRowTemplateView.this.getMethodsDispatcher().dispatch(VisualRowTemplateView.this.getOwnerId(), VisualRowTemplateView.this.onEndOfList);
                VisualRowTemplateView.this.isEndOfListTriggered = true;
            }
        });
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
        if (method instanceof AddVisualRowItemsMethod) {
            addListItemsMethod((AddVisualRowItemsMethod) method);
        }
    }

    @Override // com.amazon.podcast.views.UiMetrics
    public void handleOnViewed() {
        getMethodsDispatcher().dispatch(getOwnerId(), this.onViewed);
    }

    private void addListItemsMethod(final AddVisualRowItemsMethod method) {
        this.onEndOfList = method.getOnEndOfList();
        this.onViewed = method.getOnViewed();
        this.adapter.addItems(method.getItems());
        handleOnViewed();
        this.isEndOfListTriggered = false;
    }
}
