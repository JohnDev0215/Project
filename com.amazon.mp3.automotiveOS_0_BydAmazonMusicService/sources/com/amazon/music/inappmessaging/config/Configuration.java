package com.amazon.music.inappmessaging.config;

/* JADX INFO: loaded from: classes4.dex */
public class Configuration {
    private static final String DYN_MSG_ENDPOINT_SUFFIX = "/v2/dynamicmessages";
    private static final String GAMMA_STAGE = "/gamma";
    private static final String HANDLE_ACTION_ENDPOINT_SUFFIX = "/v2/handleaction";
    private static final String IAM_ENDPOINT_SUFFIX = "/v1/inappmessages";
    private static final String PROD_STAGE = "/prod";
    private static final String WEBLAB_TRIGGER_ENDPOINT_SUFFIX = "/v2/recordWeblabTriggers";
    private final String dynMsgEndpointUrl;
    private final String handleActionEndpointUrl;
    private final String iamEndpointUrl;
    private final Long timeout;
    private final String weblabTriggerEndpointUrl;

    Configuration(final Long timeout, final String iamLambdaEndpointUrl, final boolean shouldUseGammaOverride) {
        this.timeout = timeout;
        if (shouldUseGammaOverride) {
            this.iamEndpointUrl = iamLambdaEndpointUrl + GAMMA_STAGE + IAM_ENDPOINT_SUFFIX;
            this.dynMsgEndpointUrl = iamLambdaEndpointUrl + GAMMA_STAGE + DYN_MSG_ENDPOINT_SUFFIX;
            this.weblabTriggerEndpointUrl = iamLambdaEndpointUrl + GAMMA_STAGE + WEBLAB_TRIGGER_ENDPOINT_SUFFIX;
            this.handleActionEndpointUrl = iamLambdaEndpointUrl + GAMMA_STAGE + HANDLE_ACTION_ENDPOINT_SUFFIX;
            return;
        }
        this.iamEndpointUrl = iamLambdaEndpointUrl + PROD_STAGE + IAM_ENDPOINT_SUFFIX;
        this.dynMsgEndpointUrl = iamLambdaEndpointUrl + PROD_STAGE + DYN_MSG_ENDPOINT_SUFFIX;
        this.weblabTriggerEndpointUrl = iamLambdaEndpointUrl + PROD_STAGE + WEBLAB_TRIGGER_ENDPOINT_SUFFIX;
        this.handleActionEndpointUrl = iamLambdaEndpointUrl + PROD_STAGE + HANDLE_ACTION_ENDPOINT_SUFFIX;
    }

    public Long getTimeout() {
        return this.timeout;
    }

    public String getIamEndpointUrl() {
        return this.iamEndpointUrl;
    }

    public String getDynMsgEndpointUrl() {
        return this.dynMsgEndpointUrl;
    }

    public String getWeblabTriggerEndpointUrl() {
        return this.weblabTriggerEndpointUrl;
    }

    public String getHandleActionEndpointUrl() {
        return this.handleActionEndpointUrl;
    }
}
