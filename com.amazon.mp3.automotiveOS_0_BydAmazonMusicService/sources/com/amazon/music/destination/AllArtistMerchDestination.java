package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes4.dex */
public class AllArtistMerchDestination extends Destination {
    private final String artistAsin;

    @Nullable
    private final String category;

    public AllArtistMerchDestination(String str, @Nullable String str2, String str3, String str4) {
        super(str3, str4);
        this.artistAsin = str;
        this.category = str2;
    }

    public String getArtistAsin() {
        return this.artistAsin;
    }

    public String getCategory() {
        return this.category;
    }
}
