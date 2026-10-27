package com.amazon.minerva.client.common.api;

import android.content.Context;
import com.amazon.minerva.client.common.api.impl.AmazonMinervaImpl;
import com.amazon.minerva.client.common.compliance.ChildProfileVerifier;
import com.amazon.minerva.client.common.compliance.NonAnonymousCustomerIdProvider;
import com.amazon.minerva.client.common.compliance.UserControlVerifier;
import com.amazon.minerva.client.common.transport.OAuthProvider;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class AmazonMinervaClientBuilder {
    private ChildProfileVerifier mChildProfileVerifier;
    private Context mContext;
    private NonAnonymousCustomerIdProvider mNonAnonymousCustomerIdProvider;
    private OAuthProvider mOAuthProvider;
    private UserControlVerifier mUserControlVerifier;
    private String mRegion = "";
    private String mDeviceType = "";
    private String mNonAnonymousDeviceId = "";
    private String mCustomAppConfigId = "";
    private boolean mIsUseDES = false;
    private boolean mIsRunInitialUpload = true;

    private AmazonMinervaClientBuilder(Context context) {
        this.mContext = context;
    }

    public static AmazonMinervaClientBuilder standard(Context context) {
        return new AmazonMinervaClientBuilder(context);
    }

    public AmazonMinervaClientBuilder withRegion(String str) {
        Objects.requireNonNull(str, "parameter region cannot be null");
        this.mRegion = str;
        return this;
    }

    public AmazonMinervaClientBuilder withDeviceType(String str) {
        this.mDeviceType = str;
        return this;
    }

    public AmazonMinervaClientBuilder withOAuthProvider(OAuthProvider oAuthProvider) {
        this.mOAuthProvider = oAuthProvider;
        return this;
    }

    public AmazonMinervaClientBuilder withChildProfileVerifier(ChildProfileVerifier childProfileVerifier) {
        this.mChildProfileVerifier = childProfileVerifier;
        return this;
    }

    public AmazonMinervaClientBuilder withUserControlVerifier(UserControlVerifier userControlVerifier) {
        this.mUserControlVerifier = userControlVerifier;
        return this;
    }

    public AmazonMinervaClientBuilder withUseDES(boolean z) {
        this.mIsUseDES = z;
        return this;
    }

    public AmazonMinervaClientBuilder withRunInitialUpload(boolean z) {
        this.mIsRunInitialUpload = z;
        return this;
    }

    public AmazonMinerva build() {
        buildChecker();
        return new AmazonMinervaImpl(this.mContext, this.mRegion, this.mDeviceType, this.mOAuthProvider, this.mChildProfileVerifier, this.mUserControlVerifier, this.mNonAnonymousCustomerIdProvider, this.mNonAnonymousDeviceId, this.mCustomAppConfigId, this.mIsUseDES, this.mIsRunInitialUpload);
    }

    private void buildChecker() {
        if (this.mRegion.isEmpty()) {
            throw new IllegalStateException("withRegion must be called with a valid region before build.");
        }
        if (this.mDeviceType.isEmpty()) {
            throw new IllegalStateException("withDeviceType must be called with a valid deviceType before build.");
        }
        if (this.mOAuthProvider == null) {
            throw new IllegalStateException("withOAuthProvider must be called before build.");
        }
        if (this.mChildProfileVerifier == null) {
            throw new IllegalStateException("withChildProfileVerifier must be called before build.");
        }
        if (this.mUserControlVerifier == null) {
            throw new IllegalStateException("withUserControlVerifier must be called before build.");
        }
    }
}
