package com.amazon.mp3.inapplandingpage;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.Log;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.destination.parser.ParserUtil;
import com.google.android.gms.common.internal.ImagesContract;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

/* JADX INFO: compiled from: InAppLandingPageDeeplinkHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0007R\u001c\u0010\t\u001a\n \b*\u0004\u0018\u00010\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/inapplandingpage/InAppLandingPageDeeplinkHandler;", "", "", ImagesContract.URL, "", "isInAppUrl", "", "handleInAppUrl", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class InAppLandingPageDeeplinkHandler {
    public static final InAppLandingPageDeeplinkHandler INSTANCE = new InAppLandingPageDeeplinkHandler();
    private static final String TAG = InAppLandingPageDeeplinkHandler.class.getSimpleName();

    private InAppLandingPageDeeplinkHandler() {
    }

    @JvmStatic
    public static final boolean isInAppUrl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        try {
            for (NameValuePair nameValuePair : URLEncodedUtils.parse(new URI(url), ParserUtil.UTF_8)) {
                if (Intrinsics.areEqual("openinapp", nameValuePair.getName()) && Intrinsics.areEqual("true", nameValuePair.getValue())) {
                    return true;
                }
            }
            return false;
        } catch (URISyntaxException unused) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("URL : %s is invalid, cannot parse it into query params", Arrays.copyOf(new Object[]{url}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            Log.error(TAG, str);
            return false;
        }
    }

    @JvmStatic
    public static final void handleInAppUrl(String url) throws InAppLandingPageDeeplinkHandlerException {
        Intrinsics.checkNotNullParameter(url, "url");
        try {
            DeeplinksManager.get(AmazonApplication.getContext()).handleInternal(Uri.parse(url));
        } catch (Exception e) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("Unable to open the following in-app url : %s due to the exception : %s", Arrays.copyOf(new Object[]{url, e}, 2));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            Log.error(TAG, str);
            throw new InAppLandingPageDeeplinkHandlerException(e);
        }
    }
}
