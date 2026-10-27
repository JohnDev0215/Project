package com.amazon.music.authentication;

import android.content.Context;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPActorManager;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.music.platform.providers.ActorAuthenticationProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: ActorAuthenticationProviderImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0013\u001a\n \u0012*\u0004\u0018\u00010\u00110\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/authentication/ActorAuthenticationProviderImpl;", "Lcom/amazon/music/platform/providers/ActorAuthenticationProvider;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/identity/auth/device/api/MAPAccountManager;", "mapAccountManager", "Lcom/amazon/identity/auth/device/api/MAPAccountManager;", "Lcom/amazon/identity/auth/device/api/MAPActorManager;", "mapActorManager", "Lcom/amazon/identity/auth/device/api/MAPActorManager;", "Lcom/amazon/identity/auth/device/api/TokenManagement;", "tokenManagement", "Lcom/amazon/identity/auth/device/api/TokenManagement;", "Lcom/amazon/music/authentication/TokenKeyProvider;", "tokenKeyProvider", "Lcom/amazon/music/authentication/TokenKeyProvider;", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "logger", "Lorg/slf4j/Logger;", "<init>", "(Landroid/content/Context;Lcom/amazon/identity/auth/device/api/MAPAccountManager;Lcom/amazon/identity/auth/device/api/MAPActorManager;Lcom/amazon/identity/auth/device/api/TokenManagement;Lcom/amazon/music/authentication/TokenKeyProvider;)V", "Companion", "DMMAuthentication_release"}, k = 1, mv = {1, 6, 0})
public final class ActorAuthenticationProviderImpl implements ActorAuthenticationProvider {
    private final Context context;
    private final Logger logger;
    private final MAPAccountManager mapAccountManager;
    private final MAPActorManager mapActorManager;
    private final TokenKeyProvider tokenKeyProvider;
    private final TokenManagement tokenManagement;

    public ActorAuthenticationProviderImpl(Context context, MAPAccountManager mapAccountManager, MAPActorManager mapActorManager, TokenManagement tokenManagement, TokenKeyProvider tokenKeyProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mapAccountManager, "mapAccountManager");
        Intrinsics.checkNotNullParameter(mapActorManager, "mapActorManager");
        Intrinsics.checkNotNullParameter(tokenManagement, "tokenManagement");
        Intrinsics.checkNotNullParameter(tokenKeyProvider, "tokenKeyProvider");
        this.context = context;
        this.mapAccountManager = mapAccountManager;
        this.mapActorManager = mapActorManager;
        this.tokenManagement = tokenManagement;
        this.tokenKeyProvider = tokenKeyProvider;
        this.logger = LoggerFactory.getLogger(Reflection.getOrCreateKotlinClass(ActorAuthenticationProviderImpl.class).getSimpleName());
    }

    public /* synthetic */ ActorAuthenticationProviderImpl(Context context, MAPAccountManager mAPAccountManager, MAPActorManager mAPActorManager, TokenManagement tokenManagement, TokenKeyProvider tokenKeyProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new MAPAccountManager(context) : mAPAccountManager, (i & 4) != 0 ? new MAPActorManager(context) : mAPActorManager, (i & 8) != 0 ? new TokenManagement(context) : tokenManagement, (i & 16) != 0 ? new TokenKeyProvider() : tokenKeyProvider);
    }
}
