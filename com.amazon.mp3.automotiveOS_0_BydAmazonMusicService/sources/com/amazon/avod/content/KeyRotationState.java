package com.amazon.avod.content;

import com.amazon.avod.content.smoothstream.manifest.ProtectionHeader;
import com.amazon.avod.drm.widevine.WidevineHeader;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.util.Base64;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class KeyRotationState {
    private volatile String mAudioPsshIdentifier;
    private volatile boolean mIsInitialized = false;
    private volatile String mVideoPsshIdentifier;

    public void initialize(@Nullable ProtectionHeader protectionHeader, @Nullable ProtectionHeader protectionHeader2) throws IOException {
        Preconditions.checkState(!this.mIsInitialized, "initialize has already been invoked previously.");
        this.mVideoPsshIdentifier = protectionHeader == null ? null : getIdentifierFromPSSH(protectionHeader);
        this.mAudioPsshIdentifier = protectionHeader2 != null ? getIdentifierFromPSSH(protectionHeader2) : null;
        this.mIsInitialized = true;
    }

    String getIdentifierFromPSSH(@Nonnull ProtectionHeader protectionHeader) throws IOException {
        Preconditions.checkNotNull(protectionHeader, "pssh");
        DrmScheme drmScheme = protectionHeader.getDrmScheme();
        Preconditions.checkArgument(drmScheme == DrmScheme.WIDEVINE || drmScheme == DrmScheme.PLAYREADY, "Unrecognized DrmScheme:%s", drmScheme);
        if (drmScheme == DrmScheme.PLAYREADY) {
            return extractPlayReadyKeyId(protectionHeader);
        }
        return bytesToHexString(WidevineHeader.WidevineCencHeader.parseFrom(Base64.decode(protectionHeader.getBase64EncodedData())).getKeyId(0).toByteArray());
    }

    private String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(String.format(Locale.US, "%02x", Byte.valueOf(b)));
        }
        return sb.toString();
    }

    public boolean checkKeyRotated(@Nullable ProtectionHeader protectionHeader, boolean z) throws IOException {
        if (!this.mIsInitialized) {
            return false;
        }
        String str = z ? this.mVideoPsshIdentifier : this.mAudioPsshIdentifier;
        return str == null ? protectionHeader != null : (protectionHeader == null || str.equals(getIdentifierFromPSSH(protectionHeader))) ? false : true;
    }

    String extractPlayReadyKeyId(ProtectionHeader protectionHeader) {
        try {
            return new String(BaseEncoding.base64().decode(protectionHeader.getBase64EncodedData()), "UTF-16LE").split("<KID>")[1].split("</KID>")[0];
        } catch (UnsupportedEncodingException | IllegalStateException | IndexOutOfBoundsException unused) {
            return protectionHeader.getBase64EncodedData();
        }
    }
}
