package com.google.android.exoplayer2;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.google.android.exoplayer2.audio.AudioCapabilities;
import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.metadata.MetadataRenderer;
import com.google.android.exoplayer2.text.TextRenderer;
import com.google.android.exoplayer2.video.MediaCodecVideoRenderer;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes6.dex */
public class DefaultRenderersFactory implements RenderersFactory {
    private final long allowedVideoJoiningTimeMs;
    private final Context context;
    private final DrmSessionManager<FrameworkMediaCrypto> drmSessionManager;
    private final int extensionRendererMode;

    protected AudioProcessor[] buildAudioProcessors() {
        return new AudioProcessor[0];
    }

    protected void buildMiscellaneousRenderers(Context context, Handler handler, int i, ArrayList<Renderer> arrayList) {
    }

    public DefaultRenderersFactory(Context context) {
        this(context, null);
    }

    public DefaultRenderersFactory(Context context, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager) {
        this(context, drmSessionManager, 0);
    }

    public DefaultRenderersFactory(Context context, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, int i) {
        this(context, drmSessionManager, i, CoroutineLiveDataKt.DEFAULT_TIMEOUT);
    }

    public DefaultRenderersFactory(Context context, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, int i, long j) {
        this.context = context;
        this.drmSessionManager = drmSessionManager;
        this.extensionRendererMode = i;
        this.allowedVideoJoiningTimeMs = j;
    }

    @Override // com.google.android.exoplayer2.RenderersFactory
    public Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextRenderer.Output output, MetadataRenderer.Output output2) {
        ArrayList<Renderer> arrayList = new ArrayList<>();
        buildVideoRenderers(this.context, this.drmSessionManager, this.allowedVideoJoiningTimeMs, handler, videoRendererEventListener, this.extensionRendererMode, arrayList);
        buildAudioRenderers(this.context, this.drmSessionManager, buildAudioProcessors(), handler, audioRendererEventListener, this.extensionRendererMode, arrayList);
        buildTextRenderers(this.context, output, handler.getLooper(), this.extensionRendererMode, arrayList);
        buildMetadataRenderers(this.context, output2, handler.getLooper(), this.extensionRendererMode, arrayList);
        buildMiscellaneousRenderers(this.context, handler, this.extensionRendererMode, arrayList);
        return (Renderer[]) arrayList.toArray(new Renderer[arrayList.size()]);
    }

    protected void buildVideoRenderers(Context context, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, long j, Handler handler, VideoRendererEventListener videoRendererEventListener, int i, ArrayList<Renderer> arrayList) {
        arrayList.add(new MediaCodecVideoRenderer(context, MediaCodecSelector.DEFAULT, j, drmSessionManager, false, handler, videoRendererEventListener, 50));
        if (i == 0) {
            return;
        }
        int size = arrayList.size();
        if (i == 2) {
            size--;
        }
        try {
            arrayList.add(size, (Renderer) Class.forName("com.google.android.exoplayer2.ext.vp9.LibvpxVideoRenderer").getConstructor(Boolean.TYPE, Long.TYPE, Handler.class, VideoRendererEventListener.class, Integer.TYPE).newInstance(Boolean.TRUE, Long.valueOf(j), handler, videoRendererEventListener, 50));
            Log.i("DefaultRenderersFactory", "Loaded LibvpxVideoRenderer.");
        } catch (ClassNotFoundException unused) {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void buildAudioRenderers(Context context, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, AudioProcessor[] audioProcessorArr, Handler handler, AudioRendererEventListener audioRendererEventListener, int i, ArrayList<Renderer> arrayList) {
        int i2;
        int i3;
        arrayList.add(new MediaCodecAudioRenderer(MediaCodecSelector.DEFAULT, drmSessionManager, true, handler, audioRendererEventListener, AudioCapabilities.getCapabilities(context), audioProcessorArr));
        if (i == 0) {
            return;
        }
        int size = arrayList.size();
        if (i == 2) {
            size--;
        }
        try {
            try {
                i2 = size + 1;
                try {
                    arrayList.add(size, (Renderer) Class.forName("com.google.android.exoplayer2.ext.opus.LibopusAudioRenderer").getConstructor(Handler.class, AudioRendererEventListener.class, AudioProcessor[].class).newInstance(handler, audioRendererEventListener, audioProcessorArr));
                    Log.i("DefaultRenderersFactory", "Loaded LibopusAudioRenderer.");
                } catch (ClassNotFoundException unused) {
                    size = i2;
                    i2 = size;
                }
            } catch (ClassNotFoundException unused2) {
            }
            try {
                try {
                    i3 = i2 + 1;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } catch (ClassNotFoundException unused3) {
            }
            try {
                arrayList.add(i2, (Renderer) Class.forName("com.google.android.exoplayer2.ext.flac.LibflacAudioRenderer").getConstructor(Handler.class, AudioRendererEventListener.class, AudioProcessor[].class).newInstance(handler, audioRendererEventListener, audioProcessorArr));
                Log.i("DefaultRenderersFactory", "Loaded LibflacAudioRenderer.");
            } catch (ClassNotFoundException unused4) {
                i2 = i3;
                i3 = i2;
            }
            try {
                arrayList.add(i3, (Renderer) Class.forName("com.google.android.exoplayer2.ext.ffmpeg.FfmpegAudioRenderer").getConstructor(Handler.class, AudioRendererEventListener.class, AudioProcessor[].class).newInstance(handler, audioRendererEventListener, audioProcessorArr));
                Log.i("DefaultRenderersFactory", "Loaded FfmpegAudioRenderer.");
            } catch (ClassNotFoundException unused5) {
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        } catch (Exception e3) {
            throw new RuntimeException(e3);
        }
    }

    protected void buildTextRenderers(Context context, TextRenderer.Output output, Looper looper, int i, ArrayList<Renderer> arrayList) {
        arrayList.add(new TextRenderer(output, looper));
    }

    protected void buildMetadataRenderers(Context context, MetadataRenderer.Output output, Looper looper, int i, ArrayList<Renderer> arrayList) {
        arrayList.add(new MetadataRenderer(output, looper));
    }
}
