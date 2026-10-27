package e4;

import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public interface d {
    ImageHeaderParser$ImageType a(ByteBuffer byteBuffer);

    int b(ByteBuffer byteBuffer, F.l lVar);

    int c(InputStream inputStream, F.l lVar);

    ImageHeaderParser$ImageType d(InputStream inputStream);
}
