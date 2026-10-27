package com.google.firebase.remoteconfig;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes6.dex */
public interface FirebaseRemoteConfigValue {
    boolean asBoolean() throws IllegalArgumentException;

    double asDouble() throws IllegalArgumentException;

    long asLong() throws IllegalArgumentException;

    @NonNull
    String asString();

    int getSource();
}
