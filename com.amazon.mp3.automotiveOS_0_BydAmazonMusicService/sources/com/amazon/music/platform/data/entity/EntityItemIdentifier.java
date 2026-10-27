package com.amazon.music.platform.data.entity;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.data.eligibility.factory.EligibilityInput;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;

/* JADX INFO: compiled from: EntityItemIdentifier.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0087\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0017\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "Lcom/amazon/music/platform/data/eligibility/factory/EligibilityInput;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/music/platform/data/entity/EntityId;", "id", "Lcom/amazon/music/platform/data/entity/EntityId;", "getId", "()Lcom/amazon/music/platform/data/entity/EntityId;", "Lcom/amazon/music/platform/data/entity/EntityItemType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/music/platform/data/entity/EntityItemType;", "getType", "()Lcom/amazon/music/platform/data/entity/EntityItemType;", "<init>", "(Lcom/amazon/music/platform/data/entity/EntityId;Lcom/amazon/music/platform/data/entity/EntityItemType;)V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
@Serializable
public final /* data */ class EntityItemIdentifier implements EligibilityInput {
    private final EntityId id;
    private final EntityItemType type;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EntityItemIdentifier)) {
            return false;
        }
        EntityItemIdentifier entityItemIdentifier = (EntityItemIdentifier) other;
        return Intrinsics.areEqual(this.id, entityItemIdentifier.id) && Intrinsics.areEqual(this.type, entityItemIdentifier.type);
    }

    public int hashCode() {
        return (this.id.hashCode() * 31) + this.type.hashCode();
    }

    public String toString() {
        return "EntityItemIdentifier(id=" + this.id + ", type=" + this.type + ')';
    }

    public EntityItemIdentifier(EntityId id, EntityItemType type) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        this.id = id;
        this.type = type;
        PlatformProviderUtilKt.freeze(this);
    }

    public final EntityId getId() {
        return this.id;
    }

    public final EntityItemType getType() {
        return this.type;
    }
}
