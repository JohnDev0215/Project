package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class DeviceCapability {
    final short maximumBitDepth;
    final int maximumSampleRate;
    final boolean supportsAtmos;
    final boolean supportsMPEGH;
    final boolean supportsUHD;

    public DeviceCapability(boolean z, boolean z2, boolean z3, short s, int i) {
        this.supportsUHD = z;
        this.supportsAtmos = z2;
        this.supportsMPEGH = z3;
        this.maximumBitDepth = s;
        this.maximumSampleRate = i;
    }

    public boolean getSupportsUHD() {
        return this.supportsUHD;
    }

    public boolean getSupportsAtmos() {
        return this.supportsAtmos;
    }

    public boolean getSupportsMPEGH() {
        return this.supportsMPEGH;
    }

    public short getMaximumBitDepth() {
        return this.maximumBitDepth;
    }

    public int getMaximumSampleRate() {
        return this.maximumSampleRate;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DeviceCapability)) {
            return false;
        }
        DeviceCapability deviceCapability = (DeviceCapability) obj;
        return this.supportsUHD == deviceCapability.supportsUHD && this.supportsAtmos == deviceCapability.supportsAtmos && this.supportsMPEGH == deviceCapability.supportsMPEGH && this.maximumBitDepth == deviceCapability.maximumBitDepth && this.maximumSampleRate == deviceCapability.maximumSampleRate;
    }

    public int hashCode() {
        return ((((((((527 + (this.supportsUHD ? 1 : 0)) * 31) + (this.supportsAtmos ? 1 : 0)) * 31) + (this.supportsMPEGH ? 1 : 0)) * 31) + this.maximumBitDepth) * 31) + this.maximumSampleRate;
    }

    public String toString() {
        return "DeviceCapability{supportsUHD=" + this.supportsUHD + ",supportsAtmos=" + this.supportsAtmos + ",supportsMPEGH=" + this.supportsMPEGH + ",maximumBitDepth=" + ((int) this.maximumBitDepth) + ",maximumSampleRate=" + this.maximumSampleRate + "}";
    }
}
