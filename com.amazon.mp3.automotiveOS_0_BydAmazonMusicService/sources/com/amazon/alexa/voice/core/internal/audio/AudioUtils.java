package com.amazon.alexa.voice.core.internal.audio;

import com.amazon.alexa.voice.core.AudioSink;
import com.amazon.alexa.voice.core.AudioSource;
import com.amazon.alexa.voice.core.Cancellable;
import com.amazon.alexa.voice.core.Logger;
import com.amazon.alexa.voice.core.audio.AudioOutputStream;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioUtils {
    public static int getPCMBitRate(int i, int i2, int i3) {
        return i * i2 * i3;
    }

    private AudioUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static void transfer(AudioSource audioSource, OutputStream outputStream, Cancellable cancellable) throws IOException {
        Preconditions.notNull(audioSource, "source == null");
        Preconditions.notNull(outputStream, "output == null");
        byte[] bArr = new byte[8192];
        Logger.debug("Audio transferring begin");
        while (true) {
            int i = audioSource.read(bArr, 0, 8192);
            if (i != -1) {
                if (cancellable != null && cancellable.isCancelled()) {
                    return;
                }
                if (i != 0) {
                    outputStream.write(bArr, 0, i);
                }
            } else {
                Logger.debug("Audio transferring flush");
                outputStream.flush();
                Logger.debug("Audio transferring end");
                return;
            }
        }
    }

    public static void transfer(AudioSource audioSource, AudioSink audioSink, Cancellable cancellable) throws IOException {
        transfer(audioSource, new AudioOutputStream(audioSink), cancellable);
    }
}
