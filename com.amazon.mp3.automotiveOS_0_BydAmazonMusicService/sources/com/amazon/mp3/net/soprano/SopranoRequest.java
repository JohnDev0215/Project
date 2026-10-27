package com.amazon.mp3.net.soprano;

import com.amazon.mp3.net.BaseRequestDispatcher;
import com.amazon.mp3.net.service.ServiceApi;
import com.amazon.mp3.net.service.ServiceRequest;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: SopranoRequest.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\bH\u0016R\u0014\u0010\u000b\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/net/soprano/SopranoRequest;", "Lcom/amazon/mp3/net/service/ServiceRequest;", "", "getRequestName", "", "getApiVersion", "Lcom/amazon/mp3/net/BaseRequestDispatcher;", "getDispatcher", "Lorg/json/JSONObject;", "requestParams", "execute", "requestName", "Ljava/lang/String;", "sDispatcher", "Lcom/amazon/mp3/net/BaseRequestDispatcher;", "<init>", "(Ljava/lang/String;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class SopranoRequest implements ServiceRequest {
    private final String requestName;
    private BaseRequestDispatcher sDispatcher;

    /* JADX INFO: renamed from: getApiVersion, reason: collision with other method in class */
    public Void m1358getApiVersion() {
        return null;
    }

    public SopranoRequest(String requestName) {
        Intrinsics.checkNotNullParameter(requestName, "requestName");
        this.requestName = requestName;
    }

    @Override // com.amazon.mp3.net.service.ServiceRequest
    public /* bridge */ /* synthetic */ ServiceApi getApiVersion() {
        return (ServiceApi) m1358getApiVersion();
    }

    @Override // com.amazon.mp3.net.service.ServiceRequest
    public String getRequestName() {
        return this.requestName;
    }

    public BaseRequestDispatcher getDispatcher() {
        if (this.sDispatcher == null) {
            this.sDispatcher = new SopranoRequestDispatcher();
        }
        BaseRequestDispatcher baseRequestDispatcher = this.sDispatcher;
        if (baseRequestDispatcher != null) {
            return baseRequestDispatcher;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.net.BaseRequestDispatcher");
    }

    @Override // com.amazon.mp3.net.service.ServiceRequest
    public JSONObject execute(JSONObject requestParams) {
        Intrinsics.checkNotNullParameter(requestParams, "requestParams");
        return getDispatcher().dispatch(this, requestParams);
    }
}
