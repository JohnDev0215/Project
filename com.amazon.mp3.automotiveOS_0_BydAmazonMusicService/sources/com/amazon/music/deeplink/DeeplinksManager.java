package com.amazon.music.deeplink;

import android.content.Context;
import android.net.Uri;
import com.amazon.music.destination.core.DeeplinkParser;
import com.amazon.music.destination.core.Destination;
import com.amazon.music.router.DestinationHandler;
import com.amazon.music.router.Router;
import com.amazon.music.router.UnknownDestinationException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public class DeeplinksManager {
    private static DeeplinksManager INSTANCE = null;
    private static final String ONE_CLICK_QUERY_PARAM_KEY_CODE = "oneClickScheduleId";
    private static final String TAG = "DeeplinksManager";
    private Context context;
    private final List<DeeplinkParser> parsers = new ArrayList();

    public static synchronized DeeplinksManager get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DeeplinksManager(context.getApplicationContext());
        }
        return INSTANCE;
    }

    private DeeplinksManager(Context context) {
        this.context = context.getApplicationContext();
    }

    public <T extends Destination> void register(Class<T> cls, DestinationHandler<T> destinationHandler, DeeplinkParser<T> deeplinkParser) {
        if (this.parsers.contains(deeplinkParser)) {
            throw new IllegalStateException(String.format("%s: Cannot register the same Deeplink parser twice", TAG));
        }
        this.parsers.add(deeplinkParser);
        Router.get(this.context).register(cls, destinationHandler);
    }

    public void handle(Uri uri) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        handle(uri, null, false);
    }

    public void handle(Uri uri, Runnable runnable) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        handle(uri, runnable, false);
    }

    public void handleInternal(Uri uri) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        handle(uri, null, true);
    }

    public Uri getSanitizedExternalUri(Uri uri) {
        Set<String> queryParameterNames = uri.getQueryParameterNames();
        Uri.Builder builderClearQuery = uri.buildUpon().clearQuery();
        for (String str : queryParameterNames) {
            if (!ONE_CLICK_QUERY_PARAM_KEY_CODE.equals(str)) {
                builderClearQuery.appendQueryParameter(str, uri.getQueryParameter(str));
            }
        }
        return builderClearQuery.build();
    }

    public void handle(Uri uri, final Runnable runnable, boolean z) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        if (!z) {
            uri = getSanitizedExternalUri(uri);
        }
        Router.get(this.context).navigateTo(getDestination(uri), new Runnable() { // from class: com.amazon.music.deeplink.DeeplinksManager.1
            @Override // java.lang.Runnable
            public void run() {
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        });
    }

    public void handle(Destination destination, final Runnable runnable) throws MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        Router.get(this.context).navigateTo(destination, new Runnable() { // from class: com.amazon.music.deeplink.DeeplinksManager.2
            @Override // java.lang.Runnable
            public void run() {
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        });
    }

    public Uri getBaseDeeplinkUrl(Uri uri) {
        return checkIfSonarUri(uri.toString()) ? Uri.parse(uri.getQueryParameter("U")) : uri;
    }

    public Destination getDestination(Uri uri) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        if (checkIfSonarUri(uri.toString())) {
            new MakeSonarAttributionCallTask().execute(uri.toString());
            uri = Uri.parse(uri.getQueryParameter("U"));
        }
        if (checkIfMoveableInkUri(uri.toString())) {
            MakeMoveableInkCallTask makeMoveableInkCallTask = new MakeMoveableInkCallTask();
            makeMoveableInkCallTask.execute(uri.toString());
            try {
                uri = makeMoveableInkCallTask.get();
            } catch (Exception unused) {
                throw new UnknownDeeplinkException(uri);
            }
        }
        return findParser(uri).parse(uri);
    }

    private static boolean checkIfSonarUri(String str) {
        return str.contains("/gp/r.html");
    }

    private static boolean checkIfMoveableInkUri(String str) {
        return str.contains("mi.amazonmusic.com");
    }

    DeeplinkParser findParser(Uri uri) throws UnknownDeeplinkException {
        for (DeeplinkParser deeplinkParser : this.parsers) {
            if (uri.getPath().matches(deeplinkParser.getUrlRegex()) || uri.toString().startsWith(deeplinkParser.getUrlRegex())) {
                return deeplinkParser;
            }
        }
        throw new UnknownDeeplinkException(uri);
    }

    public <T extends Destination> void appendRNParser(Class<T> cls, DestinationHandler<T> destinationHandler, DeeplinkParser<T> deeplinkParser) {
        this.parsers.add(0, deeplinkParser);
        Router.get(this.context).register(cls, destinationHandler);
    }
}
