package com.bumptech.glide.load.p.h;

import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.k;
import com.bumptech.glide.load.n.v;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class j implements k<InputStream, c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<ImageHeaderParser> f2406a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final k<ByteBuffer, c> f2407b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.n.a0.b f2408c;

    public j(List<ImageHeaderParser> list, k<ByteBuffer, c> kVar, com.bumptech.glide.load.n.a0.b bVar) {
        this.f2406a = list;
        this.f2407b = kVar;
        this.f2408c = bVar;
    }

    private static byte[] e(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16384);
        try {
            byte[] bArr = new byte[16384];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    byteArrayOutputStream.flush();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
        } catch (IOException e) {
            if (!Log.isLoggable("StreamGifDecoder", 5)) {
                return null;
            }
            Log.w("StreamGifDecoder", "Error reading data from stream", e);
            return null;
        }
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public v<c> a(InputStream inputStream, int i, int i2, com.bumptech.glide.load.i iVar) {
        byte[] bArrE = e(inputStream);
        if (bArrE == null) {
            return null;
        }
        return this.f2407b.a(ByteBuffer.wrap(bArrE), i, i2, iVar);
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(InputStream inputStream, com.bumptech.glide.load.i iVar) {
        return !((Boolean) iVar.c(i.f2405b)).booleanValue() && com.bumptech.glide.load.f.e(this.f2406a, inputStream, this.f2408c) == ImageHeaderParser.ImageType.GIF;
    }
}
