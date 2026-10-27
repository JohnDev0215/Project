package com.amazon.music.uicasting;

import com.amazon.music.model.Block;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class UiDeviceListResult {
    private final List<Block> blocks;

    UiDeviceListResult(List<Block> list) {
        this.blocks = list;
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }
}
