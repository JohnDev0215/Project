package com.amazon.podcast.biteBookmark;

import Podcast.BiteBookmarkInterface.v1_0.BiteBookmarkOperationsClientState;
import Podcast.BiteBookmarkInterface.v1_0.BiteBookmarkWriteElement;
import Podcast.BiteBookmarkInterface.v1_0.BiteBookmarksLastSyncTimeClientState;
import Podcast.BiteBookmarkInterface.v1_0.SetBiteBookmarksLastSyncTimeMethod;
import Podcast.BiteBookmarkInterface.v1_0.SetOnReportOperationsMethod;
import SOAAppSyncInterface.v1_0.ConditionElement;
import SOAAppSyncInterface.v1_0.OperationElement;
import SOAAppSyncInterface.v1_0.TimeConditionElement;
import SOAAppSyncInterface.v1_0.WriteElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.LiveData;
import com.amazon.podcast.ClientStates;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.appsync.SyncData;
import com.amazon.podcast.mappers.Mappers;
import com.amazon.podcast.media.playback.PlaybackCache;
import com.amazon.podcast.storage.PodcastRoomDatabase;
import com.amazon.soa.core.Engine;
import com.amazon.soa.core.MethodCallback;
import com.amazon.soa.core.OnMethodExecutedListener;
import com.google.android.gms.common.util.CollectionUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class BiteBookmarkSyncData extends SyncData<BiteBookmarkWriteElement, WriteElement, BiteBookmark, BiteBookmarkQueryParameters> implements MethodCallback {
    private final BiteBookmarkOperationsDoa biteBookmarkOperationsDoa;
    private final BiteBookmarksDao biteBookmarksDao;
    private final Context context;
    private final Logger logger = LoggerFactory.getLogger("BiteBookmarkSyncData");
    private final Integer biteBookmarksUpperBoundPercentage = Podcast.getBookmarksUpperBoundPercentage();
    private final Long biteBookmarksLowerBoundProgressMS = Podcast.getBookmarksLowerBoundProgressMS();

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeMetadata(final WriteElement element) {
    }

    public BiteBookmarkSyncData(final Engine engine, final String ownerId, final Context context, final PodcastRoomDatabase database) {
        this.context = context;
        this.biteBookmarksDao = database.biteBookmarksDao();
        this.biteBookmarkOperationsDoa = database.biteBookmarkOperationsDoa();
        engine.registerOwner(ownerId);
        HashSet hashSet = new HashSet();
        hashSet.add(SetOnReportOperationsMethod.class.getCanonicalName());
        hashSet.add(SetBiteBookmarksLastSyncTimeMethod.class.getCanonicalName());
        engine.registerMethods(Collections.emptySet(), hashSet, this);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.podcast.appsync.SyncData
    public BiteBookmark read(final String id) {
        return this.biteBookmarksDao.getData(id, this.biteBookmarksLowerBoundProgressMS, this.biteBookmarksUpperBoundPercentage);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<BiteBookmark> readState(final String id) {
        return this.biteBookmarksDao.getLiveData(id);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<BiteBookmark>> readStates() {
        return this.biteBookmarksDao.getAll(this.biteBookmarksLowerBoundProgressMS, this.biteBookmarksUpperBoundPercentage);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public List<BiteBookmark> readStates(final BiteBookmarkQueryParameters queryParameters) {
        if (queryParameters == null) {
            return Collections.emptyList();
        }
        Set<String> ids = queryParameters.getIds();
        return CollectionUtils.isEmpty(ids) ? Collections.emptyList() : this.biteBookmarksDao.getAllList(ids);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<BiteBookmark>> readStates(final Set<String> ids) {
        return this.biteBookmarksDao.getAll(ids);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public boolean writeState(final BiteBookmarkWriteElement element, final ConditionElement conditionElement, final long updatedTime) {
        try {
            if (conditionElement instanceof TimeConditionElement) {
                updatedTime = ((TimeConditionElement) conditionElement).getUpdatedTime();
                BiteBookmark biteBookmark = this.biteBookmarksDao.get(element.getId());
                if (biteBookmark != null && biteBookmark.getUpdatedTime() > updatedTime) {
                    return false;
                }
            }
            long j = updatedTime;
            Long lProgress = progress(element, conditionElement);
            Long lDuration = duration(element, conditionElement);
            element.setProgressMilliseconds(lProgress);
            element.setTotalDurationMilliseconds(lDuration);
            this.biteBookmarksDao.insert(new BiteBookmark(element.getId(), element.getPodcastId(), element.getEpisodeId(), element.getTotalDurationMilliseconds().longValue(), element.getProgressMilliseconds().longValue(), j));
            return true;
        } catch (Exception e) {
            this.logger.error("Error in writeState method", (Throwable) e);
            return false;
        }
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeToOperations(final BiteBookmarkWriteElement element, final long updatedTime) {
        try {
            this.biteBookmarkOperationsDoa.insert(new BiteBookmarkOperations(UUID.randomUUID().toString(), element.getId(), element.getPodcastId(), element.getEpisodeId(), element.getTotalDurationMilliseconds().longValue(), element.getProgressMilliseconds().longValue(), updatedTime, false));
        } catch (Exception e) {
            this.logger.error("Error in writeToOperations method", (Throwable) e);
        }
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public List<Method> reportOperationMethods() {
        return readReportOperationsMethods(this.context);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public ClientStates.Entry getClientState(final String name) {
        if (name.equals(BiteBookmarkOperationsClientState.class.getCanonicalName())) {
            return getBiteBookmarkOperationsClientState();
        }
        if (name.equals(BiteBookmarksLastSyncTimeClientState.class.getCanonicalName())) {
            return getBiteBookmarksLastSyncTimeClientState();
        }
        return null;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void deleteOperations(final List<String> ids) {
        this.biteBookmarkOperationsDoa.deleteIds(ids);
    }

    @Override // com.amazon.soa.core.MethodCallback
    public void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        if (method instanceof SetOnReportOperationsMethod) {
            saveReportOperations(this.context, (SetOnReportOperationsMethod) method);
        } else if (method instanceof SetBiteBookmarksLastSyncTimeMethod) {
            saveLastSyncTime(this.context, (SetBiteBookmarksLastSyncTimeMethod) method);
        }
        listener.onMethodExecuted(method);
    }

    public static void clearData(final Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("BiteBookmarkSync", 0).edit();
        editorEdit.clear();
        editorEdit.apply();
    }

    private ClientStates.Entry getBiteBookmarkOperationsClientState() {
        List<BiteBookmarkOperations> unProcessed = this.biteBookmarkOperationsDoa.getUnProcessed();
        BiteBookmarkOperationsClientState biteBookmarkOperationsClientStateConvertOperationsToClientState = convertOperationsToClientState(unProcessed);
        ArrayList arrayList = new ArrayList();
        Iterator<BiteBookmarkOperations> it = unProcessed.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getOperationId());
        }
        this.biteBookmarkOperationsDoa.updateToProcessing(arrayList);
        return ClientStates.Entry.create("bite", biteBookmarkOperationsClientStateConvertOperationsToClientState);
    }

    private ClientStates.Entry getBiteBookmarksLastSyncTimeClientState() {
        return ClientStates.Entry.create("biteBookmarksLastSyncTime", BiteBookmarksLastSyncTimeClientState.builder().withLastSyncTime(getLastSyncTime(this.context)).build());
    }

    private List<Method> readReportOperationsMethods(final Context context) {
        String string = getSharedPreferences(context).getString("onReportOperations", null);
        if (string == null) {
            return Collections.emptyList();
        }
        try {
            return ((SetOnReportOperationsMethod) Mappers.mapper().readValue(string, SetOnReportOperationsMethod.class)).getOnReportOperations();
        } catch (IOException e) {
            this.logger.error("Error in readReportOperationsMethods method", (Throwable) e);
            return Collections.emptyList();
        }
    }

    private SharedPreferences getSharedPreferences(final Context context) {
        return context.getSharedPreferences("BiteBookmarkSync", 0);
    }

    private BiteBookmarkOperationsClientState convertOperationsToClientState(final List<BiteBookmarkOperations> Operations) {
        ArrayList arrayList = new ArrayList();
        for (BiteBookmarkOperations biteBookmarkOperations : Operations) {
            BiteBookmarkWriteElement biteBookmarkWriteElementConvertOperationsToBookmarkWriteElement = convertOperationsToBookmarkWriteElement(biteBookmarkOperations);
            arrayList.add(OperationElement.builder().withId(biteBookmarkOperations.getOperationId()).withElement(biteBookmarkWriteElementConvertOperationsToBookmarkWriteElement).withCondition(TimeConditionElement.builder().withUpdatedTime(biteBookmarkOperations.getUpdatedTime()).build()).build());
        }
        return BiteBookmarkOperationsClientState.builder().withOperations(arrayList).build();
    }

    private BiteBookmarkWriteElement convertOperationsToBookmarkWriteElement(final BiteBookmarkOperations operation) {
        try {
            return BiteBookmarkWriteElement.builder().withId(operation.getId()).withPodcastId(operation.getPodcastId()).withEpisodeId(operation.getEpisodeId()).withTotalDurationMilliseconds(Long.valueOf(operation.getTotalDurationMilliseconds())).withProgressMilliseconds(Long.valueOf(operation.getProgressMilliseconds())).build();
        } catch (Exception e) {
            this.logger.error("Error in convertOperationsToBookmarkWriteElement method", (Throwable) e);
            return BiteBookmarkWriteElement.builder().build();
        }
    }

    private void saveReportOperations(final Context context, final SetOnReportOperationsMethod method) {
        try {
            String strWriteValueAsString = Mappers.mapper().writeValueAsString(method);
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("BiteBookmarkSync", 0).edit();
            editorEdit.putString("onReportOperations", strWriteValueAsString);
            editorEdit.apply();
        } catch (IOException e) {
            this.logger.error("Error in saveReportOperations method", (Throwable) e);
        }
    }

    private void saveLastSyncTime(final Context context, final SetBiteBookmarksLastSyncTimeMethod method) {
        Long lastSyncTime = method.getLastSyncTime();
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("BiteBookmarkSync", 0).edit();
        editorEdit.putLong("lastSyncTime", lastSyncTime.longValue());
        editorEdit.apply();
    }

    private Long getLastSyncTime(final Context context) {
        return Long.valueOf(context.getSharedPreferences("BiteBookmarkSync", 0).getLong("lastSyncTime", 0L));
    }

    private Long duration(final BiteBookmarkWriteElement biteBookmarkWriteElement, final ConditionElement timeConditionElement) {
        Long totalDurationMilliseconds = biteBookmarkWriteElement == null ? null : biteBookmarkWriteElement.getTotalDurationMilliseconds();
        if (timeConditionElement != null || Podcast.getPlayback() == null) {
            return totalDurationMilliseconds;
        }
        Long lValueOf = Long.valueOf(PlaybackCache.get(biteBookmarkWriteElement.getId()).getDurationMilliseconds());
        return lValueOf.longValue() == 0 ? totalDurationMilliseconds : lValueOf;
    }

    private Long progress(final BiteBookmarkWriteElement biteBookmarkWriteElement, final ConditionElement timeConditionElement) {
        return timeConditionElement != null ? biteBookmarkWriteElement == null ? null : biteBookmarkWriteElement.getProgressMilliseconds() : Long.valueOf(PlaybackCache.get(biteBookmarkWriteElement.getId()).getProgressMilliseconds());
    }
}
