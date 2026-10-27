package com.amazon.mp3.brush;

import com.amazon.music.views.library.models.base.BaseViewContentModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CommentaryStateUseCase.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class CommentaryStateUseCase$updateCommentaryToggleState$action$1 extends FunctionReferenceImpl implements Function1<BaseViewContentModel, Unit> {
    CommentaryStateUseCase$updateCommentaryToggleState$action$1(Object obj) {
        super(1, obj, CommentaryStateUseCase.class, "showArtistCommentary", "showArtistCommentary(Lcom/amazon/music/views/library/models/base/BaseViewContentModel;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewContentModel baseViewContentModel) {
        invoke2(baseViewContentModel);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewContentModel p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((CommentaryStateUseCase) this.receiver).showArtistCommentary(p0);
    }
}
