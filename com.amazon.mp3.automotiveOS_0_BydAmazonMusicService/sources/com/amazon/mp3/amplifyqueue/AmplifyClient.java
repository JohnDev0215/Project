package com.amazon.mp3.amplifyqueue;

import android.content.Context;
import android.util.Log;
import com.amazon.digitalmusicxp.CustomerInfoProvider;
import com.amazon.digitalmusicxp.callbacks.ChangeStateResult;
import com.amazon.digitalmusicxp.callbacks.Outcome;
import com.amazon.digitalmusicxp.clients.CloudQueueClient;
import com.amazon.digitalmusicxp.enums.GetNextQueueEntityResponseStatusEnum;
import com.amazon.digitalmusicxp.enums.QueueSeedTypeEnum;
import com.amazon.digitalmusicxp.enums.QueueViewDirectionEnum;
import com.amazon.digitalmusicxp.enums.ServiceTierEnum;
import com.amazon.digitalmusicxp.inputs.DeviceContextInput;
import com.amazon.digitalmusicxp.inputs.GetNextEntityInput;
import com.amazon.digitalmusicxp.inputs.GetQueueViewInput;
import com.amazon.digitalmusicxp.inputs.InitiateQueueInput;
import com.amazon.digitalmusicxp.inputs.ParentalControlsInput;
import com.amazon.digitalmusicxp.models.MetricsContext;
import com.amazon.digitalmusicxp.models.QueueCapabilitySet;
import com.amazon.digitalmusicxp.models.QueueEntityCapabilitySet;
import com.amazon.digitalmusicxp.types.ConnectResponse;
import com.amazon.digitalmusicxp.types.ConnectionInfoResponse;
import com.amazon.digitalmusicxp.types.DefaultQueueCapabilityIds;
import com.amazon.digitalmusicxp.types.EntityReference;
import com.amazon.digitalmusicxp.types.EntityResponseTuple;
import com.amazon.digitalmusicxp.types.GenericQueueEntity;
import com.amazon.digitalmusicxp.types.GetNextEntityResponse;
import com.amazon.digitalmusicxp.types.GetQueueViewResponse;
import com.amazon.digitalmusicxp.types.InitiateQueueRequest;
import com.amazon.digitalmusicxp.types.InitiateQueueResponse;
import com.amazon.digitalmusicxp.types.ParentalControls;
import com.amazon.digitalmusicxp.types.QueueMetadata;
import com.amazon.digitalmusicxp.types.QueueSeed;
import com.amazon.digitalmusicxp.types.Setting;
import com.amazon.mp3.amplifyqueue.api.ConnectGraphQLRequest;
import com.amazon.mp3.amplifyqueue.api.GetNextEntityGraphQLRequest;
import com.amazon.mp3.amplifyqueue.api.GetQueueViewGraphQLRequest;
import com.amazon.mp3.amplifyqueue.api.InitiateQueueGraphQLRequest;
import com.amazon.mp3.amplifyqueue.api.login.CloudQueueAuthDataClient;
import com.amazon.mp3.amplifyqueue.api.login.FunctionAuthProviderImpl;
import com.amazon.mp3.amplifyqueue.converters.InputsTransformerKt;
import com.amazon.mp3.amplifyqueue.converters.ModelsTransformerKt;
import com.amazon.mp3.amplifyqueue.model.AmplifyModelProvider;
import com.amazon.mp3.amplifyqueue.model.ConnectResponseData;
import com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings;
import com.amazon.mp3.amplifyqueue.model.DevicePlaybackState;
import com.amazon.mp3.amplifyqueue.model.GetNextEntityResponseData;
import com.amazon.mp3.amplifyqueue.model.GetQueueViewResponseData;
import com.amazon.mp3.amplifyqueue.model.InitiateQueueResponseData;
import com.amazon.mp3.amplifyqueue.model.ProposedDevicePlaybackState;
import com.amazon.mp3.amplifyqueue.model.Queue;
import com.amazon.mp3.amplifyqueue.model.QueueCapabilitySetV2;
import com.amazon.mp3.amplifyqueue.model.QueueEntityMetadata;
import com.amazon.mp3.amplifyqueue.model.QueueInteractionPatternEnum;
import com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice;
import com.amazon.mp3.amplifyqueue.utils.AmplifyConfigurationUtil;
import com.amazon.mp3.amplifyqueue.utils.AmplifyDatastoreUtil;
import com.amazon.mp3.amplifyqueue.utils.AmplifyModelUtil;
import com.amazon.mp3.amplifyqueue.utils.CQEThrottleUtil;
import com.amazon.mp3.amplifyqueue.utils.MetricsUtil;
import com.amazon.mp3.amplifyqueue.utils.RetryWithDelay;
import com.amazon.mp3.amplifyqueue.utils.RetryWithExponentialBackoff;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.okhttp3.AMOkHttpClient;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.metrics.event.FlexWrapper;
import com.amazon.music.platform.metrics.event.LatencyWrapper;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.platform.providers.ProfileProvider;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.aws.ApiAuthProviders;
import com.amplifyframework.api.aws.AuthorizationType;
import com.amplifyframework.api.aws.OkHttpConfigurator;
import com.amplifyframework.api.aws.sigv4.FunctionAuthProvider;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.AmplifyConfiguration;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.SchemaRegistry;
import com.amplifyframework.core.model.query.QueryOptions;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.core.model.query.predicate.QueryPredicateGroup;
import com.amplifyframework.core.model.query.predicate.QueryPredicateOperation;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.DataStoreConfiguration;
import com.amplifyframework.datastore.DataStoreConflictHandler;
import com.amplifyframework.datastore.DataStoreErrorHandler;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.DataStoreSyncExpression;
import com.amplifyframework.logging.AndroidLoggingPlugin;
import com.amplifyframework.logging.LogLevel;
import com.amplifyframework.rx.RxAmplify;
import com.amplifyframework.rx.RxDataStoreCategoryBehavior;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import okhttp3.OkHttpClient;

