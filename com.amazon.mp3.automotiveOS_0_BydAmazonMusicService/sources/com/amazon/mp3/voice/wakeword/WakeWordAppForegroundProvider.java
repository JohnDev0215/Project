package com.amazon.mp3.voice.wakeword;

import com.amazon.mp3.AmazonApplication;
import com.amazon.music.wakeword.ApplicationForegroundProvider;

/* JADX INFO: loaded from: classes4.dex */
public class WakeWordAppForegroundProvider implements ApplicationForegroundProvider {
    @Override // com.amazon.music.wakeword.ApplicationForegroundProvider
    public boolean isApplicationInForeground() {
        return AmazonApplication.isApplicationForeground();
    }
}
