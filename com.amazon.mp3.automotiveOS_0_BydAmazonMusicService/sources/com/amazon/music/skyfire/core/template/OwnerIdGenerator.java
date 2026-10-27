package com.amazon.music.skyfire.core.template;

import android.net.Uri;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.util.UUID;

/* JADX INFO: loaded from: classes4.dex */
public final class OwnerIdGenerator {
    public static final String amconnect = "amconnect";
    public static final String watch = "watch";

    public static String forTemplateList() {
        return new Uri.Builder().scheme(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER).appendPath(UUID.randomUUID().toString().replace("-", "")).build().toString();
    }

    public static String forTemplateShard(String str) {
        return Uri.parse(str).buildUpon().appendPath(UUID.randomUUID().toString().replace("-", "")).build().toString();
    }
}
