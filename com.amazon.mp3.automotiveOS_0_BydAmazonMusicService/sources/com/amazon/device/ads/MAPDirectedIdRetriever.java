package com.amazon.device.ads;

/* JADX INFO: loaded from: classes2.dex */
class MAPDirectedIdRetriever implements DirectedIdRetriever {
    private final MAPUtils mapUtils;

    MAPDirectedIdRetriever() {
        this(new MAPUtils());
    }

    MAPDirectedIdRetriever(MAPUtils mAPUtils) {
        this.mapUtils = mAPUtils;
    }
}
