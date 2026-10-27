package com.amazon.music.share.menu;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StoryAssetsGenerator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class StoryAssetGenerator$stickerTarget$1 extends FunctionReferenceImpl implements Function2<ShareableContent, Bitmap, Unit> {
    StoryAssetGenerator$stickerTarget$1(Object obj) {
        super(2, obj, StoryAssetGenerator.class, "stickerReadyCallback", "stickerReadyCallback(Lcom/amazon/music/share/menu/ShareableContent;Landroid/graphics/Bitmap;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo4invoke(ShareableContent shareableContent, Bitmap bitmap) {
        invoke2(shareableContent, bitmap);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(ShareableContent p0, Bitmap p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        ((StoryAssetGenerator) this.receiver).stickerReadyCallback(p0, p1);
    }
}
