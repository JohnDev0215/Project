package io.branch.referral;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.android.gms.common.internal.ImagesContract;

/* JADX INFO: loaded from: classes7.dex */
public enum Defines$LinkParam {
    Tags("tags"),
    Alias("alias"),
    Type(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE),
    Duration("duration"),
    Channel("channel"),
    Feature("feature"),
    Stage("stage"),
    Campaign("campaign"),
    Data("data"),
    URL(ImagesContract.URL);

    private String key;

    Defines$LinkParam(String str) {
        this.key = str;
    }

    public String getKey() {
        return this.key;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.key;
    }
}
