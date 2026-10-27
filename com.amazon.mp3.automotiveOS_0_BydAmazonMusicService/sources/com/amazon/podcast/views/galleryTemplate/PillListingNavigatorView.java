package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.PillListingNavigatorElementInterface.v1_0.PillListingNavigatorElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.views.EmberTextView;
import com.google.android.flexbox.FlexboxLayoutManager;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
final class PillListingNavigatorView extends LinearLayout {
    private EmberTextView header;
    private boolean isFirstView;
    private final MethodsDispatcher methodsDispatcher;
    private List<Method> onContentFirstViewed;
    private List<Method> onViewed;
    private final String ownerId;
    private RecyclerView recyclerView;

    public PillListingNavigatorView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher) {
        super(context);
        this.isFirstView = true;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_pill_listing_navigator, this);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.pill_listing_navigator_header);
        this.header = emberTextView;
        emberTextView.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        this.recyclerView = (RecyclerView) findViewById(R$id.pill_listing_navigator_recycler_view);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getContext());
        flexboxLayoutManager.setFlexDirection(0);
        flexboxLayoutManager.setFlexWrap(1);
        this.recyclerView.setLayoutManager(flexboxLayoutManager);
        this.recyclerView.setAdapter(new PillItemAdapter(this.ownerId, this.methodsDispatcher));
    }

    public void bind(final PillListingNavigatorElement element) {
        this.header.setText(element.getHeader());
        ((PillItemAdapter) this.recyclerView.getAdapter()).bind(element.getItems());
        this.onViewed = element.getOnViewed();
        this.onContentFirstViewed = element.getOnContentFirstViewed();
    }

    public void handleOnViewed() {
        this.methodsDispatcher.dispatch(this.ownerId, this.onViewed);
        if (this.isFirstView) {
            handleOnContentFirstViewed();
        }
    }

    private void handleOnContentFirstViewed() {
        this.methodsDispatcher.dispatch(this.ownerId, this.onContentFirstViewed);
        this.isFirstView = false;
    }
}
