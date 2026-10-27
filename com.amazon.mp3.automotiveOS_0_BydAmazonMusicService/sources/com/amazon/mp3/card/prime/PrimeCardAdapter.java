package com.amazon.mp3.card.prime;

import androidx.annotation.Nullable;
import com.amazon.mp3.card.CardAdapter;
import com.amazon.mp3.library.provider.StateProvider;

/* JADX INFO: loaded from: classes3.dex */
public interface PrimeCardAdapter<T> extends CardAdapter<T> {
    @Override // com.amazon.mp3.card.CardAdapter
    /* synthetic */ int getOverflowMenuButtonResId();

    StateProvider<T> getStateProvider();

    void updateItemStatus(T item);

    void updateItemStatus(T item, @Nullable StateProvider.Listener<T> listener);
}
