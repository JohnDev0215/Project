package com.android.systemui.glwallpaper;

import android.util.Size;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public interface GLWallpaperRenderer {
    void dump(String prefix, FileDescriptor fd, PrintWriter out, String[] args);

    void finish();

    boolean isWcgContent();

    void onDrawFrame();

    void onSurfaceChanged(int width, int height);

    void onSurfaceCreated();

    Size reportSurfaceSize();
}
