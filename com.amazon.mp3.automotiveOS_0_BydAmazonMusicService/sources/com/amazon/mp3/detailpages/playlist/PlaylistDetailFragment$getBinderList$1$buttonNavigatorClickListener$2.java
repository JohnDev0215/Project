package com.amazon.mp3.detailpages.playlist;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: PlaylistDetailFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class PlaylistDetailFragment$getBinderList$1$buttonNavigatorClickListener$2 extends FunctionReferenceImpl implements Function0<String> {
    PlaylistDetailFragment$getBinderList$1$buttonNavigatorClickListener$2(Object obj) {
        super(0, obj, PlaylistDetailFragment.class, "getPlaylistName", "getPlaylistName()Ljava/lang/String;", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final String invoke() {
        return ((PlaylistDetailFragment) this.receiver).getPlaylistName();
    }
}
