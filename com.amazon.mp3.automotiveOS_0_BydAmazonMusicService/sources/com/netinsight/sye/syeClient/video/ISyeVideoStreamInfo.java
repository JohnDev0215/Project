package com.netinsight.sye.syeClient.video;

import a.b.a.a.o.b;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0016\u0010\u0005\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0007\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004R\u0016\u0010\t\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004R\u0016\u0010\u000b\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0004R\u0016\u0010\r\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0004R\u0016\u0010\u0011\u001a\u00020\u000e8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lcom/netinsight/sye/syeClient/video/ISyeVideoStreamInfo;", "", "", "getWidth", "()I", "width", "getHeight", "height", "getBitrate", "bitrate", "getFramerateDen", "framerateDen", "getFramerateNum", "framerateNum", "La/b/a/a/o/b;", "getCodec", "()La/b/a/a/o/b;", "codec", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface ISyeVideoStreamInfo {
    int getBitrate();

    b getCodec();

    int getFramerateDen();

    int getFramerateNum();

    int getHeight();

    int getWidth();
}
