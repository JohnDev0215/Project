package com.amazon.minerva.client.thirdparty.api;

import android.content.Context;
import com.amazon.minerva.client.thirdparty.MinervaServiceAndroidAdapter;
import com.amazon.minerva.client.thirdparty.api.impl.AmazonMinervaImpl;
import com.amazon.minerva.client.thirdparty.compliance.ChildProfileVerifier;
import com.amazon.minerva.client.thirdparty.compliance.NonAnonymousCustomerIdProvider;
import com.amazon.minerva.client.thirdparty.compliance.UserControlVerifier;
import com.amazon.minerva.client.thirdparty.transport.OAuthProvider;
import com.amazon.minerva.client.thirdparty.utils.CustomDeviceUtil;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class AmazonMinervaAndroidClientBuilder {
    private static CustomDeviceUtil sCustomDeviceUtil;
    private final Context context;
    private ChildProfileVerifier mChildProfileVerifier;
    private NonAnonymousCustomerIdProvider mNonAnonymousCustomerIdProvider;
    private OAuthProvider mOAuthProvider;
    private UserControlVerifier mUserControlVerifier;
    private MinervaServiceAndroidAdapter minervaServiceAndroidAdapter;
    private String mRegion = "";
    private String mDeviceType = "";
    private String mNonAnonymousDeviceId = "";
    private boolean mIsUseDES = false;
    private boolean mIsRunInitialUpload = true;

    AmazonMinervaAndroidClientBuilder(Context context) {
        this.context = context;
    }

    public static AmazonMinervaAndroidClientBuilder standard(Context context, String str) {
        Objects.requireNonNull(context, "parameter context can not be null.");
        Objects.requireNonNull(str, "customAppConfigId can not be null");
        CustomDeviceUtil customDeviceUtil = CustomDeviceUtil.getInstance();
        sCustomDeviceUtil = customDeviceUtil;
        if (!customDeviceUtil.isInitialized()) {
            sCustomDeviceUtil.initialize(context);
        }
        sCustomDeviceUtil.setAppConfigId(str);
        return new AmazonMinervaAndroidClientBuilder(context);
    }

    public static AmazonMinervaAndroidClientBuilder standard(Context context) {
        Objects.requireNonNull(context, "parameter context can not be null.");
        CustomDeviceUtil customDeviceUtil = CustomDeviceUtil.getInstance();
        sCustomDeviceUtil = customDeviceUtil;
        if (!customDeviceUtil.isInitialized()) {
            sCustomDeviceUtil.initialize(context);
        }
        return new AmazonMinervaAndroidClientBuilder(context);
    }

    public AmazonMinervaAndroidClientBuilder withRegion(String str) {
        Objects.requireNonNull(str, "parameter region can not be null.");
        this.mRegion = str;
        return this;
    }

    public AmazonMinervaAndroidClientBuilder withDeviceType(String str) {
        Objects.requireNonNull(str, "parameter deviceType can not be null.");
        this.mDeviceType = str;
        sCustomDeviceUtil.setDeviceType(str);
        return this;
    }

    public AmazonMinervaAndroidClientBuilder withOAuthProvider(OAuthProvider oAuthProvider) {
        Objects.requireNonNull(oAuthProvider, "parameter oAuthProvider can not be null.");
        this.mOAuthProvider = oAuthProvider;
        sCustomDeviceUtil.setOAuthProvider(oAuthProvider);
        return this;
    }

    public AmazonMinervaAndroidClientBuilder withChildProfileVerifier(ChildProfileVerifier childProfileVerifier) {
        Objects.requireNonNull(childProfileVerifier, "parameter childProfileVerifier can not be null.");
        this.mChildProfileVerifier = childProfileVerifier;
        sCustomDeviceUtil.setChildProfileVerifier(childProfileVerifier);
        return this;
    }

    public AmazonMinervaAndroidClientBuilder withUserControlVerifier(UserControlVerifier userControlVerifier) {
        Objects.requireNonNull(userControlVerifier, "parameter userControlVerifier can not be null.");
        this.mUserControlVerifier = userControlVerifier;
        sCustomDeviceUtil.setUserControlVerifier(userControlVerifier);
        return this;
    }

    public AmazonMinervaAndroidClientBuilder withNonAnonymousCustomerIdProvider(NonAnonymousCustomerIdProvider nonAnonymousCustomerIdProvider) {
        Objects.requireNonNull(nonAnonymousCustomerIdProvider, "parameter nonAnonymousCustomerIdProvider can not be null.");
        this.mNonAnonymousCustomerIdProvider = nonAnonymousCustomerIdProvider;
        sCustomDeviceUtil.setCustomerIdProvider(nonAnonymousCustomerIdProvider);
        return this;
    }

    public AmazonMinervaAndroidClientBuilder withNonAnonymousDeviceId(String str) {
        Objects.requireNonNull(str, "parameter nonAnonymousDeviceId can not be null.");
        this.mNonAnonymousDeviceId = str;
        sCustomDeviceUtil.setNonAnonymousDeviceId(str);
        return this;
    }

    public AmazonMinervaAndroidClientBuilder withUseDES(boolean z) {
        this.mIsUseDES = z;
        return this;
    }

    public AmazonMinervaAndroidClientBuilder withRunInitialUpload(boolean z) {
        this.mIsRunInitialUpload = z;
        return this;
    }

    public AmazonMinerva build() {
        buildChecker();
        Context contextToUse = getContextToUse();
        MinervaServiceAndroidAdapter minervaServiceAndroidAdapter = MinervaServiceAndroidAdapter.getInstance(contextToUse, this.mIsRunInitialUpload);
        this.minervaServiceAndroidAdapter = minervaServiceAndroidAdapter;
        return new AmazonMinervaImpl(contextToUse, minervaServiceAndroidAdapter, this.mRegion, this.mDeviceType);
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

    private Context getContextToUse() {
        Context applicationContext = this.context.getApplicationContext();
        return this.mIsUseDES ? applicationContext.createDeviceProtectedStorageContext() : applicationContext;
    }
}
