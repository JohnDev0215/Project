package com.amazon.music.find.intents;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.find.common.EntityIdType;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.platform.find.service.common.EntityProperties;
import com.amazon.music.platform.find.utils.database.JsonSerializationHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityVisitIntentInfo.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 32\u00020\u0001:\u00013B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004BM\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012J\t\u0010 \u001a\u00020\u0006HÆ\u0003J\t\u0010!\u001a\u00020\u0006HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\t\u0010#\u001a\u00020\u000bHÆ\u0003J\t\u0010$\u001a\u00020\u0006HÆ\u0003J\t\u0010%\u001a\u00020\u000eHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0011HÆ\u0003J]\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÆ\u0001J\b\u0010)\u001a\u00020*H\u0016J\u0013\u0010+\u001a\u00020\u000e2\b\u0010,\u001a\u0004\u0018\u00010-HÖ\u0003J\t\u0010.\u001a\u00020*HÖ\u0001J\t\u0010/\u001a\u00020\u0006HÖ\u0001J\u0018\u00100\u001a\u0002012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00102\u001a\u00020*H\u0016R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014¨\u00064"}, d2 = {"Lcom/amazon/music/find/intents/EntityVisitIntentInfo;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "title", "", ContextMappingConstants.ENTITY_ID, ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/platform/find/common/EntityIdType;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/platform/find/common/EntityType;", "imageUrl", "didNavigateFromSearch", "", "contentUrl", "entityProperties", "Lcom/amazon/music/platform/find/service/common/EntityProperties;", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/platform/find/common/EntityIdType;Lcom/amazon/music/platform/find/common/EntityType;Ljava/lang/String;ZLjava/lang/String;Lcom/amazon/music/platform/find/service/common/EntityProperties;)V", "getContentUrl", "()Ljava/lang/String;", "getDidNavigateFromSearch", "()Z", "getEntityId", "getEntityIdType", "()Lcom/amazon/music/platform/find/common/EntityIdType;", "getEntityProperties", "()Lcom/amazon/music/platform/find/service/common/EntityProperties;", "getEntityType", "()Lcom/amazon/music/platform/find/common/EntityType;", "getImageUrl", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "flags", "CREATOR", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class EntityVisitIntentInfo implements Parcelable {

    /* JADX INFO: renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String contentUrl;
    private final boolean didNavigateFromSearch;
    private final String entityId;
    private final EntityIdType entityIdType;
    private final EntityProperties entityProperties;
    private final EntityType entityType;
    private final String imageUrl;
    private final String title;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
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
    public final EntityType getEntityType() {
        return this.entityType;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getImageUrl() {
        return this.imageUrl;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getDidNavigateFromSearch() {
        return this.didNavigateFromSearch;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getContentUrl() {
        return this.contentUrl;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final EntityProperties getEntityProperties() {
        return this.entityProperties;
    }

    public final EntityVisitIntentInfo copy(String title, String entityId, EntityIdType entityIdType, EntityType entityType, String imageUrl, boolean didNavigateFromSearch, String contentUrl, EntityProperties entityProperties) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(entityIdType, "entityIdType");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        return new EntityVisitIntentInfo(title, entityId, entityIdType, entityType, imageUrl, didNavigateFromSearch, contentUrl, entityProperties);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EntityVisitIntentInfo)) {
            return false;
        }
        EntityVisitIntentInfo entityVisitIntentInfo = (EntityVisitIntentInfo) other;
        return Intrinsics.areEqual(this.title, entityVisitIntentInfo.title) && Intrinsics.areEqual(this.entityId, entityVisitIntentInfo.entityId) && this.entityIdType == entityVisitIntentInfo.entityIdType && this.entityType == entityVisitIntentInfo.entityType && Intrinsics.areEqual(this.imageUrl, entityVisitIntentInfo.imageUrl) && this.didNavigateFromSearch == entityVisitIntentInfo.didNavigateFromSearch && Intrinsics.areEqual(this.contentUrl, entityVisitIntentInfo.contentUrl) && Intrinsics.areEqual(this.entityProperties, entityVisitIntentInfo.entityProperties);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v9, types: [int] */
    public int hashCode() {
        int iHashCode = ((((((((this.title.hashCode() * 31) + this.entityId.hashCode()) * 31) + this.entityIdType.hashCode()) * 31) + this.entityType.hashCode()) * 31) + this.imageUrl.hashCode()) * 31;
        boolean z = this.didNavigateFromSearch;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = (iHashCode + r1) * 31;
        String str = this.contentUrl;
        int iHashCode2 = (i + (str == null ? 0 : str.hashCode())) * 31;
        EntityProperties entityProperties = this.entityProperties;
        return iHashCode2 + (entityProperties != null ? entityProperties.hashCode() : 0);
    }

    public String toString() {
        return "EntityVisitIntentInfo(title=" + this.title + ", entityId=" + this.entityId + ", entityIdType=" + this.entityIdType + ", entityType=" + this.entityType + ", imageUrl=" + this.imageUrl + ", didNavigateFromSearch=" + this.didNavigateFromSearch + ", contentUrl=" + ((Object) this.contentUrl) + ", entityProperties=" + this.entityProperties + ')';
    }

    public EntityVisitIntentInfo(String title, String entityId, EntityIdType entityIdType, EntityType entityType, String imageUrl, boolean z, String str, EntityProperties entityProperties) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(entityIdType, "entityIdType");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        this.title = title;
        this.entityId = entityId;
        this.entityIdType = entityIdType;
        this.entityType = entityType;
        this.imageUrl = imageUrl;
        this.didNavigateFromSearch = z;
        this.contentUrl = str;
        this.entityProperties = entityProperties;
    }

    public /* synthetic */ EntityVisitIntentInfo(String str, String str2, EntityIdType entityIdType, EntityType entityType, String str3, boolean z, String str4, EntityProperties entityProperties, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, entityIdType, entityType, str3, z, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? null : entityProperties);
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getEntityId() {
        return this.entityId;
    }

    public final EntityIdType getEntityIdType() {
        return this.entityIdType;
    }

    public final EntityType getEntityType() {
        return this.entityType;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final boolean getDidNavigateFromSearch() {
        return this.didNavigateFromSearch;
    }

    public final String getContentUrl() {
        return this.contentUrl;
    }

    public final EntityProperties getEntityProperties() {
        return this.entityProperties;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public EntityVisitIntentInfo(Parcel parcel) {
        EntityIdType entityIdTypeValueOf;
        EntityType entityTypeValueOf;
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        String string = parcel.readString();
        String str = string == null ? "" : string;
        String string2 = parcel.readString();
        String str2 = string2 == null ? "" : string2;
        try {
            String string3 = parcel.readString();
            entityIdTypeValueOf = EntityIdType.valueOf(string3 == null ? "" : string3);
        } catch (Exception unused) {
            entityIdTypeValueOf = EntityIdType.ASIN;
        }
        EntityIdType entityIdType = entityIdTypeValueOf;
        try {
            String string4 = parcel.readString();
            entityTypeValueOf = EntityType.valueOf(string4 == null ? "" : string4);
        } catch (Exception unused2) {
            entityTypeValueOf = EntityType.TRACK;
        }
        EntityType entityType = entityTypeValueOf;
        String string5 = parcel.readString();
        this(str, str2, entityIdType, entityType, string5 == null ? "" : string5, parcel.readByte() != 0, parcel.readString(), INSTANCE.deserialize(parcel.readString()));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(this.title);
        parcel.writeString(this.entityId);
        parcel.writeString(this.entityIdType.name());
        parcel.writeString(this.entityType.name());
        parcel.writeString(this.imageUrl);
        parcel.writeByte(this.didNavigateFromSearch ? (byte) 1 : (byte) 0);
        parcel.writeString(this.contentUrl);
        parcel.writeString(INSTANCE.serialize(this.entityProperties));
    }

    /* JADX INFO: renamed from: com.amazon.music.find.intents.EntityVisitIntentInfo$CREATOR, reason: from kotlin metadata */
    /* JADX INFO: compiled from: EntityVisitIntentInfo.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u001d\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u000fJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\b¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/find/intents/EntityVisitIntentInfo$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/amazon/music/find/intents/EntityVisitIntentInfo;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "deserialize", "Lcom/amazon/music/platform/find/service/common/EntityProperties;", "entityPropertiesString", "", "newArray", "", "size", "", "(I)[Lcom/amazon/music/find/intents/EntityVisitIntentInfo;", "serialize", "entityProperties", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion implements Parcelable.Creator<EntityVisitIntentInfo> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EntityVisitIntentInfo createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new EntityVisitIntentInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EntityVisitIntentInfo[] newArray(int size) {
            return new EntityVisitIntentInfo[size];
        }

        public final String serialize(EntityProperties entityProperties) {
            return JsonSerializationHelper.INSTANCE.serializeEntityProperties(entityProperties);
        }

        public final EntityProperties deserialize(String entityPropertiesString) {
            return JsonSerializationHelper.INSTANCE.deserializeEntityProperties(entityPropertiesString);
        }
    }
}
