package com.amazon.music.castingviews.button;

import com.amazon.music.model.Block;
import com.amazon.music.optional.Optional;

/* JADX INFO: loaded from: classes4.dex */
public class ButtonTileModel extends Block {
    public final Optional<Integer> textResId;

    public ButtonTileModel(int i) {
        super("uuid");
        this.textResId = Optional.of(Integer.valueOf(i));
    }
}
