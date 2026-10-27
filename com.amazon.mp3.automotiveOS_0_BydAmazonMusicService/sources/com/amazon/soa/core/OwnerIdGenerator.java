package com.amazon.soa.core;

import android.net.Uri;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.util.UUID;

/* JADX INFO: loaded from: classes5.dex */
public final class OwnerIdGenerator {
    public static String forTemplateList() {
        return new Uri.Builder().scheme(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER).appendPath(UUID.randomUUID().toString().replace("-", "")).build().toString();
    }

    public static String forTemplateShard(final String templateListId) {
        return Uri.parse(templateListId).buildUpon().appendPath(UUID.randomUUID().toString().replace("-", "")).build().toString();
    }
}
