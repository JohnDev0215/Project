package com.amazon.podcast.views.horizontalRowItemsListView;

import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.ButtonElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElement;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$layout;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class HorizontalRowTemplateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ButtonElement button;
    private Context context;
    private HorizontalRowItemView horizontalRowItemView;
    private Integer itemCount;
    private boolean itemIndexingEnabled;
    private List<HorizontalItemElement> items;
    private LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;

    private enum ViewType {
        button(0),
        rowItem(1);

        private final int value;

        ViewType(final int value) {
            this.value = value;
        }
    }

    public HorizontalRowTemplateAdapter(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner) {
        this.context = context;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int viewType) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
        if (ViewType.button.value == viewType) {
            return new ButtonViewHolder(layoutInflaterFrom.inflate(R$layout.podcast_button_item, viewGroup, false), this.ownerId, this.methodsDispatcher);
        }
        this.horizontalRowItemView = new HorizontalRowItemView(this.context, this.ownerId, this.methodsDispatcher, this.lifecycleOwner, false);
        this.horizontalRowItemView.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        return new HorizontalItemViewHolder(this.horizontalRowItemView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int position) {
        ButtonElement buttonElement = this.button;
        if (buttonElement != null && position == 0) {
            ((ButtonViewHolder) viewHolder).bind(buttonElement);
            return;
        }
        if (buttonElement != null) {
            position--;
        }
        HorizontalItemElement horizontalItemElement = this.items.get(position);
        if (this.itemIndexingEnabled) {
            ((HorizontalItemViewHolder) viewHolder).bind(position + 1, horizontalItemElement);
        } else {
            ((HorizontalItemViewHolder) viewHolder).bind(-1, horizontalItemElement);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemCount.intValue();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(final int position) {
        return this.items.get(position) instanceof HorizontalItemElement ? ViewType.rowItem.value : ViewType.button.value;
    }

    public void addItems(final List<HorizontalItemElement> items) {
        if (items.isEmpty()) {
            return;
        }
        this.items.addAll(items);
        this.itemCount = Integer.valueOf(this.itemCount.intValue() + items.size());
        notifyDataSetChanged();
    }

    public void bind(final boolean itemIndexingEnabled, final ButtonElement button, final List<HorizontalItemElement> items) {
        this.itemIndexingEnabled = itemIndexingEnabled;
        this.items = new ArrayList(items);
        this.button = button;
        if (button != null) {
            this.itemCount = Integer.valueOf(this.itemCount.intValue() + 1);
        }
        this.itemCount = Integer.valueOf(this.items.size());
        notifyDataSetChanged();
    }
}
