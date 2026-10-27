package com.amazon.mls.config.internal.sushi.storage;

import com.amazon.mls.config.internal.sushi.SushiFile;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
class DummyFileRing implements FileRing {
    @Override // com.amazon.mls.config.internal.sushi.storage.FileRing
    public void addFile(SushiFile sushiFile) {
    }

    @Override // com.amazon.mls.config.internal.sushi.storage.FileRing
    public void delete(SushiFile sushiFile) {
    }

    DummyFileRing() {
    }

    @Override // com.amazon.mls.config.internal.sushi.storage.FileRing
    public List<SushiFile> getFiles() {
        return new LinkedList();
    }
}
