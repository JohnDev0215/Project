package com.amazon.music.views.library.models;

import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: BaseDetailHeaderModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\r\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\r\u0010\u000eR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/views/library/models/BaseDetailHeaderModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "secondaryTitle", "getSecondaryTitle", "tertiaryTitle", "getTertiaryTitle", "imageUrl", "getImageUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class BaseDetailHeaderModel extends BaseViewModel {
    private final String imageUrl;
    private final String secondaryTitle;
    private final String tertiaryTitle;
    private final String title;

    public BaseDetailHeaderModel() {
        this(null, null, null, null, 15, null);
    }

    public /* synthetic */ BaseDetailHeaderModel(String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4);
    }

    public final String getTitle() {
        return this.title;
    }

    public BaseDetailHeaderModel(String str, String str2, String str3, String str4) {
        super(null, null, 2, null);
        this.title = str;
        this.secondaryTitle = str2;
        this.tertiaryTitle = str3;
        this.imageUrl = str4;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }
}
