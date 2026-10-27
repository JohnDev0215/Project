package com.spotify.playbacknative;

import android.media.AudioDeviceInfo;
import p.hf2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class AudioDeviceInfoFacade {
    private final String address;
    private final int audioDeviceInfoType;
    private final String productName;

    public AudioDeviceInfoFacade(String str, int i, String str2) {
        v03.j(str, "productName");
        this.productName = str;
        this.audioDeviceInfoType = i;
        this.address = str2;
    }

    public static /* synthetic */ AudioDeviceInfoFacade copy$default(AudioDeviceInfoFacade audioDeviceInfoFacade, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = audioDeviceInfoFacade.productName;
        }
        if ((i2 & 2) != 0) {
            i = audioDeviceInfoFacade.audioDeviceInfoType;
        }
        if ((i2 & 4) != 0) {
            str2 = audioDeviceInfoFacade.address;
        }
        return audioDeviceInfoFacade.copy(str, i, str2);
    }

    public final String component1() {
        return this.productName;
    }

    public final int component2() {
        return this.audioDeviceInfoType;
    }

    public final String component3() {
        return this.address;
    }

    public final AudioDeviceInfoFacade copy(String str, int i, String str2) {
        v03.j(str, "productName");
        return new AudioDeviceInfoFacade(str, i, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioDeviceInfoFacade)) {
            return false;
        }
        AudioDeviceInfoFacade audioDeviceInfoFacade = (AudioDeviceInfoFacade) obj;
        return v03.b(this.productName, audioDeviceInfoFacade.productName) && this.audioDeviceInfoType == audioDeviceInfoFacade.audioDeviceInfoType && v03.b(this.address, audioDeviceInfoFacade.address);
    }

    public final String getAddress() {
        return this.address;
    }

    public final int getAudioDeviceInfoType() {
        return this.audioDeviceInfoType;
    }

    public final String getProductName() {
        return this.productName;
    }

    public int hashCode() {
        int iA = hf2.a(this.audioDeviceInfoType, this.productName.hashCode() * 31, 31);
        String str = this.address;
        return iA + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AudioDeviceInfoFacade(productName=");
        sb.append(this.productName);
        sb.append(", audioDeviceInfoType=");
        sb.append(this.audioDeviceInfoType);
        sb.append(", address=");
        return hf2.d(sb, this.address, ')');
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AudioDeviceInfoFacade(AudioDeviceInfo audioDeviceInfo) {
        this(audioDeviceInfo.getProductName().toString(), audioDeviceInfo.getType(), AudioDeviceInfoFacadeKt.getAddressFrom(audioDeviceInfo));
        v03.j(audioDeviceInfo, "audioDeviceInfo");
    }
}
