package com.amazon.music.platform.aom.launcher;

import com.amazon.music.platform.aom.Configuration;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: LauncherConfiguration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/platform/aom/launcher/LauncherConfiguration;", "", "()V", "Companion", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class LauncherConfiguration {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Configuration CONFIGURATION = new Configuration(CollectionsKt__CollectionsJVMKt.listOf(new Catalog(CatalogType.GOOGLE_PLAY_STORE, CollectionsKt__CollectionsKt.listOf((Object[]) new IdentifierType[]{IdentifierType.URI_HTTP_SCHEME, IdentifierType.URI_CUSTOM_SCHEME, IdentifierType.URI_APP_IDENTIFIER_SCHEME, IdentifierType.URI_ANDROID_INTENT_SCHEME}))));

    /* JADX INFO: compiled from: LauncherConfiguration.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/platform/aom/launcher/LauncherConfiguration$Companion;", "", "()V", "CONFIGURATION", "Lcom/amazon/music/platform/aom/Configuration;", "getCONFIGURATION", "()Lcom/amazon/music/platform/aom/Configuration;", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Configuration getCONFIGURATION() {
            return LauncherConfiguration.CONFIGURATION;
        }
    }
}
