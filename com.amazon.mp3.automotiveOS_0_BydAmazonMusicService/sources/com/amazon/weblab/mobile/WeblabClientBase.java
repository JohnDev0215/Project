package com.amazon.weblab.mobile;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.weblab.mobile.cache.CachePolicyFactory;
import com.amazon.weblab.mobile.cache.ICachePolicy;
import com.amazon.weblab.mobile.cache.ICacheRefresher;
import com.amazon.weblab.mobile.metrics.ApplicationContextHolder;
import com.amazon.weblab.mobile.model.CustomerInfo;
import com.amazon.weblab.mobile.model.MobileWeblabException;
import com.amazon.weblab.mobile.model.SessionInfo;
import com.amazon.weblab.mobile.model.TreatmentAssignment;
import com.amazon.weblab.mobile.repository.IRepository;
import com.amazon.weblab.mobile.repository.RepositoryFactory;
import com.amazon.weblab.mobile.repository.RepositoryType;
import com.amazon.weblab.mobile.service.IServiceProxy;
import com.amazon.weblab.mobile.service.ITriggerRequestListener;
import com.amazon.weblab.mobile.service.ServiceProxyFactory;
import com.amazon.weblab.mobile.settings.ICacheConfiguration;
import com.amazon.weblab.mobile.settings.IMobileWeblabClientAttributes;
import com.amazon.weblab.mobile.settings.IMobileWeblabRuntimeConfiguration;
import com.amazon.weblab.mobile.utils.TestUtils;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes5.dex */
class WeblabClientBase implements IMobileWeblabClient {
    private IMobileWeblabClientAttributes mClientAttributes;
    private IMobileWeblabRuntimeConfiguration mConfiguration;
    private CustomerInfo mCustomerInfo;
    protected ExecutorService mExecutor;
    private ConcurrentHashMap<String, TreatmentAssignment> mLockedTreatments;
    private IServiceProxy mProxy;
    private ReentrantReadWriteLock mReadWriteLock;
    private IRepository mRepository;
    private SessionInfo mSessionInfo;
    private volatile Future<Boolean> mUpdateTask;

    /* JADX INFO: renamed from: com.amazon.weblab.mobile.WeblabClientBase$1, reason: invalid class name */
    class AnonymousClass1 implements Callable<Boolean> {
    }

    private class CacheListener implements ICacheRefresher {
        private CacheListener() {
        }

        /* synthetic */ CacheListener(WeblabClientBase weblabClientBase, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.amazon.weblab.mobile.cache.ICacheRefresher
        public void refresh() {
            WeblabClientBase.this.updateAsync();
        }
    }

    private class ServiceListener implements ITriggerRequestListener {
        private ServiceListener() {
        }

        /* synthetic */ ServiceListener(WeblabClientBase weblabClientBase, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.amazon.weblab.mobile.service.ITriggerRequestListener
        public void notifyMismatchAllocation(SessionInfo sessionInfo, CustomerInfo customerInfo, Map<String, TreatmentAssignment> map) {
            try {
                WeblabClientBase.this.pushAll(sessionInfo, customerInfo, map);
            } catch (MobileWeblabException unused) {
            }
        }
    }

    WeblabClientBase(IMobileWeblabClientAttributes iMobileWeblabClientAttributes, IMobileWeblabRuntimeConfiguration iMobileWeblabRuntimeConfiguration, String str, String str2, String str3, Context context) {
        System.currentTimeMillis();
        if (iMobileWeblabClientAttributes == null) {
            throw new IllegalArgumentException("clientAttr can't be null");
        }
        if (iMobileWeblabRuntimeConfiguration == null) {
            throw new IllegalArgumentException("config can't be null");
        }
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("sessionId can't be null nor empty");
        }
        if (str2 == null || str2.isEmpty()) {
            throw new IllegalArgumentException("marketplaceId can't be null nor empty");
        }
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }
        this.mSessionInfo = new SessionInfo(str, str2);
        this.mCustomerInfo = new CustomerInfo(str3);
        this.mClientAttributes = iMobileWeblabClientAttributes;
        this.mConfiguration = iMobileWeblabRuntimeConfiguration;
        ApplicationContextHolder.setApplicationContext(context);
        initialize();
        initializeData(getRepositoryType(iMobileWeblabRuntimeConfiguration));
        if (iMobileWeblabRuntimeConfiguration.isUpdateAtInitEnabled()) {
            updateAsync();
        }
    }

