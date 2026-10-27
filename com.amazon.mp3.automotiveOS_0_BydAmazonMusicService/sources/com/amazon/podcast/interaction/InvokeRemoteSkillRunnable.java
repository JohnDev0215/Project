package com.amazon.podcast.interaction;

import Podcast.DeeplinkInterface.v1_0.InvokeDeeplinkMethod;
import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.ClientState;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Response;
import SOAInteractionInterface.v1_0.GlobalInvokeRemoteSkillMethod;
import SOAInteractionInterface.v1_0.InvokeRemoteSkillMethod;
import android.util.ArrayMap;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.podcast.ClientStates;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.bootstrap.UserInfoProvider;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.metrics.latency.LatencyMetricInterface;
import com.amazon.soa.core.OnMethodExecutedListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class InvokeRemoteSkillRunnable implements Runnable {
    private final AuthenticationProvider authProvider;
    private final BuildInfoProvider buildInfoProvider;
    private final ClientStates clientStates;
    private final DeviceInfoProvider deviceInfoProvider;
    private final OnMethodExecutedListener listener;
    private final Logger logger = LoggerFactory.getLogger("InvokeRemoteSkillRunnab");
    private final Method method;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final UserInfoProvider userInfoProvider;

    public InvokeRemoteSkillRunnable(final String ownerId, final Method method, final DeviceInfoProvider deviceInfoProvider, final BuildInfoProvider buildInfoProvider, final ClientStates clientStates, final UserInfoProvider userInfoProvider, final AuthenticationProvider authProvider, final MethodsDispatcher methodsDispatcher, final OnMethodExecutedListener listener) {
        this.ownerId = ownerId;
        this.method = method;
        this.deviceInfoProvider = deviceInfoProvider;
        this.buildInfoProvider = buildInfoProvider;
        this.clientStates = clientStates;
        this.userInfoProvider = userInfoProvider;
        this.authProvider = authProvider;
        this.methodsDispatcher = methodsDispatcher;
        this.listener = listener;
    }

    @Override // java.lang.Runnable
    public void run() {
        String target;
        String preset;
        Map<String, String> extra;
        List<String> clientStates;
        List<Method> before;
        List<Method> after;
        List<Method> onError;
        List<Method> onSuccess;
        String str;
        String pageType;
        String str2;
        String str3;
        Map<String, String> map;
        Method method = this.method;
        boolean z = false;
        if (method instanceof InvokeDeeplinkMethod) {
            InvokeDeeplinkMethod invokeDeeplinkMethod = (InvokeDeeplinkMethod) method;
            String url = invokeDeeplinkMethod.getUrl();
            String deeplinkUrl = invokeDeeplinkMethod.getDeeplinkUrl();
            String target2 = invokeDeeplinkMethod.getTarget();
            String preset2 = invokeDeeplinkMethod.getPreset();
            Map<String, String> extra2 = invokeDeeplinkMethod.getExtra();
            clientStates = invokeDeeplinkMethod.getClientStates();
            before = invokeDeeplinkMethod.getBefore();
            after = invokeDeeplinkMethod.getAfter();
            onError = invokeDeeplinkMethod.getOnError();
            onSuccess = invokeDeeplinkMethod.getOnSuccess();
            boolean zIsBackStackRequired = invokeDeeplinkMethod.isBackStackRequired();
            LatencyMetricElement latencyMetricElement = invokeDeeplinkMethod.getLatencyMetricElement();
            str = url;
            pageType = latencyMetricElement != null ? latencyMetricElement.getPageType() : null;
            str2 = target2;
            str3 = preset2;
            map = extra2;
            str = deeplinkUrl;
            z = zIsBackStackRequired;
        } else {
            if (method instanceof InvokeRemoteSkillMethod) {
                InvokeRemoteSkillMethod invokeRemoteSkillMethod = (InvokeRemoteSkillMethod) method;
                String url2 = invokeRemoteSkillMethod.getUrl();
                target = invokeRemoteSkillMethod.getTarget();
                preset = invokeRemoteSkillMethod.getPreset();
                extra = invokeRemoteSkillMethod.getExtra();
                clientStates = invokeRemoteSkillMethod.getClientStates();
                before = invokeRemoteSkillMethod.getBefore();
                after = invokeRemoteSkillMethod.getAfter();
                onError = invokeRemoteSkillMethod.getOnError();
                onSuccess = invokeRemoteSkillMethod.getOnSuccess();
                LatencyMetricElement latencyMetricElement2 = invokeRemoteSkillMethod.getLatencyMetricElement();
                pageType = latencyMetricElement2 != null ? latencyMetricElement2.getPageType() : null;
                str = url2;
            } else {
                GlobalInvokeRemoteSkillMethod globalInvokeRemoteSkillMethod = (GlobalInvokeRemoteSkillMethod) method;
                String url3 = globalInvokeRemoteSkillMethod.getUrl();
                target = globalInvokeRemoteSkillMethod.getTarget();
                preset = globalInvokeRemoteSkillMethod.getPreset();
                extra = globalInvokeRemoteSkillMethod.getExtra();
                clientStates = globalInvokeRemoteSkillMethod.getClientStates();
                before = globalInvokeRemoteSkillMethod.getBefore();
                after = globalInvokeRemoteSkillMethod.getAfter();
                onError = globalInvokeRemoteSkillMethod.getOnError();
                onSuccess = globalInvokeRemoteSkillMethod.getOnSuccess();
                str = url3;
                pageType = null;
            }
            str2 = target;
            str3 = preset;
            map = extra;
        }
        this.logger.debug("deeplink url {}", str);
        LatencyMetricInterface.trackLatency(LatencyTrackingLeg.REQUEST, pageType);
        Map<String, ClientState> mapClientStateMap = clientStateMap(this.clientStates, clientStates);
        if (StringUtils.isNotBlank(str)) {
            ClientStates.Entry entryDeeplinkClientState = this.clientStates.deeplinkClientState(str, z);
            mapClientStateMap.put(entryDeeplinkClientState.getKey(), entryDeeplinkClientState.getClientState());
        }
        if (shouldAbortRequest(mapClientStateMap)) {
            this.listener.onMethodExecuted(this.method);
            return;
        }
        this.methodsDispatcher.dispatch(this.ownerId, before);
        Response responseExecute = new InvokeRemoteSkillTask(str, str2, str3, this.deviceInfoProvider, this.buildInfoProvider, map, this.userInfoProvider, this.authProvider, mapClientStateMap, pageType).execute();
        this.listener.onMethodExecuted(this.method);
        if (responseExecute == null) {
            this.methodsDispatcher.dispatch(this.ownerId, after);
            this.methodsDispatcher.dispatch(this.ownerId, onError);
        } else {
            this.methodsDispatcher.dispatch(this.ownerId, responseExecute.getMethods());
            this.methodsDispatcher.dispatch(this.ownerId, after);
            this.methodsDispatcher.dispatch(this.ownerId, onSuccess);
        }
    }

    private Map<String, ClientState> clientStateMap(final ClientStates clientStates, final List<String> clientStateNames) {
        ArrayMap arrayMap = new ArrayMap();
        if (clientStateNames == null) {
            return arrayMap;
        }
        Iterator<String> it = clientStateNames.iterator();
        while (it.hasNext()) {
            ClientStates.Entry entry = clientStates.get(it.next());
            if (entry != null) {
                arrayMap.put(entry.getKey(), entry.getClientState());
            }
        }
        return arrayMap;
    }

    private boolean shouldAbortRequest(final Map<String, ClientState> clientStateMap) {
        return PodcastFeatureGating.REQUEST_FILTER.isEnabled() && !clientStateMap.isEmpty() && containsExcludableClientStates(clientStateMap);
    }

    private boolean containsExcludableClientStates(final Map<String, ClientState> clientStateMap) {
        Iterator<ClientState> it = clientStateMap.values().iterator();
        while (it.hasNext()) {
            if (!this.clientStates.shouldExclude(it.next())) {
                return false;
            }
        }
        return true;
    }
}
