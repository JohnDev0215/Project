package com.amazon.avod.playerheuristics;

import com.amazon.avod.media.framework.libraries.LoadableNativeLibraryBase;
import com.google.common.collect.Lists;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerHeuristicsNativeLibrary extends LoadableNativeLibraryBase {
    @Override // com.amazon.avod.media.framework.libraries.LoadableNativeLibrary
    public List<String> getLibraryNames() {
        return Lists.newArrayList("AIVHeuristics");
    }
}
