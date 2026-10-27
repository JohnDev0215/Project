package com.amazon.mp3.recents;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import kotlin.Metadata;

/* JADX INFO: compiled from: RecentsUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/recents/RecentsUtil;", "", "()V", "getSmartPlaylistTitleFromId", "", "id", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RecentsUtil {
    public static final RecentsUtil INSTANCE = new RecentsUtil();

    private RecentsUtil() {
    }

    public final String getSmartPlaylistTitleFromId(long id) {
        if (id == 3) {
            return AmazonApplication.getContext().getString(R.string.dmusic_listening_history);
        }
        if (id == 1) {
            return AmazonApplication.getContext().getString(R.string.dmusic_recently_added_my_music);
        }
        if (id == 2) {
            return AmazonApplication.getContext().getString(R.string.dmusic_recently_downloaded_songs);
        }
        return null;
    }
}
