package com.amazon.mp3.library.item;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes3.dex */
public class AllTracks extends AbstractItem {
    public static final AllTracks INSTANCE = new AllTracks();

    @Override // com.amazon.mp3.library.item.AbstractItem
    public String getItemTypeName() {
        return null;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isExplicit() {
        return false;
    }

    private AllTracks() {
    }

    public String getTitle() {
        return AmazonApplication.getContext().getString(R.string.dmusic_player_now_playing_all_songs);
    }
}
