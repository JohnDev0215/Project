package com.amazon.avod.content.smoothstream.manifest;

import android.util.Base64;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class ProtectionHeaderWithValidation implements ProtectionHeader {
    private final DrmScheme mDrmScheme;
    private final String mEncodedData;

    @Nonnull
    public static ProtectionHeader fromRawProtectionHeader(@Nonnull ProtectionHeader protectionHeader) {
        Preconditions.checkNotNull(protectionHeader, "originalProtectionHeader");
        if (protectionHeader.getDrmScheme() != DrmScheme.PLAYREADY) {
            DLog.logf("Skip validation because drm scheme is not playready");
            return protectionHeader;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(Base64.decode(protectionHeader.getBase64EncodedData(), 2));
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        int iCapacity = byteBufferWrap.capacity();
        if (iCapacity < 10) {
            DLog.logf("Skip validation because pssh header is too short: length->%s", Integer.valueOf(iCapacity));
            return protectionHeader;
        }
        int i = byteBufferWrap.getInt();
        short s = byteBufferWrap.getShort();
        if (i != iCapacity) {
            int i2 = 0;
            byteBufferWrap.putInt(0, iCapacity);
            DLog.logf("Changed PSSH header length from %s bytes to %s bytes", Integer.valueOf(i), Integer.valueOf(iCapacity));
            int iPosition = byteBufferWrap.position();
            while (byteBufferWrap.position() < iCapacity) {
                try {
                    i2++;
                    byteBufferWrap.getShort();
                    byteBufferWrap.position(byteBufferWrap.position() + byteBufferWrap.getShort());
                } catch (IllegalArgumentException | BufferUnderflowException unused) {
                    DLog.logf("There is error when trying to validate record count. Returning original protection header");
                    return protectionHeader;
                }
            }
            byteBufferWrap.putShort(4, (short) i2);
            byteBufferWrap.position(iPosition);
        }
        if (s != 1) {
            DLog.logf("Skip validating record length because number of record count is %s", Short.valueOf(s));
        } else {
            byteBufferWrap.getShort();
            short s2 = byteBufferWrap.getShort();
            int i3 = iCapacity - 10;
            if (s2 != i3) {
                short s3 = (short) i3;
                byteBufferWrap.putShort(8, s3);
                DLog.logf("Changed the only record length from %s bytes to %s bytes", Short.valueOf(s2), Short.valueOf(s3));
            }
        }
        return new ProtectionHeaderWithValidation(Base64.encodeToString(byteBufferWrap.array(), 2), protectionHeader.getDrmScheme());
    }

    ProtectionHeaderWithValidation(String str, DrmScheme drmScheme) {
        this.mEncodedData = str;
        this.mDrmScheme = drmScheme;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.ProtectionHeader
    @Nonnull
    public String getBase64EncodedData() {
        return this.mEncodedData;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.ProtectionHeader
    @Nonnull
    public DrmScheme getDrmScheme() {
        return this.mDrmScheme;
    }
}
