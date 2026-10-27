package com.amazon.alexa.voice.core.processor.superbowl;

import com.amazon.alexa.voice.core.AudioSource;
import com.amazon.alexa.voice.core.audio.StreamAudioSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class Content {
    private final byte[] data;
    private final String id;
    private final int offset;
    private final int size;

    public Content(String str, byte[] bArr, int i, int i2) {
        this.id = str;
        this.data = bArr;
        this.offset = i;
        this.size = i2;
    }

    public String id() {
        return this.id;
    }

    public byte[] data() {
        return this.data;
    }

    public int size() {
        return this.size;
    }

    public int offset() {
        return this.offset;
    }

    public InputStream inputStream() {
        return new ByteArrayInputStream(this.data, this.offset, this.size);
    }

    public AudioSource audioSource() {
        return new StreamAudioSource(inputStream());
    }
}
