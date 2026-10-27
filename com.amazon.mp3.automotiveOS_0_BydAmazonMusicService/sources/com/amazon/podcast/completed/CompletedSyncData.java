package com.amazon.podcast.completed;

import Podcast.CompletedInterface.v1_0.CompletedLastSyncTimeClientState;
import Podcast.CompletedInterface.v1_0.CompletedMetadataWriteElement;
import Podcast.CompletedInterface.v1_0.CompletedOperationsClientState;
import Podcast.CompletedInterface.v1_0.CompletedWriteElement;
import Podcast.CompletedInterface.v1_0.SetCompletedLastSyncTimeMethod;
import Podcast.CompletedInterface.v1_0.SetOnReportOperationsMethod;
import SOAAppSyncInterface.v1_0.ConditionElement;
import SOAAppSyncInterface.v1_0.OperationElement;
import SOAAppSyncInterface.v1_0.TimeConditionElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.LiveData;
import com.amazon.podcast.ClientStates;
import com.amazon.podcast.appsync.SyncData;
import com.amazon.podcast.mappers.Mappers;
import com.amazon.podcast.storage.PodcastRoomDatabase;
import com.amazon.podcast.views.Strings;
import com.amazon.soa.core.Engine;
import com.amazon.soa.core.MethodCallback;
import com.amazon.soa.core.OnMethodExecutedListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class CompletedSyncData extends SyncData<CompletedWriteElement, CompletedMetadataWriteElement, Completed, CompletedQueryParameters> implements MethodCallback {
    private final CompletedDao completedDao;
    private final CompletedOperationsDoa completedOperationsDoa;
    private final Context context;
    private final Logger logger = LoggerFactory.getLogger("CompletedSyncData");

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<Completed>> readStates(final Set<String> ids) {
        return null;
    }

    public CompletedSyncData(final Engine engine, final String ownerId, final Context context, final PodcastRoomDatabase database) {
        this.context = context;
        this.completedDao = database.completedDao();
        this.completedOperationsDoa = database.completedOperationsDoa();
        engine.registerOwner(ownerId);
        HashSet hashSet = new HashSet();
        hashSet.add(SetOnReportOperationsMethod.class.getCanonicalName());
        hashSet.add(SetCompletedLastSyncTimeMethod.class.getCanonicalName());
        engine.registerMethods(Collections.emptySet(), hashSet, this);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.podcast.appsync.SyncData
    public Completed read(String id) {
        return this.completedDao.get(id);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<Completed> readState(final String id) {
        return this.completedDao.getLiveData(id);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<Completed>> readStates() {
        return this.completedDao.getAllLiveData(true);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public List<Completed> readStates(final CompletedQueryParameters queryParameters) {
        if (queryParameters == null) {
            return this.completedDao.getAll(true);
        }
        String podcastId = queryParameters.getPodcastId();
        String seasonNumber = queryParameters.getSeasonNumber();
        if (StringUtils.isNotBlank(seasonNumber) && StringUtils.isNotBlank(podcastId)) {
            return this.completedDao.getAll(true, podcastId, seasonNumber);
        }
        if (StringUtils.isBlank(seasonNumber) && StringUtils.isNotBlank(podcastId)) {
            return this.completedDao.getAll(true, podcastId);
        }
        return this.completedDao.getAll(true);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<Completed>> readStatesLiveData(final CompletedQueryParameters queryParameters) {
        if (queryParameters == null) {
            return this.completedDao.getAllLiveData(true);
        }
        String podcastId = queryParameters.getPodcastId();
        String seasonNumber = queryParameters.getSeasonNumber();
        if (StringUtils.isNotBlank(seasonNumber) && StringUtils.isNotBlank(podcastId)) {
            return this.completedDao.getAllLiveData(true, podcastId, seasonNumber);
        }
        if (StringUtils.isBlank(seasonNumber) && StringUtils.isNotBlank(podcastId)) {
            return this.completedDao.getAllLiveData(true, podcastId);
        }
        return this.completedDao.getAllLiveData(true);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public boolean writeState(final CompletedWriteElement element, final ConditionElement conditionElement, final long updatedTime) {
        long j;
        try {
            if (conditionElement instanceof TimeConditionElement) {
                long updatedTime2 = ((TimeConditionElement) conditionElement).getUpdatedTime();
                Completed completed = this.completedDao.get(element.getId());
                if (completed != null && completed.getUpdatedTime() > updatedTime2) {
                    return false;
                }
                j = updatedTime2;
            } else {
                j = updatedTime;
            }
            this.completedDao.insert(new Completed(element.getId(), element.getPodcastId(), element.getPodcastTitle(), element.getTitle(), Strings.checkNullString(element.getImage()), Strings.checkNullString(element.getPodcastImage()), Strings.checkNullString(element.getPublishDate()), Strings.checkNullString(element.getDescription()), element.getAuthors() == null ? "" : Strings.convertListToString(element.getAuthors()), Strings.checkNullString(element.getSeasonNumber()), element.isCompleted(), j));
            return true;
        } catch (IOException e) {
            this.logger.error("Error in Completed Write state method", (Throwable) e);
            return false;
        }
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeToOperations(final CompletedWriteElement element, final long updatedTime) {
        try {
            this.completedOperationsDoa.insert(new CompletedOperations(UUID.randomUUID().toString(), element.getId(), element.getPodcastId(), element.getPodcastTitle(), element.getTitle(), Strings.checkNullString(element.getImage()), Strings.checkNullString(element.getPodcastImage()), Strings.checkNullString(element.getPublishDate()), Strings.checkNullString(element.getDescription()), element.getAuthors() == null ? "" : Strings.convertListToString(element.getAuthors()), Strings.checkNullString(element.getSeasonNumber()), element.isCompleted(), updatedTime, false));
        } catch (IOException e) {
            this.logger.error("Error in writeOperations Method", (Throwable) e);
        }
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeMetadata(final CompletedMetadataWriteElement element) {
        try {
            Completed completed = this.completedDao.get(element.getId());
            if (completed == null) {
                return;
            }
            this.completedDao.insert(new Completed(completed, element.getId(), element.getPodcastId(), element.getPodcastTitle(), element.getTitle(), Strings.checkNullString(element.getImage()), Strings.checkNullString(element.getPodcastImage()), Strings.checkNullString(element.getPublishDate()), Strings.checkNullString(element.getDescription()), element.getAuthors() == null ? "" : Strings.convertListToString(element.getAuthors()), Strings.checkNullString(element.getSeasonNumber())));
        } catch (IOException e) {
            this.logger.error("Error in writeMetadata Method", (Throwable) e);
        }
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public List<Method> reportOperationMethods() {
        String string = getSharedPerferences(this.context).getString("onReportOperations", null);
        if (string == null) {
            return Collections.emptyList();
        }
        try {
            return ((SetOnReportOperationsMethod) Mappers.mapper().readValue(string, SetOnReportOperationsMethod.class)).getOnReportOperations();
        } catch (IOException e) {
            this.logger.error("Error in readReportOperationsMethods", (Throwable) e);
            return Collections.emptyList();
        }
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public ClientStates.Entry getClientState(final String name) {
        if (name.equals(CompletedOperationsClientState.class.getCanonicalName())) {
            return getCompletedOperationsClientState();
        }
        if (name.equals(CompletedLastSyncTimeClientState.class.getCanonicalName())) {
            return getCompletedLastSyncTimeClientState();
        }
        return null;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void deleteOperations(final List<String> ids) {
        this.completedOperationsDoa.deleteIds(ids);
    }

    @Override // com.amazon.soa.core.MethodCallback
    public void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener onMethodExecutedListener) {
        if (method instanceof SetOnReportOperationsMethod) {
            completedReportOperations(this.context, (SetOnReportOperationsMethod) method);
        } else if (method instanceof SetCompletedLastSyncTimeMethod) {
            completedLastSyncTime(this.context, (SetCompletedLastSyncTimeMethod) method);
        }
        onMethodExecutedListener.onMethodExecuted(method);
    }

    private SharedPreferences getSharedPerferences(final Context context) {
        return context.getSharedPreferences("CompletedSync", 0);
    }

    private ClientStates.Entry getCompletedOperationsClientState() {
        List<CompletedOperations> unProcessed = this.completedOperationsDoa.getUnProcessed();
        CompletedOperationsClientState completedOperationsClientStateConvertOperationsToClientState = convertOperationsToClientState(unProcessed);
        ArrayList arrayList = new ArrayList();
        Iterator<CompletedOperations> it = unProcessed.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getOperationId());
        }
        this.completedOperationsDoa.updateToProcessing(arrayList);
        return ClientStates.Entry.create("completed", completedOperationsClientStateConvertOperationsToClientState);
    }

    private CompletedOperationsClientState convertOperationsToClientState(final List<CompletedOperations> pendingOperations) {
        ArrayList arrayList = new ArrayList();
        for (CompletedOperations completedOperations : pendingOperations) {
            CompletedWriteElement completedWriteElementConvertOperationsToCompletedWriteElement = convertOperationsToCompletedWriteElement(completedOperations);
            arrayList.add(OperationElement.builder().withId(completedOperations.getOperationId()).withElement(completedWriteElementConvertOperationsToCompletedWriteElement).withCondition(TimeConditionElement.builder().withUpdatedTime(completedOperations.getUpdatedTime()).build()).build());
        }
        return CompletedOperationsClientState.builder().withOperations(arrayList).build();
    }

    private CompletedWriteElement convertOperationsToCompletedWriteElement(final CompletedOperations operation) {
        try {
            return CompletedWriteElement.builder().withId(operation.getId()).withPodcastId(operation.getPodcastId()).withImage(operation.getImage()).withPodcastImage(operation.getPodcastImage()).withPodcastTitle(operation.getPodcastTitle()).withTitle(operation.getTitle()).withDescription(operation.getDescription()).withSeasonNumber(StringUtils.isBlank(operation.getSeasonNumber()) ? null : Integer.valueOf(operation.getSeasonNumber())).withAuthors(Strings.convertStringToList(operation.getAuthors())).withCompleted(operation.isCompleted()).build();
        } catch (IOException e) {
            this.logger.error("Error in convertOperationsToCompletedWriteElement method", (Throwable) e);
            return CompletedWriteElement.builder().build();
        }
    }

    private ClientStates.Entry getCompletedLastSyncTimeClientState() {
        return ClientStates.Entry.create("completedLastSyncTime", CompletedLastSyncTimeClientState.builder().withLastSyncTime(getLastSyncTime(this.context)).build());
    }

    private Long getLastSyncTime(final Context context) {
        return Long.valueOf(context.getSharedPreferences("CompletedSync", 0).getLong("lastSyncTime", 0L));
    }

    private void completedReportOperations(final Context context, final SetOnReportOperationsMethod method) {
        try {
            String strWriteValueAsString = Mappers.mapper().writeValueAsString(method);
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("CompletedSync", 0).edit();
            editorEdit.putString("onReportOperations", strWriteValueAsString);
            editorEdit.apply();
        } catch (IOException e) {
            this.logger.error("Error in completeReportOperations", (Throwable) e);
        }
    }

    private void completedLastSyncTime(final Context context, final SetCompletedLastSyncTimeMethod method) {
        Long lastSyncTime = method.getLastSyncTime();
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("CompletedSync", 0).edit();
        editorEdit.putLong("lastSyncTime", lastSyncTime.longValue());
        editorEdit.apply();
    }

    public static void clearData(final Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("CompletedSync", 0).edit();
        editorEdit.clear();
        editorEdit.apply();
    }
}
