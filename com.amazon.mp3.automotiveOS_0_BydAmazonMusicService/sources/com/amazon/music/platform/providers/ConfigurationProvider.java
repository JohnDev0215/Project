package com.amazon.music.platform.providers;

import kotlin.Metadata;

/* JADX INFO: compiled from: ConfigurationProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/amazon/music/platform/providers/ConfigurationProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "getBooleanForKey", "", "key", "", "getIntForKey", "", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface ConfigurationProvider extends InterfaceProvider {
    boolean getBooleanForKey(String key);

    int getIntForKey(String key);
}
