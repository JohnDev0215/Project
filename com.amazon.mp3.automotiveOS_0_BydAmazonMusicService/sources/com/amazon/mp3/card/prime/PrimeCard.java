package com.amazon.mp3.card.prime;

import android.content.Context;
import android.util.AttributeSet;
import com.amazon.mp3.card.Card;
import com.amazon.mp3.card.CardConfiguration;

/* JADX INFO: loaded from: classes3.dex */
public class PrimeCard<T> extends Card<T, PrimeCardAdapter<T>> {
    public PrimeCard(Context context, CardConfiguration configuration) {
        super(context, configuration);
    }

    public PrimeCard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
