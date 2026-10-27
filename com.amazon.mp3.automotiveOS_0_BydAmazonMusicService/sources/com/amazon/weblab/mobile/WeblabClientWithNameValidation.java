package com.amazon.weblab.mobile;

import android.content.Context;
import com.amazon.weblab.mobile.model.MobileWeblabException;
import com.amazon.weblab.mobile.model.MobileWeblabTriggerResult;
import com.amazon.weblab.mobile.settings.IMobileWeblabClientAttributes;
import com.amazon.weblab.mobile.settings.IMobileWeblabRuntimeConfiguration;
import com.amazon.weblab.mobile.settings.MobileWeblabClientAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
final class WeblabClientWithNameValidation implements IMobileWeblabClient {
    private static final Pattern WEBLAB_NAME_PATTERN = Pattern.compile("^([A-Z0-9]+_)+[0-9]+$");
    protected final IMobileWeblabClient mClient;
    private final MobileWeblabClientAttributes mInitialAttributes;
    private ConcurrentHashMap<String, String> mInvalidWeblabsAndTheirLock = new ConcurrentHashMap<>();
    private final boolean mIsDebuggable;

    WeblabClientWithNameValidation(MobileWeblabClientAttributes mobileWeblabClientAttributes, IMobileWeblabRuntimeConfiguration iMobileWeblabRuntimeConfiguration, String str, String str2, String str3, Context context) {
        if (mobileWeblabClientAttributes == null) {
            throw new IllegalArgumentException("Client attributes is null");
        }
        if (context == null) {
            throw new IllegalArgumentException("Context is null");
        }
        this.mInitialAttributes = mobileWeblabClientAttributes;
        this.mIsDebuggable = (context.getApplicationInfo().flags & 2) != 0;
        this.mClient = new WeblabClientBase(createFilteredAttributes(filterInvalidFormatWeblabsAndAddInInvalidList(), mobileWeblabClientAttributes), iMobileWeblabRuntimeConfiguration, str, str2, str3, context);
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public void setSessionAndMarketplaceId(String str, String str2) throws IllegalArgumentException {
        this.mClient.setSessionAndMarketplaceId(str, str2);
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public String getSession() {
        return this.mClient.getSession();
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public String getMarketplace() {
        return this.mClient.getMarketplace();
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public void setDirectedId(String str) {
        this.mClient.setDirectedId(str);
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public String getDirectedId() {
        return this.mClient.getDirectedId();
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public IMobileWeblabClientAttributes getIMobileWeblabClientAttributes() {
        return this.mInitialAttributes;
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public void addWeblab(String str, String str2) throws TooManyRegisteredWeblabsException, IllegalArgumentException {
        this.mInitialAttributes.addWeblab(str, str2);
        if (isWeblabNameValidAndSetupInvalidWeblabIfNecessary(str)) {
            this.mClient.addWeblab(str, str2);
        }
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public boolean update() throws MobileWeblabException {
        return this.mClient.update();
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public Future<Boolean> updateAsync() throws RejectedExecutionException {
        return this.mClient.updateAsync();
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public IMobileWeblab getWeblab(String str) throws IllegalStateException, IllegalArgumentException {
        if (this.mInvalidWeblabsAndTheirLock.containsKey(str)) {
            return new IMobileWeblab(str) { // from class: com.amazon.weblab.mobile.WeblabClientWithNameValidation.1
                private Future<MobileWeblabTriggerResult> resultFuture;
                final /* synthetic */ String val$weblabName;

                {
                    this.val$weblabName = str;
                    this.resultFuture = new MobileWeblabFailedImmediateFuture(new MobileWeblabException("Weblab " + str + " does not have a valid name"));
                }

                @Override // com.amazon.weblab.mobile.IMobileWeblab
                public String getTreatmentAssignment() {
                    return WeblabClientWithNameValidation.this.getInvalidWeblabTreatment(this.val$weblabName);
                }

                @Override // com.amazon.weblab.mobile.IMobileWeblab
                public IMobileWeblabTreatmentAndTriggerResult getTreatmentAndRecordTrigger() throws RejectedExecutionException {
                    return new MobileWeblabTreatmentAndTriggerResult(WeblabClientWithNameValidation.this.getInvalidWeblabTreatment(this.val$weblabName), this.resultFuture);
                }
            };
        }
        return this.mClient.getWeblab(str);
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public void lockWeblab(String str, String str2) throws IllegalStateException, IllegalArgumentException {
        if (this.mInvalidWeblabsAndTheirLock.containsKey(str)) {
            if (str2 == null || str2.isEmpty()) {
                return;
            }
            this.mInvalidWeblabsAndTheirLock.put(str, str2);
            return;
        }
        this.mClient.lockWeblab(str, str2);
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabClient
    public void unlockWeblab(String str) {
        if (this.mInvalidWeblabsAndTheirLock.containsKey(str)) {
            this.mInvalidWeblabsAndTheirLock.put(str, "");
        } else {
            this.mClient.unlockWeblab(str);
        }
    }

    private MobileWeblabClientAttributes createFilteredAttributes(Map<String, String> map, MobileWeblabClientAttributes mobileWeblabClientAttributes) {
        return new MobileWeblabClientAttributes(mobileWeblabClientAttributes.getIdentifier(), mobileWeblabClientAttributes.getApplicationName(), mobileWeblabClientAttributes.getApplicationVersion(), mobileWeblabClientAttributes.getOSName(), mobileWeblabClientAttributes.getOSVersion(), mobileWeblabClientAttributes.getClientAttributes(), mobileWeblabClientAttributes.getDomain(), map);
    }

    private Map<String, String> filterInvalidFormatWeblabsAndAddInInvalidList() {
        HashMap map = new HashMap();
        for (Map.Entry<String, String> entry : this.mInitialAttributes.getWeblabs().entrySet()) {
            if (isWeblabNameValidAndSetupInvalidWeblabIfNecessary(entry.getKey())) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }

    private boolean isWeblabNameValidAndSetupInvalidWeblabIfNecessary(String str) {
        boolean zIsWeblabNameValid = isWeblabNameValid(str);
        if (!zIsWeblabNameValid) {
            this.mInvalidWeblabsAndTheirLock.putIfAbsent(str, "");
        }
        return zIsWeblabNameValid;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getInvalidWeblabTreatment(String str) {
        String str2 = this.mInvalidWeblabsAndTheirLock.get(str);
        return str2.equals("") ? this.mInitialAttributes.getWeblabs().get(str) : str2;
    }

    private boolean isWeblabNameValid(String str) {
        boolean z = false;
        if ((str.length() <= 100) && WEBLAB_NAME_PATTERN.matcher(str).matches()) {
            z = true;
        }
        if (z || !isDebuggable()) {
            return z;
        }
        throw new IllegalArgumentException(str + " is not valid");
    }

    private boolean isDebuggable() {
        return this.mIsDebuggable;
    }
}
