package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playlist.api.MC2PlaylistApi;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.coroutines.LookupError;
import com.amazon.music.destination.PersonalizedPlaylistsDestination;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.router.DestinationHandler;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: PersonalizedPlaylistsDestinationHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u001d\b\u0007\u0012\u0014\b\u0002\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\u0010\u0007J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0018\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0002J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0015\u001a\u00020\u0012H\u0002J\u0018\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/navigation/PersonalizedPlaylistsDestinationHandler;", "Lcom/amazon/music/router/DestinationHandler;", "Lcom/amazon/music/destination/PersonalizedPlaylistsDestination;", "onError", "Lkotlin/Function1;", "Lcom/amazon/mp3/util/coroutines/LookupError;", "", "(Lkotlin/jvm/functions/Function1;)V", "coroutineExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getIntentForPersonalizedPlaylists", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "destination", "asin", "", "getIntentForPlaylists", "getPersonalizedPlaylistsType", "scheme", "navigateTo", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PersonalizedPlaylistsDestinationHandler implements DestinationHandler<PersonalizedPlaylistsDestination> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = PersonalizedPlaylistsDestinationHandler.class.getSimpleName();
    private final CoroutineExceptionHandler coroutineExceptionHandler;
    private final CoroutineScope coroutineScope;

    @JvmOverloads
    public PersonalizedPlaylistsDestinationHandler() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public PersonalizedPlaylistsDestinationHandler(Function1<? super LookupError, Unit> onError) {
        Intrinsics.checkNotNullParameter(onError, "onError");
        PersonalizedPlaylistsDestinationHandler$special$$inlined$CoroutineExceptionHandler$1 personalizedPlaylistsDestinationHandler$special$$inlined$CoroutineExceptionHandler$1 = new PersonalizedPlaylistsDestinationHandler$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE, onError);
        this.coroutineExceptionHandler = personalizedPlaylistsDestinationHandler$special$$inlined$CoroutineExceptionHandler$1;
        this.coroutineScope = CoroutineScopeKt.plus(GlobalScope.INSTANCE, personalizedPlaylistsDestinationHandler$special$$inlined$CoroutineExceptionHandler$1);
    }

    public /* synthetic */ PersonalizedPlaylistsDestinationHandler(Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function1<LookupError, Unit>() { // from class: com.amazon.mp3.navigation.PersonalizedPlaylistsDestinationHandler.1
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

    /* JADX INFO: compiled from: PersonalizedPlaylistsDestinationHandler.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/navigation/PersonalizedPlaylistsDestinationHandler$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return PersonalizedPlaylistsDestinationHandler.TAG;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.navigation.PersonalizedPlaylistsDestinationHandler$navigateTo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PersonalizedPlaylistsDestinationHandler.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.navigation.PersonalizedPlaylistsDestinationHandler$navigateTo$1", f = "PersonalizedPlaylistsDestinationHandler.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03731 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ PersonalizedPlaylistsDestination $destination;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03731(Context context, PersonalizedPlaylistsDestination personalizedPlaylistsDestination, Continuation<? super C03731> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$destination = personalizedPlaylistsDestination;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PersonalizedPlaylistsDestinationHandler.this.new C03731(this.$context, this.$destination, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03731) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            JSONArray jSONArray;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Intent intentForPlaylists = PersonalizedPlaylistsDestinationHandler.this.getIntentForPlaylists(this.$context, this.$destination);
            String personalizedPlaylistsType = null;
            if (this.$destination.getPersonalizedPlaylistsType() != null) {
                PersonalizedPlaylistsDestinationHandler personalizedPlaylistsDestinationHandler = PersonalizedPlaylistsDestinationHandler.this;
                String personalizedPlaylistsType2 = this.$destination.getPersonalizedPlaylistsType();
                Intrinsics.checkNotNullExpressionValue(personalizedPlaylistsType2, "destination.personalizedPlaylistsType");
                personalizedPlaylistsType = personalizedPlaylistsDestinationHandler.getPersonalizedPlaylistsType(personalizedPlaylistsType2);
                Log.debug(PersonalizedPlaylistsDestinationHandler.INSTANCE.getTAG(), Intrinsics.stringPlus("playlistTypes: ", personalizedPlaylistsType));
            }
            if (personalizedPlaylistsType != null) {
                try {
                    JSONObject personalizedPlaylists = new MC2PlaylistApi().getPersonalizedPlaylists(new JSONObject().put("playlistTypes", new JSONArray((Collection<?>) CollectionsKt__CollectionsJVMKt.listOf(personalizedPlaylistsType))));
                    if (personalizedPlaylists != null && (jSONArray = personalizedPlaylists.getJSONArray("playlists")) != null && jSONArray.length() > 0) {
                        String asin = jSONArray.getJSONObject(0).getString("playlistId");
                        Log.debug(PersonalizedPlaylistsDestinationHandler.INSTANCE.getTAG(), Intrinsics.stringPlus("playlistId: ", asin));
                        PersonalizedPlaylistsDestinationHandler personalizedPlaylistsDestinationHandler2 = PersonalizedPlaylistsDestinationHandler.this;
                        Context context = this.$context;
                        PersonalizedPlaylistsDestination personalizedPlaylistsDestination = this.$destination;
                        Intrinsics.checkNotNullExpressionValue(asin, "asin");
                        intentForPlaylists = personalizedPlaylistsDestinationHandler2.getIntentForPersonalizedPlaylists(context, personalizedPlaylistsDestination, asin);
                    }
                } catch (Exception e) {
                    if (e instanceof JSONException) {
                        Log.debug(PersonalizedPlaylistsDestinationHandler.INSTANCE.getTAG(), "Error while parsing getPersonalizedPlaylists response", e);
                    } else {
                        Log.error(PersonalizedPlaylistsDestinationHandler.INSTANCE.getTAG(), "Encountered an exception when calling getPersonalizedPlaylists API", e);
                    }
                }
            }
            this.$context.startActivity(intentForPlaylists);
            return Unit.INSTANCE;
        }
    }

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, PersonalizedPlaylistsDestination destination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(destination, "destination");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C03731(context, destination, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getPersonalizedPlaylistsType(String scheme) {
        JsonObject jsonObject;
        if (scheme == null) {
            return null;
        }
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof ConfigurationProvider)) {
            interfaceProvider = null;
        }
        ConfigurationProvider configurationProvider = (ConfigurationProvider) interfaceProvider;
        Configuration configuration = configurationProvider instanceof Configuration ? (Configuration) configurationProvider : null;
        JsonArray jsonArray = configuration == null ? null : (JsonArray) Configuration.DefaultImpls.value$default(configuration, "personalized_playlists_schemas", Reflection.getOrCreateKotlinClass(JsonArray.class), null, 4, null);
        if (jsonArray == null || (jsonObject = JsonElementKt.getJsonObject(jsonArray.get(0))) == null || !jsonObject.containsKey((Object) scheme)) {
            return null;
        }
        return JsonElementKt.getJsonPrimitive((JsonElement) MapsKt__MapsKt.getValue(jsonObject, scheme)).getContent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Intent getIntentForPersonalizedPlaylists(Context context, PersonalizedPlaylistsDestination destination, String asin) {
        Intent intent = LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.PrimePlaylists.getContentUri("cirrus", asin));
        intent.addFlags(268435456);
        intent.putExtra("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION", destination.getAction());
        intent.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), destination.getRef());
        intent.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), destination.getTag());
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Intent getIntentForPlaylists(Context context, PersonalizedPlaylistsDestination destination) {
        Intent intent = PrimeActivityFactory.getPlaylistsIntent(context);
        intent.addFlags(268435456);
        intent.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), destination.getRef());
        intent.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), destination.getTag());
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        return intent;
    }
}
