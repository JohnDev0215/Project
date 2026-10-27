package com.amazon.music.curate.skyfire.views.galleryTemplate;

import CoreInterface.v1_0.Method;
import Touch.GalleryTemplateInterface.v1_0.GalleryTemplate;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.R;
import com.amazon.music.curate.featuregating.CurateFeatureGating;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.curate.skyfire.views.TemplateView;
import com.amazon.music.curate.skyfire.views.TemplateViewContext;

/* JADX INFO: loaded from: classes4.dex */
public final class GalleryTemplateView extends TemplateView<GalleryTemplate> {
    private final int DEFAULT_VIEW_CACHE_SIZE;
    private WidgetsAdapter adapter;
    private ImageView background;
    private ImageView backgroundGradient;
    private final CurateBootstrapProviders curateBootstrapProviders;
    private ConstraintLayout galleryTemplateView;
    private LinearLayoutManager layoutManager;
    private ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener;
    private RecyclerView recyclerView;

    @Override // com.amazon.music.curate.skyfire.views.TemplateViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
    }

    public GalleryTemplateView(final TemplateViewContext templateViewContext, final CurateBootstrapProviders curateBootstrapProviders) {
        super(templateViewContext);
        this.DEFAULT_VIEW_CACHE_SIZE = 20;
        this.curateBootstrapProviders = curateBootstrapProviders;
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.library_gallery_template_view, this);
        this.galleryTemplateView = (ConstraintLayout) findViewById(R.id.library_gallery_template_view);
        this.background = (ImageView) findViewById(R.id.library_background);
        this.backgroundGradient = (ImageView) findViewById(R.id.library_background_gradient);
        this.recyclerView = (RecyclerView) findViewById(R.id.library_recycler_view);
        this.layoutManager = new LinearLayoutManager(getContext(), 1, false);
        WidgetsAdapter widgetsAdapter = new WidgetsAdapter(getLifecycleOwner(), getOwnerId(), getResources(), getMethodsDispatcher(), this.curateBootstrapProviders);
        this.adapter = widgetsAdapter;
        this.recyclerView.setAdapter(widgetsAdapter);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.setItemViewCacheSize(20);
    }

    @Override // com.amazon.music.curate.skyfire.views.TemplateViewBinder
    public void bind(final GalleryTemplate galleryTemplate) {
        WidgetsAdapter widgetsAdapter = (WidgetsAdapter) this.recyclerView.getAdapter();
        this.adapter = widgetsAdapter;
        widgetsAdapter.bind(galleryTemplate.widgetElements(), galleryTemplate.mainText());
        if (CurateFeatureGating.NATIVE_PROFILE_MY_LIBRARY_PAGE_ANDROID.isEnabled()) {
            this.backgroundGradient.setVisibility(4);
        }
        ViewTreeObserver viewTreeObserver = this.galleryTemplateView.getViewTreeObserver();
        if (viewTreeObserver == null) {
            return;
        }
        ViewTreeObserver.OnScrollChangedListener onScrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.GalleryTemplateView$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public final void onScrollChanged() {
                this.f$0.lambda$bind$0();
            }
        };
        this.mOnScrollChangedListener = onScrollChangedListener;
        viewTreeObserver.addOnScrollChangedListener(onScrollChangedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bind$0() {
        RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = this.recyclerView.findViewHolderForAdapterPosition(0);
        HeaderViewHolder headerViewHolder = viewHolderFindViewHolderForAdapterPosition instanceof HeaderViewHolder ? (HeaderViewHolder) viewHolderFindViewHolderForAdapterPosition : null;
        if (headerViewHolder == null) {
            return;
        }
        headerViewHolder.getHeaderView().getGlobalVisibleRect(new Rect());
    }

    @Override // com.amazon.music.curate.skyfire.views.TemplateViewBinder
    public void onConfigurationChanged() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 1, false);
        this.layoutManager = linearLayoutManager;
        this.recyclerView.setLayoutManager(linearLayoutManager);
        WidgetsAdapter widgetsAdapter = new WidgetsAdapter(getLifecycleOwner(), getOwnerId(), getResources(), getMethodsDispatcher(), this.curateBootstrapProviders);
        this.adapter = widgetsAdapter;
        this.recyclerView.setAdapter(widgetsAdapter);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeListeners();
    }

    public void clearAdapter() {
        this.recyclerView.setAdapter(null);
    }

    public void removeListeners() {
        ViewTreeObserver viewTreeObserver = this.galleryTemplateView.getViewTreeObserver();
        if (viewTreeObserver == null) {
            return;
        }
        viewTreeObserver.removeOnScrollChangedListener(this.mOnScrollChangedListener);
    }
}
