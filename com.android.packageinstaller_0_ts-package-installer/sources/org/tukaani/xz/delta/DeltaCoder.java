package org.tukaani.xz.delta;

/* JADX INFO: loaded from: classes2.dex */
abstract class DeltaCoder {
    static final int DISTANCE_MASK = 255;
    static final int DISTANCE_MAX = 256;
    static final int DISTANCE_MIN = 1;
    final int distance;
    final byte[] history = new byte[256];
    int pos = 0;

    DeltaCoder(int distance) {
        if (distance < 1 || distance > 256) {
            throw new IllegalArgumentException();
        }
        this.distance = distance;
    }
}
