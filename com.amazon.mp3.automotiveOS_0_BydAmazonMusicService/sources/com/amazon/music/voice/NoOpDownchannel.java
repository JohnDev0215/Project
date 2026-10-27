package com.amazon.music.voice;

import com.amazon.alexa.voice.core.processor.superbowl.Downchannel;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public class NoOpDownchannel implements Downchannel {
    @Override // com.amazon.alexa.voice.core.processor.superbowl.Downchannel
    public void activate() throws IOException {
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.Downchannel
    public void addListener(Downchannel.Listener listener) {
    }
}
