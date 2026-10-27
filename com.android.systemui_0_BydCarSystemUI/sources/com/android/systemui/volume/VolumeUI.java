package com.android.systemui.volume;

import android.content.res.Configuration;
import com.android.systemui.SystemUI;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class VolumeUI extends SystemUI {
    private VolumeDialogComponent mVolumeComponent;

    @Override // com.android.systemui.SystemUI
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
    }

    @Override // com.android.systemui.SystemUI
    protected void onBootCompleted() {
    }

    @Override // com.android.systemui.SystemUI
    public void start() {
        this.mVolumeComponent = new VolumeDialogComponent(this.mContext);
        putComponent(VolumeComponent.class, getVolumeComponent());
    }

    private VolumeComponent getVolumeComponent() {
        return this.mVolumeComponent;
    }

    @Override // com.android.systemui.SystemUI
    protected void onConfigurationChanged(Configuration newConfig) {
        getVolumeComponent().onConfigurationChanged(newConfig);
    }
}
