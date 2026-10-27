package com.amazon.podcast.views.descriptiveShowcase;

import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElement;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class HorizontalRowItemsAdapter extends RecyclerView.Adapter<HorizontalItemViewHolder> {
    private int imageDimension;
    private List<HorizontalItemElement> items;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private RoundedCornersTransformation transformation;

    HorizontalRowItemsAdapter(final Resources resources, final String ownerId, final MethodsDispatcher methodsDispatcher) {
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.transformation = new RoundedCornersTransformation(resources.getDimensionPixelSize(R$dimen.podcast_horizontal_row_item_image_corner_radius));
        this.imageDimension = resources.getDimensionPixelSize(R$dimen.podcast_horizontal_row_item_image_dimension);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public HorizontalItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new HorizontalItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.podcast_horizontal_row_item, viewGroup, false), this.imageDimension, this.transformation, this.ownerId, this.methodsDispatcher);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final HorizontalItemViewHolder viewHolder, final int position) {
        final HorizontalItemElement horizontalItemElement = this.items.get(position);
        viewHolder.bind(-1, horizontalItemElement, new View.OnClickListener() { // from class: com.amazon.podcast.views.descriptiveShowcase.HorizontalRowItemsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                viewHolder.followButton.setImageResource(R$drawable.ic_following);
                HorizontalItemElement horizontalItemElement2 = horizontalItemElement;
                if (horizontalItemElement2 != null && horizontalItemElement2.getFollow() != null) {
                    HorizontalRowItemsAdapter.this.methodsDispatcher.dispatch(HorizontalRowItemsAdapter.this.ownerId, horizontalItemElement.getFollow().getOnFollow());
                }
                HorizontalRowItemsAdapter.this.items.remove(position);
                HorizontalRowItemsAdapter.this.notifyItemRemoved(position);
                HorizontalRowItemsAdapter horizontalRowItemsAdapter = HorizontalRowItemsAdapter.this;
                horizontalRowItemsAdapter.notifyItemRangeChanged(position, horizontalRowItemsAdapter.items.size());
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<HorizontalItemElement> list = this.items;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void bind(final List<HorizontalItemElement> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
