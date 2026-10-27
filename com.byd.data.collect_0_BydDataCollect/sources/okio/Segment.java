package okio;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
final class Segment {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final byte[] f5842a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f5843b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f5844c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f5845d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f5846e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Segment f5847f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    Segment f5848g;

    Segment() {
        this.f5842a = new byte[8192];
        this.f5846e = true;
        this.f5845d = false;
    }

    public final void a() {
        Segment segment = this.f5848g;
        if (segment == this) {
            throw new IllegalStateException();
        }
        if (segment.f5846e) {
            int i2 = this.f5844c - this.f5843b;
            if (i2 > (8192 - segment.f5844c) + (segment.f5845d ? 0 : segment.f5843b)) {
                return;
            }
            f(segment, i2);
            b();
            SegmentPool.a(this);
        }
    }

    @Nullable
    public final Segment b() {
        Segment segment = this.f5847f;
        Segment segment2 = segment != this ? segment : null;
        Segment segment3 = this.f5848g;
        segment3.f5847f = segment;
        this.f5847f.f5848g = segment3;
        this.f5847f = null;
        this.f5848g = null;
        return segment2;
    }

    public final Segment c(Segment segment) {
        segment.f5848g = this;
        segment.f5847f = this.f5847f;
        this.f5847f.f5848g = segment;
        this.f5847f = segment;
        return segment;
    }

    final Segment d() {
        this.f5845d = true;
        return new Segment(this.f5842a, this.f5843b, this.f5844c, true, false);
    }

    public final Segment e(int i2) {
        Segment segmentB;
        if (i2 <= 0 || i2 > this.f5844c - this.f5843b) {
            throw new IllegalArgumentException();
        }
        if (i2 >= 1024) {
            segmentB = d();
        } else {
            segmentB = SegmentPool.b();
            System.arraycopy(this.f5842a, this.f5843b, segmentB.f5842a, 0, i2);
        }
        segmentB.f5844c = segmentB.f5843b + i2;
        this.f5843b += i2;
        this.f5848g.c(segmentB);
        return segmentB;
    }

    public final void f(Segment segment, int i2) {
        if (!segment.f5846e) {
            throw new IllegalArgumentException();
        }
        int i3 = segment.f5844c;
        if (i3 + i2 > 8192) {
            if (segment.f5845d) {
                throw new IllegalArgumentException();
            }
            int i4 = segment.f5843b;
            if ((i3 + i2) - i4 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = segment.f5842a;
            System.arraycopy(bArr, i4, bArr, 0, i3 - i4);
            segment.f5844c -= segment.f5843b;
            segment.f5843b = 0;
        }
        System.arraycopy(this.f5842a, this.f5843b, segment.f5842a, segment.f5844c, i2);
        segment.f5844c += i2;
        this.f5843b += i2;
    }

    Segment(byte[] bArr, int i2, int i3, boolean z, boolean z2) {
        this.f5842a = bArr;
        this.f5843b = i2;
        this.f5844c = i3;
        this.f5845d = z;
        this.f5846e = z2;
    }
}
