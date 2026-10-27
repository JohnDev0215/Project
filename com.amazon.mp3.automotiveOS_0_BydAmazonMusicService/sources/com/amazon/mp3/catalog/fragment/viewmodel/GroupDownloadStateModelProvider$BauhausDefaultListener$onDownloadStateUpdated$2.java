package com.amazon.mp3.catalog.fragment.viewmodel;

import com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GroupDownloadStateModelProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class GroupDownloadStateModelProvider$BauhausDefaultListener$onDownloadStateUpdated$2 extends FunctionReferenceImpl implements Function3<BaseViewModel, String, DownloadStateModel, Boolean> {
    GroupDownloadStateModelProvider$BauhausDefaultListener$onDownloadStateUpdated$2(Object obj) {
        super(3, obj, GroupDownloadStateModelProvider.BauhausDefaultListener.class, "updateDownloadState", "updateDownloadState(Lcom/amazon/music/views/library/models/base/BaseViewModel;Ljava/lang/String;Lcom/amazon/music/views/library/models/DownloadStateModel;)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function3
    public final Boolean invoke(BaseViewModel p0, String str, DownloadStateModel p2) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p2, "p2");
        return Boolean.valueOf(((GroupDownloadStateModelProvider.BauhausDefaultListener) this.receiver).updateDownloadState(p0, str, p2));
    }
}
