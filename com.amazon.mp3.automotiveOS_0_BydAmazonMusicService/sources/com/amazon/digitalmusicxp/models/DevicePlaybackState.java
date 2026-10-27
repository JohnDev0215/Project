package com.amazon.digitalmusicxp.models;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.DevicePlaybackStateEnum;
import com.amazon.digitalmusicxp.enums.LoopModeEnum;
import com.amazon.digitalmusicxp.enums.ShuffleModeEnum;
import com.amazon.digitalmusicxp.types.DeviceCurrentPlaybackState;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DevicePlaybackState.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\"\b\u0086\b\u0018\u00002\u00020\u0001B\u009b\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0018\u001a\u00020\u0016¢\u0006\u0004\b=\u0010>J³\u0001\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u0016HÆ\u0001J\t\u0010\u001a\u001a\u00020\u0002HÖ\u0001J\t\u0010\u001b\u001a\u00020\tHÖ\u0001J\u0013\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010!R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001f\u001a\u0004\b\"\u0010!R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001f\u001a\u0004\b#\u0010!R\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b$\u0010!R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001f\u001a\u0004\b%\u0010!R\u0017\u0010\b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\u001f\u001a\u0004\b&\u0010!R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010'\u001a\u0004\b(\u0010)R\u0019\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010*\u001a\u0004\b+\u0010,R\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010-\u001a\u0004\b.\u0010/R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u00100\u001a\u0004\b1\u00102R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u001f\u001a\u0004\b3\u0010!R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u001f\u001a\u0004\b4\u0010!R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u00105\u001a\u0004\b6\u00107R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u001f\u001a\u0004\b8\u0010!R\u0017\u0010\u0017\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u00109\u001a\u0004\b:\u0010;R\u0017\u0010\u0018\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b\u0018\u00109\u001a\u0004\b<\u0010;¨\u0006?"}, d2 = {"Lcom/amazon/digitalmusicxp/models/DevicePlaybackState;", "", "", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE, "queueId", "customerId", "id", "sequenceName", "", "sequenceVersion", "Lcom/amazon/digitalmusicxp/types/DeviceCurrentPlaybackState;", "deviceCurrentPlaybackState", "Lcom/amazon/digitalmusicxp/enums/LoopModeEnum;", "loopMode", "Lcom/amazon/digitalmusicxp/enums/ShuffleModeEnum;", "shuffleMode", "djMode", "playbackContinuationMode", "Lcom/amazon/digitalmusicxp/enums/DevicePlaybackStateEnum;", TetheredMessageKey.playbackState, "ipAddress", "", "createdAt", "updatedAt", "copy", "toString", "hashCode", "other", "", "equals", "Ljava/lang/String;", "getDeviceId", "()Ljava/lang/String;", "getDeviceType", "getQueueId", "getCustomerId", "getId", "getSequenceName", "I", "getSequenceVersion", "()I", "Lcom/amazon/digitalmusicxp/types/DeviceCurrentPlaybackState;", "getDeviceCurrentPlaybackState", "()Lcom/amazon/digitalmusicxp/types/DeviceCurrentPlaybackState;", "Lcom/amazon/digitalmusicxp/enums/LoopModeEnum;", "getLoopMode", "()Lcom/amazon/digitalmusicxp/enums/LoopModeEnum;", "Lcom/amazon/digitalmusicxp/enums/ShuffleModeEnum;", "getShuffleMode", "()Lcom/amazon/digitalmusicxp/enums/ShuffleModeEnum;", "getDjMode", "getPlaybackContinuationMode", "Lcom/amazon/digitalmusicxp/enums/DevicePlaybackStateEnum;", "getPlaybackState", "()Lcom/amazon/digitalmusicxp/enums/DevicePlaybackStateEnum;", "getIpAddress", "J", "getCreatedAt", "()J", "getUpdatedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILcom/amazon/digitalmusicxp/types/DeviceCurrentPlaybackState;Lcom/amazon/digitalmusicxp/enums/LoopModeEnum;Lcom/amazon/digitalmusicxp/enums/ShuffleModeEnum;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/enums/DevicePlaybackStateEnum;Ljava/lang/String;JJ)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class DevicePlaybackState {
    private final long createdAt;
    private final String customerId;
    private final DeviceCurrentPlaybackState deviceCurrentPlaybackState;
    private final String deviceId;
    private final String deviceType;
    private final String djMode;
    private final String id;
    private final String ipAddress;
    private final LoopModeEnum loopMode;
    private final String playbackContinuationMode;
    private final DevicePlaybackStateEnum playbackState;
    private final String queueId;
    private final String sequenceName;
    private final int sequenceVersion;
    private final ShuffleModeEnum shuffleMode;
    private final long updatedAt;

    public final DevicePlaybackState copy(String deviceId, String deviceType, String queueId, String customerId, String id, String sequenceName, int sequenceVersion, DeviceCurrentPlaybackState deviceCurrentPlaybackState, LoopModeEnum loopMode, ShuffleModeEnum shuffleMode, String djMode, String playbackContinuationMode, DevicePlaybackStateEnum playbackState, String ipAddress, long createdAt, long updatedAt) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(sequenceName, "sequenceName");
        Intrinsics.checkNotNullParameter(loopMode, "loopMode");
        Intrinsics.checkNotNullParameter(shuffleMode, "shuffleMode");
        Intrinsics.checkNotNullParameter(playbackState, "playbackState");
        return new DevicePlaybackState(deviceId, deviceType, queueId, customerId, id, sequenceName, sequenceVersion, deviceCurrentPlaybackState, loopMode, shuffleMode, djMode, playbackContinuationMode, playbackState, ipAddress, createdAt, updatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DevicePlaybackState)) {
            return false;
        }
        DevicePlaybackState devicePlaybackState = (DevicePlaybackState) other;
        return Intrinsics.areEqual(this.deviceId, devicePlaybackState.deviceId) && Intrinsics.areEqual(this.deviceType, devicePlaybackState.deviceType) && Intrinsics.areEqual(this.queueId, devicePlaybackState.queueId) && Intrinsics.areEqual(this.customerId, devicePlaybackState.customerId) && Intrinsics.areEqual(this.id, devicePlaybackState.id) && Intrinsics.areEqual(this.sequenceName, devicePlaybackState.sequenceName) && this.sequenceVersion == devicePlaybackState.sequenceVersion && Intrinsics.areEqual(this.deviceCurrentPlaybackState, devicePlaybackState.deviceCurrentPlaybackState) && this.loopMode == devicePlaybackState.loopMode && this.shuffleMode == devicePlaybackState.shuffleMode && Intrinsics.areEqual(this.djMode, devicePlaybackState.djMode) && Intrinsics.areEqual(this.playbackContinuationMode, devicePlaybackState.playbackContinuationMode) && this.playbackState == devicePlaybackState.playbackState && Intrinsics.areEqual(this.ipAddress, devicePlaybackState.ipAddress) && this.createdAt == devicePlaybackState.createdAt && this.updatedAt == devicePlaybackState.updatedAt;
    }

    public int hashCode() {
        int iHashCode = ((((((this.deviceId.hashCode() * 31) + this.deviceType.hashCode()) * 31) + this.queueId.hashCode()) * 31) + this.customerId.hashCode()) * 31;
        String str = this.id;
        int iHashCode2 = (((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.sequenceName.hashCode()) * 31) + Integer.hashCode(this.sequenceVersion)) * 31;
        DeviceCurrentPlaybackState deviceCurrentPlaybackState = this.deviceCurrentPlaybackState;
        int iHashCode3 = (((((iHashCode2 + (deviceCurrentPlaybackState == null ? 0 : deviceCurrentPlaybackState.hashCode())) * 31) + this.loopMode.hashCode()) * 31) + this.shuffleMode.hashCode()) * 31;
        String str2 = this.djMode;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.playbackContinuationMode;
        int iHashCode5 = (((iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.playbackState.hashCode()) * 31;
        String str4 = this.ipAddress;
        return ((((iHashCode5 + (str4 != null ? str4.hashCode() : 0)) * 31) + Long.hashCode(this.createdAt)) * 31) + Long.hashCode(this.updatedAt);
    }

    public String toString() {
        return "DevicePlaybackState(deviceId=" + this.deviceId + ", deviceType=" + this.deviceType + ", queueId=" + this.queueId + ", customerId=" + this.customerId + ", id=" + this.id + ", sequenceName=" + this.sequenceName + ", sequenceVersion=" + this.sequenceVersion + ", deviceCurrentPlaybackState=" + this.deviceCurrentPlaybackState + ", loopMode=" + this.loopMode + ", shuffleMode=" + this.shuffleMode + ", djMode=" + this.djMode + ", playbackContinuationMode=" + this.playbackContinuationMode + ", playbackState=" + this.playbackState + ", ipAddress=" + this.ipAddress + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ')';
    }

    public DevicePlaybackState(String deviceId, String deviceType, String queueId, String customerId, String str, String sequenceName, int i, DeviceCurrentPlaybackState deviceCurrentPlaybackState, LoopModeEnum loopMode, ShuffleModeEnum shuffleMode, String str2, String str3, DevicePlaybackStateEnum playbackState, String str4, long j, long j2) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(sequenceName, "sequenceName");
        Intrinsics.checkNotNullParameter(loopMode, "loopMode");
        Intrinsics.checkNotNullParameter(shuffleMode, "shuffleMode");
        Intrinsics.checkNotNullParameter(playbackState, "playbackState");
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.queueId = queueId;
        this.customerId = customerId;
        this.id = str;
        this.sequenceName = sequenceName;
        this.sequenceVersion = i;
        this.deviceCurrentPlaybackState = deviceCurrentPlaybackState;
        this.loopMode = loopMode;
        this.shuffleMode = shuffleMode;
        this.djMode = str2;
        this.playbackContinuationMode = str3;
        this.playbackState = playbackState;
        this.ipAddress = str4;
        this.createdAt = j;
        this.updatedAt = j2;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ DevicePlaybackState(String str, String str2, String str3, String str4, String str5, String str6, int i, DeviceCurrentPlaybackState deviceCurrentPlaybackState, LoopModeEnum loopModeEnum, ShuffleModeEnum shuffleModeEnum, String str7, String str8, DevicePlaybackStateEnum devicePlaybackStateEnum, String str9, long j, long j2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i2 & 16) != 0 ? null : str5, str6, i, (i2 & 128) != 0 ? null : deviceCurrentPlaybackState, loopModeEnum, shuffleModeEnum, (i2 & 1024) != 0 ? null : str7, (i2 & 2048) != 0 ? null : str8, devicePlaybackStateEnum, (i2 & 8192) != 0 ? null : str9, j, j2);
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final String getDeviceType() {
        return this.deviceType;
    }

    public final String getQueueId() {
        return this.queueId;
    }

    public final String getCustomerId() {
        return this.customerId;
    }

    public final String getId() {
        return this.id;
    }

    public final String getSequenceName() {
        return this.sequenceName;
    }

    public final int getSequenceVersion() {
        return this.sequenceVersion;
    }

    public final DeviceCurrentPlaybackState getDeviceCurrentPlaybackState() {
        return this.deviceCurrentPlaybackState;
    }

    public final LoopModeEnum getLoopMode() {
        return this.loopMode;
    }

    public final ShuffleModeEnum getShuffleMode() {
        return this.shuffleMode;
    }

    public final String getDjMode() {
        return this.djMode;
    }

    public final String getPlaybackContinuationMode() {
        return this.playbackContinuationMode;
    }

    public final DevicePlaybackStateEnum getPlaybackState() {
        return this.playbackState;
    }

    public final String getIpAddress() {
        return this.ipAddress;
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final long getUpdatedAt() {
        return this.updatedAt;
    }
}
