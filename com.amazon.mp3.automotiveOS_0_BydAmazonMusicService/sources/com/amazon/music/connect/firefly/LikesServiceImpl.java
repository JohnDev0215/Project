package com.amazon.music.connect.firefly;

import android.util.Log;
import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
import com.amazon.music.connect.IdentityProvider;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.media.playback.ratings.LikeState;
import com.amplifyframework.core.model.ModelIdentifier;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: LikesServiceImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0010H\u0016R\u001d\u0010\b\u001a\u0004\u0018\u00010\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/connect/firefly/LikesServiceImpl;", "Lcom/amazon/music/connect/firefly/LikesService;", "identityProvider", "Lcom/amazon/music/connect/IdentityProvider;", "serviceEndpoint", "", "apiKey", "(Lcom/amazon/music/connect/IdentityProvider;Ljava/lang/String;Ljava/lang/String;)V", "fireFlyService", "Lcom/amazon/music/connect/firefly/FireFlyService;", "getFireFlyService", "()Lcom/amazon/music/connect/firefly/FireFlyService;", "fireFlyService$delegate", "Lkotlin/Lazy;", "getLikeStates", "", "Lcom/amazon/music/media/playback/ratings/LikeState;", "trackAsins", "setLikeState", "", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "likeState", "Companion", "DMMConnect_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LikesServiceImpl implements LikesService {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = LikesServiceImpl.class.getSimpleName();
    private static volatile LikesServiceImpl instance;

    /* JADX INFO: renamed from: fireFlyService$delegate, reason: from kotlin metadata */
    private final Lazy fireFlyService;

    public /* synthetic */ LikesServiceImpl(IdentityProvider identityProvider, String str, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(identityProvider, str, str2);
    }

    private LikesServiceImpl(final IdentityProvider identityProvider, final String str, final String str2) {
        this.fireFlyService = LazyKt__LazyJVMKt.lazy(new Function0<FireFlyService>() { // from class: com.amazon.music.connect.firefly.LikesServiceImpl$fireFlyService$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FireFlyService invoke() {
                return FireFlyService.INSTANCE.getInstance(identityProvider, str, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FireFlyService getFireFlyService() {
        return (FireFlyService) this.fireFlyService.getValue();
    }

    @Override // com.amazon.music.connect.firefly.LikesService
    public List<LikeState> getLikeStates(List<String> trackAsins) throws Exception {
        String string;
        JSONObject jSONObject;
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        Intrinsics.checkNotNullParameter(trackAsins, "trackAsins");
        StringBuilder sb = new StringBuilder();
        String str = "";
        for (String str2 : trackAsins) {
            sb.append(str);
            sb.append(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR + str2 + ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
            str = ", ";
        }
        String str3 = "{ user { tracks(trackIds:[" + ((Object) sb) + "]) { edgeCount edges { likeState node { id } } } } }";
        FireFlyService fireFlyService = getFireFlyService();
        JSONArray jSONArray = null;
        JSONObject jSONObjectQuery = fireFlyService == null ? null : fireFlyService.query(str3);
        JSONArray jSONArrayOptJSONArray = jSONObjectQuery == null ? null : jSONObjectQuery.optJSONArray(MAPWebViewEventHelper.KEY_ERRORS);
        if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
            throw new Exception(jSONArrayOptJSONArray.toString());
        }
        if (jSONObjectQuery != null && (jSONObject = jSONObjectQuery.getJSONObject("data")) != null && (jSONObject2 = jSONObject.getJSONObject("user")) != null && (jSONObject3 = jSONObject2.getJSONObject("tracks")) != null) {
            jSONArray = jSONObject3.getJSONArray("edges");
        }
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            int i = 0;
            int length = jSONArray.length();
            while (i < length) {
                int i2 = i + 1;
                JSONObject jSONObject4 = jSONArray.getJSONObject(i);
                if (jSONObject4 != null && (string = jSONObject4.getString("likeState")) != null) {
                    arrayList.add(LikeState.INSTANCE.fromString(string));
                }
                i = i2;
            }
        }
        Log.d(TAG, "FireFly call for NPS Likes States successful, retrieved " + arrayList.size() + " like states");
        return arrayList;
    }

    @Override // com.amazon.music.connect.firefly.LikesService
    public void setLikeState(String trackAsin, LikeState likeState) {
        Intrinsics.checkNotNullParameter(trackAsin, "trackAsin");
        Intrinsics.checkNotNullParameter(likeState, "likeState");
        if (getFireFlyService() == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(trackAsin, likeState, this, null), 3, null);
    }

    /* JADX INFO: renamed from: com.amazon.music.connect.firefly.LikesServiceImpl$setLikeState$1, reason: invalid class name */
    /* JADX INFO: compiled from: LikesServiceImpl.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.connect.firefly.LikesServiceImpl$setLikeState$1", f = "LikesServiceImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ LikeState $likeState;
        final /* synthetic */ String $trackAsin;
        int label;
        final /* synthetic */ LikesServiceImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, LikeState likeState, LikesServiceImpl likesServiceImpl, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$trackAsin = str;
            this.$likeState = likeState;
            this.this$0 = likesServiceImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$trackAsin, this.$likeState, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                String str = "mutation { setTrackLikeState(trackId: \"" + this.$trackAsin + "\", likeState: \"" + this.$likeState.getValue() + "\") { likeState } }";
                FireFlyService fireFlyService = this.this$0.getFireFlyService();
                if (fireFlyService != null) {
                    fireFlyService.query(str);
                }
                Log.d(LikesServiceImpl.TAG, "FireFly call to set like state for asin " + this.$trackAsin + " to " + this.$likeState + " state successful");
            } catch (Exception e) {
                Log.e(LikesServiceImpl.TAG, "Exception encountered while attempting to set LikeState for Asin " + this.$trackAsin + '.' + e);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: LikesServiceImpl.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \n*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/connect/firefly/LikesServiceImpl$Companion;", "", "()V", "DATA_PROPERTY_KEY", "", "EDGES_PROPERTY_KEY", "ERRORS_PROPERTY_KEY", "LIKE_STATE_PROPERTY_KEY", "MY_TRACKS_RESOURCE_BASE_PATH", "TAG", "kotlin.jvm.PlatformType", "TRACKS_PROPERTY_KEY", "USER_PROPERTY_KEY", "instance", "Lcom/amazon/music/connect/firefly/LikesServiceImpl;", "getInstance", "identityProvider", "Lcom/amazon/music/connect/IdentityProvider;", "serviceEndpoint", "apiKey", "DMMConnect_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LikesServiceImpl getInstance(IdentityProvider identityProvider, String serviceEndpoint, String apiKey) {
            Intrinsics.checkNotNullParameter(identityProvider, "identityProvider");
            Intrinsics.checkNotNullParameter(serviceEndpoint, "serviceEndpoint");
            Intrinsics.checkNotNullParameter(apiKey, "apiKey");
            LikesServiceImpl likesServiceImpl = LikesServiceImpl.instance;
            if (likesServiceImpl == null) {
                synchronized (this) {
                    likesServiceImpl = LikesServiceImpl.instance;
                    if (likesServiceImpl == null) {
                        likesServiceImpl = new LikesServiceImpl(identityProvider, serviceEndpoint, apiKey, null);
                        LikesServiceImpl.instance = likesServiceImpl;
                    }
                }
            }
            return likesServiceImpl;
        }
    }
}
