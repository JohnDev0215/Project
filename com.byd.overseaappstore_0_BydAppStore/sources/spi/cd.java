package spi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicReference;
import spi.u2;

/* JADX INFO: loaded from: classes.dex */
public final class cd implements in<GifDrawable, byte[]> {
    @Override // spi.in
    @Nullable
    public final wm<byte[]> a(@NonNull wm<GifDrawable> wmVar, @NonNull lk lkVar) {
        byte[] bArrArray;
        ByteBuffer byteBufferAsReadOnlyBuffer = wmVar.get().a.a.a.getData().asReadOnlyBuffer();
        AtomicReference<byte[]> atomicReference = u2.a;
        u2.b bVar = (byteBufferAsReadOnlyBuffer.isReadOnly() || !byteBufferAsReadOnlyBuffer.hasArray()) ? null : new u2.b(byteBufferAsReadOnlyBuffer.array(), byteBufferAsReadOnlyBuffer.arrayOffset(), byteBufferAsReadOnlyBuffer.limit());
        if (bVar != null && bVar.a == 0 && bVar.b == bVar.c.length) {
            bArrArray = byteBufferAsReadOnlyBuffer.array();
        } else {
            ByteBuffer byteBufferAsReadOnlyBuffer2 = byteBufferAsReadOnlyBuffer.asReadOnlyBuffer();
            byte[] bArr = new byte[byteBufferAsReadOnlyBuffer2.limit()];
            byteBufferAsReadOnlyBuffer2.get(bArr);
            bArrArray = bArr;
        }
        return new v2(bArrArray);
    }
}
