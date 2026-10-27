package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.CompactPillItemElementInterface.v1_0.CompactPillItemElement;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.google.android.flexbox.FlexboxLayoutManager;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
final class CompactPillListingView extends LinearLayout {
    private RecyclerView recyclerView;

    public CompactPillListingView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setOwnerId(final String ownerId) {
        ((CompactPillItemAdapter) this.recyclerView.getAdapter()).setOwnerId(ownerId);
    }

    public void setMethodsDispatcher(final MethodsDispatcher methodsDispatcher) {
        ((CompactPillItemAdapter) this.recyclerView.getAdapter()).setMethodsDispatcher(methodsDispatcher);
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_about_view_compact_pill_listing, this);
        this.recyclerView = (RecyclerView) findViewById(R$id.compact_pill_listing_recycler_view);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getContext());
        flexboxLayoutManager.setFlexDirection(0);
        flexboxLayoutManager.setFlexWrap(1);
        this.recyclerView.setLayoutManager(flexboxLayoutManager);
        this.recyclerView.setAdapter(new CompactPillItemAdapter());
    }

    public void bind(final List<CompactPillItemElement> elements) {
        ((CompactPillItemAdapter) this.recyclerView.getAdapter()).bind(elements);
    }
}
