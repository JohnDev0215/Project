package com.android.systemui.volume;

import android.content.res.Configuration;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public interface VolumeComponent {
    void dismiss();

    void dismissLauncherDialog();

    void dismissWirelessDialog();

    void dump(FileDescriptor fd, PrintWriter pw, String[] args);

    void onConfigurationChanged(Configuration newConfig);

    void setWirelessPowerLevel(boolean level);

    void showLauncherDialog();

    void showWirelessDialog();
}
