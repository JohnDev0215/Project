package com.amazon.mls.config.internal.sushi.storage;

import com.amazon.mls.config.internal.sushi.SushiFile;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface FileRing {
    void addFile(SushiFile sushiFile);

    void delete(SushiFile sushiFile);

    List<SushiFile> getFiles();
}
