package org.tukaani.xz;

import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class DeltaOptions extends FilterOptions {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DISTANCE_MAX = 256;
    public static final int DISTANCE_MIN = 1;
    private int distance = 1;

    public DeltaOptions() {
    }

    public DeltaOptions(int distance) throws UnsupportedOptionsException {
        setDistance(distance);
    }

    public void setDistance(int distance) throws UnsupportedOptionsException {
        if (distance < 1 || distance > 256) {
            throw new UnsupportedOptionsException("Delta distance must be in the range [1, 256]: " + distance);
        }
        this.distance = distance;
    }

    public int getDistance() {
        return this.distance;
    }

    @Override // org.tukaani.xz.FilterOptions
    public int getEncoderMemoryUsage() {
        return DeltaOutputStream.getMemoryUsage();
    }

    @Override // org.tukaani.xz.FilterOptions
    public FinishableOutputStream getOutputStream(FinishableOutputStream out, ArrayCache arrayCache) {
        return new DeltaOutputStream(out, this);
    }

    @Override // org.tukaani.xz.FilterOptions
    public int getDecoderMemoryUsage() {
        return 1;
    }

    @Override // org.tukaani.xz.FilterOptions
    public InputStream getInputStream(InputStream in, ArrayCache arrayCache) {
        return new DeltaInputStream(in, this.distance);
    }

    @Override // org.tukaani.xz.FilterOptions
    FilterEncoder getFilterEncoder() {
        return new DeltaEncoder(this);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
    }
}
