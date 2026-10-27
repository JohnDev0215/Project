package com.amazon.music.voice.googleAssistant;

import com.amazon.music.platform.data.mediagateway.RecommendForAssistant$MediaContent;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: GoogleAssistantListener.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0016\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH&¨\u0006\f"}, d2 = {"Lcom/amazon/music/voice/googleAssistant/GoogleAssistantListener;", "", "onError", "", "error", "Lcom/amazon/music/voice/googleAssistant/GoogleAssistantError;", "errorMessage", "", "onMediaGatewayResponse", "recommendForGoogleAssistant", "", "Lcom/amazon/music/platform/data/mediagateway/RecommendForAssistant$MediaContent;", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface GoogleAssistantListener {
    void onError(GoogleAssistantError error, String errorMessage);

    void onMediaGatewayResponse(List<? extends RecommendForAssistant$MediaContent> recommendForGoogleAssistant);

    /* JADX INFO: compiled from: GoogleAssistantListener.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void onError$default(GoogleAssistantListener googleAssistantListener, GoogleAssistantError googleAssistantError, String str, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onError");
            }
            if ((i & 2) != 0) {
                str = null;
            }
            googleAssistantListener.onError(googleAssistantError, str);
        }
    }
}
