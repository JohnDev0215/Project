package com.amazon.avod.media.framework.libraries;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface LoadableNativeLibrary {
    List<String> getLibraryNames();

    void initialize();
}
