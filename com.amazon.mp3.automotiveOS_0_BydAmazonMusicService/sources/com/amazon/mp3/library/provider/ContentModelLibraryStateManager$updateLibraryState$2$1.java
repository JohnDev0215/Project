package com.amazon.mp3.library.provider;

import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentModelLibraryStateManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class ContentModelLibraryStateManager$updateLibraryState$2$1 extends FunctionReferenceImpl implements Function3<BaseViewModel, String, LibraryStateData, Boolean> {
    ContentModelLibraryStateManager$updateLibraryState$2$1(Object obj) {
        super(3, obj, ContentModelLibraryStateManager.class, "updateLibraryStateData", "updateLibraryStateData(Lcom/amazon/music/views/library/models/base/BaseViewModel;Ljava/lang/String;Lcom/amazon/mp3/library/provider/LibraryStateData;)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function3
    public final Boolean invoke(BaseViewModel p0, String p1, LibraryStateData p2) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        return Boolean.valueOf(((ContentModelLibraryStateManager) this.receiver).updateLibraryStateData(p0, p1, p2));
    }
}
