package n2;

import h2.AbstractC1343A;
import java.util.Locale;

/* JADX INFO: renamed from: n2.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1906f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f19649a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f19650b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f19651e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f19652f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f19653g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f19654h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f19655i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f19656j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f19657k;
    public int l;

    public final String toString() {
        int i7 = this.f19649a;
        int i10 = this.f19650b;
        int i11 = this.c;
        int i12 = this.d;
        int i13 = this.f19651e;
        int i14 = this.f19652f;
        int i15 = this.f19653g;
        int i16 = this.f19654h;
        int i17 = this.f19655i;
        int i18 = this.f19656j;
        long j3 = this.f19657k;
        int i19 = this.l;
        int i20 = AbstractC1343A.f15195a;
        Locale locale = Locale.US;
        StringBuilder sbL = M5.b.l("DecoderCounters {\n decoderInits=", ",\n decoderReleases=", i7, i10, "\n queuedInputBuffers=");
        sbL.append(i11);
        sbL.append("\n skippedInputBuffers=");
        sbL.append(i12);
        sbL.append("\n renderedOutputBuffers=");
        sbL.append(i13);
        sbL.append("\n skippedOutputBuffers=");
        sbL.append(i14);
        sbL.append("\n droppedBuffers=");
        sbL.append(i15);
        sbL.append("\n droppedInputBuffers=");
        sbL.append(i16);
        sbL.append("\n maxConsecutiveDroppedBuffers=");
        sbL.append(i17);
        sbL.append("\n droppedToKeyframeEvents=");
        sbL.append(i18);
        sbL.append("\n totalVideoFrameProcessingOffsetUs=");
        sbL.append(j3);
        sbL.append("\n videoFrameProcessingOffsetCount=");
        sbL.append(i19);
        sbL.append("\n}");
        return sbL.toString();
    }
}
