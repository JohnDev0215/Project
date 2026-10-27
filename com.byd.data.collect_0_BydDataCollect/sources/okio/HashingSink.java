package okio;

import java.security.MessageDigest;
import javax.annotation.Nullable;
import javax.crypto.Mac;

/* JADX INFO: loaded from: classes.dex */
public final class HashingSink extends ForwardingSink {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    private final MessageDigest f5800c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Nullable
    private final Mac f5801d;

    @Override // okio.ForwardingSink, okio.Sink
    public void h(Buffer buffer, long j) {
        Util.b(buffer.f5771d, 0L, j);
        Segment segment = buffer.f5770c;
        long j2 = 0;
        while (j2 < j) {
            int iMin = (int) Math.min(j - j2, segment.f5844c - segment.f5843b);
            MessageDigest messageDigest = this.f5800c;
            if (messageDigest != null) {
                messageDigest.update(segment.f5842a, segment.f5843b, iMin);
            } else {
                this.f5801d.update(segment.f5842a, segment.f5843b, iMin);
            }
            j2 += (long) iMin;
            segment = segment.f5847f;
        }
        super.h(buffer, j);
    }
}
