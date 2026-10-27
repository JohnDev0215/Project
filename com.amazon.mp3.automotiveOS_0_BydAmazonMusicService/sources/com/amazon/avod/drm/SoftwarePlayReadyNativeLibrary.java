package com.amazon.avod.drm;

import com.amazon.avod.media.framework.libraries.LoadableNativeLibraryBase;
import com.amazon.avod.playback.renderer.visualon.VisualOnNativeLibrary;
import com.google.common.collect.Lists;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SoftwarePlayReadyNativeLibrary extends LoadableNativeLibraryBase {
    @Override // com.amazon.avod.media.framework.libraries.LoadableNativeLibrary
    public List<String> getLibraryNames() {
        return Lists.newArrayList(VisualOnNativeLibrary.PLAY_READY_LIBRARY);
    }
}
