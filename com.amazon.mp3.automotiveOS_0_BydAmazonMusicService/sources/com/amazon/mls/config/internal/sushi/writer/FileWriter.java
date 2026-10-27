package com.amazon.mls.config.internal.sushi.writer;

import com.amazon.mls.config.internal.sushi.SushiFile;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public interface FileWriter {
    boolean fileIsEmpty();

    SushiFile rotate() throws IOException;
}
