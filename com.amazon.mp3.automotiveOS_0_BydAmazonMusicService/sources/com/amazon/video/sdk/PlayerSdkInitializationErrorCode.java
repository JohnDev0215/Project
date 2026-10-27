package com.amazon.video.sdk;

import com.amazon.avod.core.InitializationErrorCode;
import com.amazon.avod.playback.core.PlayerInitializationErrorCode;
import com.amazon.avod.util.Preconditions2;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 com.amazon.video.sdk.PlayerSdkInitializationErrorCode, still in use, count: 1, list:
  (r0v0 com.amazon.video.sdk.PlayerSdkInitializationErrorCode) from 0x0046: INVOKE 
  (wrap:com.amazon.avod.playback.core.PlayerInitializationErrorCode:0x0044: SGET  A[WRAPPED] (LINE:93) com.amazon.avod.playback.core.PlayerInitializationErrorCode.NULL_PACKAGE_NAME com.amazon.avod.playback.core.PlayerInitializationErrorCode)
  (r0v0 com.amazon.video.sdk.PlayerSdkInitializationErrorCode)
 STATIC call: kotlin.TuplesKt.to(java.lang.Object, java.lang.Object):kotlin.Pair A[MD:<A, B>:(A, B):kotlin.Pair<A, B> (m), WRAPPED] (LINE:93)
	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:252)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:180)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: PlayerSdk.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0080\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/amazon/video/sdk/PlayerSdkInitializationErrorCode;", "", "Lcom/amazon/video/sdk/FailureErrorCode;", "(Ljava/lang/String;I)V", "errorCode", "", "NULL_PACKAGE_NAME", "SERVER_CONFIG_FETCH_ERROR", "NO_SUPPORTED_RENDERER_SCHEMES", "DOWNLOAD_PERSISTENCE_NOT_READABLE", "UNKNOWN_ERROR", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayerSdkInitializationErrorCode implements FailureErrorCode {
    NULL_PACKAGE_NAME,
    SERVER_CONFIG_FETCH_ERROR,
    NO_SUPPORTED_RENDERER_SCHEMES,
    DOWNLOAD_PERSISTENCE_NOT_READABLE,
    UNKNOWN_ERROR;

    private static final HashMap<PlayerInitializationErrorCode, PlayerSdkInitializationErrorCode> playerInitializationErrorToSdkErrorMap;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static PlayerSdkInitializationErrorCode valueOf(String str) {
        return (PlayerSdkInitializationErrorCode) Enum.valueOf(PlayerSdkInitializationErrorCode.class, str);
    }

    public static PlayerSdkInitializationErrorCode[] values() {
        return (PlayerSdkInitializationErrorCode[]) $VALUES.clone();
    }

    private PlayerSdkInitializationErrorCode() {
    }

    static {
        Map mapCheckFullKeyMapping = Preconditions2.checkFullKeyMapping(PlayerInitializationErrorCode.class, MapsKt__MapsKt.hashMapOf(TuplesKt.to(PlayerInitializationErrorCode.NULL_PACKAGE_NAME, new PlayerSdkInitializationErrorCode()), TuplesKt.to(PlayerInitializationErrorCode.SERVER_CONFIG_FETCH_ERROR, new PlayerSdkInitializationErrorCode()), TuplesKt.to(PlayerInitializationErrorCode.NO_SUPPORTED_RENDERER_SCHEMES, new PlayerSdkInitializationErrorCode()), TuplesKt.to(PlayerInitializationErrorCode.DOWNLOAD_PERSISTENCE_NOT_READABLE, new PlayerSdkInitializationErrorCode())));
        Intrinsics.checkNotNullExpressionValue(mapCheckFullKeyMapping, "checkFullKeyMapping(\n   …          )\n            )");
        playerInitializationErrorToSdkErrorMap = (HashMap) mapCheckFullKeyMapping;
    }

    /* JADX INFO: compiled from: PlayerSdk.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000bR\"\u0010\u0003\u001a\u0016\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/video/sdk/PlayerSdkInitializationErrorCode$Companion;", "", "()V", "playerInitializationErrorToSdkErrorMap", "Ljava/util/HashMap;", "Lcom/amazon/avod/playback/core/PlayerInitializationErrorCode;", "kotlin.jvm.PlatformType", "Lcom/amazon/video/sdk/PlayerSdkInitializationErrorCode;", "fromInitializationErrorCode", "errorCode", "Lcom/amazon/avod/core/InitializationErrorCode;", "fromInitializationErrorCode$AmazonAndroidVideoPlayer_release", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PlayerSdkInitializationErrorCode fromInitializationErrorCode$AmazonAndroidVideoPlayer_release(InitializationErrorCode errorCode) {
            Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            try {
                HashMap map = PlayerSdkInitializationErrorCode.playerInitializationErrorToSdkErrorMap;
                String strName = errorCode.name();
                Intrinsics.checkNotNullExpressionValue(strName, "errorCode.name()");
                Object obj = map.get(PlayerInitializationErrorCode.valueOf(strName));
                if (obj == null) {
                    obj = PlayerSdkInitializationErrorCode.UNKNOWN_ERROR;
                }
                return (PlayerSdkInitializationErrorCode) obj;
            } catch (IllegalArgumentException unused) {
                return PlayerSdkInitializationErrorCode.UNKNOWN_ERROR;
            }
        }
    }

    public String errorCode() {
        return name();
    }
}
