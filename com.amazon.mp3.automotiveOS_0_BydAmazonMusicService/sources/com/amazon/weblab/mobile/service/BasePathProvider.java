package com.amazon.weblab.mobile.service;

import com.amazon.weblab.mobile.settings.MobileWeblabServiceEndpoint;

/* JADX INFO: loaded from: classes5.dex */
class BasePathProvider {
    private Marketplaces mMarketplaces = new Marketplaces();

    BasePathProvider() {
    }

    private enum BasePath {
        NA("msh.amazon.com/mwl", "shm.amazon.com/mwl", "mobileweblab-live-beta.corp.amazon.com/mwl"),
        EU("msh.amazon.co.uk/mwl", "shm.amazon.co.uk/mwl", "mobileweblab-live-beta.corp.amazon.com/mwl"),
        FE("msh.amazon.co.jp/mwl", "shm.amazon.co.jp/mwl", "mobileweblab-live-beta.corp.amazon.com/mwl"),
        CN("msh.amazon.cn/mwl", "shm.amazon.cn/mwl", "mobileweblab-live-beta.corp.amazon.com/mwl");

        private String mExternalGammaBasePath;
        private String mExternalProdBasePath;
        private String mInternalBetaBasePath;

        BasePath(String str, String str2, String str3) {
            this.mExternalProdBasePath = str;
            this.mExternalGammaBasePath = str2;
            this.mInternalBetaBasePath = str3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getBasePath(MobileWeblabServiceEndpoint mobileWeblabServiceEndpoint) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$weblab$mobile$settings$MobileWeblabServiceEndpoint[mobileWeblabServiceEndpoint.ordinal()];
            if (i == 1) {
                return this.mExternalProdBasePath;
            }
            if (i == 2) {
                return this.mExternalGammaBasePath;
            }
            if (i == 3) {
                return this.mInternalBetaBasePath;
            }
            throw new IllegalArgumentException(String.format("%s is not a supported endpoint.", mobileWeblabServiceEndpoint.name()));
        }
    }

    /* JADX INFO: renamed from: com.amazon.weblab.mobile.service.BasePathProvider$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$weblab$mobile$settings$MobileWeblabServiceEndpoint;

        static {
            int[] iArr = new int[MobileWeblabServiceEndpoint.values().length];
            $SwitchMap$com$amazon$weblab$mobile$settings$MobileWeblabServiceEndpoint = iArr;
            try {
                iArr[MobileWeblabServiceEndpoint.PROD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$weblab$mobile$settings$MobileWeblabServiceEndpoint[MobileWeblabServiceEndpoint.GAMMA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$weblab$mobile$settings$MobileWeblabServiceEndpoint[MobileWeblabServiceEndpoint.BETA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private BasePath getBasePath(String str) {
        String region = this.mMarketplaces.getRegion(str);
        if (region == null) {
            throw new IllegalStateException("obfuscatedMarketplaceId is not supported.");
        }
        return BasePath.valueOf(region);
    }

    String getBasePath(String str, MobileWeblabServiceEndpoint mobileWeblabServiceEndpoint) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("obfuscatedMarketplaceId cannot be null.");
        }
        if (mobileWeblabServiceEndpoint != null) {
            return getBasePath(str).getBasePath(mobileWeblabServiceEndpoint);
        }
        throw new IllegalArgumentException("endpointType cannot be null.");
    }
}
