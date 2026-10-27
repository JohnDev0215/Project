package com.amazon.music.platform.experiences.socialprofile;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FireFlyServiceProvider;
import com.amazon.music.platform.providers.LoggingProvider;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: SocialProfileService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 )2\u00020\u0001:\u0001)B\u001f\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010$¢\u0006\u0004\b'\u0010(J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0002JM\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\u0010\b\u001a\u0004\u0018\u00010\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0011\u001a\u00020\u0002H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J!\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0011\u001a\u00020\u0002H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0013J/\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\r2\u0006\u0010\u0016\u001a\u00020\u00152\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ7\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\r2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00152\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\rH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 R\u0016\u0010\"\u001a\u0004\u0018\u00010!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010%\u001a\u0004\u0018\u00010$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, d2 = {"Lcom/amazon/music/platform/experiences/socialprofile/SocialProfileService;", "", "", "title", "", "logSuccess", "errorMsg", "logFailure", "name", "visibility", "playbackVisibility", "imageUrl", "handle", "Lcom/amazon/music/platform/Outcome;", "", "updateUser", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "id", "followUser", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unfollowUser", "", "limit", "cursor", "Lcom/amazon/music/platform/graphql/generated/ActiveSocialProfileQuery$User;", "getActiveUser", "(DLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/amazon/music/platform/experiences/socialprofile/SocialProfile;", "getUser", "(Ljava/lang/String;DLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/amazon/music/platform/graphql/generated/SocialProfileImageUploadFormQuery$ProfileImageUploadForm;", "getImageUploadForm", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/amazon/music/platform/providers/FireFlyServiceProvider;", "fireflyProvider", "Lcom/amazon/music/platform/providers/FireFlyServiceProvider;", "Lcom/amazon/music/platform/providers/LoggingProvider;", "loggingProvider", "Lcom/amazon/music/platform/providers/LoggingProvider;", "<init>", "(Lcom/amazon/music/platform/providers/FireFlyServiceProvider;Lcom/amazon/music/platform/providers/LoggingProvider;)V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class SocialProfileService {
    private static final Map<String, String> FEATURE_FLAGS_HEADERS;
    private static final Map<String, String> NO_CACHE_HEADERS;
    private static final String TAG;
    private final FireFlyServiceProvider fireflyProvider;
    private final LoggingProvider loggingProvider;

    /* JADX INFO: renamed from: com.amazon.music.platform.experiences.socialprofile.SocialProfileService$followUser$1, reason: invalid class name */
    /* JADX INFO: compiled from: SocialProfileService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.platform.experiences.socialprofile.SocialProfileService", f = "SocialProfileService.kt", i = {0}, l = {138}, m = "followUser", n = {"this"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SocialProfileService.this.followUser(null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.platform.experiences.socialprofile.SocialProfileService$getActiveUser$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SocialProfileService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.platform.experiences.socialprofile.SocialProfileService", f = "SocialProfileService.kt", i = {0}, l = {182}, m = "getActiveUser", n = {"this"}, s = {"L$0"})
    static final class C04381 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C04381(Continuation<? super C04381> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SocialProfileService.this.getActiveUser(0.0d, null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.platform.experiences.socialprofile.SocialProfileService$getImageUploadForm$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SocialProfileService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.platform.experiences.socialprofile.SocialProfileService", f = "SocialProfileService.kt", i = {0}, l = {253}, m = "getImageUploadForm", n = {"this"}, s = {"L$0"})
    static final class C04391 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C04391(Continuation<? super C04391> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SocialProfileService.this.getImageUploadForm(this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.platform.experiences.socialprofile.SocialProfileService$getUser$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SocialProfileService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.platform.experiences.socialprofile.SocialProfileService", f = "SocialProfileService.kt", i = {0}, l = {228}, m = "getUser", n = {"this"}, s = {"L$0"})
    static final class C04401 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C04401(Continuation<? super C04401> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SocialProfileService.this.getUser(null, 0.0d, null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.platform.experiences.socialprofile.SocialProfileService$unfollowUser$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SocialProfileService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.platform.experiences.socialprofile.SocialProfileService", f = "SocialProfileService.kt", i = {0}, l = {159}, m = "unfollowUser", n = {"this"}, s = {"L$0"})
    static final class C04411 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C04411(Continuation<? super C04411> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SocialProfileService.this.unfollowUser(null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.platform.experiences.socialprofile.SocialProfileService$updateUser$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SocialProfileService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.platform.experiences.socialprofile.SocialProfileService", f = "SocialProfileService.kt", i = {0}, l = {70}, m = "updateUser", n = {"this"}, s = {"L$0"})
    static final class C04421 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C04421(Continuation<? super C04421> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SocialProfileService.this.updateUser(null, null, null, null, null, this);
        }
    }

    public SocialProfileService() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public SocialProfileService(FireFlyServiceProvider fireFlyServiceProvider, LoggingProvider loggingProvider) {
        this.fireflyProvider = fireFlyServiceProvider;
        this.loggingProvider = loggingProvider;
        PlatformProviderUtilKt.freeze(this);
    }

    public /* synthetic */ SocialProfileService(FireFlyServiceProvider fireFlyServiceProvider, LoggingProvider loggingProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (FireFlyServiceProvider) Providers.INSTANCE.get(Reflection.getOrCreateKotlinClass(FireFlyServiceProvider.class)) : fireFlyServiceProvider, (i & 2) != 0 ? (LoggingProvider) Providers.INSTANCE.get(Reflection.getOrCreateKotlinClass(LoggingProvider.class)) : loggingProvider);
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        String simpleName = Reflection.getOrCreateKotlinClass(companion.getClass()).getSimpleName();
        if (simpleName == null) {
            simpleName = "SocialProfileService";
        }
        TAG = simpleName;
        FEATURE_FLAGS_HEADERS = MapsKt__MapsJVMKt.mapOf(TuplesKt.to("x-amzn-feature-flags", "use-mups"));
        NO_CACHE_HEADERS = MapsKt__MapsJVMKt.mapOf(TuplesKt.to("cache-control", "no-cache"));
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001e  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x012b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object updateUser(java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, kotlin.coroutines.Continuation<? super com.amazon.music.platform.Outcome<java.lang.Boolean>> r19) {
        /*
            Method dump skipped, instruction units count: 344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.experiences.socialprofile.SocialProfileService.updateUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object followUser(java.lang.String r5, kotlin.coroutines.Continuation<? super com.amazon.music.platform.Outcome<java.lang.Boolean>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.amazon.music.platform.experiences.socialprofile.SocialProfileService.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.music.platform.experiences.socialprofile.SocialProfileService$followUser$1 r0 = (com.amazon.music.platform.experiences.socialprofile.SocialProfileService.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.music.platform.experiences.socialprofile.SocialProfileService$followUser$1 r0 = new com.amazon.music.platform.experiences.socialprofile.SocialProfileService$followUser$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r5 = r0.L$0
            com.amazon.music.platform.experiences.socialprofile.SocialProfileService r5 = (com.amazon.music.platform.experiences.socialprofile.SocialProfileService) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L52
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            com.amazon.music.platform.graphql.generated.FollowSocialProfileMutation r6 = new com.amazon.music.platform.graphql.generated.FollowSocialProfileMutation
            com.apollographql.apollo3.api.Optional$Present r2 = new com.apollographql.apollo3.api.Optional$Present
            r2.<init>(r5)
            r6.<init>(r2)
            com.amazon.music.platform.providers.FireFlyServiceProvider r5 = r4.fireflyProvider
            if (r5 == 0) goto L55
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r6 = r5.mutation(r6, r0)
            if (r6 != r1) goto L51
            return r1
        L51:
            r5 = r4
        L52:
            com.amazon.music.platform.Outcome r6 = (com.amazon.music.platform.Outcome) r6
            goto L57
        L55:
            r6 = 0
            r5 = r4
        L57:
            boolean r0 = r6 instanceof com.amazon.music.platform.Outcome.Success
            java.lang.String r1 = "FollowSocialProfileMutation"
            if (r0 == 0) goto L6a
            r5.logSuccess(r1)
            com.amazon.music.platform.Outcome$Success r5 = new com.amazon.music.platform.Outcome$Success
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            r5.<init>(r6)
            return r5
        L6a:
            boolean r0 = r6 instanceof com.amazon.music.platform.Outcome.Failure
            java.lang.String r2 = "Uknown Firefly Apollo Client Errror"
            if (r0 == 0) goto L8e
            com.amazon.music.platform.Outcome$Failure r6 = (com.amazon.music.platform.Outcome.Failure) r6
            java.lang.Throwable r6 = r6.getCause()
            if (r6 == 0) goto L80
            java.lang.String r6 = r6.getMessage()
            if (r6 != 0) goto L7f
            goto L80
        L7f:
            r2 = r6
        L80:
            r5.logFailure(r1, r2)
            com.amazon.music.platform.Outcome$Failure r5 = new com.amazon.music.platform.Outcome$Failure
            java.lang.Exception r6 = new java.lang.Exception
            r6.<init>(r2)
            r5.<init>(r6)
            return r5
        L8e:
            com.amazon.music.platform.Outcome$Failure r5 = new com.amazon.music.platform.Outcome$Failure
            java.lang.Exception r6 = new java.lang.Exception
            r6.<init>(r2)
            r5.<init>(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.experiences.socialprofile.SocialProfileService.followUser(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object unfollowUser(java.lang.String r5, kotlin.coroutines.Continuation<? super com.amazon.music.platform.Outcome<java.lang.Boolean>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.amazon.music.platform.experiences.socialprofile.SocialProfileService.C04411
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.music.platform.experiences.socialprofile.SocialProfileService$unfollowUser$1 r0 = (com.amazon.music.platform.experiences.socialprofile.SocialProfileService.C04411) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.music.platform.experiences.socialprofile.SocialProfileService$unfollowUser$1 r0 = new com.amazon.music.platform.experiences.socialprofile.SocialProfileService$unfollowUser$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r5 = r0.L$0
            com.amazon.music.platform.experiences.socialprofile.SocialProfileService r5 = (com.amazon.music.platform.experiences.socialprofile.SocialProfileService) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4d
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            com.amazon.music.platform.graphql.generated.UnfollowSocialProfileMutation r6 = new com.amazon.music.platform.graphql.generated.UnfollowSocialProfileMutation
            r6.<init>(r5)
            com.amazon.music.platform.providers.FireFlyServiceProvider r5 = r4.fireflyProvider
            if (r5 == 0) goto L50
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r6 = r5.mutation(r6, r0)
            if (r6 != r1) goto L4c
            return r1
        L4c:
            r5 = r4
        L4d:
            com.amazon.music.platform.Outcome r6 = (com.amazon.music.platform.Outcome) r6
            goto L52
        L50:
            r6 = 0
            r5 = r4
        L52:
            boolean r0 = r6 instanceof com.amazon.music.platform.Outcome.Success
            java.lang.String r1 = "UnfollowSocialProfileMutation"
            if (r0 == 0) goto L65
            r5.logSuccess(r1)
            com.amazon.music.platform.Outcome$Success r5 = new com.amazon.music.platform.Outcome$Success
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            r5.<init>(r6)
            return r5
        L65:
            boolean r0 = r6 instanceof com.amazon.music.platform.Outcome.Failure
            java.lang.String r2 = "Uknown Firefly Apollo Client Errror"
            if (r0 == 0) goto L89
            com.amazon.music.platform.Outcome$Failure r6 = (com.amazon.music.platform.Outcome.Failure) r6
            java.lang.Throwable r6 = r6.getCause()
            if (r6 == 0) goto L7b
            java.lang.String r6 = r6.getMessage()
            if (r6 != 0) goto L7a
            goto L7b
        L7a:
            r2 = r6
        L7b:
            r5.logFailure(r1, r2)
            com.amazon.music.platform.Outcome$Failure r5 = new com.amazon.music.platform.Outcome$Failure
            java.lang.Exception r6 = new java.lang.Exception
            r6.<init>(r2)
            r5.<init>(r6)
            return r5
        L89:
            com.amazon.music.platform.Outcome$Failure r5 = new com.amazon.music.platform.Outcome$Failure
            java.lang.Exception r6 = new java.lang.Exception
            r6.<init>(r2)
            r5.<init>(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.experiences.socialprofile.SocialProfileService.unfollowUser(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object getActiveUser$default(SocialProfileService socialProfileService, double d, String str, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return socialProfileService.getActiveUser(d, str, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getActiveUser(double r6, java.lang.String r8, kotlin.coroutines.Continuation<? super com.amazon.music.platform.Outcome<com.amazon.music.platform.graphql.generated.ActiveSocialProfileQuery.User>> r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof com.amazon.music.platform.experiences.socialprofile.SocialProfileService.C04381
            if (r0 == 0) goto L13
            r0 = r9
            com.amazon.music.platform.experiences.socialprofile.SocialProfileService$getActiveUser$1 r0 = (com.amazon.music.platform.experiences.socialprofile.SocialProfileService.C04381) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.music.platform.experiences.socialprofile.SocialProfileService$getActiveUser$1 r0 = new com.amazon.music.platform.experiences.socialprofile.SocialProfileService$getActiveUser$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L36
            if (r2 != r4) goto L2e
            java.lang.Object r6 = r0.L$0
            com.amazon.music.platform.experiences.socialprofile.SocialProfileService r6 = (com.amazon.music.platform.experiences.socialprofile.SocialProfileService) r6
            kotlin.ResultKt.throwOnFailure(r9)
            goto L5b
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L36:
            kotlin.ResultKt.throwOnFailure(r9)
            com.amazon.music.platform.graphql.generated.ActiveSocialProfileQuery r9 = new com.amazon.music.platform.graphql.generated.ActiveSocialProfileQuery
            com.apollographql.apollo3.api.Optional$Present r2 = new com.apollographql.apollo3.api.Optional$Present
            r2.<init>(r8)
            r9.<init>(r6, r2)
            java.util.Map<java.lang.String, java.lang.String> r6 = com.amazon.music.platform.experiences.socialprofile.SocialProfileService.FEATURE_FLAGS_HEADERS
            java.util.Map<java.lang.String, java.lang.String> r7 = com.amazon.music.platform.experiences.socialprofile.SocialProfileService.NO_CACHE_HEADERS
            java.util.Map r6 = kotlin.collections.MapsKt.plus(r6, r7)
            com.amazon.music.platform.providers.FireFlyServiceProvider r7 = r5.fireflyProvider
            if (r7 == 0) goto L5e
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r9 = r7.query(r9, r6, r0)
            if (r9 != r1) goto L5a
            return r1
        L5a:
            r6 = r5
        L5b:
            com.amazon.music.platform.Outcome r9 = (com.amazon.music.platform.Outcome) r9
            goto L60
        L5e:
            r6 = r5
            r9 = r3
        L60:
            boolean r7 = r9 instanceof com.amazon.music.platform.Outcome.Success
            java.lang.String r8 = "SocialProfileQuery"
            if (r7 == 0) goto L83
            r6.logSuccess(r8)
            com.amazon.music.platform.Outcome$Success r6 = new com.amazon.music.platform.Outcome$Success
            com.amazon.music.platform.Outcome$Success r9 = (com.amazon.music.platform.Outcome.Success) r9
            java.lang.Object r7 = r9.getValue()
            com.apollographql.apollo3.api.ApolloResponse r7 = (com.apollographql.apollo3.api.ApolloResponse) r7
            if (r7 == 0) goto L7f
            D extends com.apollographql.apollo3.api.Operation$Data r7 = r7.data
            com.amazon.music.platform.graphql.generated.ActiveSocialProfileQuery$Data r7 = (com.amazon.music.platform.graphql.generated.ActiveSocialProfileQuery.Data) r7
            if (r7 == 0) goto L7f
            com.amazon.music.platform.graphql.generated.ActiveSocialProfileQuery$User r3 = r7.getUser()
        L7f:
            r6.<init>(r3)
            return r6
        L83:
            boolean r7 = r9 instanceof com.amazon.music.platform.Outcome.Failure
            java.lang.String r0 = "Uknown Firefly Apollo Client Errror"
            if (r7 == 0) goto La7
            com.amazon.music.platform.Outcome$Failure r9 = (com.amazon.music.platform.Outcome.Failure) r9
            java.lang.Throwable r7 = r9.getCause()
            if (r7 == 0) goto L99
            java.lang.String r7 = r7.getMessage()
            if (r7 != 0) goto L98
            goto L99
        L98:
            r0 = r7
        L99:
            r6.logFailure(r8, r0)
            com.amazon.music.platform.Outcome$Failure r6 = new com.amazon.music.platform.Outcome$Failure
            java.lang.Exception r7 = new java.lang.Exception
            r7.<init>(r0)
            r6.<init>(r7)
            return r6
        La7:
            com.amazon.music.platform.Outcome$Failure r6 = new com.amazon.music.platform.Outcome$Failure
            java.lang.Exception r7 = new java.lang.Exception
            r7.<init>(r0)
            r6.<init>(r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.experiences.socialprofile.SocialProfileService.getActiveUser(double, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object getUser$default(SocialProfileService socialProfileService, String str, double d, String str2, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        return socialProfileService.getUser(str, d, str2, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getUser(java.lang.String r6, double r7, java.lang.String r9, kotlin.coroutines.Continuation<? super com.amazon.music.platform.Outcome<com.amazon.music.platform.experiences.socialprofile.SocialProfile>> r10) {
        /*
            Method dump skipped, instruction units count: 259
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.experiences.socialprofile.SocialProfileService.getUser(java.lang.String, double, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getImageUploadForm(kotlin.coroutines.Continuation<? super com.amazon.music.platform.Outcome<com.amazon.music.platform.graphql.generated.SocialProfileImageUploadFormQuery.ProfileImageUploadForm>> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.amazon.music.platform.experiences.socialprofile.SocialProfileService.C04391
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.music.platform.experiences.socialprofile.SocialProfileService$getImageUploadForm$1 r0 = (com.amazon.music.platform.experiences.socialprofile.SocialProfileService.C04391) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.music.platform.experiences.socialprofile.SocialProfileService$getImageUploadForm$1 r0 = new com.amazon.music.platform.experiences.socialprofile.SocialProfileService$getImageUploadForm$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L36
            if (r2 != r4) goto L2e
            java.lang.Object r0 = r0.L$0
            com.amazon.music.platform.experiences.socialprofile.SocialProfileService r0 = (com.amazon.music.platform.experiences.socialprofile.SocialProfileService) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4e
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L36:
            kotlin.ResultKt.throwOnFailure(r6)
            com.amazon.music.platform.graphql.generated.SocialProfileImageUploadFormQuery r6 = new com.amazon.music.platform.graphql.generated.SocialProfileImageUploadFormQuery
            r6.<init>()
            com.amazon.music.platform.providers.FireFlyServiceProvider r2 = r5.fireflyProvider
            if (r2 == 0) goto L51
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r2.query(r6, r0)
            if (r6 != r1) goto L4d
            return r1
        L4d:
            r0 = r5
        L4e:
            com.amazon.music.platform.Outcome r6 = (com.amazon.music.platform.Outcome) r6
            goto L53
        L51:
            r0 = r5
            r6 = r3
        L53:
            boolean r1 = r6 instanceof com.amazon.music.platform.Outcome.Success
            java.lang.String r2 = "SocialProfileImageUploadFormQuery"
            if (r1 == 0) goto L76
            r0.logSuccess(r2)
            com.amazon.music.platform.Outcome$Success r0 = new com.amazon.music.platform.Outcome$Success
            com.amazon.music.platform.Outcome$Success r6 = (com.amazon.music.platform.Outcome.Success) r6
            java.lang.Object r6 = r6.getValue()
            com.apollographql.apollo3.api.ApolloResponse r6 = (com.apollographql.apollo3.api.ApolloResponse) r6
            if (r6 == 0) goto L72
            D extends com.apollographql.apollo3.api.Operation$Data r6 = r6.data
            com.amazon.music.platform.graphql.generated.SocialProfileImageUploadFormQuery$Data r6 = (com.amazon.music.platform.graphql.generated.SocialProfileImageUploadFormQuery.Data) r6
            if (r6 == 0) goto L72
            com.amazon.music.platform.graphql.generated.SocialProfileImageUploadFormQuery$ProfileImageUploadForm r3 = r6.getProfileImageUploadForm()
        L72:
            r0.<init>(r3)
            return r0
        L76:
            boolean r1 = r6 instanceof com.amazon.music.platform.Outcome.Failure
            java.lang.String r3 = "Uknown Firefly Apollo Client Errror"
            if (r1 == 0) goto L9a
            com.amazon.music.platform.Outcome$Failure r6 = (com.amazon.music.platform.Outcome.Failure) r6
            java.lang.Throwable r6 = r6.getCause()
            if (r6 == 0) goto L8c
            java.lang.String r6 = r6.getMessage()
            if (r6 != 0) goto L8b
            goto L8c
        L8b:
            r3 = r6
        L8c:
            r0.logFailure(r2, r3)
            com.amazon.music.platform.Outcome$Failure r6 = new com.amazon.music.platform.Outcome$Failure
            java.lang.Exception r0 = new java.lang.Exception
            r0.<init>(r3)
            r6.<init>(r0)
            return r6
        L9a:
            com.amazon.music.platform.Outcome$Failure r6 = new com.amazon.music.platform.Outcome$Failure
            java.lang.Exception r0 = new java.lang.Exception
            r0.<init>(r3)
            r6.<init>(r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.experiences.socialprofile.SocialProfileService.getImageUploadForm(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void logSuccess(String title) {
        LoggingProvider loggingProvider = this.loggingProvider;
        if (loggingProvider != null) {
            loggingProvider.logInfo(TAG, title + " : Success");
        }
    }

    private final void logFailure(String title, String errorMsg) {
        LoggingProvider loggingProvider = this.loggingProvider;
        if (loggingProvider != null) {
            loggingProvider.logInfo(TAG, title + " : " + errorMsg);
        }
    }
}
