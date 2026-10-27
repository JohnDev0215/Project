package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bugsnag.android.FileStore;
import com.bugsnag.android.JsonStream;
import com.bugsnag.android.internal.BackgroundTaskService;
import com.bugsnag.android.internal.ImmutableConfig;
import com.bugsnag.android.internal.TaskType;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes5.dex */
class EventStore extends FileStore {
    static final Comparator<File> EVENT_COMPARATOR = new Comparator<File>() { // from class: com.bugsnag.android.EventStore.1
        @Override // java.util.Comparator
        public int compare(File file, File file2) {
            if (file == null && file2 == null) {
                return 0;
            }
            if (file == null) {
                return 1;
            }
            if (file2 == null) {
                return -1;
            }
            return file.compareTo(file2);
        }
    };
    private static long oneMegabyte = 1048576;
    private final BackgroundTaskService bgTaskSevice;
    private final CallbackState callbackState;
    private final ImmutableConfig config;
    private final FileStore.Delegate delegate;
    final Logger logger;
    private final Notifier notifier;

    EventStore(@NonNull ImmutableConfig immutableConfig, @NonNull Logger logger, Notifier notifier, BackgroundTaskService backgroundTaskService, FileStore.Delegate delegate, CallbackState callbackState) {
        super(new File(immutableConfig.getPersistenceDirectory().getValue(), "bugsnag-errors"), immutableConfig.getMaxPersistedEvents(), EVENT_COMPARATOR, logger, delegate);
        this.config = immutableConfig;
        this.logger = logger;
        this.delegate = delegate;
        this.notifier = notifier;
        this.bgTaskSevice = backgroundTaskService;
        this.callbackState = callbackState;
    }

    void flushOnLaunch() {
        if (this.config.getSendLaunchCrashesSynchronously()) {
            Future<?> futureSubmitTask = null;
            try {
                futureSubmitTask = this.bgTaskSevice.submitTask(TaskType.ERROR_REQUEST, new Runnable() { // from class: com.bugsnag.android.EventStore.2
                    @Override // java.lang.Runnable
                    public void run() {
                        EventStore.this.flushLaunchCrashReport();
                    }
                });
            } catch (RejectedExecutionException e) {
                this.logger.d("Failed to flush launch crash reports, continuing.", e);
            }
            if (futureSubmitTask != null) {
                try {
                    futureSubmitTask.get(2000L, TimeUnit.MILLISECONDS);
                } catch (InterruptedException | ExecutionException | TimeoutException e2) {
                    this.logger.d("Failed to send launch crash reports within 2s timeout, continuing.", e2);
                }
            }
        }
    }

    void flushLaunchCrashReport() {
        List<File> listFindStoredFiles = findStoredFiles();
        File fileFindLaunchCrashReport = findLaunchCrashReport(listFindStoredFiles);
        if (fileFindLaunchCrashReport != null) {
            listFindStoredFiles.remove(fileFindLaunchCrashReport);
        }
        cancelQueuedFiles(listFindStoredFiles);
        if (fileFindLaunchCrashReport != null) {
            this.logger.i("Attempting to send the most recent launch crash report");
            flushReports(Collections.singletonList(fileFindLaunchCrashReport));
            this.logger.i("Continuing with Bugsnag initialisation");
            return;
        }
        this.logger.d("No startupcrash events to flush to Bugsnag.");
    }

    @Nullable
    File findLaunchCrashReport(Collection<File> collection) {
        ArrayList arrayList = new ArrayList();
        for (File file : collection) {
            if (EventFilenameInfo.fromFile(file, this.config).isLaunchCrashReport()) {
                arrayList.add(file);
            }
        }
        Collections.sort(arrayList, EVENT_COMPARATOR);
        if (arrayList.isEmpty()) {
            return null;
        }
        return (File) arrayList.get(arrayList.size() - 1);
    }

    @Nullable
    Future<String> writeAndDeliver(@NonNull JsonStream.Streamable streamable) {
        final String strWrite = write(streamable);
        if (strWrite == null) {
            return null;
        }
        try {
            return this.bgTaskSevice.submitTask(TaskType.ERROR_REQUEST, new Callable<String>() { // from class: com.bugsnag.android.EventStore.3
                @Override // java.util.concurrent.Callable
                public String call() {
                    EventStore.this.flushEventFile(new File(strWrite));
                    return strWrite;
                }
            });
        } catch (RejectedExecutionException unused) {
            this.logger.w("Failed to flush all on-disk errors, retaining unsent errors for later.");
            return null;
        }
    }

