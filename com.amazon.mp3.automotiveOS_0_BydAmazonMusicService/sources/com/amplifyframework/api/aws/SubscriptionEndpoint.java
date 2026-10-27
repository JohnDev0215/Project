package com.amplifyframework.api.aws;

import android.net.Uri;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.logging.Logger;
import com.google.firebase.perf.plugin.util.AsmString;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
final class SubscriptionEndpoint {
    private static final int CONNECTION_ACKNOWLEDGEMENT_TIMEOUT = 30;
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-api");
    private static final int NORMAL_CLOSURE_STATUS = 1000;
    private static final String UNAUTHORIZED_EXCEPTION = "UnauthorizedException";
    private final ApiConfiguration apiConfiguration;
    private final SubscriptionAuthorizer authorizer;
    private final OkHttpClient okHttpClient;
    private final Set<String> pendingSubscriptionIds;
    private final GraphQLResponse.Factory responseFactory;
    private final Map<String, Subscription<?>> subscriptions;
    private final TimeoutWatchdog timeoutWatchdog;
    private WebSocket webSocket;
    private AmplifyWebSocketListener webSocketListener;

    SubscriptionEndpoint(@NonNull ApiConfiguration apiConfiguration, @NonNull GraphQLResponse.Factory factory, @NonNull SubscriptionAuthorizer subscriptionAuthorizer) throws ApiException {
        Objects.requireNonNull(apiConfiguration);
        this.apiConfiguration = apiConfiguration;
        this.subscriptions = new ConcurrentHashMap();
        Objects.requireNonNull(factory);
        this.responseFactory = factory;
        Objects.requireNonNull(subscriptionAuthorizer);
        this.authorizer = subscriptionAuthorizer;
        this.timeoutWatchdog = new TimeoutWatchdog();
        this.pendingSubscriptionIds = Collections.synchronizedSet(new HashSet());
        this.okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(UserAgentInterceptor.using(new AWSApiPlugin$$ExternalSyntheticLambda0())).retryOnConnectionFailure(true).build();
    }

    synchronized <T> void requestSubscription(@NonNull GraphQLRequest<T> graphQLRequest, @NonNull Consumer<String> consumer, @NonNull Consumer<GraphQLResponse<T>> consumer2, @NonNull Consumer<ApiException> consumer3, @NonNull Action action) {
        requestSubscription(graphQLRequest, this.apiConfiguration.getAuthorizationType(), consumer, consumer2, consumer3, action);
    }

