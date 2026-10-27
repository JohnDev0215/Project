package com.visualon.OSMPUtils;

import android.os.Parcel;
import com.visualon.OSMPUtils.voOSType;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes7.dex */
public class voOSTrackInfoImpl implements voOSTrackInfo {
    private voOSAudioInfo AudioInfo;
    private int Bitrate;
    private int ChunkCounts;
    private int Codec;
    private long Duration;
    private char[] FourCC;
    private char[] HeadData;
    private int HeadSize;
    private int SelectInfo;
    private voOSSubtitleInfo SubtitleInfo;
    private int TrackID;
    private voOSType.VOOSMP_SOURCE_STREAMTYPE TrackType;
    private voOSVideoInfo VideoInfo;

    public voOSTrackInfoImpl(int i, int i2, char[] cArr, voOSType.VOOSMP_SOURCE_STREAMTYPE voosmp_source_streamtype, int i3, long j, voOSAudioInfo voosaudioinfo, voOSVideoInfo voosvideoinfo, int i4, int i5, int i6, char[] cArr2) {
        this.TrackID = i;
        this.SelectInfo = i2;
        this.FourCC = cArr;
        this.TrackType = voosmp_source_streamtype;
        this.Codec = i3;
        this.Duration = j;
        this.AudioInfo = voosaudioinfo;
        this.VideoInfo = voosvideoinfo;
        this.ChunkCounts = i4;
        this.Bitrate = i5;
        this.HeadSize = i6;
        this.HeadData = cArr2;
    }

    public voOSTrackInfoImpl() {
    }

    @Override // com.visualon.OSMPUtils.voOSTrackInfo
    public boolean parse(Parcel parcel) {
        byte[] bArr = new byte[8];
        this.TrackID = parcel.readInt();
        this.SelectInfo = parcel.readInt();
        try {
            parcel.readByteArray(bArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.FourCC = new String(bArr, 0, 8, "utf-8").toCharArray();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        this.TrackType = voOSType.VOOSMP_SOURCE_STREAMTYPE.valueOf(parcel.readInt());
        this.Codec = parcel.readInt();
        this.Duration = parcel.readInt();
        this.ChunkCounts = parcel.readInt();
        this.Bitrate = parcel.readInt();
        if (this.TrackType.compareTo(voOSType.VOOSMP_SOURCE_STREAMTYPE.VOOSMP_SS_AUDIO) == 0 || this.TrackType.compareTo(voOSType.VOOSMP_SOURCE_STREAMTYPE.VOOSMP_SS_AUDIO_GROUP) == 0) {
            voOSAudioInfoImpl voosaudioinfoimpl = new voOSAudioInfoImpl();
            this.AudioInfo = voosaudioinfoimpl;
            voosaudioinfoimpl.parse(parcel);
        } else if (this.TrackType.compareTo(voOSType.VOOSMP_SOURCE_STREAMTYPE.VOOSMP_SS_VIDEO) == 0 || this.TrackType.compareTo(voOSType.VOOSMP_SOURCE_STREAMTYPE.VOOSMP_SS_VIDEO_GROUP) == 0) {
            voOSVideoInfoImpl voosvideoinfoimpl = new voOSVideoInfoImpl();
            this.VideoInfo = voosvideoinfoimpl;
            voosvideoinfoimpl.parse(parcel);
        } else if (this.TrackType.compareTo(voOSType.VOOSMP_SOURCE_STREAMTYPE.VOOSMP_SS_SUBTITLE) == 0 || this.TrackType.compareTo(voOSType.VOOSMP_SOURCE_STREAMTYPE.VOOSMP_SS_SUBTITLE_GROUP) == 0) {
            voOSSubtitleInfoImpl voossubtitleinfoimpl = new voOSSubtitleInfoImpl();
            voossubtitleinfoimpl.parse(parcel);
            this.SubtitleInfo = voossubtitleinfoimpl;
        }
        int i = parcel.readInt();
        this.HeadSize = i;
        if (i <= 0) {
            return true;
        }
        byte[] bArr2 = new byte[i];
        try {
            parcel.readByteArray(bArr2);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            this.HeadData = new String(bArr2, 0, this.HeadSize, "utf-8").toCharArray();
            return true;
        } catch (UnsupportedEncodingException e4) {
            e4.printStackTrace();
            return true;
        }
    }

    @Override // com.visualon.OSMPUtils.voOSTrackInfo
    public int getTrackID() {
        return this.TrackID;
    }

    @Override // com.visualon.OSMPUtils.voOSTrackInfo
    public char[] getFourCC() {
        return this.FourCC;
    }

    @Override // com.visualon.OSMPUtils.voOSTrackInfo
    public voOSType.VOOSMP_SOURCE_STREAMTYPE getTrackType() {
        return this.TrackType;
    }

    @Override // com.visualon.OSMPUtils.voOSTrackInfo
    public int getCodec() {
        return this.Codec;
    }

    @Override // com.visualon.OSMPUtils.voOSTrackInfo
    public long getDuration() {
        return this.Duration;
    }

    @Override // com.visualon.OSMPUtils.voOSTrackInfo
    public voOSAudioInfo getAudioInfo() {
        return this.AudioInfo;
    }

    @Override // com.visualon.OSMPUtils.voOSTrackInfo
    public voOSVideoInfo getVideoInfo() {
        return this.VideoInfo;
    }

    @Override // com.visualon.OSMPUtils.voOSTrackInfo
    public int getHeadSize() {
        return this.HeadSize;
    }

    @Override // com.visualon.OSMPUtils.voOSTrackInfo
    public char[] getHeadData() {
        return this.HeadData;
    }

    @Override // com.visualon.OSMPUtils.voOSTrackInfo
    public int getSelectInfo() {
        return this.SelectInfo;
    }

    @Override // com.visualon.OSMPUtils.voOSTrackInfo
    public int getChunkCounts() {
        return this.ChunkCounts;
    }

    @Override // com.visualon.OSMPUtils.voOSTrackInfo
    public int getBitrate() {
        return this.Bitrate;
    }

    @Override // com.visualon.OSMPUtils.voOSTrackInfo
    public voOSSubtitleInfo getSubtitleInfo() {
        return this.SubtitleInfo;
    }
}
