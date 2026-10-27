package android.media.tv.tuner.filter;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class IpFilterConfiguration extends FilterConfiguration {
    private final byte[] mDstIpAddress;
    private final int mDstPort;
    private final boolean mPassthrough;
    private final byte[] mSrcIpAddress;
    private final int mSrcPort;

    @Override // android.media.tv.tuner.filter.FilterConfiguration
    public int getType() {
        return 4;
    }

    private IpFilterConfiguration(Settings settings, byte[] bArr, byte[] bArr2, int i, int i2, boolean z) {
        super(settings);
        this.mSrcIpAddress = bArr;
        this.mDstIpAddress = bArr2;
        this.mSrcPort = i;
        this.mDstPort = i2;
        this.mPassthrough = z;
    }

    public byte[] getSrcIpAddress() {
        return this.mSrcIpAddress;
    }

    public byte[] getDstIpAddress() {
        return this.mDstIpAddress;
    }

    public int getSrcPort() {
        return this.mSrcPort;
    }

    public int getDstPort() {
        return this.mDstPort;
    }

    public boolean isPassthrough() {
        return this.mPassthrough;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private byte[] mDstIpAddress;
        private int mDstPort;
        private boolean mPassthrough;
        private Settings mSettings;
        private byte[] mSrcIpAddress;
        private int mSrcPort;

        private Builder() {
            this.mSrcIpAddress = new byte[]{0, 0, 0, 0};
            this.mDstIpAddress = new byte[]{0, 0, 0, 0};
            this.mSrcPort = 0;
            this.mDstPort = 0;
            this.mPassthrough = false;
        }

        public Builder setSrcIpAddress(byte[] bArr) {
            this.mSrcIpAddress = bArr;
            return this;
        }

        public Builder setDstIpAddress(byte[] bArr) {
            this.mDstIpAddress = bArr;
            return this;
        }

        public Builder setSrcPort(int i) {
            this.mSrcPort = i;
            return this;
        }

        public Builder setDstPort(int i) {
            this.mDstPort = i;
            return this;
        }

        public Builder setPassthrough(boolean z) {
            this.mPassthrough = z;
            return this;
        }

        public Builder setSettings(Settings settings) {
            this.mSettings = settings;
            return this;
        }

        public IpFilterConfiguration build() {
            int length = this.mSrcIpAddress.length;
            if (length != this.mDstIpAddress.length || (length != 4 && length != 16)) {
                throw new IllegalArgumentException("The lengths of src and dst IP address must be 4 or 16 and must be the same.srcLength=" + length + ", dstLength=" + this.mDstIpAddress.length);
            }
            return new IpFilterConfiguration(this.mSettings, this.mSrcIpAddress, this.mDstIpAddress, this.mSrcPort, this.mDstPort, this.mPassthrough);
        }
    }
}
