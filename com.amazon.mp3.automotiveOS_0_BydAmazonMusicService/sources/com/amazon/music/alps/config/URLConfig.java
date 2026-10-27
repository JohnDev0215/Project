package com.amazon.music.alps.config;

import android.util.Log;
import com.amazon.music.alps.liveevent.ktx.StringKt;
import com.amazon.music.featuregating.rules.URLConfigurationValue;
import java.net.URL;
import kotlin.Metadata;

/* JADX INFO: compiled from: URLConfig.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0003R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/alps/config/URLConfig;", "", "key", "", "defaultValue", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getValue", "SHARE_BASE_URL", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum URLConfig {
    SHARE_BASE_URL("share_base_url", "https://music.amazon.com/");

    private final String defaultValue;
    private final String key;

    URLConfig(String str, String str2) {
        this.key = str;
        this.defaultValue = str2;
    }

    public final String getValue() {
        try {
            URL value = new URLConfigurationValue(this.key).getValue();
            return StringKt.orDefault(value == null ? null : value.toString(), this.defaultValue);
        } catch (Exception unused) {
            Log.e(URLConfig.class.getSimpleName(), "Unable to get URL value by key");
            return this.defaultValue;
        }
    }
}
