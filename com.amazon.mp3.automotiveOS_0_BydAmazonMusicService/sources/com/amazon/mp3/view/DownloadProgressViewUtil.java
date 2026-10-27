package com.amazon.mp3.view;

/* JADX INFO: loaded from: classes4.dex */
public class DownloadProgressViewUtil {
    public static boolean shouldShowProgress(int downloadState) {
        return (downloadState == -1 || downloadState == 5) ? false : true;
    }

    private DownloadProgressViewUtil() {
    }
}
