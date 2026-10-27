package b2;

import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;
import n1.k;
import q1.v;

/* JADX INFO: compiled from: StreamGifDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class j implements k<InputStream, c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<ImageHeaderParser> f3524a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final k<ByteBuffer, c> f3525b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final r1.b f3526c;

    public j(List<ImageHeaderParser> list, k<ByteBuffer, c> kVar, r1.b bVar) {
        this.f3524a = list;
        this.f3525b = kVar;
        this.f3526c = bVar;
    }

    private static byte[] e(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16384);
        try {
            byte[] bArr = new byte[16384];
            while (true) {
                int i7 = inputStream.read(bArr);
                if (i7 == -1) {
                    byteArrayOutputStream.flush();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, i7);
            }
        } catch (IOException e7) {
            if (!Log.isLoggable("StreamGifDecoder", 5)) {
                return null;
            }
            Log.w("StreamGifDecoder", "Error reading data from stream", e7);
            return null;
        }
    }

    @Override // n1.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public v<c> b(InputStream inputStream, int i7, int i8, n1.i iVar) {
        byte[] bArrE = e(inputStream);
        if (bArrE == null) {
            return null;
        }
        return this.f3525b.b(ByteBuffer.wrap(bArrE), i7, i8, iVar);
    }

    @Override // n1.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(InputStream inputStream, n1.i iVar) {
        return !((Boolean) iVar.c(i.f3523b)).booleanValue() && com.bumptech.glide.load.a.b(this.f3524a, inputStream, this.f3526c) == ImageHeaderParser.ImageType.GIF;
    }
}
