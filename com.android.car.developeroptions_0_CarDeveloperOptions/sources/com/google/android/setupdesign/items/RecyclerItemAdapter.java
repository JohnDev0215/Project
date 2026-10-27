package com.google.android.setupdesign.items;

import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupdesign.R$styleable;
import com.google.android.setupdesign.items.ItemHierarchy;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerItemAdapter extends RecyclerView.Adapter<ItemViewHolder> implements ItemHierarchy.Observer {
    public final boolean applyPartnerHeavyThemeResource;
    private final ItemHierarchy itemHierarchy;
    private OnItemSelectedListener listener;

    public interface OnItemSelectedListener {
        void onItemSelected(IItem iItem);
    }

    public RecyclerItemAdapter(ItemHierarchy itemHierarchy, boolean z) {
        this.applyPartnerHeavyThemeResource = z;
        this.itemHierarchy = itemHierarchy;
        itemHierarchy.registerObserver(this);
    }

    public IItem getItem(int i) {
        return this.itemHierarchy.getItemAt(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        int id;
        IItem item = getItem(i);
        if (!(item instanceof AbstractItem) || (id = ((AbstractItem) item).getId()) <= 0) {
            return -1L;
        }
        return id;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemHierarchy.getCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Drawable background;
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        final ItemViewHolder itemViewHolder = new ItemViewHolder(viewInflate);
        if (!"noBackground".equals(viewInflate.getTag())) {
            TypedArray typedArrayObtainStyledAttributes = viewGroup.getContext().obtainStyledAttributes(R$styleable.SudRecyclerItemAdapter);
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.SudRecyclerItemAdapter_android_selectableItemBackground);
            if (drawable == null) {
                drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.SudRecyclerItemAdapter_selectableItemBackground);
                background = null;
            } else {
                background = viewInflate.getBackground();
                if (background == null) {
                    if (this.applyPartnerHeavyThemeResource) {
                        background = new ColorDrawable(PartnerConfigHelper.get(viewInflate.getContext()).getColor(viewInflate.getContext(), PartnerConfig.CONFIG_LAYOUT_BACKGROUND_COLOR));
                    } else {
                        background = typedArrayObtainStyledAttributes.getDrawable(R$styleable.SudRecyclerItemAdapter_android_colorBackground);
                    }
                }
            }
            if (drawable == null || background == null) {
                Log.e("RecyclerItemAdapter", "Cannot resolve required attributes. selectableItemBackground=" + drawable + " background=" + background);
            } else {
                viewInflate.setBackgroundDrawable(new PatchedLayerDrawable(new Drawable[]{background, drawable}));
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        viewInflate.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.setupdesign.items.RecyclerItemAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IItem item = itemViewHolder.getItem();
                if (RecyclerItemAdapter.this.listener == null || item == null || !item.isEnabled()) {
                    return;
                }
                RecyclerItemAdapter.this.listener.onItemSelected(item);
            }
        });
        return itemViewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        IItem item = getItem(i);
        itemViewHolder.setEnabled(item.isEnabled());
        itemViewHolder.setItem(item);
        item.onBindView(itemViewHolder.itemView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return getItem(i).getLayoutResource();
    }

    @Override // com.google.android.setupdesign.items.ItemHierarchy.Observer
    public void onItemRangeChanged(ItemHierarchy itemHierarchy, int i, int i2) {
        notifyItemRangeChanged(i, i2);
    }

    @Override // com.google.android.setupdesign.items.ItemHierarchy.Observer
    public void onItemRangeInserted(ItemHierarchy itemHierarchy, int i, int i2) {
        notifyItemRangeInserted(i, i2);
    }

    static class PatchedLayerDrawable extends LayerDrawable {
        PatchedLayerDrawable(Drawable[] drawableArr) {
            super(drawableArr);
        }

        @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
        public boolean getPadding(Rect rect) {
            return super.getPadding(rect) && !(rect.left == 0 && rect.top == 0 && rect.right == 0 && rect.bottom == 0);
        }
    }
}
