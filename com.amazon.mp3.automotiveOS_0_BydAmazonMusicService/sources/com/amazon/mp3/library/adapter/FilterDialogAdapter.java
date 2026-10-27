package com.amazon.mp3.library.adapter;

import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.FilterableFragment;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FilterDialogAdapter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001*B\u001f\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001f\u001a\u00020\u001e\u0012\u0006\u0010$\u001a\u00020#¢\u0006\u0004\b(\u0010)J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0002J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0018\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\u0018\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0012H\u0016J\b\u0010\u0018\u001a\u00020\u0012H\u0016R\u0017\u0010\u001a\u001a\u00020\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u001f\u001a\u00020\u001e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u0017\u0010$\u001a\u00020#8\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'¨\u0006+"}, d2 = {"Lcom/amazon/mp3/library/adapter/FilterDialogAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/mp3/library/adapter/FilterDialogAdapter$PlaylistFilterRowViewHolder;", "filterRow", "Lcom/amazon/music/views/library/models/FilterItemModel;", "model", "", "bindFilterRow", "Landroid/widget/TextView;", "textView", "setUpFilterTitleClickHandling", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "sendUiClickMetric", "Landroid/view/ViewGroup;", "parent", "", "viewType", "onCreateViewHolder", "holder", "position", "onBindViewHolder", "getItemCount", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", PageUriUtils.FILTER_QUERY_PARAM, "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "getFilter", "()Lcom/amazon/music/views/library/models/FilterCollectionModel;", "Landroidx/fragment/app/DialogFragment;", "dialogFragment", "Landroidx/fragment/app/DialogFragment;", "getDialogFragment", "()Landroidx/fragment/app/DialogFragment;", "Lcom/amazon/mp3/catalog/fragment/FilterableFragment;", "hostFragment", "Lcom/amazon/mp3/catalog/fragment/FilterableFragment;", "getHostFragment", "()Lcom/amazon/mp3/catalog/fragment/FilterableFragment;", "<init>", "(Lcom/amazon/music/views/library/models/FilterCollectionModel;Landroidx/fragment/app/DialogFragment;Lcom/amazon/mp3/catalog/fragment/FilterableFragment;)V", "PlaylistFilterRowViewHolder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class FilterDialogAdapter extends RecyclerView.Adapter<PlaylistFilterRowViewHolder> {
    private final DialogFragment dialogFragment;
    private final FilterCollectionModel filter;
    private final FilterableFragment hostFragment;

    public final DialogFragment getDialogFragment() {
        return this.dialogFragment;
    }

    /* JADX INFO: compiled from: FilterDialogAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/library/adapter/FilterDialogAdapter$PlaylistFilterRowViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "playlistFilterRowView", "Landroid/view/View;", "(Landroid/view/View;)V", "getPlaylistFilterRowView", "()Landroid/view/View;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class PlaylistFilterRowViewHolder extends RecyclerView.ViewHolder {
        private final View playlistFilterRowView;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PlaylistFilterRowViewHolder(View playlistFilterRowView) {
            super(playlistFilterRowView);
            Intrinsics.checkNotNullParameter(playlistFilterRowView, "playlistFilterRowView");
            this.playlistFilterRowView = playlistFilterRowView;
        }

        public final View getPlaylistFilterRowView() {
            return this.playlistFilterRowView;
        }
    }

    public FilterDialogAdapter(FilterCollectionModel filter, DialogFragment dialogFragment, FilterableFragment hostFragment) {
        Intrinsics.checkNotNullParameter(filter, "filter");
        Intrinsics.checkNotNullParameter(dialogFragment, "dialogFragment");
        Intrinsics.checkNotNullParameter(hostFragment, "hostFragment");
        this.filter = filter;
        this.dialogFragment = dialogFragment;
        this.hostFragment = hostFragment;
    }

    public final FilterableFragment getHostFragment() {
        return this.hostFragment;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public PlaylistFilterRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View playlistFilterRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_filter_row, parent, false);
        Intrinsics.checkNotNullExpressionValue(playlistFilterRowView, "playlistFilterRowView");
        return new PlaylistFilterRowViewHolder(playlistFilterRowView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(PlaylistFilterRowViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        FilterItemModel filterItemModel = this.filter.getFilters().get(position);
        if (filterItemModel == null) {
            return;
        }
        bindFilterRow(holder, filterItemModel);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.filter.getFilters().size();
    }

    private final void bindFilterRow(PlaylistFilterRowViewHolder filterRow, FilterItemModel model) {
        TextView filterTitleView = (TextView) filterRow.getPlaylistFilterRowView().findViewById(R.id.filter_title);
        ImageView imageView = (ImageView) filterRow.getPlaylistFilterRowView().findViewById(R.id.filter_badge);
        Intrinsics.checkNotNullExpressionValue(filterTitleView, "filterTitleView");
        setUpFilterTitleClickHandling(model, filterTitleView);
        String browseId = model.getBrowseId();
        FilterItemModel value = this.hostFragment.getActiveFilter().getValue();
        if (Intrinsics.areEqual(browseId, value == null ? null : value.getBrowseId())) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
    }

    private final void setUpFilterTitleClickHandling(final FilterItemModel model, TextView textView) {
        SpannableString spannableString = new SpannableString(model.getTitle());
        spannableString.setSpan(new ClickableSpan() { // from class: com.amazon.mp3.library.adapter.FilterDialogAdapter$setUpFilterTitleClickHandling$clickableSpan$1
            @Override // android.text.style.ClickableSpan
            public void onClick(View textView2) {
                Intrinsics.checkNotNullParameter(textView2, "textView");
                FilterCollectionModel parentCollection = model.getParentCollection();
                if (parentCollection != null) {
                    parentCollection.setSelectedFilter(model);
                }
                this.getHostFragment().getActiveFilter().setValue(model);
                this.getDialogFragment().dismiss();
                this.sendUiClickMetric(ActionType.APPLY_FILTER, PageType.BROWSE_PLAYLISTS_FILTER);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint ds) {
                Intrinsics.checkNotNullParameter(ds, "ds");
                String browseId = model.getBrowseId();
                FilterItemModel value = this.getHostFragment().getActiveFilter().getValue();
                if (Intrinsics.areEqual(browseId, value == null ? null : value.getBrowseId())) {
                    ds.setColor(ContextCompat.getColor(this.getDialogFragment().requireContext(), R.color.blue_accent));
                } else {
                    ds.setColor(ContextCompat.getColor(this.getDialogFragment().requireContext(), R.color.white));
                }
            }
        }, 0, spannableString.length(), 33);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendUiClickMetric(ActionType actionType, PageType pageType) {
        UiClickEvent uiClickEventBuild = UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withPageType(pageType).withEventTime(System.currentTimeMillis()).build();
        if (uiClickEventBuild != null) {
            MetricsHolder.getManager().handleEvent(uiClickEventBuild);
        }
    }
}
