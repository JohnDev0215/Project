package com.amazon.avod.playback.smoothstream;

import com.amazon.avod.media.framework.error.MediaException;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface ManifestParser {
    Manifest parse(byte[] bArr, int i, int i2) throws MediaException;
}
