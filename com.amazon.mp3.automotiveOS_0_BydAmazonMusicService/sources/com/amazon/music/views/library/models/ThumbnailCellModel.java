package com.amazon.music.views.library.models;

import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;

/* JADX INFO: compiled from: ThumbnailCellModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\r\u0018\u00002\u00020\u0001B3\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\r\u0010\u000eR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/views/library/models/ThumbnailCellModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "text", "getText", "target", "getTarget", "backgroundImageUrl", "getBackgroundImageUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class ThumbnailCellModel extends BaseViewModel {
    private final String backgroundImageUrl;
    private final String target;
    private final String text;
    private final String viewId;

    public final String getText() {
        return this.text;
    }

    public final String getTarget() {
        return this.target;
    }

    public ThumbnailCellModel(String str, String str2, String str3, String str4) {
        super(str, null, 2, null);
        this.viewId = str;
        this.text = str2;
        this.target = str3;
        this.backgroundImageUrl = str4;
    }

    public final String getBackgroundImageUrl() {
        return this.backgroundImageUrl;
    }
}
