package com.amazon.music.media.auto.provider.network;

import kotlin.Metadata;
import okhttp3.Headers;
import okhttp3.RequestBody;

/* JADX INFO: compiled from: MESKRequestProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/amazon/music/media/auto/provider/network/MESKRequestProvider;", "", "buildBody", "Lokhttp3/RequestBody;", "meskRequestBody", "Lcom/amazon/music/media/auto/provider/network/MESKRequestBody;", "buildHeaders", "Lokhttp3/Headers;", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface MESKRequestProvider {
    RequestBody buildBody(MESKRequestBody meskRequestBody);

    Headers buildHeaders();
}