    void flushAsync() {
        try {
            this.bgTaskSevice.submitTask(TaskType.ERROR_REQUEST, new Runnable() { // from class: com.bugsnag.android.EventStore.4
                @Override // java.lang.Runnable
                public void run() {
                    List<File> listFindStoredFiles = EventStore.this.findStoredFiles();
                    if (listFindStoredFiles.isEmpty()) {
                        EventStore.this.logger.d("No regular events to flush to Bugsnag.");
                    }
                    EventStore.this.flushReports(listFindStoredFiles);
                }
            });
        } catch (RejectedExecutionException unused) {
            this.logger.w("Failed to flush all on-disk errors, retaining unsent errors for later.");
        }
    }

    void flushReports(Collection<File> collection) {
        if (collection.isEmpty()) {
            return;
        }
        int size = collection.size();
        this.logger.i("Sending " + size + " saved error(s) to Bugsnag");
        Iterator<File> it = collection.iterator();
        while (it.hasNext()) {
            flushEventFile(it.next());
        }
    }

    void flushEventFile(File file) {
        try {
            EventPayload eventPayloadCreateEventPayload = createEventPayload(file, EventFilenameInfo.fromFile(file, this.config).getApiKey());
            if (eventPayloadCreateEventPayload == null) {
                deleteStoredFiles(Collections.singleton(file));
            } else {
                deliverEventPayload(file, eventPayloadCreateEventPayload);
            }
        } catch (Exception e) {
            handleEventFlushFailure(e, file);
        }
    }

    private void deliverEventPayload(File file, EventPayload eventPayload) {
        int i = AnonymousClass5.$SwitchMap$com$bugsnag$android$DeliveryStatus[this.config.getDelivery().deliver(eventPayload, this.config.getErrorApiDeliveryParams(eventPayload)).ordinal()];
        if (i == 1) {
            deleteStoredFiles(Collections.singleton(file));
            this.logger.i("Deleting sent error file " + file.getName());
            return;
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
            handleEventFlushFailure(new RuntimeException("Failed to deliver event payload"), file);
            return;
        }
        if (isTooBig(file)) {
            this.logger.w("Discarding over-sized event (" + file.length() + ") after failed delivery");
            deleteStoredFiles(Collections.singleton(file));
            return;
        }
        if (isTooOld(file)) {
            this.logger.w("Discarding historical event (from " + getCreationDate(file) + ") after failed delivery");
            deleteStoredFiles(Collections.singleton(file));
            return;
        }
        cancelQueuedFiles(Collections.singleton(file));
        this.logger.w("Could not send previously saved error(s) to Bugsnag, will try again later");
    }

    /* JADX INFO: renamed from: com.bugsnag.android.EventStore$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$bugsnag$android$DeliveryStatus;

        static {
            int[] iArr = new int[DeliveryStatus.values().length];
            $SwitchMap$com$bugsnag$android$DeliveryStatus = iArr;
            try {
                iArr[DeliveryStatus.DELIVERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$bugsnag$android$DeliveryStatus[DeliveryStatus.UNDELIVERED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$bugsnag$android$DeliveryStatus[DeliveryStatus.FAILURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Nullable
    private EventPayload createEventPayload(File file, String str) {
        MarshalledEventSource marshalledEventSource = new MarshalledEventSource(file, str, this.logger);
        try {
            if (!this.callbackState.runOnSendTasks(marshalledEventSource, this.logger)) {
                return null;
            }
        } catch (Exception unused) {
            marshalledEventSource.clear();
        }
        Event event = marshalledEventSource.getEvent();
        if (event != null) {
            return new EventPayload(event.getApiKey(), event, null, this.notifier, this.config);
        }
        return new EventPayload(str, null, file, this.notifier, this.config);
    }

    private void handleEventFlushFailure(Exception exc, File file) {
        FileStore.Delegate delegate = this.delegate;
        if (delegate != null) {
            delegate.onErrorIOFailure(exc, file, "Crash Report Deserialization");
        }
        deleteStoredFiles(Collections.singleton(file));
    }

    @Override // com.bugsnag.android.FileStore
    @NonNull
    String getFilename(Object obj) {
        return EventFilenameInfo.fromEvent(obj, null, this.config).encode();
    }

    String getNdkFilename(Object obj, String str) {
        return EventFilenameInfo.fromEvent(obj, str, this.config).encode();
    }

    public boolean isTooBig(File file) {
        return file.length() > oneMegabyte;
    }

    public boolean isTooOld(File file) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -60);
        return EventFilenameInfo.findTimestampInFilename(file) < calendar.getTimeInMillis();
    }

    public Date getCreationDate(File file) {
        return new Date(EventFilenameInfo.findTimestampInFilename(file));
    }
}
