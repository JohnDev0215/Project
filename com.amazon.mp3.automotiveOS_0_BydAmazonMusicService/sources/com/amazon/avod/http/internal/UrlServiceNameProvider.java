package com.amazon.avod.http.internal;

import com.amazon.avod.http.MetricEventListener;
import com.amazon.bolthttp.Request;
import com.google.common.base.Joiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class UrlServiceNameProvider implements MetricEventListener.ServiceNameProvider {
    private final Pattern mRemoteTransformApiNamePattern = Pattern.compile("//(.*/)?getDataByTransform/v1/([^?]+)/?\\?");
    private final Pattern mApiNamePattern = Pattern.compile("//(.*/)?([^/]+/[^/]+)/?\\?");

    @Override // com.amazon.avod.http.MetricEventListener.ServiceNameProvider
    @Nullable
    public String getApiShortName(@Nonnull Request<?> request) {
        String string = request.getUrl().toString();
        Matcher matcher = this.mRemoteTransformApiNamePattern.matcher(string);
        if (matcher.find()) {
            return matcher.group(2);
        }
        Matcher matcher2 = this.mApiNamePattern.matcher(string);
        if (!matcher2.find()) {
            return null;
        }
        if (request.getMethod() == Request.HttpMethod.GET) {
            return matcher2.group(2);
        }
        return Joiner.on('-').join(request.getMethod().name(), matcher2.group(2), new Object[0]);
    }
}
