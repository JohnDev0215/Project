package com.byd.vrdownload.model;

/* JADX INFO: loaded from: classes.dex */
public interface ILanguageOTA extends ILanguageInstalled {
    String getPkgKey();

    String getPkgMd5();

    String getPkgSize();

    String getPkgVersion();

    Long getTimeStamp();
}
