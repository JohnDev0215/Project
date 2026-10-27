package org.chromium.chrome.browser.privacy_sandbox;

import android.content.Context;
import org.chromium.components.browser_ui.settings.ImageButtonPreference;

/* JADX INFO: loaded from: classes2.dex */
public class TopicPreference extends ImageButtonPreference {
    public final Topic a0;

    public TopicPreference(Context context, Topic topic) {
        super(context);
        this.a0 = topic;
        T(topic.c);
    }
}
