package com.neusoft.na.data.collection;

import android.util.Log;
import cerence.ark.assistant.library.JsonKey;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

/* JADX INFO: compiled from: NetWorkManger.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0006\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\u0011¨\u0006\u001a"}, d2 = {"Lcom/neusoft/na/data/collection/NetWorkManger;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "client", "Lokhttp3/OkHttpClient;", "getClient", "()Lokhttp3/OkHttpClient;", "setClient", "(Lokhttp3/OkHttpClient;)V", "dataAcquisitionTestPath", "mKey", "getMKey", "setMKey", "(Ljava/lang/String;)V", "mUrl", "getMUrl", "setMUrl", "send", "", JsonKey.RESULTS_DATA, "Lorg/json/JSONObject;", "Companion", "data_collection_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NetWorkManger {
    private static NetWorkManger instance;
    private final String TAG = "NetWorkManger";
    private OkHttpClient client = new OkHttpClient();
    private final String dataAcquisitionTestPath = "/sdcard/naviexternconfig/dataAcquisitionTest.xj";
    private String mKey;
    private String mUrl;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String debugUrl = "https://st.api.oneanalysis.neusoftauto.com/collection/datatracker";
    private static final String debugKey = "XRRANWOnSJ5VPQjXMF4Sw2defnS43ZCy3n8vbVo8";
    private static final String releaseUrl = "https://api.oneanalysis.neusoftauto.com/collection/datatracker";
    private static final String releaseKey = "UiNMDfzFJPi07YyB0iA41cEvy6aVRVu1DoYaNQ29";

    public NetWorkManger() {
        this.mUrl = "";
        this.mKey = "";
        if (new File("/sdcard/naviexternconfig/dataAcquisitionTest.xj").exists()) {
            Log.i("NetWorkManger", "NetWorkManger：debug");
            this.mUrl = debugUrl;
            this.mKey = debugKey;
        } else {
            Log.i("NetWorkManger", "NetWorkManger：release");
            this.mUrl = releaseUrl;
            this.mKey = releaseKey;
        }
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final OkHttpClient getClient() {
        return this.client;
    }

    public final void setClient(OkHttpClient okHttpClient) {
        Intrinsics.checkNotNullParameter(okHttpClient, "<set-?>");
        this.client = okHttpClient;
    }

    public final String getMUrl() {
        return this.mUrl;
    }

    public final void setMUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mUrl = str;
    }

    public final String getMKey() {
        return this.mKey;
    }

    public final void setMKey(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mKey = str;
    }

    /* JADX INFO: compiled from: NetWorkManger.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u0004\u0018\u00010\nR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/neusoft/na/data/collection/NetWorkManger$Companion;", "", "()V", "debugKey", "", "getDebugKey", "()Ljava/lang/String;", "debugUrl", "getDebugUrl", "instance", "Lcom/neusoft/na/data/collection/NetWorkManger;", "releaseKey", "getReleaseKey", "releaseUrl", "getReleaseUrl", "getInstance", "data_collection_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getDebugUrl() {
            return NetWorkManger.debugUrl;
        }

        public final String getDebugKey() {
            return NetWorkManger.debugKey;
        }

        public final String getReleaseUrl() {
            return NetWorkManger.releaseUrl;
        }

        public final String getReleaseKey() {
            return NetWorkManger.releaseKey;
        }

        public final NetWorkManger getInstance() {
            if (NetWorkManger.instance == null) {
                NetWorkManger.instance = new NetWorkManger();
            }
            return NetWorkManger.instance;
        }
    }

    public final void send(JSONObject data) {
        Intrinsics.checkNotNullParameter(data, "data");
        Log.i(this.TAG, data.toString());
        Request.Builder builderAddHeader = new Request.Builder().url(this.mUrl).addHeader("Content-Type", "application/json; charset=UTF-8").addHeader("x-api-key", this.mKey);
        RequestBody.Companion companion = RequestBody.INSTANCE;
        String string = data.toString();
        Intrinsics.checkNotNullExpressionValue(string, "data.toString()");
        this.client.newCall(builderAddHeader.post(companion.create((MediaType) null, string)).build()).enqueue(new Callback() { // from class: com.neusoft.na.data.collection.NetWorkManger.send.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                Log.i(NetWorkManger.this.getTAG(), "onFailure:" + e.getMessage());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                Log.i(NetWorkManger.this.getTAG(), "onResponse" + response.code());
            }
        });
    }
}
