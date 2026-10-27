package com.amazon.mp3.library.provider.source.cirrus.dbutils;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocalRepositoryEvents.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/LocalRepositoryEvents;", "", "()V", "repositoryUpdateLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RepositoryEvent;", "getFilteredRepositoryUpdateLiveData", "Landroidx/lifecycle/LiveData;", "repositoryUpdatesEventTypeAllowList", "", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RepositoryEventType;", "sendEvent", "", NotificationCompat.CATEGORY_EVENT, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LocalRepositoryEvents {
    public static final LocalRepositoryEvents INSTANCE = new LocalRepositoryEvents();
    private static final MutableLiveData<RepositoryEvent> repositoryUpdateLiveData = new MutableLiveData<>();

    private LocalRepositoryEvents() {
    }

    public final LiveData<RepositoryEvent> getFilteredRepositoryUpdateLiveData(final List<? extends RepositoryEventType> repositoryUpdatesEventTypeAllowList) {
        Intrinsics.checkNotNullParameter(repositoryUpdatesEventTypeAllowList, "repositoryUpdatesEventTypeAllowList");
        MutableLiveData<RepositoryEvent> mutableLiveData = repositoryUpdateLiveData;
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(mutableLiveData, new Observer() { // from class: com.amazon.mp3.library.provider.source.cirrus.dbutils.LocalRepositoryEvents$getFilteredRepositoryUpdateLiveData$$inlined$filter$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                RepositoryEvent repositoryEvent = (RepositoryEvent) t;
                if (CollectionsKt___CollectionsKt.contains(repositoryUpdatesEventTypeAllowList, repositoryEvent == null ? null : repositoryEvent.getEventType())) {
                    mediatorLiveData.setValue(t);
                }
            }
        });
        return mediatorLiveData;
    }

    public final void sendEvent(RepositoryEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        repositoryUpdateLiveData.postValue(event);
    }
}
