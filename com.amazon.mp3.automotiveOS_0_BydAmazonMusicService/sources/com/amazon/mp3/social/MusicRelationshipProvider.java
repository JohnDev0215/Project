package com.amazon.mp3.social;

import kotlin.Metadata;
import rx.Observable;

/* JADX INFO: compiled from: MusicRelationshipProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\t\u001a\u00020\u0002H&¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/social/MusicRelationshipProvider;", "", "", "artistAsin", "", "isChildRequest", "Lrx/Observable;", "Ljava/lang/Void;", "followArtist", "artistAstin", "unFollowArtist", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface MusicRelationshipProvider {
    Observable<Void> followArtist(String artistAsin, boolean isChildRequest);

    Observable<Void> unFollowArtist(String artistAstin);
}
