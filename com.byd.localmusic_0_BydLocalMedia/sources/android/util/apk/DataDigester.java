package android.util.apk;

import java.nio.ByteBuffer;
import java.security.DigestException;

/* JADX INFO: loaded from: classes3.dex */
interface DataDigester {
    void consume(ByteBuffer byteBuffer) throws DigestException;
}
