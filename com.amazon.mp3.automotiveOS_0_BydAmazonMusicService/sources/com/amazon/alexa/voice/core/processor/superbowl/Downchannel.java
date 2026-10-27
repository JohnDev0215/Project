package com.amazon.alexa.voice.core.processor.superbowl;

import com.amazon.alexa.voice.core.Directive;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public interface Downchannel {

    public interface Listener {
        void onDirectiveReceived(Directive directive);

        void onDownchannelActivated();

        void onDownchannelDeactivated();
    }

    void activate() throws IOException;

    void addListener(Listener listener);
}
