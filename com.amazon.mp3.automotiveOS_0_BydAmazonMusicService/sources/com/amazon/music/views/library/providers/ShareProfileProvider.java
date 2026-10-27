package com.amazon.music.views.library.providers;

import kotlin.Metadata;

/* JADX INFO: compiled from: ShareProfileProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0005H&¨\u0006\t"}, d2 = {"Lcom/amazon/music/views/library/providers/ShareProfileProvider;", "", "onShare", "", "profileKey", "", "profileName", "profileImage", "profileUrl", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ShareProfileProvider {
    void onShare(String profileKey, String profileName, String profileImage, String profileUrl);
}
