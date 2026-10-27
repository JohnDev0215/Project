package com.amazon.music.identity.profiles.datasource.repository;

import com.amazon.music.identity.profiles.datasource.mockprofile.MockProfileManager;
import com.amazon.music.identity.profiles.datasource.mockprofile.MockProfileManagerSingleton;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProfileDataRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/identity/profiles/datasource/repository/ProfileDataRepository;", "", "()V", "profileManager", "Lcom/amazon/music/identity/profiles/datasource/mockprofile/MockProfileManager;", "getSingleMockProfileManager", "Lio/reactivex/rxjava3/core/Single;", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ProfileDataRepository {
    private MockProfileManager profileManager = MockProfileManagerSingleton.INSTANCE.get();

    public final Single<MockProfileManager> getSingleMockProfileManager() {
        Single<MockProfileManager> singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.amazon.music.identity.profiles.datasource.repository.ProfileDataRepository$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                ProfileDataRepository.m2129getSingleMockProfileManager$lambda0(this.f$0, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create{ emitter ->\n     …profileManager)\n        }");
        return singleCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getSingleMockProfileManager$lambda-0, reason: not valid java name */
    public static final void m2129getSingleMockProfileManager$lambda0(ProfileDataRepository this$0, SingleEmitter singleEmitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        singleEmitter.onSuccess(this$0.profileManager);
    }
}
