package com.amazon.avod.http.internal;

import com.amazon.avod.util.DLog;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.google.android.gms.common.Scopes;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class TokenKey implements Serializable {
    private final String mAccountDirectedId;
    private final String mProfileDirectedId;

    public TokenKey(@Nonnull String str, @Nullable String str2) {
        this.mAccountDirectedId = (String) Preconditions.checkNotNull(str, MAPAccountManager.KEY_SSO_ACCOUNT_DIRECTED_ID);
        this.mProfileDirectedId = str2;
    }

    @Nonnull
    public String getAccountDirectedId() {
        return this.mAccountDirectedId;
    }

    @Nullable
    public String getProfileDirectedId() {
        return this.mProfileDirectedId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TokenKey tokenKey = (TokenKey) obj;
        return Objects.equal(this.mAccountDirectedId, tokenKey.mAccountDirectedId) && Objects.equal(this.mProfileDirectedId, tokenKey.mProfileDirectedId);
    }

    public int hashCode() {
        return Objects.hashCode(this.mAccountDirectedId, this.mProfileDirectedId);
    }

    @Nonnull
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("account", DLog.maskString(this.mAccountDirectedId)).add(Scopes.PROFILE, DLog.maskString(this.mProfileDirectedId)).toString();
    }
}
