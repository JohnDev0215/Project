package com.amazon.music.platform.providers;

import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.AdpInfo;
import com.amazon.music.platform.data.AuthCookies;
import com.amazon.music.platform.data.OAuthInfo;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: AuthenticationProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H¦@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H&J\n\u0010\b\u001a\u0004\u0018\u00010\u0007H&J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0002H&J\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u00022\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007H&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/platform/providers/AuthenticationProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "Lcom/amazon/music/platform/Outcome;", "Lcom/amazon/music/platform/data/OAuthInfo;", "getOAuthInfo", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOAuthInfoBlocking", "", "getAccessTokenBlocking", "Lcom/amazon/music/platform/data/AdpInfo;", "getAdpInfoBlocking", "domain", "Lcom/amazon/music/platform/data/AuthCookies;", "getAuthCookiesBlocking", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface AuthenticationProvider extends InterfaceProvider {
    String getAccessTokenBlocking();

    Outcome<AdpInfo> getAdpInfoBlocking();

    Outcome<AuthCookies> getAuthCookiesBlocking(String domain);

    Object getOAuthInfo(Continuation<? super Outcome<OAuthInfo>> continuation);

    Outcome<OAuthInfo> getOAuthInfoBlocking();

    /* JADX INFO: compiled from: AuthenticationProvider.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Outcome getAuthCookiesBlocking$default(AuthenticationProvider authenticationProvider, String str, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getAuthCookiesBlocking");
            }
            if ((i & 1) != 0) {
                str = null;
            }
            return authenticationProvider.getAuthCookiesBlocking(str);
        }
    }
}