    synchronized <T> void requestSubscription(@NonNull GraphQLRequest<T> graphQLRequest, @NonNull AuthorizationType authorizationType, @NonNull Consumer<String> consumer, @NonNull Consumer<GraphQLResponse<T>> consumer2, @NonNull Consumer<ApiException> consumer3, @NonNull Action action) {
        Objects.requireNonNull(graphQLRequest);
        Objects.requireNonNull(consumer);
        Objects.requireNonNull(consumer2);
        Objects.requireNonNull(consumer3);
        Objects.requireNonNull(action);
        AmplifyWebSocketListener amplifyWebSocketListener = this.webSocketListener;
        if (amplifyWebSocketListener == null || amplifyWebSocketListener.isDisconnectedState()) {
            this.webSocketListener = new AmplifyWebSocketListener(this);
            try {
                this.webSocket = this.okHttpClient.newWebSocket(new Request.Builder().url(buildConnectionRequestUrl(authorizationType)).addHeader("Sec-WebSocket-Protocol", "graphql-ws").build(), this.webSocketListener);
            } catch (ApiException e) {
                consumer3.accept(e);
                return;
            }
        }
        String string = UUID.randomUUID().toString();
        this.pendingSubscriptionIds.add(string);
        Connection connectionWaitForConnectionReady = this.webSocketListener.waitForConnectionReady();
        if (connectionWaitForConnectionReady.hasFailure() && this.pendingSubscriptionIds.remove(string)) {
            consumer3.accept(new ApiException(connectionWaitForConnectionReady.getFailureReason(), AmplifyException.TODO_RECOVERY_SUGGESTION));
            return;
        }
        try {
            this.webSocket.send(new JSONObject().put("id", string).put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, AsmString.METHOD_FIREBASE_PERF_TRACE_START).put("payload", new JSONObject().put("data", graphQLRequest.getContent()).put("extensions", new JSONObject().put("authorization", this.authorizer.createHeadersForSubscription(graphQLRequest, authorizationType)))).toString());
            Subscription<?> subscription = new Subscription<>(consumer2, consumer3, action, this.responseFactory, graphQLRequest.getResponseType(), graphQLRequest);
            this.subscriptions.put(string, subscription);
            if (subscription.awaitSubscriptionReady()) {
                this.pendingSubscriptionIds.remove(string);
                consumer.accept(string);
            }
        } catch (ApiException | JSONException e2) {
            if (this.pendingSubscriptionIds.remove(string)) {
                if (e2 instanceof ApiException.ApiAuthException) {
                    consumer3.accept((ApiException.ApiAuthException) e2);
                } else {
                    consumer3.accept(new ApiException("Failed to construct subscription registration message.", e2, AmplifyException.TODO_RECOVERY_SUGGESTION));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySubscriptionAcknowledged(String str) throws ApiException {
        Subscription<?> subscription = this.subscriptions.get(str);
        if (subscription != null && this.pendingSubscriptionIds.remove(str)) {
            subscription.acknowledgeSubscriptionReady();
            return;
        }
        throw new ApiException("Acknowledgement for unknown subscription: " + str, AmplifyException.TODO_RECOVERY_SUGGESTION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySubscriptionFailure(String str) {
        Subscription<?> subscription = this.subscriptions.get(str);
        if (subscription == null || !this.pendingSubscriptionIds.remove(str)) {
            return;
        }
        subscription.acknowledgeSubscriptionFailure();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAllSubscriptionsCompleted() {
        Iterator it = new HashSet(this.subscriptions.values()).iterator();
        while (it.hasNext()) {
            ((Subscription) it.next()).dispatchCompleted();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySubscriptionCompleted(String str) throws ApiException {
        Subscription<?> subscription = this.subscriptions.get(str);
        if (subscription == null) {
            throw new ApiException("Got subscription completion for unknown subscription:" + str, AmplifyException.TODO_RECOVERY_SUGGESTION);
        }
        subscription.dispatchCompleted();
        subscription.acknowledgeSubscriptionCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyError(Throwable th) {
        Iterator it = new HashSet(this.subscriptions.values()).iterator();
        while (it.hasNext()) {
            ((Subscription) it.next()).dispatchError(new ApiException("Subscription failed.", th, "Check your Internet connection. Is your device online?"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySubscriptionData(String str, String str2) throws ApiException {
        Subscription<?> subscription = this.subscriptions.get(str);
        if (subscription == null) {
            throw new ApiException("Got subscription data for unknown subscription ID: " + str, AmplifyException.TODO_RECOVERY_SUGGESTION);
        }
        subscription.dispatchNextMessage(str2);
    }

    synchronized void releaseSubscription(String str) throws ApiException {
        Subscription<?> subscription = this.subscriptions.get(str);
        boolean zRemove = this.pendingSubscriptionIds.remove(str);
        if (subscription == null && !zRemove) {
            throw new ApiException("No existing subscription with the given id.", AmplifyException.TODO_RECOVERY_SUGGESTION);
        }
        if (!zRemove && !this.webSocketListener.isDisconnectedState()) {
            try {
                this.webSocket.send(new JSONObject().put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, AsmString.METHOD_FIREBASE_PERF_TRACE_STOP).put("id", str).toString());
                subscription.awaitSubscriptionCompleted();
            } catch (JSONException e) {
                throw new ApiException("Failed to construct subscription release message.", e, AmplifyException.TODO_RECOVERY_SUGGESTION);
            }
        }
        this.subscriptions.remove(str);
        if (this.subscriptions.size() == 0) {
            LOG.info("No more active subscriptions. Closing web socket.");
            this.timeoutWatchdog.stop();
            this.webSocket.close(1000, "No active subscriptions");
        }
    }

    private String buildConnectionRequestUrl(AuthorizationType authorizationType) throws ApiException {
        URL url;
        byte[] bytes = this.authorizer.createHeadersForConnection(authorizationType).toString().getBytes();
        try {
            url = new URL(this.apiConfiguration.getEndpoint());
        } catch (MalformedURLException unused) {
            url = null;
        }
        if (url == null) {
            throw new ApiException("Malformed API Url: " + this.apiConfiguration.getEndpoint(), "Verify that GraphQL endpoint is properly formatted.");
        }
        DomainType domainTypeFrom = DomainType.from(this.apiConfiguration.getEndpoint());
        String host = url.getHost();
        if (domainTypeFrom == DomainType.STANDARD) {
            host = host.replace("appsync-api", "appsync-realtime-api");
        }
        String path = url.getPath();
        if (domainTypeFrom == DomainType.CUSTOM) {
            path = path + "/realtime";
        }
        return new Uri.Builder().scheme("wss").authority(host).appendPath(path).appendQueryParameter("header", Base64.encodeToString(bytes, 0)).appendQueryParameter("payload", "e30=").build().toString();
    }

    static final class Subscription<T> {
        private static final int ACKNOWLEDGEMENT_TIMEOUT = 10;
        private final Consumer<GraphQLResponse<T>> onNextItem;
        private final Action onSubscriptionComplete;
        private final Consumer<ApiException> onSubscriptionError;
        private final GraphQLRequest<T> request;
        private final GraphQLResponse.Factory responseFactory;
        private final Type responseType;
        private final CountDownLatch subscriptionReadyAcknowledgment = new CountDownLatch(1);
        private final CountDownLatch subscriptionCompletionAcknowledgement = new CountDownLatch(1);
        private boolean failed = false;

        Subscription(Consumer<GraphQLResponse<T>> consumer, Consumer<ApiException> consumer2, Action action, GraphQLResponse.Factory factory, Type type, GraphQLRequest<T> graphQLRequest) {
            this.onNextItem = consumer;
            this.onSubscriptionError = consumer2;
            this.onSubscriptionComplete = action;
            this.responseFactory = factory;
            this.responseType = type;
            this.request = graphQLRequest;
        }

        void acknowledgeSubscriptionReady() {
            this.subscriptionReadyAcknowledgment.countDown();
        }

        void acknowledgeSubscriptionFailure() {
            this.failed = true;
            this.subscriptionReadyAcknowledgment.countDown();
        }

        boolean awaitSubscriptionReady() {
            try {
                if (this.subscriptionReadyAcknowledgment.await(10L, TimeUnit.SECONDS)) {
                    return !this.failed;
                }
                dispatchError(new ApiException("Timed out waiting for subscription start_ack.", "Check your Internet connection. Is your device online?"));
                return false;
            } catch (InterruptedException e) {
                SubscriptionEndpoint.LOG.warn("Thread interrupted awaiting subscription acknowledgement.", e);
                return false;
            }
        }

        void acknowledgeSubscriptionCompleted() {
            this.subscriptionCompletionAcknowledgement.countDown();
        }

        void awaitSubscriptionCompleted() {
            try {
                if (this.subscriptionCompletionAcknowledgement.await(10L, TimeUnit.SECONDS)) {
                    return;
                }
                dispatchError(new ApiException("Subscription completion not acknowledged.", AmplifyException.TODO_RECOVERY_SUGGESTION));
            } catch (InterruptedException e) {
                dispatchError(new ApiException("Thread interrupted awaiting subscription completion.", e, AmplifyException.TODO_RECOVERY_SUGGESTION));
            }
        }

        void dispatchNextMessage(String str) {
            try {
                this.onNextItem.accept(this.responseFactory.buildResponse(this.request, str));
            } catch (ApiException e) {
                dispatchError(e);
            }
        }

        void dispatchError(ApiException apiException) {
            this.onSubscriptionError.accept(apiException);
        }

        void dispatchCompleted() {
            this.onSubscriptionComplete.call();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Subscription.class != obj.getClass()) {
                return false;
            }
            Subscription subscription = (Subscription) obj;
            if (ObjectsCompat.equals(this.onNextItem, subscription.onNextItem) && ObjectsCompat.equals(this.onSubscriptionError, subscription.onSubscriptionError) && ObjectsCompat.equals(this.onSubscriptionComplete, subscription.onSubscriptionComplete) && ObjectsCompat.equals(this.responseFactory, subscription.responseFactory) && ObjectsCompat.equals(this.responseType, subscription.responseType) && ObjectsCompat.equals(this.subscriptionReadyAcknowledgment, subscription.subscriptionReadyAcknowledgment)) {
                return ObjectsCompat.equals(this.subscriptionCompletionAcknowledgement, subscription.subscriptionCompletionAcknowledgement);
            }
            return false;
        }

        public int hashCode() {
            return (((((((((((this.onNextItem.hashCode() * 31) + this.onSubscriptionError.hashCode()) * 31) + this.onSubscriptionComplete.hashCode()) * 31) + this.responseFactory.hashCode()) * 31) + this.responseType.hashCode()) * 31) + this.subscriptionReadyAcknowledgment.hashCode()) * 31) + this.subscriptionCompletionAcknowledgement.hashCode();
        }
    }

    final class AmplifyWebSocketListener extends WebSocketListener {
        private final CountDownLatch connectionResponse;
        private final AtomicReference<EndpointStatus> endpointStatus;
        private OkHttpClient okHttpClient;

        AmplifyWebSocketListener(SubscriptionEndpoint subscriptionEndpoint) {
            this(new CountDownLatch(1));
        }

        AmplifyWebSocketListener(CountDownLatch countDownLatch) {
            this.connectionResponse = countDownLatch;
            this.endpointStatus = new AtomicReference<>(EndpointStatus.DISCONNECTED);
        }

        @Override // okhttp3.WebSocketListener
        public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
            sendConnectionInit(webSocket);
        }

        @Override // okhttp3.WebSocketListener
        public void onMessage(@NonNull WebSocket webSocket, @NonNull String str) {
            try {
                processJsonMessage(webSocket, str);
            } catch (ApiException e) {
                SubscriptionEndpoint.this.notifyError(e);
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onClosing(@NonNull WebSocket webSocket, int i, @NonNull String str) {
            SubscriptionEndpoint.this.notifyAllSubscriptionsCompleted();
        }

        @Override // okhttp3.WebSocketListener
        public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable th, Response response) {
            SubscriptionEndpoint.LOG.warn("Websocket connection failed.", th);
            this.endpointStatus.set(EndpointStatus.CONNECTION_FAILED);
            webSocket.cancel();
            this.connectionResponse.countDown();
            SubscriptionEndpoint.this.notifyError(th);
        }

        @Override // okhttp3.WebSocketListener
        public void onClosed(@NonNull WebSocket webSocket, int i, @NonNull String str) {
            super.onClosed(webSocket, i, str);
            this.endpointStatus.set(EndpointStatus.DISCONNECTED);
        }

        public boolean isDisconnectedState() {
            return this.endpointStatus.get().isDisconnectedState();
        }

        public Connection waitForConnectionReady() {
            try {
                if (!this.connectionResponse.await(30L, TimeUnit.SECONDS)) {
                    SubscriptionEndpoint.LOG.warn("Timed out waiting for connection acknowledgement.");
                    return new Connection("Timed out waiting for connection acknowledgement.");
                }
                SubscriptionEndpoint.LOG.debug("Current endpoint status: " + this.endpointStatus.get());
                if (EndpointStatus.CONNECTION_FAILED.equals(this.endpointStatus.get())) {
                    return new Connection("Connection failed.");
                }
                return new Connection();
            } catch (InterruptedException unused) {
                SubscriptionEndpoint.LOG.warn("Thread interrupted waiting for connection acknowledgement");
                return new Connection("Thread interrupted waiting for connection acknowledgement");
            }
        }

        private void sendConnectionInit(WebSocket webSocket) {
            try {
                webSocket.send(new JSONObject().put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "connection_init").toString());
            } catch (JSONException e) {
                SubscriptionEndpoint.this.notifyError(e);
            }
        }

        private void processJsonMessage(final WebSocket webSocket, String str) throws ApiException {
            try {
                JSONObject jSONObject = new JSONObject(str);
                SubscriptionMessageType subscriptionMessageTypeFrom = SubscriptionMessageType.from(jSONObject.getString(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE));
                switch (AnonymousClass1.$SwitchMap$com$amplifyframework$api$aws$SubscriptionMessageType[subscriptionMessageTypeFrom.ordinal()]) {
                    case 1:
                        SubscriptionEndpoint.this.timeoutWatchdog.start(new Runnable() { // from class: com.amplifyframework.api.aws.SubscriptionEndpoint$AmplifyWebSocketListener$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                webSocket.close(1000, "WebSocket closed due to timeout.");
                            }
                        }, Integer.parseInt(jSONObject.getJSONObject("payload").getString("connectionTimeoutMs")));
                        this.endpointStatus.set(EndpointStatus.CONNECTED);
                        this.connectionResponse.countDown();
                        return;
                    case 2:
                        this.endpointStatus.set(EndpointStatus.CONNECTION_FAILED);
                        SubscriptionEndpoint.LOG.warn("Websocket listener received a CONNECTION_ERROR event. " + str);
                        this.connectionResponse.countDown();
                        return;
                    case 3:
                        SubscriptionEndpoint.this.notifySubscriptionAcknowledged(jSONObject.getString("id"));
                        return;
                    case 4:
                        SubscriptionEndpoint.this.notifySubscriptionCompleted(jSONObject.getString("id"));
                        return;
                    case 5:
                        SubscriptionEndpoint.this.timeoutWatchdog.reset();
                        return;
                    case 6:
                        SubscriptionEndpoint.this.notifySubscriptionFailure(jSONObject.getString("id"));
                        SubscriptionEndpoint.this.notifySubscriptionData(jSONObject.getString("id"), jSONObject.getString("payload"));
                        return;
                    case 7:
                        SubscriptionEndpoint.this.notifySubscriptionData(jSONObject.getString("id"), jSONObject.getString("payload"));
                        return;
                    default:
                        SubscriptionEndpoint.this.notifyError(new ApiException("Got unknown message type: " + subscriptionMessageTypeFrom, AmplifyException.TODO_RECOVERY_SUGGESTION));
                        return;
                }
            } catch (JSONException e) {
                throw new ApiException("Error processing Json message in subscription endpoint.", e, AmplifyException.TODO_RECOVERY_SUGGESTION);
            }
        }
    }

    /* JADX INFO: renamed from: com.amplifyframework.api.aws.SubscriptionEndpoint$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$api$aws$SubscriptionMessageType;

        static {
            int[] iArr = new int[SubscriptionMessageType.values().length];
            $SwitchMap$com$amplifyframework$api$aws$SubscriptionMessageType = iArr;
            try {
                iArr[SubscriptionMessageType.CONNECTION_ACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$SubscriptionMessageType[SubscriptionMessageType.CONNECTION_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$SubscriptionMessageType[SubscriptionMessageType.SUBSCRIPTION_ACK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$SubscriptionMessageType[SubscriptionMessageType.SUBSCRIPTION_COMPLETE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$SubscriptionMessageType[SubscriptionMessageType.CONNECTION_KEEP_ALIVE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$SubscriptionMessageType[SubscriptionMessageType.SUBSCRIPTION_ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$SubscriptionMessageType[SubscriptionMessageType.SUBSCRIPTION_DATA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    static final class Connection {
        private final String failureReason;

        Connection() {
            this.failureReason = null;
        }

        Connection(String str) {
            this.failureReason = str;
        }

        public String getFailureReason() {
            return this.failureReason;
        }

        public boolean hasFailure() {
            return this.failureReason != null;
        }
    }

    enum EndpointStatus {
        DISCONNECTED,
        CONNECTING,
        CONNECTED,
        CONNECTION_FAILED;

        boolean isDisconnectedState() {
            return equals(DISCONNECTED) || equals(CONNECTION_FAILED);
        }
    }
}
