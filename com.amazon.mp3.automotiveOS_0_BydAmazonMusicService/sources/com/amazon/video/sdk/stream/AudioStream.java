package com.amazon.video.sdk.stream;

import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.Metadata;

/* JADX INFO: compiled from: AudioStream.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001R\u0014\u0010\u0006\u001a\u00020\u00038&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0016\u0010\n\u001a\u0004\u0018\u00010\u00078&X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/amazon/video/sdk/stream/AudioStream;", "Lcom/amazon/video/sdk/stream/Stream;", "Lcom/amazon/video/sdk/stream/AudioVariant;", "", "getLanguage", "()Ljava/lang/String;", "language", "Lcom/amazon/video/sdk/stream/AudioType;", "getType", "()Lcom/amazon/video/sdk/stream/AudioType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public interface AudioStream extends Stream<AudioVariant> {
    String getLanguage();

    AudioType getType();
}
