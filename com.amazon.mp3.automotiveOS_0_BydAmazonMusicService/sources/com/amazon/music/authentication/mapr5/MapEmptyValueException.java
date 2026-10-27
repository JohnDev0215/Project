package com.amazon.music.authentication.mapr5;

import android.os.Bundle;

/* JADX INFO: loaded from: classes4.dex */
public class MapEmptyValueException extends Exception {
    private final String keyName;
    private final Bundle responseBundle;

    public MapEmptyValueException(Bundle bundle, String str) {
        this.responseBundle = bundle;
        this.keyName = str;
    }

    public Bundle getResponseBundle() {
        return this.responseBundle;
    }

    public String getKeyName() {
        return this.keyName;
    }
}
