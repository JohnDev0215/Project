package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class PlayableEntitySeedContextInput {
    private final String id;
    private final PlayableEntitySeedIdTypeEnum idType;
    private final MetricsContextInput metricsContext;

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

    public MetricsContextInput getMetricsContext() {
        return this.metricsContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PlayableEntitySeedContextInput.class != obj.getClass()) {
            return false;
        }
        PlayableEntitySeedContextInput playableEntitySeedContextInput = (PlayableEntitySeedContextInput) obj;
        return ObjectsCompat.equals(getId(), playableEntitySeedContextInput.getId()) && ObjectsCompat.equals(getIdType(), playableEntitySeedContextInput.getIdType()) && ObjectsCompat.equals(getMetricsContext(), playableEntitySeedContextInput.getMetricsContext());
    }

    public int hashCode() {
        return (getId() + getIdType() + getMetricsContext()).hashCode();
    }
}
