package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class UpsellAction {
    private final UpsellTypeEnum upsellType;
    private final String upsellTypeEnumValue;

    public interface BuildStep {
    }

    public static class Builder implements UpsellTypeStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface UpsellTypeStep {
    }

    public UpsellTypeEnum getUpsellType() {
        return this.upsellType;
    }

    public String getUpsellTypeEnumValue() {
        return this.upsellTypeEnumValue;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || UpsellAction.class != obj.getClass()) {
            return false;
        }
        UpsellAction upsellAction = (UpsellAction) obj;
        return ObjectsCompat.equals(getUpsellType(), upsellAction.getUpsellType()) && ObjectsCompat.equals(getUpsellTypeEnumValue(), upsellAction.getUpsellTypeEnumValue());
    }

    public int hashCode() {
        return (getUpsellType() + getUpsellTypeEnumValue()).hashCode();
    }
}
