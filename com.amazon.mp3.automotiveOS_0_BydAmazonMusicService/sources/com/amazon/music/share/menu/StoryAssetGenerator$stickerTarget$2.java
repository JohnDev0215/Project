package com.amazon.music.share.menu;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StoryAssetsGenerator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class StoryAssetGenerator$stickerTarget$2 extends FunctionReferenceImpl implements Function1<Exception, Unit> {
    StoryAssetGenerator$stickerTarget$2(Object obj) {
        super(1, obj, StoryAssetGenerator.class, "fetchFailedCallback", "fetchFailedCallback(Ljava/lang/Exception;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
        invoke2(exc);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Exception p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((StoryAssetGenerator) this.receiver).fetchFailedCallback(p0);
    }
}
