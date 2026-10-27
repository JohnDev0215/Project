package com.visualon.OSMPUtils;

import android.os.Parcel;
import com.visualon.OSMPUtils.voOSType;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes7.dex */
public class voOSProgramInfoImpl implements voOSProgramInfo {
    int ProgramID;
    String ProgramName;
    voOSType.VOOSMP_SRC_PROGRAM_TYPE ProgramType;
    int SelInfo;
    int StreamCount;
    voOSStreamInfo[] StreamInfo;

    public voOSProgramInfoImpl() {
    }

    public voOSProgramInfoImpl(int i, int i2, String str, voOSType.VOOSMP_SRC_PROGRAM_TYPE voosmp_src_program_type, int i3, voOSStreamInfo[] voosstreaminfoArr) {
        this.ProgramID = i;
        this.SelInfo = i2;
        this.ProgramName = str;
        this.ProgramType = voosmp_src_program_type;
        this.StreamCount = i3;
        this.StreamInfo = voosstreaminfoArr;
    }

    @Override // com.visualon.OSMPUtils.voOSProgramInfo
    public boolean parse(Parcel parcel) {
        this.ProgramID = parcel.readInt();
        this.SelInfo = parcel.readInt();
        byte[] bArr = new byte[256];
        parcel.readByteArray(bArr);
        try {
            String str = new String(bArr, 0, 256, "utf-8");
            this.ProgramName = str;
            this.ProgramName = str.trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int i = parcel.readInt();
        if (i < 0) {
            i = 0;
        }
        if (i > 1) {
            i = 0;
        }
        this.ProgramType = voOSType.VOOSMP_SRC_PROGRAM_TYPE.valueOf(i);
        int i2 = parcel.readInt();
        this.StreamCount = i2;
        if (i2 > 0) {
            this.StreamInfo = new voOSStreamInfo[i2];
        }
        for (int i3 = 0; i3 < this.StreamCount; i3++) {
            this.StreamInfo[i3] = new voOSStreamInfoImpl();
            this.StreamInfo[i3].parse(parcel);
        }
        return true;
    }

    @Override // com.visualon.OSMPUtils.voOSProgramInfo
    public int getProgramID() {
        return this.ProgramID;
    }

    @Override // com.visualon.OSMPUtils.voOSProgramInfo
    public int getSelInfo() {
        return this.SelInfo;
    }

    @Override // com.visualon.OSMPUtils.voOSProgramInfo
    public String getProgramName() {
        return this.ProgramName;
    }

    @Override // com.visualon.OSMPUtils.voOSProgramInfo
    public voOSType.VOOSMP_SRC_PROGRAM_TYPE getProgramType() {
        return this.ProgramType;
    }

    @Override // com.visualon.OSMPUtils.voOSProgramInfo
    public int getStreamCount() {
        return this.StreamCount;
    }

    @Override // com.visualon.OSMPUtils.voOSProgramInfo
    public voOSStreamInfo[] getStreamInfo() {
        return this.StreamInfo;
    }
}
