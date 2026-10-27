package com.amazon.music.find.model.search;

import android.net.Uri;
import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchSports.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/amazon/music/find/model/search/SearchSports;", "Lcom/amazon/music/find/model/search/SearchItem;", "deeplink", "Landroid/net/Uri;", "getDeeplink", "()Landroid/net/Uri;", MediaTrack.ROLE_SUBTITLE, "", "getSubtitle", "()Ljava/lang/String;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchSports extends SearchItem {
    Uri getDeeplink();

    String getSubtitle();
}
