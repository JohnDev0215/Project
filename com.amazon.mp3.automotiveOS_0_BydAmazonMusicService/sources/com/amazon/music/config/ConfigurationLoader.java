package com.amazon.music.config;

import android.content.Context;
import java.io.File;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public interface ConfigurationLoader {
    String getEncryptedConfigFile(JSONObject jSONObject);

    JSONObject loadFromDiskCache(File file);

    JSONObject loadFromRemoteService(String str);

    void loadPreloadedAssociatedTag(Context context);
}
