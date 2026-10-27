package com.amazon.music.media.auto.tab.home.brush;

import android.content.Context;
import com.amazon.hermes.Hermes;
import com.amazon.hermes.RequestFacade;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.hermes.security.RequestSigner;
import com.amazon.music.authentication.AdpInfo;
import com.amazon.music.media.auto.util.AutoLog;
import com.amazon.music.media.auto.util.InterceptorHeaderUtil;
import com.amazon.music.signin.MAPAccount;
import com.amazonaws.http.HttpHeader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.perf.FirebasePerformance;
import java.io.IOException;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushRequestInterceptor.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000eH\u0016J \u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/media/auto/tab/home/brush/BrushRequestInterceptor;", "Lcom/amazon/hermes/RequestInterceptor;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getSerializedBody", "", "body", "", "mapper", "Lcom/fasterxml/jackson/databind/ObjectMapper;", "intercept", "", "requestFacade", "Lcom/amazon/hermes/RequestFacade;", "objectMapper", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BrushRequestInterceptor implements RequestInterceptor {
    private static final String TAG = BrushRequestInterceptor.class.getSimpleName();
    private final Context context;

    public BrushRequestInterceptor(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.hermes.RequestInterceptor
    public void intercept(RequestFacade<?> requestFacade) {
        intercept(requestFacade, Hermes.get().getObjectMapper());
    }

    @Override // com.amazon.hermes.RequestInterceptor
    public void intercept(RequestFacade<?> requestFacade, ObjectMapper objectMapper) {
        AdpInfo aDPToken = new MAPAccount(this.context).getADPToken();
        Intrinsics.checkNotNullExpressionValue(aDPToken, "mapAccount.adpToken");
        if (requestFacade == null || objectMapper == null) {
            return;
        }
        URL url = requestFacade.getUrl();
        Object body = requestFacade.getBody();
        Intrinsics.checkNotNullExpressionValue(body, "requestFacade.body");
        requestFacade.addHeaders(RequestSigner.signRequest(FirebasePerformance.HttpMethod.POST, url, getSerializedBody(body, objectMapper), aDPToken.privateKeyString, aDPToken.adpToken));
        InterceptorHeaderUtil interceptorHeaderUtil = InterceptorHeaderUtil.INSTANCE;
        requestFacade.addHeaders(HttpHeader.USER_AGENT, interceptorHeaderUtil.getUserAgent(this.context));
        requestFacade.addHeaders("Android-App-Version", interceptorHeaderUtil.getApplicationVersion(this.context));
    }

    private final String getSerializedBody(Object body, ObjectMapper mapper) {
        try {
            String strWriteValueAsString = mapper.writeValueAsString(body);
            Intrinsics.checkNotNullExpressionValue(strWriteValueAsString, "{\n            mapper.wri…eAsString(body)\n        }");
            return strWriteValueAsString;
        } catch (IOException e) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.error(TAG2, "Fail to serialize", e);
            return new String();
        }
    }
}
