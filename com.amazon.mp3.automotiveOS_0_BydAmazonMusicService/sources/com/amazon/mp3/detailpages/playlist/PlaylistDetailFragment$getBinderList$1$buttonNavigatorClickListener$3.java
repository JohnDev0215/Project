package com.amazon.mp3.detailpages.playlist;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: PlaylistDetailFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class PlaylistDetailFragment$getBinderList$1$buttonNavigatorClickListener$3 extends FunctionReferenceImpl implements Function0<Uri> {
    PlaylistDetailFragment$getBinderList$1$buttonNavigatorClickListener$3(Object obj) {
        super(0, obj, PlaylistDetailFragment.class, "getPlaylistUriForQuery", "getPlaylistUriForQuery()Landroid/net/Uri;", 0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Uri invoke() {
        return ((PlaylistDetailFragment) this.receiver).getPlaylistUriForQuery();
    }
}
