package com.amazon.music.model;

import com.amazon.music.optional.Optional;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Block {
    public final Optional<String> uuid;

    protected Block(String str) {
        this.uuid = Optional.ofNullable(str);
    }
}
