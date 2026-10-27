package com.amazon.mp3.amplifyqueue.converters;

import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.digitalmusicxp.enums.QueueEntityIdTypeEnum;
import com.amazon.digitalmusicxp.enums.QueueEntitySubtypeEnum;
import com.amazon.digitalmusicxp.enums.SeedContextTypeEnum;
import com.amazon.digitalmusicxp.enums.SupportedFeatureEnum;
import com.amazon.digitalmusicxp.inputs.AdContextInput;
import com.amazon.digitalmusicxp.inputs.CustomerDeviceSettingsInput;
import com.amazon.digitalmusicxp.inputs.MetricsContextInput;
import com.amazon.digitalmusicxp.inputs.PlaybackOptionsInput;
import com.amazon.digitalmusicxp.inputs.QueueSeedContextInput;
import com.amazon.digitalmusicxp.models.CustomerDeviceSettings;
import com.amazon.digitalmusicxp.models.MetricsContext;
import com.amazon.digitalmusicxp.types.AdContext;
import com.amazon.digitalmusicxp.types.ConnectResponse;
import com.amazon.digitalmusicxp.types.DeviceContext;
import com.amazon.digitalmusicxp.types.EntityResponseTuple;
import com.amazon.digitalmusicxp.types.GetNextEntityResponse;
import com.amazon.digitalmusicxp.types.GetQueueViewResponse;
import com.amazon.digitalmusicxp.types.InitiateQueueRequest;
import com.amazon.digitalmusicxp.types.InitiateQueueResponse;
import com.amazon.digitalmusicxp.types.ParentalControls;
import com.amazon.mp3.amplifyqueue.model.AccountSeedContextInput;
import com.amazon.mp3.amplifyqueue.model.ClientMetricsInfo;
import com.amazon.mp3.amplifyqueue.model.ConnectResponseStatusEnum;
import com.amazon.mp3.amplifyqueue.model.ContentEncodingEnum;
import com.amazon.mp3.amplifyqueue.model.CuePoints;
import com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput;
import com.amazon.mp3.amplifyqueue.model.DeviceContextInput;
import com.amazon.mp3.amplifyqueue.model.DeviceFamilyEnum;
import com.amazon.mp3.amplifyqueue.model.ExperienceStateEnum;
import com.amazon.mp3.amplifyqueue.model.FixedSizeImage;
import com.amazon.mp3.amplifyqueue.model.GenericQueueEntity;
import com.amazon.mp3.amplifyqueue.model.GetNextEntityInput;
import com.amazon.mp3.amplifyqueue.model.GetNextQueueEntityResponseStatusEnum;
import com.amazon.mp3.amplifyqueue.model.GetQueueViewInput;
import com.amazon.mp3.amplifyqueue.model.GetQueueViewResponseStatusEnum;
import com.amazon.mp3.amplifyqueue.model.IdentityContextInput;
import com.amazon.mp3.amplifyqueue.model.ImageMetadata;
import com.amazon.mp3.amplifyqueue.model.InitiateQueueInput;
import com.amazon.mp3.amplifyqueue.model.InitiateQueueResponseContextEnum;
import com.amazon.mp3.amplifyqueue.model.LibraryAlbumSeedContextInput;
import com.amazon.mp3.amplifyqueue.model.LibraryArtistSeedContextInput;
import com.amazon.mp3.amplifyqueue.model.MetricsContextTuple;
import com.amazon.mp3.amplifyqueue.model.MetricsSpecEnum;
import com.amazon.mp3.amplifyqueue.model.OpaqueTrackSearchSeedContextInput;
import com.amazon.mp3.amplifyqueue.model.ParentalControlsInput;
import com.amazon.mp3.amplifyqueue.model.PlayableEntitySeedContextInput;
import com.amazon.mp3.amplifyqueue.model.PlaybackAssetSource;
import com.amazon.mp3.amplifyqueue.model.PlaybackInstruction;
import com.amazon.mp3.amplifyqueue.model.QueueEntityCapability;
import com.amazon.mp3.amplifyqueue.model.QueueIdHistoricalPlaybackSeedContextInput;
import com.amazon.mp3.amplifyqueue.model.QueueIdTransferSeedContextInput;
import com.amazon.mp3.amplifyqueue.model.QueueMetadata;
import com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput;
import com.amazon.mp3.amplifyqueue.model.QueueSeedTypeEnum;
import com.amazon.mp3.amplifyqueue.model.RatingEnum;
import com.amazon.mp3.amplifyqueue.model.SearchSeedContextInput;
import com.amazon.mp3.amplifyqueue.model.SeedContextInput;
import com.amazon.mp3.amplifyqueue.model.SeedContextTypeEnumV2;
import com.amazon.mp3.amplifyqueue.model.SmartLibraryPlaylistSeedContextInput;
import com.amazon.mp3.amplifyqueue.model.SourceDeviceTransferSeedContextInput;
import com.amazon.mp3.amplifyqueue.model.StartAtInput;
import com.amazon.mp3.amplifyqueue.model.StationSeedContextInput;
import com.amazon.mp3.amplifyqueue.model.StratusBenefitsInput;
import com.amazon.mp3.amplifyqueue.model.TrackAlbum;
import com.amazon.mp3.amplifyqueue.model.TrackArtist;
import com.amazon.mp3.amplifyqueue.model.TrackListSeedContextInput;
import com.amazon.mp3.amplifyqueue.model.VideoChannelSeedContextInput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InputsTransformer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000¤\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0003*\u00020\u0004H\u0000\u001a\n\u0010\u0000\u001a\u00020\u0005*\u00020\u0006\u001a\n\u0010\u0000\u001a\u00020\u0007*\u00020\b\u001a\n\u0010\u0000\u001a\u00020\t*\u00020\n\u001a\f\u0010\u0000\u001a\u00020\u000b*\u00020\fH\u0000\u001a\f\u0010\u0000\u001a\u00020\r*\u00020\u000eH\u0000\u001a\f\u0010\u0000\u001a\u00020\u000f*\u00020\u0010H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0011*\u00020\u0012H\u0000\u001a\f\u0010\u0013\u001a\u00020\u0012*\u00020\u0011H\u0000\u001a\n\u0010\u0013\u001a\u00020\u0014*\u00020\u0015\u001a\n\u0010\u0013\u001a\u00020\u0016*\u00020\u0017\u001a\f\u0010\u0013\u001a\u00020\u0018*\u00020\u0019H\u0000\u001a\n\u0010\u0013\u001a\u00020\u001a*\u00020\u001b\u001a\n\u0010\u0013\u001a\u00020\u001c*\u00020\u001d\u001a\n\u0010\u0013\u001a\u00020\u001e*\u00020\u001f\u001a\f\u0010\u0013\u001a\u00020 *\u00020!H\u0000\u001a\f\u0010\"\u001a\u00020\u0004*\u00020#H\u0000\u001a\f\u0010\"\u001a\u00020\u0002*\u00020$H\u0000\u001a\f\u0010\"\u001a\u00020%*\u00020&H\u0000\u001a\n\u0010\"\u001a\u00020\n*\u00020'\u001a\f\u0010\"\u001a\u00020(*\u00020)H\u0000¨\u0006*"}, d2 = {"toAmplify", "Lcom/amazon/mp3/amplifyqueue/model/AdContextInput;", "Lcom/amazon/digitalmusicxp/inputs/AdContextInput;", "Lcom/amazon/mp3/amplifyqueue/model/CustomerDeviceSettingsInput;", "Lcom/amazon/digitalmusicxp/inputs/CustomerDeviceSettingsInput;", "Lcom/amazon/mp3/amplifyqueue/model/GetNextEntityInput;", "Lcom/amazon/digitalmusicxp/inputs/GetNextEntityInput;", "Lcom/amazon/mp3/amplifyqueue/model/GetQueueViewInput;", "Lcom/amazon/digitalmusicxp/inputs/GetQueueViewInput;", "Lcom/amazon/mp3/amplifyqueue/model/InitiateQueueInput;", "Lcom/amazon/digitalmusicxp/inputs/InitiateQueueInput;", "Lcom/amazon/mp3/amplifyqueue/model/PlaybackOptionsInput;", "Lcom/amazon/digitalmusicxp/inputs/PlaybackOptionsInput;", "Lcom/amazon/mp3/amplifyqueue/model/QueueSeedContextInput;", "Lcom/amazon/digitalmusicxp/inputs/QueueSeedContextInput;", "Lcom/amazon/mp3/amplifyqueue/model/SeedContextInput;", "Lcom/amazon/digitalmusicxp/inputs/SeedContextInput;", "Lcom/amazon/mp3/amplifyqueue/model/ClientMetricsInfo;", "Lcom/amazon/digitalmusicxp/types/ClientMetricsInfo;", "toExternal", "Lcom/amazon/digitalmusicxp/types/ConnectResponse;", "Lcom/amazon/mp3/amplifyqueue/model/ConnectResponse;", "Lcom/amazon/digitalmusicxp/types/EntityResponseTuple;", "Lcom/amazon/mp3/amplifyqueue/model/EntityResponseTuple;", "Lcom/amazon/digitalmusicxp/types/GenericQueueEntity;", "Lcom/amazon/mp3/amplifyqueue/model/GenericQueueEntity;", "Lcom/amazon/digitalmusicxp/types/GetNextEntityResponse;", "Lcom/amazon/mp3/amplifyqueue/model/GetNextEntityResponse;", "Lcom/amazon/digitalmusicxp/types/GetQueueViewResponse;", "Lcom/amazon/mp3/amplifyqueue/model/GetQueueViewResponse;", "Lcom/amazon/digitalmusicxp/types/InitiateQueueResponse;", "Lcom/amazon/mp3/amplifyqueue/model/InitiateQueueResponse;", "Lcom/amazon/digitalmusicxp/types/MetricsContextTuple;", "Lcom/amazon/mp3/amplifyqueue/model/MetricsContextTuple;", "toInputType", "Lcom/amazon/digitalmusicxp/models/CustomerDeviceSettings;", "Lcom/amazon/digitalmusicxp/types/AdContext;", "Lcom/amazon/digitalmusicxp/inputs/DeviceContextInput;", "Lcom/amazon/digitalmusicxp/types/DeviceContext;", "Lcom/amazon/digitalmusicxp/types/InitiateQueueRequest;", "Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;", "Lcom/amazon/digitalmusicxp/types/ParentalControls;", "amplifyqueue_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class InputsTransformerKt {
    public static final ConnectResponse toExternal(com.amazon.mp3.amplifyqueue.model.ConnectResponse connectResponse) {
        Intrinsics.checkNotNullParameter(connectResponse, "<this>");
        ConnectResponseStatusEnum status = connectResponse.getStatus();
        Intrinsics.checkNotNullExpressionValue(status, "this.status");
        return new ConnectResponse(EnumsTransformerKt.toExternal(status));
    }

    public static final InitiateQueueInput toAmplify(com.amazon.digitalmusicxp.inputs.InitiateQueueInput initiateQueueInput) {
        Intrinsics.checkNotNullParameter(initiateQueueInput, "<this>");
        InitiateQueueInput.QueueSeedsStep queueSeedsStepDeviceContext = InitiateQueueInput.builder().customerId(initiateQueueInput.getCustomerId()).deviceContext((DeviceContextInput) AmplifyModelConverter.INSTANCE.convertToAmplify(initiateQueueInput.getDeviceContext(), DeviceContextInput.class));
        List<QueueSeedContextInput> queueSeeds = initiateQueueInput.getQueueSeeds();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(queueSeeds, 10));
        Iterator<T> it = queueSeeds.iterator();
        while (it.hasNext()) {
            arrayList.add(toAmplify((QueueSeedContextInput) it.next()));
        }
        InitiateQueueInput.AllowedParentalControlsStep allowedParentalControlsStepQueueSeeds = queueSeedsStepDeviceContext.queueSeeds(arrayList);
        AmplifyModelConverter amplifyModelConverter = AmplifyModelConverter.INSTANCE;
        InitiateQueueInput.BuildStep buildStepAllowedParentalControls = allowedParentalControlsStepQueueSeeds.allowedParentalControls((ParentalControlsInput) amplifyModelConverter.convertToAmplify(initiateQueueInput.getAllowedParentalControls(), ParentalControlsInput.class));
        CustomerDeviceSettingsInput customerDeviceSettings = initiateQueueInput.getCustomerDeviceSettings();
        InitiateQueueInput.BuildStep buildStepIdentityContext = buildStepAllowedParentalControls.customerDeviceSettings(customerDeviceSettings == null ? null : toAmplify(customerDeviceSettings)).identityContext((IdentityContextInput) amplifyModelConverter.convertToAmplify(initiateQueueInput.getIdentityContext(), IdentityContextInput.class));
        PlaybackOptionsInput playbackOptions = initiateQueueInput.getPlaybackOptions();
        InitiateQueueInput.BuildStep buildStepStartAt = buildStepIdentityContext.playbackOptions(playbackOptions == null ? null : toAmplify(playbackOptions)).queueId(initiateQueueInput.getQueueId()).serializedMusicRequestIdentityContext(initiateQueueInput.getSerializedMusicRequestIdentityContext()).startAt((StartAtInput) amplifyModelConverter.convertToAmplify(initiateQueueInput.getStartAt(), StartAtInput.class));
        initiateQueueInput.getStratusBenefits();
        InitiateQueueInput initiateQueueInputBuild = buildStepStartAt.stratusBenefits((StratusBenefitsInput) amplifyModelConverter.convertToAmplify(null, StratusBenefitsInput.class)).targetDeviceContext((DeviceContextInput) amplifyModelConverter.convertToAmplify(initiateQueueInput.getTargetDeviceContext(), DeviceContextInput.class)).build();
        Intrinsics.checkNotNullExpressionValue(initiateQueueInputBuild, "builder()\n            .c…va))\n            .build()");
        return initiateQueueInputBuild;
    }

    public static final com.amazon.digitalmusicxp.inputs.InitiateQueueInput toInputType(InitiateQueueRequest initiateQueueRequest) {
        Intrinsics.checkNotNullParameter(initiateQueueRequest, "<this>");
        String customerId = initiateQueueRequest.getCustomerId();
        String queueId = initiateQueueRequest.getQueueId();
        com.amazon.digitalmusicxp.inputs.DeviceContextInput inputType = toInputType(initiateQueueRequest.getDeviceContext());
        DeviceContext targetDeviceContext = initiateQueueRequest.getTargetDeviceContext();
        ArrayList arrayList = null;
        com.amazon.digitalmusicxp.inputs.DeviceContextInput inputType2 = targetDeviceContext == null ? null : toInputType(targetDeviceContext);
        CustomerDeviceSettings customerDeviceSettings = initiateQueueRequest.getCustomerDeviceSettings();
        CustomerDeviceSettingsInput inputType3 = customerDeviceSettings == null ? null : toInputType(customerDeviceSettings);
        List<QueueSeedContextInput> queueSeeds = initiateQueueRequest.getQueueSeeds();
        List<MetricsContext> metricsContext = initiateQueueRequest.getMetricsContext();
        if (metricsContext != null) {
            arrayList = new ArrayList();
            Iterator<T> it = metricsContext.iterator();
            while (it.hasNext()) {
                MetricsContextInput metricsContextInput = (MetricsContextInput) AmplifyModelConverter.INSTANCE.convertFromAmplify((MetricsContext) it.next(), MetricsContextInput.class);
                if (metricsContextInput != null) {
                    arrayList.add(metricsContextInput);
                }
            }
        }
        ArrayList arrayList2 = arrayList;
        com.amazon.digitalmusicxp.inputs.StartAtInput startAtInput = (com.amazon.digitalmusicxp.inputs.StartAtInput) AmplifyModelConverter.INSTANCE.convertFromAmplify(initiateQueueRequest.getStartAt(), com.amazon.digitalmusicxp.inputs.StartAtInput.class);
        com.amazon.digitalmusicxp.inputs.ParentalControlsInput inputType4 = toInputType(initiateQueueRequest.getAllowedParentalControls());
        String serializedMusicRequestIdentityContext = initiateQueueRequest.getSerializedMusicRequestIdentityContext();
        com.amazon.digitalmusicxp.inputs.IdentityContextInput identityContext = initiateQueueRequest.getIdentityContext();
        PlaybackOptionsInput playbackOptions = initiateQueueRequest.getPlaybackOptions();
        initiateQueueRequest.getStratusBenefitsInput();
        return new com.amazon.digitalmusicxp.inputs.InitiateQueueInput(customerId, queueId, inputType, inputType2, inputType3, queueSeeds, arrayList2, startAtInput, inputType4, serializedMusicRequestIdentityContext, identityContext, playbackOptions, null);
    }

    public static final com.amazon.digitalmusicxp.inputs.DeviceContextInput toInputType(DeviceContext deviceContext) {
        Intrinsics.checkNotNullParameter(deviceContext, "<this>");
        return new com.amazon.digitalmusicxp.inputs.DeviceContextInput(deviceContext.getDeviceId(), deviceContext.getDeviceType());
    }

    public static final com.amazon.digitalmusicxp.inputs.ParentalControlsInput toInputType(ParentalControls parentalControls) {
        Intrinsics.checkNotNullParameter(parentalControls, "<this>");
        return new com.amazon.digitalmusicxp.inputs.ParentalControlsInput(parentalControls.getHasExplicitLanguage());
    }

    public static final AdContextInput toInputType(AdContext adContext) {
        Intrinsics.checkNotNullParameter(adContext, "<this>");
        return new AdContextInput(adContext.getUserAgent(), adContext.getDeviceFamily(), adContext.getAdvertiserId());
    }

    public static final InitiateQueueResponse toExternal(com.amazon.mp3.amplifyqueue.model.InitiateQueueResponse initiateQueueResponse) {
        Intrinsics.checkNotNullParameter(initiateQueueResponse, "<this>");
        QueueMetadata queueMetadata = initiateQueueResponse.getQueueMetadata();
        com.amazon.digitalmusicxp.types.QueueMetadata external = queueMetadata == null ? null : ToExternalExtensionKt.toExternal(queueMetadata);
        GenericQueueEntity genericQueueEntity = initiateQueueResponse.getGenericQueueEntity();
        com.amazon.digitalmusicxp.types.GenericQueueEntity external2 = genericQueueEntity == null ? null : toExternal(genericQueueEntity);
        PlaybackInstruction playbackInstruction = initiateQueueResponse.getPlaybackInstruction();
        com.amazon.digitalmusicxp.types.PlaybackInstruction external3 = playbackInstruction == null ? null : ToExternalExtensionKt.toExternal(playbackInstruction);
        ExperienceStateEnum experienceState = initiateQueueResponse.getExperienceState();
        com.amazon.digitalmusicxp.enums.ExperienceStateEnum external4 = experienceState == null ? null : EnumsTransformerKt.toExternal(experienceState);
        InitiateQueueResponseContextEnum responseContext = initiateQueueResponse.getResponseContext();
        Intrinsics.checkNotNullExpressionValue(responseContext, "this.responseContext");
        return new InitiateQueueResponse(external, external2, external3, external4, EnumsTransformerKt.toExternal(responseContext));
    }

    public static final com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput toAmplify(CustomerDeviceSettingsInput customerDeviceSettingsInput) {
        List<String> listEmptyList;
        Intrinsics.checkNotNullParameter(customerDeviceSettingsInput, "<this>");
        CustomerDeviceSettingsInput.SupportedFeaturesEnumValuesStep supportedFeaturesEnumValuesStepSupportedFeatures = com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.builder().customerId(customerDeviceSettingsInput.getCustomerId()).deviceId(customerDeviceSettingsInput.getDeviceId()).deviceType(customerDeviceSettingsInput.getDeviceType()).locale(customerDeviceSettingsInput.getLocale()).timezone(customerDeviceSettingsInput.getTimezone()).adContext(toAmplify(customerDeviceSettingsInput.getAdContext())).parentalControls((ParentalControlsInput) AmplifyModelConverter.INSTANCE.convertToAmplify(customerDeviceSettingsInput.getParentalControls(), ParentalControlsInput.class)).supportedFeatures(CollectionsKt__CollectionsKt.emptyList());
        List<SupportedFeatureEnum> supportedFeatures = customerDeviceSettingsInput.getSupportedFeatures();
        if (supportedFeatures == null) {
            listEmptyList = null;
        } else {
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(supportedFeatures, 10));
            Iterator<T> it = supportedFeatures.iterator();
            while (it.hasNext()) {
                arrayList.add(((SupportedFeatureEnum) it.next()).name());
            }
            listEmptyList = arrayList;
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput customerDeviceSettingsInputBuild = supportedFeaturesEnumValuesStepSupportedFeatures.supportedFeaturesEnumValues(listEmptyList).id(customerDeviceSettingsInput.getId()).sessionId(customerDeviceSettingsInput.getSessionId()).languageOfPreference(customerDeviceSettingsInput.getLanguageOfPreference()).build();
        Intrinsics.checkNotNullExpressionValue(customerDeviceSettingsInputBuild, "builder()\n            .c…nce)\n            .build()");
        return customerDeviceSettingsInputBuild;
    }

    public static final com.amazon.digitalmusicxp.inputs.CustomerDeviceSettingsInput toInputType(CustomerDeviceSettings customerDeviceSettings) {
        Intrinsics.checkNotNullParameter(customerDeviceSettings, "<this>");
        return new com.amazon.digitalmusicxp.inputs.CustomerDeviceSettingsInput(customerDeviceSettings.getCustomerId(), customerDeviceSettings.getDeviceId(), customerDeviceSettings.getDeviceType(), customerDeviceSettings.getId(), customerDeviceSettings.getSessionId(), customerDeviceSettings.getLocale(), customerDeviceSettings.getLanguageOfPreference(), customerDeviceSettings.getTimezone(), toInputType(customerDeviceSettings.getAdContext()), toInputType(customerDeviceSettings.getParentalControls()), customerDeviceSettings.getSupportedFeatures());
    }

    public static final com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput toAmplify(QueueSeedContextInput queueSeedContextInput) {
        List<SeedContextInput> arrayList;
        Intrinsics.checkNotNullParameter(queueSeedContextInput, "<this>");
        QueueSeedContextInput.SeedContextStep seedContextStepQueueSeedType = com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput.builder().queueSeedType((QueueSeedTypeEnum) AmplifyModelConverter.getDummyEnumValue$default(AmplifyModelConverter.INSTANCE, QueueSeedTypeEnum.class, null, 2, null));
        List<com.amazon.digitalmusicxp.inputs.SeedContextInput> seedContext = queueSeedContextInput.getSeedContext();
        if (seedContext == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList<>(CollectionsKt__IterablesKt.collectionSizeOrDefault(seedContext, 10));
            Iterator<T> it = seedContext.iterator();
            while (it.hasNext()) {
                arrayList.add(toAmplify((com.amazon.digitalmusicxp.inputs.SeedContextInput) it.next()));
            }
        }
        if (arrayList == null) {
            arrayList = CollectionsKt__CollectionsKt.emptyList();
        }
        QueueSeedContextInput.BuildStep buildStepSeedContext = seedContextStepQueueSeedType.seedContext(arrayList);
        PlayModeEnum playMode = queueSeedContextInput.getPlayMode();
        QueueSeedContextInput.BuildStep buildStepQueueSeedTypeEnumValue = buildStepSeedContext.playModeEnumValue(playMode == null ? null : playMode.name()).queueSeedTypeEnumValue(queueSeedContextInput.getQueueSeedType().name());
        AmplifyModelConverter amplifyModelConverter = AmplifyModelConverter.INSTANCE;
        QueueSeedContextInput.BuildStep buildStepStation = buildStepQueueSeedTypeEnumValue.queueIdTransfer((QueueIdTransferSeedContextInput) amplifyModelConverter.convertToAmplify(queueSeedContextInput.getQueueIdTransfer(), QueueIdTransferSeedContextInput.class)).queueIdHistoricalPlayback((QueueIdHistoricalPlaybackSeedContextInput) amplifyModelConverter.convertToAmplify(queueSeedContextInput.getQueueIdHistoricalPlayback(), QueueIdHistoricalPlaybackSeedContextInput.class)).playableEntity((PlayableEntitySeedContextInput) amplifyModelConverter.convertToAmplify(queueSeedContextInput.getPlayableEntity(), PlayableEntitySeedContextInput.class)).trackList((TrackListSeedContextInput) amplifyModelConverter.convertToAmplify(queueSeedContextInput.getTrackList(), TrackListSeedContextInput.class)).libraryAlbum((LibraryAlbumSeedContextInput) amplifyModelConverter.convertToAmplify(queueSeedContextInput.getLibraryAlbum(), LibraryAlbumSeedContextInput.class)).libraryArtist((LibraryArtistSeedContextInput) amplifyModelConverter.convertToAmplify(queueSeedContextInput.getLibraryArtist(), LibraryArtistSeedContextInput.class)).station((StationSeedContextInput) amplifyModelConverter.convertToAmplify(queueSeedContextInput.getStation(), StationSeedContextInput.class));
        queueSeedContextInput.getAccount();
        QueueSeedContextInput.BuildStep buildStepAccount = buildStepStation.account((AccountSeedContextInput) amplifyModelConverter.convertToAmplify(null, AccountSeedContextInput.class));
        queueSeedContextInput.getSearch();
        QueueSeedContextInput.BuildStep buildStepSearch = buildStepAccount.search((SearchSeedContextInput) amplifyModelConverter.convertToAmplify(null, SearchSeedContextInput.class));
        queueSeedContextInput.getOpaqueTrackSearch();
        QueueSeedContextInput.BuildStep buildStepOpaqueTrackSearch = buildStepSearch.opaqueTrackSearch((OpaqueTrackSearchSeedContextInput) amplifyModelConverter.convertToAmplify(null, OpaqueTrackSearchSeedContextInput.class));
        queueSeedContextInput.getSmartLibraryPlaylist();
        QueueSeedContextInput.BuildStep buildStepSmartLibraryPlaylist = buildStepOpaqueTrackSearch.smartLibraryPlaylist((SmartLibraryPlaylistSeedContextInput) amplifyModelConverter.convertToAmplify(null, SmartLibraryPlaylistSeedContextInput.class));
        queueSeedContextInput.getVideoChannel();
        QueueSeedContextInput.BuildStep buildStepVideoChannel = buildStepSmartLibraryPlaylist.videoChannel((VideoChannelSeedContextInput) amplifyModelConverter.convertToAmplify(null, VideoChannelSeedContextInput.class));
        queueSeedContextInput.getSourceDeviceTransfer();
        com.amazon.mp3.amplifyqueue.model.QueueSeedContextInput queueSeedContextInputBuild = buildStepVideoChannel.sourceDeviceTransfer((SourceDeviceTransferSeedContextInput) amplifyModelConverter.convertToAmplify(null, SourceDeviceTransferSeedContextInput.class)).build();
        Intrinsics.checkNotNullExpressionValue(queueSeedContextInputBuild, "builder()\n            .q…va))\n            .build()");
        return queueSeedContextInputBuild;
    }

    public static final SeedContextInput toAmplify(com.amazon.digitalmusicxp.inputs.SeedContextInput seedContextInput) {
        Intrinsics.checkNotNullParameter(seedContextInput, "<this>");
        SeedContextInput.ContextTypeStep contextTypeStepBuilder = SeedContextInput.builder();
        SeedContextTypeEnum contextType = seedContextInput.getContextType();
        String strName = contextType == null ? null : contextType.name();
        Enum r4 = com.amazon.mp3.amplifyqueue.model.SeedContextTypeEnum.SPOTLIGHT;
        if (strName != null) {
            try {
                Enum enumValueOf = Enum.valueOf(com.amazon.mp3.amplifyqueue.model.SeedContextTypeEnum.class, strName);
                if (enumValueOf != null) {
                    r4 = enumValueOf;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SeedContextInput.BuildStep buildStepContextType = contextTypeStepBuilder.contextType((com.amazon.mp3.amplifyqueue.model.SeedContextTypeEnum) r4);
        SeedContextTypeEnum contextType2 = seedContextInput.getContextType();
        String strName2 = contextType2 != null ? contextType2.name() : null;
        Enum r2 = SeedContextTypeEnumV2.SPOTLIGHT;
        if (strName2 != null) {
            try {
                Enum enumValueOf2 = Enum.valueOf(SeedContextTypeEnumV2.class, strName2);
                if (enumValueOf2 != null) {
                    r2 = enumValueOf2;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        SeedContextInput seedContextInputBuild = buildStepContextType.contextTypeEnumValue(((SeedContextTypeEnumV2) r2).name()).contextValue(seedContextInput.getContextValue()).build();
        Intrinsics.checkNotNullExpressionValue(seedContextInputBuild, "builder()\n            .c…lue)\n            .build()");
        return seedContextInputBuild;
    }

    public static final com.amazon.digitalmusicxp.types.GenericQueueEntity toExternal(GenericQueueEntity genericQueueEntity) {
        Enum enumValueOf;
        ArrayList arrayList;
        Boolean bool;
        ArrayList arrayList2;
        ArrayList arrayList3;
        ArrayList arrayList4;
        Intrinsics.checkNotNullParameter(genericQueueEntity, "<this>");
        String entityReferenceId = genericQueueEntity.getEntityReferenceId();
        Intrinsics.checkNotNullExpressionValue(entityReferenceId, "this.entityReferenceId");
        String identifier = genericQueueEntity.getIdentifier();
        Intrinsics.checkNotNullExpressionValue(identifier, "this.identifier");
        String identifierTypeEnumValue = genericQueueEntity.getIdentifierTypeEnumValue();
        Enum r5 = QueueEntityIdTypeEnum.ASIN;
        if (identifierTypeEnumValue != null) {
            try {
                Enum enumValueOf2 = Enum.valueOf(QueueEntityIdTypeEnum.class, identifierTypeEnumValue);
                if (enumValueOf2 != null) {
                    r5 = enumValueOf2;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        QueueEntityIdTypeEnum queueEntityIdTypeEnum = (QueueEntityIdTypeEnum) r5;
        String queueEntitySubtypeEnumValue = genericQueueEntity.getQueueEntitySubtypeEnumValue();
        if (queueEntitySubtypeEnumValue == null) {
            enumValueOf = null;
        } else {
            try {
                enumValueOf = Enum.valueOf(QueueEntitySubtypeEnum.class, queueEntitySubtypeEnumValue);
            } catch (Exception e2) {
                e2.printStackTrace();
                enumValueOf = null;
            }
        }
        QueueEntitySubtypeEnum queueEntitySubtypeEnum = (QueueEntitySubtypeEnum) enumValueOf;
        Double durationInMs = genericQueueEntity.getDurationInMs();
        Float fValueOf = durationInMs == null ? null : Float.valueOf((float) durationInMs.doubleValue());
        String title = genericQueueEntity.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "this.title");
        String subTitle = genericQueueEntity.getSubTitle();
        ImageMetadata imageMetadata = genericQueueEntity.getImageMetadata();
        Intrinsics.checkNotNullExpressionValue(imageMetadata, "this.imageMetadata");
        com.amazon.digitalmusicxp.types.ImageMetadata external = ToExternalExtensionKt.toExternal(imageMetadata);
        RatingEnum rating = genericQueueEntity.getRating();
        com.amazon.digitalmusicxp.enums.RatingEnum external2 = rating == null ? null : EnumsTransformerKt.toExternal(rating);
        TrackAlbum album = genericQueueEntity.getAlbum();
        com.amazon.digitalmusicxp.types.TrackAlbum external3 = album == null ? null : ToExternalExtensionKt.toExternal(album);
        String concatenatedArtistName = genericQueueEntity.getConcatenatedArtistName();
        List<TrackArtist> artists = genericQueueEntity.getArtists();
        if (artists == null) {
            arrayList = null;
        } else {
            ArrayList arrayList5 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(artists, 10));
            Iterator<T> it = artists.iterator();
            while (it.hasNext()) {
                arrayList5.add(ToExternalExtensionKt.toExternal((TrackArtist) it.next()));
            }
            arrayList = arrayList5;
        }
        Boolean hasLyrics = genericQueueEntity.getHasLyrics();
        CuePoints cuePoints = genericQueueEntity.getCuePoints();
        com.amazon.digitalmusicxp.types.CuePoints external4 = cuePoints == null ? null : ToExternalExtensionKt.toExternal(cuePoints);
        QueueEntityCapability capabilities = genericQueueEntity.getCapabilities();
        Intrinsics.checkNotNullExpressionValue(capabilities, "this.capabilities");
        com.amazon.digitalmusicxp.types.QueueEntityCapability external5 = ToExternalExtensionKt.toExternal(capabilities);
        PlaybackAssetSource playbackAssetSource = genericQueueEntity.getPlaybackAssetSource();
        com.amazon.digitalmusicxp.types.PlaybackAssetSource external6 = playbackAssetSource == null ? null : ToExternalExtensionKt.toExternal(playbackAssetSource);
        List<MetricsContextTuple> metricsContext = genericQueueEntity.getMetricsContext();
        if (metricsContext == null) {
            bool = hasLyrics;
            arrayList2 = null;
        } else {
            bool = hasLyrics;
            ArrayList arrayList6 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(metricsContext, 10));
            Iterator<T> it2 = metricsContext.iterator();
            while (it2.hasNext()) {
                arrayList6.add(toExternal((MetricsContextTuple) it2.next()));
            }
            arrayList2 = arrayList6;
        }
        ClientMetricsInfo clientMetricsInfo = genericQueueEntity.getClientMetricsInfo();
        com.amazon.digitalmusicxp.types.ClientMetricsInfo external7 = clientMetricsInfo == null ? null : toExternal(clientMetricsInfo);
        MetricsSpecEnum metricsSpec = genericQueueEntity.getMetricsSpec();
        com.amazon.digitalmusicxp.enums.MetricsSpecEnum external8 = metricsSpec == null ? null : EnumsTransformerKt.toExternal(metricsSpec);
        List<ContentEncodingEnum> availableContentEncodings = genericQueueEntity.getAvailableContentEncodings();
        if (availableContentEncodings == null) {
            arrayList3 = null;
        } else {
            ArrayList arrayList7 = new ArrayList();
            Iterator<T> it3 = availableContentEncodings.iterator();
            while (it3.hasNext()) {
                com.amazon.digitalmusicxp.enums.ContentEncodingEnum external9 = EnumsTransformerKt.toExternal((ContentEncodingEnum) it3.next());
                if (external9 != null) {
                    arrayList7.add(external9);
                }
            }
            arrayList3 = arrayList7;
        }
        String playableAsin = genericQueueEntity.getPlayableAsin();
        List<String> libraryObjectIds = genericQueueEntity.getLibraryObjectIds();
        Boolean isPurchased = genericQueueEntity.getIsPurchased();
        Boolean isInstantImport = genericQueueEntity.getIsInstantImport();
        Boolean isUploaded = genericQueueEntity.getIsUploaded();
        com.amazon.mp3.amplifyqueue.model.ParentalControls parentalControls = genericQueueEntity.getParentalControls();
        ParentalControls external10 = parentalControls == null ? null : ToExternalExtensionKt.toExternal(parentalControls);
        List<FixedSizeImage> backgroundArtistImages = genericQueueEntity.getBackgroundArtistImages();
        if (backgroundArtistImages == null) {
            arrayList4 = null;
        } else {
            ArrayList arrayList8 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(backgroundArtistImages, 10));
            Iterator<T> it4 = backgroundArtistImages.iterator();
            while (it4.hasNext()) {
                arrayList8.add(ToExternalExtensionKt.toExternal((FixedSizeImage) it4.next()));
            }
            arrayList4 = arrayList8;
        }
        return new com.amazon.digitalmusicxp.types.GenericQueueEntity(entityReferenceId, identifier, queueEntityIdTypeEnum, queueEntitySubtypeEnum, fValueOf, title, subTitle, external, external2, external3, concatenatedArtistName, arrayList, bool, external4, external5, external6, arrayList2, external7, external8, arrayList3, playableAsin, libraryObjectIds, isPurchased, isInstantImport, isUploaded, external10, arrayList4);
    }

    public static final GetNextEntityInput toAmplify(com.amazon.digitalmusicxp.inputs.GetNextEntityInput getNextEntityInput) {
        Intrinsics.checkNotNullParameter(getNextEntityInput, "<this>");
        GetNextEntityInput.DeviceContextStep deviceContextStepQueueId = GetNextEntityInput.builder().customerId(getNextEntityInput.getCustomerId()).queueId(getNextEntityInput.getQueueId());
        AmplifyModelConverter amplifyModelConverter = AmplifyModelConverter.INSTANCE;
        GetNextEntityInput getNextEntityInputBuild = deviceContextStepQueueId.deviceContext((DeviceContextInput) amplifyModelConverter.convertToAmplify(getNextEntityInput.getDeviceContext(), DeviceContextInput.class)).adContext((com.amazon.mp3.amplifyqueue.model.AdContextInput) amplifyModelConverter.convertToAmplify(getNextEntityInput.getAdContext(), com.amazon.mp3.amplifyqueue.model.AdContextInput.class)).allowedParentalControls((ParentalControlsInput) amplifyModelConverter.convertToAmplify(getNextEntityInput.getAllowedParentalControls(), ParentalControlsInput.class)).currentEntityReferenceId(getNextEntityInput.getCurrentEntityReferenceId()).isUserInitiated(getNextEntityInput.getIsUserInitiated()).build();
        Intrinsics.checkNotNullExpressionValue(getNextEntityInputBuild, "builder()\n            .c…ted)\n            .build()");
        return getNextEntityInputBuild;
    }

    public static final EntityResponseTuple toExternal(com.amazon.mp3.amplifyqueue.model.EntityResponseTuple entityResponseTuple) {
        Intrinsics.checkNotNullParameter(entityResponseTuple, "<this>");
        GenericQueueEntity entity = entityResponseTuple.getEntity();
        com.amazon.digitalmusicxp.types.GenericQueueEntity external = entity == null ? null : toExternal(entity);
        GetNextQueueEntityResponseStatusEnum status = entityResponseTuple.getStatus();
        return new EntityResponseTuple(external, status != null ? EnumsTransformerKt.toExternal(status) : null);
    }

    public static final GetNextEntityResponse toExternal(com.amazon.mp3.amplifyqueue.model.GetNextEntityResponse getNextEntityResponse) {
        Intrinsics.checkNotNullParameter(getNextEntityResponse, "<this>");
        com.amazon.mp3.amplifyqueue.model.EntityResponseTuple naturalNext = getNextEntityResponse.getNaturalNext();
        EntityResponseTuple external = naturalNext == null ? null : toExternal(naturalNext);
        com.amazon.mp3.amplifyqueue.model.EntityResponseTuple userNext = getNextEntityResponse.getUserNext();
        return new GetNextEntityResponse(external, userNext != null ? toExternal(userNext) : null);
    }

    public static final GetQueueViewInput toAmplify(com.amazon.digitalmusicxp.inputs.GetQueueViewInput getQueueViewInput) {
        Intrinsics.checkNotNullParameter(getQueueViewInput, "<this>");
        GetQueueViewInput.DeviceContextStep deviceContextStepQueueId = GetQueueViewInput.builder().customerId(getQueueViewInput.getCustomerId()).queueId(getQueueViewInput.getQueueId());
        AmplifyModelConverter amplifyModelConverter = AmplifyModelConverter.INSTANCE;
        GetQueueViewInput getQueueViewInputBuild = deviceContextStepQueueId.deviceContext((DeviceContextInput) amplifyModelConverter.convertToAmplify(getQueueViewInput.getDeviceContext(), DeviceContextInput.class)).allowedParentalControls((ParentalControlsInput) amplifyModelConverter.convertToAmplify(getQueueViewInput.getAllowedParentalControls(), ParentalControlsInput.class)).currentEntityReferenceId(getQueueViewInput.getCurrentEntityReferenceId()).desiredNumberOfTracks(getQueueViewInput.getDesiredNumberOfTracks()).build();
        Intrinsics.checkNotNullExpressionValue(getQueueViewInputBuild, "builder()\n            .c…cks)\n            .build()");
        return getQueueViewInputBuild;
    }

    public static final GetQueueViewResponse toExternal(com.amazon.mp3.amplifyqueue.model.GetQueueViewResponse getQueueViewResponse) {
        Intrinsics.checkNotNullParameter(getQueueViewResponse, "<this>");
        List<GenericQueueEntity> entities = getQueueViewResponse.getEntities();
        Intrinsics.checkNotNullExpressionValue(entities, "this.entities");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(entities, 10));
        Iterator<T> it = entities.iterator();
        while (it.hasNext()) {
            arrayList.add(toExternal((GenericQueueEntity) it.next()));
        }
        GetQueueViewResponseStatusEnum status = getQueueViewResponse.getStatus();
        Intrinsics.checkNotNullExpressionValue(status, "this.status");
        return new GetQueueViewResponse(arrayList, EnumsTransformerKt.toExternal(status));
    }

    public static final com.amazon.digitalmusicxp.types.ClientMetricsInfo toExternal(ClientMetricsInfo clientMetricsInfo) {
        Intrinsics.checkNotNullParameter(clientMetricsInfo, "<this>");
        return new com.amazon.digitalmusicxp.types.ClientMetricsInfo(clientMetricsInfo.getContentBlockRef());
    }

    public static final ClientMetricsInfo toAmplify(com.amazon.digitalmusicxp.types.ClientMetricsInfo clientMetricsInfo) {
        Intrinsics.checkNotNullParameter(clientMetricsInfo, "<this>");
        ClientMetricsInfo clientMetricsInfoBuild = ClientMetricsInfo.builder().contentBlockRef(clientMetricsInfo.getContentBlockRef()).build();
        Intrinsics.checkNotNullExpressionValue(clientMetricsInfoBuild, "builder()\n            .c…Ref)\n            .build()");
        return clientMetricsInfoBuild;
    }

    public static final com.amazon.digitalmusicxp.types.MetricsContextTuple toExternal(MetricsContextTuple metricsContextTuple) {
        Intrinsics.checkNotNullParameter(metricsContextTuple, "<this>");
        String key = metricsContextTuple.getKey();
        Intrinsics.checkNotNullExpressionValue(key, "this.key");
        String value = metricsContextTuple.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "this.value");
        return new com.amazon.digitalmusicxp.types.MetricsContextTuple(key, value);
    }

    public static final com.amazon.mp3.amplifyqueue.model.AdContextInput toAmplify(AdContextInput adContextInput) {
        Intrinsics.checkNotNullParameter(adContextInput, "<this>");
        com.amazon.mp3.amplifyqueue.model.AdContextInput adContextInputBuild = com.amazon.mp3.amplifyqueue.model.AdContextInput.builder().userAgent(adContextInput.getUserAgent()).deviceFamily((DeviceFamilyEnum) AmplifyModelConverter.INSTANCE.getDummyEnumValue(DeviceFamilyEnum.class, adContextInput.getDeviceFamily().name())).deviceFamilyEnumValue(adContextInput.getDeviceFamily().name()).advertiserId(adContextInput.getAdvertiserId()).build();
        Intrinsics.checkNotNullExpressionValue(adContextInputBuild, "builder()\n            .u…rId)\n            .build()");
        return adContextInputBuild;
    }

    public static final com.amazon.mp3.amplifyqueue.model.PlaybackOptionsInput toAmplify(PlaybackOptionsInput playbackOptionsInput) {
        Intrinsics.checkNotNullParameter(playbackOptionsInput, "<this>");
        com.amazon.mp3.amplifyqueue.model.PlaybackOptionsInput playbackOptionsInputBuild = com.amazon.mp3.amplifyqueue.model.PlaybackOptionsInput.builder().loopMode(EnumsTransformerKt.toAmplify(playbackOptionsInput.getLoopMode())).shuffleMode(EnumsTransformerKt.toAmplify(playbackOptionsInput.getShuffleMode())).build();
        Intrinsics.checkNotNullExpressionValue(playbackOptionsInputBuild, "builder()\n            .l…y())\n            .build()");
        return playbackOptionsInputBuild;
    }
}
