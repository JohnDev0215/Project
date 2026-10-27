package com.amazon.music.connect.contacts.providers;

import android.util.Log;
import com.amazon.commsnetworking.api.INetworkRequest;
import com.amazon.commsnetworking.auth.AuthenticationProvider;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.AuthCookies;
import com.amazon.music.platform.providers.AuthenticationProvider;
import java.net.HttpCookie;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContactsAuthenticationProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/music/connect/contacts/providers/ContactsAuthenticationProvider;", "Lcom/amazon/commsnetworking/auth/AuthenticationProvider;", "authenticationProvider", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "(Lcom/amazon/music/platform/providers/AuthenticationProvider;)V", "TAG", "", "kotlin.jvm.PlatformType", "authenticateRequest", "", "networkRequest", "Lcom/amazon/commsnetworking/api/INetworkRequest;", "DMMConnect_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContactsAuthenticationProvider implements AuthenticationProvider {
    private final String TAG;
    private final com.amazon.music.platform.providers.AuthenticationProvider authenticationProvider;

    public ContactsAuthenticationProvider(com.amazon.music.platform.providers.AuthenticationProvider authenticationProvider) {
        Intrinsics.checkNotNullParameter(authenticationProvider, "authenticationProvider");
        this.authenticationProvider = authenticationProvider;
        this.TAG = ContactsAuthenticationProvider.class.getSimpleName();
    }

    @Override // com.amazon.commsnetworking.auth.AuthenticationProvider
    public void authenticateRequest(INetworkRequest networkRequest) {
        Intrinsics.checkNotNullParameter(networkRequest, "networkRequest");
        Outcome authCookiesBlocking$default = AuthenticationProvider.DefaultImpls.getAuthCookiesBlocking$default(this.authenticationProvider, null, 1, null);
        if (authCookiesBlocking$default instanceof Outcome.Success) {
            Outcome.Success success = (Outcome.Success) authCookiesBlocking$default;
            if (!((AuthCookies) success.getValue()).getCookies().isEmpty()) {
                networkRequest.withHeader("Cookie", CollectionsKt___CollectionsKt.joinToString$default(((AuthCookies) success.getValue()).getCookies(), "; ", null, null, 0, null, new Function1<HttpCookie, CharSequence>() { // from class: com.amazon.music.connect.contacts.providers.ContactsAuthenticationProvider$authenticateRequest$cookieHeaderValue$1
                    @Override // kotlin.jvm.functions.Function1
                    public final CharSequence invoke(HttpCookie it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        String string = it.toString();
                        Intrinsics.checkNotNullExpressionValue(string, "it.toString()");
                        return string;
                    }
                }, 30, null));
                return;
            }
            return;
        }
        Log.e(this.TAG, "Failed to fetch auth cookies from MAP");
    }
}
