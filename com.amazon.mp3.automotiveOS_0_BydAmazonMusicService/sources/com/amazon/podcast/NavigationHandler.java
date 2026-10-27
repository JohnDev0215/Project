package com.amazon.podcast;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import com.amazon.music.deeplink.DeeplinksManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class NavigationHandler {
    private final Context context;
    private final Logger logger = LoggerFactory.getLogger("PodcastNavigationHandler");

    public NavigationHandler(final Context context) {
        this.context = context;
    }

    public final void navigateToDeeplink(final String url) {
        try {
            DeeplinksManager.get(this.context).handle(Uri.parse(url));
        } catch (Exception e) {
            this.logger.error("Error handling deeplink " + url, (Throwable) e);
        }
        finishCurrentActivity();
    }

    private void finishCurrentActivity() {
        Context context = this.context;
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }
}
