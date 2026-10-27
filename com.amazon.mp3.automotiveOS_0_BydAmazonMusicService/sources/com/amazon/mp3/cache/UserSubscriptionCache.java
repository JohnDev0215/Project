package com.amazon.mp3.cache;

import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: UserSubscriptionCache.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\b\u0010\u0003\u001a\u00020\u0002H\u0007J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002J\u0010\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004J\u0010\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010\nR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/cache/UserSubscriptionCache;", "", "", "clearCache", "", "getUpdatedNightwingOnlyValue", "getUpdatedKatanaValue", "shouldUseCache", "isNightwingOnly", "isKatana", "Ljava/lang/Boolean;", "Lcom/amazon/music/account/AccountStateChangeListener;", "accountStateChangeListener", "Lcom/amazon/music/account/AccountStateChangeListener;", "getAccountStateChangeListener", "()Lcom/amazon/music/account/AccountStateChangeListener;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class UserSubscriptionCache {
    public static final UserSubscriptionCache INSTANCE = new UserSubscriptionCache();
    private static final AccountStateChangeListener accountStateChangeListener;
    private static Boolean isKatana;
    private static Boolean isNightwingOnly;

    private UserSubscriptionCache() {
    }

    static {
        AccountStateChangeListener accountStateChangeListener2 = new AccountStateChangeListener() { // from class: com.amazon.mp3.cache.UserSubscriptionCache$$ExternalSyntheticLambda0
            @Override // com.amazon.music.account.AccountStateChangeListener
            public final void onAccountStateChange(Set set) {
                UserSubscriptionCache.clearCache();
            }
        };
        accountStateChangeListener = accountStateChangeListener2;
        AccountManagerSingleton.get().registerListener(accountStateChangeListener2);
    }

    @JvmStatic
    public static final synchronized void clearCache() {
        isNightwingOnly = null;
        isKatana = null;
    }

    public final synchronized boolean isNightwingOnly(boolean shouldUseCache) {
        Boolean bool = isNightwingOnly;
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (shouldUseCache) {
                return zBooleanValue;
            }
        }
        return getUpdatedNightwingOnlyValue();
    }

    public final synchronized boolean isKatana(boolean shouldUseCache) {
        Boolean bool = isKatana;
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (shouldUseCache) {
                return zBooleanValue;
            }
        }
        return getUpdatedKatanaValue();
    }

    private final boolean getUpdatedNightwingOnlyValue() {
        boolean zIsNightwingOnly = UserSubscriptionUtil.isNightwingOnly();
        isNightwingOnly = Boolean.valueOf(zIsNightwingOnly);
        return zIsNightwingOnly;
    }

    private final boolean getUpdatedKatanaValue() {
        boolean zIsKatana = UserSubscriptionUtil.getUserSubscription().isKatana();
        isKatana = Boolean.valueOf(zIsKatana);
        return zIsKatana;
    }
}
