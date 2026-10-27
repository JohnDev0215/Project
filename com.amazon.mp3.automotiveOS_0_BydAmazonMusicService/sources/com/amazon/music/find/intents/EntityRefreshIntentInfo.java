package com.amazon.music.find.intents;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.find.common.EntityIdType;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityRefreshIntentInfo.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u0000 %2\u00020\u0001:\u0002$%B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B+\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\fJ\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\bHÆ\u0003J7\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\bHÆ\u0001J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÖ\u0003J\t\u0010\u001f\u001a\u00020\u001aHÖ\u0001J\t\u0010 \u001a\u00020\bHÖ\u0001J\u0018\u0010!\u001a\u00020\"2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u001aH\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006&"}, d2 = {"Lcom/amazon/music/find/intents/EntityRefreshIntentInfo;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "entityIdentifierType", "Lcom/amazon/music/find/intents/EntityIdentifierType;", ContextMappingConstants.ENTITY_ID, "", ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/platform/find/common/EntityIdType;", "contentUrl", "(Lcom/amazon/music/find/intents/EntityIdentifierType;Ljava/lang/String;Lcom/amazon/music/platform/find/common/EntityIdType;Ljava/lang/String;)V", "getContentUrl", "()Ljava/lang/String;", "getEntityId", "getEntityIdType", "()Lcom/amazon/music/platform/find/common/EntityIdType;", "getEntityIdentifierType", "()Lcom/amazon/music/find/intents/EntityIdentifierType;", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "flags", "Builder", "CREATOR", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class EntityRefreshIntentInfo implements Parcelable {

    /* JADX INFO: renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String contentUrl;
    private final String entityId;
    private final EntityIdType entityIdType;
    private final EntityIdentifierType entityIdentifierType;

    public static /* synthetic */ EntityRefreshIntentInfo copy$default(EntityRefreshIntentInfo entityRefreshIntentInfo, EntityIdentifierType entityIdentifierType, String str, EntityIdType entityIdType, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            entityIdentifierType = entityRefreshIntentInfo.entityIdentifierType;
        }
        if ((i & 2) != 0) {
            str = entityRefreshIntentInfo.entityId;
        }
        if ((i & 4) != 0) {
            entityIdType = entityRefreshIntentInfo.entityIdType;
        }
        if ((i & 8) != 0) {
            str2 = entityRefreshIntentInfo.contentUrl;
        }
        return entityRefreshIntentInfo.copy(entityIdentifierType, str, entityIdType, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final EntityIdentifierType getEntityIdentifierType() {
        return this.entityIdentifierType;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getEntityId() {
        return this.entityId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final EntityIdType getEntityIdType() {
        return this.entityIdType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getContentUrl() {
        return this.contentUrl;
    }

    public final EntityRefreshIntentInfo copy(EntityIdentifierType entityIdentifierType, String entityId, EntityIdType entityIdType, String contentUrl) {
        Intrinsics.checkNotNullParameter(entityIdentifierType, "entityIdentifierType");
        return new EntityRefreshIntentInfo(entityIdentifierType, entityId, entityIdType, contentUrl);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EntityRefreshIntentInfo)) {
            return false;
        }
        EntityRefreshIntentInfo entityRefreshIntentInfo = (EntityRefreshIntentInfo) other;
        return this.entityIdentifierType == entityRefreshIntentInfo.entityIdentifierType && Intrinsics.areEqual(this.entityId, entityRefreshIntentInfo.entityId) && this.entityIdType == entityRefreshIntentInfo.entityIdType && Intrinsics.areEqual(this.contentUrl, entityRefreshIntentInfo.contentUrl);
    }

    public int hashCode() {
        int iHashCode = this.entityIdentifierType.hashCode() * 31;
        String str = this.entityId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        EntityIdType entityIdType = this.entityIdType;
        int iHashCode3 = (iHashCode2 + (entityIdType == null ? 0 : entityIdType.hashCode())) * 31;
        String str2 = this.contentUrl;
        return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "EntityRefreshIntentInfo(entityIdentifierType=" + this.entityIdentifierType + ", entityId=" + ((Object) this.entityId) + ", entityIdType=" + this.entityIdType + ", contentUrl=" + ((Object) this.contentUrl) + ')';
    }

    public EntityRefreshIntentInfo(EntityIdentifierType entityIdentifierType, String str, EntityIdType entityIdType, String str2) {
        Intrinsics.checkNotNullParameter(entityIdentifierType, "entityIdentifierType");
        this.entityIdentifierType = entityIdentifierType;
        this.entityId = str;
        this.entityIdType = entityIdType;
        this.contentUrl = str2;
    }

    public final EntityIdentifierType getEntityIdentifierType() {
        return this.entityIdentifierType;
    }

    public final String getEntityId() {
        return this.entityId;
    }

    public final EntityIdType getEntityIdType() {
        return this.entityIdType;
    }

    public final String getContentUrl() {
        return this.contentUrl;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public EntityRefreshIntentInfo(Parcel parcel) {
        EntityIdType entityIdTypeValueOf;
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        String string = parcel.readString();
        String str = "";
        EntityIdentifierType entityIdentifierTypeValueOf = EntityIdentifierType.valueOf(string == null ? "" : string);
        String string2 = parcel.readString();
        try {
            String string3 = parcel.readString();
            if (string3 != null) {
                str = string3;
            }
            entityIdTypeValueOf = EntityIdType.valueOf(str);
        } catch (Exception unused) {
            entityIdTypeValueOf = null;
        }
        this(entityIdentifierTypeValueOf, string2, entityIdTypeValueOf, parcel.readString());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(this.entityIdentifierType.name());
        parcel.writeString(this.entityId);
        EntityIdType entityIdType = this.entityIdType;
        parcel.writeString(entityIdType == null ? null : entityIdType.name());
        parcel.writeString(this.contentUrl);
    }

    /* JADX INFO: renamed from: com.amazon.music.find.intents.EntityRefreshIntentInfo$CREATOR, reason: from kotlin metadata */
    /* JADX INFO: compiled from: EntityRefreshIntentInfo.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/music/find/intents/EntityRefreshIntentInfo$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/amazon/music/find/intents/EntityRefreshIntentInfo;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/amazon/music/find/intents/EntityRefreshIntentInfo;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion implements Parcelable.Creator<EntityRefreshIntentInfo> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EntityRefreshIntentInfo createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new EntityRefreshIntentInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EntityRefreshIntentInfo[] newArray(int size) {
            return new EntityRefreshIntentInfo[size];
        }
    }

    /* JADX INFO: compiled from: EntityRefreshIntentInfo.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\u0006\u0010\u0018\u001a\u00020\u0019J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0005HÆ\u0003J7\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0005HÖ\u0001J\u0010\u0010%\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u0005J\u001a\u0010&\u001a\u00020\u00002\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006'"}, d2 = {"Lcom/amazon/music/find/intents/EntityRefreshIntentInfo$Builder;", "", "entityIdentifierType", "Lcom/amazon/music/find/intents/EntityIdentifierType;", ContextMappingConstants.ENTITY_ID, "", ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/platform/find/common/EntityIdType;", "contentUrl", "(Lcom/amazon/music/find/intents/EntityIdentifierType;Ljava/lang/String;Lcom/amazon/music/platform/find/common/EntityIdType;Ljava/lang/String;)V", "getContentUrl", "()Ljava/lang/String;", "setContentUrl", "(Ljava/lang/String;)V", "getEntityId", "setEntityId", "getEntityIdType", "()Lcom/amazon/music/platform/find/common/EntityIdType;", "setEntityIdType", "(Lcom/amazon/music/platform/find/common/EntityIdType;)V", "getEntityIdentifierType", "()Lcom/amazon/music/find/intents/EntityIdentifierType;", "setEntityIdentifierType", "(Lcom/amazon/music/find/intents/EntityIdentifierType;)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/music/find/intents/EntityRefreshIntentInfo;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "withContentUrl", "withEntity", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class Builder {
        private String contentUrl;
        private String entityId;
        private EntityIdType entityIdType;
        private EntityIdentifierType entityIdentifierType;

        public Builder() {
            this(null, null, null, null, 15, null);
        }

        public static /* synthetic */ Builder copy$default(Builder builder, EntityIdentifierType entityIdentifierType, String str, EntityIdType entityIdType, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                entityIdentifierType = builder.entityIdentifierType;
            }
            if ((i & 2) != 0) {
                str = builder.entityId;
            }
            if ((i & 4) != 0) {
                entityIdType = builder.entityIdType;
            }
            if ((i & 8) != 0) {
                str2 = builder.contentUrl;
            }
            return builder.copy(entityIdentifierType, str, entityIdType, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final EntityIdentifierType getEntityIdentifierType() {
            return this.entityIdentifierType;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getEntityId() {
            return this.entityId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final EntityIdType getEntityIdType() {
            return this.entityIdType;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getContentUrl() {
            return this.contentUrl;
        }

        public final Builder copy(EntityIdentifierType entityIdentifierType, String entityId, EntityIdType entityIdType, String contentUrl) {
            Intrinsics.checkNotNullParameter(entityIdentifierType, "entityIdentifierType");
            return new Builder(entityIdentifierType, entityId, entityIdType, contentUrl);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Builder)) {
                return false;
            }
            Builder builder = (Builder) other;
            return this.entityIdentifierType == builder.entityIdentifierType && Intrinsics.areEqual(this.entityId, builder.entityId) && this.entityIdType == builder.entityIdType && Intrinsics.areEqual(this.contentUrl, builder.contentUrl);
        }

        public int hashCode() {
            int iHashCode = this.entityIdentifierType.hashCode() * 31;
            String str = this.entityId;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            EntityIdType entityIdType = this.entityIdType;
            int iHashCode3 = (iHashCode2 + (entityIdType == null ? 0 : entityIdType.hashCode())) * 31;
            String str2 = this.contentUrl;
            return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "Builder(entityIdentifierType=" + this.entityIdentifierType + ", entityId=" + ((Object) this.entityId) + ", entityIdType=" + this.entityIdType + ", contentUrl=" + ((Object) this.contentUrl) + ')';
        }

        public Builder(EntityIdentifierType entityIdentifierType, String str, EntityIdType entityIdType, String str2) {
            Intrinsics.checkNotNullParameter(entityIdentifierType, "entityIdentifierType");
            this.entityIdentifierType = entityIdentifierType;
            this.entityId = str;
            this.entityIdType = entityIdType;
            this.contentUrl = str2;
        }

        public /* synthetic */ Builder(EntityIdentifierType entityIdentifierType, String str, EntityIdType entityIdType, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? EntityIdentifierType.ENTITY_ID_AND_TYPE : entityIdentifierType, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : entityIdType, (i & 8) != 0 ? null : str2);
        }

        public final EntityIdentifierType getEntityIdentifierType() {
            return this.entityIdentifierType;
        }

        public final void setEntityIdentifierType(EntityIdentifierType entityIdentifierType) {
            Intrinsics.checkNotNullParameter(entityIdentifierType, "<set-?>");
            this.entityIdentifierType = entityIdentifierType;
        }

        public final String getEntityId() {
            return this.entityId;
        }

        public final void setEntityId(String str) {
            this.entityId = str;
        }

        public final EntityIdType getEntityIdType() {
            return this.entityIdType;
        }

        public final void setEntityIdType(EntityIdType entityIdType) {
            this.entityIdType = entityIdType;
        }

        public final String getContentUrl() {
            return this.contentUrl;
        }

        public final void setContentUrl(String str) {
            this.contentUrl = str;
        }

        public final Builder withEntity(String entityId, EntityIdType entityIdType) {
            setEntityIdentifierType(EntityIdentifierType.ENTITY_ID_AND_TYPE);
            setEntityId(entityId);
            setEntityIdType(entityIdType);
            return this;
        }

        public final Builder withContentUrl(String contentUrl) {
            setEntityIdentifierType(EntityIdentifierType.CONTENT_URL);
            setContentUrl(contentUrl);
            return this;
        }

        public final EntityRefreshIntentInfo build() {
            return new EntityRefreshIntentInfo(this.entityIdentifierType, this.entityId, this.entityIdType, this.contentUrl);
        }
    }
}
