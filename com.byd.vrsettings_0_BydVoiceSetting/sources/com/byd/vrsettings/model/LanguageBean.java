package com.byd.vrsettings.model;

import com.byd.vrdownload.model.LanguageUpdate;
import com.byd.vrsettings.utils.Tools;
import com.chad.library.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class LanguageBean {
    LanguageLocal languageLocal;
    LanguageUpdate languageRemote;
    String localPkgSize;
    String remotePath;
    int source;
    int state;
    int taskId = -1;
    int progress = 0;
    boolean autoEnable = false;

    public LanguageUpdate getLanguageRemote() {
        return this.languageRemote;
    }

    public void setLanguageRemote(LanguageUpdate languageUpdate) {
        this.languageRemote = languageUpdate;
    }

    public LanguageLocal getLanguageLocal() {
        return this.languageLocal;
    }

    public void setLanguageLocal(LanguageLocal languageLocal) {
        this.languageLocal = languageLocal;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public int getSource() {
        return this.source;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public void setTaskId(int i) {
        this.taskId = i;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public void setSource(int i) {
        this.source = i;
    }

    public String getRemotePath() {
        return this.remotePath;
    }

    public void setRemotePath(String str) {
        this.remotePath = str;
    }

    public boolean isAutoEnable() {
        return this.autoEnable;
    }

    public void setAutoEnable(boolean z) {
        this.autoEnable = z;
    }

    public String getLocalPkgSize() {
        return this.localPkgSize;
    }

    public void setLocalPkgSize(String str) {
        this.localPkgSize = str;
    }

    public boolean isUpdate() {
        LanguageLocal languageLocal = this.languageLocal;
        if (languageLocal == null || this.languageRemote == null || languageLocal.getVersion() == null || this.languageRemote.getLatestPkgVersion() == null) {
            return false;
        }
        return Tools.compareVersion(this.languageLocal.getVersion().replace("V.", BuildConfig.FLAVOR), this.languageRemote.getLatestPkgVersion().replace("V.", BuildConfig.FLAVOR));
    }
}
