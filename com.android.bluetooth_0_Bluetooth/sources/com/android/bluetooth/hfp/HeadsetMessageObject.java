package com.android.bluetooth.hfp;

/* JADX INFO: loaded from: classes.dex */
public abstract class HeadsetMessageObject {
    public abstract void buildString(StringBuilder sb);

    public String toString() {
        StringBuilder builder = new StringBuilder();
        buildString(builder);
        return builder.toString();
    }
}
