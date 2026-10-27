package com.amazon.weblab.mobile.settings;

import com.amazon.weblab.mobile.repository.RepositoryType;
import java.io.File;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class MobileWeblabRuntimeConfiguration implements IMobileWeblabRuntimeConfiguration, ICacheConfiguration {
    public static final /* synthetic */ int $r8$clinit = 0;
    private boolean mCleanUpAtInit;
    private String mDirectory;
    private MobileWeblabServiceEndpoint mEndpoint;
    private int mMaxDegree;
    private RepositoryType mRepositoryType;
    private int mRetries;
    private Interval mTtl;
    private boolean mUpdateAtInit;
    private MobileWeblabCachePolicyType mUpdatePolicy;
    private static final Interval DEFAULT_TTL = new Interval(1440, TimeUnit.MINUTES);
    private static final MobileWeblabCachePolicyType DEFAULT_CACHE_POLICY = MobileWeblabCachePolicyType.AUTO;
    private static final MobileWeblabServiceEndpoint DEFAULT_ENDPOINT = MobileWeblabServiceEndpoint.PROD;
    private static final RepositoryType DEFAULT_REPOSITORY_TYPE = RepositoryType.FILE;

    public MobileWeblabRuntimeConfiguration(String str) {
        this(DEFAULT_TTL, 1, 4, DEFAULT_CACHE_POLICY, DEFAULT_ENDPOINT, false, true, DEFAULT_REPOSITORY_TYPE, str);
    }

    private MobileWeblabRuntimeConfiguration(Interval interval, int i, int i2, MobileWeblabCachePolicyType mobileWeblabCachePolicyType, MobileWeblabServiceEndpoint mobileWeblabServiceEndpoint, boolean z, boolean z2, RepositoryType repositoryType, String str) {
        this.mTtl = interval;
        this.mRetries = i;
        this.mMaxDegree = i2;
        this.mUpdatePolicy = mobileWeblabCachePolicyType;
        this.mEndpoint = mobileWeblabServiceEndpoint;
        this.mCleanUpAtInit = z;
        this.mUpdateAtInit = z2;
        this.mRepositoryType = repositoryType;
        setDirectory(str);
    }

    public MobileWeblabRuntimeConfiguration(MobileWeblabRuntimeConfiguration mobileWeblabRuntimeConfiguration) {
        this(mobileWeblabRuntimeConfiguration.mTtl, mobileWeblabRuntimeConfiguration.mRetries, mobileWeblabRuntimeConfiguration.mMaxDegree, mobileWeblabRuntimeConfiguration.mUpdatePolicy, mobileWeblabRuntimeConfiguration.mEndpoint, mobileWeblabRuntimeConfiguration.mCleanUpAtInit, mobileWeblabRuntimeConfiguration.mUpdateAtInit, mobileWeblabRuntimeConfiguration.getRepositoryType(), mobileWeblabRuntimeConfiguration.mDirectory);
    }

    public void setNetworkRetries(int i) throws IllegalArgumentException {
        if (i < 0) {
            throw new IllegalArgumentException("networkRetries must be greater than or equal to 0");
        }
        if (i > 10) {
            throw new IllegalArgumentException("networkRetries can't be greater than 10");
        }
        this.mRetries = i;
    }

    public void setMaxDegreeOfParallelism(int i) throws IllegalArgumentException {
        if (i > 10) {
            throw new IllegalArgumentException("max degree can't be greater than 10");
        }
        if (i < 1) {
            throw new IllegalArgumentException("max must be greater than 0");
        }
        this.mMaxDegree = i;
    }

    public void setDirectory(String str) throws IllegalArgumentException {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("directory cannot be null or empty");
        }
        File file = new File(str);
        if (!file.exists()) {
            throw new IllegalArgumentException(str + " doesn't exist");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(str + " is not a directory");
        }
        if (!file.canRead() || !file.canWrite()) {
            throw new IllegalArgumentException("Application needs read and write access to " + str);
        }
        this.mDirectory = str;
    }

    public void setEndpoint(MobileWeblabServiceEndpoint mobileWeblabServiceEndpoint) {
        if (mobileWeblabServiceEndpoint == null) {
            throw new IllegalArgumentException("endpoint cannot be null.");
        }
        this.mEndpoint = mobileWeblabServiceEndpoint;
    }

    public void setCleanUpAtInitEnabled(boolean z) {
        this.mCleanUpAtInit = z;
    }

    public void setUpdateAtInitEnabled(boolean z) {
        this.mUpdateAtInit = z;
    }

    @Override // com.amazon.weblab.mobile.settings.IMobileWeblabRuntimeConfiguration
    public MobileWeblabCachePolicyType getCachePolicy() {
        return this.mUpdatePolicy;
    }

    @Override // com.amazon.weblab.mobile.settings.IMobileWeblabRuntimeConfiguration
    public Interval getTtl() {
        return this.mTtl;
    }

    @Override // com.amazon.weblab.mobile.settings.IMobileWeblabRuntimeConfiguration
    public int getNetworkRetries() {
        return this.mRetries;
    }

    @Override // com.amazon.weblab.mobile.settings.IMobileWeblabRuntimeConfiguration
    public int getMaxDegreeOfParallelism() {
        return this.mMaxDegree;
    }

    @Override // com.amazon.weblab.mobile.settings.IMobileWeblabRuntimeConfiguration
    public String getDirectory() {
        return this.mDirectory;
    }

    @Override // com.amazon.weblab.mobile.settings.IMobileWeblabRuntimeConfiguration
    public MobileWeblabServiceEndpoint getEndpoint() {
        return this.mEndpoint;
    }

    @Override // com.amazon.weblab.mobile.settings.IMobileWeblabRuntimeConfiguration
    public boolean isCleanUpAtInitEnabled() {
        return this.mCleanUpAtInit;
    }

    @Override // com.amazon.weblab.mobile.settings.IMobileWeblabRuntimeConfiguration
    public boolean isUpdateAtInitEnabled() {
        return this.mUpdateAtInit;
    }

    public int hashCode() {
        int i = ((this.mCleanUpAtInit ? 1231 : 1237) + 31) * 31;
        String str = this.mDirectory;
        int iHashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
        MobileWeblabServiceEndpoint mobileWeblabServiceEndpoint = this.mEndpoint;
        int iHashCode2 = (((((iHashCode + (mobileWeblabServiceEndpoint == null ? 0 : mobileWeblabServiceEndpoint.hashCode())) * 31) + this.mMaxDegree) * 31) + this.mRetries) * 31;
        Interval interval = this.mTtl;
        int iHashCode3 = (((iHashCode2 + (interval == null ? 0 : interval.hashCode())) * 31) + (this.mUpdateAtInit ? 1231 : 1237)) * 31;
        MobileWeblabCachePolicyType mobileWeblabCachePolicyType = this.mUpdatePolicy;
        return iHashCode3 + (mobileWeblabCachePolicyType != null ? mobileWeblabCachePolicyType.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MobileWeblabRuntimeConfiguration)) {
            return false;
        }
        MobileWeblabRuntimeConfiguration mobileWeblabRuntimeConfiguration = (MobileWeblabRuntimeConfiguration) obj;
        return mobileWeblabRuntimeConfiguration.mDirectory.equals(this.mDirectory) && mobileWeblabRuntimeConfiguration.mEndpoint.equals(this.mEndpoint) && mobileWeblabRuntimeConfiguration.mMaxDegree == this.mMaxDegree && mobileWeblabRuntimeConfiguration.mRetries == this.mRetries && mobileWeblabRuntimeConfiguration.mTtl.equals(this.mTtl) && mobileWeblabRuntimeConfiguration.mUpdatePolicy.equals(this.mUpdatePolicy) && mobileWeblabRuntimeConfiguration.mCleanUpAtInit == this.mCleanUpAtInit && mobileWeblabRuntimeConfiguration.mUpdateAtInit == this.mUpdateAtInit && mobileWeblabRuntimeConfiguration.mRepositoryType == this.mRepositoryType;
    }

    @Override // com.amazon.weblab.mobile.settings.ICacheConfiguration
    public RepositoryType getRepositoryType() {
        return this.mRepositoryType;
    }
}
