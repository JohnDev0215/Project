package com.amazon.avod.drm.event;

/* JADX INFO: loaded from: classes2.dex */
public class LicenseAcquisitionEndEvent extends BaseLicenseAcquisitionEvent {
    private final String mNotes;

    public LicenseAcquisitionEndEvent(String str) {
        this.mNotes = str;
    }

    public String getNotes() {
        return this.mNotes;
    }
}
