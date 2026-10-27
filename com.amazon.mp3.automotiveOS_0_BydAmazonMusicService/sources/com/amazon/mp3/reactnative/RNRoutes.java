package com.amazon.mp3.reactnative;

import android.content.Context;
import android.os.Trace;
import com.amazon.mp3.navigation.ReactNativeDestinationHandler;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.destination.ReactNativeDestination;
import com.amazon.music.destination.parser.ReactNativeDeeplinkParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: RNRoutes.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/reactnative/RNRoutes;", "", "()V", "deeplinksManager", "Lcom/amazon/music/deeplink/DeeplinksManager;", "getDeeplinksManager", "()Lcom/amazon/music/deeplink/DeeplinksManager;", "setDeeplinksManager", "(Lcom/amazon/music/deeplink/DeeplinksManager;)V", "createRegexPattern", "", "uriPath", "parseRNRoutes", "", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RNRoutes {
    public static final RNRoutes INSTANCE = new RNRoutes();
    public static DeeplinksManager deeplinksManager;

    private RNRoutes() {
    }

    public final DeeplinksManager getDeeplinksManager() {
        DeeplinksManager deeplinksManager2 = deeplinksManager;
        if (deeplinksManager2 != null) {
            return deeplinksManager2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("deeplinksManager");
        return null;
    }

    public final void setDeeplinksManager(DeeplinksManager deeplinksManager2) {
        Intrinsics.checkNotNullParameter(deeplinksManager2, "<set-?>");
        deeplinksManager = deeplinksManager2;
    }

    public final void parseRNRoutes(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Trace.beginSection("AMA.RNRoutes.parseRNRoutes");
        DeeplinksManager deeplinksManager2 = DeeplinksManager.get(context);
        Intrinsics.checkNotNullExpressionValue(deeplinksManager2, "get(context)");
        setDeeplinksManager(deeplinksManager2);
        try {
            InputStream inputStreamOpen = context.getAssets().open("routes.json");
            Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "context.assets.open(\"routes.json\")");
            Reader inputStreamReader = new InputStreamReader(inputStreamOpen, Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                String text = TextStreamsKt.readText(bufferedReader);
                CloseableKt.closeFinally(bufferedReader, null);
                for (Route route : ((Routes) new Gson().fromJson(text, Routes.class)).getRoutes()) {
                    RNRoutes rNRoutes = INSTANCE;
                    String strCreateRegexPattern = rNRoutes.createRegexPattern(route.getPath());
                    route.setRegexRoute(strCreateRegexPattern);
                    rNRoutes.getDeeplinksManager().appendRNParser(ReactNativeDestination.class, new ReactNativeDestinationHandler(), new ReactNativeDeeplinkParser(strCreateRegexPattern, route.getPresentationStyle()));
                }
                Trace.endSection();
            } finally {
            }
        } catch (IOException e) {
            e.printStackTrace();
            Trace.endSection();
        }
    }

    private final String createRegexPattern(String uriPath) {
        Trace.beginSection("AMA.RNRoutes.createRegexPattern");
        List listSplit$default = StringsKt__StringsKt.split$default((CharSequence) uriPath, new String[]{"/"}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : listSplit$default) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            if (StringsKt__StringsJVMKt.startsWith$default(str, ":", false, 2, null)) {
                arrayList.add(i, "(\\w+\\-?)+");
            } else {
                arrayList.add(i, str);
            }
            i = i2;
        }
        String str2 = JsonPointer.SEPARATOR + CollectionsKt___CollectionsKt.joinToString$default(arrayList, "/", null, null, 0, null, null, 62, null) + "(\\?(\\w+=\\w+(&\\w+=\\w+)*)?)?";
        Trace.endSection();
        return str2;
    }
}
