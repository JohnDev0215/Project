package com.amazon.avod.drm.db;

import android.content.Context;
import com.amazon.avod.db.DBOpenHelperSupplier;
import com.amazon.identity.auth.device.utils.AccountConstants;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class AndroidDrmContentRegistry implements DrmContentRegistry {
    private static final String[] CONTENT_TABLE_COLUMNS = {"asin", "drm_id", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "expiry", "view_hours"};
    private static final String[] OWNER_TABLE_COLUMNS = {"asin", "owner_id"};
    private final DBOpenHelperSupplier mDBOpenHelperSupplier;

    public AndroidDrmContentRegistry(Context context) {
        this.mDBOpenHelperSupplier = new DBOpenHelperSupplier(context, new DrmContentDatabaseUpgradeManager(), new DrmContentDatabase());
    }
}
