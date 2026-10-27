package com.amazon.mp3.playback.view;

import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlayerContentView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002H&J\b\u0010\b\u001a\u00020\u0004H&J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH&J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fH&¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/playback/view/PlayerContentView;", "", "", "title", "", "setTitle", MediaTrack.ROLE_SUBTITLE, "setSubtitle", "setDefaultArtwork", "", "isLoading", "setLoading", "", "visibility", "setMinimizeViewVisibility", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface PlayerContentView {
    void setDefaultArtwork();

    void setLoading(boolean isLoading);

    void setMinimizeViewVisibility(int visibility);

    void setSubtitle(String subtitle);

    void setTitle(String title);
}
