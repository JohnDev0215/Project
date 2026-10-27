package com.android.systemui;

import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public interface Dumpable {
    void dump(FileDescriptor fd, PrintWriter pw, String[] args);
}
