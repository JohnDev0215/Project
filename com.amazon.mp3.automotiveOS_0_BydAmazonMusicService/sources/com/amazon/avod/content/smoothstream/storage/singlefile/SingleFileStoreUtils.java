package com.amazon.avod.content.smoothstream.storage.singlefile;

import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.visualon.OSMPUtils.voMimeTypes;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class SingleFileStoreUtils {
    public static String getFileNameFromSSU(SmoothStreamingURI smoothStreamingURI) {
        if (smoothStreamingURI.isVideo()) {
            return voMimeTypes.VOBASE_TYPE_VIDEO + ".mp4";
        }
        return voMimeTypes.VOBASE_TYPE_AUDIO + '_' + smoothStreamingURI.getStream().getAudioTrackId() + ".mp4";
    }

    public static String buildDownloadStatusFilePath(File file) {
        return file.getAbsolutePath() + File.separator + "download_status.json";
    }

    public static String buildMediaFilePath(File file, String str) {
        return file.getAbsolutePath() + File.separator + str;
    }
}
