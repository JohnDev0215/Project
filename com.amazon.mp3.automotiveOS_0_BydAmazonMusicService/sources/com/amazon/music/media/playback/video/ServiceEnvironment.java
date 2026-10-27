package com.amazon.music.media.playback.video;

import com.amazon.mp3.configuration.IntlConfiguration;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ServiceEnvironment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003R\u001e\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/music/media/playback/video/ServiceEnvironment;", "", "name", "", "(Ljava/lang/String;)V", "content", "", "getContent", "()Ljava/util/Map;", "contents", "getUrl", IntlConfiguration.MARKETPLACE, "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class ServiceEnvironment {
    private final Map<String, String> contents;
    private final String name;

    protected abstract Map<String, String> getContent();

    public ServiceEnvironment(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.contents = getContent();
    }

    public final String getUrl(String marketplace) {
        String str = this.contents.get(marketplace);
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("No URL were defined for the ServiceEnvironment " + this.name + " in the Marketplace " + ((Object) marketplace));
    }
}
