package com.byd.vrdownload.dao;

import com.byd.vrdownload.model.LanguageUpdate;

/* JADX INFO: loaded from: classes.dex */
public interface Maintainer<T> extends Iterable<T> {
    void onFinishMaintain();

    void onRefreshedValidData(LanguageUpdate languageUpdate);

    void onRemovedInvalidData(LanguageUpdate languageUpdate);
}