/* JADX INFO: compiled from: AmplifyClient.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000È\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\b*\u0002\u008b\u0001\u0018\u0000 \u0091\u00012\u00020\u0001:\u0002\u0091\u0001BN\b\u0012\u0012\u0006\u0010z\u001a\u00020y\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001b\u001a\u00020\u001a\u0012\u0006\u0010\u001c\u001a\u00020\u001a\u0012\u0007\u0010\u008e\u0001\u001a\u00020\u001a\u0012\u000e\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u001d\u0012\b\u0010\u0085\u0001\u001a\u00030\u0084\u0001¢\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\bH\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\fH\u0002J1\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015\"\b\b\u0000\u0010\u0011*\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0014\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J>\u0010\"\u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001a2\u000e\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u001d2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001a0\u001fH\u0002J\u0014\u0010'\u001a\u00020&2\n\u0010%\u001a\u00060#j\u0002`$H\u0002J \u0010-\u001a\b\u0012\u0004\u0012\u00020,0\u00042\u0006\u0010)\u001a\u00020(2\b\u0010+\u001a\u0004\u0018\u00010*H\u0002J\u0018\u0010/\u001a\b\u0012\u0004\u0012\u00020,0\u00042\b\u0010.\u001a\u0004\u0018\u00010\u001aH\u0002J\u0016\u00100\u001a\b\u0012\u0004\u0012\u00020,0\u00042\u0006\u0010)\u001a\u00020(H\u0002J*\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020206052\f\u00103\u001a\b\u0012\u0004\u0012\u000202012\u0006\u00104\u001a\u00020\u001aH\u0002J*\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020206052\f\u00103\u001a\b\u0012\u0004\u0012\u000202012\u0006\u00104\u001a\u00020\u001aH\u0002J\n\u0010:\u001a\u0004\u0018\u000109H\u0016J\u001c\u0010=\u001a\b\u0012\u0004\u0012\u00020<0\u00042\u0006\u0010;\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aJ\u0016\u0010@\u001a\b\u0012\u0004\u0012\u00020?0\u00042\u0006\u0010>\u001a\u00020\u001aH\u0016J\u0016\u0010C\u001a\b\u0012\u0004\u0012\u00020B0\u00042\u0006\u0010A\u001a\u00020\u001aH\u0016J\u0016\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010>\u001a\u00020\u001aH\u0016J\"\u0010H\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020G0E0\u00042\f\u0010F\u001a\b\u0012\u0004\u0012\u00020\u001a0EH\u0016J\u001e\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010>\u001a\u00020\u001a2\u0006\u0010I\u001a\u00020\u001aH\u0016JJ\u0010Q\u001a\b\u0012\u0004\u0012\u00020P0\u00042\u0006\u0010;\u001a\u00020\u001a2\u0006\u0010>\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001a2\b\u0010K\u001a\u0004\u0018\u00010\u001a2\u0006\u0010M\u001a\u00020L2\b\u0010O\u001a\u0004\u0018\u00010NH\u0016J\u0016\u0010U\u001a\b\u0012\u0004\u0012\u00020T0\u00042\u0006\u0010S\u001a\u00020RH\u0016J\u0016\u0010Y\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010X0\u00042\u0006\u0010W\u001a\u00020VJN\u0010]\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020X0E0\u00042\u0006\u0010>\u001a\u00020\u001a2\u0006\u0010;\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010Z\u001a\u00020\u001a2\u0006\u0010\\\u001a\u00020[2\b\u0010O\u001a\u0004\u0018\u00010NH\u0016J\u000e\u0010_\u001a\u00020&2\u0006\u0010^\u001a\u00020<J\u0016\u0010`\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0016\u0010a\u001a\b\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010b\u001a\u00020&2\u0006\u0010>\u001a\u00020\u001aH\u0016J\u0010\u0010c\u001a\u00020&2\u0006\u0010>\u001a\u00020\u001aH\u0016J\u0016\u0010e\u001a\b\u0012\u0004\u0012\u00020?0\u00042\u0006\u0010d\u001a\u00020?H\u0016J\u0016\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010f\u001a\u00020\u0006H\u0016J\b\u0010h\u001a\u00020&H\u0016J\u000e\u0010j\u001a\b\u0012\u0004\u0012\u00020i0\u0004H\u0016J*\u0010k\u001a\b\u0012\u0004\u0012\u00020,0\u00042\u0006\u0010)\u001a\u00020(2\b\u0010+\u001a\u0004\u0018\u00010*2\b\u0010.\u001a\u0004\u0018\u00010\u001aH\u0016J\u001e\u0010m\u001a\b\u0012\u0004\u0012\u00020l0\u00042\u0006\u0010)\u001a\u00020(2\u0006\u0010.\u001a\u00020\u001aH\u0016J\u000f\u0010o\u001a\u00020nH\u0016¢\u0006\u0004\bo\u0010pJ\u000f\u0010q\u001a\u00020nH\u0016¢\u0006\u0004\bq\u0010pJ\n\u0010r\u001a\u0004\u0018\u00010\u001aH\u0016J\u000e\u0010v\u001a\u00060tj\u0002`u*\u00020sJ\u000e\u0010x\u001a\u00020&2\u0006\u0010w\u001a\u00020BR\u0016\u0010z\u001a\u00020y8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bz\u0010{R\u0016\u0010}\u001a\u00020|8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b}\u0010~R\u0019\u0010\u007f\u001a\u0004\u0018\u0001098\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u007f\u0010\u0080\u0001R\u001b\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0081\u0001\u0010\u0082\u0001R\u0017\u0010\u0083\u0001\u001a\u00020\u001a8\u0002X\u0082D¢\u0006\b\n\u0006\b\u0083\u0001\u0010\u0082\u0001R*\u0010\u0085\u0001\u001a\u00030\u0084\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b\u0085\u0001\u0010\u0086\u0001\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001\"\u0006\b\u0089\u0001\u0010\u008a\u0001R\u0018\u0010\u008c\u0001\u001a\u00030\u008b\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008c\u0001\u0010\u008d\u0001¨\u0006\u0092\u0001"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/AmplifyClient;", "Lcom/amazon/digitalmusicxp/clients/CloudQueueClient;", "Lcom/amazon/digitalmusicxp/models/Queue;", "queue", "Lcom/amazon/digitalmusicxp/callbacks/Outcome;", "createNewQueueHelper", "Lcom/amazon/digitalmusicxp/models/QueueSequenceSlice;", "queueSequenceSlice", "Lcom/amazon/mp3/amplifyqueue/model/QueueSequenceSlice;", "createNewQueueSequenceSliceHelper", "convertedQueueSequenceSlice", "updateQueueSequencerHelper", "Lcom/amplifyframework/api/aws/AuthorizationType;", "authType", "Lcom/amplifyframework/core/AmplifyConfiguration;", "initConfigurationFromCloud", "Lcom/amplifyframework/core/model/Model;", "T", "", "it", "data", "Lio/reactivex/rxjava3/core/Observable;", "retrySaveDataHandler", "(Ljava/lang/Throwable;Lcom/amplifyframework/core/model/Model;)Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/digitalmusicxp/CustomerInfoProvider;", "customerInfoProvider", "", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE, "Lkotlin/Function0;", "accessTokenProvider", "Lcom/amazon/digitalmusicxp/callbacks/ChangeStateResult;", "callback", "Lcom/amplifyframework/api/aws/sigv4/FunctionAuthProvider;", "createFunctionProvider", "Ljava/lang/Exception;", "Lkotlin/Exception;", "e", "", "trackUncategorizedException", "Lcom/amazon/digitalmusicxp/enums/ServiceTierEnum;", "serviceTier", "Lcom/amazon/digitalmusicxp/enums/QueueSeedTypeEnum;", "queueSeedType", "Lcom/amazon/digitalmusicxp/models/QueueCapabilitySet;", "getQueueCapabilitySetV1", "queueInteractionPattern", "getQueueCapabilitySetV2", "getQueueCapabilitySetByServiceTier", "Lcom/amplifyframework/api/graphql/GraphQLRequest;", "", "graphQLRequest", "eventName", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amplifyframework/api/graphql/GraphQLResponse;", "getGraphQLResponseSingleWithExponentialBackoff", "getGraphQLResponseSingle", "Lcom/amazon/digitalmusicxp/types/ConnectionInfoResponse;", "getConnectionInfo", "customerId", "Lcom/amazon/digitalmusicxp/models/CustomerDeviceSettings;", "getCustomerSettingsById", "queueId", "Lcom/amazon/digitalmusicxp/models/DevicePlaybackState;", "getDevicePlaybackState", "metricsContextId", "Lcom/amazon/digitalmusicxp/models/MetricsContext;", "getMetricsContextById", "getQueueById", "", "queueEntityIds", "Lcom/amazon/digitalmusicxp/models/QueueEntityMetadata;", "getQueueEntityById", "sequenceSliceName", "getQueueSequenceSliceById", "currentEntityReferenceId", "Lcom/amazon/digitalmusicxp/enums/QueueViewDirectionEnum;", "direction", "Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;", "parentalControls", "Lcom/amazon/digitalmusicxp/types/GetQueueViewResponse;", "getQueueView", "Lcom/amazon/digitalmusicxp/types/InitiateQueueRequest;", "initiateQueueInput", "Lcom/amazon/digitalmusicxp/types/InitiateQueueResponse;", "initiateQueue", "Lcom/amazon/digitalmusicxp/inputs/GetNextEntityInput;", "getNextEntityInput", "Lcom/amazon/digitalmusicxp/types/GenericQueueEntity;", "getNextEntity", "lastEntityReferenceId", "", "isUserInit", "getNextTracks", "customerDeviceSettings", "createCustomerDeviceSettings", "createNewQueue", "createNewQueueSequenceSlice", "deleteQueueById", "deleteQueueSequenceSliceById", "newState", "changeDeviceState", "newSequenceSlice", "changeQueueSequenceSlice", "clearDataStore", "Lcom/amazon/digitalmusicxp/types/ConnectResponse;", "connect", "getQueueCapabilitySet", "Lcom/amazon/digitalmusicxp/models/QueueEntityCapabilitySet;", "getQueueEntityCapabilitySet", "", "getSettingMaxEntitiesPerQueueSequenceSlice", "()Ljava/lang/Integer;", "getSettingQueueTtlInSeconds", "getSettingDefaultQueueEntityCapabilitiesId", "Lcom/amplifyframework/api/graphql/GraphQLResponse$Error;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "toRuntimeException", "metricsContext", "createMetricsContext", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/mp3/amplifyqueue/api/login/CloudQueueAuthDataClient;", "authApiClient", "Lcom/amazon/mp3/amplifyqueue/api/login/CloudQueueAuthDataClient;", "connectionInfo", "Lcom/amazon/digitalmusicxp/types/ConnectionInfoResponse;", "activeQueueId", "Ljava/lang/String;", "defaultQueueId", "Lcom/amazon/music/platform/providers/MetricsProvider;", "metricsProvider", "Lcom/amazon/music/platform/providers/MetricsProvider;", "getMetricsProvider", "()Lcom/amazon/music/platform/providers/MetricsProvider;", "setMetricsProvider", "(Lcom/amazon/music/platform/providers/MetricsProvider;)V", "com/amazon/mp3/amplifyqueue/AmplifyClient$onAccessTokenChangedCallback$1", "onAccessTokenChangedCallback", "Lcom/amazon/mp3/amplifyqueue/AmplifyClient$onAccessTokenChangedCallback$1;", "marketPlace", "<init>", "(Landroid/content/Context;Lcom/amazon/digitalmusicxp/CustomerInfoProvider;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lcom/amazon/music/platform/providers/MetricsProvider;)V", "Companion", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0})
public final class AmplifyClient implements CloudQueueClient {
    private static volatile AmplifyClient INSTANCE;
    private String activeQueueId;
    private CloudQueueAuthDataClient authApiClient;
    private ConnectionInfoResponse connectionInfo;
    private Context context;
    private final String defaultQueueId;
    private MetricsProvider metricsProvider;
    private final AmplifyClient$onAccessTokenChangedCallback$1 onAccessTokenChangedCallback;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = AmplifyClient.class.getSimpleName();

    /* JADX INFO: compiled from: AmplifyClient.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[GetNextQueueEntityResponseStatusEnum.values().length];
            iArr[GetNextQueueEntityResponseStatusEnum.SUCCESS.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ServiceTierEnum.values().length];
            iArr2[ServiceTierEnum.UNLIMITED.ordinal()] = 1;
            iArr2[ServiceTierEnum.PRIME.ordinal()] = 2;
            iArr2[ServiceTierEnum.LIBRARY.ordinal()] = 3;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public /* synthetic */ AmplifyClient(Context context, CustomerInfoProvider customerInfoProvider, String str, String str2, String str3, Function0 function0, MetricsProvider metricsProvider, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, customerInfoProvider, str, str2, str3, function0, metricsProvider);
    }

    @JvmStatic
    public static final AmplifyClient getInstance(Context context, CustomerInfoProvider customerInfoProvider, String str, String str2, String str3) {
        return INSTANCE.getInstance(context, customerInfoProvider, str, str2, str3);
    }

    @JvmStatic
    public static final AmplifyClient getInstance(Context context, CustomerInfoProvider customerInfoProvider, String str, String str2, String str3, ProfileProvider profileProvider, MetricsProvider metricsProvider) {
        return INSTANCE.getInstance(context, customerInfoProvider, str, str2, str3, profileProvider, metricsProvider);
    }

    public final void createMetricsContext(MetricsContext metricsContext) {
        Intrinsics.checkNotNullParameter(metricsContext, "metricsContext");
    }

    /* JADX INFO: compiled from: AmplifyClient.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\nH\u0007J@\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0019\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/AmplifyClient$Companion;", "", "()V", "DESIRED_NUMBER_OF_TRACKS", "", "INSTANCE", "Lcom/amazon/mp3/amplifyqueue/AmplifyClient;", "MAX_RETRIES", "RETRY_INTERVAL_MILLIS", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "defaultMaxEntitiesPerQueueSequenceSlice", "defaultQueueTtlInSeconds", "getInstance", "context", "Landroid/content/Context;", "customerInfoProvider", "Lcom/amazon/digitalmusicxp/CustomerInfoProvider;", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE, "marketPlace", "profileProvider", "Lcom/amazon/music/platform/providers/ProfileProvider;", "metricsProvider", "Lcom/amazon/music/platform/providers/MetricsProvider;", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return AmplifyClient.TAG;
        }

        @JvmStatic
        public final AmplifyClient getInstance(Context context, CustomerInfoProvider customerInfoProvider, String deviceId, String deviceType, String marketPlace) throws Exception {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(customerInfoProvider, "customerInfoProvider");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            Intrinsics.checkNotNullParameter(deviceType, "deviceType");
            Intrinsics.checkNotNullParameter(marketPlace, "marketPlace");
            Providers providers = Providers.INSTANCE;
            AtomicMap<String, InterfaceProvider> providerInstances = providers.getProviderInstances();
            String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ProfileProvider.class));
            Intrinsics.checkNotNull(originalClassName);
            InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
            if (!(interfaceProvider instanceof ProfileProvider)) {
                interfaceProvider = null;
            }
            ProfileProvider profileProvider = (ProfileProvider) interfaceProvider;
            if (profileProvider != null) {
                AtomicMap<String, InterfaceProvider> providerInstances2 = providers.getProviderInstances();
                String originalClassName2 = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(MetricsProvider.class));
                Intrinsics.checkNotNull(originalClassName2);
                InterfaceProvider interfaceProvider2 = providerInstances2.get(originalClassName2);
                MetricsProvider metricsProvider = (MetricsProvider) (interfaceProvider2 instanceof MetricsProvider ? interfaceProvider2 : null);
                if (metricsProvider == null) {
                    throw new Exception("metrics provider not initialized");
                }
                return getInstance(context, customerInfoProvider, deviceId, deviceType, marketPlace, profileProvider, metricsProvider);
            }
            throw new Exception("profile provider not initialized");
        }

        @JvmStatic
        public final AmplifyClient getInstance(Context context, CustomerInfoProvider customerInfoProvider, String deviceId, String deviceType, String marketPlace, final ProfileProvider profileProvider, MetricsProvider metricsProvider) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(customerInfoProvider, "customerInfoProvider");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            Intrinsics.checkNotNullParameter(deviceType, "deviceType");
            Intrinsics.checkNotNullParameter(marketPlace, "marketPlace");
            Intrinsics.checkNotNullParameter(profileProvider, "profileProvider");
            Intrinsics.checkNotNullParameter(metricsProvider, "metricsProvider");
            AmplifyClient amplifyClient = AmplifyClient.INSTANCE;
            if (amplifyClient == null) {
                amplifyClient = new AmplifyClient(context, customerInfoProvider, deviceId, deviceType, marketPlace, new Function0<String>() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$Companion$getInstance$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return profileProvider.getAccessTokenBlocking();
                    }
                }, metricsProvider, null);
            }
            AmplifyClient.INSTANCE = amplifyClient;
            AmplifyClient amplifyClient2 = AmplifyClient.INSTANCE;
            Intrinsics.checkNotNull(amplifyClient2);
            return amplifyClient2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [com.amazon.digitalmusicxp.callbacks.ChangeStateResult, com.amazon.mp3.amplifyqueue.AmplifyClient$onAccessTokenChangedCallback$1] */
    private AmplifyClient(Context context, CustomerInfoProvider customerInfoProvider, final String str, String str2, String str3, Function0<String> function0, MetricsProvider metricsProvider) throws AmplifyException {
        this.defaultQueueId = "00000000-0000-0000-0000-000000000000";
        ?? r10 = new ChangeStateResult<String>() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$onAccessTokenChangedCallback$1
            @Override // com.amazon.digitalmusicxp.callbacks.ChangeStateResult
            public void onChangedSuccess(String newState) {
                Intrinsics.checkNotNullParameter(newState, "newState");
                Log.i(AmplifyClient.INSTANCE.getTAG(), Intrinsics.stringPlus("access token changed call connect success=", Boolean.valueOf(this.this$0.connect() instanceof Outcome.Success)));
            }

            @Override // com.amazon.digitalmusicxp.callbacks.ChangeStateResult
            public void onChangeFailed(Exception e) {
                Intrinsics.checkNotNullParameter(e, "e");
                Log.e(AmplifyClient.INSTANCE.getTAG(), "access token changed failed", e);
            }
        };
        this.onAccessTokenChangedCallback = r10;
        this.context = context;
        final String customerId = customerInfoProvider.getCustomerId();
        if (customerId == null) {
            throw new NoDataException("customerId");
        }
        this.authApiClient = new CloudQueueAuthDataClient(customerId, str, str2, str3, function0);
        MetricsUtil.INSTANCE.setMetricsProvider(metricsProvider);
        this.metricsProvider = metricsProvider;
        if (Amplify.API.getPlugins().size() == 0) {
            DataStoreConfiguration dataStoreConfigurationBuild = DataStoreConfiguration.builder().conflictHandler(DataStoreConflictHandler.alwaysApplyRemote()).errorHandler(new DataStoreErrorHandler() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda0
                @Override // com.amplifyframework.core.Consumer
                public final void accept(DataStoreException dataStoreException) {
                    this.f$0.trackUncategorizedException(dataStoreException);
                }
            }).syncMaxRecords(100000).syncPageSize(1000).syncInterval(1L, TimeUnit.HOURS).syncExpression(Queue.class, new DataStoreSyncExpression() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda1
                @Override // com.amplifyframework.datastore.DataStoreSyncExpression
                public final QueryPredicate resolvePredicate() {
                    return AmplifyClient.m176_init_$lambda1(customerId, this);
                }
            }).syncExpression(QueueSequenceSlice.class, new DataStoreSyncExpression() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda2
                @Override // com.amplifyframework.datastore.DataStoreSyncExpression
                public final QueryPredicate resolvePredicate() {
                    return AmplifyClient.m177_init_$lambda2(customerId, this);
                }
            }).syncExpression(CustomerDeviceSettings.class, new DataStoreSyncExpression() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda3
                @Override // com.amplifyframework.datastore.DataStoreSyncExpression
                public final QueryPredicate resolvePredicate() {
                    return AmplifyClient.m178_init_$lambda3(customerId, str);
                }
            }).syncExpression(DevicePlaybackState.class, new DataStoreSyncExpression() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda4
                @Override // com.amplifyframework.datastore.DataStoreSyncExpression
                public final QueryPredicate resolvePredicate() {
                    return AmplifyClient.m179_init_$lambda4(customerId, str);
                }
            }).syncExpression(com.amazon.mp3.amplifyqueue.model.MetricsContext.class, new DataStoreSyncExpression() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda5
                @Override // com.amplifyframework.datastore.DataStoreSyncExpression
                public final QueryPredicate resolvePredicate() {
                    return AmplifyClient.m180_init_$lambda5(customerId, this);
                }
            }).syncExpression(QueueEntityMetadata.class, new DataStoreSyncExpression() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda6
                @Override // com.amplifyframework.datastore.DataStoreSyncExpression
                public final QueryPredicate resolvePredicate() {
                    return AmplifyClient.m181_init_$lambda6(customerId, this);
                }
            }).syncExpression(ProposedDevicePlaybackState.class, new DataStoreSyncExpression() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda7
                @Override // com.amplifyframework.datastore.DataStoreSyncExpression
                public final QueryPredicate resolvePredicate() {
                    return AmplifyClient.m182_init_$lambda7(customerId, str);
                }
            }).build();
            Intrinsics.checkNotNullExpressionValue(dataStoreConfigurationBuild, "builder()\n              …                 .build()");
            AmplifyModelProvider amplifyModelProvider = AmplifyModelProvider.getInstance();
            SchemaRegistry schemaRegistryInstance = SchemaRegistry.instance();
            Intrinsics.checkNotNullExpressionValue(schemaRegistryInstance, "instance()");
            schemaRegistryInstance.register(amplifyModelProvider.models());
            RxAmplify.addPlugin(new AndroidLoggingPlugin(LogLevel.ERROR));
            AWSDataStorePlugin aWSDataStorePluginBuild = AWSDataStorePlugin.builder().modelProvider(amplifyModelProvider).schemaRegistry(schemaRegistryInstance).dataStoreConfiguration(dataStoreConfigurationBuild).build();
            AmplifyConfiguration amplifyConfigurationInitConfigurationFromCloud = initConfigurationFromCloud(AuthorizationType.AWS_LAMBDA);
            if (amplifyConfigurationInitConfigurationFromCloud == null) {
                throw new NoDataException("configuration");
            }
            RxAmplify.addPlugin(aWSDataStorePluginBuild);
            AWSApiPlugin aWSApiPluginBuild = AWSApiPlugin.builder().apiAuthProviders(ApiAuthProviders.builder().functionAuthProvider(createFunctionProvider(customerInfoProvider, str, str2, function0, r10)).build()).configureClient(AmplifyConfigurationUtil.INSTANCE.getAPP_NAME(), new OkHttpConfigurator() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda8
                @Override // com.amplifyframework.api.aws.OkHttpConfigurator
                public final void applyConfiguration(OkHttpClient.Builder builder) {
                    AMOkHttpClient.unifyOkHttpClientBuilder(builder);
                }
            }).build();
            Intrinsics.checkNotNullExpressionValue(aWSApiPluginBuild, "builder()\n              …                 .build()");
            RxAmplify.addPlugin(aWSApiPluginBuild);
            RxAmplify.configure(amplifyConfigurationInitConfigurationFromCloud, context);
            AmplifyDatastoreUtil amplifyDatastoreUtil = AmplifyDatastoreUtil.INSTANCE;
            amplifyDatastoreUtil.startDataStore(this.metricsProvider);
            amplifyDatastoreUtil.startHubListener(this.metricsProvider);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-1, reason: not valid java name */
    public static final QueryPredicate m176_init_$lambda1(String customerId, AmplifyClient this$0) {
        Intrinsics.checkNotNullParameter(customerId, "$customerId");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        QueryPredicateOperation<Object> queryPredicateOperationEq = Queue.CUSTOMER_ID.eq(customerId);
        QueryField queryField = Queue.ID;
        String str = this$0.activeQueueId;
        if (str == null) {
            str = this$0.defaultQueueId;
        }
        return queryPredicateOperationEq.and((QueryPredicate) queryField.eq(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-2, reason: not valid java name */
    public static final QueryPredicate m177_init_$lambda2(String customerId, AmplifyClient this$0) {
        Intrinsics.checkNotNullParameter(customerId, "$customerId");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        QueryPredicateOperation<Object> queryPredicateOperationEq = QueueSequenceSlice.CUSTOMER_ID.eq(customerId);
        QueryField queryField = QueueSequenceSlice.QUEUE_ID;
        String str = this$0.activeQueueId;
        if (str == null) {
            str = this$0.defaultQueueId;
        }
        return queryPredicateOperationEq.and((QueryPredicate) queryField.beginsWith(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-3, reason: not valid java name */
    public static final QueryPredicate m178_init_$lambda3(String customerId, String deviceId) {
        Intrinsics.checkNotNullParameter(customerId, "$customerId");
        Intrinsics.checkNotNullParameter(deviceId, "$deviceId");
        return CustomerDeviceSettings.CUSTOMER_ID.eq(customerId).and((QueryPredicate) CustomerDeviceSettings.DEVICE_ID.eq(deviceId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-4, reason: not valid java name */
    public static final QueryPredicate m179_init_$lambda4(String customerId, String deviceId) {
        Intrinsics.checkNotNullParameter(customerId, "$customerId");
        Intrinsics.checkNotNullParameter(deviceId, "$deviceId");
        return DevicePlaybackState.CUSTOMER_ID.eq(customerId).and((QueryPredicate) DevicePlaybackState.DEVICE_ID.eq(deviceId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-5, reason: not valid java name */
    public static final QueryPredicate m180_init_$lambda5(String customerId, AmplifyClient this$0) {
        Intrinsics.checkNotNullParameter(customerId, "$customerId");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        QueryPredicateOperation<Object> queryPredicateOperationEq = com.amazon.mp3.amplifyqueue.model.MetricsContext.CUSTOMER_ID.eq(customerId);
        QueryField queryField = com.amazon.mp3.amplifyqueue.model.MetricsContext.QUEUE_ID;
        String str = this$0.activeQueueId;
        if (str == null) {
            str = this$0.defaultQueueId;
        }
        return queryPredicateOperationEq.and((QueryPredicate) queryField.eq(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-6, reason: not valid java name */
    public static final QueryPredicate m181_init_$lambda6(String customerId, AmplifyClient this$0) {
        Intrinsics.checkNotNullParameter(customerId, "$customerId");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        QueryPredicateOperation<Object> queryPredicateOperationEq = QueueEntityMetadata.CUSTOMER_ID.eq(customerId);
        QueryField queryField = QueueEntityMetadata.QUEUE_ID;
        String str = this$0.activeQueueId;
        if (str == null) {
            str = this$0.defaultQueueId;
        }
        return queryPredicateOperationEq.and((QueryPredicate) queryField.eq(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-7, reason: not valid java name */
    public static final QueryPredicate m182_init_$lambda7(String customerId, String deviceId) {
        Intrinsics.checkNotNullParameter(customerId, "$customerId");
        Intrinsics.checkNotNullParameter(deviceId, "$deviceId");
        return ProposedDevicePlaybackState.CUSTOMER_ID.eq(customerId).and((QueryPredicate) ProposedDevicePlaybackState.DEVICE_ID.eq(deviceId));
    }

    public ConnectionInfoResponse getConnectionInfo() {
        if (this.connectionInfo == null) {
            try {
                ConnectionInfoResponse connectionInfoResponse = this.authApiClient.getConnectionInfoResponse();
                this.connectionInfo = connectionInfoResponse;
                Log.i(TAG, Intrinsics.stringPlus("appSyncEndpoint ", connectionInfoResponse == null ? null : connectionInfoResponse.getAppSyncEndpoint()));
            } catch (Exception e) {
                Log.e(TAG, Intrinsics.stringPlus("getConnectionInfoResponse failed with error: ", e));
                CQEThrottleUtil.INSTANCE.authThrottle(new Function0<Unit>() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient.getConnectionInfo.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        MetricsUtil.INSTANCE.trackEvent("cloudQueueAuth", (32 & 2) != 0 ? null : "getConnectionInfo", (32 & 4) != 0 ? null : null, (32 & 8) == 0 ? null : null, (32 & 16) != 0 ? true : AmplifyClient.this.connectionInfo != null, (32 & 32) != 0 ? false : false);
                    }
                });
            }
        }
        return this.connectionInfo;
    }

    public final Outcome<com.amazon.digitalmusicxp.models.CustomerDeviceSettings> getCustomerSettingsById(String customerId, String deviceId) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        try {
            Log.d(TAG, "getCustomerSettingsById start");
            com.amazon.digitalmusicxp.models.CustomerDeviceSettings customerDeviceSettings = (com.amazon.digitalmusicxp.models.CustomerDeviceSettings) RxAmplify.DataStore.query(CustomerDeviceSettings.class, CustomerDeviceSettings.CUSTOMER_ID.eq(customerId).and((QueryPredicate) CustomerDeviceSettings.DEVICE_ID.eq(deviceId))).retryWhen(new RetryWithDelay(3, 7000)).singleOrError().map(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda28
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return AmplifyClient.m200getCustomerSettingsById$lambda8((CustomerDeviceSettings) obj);
                }
            }).subscribeOn(Schedulers.io()).blockingGet();
            if (customerDeviceSettings != null) {
                return new Outcome.Success(customerDeviceSettings);
            }
            return new Outcome.Failure(new RuntimeException("no data return for CustomerDeviceSettings"));
        } catch (Exception unused) {
            return new Outcome.Failure(new RuntimeException("no data return for CustomerDeviceSettings"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getCustomerSettingsById$lambda-8, reason: not valid java name */
    public static final com.amazon.digitalmusicxp.models.CustomerDeviceSettings m200getCustomerSettingsById$lambda8(CustomerDeviceSettings it) {
        Intrinsics.checkNotNullExpressionValue(it, "it");
        return ModelsTransformerKt.toExternal(it);
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<com.amazon.digitalmusicxp.models.DevicePlaybackState> getDevicePlaybackState(String queueId) {
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        try {
            com.amazon.digitalmusicxp.models.DevicePlaybackState devicePlaybackState = (com.amazon.digitalmusicxp.models.DevicePlaybackState) RxAmplify.DataStore.query(DevicePlaybackState.class, DevicePlaybackState.QUEUE_ID.eq(queueId)).retryWhen(new RetryWithDelay(3, 7000)).firstElement().map(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda9
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return AmplifyClient.m201getDevicePlaybackState$lambda9((DevicePlaybackState) obj);
                }
            }).subscribeOn(Schedulers.io()).blockingGet();
            if (devicePlaybackState != null) {
                return new Outcome.Success(devicePlaybackState);
            }
            return new Outcome.Failure(new RuntimeException("no data retur for DevicePlaybackState"));
        } catch (Exception e) {
            MetricsUtil.INSTANCE.trackEvent(MetricsUtil.EventType.EVENT_GET_DEVICE_PLAYBACK_STATE, e);
            return new Outcome.Failure(new RuntimeException("no data return for DevicePlaybackState"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getDevicePlaybackState$lambda-9, reason: not valid java name */
    public static final com.amazon.digitalmusicxp.models.DevicePlaybackState m201getDevicePlaybackState$lambda9(DevicePlaybackState it) {
        Intrinsics.checkNotNullExpressionValue(it, "it");
        return ModelsTransformerKt.toExternal(it);
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<MetricsContext> getMetricsContextById(String metricsContextId) {
        Intrinsics.checkNotNullParameter(metricsContextId, "metricsContextId");
        try {
            MetricsContext metricsContext = (MetricsContext) RxAmplify.DataStore.query(com.amazon.mp3.amplifyqueue.model.MetricsContext.class, com.amazon.mp3.amplifyqueue.model.MetricsContext.ID.eq(metricsContextId)).retryWhen(new RetryWithDelay(3, 7000)).singleOrError().subscribeOn(Schedulers.io()).map(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda45
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return AmplifyClient.m204getMetricsContextById$lambda10((com.amazon.mp3.amplifyqueue.model.MetricsContext) obj);
                }
            }).blockingGet();
            if (metricsContext != null) {
                return new Outcome.Success(metricsContext);
            }
            return new Outcome.Failure(new RuntimeException("no data return for MetricsContext"));
        } catch (Exception e) {
            MetricsUtil.INSTANCE.trackEvent(MetricsUtil.EventType.EVENT_GET_METRICS_CONTEXT_BY_ID, e);
            return new Outcome.Failure(new RuntimeException(new NoDataException(e)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getMetricsContextById$lambda-10, reason: not valid java name */
    public static final MetricsContext m204getMetricsContextById$lambda10(com.amazon.mp3.amplifyqueue.model.MetricsContext it) {
        Intrinsics.checkNotNullExpressionValue(it, "it");
        return ModelsTransformerKt.toExternal(it);
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<com.amazon.digitalmusicxp.models.Queue> getQueueById(String queueId) {
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        try {
            com.amazon.digitalmusicxp.models.Queue queue = (com.amazon.digitalmusicxp.models.Queue) RxAmplify.DataStore.query(Queue.class, Queue.ID.eq(queueId)).retryWhen(new RetryWithDelay(3, 7000)).singleOrError().subscribeOn(Schedulers.io()).map(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda21
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return AmplifyClient.m206getQueueById$lambda11((Queue) obj);
                }
            }).blockingGet();
            if (queue != null) {
                return new Outcome.Success(queue);
            }
            return new Outcome.Failure(new RuntimeException("no data return for Queue"));
        } catch (Exception e) {
            MetricsUtil.INSTANCE.trackEvent(MetricsUtil.EventType.EVENT_GET_QUEUE_BY_ID, e);
            return new Outcome.Failure(new RuntimeException("no data return for Queue"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getQueueById$lambda-11, reason: not valid java name */
    public static final com.amazon.digitalmusicxp.models.Queue m206getQueueById$lambda11(Queue it) {
        Intrinsics.checkNotNullExpressionValue(it, "it");
        return ModelsTransformerKt.toExternal(it);
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<List<com.amazon.digitalmusicxp.models.QueueEntityMetadata>> getQueueEntityById(List<String> queueEntityIds) {
        Intrinsics.checkNotNullParameter(queueEntityIds, "queueEntityIds");
        try {
            List list = (List) Observable.fromIterable(queueEntityIds).concatMap(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda43
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return AmplifyClient.m207getQueueEntityById$lambda12((String) obj);
                }
            }).map(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda44
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return AmplifyClient.m208getQueueEntityById$lambda13((QueueEntityMetadata) obj);
                }
            }).toList().blockingGet();
            if (list != null) {
                return new Outcome.Success(list);
            }
            return new Outcome.Failure(new RuntimeException("no data return for QueueEntityMetadata"));
        } catch (Exception e) {
            MetricsUtil.INSTANCE.trackEvent(MetricsUtil.EventType.EVENT_GET_QUEUE_ENTITY_BY_ID, e);
            return new Outcome.Failure(new RuntimeException("no data return for QueueEntityMetadata"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getQueueEntityById$lambda-12, reason: not valid java name */
    public static final ObservableSource m207getQueueEntityById$lambda12(String str) {
        return RxAmplify.DataStore.query(QueueEntityMetadata.class, QueueEntityMetadata.ID.eq(str)).firstElement().toObservable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getQueueEntityById$lambda-13, reason: not valid java name */
    public static final com.amazon.digitalmusicxp.models.QueueEntityMetadata m208getQueueEntityById$lambda13(QueueEntityMetadata t) {
        Intrinsics.checkNotNullExpressionValue(t, "t");
        return ModelsTransformerKt.toExternal(t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<com.amazon.digitalmusicxp.models.QueueSequenceSlice> getQueueSequenceSliceById(String queueId, String sequenceSliceName) {
        Outcome.Success success;
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(sequenceSliceName, "sequenceSliceName");
        QueryPredicateGroup queryPredicateGroupAnd = QueueSequenceSlice.QUEUE_ID.eq(queueId).and((QueryPredicate) QueueSequenceSlice.SEQUENCE_NAME.eq(sequenceSliceName));
        Intrinsics.checkNotNullExpressionValue(queryPredicateGroupAnd, "QUEUE_ID.eq(queueId)\n   …ME.eq(sequenceSliceName))");
        final ArrayList arrayList = new ArrayList();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        try {
            RxAmplify.DataStore.query(QueueSequenceSlice.class, Where.matches(queryPredicateGroupAnd).sorted(QueueSequenceSlice.SEQUENCE_VERSION.descending(), QueueSequenceSlice.SLICE_ORDINAL.ascending())).subscribeOn(Schedulers.io()).blockingSubscribe(new Consumer() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda22
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    AmplifyClient.m210getQueueSequenceSliceById$lambda15(objectRef2, booleanRef, objectRef, arrayList, (QueueSequenceSlice) obj);
                }
            });
            com.amazon.digitalmusicxp.models.QueueSequenceSlice queueSequenceSlice = (com.amazon.digitalmusicxp.models.QueueSequenceSlice) objectRef.element;
            if (queueSequenceSlice == null) {
                success = null;
            } else {
                success = new Outcome.Success(queueSequenceSlice.copy((383 & 1) != 0 ? queueSequenceSlice.queueId : null, (383 & 2) != 0 ? queueSequenceSlice.customerId : null, (383 & 4) != 0 ? queueSequenceSlice.id : null, (383 & 8) != 0 ? queueSequenceSlice.sequenceName : null, (383 & 16) != 0 ? queueSequenceSlice.sequenceVersion : 0, (383 & 32) != 0 ? queueSequenceSlice.sliceOrdinal : 0, (383 & 64) != 0 ? queueSequenceSlice.isLastSlice : false, (383 & 128) != 0 ? queueSequenceSlice.entityReferences : arrayList, (383 & 256) != 0 ? queueSequenceSlice.createdAt : 0L, (383 & 512) != 0 ? queueSequenceSlice.updatedAt : 0L));
            }
            return success == null ? new Outcome.Failure(new RuntimeException(new NoDataException())) : success;
        } catch (Exception e) {
            MetricsUtil.INSTANCE.trackEvent(MetricsUtil.EventType.EVENT_GET_QUEUE_SEQUENCE_SLICE_BY_ID, e);
            return new Outcome.Failure(new RuntimeException(new NoDataException(e)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v10, types: [T, java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r7v1, types: [T, com.amazon.digitalmusicxp.models.QueueSequenceSlice] */
    /* JADX INFO: renamed from: getQueueSequenceSliceById$lambda-15, reason: not valid java name */
    public static final void m210getQueueSequenceSliceById$lambda15(Ref.ObjectRef highestSequenceVersion, Ref.BooleanRef isLastSlice, Ref.ObjectRef slice, List references, QueueSequenceSlice it) {
        Intrinsics.checkNotNullParameter(highestSequenceVersion, "$highestSequenceVersion");
        Intrinsics.checkNotNullParameter(isLastSlice, "$isLastSlice");
        Intrinsics.checkNotNullParameter(slice, "$slice");
        Intrinsics.checkNotNullParameter(references, "$references");
        if (highestSequenceVersion.element == 0) {
            highestSequenceVersion.element = it.getSequenceVersion();
        }
        if (!Intrinsics.areEqual(it.getSequenceVersion(), highestSequenceVersion.element) || isLastSlice.element) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(it, "it");
        ?? external = ModelsTransformerKt.toExternal(it);
        Log.d(TAG, "getQueueSequenceSliceById version=" + highestSequenceVersion.element + " sliceOrdinal=" + external.getSliceOrdinal());
        if (external.getSliceOrdinal() == 0) {
            slice.element = external;
        }
        references.addAll(external.getEntityReferences());
        isLastSlice.element = external.getIsLastSlice();
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<GetQueueViewResponse> getQueueView(String customerId, String queueId, String deviceId, String deviceType, String currentEntityReferenceId, QueueViewDirectionEnum direction, ParentalControlsInput parentalControls) {
        Outcome<GetQueueViewResponse> failure;
        MetricsProvider metricsProvider;
        LatencyWrapper latencyWrapper;
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(direction, "direction");
        MetricsProvider metricsProvider2 = this.metricsProvider;
        String strName = LatencyTrackingLeg.REQUEST.name();
        PageLoadLatencyCode pageLoadLatencyCode = PageLoadLatencyCode.CLOUD_QUEUE_GET_QUEUE_VIEW;
        metricsProvider2.trackLatency(new LatencyWrapper(strName, pageLoadLatencyCode.name()));
        GraphQLRequest<Object> graphQLRequestInitInput = GetQueueViewGraphQLRequest.INSTANCE.initInput(new GetQueueViewInput(customerId, queueId, new DeviceContextInput(deviceId, deviceType), currentEntityReferenceId, direction, 50, parentalControls), this.context);
        this.metricsProvider.trackLatency(new LatencyWrapper(LatencyTrackingLeg.RESPONSE.name(), pageLoadLatencyCode.name()));
        try {
            if (graphQLRequestInitInput != null) {
                try {
                    Log.i(TAG, "Fetching Queue View");
                    AmplifyConfigurationUtil.Companion companion = AmplifyConfigurationUtil.INSTANCE;
                    MetricsUtil.EventType eventType = MetricsUtil.EventType.EVENT_GET_QUEUE_VIEW;
                    Object response = RxAmplify.API.query(graphQLRequestInitInput).timeout(companion.getTimeoutConfig(eventType.getValue()), TimeUnit.SECONDS).subscribeOn(Schedulers.io()).map(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda12
                        @Override // io.reactivex.rxjava3.functions.Function
                        public final Object apply(Object obj) {
                            return AmplifyClient.m211getQueueView$lambda17(this.f$0, (GraphQLResponse) obj);
                        }
                    }).blockingGet();
                    if (response instanceof GetQueueViewResponse) {
                        Intrinsics.checkNotNullExpressionValue(response, "response");
                        failure = new Outcome.Success<>(response);
                    } else if (response instanceof GraphQLResponse.Error) {
                        Intrinsics.checkNotNullExpressionValue(response, "response");
                        RuntimeException runtimeException = toRuntimeException((GraphQLResponse.Error) response);
                        MetricsUtil.INSTANCE.trackEvent(eventType, runtimeException);
                        failure = new Outcome.Failure<>(runtimeException);
                    } else {
                        failure = new Outcome.Failure(new RuntimeException("unknown error happens for GetQueueView"));
                    }
                    metricsProvider = this.metricsProvider;
                    latencyWrapper = new LatencyWrapper(LatencyTrackingLeg.LOAD_COMPLETE.name(), pageLoadLatencyCode.name());
                } catch (Exception e) {
                    MetricsUtil.INSTANCE.trackEvent(MetricsUtil.EventType.EVENT_GET_QUEUE_VIEW, e);
                    failure = new Outcome.Failure<>(new RuntimeException(Intrinsics.stringPlus("error happens for GetQueueView: ", e.getMessage()), e));
                    metricsProvider = this.metricsProvider;
                    latencyWrapper = new LatencyWrapper(LatencyTrackingLeg.LOAD_COMPLETE.name(), PageLoadLatencyCode.CLOUD_QUEUE_GET_QUEUE_VIEW.name());
                }
                metricsProvider.trackLatency(latencyWrapper);
                return failure;
            }
            this.metricsProvider.trackLatency(new LatencyWrapper(LatencyTrackingLeg.LOAD_COMPLETE.name(), pageLoadLatencyCode.name()));
            return new Outcome.Failure(new RuntimeException("unknown error happens for GetQueueView"));
        } catch (Throwable th) {
            this.metricsProvider.trackLatency(new LatencyWrapper(LatencyTrackingLeg.LOAD_COMPLETE.name(), PageLoadLatencyCode.CLOUD_QUEUE_GET_QUEUE_VIEW.name()));
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getQueueView$lambda-17, reason: not valid java name */
    public static final Object m211getQueueView$lambda17(AmplifyClient this$0, GraphQLResponse graphQLResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.metricsProvider.trackLatency(new LatencyWrapper(LatencyTrackingLeg.PROCESSING.name(), PageLoadLatencyCode.CLOUD_QUEUE_GET_QUEUE_VIEW.name()));
        Intrinsics.checkNotNullExpressionValue(graphQLResponse.getErrors(), "it.errors");
        if (!r3.isEmpty()) {
            Log.e(TAG, Intrinsics.stringPlus("error fetching the queue ", graphQLResponse.getErrors()));
            List<GraphQLResponse.Error> errors = graphQLResponse.getErrors();
            Intrinsics.checkNotNullExpressionValue(errors, "it.errors");
            return CollectionsKt___CollectionsKt.first((List) errors);
        }
        Object data = graphQLResponse.getData();
        if (data == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.amplifyqueue.model.GetQueueViewResponseData");
        }
        com.amazon.mp3.amplifyqueue.model.GetQueueViewResponse queueView = ((GetQueueViewResponseData) data).getQueueView();
        Intrinsics.checkNotNullExpressionValue(queueView, "data.queueView");
        return InputsTransformerKt.toExternal(queueView);
    }

    public Outcome<InitiateQueueResponse> initiateQueue(InitiateQueueRequest initiateQueueInput) {
        Outcome<InitiateQueueResponse> failure;
        MetricsProvider metricsProvider;
        LatencyWrapper latencyWrapper;
        Intrinsics.checkNotNullParameter(initiateQueueInput, "initiateQueueInput");
        MetricsProvider metricsProvider2 = this.metricsProvider;
        String strName = LatencyTrackingLeg.REQUEST.name();
        PageLoadLatencyCode pageLoadLatencyCode = PageLoadLatencyCode.CLOUD_QUEUE_INITIATE_QUEUE;
        metricsProvider2.trackLatency(new LatencyWrapper(strName, pageLoadLatencyCode.name()));
        InitiateQueueInput inputType = InputsTransformerKt.toInputType(initiateQueueInput);
        if (inputType.getCustomerDeviceSettings() == null) {
            return new Outcome.Failure(new RuntimeException("customerDeviceSettings is missing after conversion"));
        }
        GraphQLRequest<Object> graphQLRequestInitInput = InitiateQueueGraphQLRequest.INSTANCE.initInput(inputType, this.context);
        this.metricsProvider.trackLatency(new LatencyWrapper(LatencyTrackingLeg.RESPONSE.name(), pageLoadLatencyCode.name()));
        try {
            if (graphQLRequestInitInput != null) {
                try {
                    MetricsUtil.EventType eventType = MetricsUtil.EventType.EVENT_INITIATE_QUEUE;
                    Object response = getGraphQLResponseSingleWithExponentialBackoff(graphQLRequestInitInput, eventType.getValue()).map(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda10
                        @Override // io.reactivex.rxjava3.functions.Function
                        public final Object apply(Object obj) {
                            return AmplifyClient.m212initiateQueue$lambda18(this.f$0, (GraphQLResponse) obj);
                        }
                    }).blockingGet();
                    if (response instanceof InitiateQueueResponse) {
                        QueueMetadata queueMetadata = ((InitiateQueueResponse) response).getQueueMetadata();
                        this.activeQueueId = queueMetadata == null ? null : queueMetadata.getQueueId();
                        Intrinsics.checkNotNullExpressionValue(response, "response");
                        failure = new Outcome.Success<>(response);
                    } else if (response instanceof GraphQLResponse.Error) {
                        Intrinsics.checkNotNullExpressionValue(response, "response");
                        RuntimeException runtimeException = toRuntimeException((GraphQLResponse.Error) response);
                        MetricsUtil.INSTANCE.trackEvent(eventType, runtimeException);
                        failure = new Outcome.Failure<>(runtimeException);
                    } else {
                        failure = new Outcome.Failure(new RuntimeException("unknown error happens for initiateQueue"));
                    }
                    metricsProvider = this.metricsProvider;
                    latencyWrapper = new LatencyWrapper(LatencyTrackingLeg.LOAD_COMPLETE.name(), pageLoadLatencyCode.name());
                } catch (Exception e) {
                    MetricsUtil.INSTANCE.trackEvent(MetricsUtil.EventType.EVENT_INITIATE_QUEUE, e);
                    failure = new Outcome.Failure<>(new RuntimeException(Intrinsics.stringPlus("error happens for initiateQueue: ", e.getMessage()), e));
                    metricsProvider = this.metricsProvider;
                    latencyWrapper = new LatencyWrapper(LatencyTrackingLeg.LOAD_COMPLETE.name(), PageLoadLatencyCode.CLOUD_QUEUE_INITIATE_QUEUE.name());
                }
                metricsProvider.trackLatency(latencyWrapper);
                return failure;
            }
            this.metricsProvider.trackLatency(new LatencyWrapper(LatencyTrackingLeg.LOAD_COMPLETE.name(), pageLoadLatencyCode.name()));
            return new Outcome.Failure(new RuntimeException("unknown error happens for initiateQueue"));
        } catch (Throwable th) {
            this.metricsProvider.trackLatency(new LatencyWrapper(LatencyTrackingLeg.LOAD_COMPLETE.name(), PageLoadLatencyCode.CLOUD_QUEUE_INITIATE_QUEUE.name()));
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initiateQueue$lambda-18, reason: not valid java name */
    public static final Object m212initiateQueue$lambda18(AmplifyClient this$0, GraphQLResponse graphQLResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.metricsProvider.trackLatency(new LatencyWrapper(LatencyTrackingLeg.PROCESSING.name(), PageLoadLatencyCode.CLOUD_QUEUE_INITIATE_QUEUE.name()));
        Intrinsics.checkNotNullExpressionValue(graphQLResponse.getErrors(), "it.errors");
        if (!r3.isEmpty()) {
            Log.e(TAG, Intrinsics.stringPlus("error initiating the queue ", graphQLResponse.getErrors()));
            List<GraphQLResponse.Error> errors = graphQLResponse.getErrors();
            Intrinsics.checkNotNullExpressionValue(errors, "it.errors");
            return CollectionsKt___CollectionsKt.first((List) errors);
        }
        Object data = graphQLResponse.getData();
        if (data == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.amplifyqueue.model.InitiateQueueResponseData");
        }
        com.amazon.mp3.amplifyqueue.model.InitiateQueueResponse initiateQueue = ((InitiateQueueResponseData) data).getInitiateQueue();
        Intrinsics.checkNotNullExpressionValue(initiateQueue, "data.initiateQueue");
        return InputsTransformerKt.toExternal(initiateQueue);
    }

    public final Outcome<GenericQueueEntity> getNextEntity(GetNextEntityInput getNextEntityInput) {
        Outcome<GenericQueueEntity> failure;
        MetricsProvider metricsProvider;
        LatencyWrapper latencyWrapper;
        Intrinsics.checkNotNullParameter(getNextEntityInput, "getNextEntityInput");
        MetricsProvider metricsProvider2 = this.metricsProvider;
        String strName = LatencyTrackingLeg.REQUEST.name();
        PageLoadLatencyCode pageLoadLatencyCode = PageLoadLatencyCode.CLOUD_QUEUE_GET_NEXT_ENTITIES;
        metricsProvider2.trackLatency(new LatencyWrapper(strName, pageLoadLatencyCode.name()));
        GraphQLRequest<Object> graphQLRequestInitInput = GetNextEntityGraphQLRequest.INSTANCE.initInput(getNextEntityInput, this.context);
        this.metricsProvider.trackLatency(new LatencyWrapper(LatencyTrackingLeg.RESPONSE.name(), pageLoadLatencyCode.name()));
        try {
            if (graphQLRequestInitInput != null) {
                try {
                    Object response = getGraphQLResponseSingle(graphQLRequestInitInput, MetricsUtil.EventType.EVENT_GET_NEXT_TRACKS.getValue()).map(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda14
                        @Override // io.reactivex.rxjava3.functions.Function
                        public final Object apply(Object obj) {
                            return AmplifyClient.m205getNextEntity$lambda19(this.f$0, (GraphQLResponse) obj);
                        }
                    }).blockingGet();
                    if (response instanceof GetNextEntityResponse) {
                        EntityResponseTuple userNext = Intrinsics.areEqual(getNextEntityInput.getIsUserInitiated(), Boolean.TRUE) ? ((GetNextEntityResponse) response).getUserNext() : ((GetNextEntityResponse) response).getNaturalNext();
                        GenericQueueEntity entity = null;
                        GetNextQueueEntityResponseStatusEnum status = userNext == null ? null : userNext.getStatus();
                        if ((status == null ? -1 : WhenMappings.$EnumSwitchMapping$0[status.ordinal()]) == 1) {
                            if (userNext != null) {
                                entity = userNext.getEntity();
                            }
                            failure = new Outcome.Success<>(entity);
                        } else {
                            Log.d(TAG, Intrinsics.stringPlus("getNextEntity response=", response));
                            failure = new Outcome.Failure<>(new RuntimeException(Intrinsics.stringPlus("error happens for getNextEntity status=", status)));
                        }
                    } else if (response instanceof GraphQLResponse.Error) {
                        Intrinsics.checkNotNullExpressionValue(response, "response");
                        failure = new Outcome.Failure(toRuntimeException((GraphQLResponse.Error) response));
                    } else {
                        failure = new Outcome.Failure<>(new RuntimeException("unknown error happens for getNextEntity"));
                    }
                    metricsProvider = this.metricsProvider;
                    latencyWrapper = new LatencyWrapper(LatencyTrackingLeg.LOAD_COMPLETE.name(), pageLoadLatencyCode.name());
                } catch (Exception e) {
                    failure = new Outcome.Failure<>(new RuntimeException(Intrinsics.stringPlus("error happens for getNextEntity: ", e.getMessage()), e));
                    metricsProvider = this.metricsProvider;
                    latencyWrapper = new LatencyWrapper(LatencyTrackingLeg.LOAD_COMPLETE.name(), PageLoadLatencyCode.CLOUD_QUEUE_GET_NEXT_ENTITIES.name());
                }
                metricsProvider.trackLatency(latencyWrapper);
                return failure;
            }
            this.metricsProvider.trackLatency(new LatencyWrapper(LatencyTrackingLeg.LOAD_COMPLETE.name(), pageLoadLatencyCode.name()));
            return new Outcome.Failure(new RuntimeException("unknown error happens for getNextEntity"));
        } catch (Throwable th) {
            this.metricsProvider.trackLatency(new LatencyWrapper(LatencyTrackingLeg.LOAD_COMPLETE.name(), PageLoadLatencyCode.CLOUD_QUEUE_GET_NEXT_ENTITIES.name()));
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getNextEntity$lambda-19, reason: not valid java name */
    public static final Object m205getNextEntity$lambda19(AmplifyClient this$0, GraphQLResponse graphQLResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.metricsProvider.trackLatency(new LatencyWrapper(LatencyTrackingLeg.PROCESSING.name(), PageLoadLatencyCode.CLOUD_QUEUE_GET_NEXT_ENTITIES.name()));
        Intrinsics.checkNotNullExpressionValue(graphQLResponse.getErrors(), "it.errors");
        if (!r3.isEmpty()) {
            Log.e(TAG, Intrinsics.stringPlus("getting next entity ", graphQLResponse.getErrors()));
            List<GraphQLResponse.Error> errors = graphQLResponse.getErrors();
            Intrinsics.checkNotNullExpressionValue(errors, "it.errors");
            return CollectionsKt___CollectionsKt.first((List) errors);
        }
        Object data = graphQLResponse.getData();
        if (data == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.amplifyqueue.model.GetNextEntityResponseData");
        }
        com.amazon.mp3.amplifyqueue.model.GetNextEntityResponse getNextEntity = ((GetNextEntityResponseData) data).getGetNextEntity();
        Intrinsics.checkNotNullExpressionValue(getNextEntity, "data.getNextEntity");
        return InputsTransformerKt.toExternal(getNextEntity);
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<List<GenericQueueEntity>> getNextTracks(String queueId, String customerId, String deviceId, String deviceType, String lastEntityReferenceId, boolean isUserInit, ParentalControlsInput parentalControls) {
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(lastEntityReferenceId, "lastEntityReferenceId");
        try {
            Outcome<com.amazon.digitalmusicxp.models.CustomerDeviceSettings> customerSettingsById = getCustomerSettingsById(customerId, deviceId);
            if (customerSettingsById instanceof Outcome.Success) {
                ParentalControls parentalControls2 = new ParentalControls(parentalControls == null ? false : parentalControls.getHasExplicitLanguage());
                if (!Intrinsics.areEqual(((com.amazon.digitalmusicxp.models.CustomerDeviceSettings) ((Outcome.Success) customerSettingsById).getValue()).getParentalControls(), parentalControls2)) {
                    com.amazon.digitalmusicxp.models.CustomerDeviceSettings customerDeviceSettings = (com.amazon.digitalmusicxp.models.CustomerDeviceSettings) ((Outcome.Success) customerSettingsById).getValue();
                    createCustomerDeviceSettings(customerDeviceSettings.copy((7679 & 1) != 0 ? customerDeviceSettings.customerId : null, (7679 & 2) != 0 ? customerDeviceSettings.deviceId : null, (7679 & 4) != 0 ? customerDeviceSettings.deviceType : null, (7679 & 8) != 0 ? customerDeviceSettings.id : null, (7679 & 16) != 0 ? customerDeviceSettings.sessionId : null, (7679 & 32) != 0 ? customerDeviceSettings.locale : null, (7679 & 64) != 0 ? customerDeviceSettings.languageOfPreference : null, (7679 & 128) != 0 ? customerDeviceSettings.timezone : null, (7679 & 256) != 0 ? customerDeviceSettings.adContext : null, (7679 & 512) != 0 ? customerDeviceSettings.parentalControls : parentalControls2, (7679 & 1024) != 0 ? customerDeviceSettings.supportedFeatures : null, (7679 & 2048) != 0 ? customerDeviceSettings.createdAt : 0L, (7679 & 4096) != 0 ? customerDeviceSettings.updatedAt : 0L));
                }
            }
        } catch (Exception e) {
            MetricsUtil.INSTANCE.trackEvent(MetricsUtil.EventType.EVENT_GET_NEXT_TRACKS, e);
        }
        GetNextEntityInput getNextEntityInput = new GetNextEntityInput(customerId, queueId, new DeviceContextInput(deviceId, deviceType), null, lastEntityReferenceId, Boolean.valueOf(isUserInit), parentalControls, 8, null);
        Outcome<GenericQueueEntity> nextEntity = getNextEntity(getNextEntityInput);
        if (nextEntity instanceof Outcome.Success) {
            Outcome.Success success = (Outcome.Success) nextEntity;
            if (success.getValue() == null) {
                MetricsUtil.INSTANCE.trackEvent(MetricsUtil.EventType.EVENT_GET_NEXT_TRACKS, new Exception(Intrinsics.stringPlus("entity is null, request=", getNextEntityInput)));
            }
            return new Outcome.Success(CollectionsKt__CollectionsKt.listOfNotNull(success.getValue()));
        }
        if (!(nextEntity instanceof Outcome.Failure)) {
            throw new NoWhenBranchMatchedException();
        }
        RuntimeException runtimeException = new RuntimeException(((Outcome.Failure) nextEntity).getCause());
        MetricsUtil.INSTANCE.trackEvent(MetricsUtil.EventType.EVENT_GET_NEXT_TRACKS, runtimeException);
        return new Outcome.Failure(runtimeException);
    }

    public final void createCustomerDeviceSettings(com.amazon.digitalmusicxp.models.CustomerDeviceSettings customerDeviceSettings) {
        Intrinsics.checkNotNullParameter(customerDeviceSettings, "customerDeviceSettings");
        final CustomerDeviceSettings amplify = ModelsTransformerKt.toAmplify(customerDeviceSettings);
        if (amplify != null) {
            try {
                RxDataStoreCategoryBehavior rxDataStoreCategoryBehavior = RxAmplify.DataStore;
                rxDataStoreCategoryBehavior.query(CustomerDeviceSettings.class, CustomerDeviceSettings.CUSTOMER_ID.eq(amplify.getCustomerId()).and((QueryPredicate) CustomerDeviceSettings.DEVICE_ID.eq(amplify.getDeviceId()))).take(1L).map(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda18
                    @Override // io.reactivex.rxjava3.functions.Function
                    public final Object apply(Object obj) {
                        return AmplifyClient.m187createCustomerDeviceSettings$lambda20(amplify, (CustomerDeviceSettings) obj);
                    }
                }).concatMap(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda19
                    @Override // io.reactivex.rxjava3.functions.Function
                    public final Object apply(Object obj) {
                        return AmplifyClient.m188createCustomerDeviceSettings$lambda21((CustomerDeviceSettings) obj);
                    }
                }).switchIfEmpty(rxDataStoreCategoryBehavior.save(amplify).toObservable().defaultIfEmpty(amplify)).onErrorResumeNext(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda20
                    @Override // io.reactivex.rxjava3.functions.Function
                    public final Object apply(Object obj) {
                        return AmplifyClient.m189createCustomerDeviceSettings$lambda22(this.f$0, amplify, (Throwable) obj);
                    }
                }).singleOrError().subscribeOn(Schedulers.io()).subscribe();
            } catch (Exception e) {
                Log.e(TAG, "error to save and update customerDeviceSettings", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createCustomerDeviceSettings$lambda-20, reason: not valid java name */
    public static final CustomerDeviceSettings m187createCustomerDeviceSettings$lambda20(CustomerDeviceSettings convertedCustomerDeviceSettings, CustomerDeviceSettings it) {
        Intrinsics.checkNotNullParameter(convertedCustomerDeviceSettings, "$convertedCustomerDeviceSettings");
        AmplifyModelUtil.Companion companion = AmplifyModelUtil.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        return companion.overrideCustomerDeviceSettings(it, convertedCustomerDeviceSettings);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createCustomerDeviceSettings$lambda-21, reason: not valid java name */
    public static final ObservableSource m188createCustomerDeviceSettings$lambda21(CustomerDeviceSettings customerDeviceSettings) {
        return RxAmplify.DataStore.save(customerDeviceSettings).toObservable().defaultIfEmpty(customerDeviceSettings);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createCustomerDeviceSettings$lambda-22, reason: not valid java name */
    public static final ObservableSource m189createCustomerDeviceSettings$lambda22(AmplifyClient this$0, CustomerDeviceSettings convertedCustomerDeviceSettings, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(convertedCustomerDeviceSettings, "$convertedCustomerDeviceSettings");
        return this$0.retrySaveDataHandler(th, convertedCustomerDeviceSettings);
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<com.amazon.digitalmusicxp.models.Queue> createNewQueue(com.amazon.digitalmusicxp.models.Queue queue) {
        QueueSeed queueSeed;
        QueueSeedTypeEnum queueSeedType;
        Intrinsics.checkNotNullParameter(queue, "queue");
        Outcome<com.amazon.digitalmusicxp.models.Queue> outcomeCreateNewQueueHelper = createNewQueueHelper(queue);
        FlexWrapper.Builder builderWithName = new FlexWrapper.Builder().withName("cloudQueueCreateQueue");
        List<QueueSeed> queueSeeds = queue.getQueueSeeds();
        String string = null;
        if (queueSeeds != null && (queueSeed = (QueueSeed) CollectionsKt___CollectionsKt.firstOrNull((List) queueSeeds)) != null && (queueSeedType = queueSeed.getQueueSeedType()) != null) {
            string = queueSeedType.toString();
        }
        this.metricsProvider.sendMetricsEvent(builderWithName.withStr1(string).withNum1(Float.valueOf(outcomeCreateNewQueueHelper instanceof Outcome.Success ? 1.0f : 0.0f)).withNum2(Float.valueOf(1.0f)).build());
        this.metricsProvider.trackLatency(new LatencyWrapper(LatencyTrackingLeg.LOAD_COMPLETE.name(), PageLoadLatencyCode.CLOUD_QUEUE_CREATE_QUEUE.name()));
        return outcomeCreateNewQueueHelper;
    }

    private final Outcome<com.amazon.digitalmusicxp.models.Queue> createNewQueueHelper(com.amazon.digitalmusicxp.models.Queue queue) {
        MetricsProvider metricsProvider = this.metricsProvider;
        String strName = LatencyTrackingLeg.REQUEST.name();
        PageLoadLatencyCode pageLoadLatencyCode = PageLoadLatencyCode.CLOUD_QUEUE_CREATE_QUEUE;
        metricsProvider.trackLatency(new LatencyWrapper(strName, pageLoadLatencyCode.name()));
        this.activeQueueId = queue.getId();
        final Queue amplify = ModelsTransformerKt.toAmplify(queue);
        this.metricsProvider.trackLatency(new LatencyWrapper(LatencyTrackingLeg.RESPONSE.name(), pageLoadLatencyCode.name()));
        if (amplify != null) {
            try {
                QueryOptions queryOptionsMatchesAndSorts = Where.matchesAndSorts(Queue.CUSTOMER_ID.eq(amplify.getCustomerId()).and((QueryPredicate) Queue.QUEUE_SEEDS.eq(amplify.getQueueSeeds())), CollectionsKt__CollectionsJVMKt.listOf(Queue.CREATED_AT.descending()));
                RxDataStoreCategoryBehavior rxDataStoreCategoryBehavior = RxAmplify.DataStore;
                com.amazon.digitalmusicxp.models.Queue queue2 = (com.amazon.digitalmusicxp.models.Queue) rxDataStoreCategoryBehavior.query(Queue.class, queryOptionsMatchesAndSorts).take(1L).switchIfEmpty(rxDataStoreCategoryBehavior.save(amplify).toObservable().defaultIfEmpty(amplify)).onErrorResumeNext(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda33
                    @Override // io.reactivex.rxjava3.functions.Function
                    public final Object apply(Object obj) {
                        return AmplifyClient.m190createNewQueueHelper$lambda23(this.f$0, amplify, (Throwable) obj);
                    }
                }).map(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda34
                    @Override // io.reactivex.rxjava3.functions.Function
                    public final Object apply(Object obj) {
                        return AmplifyClient.m191createNewQueueHelper$lambda24((Queue) obj);
                    }
                }).blockingFirst();
                this.metricsProvider.trackLatency(new LatencyWrapper(LatencyTrackingLeg.PROCESSING.name(), pageLoadLatencyCode.name()));
                if (queue2 != null) {
                    this.activeQueueId = queue2.getId();
                    return new Outcome.Success(queue2);
                }
                return new Outcome.Failure(new NoDataException());
            } catch (Exception e) {
                MetricsUtil.INSTANCE.trackEvent(MetricsUtil.EventType.EVENT_CREATE_NEW_QUEUE, e);
                return new Outcome.Failure(new NoDataException(e));
            }
        }
        return new Outcome.Failure(new RuntimeException("unable to get data for new Queue"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createNewQueueHelper$lambda-23, reason: not valid java name */
    public static final ObservableSource m190createNewQueueHelper$lambda23(AmplifyClient this$0, Queue convertedQueue, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(convertedQueue, "$convertedQueue");
        return this$0.retrySaveDataHandler(th, convertedQueue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createNewQueueHelper$lambda-24, reason: not valid java name */
    public static final com.amazon.digitalmusicxp.models.Queue m191createNewQueueHelper$lambda24(Queue it) {
        Intrinsics.checkNotNullExpressionValue(it, "it");
        return ModelsTransformerKt.toExternal(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Outcome<QueueSequenceSlice> createNewQueueSequenceSliceHelper(final com.amazon.digitalmusicxp.models.QueueSequenceSlice queueSequenceSlice) {
        String string;
        if (queueSequenceSlice.getSliceOrdinal() == 0) {
            string = queueSequenceSlice.getId();
            if (string == null) {
                string = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
            }
        } else {
            string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "{\n            UUID.rando…t a random uuid\n        }");
        }
        final QueueSequenceSlice amplify = ModelsTransformerKt.toAmplify(queueSequenceSlice.copy((383 & 1) != 0 ? queueSequenceSlice.queueId : null, (383 & 2) != 0 ? queueSequenceSlice.customerId : null, (383 & 4) != 0 ? queueSequenceSlice.id : string, (383 & 8) != 0 ? queueSequenceSlice.sequenceName : null, (383 & 16) != 0 ? queueSequenceSlice.sequenceVersion : 0, (383 & 32) != 0 ? queueSequenceSlice.sliceOrdinal : 0, (383 & 64) != 0 ? queueSequenceSlice.isLastSlice : false, (383 & 128) != 0 ? queueSequenceSlice.entityReferences : null, (383 & 256) != 0 ? queueSequenceSlice.createdAt : 0L, (383 & 512) != 0 ? queueSequenceSlice.updatedAt : 0L));
        if (amplify != null) {
            try {
                try {
                    QueueSequenceSlice queueSequenceSlice2 = (QueueSequenceSlice) RxAmplify.DataStore.query(QueueSequenceSlice.class, QueueSequenceSlice.QUEUE_ID.eq(queueSequenceSlice.getQueueId())).filter(new Predicate() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda36
                        @Override // io.reactivex.rxjava3.functions.Predicate
                        public final boolean test(Object obj) {
                            return AmplifyClient.m192createNewQueueSequenceSliceHelper$lambda26(queueSequenceSlice, (QueueSequenceSlice) obj);
                        }
                    }).take(1L).map(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda37
                        @Override // io.reactivex.rxjava3.functions.Function
                        public final Object apply(Object obj) {
                            return AmplifyClient.m193createNewQueueSequenceSliceHelper$lambda27(amplify, (QueueSequenceSlice) obj);
                        }
                    }).concatMap(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda38
                        @Override // io.reactivex.rxjava3.functions.Function
                        public final Object apply(Object obj) {
                            return AmplifyClient.m194createNewQueueSequenceSliceHelper$lambda28((QueueSequenceSlice) obj);
                        }
                    }).switchIfEmpty(AmplifyDatastoreUtil.INSTANCE.saveAndReturnObservable(amplify)).onErrorResumeNext(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda39
                        @Override // io.reactivex.rxjava3.functions.Function
                        public final Object apply(Object obj) {
                            return AmplifyClient.m195createNewQueueSequenceSliceHelper$lambda29(this.f$0, amplify, (Throwable) obj);
                        }
                    }).singleOrError().blockingGet();
                    if (queueSequenceSlice2 != null) {
                        return new Outcome.Success(queueSequenceSlice2);
                    }
                    return new Outcome.Failure(new NoDataException());
                } catch (Exception e) {
                    e = e;
                    MetricsUtil.INSTANCE.trackEvent(MetricsUtil.EventType.EVENT_CREATE_NEW_QUEUE_SEQUENCE_SLICE, e);
                    return new Outcome.Failure(new NoDataException(e));
                }
            } catch (Exception e2) {
                e = e2;
            }
        } else {
            return new Outcome.Failure(new NoDataException());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createNewQueueSequenceSliceHelper$lambda-26, reason: not valid java name */
    public static final boolean m192createNewQueueSequenceSliceHelper$lambda26(com.amazon.digitalmusicxp.models.QueueSequenceSlice queueSequenceSlice, QueueSequenceSlice queueSequenceSlice2) {
        Intrinsics.checkNotNullParameter(queueSequenceSlice, "$queueSequenceSlice");
        if (Intrinsics.areEqual(queueSequenceSlice2.getSequenceName(), queueSequenceSlice.getSequenceName())) {
            Integer sequenceVersion = queueSequenceSlice2.getSequenceVersion();
            int sequenceVersion2 = queueSequenceSlice.getSequenceVersion();
            if (sequenceVersion != null && sequenceVersion.intValue() == sequenceVersion2) {
                Integer sliceOrdinal = queueSequenceSlice2.getSliceOrdinal();
                int sliceOrdinal2 = queueSequenceSlice.getSliceOrdinal();
                if (sliceOrdinal != null && sliceOrdinal.intValue() == sliceOrdinal2) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createNewQueueSequenceSliceHelper$lambda-27, reason: not valid java name */
    public static final QueueSequenceSlice m193createNewQueueSequenceSliceHelper$lambda27(QueueSequenceSlice convertedQueueSequenceSlice, QueueSequenceSlice it) {
        Intrinsics.checkNotNullParameter(convertedQueueSequenceSlice, "$convertedQueueSequenceSlice");
        AmplifyModelUtil.Companion companion = AmplifyModelUtil.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        return companion.overrideQueueSequenceSlice(it, convertedQueueSequenceSlice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createNewQueueSequenceSliceHelper$lambda-28, reason: not valid java name */
    public static final ObservableSource m194createNewQueueSequenceSliceHelper$lambda28(QueueSequenceSlice queueSequenceSlice) {
        return AmplifyDatastoreUtil.INSTANCE.saveAndReturnObservable(queueSequenceSlice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createNewQueueSequenceSliceHelper$lambda-29, reason: not valid java name */
    public static final ObservableSource m195createNewQueueSequenceSliceHelper$lambda29(AmplifyClient this$0, QueueSequenceSlice convertedQueueSequenceSlice, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(convertedQueueSequenceSlice, "$convertedQueueSequenceSlice");
        return this$0.retrySaveDataHandler(th, convertedQueueSequenceSlice);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<com.amazon.digitalmusicxp.models.QueueSequenceSlice> createNewQueueSequenceSlice(final com.amazon.digitalmusicxp.models.QueueSequenceSlice queueSequenceSlice) {
        Intrinsics.checkNotNullParameter(queueSequenceSlice, "queueSequenceSlice");
        int iIntValue = getSettingMaxEntitiesPerQueueSequenceSlice().intValue();
        final Ref.IntRef intRef = new Ref.IntRef();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final int iCeil = (int) Math.ceil(((double) queueSequenceSlice.getEntityReferences().size()) / ((double) iIntValue));
        CollectionsKt___CollectionsKt.chunked(queueSequenceSlice.getEntityReferences(), iIntValue, new Function1<List<? extends EntityReference>, Unit>() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient.createNewQueueSequenceSlice.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends EntityReference> list) {
                invoke2((List<EntityReference>) list);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Type inference failed for: r1v9, types: [T, java.lang.Throwable] */
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<EntityReference> subList) {
                Intrinsics.checkNotNullParameter(subList, "subList");
                if (objectRef.element == null) {
                    Ref.IntRef intRef2 = intRef;
                    int i = intRef2.element;
                    intRef2.element = i + 1;
                    Log.d(AmplifyClient.INSTANCE.getTAG(), "createNewQueueSequenceSlice name=" + queueSequenceSlice.getSequenceName() + " sliceOrdinal=" + i + " size=" + subList.size());
                    AmplifyClient amplifyClient = this;
                    boolean z = i == iCeil - 1;
                    com.amazon.digitalmusicxp.models.QueueSequenceSlice queueSequenceSlice2 = queueSequenceSlice;
                    Outcome outcomeCreateNewQueueSequenceSliceHelper = amplifyClient.createNewQueueSequenceSliceHelper(queueSequenceSlice2.copy((383 & 1) != 0 ? queueSequenceSlice2.queueId : null, (383 & 2) != 0 ? queueSequenceSlice2.customerId : null, (383 & 4) != 0 ? queueSequenceSlice2.id : null, (383 & 8) != 0 ? queueSequenceSlice2.sequenceName : null, (383 & 16) != 0 ? queueSequenceSlice2.sequenceVersion : 0, (383 & 32) != 0 ? queueSequenceSlice2.sliceOrdinal : i, (383 & 64) != 0 ? queueSequenceSlice2.isLastSlice : z, (383 & 128) != 0 ? queueSequenceSlice2.entityReferences : subList, (383 & 256) != 0 ? queueSequenceSlice2.createdAt : 0L, (383 & 512) != 0 ? queueSequenceSlice2.updatedAt : 0L));
                    if (outcomeCreateNewQueueSequenceSliceHelper instanceof Outcome.Failure) {
                        objectRef.element = ((Outcome.Failure) outcomeCreateNewQueueSequenceSliceHelper).getCause();
                    }
                }
            }
        });
        Throwable th = (Throwable) objectRef.element;
        Outcome.Failure failure = th == null ? null : new Outcome.Failure(th);
        return failure == null ? new Outcome.Success(queueSequenceSlice) : failure;
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public void deleteQueueById(final String queueId) {
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        final RxDataStoreCategoryBehavior rxDataStoreCategoryBehavior = RxAmplify.DataStore;
        rxDataStoreCategoryBehavior.query(Queue.class, Queue.ID.eq(queueId)).flatMapCompletable(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda23
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return rxDataStoreCategoryBehavior.delete((Queue) obj);
            }
        }).subscribe(new Action() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda24
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AmplifyClient.m196deleteQueueById$lambda31(queueId);
            }
        }, new Consumer() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda25
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmplifyClient.m197deleteQueueById$lambda32((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deleteQueueById$lambda-31, reason: not valid java name */
    public static final void m196deleteQueueById$lambda31(String queueId) {
        Intrinsics.checkNotNullParameter(queueId, "$queueId");
        Log.i(TAG, Intrinsics.stringPlus("delete queue with id ", queueId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deleteQueueById$lambda-32, reason: not valid java name */
    public static final void m197deleteQueueById$lambda32(Throwable th) {
        MetricsUtil.INSTANCE.trackEvent(MetricsUtil.EventType.EVENT_DELETE_QUEUE_BY_ID, new RuntimeException(th));
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public void deleteQueueSequenceSliceById(final String queueId) {
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        final RxDataStoreCategoryBehavior rxDataStoreCategoryBehavior = RxAmplify.DataStore;
        rxDataStoreCategoryBehavior.query(QueueSequenceSlice.class, QueueSequenceSlice.QUEUE_ID.eq(queueId)).flatMapCompletable(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda15
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return rxDataStoreCategoryBehavior.delete((QueueSequenceSlice) obj);
            }
        }).subscribe(new Action() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda16
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AmplifyClient.m198deleteQueueSequenceSliceById$lambda33(queueId);
            }
        }, new Consumer() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda17
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmplifyClient.m199deleteQueueSequenceSliceById$lambda34((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deleteQueueSequenceSliceById$lambda-33, reason: not valid java name */
    public static final void m198deleteQueueSequenceSliceById$lambda33(String queueId) {
        Intrinsics.checkNotNullParameter(queueId, "$queueId");
        Log.i(TAG, Intrinsics.stringPlus("delete queueSequenceSlice with queueId ", queueId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deleteQueueSequenceSliceById$lambda-34, reason: not valid java name */
    public static final void m199deleteQueueSequenceSliceById$lambda34(Throwable th) {
        MetricsUtil.INSTANCE.trackEvent(MetricsUtil.EventType.EVENT_DELETE_QUEUE_SEQUENCE_SLICE_BY_ID, new RuntimeException(th));
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<com.amazon.digitalmusicxp.models.DevicePlaybackState> changeDeviceState(com.amazon.digitalmusicxp.models.DevicePlaybackState newState) {
        Intrinsics.checkNotNullParameter(newState, "newState");
        final DevicePlaybackState amplify = ModelsTransformerKt.toAmplify(newState);
        if (amplify == null) {
            return new Outcome.Failure(new RuntimeException("Unable to get data as amplifyDevicePlaybackState converted is null"));
        }
        try {
            RxDataStoreCategoryBehavior rxDataStoreCategoryBehavior = RxAmplify.DataStore;
            if (((DevicePlaybackState) rxDataStoreCategoryBehavior.query(DevicePlaybackState.class, DevicePlaybackState.DEVICE_ID.eq(newState.getDeviceId()).and((QueryPredicate) DevicePlaybackState.CUSTOMER_ID.eq(newState.getCustomerId()))).take(1L).map(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda30
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return AmplifyClient.m183changeDeviceState$lambda35(amplify, (DevicePlaybackState) obj);
                }
            }).concatMap(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda31
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return AmplifyClient.m184changeDeviceState$lambda36((DevicePlaybackState) obj);
                }
            }).switchIfEmpty(rxDataStoreCategoryBehavior.save(amplify).toObservable().defaultIfEmpty(amplify)).onErrorResumeNext(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda32
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return AmplifyClient.m185changeDeviceState$lambda37(this.f$0, amplify, (Throwable) obj);
                }
            }).blockingFirst()) != null) {
                return new Outcome.Success(newState);
            }
            return new Outcome.Failure(new RuntimeException("no data return for QueueSequenceSlice"));
        } catch (Exception e) {
            MetricsUtil.INSTANCE.trackEvent(MetricsUtil.EventType.EVENT_CHANGE_DEVICE_STATE, e);
            return new Outcome.Failure(new RuntimeException("no data return for QueueSequenceSlice"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: changeDeviceState$lambda-35, reason: not valid java name */
    public static final DevicePlaybackState m183changeDeviceState$lambda35(DevicePlaybackState convertedDeviceState, DevicePlaybackState it) {
        Intrinsics.checkNotNullParameter(convertedDeviceState, "$convertedDeviceState");
        AmplifyModelUtil.Companion companion = AmplifyModelUtil.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        return companion.overrideDevicePlaybackState(it, convertedDeviceState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: changeDeviceState$lambda-36, reason: not valid java name */
    public static final ObservableSource m184changeDeviceState$lambda36(DevicePlaybackState devicePlaybackState) {
        return RxAmplify.DataStore.save(devicePlaybackState).toObservable().defaultIfEmpty(devicePlaybackState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: changeDeviceState$lambda-37, reason: not valid java name */
    public static final ObservableSource m185changeDeviceState$lambda37(AmplifyClient this$0, DevicePlaybackState convertedDeviceState, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(convertedDeviceState, "$convertedDeviceState");
        return this$0.retrySaveDataHandler(th, convertedDeviceState);
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<com.amazon.digitalmusicxp.models.QueueSequenceSlice> changeQueueSequenceSlice(com.amazon.digitalmusicxp.models.QueueSequenceSlice newSequenceSlice) {
        Intrinsics.checkNotNullParameter(newSequenceSlice, "newSequenceSlice");
        if (newSequenceSlice.getEntityReferences().size() > getSettingMaxEntitiesPerQueueSequenceSlice().intValue()) {
            return createNewQueueSequenceSlice(newSequenceSlice);
        }
        try {
            if (updateQueueSequencerHelper(ModelsTransformerKt.toAmplify(newSequenceSlice)) != null) {
                return new Outcome.Success(newSequenceSlice);
            }
            return new Outcome.Failure(new NoDataException());
        } catch (Exception e) {
            MetricsUtil.INSTANCE.trackEvent(MetricsUtil.EventType.EVENT_CHANGE_QUEUE_SEQUENCE_SLICE, e);
            return new Outcome.Failure(new NoDataException(e));
        }
    }

    private final QueueSequenceSlice updateQueueSequencerHelper(final QueueSequenceSlice convertedQueueSequenceSlice) {
        RxDataStoreCategoryBehavior rxDataStoreCategoryBehavior = RxAmplify.DataStore;
        return (QueueSequenceSlice) rxDataStoreCategoryBehavior.query(QueueSequenceSlice.class, QueueSequenceSlice.CUSTOMER_ID.eq(convertedQueueSequenceSlice.getCustomerId()).and((QueryPredicate) QueueSequenceSlice.ID.eq(convertedQueueSequenceSlice.getId()))).take(1L).map(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda40
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return AmplifyClient.m213updateQueueSequencerHelper$lambda38(convertedQueueSequenceSlice, (QueueSequenceSlice) obj);
            }
        }).concatMap(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda41
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return AmplifyClient.m214updateQueueSequencerHelper$lambda39((QueueSequenceSlice) obj);
            }
        }).switchIfEmpty(rxDataStoreCategoryBehavior.save(convertedQueueSequenceSlice).toObservable().defaultIfEmpty(convertedQueueSequenceSlice)).onErrorResumeNext(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda42
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return AmplifyClient.m215updateQueueSequencerHelper$lambda40(convertedQueueSequenceSlice, (Throwable) obj);
            }
        }).blockingFirst();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateQueueSequencerHelper$lambda-38, reason: not valid java name */
    public static final QueueSequenceSlice m213updateQueueSequencerHelper$lambda38(QueueSequenceSlice convertedQueueSequenceSlice, QueueSequenceSlice it) {
        Intrinsics.checkNotNullParameter(convertedQueueSequenceSlice, "$convertedQueueSequenceSlice");
        AmplifyModelUtil.Companion companion = AmplifyModelUtil.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        return companion.overrideQueueSequenceSlice(it, convertedQueueSequenceSlice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateQueueSequencerHelper$lambda-39, reason: not valid java name */
    public static final ObservableSource m214updateQueueSequencerHelper$lambda39(QueueSequenceSlice queueSequenceSlice) {
        return RxAmplify.DataStore.save(queueSequenceSlice).toObservable().defaultIfEmpty(queueSequenceSlice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateQueueSequencerHelper$lambda-40, reason: not valid java name */
    public static final ObservableSource m215updateQueueSequencerHelper$lambda40(QueueSequenceSlice convertedQueueSequenceSlice, final Throwable th) {
        Intrinsics.checkNotNullParameter(convertedQueueSequenceSlice, "$convertedQueueSequenceSlice");
        if (new PropertyReference0Impl(th) { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$updateQueueSequencerHelper$3$1
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return this.receiver.getClass();
            }
        } instanceof NoSuchElementException) {
            return RxAmplify.DataStore.save(convertedQueueSequenceSlice).toObservable().defaultIfEmpty(convertedQueueSequenceSlice);
        }
        return Observable.error(th);
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public void clearDataStore() {
        RxAmplify.DataStore.clear();
    }

    public Outcome<ConnectResponse> connect() {
        GraphQLRequest<Object> graphQLRequestInitInput = ConnectGraphQLRequest.INSTANCE.initInput(0, this.context);
        if (graphQLRequestInitInput == null) {
            return new Outcome.Failure(new RuntimeException("unknown error happens for connect"));
        }
        try {
            Object response = getGraphQLResponseSingleWithExponentialBackoff(graphQLRequestInitInput, MetricsUtil.EventType.EVENT_CONNECT.getValue()).map(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda11
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return AmplifyClient.m186connect$lambda41((GraphQLResponse) obj);
                }
            }).blockingGet();
            if (response instanceof ConnectResponse) {
                Intrinsics.checkNotNullExpressionValue(response, "response");
                return new Outcome.Success(response);
            }
            if (response instanceof GraphQLResponse.Error) {
                final Map<String, Object> extensions = ((GraphQLResponse.Error) response).getExtensions();
                Intrinsics.checkNotNullExpressionValue(response, "response");
                RuntimeException runtimeException = toRuntimeException((GraphQLResponse.Error) response);
                CQEThrottleUtil.INSTANCE.authThrottle(new Function0<Unit>() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient.connect.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        MetricsUtil metricsUtil = MetricsUtil.INSTANCE;
                        Map<String, Object> map = extensions;
                        Object obj = map == null ? null : map.get("errorType");
                        String str = obj instanceof String ? (String) obj : null;
                        Map<String, Object> map2 = extensions;
                        Object obj2 = map2 == null ? null : map2.get("errorInfo");
                        metricsUtil.trackEvent("cloudQueueAuth", (32 & 2) != 0 ? null : "connect", (32 & 4) != 0 ? null : str, (32 & 8) == 0 ? obj2 instanceof String ? (String) obj2 : null : null, (32 & 16) != 0, (32 & 32) != 0 ? false : false);
                    }
                });
                return new Outcome.Failure(runtimeException);
            }
            RuntimeException runtimeException2 = new RuntimeException("unknown error happens for connect");
            CQEThrottleUtil.INSTANCE.authThrottle(new Function0<Unit>() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient.connect.2
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    MetricsUtil.INSTANCE.trackEvent("cloudQueueAuth", (32 & 2) != 0 ? null : "connect", (32 & 4) != 0 ? null : null, (32 & 8) == 0 ? null : null, (32 & 16) != 0, (32 & 32) != 0 ? false : false);
                }
            });
            return new Outcome.Failure(runtimeException2);
        } catch (Exception e) {
            CQEThrottleUtil.INSTANCE.authThrottle(new Function0<Unit>() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient.connect.3
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    MetricsUtil.INSTANCE.trackEvent("cloudQueueAuth", (32 & 2) != 0 ? null : "connect", (32 & 4) != 0 ? null : null, (32 & 8) == 0 ? null : null, (32 & 16) != 0, (32 & 32) != 0 ? false : false);
                }
            });
            return new Outcome.Failure(new RuntimeException(Intrinsics.stringPlus("error happens for connect: ", e.getMessage()), e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: connect$lambda-41, reason: not valid java name */
    public static final Object m186connect$lambda41(GraphQLResponse graphQLResponse) {
        Intrinsics.checkNotNullExpressionValue(graphQLResponse.getErrors(), "it.errors");
        if (!r0.isEmpty()) {
            Log.e(TAG, Intrinsics.stringPlus("error connect attempt#2", graphQLResponse.getErrors()));
            List<GraphQLResponse.Error> errors = graphQLResponse.getErrors();
            Intrinsics.checkNotNullExpressionValue(errors, "it.errors");
            return CollectionsKt___CollectionsKt.first((List) errors);
        }
        Log.i(TAG, Intrinsics.stringPlus("get result ", graphQLResponse.getData()));
        Object data = graphQLResponse.getData();
        if (data == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.amplifyqueue.model.ConnectResponseData");
        }
        com.amazon.mp3.amplifyqueue.model.ConnectResponse connect = ((ConnectResponseData) data).getConnect();
        Intrinsics.checkNotNullExpressionValue(connect, "data.connect");
        return InputsTransformerKt.toExternal(connect);
    }

    private final AmplifyConfiguration initConfigurationFromCloud(AuthorizationType authType) {
        AmplifyConfigurationUtil.Companion companion = AmplifyConfigurationUtil.INSTANCE;
        ConfigurationProvider configurationProvider = companion.getConfigurationProvider();
        Configuration configuration = configurationProvider instanceof Configuration ? (Configuration) configurationProvider : null;
        String strString = configuration == null ? null : configuration.string("cloud_queue_appsync_endpoint");
        String strString2 = configuration == null ? null : configuration.string("cloud_queue_appsync_region");
        if (strString != null && strString2 != null) {
            return companion.createAmplifyConfiguration(strString, strString2, authType);
        }
        try {
            ConnectionInfoResponse connectionInfo = getConnectionInfo();
            if (connectionInfo == null) {
                return null;
            }
            return companion.createAmplifyConfiguration(connectionInfo.getAppSyncEndpoint(), connectionInfo.getRegion(), authType);
        } catch (Exception e) {
            Log.e(TAG, Intrinsics.stringPlus("Failed to init configuration with connectionInfo ", this.connectionInfo), e);
            return null;
        }
    }

    private final <T extends Model> Observable<T> retrySaveDataHandler(Throwable it, T data) {
        AmplifyDatastoreUtil amplifyDatastoreUtil = AmplifyDatastoreUtil.INSTANCE;
        if (amplifyDatastoreUtil.isErrorShouldRetry(it)) {
            Log.d(TAG, Intrinsics.stringPlus("retrySaveDataHandler ", data));
            return amplifyDatastoreUtil.saveAndReturnObservable(data);
        }
        Observable<T> observableError = Observable.error(it);
        Intrinsics.checkNotNullExpressionValue(observableError, "{\n                Observ…e.error(it)\n            }");
        return observableError;
    }

    private final FunctionAuthProvider createFunctionProvider(CustomerInfoProvider customerInfoProvider, String deviceId, String deviceType, Function0<String> accessTokenProvider, ChangeStateResult<String> callback) {
        return new FunctionAuthProviderImpl(customerInfoProvider, deviceId, deviceType, accessTokenProvider, callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void trackUncategorizedException(Exception e) {
        Log.e(TAG, "track uncategorized exception", e);
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<QueueCapabilitySet> getQueueCapabilitySet(ServiceTierEnum serviceTier, QueueSeedTypeEnum queueSeedType, String queueInteractionPattern) {
        Intrinsics.checkNotNullParameter(serviceTier, "serviceTier");
        MetricsUtil.INSTANCE.setServiceTier(serviceTier.name());
        if (serviceTier == ServiceTierEnum.SONIC_RUSH || serviceTier == ServiceTierEnum.NIGHTWING) {
            return getQueueCapabilitySetV2(queueInteractionPattern);
        }
        return getQueueCapabilitySetV1(serviceTier, queueSeedType);
    }

    private final Outcome<QueueCapabilitySet> getQueueCapabilitySetV1(ServiceTierEnum serviceTier, QueueSeedTypeEnum queueSeedType) {
        if (queueSeedType == null) {
            return new Outcome.Failure(new RuntimeException("getQueueCapabilitySetV1: queueSeedType is null"));
        }
        try {
            return new Outcome.Success((QueueCapabilitySet) RxAmplify.DataStore.query(com.amazon.mp3.amplifyqueue.model.QueueCapabilitySet.class, com.amazon.mp3.amplifyqueue.model.QueueCapabilitySet.SERVICE_TIER.eq(serviceTier.name()).and((QueryPredicate) com.amazon.mp3.amplifyqueue.model.QueueCapabilitySet.QUEUE_SEED_TYPE.eq(queueSeedType.name()))).firstOrError().map(new AmplifyClient$$ExternalSyntheticLambda26()).subscribeOn(Schedulers.io()).blockingGet());
        } catch (Exception e) {
            Log.w(TAG, Intrinsics.stringPlus("getQueueCapabilitySet failed, error=", e));
            return getQueueCapabilitySetByServiceTier(serviceTier);
        }
    }

    private final Outcome<QueueCapabilitySet> getQueueCapabilitySetV2(String queueInteractionPattern) {
        if (queueInteractionPattern == null) {
            return new Outcome.Failure(new RuntimeException("getQueueCapabilitySetV2: queueInteractionPattern is null"));
        }
        try {
            return new Outcome.Success((QueueCapabilitySet) RxAmplify.DataStore.query(QueueCapabilitySetV2.class, QueueCapabilitySetV2.QUEUE_INTERACTION_PATTERN.eq(queueInteractionPattern)).firstOrError().map(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda27
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return ModelsTransformerKt.toExternal((QueueCapabilitySetV2) obj);
                }
            }).subscribeOn(Schedulers.io()).blockingGet());
        } catch (Exception e) {
            Log.w(TAG, Intrinsics.stringPlus("getQueueCapabilitySet failed, error=", e));
            return AmplifyDatastoreUtil.INSTANCE.getDefaultQueueCapabilitySetV2(queueInteractionPattern);
        }
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Outcome<QueueEntityCapabilitySet> getQueueEntityCapabilitySet(ServiceTierEnum serviceTier, String queueInteractionPattern) {
        Intrinsics.checkNotNullParameter(serviceTier, "serviceTier");
        Intrinsics.checkNotNullParameter(queueInteractionPattern, "queueInteractionPattern");
        try {
        } catch (Exception e) {
            Log.w(TAG, Intrinsics.stringPlus("getQueueEntityCapabilitySet failed, error=", e));
        }
        if (!Intrinsics.areEqual(queueInteractionPattern, "LOCAL_QUEUE_CREATION_LOCAL_TRACK_CONSUMPTION")) {
            throw new Exception(Intrinsics.stringPlus("Unsupported queueInteractionPattern. You provided %s", queueInteractionPattern));
        }
        QueueEntityCapabilitySet queueEntityCapabilitySet = (QueueEntityCapabilitySet) RxAmplify.DataStore.query(com.amazon.mp3.amplifyqueue.model.QueueEntityCapabilitySet.class, com.amazon.mp3.amplifyqueue.model.QueueEntityCapabilitySet.ID.eq("00000000-0000-0000-0000-000000000005")).firstOrError().map(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda13
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return AmplifyClient.m209getQueueEntityCapabilitySet$lambda44((com.amazon.mp3.amplifyqueue.model.QueueEntityCapabilitySet) obj);
            }
        }).subscribeOn(Schedulers.io()).blockingGet();
        if (queueEntityCapabilitySet != null) {
            return new Outcome.Success(queueEntityCapabilitySet);
        }
        return AmplifyDatastoreUtil.INSTANCE.getDefaultQueueEntityCapabilitySet(queueInteractionPattern);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getQueueEntityCapabilitySet$lambda-44, reason: not valid java name */
    public static final QueueEntityCapabilitySet m209getQueueEntityCapabilitySet$lambda44(com.amazon.mp3.amplifyqueue.model.QueueEntityCapabilitySet it) {
        Intrinsics.checkNotNullExpressionValue(it, "it");
        return ModelsTransformerKt.toExternal(it);
    }

    private final Outcome<QueueCapabilitySet> getQueueCapabilitySetByServiceTier(ServiceTierEnum serviceTier) {
        String unlimitedQueueCapabilityId;
        AmplifyDatastoreUtil amplifyDatastoreUtil = AmplifyDatastoreUtil.INSTANCE;
        Setting setting = amplifyDatastoreUtil.getSetting();
        DefaultQueueCapabilityIds defaultQueueCapabilityIds = setting == null ? null : setting.getDefaultQueueCapabilityIds();
        if (defaultQueueCapabilityIds == null) {
            defaultQueueCapabilityIds = amplifyDatastoreUtil.getDefaultQueueCapabilityIdsNonSonicRushTier();
        }
        int i = WhenMappings.$EnumSwitchMapping$1[serviceTier.ordinal()];
        if (i == 1) {
            unlimitedQueueCapabilityId = defaultQueueCapabilityIds.getUnlimitedQueueCapabilityId();
        } else if (i == 2) {
            unlimitedQueueCapabilityId = defaultQueueCapabilityIds.getPrimeQueueCapabilityId();
        } else if (i == 3) {
            unlimitedQueueCapabilityId = defaultQueueCapabilityIds.getLibraryQueueCapabilityId();
        } else {
            return new Outcome.Failure(new RuntimeException("not supported tier"));
        }
        try {
            return new Outcome.Success((QueueCapabilitySet) RxAmplify.DataStore.query(com.amazon.mp3.amplifyqueue.model.QueueCapabilitySet.class, com.amazon.mp3.amplifyqueue.model.QueueCapabilitySet.ID.eq(unlimitedQueueCapabilityId)).singleOrError().map(new AmplifyClient$$ExternalSyntheticLambda26()).subscribeOn(Schedulers.io()).blockingGet());
        } catch (Exception e) {
            Log.e(TAG, "getQueueCapabilitySetById failed", e);
            return AmplifyDatastoreUtil.INSTANCE.getDefaultQueueCapabilitySet(serviceTier);
        }
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Integer getSettingMaxEntitiesPerQueueSequenceSlice() {
        Setting setting = AmplifyDatastoreUtil.INSTANCE.getSetting();
        return Integer.valueOf(setting == null ? 665 : setting.getMaxEntitiesPerQueueSequenceSlice());
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public Integer getSettingQueueTtlInSeconds() {
        Setting setting = AmplifyDatastoreUtil.INSTANCE.getSetting();
        return Integer.valueOf(setting == null ? 1209600 : setting.getQueueTtlInSeconds());
    }

    @Override // com.amazon.digitalmusicxp.clients.CloudQueueClient
    public String getSettingDefaultQueueEntityCapabilitiesId() {
        Enum enumValueOf;
        QueueEntityCapabilitySet queueEntityCapabilitySet;
        Setting setting = AmplifyDatastoreUtil.INSTANCE.getSetting();
        String id = null;
        String defaultQueueEntityCapabilitiesId = setting == null ? null : setting.getDefaultQueueEntityCapabilitiesId();
        if (defaultQueueEntityCapabilitiesId != null) {
            return defaultQueueEntityCapabilitiesId;
        }
        String serviceTier = MetricsUtil.INSTANCE.getServiceTier();
        if (serviceTier == null) {
            enumValueOf = null;
        } else {
            try {
                enumValueOf = Enum.valueOf(ServiceTierEnum.class, serviceTier);
            } catch (Exception e) {
                e.printStackTrace();
                enumValueOf = null;
            }
        }
        ServiceTierEnum serviceTierEnum = (ServiceTierEnum) enumValueOf;
        if (serviceTierEnum != null) {
            Outcome<QueueEntityCapabilitySet> queueEntityCapabilitySet2 = getQueueEntityCapabilitySet(serviceTierEnum, QueueInteractionPatternEnum.LOCAL_QUEUE_CREATION_LOCAL_TRACK_CONSUMPTION.name());
            Outcome.Success success = queueEntityCapabilitySet2 instanceof Outcome.Success ? (Outcome.Success) queueEntityCapabilitySet2 : null;
            if (success != null && (queueEntityCapabilitySet = (QueueEntityCapabilitySet) success.getValue()) != null) {
                id = queueEntityCapabilitySet.getId();
            }
        }
        return id;
    }

    public final RuntimeException toRuntimeException(GraphQLResponse.Error error) {
        Object obj;
        Intrinsics.checkNotNullParameter(error, "<this>");
        StringBuilder sb = new StringBuilder();
        sb.append("errorType=");
        Map<String, Object> extensions = error.getExtensions();
        String string = null;
        sb.append(extensions == null ? null : extensions.get("errorType"));
        sb.append(": ");
        sb.append(error.getMessage());
        String string2 = sb.toString();
        Map<String, Object> extensions2 = error.getExtensions();
        if (extensions2 != null && (obj = extensions2.get("errorInfo")) != null) {
            string = obj.toString();
        }
        return new RuntimeException(string2, new Throwable(string));
    }

    private final Single<GraphQLResponse<Object>> getGraphQLResponseSingleWithExponentialBackoff(GraphQLRequest<Object> graphQLRequest, String eventName) {
        Single<GraphQLResponse<Object>> singleSubscribeOn = RxAmplify.API.query(graphQLRequest).retryWhen(new RetryWithExponentialBackoff(eventName, 3, false, null, null, 28, null)).subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(singleSubscribeOn, "API.query(graphQLRequest…scribeOn(Schedulers.io())");
        return singleSubscribeOn;
    }

    private final Single<GraphQLResponse<Object>> getGraphQLResponseSingle(final GraphQLRequest<Object> graphQLRequest, final String eventName) {
        final long timeoutConfig = AmplifyConfigurationUtil.INSTANCE.getTimeoutConfig(eventName);
        Single<GraphQLResponse<Object>> singleOnErrorResumeNext = RxAmplify.API.query(graphQLRequest).subscribeOn(Schedulers.io()).timeout(timeoutConfig, TimeUnit.SECONDS).onErrorResumeNext(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda29
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return AmplifyClient.m202getGraphQLResponseSingle$lambda48(eventName, graphQLRequest, timeoutConfig, (Throwable) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleOnErrorResumeNext, "API.query(graphQLRequest…      }\n                }");
        return singleOnErrorResumeNext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getGraphQLResponseSingle$lambda-48, reason: not valid java name */
    public static final SingleSource m202getGraphQLResponseSingle$lambda48(final String eventName, final GraphQLRequest graphQLRequest, final long j, final Throwable th) {
        Intrinsics.checkNotNullParameter(eventName, "$eventName");
        Intrinsics.checkNotNullParameter(graphQLRequest, "$graphQLRequest");
        if (th instanceof ApiException.NonRetryableException) {
            return Single.error(th);
        }
        Log.w(TAG, "error " + eventName + " attempt#1, retry one more time", th);
        final Ref.IntRef intRef = new Ref.IntRef();
        if (th instanceof TimeoutException) {
            intRef.element++;
        }
        return RxAmplify.API.query(graphQLRequest).timeout(j, TimeUnit.SECONDS).onErrorResumeNext(new Function() { // from class: com.amazon.mp3.amplifyqueue.AmplifyClient$$ExternalSyntheticLambda35
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return AmplifyClient.m203getGraphQLResponseSingle$lambda48$lambda47(th, intRef, eventName, graphQLRequest, j, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getGraphQLResponseSingle$lambda-48$lambda-47, reason: not valid java name */
    public static final SingleSource m203getGraphQLResponseSingle$lambda48$lambda47(Throwable th, Ref.IntRef totalTimeoutCount, String eventName, GraphQLRequest graphQLRequest, long j, Throwable th2) {
        Intrinsics.checkNotNullParameter(totalTimeoutCount, "$totalTimeoutCount");
        Intrinsics.checkNotNullParameter(eventName, "$eventName");
        Intrinsics.checkNotNullParameter(graphQLRequest, "$graphQLRequest");
        if (th2 instanceof ApiException.NonRetryableException) {
            return Single.error(th);
        }
        if (th instanceof TimeoutException) {
            totalTimeoutCount.element++;
        }
        if (totalTimeoutCount.element == 2) {
            return Single.error(th);
        }
        Log.w(TAG, "error " + eventName + " attempt#2, retry one more time", th);
        return RxAmplify.API.query(graphQLRequest).timeout(j, TimeUnit.SECONDS);
    }
}
