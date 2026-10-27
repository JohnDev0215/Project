package org.chromium.media;

import java.util.Comparator;
import org.chromium.media.VideoCapture;

/* JADX INFO: compiled from: VideoCapture.java */
/* JADX INFO: loaded from: classes3.dex */
public final class m implements Comparator<VideoCapture.a> {
    public final /* synthetic */ int e;

    public m(int i) {
        this.e = i;
    }

    @Override // java.util.Comparator
    public final int compare(VideoCapture.a aVar, VideoCapture.a aVar2) {
        VideoCapture.a aVar3 = aVar;
        VideoCapture.a aVar4 = aVar2;
        int i = aVar3.a;
        int i2 = i < 8000 ? i * 1 : ((i - 8000) * 4) + 8000;
        int i3 = aVar3.b;
        int i4 = this.e;
        int iAbs = Math.abs(i4 - i3);
        int i5 = (iAbs < 5000 ? iAbs * 1 : ((iAbs - 5000) * 3) + 5000) + i2;
        int i6 = aVar4.a;
        int i7 = i6 < 8000 ? i6 * 1 : ((i6 - 8000) * 4) + 8000;
        int iAbs2 = Math.abs(i4 - aVar4.b);
        return i5 - ((iAbs2 < 5000 ? iAbs2 * 1 : ((iAbs2 - 5000) * 3) + 5000) + i7);
    }
}
