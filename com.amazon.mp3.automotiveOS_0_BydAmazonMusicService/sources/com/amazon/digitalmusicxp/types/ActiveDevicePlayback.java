package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.enums.DevicePlaybackStateEnum;
import com.amazon.digitalmusicxp.enums.LoopModeEnum;
import com.amazon.digitalmusicxp.enums.ShuffleModeEnum;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActiveDevicePlayback.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0017\u0010\u0011\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\fR\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0014\u0010\fR\u0017\u0010\u0015\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\n\u001a\u0004\b\u0016\u0010\fR\u0017\u0010\u0018\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001d\u001a\u00020\u001c8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\"\u001a\u00020!8\u0006¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u0017\u0010'\u001a\u00020&8\u0006¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R\u0017\u0010,\u001a\u00020+8\u0006¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R\u0019\u00100\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b0\u0010\n\u001a\u0004\b1\u0010\fR\u0019\u00102\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b2\u0010\n\u001a\u0004\b3\u0010\fR\u0019\u00105\u001a\u0004\u0018\u0001048\u0006¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108R\u0019\u00109\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b9\u0010\n\u001a\u0004\b:\u0010\f¨\u0006;"}, d2 = {"Lcom/amazon/digitalmusicxp/types/ActiveDevicePlayback;", "", "", "toString", "", "hashCode", "other", "", "equals", "customerId", "Ljava/lang/String;", "getCustomerId", "()Ljava/lang/String;", "queueId", "getQueueId", Splash.PARAMS_DEVICE_ID, "getDeviceId", Splash.PARAMS_DEVICE_TYPE, "getDeviceType", "deviceName", "getDeviceName", "entityReferenceId", "getEntityReferenceId", "", "progressInMs", "F", "getProgressInMs", "()F", "", "progressTimestamp", "J", "getProgressTimestamp", "()J", "Lcom/amazon/digitalmusicxp/enums/LoopModeEnum;", "loopMode", "Lcom/amazon/digitalmusicxp/enums/LoopModeEnum;", "getLoopMode", "()Lcom/amazon/digitalmusicxp/enums/LoopModeEnum;", "Lcom/amazon/digitalmusicxp/enums/ShuffleModeEnum;", "shuffleMode", "Lcom/amazon/digitalmusicxp/enums/ShuffleModeEnum;", "getShuffleMode", "()Lcom/amazon/digitalmusicxp/enums/ShuffleModeEnum;", "Lcom/amazon/digitalmusicxp/enums/DevicePlaybackStateEnum;", TetheredMessageKey.playbackState, "Lcom/amazon/digitalmusicxp/enums/DevicePlaybackStateEnum;", "getPlaybackState", "()Lcom/amazon/digitalmusicxp/enums/DevicePlaybackStateEnum;", "title", "getTitle", "subTitle", "getSubTitle", "Lcom/amazon/digitalmusicxp/types/ImageMetadata;", "imageMetadata", "Lcom/amazon/digitalmusicxp/types/ImageMetadata;", "getImageMetadata", "()Lcom/amazon/digitalmusicxp/types/ImageMetadata;", "serializedMusicRequestIdentityContext", "getSerializedMusicRequestIdentityContext", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class ActiveDevicePlayback {
    private final String customerId;
    private final String deviceId;
    private final String deviceName;
    private final String deviceType;
    private final String entityReferenceId;
    private final ImageMetadata imageMetadata;
    private final LoopModeEnum loopMode;
    private final DevicePlaybackStateEnum playbackState;
    private final float progressInMs;
    private final long progressTimestamp;
    private final String queueId;
    private final String serializedMusicRequestIdentityContext;
    private final ShuffleModeEnum shuffleMode;
    private final String subTitle;
    private final String title;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActiveDevicePlayback)) {
            return false;
        }
        ActiveDevicePlayback activeDevicePlayback = (ActiveDevicePlayback) other;
        return Intrinsics.areEqual(this.customerId, activeDevicePlayback.customerId) && Intrinsics.areEqual(this.queueId, activeDevicePlayback.queueId) && Intrinsics.areEqual(this.deviceId, activeDevicePlayback.deviceId) && Intrinsics.areEqual(this.deviceType, activeDevicePlayback.deviceType) && Intrinsics.areEqual(this.deviceName, activeDevicePlayback.deviceName) && Intrinsics.areEqual(this.entityReferenceId, activeDevicePlayback.entityReferenceId) && Intrinsics.areEqual((Object) Float.valueOf(this.progressInMs), (Object) Float.valueOf(activeDevicePlayback.progressInMs)) && this.progressTimestamp == activeDevicePlayback.progressTimestamp && this.loopMode == activeDevicePlayback.loopMode && this.shuffleMode == activeDevicePlayback.shuffleMode && this.playbackState == activeDevicePlayback.playbackState && Intrinsics.areEqual(this.title, activeDevicePlayback.title) && Intrinsics.areEqual(this.subTitle, activeDevicePlayback.subTitle) && Intrinsics.areEqual(this.imageMetadata, activeDevicePlayback.imageMetadata) && Intrinsics.areEqual(this.serializedMusicRequestIdentityContext, activeDevicePlayback.serializedMusicRequestIdentityContext);
    }

    public int hashCode() {
        int iHashCode = ((((((this.customerId.hashCode() * 31) + this.queueId.hashCode()) * 31) + this.deviceId.hashCode()) * 31) + this.deviceType.hashCode()) * 31;
        String str = this.deviceName;
        int iHashCode2 = (((((((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.entityReferenceId.hashCode()) * 31) + Float.hashCode(this.progressInMs)) * 31) + Long.hashCode(this.progressTimestamp)) * 31) + this.loopMode.hashCode()) * 31) + this.shuffleMode.hashCode()) * 31) + this.playbackState.hashCode()) * 31;
        String str2 = this.title;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.subTitle;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        ImageMetadata imageMetadata = this.imageMetadata;
        int iHashCode5 = (iHashCode4 + (imageMetadata == null ? 0 : imageMetadata.hashCode())) * 31;
        String str4 = this.serializedMusicRequestIdentityContext;
        return iHashCode5 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "ActiveDevicePlayback(customerId=" + this.customerId + ", queueId=" + this.queueId + ", deviceId=" + this.deviceId + ", deviceType=" + this.deviceType + ", deviceName=" + this.deviceName + ", entityReferenceId=" + this.entityReferenceId + ", progressInMs=" + this.progressInMs + ", progressTimestamp=" + this.progressTimestamp + ", loopMode=" + this.loopMode + ", shuffleMode=" + this.shuffleMode + ", playbackState=" + this.playbackState + ", title=" + this.title + ", subTitle=" + this.subTitle + ", imageMetadata=" + this.imageMetadata + ", serializedMusicRequestIdentityContext=" + this.serializedMusicRequestIdentityContext + ')';
    }

    public final String getQueueId() {
        return this.queueId;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final String getDeviceType() {
        return this.deviceType;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final float getProgressInMs() {
        return this.progressInMs;
    }

    public final String getTitle() {
        return this.title;
    }
}
