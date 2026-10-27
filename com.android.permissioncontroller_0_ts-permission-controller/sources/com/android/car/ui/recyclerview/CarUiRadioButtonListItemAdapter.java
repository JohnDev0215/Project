package com.android.car.ui.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R;
import com.android.car.ui.recyclerview.CarUiListItemAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
public class CarUiRadioButtonListItemAdapter extends CarUiListItemAdapter {
    private int mSelectedIndex;

    public CarUiRadioButtonListItemAdapter(List<CarUiRadioButtonListItem> items) {
        super(items);
        this.mSelectedIndex = -1;
        for (int i = 0; i < items.size(); i++) {
            CarUiRadioButtonListItem item = items.get(i);
            if (item.isChecked() && this.mSelectedIndex >= 0) {
                throw new IllegalStateException("At most one item in a CarUiRadioButtonListItemAdapter can be checked");
            }
            if (item.isChecked()) {
                this.mSelectedIndex = i;
            }
        }
    }

    @Override // com.android.car.ui.recyclerview.CarUiListItemAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 1) {
            return new RadioButtonListItemViewHolder(inflater.inflate(R.layout.car_ui_list_item, parent, false));
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override // com.android.car.ui.recyclerview.CarUiListItemAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder.getItemViewType() == 1) {
            if (!(holder instanceof RadioButtonListItemViewHolder)) {
                throw new IllegalStateException("Incorrect view holder type for list item.");
            }
            CarUiListItem item = getItems().get(position);
            if (!(item instanceof CarUiRadioButtonListItem)) {
                throw new IllegalStateException("Expected item to be bound to viewHolder to be instance of CarUiRadioButtonListItem.");
            }
            RadioButtonListItemViewHolder actualHolder = (RadioButtonListItemViewHolder) holder;
            actualHolder.bind((CarUiRadioButtonListItem) item);
            actualHolder.setOnCheckedChangeListener(new RadioButtonListItemViewHolder.OnCheckedChangeListener() { // from class: com.android.car.ui.recyclerview.-$$Lambda$CarUiRadioButtonListItemAdapter$mvviQERdRkFrTDqGYXRdYLzEYfU
                @Override // com.android.car.ui.recyclerview.CarUiRadioButtonListItemAdapter.RadioButtonListItemViewHolder.OnCheckedChangeListener
                public final void onCheckedChanged(boolean z) {
                    this.f$0.lambda$onBindViewHolder$0$CarUiRadioButtonListItemAdapter(position, z);
                }
            });
            return;
        }
        super.onBindViewHolder(holder, position);
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$CarUiRadioButtonListItemAdapter(int position, boolean isChecked) {
        if (isChecked && this.mSelectedIndex >= 0) {
            CarUiRadioButtonListItem previousSelectedItem = (CarUiRadioButtonListItem) getItems().get(this.mSelectedIndex);
            previousSelectedItem.setChecked(false);
            notifyItemChanged(this.mSelectedIndex);
        }
        if (isChecked) {
            this.mSelectedIndex = position;
            CarUiRadioButtonListItem currentSelectedItem = (CarUiRadioButtonListItem) getItems().get(this.mSelectedIndex);
            currentSelectedItem.setChecked(true);
            notifyItemChanged(this.mSelectedIndex);
        }
    }

    public int getSelectedItemPosition() {
        return this.mSelectedIndex;
    }

    static class RadioButtonListItemViewHolder extends CarUiListItemAdapter.ListItemViewHolder {
        private OnCheckedChangeListener mListener;

        public interface OnCheckedChangeListener {
            void onCheckedChanged(boolean z);
        }

        RadioButtonListItemViewHolder(View itemView) {
            super(itemView);
        }

        void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
            this.mListener = listener;
        }

        @Override // com.android.car.ui.recyclerview.CarUiListItemAdapter.ListItemViewHolder
        void bind(final CarUiContentListItem item) {
            super.bind(item);
            this.mRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.android.car.ui.recyclerview.-$$Lambda$CarUiRadioButtonListItemAdapter$RadioButtonListItemViewHolder$vf4rZnq1exIlQuEk85FaP9k-ad0
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    this.f$0.lambda$bind$0$CarUiRadioButtonListItemAdapter$RadioButtonListItemViewHolder(item, compoundButton, z);
                }
            });
        }

        public /* synthetic */ void lambda$bind$0$CarUiRadioButtonListItemAdapter$RadioButtonListItemViewHolder(CarUiContentListItem item, CompoundButton buttonView, boolean isChecked) {
            item.setChecked(isChecked);
            OnCheckedChangeListener onCheckedChangeListener = this.mListener;
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onCheckedChanged(isChecked);
            }
        }
    }
}
