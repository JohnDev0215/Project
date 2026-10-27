package com.amazon.mp3.brush;

import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PodcastStatesUseCase.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\b\u001a\u00020\u0006J\u0014\u0010\t\u001a\u00020\u00062\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002J\u0014\u0010\f\u001a\u00020\u00062\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002J\u0014\u0010\r\u001a\u00020\u00062\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/brush/PodcastStatesUseCase;", "", "stateManager", "Lcom/amazon/mp3/playback/state/PageStateManager;", "(Lcom/amazon/mp3/playback/state/PageStateManager;)V", "podcastFollowStateUpdate", "", "podcastJumpBackInStateUpdate", "podcastSavedStateUpdate", "updatePodcastFollowState", "model", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "updatePodcastJumpBackInState", "updatePodcastSaveState", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PodcastStatesUseCase {
    private final PageStateManager stateManager;

    public PodcastStatesUseCase(PageStateManager stateManager) {
        Intrinsics.checkNotNullParameter(stateManager, "stateManager");
        this.stateManager = stateManager;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.brush.PodcastStatesUseCase$podcastJumpBackInStateUpdate$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PodcastStatesUseCase.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* synthetic */ class C03081 extends FunctionReferenceImpl implements Function1<BaseViewContainerModel<?>, Unit> {
        C03081(Object obj) {
            super(1, obj, PodcastStatesUseCase.class, "updatePodcastJumpBackInState", "updatePodcastJumpBackInState(Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(BaseViewContainerModel<?> baseViewContainerModel) {
            invoke2(baseViewContainerModel);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(BaseViewContainerModel<?> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((PodcastStatesUseCase) this.receiver).updatePodcastJumpBackInState(p0);
        }
    }

    public final void podcastJumpBackInStateUpdate() {
        this.stateManager.performActionPerContainerModel(new C03081(this));
    }

    /* JADX INFO: renamed from: com.amazon.mp3.brush.PodcastStatesUseCase$podcastFollowStateUpdate$1, reason: invalid class name */
    /* JADX INFO: compiled from: PodcastStatesUseCase.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<BaseViewContainerModel<?>, Unit> {
        AnonymousClass1(Object obj) {
            super(1, obj, PodcastStatesUseCase.class, "updatePodcastFollowState", "updatePodcastFollowState(Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(BaseViewContainerModel<?> baseViewContainerModel) {
            invoke2(baseViewContainerModel);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(BaseViewContainerModel<?> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((PodcastStatesUseCase) this.receiver).updatePodcastFollowState(p0);
        }
    }

    public final void podcastFollowStateUpdate() {
        this.stateManager.performActionPerContainerModel(new AnonymousClass1(this));
    }

    /* JADX INFO: renamed from: com.amazon.mp3.brush.PodcastStatesUseCase$podcastSavedStateUpdate$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PodcastStatesUseCase.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* synthetic */ class C03091 extends FunctionReferenceImpl implements Function1<BaseViewContainerModel<?>, Unit> {
        C03091(Object obj) {
            super(1, obj, PodcastStatesUseCase.class, "updatePodcastSaveState", "updatePodcastSaveState(Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(BaseViewContainerModel<?> baseViewContainerModel) {
            invoke2(baseViewContainerModel);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(BaseViewContainerModel<?> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((PodcastStatesUseCase) this.receiver).updatePodcastSaveState(p0);
        }
    }

    public final void podcastSavedStateUpdate() {
        this.stateManager.performActionPerContainerModel(new C03091(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updatePodcastJumpBackInState(BaseViewContainerModel<?> model) {
        model.notifyPodcastJumpBackInState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updatePodcastFollowState(BaseViewContainerModel<?> model) {
        model.notifyPodcastFollowState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updatePodcastSaveState(BaseViewContainerModel<?> model) {
        model.notifyPodcastSaveState();
    }
}
