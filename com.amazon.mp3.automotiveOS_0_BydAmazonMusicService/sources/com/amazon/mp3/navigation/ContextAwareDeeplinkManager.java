package com.amazon.mp3.navigation;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.mp3.navigation.ContextAwareDeeplinkManager;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.deeplink.UnknownDeeplinkException;
import com.amazon.music.destination.core.DeeplinkParser;
import com.amazon.music.destination.core.Destination;
import com.amazon.music.destination.parser.FindDeeplinkParser;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.router.UnknownDestinationException;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.amazon.music.voice.ContextAwareDeeplinkDelegate;
import com.amazon.music.voice.launcher.CannotHandle;
import com.amazon.music.voice.launcher.LaunchTargetError;
import com.amazon.music.voice.launcher.LauncherTokens;
import com.amazon.music.voice.launcher.UnknownContext;
import com.amazon.music.voice.launcher.UnknownTargetError;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import main.java.com.amazon.music.destination.parser.CarModeDeeplinkParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: ContextAwareDeeplinkManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\\\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020\b28\u0010#\u001a4\u0012\u0015\u0012\u0013\u0018\u00010%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110!¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u001f0$H\u0002J\n\u0010)\u001a\u0004\u0018\u00010*H\u0002JR\u0010+\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!28\u0010#\u001a4\u0012\u0015\u0012\u0013\u0018\u00010%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110!¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u001f0$H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R1\u0010\u0012\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u000e\b\u0001\u0012\n \u0016*\u0004\u0018\u00010\u00150\u00150\u00140\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\f\u001a\u0004\b\u0017\u0010\u0018¨\u0006-"}, d2 = {"Lcom/amazon/mp3/navigation/ContextAwareDeeplinkManager;", "Lcom/amazon/music/voice/ContextAwareDeeplinkDelegate;", "deeplinksManager", "Lcom/amazon/music/deeplink/DeeplinksManager;", "context", "Landroid/content/Context;", "(Lcom/amazon/music/deeplink/DeeplinksManager;Landroid/content/Context;)V", "defaultBackgroundDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "getDefaultBackgroundDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "defaultBackgroundDispatcher$delegate", "Lkotlin/Lazy;", "localBroadcastManager", "Landroidx/localbroadcastmanager/content/LocalBroadcastManager;", "getLocalBroadcastManager", "()Landroidx/localbroadcastmanager/content/LocalBroadcastManager;", "localBroadcastManager$delegate", "parsers", "", "Lcom/amazon/music/destination/core/DeeplinkParser;", "Lcom/amazon/music/destination/core/Destination;", "kotlin.jvm.PlatformType", "getParsers", "()Ljava/util/List;", "parsers$delegate", "checkIfExperienceDeeplink", "", "uri", "Landroid/net/Uri;", "dispatchAppLinkUriProcessing", "", "launcherTokens", "Lcom/amazon/music/voice/launcher/LauncherTokens;", "dispatcher", "onComplete", "Lkotlin/Function2;", "Lcom/amazon/music/voice/launcher/LaunchTargetError;", "Lkotlin/ParameterName;", "name", "launchTargetError", "getVisibleDeeplinkAwareFragment", "Lcom/amazon/mp3/navigation/DeeplinkAware;", "handle", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContextAwareDeeplinkManager implements ContextAwareDeeplinkDelegate {
    private static final Logger LOG;
    private static final String TAG;
    private final Context context;
    private final DeeplinksManager deeplinksManager;

    /* JADX INFO: renamed from: defaultBackgroundDispatcher$delegate, reason: from kotlin metadata */
    private final Lazy defaultBackgroundDispatcher;

    /* JADX INFO: renamed from: localBroadcastManager$delegate, reason: from kotlin metadata */
    private final Lazy localBroadcastManager;

    /* JADX INFO: renamed from: parsers$delegate, reason: from kotlin metadata */
    private final Lazy parsers;

    public ContextAwareDeeplinkManager(DeeplinksManager deeplinksManager, Context context) {
        Intrinsics.checkNotNullParameter(deeplinksManager, "deeplinksManager");
        Intrinsics.checkNotNullParameter(context, "context");
        this.deeplinksManager = deeplinksManager;
        this.context = context;
        this.localBroadcastManager = LazyKt__LazyJVMKt.lazy(new Function0<LocalBroadcastManager>() { // from class: com.amazon.mp3.navigation.ContextAwareDeeplinkManager$localBroadcastManager$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LocalBroadcastManager invoke() {
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this.this$0.context);
                Intrinsics.checkNotNullExpressionValue(localBroadcastManager, "getInstance(context)");
                return localBroadcastManager;
            }
        });
        this.parsers = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends DeeplinkParser<? extends Destination>>>() { // from class: com.amazon.mp3.navigation.ContextAwareDeeplinkManager$parsers$2
            @Override // kotlin.jvm.functions.Function0
            public final List<? extends DeeplinkParser<? extends Destination>> invoke() {
                return CollectionsKt__CollectionsKt.listOf((Object[]) new DeeplinkParser[]{new FindDeeplinkParser(), new CarModeDeeplinkParser()});
            }
        });
        this.defaultBackgroundDispatcher = LazyKt__LazyJVMKt.lazy(new Function0<CoroutineDispatcher>() { // from class: com.amazon.mp3.navigation.ContextAwareDeeplinkManager$defaultBackgroundDispatcher$2
            @Override // kotlin.jvm.functions.Function0
            public final CoroutineDispatcher invoke() {
                return Dispatchers.getDefault();
            }
        });
    }

    static {
        String simpleName = ContextAwareDeeplinkManager.class.getSimpleName();
        TAG = simpleName;
        Logger logger = LoggerFactory.getLogger(simpleName);
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(TAG)");
        LOG = logger;
    }

    private final void dispatchAppLinkUriProcessing(Uri uri, LauncherTokens launcherTokens, CoroutineDispatcher dispatcher, Function2<? super LaunchTargetError, ? super LauncherTokens, Unit> onComplete) {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(JobKt__JobKt.Job$default((Job) null, 1, (Object) null).plus(dispatcher)), new ContextAwareDeeplinkManager$dispatchAppLinkUriProcessing$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE, uri, onComplete, launcherTokens), null, new AnonymousClass1(uri, launcherTokens, onComplete, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LocalBroadcastManager getLocalBroadcastManager() {
        return (LocalBroadcastManager) this.localBroadcastManager.getValue();
    }

    private final List<DeeplinkParser<? extends Destination>> getParsers() {
        return (List) this.parsers.getValue();
    }

    private final CoroutineDispatcher getDefaultBackgroundDispatcher() {
        return (CoroutineDispatcher) this.defaultBackgroundDispatcher.getValue();
    }

    @Override // com.amazon.music.voice.ContextAwareDeeplinkDelegate
    public void handle(Uri uri, LauncherTokens launcherTokens, Function2<? super LaunchTargetError, ? super LauncherTokens, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(launcherTokens, "launcherTokens");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        dispatchAppLinkUriProcessing$default(this, uri, launcherTokens, null, onComplete, 4, null);
    }

    static /* synthetic */ void dispatchAppLinkUriProcessing$default(ContextAwareDeeplinkManager contextAwareDeeplinkManager, Uri uri, LauncherTokens launcherTokens, CoroutineDispatcher coroutineDispatcher, Function2 function2, int i, Object obj) {
        if ((i & 4) != 0) {
            coroutineDispatcher = contextAwareDeeplinkManager.getDefaultBackgroundDispatcher();
        }
        contextAwareDeeplinkManager.dispatchAppLinkUriProcessing(uri, launcherTokens, coroutineDispatcher, function2);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.navigation.ContextAwareDeeplinkManager$dispatchAppLinkUriProcessing$1, reason: invalid class name */
    /* JADX INFO: compiled from: ContextAwareDeeplinkManager.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.navigation.ContextAwareDeeplinkManager$dispatchAppLinkUriProcessing$1", f = "ContextAwareDeeplinkManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ LauncherTokens $launcherTokens;
        final /* synthetic */ Function2<LaunchTargetError, LauncherTokens, Unit> $onComplete;
        final /* synthetic */ Uri $uri;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Uri uri, LauncherTokens launcherTokens, Function2<? super LaunchTargetError, ? super LauncherTokens, Unit> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$uri = uri;
            this.$launcherTokens = launcherTokens;
            this.$onComplete = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = ContextAwareDeeplinkManager.this.new AnonymousClass1(this.$uri, this.$launcherTokens, this.$onComplete, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r7v0, types: [android.content.BroadcastReceiver, com.amazon.mp3.navigation.ContextAwareDeeplinkManager$dispatchAppLinkUriProcessing$1$appLinkHandlerStatusReceiver$1] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            if (!ContextAwareDeeplinkManager.this.checkIfExperienceDeeplink(this.$uri)) {
                DeeplinkAware visibleDeeplinkAwareFragment = ContextAwareDeeplinkManager.this.getVisibleDeeplinkAwareFragment();
                if (visibleDeeplinkAwareFragment != null) {
                    ContextAwareDeeplinkManager.LOG.debug(Intrinsics.stringPlus("Current fragment is deeplink aware - ", this.$uri));
                    visibleDeeplinkAwareFragment.handle(this.$uri, this.$launcherTokens, this.$onComplete);
                } else {
                    String strStringPlus = Intrinsics.stringPlus("Unable to find DeeplinkAware fragment to handle App Interaction Uri - ", this.$uri);
                    ContextAwareDeeplinkManager.LOG.error(strStringPlus);
                    this.$onComplete.mo4invoke(new UnknownContext(strStringPlus), this.$launcherTokens);
                }
            } else {
                final Uri uri = this.$uri;
                final Function2<LaunchTargetError, LauncherTokens, Unit> function2 = this.$onComplete;
                final LauncherTokens launcherTokens = this.$launcherTokens;
                final ContextAwareDeeplinkManager contextAwareDeeplinkManager = ContextAwareDeeplinkManager.this;
                ?? r7 = new BroadcastReceiver() { // from class: com.amazon.mp3.navigation.ContextAwareDeeplinkManager$dispatchAppLinkUriProcessing$1$appLinkHandlerStatusReceiver$1
                    @Override // android.content.BroadcastReceiver
                    public void onReceive(Context context, Intent intent) {
                        CoroutineScopeKt.ensureActive(coroutineScope);
                        if (intent != null) {
                            Bundle extras = intent.getExtras();
                            if (StringsKt__StringsJVMKt.equals(extras == null ? null : extras.getString(Contexts.OpenSeeMore.uri), uri.toString(), false)) {
                                Bundle extras2 = intent.getExtras();
                                Object obj2 = extras2 == null ? null : extras2.get("STATUS");
                                if (obj2 == ContextAwareDeeplinkManager.Companion.Status.SUCCESS) {
                                    function2.mo4invoke(null, launcherTokens);
                                } else if (obj2 == ContextAwareDeeplinkManager.Companion.Status.FAILURE) {
                                    Bundle extras3 = intent.getExtras();
                                    Object obj3 = extras3 == null ? null : extras3.get("MESSAGE");
                                    function2.mo4invoke(new UnknownTargetError(obj3 != null ? obj3.toString() : null), launcherTokens);
                                }
                                if (context == null) {
                                    return;
                                }
                                contextAwareDeeplinkManager.getLocalBroadcastManager().unregisterReceiver(this);
                            }
                        }
                    }
                };
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00931(ContextAwareDeeplinkManager.this, r7, this.$onComplete, this.$launcherTokens, null), 3, null);
                ContextAwareDeeplinkManager.this.getLocalBroadcastManager().registerReceiver(r7, new IntentFilter("APPLINK_DEEPLINKS"));
                ContextAwareDeeplinkManager.this.deeplinksManager.handle(this.$uri);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.amazon.mp3.navigation.ContextAwareDeeplinkManager$dispatchAppLinkUriProcessing$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: ContextAwareDeeplinkManager.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.mp3.navigation.ContextAwareDeeplinkManager$dispatchAppLinkUriProcessing$1$1", f = "ContextAwareDeeplinkManager.kt", i = {0}, l = {113}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
        static final class C00931 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ ContextAwareDeeplinkManager$dispatchAppLinkUriProcessing$1$appLinkHandlerStatusReceiver$1 $appLinkHandlerStatusReceiver;
            final /* synthetic */ LauncherTokens $launcherTokens;
            final /* synthetic */ Function2<LaunchTargetError, LauncherTokens, Unit> $onComplete;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ ContextAwareDeeplinkManager this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00931(ContextAwareDeeplinkManager contextAwareDeeplinkManager, ContextAwareDeeplinkManager$dispatchAppLinkUriProcessing$1$appLinkHandlerStatusReceiver$1 contextAwareDeeplinkManager$dispatchAppLinkUriProcessing$1$appLinkHandlerStatusReceiver$1, Function2<? super LaunchTargetError, ? super LauncherTokens, Unit> function2, LauncherTokens launcherTokens, Continuation<? super C00931> continuation) {
                super(2, continuation);
                this.this$0 = contextAwareDeeplinkManager;
                this.$appLinkHandlerStatusReceiver = contextAwareDeeplinkManager$dispatchAppLinkUriProcessing$1$appLinkHandlerStatusReceiver$1;
                this.$onComplete = function2;
                this.$launcherTokens = launcherTokens;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00931 c00931 = new C00931(this.this$0, this.$appLinkHandlerStatusReceiver, this.$onComplete, this.$launcherTokens, continuation);
                c00931.L$0 = obj;
                return c00931;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00931) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineScope coroutineScope;
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                    this.L$0 = coroutineScope2;
                    this.label = 1;
                    if (DelayKt.delay(CoroutineLiveDataKt.DEFAULT_TIMEOUT, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope = coroutineScope2;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                try {
                    this.this$0.getLocalBroadcastManager().unregisterReceiver(this.$appLinkHandlerStatusReceiver);
                    this.$onComplete.mo4invoke(new CannotHandle("cannot handle due to timeout"), this.$launcherTokens);
                    CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    ContextAwareDeeplinkManager.LOG.error(e.getMessage());
                }
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkIfExperienceDeeplink(Uri uri) {
        String path = uri.getPath();
        if (path == null) {
            return false;
        }
        Iterator<DeeplinkParser<? extends Destination>> it = getParsers().iterator();
        while (it.hasNext()) {
            String urlRegex = it.next().getUrlRegex();
            Intrinsics.checkNotNullExpressionValue(urlRegex, "parser.urlRegex");
            if (new Regex(urlRegex).matches(path)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final DeeplinkAware getVisibleDeeplinkAwareFragment() {
        List<Fragment> arrayList = new ArrayList<>();
        Activity currentActivity = AndroidUtils.getCurrentActivity();
        if (currentActivity != null && (currentActivity instanceof AppCompatActivity)) {
            arrayList = ((AppCompatActivity) currentActivity).getSupportFragmentManager().getFragments();
            Intrinsics.checkNotNullExpressionValue(arrayList, "it.supportFragmentManager.fragments");
        }
        for (Fragment fragment : arrayList) {
            if (fragment.isVisible() && (fragment instanceof DeeplinkAware)) {
                return (DeeplinkAware) fragment;
            }
        }
        return null;
    }
}
