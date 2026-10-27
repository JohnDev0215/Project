package com.amazon.avod.http;

import com.amazon.avod.http.internal.TokenKey;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface PrimeVideoLegacyQOSHackAuthProvider {
    @Nullable
    @Deprecated
    TokenKey getTokenKeyForCurrentUser();
}
