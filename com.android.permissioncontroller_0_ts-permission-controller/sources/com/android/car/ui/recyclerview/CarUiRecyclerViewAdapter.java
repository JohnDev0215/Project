package com.android.car.ui.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R;
import com.android.car.ui.utils.CarUiUtils;

/* JADX INFO: loaded from: classes7.dex */
final class CarUiRecyclerViewAdapter extends RecyclerView.Adapter<NestedRowViewHolder> {
    CarUiRecyclerViewAdapter() {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public NestedRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_ui_recycler_view_item, parent, false);
        return new NestedRowViewHolder(v);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(NestedRowViewHolder holder, int position) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 1;
    }

    static class NestedRowViewHolder extends RecyclerView.ViewHolder {
        public final FrameLayout frameLayout;

        NestedRowViewHolder(View view) {
            super(view);
            this.frameLayout = (FrameLayout) CarUiUtils.findViewByRefId(view, R.id.nested_recycler_view_layout);
        }
    }
}
