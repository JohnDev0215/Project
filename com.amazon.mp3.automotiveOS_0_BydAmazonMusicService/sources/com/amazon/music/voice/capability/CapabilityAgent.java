package com.amazon.music.voice.capability;

import android.os.Looper;
import com.amazon.alexa.voice.core.http.TLSv12HttpClient;
import com.amazon.alexa.voice.core.processor.superbowl.AccessTokenProvider;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.aom.Capability;
import com.amazon.music.platform.aom.Configuration;
import com.amazon.music.platform.aom.launcher.Catalog;
import com.amazon.music.platform.aom.launcher.CatalogType;
import com.amazon.music.platform.aom.launcher.IdentifierType;
import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CapabilityAgent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0016\u0010\u000f\u001a\u00020\u00102\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002J\u0014\u0010\u0011\u001a\u00020\u00122\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/voice/capability/CapabilityAgent;", "", "accessTokenProvider", "Lcom/amazon/alexa/voice/core/processor/superbowl/AccessTokenProvider;", "(Lcom/amazon/alexa/voice/core/processor/superbowl/AccessTokenProvider;)V", "httpClient", "Lokhttp3/OkHttpClient;", "createCapabilitiesArray", "Lorg/json/JSONArray;", "capabilities", "", "Lcom/amazon/music/platform/aom/Capability;", "createCatalogsArray", "configuration", "Lcom/amazon/music/platform/aom/Configuration;", "createRequestBodyMetadata", "Lorg/json/JSONObject;", "registerCapabilities", "", "", "Companion", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CapabilityAgent {
    private static final Logger LOG = LoggerFactory.getLogger(Reflection.getOrCreateKotlinClass(CapabilityAgent.class).getSimpleName());
    private final AccessTokenProvider accessTokenProvider;
    private OkHttpClient httpClient;

    public CapabilityAgent(AccessTokenProvider accessTokenProvider) {
        Intrinsics.checkNotNullParameter(accessTokenProvider, "accessTokenProvider");
        this.accessTokenProvider = accessTokenProvider;
    }

    public final void registerCapabilities(List<Capability> capabilities) throws Exception {
        Intrinsics.checkNotNullParameter(capabilities, "capabilities");
        if (!(!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper()))) {
            throw new IllegalStateException("You must call this method on a background thread".toString());
        }
        if (!(!capabilities.isEmpty())) {
            throw new IllegalStateException("Capabilities cannot be empty".toString());
        }
        String accessToken = this.accessTokenProvider.getAccessToken();
        if (accessToken == null) {
            throw new IllegalArgumentException("accessToken == null".toString());
        }
        String string = createRequestBodyMetadata(capabilities).toString();
        Intrinsics.checkNotNullExpressionValue(string, "createRequestBodyMetadata(capabilities).toString()");
        RequestBody requestBodyCreate = RequestBody.INSTANCE.create(string, MediaType.INSTANCE.get("application/json; charset=utf-8"));
        Request requestBuild = new Request.Builder().url("https://api.amazonalexa.com/v1/devices/@self/capabilities").put(requestBodyCreate).addHeader(HttpHeader.CONTENT_LENGTH, String.valueOf(requestBodyCreate.contentLength())).addHeader("x-amz-access-token", accessToken).addHeader(HttpHeader.CONTENT_TYPE, "application/json").build();
        if (this.httpClient == null) {
            OkHttpClient okHttpClientCreate = TLSv12HttpClient.create();
            Intrinsics.checkNotNullExpressionValue(okHttpClientCreate, "create()");
            this.httpClient = okHttpClientCreate;
        }
        OkHttpClient okHttpClient = this.httpClient;
        if (okHttpClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("httpClient");
            okHttpClient = null;
        }
        Response responseExecute = FirebasePerfOkHttpClient.execute(okHttpClient.newCall(requestBuild));
        try {
            int iCode = responseExecute.code();
            String strMessage = responseExecute.message();
            if (!responseExecute.isSuccessful()) {
                Logger logger = LOG;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("Capability registration failed with response code %d and message %s", Arrays.copyOf(new Object[]{Integer.valueOf(iCode), strMessage}, 2));
                Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                logger.info(str);
                String str2 = String.format("Capability registration failed with response code %d and message %s", Arrays.copyOf(new Object[]{Integer.valueOf(iCode), strMessage}, 2));
                Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
                throw new Exception(str2);
            }
            LOG.info("Capability registration Successful");
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(responseExecute, null);
        } finally {
        }
    }

    private final JSONObject createRequestBodyMetadata(Collection<Capability> capabilities) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("envelopeVersion", "20160207");
            jSONObject.put("capabilities", createCapabilitiesArray(capabilities));
            return jSONObject;
        } catch (JSONException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private final JSONArray createCapabilitiesArray(Collection<Capability> capabilities) {
        try {
            JSONArray jSONArray = new JSONArray();
            for (Capability capability : capabilities) {
                String capabilityInterface = capability.getCapabilityInterface();
                String capabilityType = capability.getCapabilityType();
                String capabilityVersion = capability.getCapabilityVersion();
                Configuration configuration = capability.getConfiguration();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, capabilityType);
                jSONObject.put(ContextMappingConstants.INTERFACE, capabilityInterface);
                jSONObject.put("version", capabilityVersion);
                if (configuration != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("catalogs", createCatalogsArray(configuration));
                    jSONObject.put("configurations", jSONObject2);
                }
                jSONArray.put(jSONObject);
            }
            return jSONArray;
        } catch (JSONException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private final JSONArray createCatalogsArray(Configuration configuration) {
        JSONArray jSONArray = new JSONArray();
        for (Catalog catalog : configuration.getCatalogs()) {
            CatalogType type = catalog.getType();
            List<IdentifierType> listComponent2 = catalog.component2();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, type.name());
            JSONArray jSONArray2 = new JSONArray();
            Iterator<IdentifierType> it = listComponent2.iterator();
            while (it.hasNext()) {
                jSONArray2.put(it.next().name());
            }
            jSONObject.put("identifierTypes", jSONArray2);
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }
}
