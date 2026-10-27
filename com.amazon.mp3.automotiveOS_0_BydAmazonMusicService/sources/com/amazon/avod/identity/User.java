package com.amazon.avod.identity;

import com.amazon.avod.util.DLog;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class User implements Serializable {
    private final String mAccountId;
    private final int mAndroidId;
    private final Role mRole;

    public enum Role {
        ADULT,
        CHILD,
        UNKNOWN
    }

    public User(@Nonnull Role role, @Nonnull String str, int i) {
        this.mRole = (Role) Preconditions.checkNotNull(role, "role");
        this.mAccountId = (String) Preconditions.checkNotNull(str, "accountId");
        this.mAndroidId = i;
    }

    @Nonnull
    public String getAccountId() {
        return this.mAccountId;
    }

    public int getAndroidId() {
        return this.mAndroidId;
    }

    public int hashCode() {
        return Objects.hashCode(this.mRole, this.mAccountId, Integer.valueOf(this.mAndroidId));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return Objects.equal(this.mRole, user.mRole) && Objects.equal(this.mAccountId, user.mAccountId) && Objects.equal(Integer.valueOf(this.mAndroidId), Integer.valueOf(user.mAndroidId));
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass().getSimpleName()).add("androidId", this.mAndroidId).add("accountId", DLog.maskString(this.mAccountId)).add("role", this.mRole).toString();
    }
}
