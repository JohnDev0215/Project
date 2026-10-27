package com.amazon.podcast.views.descriptiveShowcase;

import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElement;
import Podcast.Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.views.EmberTextView;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DescriptiveShowcaseElementView extends LinearLayout {
    private HorizontalRowItemsAdapter adapter;
    private List<HorizontalItemElement> items;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private RecyclerView recyclerView;
    private EmberTextView titleView;
    private View view;

    public DescriptiveShowcaseElementView(final Context context) {
        super(context);
        init();
    }

    public DescriptiveShowcaseElementView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.view = View.inflate(getContext(), R$layout.descriptive_showcase_view, this);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.podcast_descriptive_showcase_title);
        this.titleView = emberTextView;
        emberTextView.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        this.recyclerView = (RecyclerView) this.view.findViewById(R$id.podcast_descriptive_showcase_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void bind(final DescriptiveShowcaseElement element) {
        if (element == null) {
            return;
        }
        List<HorizontalItemElement> items = element.getItems();
        this.items = items;
        if (items == null || items.isEmpty()) {
            return;
        }
        HorizontalRowItemsAdapter horizontalRowItemsAdapter = new HorizontalRowItemsAdapter(getResources(), this.ownerId, this.methodsDispatcher);
        this.adapter = horizontalRowItemsAdapter;
        this.recyclerView.setAdapter(horizontalRowItemsAdapter);
        this.titleView.setText(element.getTitle());
        this.adapter.bind(this.items);
        if (this.items.size() > 0) {
            this.titleView.setVisibility(0);
            this.view.setVisibility(0);
        }
        bindOnViewed(element.getOnViewed());
    }

    private void bindOnViewed(final List<Method> onViewed) {
        if (onViewed == null || onViewed.isEmpty()) {
            return;
        }
        this.methodsDispatcher.dispatch(this.ownerId, onViewed);
    }

    public void setOwnerId(final String ownerId) {
        this.ownerId = ownerId;
    }

    public void setMethodsDispatcher(final MethodsDispatcher methodsDispatcher) {
        this.methodsDispatcher = methodsDispatcher;
    }
}
