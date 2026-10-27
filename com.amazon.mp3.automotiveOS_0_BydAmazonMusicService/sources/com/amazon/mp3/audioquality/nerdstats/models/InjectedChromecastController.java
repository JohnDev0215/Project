package com.amazon.mp3.audioquality.nerdstats.models;

import com.amazon.music.chromecast.alert.ReceiverAlert;
import kotlin.Metadata;

/* JADX INFO: compiled from: NerdStatsInjectedAccess.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/models/InjectedChromecastController;", "", "chromecastReceiverAlert", "Lcom/amazon/music/chromecast/alert/ReceiverAlert;", "enableChromecastSpatialToggle", "", "isChromecastNerdStatsChannelActive", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface InjectedChromecastController {
    ReceiverAlert<?> chromecastReceiverAlert();

    boolean enableChromecastSpatialToggle();

    boolean isChromecastNerdStatsChannelActive();
}
