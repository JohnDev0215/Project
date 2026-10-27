package com.android.car.ui.appstyledview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;

/* JADX INFO: loaded from: classes2.dex */
public class AppStyledRecyclerViewAdapter extends RecyclerView.Adapter<AppStyledRecyclerViewHolder> {

    @Nullable
    public final View a;

    public static class AppStyledRecyclerViewHolder extends RecyclerView.ViewHolder {
        public View a;

        public AppStyledRecyclerViewHolder(@NonNull View view) {
            super(view);
            this.a = view;
        }
    }

    public AppStyledRecyclerViewAdapter(@Nullable View view) {
        this.a = view;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.a != null ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((FrameLayout) ((AppStyledRecyclerViewHolder) viewHolder).a.findViewById(R$id.car_ui_app_styled_item)).addView(this.a);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AppStyledRecyclerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.car_ui_app_styled_view_item, viewGroup, false));
    }
}
