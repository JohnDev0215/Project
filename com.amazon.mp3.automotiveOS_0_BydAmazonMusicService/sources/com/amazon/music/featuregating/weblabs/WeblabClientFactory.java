package com.amazon.music.featuregating.weblabs;

import android.content.Context;
import android.os.Build;
import androidx.annotation.VisibleForTesting;
import com.amazon.weblab.mobile.IMobileWeblabClient;
import com.amazon.weblab.mobile.MobileWeblabClientFactory;
import com.amazon.weblab.mobile.settings.MobileWeblabClientAttributes;
import com.amazon.weblab.mobile.settings.MobileWeblabOS;
import com.amazon.weblab.mobile.settings.MobileWeblabRuntimeConfiguration;
import com.amazon.weblab.mobile.settings.MobileWeblabServiceEndpoint;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WeblabClientFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u001c\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tJ4\u0010\u0013\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J \u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/featuregating/weblabs/WeblabClientFactory;", "", "Lcom/amazon/music/featuregating/weblabs/WeblabClientInfo;", "clientInfo", "", "Lcom/amazon/music/featuregating/weblabs/WeblabDescriptor;", "weblabs", "Lcom/amazon/weblab/mobile/settings/MobileWeblabClientAttributes;", "getClientAttributes", "Landroid/content/Context;", "context", "Lcom/amazon/weblab/mobile/settings/MobileWeblabRuntimeConfiguration;", "getRuntimeConfiguration", "applicationContext", "", "isAuthenticated", "Lcom/amazon/music/featuregating/weblabs/WeblabCustomerInfo;", "customerInfo", "Lcom/amazon/weblab/mobile/IMobileWeblabClient;", "create", "", "validateWeblabData", "<init>", "()V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public final class WeblabClientFactory {
    public static final WeblabClientFactory INSTANCE = new WeblabClientFactory();

    private WeblabClientFactory() {
    }

    public final MobileWeblabClientAttributes getClientAttributes(WeblabClientInfo clientInfo, List<WeblabDescriptor> weblabs) {
        Intrinsics.checkNotNullParameter(clientInfo, "clientInfo");
        Intrinsics.checkNotNullParameter(weblabs, "weblabs");
        MobileWeblabClientAttributes mobileWeblabClientAttributes = new MobileWeblabClientAttributes(clientInfo.getClientIdentifier(), clientInfo.getAppName(), clientInfo.getAppVersion(), Intrinsics.areEqual("Amazon", Build.MANUFACTURER) ? MobileWeblabOS.FIRE_OS : MobileWeblabOS.ANDROID, clientInfo.getOsVersion());
        for (WeblabDescriptor weblabDescriptor : weblabs) {
            mobileWeblabClientAttributes.addWeblab(weblabDescriptor.getName(), weblabDescriptor.getDefaultTreatment().getTreatment());
        }
        return mobileWeblabClientAttributes;
    }

    public final MobileWeblabRuntimeConfiguration getRuntimeConfiguration(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        MobileWeblabRuntimeConfiguration mobileWeblabRuntimeConfiguration = new MobileWeblabRuntimeConfiguration(context.getDir("weblab", 0).getAbsolutePath());
        mobileWeblabRuntimeConfiguration.setEndpoint(MobileWeblabServiceEndpoint.PROD);
        mobileWeblabRuntimeConfiguration.setCleanUpAtInitEnabled(true);
        mobileWeblabRuntimeConfiguration.setUpdateAtInitEnabled(true);
        return mobileWeblabRuntimeConfiguration;
    }

    public final IMobileWeblabClient create(Context applicationContext, boolean isAuthenticated, WeblabCustomerInfo customerInfo, WeblabClientInfo clientInfo, List<WeblabDescriptor> weblabs) throws RuntimeException {
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        Intrinsics.checkNotNullParameter(customerInfo, "customerInfo");
        Intrinsics.checkNotNullParameter(clientInfo, "clientInfo");
        Intrinsics.checkNotNullParameter(weblabs, "weblabs");
        String strValidateWeblabData = validateWeblabData(isAuthenticated, customerInfo, clientInfo);
        if (!Intrinsics.areEqual(strValidateWeblabData, "clientCreateSuccess")) {
            new WeblabMetrics().clientInitFailed(strValidateWeblabData);
            throw new IllegalArgumentException(strValidateWeblabData);
        }
        IMobileWeblabClient iMobileWeblabClientCreateMobileWeblabClient = MobileWeblabClientFactory.createMobileWeblabClient(getClientAttributes(clientInfo, weblabs), getRuntimeConfiguration(applicationContext), customerInfo.getSessionId(), customerInfo.getMarketplace(), customerInfo.getDirectedId(), applicationContext);
        Intrinsics.checkNotNullExpressionValue(iMobileWeblabClientCreateMobileWeblabClient, "createMobileWeblabClient…licationContext\n        )");
        return iMobileWeblabClientCreateMobileWeblabClient;
    }

    @VisibleForTesting(otherwise = 2)
    public final String validateWeblabData(boolean isAuthenticated, WeblabCustomerInfo customerInfo, WeblabClientInfo clientInfo) {
        Intrinsics.checkNotNullParameter(customerInfo, "customerInfo");
        Intrinsics.checkNotNullParameter(clientInfo, "clientInfo");
        boolean z = true;
        if (customerInfo.getMarketplace().length() == 0) {
            return "clientCreateFailureNullMarket";
        }
        if (customerInfo.getSessionId().length() == 0) {
            return "clientCreateFailureEmptySessionId";
        }
        if (clientInfo.getAppName().length() == 0) {
            return "clientCreateFailureEmptyAppName";
        }
        String directedId = customerInfo.getDirectedId();
        if (directedId != null && directedId.length() != 0) {
            z = false;
        }
        return (z && isAuthenticated) ? "clientCreateFailureEmptyDirectedId" : "clientCreateSuccess";
    }
}
