package com.byd.card;

import androidx.core.os.EnvironmentCompat;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppBlockCanaryContext {
    public List<String> concernPackages() {
        return null;
    }

    public boolean deleteFilesInWhiteList() {
        return true;
    }

    public boolean displayNotification() {
        return true;
    }

    public boolean filterNonConcernStack() {
        return false;
    }

    public int provideBlockThreshold() {
        return 1000;
    }

    public int provideMonitorDuration() {
        return -1;
    }

    public String provideNetworkType() {
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }

    public String providePath() {
        return "/blockcanary/";
    }

    public String provideQualifier() {
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }

    public String provideUid() {
        return "uid";
    }

    public boolean zip(File[] src, File dest) {
        return false;
    }

    public int provideDumpInterval() {
        return provideBlockThreshold();
    }

    public void upload(File zippedFile) {
        throw new UnsupportedOperationException();
    }

    public List<String> provideWhiteList() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("org.chromium");
        return linkedList;
    }
}
