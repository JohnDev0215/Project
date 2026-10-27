package com.amazon.avod.smoothstream.dash;

import com.amazon.avod.media.framework.libraries.LoadableNativeLibraryBase;
import com.google.common.collect.Lists;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DashNativeLibrary extends LoadableNativeLibraryBase {
    @Override // com.amazon.avod.media.framework.libraries.LoadableNativeLibrary
    public List<String> getLibraryNames() {
        return Lists.newArrayList("AIVDash");
    }
}
