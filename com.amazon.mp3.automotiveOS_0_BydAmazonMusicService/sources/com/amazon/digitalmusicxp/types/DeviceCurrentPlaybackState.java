package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.QueueEntityIdTypeEnum;
import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DeviceCurrentPlaybackState.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b%\u0010&J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0018\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010\n\u001a\u0004\b\u001d\u0010\fR\u0019\u0010\u001e\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001e\u0010\n\u001a\u0004\b\u001f\u0010\fR\u0019\u0010!\u001a\u0004\u0018\u00010 8\u0006¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$¨\u0006'"}, d2 = {"Lcom/amazon/digitalmusicxp/types/DeviceCurrentPlaybackState;", "", "", "toString", "", "hashCode", "other", "", "equals", "referenceId", "Ljava/lang/String;", "getReferenceId", "()Ljava/lang/String;", "Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;", "getType", "()Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;", "", "progressInMs", "F", "getProgressInMs", "()F", "", "progressTimestamp", "J", "getProgressTimestamp", "()J", "title", "getTitle", "subTitle", "getSubTitle", "Lcom/amazon/digitalmusicxp/types/ImageMetadata;", "imageMetadata", "Lcom/amazon/digitalmusicxp/types/ImageMetadata;", "getImageMetadata", "()Lcom/amazon/digitalmusicxp/types/ImageMetadata;", "<init>", "(Ljava/lang/String;Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;FJLjava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/types/ImageMetadata;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class DeviceCurrentPlaybackState {
    private final ImageMetadata imageMetadata;
    private final float progressInMs;
    private final long progressTimestamp;
    private final String referenceId;
    private final String subTitle;
    private final String title;
    private final QueueEntityIdTypeEnum type;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceCurrentPlaybackState)) {
            return false;
        }
        DeviceCurrentPlaybackState deviceCurrentPlaybackState = (DeviceCurrentPlaybackState) other;
        return Intrinsics.areEqual(this.referenceId, deviceCurrentPlaybackState.referenceId) && this.type == deviceCurrentPlaybackState.type && Intrinsics.areEqual((Object) Float.valueOf(this.progressInMs), (Object) Float.valueOf(deviceCurrentPlaybackState.progressInMs)) && this.progressTimestamp == deviceCurrentPlaybackState.progressTimestamp && Intrinsics.areEqual(this.title, deviceCurrentPlaybackState.title) && Intrinsics.areEqual(this.subTitle, deviceCurrentPlaybackState.subTitle) && Intrinsics.areEqual(this.imageMetadata, deviceCurrentPlaybackState.imageMetadata);
    }

    public int hashCode() {
        int iHashCode = ((((((this.referenceId.hashCode() * 31) + this.type.hashCode()) * 31) + Float.hashCode(this.progressInMs)) * 31) + Long.hashCode(this.progressTimestamp)) * 31;
        String str = this.title;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.subTitle;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        ImageMetadata imageMetadata = this.imageMetadata;
        return iHashCode3 + (imageMetadata != null ? imageMetadata.hashCode() : 0);
    }

    public String toString() {
        return "DeviceCurrentPlaybackState(referenceId=" + this.referenceId + ", type=" + this.type + ", progressInMs=" + this.progressInMs + ", progressTimestamp=" + this.progressTimestamp + ", title=" + this.title + ", subTitle=" + this.subTitle + ", imageMetadata=" + this.imageMetadata + ')';
    }

    public DeviceCurrentPlaybackState(String referenceId, QueueEntityIdTypeEnum type, float f, long j, String str, String str2, ImageMetadata imageMetadata) {
        Intrinsics.checkNotNullParameter(referenceId, "referenceId");
        Intrinsics.checkNotNullParameter(type, "type");
        this.referenceId = referenceId;
        this.type = type;
        this.progressInMs = f;
        this.progressTimestamp = j;
        this.title = str;
        this.subTitle = str2;
        this.imageMetadata = imageMetadata;
        ExpectedFunctionsKt.freeze(this);
    }

    public final String getReferenceId() {
        return this.referenceId;
    }

    public final QueueEntityIdTypeEnum getType() {
        return this.type;
    }

    public final float getProgressInMs() {
        return this.progressInMs;
    }

    public final long getProgressTimestamp() {
        return this.progressTimestamp;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    public final ImageMetadata getImageMetadata() {
        return this.imageMetadata;
    }
}
