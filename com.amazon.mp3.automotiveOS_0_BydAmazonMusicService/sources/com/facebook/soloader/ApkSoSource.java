package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import com.facebook.soloader.ExtractFromZipSoSource;
import com.facebook.soloader.UnpackingSoSource;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public class ApkSoSource extends ExtractFromZipSoSource {
    private final int mFlags;

    public ApkSoSource(Context context, File file, String str, int i) {
        super(context, str, file, "^lib/([^/]+)/([^/]+\\.so)$");
        this.mFlags = i;
    }

    @Override // com.facebook.soloader.ExtractFromZipSoSource, com.facebook.soloader.UnpackingSoSource
    protected UnpackingSoSource.Unpacker makeUnpacker(byte b) throws IOException {
        return new ApkUnpacker(this);
    }

    protected class ApkUnpacker extends ExtractFromZipSoSource.ZipUnpacker {
        private final int mFlags;
        private final File mLibDir;

        ApkUnpacker(ExtractFromZipSoSource extractFromZipSoSource) throws IOException {
            super(extractFromZipSoSource);
            this.mLibDir = new File(ApkSoSource.this.mContext.getApplicationInfo().nativeLibraryDir);
            this.mFlags = ApkSoSource.this.mFlags;
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x007e  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00cc  */
        @Override // com.facebook.soloader.ExtractFromZipSoSource.ZipUnpacker
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        protected boolean shouldExtract(java.util.zip.ZipEntry r10, java.lang.String r11) {
            /*
                Method dump skipped, instruction units count: 211
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.ApkSoSource.ApkUnpacker.shouldExtract(java.util.zip.ZipEntry, java.lang.String):boolean");
        }
    }

    @Override // com.facebook.soloader.UnpackingSoSource
    protected byte[] getDepsBlock() throws IOException {
        File canonicalFile = this.mZipFileName.getCanonicalFile();
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeByte((byte) 2);
            parcelObtain.writeString(canonicalFile.getPath());
            parcelObtain.writeLong(canonicalFile.lastModified());
            parcelObtain.writeInt(SysUtil.getAppVersionCode(this.mContext));
            if ((this.mFlags & 1) == 0) {
                parcelObtain.writeByte((byte) 0);
                return parcelObtain.marshall();
            }
            String str = this.mContext.getApplicationInfo().nativeLibraryDir;
            if (str == null) {
                parcelObtain.writeByte((byte) 1);
                return parcelObtain.marshall();
            }
            File canonicalFile2 = new File(str).getCanonicalFile();
            if (!canonicalFile2.exists()) {
                parcelObtain.writeByte((byte) 1);
                return parcelObtain.marshall();
            }
            parcelObtain.writeByte((byte) 2);
            parcelObtain.writeString(canonicalFile2.getPath());
            parcelObtain.writeLong(canonicalFile2.lastModified());
            return parcelObtain.marshall();
        } finally {
            parcelObtain.recycle();
        }
    }
}
