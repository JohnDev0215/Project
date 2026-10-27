package com.amazon.mp3.catalog.fragment.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.fragment.viewmodel.LibrarySyncStateUpdater;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.RepositoryEvent;
import com.amazon.mp3.platform.providers.LibrarySyncStateProvider;
import kotlin.Metadata;

/* JADX INFO: compiled from: LibraryStateObserver.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H&J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0002H&J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/LibraryStateObserver;", "", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/mp3/platform/providers/LibrarySyncStateProvider$LibrarySyncState;", "getLibrarySyncStateLiveData", "Lcom/amazon/mp3/fragment/viewmodel/LibrarySyncStateUpdater$LibrarySyncEvent;", "getLibrarySyncEventLiveData", "Landroidx/lifecycle/LiveData;", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RepositoryEvent;", "getLocalRepositoryUpdateEventLiveData", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface LibraryStateObserver {
    MutableLiveData<LibrarySyncStateUpdater.LibrarySyncEvent> getLibrarySyncEventLiveData();

    MutableLiveData<LibrarySyncStateProvider.LibrarySyncState> getLibrarySyncStateLiveData();

    LiveData<RepositoryEvent> getLocalRepositoryUpdateEventLiveData();
}
