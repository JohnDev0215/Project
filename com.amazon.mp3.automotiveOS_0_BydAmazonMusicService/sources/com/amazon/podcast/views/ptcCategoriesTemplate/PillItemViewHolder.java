package com.amazon.podcast.views.ptcCategoriesTemplate;

import Podcast.Touch.PTCTemplateInterface.v1_0.PillItemElement;
import android.content.res.Resources;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$id;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.ptcCategoriesTemplate.PillItemAdapter;

/* JADX INFO: loaded from: classes5.dex */
final class PillItemViewHolder extends RecyclerView.ViewHolder {
    private PillItemElement element;
    private Boolean isSelected;
    private final PillItemAdapter.OnPillStateUpdateListener listener;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final Resources resources;
    private final EmberTextView text;

    public PillItemViewHolder(final View view, final String ownerId, final MethodsDispatcher methodsDispatcher, final PillItemAdapter.OnPillStateUpdateListener listener) {
        super(view);
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.text = (EmberTextView) view.findViewById(R$id.pill_item);
        this.listener = listener;
        this.resources = view.getResources();
    }

    public final void bind(final PillItemElement element, final boolean isSelectedState) {
        this.element = element;
        restoreState(isSelectedState);
        this.text.setBackgroundResource(Podcast.getRuntimeStyleSheet().getPillItemBorderDrawableId());
        this.text.setText(element.getText());
        this.text.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.ptcCategoriesTemplate.PillItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                if (PillItemViewHolder.this.isSelected.booleanValue()) {
                    PillItemViewHolder.this.deselectItem();
                    PillItemViewHolder.this.methodsDispatcher.dispatch(PillItemViewHolder.this.ownerId, element.getOnItemDeselected());
                } else {
                    PillItemViewHolder.this.selectItem();
                    PillItemViewHolder.this.methodsDispatcher.dispatch(PillItemViewHolder.this.ownerId, element.getOnItemSelected());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectItem() {
        this.text.setSelected(true);
        this.text.setTextColor(Podcast.getRuntimeStyleSheet().getAccentColor());
        Boolean bool = Boolean.TRUE;
        this.isSelected = bool;
        this.listener.onStateUpdate(this.element, bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deselectItem() {
        this.text.setSelected(false);
        this.text.setTextColor(this.resources.getColor(R$color.primary));
        Boolean bool = Boolean.FALSE;
        this.isSelected = bool;
        this.listener.onStateUpdate(this.element, bool.booleanValue());
    }

    private void restoreState(final boolean isSelectedState) {
        if (this.isSelected != null) {
            return;
        }
        if (isSelectedState) {
            selectItem();
        } else {
            deselectItem();
        }
    }
}
