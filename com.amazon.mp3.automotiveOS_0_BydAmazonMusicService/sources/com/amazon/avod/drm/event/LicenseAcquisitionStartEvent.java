package com.amazon.avod.drm.event;

/* JADX INFO: loaded from: classes2.dex */
public class LicenseAcquisitionStartEvent extends BaseLicenseAcquisitionEvent {
    private final boolean mIsDownload;
    private final String mNotes;

    public LicenseAcquisitionStartEvent(boolean z, String str) {
        this.mIsDownload = z;
        this.mNotes = str;
    }

    public String getNotes() {
        return this.mNotes;
    }
}
