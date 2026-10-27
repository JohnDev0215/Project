package com.amazon.avod.media.downloadservice.internal;

import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes2.dex */
public class QAHttpRequestThrottlingInterceptor implements Interceptor {
    private static String DASH_MANIFEST_INDICATOR = ".mpd";
    private static String FRAGMENT_INDICATOR = ".mp4";
    private static String SS_MANIFEST_INDICATOR = ".ism";
    private static int sFragmentResponseStatusCode = 200;
    private static int sManifestResponseStatusCode = 200;
    private static boolean sShouldFailFragmentRequests = false;
    private static boolean sShouldFailManifestRequests = false;

    static void setShouldFailAllRequests(boolean z, int i) {
        if (z) {
            sShouldFailManifestRequests = true;
            sShouldFailFragmentRequests = true;
        } else {
            sShouldFailManifestRequests = false;
            sShouldFailFragmentRequests = false;
        }
        sManifestResponseStatusCode = i;
        sFragmentResponseStatusCode = i;
    }

    static void setShouldFailManifestRequests(boolean z, int i) {
        sShouldFailManifestRequests = z;
        sManifestResponseStatusCode = i;
    }

    static void setShouldFailFragmentsRequests(boolean z, int i) {
        sShouldFailFragmentRequests = z;
        sFragmentResponseStatusCode = i;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(@Nonnull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        String url = request.url().getUrl();
        if (sShouldFailManifestRequests && isManifestUrl(url)) {
            return interceptWithFailure(chain, request, sManifestResponseStatusCode);
        }
        if (sShouldFailFragmentRequests && isFragmentUrl(url)) {
            return interceptWithFailure(chain, request, sFragmentResponseStatusCode);
        }
        return chain.proceed(request);
    }

    private Response interceptWithFailure(@Nonnull Interceptor.Chain chain, @Nonnull Request request, int i) throws IOException {
        Preconditions.checkNotNull(chain, "chain");
        Preconditions.checkNotNull(request, "request");
        Response responseProceed = chain.proceed(request);
        ResponseBody responseBodyBody = responseProceed.body();
        try {
            Response responseBuild = new Response.Builder().request(request).protocol(responseProceed.protocol()).message("QA interceptor modified this response!").code(i).body(ResponseBody.create(responseBodyBody.contentType(), responseBodyBody.bytes())).build();
            responseBodyBody.close();
            return responseBuild;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (responseBodyBody != null) {
                    try {
                        responseBodyBody.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private boolean isManifestUrl(@Nonnull String str) {
        Preconditions.checkNotNull(str, ImagesContract.URL);
        return str.contains(DASH_MANIFEST_INDICATOR) || str.contains(SS_MANIFEST_INDICATOR);
    }

    private boolean isFragmentUrl(@Nonnull String str) {
        Preconditions.checkNotNull(str, ImagesContract.URL);
        return str.contains(FRAGMENT_INDICATOR);
    }
}
