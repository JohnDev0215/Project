package com.amazon.weblab.mobile.repository;

import com.amazon.weblab.mobile.cache.ICachePolicy;
import com.amazon.weblab.mobile.experimental.PlatformWeblabs;
import com.amazon.weblab.mobile.experimental.PlatformWeblabsGlobalState;
import com.amazon.weblab.mobile.model.CustomerInfo;
import com.amazon.weblab.mobile.model.MobileWeblabException;
import com.amazon.weblab.mobile.model.SessionInfo;
import com.amazon.weblab.mobile.model.TreatmentAssignment;
import com.amazon.weblab.mobile.utils.TestUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
class Repository implements IRepository {
    private String mApplicationVersion;
    private IRepository mFailsafeRepository;
    private CustomerInfo mLastSeenCustomerInfo;
    private ICachePolicy mPolicy;
    private SessionInfo mSessionInfo;
    private IStorage mStorage;
    private HashMap<String, TreatmentAssignment> mTreatments;
    private String mWeblabClientIdentifier;
    private final long TEN_MIN_IN_MILLIS = 600000;
    private final int TREATMENTS_CACHE_DURATION_IN_DAYS = 30;
    Object mRestoreObject = new Object();

    Repository(ICachePolicy iCachePolicy, IStorage iStorage, String str) {
        if (iCachePolicy == null) {
            throw new IllegalArgumentException("policy can't be null");
        }
        if (iStorage == null) {
            throw new IllegalArgumentException("storage can't be null");
        }
        this.mPolicy = iCachePolicy;
        this.mStorage = iStorage;
        this.mTreatments = new HashMap<>();
        this.mWeblabClientIdentifier = str;
        this.mLastSeenCustomerInfo = new CustomerInfo(null);
    }

    @Override // com.amazon.weblab.mobile.repository.IRepository
    public void clear() {
        synchronized (this.mRestoreObject) {
            this.mApplicationVersion = null;
            this.mSessionInfo = null;
            this.mTreatments.clear();
            PlatformWeblabsGlobalState.clear();
        }
    }

    @Override // com.amazon.weblab.mobile.repository.IRepository
    public void pushAll(Map<String, TreatmentAssignment> map, CustomerInfo customerInfo) {
        if (map == null) {
            throw new IllegalArgumentException("map can't be null");
        }
        updatePlatformSpecificWeblabs(map);
        if (customerInfo != null && !this.mLastSeenCustomerInfo.equals(customerInfo)) {
            this.mLastSeenCustomerInfo.setDirectedId(customerInfo.getDirectedId());
            resetAllocationChangedFlags();
        }
        removeInconsistentAllocations(map);
        for (Map.Entry<String, TreatmentAssignment> entry : map.entrySet()) {
            String key = entry.getKey();
            TreatmentAssignment value = entry.getValue();
            if (this.mTreatments.containsKey(key)) {
                value.setKeepInCacheDateInMillis(this.mTreatments.get(key).getKeepInCacheDateInMillis());
            }
            this.mTreatments.put(key, value);
        }
    }

    protected void updatePlatformSpecificWeblabs(Map<String, TreatmentAssignment> map) {
        for (PlatformWeblabs platformWeblabs : PlatformWeblabs.values()) {
            TreatmentAssignment treatmentAssignment = map.get(platformWeblabs.getWeblabName());
            if (treatmentAssignment != null) {
                PlatformWeblabsGlobalState.setWeblabTreatment(platformWeblabs, treatmentAssignment);
            }
        }
    }

    protected void resetAllocationChangedFlags() {
        Iterator<TreatmentAssignment> it = this.mTreatments.values().iterator();
        while (it.hasNext()) {
            it.next().setAllocationChanged(false);
        }
    }

