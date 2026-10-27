package com.amazon.mp3.brush;

import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FollowStateUseCase.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\u0005\u001a\u00020\u00042\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0002J\u0006\u0010\u0006\u001a\u00020\u0004R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/brush/FollowStateUseCase;", "", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "model", "", "updateArtistFollowState", "artistFollowedUpdate", "Lcom/amazon/mp3/playback/state/PageStateManager;", "stateManager", "Lcom/amazon/mp3/playback/state/PageStateManager;", "<init>", "(Lcom/amazon/mp3/playback/state/PageStateManager;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class FollowStateUseCase {
    private final PageStateManager stateManager;

    public FollowStateUseCase(PageStateManager stateManager) {
        Intrinsics.checkNotNullParameter(stateManager, "stateManager");
        this.stateManager = stateManager;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.brush.FollowStateUseCase$artistFollowedUpdate$1, reason: invalid class name */
    /* JADX INFO: compiled from: FollowStateUseCase.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<BaseViewContainerModel<?>, Unit> {
        AnonymousClass1(Object obj) {
            super(1, obj, FollowStateUseCase.class, "updateArtistFollowState", "updateArtistFollowState(Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(BaseViewContainerModel<?> baseViewContainerModel) {
            invoke2(baseViewContainerModel);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(BaseViewContainerModel<?> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((FollowStateUseCase) this.receiver).updateArtistFollowState(p0);
        }
    }

    public final void artistFollowedUpdate() {
        this.stateManager.performActionPerContainerModel(new AnonymousClass1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateArtistFollowState(BaseViewContainerModel<?> model) {
        model.notifyArtistFollowed();
    }
}
