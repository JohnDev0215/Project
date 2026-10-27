package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.temporal.Temporal;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceCurrentPlaybackState {
    private final ImageMetadata imageMetadata;
    private final Double progressInMs;
    private final Temporal.Timestamp progressTimestamp;
    private final String referenceId;
    private final String subTitle;
    private final String title;
    private final QueueEntityIdTypeEnum type;
    private final String typeEnumValue;

    public interface BuildStep {
        DeviceCurrentPlaybackState build();

        BuildStep imageMetadata(ImageMetadata imageMetadata);

        BuildStep subTitle(String str);

        BuildStep title(String str);

        BuildStep typeEnumValue(String str);
    }

    public interface ProgressInMsStep {
        ProgressTimestampStep progressInMs(Double d);
    }

    public interface ProgressTimestampStep {
        BuildStep progressTimestamp(Temporal.Timestamp timestamp);
    }

    public interface ReferenceIdStep {
        TypeStep referenceId(String str);
    }

    public interface TypeStep {
        ProgressInMsStep type(QueueEntityIdTypeEnum queueEntityIdTypeEnum);
    }

    public String getReferenceId() {
        return this.referenceId;
    }

    public QueueEntityIdTypeEnum getType() {
        return this.type;
    }

    public String getTypeEnumValue() {
        return this.typeEnumValue;
    }

    public Double getProgressInMs() {
        return this.progressInMs;
    }

    public Temporal.Timestamp getProgressTimestamp() {
        return this.progressTimestamp;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public ImageMetadata getImageMetadata() {
        return this.imageMetadata;
    }

    private DeviceCurrentPlaybackState(String str, QueueEntityIdTypeEnum queueEntityIdTypeEnum, String str2, Double d, Temporal.Timestamp timestamp, String str3, String str4, ImageMetadata imageMetadata) {
        this.referenceId = str;
        this.type = queueEntityIdTypeEnum;
        this.typeEnumValue = str2;
        this.progressInMs = d;
        this.progressTimestamp = timestamp;
        this.title = str3;
        this.subTitle = str4;
        this.imageMetadata = imageMetadata;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DeviceCurrentPlaybackState.class != obj.getClass()) {
            return false;
        }
        DeviceCurrentPlaybackState deviceCurrentPlaybackState = (DeviceCurrentPlaybackState) obj;
        return ObjectsCompat.equals(getReferenceId(), deviceCurrentPlaybackState.getReferenceId()) && ObjectsCompat.equals(getType(), deviceCurrentPlaybackState.getType()) && ObjectsCompat.equals(getTypeEnumValue(), deviceCurrentPlaybackState.getTypeEnumValue()) && ObjectsCompat.equals(getProgressInMs(), deviceCurrentPlaybackState.getProgressInMs()) && ObjectsCompat.equals(getProgressTimestamp(), deviceCurrentPlaybackState.getProgressTimestamp()) && ObjectsCompat.equals(getTitle(), deviceCurrentPlaybackState.getTitle()) && ObjectsCompat.equals(getSubTitle(), deviceCurrentPlaybackState.getSubTitle()) && ObjectsCompat.equals(getImageMetadata(), deviceCurrentPlaybackState.getImageMetadata());
    }

    public int hashCode() {
        return (getReferenceId() + getType() + getTypeEnumValue() + getProgressInMs() + getProgressTimestamp() + getTitle() + getSubTitle() + getImageMetadata()).hashCode();
    }

    public static ReferenceIdStep builder() {
        return new Builder();
    }

    public static class Builder implements ReferenceIdStep, TypeStep, ProgressInMsStep, ProgressTimestampStep, BuildStep {
        private ImageMetadata imageMetadata;
        private Double progressInMs;
        private Temporal.Timestamp progressTimestamp;
        private String referenceId;
        private String subTitle;
        private String title;
        private QueueEntityIdTypeEnum type;
        private String typeEnumValue;

        @Override // com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.BuildStep
        public DeviceCurrentPlaybackState build() {
            return new DeviceCurrentPlaybackState(this.referenceId, this.type, this.typeEnumValue, this.progressInMs, this.progressTimestamp, this.title, this.subTitle, this.imageMetadata);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.ReferenceIdStep
        public TypeStep referenceId(String str) {
            Objects.requireNonNull(str);
            this.referenceId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.TypeStep
        public ProgressInMsStep type(QueueEntityIdTypeEnum queueEntityIdTypeEnum) {
            Objects.requireNonNull(queueEntityIdTypeEnum);
            this.type = queueEntityIdTypeEnum;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.ProgressInMsStep
        public ProgressTimestampStep progressInMs(Double d) {
            Objects.requireNonNull(d);
            this.progressInMs = d;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.ProgressTimestampStep
        public BuildStep progressTimestamp(Temporal.Timestamp timestamp) {
            Objects.requireNonNull(timestamp);
            this.progressTimestamp = timestamp;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.BuildStep
        public BuildStep typeEnumValue(String str) {
            this.typeEnumValue = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.BuildStep
        public BuildStep title(String str) {
            this.title = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.BuildStep
        public BuildStep subTitle(String str) {
            this.subTitle = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.BuildStep
        public BuildStep imageMetadata(ImageMetadata imageMetadata) {
            this.imageMetadata = imageMetadata;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.ReferenceIdStep
        public CopyOfBuilder referenceId(String str) {
            return (CopyOfBuilder) super.referenceId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.TypeStep
        public CopyOfBuilder type(QueueEntityIdTypeEnum queueEntityIdTypeEnum) {
            return (CopyOfBuilder) super.type(queueEntityIdTypeEnum);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.ProgressInMsStep
        public CopyOfBuilder progressInMs(Double d) {
            return (CopyOfBuilder) super.progressInMs(d);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.ProgressTimestampStep
        public CopyOfBuilder progressTimestamp(Temporal.Timestamp timestamp) {
            return (CopyOfBuilder) super.progressTimestamp(timestamp);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.BuildStep
        public CopyOfBuilder typeEnumValue(String str) {
            return (CopyOfBuilder) super.typeEnumValue(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.BuildStep
        public CopyOfBuilder title(String str) {
            return (CopyOfBuilder) super.title(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.BuildStep
        public CopyOfBuilder subTitle(String str) {
            return (CopyOfBuilder) super.subTitle(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.Builder, com.amazon.mp3.amplifyqueue.model.DeviceCurrentPlaybackState.BuildStep
        public CopyOfBuilder imageMetadata(ImageMetadata imageMetadata) {
            return (CopyOfBuilder) super.imageMetadata(imageMetadata);
        }
    }
}
