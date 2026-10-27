package com.amazon.podcast.bookmark;

import Podcast.BookmarkInterface.v1_0.BookmarkMetadataWriteElement;
import Podcast.BookmarkInterface.v1_0.BookmarkOperationsClientState;
import Podcast.BookmarkInterface.v1_0.BookmarkWriteElement;
import Podcast.BookmarkInterface.v1_0.BookmarksLastSyncTimeClientState;
import Podcast.BookmarkInterface.v1_0.SetBookmarksLastSyncTimeMethod;
import Podcast.BookmarkInterface.v1_0.SetBookmarksThresholdsMethod;
import Podcast.BookmarkInterface.v1_0.SetOnReportOperationsMethod;
import SOAAppSyncInterface.v1_0.ConditionElement;
import SOAAppSyncInterface.v1_0.OperationElement;
import SOAAppSyncInterface.v1_0.TimeConditionElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.LiveData;
import com.amazon.podcast.ClientStates;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.appsync.SyncData;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.mappers.Mappers;
import com.amazon.podcast.media.playback.PlaybackCache;
import com.amazon.podcast.storage.PodcastRoomDatabase;
import com.amazon.podcast.views.Strings;
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
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class BookmarkSyncData extends SyncData<BookmarkWriteElement, BookmarkMetadataWriteElement, Bookmark, BookmarkQueryParameters> implements MethodCallback {
    private final BookmarkOperationsDoa bookmarkOperationsDoa;
    private final BookmarksDao bookmarksDao;
    private final Context context;
    private final Logger logger = LoggerFactory.getLogger("BookmarkSyncData");
    private Integer bookmarksUpperBoundPercentage = Podcast.getBookmarksUpperBoundPercentage();
    private Long bookmarksLowerBoundProgressMS = Podcast.getBookmarksLowerBoundProgressMS();
    private final Object lock = new Object();

    public BookmarkSyncData(final Engine engine, final String ownerId, final Context context, final PodcastRoomDatabase database) {
        this.context = context;
        this.bookmarksDao = database.bookmarksDao();
        this.bookmarkOperationsDoa = database.bookmarkOperationsDoa();
        engine.registerOwner(ownerId);
        HashSet hashSet = new HashSet();
        hashSet.add(SetOnReportOperationsMethod.class.getCanonicalName());
        hashSet.add(SetBookmarksThresholdsMethod.class.getCanonicalName());
        hashSet.add(SetBookmarksLastSyncTimeMethod.class.getCanonicalName());
        engine.registerMethods(Collections.emptySet(), hashSet, this);
        loadThresholds();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.podcast.appsync.SyncData
    public Bookmark read(final String id) {
        return this.bookmarksDao.getData(id, this.bookmarksLowerBoundProgressMS, this.bookmarksUpperBoundPercentage);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<Bookmark> readState(final String id) {
        return this.bookmarksDao.getLiveData(id);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<Bookmark>> readStates() {
        return this.bookmarksDao.getAll(this.bookmarksLowerBoundProgressMS, this.bookmarksUpperBoundPercentage);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public List<Bookmark> readStates(final BookmarkQueryParameters queryParameters) {
        if (queryParameters == null) {
            return Collections.emptyList();
        }
        Set<String> ids = queryParameters.getIds();
        return CollectionUtils.isEmpty(ids) ? Collections.emptyList() : this.bookmarksDao.getAllList(ids);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<Bookmark>> readStates(final Set<String> ids) {
        return this.bookmarksDao.getAll(ids);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public boolean writeState(final BookmarkWriteElement element, final ConditionElement conditionElement, final long updatedTime) {
        long j;
        try {
            String strConvertListToString = "";
            String strMediaId = Podcast.getPlayback().getMedia() == null ? "" : Podcast.getPlayback().getMedia().mediaId();
            int playbackState = Podcast.getPlayback().getPlaybackState();
            boolean z = playbackState == 3 || playbackState == 2;
            if (conditionElement instanceof TimeConditionElement) {
                long updatedTime2 = ((TimeConditionElement) conditionElement).getUpdatedTime();
                PodcastFeatureGating podcastFeatureGating = PodcastFeatureGating.PLAYER_BOOKMARK_DB_BINDINGS;
                if (podcastFeatureGating.isEnabled()) {
                    if (!(playbackState == 2) && element.getId().equals(strMediaId)) {
                        return false;
                    }
                }
                if (!podcastFeatureGating.isEnabled() && z && element.getId().equals(strMediaId)) {
                    return false;
                }
                Bookmark bookmark = this.bookmarksDao.get(element.getId());
                if (bookmark != null && bookmark.getUpdatedTime() > updatedTime2) {
                    return false;
                }
                j = updatedTime2;
            } else {
                j = updatedTime;
            }
            if (element.getAuthors() != null) {
                strConvertListToString = Strings.convertListToString(element.getAuthors());
            }
            Long lProgress = progress(element, conditionElement);
            Long lDuration = duration(element, conditionElement);
            element.setProgressMilliSeconds(lProgress);
            element.setTotalDurationMilliseconds(lDuration);
            this.bookmarksDao.insert(new Bookmark(element.getId(), element.getPodcastId(), element.getPodcastTitle(), element.getTitle(), Strings.checkNullString(element.getImage()), Strings.checkNullString(element.getPodcastImage()), Strings.checkNullString(element.getPublishDate()), Strings.checkNullString(element.getDescription()), strConvertListToString, Strings.checkNullString(element.getSeasonNumber()), element.getTotalDurationMilliseconds().longValue(), element.getProgressMilliSeconds().longValue(), j));
            return true;
        } catch (IOException e) {
            this.logger.error("Error in writeState method", (Throwable) e);
            return false;
        }
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeToOperations(final BookmarkWriteElement element, final long updatedTime) {
        try {
            this.bookmarkOperationsDoa.insert(new BookmarkOperations(UUID.randomUUID().toString(), element.getId(), element.getPodcastId(), element.getPodcastTitle(), Strings.checkNullString(element.getTitle()), Strings.checkNullString(element.getImage()), Strings.checkNullString(element.getPodcastImage()), Strings.checkNullString(element.getPublishDate()), Strings.checkNullString(element.getDescription()), element.getAuthors() == null ? "" : Strings.convertListToString(element.getAuthors()), Strings.checkNullString(element.getSeasonNumber()), element.getTotalDurationMilliseconds().longValue(), element.getProgressMilliSeconds().longValue(), updatedTime, false));
        } catch (IOException e) {
            this.logger.error("Error in writeToOperations method", (Throwable) e);
        }
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeMetadata(final BookmarkMetadataWriteElement element) {
        try {
            Bookmark bookmark = this.bookmarksDao.get(element.getId());
            if (bookmark == null) {
                return;
            }
            this.bookmarksDao.insert(new Bookmark(bookmark, element.getId(), element.getPodcastId(), element.getPodcastTitle(), element.getTitle(), Strings.checkNullString(element.getImage()), Strings.checkNullString(element.getPodcastImage()), Strings.checkNullString(element.getPublishDate()), Strings.checkNullString(element.getDescription()), element.getAuthors() == null ? "" : Strings.convertListToString(element.getAuthors()), Strings.checkNullString(element.getSeasonNumber())));
        } catch (IOException e) {
            this.logger.error("Error in writeMetadata method", (Throwable) e);
        }
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public List<Method> reportOperationMethods() {
        return readReportOperationsMethods(this.context);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public ClientStates.Entry getClientState(final String name) {
        if (name.equals(BookmarkOperationsClientState.class.getCanonicalName())) {
            return getBookmarkOperationsClientState();
        }
        if (name.equals(BookmarksLastSyncTimeClientState.class.getCanonicalName())) {
            return getBookmarksLastSyncTimeClientState();
        }
        return null;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void deleteOperations(final List<String> ids) {
        this.bookmarkOperationsDoa.deleteIds(ids);
    }

    @Override // com.amazon.soa.core.MethodCallback
    public void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        if (method instanceof SetOnReportOperationsMethod) {
            saveReportOperations(this.context, (SetOnReportOperationsMethod) method);
        } else if (method instanceof SetBookmarksThresholdsMethod) {
            setBookmarkThresholds(this.context, (SetBookmarksThresholdsMethod) method);
        } else if (method instanceof SetBookmarksLastSyncTimeMethod) {
            saveLastSyncTime(this.context, (SetBookmarksLastSyncTimeMethod) method);
        }
        listener.onMethodExecuted(method);
    }

    public static void clearData(final Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("BookmarkSync", 0).edit();
        editorEdit.clear();
        editorEdit.apply();
    }

    private ClientStates.Entry getBookmarkOperationsClientState() {
        List<BookmarkOperations> unProcessed = this.bookmarkOperationsDoa.getUnProcessed();
        BookmarkOperationsClientState bookmarkOperationsClientStateConvertOperationsToClientState = convertOperationsToClientState(unProcessed);
        ArrayList arrayList = new ArrayList();
        Iterator<BookmarkOperations> it = unProcessed.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getOperationId());
        }
        this.bookmarkOperationsDoa.updateToProcessing(arrayList);
        return ClientStates.Entry.create("bookmark", bookmarkOperationsClientStateConvertOperationsToClientState);
    }

    private ClientStates.Entry getBookmarksLastSyncTimeClientState() {
        return ClientStates.Entry.create("bookmarksLastSyncTime", BookmarksLastSyncTimeClientState.builder().withLastSyncTime(getLastSyncTime(this.context)).build());
    }

    private void loadThresholds() {
        Handlers.INSTANCE.getBackground().post(new Runnable() { // from class: com.amazon.podcast.bookmark.BookmarkSyncData.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (BookmarkSyncData.this.lock) {
                    BookmarkSyncData bookmarkSyncData = BookmarkSyncData.this;
                    SharedPreferences sharedPreferences = bookmarkSyncData.getSharedPreferences(bookmarkSyncData.context);
                    BookmarkSyncData.this.bookmarksLowerBoundProgressMS = Long.valueOf(sharedPreferences.getLong("lowerBound", TimeUnit.SECONDS.toMillis(30L)));
                    BookmarkSyncData.this.bookmarksUpperBoundPercentage = Integer.valueOf(sharedPreferences.getInt("upperBound", 95));
                }
            }
        });
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

    /* JADX INFO: Access modifiers changed from: private */
    public SharedPreferences getSharedPreferences(final Context context) {
        return context.getSharedPreferences("BookmarkSync", 0);
    }

    private BookmarkOperationsClientState convertOperationsToClientState(final List<BookmarkOperations> Operations) {
        ArrayList arrayList = new ArrayList();
        for (BookmarkOperations bookmarkOperations : Operations) {
            BookmarkWriteElement bookmarkWriteElementConvertOperationsToBookmarkWriteElement = convertOperationsToBookmarkWriteElement(bookmarkOperations);
            arrayList.add(OperationElement.builder().withId(bookmarkOperations.getOperationId()).withElement(bookmarkWriteElementConvertOperationsToBookmarkWriteElement).withCondition(TimeConditionElement.builder().withUpdatedTime(bookmarkOperations.getUpdatedTime()).build()).build());
        }
        return BookmarkOperationsClientState.builder().withOperations(arrayList).build();
    }

    private BookmarkWriteElement convertOperationsToBookmarkWriteElement(final BookmarkOperations operation) {
        try {
            return BookmarkWriteElement.builder().withId(operation.getId()).withPodcastId(operation.getPodcastId()).withImage(operation.getImage()).withPodcastImage(operation.getPodcastImage()).withPodcastTitle(operation.getPodcastTitle()).withTitle(operation.getTitle()).withDescription(operation.getDescription()).withSeasonNumber("".equals(operation.getSeasonNumber()) ? null : Integer.valueOf(operation.getSeasonNumber())).withAuthors(Strings.convertStringToList(operation.getAuthors())).withTotalDurationMilliseconds(Long.valueOf(operation.getTotalDurationMilliseconds())).withProgressMilliSeconds(Long.valueOf(operation.getProgressMilliseconds())).build();
        } catch (IOException e) {
            this.logger.error("Error in convertOperationsToBookmarkWriteElement method", (Throwable) e);
            return BookmarkWriteElement.builder().build();
        }
    }

    private void setBookmarkThresholds(final Context context, final SetBookmarksThresholdsMethod method) {
        try {
            synchronized (this.lock) {
                this.bookmarksUpperBoundPercentage = method.getInProgressUpperBoundPercentage();
                this.bookmarksLowerBoundProgressMS = method.getInProgressLowerBoundMilliseconds();
                SharedPreferences.Editor editorEdit = context.getSharedPreferences("BookmarkSync", 0).edit();
                editorEdit.putInt("upperBound", this.bookmarksUpperBoundPercentage.intValue());
                editorEdit.putLong("lowerBound", this.bookmarksLowerBoundProgressMS.longValue());
                editorEdit.apply();
            }
        } catch (Exception e) {
            this.logger.error("Error in setBookmarkThresholds method", (Throwable) e);
        }
    }

    private void saveReportOperations(final Context context, final SetOnReportOperationsMethod method) {
        try {
            String strWriteValueAsString = Mappers.mapper().writeValueAsString(method);
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("BookmarkSync", 0).edit();
            editorEdit.putString("onReportOperations", strWriteValueAsString);
            editorEdit.apply();
        } catch (IOException e) {
            this.logger.error("Error in saveReportOperations method", (Throwable) e);
        }
    }

    private void saveLastSyncTime(final Context context, final SetBookmarksLastSyncTimeMethod method) {
        Long lastSyncTime = method.getLastSyncTime();
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("BookmarkSync", 0).edit();
        editorEdit.putLong("lastSyncTime", lastSyncTime.longValue());
        editorEdit.apply();
    }

    private Long getLastSyncTime(final Context context) {
        return Long.valueOf(context.getSharedPreferences("BookmarkSync", 0).getLong("lastSyncTime", 0L));
    }

    private Long duration(final BookmarkWriteElement bookmarkWriteElement, final ConditionElement timeConditionElement) {
        Long totalDurationMilliseconds = bookmarkWriteElement == null ? null : bookmarkWriteElement.getTotalDurationMilliseconds();
        if (timeConditionElement != null || Podcast.getPlayback() == null) {
            return totalDurationMilliseconds;
        }
        Long lValueOf = Long.valueOf(PlaybackCache.get(bookmarkWriteElement.getId()).getDurationMilliseconds());
        return lValueOf.longValue() == 0 ? totalDurationMilliseconds : lValueOf;
    }

    private Long progress(final BookmarkWriteElement bookmarkWriteElement, final ConditionElement timeConditionElement) {
        return timeConditionElement != null ? bookmarkWriteElement == null ? null : bookmarkWriteElement.getProgressMilliSeconds() : Long.valueOf(PlaybackCache.get(bookmarkWriteElement.getId()).getProgressMilliseconds());
    }
}
