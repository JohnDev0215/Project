package com.amazon.mp3.net.cirrus;

import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.url.EndPointURL;
import com.amazon.mp3.net.service.ServiceApi;

/* JADX INFO: loaded from: classes3.dex */
public class LibraryRequestDispatcher extends CirrusRequestDispatcher {
    private static final String TAG = "LibraryRequestDispatcher";
    private boolean mIgnoreBluemoonExceptions;

    public LibraryRequestDispatcher(boolean ignoreBluemoonExceptions) {
        this.mIgnoreBluemoonExceptions = ignoreBluemoonExceptions;
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected EndPointURL getEndPointURL(ServiceApi version) {
        EndPointURL endPointURL = Environment.CLOUD.get();
        endPointURL.appendPath(version.getPath());
        return endPointURL;
    }

    @Override // com.amazon.mp3.net.cirrus.CirrusRequestDispatcher
    protected CirrusHttpClient createCirrusHttpClient() {
        if (this.mIgnoreBluemoonExceptions) {
            return new CirrusHttpClient();
        }
        return super.createCirrusHttpClient();
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected String getTag() {
        return TAG;
    }
}
