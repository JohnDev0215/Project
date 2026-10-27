package com.amazon.mp3.catalog.fragment.provider;

import android.content.Context;
import android.util.Log;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.store.html5.map.MAPCookiesManager;
import com.amazon.music.connect.IdentityProvider;
import com.amazon.music.connect.firefly.FireFlyService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FireFlyServiceProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u0004\u0018\u00010\tJ\b\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/provider/FireFlyServiceProvider;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "API_KEY", "", "DEFAULT_SERVICE_ENDPOINT", "get", "Lcom/amazon/music/connect/firefly/FireFlyService;", "getIdentityProvider", "Lcom/amazon/music/connect/IdentityProvider;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FireFlyServiceProvider {
    private final String API_KEY;
    private final String DEFAULT_SERVICE_ENDPOINT;
    private final Context context;

    public FireFlyServiceProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.DEFAULT_SERVICE_ENDPOINT = "https://gql.music.amazon.dev/";
        this.API_KEY = "amzn1.application.d5f54ca32dbe412b8adca604f36814f9";
        this.context = context;
    }

    public final FireFlyService get() {
        return FireFlyService.INSTANCE.getInstance(getIdentityProvider(), this.DEFAULT_SERVICE_ENDPOINT, this.API_KEY);
    }

    private final IdentityProvider getIdentityProvider() {
        final AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(this.context);
        final AccountDetailUtil accountDetailUtil = AccountDetailUtil.get(this.context);
        return new IdentityProvider() { // from class: com.amazon.mp3.catalog.fragment.provider.FireFlyServiceProvider$getIdentityProvider$identityProvider$1
            @Override // com.amazon.music.connect.IdentityProvider
            public String getToken() {
                try {
                    return MAPCookiesManager.getCookiesManager().getToken();
                } catch (Exception e) {
                    Log.d("LikesServiceProxy", e.toString());
                    return null;
                }
            }

            @Override // com.amazon.music.connect.IdentityProvider
            public String getCustomerId() {
                AccountDetailUtil accountDetailUtil2 = accountDetailUtil;
                if (accountDetailUtil2 == null) {
                    return null;
                }
                return accountDetailUtil2.getCustomerId();
            }

            @Override // com.amazon.music.connect.IdentityProvider
            public String getDeviceId() {
                AccountCredentialStorage accountCredentialStorage2 = accountCredentialStorage;
                if (accountCredentialStorage2 == null) {
                    return null;
                }
                return accountCredentialStorage2.getDeviceId();
            }

            @Override // com.amazon.music.connect.IdentityProvider
            public String getDeviceType() {
                AccountCredentialStorage accountCredentialStorage2 = accountCredentialStorage;
                if (accountCredentialStorage2 == null) {
                    return null;
                }
                return accountCredentialStorage2.getDeviceType();
            }
        };
    }
}
