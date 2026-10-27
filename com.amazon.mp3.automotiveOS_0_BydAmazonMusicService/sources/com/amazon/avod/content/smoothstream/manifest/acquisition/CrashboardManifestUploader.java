package com.amazon.avod.content.smoothstream.manifest.acquisition;

import android.util.Log;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class CrashboardManifestUploader implements ManifestUploader {
    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestUploader
    public void upload(@Nonnull List<PostManifestPayload> list) {
        Preconditions.checkNotNull(list, "payloads");
        StringBuilder sb = new StringBuilder();
        Iterator<PostManifestPayload> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toJSON());
        }
        Log.wtf("CrashboardsManifestUploader", sb.toString());
    }
}
