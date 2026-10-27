package com.amazon.video.sdk;

import com.amazon.video.sdk.PlayerLoaderState;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: PlayerLoader.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0002\u0018\u00002'\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0001j\u0002`\bB\u0007¢\u0006\u0004\b\u0014\u0010\u0015J-\u0010\t\u001a'\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0001j\u0002`\bH\u0002J5\u0010\u000b\u001a'\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0001j\u0002`\b2\u0006\u0010\n\u001a\u00020\u0007H\u0002J\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\f\u001a\u00020\u0002H\u0096\u0002RG\u0010\u000e\u001a'\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0001j\u0002`\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/video/sdk/PlayerLoaderState;", "Lkotlin/Function1;", "Lcom/amazon/video/sdk/PlayerSdkConfig;", "Lkotlin/ParameterName;", "name", "i", "Ljava/util/concurrent/Callable;", "Lcom/amazon/video/sdk/PlayerSdk;", "Lcom/amazon/video/sdk/FromConfig;", "preInitialize", "sdk", "initialized", "config", "invoke", "stateFunction", "Lkotlin/jvm/functions/Function1;", "getStateFunction", "()Lkotlin/jvm/functions/Function1;", "setStateFunction", "(Lkotlin/jvm/functions/Function1;)V", "<init>", "()V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
final class PlayerLoaderState implements Function1<PlayerSdkConfig, Callable<PlayerSdk>> {
    private Function1<? super PlayerSdkConfig, ? extends Callable<PlayerSdk>> stateFunction = preInitialize();

    public final void setStateFunction(Function1<? super PlayerSdkConfig, ? extends Callable<PlayerSdk>> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.stateFunction = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public Callable<PlayerSdk> invoke(PlayerSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return this.stateFunction.invoke(config);
    }

    /* JADX INFO: renamed from: com.amazon.video.sdk.PlayerLoaderState$preInitialize$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlayerLoader.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Ljava/util/concurrent/Callable;", "Lcom/amazon/video/sdk/PlayerSdk;", "it", "Lcom/amazon/video/sdk/PlayerSdkConfig;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    static final class C04571 extends Lambda implements Function1<PlayerSdkConfig, Callable<PlayerSdk>> {
        C04571() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Callable<PlayerSdk> invoke(PlayerSdkConfig it) {
            Intrinsics.checkNotNullParameter(it, "it");
            final PlayerSdkImpl playerSdkImpl = new PlayerSdkImpl(it);
            PlayerLoaderState playerLoaderState = PlayerLoaderState.this;
            playerLoaderState.setStateFunction(playerLoaderState.initialized(playerSdkImpl));
            return new Callable() { // from class: com.amazon.video.sdk.PlayerLoaderState$preInitialize$1$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return PlayerLoaderState.C04571.m2479invoke$lambda0(playerSdkImpl);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invoke$lambda-0, reason: not valid java name */
        public static final PlayerSdk m2479invoke$lambda0(PlayerSdkImpl sdk) {
            Intrinsics.checkNotNullParameter(sdk, "$sdk");
            return sdk.initialize();
        }
    }

    private final Function1<PlayerSdkConfig, Callable<PlayerSdk>> preInitialize() {
        return new C04571();
    }

    /* JADX INFO: renamed from: com.amazon.video.sdk.PlayerLoaderState$initialized$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlayerLoader.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Ljava/util/concurrent/Callable;", "Lcom/amazon/video/sdk/PlayerSdk;", "it", "Lcom/amazon/video/sdk/PlayerSdkConfig;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function1<PlayerSdkConfig, Callable<PlayerSdk>> {
        final /* synthetic */ PlayerSdk $sdk;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PlayerSdk playerSdk) {
            super(1);
            this.$sdk = playerSdk;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Callable<PlayerSdk> invoke(final PlayerSdkConfig it) {
            Intrinsics.checkNotNullParameter(it, "it");
            final PlayerSdk playerSdk = this.$sdk;
            return new Callable() { // from class: com.amazon.video.sdk.PlayerLoaderState$initialized$1$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return PlayerLoaderState.AnonymousClass1.m2477invoke$lambda0(it, playerSdk);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invoke$lambda-0, reason: not valid java name */
        public static final PlayerSdk m2477invoke$lambda0(PlayerSdkConfig it, PlayerSdk sdk) {
            Intrinsics.checkNotNullParameter(it, "$it");
            Intrinsics.checkNotNullParameter(sdk, "$sdk");
            if (Intrinsics.areEqual(it, sdk.getSdkConfig())) {
                return sdk;
            }
            throw new RuntimeException("Updating PlayerSdkConfig is not allowed.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Function1<PlayerSdkConfig, Callable<PlayerSdk>> initialized(PlayerSdk sdk) {
        return new AnonymousClass1(sdk);
    }
}
