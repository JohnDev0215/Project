package com.amazon.podcast.views;

import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.BootstrapMethods;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class FooterItemViewHolder extends RecyclerView.ViewHolder {
    private final EmberTextView explore;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;

    public FooterItemViewHolder(@NonNull final View view, final String ownerId, final MethodsDispatcher methodsDispatcher, final Resources resources, final Context context) {
        super(view);
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        EmberTextView emberTextView = (EmberTextView) view.findViewById(R$id.podcast_explore_button);
        this.explore = emberTextView;
        emberTextView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.FooterItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                methodsDispatcher.dispatch(ownerId, BootstrapMethods.onBrowseStarted(context, Podcast.getUserInfoProvider().marketplaceId(), resources));
            }
        });
    }

    public FooterItemViewHolder(@NonNull final View view, final String ownerId, final MethodsDispatcher methodsDispatcher, final Resources resources, final Context context, final UiMetricAttributes$PageType pageType, final UiMetricAttributes$ContentName contentName) {
        super(view);
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        EmberTextView emberTextView = (EmberTextView) view.findViewById(R$id.podcast_explore_button);
        this.explore = emberTextView;
        emberTextView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.FooterItemViewHolder.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                boolean zIsBitesEnabled = Podcast.getFeatureGatePreferences().isBitesEnabled();
                boolean zIsEnabled = PodcastFeatureGating.PODCAST_BITES_FROM_LATEST.isEnabled();
                boolean zIsEnabled2 = PodcastFeatureGating.PODCAST_BITES_FROM_PLAYLIST.isEnabled();
                ArrayList arrayList = new ArrayList();
                if (zIsBitesEnabled && zIsEnabled && pageType.equals(UiMetricAttributes$PageType.PODCASTS_LIBRARY_LATEST)) {
                    arrayList.addAll(FooterItemViewHolder.this.getBitesMethods(resources, pageType, contentName, true));
                } else if (zIsBitesEnabled && zIsEnabled2 && pageType.equals(UiMetricAttributes$PageType.PODCASTS_LIBRARY_PLAYLIST)) {
                    arrayList.addAll(FooterItemViewHolder.this.getBitesMethods(resources, pageType, contentName, false));
                } else {
                    arrayList.addAll(FooterItemViewHolder.this.getOnBrowseStartedMethods(resources, context, pageType, contentName));
                }
                methodsDispatcher.dispatch(ownerId, arrayList);
            }
        });
    }

    public void bind(final String header, final List<Method> methods) {
        this.explore.setText(header);
        this.explore.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.FooterItemViewHolder.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FooterItemViewHolder.this.methodsDispatcher.dispatch(FooterItemViewHolder.this.ownerId, methods);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Method> getOnBrowseStartedMethods(final Resources resources, final Context context, final UiMetricAttributes$PageType pageType, final UiMetricAttributes$ContentName contentName) {
        if (Podcast.getClientState() != null) {
            Podcast.getClientState().setDeeplinkInfo(null);
        }
        String strMarketplaceId = Podcast.getUserInfoProvider().marketplaceId();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(BootstrapMethods.onBrowseStarted(context, strMarketplaceId, resources));
        arrayList.addAll(UiMetricMethods.onClicked(pageType, UiMetricAttributes$ActionType.GO_PODCASTS_HOME, contentName));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Method> getBitesMethods(final Resources resources, final UiMetricAttributes$PageType pageType, final UiMetricAttributes$ContentName contentName, final boolean includeFollowShow) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.bites(resources, includeFollowShow));
        arrayList.addAll(UiMetricMethods.onClicked(pageType, UiMetricAttributes$ActionType.GO_PODCASTS_BITES, contentName));
        return arrayList;
    }
}
