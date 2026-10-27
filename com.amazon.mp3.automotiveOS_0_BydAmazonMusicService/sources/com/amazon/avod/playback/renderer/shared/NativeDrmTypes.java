package com.amazon.avod.playback.renderer.shared;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/* JADX INFO: loaded from: classes2.dex */
public class NativeDrmTypes {
    public static final int EXTRA_DATA_FLAG_IV_DATA = 2131755012;
    public static final int EXTRA_DATA_FLAG_NONE = 0;
    public static final int SIZEOF_AIVEXT_ENCRYPTION_METADATA = 25;
    public static final int SIZEOF_AIVEXT_IVDATA = 17;
    public static final int SIZEOF_BASIC_EXTRADATA_BLOCK = 20;
    public static final int SIZEOF_DRM_METADATA_BLOCK = 45;

    @SuppressFBWarnings(justification = "Those fields are actually used in native layer", value = {"UUF_UNUSED_FIELD"})
    public static class DRMMetadataBlock {
        byte bByteOffset;
        int eType;
        int encryptionOffset;
        int encryptionSize;
        int nDataSize;
        int nPortIndex;
        int nSize;
        int nVersion;
        long qwBlockOffset;
        long qwInitializationVector;
    }
}
