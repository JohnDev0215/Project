package com.amazon.music.views.library.models;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: CompactHeaderModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lcom/amazon/music/views/library/models/CompactHeaderModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "viewId", "", ContextMappingConstants.PRIMARY_TEXT, ContextMappingConstants.LABEL, "imageUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getImageUrl", "()Ljava/lang/String;", "getLabel", "getPrimaryText", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CompactHeaderModel extends BaseViewModel {
    private final String imageUrl;
    private final String label;
    private final String primaryText;

    public CompactHeaderModel() {
        this(null, null, null, null, 15, null);
    }

    public /* synthetic */ CompactHeaderModel(String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4);
    }

    public final String getPrimaryText() {
        return this.primaryText;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public CompactHeaderModel(String str, String str2, String str3, String str4) {
        super(str, null, 2, 0 == true ? 1 : 0);
        this.primaryText = str2;
        this.label = str3;
        this.imageUrl = str4;
    }
}
