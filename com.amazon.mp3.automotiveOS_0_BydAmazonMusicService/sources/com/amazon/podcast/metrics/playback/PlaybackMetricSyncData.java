package com.amazon.podcast.metrics.playback;

import PlaybackMetricsInterface.v1_0.PlaybackSignalType;
import Podcast.PlaybackMetricsInterface.v1_0.PlaybackMetricWriteElement;
import Podcast.PlaybackMetricsInterface.v1_0.PlaybackMetricsOperationsClientState;
import Podcast.PlaybackMetricsInterface.v1_0.SetOnReportOperationsMethod;
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
import com.amazon.podcast.appsync.QueryParameters;
import com.amazon.podcast.appsync.SyncData;
import com.amazon.podcast.mappers.Mappers;
import com.amazon.podcast.media.playback.PlaybackCache;
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
public final class PlaybackMetricSyncData extends SyncData<PlaybackMetricWriteElement, WriteElement, Void, QueryParameters> implements MethodCallback {
    private final Context context;
    private final Logger logger = LoggerFactory.getLogger("PlaybackMetricsSyncData");
    private final PlaybackMetricOperationsDao playbackMetricOperationsDao;

    @Override // com.amazon.podcast.appsync.SyncData
    public Void read(final String id) {
        return null;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<Void> readState(final String id) {
        return null;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<Void>> readStates() {
        return null;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<Void>> readStates(final Set<String> ids) {
        return null;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeMetadata(final WriteElement writeElement) {
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public boolean writeState(final PlaybackMetricWriteElement element, final ConditionElement conditionElement, final long updatedTime) {
        return true;
    }

    public PlaybackMetricSyncData(final Engine engine, final String ownerId, final Context context, final PodcastRoomDatabase database) {
        this.context = context;
        this.playbackMetricOperationsDao = database.playbackMetricOperationsDao();
        engine.registerOwner(ownerId);
        HashSet hashSet = new HashSet();
        hashSet.add(SetOnReportOperationsMethod.class.getCanonicalName());
        engine.registerMethods(Collections.emptySet(), hashSet, this);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeToOperations(final PlaybackMetricWriteElement element, final long updatedTime) {
        long initialPlaybackDelayMilliseconds;
        try {
            PlaybackCache playbackCache = PlaybackCache.get(element.getMediaId());
            if ((Podcast.isHarleyEnabled() && PlaybackSignalType.PLAYBACK_STARTED.equals(element.getPlaybackSignalType())) || PlaybackSignalType.BITE_PLAYBACK_STARTED.equals(element.getPlaybackSignalType())) {
                initialPlaybackDelayMilliseconds = playbackCache.getInitialPlaybackDelayFromPlayerMilliseconds();
            } else {
                initialPlaybackDelayMilliseconds = playbackCache.getInitialPlaybackDelayMilliseconds();
            }
            this.playbackMetricOperationsDao.insert(new PlaybackMetricOperations(UUID.randomUUID().toString(), element.getId(), element.getMediaId(), Strings.checkNullString(element.getMediaCollectionType()), element.getPlaybackSignalType(), Strings.checkNullString(element.getMetricsPreset()), element.getPlaybackRequestedAtTimestampMilliseconds().longValue(), playbackCache.getPlaybackStartedAtTimestamp(), playbackCache.isDownloaded(), playbackCache.getProgressMilliseconds(), playbackCache.getPlaybackSpeed(), playbackCache.getPlaybackStartOffsetMilliseconds(), initialPlaybackDelayMilliseconds, playbackCache.getRebufferDurationMilliseconds(), playbackCache.getRebufferCount(), updatedTime, Strings.checkNullString(playbackCache.getIngressSource()), Strings.checkNullString(playbackCache.getAudioUri()), Strings.checkNullString(element.getMediaTitle()), Strings.checkNullString(element.getMediaCollectionId()), Strings.checkNullString(element.getMediaSubTitle()), Strings.checkNullString(playbackCache.getErrorException()), false, Podcast.getPlaybackInstanceId(), playbackCache.getPlaybackEngineType()));
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
        if (!name.equals(PlaybackMetricsOperationsClientState.class.getCanonicalName())) {
            return null;
        }
        List<PlaybackMetricOperations> unProcessed = this.playbackMetricOperationsDao.getUnProcessed();
        PlaybackMetricsOperationsClientState playbackMetricsOperationsClientStateConvertOperationsToClientState = convertOperationsToClientState(unProcessed);
        ArrayList arrayList = new ArrayList();
        Iterator<PlaybackMetricOperations> it = unProcessed.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getOperationId());
        }
        this.playbackMetricOperationsDao.updateToProcessing(arrayList);
        return ClientStates.Entry.create("playbackMetric", playbackMetricsOperationsClientStateConvertOperationsToClientState);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void deleteOperations(final List<String> ids) {
        this.playbackMetricOperationsDao.deleteIds(ids);
    }

    @Override // com.amazon.soa.core.MethodCallback
    public void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        if (method instanceof SetOnReportOperationsMethod) {
            saveReportOperations(this.context, (SetOnReportOperationsMethod) method);
        }
        listener.onMethodExecuted(method);
    }

    public static void clearData(final Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("PlaybackMetricSync", 0).edit();
        editorEdit.clear();
        editorEdit.apply();
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
        return context.getSharedPreferences("PlaybackMetricSync", 0);
    }

    private PlaybackMetricsOperationsClientState convertOperationsToClientState(final List<PlaybackMetricOperations> Operations) {
        ArrayList arrayList = new ArrayList();
        for (PlaybackMetricOperations playbackMetricOperations : Operations) {
            PlaybackMetricWriteElement playbackMetricWriteElementConvertOperationsToPlaybackMetricWriteElement = convertOperationsToPlaybackMetricWriteElement(playbackMetricOperations);
            arrayList.add(OperationElement.builder().withId(playbackMetricOperations.getOperationId()).withElement(playbackMetricWriteElementConvertOperationsToPlaybackMetricWriteElement).withCondition(TimeConditionElement.builder().withUpdatedTime(playbackMetricOperations.getUpdatedTime()).build()).build());
        }
        return PlaybackMetricsOperationsClientState.builder().withOperations(arrayList).build();
    }

    private PlaybackMetricWriteElement convertOperationsToPlaybackMetricWriteElement(final PlaybackMetricOperations operation) {
        return PlaybackMetricWriteElement.builder().withId(operation.getId()).withMediaId(operation.getMediaId()).withMetricsPreset(operation.getMetricsPreset()).withMediaCollectionType(operation.getMediaCollectionType()).withPlaybackSignalType(operation.getPlaybackSignalType()).withCurrentPlaybackSpeed(Float.valueOf(operation.getCurrentPlaybackSpeed())).withCurrentProgressMilliseconds(Long.valueOf(operation.getCurrentProgressMilliseconds())).withPlaybackStartedAtTimestampMilliseconds(Long.valueOf(operation.getPlaybackStartedAtMillisecocnds())).withPlaybackRequestedAtTimestampMilliseconds(Long.valueOf(operation.getPlaybackRequestedAtMillisecocnds())).withInitialPlaybackStartDelayMilliseconds(Long.valueOf(operation.getInitialPlaybackStartDelayMilliseconds())).withIsMediaDownloaded(operation.isMediaDownloaded()).withPlaybackStartOffsetMilliseconds(Long.valueOf(operation.getPlaybackStartOffsetMilliseconds())).withRebufferCount(Integer.valueOf(operation.getRebufferCount())).withRebufferDurationMilliseconds(Long.valueOf(operation.getRebufferDurationMilliseconds())).withAudioUri(operation.getAudioUri()).withPageType(operation.getIngressSource()).withErrorException(operation.getErrorException()).withMediaTitle(operation.getMediaTitle()).withMediaCollectionId(operation.getMediaCollectionId()).withMediaSubTitle(operation.getMediaSubTitle()).withPlaybackInstanceId(operation.getPlaybackInstanceId()).withPlaybackEngineType(operation.getPlaybackEngineType()).build();
    }

    private void saveReportOperations(final Context context, final SetOnReportOperationsMethod method) {
        try {
            String strWriteValueAsString = Mappers.mapper().writeValueAsString(method);
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("PlaybackMetricSync", 0).edit();
            editorEdit.putString("onReportOperations", strWriteValueAsString);
            editorEdit.apply();
        } catch (IOException e) {
            this.logger.error("Error in saveReportOperations method", (Throwable) e);
        }
    }
}
