package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class PlayableEntitySeed {
    private final String id;
    private final PlayableEntitySeedIdTypeEnum idType;

    public interface BuildStep {
    }

    public static class Builder implements IdTypeStep, IdStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface IdStep {
    }

    public interface IdTypeStep {
    }

    public PlayableEntitySeedIdTypeEnum getIdType() {
        return this.idType;
    }

    public String getId() {
        return this.id;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PlayableEntitySeed.class != obj.getClass()) {
            return false;
        }
        PlayableEntitySeed playableEntitySeed = (PlayableEntitySeed) obj;
        return ObjectsCompat.equals(getIdType(), playableEntitySeed.getIdType()) && ObjectsCompat.equals(getId(), playableEntitySeed.getId());
    }

    public int hashCode() {
        return (getIdType() + getId()).hashCode();
    }
}
