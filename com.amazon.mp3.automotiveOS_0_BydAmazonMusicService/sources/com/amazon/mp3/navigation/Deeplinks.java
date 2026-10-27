package com.amazon.mp3.navigation;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.explore.handlers.ArtistDetailDestinationHandler;
import com.amazon.mp3.navigation.dialogtargetconverter.SignupDestinationDialogTargetConverter;
import com.amazon.mp3.navigation.webtargetconverter.CampaignWebTargetConverter;
import com.amazon.mp3.navigation.webtargetconverter.DigitalSubscriptionsWebTargetConverter;
import com.amazon.mp3.navigation.webtargetconverter.SignupWebTargetConverter;
import com.amazon.mp3.navigation.webtargetconverter.SubscriptionSettingsWebTargetConverter;
import com.amazon.mp3.upsellweb.OfferPageWebTargetBuilderFactory;
import com.amazon.mp3.upsellweb.PrimeOfferPageWebTargetBuilderFactory;
import com.amazon.mp3.upsellweb.SettingsPageWebTargetBuilderFactory;
import com.amazon.mp3.util.coroutines.LookupError;
import com.amazon.mp3.util.extensions.ContextKt;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.destination.AlbumDestination;
import com.amazon.music.destination.AlexaDestination;
import com.amazon.music.destination.AllAccessUserPlaylistDestination;
import com.amazon.music.destination.AllArtistMerchDestination;
import com.amazon.music.destination.AmpDestination;
import com.amazon.music.destination.ArtistDestination;
import com.amazon.music.destination.ArtistDetailDestination;
import com.amazon.music.destination.CampaignDestination;
import com.amazon.music.destination.ContactImportDestination;
import com.amazon.music.destination.ContactUsDestination;
import com.amazon.music.destination.CustomerProfileDestination;
import com.amazon.music.destination.DigitalSubscriptionsDestination;
import com.amazon.music.destination.EntityMerchDetailDestination;
import com.amazon.music.destination.ExternalDestination;
import com.amazon.music.destination.FamilyInviteDestination;
import com.amazon.music.destination.FindDestination;
import com.amazon.music.destination.GenresDestination;
import com.amazon.music.destination.HomeDestination;
import com.amazon.music.destination.IamDestination;
import com.amazon.music.destination.InAppPurchaseDestination;
import com.amazon.music.destination.LibraryAlbumsDestination;
import com.amazon.music.destination.LibraryArtistsDestination;
import com.amazon.music.destination.LibraryPlaylistsDestination;
import com.amazon.music.destination.LibraryTracksDestination;
import com.amazon.music.destination.LiveEventDetailDestination;
import com.amazon.music.destination.LiveEventLineupsDestination;
import com.amazon.music.destination.LiveStreamPlayerDestination;
import com.amazon.music.destination.LiveStreamsDestination;
import com.amazon.music.destination.MerchDetailDestination;
import com.amazon.music.destination.MerchShopDestination;
import com.amazon.music.destination.MyLibraryDestination;
import com.amazon.music.destination.NewReleasesDestination;
import com.amazon.music.destination.PersonalizedPlaylistsDestination;
import com.amazon.music.destination.PlaylistDestination;
import com.amazon.music.destination.PlaylistsGenreDestination;
import com.amazon.music.destination.PodcastDestination;
import com.amazon.music.destination.PopularDestination;
import com.amazon.music.destination.RecommendedDestination;
import com.amazon.music.destination.SettingsDestination;
import com.amazon.music.destination.SharedUserPlaylistDestination;
import com.amazon.music.destination.SignupDestination;
import com.amazon.music.destination.StationDestination;
import com.amazon.music.destination.StationsGenreDestination;
import com.amazon.music.destination.SubscriptionSettingsDestination;
import com.amazon.music.destination.TastesDestination;
import com.amazon.music.destination.TrackDestination;
import com.amazon.music.destination.UpsellDestination;
import com.amazon.music.destination.VideoDestination;
import com.amazon.music.destination.parser.AlbumDeeplinkParser;
import com.amazon.music.destination.parser.AlexaDeeplinkParser;
import com.amazon.music.destination.parser.AllAccessUserPlaylistDeeplinkParser;
import com.amazon.music.destination.parser.AllArtistMerchAltDeeplinkParser;
import com.amazon.music.destination.parser.AllArtistMerchDeeplinkParser;
import com.amazon.music.destination.parser.AmpDeeplinkParser;
import com.amazon.music.destination.parser.ArtistDeeplinkParser;
import com.amazon.music.destination.parser.ArtistDetailDeeplinkParser;
import com.amazon.music.destination.parser.CampaignDeeplinkParser;
import com.amazon.music.destination.parser.ContactImportDeeplinkParser;
import com.amazon.music.destination.parser.ContactUsDeeplinkParser;
import com.amazon.music.destination.parser.CustomerProfileDeeplinkParser;
import com.amazon.music.destination.parser.DigitalSubscriptionsDeeplinkParser;
import com.amazon.music.destination.parser.EntityMerchDetailDeeplinkParser;
import com.amazon.music.destination.parser.ExternalDeeplinkParser;
import com.amazon.music.destination.parser.FamilyInviteDeeplinkParser;
import com.amazon.music.destination.parser.FindDeeplinkParser;
import com.amazon.music.destination.parser.GenresDeeplinkParser;
import com.amazon.music.destination.parser.HomeDeeplinkParser;
import com.amazon.music.destination.parser.IamDeeplinkParser;
import com.amazon.music.destination.parser.InAppPurchaseDeeplinkParser;
import com.amazon.music.destination.parser.LibraryAlbumsDeeplinkParser;
import com.amazon.music.destination.parser.LibraryArtistsDeeplinkParser;
import com.amazon.music.destination.parser.LibraryPlaylistsDeeplinkParser;
import com.amazon.music.destination.parser.LibraryTracksDeeplinkParser;
import com.amazon.music.destination.parser.LiveEventDetailDeeplinkParser;
import com.amazon.music.destination.parser.LiveEventLineupsDeeplinkParser;
import com.amazon.music.destination.parser.LiveStreamPlayerDeeplinkParser;
import com.amazon.music.destination.parser.LiveStreamsDeeplinkParser;
import com.amazon.music.destination.parser.MerchDetailDeeplinkParser;
import com.amazon.music.destination.parser.MerchShopDeeplinkParser;
import com.amazon.music.destination.parser.MyLibraryDeeplinkParser;
import com.amazon.music.destination.parser.NewReleasesDeeplinkParser;
import com.amazon.music.destination.parser.PersonalizedPlaylistsDeeplinkParser;
import com.amazon.music.destination.parser.PlaylistDeeplinkParser;
import com.amazon.music.destination.parser.PlaylistsGenreDeeplinkParser;
import com.amazon.music.destination.parser.PodcastDeeplinkParser;
import com.amazon.music.destination.parser.PopularDeeplinkParser;
import com.amazon.music.destination.parser.RecommendedDeeplinkParser;
import com.amazon.music.destination.parser.SettingsDeeplinkParser;
import com.amazon.music.destination.parser.SharedUserPlaylistDeeplinkParser;
import com.amazon.music.destination.parser.SignupDeeplinkParser;
import com.amazon.music.destination.parser.StationDeeplinkParser;
import com.amazon.music.destination.parser.StationsGenreDeeplinkParser;
import com.amazon.music.destination.parser.SubscriptionSettingsDeeplinkParser;
import com.amazon.music.destination.parser.TastesDeeplinkParser;
import com.amazon.music.destination.parser.TrackDeeplinkParser;
import com.amazon.music.destination.parser.UpsellDeeplinkParser;
import com.amazon.music.destination.parser.VideoDeeplinkParser;
import com.amazon.music.explore.handlers.AmpDestinationHandler;
import com.amazon.music.explore.handlers.LiveStreamPlayerDestinationHandler;
import com.amazon.music.freetier.destination.ShowESCDestination;
import com.amazon.music.freetier.parser.ShowESCDeeplinkParser;
import com.amazon.podcast.PodcastDestinationHandler;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import main.java.com.amazon.music.destination.CarModeDestination;
import main.java.com.amazon.music.destination.parser.CarModeDeeplinkParser;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class Deeplinks {
    private final Context mContext;
    private final DeeplinksManager mDeeplinksManager;
    private Subscription mFeatureFlaggedDeeplinksSubscription;

    public Deeplinks(final DeeplinksManager deeplinksManager, final Context context) {
        this.mDeeplinksManager = deeplinksManager;
        this.mContext = context;
    }

    public void initialize() {
        this.mDeeplinksManager.register(PodcastDestination.class, new PodcastDestinationHandler(), new PodcastDeeplinkParser());
        this.mDeeplinksManager.register(InAppPurchaseDestination.class, new InAppPurchaseDestinationHandler(), new InAppPurchaseDeeplinkParser());
        this.mDeeplinksManager.register(PopularDestination.class, new PopularDestinationHandler(), new PopularDeeplinkParser());
        this.mDeeplinksManager.register(NewReleasesDestination.class, new NewReleasesDestinationHandler(), new NewReleasesDeeplinkParser());
        this.mDeeplinksManager.register(StationsGenreDestination.class, new StationsGenreDestinationHandler(), new StationsGenreDeeplinkParser());
        this.mDeeplinksManager.register(RecommendedDestination.class, new RecommendedDestinationHandler(), new RecommendedDeeplinkParser());
        this.mDeeplinksManager.register(HomeDestination.class, new HomeDestinationHandler(), new HomeDeeplinkParser());
        this.mDeeplinksManager.register(FindDestination.class, new FindDestinationHandler(), new FindDeeplinkParser());
        this.mDeeplinksManager.register(UpsellDestination.class, new UpsellDestinationHandler(), new UpsellDeeplinkParser());
        this.mDeeplinksManager.register(PlaylistsGenreDestination.class, new PlaylistsGenreDestinationHandler(), new PlaylistsGenreDeeplinkParser());
        this.mDeeplinksManager.register(PersonalizedPlaylistsDestination.class, new PersonalizedPlaylistsDestinationHandler(), new PersonalizedPlaylistsDeeplinkParser());
        this.mDeeplinksManager.register(DigitalSubscriptionsDestination.class, new DigitalSubscriptionsDestinationHandler(new DigitalSubscriptionsWebTargetConverter(), new ActivityStacker()), new DigitalSubscriptionsDeeplinkParser());
        this.mDeeplinksManager.register(SignupDestination.class, new SignupDestinationHandler(new SignupWebTargetConverter(new SettingsPageWebTargetBuilderFactory(this.mContext), new OfferPageWebTargetBuilderFactory(this.mContext), new PrimeOfferPageWebTargetBuilderFactory(this.mContext), new DeeplinkTierToSubscriptionTierConverter()), new SignupDestinationDialogTargetConverter(), new ActivityStacker()), new SignupDeeplinkParser());
        this.mDeeplinksManager.register(PlaylistDestination.class, new PlaylistDestinationHandler(new Function1() { // from class: com.amazon.mp3.navigation.Deeplinks$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$initialize$1((LookupError) obj);
            }
        }), new PlaylistDeeplinkParser());
        this.mDeeplinksManager.register(AlbumDestination.class, new AlbumDestinationHandler(), new AlbumDeeplinkParser());
        this.mDeeplinksManager.register(AllAccessUserPlaylistDestination.class, new AllAccessUserPlaylistDestinationHandler(), new AllAccessUserPlaylistDeeplinkParser());
        this.mDeeplinksManager.register(StationDestination.class, new StationDestinationHandler(), new StationDeeplinkParser());
        this.mDeeplinksManager.register(ArtistDestination.class, new ArtistDestinationHandler(), new ArtistDeeplinkParser());
        this.mDeeplinksManager.register(GenresDestination.class, new GenresDestinationHandler(), new GenresDeeplinkParser());
        this.mDeeplinksManager.register(SharedUserPlaylistDestination.class, new SharedUserPlaylistDestinationHandler(), new SharedUserPlaylistDeeplinkParser());
        this.mDeeplinksManager.register(ExternalDestination.class, new ExternalDestinationHandler(), new ExternalDeeplinkParser());
        this.mDeeplinksManager.register(SettingsDestination.class, new SettingsDestinationHandler(), new SettingsDeeplinkParser());
        this.mDeeplinksManager.register(SubscriptionSettingsDestination.class, new SubscriptionSettingsDestinationHandler(new SubscriptionSettingsWebTargetConverter(new SettingsPageWebTargetBuilderFactory(this.mContext)), new ActivityStacker()), new SubscriptionSettingsDeeplinkParser());
        this.mDeeplinksManager.register(AlexaDestination.class, new AlexaDestinationHandler(), new AlexaDeeplinkParser());
        this.mDeeplinksManager.register(IamDestination.class, new IamDestinationHandler(), new IamDeeplinkParser());
        this.mDeeplinksManager.register(FamilyInviteDestination.class, new FamilyInviteDestinationHandler(), new FamilyInviteDeeplinkParser());
        this.mDeeplinksManager.register(CampaignDestination.class, new CampaignDeeplinkDestinationHandler(new CampaignWebTargetConverter(), new ActivityStacker()), new CampaignDeeplinkParser());
        this.mDeeplinksManager.register(CustomerProfileDestination.class, new CustomerProfileDestinationHandler(), new CustomerProfileDeeplinkParser());
        this.mDeeplinksManager.register(VideoDestination.class, new VideoDestinationHandler(), new VideoDeeplinkParser());
        this.mDeeplinksManager.register(ShowESCDestination.class, new ShowESCDestinationHandler(), new ShowESCDeeplinkParser());
        this.mDeeplinksManager.register(TastesDestination.class, new TastesDestinationHandler(), new TastesDeeplinkParser());
        this.mDeeplinksManager.register(ContactUsDestination.class, new ContactUsDestinationHandler(), new ContactUsDeeplinkParser());
        this.mDeeplinksManager.register(ArtistDetailDestination.class, new ArtistDetailDestinationHandler(), new ArtistDetailDeeplinkParser());
        this.mDeeplinksManager.register(CarModeDestination.class, new CarModeDestinationHandler(), new CarModeDeeplinkParser());
        this.mDeeplinksManager.register(TrackDestination.class, new TrackDestinationHandler(), new TrackDeeplinkParser());
        this.mDeeplinksManager.register(MyLibraryDestination.class, new MyLibraryDestinationHandler(), new MyLibraryDeeplinkParser());
        this.mDeeplinksManager.register(LibraryAlbumsDestination.class, new LibraryAlbumsDestinationHandler(), new LibraryAlbumsDeeplinkParser());
        this.mDeeplinksManager.register(LibraryArtistsDestination.class, new LibraryArtistsDestinationHandler(), new LibraryArtistsDeeplinkParser());
        this.mDeeplinksManager.register(LibraryPlaylistsDestination.class, new LibraryPlaylistsDestinationHandler(), new LibraryPlaylistsDeeplinkParser());
        this.mDeeplinksManager.register(LibraryTracksDestination.class, new LibraryTracksDestinationHandler(), new LibraryTracksDeeplinkParser());
        this.mDeeplinksManager.register(ContactImportDestination.class, new ContactImportDestinationHandler(), new ContactImportDeeplinkParser());
        this.mDeeplinksManager.register(AmpDestination.class, new AmpDestinationHandler(), new AmpDeeplinkParser());
        registerFeatureFlaggedDeepLinks();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$initialize$1(final LookupError lookupError) {
        ContextKt.runOnUiThread(this.mContext, new Function1() { // from class: com.amazon.mp3.navigation.Deeplinks$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Deeplinks.lambda$initialize$0(lookupError, (Context) obj);
            }
        });
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Unit lambda$initialize$0(LookupError lookupError, Context context) {
        lookupError.handleError();
        return null;
    }

    private void registerFeatureFlaggedDeepLinks() {
        this.mFeatureFlaggedDeeplinksSubscription = BootstrapSingletonTask.get().registerFeatureFlagObserver().subscribe(new Action1() { // from class: com.amazon.mp3.navigation.Deeplinks$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$registerFeatureFlaggedDeepLinks$2((Void) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$registerFeatureFlaggedDeepLinks$2(Void r1) {
        registerLiveStreamPlayerDeeplink();
        registerLiveEventDeeplinks();
        registerMerchDeeplinks();
        Subscription subscription = this.mFeatureFlaggedDeeplinksSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
            this.mFeatureFlaggedDeeplinksSubscription = null;
        }
    }

    private void registerLiveStreamPlayerDeeplink() {
        if (AmazonApplication.getCapabilities().isWoodstockEnabled()) {
            this.mDeeplinksManager.register(LiveStreamPlayerDestination.class, new LiveStreamPlayerDestinationHandler(), new LiveStreamPlayerDeeplinkParser());
            this.mDeeplinksManager.register(LiveStreamsDestination.class, new LiveStreamsDestinationHandler(), new LiveStreamsDeeplinkParser());
        }
    }

    private void registerLiveEventDeeplinks() {
        this.mDeeplinksManager.register(LiveEventLineupsDestination.class, new LiveEventLineupsDestinationHandler(), new LiveEventLineupsDeeplinkParser());
        this.mDeeplinksManager.register(LiveEventDetailDestination.class, new LiveEventDetailDestinationHandler(), new LiveEventDetailDeeplinkParser());
    }

    private void registerMerchDeeplinks() {
        if (AmazonApplication.getCapabilities().isMerchEnabled()) {
            this.mDeeplinksManager.register(MerchShopDestination.class, new MerchShopDestinationHandler(), new MerchShopDeeplinkParser());
            this.mDeeplinksManager.register(EntityMerchDetailDestination.class, new EntityMerchDetailDestinationHandler(), new EntityMerchDetailDeeplinkParser());
            this.mDeeplinksManager.register(MerchDetailDestination.class, new MerchDetailDestinationHandler(), new MerchDetailDeeplinkParser());
            this.mDeeplinksManager.register(AllArtistMerchDestination.class, new AllArtistMerchDestinationHandler(), new AllArtistMerchDeeplinkParser());
            this.mDeeplinksManager.register(AllArtistMerchDestination.class, new AllArtistMerchDestinationHandler(), new AllArtistMerchAltDeeplinkParser());
        }
    }
}
