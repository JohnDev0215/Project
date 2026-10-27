package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.ExternalDestination;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.router.DestinationHandler;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/* JADX INFO: loaded from: classes3.dex */
public class ExternalDestinationHandler implements DestinationHandler<ExternalDestination> {
    private static final String TAG = "ExternalDestinationHandler";

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final ExternalDestination destination) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(URLDecoder.decode(destination.getUrl(), ParserUtil.UTF_8)));
            intent.setFlags(268435456);
            context.startActivity(intent);
        } catch (UnsupportedEncodingException e) {
            Log.error(TAG, "Can't decode the url " + destination.getUrl(), e);
        }
    }
}
