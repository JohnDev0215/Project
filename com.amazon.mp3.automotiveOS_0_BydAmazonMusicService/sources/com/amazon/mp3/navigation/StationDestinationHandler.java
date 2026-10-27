package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.navigation.playback.StationPlaybackHelper;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.mp3.util.coroutines.LookupError;
import com.amazon.music.destination.StationDestination;
import com.amazon.music.destination.parser.Action;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.router.DestinationHandler;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: StationDestinationHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001eB\u001f\b\u0007\u0012\u0014\b\u0002\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00060\u0019¢\u0006\u0004\b\u001c\u0010\u001dJ\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0016R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\"\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/navigation/StationDestinationHandler;", "Lcom/amazon/music/router/DestinationHandler;", "Lcom/amazon/music/destination/StationDestination;", "Landroid/content/Context;", "context", "stationDestination", "", "navigateTo", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "coroutineExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "getCoroutineExceptionHandler", "()Lkotlinx/coroutines/CoroutineExceptionHandler;", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "", "stationKey", "Ljava/lang/String;", "getStationKey", "()Ljava/lang/String;", "setStationKey", "(Ljava/lang/String;)V", "Lkotlin/Function1;", "Lcom/amazon/mp3/util/coroutines/LookupError;", "onError", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class StationDestinationHandler implements DestinationHandler<StationDestination> {
    private static final String TAG = StationDestinationHandler.class.getSimpleName();
    private final CoroutineExceptionHandler coroutineExceptionHandler;
    private final CoroutineScope coroutineScope;
    private String stationKey;

    @JvmOverloads
    public StationDestinationHandler() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public StationDestinationHandler(Function1<? super LookupError, Unit> onError) {
        Intrinsics.checkNotNullParameter(onError, "onError");
        StationDestinationHandler$special$$inlined$CoroutineExceptionHandler$1 stationDestinationHandler$special$$inlined$CoroutineExceptionHandler$1 = new StationDestinationHandler$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE, this, onError);
        this.coroutineExceptionHandler = stationDestinationHandler$special$$inlined$CoroutineExceptionHandler$1;
        this.coroutineScope = CoroutineScopeKt.plus(GlobalScope.INSTANCE, stationDestinationHandler$special$$inlined$CoroutineExceptionHandler$1);
        this.stationKey = "";
    }

    public /* synthetic */ StationDestinationHandler(Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function1<LookupError, Unit>() { // from class: com.amazon.mp3.navigation.StationDestinationHandler.1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LookupError it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LookupError lookupError) {
                invoke2(lookupError);
                return Unit.INSTANCE;
            }
        } : function1);
    }

    public final String getStationKey() {
        return this.stationKey;
    }

    public final void setStationKey(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.stationKey = str;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.navigation.StationDestinationHandler$navigateTo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: StationDestinationHandler.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.navigation.StationDestinationHandler$navigateTo$1", f = "StationDestinationHandler.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03751 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ StationDestination $stationDestination;
        int label;
        final /* synthetic */ StationDestinationHandler this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03751(Context context, StationDestinationHandler stationDestinationHandler, StationDestination stationDestination, Continuation<? super C03751> continuation) {
            super(2, continuation);
            this.$context = context;
            this.this$0 = stationDestinationHandler;
            this.$stationDestination = stationDestination;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03751(this.$context, this.this$0, this.$stationDestination, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03751) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Intent stationsIntent = PrimeActivityFactory.getStationsIntent(this.$context);
            StationPlaybackHelper stationPlaybackHelper = new StationPlaybackHelper(this.$context);
            StationDestinationHandler stationDestinationHandler = this.this$0;
            String stationKey = this.$stationDestination.getStationKey();
            Intrinsics.checkNotNullExpressionValue(stationKey, "stationDestination.stationKey");
            stationDestinationHandler.setStationKey(stationKey);
            if (this.$stationDestination.getAction() != null) {
                if (this.$stationDestination.getAction() == Action.PLAY) {
                    stationsIntent.putExtra("com.amazon.mp3.StationsFragment.extra.ACTION", PageUriUtils.DO_PLAY_VALUE);
                    stationPlaybackHelper.play(null, this.this$0.getStationKey(), false, ControlSource.EXTERNAL, true);
                } else if (this.$stationDestination.getAction() == Action.PLAYFULL) {
                    stationsIntent.putExtra("com.amazon.mp3.StationsFragment.extra.ACTION", "playNowPlaying");
                    stationPlaybackHelper.play(null, this.this$0.getStationKey(), false, ControlSource.EXTERNAL, true);
                }
            } else {
                stationPlaybackHelper.play(null, this.$stationDestination.getStationKey(), false, ControlSource.EXTERNAL, true);
            }
            stationsIntent.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), this.$stationDestination.getRef());
            stationsIntent.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), this.$stationDestination.getTag());
            stationsIntent.putExtra("com.amazon.mp3.StationsFragment.extra.STATION_KEY", this.$stationDestination.getStationKey());
            stationsIntent.setFlags(268435456);
            this.$context.startActivity(stationsIntent);
            return Unit.INSTANCE;
        }
    }

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, StationDestination stationDestination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(stationDestination, "stationDestination");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C03751(context, this, stationDestination, null), 3, null);
    }
}
