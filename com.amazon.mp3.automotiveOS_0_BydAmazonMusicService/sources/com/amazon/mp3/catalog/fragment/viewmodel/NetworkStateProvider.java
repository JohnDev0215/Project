package com.amazon.mp3.catalog.fragment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.amazon.music.platform.data.Listener;
import com.amazon.music.platform.data.NetworkInfo;
import kotlin.Metadata;

/* JADX INFO: compiled from: NetworkStateProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/NetworkStateProvider;", "Lcom/amazon/music/platform/data/Listener;", "Lcom/amazon/music/platform/data/NetworkInfo;", "Landroidx/lifecycle/MutableLiveData;", "", "getHasConnectionLiveData", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface NetworkStateProvider extends Listener<NetworkInfo> {
    MutableLiveData<Boolean> getHasConnectionLiveData();
}
