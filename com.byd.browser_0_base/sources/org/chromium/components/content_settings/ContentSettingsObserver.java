package org.chromium.components.content_settings;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ContentSettingsObserver {
    public abstract void a();

    @CalledByNative
    public final void onContentSettingChanged(String str, String str2, int i) {
        a();
    }
}
