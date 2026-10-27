package com.amazon.mp3.card;

import android.view.View;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface CardAdapter<T> {
    void bindView(int position, T item, View itemView);

    View createView();

    List<T> getData();

    int getOverflowMenuButtonResId();

    void onAttachedToWindow();

    void onDetachedFromWindow();
}
