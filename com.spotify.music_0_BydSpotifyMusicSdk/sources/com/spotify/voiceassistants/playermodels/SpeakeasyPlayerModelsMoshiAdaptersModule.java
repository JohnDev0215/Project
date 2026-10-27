package com.spotify.voiceassistants.playermodels;

import com.spotify.voiceassistants.playermodels.MetadataItem;
import com.spotify.webapi.service.models.Search;
import p.mw1;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class SpeakeasyPlayerModelsMoshiAdaptersModule {
    public final Object provideCommandPlayResponseCustomJsonAdapter() {
        return new CommandPlayResponseCustomJsonAdapter(null, 1, 0 == true ? 1 : 0);
    }

    public final Object provideMetadataItemAdapter() {
        mw1 mw1VarC = mw1.b(MetadataItem.class, "entity_type").c(MetadataItem.Album.class, Search.Type.ALBUM).c(MetadataItem.Artist.class, Search.Type.ARTIST).c(MetadataItem.Episode.class, Search.Type.EPISODE).c(MetadataItem.Playlist.class, Search.Type.PLAYLIST).c(MetadataItem.Show.class, Search.Type.SHOW).c(MetadataItem.Track.class, Search.Type.TRACK).c(MetadataItem.Error.class, "unknown");
        v03.i(mw1VarC, "of(MetadataItem::class.j…r::class.java, \"unknown\")");
        return mw1VarC;
    }
}
