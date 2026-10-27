package com.amazon.music.share.menu.destinations;

import com.amazon.music.share.menu.ShareableContent;
import com.amazon.music.share.menu.StoryAssets;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InstagramStoryShareDestination.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class InstagramStoryShareDestination$share$1$1 extends FunctionReferenceImpl implements Function2<ShareableContent, StoryAssets, Unit> {
    InstagramStoryShareDestination$share$1$1(Object obj) {
        super(2, obj, InstagramStoryShareDestination.class, "completeSharing", "completeSharing(Lcom/amazon/music/share/menu/ShareableContent;Lcom/amazon/music/share/menu/StoryAssets;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo4invoke(ShareableContent shareableContent, StoryAssets storyAssets) {
        invoke2(shareableContent, storyAssets);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(ShareableContent p0, StoryAssets p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        ((InstagramStoryShareDestination) this.receiver).completeSharing(p0, p1);
    }
}
