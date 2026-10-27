package com.android.car.ui.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import com.android.car.ui.R$string;
import spi.b2;

/* JADX INFO: loaded from: classes2.dex */
public final class ScrollingLimitedViewHolder extends RecyclerView.ViewHolder {
    public final TextView a;

    public ScrollingLimitedViewHolder(@NonNull View view) {
        super(view);
        this.a = (TextView) b2.n(view, R$id.car_ui_list_limiting_message);
    }

    public static ScrollingLimitedViewHolder b(@NonNull ViewGroup viewGroup) {
        return new ScrollingLimitedViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.car_ui_list_limiting_message, viewGroup, false));
    }

    public final void a(@Nullable @StringRes Integer num) {
        int iIntValue = num != null ? num.intValue() : R$string.car_ui_scrolling_limited_message;
        TextView textView = this.a;
        textView.setText(textView.getContext().getString(iIntValue));
    }
}
