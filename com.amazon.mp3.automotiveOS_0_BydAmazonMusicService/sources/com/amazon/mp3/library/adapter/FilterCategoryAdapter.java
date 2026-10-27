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
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.FilterSelectionDialogFragment;
import com.amazon.mp3.catalog.fragment.FilterableFragment;
import com.amazon.music.views.library.models.FilterCollectionModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FilterCategoryAdapter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001%B%\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0014\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001f\u001a\u00020\u001e¢\u0006\u0004\b#\u0010$J \u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J \u0010\f\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0018\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0006H\u0016J\u0018\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\u0013\u001a\u00020\u0006H\u0016R\u001d\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u001a\u001a\u00020\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u001f\u001a\u00020\u001e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"¨\u0006&"}, d2 = {"Lcom/amazon/mp3/library/adapter/FilterCategoryAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/mp3/library/adapter/FilterCategoryAdapter$PlaylistFilterRowViewHolder;", "filterRow", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "model", "", "position", "", "bindFilterRow", "Landroid/widget/TextView;", "textView", "setUpFilterTitleClickHandling", "Landroid/view/ViewGroup;", "parent", "viewType", "onCreateViewHolder", "holder", "onBindViewHolder", "getItemCount", "", "filters", "Ljava/util/List;", "getFilters", "()Ljava/util/List;", "Lcom/amazon/mp3/catalog/fragment/FilterSelectionDialogFragment;", "dialogFragment", "Lcom/amazon/mp3/catalog/fragment/FilterSelectionDialogFragment;", "getDialogFragment", "()Lcom/amazon/mp3/catalog/fragment/FilterSelectionDialogFragment;", "Lcom/amazon/mp3/catalog/fragment/FilterableFragment;", "hostFragment", "Lcom/amazon/mp3/catalog/fragment/FilterableFragment;", "getHostFragment", "()Lcom/amazon/mp3/catalog/fragment/FilterableFragment;", "<init>", "(Ljava/util/List;Lcom/amazon/mp3/catalog/fragment/FilterSelectionDialogFragment;Lcom/amazon/mp3/catalog/fragment/FilterableFragment;)V", "PlaylistFilterRowViewHolder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class FilterCategoryAdapter extends RecyclerView.Adapter<PlaylistFilterRowViewHolder> {
    private final FilterSelectionDialogFragment dialogFragment;
    private final List<FilterCollectionModel> filters;
    private final FilterableFragment hostFragment;

    public final FilterSelectionDialogFragment getDialogFragment() {
        return this.dialogFragment;
    }

    /* JADX INFO: compiled from: FilterCategoryAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/library/adapter/FilterCategoryAdapter$PlaylistFilterRowViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "playlistFilterRowView", "Landroid/view/View;", "(Landroid/view/View;)V", "getPlaylistFilterRowView", "()Landroid/view/View;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
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

    public FilterCategoryAdapter(List<FilterCollectionModel> filters, FilterSelectionDialogFragment dialogFragment, FilterableFragment hostFragment) {
        Intrinsics.checkNotNullParameter(filters, "filters");
        Intrinsics.checkNotNullParameter(dialogFragment, "dialogFragment");
        Intrinsics.checkNotNullParameter(hostFragment, "hostFragment");
        this.filters = filters;
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
        FilterCollectionModel filterCollectionModel = this.filters.get(position);
        if (filterCollectionModel == null) {
            return;
        }
        bindFilterRow(holder, filterCollectionModel, position);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.filters.size();
    }

    private final void bindFilterRow(PlaylistFilterRowViewHolder filterRow, FilterCollectionModel model, int position) {
        TextView filterTitleView = (TextView) filterRow.getPlaylistFilterRowView().findViewById(R.id.filter_title);
        ImageView imageView = (ImageView) filterRow.getPlaylistFilterRowView().findViewById(R.id.nav_button_icon);
        ImageView imageView2 = (ImageView) filterRow.getPlaylistFilterRowView().findViewById(R.id.filter_badge);
        if (!model.getFilters().isEmpty()) {
            imageView.setVisibility(0);
        }
        Intrinsics.checkNotNullExpressionValue(filterTitleView, "filterTitleView");
        setUpFilterTitleClickHandling(model, filterTitleView, position);
        if (model.getSelectedFilter() != null && Intrinsics.areEqual(model.getSelectedFilter(), this.hostFragment.getActiveFilter().getValue())) {
            imageView2.setVisibility(0);
        } else {
            imageView2.setVisibility(8);
        }
    }

    private final void setUpFilterTitleClickHandling(final FilterCollectionModel model, TextView textView, final int position) {
        SpannableString spannableString = new SpannableString(model.getTitle());
        spannableString.setSpan(new ClickableSpan() { // from class: com.amazon.mp3.library.adapter.FilterCategoryAdapter$setUpFilterTitleClickHandling$clickableSpan$1
            @Override // android.text.style.ClickableSpan
            public void onClick(View textView2) {
                Intrinsics.checkNotNullParameter(textView2, "textView");
                if (model.getFilters().isEmpty()) {
                    this.getHostFragment().getActiveFilter().setValue(null);
                    this.getDialogFragment().dismiss();
                } else {
                    this.getDialogFragment().showFilterDetail(position);
                }
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint ds) {
                Intrinsics.checkNotNullParameter(ds, "ds");
                if (model.getSelectedFilter() != null && Intrinsics.areEqual(model.getSelectedFilter(), this.getHostFragment().getActiveFilter().getValue())) {
                    ds.setColor(ContextCompat.getColor(this.getDialogFragment().requireContext(), R.color.blue_accent));
                } else {
                    ds.setColor(ContextCompat.getColor(this.getDialogFragment().requireContext(), R.color.white));
                }
            }
        }, 0, spannableString.length(), 33);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
