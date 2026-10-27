package com.android.car.developeroptions.users;

import android.content.Context;
import android.os.UserHandle;
import android.os.UserManager;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class UserFeatureProviderImpl implements UserFeatureProvider {
    UserManager mUm;

    public UserFeatureProviderImpl(Context context) {
        this.mUm = (UserManager) context.getSystemService("user");
    }

    @Override // com.android.car.developeroptions.users.UserFeatureProvider
    public List<UserHandle> getUserProfiles() {
        return this.mUm.getUserProfiles();
    }
}
