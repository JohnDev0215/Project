package com.amazon.avod.playback.renderer.tate;

import com.amazon.avod.media.framework.libraries.LoadableNativeLibraryBase;
import com.google.common.collect.Lists;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class OMXILNativeLibrary extends LoadableNativeLibraryBase {
    public static final String NATIVE_RENDERER_LIBRARY = "AIVSecureRenderer";

    @Override // com.amazon.avod.media.framework.libraries.LoadableNativeLibrary
    public List<String> getLibraryNames() {
        return Lists.newArrayList(NATIVE_RENDERER_LIBRARY);
    }
}
