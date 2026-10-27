package com.amazon.avod.media.drm;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.EPrivacyConsentData;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.drm.db.DrmStoredRights;
import com.amazon.avod.drm.db.DrmStoredRightsWithId;
import com.amazon.avod.drm.event.EventDispatchingLicenseAcquisitionEventReporter;
import com.amazon.avod.drm.event.LicenseAcquisitionEventReporter;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.event.EventDispatcher;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.qahooks.PlaybackQAEvent;
import com.amazon.avod.qahooks.PlaybackQAMetric;
import com.amazon.avod.qahooks.QALog;
import com.amazon.avod.util.DLog;
import com.amazon.video.sdk.player.PlaybackEnvelope;
import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DrmLicenseAcquirer {
    private final BaseDrmSystem mDrmSystem;
    private final EventDispatchingLicenseAcquisitionEventReporter.Factory mReporterFactory;

    public DrmLicenseAcquirer(@Nonnull BaseDrmSystem baseDrmSystem) {
        this(baseDrmSystem, EventDispatchingLicenseAcquisitionEventReporter.FACTORY);
    }

    DrmLicenseAcquirer(@Nonnull BaseDrmSystem baseDrmSystem, @Nonnull EventDispatchingLicenseAcquisitionEventReporter.Factory factory) {
        this.mDrmSystem = (BaseDrmSystem) Preconditions.checkNotNull(baseDrmSystem, "drmSystem");
        this.mReporterFactory = (EventDispatchingLicenseAcquisitionEventReporter.Factory) Preconditions.checkNotNull(factory, "reporterFactory");
    }

    @Nullable
    public String doLicensing(@Nonnull ContentSession contentSession, @Nonnull Map<String, String> map, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, @Nullable String str, @Nullable PlaybackEnvelope playbackEnvelope) throws MediaException {
        String str2;
        Preconditions.checkNotNull(contentSession, "contentSession");
        Preconditions.checkNotNull(map, "playbackSessionContext");
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        Preconditions.checkNotNull(rendererSchemeType, "rendererSchemeType");
        if (!contentSession.isEncryptedContent()) {
            return null;
        }
        ContentSessionContext context = contentSession.getContext();
        String titleId = context.getContent().getTitleId();
        String encodedEncryptionHeader = contentSession.getEncodedEncryptionHeader();
        ContentManagementEventBus eventDispatcher = contentSession.getEventDispatcher();
        ContentType contentType = context.getSpecification().getContentType();
        String playbackToken = context.getSpecification().getPlaybackToken();
        boolean zIsDashUrl = ContentUrl.isDashUrl(context.getContentUrl());
        String consumptionId = contentSession.getConsumptionId();
        EPrivacyConsentData ePrivacyConsent = context.getSpecification().getEPrivacyConsent();
        boolean z = !this.mDrmSystem.isPlaybackActive();
        switch (AnonymousClass1.$SwitchMap$com$amazon$avod$content$ContentSessionType[context.getSessionType().ordinal()]) {
            case 1:
                return getLicense(titleId, ConsumptionType.Download, contentType, encodedEncryptionHeader, map, drmScheme, rendererSchemeType, eventDispatcher, zIsDashUrl, str, false, consumptionId, playbackToken, playbackEnvelope, ePrivacyConsent);
            case 2:
                return getLicense(titleId, ConsumptionType.Download, contentType, encodedEncryptionHeader, map, drmScheme, rendererSchemeType, eventDispatcher, zIsDashUrl, str, z, consumptionId, playbackToken, playbackEnvelope, ePrivacyConsent);
            case 3:
            case 4:
                if (!this.mDrmSystem.isLicensingEnabled(drmScheme, rendererSchemeType)) {
                    DLog.logf("Skipping license acquisition for content precache of %s, because BaseDrmSystem is currently locked for ongoing playback", titleId);
                } else {
                    try {
                        str2 = titleId;
                    } catch (DrmLicensingException e) {
                        e = e;
                        str2 = titleId;
                    }
                    try {
                        return getLicense(titleId, ConsumptionType.Streaming, contentType, encodedEncryptionHeader, map, drmScheme, rendererSchemeType, eventDispatcher, zIsDashUrl, str, false, consumptionId, playbackToken, playbackEnvelope, ePrivacyConsent);
                    } catch (DrmLicensingException e2) {
                        e = e2;
                        DLog.logf("Swallowing licensing exception %s during content precache of %s", e, str2);
                        return str;
                    }
                }
                return str;
            case 5:
            case 6:
                return getLicense(titleId, ConsumptionType.Streaming, contentType, encodedEncryptionHeader, map, drmScheme, rendererSchemeType, eventDispatcher, zIsDashUrl, str, z, consumptionId, playbackToken, playbackEnvelope, ePrivacyConsent);
            default:
                throw new IllegalArgumentException("Unrecognized sessionType");
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.drm.DrmLicenseAcquirer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$ContentSessionType;

        static {
            int[] iArr = new int[ContentSessionType.values().length];
            $SwitchMap$com$amazon$avod$content$ContentSessionType = iArr;
            try {
                iArr[ContentSessionType.DOWNLOAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.PROGRESSIVE_PLAYBACK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.LIVE_CACHE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.CONTENT_CACHE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING_CONTINUOUS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Nullable
    private String getLicense(String str, ConsumptionType consumptionType, ContentType contentType, String str2, @Nonnull Map<String, String> map, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, EventDispatcher eventDispatcher, boolean z, @Nullable String str3, boolean z2, @Nullable String str4, @Nullable String str5, @Nullable PlaybackEnvelope playbackEnvelope, @Nonnull EPrivacyConsentData ePrivacyConsentData) throws DrmLicensingException {
        QALog qALog;
        LicenseAcquisitionEventReporter licenseAcquisitionEventReporterNewEventReporter = this.mReporterFactory.newEventReporter(eventDispatcher);
        QALog qALogAddMetric = QALog.newQALog(consumptionType == ConsumptionType.Download ? PlaybackQAEvent.DOWNLOAD_ACQUIRE_LICENSE : PlaybackQAEvent.PLAYBACK_ACQUIRE_LICENSE).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.ASIN, str).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.TITLE_ID, str);
        try {
            qALog = qALogAddMetric;
        } catch (DrmLicensingException e) {
            e = e;
            qALog = qALogAddMetric;
        }
        try {
            DrmStoredRightsWithId drmStoredRightsWithIdAcquireLicense = this.mDrmSystem.acquireLicense(str, str2, map, drmScheme, rendererSchemeType, consumptionType, contentType, licenseAcquisitionEventReporterNewEventReporter, z, str3, z2, str4, str5, playbackEnvelope);
            DrmStoredRights drmStoredRights = drmStoredRightsWithIdAcquireLicense.getDrmStoredRights();
            if (drmStoredRights != null) {
                qALog.addMetric(PlaybackQAMetric.EXPIRATION, drmStoredRights.getExpiryTimeInSeconds());
            }
            qALog.addMetric((QALog.QALoggableMetric) PlaybackQAMetric.SUCCESS, true).send();
            return drmStoredRightsWithIdAcquireLicense.getOfflineKeyId();
        } catch (DrmLicensingException e2) {
            e = e2;
            qALog.addMetric((QALog.QALoggableMetric) PlaybackQAMetric.SUCCESS, false).send();
            throw e;
        }
    }
}
