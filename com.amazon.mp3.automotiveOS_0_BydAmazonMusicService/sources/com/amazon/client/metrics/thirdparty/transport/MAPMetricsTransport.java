package com.amazon.client.metrics.thirdparty.transport;

import android.content.Context;
import android.util.Log;
import com.amazon.client.metrics.thirdparty.configuration.HttpConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.HttpRequestSignerType;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.device.utils.thirdparty.DeviceUtil;
import com.amazon.identity.auth.device.api.AccountChangeEvent;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import com.amazon.identity.auth.device.api.AuthenticationType;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPInit;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class MAPMetricsTransport extends AbstractHTTPMetricsTransport {
    protected MetricsMAPAccountChangeObserver mAccountChangeObserver;
    protected MAPAccountManager mAccountManager;
    protected AuthenticationMethodFactory mAuthFactory;

    @Override // com.amazon.client.metrics.thirdparty.transport.AbstractHTTPMetricsTransport
    protected boolean signRequest(HttpURLConnection httpURLConnection) {
        return true;
    }

    public MAPMetricsTransport(Context context, MetricsConfiguration metricsConfiguration, DeviceUtil deviceUtil, MetricsTransport metricsTransport) {
        super(context, metricsConfiguration, deviceUtil, metricsTransport);
        MAPInit.getInstance(this.mContext).initialize();
        MAPAccountManager mAPAccountManager = new MAPAccountManager(this.mContext);
        this.mAccountManager = mAPAccountManager;
        this.mAuthFactory = new AuthenticationMethodFactory(this.mContext, mAPAccountManager.getAccount());
        MetricsMAPAccountChangeObserver metricsMAPAccountChangeObserver = new MetricsMAPAccountChangeObserver();
        this.mAccountChangeObserver = metricsMAPAccountChangeObserver;
        this.mAccountManager.registerAccountChangeObserver(metricsMAPAccountChangeObserver);
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.AbstractHTTPMetricsTransport
    protected HttpURLConnection openConnection(URL url) throws IOException {
        AuthenticationType authenticationType;
        HttpConfiguration httpConfiguration = this.mMetricsConfiguration.getHttpConfiguration();
        int i = AnonymousClass1.$SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType[httpConfiguration.getHttpRequestSignerType().ordinal()];
        if (i == 1) {
            authenticationType = AuthenticationType.OAuth;
        } else {
            if (i != 2) {
                if (i == 3) {
                    throw new IllegalArgumentException("OAUTH request signer type not supported without setting an OAuthHelper");
                }
                throw new IllegalArgumentException("Unknown request signer type: " + httpConfiguration.getHttpRequestSignerType());
            }
            authenticationType = AuthenticationType.ADPAuthenticator;
        }
        return AuthenticatedURLConnection.openConnection(url, this.mAuthFactory.newAuthenticationMethod(authenticationType));
    }

    /* JADX INFO: renamed from: com.amazon.client.metrics.thirdparty.transport.MAPMetricsTransport$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType;

        static {
            int[] iArr = new int[HttpRequestSignerType.values().length];
            $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType = iArr;
            try {
                iArr[HttpRequestSignerType.DCP_OAUTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType[HttpRequestSignerType.DCP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType[HttpRequestSignerType.OAUTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.MetricsTransport
    public void shutdown() {
        this.mAccountManager.deregisterAccountChangeObserver(this.mAccountChangeObserver);
    }

    protected class MetricsMAPAccountChangeObserver implements MAPAccountManager.MAPAccountChangeObserver {
        protected MetricsMAPAccountChangeObserver() {
        }

        @Override // com.amazon.identity.auth.device.api.MAPAccountManager.MAPAccountChangeObserver
        public void onAccountChange(AccountChangeEvent accountChangeEvent) {
            Log.i(MAPMetricsTransport.this.TAG, "Account changed, recreating AuthenticationMethodFactory with new credentials");
            MAPMetricsTransport mAPMetricsTransport = MAPMetricsTransport.this;
            mAPMetricsTransport.mAuthFactory = new AuthenticationMethodFactory(mAPMetricsTransport.mContext, accountChangeEvent.getCurrentAccount());
        }
    }
}
