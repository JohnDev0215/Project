package okio;

import java.security.MessageDigest;
import javax.crypto.Mac;

/* JADX INFO: loaded from: classes.dex */
public final class HashingSource extends ForwardingSource {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final MessageDigest f5802c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Mac f5803d;

    @Override // okio.ForwardingSource, okio.Source
    public long p(Buffer buffer, long j) {
        long jP = super.p(buffer, j);
        if (jP != -1) {
            long j2 = buffer.f5771d;
            long j3 = j2 - jP;
            Segment segment = buffer.f5770c;
            while (j2 > j3) {
                segment = segment.f5848g;
                j2 -= (long) (segment.f5844c - segment.f5843b);
            }
            while (j2 < buffer.f5771d) {
                int i2 = (int) ((((long) segment.f5843b) + j3) - j2);
                MessageDigest messageDigest = this.f5802c;
                if (messageDigest != null) {
                    messageDigest.update(segment.f5842a, i2, segment.f5844c - i2);
                } else {
                    this.f5803d.update(segment.f5842a, i2, segment.f5844c - i2);
                }
                j3 = ((long) (segment.f5844c - segment.f5843b)) + j2;
                segment = segment.f5847f;
                j2 = j3;
            }
        }
        return jP;
    }
}
