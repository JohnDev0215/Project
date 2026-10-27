package com.amazon.music.curate.skyfire.views.galleryTemplate;

import Touch.WidgetsInterface.v1_0.PillNavigatorWidgetItemElement;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.R;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.views.PillCellView;

/* JADX INFO: loaded from: classes4.dex */
final class PillNavigatorItemViewHolder extends RecyclerView.ViewHolder {
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final PillCellView pillCellView;

    public PillNavigatorItemViewHolder(final PillCellView pillCellView, final String ownerId, final MethodsDispatcher methodsDispatcher) {
        super(pillCellView);
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.pillCellView = pillCellView;
    }

    public void bind(final PillNavigatorWidgetItemElement element) {
        PillCellView pillCellView = this.pillCellView;
        pillCellView.setMargins(-2, -2, Integer.valueOf(pillCellView.getLeft()), Integer.valueOf(this.pillCellView.getTop()), Integer.valueOf((int) this.pillCellView.getResources().getDimension(R.dimen.spacer_mini)), Integer.valueOf(this.pillCellView.getBottom()));
        this.pillCellView.setText(element.primaryText());
        this.pillCellView.setContentDescription(element.primaryText());
        this.pillCellView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.PillNavigatorItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PillNavigatorItemViewHolder.this.methodsDispatcher.dispatchMethods(PillNavigatorItemViewHolder.this.ownerId, element.onItemSelected());
            }
        });
    }
}
