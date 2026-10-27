package com.spotify.connectivity.httpwebgate;

import com.spotify.base.java.logging.Logger;
import com.spotify.connectivity.httpwebgate.WebgateTokenProvider;
import java.nio.charset.Charset;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ResponseBody$Companion$asResponseBody$1;
import p.cs;
import p.fo;
import p.v03;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class WebgateAuthorizer implements Interceptor {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_PREFIX = "Bearer ";
    public static final int COSMOS_TIMEOUT_MS = 10000;
    public static final Companion Companion = new Companion(null);
    private final xy1 tokenManager;
    private final WebgateHelper webgateHelper;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getAUTHORIZATION_HEADER$annotations() {
        }

        public static /* synthetic */ void getAUTHORIZATION_PREFIX$annotations() {
        }

        public static /* synthetic */ void getCOSMOS_TIMEOUT_MS$annotations() {
        }
    }

    public WebgateAuthorizer(WebgateHelper webgateHelper, xy1 xy1Var) {
        v03.j(webgateHelper, "webgateHelper");
        v03.j(xy1Var, "tokenManager");
        this.webgateHelper = webgateHelper;
        this.tokenManager = xy1Var;
    }

    private final Response authenticatedRequest(Interceptor.Chain chain, Request request, String str) {
        Request.Builder builderB = request.b();
        builderB.a(AUTHORIZATION_HEADER, AUTHORIZATION_PREFIX + str);
        return chain.a(builderB.b());
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        String strA;
        v03.j(chain, "chain");
        Request requestC = chain.c();
        if (requestC.c.a("No-Webgate-Authentication") != null) {
            Request.Builder builderB = requestC.b();
            builderB.c.g("No-Webgate-Authentication");
            return chain.a(builderB.b());
        }
        if (requestC.a().j) {
            return chain.a(requestC);
        }
        if (!this.webgateHelper.isWebgateRequest(requestC) || this.webgateHelper.hasNoAuthTag(requestC) || ((strA = requestC.c.a(AUTHORIZATION_HEADER)) != null && strA.length() != 0)) {
            return chain.a(requestC);
        }
        try {
            Response responseAuthenticatedRequest = authenticatedRequest(chain, requestC, ((WebgateTokenProvider) this.tokenManager.get()).requestAccessToken(COSMOS_TIMEOUT_MS));
            if (responseAuthenticatedRequest.l != 401 || Response.c(responseAuthenticatedRequest, "client-token-error") != null) {
                return responseAuthenticatedRequest;
            }
            ResponseBody responseBody = responseAuthenticatedRequest.o;
            if (responseBody != null) {
                responseBody.close();
            }
            return authenticatedRequest(chain, requestC, ((WebgateTokenProvider) this.tokenManager.get()).requestAccessToken(COSMOS_TIMEOUT_MS, true));
        } catch (WebgateTokenProvider.WebgateTokenException e) {
            StringBuilder sb = new StringBuilder("Could not retrieve access token for a webgate request: ");
            HttpUrl httpUrl = requestC.a;
            sb.append(httpUrl);
            sb.append(" with error: ");
            sb.append(e.getMessage());
            String string = sb.toString();
            Logger.b("%s: %s %s", string, requestC.b, httpUrl);
            Response.Builder builder = new Response.Builder();
            builder.a = requestC;
            builder.c = 503;
            builder.b = Protocol.HTTP_1_1;
            ResponseBody.Companion companion = ResponseBody.e;
            MediaType.d.getClass();
            MediaType mediaTypeA = MediaType.Companion.a("plain/text");
            companion.getClass();
            v03.j(string, "<this>");
            Charset charset = cs.a;
            Charset charsetA = mediaTypeA.a(null);
            if (charsetA == null) {
                mediaTypeA = MediaType.Companion.b(mediaTypeA + "; charset=utf-8");
            } else {
                charset = charsetA;
            }
            fo foVar = new fo();
            v03.j(charset, "charset");
            foVar.c1(string, 0, string.length(), charset);
            builder.g = new ResponseBody$Companion$asResponseBody$1(mediaTypeA, foVar.e, foVar);
            builder.d = string;
            return builder.a();
        }
    }
}
