package com.amazon.mp3.inappmessaging;

import android.content.Context;
import android.net.Uri;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.deeplink.UnknownDeeplinkException;
import com.amazon.music.inappmessaging.external.IAMWebViewDeeplinkHandler;
import com.amazon.music.inappmessaging.external.IAMWebViewDeeplinkHandlerException;
import com.amazon.music.router.UnknownDestinationException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

/* JADX INFO: loaded from: classes3.dex */
public class IAMWebViewDeeplinkHandlerImpl implements IAMWebViewDeeplinkHandler {
    private final DeeplinksManager mDeeplinksManager;

    public IAMWebViewDeeplinkHandlerImpl(Context context) {
        this.mDeeplinksManager = DeeplinksManager.get(context);
    }

    @Override // com.amazon.music.inappmessaging.external.IAMWebViewDeeplinkHandler
    public void handle(final Uri uri) throws IAMWebViewDeeplinkHandlerException {
        try {
            this.mDeeplinksManager.handleInternal(uri);
        } catch (UnknownDeeplinkException | UnknownDestinationException | UnsupportedEncodingException | MalformedURLException | URISyntaxException e) {
            throw new IAMWebViewDeeplinkHandlerException(e);
        }
    }
}
