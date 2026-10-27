package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class PlayableEntitySeedInput {
    private final String id;
    private final PlayableEntitySeedIdTypeEnum idType;

    public interface BuildStep {
    }

    public static class Builder implements IdStep, IdTypeStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface IdStep {
    }

    public interface IdTypeStep {
    }

    public String getId() {
        return this.id;
    }

    public PlayableEntitySeedIdTypeEnum getIdType() {
        return this.idType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PlayableEntitySeedInput.class != obj.getClass()) {
            return false;
        }
        PlayableEntitySeedInput playableEntitySeedInput = (PlayableEntitySeedInput) obj;
        return ObjectsCompat.equals(getId(), playableEntitySeedInput.getId()) && ObjectsCompat.equals(getIdType(), playableEntitySeedInput.getIdType());
    }

    public int hashCode() {
        return (getId() + getIdType()).hashCode();
    }
}
