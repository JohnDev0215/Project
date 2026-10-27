package com.amazon.video.sdk.stream;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: StreamMatcher.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\b`\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/amazon/video/sdk/stream/StreamPreferencesProvider;", "", "T", "", "get", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public interface StreamPreferencesProvider<T> {
    List<T> get();
}
