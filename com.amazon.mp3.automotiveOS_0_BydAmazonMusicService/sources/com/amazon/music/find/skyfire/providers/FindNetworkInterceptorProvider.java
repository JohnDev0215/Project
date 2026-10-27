package com.amazon.music.find.skyfire.providers;

import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.skyfire.core.network.AuthenticationMissingException;
import com.amazon.music.skyfire.core.network.NetworkInterceptor;
import com.amazon.music.skyfire.core.network.NetworkTaskError;
import com.amazon.music.skyfire.core.network.ValidationResult;
import java.net.ConnectException;
import kotlin.Metadata;
import okhttp3.Headers;

/* JADX INFO: compiled from: FindNetworkInterceptorProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u001c\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u001c\u0010\f\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/find/skyfire/providers/FindNetworkInterceptorProvider;", "Lcom/amazon/music/skyfire/core/network/NetworkInterceptor;", "()V", "forceAuthentication", "", "p0", "", "onError", "", "skillPath", "error", "Lcom/amazon/music/skyfire/core/network/NetworkTaskError;", "validateRequest", "Lcom/amazon/music/skyfire/core/network/ValidationResult;", "headers", "Lokhttp3/Headers;", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FindNetworkInterceptorProvider implements NetworkInterceptor {
    public static final String AUTHENTICATION_HEADER = "x-amzn-authentication";
    private static final String FLEX_EVENT_NAME = "findPageError";

    @Override // com.amazon.music.skyfire.core.network.NetworkInterceptor
    public boolean forceAuthentication(String p0) {
        return true;
    }

    @Override // com.amazon.music.skyfire.core.network.NetworkInterceptor
    public void onError(String skillPath, NetworkTaskError error) {
        if ((skillPath == null || skillPath.length() == 0) || error == null) {
            return;
        }
        MetricsHolder.getManager().handleEvent(FlexEvent.builder(FLEX_EVENT_NAME).withFlexStr1(skillPath).withFlexStr2(error.label).build());
    }

    @Override // com.amazon.music.skyfire.core.network.NetworkInterceptor
    public ValidationResult validateRequest(String skillPath, Headers headers) {
        if (skillPath == null || skillPath.length() == 0) {
            return new ValidationResult(false, new ConnectException());
        }
        if (forceAuthentication(skillPath)) {
            String str = headers == null ? null : headers.get(AUTHENTICATION_HEADER);
            if (str == null || str.length() == 0) {
                return new ValidationResult(false, new AuthenticationMissingException("Authentication header is required to make skill request"));
            }
        }
        return new ValidationResult(true, null);
    }
}
