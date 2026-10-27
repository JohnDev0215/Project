package com.amazon.mp3.library.mylibrary.profileheader;

import android.os.SystemClock;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.graphql.generated.UserProfileQuery;
import com.amazon.music.platform.graphql.generated.fragment.Image;
import com.amazon.music.platform.graphql.generated.fragment.User;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FireFlyServiceProvider;
import com.amazon.music.views.library.models.CompactHeaderModel;
import com.apollographql.apollo3.api.ApolloResponse;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: ProfileHeaderViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 '2\u00020\u0001:\u0001'B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002J\u0016\u0010 \u001a\u00020\u000e2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u001c\u0010\"\u001a\u00020\u00182\u0012\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0$H\u0002J\f\u0010%\u001a\u00020\u0018*\u00020&H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R%\u0010\u0013\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0018\u00010\n0\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006("}, d2 = {"Lcom/amazon/mp3/library/mylibrary/profileheader/ProfileHeaderViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "CACHE_CONTROL_HEADER_KEY", "", "CACHE_CONTROL_HEADER_NO_CACHE_VALUE", "TAG", "kotlin.jvm.PlatformType", "_profileResponseLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/music/platform/Outcome;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "Lcom/amazon/music/platform/graphql/generated/UserProfileQuery$Data;", "profileModel", "Lcom/amazon/music/views/library/models/CompactHeaderModel;", "getProfileModel", "()Lcom/amazon/music/views/library/models/CompactHeaderModel;", "setProfileModel", "(Lcom/amazon/music/views/library/models/CompactHeaderModel;)V", "profileResponseLiveData", "Landroidx/lifecycle/LiveData;", "getProfileResponseLiveData", "()Landroidx/lifecycle/LiveData;", "clearData", "", "fetchProfile", "Lkotlinx/coroutines/Job;", "getElapsedTime", "", "startTime", "", "endTime", "parseApolloResponse", "response", "transformOutcomeToModel", "outcome", "Lcom/amazon/music/platform/Outcome$Success;", "buildAndSendEvent", "Lcom/amazon/music/metrics/mts/event/definition/flex/FlexEvent$Builder;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ProfileHeaderViewModel extends ViewModel {
    private final MutableLiveData<Outcome<ApolloResponse<UserProfileQuery.Data>>> _profileResponseLiveData;
    private CompactHeaderModel profileModel;
    private final LiveData<Outcome<ApolloResponse<UserProfileQuery.Data>>> profileResponseLiveData;
    private final String TAG = ProfileHeaderViewModel.class.getSimpleName();
    private final String CACHE_CONTROL_HEADER_KEY = "Cache-Control";
    private final String CACHE_CONTROL_HEADER_NO_CACHE_VALUE = "no-cache";

    /* JADX INFO: Access modifiers changed from: private */
    public final float getElapsedTime(long startTime, long endTime) {
        return endTime - startTime;
    }

    public ProfileHeaderViewModel() {
        MutableLiveData<Outcome<ApolloResponse<UserProfileQuery.Data>>> mutableLiveData = new MutableLiveData<>();
        this._profileResponseLiveData = mutableLiveData;
        this.profileResponseLiveData = mutableLiveData;
    }

    public final LiveData<Outcome<ApolloResponse<UserProfileQuery.Data>>> getProfileResponseLiveData() {
        return this.profileResponseLiveData;
    }

    public final CompactHeaderModel getProfileModel() {
        return this.profileModel;
    }

    public final void setProfileModel(CompactHeaderModel compactHeaderModel) {
        this.profileModel = compactHeaderModel;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.mylibrary.profileheader.ProfileHeaderViewModel$fetchProfile$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProfileHeaderViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.library.mylibrary.profileheader.ProfileHeaderViewModel$fetchProfile$1", f = "ProfileHeaderViewModel.kt", i = {0}, l = {50}, m = "invokeSuspend", n = {"requestStartTime"}, s = {"J$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        long J$0;
        Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ProfileHeaderViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ProfileHeaderViewModel profileHeaderViewModel;
            long j;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FireFlyServiceProvider fireFlyServiceProvider = (FireFlyServiceProvider) Providers.INSTANCE.get(FireFlyServiceProvider.class);
                if (fireFlyServiceProvider != null) {
                    ProfileHeaderViewModel profileHeaderViewModel2 = ProfileHeaderViewModel.this;
                    UserProfileQuery userProfileQuery = new UserProfileQuery();
                    FlexEvent.Builder builder = FlexEvent.builder("profileFireflyRequest");
                    Intrinsics.checkNotNullExpressionValue(builder, "builder(PROFILE_FIREFLY_REQUEST)");
                    profileHeaderViewModel2.buildAndSendEvent(builder);
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    Map<String, String> mapMapOf = MapsKt__MapsJVMKt.mapOf(new Pair(profileHeaderViewModel2.CACHE_CONTROL_HEADER_KEY, profileHeaderViewModel2.CACHE_CONTROL_HEADER_NO_CACHE_VALUE));
                    this.L$0 = profileHeaderViewModel2;
                    this.J$0 = jElapsedRealtime;
                    this.label = 1;
                    obj = fireFlyServiceProvider.query(userProfileQuery, mapMapOf, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    profileHeaderViewModel = profileHeaderViewModel2;
                    j = jElapsedRealtime;
                }
                return Unit.INSTANCE;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j = this.J$0;
            profileHeaderViewModel = (ProfileHeaderViewModel) this.L$0;
            ResultKt.throwOnFailure(obj);
            Outcome outcome = (Outcome) obj;
            FlexEvent.Builder builderWithFlexNum1 = FlexEvent.builder("profileFireflyLatency").withFlexNum1(Boxing.boxFloat(profileHeaderViewModel.getElapsedTime(j, SystemClock.elapsedRealtime())));
            Intrinsics.checkNotNullExpressionValue(builderWithFlexNum1, "builder(PROFILE_FIREFLY_…artTime, requestEndTime))");
            profileHeaderViewModel.buildAndSendEvent(builderWithFlexNum1);
            if (outcome instanceof Outcome.Success) {
                FlexEvent.Builder builder2 = FlexEvent.builder("profileFireflyResponseSuccess");
                Intrinsics.checkNotNullExpressionValue(builder2, "builder(PROFILE_FIREFLY_RESPONSE_SUCCESS)");
                profileHeaderViewModel.buildAndSendEvent(builder2);
                profileHeaderViewModel.transformOutcomeToModel((Outcome.Success) outcome);
            } else if (outcome instanceof Outcome.Failure) {
                Outcome.Failure failure = (Outcome.Failure) outcome;
                FlexEvent.Builder builderWithFlexStr1 = FlexEvent.builder("profileFireflyResponseFailure").withFlexStr1(failure.getCause().getMessage());
                Intrinsics.checkNotNullExpressionValue(builderWithFlexStr1, "builder(PROFILE_FIREFLY_…tr1(result.cause.message)");
                profileHeaderViewModel.buildAndSendEvent(builderWithFlexStr1);
                Log.error(profileHeaderViewModel.TAG, failure.getCause().getMessage());
            }
            profileHeaderViewModel._profileResponseLiveData.postValue(outcome);
            return Unit.INSTANCE;
        }
    }

    public final Job fetchProfile() {
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AnonymousClass1(null), 2, null);
    }

    public final void clearData() {
        this._profileResponseLiveData.postValue(null);
        this.profileModel = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void transformOutcomeToModel(Outcome.Success<ApolloResponse<UserProfileQuery.Data>> outcome) {
        this.profileModel = parseApolloResponse(outcome.getValue());
    }

    private final CompactHeaderModel parseApolloResponse(ApolloResponse<UserProfileQuery.Data> response) {
        UserProfileQuery.User user;
        User user2;
        User.Image image;
        Image image2;
        UserProfileQuery.Data data = (UserProfileQuery.Data) response.data;
        CompactHeaderModel compactHeaderModel = null;
        url = null;
        url = null;
        String url = null;
        compactHeaderModel = null;
        compactHeaderModel = null;
        if (data != null && (user = data.getUser()) != null && (user2 = user.getUser()) != null) {
            List<User.Image> images = user2.getImages();
            if (images != null && (image = (User.Image) CollectionsKt___CollectionsKt.first((List) images)) != null && (image2 = image.getImage()) != null) {
                url = image2.getUrl();
            }
            compactHeaderModel = new CompactHeaderModel(null, user2.getName(), user2.getTier(), url, 1, null);
        }
        return compactHeaderModel == null ? new CompactHeaderModel(null, null, null, null, 15, null) : compactHeaderModel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void buildAndSendEvent(FlexEvent.Builder builder) {
        MetricsLogger.sendEvent(builder.build());
    }
}
