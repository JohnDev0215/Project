package com.amazon.mp3.auto.storage;

import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.music.find.utils.PageUriUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PresetIdentifier.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\n\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/auto/storage/PresetIdentifier;", "Lcom/amazon/mp3/library/refinement/Refinable;", "", "toString", "", "hashCode", "", "other", "", "equals", "identifier", "Ljava/lang/String;", "getIdentifier", "()Ljava/lang/String;", "Lcom/amazon/mp3/auto/storage/PresetEntityType;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/mp3/auto/storage/PresetEntityType;", "getEntityType", "()Lcom/amazon/mp3/auto/storage/PresetEntityType;", "<init>", "(Ljava/lang/String;Lcom/amazon/mp3/auto/storage/PresetEntityType;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class PresetIdentifier implements Refinable {
    private final PresetEntityType entityType;
    private final String identifier;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PresetIdentifier)) {
            return false;
        }
        PresetIdentifier presetIdentifier = (PresetIdentifier) other;
        return Intrinsics.areEqual(this.identifier, presetIdentifier.identifier) && this.entityType == presetIdentifier.entityType;
    }

    public int hashCode() {
        return (this.identifier.hashCode() * 31) + this.entityType.hashCode();
    }

    public String toString() {
        return "PresetIdentifier(identifier=" + this.identifier + ", entityType=" + this.entityType + ')';
    }

    public PresetIdentifier(String identifier, PresetEntityType entityType) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        this.identifier = identifier;
        this.entityType = entityType;
    }

    public final PresetEntityType getEntityType() {
        return this.entityType;
    }

    public final String getIdentifier() {
        return this.identifier;
    }
}
