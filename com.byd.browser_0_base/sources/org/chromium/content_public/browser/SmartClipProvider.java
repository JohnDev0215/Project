package org.chromium.content_public.browser;

import android.os.Handler;
import org.chromium.build.annotations.UsedByReflection;

/* JADX INFO: loaded from: classes3.dex */
@UsedByReflection
public interface SmartClipProvider {
    @UsedByReflection
    void extractSmartClipData(int i, int i2, int i3, int i4);

    @UsedByReflection
    void setSmartClipResultHandler(Handler handler);
}
