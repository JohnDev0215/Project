package com.amazon.music.media.auto.provider.network;

import android.content.Context;
import com.amazon.music.media.auto.util.AutoLog;
import com.amazon.music.media.auto.util.PresetsUtil;
import com.amazon.music.platform.providers.FeatureGateProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONObject;

/* JADX INFO: compiled from: LibraryRequestProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/music/media/auto/provider/network/LibraryRequestProvider;", "Lcom/amazon/music/media/auto/provider/network/DefaultMESKRequestProvider;", "context", "Landroid/content/Context;", "featureGateProvider", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "(Landroid/content/Context;Lcom/amazon/music/platform/providers/FeatureGateProvider;)V", "buildBody", "Lokhttp3/RequestBody;", "meskRequestBody", "Lcom/amazon/music/media/auto/provider/network/MESKRequestBody;", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryRequestProvider extends DefaultMESKRequestProvider {
    private static final String TAG = LibraryRequestProvider.class.getSimpleName();
    private final Context context;
    private final FeatureGateProvider featureGateProvider;

    public LibraryRequestProvider(Context context, FeatureGateProvider featureGateProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.featureGateProvider = featureGateProvider;
    }

    @Override // com.amazon.music.media.auto.provider.network.DefaultMESKRequestProvider, com.amazon.music.media.auto.provider.network.MESKRequestProvider
    public RequestBody buildBody(MESKRequestBody meskRequestBody) throws Exception {
        Intrinsics.checkNotNullParameter(meskRequestBody, "meskRequestBody");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", meskRequestBody.getId());
            jSONObject.put("presetString", PresetsUtil.INSTANCE.readSharedPreferences(this.context, "added_presets_identifiers_key", this.featureGateProvider));
            MediaType mediaType = MediaType.INSTANCE.get("application/json; charset=utf-8");
            RequestBody.Companion companion = RequestBody.INSTANCE;
            String string = jSONObject.toString();
            Intrinsics.checkNotNullExpressionValue(string, "bodyObject.toString()");
            return companion.create(string, mediaType);
        } catch (Exception e) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.error(TAG2, "Failed to build request body", e);
            throw e;
        }
    }
}
