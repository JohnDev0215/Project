package com.google.android.exoplayer2;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.ColorInfo;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public final class Format implements Parcelable {
    public static final Parcelable.Creator<Format> CREATOR = new Parcelable.Creator<Format>() { // from class: com.google.android.exoplayer2.Format.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Format createFromParcel(Parcel parcel) {
            return new Format(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Format[] newArray(int i) {
            return new Format[i];
        }
    };
    public final int accessibilityChannel;
    public final int bitrate;
    public final int channelCount;
    public final String codecs;
    public final ColorInfo colorInfo;
    public final String containerMimeType;
    public final DrmInitData drmInitData;
    public final int encoderDelay;
    public final int encoderPadding;
    public final float frameRate;
    private int hashCode;
    public final int height;
    public final String id;
    public final List<byte[]> initializationData;
    public final boolean isWideAspectRatio;
    public final String language;
    public final int maxInputSize;
    public final Metadata metadata;
    public final int pcmEncoding;
    public final float pixelWidthHeightRatio;
    public final byte[] projectionData;
    public final int rotationDegrees;
    public final String sampleMimeType;
    public final int sampleRate;
    public final int selectionFlags;
    public final int stereoMode;
    public final long subsampleOffsetUs;
    public final int width;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static Format createVideoContainerFormat(String str, String str2, String str3, String str4, int i, int i2, int i3, float f, List<byte[]> list, int i4) {
        return new Format(str, str2, str3, str4, i, -1, i2, i3, f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, i4, null, -1, Long.MAX_VALUE, list, null, null);
    }

    public static Format createVideoSampleFormat(String str, String str2, String str3, int i, int i2, int i3, int i4, float f, List<byte[]> list, int i5, float f2, DrmInitData drmInitData) {
        return createVideoSampleFormat(str, str2, str3, i, i2, i3, i4, f, list, i5, f2, null, -1, null, drmInitData);
    }

    public static Format createVideoSampleFormat(String str, String str2, String str3, int i, int i2, int i3, int i4, float f, List<byte[]> list, int i5, float f2, byte[] bArr, int i6, ColorInfo colorInfo, DrmInitData drmInitData) {
        return new Format(str, null, str2, str3, i, i2, i3, i4, f, i5, f2, bArr, i6, colorInfo, -1, -1, -1, -1, -1, 0, null, -1, Long.MAX_VALUE, list, drmInitData, null);
    }

    public static Format createAudioContainerFormat(String str, String str2, String str3, String str4, int i, int i2, int i3, List<byte[]> list, int i4, String str5) {
        return new Format(str, str2, str3, str4, i, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, i2, i3, -1, -1, -1, i4, str5, -1, Long.MAX_VALUE, list, null, null);
    }

    public static Format createAudioSampleFormat(String str, String str2, String str3, int i, int i2, int i3, int i4, List<byte[]> list, DrmInitData drmInitData, int i5, String str4) {
        return createAudioSampleFormat(str, str2, str3, i, i2, i3, i4, -1, list, drmInitData, i5, str4);
    }

    public static Format createAudioSampleFormat(String str, String str2, String str3, int i, int i2, int i3, int i4, int i5, List<byte[]> list, DrmInitData drmInitData, int i6, String str4) {
        return createAudioSampleFormat(str, str2, str3, i, i2, i3, i4, i5, -1, -1, list, drmInitData, i6, str4, null);
    }

    public static Format createAudioSampleFormat(String str, String str2, String str3, int i, int i2, int i3, int i4, int i5, int i6, int i7, List<byte[]> list, DrmInitData drmInitData, int i8, String str4, Metadata metadata) {
        return new Format(str, null, str2, str3, i, i2, -1, -1, -1.0f, -1, -1.0f, null, -1, null, i3, i4, i5, i6, i7, i8, str4, -1, Long.MAX_VALUE, list, drmInitData, metadata);
    }

    public static Format createTextContainerFormat(String str, String str2, String str3, String str4, int i, int i2, String str5) {
        return createTextContainerFormat(str, str2, str3, str4, i, i2, str5, -1);
    }

    public static Format createTextContainerFormat(String str, String str2, String str3, String str4, int i, int i2, String str5, int i3) {
        return new Format(str, str2, str3, str4, i, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, i2, str5, i3, Long.MAX_VALUE, null, null, null);
    }

    public static Format createTextSampleFormat(String str, String str2, int i, String str3) {
        return createTextSampleFormat(str, str2, i, str3, null);
    }

    public static Format createTextSampleFormat(String str, String str2, int i, String str3, DrmInitData drmInitData) {
        return createTextSampleFormat(str, str2, null, -1, i, str3, -1, drmInitData, Long.MAX_VALUE, Collections.emptyList());
    }

    public static Format createTextSampleFormat(String str, String str2, String str3, int i, int i2, String str4, int i3, DrmInitData drmInitData) {
        return createTextSampleFormat(str, str2, str3, i, i2, str4, i3, drmInitData, Long.MAX_VALUE, Collections.emptyList());
    }

    public static Format createTextSampleFormat(String str, String str2, String str3, int i, int i2, String str4, int i3, DrmInitData drmInitData, boolean z) {
        return new Format(str, null, str2, str3, i, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, i2, str4, i3, Long.MAX_VALUE, null, drmInitData, null, z);
    }

    public static Format createTextSampleFormat(String str, String str2, String str3, int i, int i2, String str4, DrmInitData drmInitData, long j) {
        return createTextSampleFormat(str, str2, str3, i, i2, str4, -1, drmInitData, j, Collections.emptyList());
    }

    public static Format createTextSampleFormat(String str, String str2, String str3, int i, int i2, String str4, int i3, DrmInitData drmInitData, long j, List<byte[]> list) {
        return new Format(str, null, str2, str3, i, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, i2, str4, i3, j, list, drmInitData, null);
    }

    public static Format createImageSampleFormat(String str, String str2, String str3, int i, List<byte[]> list, String str4, DrmInitData drmInitData) {
        return new Format(str, null, str2, str3, i, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, str4, -1, Long.MAX_VALUE, list, drmInitData, null);
    }

    public static Format createContainerFormat(String str, String str2, String str3, String str4, int i, int i2, String str5) {
        return new Format(str, str2, str3, str4, i, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, i2, str5, -1, Long.MAX_VALUE, null, null, null);
    }

    public static Format createSampleFormat(String str, String str2, long j) {
        return new Format(str, null, str2, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, null, -1, j, null, null, null);
    }

    public static Format createSampleFormat(String str, String str2, String str3, int i, DrmInitData drmInitData) {
        return new Format(str, null, str2, str3, i, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, null, -1, Long.MAX_VALUE, null, drmInitData, null);
    }

    Format(String str, String str2, String str3, String str4, int i, int i2, int i3, int i4, float f, int i5, float f2, byte[] bArr, int i6, ColorInfo colorInfo, int i7, int i8, int i9, int i10, int i11, int i12, String str5, int i13, long j, List<byte[]> list, DrmInitData drmInitData, Metadata metadata) {
        this(str, str2, str3, str4, i, i2, i3, i4, f, i5, f2, bArr, i6, colorInfo, i7, i8, i9, i10, i11, i12, str5, i13, j, list, drmInitData, metadata, true);
    }

    Format(String str, String str2, String str3, String str4, int i, int i2, int i3, int i4, float f, int i5, float f2, byte[] bArr, int i6, ColorInfo colorInfo, int i7, int i8, int i9, int i10, int i11, int i12, String str5, int i13, long j, List<byte[]> list, DrmInitData drmInitData, Metadata metadata, boolean z) {
        this.id = str;
        this.containerMimeType = str2;
        this.sampleMimeType = str3;
        this.codecs = str4;
        this.bitrate = i;
        this.maxInputSize = i2;
        this.width = i3;
        this.height = i4;
        this.frameRate = f;
        this.rotationDegrees = i5;
        this.pixelWidthHeightRatio = f2;
        this.projectionData = bArr;
        this.stereoMode = i6;
        this.colorInfo = colorInfo;
        this.channelCount = i7;
        this.sampleRate = i8;
        this.pcmEncoding = i9;
        this.encoderDelay = i10;
        this.encoderPadding = i11;
        this.selectionFlags = i12;
        this.language = str5;
        this.accessibilityChannel = i13;
        this.subsampleOffsetUs = j;
        this.initializationData = list == null ? Collections.emptyList() : list;
        this.drmInitData = drmInitData;
        this.metadata = metadata;
        this.isWideAspectRatio = z;
    }

    Format(Parcel parcel) {
        this.id = parcel.readString();
        this.containerMimeType = parcel.readString();
        this.sampleMimeType = parcel.readString();
        this.codecs = parcel.readString();
        this.bitrate = parcel.readInt();
        this.maxInputSize = parcel.readInt();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.frameRate = parcel.readFloat();
        this.rotationDegrees = parcel.readInt();
        this.pixelWidthHeightRatio = parcel.readFloat();
        this.projectionData = parcel.readInt() != 0 ? parcel.createByteArray() : null;
        this.stereoMode = parcel.readInt();
        this.colorInfo = (ColorInfo) parcel.readParcelable(ColorInfo.class.getClassLoader());
        this.channelCount = parcel.readInt();
        this.sampleRate = parcel.readInt();
        this.pcmEncoding = parcel.readInt();
        this.encoderDelay = parcel.readInt();
        this.encoderPadding = parcel.readInt();
        this.selectionFlags = parcel.readInt();
        this.language = parcel.readString();
        this.accessibilityChannel = parcel.readInt();
        this.subsampleOffsetUs = parcel.readLong();
        int i = parcel.readInt();
        this.initializationData = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            this.initializationData.add(parcel.createByteArray());
        }
        this.drmInitData = (DrmInitData) parcel.readParcelable(DrmInitData.class.getClassLoader());
        this.metadata = (Metadata) parcel.readParcelable(Metadata.class.getClassLoader());
        this.isWideAspectRatio = parcel.readInt() == 1;
    }

    public Format copyWithMaxInputSize(int i) {
        return new Format(this.id, this.containerMimeType, this.sampleMimeType, this.codecs, this.bitrate, i, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.selectionFlags, this.language, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, this.drmInitData, this.metadata);
    }

    public Format copyWithSubsampleOffsetUs(long j) {
        return new Format(this.id, this.containerMimeType, this.sampleMimeType, this.codecs, this.bitrate, this.maxInputSize, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.selectionFlags, this.language, this.accessibilityChannel, j, this.initializationData, this.drmInitData, this.metadata);
    }

    public Format copyWithContainerInfo(String str, String str2, int i, int i2, int i3, int i4, String str3) {
        return new Format(str, this.containerMimeType, this.sampleMimeType, str2, i, this.maxInputSize, i2, i3, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, i4, str3, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, this.drmInitData, this.metadata);
    }

    public Format copyWithManifestFormatInfo(Format format) {
        if (this == format) {
            return this;
        }
        String str = format.id;
        String str2 = this.codecs;
        if (str2 == null) {
            str2 = format.codecs;
        }
        String str3 = str2;
        int i = this.bitrate;
        if (i == -1) {
            i = format.bitrate;
        }
        int i2 = i;
        float f = this.frameRate;
        if (f == -1.0f) {
            f = format.frameRate;
        }
        float f2 = f;
        int i3 = this.selectionFlags | format.selectionFlags;
        String str4 = this.language;
        if (str4 == null) {
            str4 = format.language;
        }
        String str5 = str4;
        DrmInitData drmInitData = format.drmInitData;
        if (drmInitData == null) {
            drmInitData = this.drmInitData;
        }
        return new Format(str, this.containerMimeType, this.sampleMimeType, str3, i2, this.maxInputSize, this.width, this.height, f2, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, i3, str5, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, drmInitData, this.metadata);
    }

    public Format copyWithGaplessInfo(int i, int i2) {
        return new Format(this.id, this.containerMimeType, this.sampleMimeType, this.codecs, this.bitrate, this.maxInputSize, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, i, i2, this.selectionFlags, this.language, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, this.drmInitData, this.metadata);
    }

    public Format copyWithDrmInitData(DrmInitData drmInitData) {
        return new Format(this.id, this.containerMimeType, this.sampleMimeType, this.codecs, this.bitrate, this.maxInputSize, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.selectionFlags, this.language, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, drmInitData, this.metadata);
    }

    public Format copyWithMetadata(Metadata metadata) {
        return new Format(this.id, this.containerMimeType, this.sampleMimeType, this.codecs, this.bitrate, this.maxInputSize, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.selectionFlags, this.language, this.accessibilityChannel, this.subsampleOffsetUs, this.initializationData, this.drmInitData, metadata);
    }

    public int getPixelCount() {
        int i;
        int i2 = this.width;
        if (i2 == -1 || (i = this.height) == -1) {
            return -1;
        }
        return i2 * i;
    }

    @SuppressLint({"InlinedApi"})
    @TargetApi(16)
    public final MediaFormat getFrameworkMediaFormatV16() {
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", this.sampleMimeType);
        maybeSetStringV16(mediaFormat, "language", this.language);
        maybeSetIntegerV16(mediaFormat, "max-input-size", this.maxInputSize);
        maybeSetIntegerV16(mediaFormat, "width", this.width);
        maybeSetIntegerV16(mediaFormat, "height", this.height);
        maybeSetFloatV16(mediaFormat, "frame-rate", this.frameRate);
        maybeSetIntegerV16(mediaFormat, "rotation-degrees", this.rotationDegrees);
        maybeSetIntegerV16(mediaFormat, "channel-count", this.channelCount);
        maybeSetIntegerV16(mediaFormat, "sample-rate", this.sampleRate);
        maybeSetIntegerV16(mediaFormat, "encoder-delay", this.encoderDelay);
        maybeSetIntegerV16(mediaFormat, "encoder-padding", this.encoderPadding);
        for (int i = 0; i < this.initializationData.size(); i++) {
            mediaFormat.setByteBuffer("csd-" + i, ByteBuffer.wrap(this.initializationData.get(i)));
        }
        maybeSetColorInfoV24(mediaFormat, this.colorInfo);
        return mediaFormat;
    }

    public String toString() {
        return "Format(" + this.id + ", " + this.containerMimeType + ", " + this.sampleMimeType + ", " + this.bitrate + ", " + this.language + ", [" + this.width + ", " + this.height + ", " + this.frameRate + "], [" + this.channelCount + ", " + this.sampleRate + "])";
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            String str = this.id;
            int iHashCode = (527 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.containerMimeType;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.sampleMimeType;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.codecs;
            int iHashCode4 = (((((((((((iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31) + this.bitrate) * 31) + this.width) * 31) + this.height) * 31) + this.channelCount) * 31) + this.sampleRate) * 31;
            String str5 = this.language;
            int iHashCode5 = (((iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31) + this.accessibilityChannel) * 31;
            DrmInitData drmInitData = this.drmInitData;
            int iHashCode6 = (iHashCode5 + (drmInitData == null ? 0 : drmInitData.hashCode())) * 31;
            Metadata metadata = this.metadata;
            this.hashCode = iHashCode6 + (metadata != null ? metadata.hashCode() : 0);
        }
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Format.class == obj.getClass()) {
            Format format = (Format) obj;
            if (this.bitrate == format.bitrate && this.maxInputSize == format.maxInputSize && this.width == format.width && this.height == format.height && this.frameRate == format.frameRate && this.rotationDegrees == format.rotationDegrees && this.pixelWidthHeightRatio == format.pixelWidthHeightRatio && this.stereoMode == format.stereoMode && this.channelCount == format.channelCount && this.sampleRate == format.sampleRate && this.pcmEncoding == format.pcmEncoding && this.encoderDelay == format.encoderDelay && this.encoderPadding == format.encoderPadding && this.subsampleOffsetUs == format.subsampleOffsetUs && this.selectionFlags == format.selectionFlags && Util.areEqual(this.id, format.id) && Util.areEqual(this.language, format.language) && this.accessibilityChannel == format.accessibilityChannel && Util.areEqual(this.containerMimeType, format.containerMimeType) && Util.areEqual(this.sampleMimeType, format.sampleMimeType) && Util.areEqual(this.codecs, format.codecs) && Util.areEqual(this.drmInitData, format.drmInitData) && Util.areEqual(this.metadata, format.metadata) && Util.areEqual(this.colorInfo, format.colorInfo) && Arrays.equals(this.projectionData, format.projectionData) && this.initializationData.size() == format.initializationData.size() && this.isWideAspectRatio == format.isWideAspectRatio) {
                for (int i = 0; i < this.initializationData.size(); i++) {
                    if (!Arrays.equals(this.initializationData.get(i), format.initializationData.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @TargetApi(24)
    private static void maybeSetColorInfoV24(MediaFormat mediaFormat, ColorInfo colorInfo) {
        if (colorInfo == null) {
            return;
        }
        maybeSetIntegerV16(mediaFormat, "color-transfer", colorInfo.colorTransfer);
        maybeSetIntegerV16(mediaFormat, "color-standard", colorInfo.colorSpace);
        maybeSetIntegerV16(mediaFormat, "color-range", colorInfo.colorRange);
        maybeSetByteBufferV16(mediaFormat, "hdr-static-info", colorInfo.hdrStaticInfo);
    }

    @TargetApi(16)
    private static void maybeSetStringV16(MediaFormat mediaFormat, String str, String str2) {
        if (str2 != null) {
            mediaFormat.setString(str, str2);
        }
    }

    @TargetApi(16)
    private static void maybeSetIntegerV16(MediaFormat mediaFormat, String str, int i) {
        if (i != -1) {
            mediaFormat.setInteger(str, i);
        }
    }

    @TargetApi(16)
    private static void maybeSetFloatV16(MediaFormat mediaFormat, String str, float f) {
        if (f != -1.0f) {
            mediaFormat.setFloat(str, f);
        }
    }

    @TargetApi(16)
    private static void maybeSetByteBufferV16(MediaFormat mediaFormat, String str, byte[] bArr) {
        if (bArr != null) {
            mediaFormat.setByteBuffer(str, ByteBuffer.wrap(bArr));
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.containerMimeType);
        parcel.writeString(this.sampleMimeType);
        parcel.writeString(this.codecs);
        parcel.writeInt(this.bitrate);
        parcel.writeInt(this.maxInputSize);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeFloat(this.frameRate);
        parcel.writeInt(this.rotationDegrees);
        parcel.writeFloat(this.pixelWidthHeightRatio);
        parcel.writeInt(this.projectionData != null ? 1 : 0);
        byte[] bArr = this.projectionData;
        if (bArr != null) {
            parcel.writeByteArray(bArr);
        }
        parcel.writeInt(this.stereoMode);
        parcel.writeParcelable(this.colorInfo, i);
        parcel.writeInt(this.channelCount);
        parcel.writeInt(this.sampleRate);
        parcel.writeInt(this.pcmEncoding);
        parcel.writeInt(this.encoderDelay);
        parcel.writeInt(this.encoderPadding);
        parcel.writeInt(this.selectionFlags);
        parcel.writeString(this.language);
        parcel.writeInt(this.accessibilityChannel);
        parcel.writeLong(this.subsampleOffsetUs);
        int size = this.initializationData.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeByteArray(this.initializationData.get(i2));
        }
        parcel.writeParcelable(this.drmInitData, 0);
        parcel.writeParcelable(this.metadata, 0);
        parcel.writeInt(this.isWideAspectRatio ? 1 : 0);
    }
}
