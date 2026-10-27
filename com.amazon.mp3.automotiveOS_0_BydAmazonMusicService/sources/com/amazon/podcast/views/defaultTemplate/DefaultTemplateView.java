package com.amazon.podcast.views.defaultTemplate;

import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.Template;
import android.view.View;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.views.ActionBarView;
import com.amazon.podcast.views.BaseView;
import com.amazon.podcast.views.LoadingView;
import com.amazon.podcast.views.TemplateContext;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class DefaultTemplateView extends BaseView<Template> {
    private ActionBarView actionBarView;
    private LoadingView loadingView;
    private List<Method> onViewed;

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
    }

    public DefaultTemplateView(final TemplateContext templateContext) {
        super(templateContext);
        init();
    }

    private void init() {
        View viewInflate = View.inflate(getContext(), R$layout.podcast_default_template, this);
        setBackgroundColor(Podcast.getRuntimeStyleSheet().getBaseColor());
        LoadingView loadingView = (LoadingView) findViewById(R$id.default_loading_view);
        this.loadingView = loadingView;
        loadingView.setVisibility(0);
        ActionBarView actionBarView = (ActionBarView) findViewById(R$id.action_bar_view);
        this.actionBarView = actionBarView;
        actionBarView.bindBackButton(new View.OnClickListener() { // from class: com.amazon.podcast.views.defaultTemplate.DefaultTemplateView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DefaultTemplateView.this.handleBackPressed();
            }
        });
        addOnViewedListener(viewInflate);
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final Template template, final boolean hideActionBar) {
        if (hideActionBar) {
            this.actionBarView.setVisibility(8);
        }
        this.onViewed = template.getOnViewed();
    }

    @Override // com.amazon.podcast.views.UiMetrics
    public void handleOnViewed() {
        getMethodsDispatcher().dispatch(getOwnerId(), this.onViewed);
    }
}
