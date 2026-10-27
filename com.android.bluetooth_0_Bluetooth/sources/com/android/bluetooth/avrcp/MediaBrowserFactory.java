package com.android.bluetooth.avrcp;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import com.android.bluetooth.avrcp.MediaBrowser;

/* JADX INFO: loaded from: classes.dex */
public final class MediaBrowserFactory {
    private static MediaBrowser sInjectedBrowser;

    static MediaBrowser wrap(android.media.browse.MediaBrowser delegate) {
        MediaBrowser mediaBrowser = sInjectedBrowser;
        if (mediaBrowser != null) {
            return mediaBrowser;
        }
        if (delegate != null) {
            return new MediaBrowser(delegate);
        }
        return null;
    }

    static MediaBrowser make(Context context, ComponentName serviceComponent, MediaBrowser.ConnectionCallback callback, Bundle rootHints) {
        MediaBrowser mediaBrowser = sInjectedBrowser;
        if (mediaBrowser != null) {
            mediaBrowser.testInit(context, serviceComponent, callback, rootHints);
            return sInjectedBrowser;
        }
        return new MediaBrowser(context, serviceComponent, callback, rootHints);
    }

    static void inject(MediaBrowser browser) {
        sInjectedBrowser = browser;
    }
}
