package com.amazon.podcast.views.verticalGridTemplate;

import Podcast.Touch.VerticalGridTemplateInterface.v1_0.AddVerticalItemsMethod;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.ButtonElement;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalGridTemplate;
import SOACoreInterface.v1_0.Method;
import android.content.res.Resources;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$integer;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.views.ActionBarView;
import com.amazon.podcast.views.BaseView;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.UiMetrics;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class VerticalGridTemplateView extends BaseView<VerticalGridTemplate> {
    private static final String TAG;
    private static final Logger logger;
    private ActionBarView actionBarView;
    private VerticalItemsAdapter adapter;
    private EmberTextView button;
    private int columns;
    private boolean isEndOfListTriggered;
    private GridLayoutManager layoutManager;
    private List<Method> onEndOfList;
    private List<Method> onViewed;
    private RecyclerView recyclerView;
    private View verticalGridView;
    private int verticalItemDimension;

    static {
        String simpleName = VerticalGridTemplateView.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public VerticalGridTemplateView(final TemplateContext templateContext) {
        super(templateContext);
        init();
    }

    private View init() {
        this.verticalGridView = View.inflate(getContext(), R$layout.podcast_vertical_grid_template_view, this);
        this.isEndOfListTriggered = false;
        this.button = (EmberTextView) findViewById(R$id.grid_template_button);
        this.recyclerView = (RecyclerView) findViewById(R$id.grid_template_recycler_view);
        this.verticalItemDimension = getVerticalItemDimension();
        this.columns = getResources().getInteger(R$integer.podcast_grid_template_columns);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), this.columns);
        this.layoutManager = gridLayoutManager;
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.recyclerView.setAdapter(new VerticalItemsAdapter(getOwnerId(), getResources(), getMethodsDispatcher(), this.verticalItemDimension));
        ActionBarView actionBarView = (ActionBarView) findViewById(R$id.action_bar_view);
        this.actionBarView = actionBarView;
        actionBarView.bindBackButton(new View.OnClickListener() { // from class: com.amazon.podcast.views.verticalGridTemplate.VerticalGridTemplateView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VerticalGridTemplateView.this.handleBackPressed();
            }
        });
        addOnViewedListener(this.verticalGridView);
        return this.verticalGridView;
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final VerticalGridTemplate template, final boolean hideActionBar) {
        if (hideActionBar) {
            this.actionBarView.setVisibility(8);
        }
        this.actionBarView.bindTitle(template.getHeader());
        final ButtonElement button = template.getButton();
        if (button == null) {
            this.button.setVisibility(8);
        } else {
            this.button.setVisibility(0);
            this.button.setText(button.getText());
            this.button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.verticalGridTemplate.VerticalGridTemplateView.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    VerticalGridTemplateView.this.getMethodsDispatcher().dispatch(VerticalGridTemplateView.this.getOwnerId(), button.getOnItemSelected());
                }
            });
        }
        bindOnViewed(template);
        this.onEndOfList = template.getOnEndOfList();
        VerticalItemsAdapter verticalItemsAdapter = (VerticalItemsAdapter) this.recyclerView.getAdapter();
        this.adapter = verticalItemsAdapter;
        verticalItemsAdapter.bind(template.getItems());
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.podcast.views.verticalGridTemplate.VerticalGridTemplateView.3
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (VerticalGridTemplateView.this.layoutManager.getItemCount() - VerticalGridTemplateView.this.layoutManager.findLastVisibleItemPosition() > 4 || VerticalGridTemplateView.this.isEndOfListTriggered) {
                    return;
                }
                VerticalGridTemplateView.this.getMethodsDispatcher().dispatch(VerticalGridTemplateView.this.getOwnerId(), VerticalGridTemplateView.this.onEndOfList);
                VerticalGridTemplateView.this.isEndOfListTriggered = true;
            }
        });
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
        if (method instanceof AddVerticalItemsMethod) {
            addListItemsMethod((AddVerticalItemsMethod) method);
        }
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
        this.columns = getResources().getInteger(R$integer.podcast_grid_template_columns);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), this.columns);
        this.layoutManager = gridLayoutManager;
        this.recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override // com.amazon.podcast.views.UiMetrics
    public void handleOnViewed() {
        getMethodsDispatcher().dispatch(getOwnerId(), this.onViewed);
    }

    private void bindOnViewed(VerticalGridTemplate template) {
        if (CollectionUtils.isEmpty(this.onViewed)) {
            this.onViewed = template.getOnViewed();
            if (UiMetrics.isOnScreen(this.verticalGridView)) {
                handleOnViewed();
            }
        }
    }

    private void addListItemsMethod(final AddVerticalItemsMethod method) {
        this.onEndOfList = method.getOnEndOfList();
        this.onViewed = method.getOnViewed();
        this.adapter.addItems(method.getItems());
        handleOnViewed();
        this.isEndOfListTriggered = false;
    }

    private int getVerticalItemDimension() {
        Resources resources = getResources();
        int i = resources.getDisplayMetrics().widthPixels;
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.margin) * 2;
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R$dimen.gutter);
        int integer = resources.getInteger(R$integer.podcast_grid_template_columns);
        return (i - (((integer * dimensionPixelSize2) + dimensionPixelSize) + dimensionPixelSize2)) / integer;
    }
}
