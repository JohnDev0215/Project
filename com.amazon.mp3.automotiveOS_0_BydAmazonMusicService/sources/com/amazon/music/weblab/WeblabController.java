package com.amazon.music.weblab;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.weblab.mobile.IMobileWeblabClient;
import com.amazon.weblab.mobile.model.MobileWeblabException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.SerializedSubject;

/* JADX INFO: loaded from: classes4.dex */
public final class WeblabController {
    static final Treatment DEFAULT_TREATMENT = Treatment.C;
    private static final Logger LOG;
    private static final String TAG;
    private static final long WEBLAB_UPDATE_ASYNC_TIMEOUT_MILLISECONDS;
    private static final long WEBLAB_UPDATE_SYNC_TIMEOUT_MILLISECONDS;
    private static WeblabController instance;
    private Context applicationContext;
    private IMobileWeblabClient client;
    private WeblabDataProvider dataProvider;
    private ExecutorService executorService;
    private SerializedSubject statusEventListener;
    private Subscription statusSubscription;
    private String[] weblabsToRegisterSync;
    private final Object registeredWeblabsLock = new Object();
    private final Object pendingWeblabsLock = new Object();
    volatile Status status = Status.INIT_NOT_CALLED;
    int retryCount = 0;
    private String overrideWeblab = "";
    private Treatment overrideTreatment = DEFAULT_TREATMENT;
    final List<String> registeredWeblabs = new ArrayList();
    final List<String> pendingWeblabs = new ArrayList();
    private Action1<Status> onStatusChanged = new Action1<Status>() { // from class: com.amazon.music.weblab.WeblabController.1
        @Override // rx.functions.Action1
        public void call(Status status) {
            if (Status.INITIALIZED.equals(status)) {
                WeblabController.this.registerWeblabs(WeblabController.WEBLAB_UPDATE_SYNC_TIMEOUT_MILLISECONDS, false, WeblabController.this.weblabsToRegisterSync);
            } else if (Status.FAILED.equals(status)) {
                WeblabController weblabController = WeblabController.this;
                weblabController.saveWeblabsToRegisterLater(weblabController.weblabsToRegisterSync);
            }
        }
    };
    private Action1<Throwable> onError = new Action1<Throwable>() { // from class: com.amazon.music.weblab.WeblabController.2
        @Override // rx.functions.Action1
        public void call(Throwable th) {
            WeblabController.LOG.error("An unexpected error was thrown when initializing the client." + th.getMessage());
            WeblabController.this.unsubscribe();
        }
    };
    private Action0 onCompleted = new Action0() { // from class: com.amazon.music.weblab.WeblabController.3
        @Override // rx.functions.Action0
        public void call() {
            WeblabController.this.unsubscribe();
        }
    };

    static {
        String simpleName = WeblabController.class.getSimpleName();
        TAG = simpleName;
        LOG = LoggerFactory.getLogger(simpleName);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        WEBLAB_UPDATE_ASYNC_TIMEOUT_MILLISECONDS = timeUnit.toMillis(5L);
        WEBLAB_UPDATE_SYNC_TIMEOUT_MILLISECONDS = timeUnit.toMillis(1L);
    }

    private WeblabController() {
    }

    public static synchronized WeblabController getInstance() {
        if (instance == null) {
            instance = new WeblabController();
        }
        return instance;
    }

    private void initInternal() {
        this.status = Status.INITIALIZING;
        ArrayList arrayList = new ArrayList(this.pendingWeblabs);
        Context context = this.applicationContext;
        this.dataProvider.getData();
        IMobileWeblabClient iMobileWeblabClientCreate = WeblabClientFactory.create(context, null, arrayList);
        this.client = iMobileWeblabClientCreate;
        if (iMobileWeblabClientCreate != null) {
            Status status = Status.INITIALIZED;
            this.status = status;
            getAndCacheTreatment(arrayList);
            synchronized (this.registeredWeblabsLock) {
                this.registeredWeblabs.addAll(arrayList);
            }
            synchronized (this.pendingWeblabsLock) {
                this.pendingWeblabs.removeAll(arrayList);
                if (!this.pendingWeblabs.isEmpty()) {
                    List<String> list = this.pendingWeblabs;
                    addWeblab((String[]) list.toArray(new String[list.size()]));
                    this.pendingWeblabs.clear();
                }
            }
            this.statusEventListener.onNext(status);
            this.statusEventListener.onCompleted();
        } else {
            Status status2 = Status.FAILED;
            this.status = status2;
            this.statusEventListener.onNext(status2);
            this.statusEventListener.onCompleted();
        }
        LOG.debug("Weblab initialized with status = {}", this.status);
    }

