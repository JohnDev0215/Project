package com.amazon.avod.playbackclient.displaymode;

import android.content.Context;
import com.amazon.avod.playback.framework.R$bool;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ScreenSizeUtils {
    public static boolean isCompactDevice(@Nonnull Context context) {
        Preconditions.checkNotNull(context, "Context");
        return context.getResources().getBoolean(R$bool.is_compact);
    }
}
