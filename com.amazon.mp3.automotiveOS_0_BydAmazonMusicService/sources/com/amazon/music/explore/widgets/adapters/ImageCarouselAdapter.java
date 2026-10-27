package com.amazon.music.explore.widgets.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.explore.R;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.ui.foundations.ktx.ViewKt;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageCarouselAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010J\u001a\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006H\u0002J\u0006\u0010\u0015\u001a\u00020\u000eJ\b\u0010\u0016\u001a\u00020\bH\u0016J\u0018\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\bH\u0016J\u0018\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\bH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/explore/widgets/adapters/ImageCarouselAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/music/explore/widgets/adapters/ImageCarouselAdapter$ImageCarouselItemViewHolder;", "()V", ContextMappingConstants.ITEMS, "", "", "placeholderResId", "", "getPlaceholderResId", "()I", "setPlaceholderResId", "(I)V", "addItems", "", "newItems", "", "bindImage", "view", "Landroid/widget/ImageView;", ContextMappingConstants.IMAGE, "clear", "getItemCount", "onBindViewHolder", "viewHolder", "position", "onCreateViewHolder", "viewGroup", "Landroid/view/ViewGroup;", "ImageCarouselItemViewHolder", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ImageCarouselAdapter extends RecyclerView.Adapter<ImageCarouselItemViewHolder> {
    private List<String> items = new ArrayList();
    private int placeholderResId;

    public final int getPlaceholderResId() {
        return this.placeholderResId;
    }

    public final void setPlaceholderResId(int i) {
        this.placeholderResId = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ImageCarouselItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        return new ImageCarouselItemViewHolder(ViewGroupKt.inflate$default(viewGroup, R.layout.image_carousel_view, false, 2, null));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ImageCarouselItemViewHolder viewHolder, int position) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        bindImage(viewHolder.getImageView(), this.items.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    public final void addItems(List<String> newItems) {
        Intrinsics.checkNotNullParameter(newItems, "newItems");
        int size = this.items.size();
        this.items.addAll(newItems);
        notifyItemRangeInserted(size, newItems.size());
    }

    public final void clear() {
        this.items.clear();
        notifyDataSetChanged();
    }

    private final void bindImage(ImageView view, String image) {
        ViewKt.collapseIf(view, image == null || image.length() == 0);
        if (image == null || image.length() == 0) {
            return;
        }
        RequestCreator requestCreatorLoad = Picasso.get().load(image);
        if (getPlaceholderResId() != 0) {
            requestCreatorLoad.placeholder(getPlaceholderResId());
        }
        requestCreatorLoad.into(view);
    }

    /* JADX INFO: compiled from: ImageCarouselAdapter.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/music/explore/widgets/adapters/ImageCarouselAdapter$ImageCarouselItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "imageView", "Landroid/widget/ImageView;", "getImageView", "()Landroid/widget/ImageView;", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ImageCarouselItemViewHolder extends RecyclerView.ViewHolder {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ImageCarouselItemViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
        }

        public final ImageView getImageView() {
            View viewFindViewById = this.itemView.findViewById(R.id.image_view);
            if (viewFindViewById != null) {
                return (ImageView) viewFindViewById;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
        }
    }
}
