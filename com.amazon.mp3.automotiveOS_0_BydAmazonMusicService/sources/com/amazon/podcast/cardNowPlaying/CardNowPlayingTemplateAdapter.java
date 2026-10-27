package com.amazon.podcast.cardNowPlaying;

import Podcast.FollowInterface.v1_0.FooterElement;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardElement;
import SOATemplateListInterface.v1_0.TemplateElement;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.views.PTCWidgetView;
import com.amazon.podcast.views.followsTemplate.PTCWidgetViewHolder;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
final class CardNowPlayingTemplateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int cardHeight;
    private int cardWidth;
    private final FragmentManager fragmentManager;
    private boolean isInitialBind = true;
    private List<TemplateElement> items = new ArrayList();
    private final LifecycleOwner lifecycleOwner;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final Resources resources;

    private enum ViewType {
        card(0),
        ptcIngress(1);

        private final int value;

        ViewType(final int value) {
            this.value = value;
        }
    }

    public CardNowPlayingTemplateAdapter(final String ownerId, final Resources resources, final MethodsDispatcher methodsDispatcher, final FragmentManager fragmentManager, final LifecycleOwner lifecycleOwner, final int cardWidth, final int cardHeight) {
        this.ownerId = ownerId;
        this.resources = resources;
        this.methodsDispatcher = methodsDispatcher;
        this.fragmentManager = fragmentManager;
        this.lifecycleOwner = lifecycleOwner;
        this.cardWidth = cardWidth;
        this.cardHeight = cardHeight;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int viewType) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
        if (ViewType.card.value == viewType) {
            return new CardNowPlayingViewHolder(layoutInflaterFrom.inflate(R$layout.podcast_card_view, viewGroup, false), this.resources, this.ownerId, this.methodsDispatcher, this.fragmentManager, this.cardWidth, this.cardHeight);
        }
        PTCWidgetView pTCWidgetView = new PTCWidgetView(viewGroup.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        pTCWidgetView.setLayoutParams(layoutParams);
        pTCWidgetView.getChildAt(0).setLayoutParams(layoutParams);
        return new PTCWidgetViewHolder(pTCWidgetView, this.methodsDispatcher, this.ownerId);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof CardNowPlayingViewHolder) {
            CardElement cardElement = (CardElement) this.items.get(position);
            CardNowPlayingViewHolder cardNowPlayingViewHolder = (CardNowPlayingViewHolder) viewHolder;
            cardNowPlayingViewHolder.setLifecycleOwner(this.lifecycleOwner);
            cardNowPlayingViewHolder.bind(cardElement);
            if (this.isInitialBind && position == Podcast.getPlayback().getBitesPlaybackStartingIndex()) {
                cardNowPlayingViewHolder.onPlaybackStateChange(8);
                cardNowPlayingViewHolder.addMediaMetadataCallbacks();
                Podcast.getPlayback().dispatch(this.ownerId, cardElement.getOnCardCentered());
                this.isInitialBind = false;
                return;
            }
            return;
        }
        if (viewHolder instanceof PTCWidgetViewHolder) {
            ((PTCWidgetViewHolder) viewHolder).bind((FooterElement) this.items.get(position), false);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(final int position) {
        return this.items.get(position) instanceof CardElement ? ViewType.card.value : ViewType.ptcIngress.value;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof CardNowPlayingViewHolder) {
            ((CardNowPlayingViewHolder) viewHolder).setAboutViewVisibility(8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<TemplateElement> list = this.items;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void addItems(final List<CardElement> newItems) {
        if (CollectionUtils.isEmpty(newItems)) {
            return;
        }
        int size = this.items.size();
        this.items.addAll(newItems);
        notifyItemRangeChanged(size, newItems.size());
    }

    public void bindCards(final List<CardElement> cards) {
        if (cards == null || cards.isEmpty()) {
            return;
        }
        this.items = new ArrayList(cards);
        notifyDataSetChanged();
    }

    public TemplateElement getItem(int index) {
        if (index < 0 || index >= this.items.size()) {
            return null;
        }
        return this.items.get(index);
    }

    public void addPTCIngress() {
        if (PodcastFeatureGating.TASTE_COLLECTION.isEnabled()) {
            this.items.add(FooterElement.builder().withOnItemSelected(DeeplinkMethods.ptc(this.resources)).withText(this.resources.getString(R$string.podcast_ptc_lets_get_started)).build());
            notifyDataSetChanged();
        }
    }

    public boolean isInitialBind() {
        return this.isInitialBind;
    }
}
