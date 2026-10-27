package com.amazon.mls.config.internal.sushi.writer;

import com.amazon.mls.config.internal.sushi.SushiFile;

/* JADX INFO: loaded from: classes3.dex */
public class DummyFileWriter implements FileWriter {
    @Override // com.amazon.mls.config.internal.sushi.writer.FileWriter
    public boolean fileIsEmpty() {
        return true;
    }

    @Override // com.amazon.mls.config.internal.sushi.writer.FileWriter
    public SushiFile rotate() {
        return null;
    }
}
