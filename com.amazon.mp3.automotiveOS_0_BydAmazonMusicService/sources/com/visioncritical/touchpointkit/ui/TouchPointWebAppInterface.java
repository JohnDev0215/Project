package com.visioncritical.touchpointkit.ui;

import android.webkit.JavascriptInterface;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.destination.parser.ParserUtil;
import com.visioncritical.touchpointkit.commons.TouchPointLogger;
import com.visioncritical.touchpointkit.utils.TouchPointActivity;
import com.visioncritical.touchpointkit.utils.TouchPointActivityInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: TouchPointWebAppInterface.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/visioncritical/touchpointkit/ui/TouchPointWebAppInterface;", "", "()V", ParserUtil.TAG_QUERY_PARAM_NAME, "", "postMessage", "", "jsonData", "touchpointkit_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TouchPointWebAppInterface {
    private final String tag = "TouchPointWebAppInterface";

    @JavascriptInterface
    public final void postMessage(String jsonData) {
        try {
            if (Intrinsics.areEqual(new JSONObject(jsonData).optString(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE), "ActivityCompleted")) {
                TouchPointActivity.Companion companion = TouchPointActivity.INSTANCE;
                TouchPointActivityInterface listener = companion.getShared().getListener();
                if (listener != null) {
                    listener.onTouchPointActivityFinished();
                }
                companion.getShared().setListener(null);
            }
        } catch (Exception e) {
            TouchPointLogger.INSTANCE.errorLog(this.tag, Intrinsics.stringPlus("WebAppInterface: Error while parsing response: ", e.getLocalizedMessage()));
        }
    }
}