    @Deprecated
    public Treatment getTreatmentAndRecordTrigger(String str) {
        return getTreatment(str, true);
    }

    private Treatment getTreatment(String str, boolean z) {
        String treatmentAssignment;
        Validate.notNull(str, "weblab cannot be null", new Object[0]);
        if (str.equals(this.overrideWeblab)) {
            LOG.debug("Returning overridden weblab and treatment " + this.overrideWeblab + ":" + this.overrideTreatment.toString());
            return this.overrideTreatment;
        }
        int i = AnonymousClass6.$SwitchMap$com$amazon$music$weblab$Status[this.status.ordinal()];
        if (i != 1) {
            if (i == 2) {
                LOG.error("Client has not yet been initialized -- returning cached treatment.");
                return getCachedTreatment(str);
            }
            if (i != 3) {
                if (i != 4) {
                    return null;
                }
                throw new IllegalStateException("init must be called before calling getTreatmentAndRecordTrigger");
            }
            LOG.error("Initialize has failed -- returning cached treatment");
            retryInitialization();
            return getCachedTreatment(str);
        }
        if (z) {
            triggerUpdateIfRequired();
        }
        try {
            if (z) {
                treatmentAssignment = this.client.getWeblab(str).getTreatmentAndRecordTrigger().getTreatment();
            } else {
                getAndCacheTreatment(Arrays.asList(str));
                treatmentAssignment = this.client.getWeblab(str).getTreatmentAssignment();
            }
            cacheTreatment(str, treatmentAssignment);
            LOG.debug("Returned treatment " + treatmentAssignment + " for weblab " + str);
            return Treatment.valueOf(treatmentAssignment);
        } catch (IllegalArgumentException e) {
            LOG.error("Unable to obtain treatment for " + str + " because of IllegalArgumentException", (Throwable) e);
            throw e;
        } catch (IllegalStateException e2) {
            if (exceptionWasThrownDueToValidWeblabRegistrationRaceCondition(e2.getMessage())) {
                LOG.debug("Weblab was not finished registering before the call to getTreatmentAndRecordTrigger,most likely due to valid race condition between registering the weblaband the client update completing. Returning default Control treatment for weblab.Original exception:", (Throwable) e2);
                triggerUpdate(true);
                return DEFAULT_TREATMENT;
            }
            LOG.error("Unable to obtain treatment for " + str + " because of IllegalStateException", (Throwable) e2);
            throw e2;
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.weblab.WeblabController$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$weblab$Status;

        static {
            int[] iArr = new int[Status.values().length];
            $SwitchMap$com$amazon$music$weblab$Status = iArr;
            try {
                iArr[Status.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$weblab$Status[Status.INITIALIZING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$weblab$Status[Status.FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$weblab$Status[Status.INIT_NOT_CALLED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private boolean exceptionWasThrownDueToValidWeblabRegistrationRaceCondition(String str) {
        return str != null && isCaseOfWeblabNotRegisteredException(str) && exceptionWasThrownBecauseClientUpdateHasNotFinished(str);
    }

    private boolean isCaseOfWeblabNotRegisteredException(String str) {
        return str.contains("No registered weblab for");
    }

    private boolean exceptionWasThrownBecauseClientUpdateHasNotFinished(String str) {
        String weblabNameFromWeblabNotRegisteredExceptionMessage = getWeblabNameFromWeblabNotRegisteredExceptionMessage(str);
        return weblabNameFromWeblabNotRegisteredExceptionMessage != null && this.registeredWeblabs.contains(weblabNameFromWeblabNotRegisteredExceptionMessage);
    }

    private String getWeblabNameFromWeblabNotRegisteredExceptionMessage(String str) {
        Matcher matcher = Pattern.compile("No registered weblab for ([A-Za-z_1-9]*)").matcher(str);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

    private void cacheTreatment(String str, String str2) {
        SharedPreferences.Editor editorEdit = this.applicationContext.getSharedPreferences("weblab", 0).edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }

    private Treatment getCachedTreatment(String str) {
        return Treatment.valueOf(this.applicationContext.getSharedPreferences("weblab", 0).getString(str, DEFAULT_TREATMENT.toString()));
    }

    public void addWeblab(String... strArr) {
        if (this.status.equals(Status.INIT_NOT_CALLED)) {
            throw new IllegalStateException("init must be called before calling addWeblab");
        }
        LOG.debug("Called addWeblab with controller status: " + this.status.toString());
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            Validate.notBlank(strArr[i], "weblab names cannot be empty", new Object[0]);
        }
        if (this.status.equals(Status.INITIALIZED)) {
            registerWeblabs(WEBLAB_UPDATE_ASYNC_TIMEOUT_MILLISECONDS, true, strArr);
            return;
        }
        saveWeblabsToRegisterLater(strArr);
        if (this.status.equals(Status.FAILED)) {
            retryInitialization();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveWeblabsToRegisterLater(String... strArr) {
        synchronized (this.pendingWeblabsLock) {
            this.pendingWeblabs.addAll(Arrays.asList(strArr));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unsubscribe() {
        Subscription subscription = this.statusSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerWeblabs(long j, boolean z, String... strArr) {
        boolean z2;
        synchronized (this.registeredWeblabsLock) {
            z2 = false;
            for (String str : strArr) {
                if (!this.registeredWeblabs.contains(str)) {
                    this.client.addWeblab(str, DEFAULT_TREATMENT.name());
                    this.registeredWeblabs.add(str);
                    z2 = true;
                }
            }
        }
        if (z2) {
            updateAndCacheTreatment(j, z, strArr);
        }
    }

    private void updateAndCacheTreatment(final long j, boolean z, final String... strArr) {
        if (z) {
            this.executorService.submit(new Runnable() { // from class: com.amazon.music.weblab.WeblabController.4
                @Override // java.lang.Runnable
                public void run() {
                    WeblabController.this.updateTreatmentWithTimeout(j, strArr);
                }
            });
        } else {
            updateTreatmentWithTimeout(j, strArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTreatmentWithTimeout(long j, String... strArr) {
        try {
            if (this.client.updateAsync().get(j, TimeUnit.MILLISECONDS).booleanValue()) {
                getAndCacheTreatment(Arrays.asList(strArr));
            }
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            LOG.error("weblab update failed", e.getMessage());
        }
    }

    private void getAndCacheTreatment(List<String> list) {
        for (String str : list) {
            String treatmentAssignment = this.client.getWeblab(str).getTreatmentAssignment();
            cacheTreatment(str, treatmentAssignment);
            LOG.debug("get and cache treatment [" + treatmentAssignment + "] for Weblab [" + str + "]");
        }
    }

    private void triggerUpdateIfRequired() {
        this.executorService.submit(new Runnable() { // from class: com.amazon.music.weblab.WeblabController.5
            @Override // java.lang.Runnable
            public void run() {
                if (WeblabController.this.updateClientSessionParameters()) {
                    WeblabController.this.triggerUpdate(true);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updateClientSessionParameters() {
        this.dataProvider.getData();
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void triggerUpdate(boolean z) {
        try {
            if (z) {
                this.client.updateAsync();
            } else {
                this.client.update();
            }
        } catch (MobileWeblabException e) {
            LOG.error("Client update failed", e.getMessage());
        }
    }

    private void retryInitialization() {
        if (this.retryCount <= 5) {
            LOG.error("Retrying initialization.");
            this.retryCount++;
            initInternal();
        }
    }
}
