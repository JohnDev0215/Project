package com.amazon.mp3.stability;

import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.music.curate.BuildConfig;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: BugsnagProjectPackages.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/stability/BugsnagProjectPackages;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BugsnagProjectPackages {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Set<String> packages = SetsKt__SetsKt.setOf((Object[]) new String[]{"com.amazon.music.queueexperience", "com.amazon.music.alps", "com.amazon.music.media.auto", "com.amazon.music.push", "com.amazon.music.config", BuildConfig.LIBRARY_PACKAGE_NAME, com.amazon.music.inappmessaging.BuildConfig.LIBRARY_PACKAGE_NAME, "com.amazon.music.chromecast", "com.amazon.music.connect", "com.amazon.music.voice", "com.amazon.music.platform.aom", com.amazon.music.explore.BuildConfig.LIBRARY_PACKAGE_NAME, com.amazon.music.find.BuildConfig.LIBRARY_PACKAGE_NAME, "com.amazon.music.platform.find", "com.amazon.mp3.amplifyqueue", "com.amazon.music.freetier", "com.amazon.podcast", "com.amazon.music.rxbrowse", "com.amazon.music.fire.magazine", "com.amazon.music.recommendation.rx", "com.amazon.music.castingviews", "com.amazon.music.search", "com.amazon.music.search.suggestion", "com.amazon.music.browse", "com.amazon.music.fire.cards.templates", "com.amazon.music.recommendation", "com.amazon.music.recents", "com.amazon.music.externalstorage", "com.amazon.music.media.playback", "com.amazon.music.endpoint", "com.amazon.music.downloads.worker", "com.amazon.music.downloads", "com.amazon.music.metrics.mts.event", "com.amazon.music.metrics.mts.android", "com.amazon.music.play.queue", "com.amazon.music.picassoscrolllistener", "com.amazon.music.playback.data", "com.amazon.music.runtimePreferences", "com.amazon.mp3.library.provider.source.nowplaying.rx", "com.amazon.mp3.library.provider.source.nowplaying", "com.amazon.music.RxSocial", "com.amazon.music.social", "com.amazon.music.nautilus", "com.amazon.music.validator", "com.amazon.music.wakelock", "com.amazon.music.page.api", "com.amazon.music.signin", "com.amazon.music.playback", "com.amazon.music.playback.bitrates", "com.amazon.music.weblab", "com.amazon.music.authentication.mapr5", "com.amazon.music.authentication", "com.amazon.music.widget.playnotificationicon", "com.amazon.music.station", "com.amazon.music.contentEncoding", "com.amazon.music.uicasting", "com.amazon.music.playbacknotification", "com.amazon.music.logger", "com.github.amlcurran.showcaseview", com.amazon.music.skyfire.ui.BuildConfig.LIBRARY_PACKAGE_NAME, "com.amazon.music.core", "com.amazon.music.uiinteraction", "com.amazon.music.share", "com.amazon.mp3.reactnativecore", "com.amazon.music.views.library", "com.amazon.music.widget.eux", "com.amazon.music.widget.languagepreferenceView", "com.amazon.music.ui.model.castingtile", "com.amazon.music.casting", "com.amazon.music.ui.model", "com.amazon.music.widget.fixedwidthimageview", "com.amazon.music.model", "com.amazon.music.awsiot", "com.amazon.music.subscription", "com.amazon.music.languagepreference", "com.amazon.music.widget.nowplaying", "com.amazon.music.fire.cards", "com.amazon.ui.foundations", "com.amazon.music.picassoimageloader", "com.amazon.music.imageloader", "com.amazon.music.uicontentview", "com.amazon.music.common.models", com.amazon.music.ghostlistening.BuildConfig.LIBRARY_PACKAGE_NAME, "com.amazon.music.library.thinlibrary", com.amazon.music.deeplink.BuildConfig.LIBRARY_PACKAGE_NAME, com.amazon.music.destination.parser.BuildConfig.LIBRARY_PACKAGE_NAME, com.amazon.music.router.BuildConfig.LIBRARY_PACKAGE_NAME, "com.amazon.music.library", "com.amazon.music.events", "com.amazon.music.view.endlessrecylerview", "com.amazon.music.resources", "com.amazon.music.featureflag", "com.amazon.music.featuregating", "com.amazon.music.wakeword", "com.amazon.music.account", "com.amazon.music.crypto", "com.amazon.music.arcus", "com.amazon.music.pager", com.amazon.music.destination.core.BuildConfig.LIBRARY_PACKAGE_NAME, "com.amazonaws.mobileconnectors.remoteconfiguration", com.amazon.device.crashmanager.library.BuildConfig.APPLICATION_ID, com.amazon.device.crashmanager.thirdparty.library.BuildConfig.APPLICATION_ID, "com.amazon.scrublib", "com.amazon.dp.logger.library", "com.amazon.dp.logger", "com.amazon.device.utils.det.library", "com.amazon.client.metrics.thirdparty.library", "com.amazon.client.metrics.common.library", "com.amazon.client.metrics.thirdparty.codec", "com.amazon.digitalmusicxp.android", "com.amazon.music.config.aggregator", "com.amazon.mshop.buildsettings", "MShopAndroidPeruBuildSettings", "com.amazon.mshop.androidx", "com.amazon.mshop.telemetry.publisher", "com.amazon.weblab.mobile", "MusicAndroidPeruBuildSettings", "com.amazon.music.experience"});

    /* JADX INFO: compiled from: BugsnagProjectPackages.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/stability/BugsnagProjectPackages$Companion;", "", "()V", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_PACKAGES, "", "", "getPackages", "()Ljava/util/Set;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<String> getPackages() {
            return BugsnagProjectPackages.packages;
        }
    }
}
