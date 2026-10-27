package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.Indexes;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.storage.sqlite.SQLiteCommandFactory;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
@Indexes({@Index(fields = {"customerId", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE}, name = SQLiteCommandFactory.UNDEFINED), @Index(fields = {"customerId", "queueId"}, name = "byQueueId")})
@ModelConfig(pluralName = "DevicePlaybackStates")
public final class DevicePlaybackState implements Model {

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime createdAt;

    @ModelField(isRequired = true, targetType = "ID")
    private final String customerId;

    @ModelField(targetType = "DeviceCurrentPlaybackState")
    private final DeviceCurrentPlaybackState deviceCurrentPlaybackState;

    @ModelField(isRequired = true, targetType = "String")
    private final String deviceId;

    @ModelField(isRequired = true, targetType = "String")
    private final String deviceType;

    @ModelField(targetType = "String")
    private final String djMode;

    @ModelField(isRequired = true, targetType = "ID")
    private final String id;

    @ModelField(targetType = "String")
    private final String ipAddress;

    @ModelField(isRequired = true, targetType = "LoopModeEnum")
    private final LoopModeEnum loopMode;

    @ModelField(targetType = "String")
    private final String playbackContinuationMode;

    @ModelField(isRequired = true, targetType = "DevicePlaybackStateEnum")
    private final DevicePlaybackStateEnum playbackState;

    @ModelField(isRequired = true, targetType = "ID")
    private final String queueId;

    @ModelField(isRequired = true, targetType = "String")
    private final String sequenceName;

    @ModelField(isRequired = true, targetType = "Int")
    private final Integer sequenceVersion;

    @ModelField(isRequired = true, targetType = "ShuffleModeEnum")
    private final ShuffleModeEnum shuffleMode;

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime updatedAt;
    public static final QueryField ID = QueryField.field("DevicePlaybackState", "id");
    public static final QueryField DEVICE_ID = QueryField.field("DevicePlaybackState", Splash.PARAMS_DEVICE_ID);
    public static final QueryField DEVICE_TYPE = QueryField.field("DevicePlaybackState", Splash.PARAMS_DEVICE_TYPE);
    public static final QueryField QUEUE_ID = QueryField.field("DevicePlaybackState", "queueId");
    public static final QueryField CUSTOMER_ID = QueryField.field("DevicePlaybackState", "customerId");
    public static final QueryField SEQUENCE_NAME = QueryField.field("DevicePlaybackState", "sequenceName");
    public static final QueryField SEQUENCE_VERSION = QueryField.field("DevicePlaybackState", "sequenceVersion");
    public static final QueryField DEVICE_CURRENT_PLAYBACK_STATE = QueryField.field("DevicePlaybackState", "deviceCurrentPlaybackState");
    public static final QueryField LOOP_MODE = QueryField.field("DevicePlaybackState", "loopMode");
    public static final QueryField SHUFFLE_MODE = QueryField.field("DevicePlaybackState", "shuffleMode");
    public static final QueryField DJ_MODE = QueryField.field("DevicePlaybackState", "djMode");
    public static final QueryField PLAYBACK_CONTINUATION_MODE = QueryField.field("DevicePlaybackState", "playbackContinuationMode");
    public static final QueryField PLAYBACK_STATE = QueryField.field("DevicePlaybackState", TetheredMessageKey.playbackState);
    public static final QueryField IP_ADDRESS = QueryField.field("DevicePlaybackState", "ipAddress");
    public static final QueryField CREATED_AT = QueryField.field("DevicePlaybackState", "createdAt");
    public static final QueryField UPDATED_AT = QueryField.field("DevicePlaybackState", "updatedAt");

    public interface BuildStep {
        DevicePlaybackState build();

        BuildStep deviceCurrentPlaybackState(DeviceCurrentPlaybackState deviceCurrentPlaybackState);

        BuildStep djMode(String str);

        BuildStep id(String str);

        BuildStep ipAddress(String str);

        BuildStep playbackContinuationMode(String str);
    }

    public interface CreatedAtStep {
        UpdatedAtStep createdAt(Temporal.DateTime dateTime);
    }

    public interface CustomerIdStep {
        SequenceNameStep customerId(String str);
    }

    public interface DeviceIdStep {
        DeviceTypeStep deviceId(String str);
    }

    public interface DeviceTypeStep {
        QueueIdStep deviceType(String str);
    }

    public interface LoopModeStep {
        ShuffleModeStep loopMode(LoopModeEnum loopModeEnum);
    }

    public interface PlaybackStateStep {
        CreatedAtStep playbackState(DevicePlaybackStateEnum devicePlaybackStateEnum);
    }

