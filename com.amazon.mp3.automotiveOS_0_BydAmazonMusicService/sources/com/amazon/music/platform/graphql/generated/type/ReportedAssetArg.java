package com.amazon.music.platform.graphql.generated.type;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.apollographql.apollo3.api.Optional;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: ReportedAssetArg.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0006\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t\u0012\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t\u0012\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t\u0012\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t\u0012\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t\u0012\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\t¢\u0006\u0004\b\u001b\u0010\u001cJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001f\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001f\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u001f\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\rR\u001f\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000b\u001a\u0004\b\u0013\u0010\rR\u001f\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0015\u0010\rR\u001f\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u000b\u001a\u0004\b\u0017\u0010\rR\u001f\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\t8\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u000b\u001a\u0004\b\u001a\u0010\r¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/type/ReportedAssetArg;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/apollographql/apollo3/api/Optional;", "id", "Lcom/apollographql/apollo3/api/Optional;", "getId", "()Lcom/apollographql/apollo3/api/Optional;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "getType", "title", "getTitle", ContextMappingConstants.IMAGE, "getImage", "creatorId", "getCreatorId", "publisherName", "getPublisherName", "", "durationInAsset", "getDurationInAsset", "<init>", "(Lcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class ReportedAssetArg {
    private final Optional<String> creatorId;
    private final Optional<Double> durationInAsset;
    private final Optional<String> id;
    private final Optional<String> image;
    private final Optional<String> publisherName;
    private final Optional<String> title;
    private final Optional<String> type;

    public ReportedAssetArg() {
        this(null, null, null, null, null, null, null, WorkQueueKt.MASK, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReportedAssetArg)) {
            return false;
        }
        ReportedAssetArg reportedAssetArg = (ReportedAssetArg) other;
        return Intrinsics.areEqual(this.id, reportedAssetArg.id) && Intrinsics.areEqual(this.type, reportedAssetArg.type) && Intrinsics.areEqual(this.title, reportedAssetArg.title) && Intrinsics.areEqual(this.image, reportedAssetArg.image) && Intrinsics.areEqual(this.creatorId, reportedAssetArg.creatorId) && Intrinsics.areEqual(this.publisherName, reportedAssetArg.publisherName) && Intrinsics.areEqual(this.durationInAsset, reportedAssetArg.durationInAsset);
    }

    public int hashCode() {
        return (((((((((((this.id.hashCode() * 31) + this.type.hashCode()) * 31) + this.title.hashCode()) * 31) + this.image.hashCode()) * 31) + this.creatorId.hashCode()) * 31) + this.publisherName.hashCode()) * 31) + this.durationInAsset.hashCode();
    }

    public String toString() {
        return "ReportedAssetArg(id=" + this.id + ", type=" + this.type + ", title=" + this.title + ", image=" + this.image + ", creatorId=" + this.creatorId + ", publisherName=" + this.publisherName + ", durationInAsset=" + this.durationInAsset + ')';
    }

    public ReportedAssetArg(Optional<String> id, Optional<String> type, Optional<String> title, Optional<String> image, Optional<String> creatorId, Optional<String> publisherName, Optional<Double> durationInAsset) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(creatorId, "creatorId");
        Intrinsics.checkNotNullParameter(publisherName, "publisherName");
        Intrinsics.checkNotNullParameter(durationInAsset, "durationInAsset");
        this.id = id;
        this.type = type;
        this.title = title;
        this.image = image;
        this.creatorId = creatorId;
        this.publisherName = publisherName;
        this.durationInAsset = durationInAsset;
    }

    public /* synthetic */ ReportedAssetArg(Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, Optional optional6, Optional optional7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Optional.Absent.INSTANCE : optional, (i & 2) != 0 ? Optional.Absent.INSTANCE : optional2, (i & 4) != 0 ? Optional.Absent.INSTANCE : optional3, (i & 8) != 0 ? Optional.Absent.INSTANCE : optional4, (i & 16) != 0 ? Optional.Absent.INSTANCE : optional5, (i & 32) != 0 ? Optional.Absent.INSTANCE : optional6, (i & 64) != 0 ? Optional.Absent.INSTANCE : optional7);
    }

    public final Optional<String> getId() {
        return this.id;
    }

    public final Optional<String> getType() {
        return this.type;
    }

    public final Optional<String> getTitle() {
        return this.title;
    }

    public final Optional<String> getImage() {
        return this.image;
    }

    public final Optional<String> getCreatorId() {
        return this.creatorId;
    }

    public final Optional<String> getPublisherName() {
        return this.publisherName;
    }

    public final Optional<Double> getDurationInAsset() {
        return this.durationInAsset;
    }
}
