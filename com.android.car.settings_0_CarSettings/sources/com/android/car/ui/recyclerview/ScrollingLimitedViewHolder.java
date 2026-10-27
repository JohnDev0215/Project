package com.android.car.ui.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R;
import com.android.car.ui.utils.CarUiUtils;

/* JADX INFO: loaded from: classes6.dex */
public final class ScrollingLimitedViewHolder extends RecyclerView.ViewHolder {
    private final TextView mMessage;

    public static ScrollingLimitedViewHolder create(ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_ui_list_limiting_message, parent, false);
        return new ScrollingLimitedViewHolder(rootView);
    }

    ScrollingLimitedViewHolder(View itemView) {
        super(itemView);
        this.mMessage = (TextView) CarUiUtils.requireViewByRefId(itemView, R.id.car_ui_list_limiting_message);
    }

    public void bind(Integer messageId) {
        int resId = messageId != null ? messageId.intValue() : R.string.car_ui_scrolling_limited_message;
        TextView textView = this.mMessage;
        textView.setText(textView.getContext().getString(resId));
    }
}
