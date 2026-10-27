package com.amazon.music.views.library.models;

import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.google.android.gms.cast.MediaTrack;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PodcastShovelerModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001Bu\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u000fR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/views/library/models/PodcastShovelerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "viewId", "", "models", "", "tileType", "", "tags", "title", MediaTrack.ROLE_SUBTITLE, "moreText", "nextPageToken", "moreTarget", "(Ljava/lang/String;Ljava/util/List;ILjava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getMoreTarget", "()Ljava/lang/String;", "getMoreText", "getNextPageToken", "getSubtitle", "getTitle", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PodcastShovelerModel extends BaseViewContainerModel<BaseViewModel> {
    private final String moreTarget;
    private final String moreText;
    private final String nextPageToken;
    private final String subtitle;
    private final String title;

    public /* synthetic */ PodcastShovelerModel(String str, List list, int i, List list2, String str2, String str3, String str4, String str5, String str6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, list, i, (i2 & 8) != 0 ? null : list2, (i2 & 16) != 0 ? null : str2, (i2 & 32) != 0 ? null : str3, (i2 & 64) != 0 ? null : str4, (i2 & 128) != 0 ? null : str5, (i2 & 256) != 0 ? null : str6);
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubtitle() {
        return this.subtitle;
    }

    public final String getMoreText() {
        return this.moreText;
    }

    public final String getNextPageToken() {
        return this.nextPageToken;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PodcastShovelerModel(String str, List<? extends BaseViewModel> models, int i, List<String> list, String str2, String str3, String str4, String str5, String str6) {
        super(str, models, i, list);
        Intrinsics.checkNotNullParameter(models, "models");
        this.title = str2;
        this.subtitle = str3;
        this.moreText = str4;
        this.nextPageToken = str5;
        this.moreTarget = str6;
    }

    public final String getMoreTarget() {
        return this.moreTarget;
    }
}
