package com.amazon.mp3.brush;

import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.music.views.library.models.CommentaryToggleStateObserver;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CommentaryStateUseCase.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/brush/CommentaryStateUseCase;", "", "Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "model", "", "showArtistCommentary", "hideArtistCommentary", "", Constants.ENABLE_DISABLE, "updateCommentaryToggleState", "Lcom/amazon/mp3/playback/state/PageStateManager;", "mStateManager", "Lcom/amazon/mp3/playback/state/PageStateManager;", "<init>", "(Lcom/amazon/mp3/playback/state/PageStateManager;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CommentaryStateUseCase {
    private final PageStateManager mStateManager;

    public CommentaryStateUseCase(PageStateManager mStateManager) {
        Intrinsics.checkNotNullParameter(mStateManager, "mStateManager");
        this.mStateManager = mStateManager;
    }

    public final void updateCommentaryToggleState(boolean isEnabled) {
        this.mStateManager.performActionPerContentModel(isEnabled ? new CommentaryStateUseCase$updateCommentaryToggleState$action$1(this) : new CommentaryStateUseCase$updateCommentaryToggleState$action$2(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void showArtistCommentary(BaseViewContentModel model) {
        CommentaryToggleStateObserver commentaryToggleStateObserver = model instanceof CommentaryToggleStateObserver ? (CommentaryToggleStateObserver) model : null;
        if (commentaryToggleStateObserver == null) {
            return;
        }
        commentaryToggleStateObserver.updateCommentaryToggleState(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void hideArtistCommentary(BaseViewContentModel model) {
        CommentaryToggleStateObserver commentaryToggleStateObserver = model instanceof CommentaryToggleStateObserver ? (CommentaryToggleStateObserver) model : null;
        if (commentaryToggleStateObserver == null) {
            return;
        }
        commentaryToggleStateObserver.updateCommentaryToggleState(false);
    }
}
