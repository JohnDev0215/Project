package com.amazon.avod.playbackclient.trickplay.internal;

import android.annotation.TargetApi;
import com.amazon.avod.content.image.TrickplayIndex;
import com.amazon.avod.content.image.TrickplayManifest;
import com.amazon.avod.media.download.plugin.PluginLoadStatus;
import com.amazon.avod.playbackclient.trickplay.download.TrickplayLoadingResponse;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Optional;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class TrickplayEventDataBuilder {
    @TargetApi(9)
    public String createNote(@Nonnull TrickplayLoadingResponse trickplayLoadingResponse) {
        String str;
        String strValueOf;
        PluginLoadStatus loadingStatus = trickplayLoadingResponse.getLoadingStatus();
        Optional<TrickplayIndex> trickplayIndex = trickplayLoadingResponse.getTrickplayIndex();
        if (trickplayIndex.isPresent()) {
            TrickplayManifest rawManifest = trickplayIndex.get().getRawManifest();
            if (rawManifest.isLive()) {
                str = String.format(Locale.US, "[%sx%s live manifest]", Integer.valueOf(rawManifest.getImageWidth()), Integer.valueOf(rawManifest.getImageHeight()));
            } else {
                str = String.format(Locale.US, "[%s %sx%s images available]", Integer.valueOf(rawManifest.getTimecodeToFilepathMap().size()), Integer.valueOf(rawManifest.getImageWidth()), Integer.valueOf(rawManifest.getImageHeight()));
            }
        } else {
            str = "[Not available]";
        }
        if (loadingStatus.getStatus() == PluginLoadStatus.Status.ERRORED) {
            strValueOf = String.valueOf(loadingStatus.getErrorType());
        } else {
            strValueOf = loadingStatus.getStatus() == PluginLoadStatus.Status.CANCELLED ? String.valueOf(loadingStatus.getCancellationReason()) : "[No further info]";
        }
        Locale locale = Locale.US;
        StringBuilder sb = new StringBuilder(String.format(locale, "Availability=%s,Status=%s,Source=%s,Reason=%s,Manifest=%s", loadingStatus.getAvailability(), loadingStatus.getStatus(), loadingStatus.getSource(), strValueOf, str));
        if (trickplayLoadingResponse.getBifFileSize() != -1) {
            sb.append(String.format(locale, ",BifFileSize=%s", Long.valueOf((long) DataUnit.BYTES.toKiloBytes(trickplayLoadingResponse.getBifFileSize()))));
        }
        return sb.toString();
    }
}