    private void initialize() {
        AnonymousClass1 anonymousClass1 = null;
        CacheListener cacheListener = new CacheListener(this, anonymousClass1);
        this.mExecutor = new ExceptionLoggingThreadPoolExecutor(0, this.mConfiguration.getMaxDegreeOfParallelism(), CoroutineLiveDataKt.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), this.mClientAttributes.getIdentifier());
        this.mReadWriteLock = new ReentrantReadWriteLock(true);
        this.mLockedTreatments = new ConcurrentHashMap<>();
        ICachePolicy iCachePolicyCreateCachePolicy = CachePolicyFactory.createCachePolicy(this.mConfiguration.getCachePolicy(), this.mConfiguration.getTtl());
        iCachePolicyCreateCachePolicy.setListener(cacheListener);
        this.mRepository = RepositoryFactory.createRepository(getRepositoryType(this.mConfiguration), this.mConfiguration.getDirectory(), this.mClientAttributes.getIdentifier(), iCachePolicyCreateCachePolicy);
        this.mProxy = ServiceProxyFactory.createServiceProxy(new ServiceListener(this, anonymousClass1), this.mClientAttributes, this.mConfiguration);
    }

    private void initializeData(RepositoryType repositoryType) {
        System.currentTimeMillis();
        if (this.mRepository.hasBackup()) {
            try {
                loadCacheFromRepository(0);
                if (hasSessionFlip(this.mRepository.getSessionInfo())) {
                    refreshRepository(false, this.mConfiguration.isUpdateAtInitEnabled(), repositoryType);
                } else {
                    refreshRepository(true, this.mConfiguration.isCleanUpAtInitEnabled(), repositoryType);
                }
                return;
            } catch (MobileWeblabException unused) {
                refreshRepository(false, this.mConfiguration.isCleanUpAtInitEnabled(), repositoryType);
                return;
            }
        }
        refreshRepository(false, this.mConfiguration.isCleanUpAtInitEnabled(), repositoryType);
    }

    private void loadCacheFromRepository(int i) throws MobileWeblabException {
        if (i > 1) {
            throw new MobileWeblabException("Retried too many times in repository");
        }
        System.currentTimeMillis();
        try {
            this.mRepository.restore();
        } catch (MobileWeblabException e) {
            if (this.mRepository.getFailsafeRepository() != null) {
                this.mRepository = this.mRepository.getFailsafeRepository();
                loadCacheFromRepository(i + 1);
                return;
            }
            throw new MobileWeblabException(e);
        }
    }

    RepositoryType getRepositoryType(IMobileWeblabRuntimeConfiguration iMobileWeblabRuntimeConfiguration) {
        IMobileWeblabRuntimeConfiguration iMobileWeblabRuntimeConfiguration2 = this.mConfiguration;
        if (iMobileWeblabRuntimeConfiguration2 instanceof ICacheConfiguration) {
            return ((ICacheConfiguration) iMobileWeblabRuntimeConfiguration2).getRepositoryType();
        }
        return RepositoryType.FILE;
    }

    void refreshRepository(boolean z, boolean z2, RepositoryType repositoryType) {
        if (repositoryType.equals(RepositoryType.LAZY) || repositoryType.equals(RepositoryType.FLATBUFFERS)) {
            refreshRepositoryLazy(z, z2);
        } else {
            refreshRepository(z, z2);
        }
    }

    private void refreshRepository(boolean z, boolean z2) {
        HashMap map = new HashMap();
        for (Map.Entry<String, String> entry : this.mClientAttributes.getWeblabs().entrySet()) {
            if (this.mRepository.containsValidatedWeblab(entry.getKey()) && z) {
                TreatmentAssignment treatmentAssignment = this.mRepository.getTreatmentAssignment(entry.getKey());
                treatmentAssignment.setKeepInCacheDateInMillis(Calendar.getInstance().getTimeInMillis());
                map.put(entry.getKey(), treatmentAssignment);
            } else {
                map.put(entry.getKey(), createDefaultTreatment(entry.getKey(), entry.getValue()));
            }
        }
        if (z2 || !z) {
            this.mRepository.clear();
        }
        this.mRepository.setSessionInfo(this.mSessionInfo);
        this.mRepository.setApplicationVersion(this.mClientAttributes.getApplicationVersion());
        this.mRepository.pushAll(map, this.mCustomerInfo);
    }

    private void refreshRepositoryLazy(boolean z, boolean z2) {
        if (z2 || !z) {
            this.mRepository.clear();
        }
        this.mRepository.setSessionInfo(this.mSessionInfo);
        this.mRepository.setApplicationVersion(this.mClientAttributes.getApplicationVersion());
    }

    private TreatmentAssignment createDefaultTreatment(String str, String str2) {
        TreatmentAssignment treatmentAssignment = new TreatmentAssignment(str, str2, "com.amazon.weblab.mobile.version.Default", 0L, 0L, false, System.currentTimeMillis());
        treatmentAssignment.setLocked(false);
        return treatmentAssignment;
    }

    private boolean hasSessionFlip(SessionInfo sessionInfo) {
        return !this.mSessionInfo.equals(sessionInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pushAll(SessionInfo sessionInfo, CustomerInfo customerInfo, Map<String, TreatmentAssignment> map) throws MobileWeblabException {
        this.mReadWriteLock.writeLock().lock();
        try {
            boolean z = !hasSessionFlip(sessionInfo);
            if (z) {
                this.mRepository.pushAll(map, customerInfo);
            }
            if (z) {
                this.mReadWriteLock.readLock().lock();
                try {
                    this.mRepository.save();
                } finally {
                    this.mReadWriteLock.readLock().unlock();
                }
            }
        } finally {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updateImpl() throws MobileWeblabException {
        int size;
        if (TestUtils.isUnderTest()) {
            TestUtils.logDebugForAutomation(String.format(Locale.US, "updateImpl:start - parameters: directedId=%s, sessionId=%s, marketplaceId=%s ", getDirectedId(), getSession(), getMarketplace()));
        }
        this.mReadWriteLock.readLock().lock();
        SessionInfo sessionInfo = this.mSessionInfo;
        this.mReadWriteLock.readLock().unlock();
        new HashSet();
        do {
            size = this.mClientAttributes.getWeblabs().size();
            CustomerInfo customerInfo = new CustomerInfo(this.mCustomerInfo.getDirectedId());
            pushAll(sessionInfo, customerInfo, this.mProxy.getTreatmentAssignments(sessionInfo, customerInfo, this.mClientAttributes.getWeblabs().keySet()).getTreatmentAssignments());
        } while (size != this.mClientAttributes.getWeblabs().size());
        if (TestUtils.isUnderTest()) {
            TestUtils.logDebugForAutomation("updateImpl:end");
        }
        return true;
    }

    private void setSessionInfo(SessionInfo sessionInfo) {
        this.mReadWriteLock.writeLock().lock();
        try {
            if (hasSessionFlip(sessionInfo)) {
                this.mSessionInfo = sessionInfo;
                refreshRepository(false, false);
            }
        } finally {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public void setSessionAndMarketplaceId(String str, String str2) throws IllegalArgumentException {
        setSessionInfo(new SessionInfo(str, str2));
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public String getSession() {
        return this.mSessionInfo.getSessionId();
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public String getMarketplace() {
        return this.mSessionInfo.getMarketplaceId();
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public void setDirectedId(String str) {
        this.mCustomerInfo.setDirectedId(str);
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public String getDirectedId() {
        return this.mCustomerInfo.getDirectedId();
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public IMobileWeblabClientAttributes getIMobileWeblabClientAttributes() {
        return this.mClientAttributes;
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public void addWeblab(String str, String str2) throws TooManyRegisteredWeblabsException, IllegalArgumentException {
        if (this.mConfiguration.isCleanUpAtInitEnabled()) {
            Log.e("MWAC", "Using addWeblab for weblab name " + str + " with cleanupAtInit set on true could result in many default treatments being thrown. Turn the cleanupAtInit off or add all the weblabs in MobileWeblabClientAttributes before constructing this object");
        }
        this.mReadWriteLock.writeLock().lock();
        this.mClientAttributes.addWeblab(str, str2);
        try {
            if (!this.mRepository.containsValidatedWeblab(str)) {
                this.mRepository.pushAll(Collections.singletonMap(str, createDefaultTreatment(str, str2)), this.mCustomerInfo);
            }
            this.mRepository.updateKeepInCacheDateInMillis(str);
        } finally {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public boolean update() throws MobileWeblabException {
        return updateImpl();
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public synchronized Future<Boolean> updateAsync() {
        if (this.mUpdateTask != null && !this.mUpdateTask.isDone()) {
            if (TestUtils.isUnderTest()) {
                TestUtils.logDebugForAutomation(String.format(Locale.US, "updateAsync:already in flight - parameters: directedId=%s, sessionId=%s, marketplaceId=%s ", getDirectedId(), getSession(), getMarketplace()));
            }
            return this.mUpdateTask;
        }
        if (TestUtils.isUnderTest()) {
            TestUtils.logDebugForAutomation(String.format(Locale.US, "updateAsync:enqueued - parameters: directedId=%s, sessionId=%s, marketplaceId=%s ", getDirectedId(), getSession(), getMarketplace()));
        }
        this.mUpdateTask = this.mExecutor.submit(new Callable<Boolean>() { // from class: com.amazon.weblab.mobile.WeblabClientBase.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                return Boolean.valueOf(WeblabClientBase.this.updateImpl());
            }
        });
        return this.mUpdateTask;
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public IMobileWeblab getWeblab(String str) throws IllegalStateException, IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("weblabName can't be null");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("weblabName can't be empty");
        }
        if (!this.mClientAttributes.getWeblabs().containsKey(str)) {
            throw new IllegalStateException("No registered weblab for " + str);
        }
        TreatmentAssignment treatmentAssignment = this.mLockedTreatments.get(str);
        if (treatmentAssignment != null) {
            return new WeblabBase(treatmentAssignment, this.mSessionInfo, this.mCustomerInfo, this.mExecutor, this.mProxy, this.mClientAttributes.getIdentifier());
        }
        if (!this.mRepository.containsValidatedWeblab(str)) {
            HashMap map = new HashMap();
            map.put(str, createDefaultTreatment(str, this.mClientAttributes.getWeblabs().get(str)));
            this.mReadWriteLock.writeLock().lock();
            try {
                this.mRepository.pushAll(map, this.mCustomerInfo);
            } finally {
                this.mReadWriteLock.writeLock().unlock();
            }
        }
        this.mReadWriteLock.readLock().lock();
        try {
            try {
                return new WeblabBase(this.mRepository.getTreatmentAssignment(str), this.mSessionInfo, this.mCustomerInfo, this.mExecutor, this.mProxy, this.mClientAttributes.getIdentifier());
            } catch (IllegalArgumentException e) {
                throw e;
            }
        } finally {
            this.mReadWriteLock.readLock().unlock();
        }
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public void lockWeblab(String str, String str2) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("weblab can't be null nor empty");
        }
        if (!this.mClientAttributes.getWeblabs().containsKey(str)) {
            throw new IllegalStateException("given weblab is not registered");
        }
        TreatmentAssignment treatmentAssignmentCreateDefaultTreatment = createDefaultTreatment(str, str2);
        treatmentAssignmentCreateDefaultTreatment.setLocked(true);
        this.mLockedTreatments.put(treatmentAssignmentCreateDefaultTreatment.getWeblabName(), treatmentAssignmentCreateDefaultTreatment);
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public void unlockWeblab(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("weblabName can't be null nor empty");
        }
        if (this.mLockedTreatments.containsKey(str)) {
            this.mLockedTreatments.remove(str);
        }
    }
}
