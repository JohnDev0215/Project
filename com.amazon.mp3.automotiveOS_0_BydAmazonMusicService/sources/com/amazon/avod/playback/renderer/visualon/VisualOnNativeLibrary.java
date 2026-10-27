package com.amazon.avod.playback.renderer.visualon;

import android.content.Context;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.libraries.LoadableNativeLibraryBase;
import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VisualOnNativeLibrary extends LoadableNativeLibraryBase {
    public static final String NATIVE_RENDERER_LIBRARY = "AIVVisualOnRenderer";
    private static final String NATIVE_VO_ENGINE_LIBRARY = "voOSCEngine";
    public static final String PLAY_READY_LIBRARY = "AIVPlayReadyLicensing";
    private final Context mContext;

    public VisualOnNativeLibrary(@Nonnull MediaSystemSharedContext mediaSystemSharedContext) {
        this(mediaSystemSharedContext.getAppContext());
    }

    public VisualOnNativeLibrary(@Nonnull Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.avod.media.framework.libraries.LoadableNativeLibrary
    public List<String> getLibraryNames() {
        return Lists.newArrayList(PLAY_READY_LIBRARY, NATIVE_VO_ENGINE_LIBRARY, NATIVE_RENDERER_LIBRARY);
    }
}
