package com.amazon.music.alps.liveevent.viewmodel;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FlexibleTextContainerViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B9\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/alps/liveevent/viewmodel/FlexibleTextContainerViewModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", ContentUtils.KEY_VIEW_REFERENCE_ID, "", "htmlText", "expandLabelText", "collapseLabelText", "maxCollapsedLineCount", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getCollapseLabelText", "()Ljava/lang/String;", "getExpandLabelText", "getHtmlText", "getMaxCollapsedLineCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FlexibleTextContainerViewModel extends BaseViewModel {
    private final String collapseLabelText;
    private final String expandLabelText;
    private final String htmlText;
    private final Integer maxCollapsedLineCount;

    public /* synthetic */ FlexibleTextContainerViewModel(String str, String str2, String str3, String str4, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, str2, str3, str4, num);
    }

    public final String getHtmlText() {
        return this.htmlText;
    }

    public final String getExpandLabelText() {
        return this.expandLabelText;
    }

    public final String getCollapseLabelText() {
        return this.collapseLabelText;
    }

    public final Integer getMaxCollapsedLineCount() {
        return this.maxCollapsedLineCount;
    }

    public FlexibleTextContainerViewModel(String str, String str2, String str3, String str4, Integer num) {
        super(str, null, 2, null);
        this.htmlText = str2;
        this.expandLabelText = str3;
        this.collapseLabelText = str4;
        this.maxCollapsedLineCount = num;
    }
}
