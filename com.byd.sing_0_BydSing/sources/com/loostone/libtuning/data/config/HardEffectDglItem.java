package com.loostone.libtuning.data.config;

/* JADX INFO: loaded from: classes.dex */
public final class HardEffectDglItem {
    private HardEffect hardEffect;
    private byte[] hardEffectData;
    private HardEffectVolume hardEffectVolume;
    private byte[] hardEffectVolumeData;
    private String productName;

    public final HardEffect getHardEffect() {
        return this.hardEffect;
    }

    public final byte[] getHardEffectData() {
        return this.hardEffectData;
    }

    public final HardEffectVolume getHardEffectVolume() {
        return this.hardEffectVolume;
    }

    public final byte[] getHardEffectVolumeData() {
        return this.hardEffectVolumeData;
    }

    public final String getProductName() {
        return this.productName;
    }

    public final void setHardEffect(HardEffect hardEffect) {
        this.hardEffect = hardEffect;
    }

    public final void setHardEffectData(byte[] bArr) {
        this.hardEffectData = bArr;
    }

    public final void setHardEffectVolume(HardEffectVolume hardEffectVolume) {
        this.hardEffectVolume = hardEffectVolume;
    }

    public final void setHardEffectVolumeData(byte[] bArr) {
        this.hardEffectVolumeData = bArr;
    }

    public final void setProductName(String str) {
        this.productName = str;
    }
}
