package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class PlaybackAssetSource {
    private final Integer expiresTimeSeconds;
    private final PlaybackAssetSourceTypeEnum type;
    private final String typeEnumValue;
    private final String value;

    public interface BuildStep {
    }

    public static class Builder implements TypeStep, ValueStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface TypeStep {
    }

    public interface ValueStep {
    }

    public PlaybackAssetSourceTypeEnum getType() {
        return this.type;
    }

    public String getTypeEnumValue() {
        return this.typeEnumValue;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getExpiresTimeSeconds() {
        return this.expiresTimeSeconds;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PlaybackAssetSource.class != obj.getClass()) {
            return false;
        }
        PlaybackAssetSource playbackAssetSource = (PlaybackAssetSource) obj;
        return ObjectsCompat.equals(getType(), playbackAssetSource.getType()) && ObjectsCompat.equals(getTypeEnumValue(), playbackAssetSource.getTypeEnumValue()) && ObjectsCompat.equals(getValue(), playbackAssetSource.getValue()) && ObjectsCompat.equals(getExpiresTimeSeconds(), playbackAssetSource.getExpiresTimeSeconds());
    }

    public int hashCode() {
        return (getType() + getTypeEnumValue() + getValue() + getExpiresTimeSeconds()).hashCode();
    }
}