    private void removeInconsistentAllocations(Map<String, TreatmentAssignment> map) {
        Iterator<Map.Entry<String, TreatmentAssignment>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, TreatmentAssignment> next = it.next();
            TreatmentAssignment value = next.getValue();
            TreatmentAssignment treatmentAssignment = this.mTreatments.get(next.getKey());
            value.setAllocationChanged(isAllocationChange(treatmentAssignment, value));
            if (treatmentAssignment != null && treatmentAssignment.isAllocationChanged() && Math.abs(treatmentAssignment.getDateModified().longValue() - value.getDateModified().longValue()) < 600000) {
                it.remove();
            }
        }
    }

    private boolean isAllocationChange(TreatmentAssignment treatmentAssignment, TreatmentAssignment treatmentAssignment2) {
        if (treatmentAssignment == null || treatmentAssignment2 == null) {
            return false;
        }
        return (treatmentAssignment.getTreatment().equals(treatmentAssignment2.getTreatment()) && treatmentAssignment.getVersion().equals(treatmentAssignment2.getVersion())) ? false : true;
    }

    @Override // com.amazon.weblab.mobile.repository.IRepository
    public void updateKeepInCacheDateInMillis(String str) {
        this.mTreatments.get(str).setKeepInCacheDateInMillis(System.currentTimeMillis());
    }

    @Override // com.amazon.weblab.mobile.repository.IRepository
    public IRepository getFailsafeRepository() {
        return this.mFailsafeRepository;
    }

    public void setFailsafeRepository(IRepository iRepository) {
        this.mFailsafeRepository = iRepository;
    }

    @Override // com.amazon.weblab.mobile.repository.IRepository
    public void save() throws MobileWeblabException {
        String str = this.mApplicationVersion;
        if (str == null || str.isEmpty()) {
            throw new IllegalStateException("cannot save, the application version has not been set");
        }
        if (this.mSessionInfo == null) {
            throw new IllegalStateException("cannot save, the session info has not been set");
        }
        if (this.mTreatments.isEmpty()) {
            throw new IllegalStateException("cannot save, empty collection of weblabs");
        }
        StorageEntity storageEntity = new StorageEntity(this.mApplicationVersion, new SessionInfo(this.mSessionInfo), new HashMap(this.mTreatments));
        try {
            for (TreatmentAssignment treatmentAssignment : storageEntity.getTreatments().values()) {
                if (treatmentAssignment.getKeepInCacheDateInMillis() == 0) {
                    treatmentAssignment.setKeepInCacheDateInMillis(System.currentTimeMillis());
                }
            }
            filterAssignments(storageEntity.getTreatments());
            this.mStorage.writeBackup(storageEntity);
        } catch (IOException e) {
            throw new MobileWeblabException("An error ocurred while trying to write to storage", e);
        } catch (RuntimeException e2) {
            throw new MobileWeblabException("An error ocurred while parsing the data before writing to storage", e2);
        }
    }

    private Map<String, TreatmentAssignment> filterAssignments(Map<String, TreatmentAssignment> map) {
        Iterator<Map.Entry<String, TreatmentAssignment>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            TreatmentAssignment value = it.next().getValue();
            boolean zEquals = value.getVersion().equals("com.amazon.weblab.mobile.version.Default");
            boolean z = Math.abs(value.getKeepInCacheDateInMillis() - System.currentTimeMillis()) > TimeUnit.MILLISECONDS.convert(30L, TimeUnit.DAYS);
            if (zEquals || z) {
                it.remove();
            }
        }
        return map;
    }

    @Override // com.amazon.weblab.mobile.repository.IRepository
    public void restore() throws MobileWeblabException {
        try {
            StorageEntity backup = this.mStorage.readBackup();
            Map<String, TreatmentAssignment> treatments = backup.getTreatments();
            TestUtils.logDebugForAutomation("Loaded " + treatments.size() + " assignments from the disk.");
            updatePlatformSpecificWeblabs(treatments);
            this.mApplicationVersion = backup.getApplicationVersion();
            this.mSessionInfo = backup.getSessionInfo();
            this.mTreatments.putAll(backup.getTreatments());
        } catch (IOException e) {
            throw new MobileWeblabException("An error occurred while trying to read from", e);
        } catch (RuntimeException e2) {
            throw new MobileWeblabException("An error occurred while parsing the data from the storage", e2);
        }
    }

    @Override // com.amazon.weblab.mobile.repository.IRepository
    public final TreatmentAssignment getTreatmentAssignment(String str) {
        TreatmentAssignment treatmentAssignment = this.mTreatments.get(str);
        if (treatmentAssignment == null) {
            throw new IllegalStateException("Weblab " + str + " does not exist");
        }
        this.mPolicy.applyPolicy(treatmentAssignment);
        return treatmentAssignment;
    }

    @Override // com.amazon.weblab.mobile.repository.IRepository
    public SessionInfo getSessionInfo() {
        return this.mSessionInfo;
    }

    @Override // com.amazon.weblab.mobile.repository.IRepository
    public void setSessionInfo(SessionInfo sessionInfo) {
        if (sessionInfo == null) {
            throw new IllegalArgumentException("info can't be null");
        }
        this.mSessionInfo = sessionInfo;
    }

    @Override // com.amazon.weblab.mobile.repository.IRepository
    public boolean containsValidatedWeblab(String str) {
        return this.mTreatments.containsKey(str);
    }

    @Override // com.amazon.weblab.mobile.repository.IRepository
    public void setApplicationVersion(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("applicationVersion can't be null nor empty");
        }
        this.mApplicationVersion = str;
    }

    @Override // com.amazon.weblab.mobile.repository.IRepository
    public boolean hasBackup() {
        return this.mStorage.hasBackup();
    }
}
