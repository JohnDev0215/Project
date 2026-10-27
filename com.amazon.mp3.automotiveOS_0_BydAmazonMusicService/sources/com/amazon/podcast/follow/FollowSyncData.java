package com.amazon.podcast.follow;

import Podcast.FollowInterface.v1_0.FollowMetadataWriteElement;
import Podcast.FollowInterface.v1_0.FollowOperationsClientState;
import Podcast.FollowInterface.v1_0.FollowWriteElement;
import Podcast.FollowInterface.v1_0.FollowsLastSyncTimeClientState;
import Podcast.FollowInterface.v1_0.SetFollowsLastSyncTimeMethod;
import Podcast.FollowInterface.v1_0.SetOnReportOperationsMethod;
import SOAAppSyncInterface.v1_0.ConditionElement;
import SOAAppSyncInterface.v1_0.OperationElement;
import SOAAppSyncInterface.v1_0.TimeConditionElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.LiveData;
import com.amazon.podcast.ClientStates;
import com.amazon.podcast.appsync.QueryParameters;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class FollowSyncData extends SyncData<FollowWriteElement, FollowMetadataWriteElement, Follow, QueryParameters> implements MethodCallback {
    private final Context context;
    private final FollowOperationsDoa followOperationsDoa;
    private final FollowsDao followsDao;
    private final Logger logger = LoggerFactory.getLogger("FollowSyncData");

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.podcast.appsync.SyncData
    public Follow read(final String id) {
        return null;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<Follow>> readStates(final Set<String> ids) {
        return null;
    }

    public FollowSyncData(final Engine engine, final String ownerId, final Context context, final PodcastRoomDatabase database) {
        this.context = context;
        this.followsDao = database.followsDao();
        this.followOperationsDoa = database.followOperationsDoa();
        engine.registerOwner(ownerId);
        HashSet hashSet = new HashSet();
        hashSet.add(SetOnReportOperationsMethod.class.getCanonicalName());
        hashSet.add(SetFollowsLastSyncTimeMethod.class.getCanonicalName());
        engine.registerMethods(Collections.emptySet(), hashSet, this);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public boolean writeState(final FollowWriteElement element, final ConditionElement conditionElement, final long updatedTime) {
        if (conditionElement instanceof TimeConditionElement) {
            updatedTime = ((TimeConditionElement) conditionElement).getUpdatedTime();
            Follow follow = this.followsDao.get(element.getId());
            if (follow != null && follow.getUpdatedTime() > updatedTime) {
                return false;
            }
        }
        this.followsDao.insert(new Follow(element.getId(), element.getTitle(), Strings.checkNullString(element.getImage()), Strings.checkNullString(element.getDescription()), Strings.checkNullString(element.getPublisher()), element.isFollowed(), updatedTime, Strings.checkNullString(element.getPodcastShowVariantId())));
        return true;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeMetadata(final FollowMetadataWriteElement element) {
        Follow follow = this.followsDao.get(element.getId());
        if (follow == null) {
            return;
        }
        this.followsDao.insert(new Follow(follow, element.getId(), element.getTitle(), Strings.checkNullString(element.getImage()), Strings.checkNullString(element.getDescription()), Strings.checkNullString(element.getPublisher()), Strings.checkNullString(element.getPodcastShowVariantId())));
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<Follow> readState(final String id) {
        return this.followsDao.getLiveData(id);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<Follow>> readStates() {
        return this.followsDao.getAll(true);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeToOperations(final FollowWriteElement element, final long updatedTime) {
        this.followOperationsDoa.insert(new FollowOperations(UUID.randomUUID().toString(), element.getId(), element.getTitle(), Strings.checkNullString(element.getImage()), Strings.checkNullString(element.getDescription()), Strings.checkNullString(element.getPublisher()), element.isFollowed(), updatedTime, false));
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public List<Method> reportOperationMethods() {
        return readReportOperationsMethods(this.context);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void deleteOperations(final List<String> ids) {
        this.followOperationsDoa.deleteIds(ids);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public ClientStates.Entry getClientState(final String name) {
        if (name.equals(FollowOperationsClientState.class.getCanonicalName())) {
            return getFollowOperationsClientState();
        }
        if (name.equals(FollowsLastSyncTimeClientState.class.getCanonicalName())) {
            return getFollowsLastSyncTimeClientState();
        }
        return null;
    }

    private ClientStates.Entry getFollowOperationsClientState() {
        List<FollowOperations> unProcessed = this.followOperationsDoa.getUnProcessed();
        FollowOperationsClientState followOperationsClientStateConvertOperationsToClientState = convertOperationsToClientState(unProcessed);
        ArrayList arrayList = new ArrayList();
        Iterator<FollowOperations> it = unProcessed.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getOperationId());
        }
        this.followOperationsDoa.updateToProcessing(arrayList);
        return ClientStates.Entry.create("follow", followOperationsClientStateConvertOperationsToClientState);
    }

    private ClientStates.Entry getFollowsLastSyncTimeClientState() {
        return ClientStates.Entry.create("followsLastSyncTime", FollowsLastSyncTimeClientState.builder().withLastSyncTime(getLastSyncTime(this.context)).build());
    }

    @Override // com.amazon.soa.core.MethodCallback
    public void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        if (method instanceof SetOnReportOperationsMethod) {
            saveReportOperations(this.context, (SetOnReportOperationsMethod) method);
        } else if (method instanceof SetFollowsLastSyncTimeMethod) {
            saveLastSyncTime(this.context, (SetFollowsLastSyncTimeMethod) method);
        }
        listener.onMethodExecuted(method);
    }

    public static void clearData(final Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("FollowSync", 0).edit();
        editorEdit.clear();
        editorEdit.apply();
    }

    private void saveLastSyncTime(final Context context, final SetFollowsLastSyncTimeMethod method) {
        Long lastSyncTime = method.getLastSyncTime();
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("FollowSync", 0).edit();
        editorEdit.putLong("lastSyncTime", lastSyncTime.longValue());
        editorEdit.apply();
    }

    private Long getLastSyncTime(final Context context) {
        return Long.valueOf(context.getSharedPreferences("FollowSync", 0).getLong("lastSyncTime", 0L));
    }

    private void saveReportOperations(final Context context, final SetOnReportOperationsMethod method) {
        try {
            String strWriteValueAsString = Mappers.mapper().writeValueAsString(method);
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("FollowSync", 0).edit();
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
        return context.getSharedPreferences("FollowSync", 0);
    }

    private FollowOperationsClientState convertOperationsToClientState(final List<FollowOperations> pendingOperations) {
        ArrayList arrayList = new ArrayList();
        for (FollowOperations followOperations : pendingOperations) {
            FollowWriteElement followWriteElementConvertOperationsToFollowWriteElement = convertOperationsToFollowWriteElement(followOperations);
            arrayList.add(OperationElement.builder().withId(followOperations.getOperationId()).withElement(followWriteElementConvertOperationsToFollowWriteElement).withCondition(TimeConditionElement.builder().withUpdatedTime(followOperations.getUpdatedTime()).build()).build());
        }
        return FollowOperationsClientState.builder().withOperations(arrayList).build();
    }

    private FollowWriteElement convertOperationsToFollowWriteElement(final FollowOperations operation) {
        return FollowWriteElement.builder().withId(operation.getElementId()).withTitle(operation.getTitle()).withImage(Strings.checkNullString(operation.getImage())).withDescription(Strings.checkNullString(operation.getDescription())).withPublisher(Strings.checkNullString(operation.getPublisher())).withFollowed(operation.isFollowed()).build();
    }
}
