package org.chromium.content_public.browser;

import com.bydux.api.common.tools.util.ListUtils;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.common.Referrer;
import org.chromium.content_public.common.ResourceRequestBody;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class LoadUrlParams {
    String mBaseUrlForDataUrl;
    boolean mCanLoadLocalResources;
    String mDataUrlAsString;
    private Map<String, String> mExtraHeaders;
    boolean mHasUserGesture;
    long mIntentReceivedTimestamp;
    boolean mIsRendererInitiated;
    int mLoadUrlType;
    ResourceRequestBody mPostData;
    Referrer mReferrer;
    boolean mShouldReplaceCurrentEntry;
    int mTransitionType;
    int mUaOverrideOption;
    String mUrl;
    private String mVerbatimHeaders;
    String mVirtualUrlForDataUrl;

    private static native boolean nativeIsDataScheme(String str);

    public LoadUrlParams(String str) {
        this(str, 0);
    }

    public LoadUrlParams(String str, int i) {
        this.mUrl = str;
        this.mTransitionType = i;
        this.mLoadUrlType = 0;
        this.mUaOverrideOption = 0;
        this.mPostData = null;
        this.mBaseUrlForDataUrl = null;
        this.mVirtualUrlForDataUrl = null;
        this.mDataUrlAsString = null;
    }

    public static LoadUrlParams createLoadDataParams(String str, String str2, boolean z) {
        return createLoadDataParams(str, str2, z, null);
    }

    public static LoadUrlParams createLoadDataParams(String str, String str2, boolean z, String str3) {
        LoadUrlParams loadUrlParams = new LoadUrlParams(buildDataUri(str, str2, z, str3));
        loadUrlParams.setLoadType(2);
        loadUrlParams.setTransitionType(1);
        return loadUrlParams;
    }

    private static String buildDataUri(String str, String str2, boolean z, String str3) {
        StringBuilder sb = new StringBuilder("data:");
        sb.append(str2);
        if (str3 != null && !str3.isEmpty()) {
            sb.append(";charset=" + str3);
        }
        if (z) {
            sb.append(";base64");
        }
        sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        sb.append(str);
        return sb.toString();
    }

    public static LoadUrlParams createLoadDataParamsWithBaseUrl(String str, String str2, boolean z, String str3, String str4) {
        return createLoadDataParamsWithBaseUrl(str, str2, z, str3, str4, null);
    }

    public static LoadUrlParams createLoadDataParamsWithBaseUrl(String str, String str2, boolean z, String str3, String str4, String str5) {
        if (str3 == null || !str3.toLowerCase(Locale.US).startsWith("data:")) {
            LoadUrlParams loadUrlParamsCreateLoadDataParams = createLoadDataParams("", str2, z, str5);
            if (str3 == null) {
                str3 = "about:blank";
            }
            loadUrlParamsCreateLoadDataParams.setBaseUrlForDataUrl(str3);
            if (str4 == null) {
                str4 = "about:blank";
            }
            loadUrlParamsCreateLoadDataParams.setVirtualUrlForDataUrl(str4);
            loadUrlParamsCreateLoadDataParams.setDataUrlAsString(buildDataUri(str, str2, z, str5));
            return loadUrlParamsCreateLoadDataParams;
        }
        return createLoadDataParams(str, str2, z, str5);
    }

    public static LoadUrlParams createLoadHttpPostParams(String str, byte[] bArr) {
        LoadUrlParams loadUrlParams = new LoadUrlParams(str);
        loadUrlParams.setLoadType(1);
        loadUrlParams.setTransitionType(1);
        loadUrlParams.setPostData(ResourceRequestBody.createFromBytes(bArr));
        return loadUrlParams;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getBaseUrl() {
        return this.mBaseUrlForDataUrl;
    }

    public void setLoadType(int i) {
        this.mLoadUrlType = i;
    }

    public void setTransitionType(int i) {
        this.mTransitionType = i;
    }

    public int getTransitionType() {
        return this.mTransitionType;
    }

    public void setReferrer(Referrer referrer) {
        this.mReferrer = referrer;
    }

    public Referrer getReferrer() {
        return this.mReferrer;
    }

    public void setExtraHeaders(Map<String, String> map) {
        this.mExtraHeaders = map;
    }

    public Map<String, String> getExtraHeaders() {
        return this.mExtraHeaders;
    }

    public String getExtraHeadersString() {
        return getExtraHeadersString("\n", false);
    }

    public String getExtraHttpRequestHeadersString() {
        return getExtraHeadersString(IOUtils.LINE_SEPARATOR_WINDOWS, true);
    }

    private String getExtraHeadersString(String str, boolean z) {
        if (this.mExtraHeaders == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : this.mExtraHeaders.entrySet()) {
            if (sb.length() > 0) {
                sb.append(str);
            }
            sb.append(entry.getKey().toLowerCase(Locale.US));
            sb.append(":");
            sb.append(entry.getValue());
        }
        if (z) {
            sb.append(str);
        }
        return sb.toString();
    }

    public void setVerbatimHeaders(String str) {
        this.mVerbatimHeaders = str;
    }

    public String getVerbatimHeaders() {
        return this.mVerbatimHeaders;
    }

    public void setOverrideUserAgent(int i) {
        this.mUaOverrideOption = i;
    }

    public int getUserAgentOverrideOption() {
        return this.mUaOverrideOption;
    }

    public void setPostData(ResourceRequestBody resourceRequestBody) {
        this.mPostData = resourceRequestBody;
    }

    public ResourceRequestBody getPostData() {
        return this.mPostData;
    }

    public void setBaseUrlForDataUrl(String str) {
        this.mBaseUrlForDataUrl = str;
    }

    public String getVirtualUrlForDataUrl() {
        return this.mVirtualUrlForDataUrl;
    }

    public void setVirtualUrlForDataUrl(String str) {
        this.mVirtualUrlForDataUrl = str;
    }

    public String getDataUrlAsString() {
        return this.mDataUrlAsString;
    }

    public void setDataUrlAsString(String str) {
        this.mDataUrlAsString = str;
    }

    public void setCanLoadLocalResources(boolean z) {
        this.mCanLoadLocalResources = z;
    }

    public boolean getCanLoadLocalResources() {
        return this.mCanLoadLocalResources;
    }

    public int getLoadUrlType() {
        return this.mLoadUrlType;
    }

    public void setIsRendererInitiated(boolean z) {
        this.mIsRendererInitiated = z;
    }

    public boolean getIsRendererInitiated() {
        return this.mIsRendererInitiated;
    }

    public void setShouldReplaceCurrentEntry(boolean z) {
        this.mShouldReplaceCurrentEntry = z;
    }

    public boolean getShouldReplaceCurrentEntry() {
        return this.mShouldReplaceCurrentEntry;
    }

    public void setIntentReceivedTimestamp(long j) {
        this.mIntentReceivedTimestamp = j;
    }

    public long getIntentReceivedTimestamp() {
        return this.mIntentReceivedTimestamp;
    }

    public void setHasUserGesture(boolean z) {
        this.mHasUserGesture = z;
    }

    public boolean getHasUserGesture() {
        return this.mHasUserGesture;
    }

    public boolean isBaseUrlDataScheme() {
        String str = this.mBaseUrlForDataUrl;
        if (str == null && this.mLoadUrlType == 2) {
            return true;
        }
        return nativeIsDataScheme(str);
    }
}
