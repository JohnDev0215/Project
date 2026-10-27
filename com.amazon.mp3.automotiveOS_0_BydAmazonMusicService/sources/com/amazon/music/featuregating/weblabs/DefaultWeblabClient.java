package com.amazon.music.featuregating.weblabs;

import android.content.Context;
import android.util.Log;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.weblab.mobile.IMobileWeblab;
import com.amazon.weblab.mobile.IMobileWeblabClient;
import com.amazon.weblab.mobile.model.MobileWeblabException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultWeblabClient.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 %2\u00020\u0001:\u0001%B=\u0012\u0006\u0010\u001c\u001a\u00020\u001b\u0012\u0006\u0010\u001d\u001a\u00020\u0006\u0012\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e\u0012\u0006\u0010\u001a\u001a\u00020\u0014\u0012\u0006\u0010\"\u001a\u00020!\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b#\u0010$J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R$\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00148V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006&"}, d2 = {"Lcom/amazon/music/featuregating/weblabs/DefaultWeblabClient;", "Lcom/amazon/music/featuregating/weblabs/WeblabClient;", "", "weblabName", "Lcom/amazon/music/featuregating/weblabs/WeblabTreatmentSnapshot;", "treatmentSnapshot", "", "asynchronous", "", "triggerUpdate", "Lcom/amazon/music/platform/data/WeblabTreatment;", "treatment", "lockWeblab", "unlockWeblab", "Lcom/amazon/music/featuregating/weblabs/WeblabMetrics;", "metrics", "Lcom/amazon/music/featuregating/weblabs/WeblabMetrics;", "Lcom/amazon/weblab/mobile/IMobileWeblabClient;", SearchMetricsServiceDefault.CONTENT_SRC_CLIENT, "Lcom/amazon/weblab/mobile/IMobileWeblabClient;", "Lcom/amazon/music/featuregating/weblabs/WeblabCustomerInfo;", "value", "getCustomerInfo", "()Lcom/amazon/music/featuregating/weblabs/WeblabCustomerInfo;", "setCustomerInfo", "(Lcom/amazon/music/featuregating/weblabs/WeblabCustomerInfo;)V", "customerInfo", "Landroid/content/Context;", "context", "isAuthenticated", "", "Lcom/amazon/music/featuregating/weblabs/WeblabDescriptor;", "weblabs", "Lcom/amazon/music/featuregating/weblabs/WeblabClientInfo;", "clientInfo", "<init>", "(Landroid/content/Context;ZLjava/util/List;Lcom/amazon/music/featuregating/weblabs/WeblabCustomerInfo;Lcom/amazon/music/featuregating/weblabs/WeblabClientInfo;Lcom/amazon/music/featuregating/weblabs/WeblabMetrics;)V", "Companion", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public final class DefaultWeblabClient implements WeblabClient {
    private static final String TAG = DefaultWeblabClient.class.getSimpleName();
    private final IMobileWeblabClient client;
    private final WeblabMetrics metrics;

    public DefaultWeblabClient(Context context, boolean z, List<WeblabDescriptor> weblabs, WeblabCustomerInfo customerInfo, WeblabClientInfo clientInfo, WeblabMetrics metrics) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(weblabs, "weblabs");
        Intrinsics.checkNotNullParameter(customerInfo, "customerInfo");
        Intrinsics.checkNotNullParameter(clientInfo, "clientInfo");
        Intrinsics.checkNotNullParameter(metrics, "metrics");
        this.metrics = metrics;
        this.client = WeblabClientFactory.INSTANCE.create(context, z, customerInfo, clientInfo, weblabs);
    }

    @Override // com.amazon.music.featuregating.weblabs.WeblabClient
    public WeblabCustomerInfo getCustomerInfo() {
        String marketplace = this.client.getMarketplace();
        Intrinsics.checkNotNullExpressionValue(marketplace, "client.marketplace");
        String session = this.client.getSession();
        Intrinsics.checkNotNullExpressionValue(session, "client.session");
        return new WeblabCustomerInfo(marketplace, session, this.client.getDirectedId());
    }

    @Override // com.amazon.music.featuregating.weblabs.WeblabClient
    public void setCustomerInfo(WeblabCustomerInfo value) {
        Intrinsics.checkNotNullParameter(value, "value");
        IMobileWeblabClient iMobileWeblabClient = this.client;
        iMobileWeblabClient.setDirectedId(value.getDirectedId());
        iMobileWeblabClient.setSessionAndMarketplaceId(value.getSessionId(), value.getMarketplace());
    }

    @Override // com.amazon.music.featuregating.weblabs.WeblabClient
    public WeblabTreatmentSnapshot treatmentSnapshot(String weblabName) throws IllegalStateException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(weblabName, "weblabName");
        IMobileWeblab weblab = this.client.getWeblab(weblabName);
        if (weblab == null) {
            return null;
        }
        return new WeblabTreatmentSnapshotImpl(weblab);
    }

    @Override // com.amazon.music.featuregating.weblabs.WeblabClient
    public synchronized void triggerUpdate(boolean asynchronous) {
        try {
            if (asynchronous) {
                this.client.updateAsync();
            } else {
                this.client.update();
            }
        } catch (Exception e) {
            if (e instanceof MobileWeblabException) {
                Log.e(TAG, "Client update failed with MobileWeblabException");
            }
            this.metrics.trackException(TAG, "triggerUpdate", e);
        }
    }

    @Override // com.amazon.music.featuregating.weblabs.WeblabClient
    public void lockWeblab(String weblabName, WeblabTreatment treatment) {
        Intrinsics.checkNotNullParameter(weblabName, "weblabName");
        Intrinsics.checkNotNullParameter(treatment, "treatment");
        this.client.lockWeblab(weblabName, treatment.toString());
    }

    @Override // com.amazon.music.featuregating.weblabs.WeblabClient
    public void unlockWeblab(String weblabName) {
        Intrinsics.checkNotNullParameter(weblabName, "weblabName");
        this.client.unlockWeblab(weblabName);
    }
}
