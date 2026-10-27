package com.amazon.mp3.library.item;

import android.net.Uri;
import android.text.TextUtils;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;

/* JADX INFO: loaded from: classes3.dex */
public abstract class MarketplaceDependentItem extends AbstractItem {
    private String mMarketplace;

    public abstract Uri getContentUri(String source);

    public MarketplaceDependentItem() {
    }

    public MarketplaceDependentItem(LibraryItemFactory libItemFactory, Uri contentUri) {
        super(libItemFactory, contentUri);
    }

    public String getMarketplace() {
        if (TextUtils.isEmpty(this.mMarketplace)) {
            demandOptionalInfo();
        }
        if (TextUtils.isEmpty(this.mMarketplace)) {
            return AccountDetailUtil.get().getHomeMarketPlaceId();
        }
        return this.mMarketplace;
    }

    public void setMarketplace(String marketplace) {
        this.mMarketplace = marketplace;
    }
}
