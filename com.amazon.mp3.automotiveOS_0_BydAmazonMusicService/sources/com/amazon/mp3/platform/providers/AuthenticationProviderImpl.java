package com.amazon.mp3.platform.providers;

import android.content.Context;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.music.authentication.AuthenticationException;
import com.amazon.music.authentication.mapr5.AdpProviderMAPR5;
import com.amazon.music.authentication.mapr5.AuthCookiesProviderMAPR5;
import com.amazon.music.authentication.mapr5.OAuthProviderMAPR5;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.AdpInfo;
import com.amazon.music.platform.data.AuthCookies;
import com.amazon.music.platform.data.OAuthInfo;
import com.amazon.music.platform.providers.AuthenticationProvider;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: AuthenticationProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u001b\b\u0007\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u0019\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0096@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\u0005J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0016J#\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0096@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016J\n\u0010\u0010\u001a\u0004\u0018\u00010\nH\u0016R\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/platform/providers/AuthenticationProviderImpl;", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "Lcom/amazon/music/platform/Outcome;", "Lcom/amazon/music/platform/data/OAuthInfo;", "getOAuthInfo", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOAuthInfoBlocking", "Lcom/amazon/music/platform/data/AdpInfo;", "getAdpInfo", "getAdpInfoBlocking", "", "domain", "Lcom/amazon/music/platform/data/AuthCookies;", "getAuthCookies", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAuthCookiesBlocking", "getAccessTokenBlocking", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/mp3/platform/providers/DispatcherProvider;", "dispatchers", "Lcom/amazon/mp3/platform/providers/DispatcherProvider;", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/platform/providers/DispatcherProvider;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AuthenticationProviderImpl implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationProviderImpl.class.getSimpleName());
    private final Context context;
    private final DispatcherProvider dispatchers;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public AuthenticationProviderImpl(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @JvmOverloads
    public AuthenticationProviderImpl(Context context, DispatcherProvider dispatchers) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dispatchers, "dispatchers");
        this.context = context;
        this.dispatchers = dispatchers;
    }

    public /* synthetic */ AuthenticationProviderImpl(Context context, DispatcherProvider dispatcherProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? DefaultDispatcherProvider.INSTANCE : dispatcherProvider);
    }

    @Override // com.amazon.music.platform.providers.AuthenticationProvider
    public Object getOAuthInfo(Continuation<? super Outcome<OAuthInfo>> continuation) throws Exception {
        try {
            String accessToken = new OAuthProviderMAPR5(this.context).getOAuthInfo().accessToken;
            Intrinsics.checkNotNullExpressionValue(accessToken, "accessToken");
            return new Outcome.Success(new OAuthInfo(accessToken));
        } catch (Exception e) {
            if (e instanceof AuthenticationException ? true : e instanceof NullPointerException) {
                logger.error("Failed to retrieve OAuthInfo", (Throwable) e);
                return new Outcome.Failure(e);
            }
            throw e;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.AuthenticationProviderImpl$getOAuthInfoBlocking$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuthenticationProviderImpl.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lcom/amazon/music/platform/Outcome;", "Lcom/amazon/music/platform/data/OAuthInfo;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.AuthenticationProviderImpl$getOAuthInfoBlocking$1", f = "AuthenticationProviderImpl.kt", i = {}, l = {58}, m = "invokeSuspend", n = {}, s = {})
    static final class C03801 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Outcome<? extends OAuthInfo>>, Object> {
        int label;

        C03801(Continuation<? super C03801> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AuthenticationProviderImpl.this.new C03801(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public /* bridge */ /* synthetic */ Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Outcome<? extends OAuthInfo>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super Outcome<OAuthInfo>>) continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Outcome<OAuthInfo>> continuation) {
            return ((C03801) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Exception {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AuthenticationProviderImpl authenticationProviderImpl = AuthenticationProviderImpl.this;
                this.label = 1;
                obj = authenticationProviderImpl.getOAuthInfo(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    @Override // com.amazon.music.platform.providers.AuthenticationProvider
    public Outcome<OAuthInfo> getOAuthInfoBlocking() {
        return (Outcome) BuildersKt__BuildersKt.runBlocking$default(null, new C03801(null), 1, null);
    }

    public Object getAdpInfo(Continuation<? super Outcome<AdpInfo>> continuation) throws Exception {
        try {
            AdpProviderMAPR5 adpProviderMAPR5 = new AdpProviderMAPR5(this.context);
            String privateKey = adpProviderMAPR5.getAdpInfo().privateKeyString;
            String adpToken = adpProviderMAPR5.getAdpInfo().adpToken;
            Intrinsics.checkNotNullExpressionValue(privateKey, "privateKey");
            Intrinsics.checkNotNullExpressionValue(adpToken, "adpToken");
            return new Outcome.Success(new AdpInfo(privateKey, adpToken));
        } catch (Exception e) {
            if (e instanceof AuthenticationException ? true : e instanceof NullPointerException) {
                logger.error("Failed to retrieve AdpInfo", (Throwable) e);
                return new Outcome.Failure(e);
            }
            throw e;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.AuthenticationProviderImpl$getAdpInfoBlocking$1, reason: invalid class name */
    /* JADX INFO: compiled from: AuthenticationProviderImpl.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lcom/amazon/music/platform/Outcome;", "Lcom/amazon/music/platform/data/AdpInfo;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.AuthenticationProviderImpl$getAdpInfoBlocking$1", f = "AuthenticationProviderImpl.kt", i = {}, l = {79}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Outcome<? extends AdpInfo>>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AuthenticationProviderImpl.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public /* bridge */ /* synthetic */ Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Outcome<? extends AdpInfo>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super Outcome<AdpInfo>>) continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Outcome<AdpInfo>> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Exception {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AuthenticationProviderImpl authenticationProviderImpl = AuthenticationProviderImpl.this;
                this.label = 1;
                obj = authenticationProviderImpl.getAdpInfo(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    @Override // com.amazon.music.platform.providers.AuthenticationProvider
    public Outcome<AdpInfo> getAdpInfoBlocking() {
        return (Outcome) BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(null), 1, null);
    }

    public Object getAuthCookies(String str, Continuation<? super Outcome<AuthCookies>> continuation) throws Exception {
        boolean z;
        List listEmptyList;
        int i = 0;
        if (str != null) {
            try {
                z = str.length() == 0;
            } catch (Exception e) {
                if (e instanceof AuthenticationException ? true : e instanceof NullPointerException) {
                    logger.error("Failed to retrieve AuthCookies", (Throwable) e);
                    return new Outcome.Failure(e);
                }
                throw e;
            }
        }
        if (z) {
            str = EndpointsProvider.get().getDomain();
        }
        String[] cookies = new AuthCookiesProviderMAPR5(this.context, str, true).getCookies();
        if (cookies == null) {
            listEmptyList = null;
        } else {
            ArrayList arrayList = new ArrayList();
            int length = cookies.length;
            while (i < length) {
                String str2 = cookies[i];
                i++;
                List<HttpCookie> list = HttpCookie.parse(str2);
                Intrinsics.checkNotNullExpressionValue(list, "parse(it)");
                CollectionsKt__MutableCollectionsKt.addAll(arrayList, list);
            }
            listEmptyList = arrayList;
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        return new Outcome.Success(new AuthCookies(listEmptyList));
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.AuthenticationProviderImpl$getAuthCookiesBlocking$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AuthenticationProviderImpl.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lcom/amazon/music/platform/Outcome;", "Lcom/amazon/music/platform/data/AuthCookies;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.AuthenticationProviderImpl$getAuthCookiesBlocking$1", f = "AuthenticationProviderImpl.kt", i = {}, l = {100}, m = "invokeSuspend", n = {}, s = {})
    static final class C03791 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Outcome<? extends AuthCookies>>, Object> {
        final /* synthetic */ String $domain;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03791(String str, Continuation<? super C03791> continuation) {
            super(2, continuation);
            this.$domain = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AuthenticationProviderImpl.this.new C03791(this.$domain, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public /* bridge */ /* synthetic */ Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Outcome<? extends AuthCookies>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super Outcome<AuthCookies>>) continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Outcome<AuthCookies>> continuation) {
            return ((C03791) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Exception {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AuthenticationProviderImpl authenticationProviderImpl = AuthenticationProviderImpl.this;
                String str = this.$domain;
                this.label = 1;
                obj = authenticationProviderImpl.getAuthCookies(str, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    @Override // com.amazon.music.platform.providers.AuthenticationProvider
    public Outcome<AuthCookies> getAuthCookiesBlocking(String domain) {
        return (Outcome) BuildersKt__BuildersKt.runBlocking$default(null, new C03791(domain, null), 1, null);
    }

    @Override // com.amazon.music.platform.providers.AuthenticationProvider
    public String getAccessTokenBlocking() {
        Outcome<OAuthInfo> oAuthInfoBlocking = getOAuthInfoBlocking();
        if (oAuthInfoBlocking instanceof Outcome.Success) {
            return ((OAuthInfo) ((Outcome.Success) oAuthInfoBlocking).getValue()).getAccessToken();
        }
        return null;
    }
}
