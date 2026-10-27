package com.amazon.music.platform.providers;

import com.amazon.music.platform.data.Listener;
import com.amazon.music.platform.data.NetworkInfo;
import kotlin.Metadata;

/* JADX INFO: compiled from: NetworkInfoProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tH&J\u0016\u0010\n\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/platform/providers/NetworkInfoProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "networkInfo", "Lcom/amazon/music/platform/data/NetworkInfo;", "getNetworkInfo", "()Lcom/amazon/music/platform/data/NetworkInfo;", "addNetworkChangeListener", "", "networkChangeListener", "Lcom/amazon/music/platform/data/Listener;", "removeNetworkChangeListener", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface NetworkInfoProvider extends InterfaceProvider {
    void addNetworkChangeListener(Listener<NetworkInfo> networkChangeListener);

    NetworkInfo getNetworkInfo();

    void removeNetworkChangeListener(Listener<NetworkInfo> networkChangeListener);
}
