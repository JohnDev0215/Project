package com.amazon.video.sdk;

import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: compiled from: AuthContext.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\u0006J(\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/video/sdk/AuthContext;", "", "getHeaders", "", "", "sessionContext", "InvalidSessionContextException", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface AuthContext {

    /* JADX INFO: compiled from: AuthContext.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/video/sdk/AuthContext$InvalidSessionContextException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class InvalidSessionContextException extends Exception {
    }

    Map<String, String> getHeaders(Map<String, String> sessionContext) throws InvalidSessionContextException;
}
