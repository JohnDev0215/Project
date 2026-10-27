package com.amazon.podcast.categoryFollow;

import Podcast.CategoryFollowInterface.v1_0.CategoryFollowOperationsClientState;
import Podcast.CategoryFollowInterface.v1_0.CategoryFollowWriteElement;
import Podcast.CategoryFollowInterface.v1_0.CategoryFollowsLastSyncTimeClientState;
import Podcast.CategoryFollowInterface.v1_0.SetCategoryFollowsLastSyncTimeMethod;
import Podcast.CategoryFollowInterface.v1_0.SetOnReportOperationsMethod;
import SOAAppSyncInterface.v1_0.ConditionElement;
import SOAAppSyncInterface.v1_0.OperationElement;
import SOAAppSyncInterface.v1_0.TimeConditionElement;
import SOAAppSyncInterface.v1_0.WriteElement;
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
public class CategoryFollowSyncData extends SyncData<CategoryFollowWriteElement, WriteElement, CategoryFollow, QueryParameters> implements MethodCallback {
    private final CategoryFollowOperationsDoa categoryFollowOperationsDoa;
    private final CategoryFollowsDao categoryFollowsDao;
    private final Context context;
    private final Logger logger = LoggerFactory.getLogger("CategoryFollowSyncData");

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.podcast.appsync.SyncData
    public CategoryFollow read(final String id) {
        return null;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<CategoryFollow>> readStates(final Set<String> ids) {
        return null;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeMetadata(final WriteElement writeElement) {
    }

    public CategoryFollowSyncData(final Engine engine, final String ownerId, final Context context, final PodcastRoomDatabase database) {
        this.context = context;
        this.categoryFollowsDao = database.categoryFollowsDao();
        this.categoryFollowOperationsDoa = database.categoryFollowOperationsDoa();
        engine.registerOwner(ownerId);
        HashSet hashSet = new HashSet();
        hashSet.add(SetOnReportOperationsMethod.class.getCanonicalName());
        hashSet.add(SetCategoryFollowsLastSyncTimeMethod.class.getCanonicalName());
        engine.registerMethods(Collections.emptySet(), hashSet, this);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public boolean writeState(final CategoryFollowWriteElement element, final ConditionElement conditionElement, final long updatedTime) {
        if (conditionElement instanceof TimeConditionElement) {
            updatedTime = ((TimeConditionElement) conditionElement).getUpdatedTime();
            CategoryFollow categoryFollow = this.categoryFollowsDao.get(element.getId());
            if (categoryFollow != null && categoryFollow.getUpdatedTime() > updatedTime) {
                return false;
            }
        }
        this.categoryFollowsDao.insert(new CategoryFollow(element.getId(), element.getTitle(), element.isFollowed(), updatedTime));
        return true;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<CategoryFollow> readState(final String id) {
        return this.categoryFollowsDao.getLiveData(id);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<CategoryFollow>> readStates() {
        return this.categoryFollowsDao.getAll(true);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeToOperations(final CategoryFollowWriteElement element, final long updatedTime) {
        this.categoryFollowOperationsDoa.insert(new CategoryFollowOperations(UUID.randomUUID().toString(), Strings.checkNullString(element.getId()), Strings.checkNullString(element.getTitle()), element.isFollowed(), updatedTime, false));
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public List<Method> reportOperationMethods() {
        return readReportOperationsMethods(this.context);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void deleteOperations(final List<String> ids) {
        this.categoryFollowOperationsDoa.deleteIds(ids);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public ClientStates.Entry getClientState(final String name) {
        if (name.equals(CategoryFollowOperationsClientState.class.getCanonicalName())) {
            return getCategoryFollowOperationsClientState();
        }
        if (name.equals(CategoryFollowsLastSyncTimeClientState.class.getCanonicalName())) {
            return getCategoryFollowsLastSyncTimeClientState();
        }
        return null;
    }

    @Override // com.amazon.soa.core.MethodCallback
    public void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        if (method instanceof SetOnReportOperationsMethod) {
            saveReportOperations(this.context, (SetOnReportOperationsMethod) method);
        } else if (method instanceof SetCategoryFollowsLastSyncTimeMethod) {
            saveLastSyncTime(this.context, (SetCategoryFollowsLastSyncTimeMethod) method);
        }
        listener.onMethodExecuted(method);
    }

    public static void clearData(final Context context) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(context).edit();
        editorEdit.clear();
        editorEdit.apply();
    }

    private ClientStates.Entry getCategoryFollowOperationsClientState() {
        List<CategoryFollowOperations> unProcessed = this.categoryFollowOperationsDoa.getUnProcessed();
        CategoryFollowOperationsClientState categoryFollowOperationsClientStateConvertOperationsToClientState = convertOperationsToClientState(unProcessed);
        ArrayList arrayList = new ArrayList();
        Iterator<CategoryFollowOperations> it = unProcessed.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getOperationId());
        }
        this.categoryFollowOperationsDoa.updateToProcessing(arrayList);
        return ClientStates.Entry.create("categoryFollow", categoryFollowOperationsClientStateConvertOperationsToClientState);
    }

    private ClientStates.Entry getCategoryFollowsLastSyncTimeClientState() {
        return ClientStates.Entry.create("categoryFollowsLastSyncTime", CategoryFollowsLastSyncTimeClientState.builder().withLastSyncTime(getLastSyncTime(this.context)).build());
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

    private CategoryFollowOperationsClientState convertOperationsToClientState(final List<CategoryFollowOperations> pendingOperations) {
        ArrayList arrayList = new ArrayList();
        for (CategoryFollowOperations categoryFollowOperations : pendingOperations) {
            CategoryFollowWriteElement categoryFollowWriteElementConvertOperationsToCategoryFollowWriteElement = convertOperationsToCategoryFollowWriteElement(categoryFollowOperations);
            arrayList.add(OperationElement.builder().withId(categoryFollowOperations.getOperationId()).withElement(categoryFollowWriteElementConvertOperationsToCategoryFollowWriteElement).withCondition(TimeConditionElement.builder().withUpdatedTime(categoryFollowOperations.getUpdatedTime()).build()).build());
        }
        return CategoryFollowOperationsClientState.builder().withOperations(arrayList).build();
    }

    private CategoryFollowWriteElement convertOperationsToCategoryFollowWriteElement(final CategoryFollowOperations operation) {
        return CategoryFollowWriteElement.builder().withId(operation.getElementId()).withTitle(operation.getTitle()).withFollowed(operation.isFollowed()).build();
    }

    private void saveReportOperations(final Context context, final SetOnReportOperationsMethod method) {
        try {
            String strWriteValueAsString = Mappers.mapper().writeValueAsString(method);
            SharedPreferences.Editor editorEdit = getSharedPreferences(context).edit();
            editorEdit.putString("onReportOperations", strWriteValueAsString);
            editorEdit.apply();
        } catch (IOException e) {
            this.logger.error("Error in saveReportOperations", (Throwable) e);
        }
    }

    private void saveLastSyncTime(final Context context, final SetCategoryFollowsLastSyncTimeMethod method) {
        Long lastSyncTime = method.getLastSyncTime();
        SharedPreferences.Editor editorEdit = getSharedPreferences(context).edit();
        editorEdit.putLong("lastSyncTime", lastSyncTime.longValue());
        editorEdit.apply();
    }

    private Long getLastSyncTime(final Context context) {
        return Long.valueOf(getSharedPreferences(context).getLong("lastSyncTime", 0L));
    }

    private static SharedPreferences getSharedPreferences(final Context context) {
        return context.getSharedPreferences("CategoryFollowSync", 0);
    }
}
