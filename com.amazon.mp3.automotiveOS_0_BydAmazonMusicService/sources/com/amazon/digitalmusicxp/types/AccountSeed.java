package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.enums.AccountContentSortOrderEnum;
import com.amazon.digitalmusicxp.enums.AccountContentTypeEnum;
import kotlin.Metadata;

/* JADX INFO: compiled from: AccountSeed.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/amazon/digitalmusicxp/types/AccountSeed;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/enums/AccountContentTypeEnum;", "contentType", "Lcom/amazon/digitalmusicxp/enums/AccountContentTypeEnum;", "getContentType", "()Lcom/amazon/digitalmusicxp/enums/AccountContentTypeEnum;", "Lcom/amazon/digitalmusicxp/enums/AccountContentSortOrderEnum;", "sortOrder", "Lcom/amazon/digitalmusicxp/enums/AccountContentSortOrderEnum;", "getSortOrder", "()Lcom/amazon/digitalmusicxp/enums/AccountContentSortOrderEnum;", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class AccountSeed {
    private final AccountContentTypeEnum contentType;
    private final AccountContentSortOrderEnum sortOrder;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AccountSeed)) {
            return false;
        }
        AccountSeed accountSeed = (AccountSeed) other;
        return this.contentType == accountSeed.contentType && this.sortOrder == accountSeed.sortOrder;
    }

    public int hashCode() {
        return (this.contentType.hashCode() * 31) + this.sortOrder.hashCode();
    }

    public String toString() {
        return "AccountSeed(contentType=" + this.contentType + ", sortOrder=" + this.sortOrder + ')';
    }
}
