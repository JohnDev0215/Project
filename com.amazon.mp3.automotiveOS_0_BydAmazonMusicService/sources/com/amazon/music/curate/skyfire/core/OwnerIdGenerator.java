package com.amazon.music.curate.skyfire.core;

import android.net.Uri;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.util.UUID;

/* JADX INFO: loaded from: classes4.dex */
public final class OwnerIdGenerator {
    public static String forTemplateContainer() {
        return new Uri.Builder().scheme(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER).appendPath(UUID.randomUUID().toString().replace("-", "")).build().toString();
    }

    public static String forTemplate(final String templateContainerId) {
        return Uri.parse(templateContainerId).buildUpon().appendPath(UUID.randomUUID().toString().replace("-", "")).build().toString();
    }
}
