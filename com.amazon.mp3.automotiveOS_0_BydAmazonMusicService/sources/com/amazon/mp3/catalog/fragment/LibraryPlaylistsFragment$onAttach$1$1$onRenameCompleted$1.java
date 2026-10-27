package com.amazon.mp3.catalog.fragment;

import com.amazon.mp3.library.item.Playlist;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryPlaylistsFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class LibraryPlaylistsFragment$onAttach$1$1$onRenameCompleted$1 extends FunctionReferenceImpl implements Function3<BaseViewModel, Playlist, String, Boolean> {
    LibraryPlaylistsFragment$onAttach$1$1$onRenameCompleted$1(Object obj) {
        super(3, obj, LibraryPlaylistsFragment.class, "updatePlaylistName", "updatePlaylistName(Lcom/amazon/music/views/library/models/base/BaseViewModel;Lcom/amazon/mp3/library/item/Playlist;Ljava/lang/String;)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function3
    public final Boolean invoke(BaseViewModel p0, Playlist p1, String p2) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        return Boolean.valueOf(((LibraryPlaylistsFragment) this.receiver).updatePlaylistName(p0, p1, p2));
    }
}
