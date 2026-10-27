package com.amazon.podcast.save;

import Podcast.SaveInterface.v1_0.SaveMetadataWriteElement;
import Podcast.SaveInterface.v1_0.SaveOperationsClientState;
import Podcast.SaveInterface.v1_0.SaveWriteElement;
import Podcast.SaveInterface.v1_0.SavesLastSyncTimeClientState;
import Podcast.SaveInterface.v1_0.SetOnReportOperationsMethod;
import Podcast.SaveInterface.v1_0.SetSavesLastSyncTimeMethod;
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
public final class SaveSyncData extends SyncData<SaveWriteElement, SaveMetadataWriteElement, Save, SaveQueryParameters> implements MethodCallback {
    private final Context context;
    private final Logger logger = LoggerFactory.getLogger("SaveSyncData");
    private final SaveOperationsDoa saveOperationsDoa;
    private final SavesDao savesDao;

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<Save>> readStates(final Set<String> ids) {
        return null;
    }

    public SaveSyncData(final Engine engine, final String ownerId, final Context context, final PodcastRoomDatabase database) {
        this.context = context;
        this.savesDao = database.savesDao();
        this.saveOperationsDoa = database.saveOperationsDoa();
        engine.registerOwner(ownerId);
        HashSet hashSet = new HashSet();
        hashSet.add(SetOnReportOperationsMethod.class.getCanonicalName());
        hashSet.add(SetSavesLastSyncTimeMethod.class.getCanonicalName());
        engine.registerMethods(Collections.emptySet(), hashSet, this);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public boolean writeState(final SaveWriteElement element, final ConditionElement conditionElement, final long updatedTime) {
        long j;
        List<String> contentTraits;
        List<String> availableUpsells;
        try {
            if (conditionElement instanceof TimeConditionElement) {
                long updatedTime2 = ((TimeConditionElement) conditionElement).getUpdatedTime();
                Save save = this.savesDao.get(element.getId());
                if (save != null && save.getUpdatedTime() > updatedTime2) {
                    return false;
                }
                j = updatedTime2;
            } else {
                j = updatedTime;
            }
            String strConvertListToString = element.getAuthors() == null ? "" : Strings.convertListToString(element.getAuthors());
            if (element.getContentTraits() == null) {
                contentTraits = Collections.EMPTY_LIST;
            } else {
                contentTraits = element.getContentTraits();
            }
            List<String> list = contentTraits;
            if (element.getAvailableUpsells() == null) {
                availableUpsells = Collections.EMPTY_LIST;
            } else {
                availableUpsells = element.getAvailableUpsells();
            }
            this.savesDao.insert(new Save(element.getId(), element.getPodcastId(), element.getPodcastTitle(), element.getTitle(), Strings.checkNullString(element.getImage()), Strings.checkNullString(element.getPodcastImage()), Strings.checkNullString(element.getPublishDate()), Strings.checkNullString(element.getDescription()), strConvertListToString, Strings.checkNullString(element.getSeasonNumber()), element.getTotalDurationMilliseconds().longValue(), element.isSaved(), j, Strings.checkNullString(element.getPlaybackMode()), list, availableUpsells, Strings.checkNullString(element.getAvailabilityDate()), Strings.checkNullString(element.getPodcastShowVariantId()), Strings.checkNullString(element.getPodcastEpisodeVariantId())));
            return true;
        } catch (IOException e) {
            this.logger.error("Error in writeState method", (Throwable) e);
            return false;
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.podcast.appsync.SyncData
    public Save read(final String id) {
        return this.savesDao.get(id);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<Save> readState(final String id) {
        return this.savesDao.getLiveData(id);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<Save>> readStates() {
        return this.savesDao.getAllLiveData(true);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<Save>> readStatesLiveData(final SaveQueryParameters queryParameters) {
        return this.savesDao.getAllLiveData(true);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public List<Save> readStates(final SaveQueryParameters queryParameters) {
        return this.savesDao.getAll(true);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeToOperations(final SaveWriteElement element, final long updatedTime) {
        try {
            this.saveOperationsDoa.insert(new SaveOperations(UUID.randomUUID().toString(), element.getId(), element.getPodcastId(), element.getPodcastTitle(), element.getTitle(), Strings.checkNullString(element.getImage()), Strings.checkNullString(element.getPodcastImage()), Strings.checkNullString(element.getPublishDate()), Strings.checkNullString(element.getDescription()), element.getAuthors() == null ? "" : Strings.convertListToString(element.getAuthors()), Strings.checkNullString(element.getSeasonNumber()), element.getTotalDurationMilliseconds().longValue(), element.isSaved(), updatedTime, false));
        } catch (IOException e) {
            this.logger.error("Error in writeToOperations method", (Throwable) e);
        }
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeMetadata(final SaveMetadataWriteElement element) {
        List<String> contentTraits;
        List<String> availableUpsells;
        try {
            Save save = this.savesDao.get(element.getId());
            if (save == null) {
                return;
            }
            String strConvertListToString = element.getAuthors() == null ? "" : Strings.convertListToString(element.getAuthors());
            if (element.getContentTraits() == null) {
                contentTraits = Collections.EMPTY_LIST;
            } else {
                contentTraits = element.getContentTraits();
            }
            List<String> list = contentTraits;
            if (element.getAvailableUpsells() == null) {
                availableUpsells = Collections.EMPTY_LIST;
            } else {
                availableUpsells = element.getAvailableUpsells();
            }
            this.savesDao.insert(new Save(save, element.getId(), element.getPodcastId(), element.getPodcastTitle(), element.getTitle(), Strings.checkNullString(element.getImage()), Strings.checkNullString(element.getPodcastImage()), Strings.checkNullString(element.getPublishDate()), Strings.checkNullString(element.getDescription()), strConvertListToString, Strings.checkNullString(element.getSeasonNumber()), Strings.checkNullString(element.getPlaybackMode()), list, availableUpsells, Strings.checkNullString(element.getAvailabilityDate()), Strings.checkNullString(element.getPodcastShowVariantId()), Strings.checkNullString(element.getPodcastEpisodeVariantId())));
        } catch (IOException e) {
            this.logger.error("Error in writeMetadata method", (Throwable) e);
        }
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public List<Method> reportOperationMethods() {
        return readReportOperationsMethods(this.context);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void deleteOperations(final List<String> ids) {
        this.saveOperationsDoa.deleteIds(ids);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public ClientStates.Entry getClientState(final String name) {
        if (name.equals(SaveOperationsClientState.class.getCanonicalName())) {
            return getSaveOperationsClientState();
        }
        if (name.equals(SavesLastSyncTimeClientState.class.getCanonicalName())) {
            return getSavesLastSyncTimeClientState();
        }
        return null;
    }

    @Override // com.amazon.soa.core.MethodCallback
    public void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        if (method instanceof SetOnReportOperationsMethod) {
            saveReportOperations(this.context, (SetOnReportOperationsMethod) method);
        } else if (method instanceof SetSavesLastSyncTimeMethod) {
            saveLastSyncTime(this.context, (SetSavesLastSyncTimeMethod) method);
        }
        listener.onMethodExecuted(method);
    }

    public static void clearData(final Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("SaveSync", 0).edit();
        editorEdit.clear();
        editorEdit.apply();
    }

    private void saveLastSyncTime(final Context context, final SetSavesLastSyncTimeMethod method) {
        Long lastSyncTime = method.getLastSyncTime();
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("SaveSync", 0).edit();
        editorEdit.putLong("lastSyncTime", lastSyncTime.longValue());
        editorEdit.apply();
    }

    private Long getLastSyncTime(final Context context) {
        return Long.valueOf(context.getSharedPreferences("SaveSync", 0).getLong("lastSyncTime", 0L));
    }

    private ClientStates.Entry getSaveOperationsClientState() {
        List<SaveOperations> unProcessed = this.saveOperationsDoa.getUnProcessed();
        SaveOperationsClientState saveOperationsClientStateConvertOperationsToClientState = convertOperationsToClientState(unProcessed);
        ArrayList arrayList = new ArrayList();
        Iterator<SaveOperations> it = unProcessed.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getOperationId());
        }
        this.saveOperationsDoa.updateToProcessing(arrayList);
        return ClientStates.Entry.create("saves", saveOperationsClientStateConvertOperationsToClientState);
    }

    private ClientStates.Entry getSavesLastSyncTimeClientState() {
        return ClientStates.Entry.create("savesLastSyncTime", SavesLastSyncTimeClientState.builder().withLastSyncTime(getLastSyncTime(this.context)).build());
    }

    private void saveReportOperations(final Context context, final SetOnReportOperationsMethod method) {
        try {
            String strWriteValueAsString = Mappers.mapper().writeValueAsString(method);
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("SaveSync", 0).edit();
            editorEdit.putString("onReportOperations", strWriteValueAsString);
            editorEdit.apply();
        } catch (IOException e) {
            this.logger.error("Error in saveReportOperations", (Throwable) e);
        }
    }

    private List<Method> readReportOperationsMethods(final Context context) {
        String string = getSharedPreferences(context).getString("onReportOperations", null);
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

    private SharedPreferences getSharedPreferences(final Context context) {
        return context.getSharedPreferences("SaveSync", 0);
    }

    private SaveOperationsClientState convertOperationsToClientState(final List<SaveOperations> pendingOperations) {
        ArrayList arrayList = new ArrayList();
        for (SaveOperations saveOperations : pendingOperations) {
            SaveWriteElement saveWriteElementConvertOperationsToSaveWriteElement = convertOperationsToSaveWriteElement(saveOperations);
            arrayList.add(OperationElement.builder().withId(saveOperations.getOperationId()).withElement(saveWriteElementConvertOperationsToSaveWriteElement).withCondition(TimeConditionElement.builder().withUpdatedTime(saveOperations.getUpdatedTime()).build()).build());
        }
        return SaveOperationsClientState.builder().withOperations(arrayList).build();
    }

    private SaveWriteElement convertOperationsToSaveWriteElement(final SaveOperations operation) {
        try {
            return SaveWriteElement.builder().withId(operation.getId()).withPodcastId(operation.getPodcastId()).withImage(operation.getImage()).withPodcastImage(operation.getPodcastImage()).withPodcastTitle(operation.getPodcastTitle()).withTitle(operation.getTitle()).withDescription(operation.getDescription()).withSeasonNumber(StringUtils.isBlank(operation.getSeasonNumber()) ? null : Integer.valueOf(operation.getSeasonNumber())).withAuthors(Strings.convertStringToList(operation.getAuthors())).withTotalDurationMilliseconds(Long.valueOf(operation.getTotalDurationMilliseconds())).withSaved(operation.isSaved()).build();
        } catch (IOException e) {
            this.logger.error("Error in convertOperationsToSaveWriteElement method", (Throwable) e);
            return SaveWriteElement.builder().build();
        }
    }
}
