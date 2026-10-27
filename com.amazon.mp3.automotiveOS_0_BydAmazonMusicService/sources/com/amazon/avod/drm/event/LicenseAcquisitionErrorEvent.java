package com.amazon.avod.drm.event;

import java.net.URL;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class LicenseAcquisitionErrorEvent extends BaseLicenseAcquisitionEvent {
    private final String mErrorMessage;
    private final String mNotes;
    private final URL mUrl;

    public LicenseAcquisitionErrorEvent(String str, String str2, @Nullable URL url) {
        this.mErrorMessage = str2;
        this.mNotes = str;
        this.mUrl = url;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public String getNotes() {
        return this.mNotes;
    }

    @Nullable
    public URL getUrl() {
        return this.mUrl;
    }
}
