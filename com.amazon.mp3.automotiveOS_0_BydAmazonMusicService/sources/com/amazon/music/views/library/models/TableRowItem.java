package com.amazon.music.views.library.models;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import kotlin.Metadata;

/* JADX INFO: compiled from: TableRowItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\r\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\u0005\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0005\"\u0004\b\u0012\u0010\tR\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0005\"\u0004\b\u0015\u0010\tR\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/views/library/models/TableRowItem;", "", "date", "", "getDate", "()Ljava/lang/String;", ContextMappingConstants.PRIMARY_TEXT, "getPrimaryText", "setPrimaryText", "(Ljava/lang/String;)V", "rowNumber", "", "getRowNumber", "()Ljava/lang/Integer;", "setRowNumber", "(Ljava/lang/Integer;)V", "secondaryText1", "getSecondaryText1", "setSecondaryText1", "secondaryText2", "getSecondaryText2", "setSecondaryText2", "time", "getTime", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface TableRowItem {
    String getDate();

    String getPrimaryText();

    Integer getRowNumber();

    String getSecondaryText1();

    String getSecondaryText2();

    String getTime();

    void setPrimaryText(String str);

    void setRowNumber(Integer num);

    void setSecondaryText1(String str);

    void setSecondaryText2(String str);
}
