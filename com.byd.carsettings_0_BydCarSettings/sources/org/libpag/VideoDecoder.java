package org.libpag;

/* JADX INFO: loaded from: classes3.dex */
public abstract class VideoDecoder {
    static {
        org.extra.tools.a.a("pag");
    }

    public static native void RegisterSoftwareDecoderFactory(long j);

    public static native void SetMaxHardwareDecoderCount(int i);
}
