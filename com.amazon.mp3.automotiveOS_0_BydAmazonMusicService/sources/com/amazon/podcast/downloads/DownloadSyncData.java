package com.amazon.podcast.downloads;

import Podcast.DownloadInterface.v1_0.DownloadOperationsClientState;
import Podcast.DownloadInterface.v1_0.DownloadWriteElement;
import Podcast.DownloadInterface.v1_0.SetOnReportOperationsMethod;
import Podcast.Touch.AlertTemplateInterface.v1_0.AlertTemplate;
import Podcast.Touch.AlertTemplateInterface.v1_0.PositiveButtonElement;
import SOAAppSyncInterface.v1_0.ConditionElement;
import SOAAppSyncInterface.v1_0.OperationElement;
import SOAAppSyncInterface.v1_0.TimeConditionElement;
import SOAAppSyncInterface.v1_0.WriteElement;
import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.CreateAndBindTemplateMethod;
import SOATemplateListInterface.v1_0.TemplateLaunchMode;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import androidx.lifecycle.LiveData;
import com.amazon.music.downloads.worker.DownloadEventListener;
import com.amazon.music.downloads.worker.ErrorReason;
import com.amazon.podcast.ClientStates;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.Queues;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$string;
import com.amazon.podcast.appsync.SyncData;
import com.amazon.podcast.mappers.Mappers;
import com.amazon.podcast.storage.PodcastRoomDatabase;
import com.amazon.podcast.views.Strings;
import com.amazon.soa.core.Engine;
import com.amazon.soa.core.MethodCallback;
import com.amazon.soa.core.OnMethodExecutedListener;
import com.google.firebase.perf.plugin.util.AsmString;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class DownloadSyncData extends SyncData<DownloadWriteElement, WriteElement, Download, DownloadQueryParameters> implements MethodCallback, DownloadEventListener {
    private final Context context;
    private final DownloadOperationsDao downloadOperationsDao;
    private final Downloads downloads;
    private final DownloadsDao downloadsDao;
    private final Engine engine;
    private final Logger logger = LoggerFactory.getLogger("DownloadSyncData");
    private final String ownerId;
    private final Storage storage;

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onAdded(final String id) {
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onPaused(final String id) {
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onResumed(final String id) {
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<Download>> readStates(final Set<String> ids) {
        return null;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeMetadata(final WriteElement writeElement) {
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeToOperations(final DownloadWriteElement element, final long updatedTime) {
    }

    public DownloadSyncData(final Engine engine, final String ownerId, final Context context, final PodcastRoomDatabase database) {
        this.context = context;
        this.engine = engine;
        this.ownerId = ownerId;
        this.storage = new Storage(context);
        this.downloadsDao = database.downloadsDao();
        this.downloadOperationsDao = database.downloadOperationsDao();
        Downloads downloads = Podcast.getDownloads();
        this.downloads = downloads;
        downloads.setDownloadEventListener(this);
        engine.registerOwner(ownerId);
        HashSet hashSet = new HashSet();
        hashSet.add(SetOnReportOperationsMethod.class.getCanonicalName());
        hashSet.add(DeleteDownloadMethod.class.getCanonicalName());
        hashSet.add(DownloadRequestedMethod.class.getCanonicalName());
        hashSet.add(DownloadRequestCompletedMethod.class.getCanonicalName());
        engine.registerMethods(Collections.emptySet(), hashSet, this);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.podcast.appsync.SyncData
    public Download read(final String id) {
        return this.downloadsDao.get(id);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<Download> readState(final String id) {
        return this.downloadsDao.getLiveData(id);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<Download>> readStates() {
        return this.downloadsDao.getAllLiveData();
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<Download>> readStatesLiveData(final DownloadQueryParameters queryParameters) {
        if (queryParameters == null) {
            return this.downloadsDao.getAllLiveData();
        }
        String podcastId = queryParameters.getPodcastId();
        String seasonNumber = queryParameters.getSeasonNumber();
        if (StringUtils.isNotBlank(seasonNumber) && StringUtils.isNotBlank(podcastId)) {
            return this.downloadsDao.getAllLiveData(podcastId, seasonNumber);
        }
        if (StringUtils.isBlank(seasonNumber) && StringUtils.isNotBlank(podcastId)) {
            return this.downloadsDao.getAllLiveData(podcastId);
        }
        return this.downloadsDao.getAllLiveData();
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public List<Download> readStates(final DownloadQueryParameters queryParameters) {
        if (queryParameters == null) {
            return this.downloadsDao.getAll();
        }
        String podcastId = queryParameters.getPodcastId();
        String seasonNumber = queryParameters.getSeasonNumber();
        if (StringUtils.isNotBlank(seasonNumber) && StringUtils.isNotBlank(podcastId)) {
            return this.downloadsDao.getAll(podcastId, seasonNumber);
        }
        if (StringUtils.isBlank(seasonNumber) && StringUtils.isNotBlank(podcastId)) {
            return this.downloadsDao.getAll(podcastId);
        }
        return this.downloadsDao.getAll();
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public boolean writeState(final DownloadWriteElement element, final ConditionElement conditionElement, final long updatedTime) throws IOException {
        List<String> contentTraits;
        List<String> availableUpsells;
        String strConvertListToString = "";
        try {
            if (element.getAuthors() != null) {
                strConvertListToString = Strings.convertListToString(element.getAuthors());
            }
        } catch (Exception e) {
            this.logger.error("Error in writeState method", (Throwable) e);
        }
        String str = strConvertListToString;
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
        Download download = new Download(element.getId(), element.getPodcastId(), element.getPodcastTitle(), Strings.checkNullString(element.getTitle()), Strings.checkNullString(element.getImage()), Strings.checkNullString(element.getPodcastImage()), Strings.checkNullString(element.getPublishDate()), Strings.checkNullString(element.getDescription()), str, Strings.checkNullString(element.getSeasonNumber()), element.getDurationMilliseconds().longValue(), element.isExplicit(), DownloadState.QUEUED.getValue(), getDestinationUri(element), updatedTime, Strings.checkNullString(element.getPlaybackMode()), list, availableUpsells, Strings.checkNullString(element.getAvailabilityDate()), Strings.checkNullString(element.getPodcastShowVariantId()), Strings.checkNullString(element.getPodcastEpisodeVariantId()));
        try {
            this.downloadsDao.insert(download);
        } catch (Exception e2) {
            this.logger.error("Error writing dao", (Throwable) e2);
        }
        startDownload(element.getAudioUri(), download);
        return true;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public List<Method> reportOperationMethods() {
        return readReportOperationsMethods(this.context);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public ClientStates.Entry getClientState(final String name) {
        if (!name.equals(DownloadOperationsClientState.class.getCanonicalName())) {
            return null;
        }
        List<DownloadOperations> unProcessed = this.downloadOperationsDao.getUnProcessed();
        DownloadOperationsClientState downloadOperationsClientStateConvertOperationsToClientState = convertOperationsToClientState(unProcessed);
        ArrayList arrayList = new ArrayList();
        Iterator<DownloadOperations> it = unProcessed.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getOperationId());
        }
        this.downloadOperationsDao.updateToProcessing(arrayList);
        return ClientStates.Entry.create("download", downloadOperationsClientStateConvertOperationsToClientState);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void deleteOperations(final List<String> ids) {
        this.downloadOperationsDao.deleteIds(ids);
    }

    @Override // com.amazon.soa.core.MethodCallback
    public void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        if (method instanceof SetOnReportOperationsMethod) {
            saveReportOperations(this.context, (SetOnReportOperationsMethod) method);
        } else if (method instanceof DeleteDownloadMethod) {
            Handlers.INSTANCE.getBackground().post(new Runnable() { // from class: com.amazon.podcast.downloads.DownloadSyncData.1
                @Override // java.lang.Runnable
                public void run() {
                    String id = ((DeleteDownloadMethod) method).getId();
                    DownloadSyncData.this.deleteFromStorage(id);
                    DownloadSyncData.this.downloadsDao.delete(id);
                }
            });
        } else if (method instanceof DownloadRequestedMethod) {
            this.downloads.downloadRequested(((DownloadRequestedMethod) method).getId());
        } else if (method instanceof DownloadRequestCompletedMethod) {
            this.downloads.downloadRequestFailed(((DownloadRequestCompletedMethod) method).getId());
        }
        listener.onMethodExecuted(method);
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onProgress(final String id, final long bytesDownloaded, final long fileSizeInBytes) {
        Download download = this.downloadsDao.get(id);
        if (download == null) {
            return;
        }
        DownloadState downloadStateLookup = DownloadState.lookup(download.getState());
        DownloadState downloadState = DownloadState.IN_PROGRESS;
        if (downloadState == downloadStateLookup) {
            return;
        }
        this.downloadsDao.insert(Download.fromState(download, downloadState));
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onCanceled(final String id) {
        this.downloadsDao.delete(id);
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onError(final String id, final ErrorReason errorReason) {
        String string;
        this.logger.debug("Download failed due to: " + errorReason);
        Resources resources = this.context.getResources();
        if (errorReason == ErrorReason.DISK_OUT_OF_SPACE) {
            string = resources.getString(R$string.podcast_download_failed_storage_full);
        } else {
            string = String.format(resources.getString(R$string.podcast_download_failed_unknown), String.valueOf(errorReason));
        }
        if (id == null) {
            return;
        }
        this.downloadsDao.delete(id);
        this.engine.handleMethods(this.ownerId, alertDownloadError(string));
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onError(final String id, final ErrorReason errorReason, final int errorCode) {
        String string;
        this.logger.debug("Download failed due to: " + errorCode);
        Resources resources = this.context.getResources();
        if (errorReason == ErrorReason.DISK_OUT_OF_SPACE) {
            string = resources.getString(R$string.podcast_download_failed_storage_full);
        } else {
            string = String.format(resources.getString(R$string.podcast_download_failed_unknown), String.valueOf(errorCode));
        }
        if (id == null) {
            return;
        }
        this.downloadsDao.delete(id);
        this.engine.handleMethods(this.ownerId, alertDownloadError(string));
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onDownloaded(final String id) {
        Download download = this.downloadsDao.get(id);
        if (download == null) {
            return;
        }
        this.downloadsDao.insert(Download.fromState(download, DownloadState.DOWNLOADED));
    }

    public static void clearData(final Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("DownloadSync", 0).edit();
        editorEdit.clear();
        editorEdit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteFromStorage(final String id) {
        Download download = this.downloadsDao.get(id);
        if (download == null) {
            return;
        }
        File file = new File(download.getLocalAudioUri());
        file.delete();
        if (file.exists()) {
            try {
                file.getCanonicalFile().delete();
            } catch (Exception e) {
                this.logger.info("Could not delete file without Application Context: " + e.getMessage());
            }
            if (file.exists()) {
                this.context.deleteFile(file.getName());
            }
        }
    }

    private void startDownload(final String audioUri, final Download download) {
        this.logger.debug("Starting download");
        String id = download.getId();
        Intent intent = new Intent(this.context, (Class<?>) DownloadService.class);
        intent.putExtra("action", AsmString.METHOD_FIREBASE_PERF_TRACE_START);
        intent.putExtra("id", id);
        intent.putExtra("audioUri", audioUri);
        intent.putExtra("destinationUri", download.getLocalAudioUri());
        this.context.startService(intent);
    }

    private String getDestinationUri(final DownloadWriteElement element) {
        String str = Uri.parse(element.getAudioUri()).getPathSegments().get(r0.size() - 1);
        this.logger.debug("fileName {}", str);
        String strSubstring = str.substring(str.lastIndexOf(".") + 1);
        this.logger.debug("extension {}", strSubstring);
        String str2 = this.storage.getRootDestination() + "/" + element.getId() + "." + strSubstring;
        this.logger.debug("destinationUri {}", str2);
        return str2;
    }

    private void saveReportOperations(final Context context, final SetOnReportOperationsMethod method) {
        try {
            String strWriteValueAsString = Mappers.mapper().writeValueAsString(method);
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("DownloadSync", 0).edit();
            editorEdit.putString("onReportOperations", strWriteValueAsString);
            editorEdit.apply();
        } catch (IOException e) {
            this.logger.error("Error in saveReportOperations method", (Throwable) e);
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
            this.logger.error("Error in readReportOperationsMethods method", (Throwable) e);
            return Collections.emptyList();
        }
    }

    private SharedPreferences getSharedPreferences(final Context context) {
        return context.getSharedPreferences("DownloadSync", 0);
    }

    private DownloadOperationsClientState convertOperationsToClientState(final List<DownloadOperations> Operations) {
        ArrayList arrayList = new ArrayList();
        for (DownloadOperations downloadOperations : Operations) {
            DownloadWriteElement downloadWriteElementConvertOperationsToDownloadWriteElement = convertOperationsToDownloadWriteElement(downloadOperations);
            arrayList.add(OperationElement.builder().withId(downloadOperations.getOperationId()).withElement(downloadWriteElementConvertOperationsToDownloadWriteElement).withCondition(TimeConditionElement.builder().withUpdatedTime(downloadOperations.getUpdatedTime()).build()).build());
        }
        return DownloadOperationsClientState.builder().withOperations(arrayList).build();
    }

    private DownloadWriteElement convertOperationsToDownloadWriteElement(final DownloadOperations operation) {
        try {
            String seasonNumber = operation.getSeasonNumber();
            return DownloadWriteElement.builder().withId(operation.getId()).withPodcastId(operation.getPodcastId()).withImage(operation.getImage()).withPodcastImage(operation.getPodcastImage()).withPodcastTitle(operation.getPodcastTitle()).withTitle(operation.getTitle()).withDescription(operation.getDescription()).withSeasonNumber("".equals(seasonNumber) ? null : Integer.valueOf(Integer.parseInt(seasonNumber))).withAuthors(Strings.convertStringToList(operation.getAuthors())).withDurationMilliseconds(Long.valueOf(operation.getDurationMilliseconds())).build();
        } catch (IOException e) {
            this.logger.error("Error in convertOperationsToDownloadWriteElement method", (Throwable) e);
            return DownloadWriteElement.builder().build();
        }
    }

    private List<Method> alertDownloadError(final String errorMessage) {
        return Collections.singletonList(CreateAndBindTemplateMethod.builder().withTemplate(AlertTemplate.builder().withImage(String.valueOf(R$drawable.ic_action_downloadfailed)).withTitle(this.context.getResources().getString(R$string.podcast_download_failed)).withSubtitle(errorMessage).withPositiveButton(PositiveButtonElement.builder().withText(this.context.getResources().getString(R$string.podcast_alert_ok)).withOnItemSelected(Collections.EMPTY_LIST).build()).withLaunchMode(TemplateLaunchMode.singleUse).build()).withQueue(Queues.template()).build());
    }
}
