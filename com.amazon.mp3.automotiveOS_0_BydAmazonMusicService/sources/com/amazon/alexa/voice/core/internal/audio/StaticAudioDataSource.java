package com.amazon.alexa.voice.core.internal.audio;

import android.net.Uri;
import com.amazon.alexa.voice.core.AudioSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class StaticAudioDataSource implements DataSource {
    private AudioSource audioSource;
    private Uri uri;

    public StaticAudioDataSource(AudioSource audioSource) {
        this.audioSource = audioSource;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public synchronized long open(DataSpec dataSpec) throws IOException {
        this.uri = dataSpec.uri;
        return -1L;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.audioSource.read(bArr, i, i2);
        if (i3 == -1) {
            return -1;
        }
        return i3;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        return this.uri;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() throws IOException {
        this.uri = null;
        this.audioSource.close();
    }

    public static final class Factory implements DataSource.Factory {
        private final AudioSource audioSource;

        public Factory(AudioSource audioSource) {
            this.audioSource = new BufferedAudioSource(audioSource);
        }

        @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
        public DataSource createDataSource() {
            return new StaticAudioDataSource(this.audioSource);
        }
    }
}