    public interface QueueIdStep {
        CustomerIdStep queueId(String str);
    }

    public interface SequenceNameStep {
        SequenceVersionStep sequenceName(String str);
    }

    public interface SequenceVersionStep {
        LoopModeStep sequenceVersion(Integer num);
    }

    public interface ShuffleModeStep {
        PlaybackStateStep shuffleMode(ShuffleModeEnum shuffleModeEnum);
    }

    public interface UpdatedAtStep {
        BuildStep updatedAt(Temporal.DateTime dateTime);
    }

    public String getId() {
        return this.id;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getQueueId() {
        return this.queueId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getSequenceName() {
        return this.sequenceName;
    }

    public Integer getSequenceVersion() {
        return this.sequenceVersion;
    }

    public DeviceCurrentPlaybackState getDeviceCurrentPlaybackState() {
        return this.deviceCurrentPlaybackState;
    }

    public LoopModeEnum getLoopMode() {
        return this.loopMode;
    }

    public ShuffleModeEnum getShuffleMode() {
        return this.shuffleMode;
    }

    public String getDjMode() {
        return this.djMode;
    }

    public String getPlaybackContinuationMode() {
        return this.playbackContinuationMode;
    }

    public DevicePlaybackStateEnum getPlaybackState() {
        return this.playbackState;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public Temporal.DateTime getCreatedAt() {
        return this.createdAt;
    }

    public Temporal.DateTime getUpdatedAt() {
        return this.updatedAt;
    }

    private DevicePlaybackState(String str, String str2, String str3, String str4, String str5, String str6, Integer num, DeviceCurrentPlaybackState deviceCurrentPlaybackState, LoopModeEnum loopModeEnum, ShuffleModeEnum shuffleModeEnum, String str7, String str8, DevicePlaybackStateEnum devicePlaybackStateEnum, String str9, Temporal.DateTime dateTime, Temporal.DateTime dateTime2) {
        this.id = str;
        this.deviceId = str2;
        this.deviceType = str3;
        this.queueId = str4;
        this.customerId = str5;
        this.sequenceName = str6;
        this.sequenceVersion = num;
        this.deviceCurrentPlaybackState = deviceCurrentPlaybackState;
        this.loopMode = loopModeEnum;
        this.shuffleMode = shuffleModeEnum;
        this.djMode = str7;
        this.playbackContinuationMode = str8;
        this.playbackState = devicePlaybackStateEnum;
        this.ipAddress = str9;
        this.createdAt = dateTime;
        this.updatedAt = dateTime2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DevicePlaybackState.class != obj.getClass()) {
            return false;
        }
        DevicePlaybackState devicePlaybackState = (DevicePlaybackState) obj;
        return ObjectsCompat.equals(getId(), devicePlaybackState.getId()) && ObjectsCompat.equals(getDeviceId(), devicePlaybackState.getDeviceId()) && ObjectsCompat.equals(getDeviceType(), devicePlaybackState.getDeviceType()) && ObjectsCompat.equals(getQueueId(), devicePlaybackState.getQueueId()) && ObjectsCompat.equals(getCustomerId(), devicePlaybackState.getCustomerId()) && ObjectsCompat.equals(getSequenceName(), devicePlaybackState.getSequenceName()) && ObjectsCompat.equals(getSequenceVersion(), devicePlaybackState.getSequenceVersion()) && ObjectsCompat.equals(getDeviceCurrentPlaybackState(), devicePlaybackState.getDeviceCurrentPlaybackState()) && ObjectsCompat.equals(getLoopMode(), devicePlaybackState.getLoopMode()) && ObjectsCompat.equals(getShuffleMode(), devicePlaybackState.getShuffleMode()) && ObjectsCompat.equals(getDjMode(), devicePlaybackState.getDjMode()) && ObjectsCompat.equals(getPlaybackContinuationMode(), devicePlaybackState.getPlaybackContinuationMode()) && ObjectsCompat.equals(getPlaybackState(), devicePlaybackState.getPlaybackState()) && ObjectsCompat.equals(getIpAddress(), devicePlaybackState.getIpAddress()) && ObjectsCompat.equals(getCreatedAt(), devicePlaybackState.getCreatedAt()) && ObjectsCompat.equals(getUpdatedAt(), devicePlaybackState.getUpdatedAt());
    }

    public int hashCode() {
        return (getId() + getDeviceId() + getDeviceType() + getQueueId() + getCustomerId() + getSequenceName() + getSequenceVersion() + getDeviceCurrentPlaybackState() + getLoopMode() + getShuffleMode() + getDjMode() + getPlaybackContinuationMode() + getPlaybackState() + getIpAddress() + getCreatedAt() + getUpdatedAt()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DevicePlaybackState {");
        sb.append("id=" + String.valueOf(getId()) + ", ");
        sb.append("deviceId=" + String.valueOf(getDeviceId()) + ", ");
        sb.append("deviceType=" + String.valueOf(getDeviceType()) + ", ");
        sb.append("queueId=" + String.valueOf(getQueueId()) + ", ");
        sb.append("customerId=" + String.valueOf(getCustomerId()) + ", ");
        sb.append("sequenceName=" + String.valueOf(getSequenceName()) + ", ");
        sb.append("sequenceVersion=" + String.valueOf(getSequenceVersion()) + ", ");
        sb.append("deviceCurrentPlaybackState=" + String.valueOf(getDeviceCurrentPlaybackState()) + ", ");
        sb.append("loopMode=" + String.valueOf(getLoopMode()) + ", ");
        sb.append("shuffleMode=" + String.valueOf(getShuffleMode()) + ", ");
        sb.append("djMode=" + String.valueOf(getDjMode()) + ", ");
        sb.append("playbackContinuationMode=" + String.valueOf(getPlaybackContinuationMode()) + ", ");
        sb.append("playbackState=" + String.valueOf(getPlaybackState()) + ", ");
        sb.append("ipAddress=" + String.valueOf(getIpAddress()) + ", ");
        sb.append("createdAt=" + String.valueOf(getCreatedAt()) + ", ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("updatedAt=");
        sb2.append(String.valueOf(getUpdatedAt()));
        sb.append(sb2.toString());
        sb.append("}");
        return sb.toString();
    }

    public static DeviceIdStep builder() {
        return new Builder();
    }

    public CopyOfBuilder copyOfBuilder() {
        return new CopyOfBuilder(this.id, this.deviceId, this.deviceType, this.queueId, this.customerId, this.sequenceName, this.sequenceVersion, this.deviceCurrentPlaybackState, this.loopMode, this.shuffleMode, this.djMode, this.playbackContinuationMode, this.playbackState, this.ipAddress, this.createdAt, this.updatedAt);
    }

    public static class Builder implements DeviceIdStep, DeviceTypeStep, QueueIdStep, CustomerIdStep, SequenceNameStep, SequenceVersionStep, LoopModeStep, ShuffleModeStep, PlaybackStateStep, CreatedAtStep, UpdatedAtStep, BuildStep {
        private Temporal.DateTime createdAt;
        private String customerId;
        private DeviceCurrentPlaybackState deviceCurrentPlaybackState;
        private String deviceId;
        private String deviceType;
        private String djMode;
        private String id;
        private String ipAddress;
        private LoopModeEnum loopMode;
        private String playbackContinuationMode;
        private DevicePlaybackStateEnum playbackState;
        private String queueId;
        private String sequenceName;
        private Integer sequenceVersion;
        private ShuffleModeEnum shuffleMode;
        private Temporal.DateTime updatedAt;

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.BuildStep
        public DevicePlaybackState build() {
            String string = this.id;
            if (string == null) {
                string = UUID.randomUUID().toString();
            }
            return new DevicePlaybackState(string, this.deviceId, this.deviceType, this.queueId, this.customerId, this.sequenceName, this.sequenceVersion, this.deviceCurrentPlaybackState, this.loopMode, this.shuffleMode, this.djMode, this.playbackContinuationMode, this.playbackState, this.ipAddress, this.createdAt, this.updatedAt);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.DeviceIdStep
        public DeviceTypeStep deviceId(String str) {
            Objects.requireNonNull(str);
            this.deviceId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.DeviceTypeStep
        public QueueIdStep deviceType(String str) {
            Objects.requireNonNull(str);
            this.deviceType = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.QueueIdStep
        public CustomerIdStep queueId(String str) {
            Objects.requireNonNull(str);
            this.queueId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.CustomerIdStep
        public SequenceNameStep customerId(String str) {
            Objects.requireNonNull(str);
            this.customerId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.SequenceNameStep
        public SequenceVersionStep sequenceName(String str) {
            Objects.requireNonNull(str);
            this.sequenceName = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.SequenceVersionStep
        public LoopModeStep sequenceVersion(Integer num) {
            Objects.requireNonNull(num);
            this.sequenceVersion = num;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.LoopModeStep
        public ShuffleModeStep loopMode(LoopModeEnum loopModeEnum) {
            Objects.requireNonNull(loopModeEnum);
            this.loopMode = loopModeEnum;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.ShuffleModeStep
        public PlaybackStateStep shuffleMode(ShuffleModeEnum shuffleModeEnum) {
            Objects.requireNonNull(shuffleModeEnum);
            this.shuffleMode = shuffleModeEnum;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.PlaybackStateStep
        public CreatedAtStep playbackState(DevicePlaybackStateEnum devicePlaybackStateEnum) {
            Objects.requireNonNull(devicePlaybackStateEnum);
            this.playbackState = devicePlaybackStateEnum;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.CreatedAtStep
        public UpdatedAtStep createdAt(Temporal.DateTime dateTime) {
            Objects.requireNonNull(dateTime);
            this.createdAt = dateTime;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.UpdatedAtStep
        public BuildStep updatedAt(Temporal.DateTime dateTime) {
            Objects.requireNonNull(dateTime);
            this.updatedAt = dateTime;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.BuildStep
        public BuildStep deviceCurrentPlaybackState(DeviceCurrentPlaybackState deviceCurrentPlaybackState) {
            this.deviceCurrentPlaybackState = deviceCurrentPlaybackState;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.BuildStep
        public BuildStep djMode(String str) {
            this.djMode = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.BuildStep
        public BuildStep playbackContinuationMode(String str) {
            this.playbackContinuationMode = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.BuildStep
        public BuildStep ipAddress(String str) {
            this.ipAddress = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.BuildStep
        public BuildStep id(String str) {
            this.id = str;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        private CopyOfBuilder(String str, String str2, String str3, String str4, String str5, String str6, Integer num, DeviceCurrentPlaybackState deviceCurrentPlaybackState, LoopModeEnum loopModeEnum, ShuffleModeEnum shuffleModeEnum, String str7, String str8, DevicePlaybackStateEnum devicePlaybackStateEnum, String str9, Temporal.DateTime dateTime, Temporal.DateTime dateTime2) {
            super.id(str);
            super.deviceId(str2).deviceType(str3).queueId(str4).customerId(str5).sequenceName(str6).sequenceVersion(num).loopMode(loopModeEnum).shuffleMode(shuffleModeEnum).playbackState(devicePlaybackStateEnum).createdAt(dateTime).updatedAt(dateTime2).deviceCurrentPlaybackState(deviceCurrentPlaybackState).djMode(str7).playbackContinuationMode(str8).ipAddress(str9);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.DeviceIdStep
        public CopyOfBuilder deviceId(String str) {
            return (CopyOfBuilder) super.deviceId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.DeviceTypeStep
        public CopyOfBuilder deviceType(String str) {
            return (CopyOfBuilder) super.deviceType(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.QueueIdStep
        public CopyOfBuilder queueId(String str) {
            return (CopyOfBuilder) super.queueId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.CustomerIdStep
        public CopyOfBuilder customerId(String str) {
            return (CopyOfBuilder) super.customerId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.SequenceNameStep
        public CopyOfBuilder sequenceName(String str) {
            return (CopyOfBuilder) super.sequenceName(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.SequenceVersionStep
        public CopyOfBuilder sequenceVersion(Integer num) {
            return (CopyOfBuilder) super.sequenceVersion(num);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.LoopModeStep
        public CopyOfBuilder loopMode(LoopModeEnum loopModeEnum) {
            return (CopyOfBuilder) super.loopMode(loopModeEnum);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.ShuffleModeStep
        public CopyOfBuilder shuffleMode(ShuffleModeEnum shuffleModeEnum) {
            return (CopyOfBuilder) super.shuffleMode(shuffleModeEnum);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.PlaybackStateStep
        public CopyOfBuilder playbackState(DevicePlaybackStateEnum devicePlaybackStateEnum) {
            return (CopyOfBuilder) super.playbackState(devicePlaybackStateEnum);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.CreatedAtStep
        public CopyOfBuilder createdAt(Temporal.DateTime dateTime) {
            return (CopyOfBuilder) super.createdAt(dateTime);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.UpdatedAtStep
        public CopyOfBuilder updatedAt(Temporal.DateTime dateTime) {
            return (CopyOfBuilder) super.updatedAt(dateTime);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.BuildStep
        public CopyOfBuilder deviceCurrentPlaybackState(DeviceCurrentPlaybackState deviceCurrentPlaybackState) {
            return (CopyOfBuilder) super.deviceCurrentPlaybackState(deviceCurrentPlaybackState);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.BuildStep
        public CopyOfBuilder djMode(String str) {
            return (CopyOfBuilder) super.djMode(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.BuildStep
        public CopyOfBuilder playbackContinuationMode(String str) {
            return (CopyOfBuilder) super.playbackContinuationMode(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DevicePlaybackState.BuildStep
        public CopyOfBuilder ipAddress(String str) {
            return (CopyOfBuilder) super.ipAddress(str);
        }
    }
}
