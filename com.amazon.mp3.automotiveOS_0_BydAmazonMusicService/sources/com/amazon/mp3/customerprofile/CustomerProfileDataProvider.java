package com.amazon.mp3.customerprofile;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.converter.BauhausDetailHeaderTextUtil;
import com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao;
import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.graphql.generated.fragment.Eligibility;
import com.amazon.music.platform.graphql.generated.fragment.Image;
import com.amazon.music.platform.graphql.generated.fragment.SocialProfilesPlaylistEmbedded;
import com.amazon.music.platform.graphql.generated.fragment.SocialProfilesUserEmbedded;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.CustomerProfileModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.ShovelerModel;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;

/* JADX INFO: compiled from: CustomerProfileDataProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\"\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000eH\u0002J*\u0010\u0018\u001a\u00020\u00192\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u001b2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0002J0\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/amazon/mp3/customerprofile/CustomerProfileDataProvider;", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/PageViewModelDao;", "context", "Landroid/content/Context;", "profileId", "", "pageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "(Landroid/content/Context;Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;)V", "convertPlaylist", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", Environment.PLAYLIST_PATH, "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesPlaylistEmbedded;", "isSelfView", "", "convertPlaymodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "eligibility", "Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "convertProfile", "Lcom/amazon/music/views/library/models/CustomerProfileModel;", "user", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded;", "isFollowing", "createPlaylistsSection", "Lcom/amazon/music/views/library/models/ShovelerModel;", "playlists", "", "fetchViewModelItems", "Lio/reactivex/Single;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageIndex", "", "pageSize", "fetchType", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "nextPageToken", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerProfileDataProvider implements PageViewModelDao {
    private final Context context;
    private final PageLoadLatencyCode pageLoadLatencyCode;
    private final String profileId;

    public CustomerProfileDataProvider(Context context, String str, PageLoadLatencyCode pageLoadLatencyCode) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.profileId = str;
        this.pageLoadLatencyCode = pageLoadLatencyCode;
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public int getDefaultPageSize() {
        return PageViewModelDao.DefaultImpls.getDefaultPageSize(this);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public Single<PageGridViewModel> fetchViewModelItems(int pageIndex, final int pageSize, FetchType fetchType, String nextPageToken) {
        Intrinsics.checkNotNullParameter(fetchType, "fetchType");
        Single singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.customerprofile.CustomerProfileDataProvider$$ExternalSyntheticLambda0
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) throws InterruptedException {
                CustomerProfileDataProvider.m779fetchViewModelItems$lambda0(this.f$0, pageSize, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create { emitter ->\n    …}\n            }\n        }");
        Single<PageGridViewModel> singleSubscribeOn = singleCreate.subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(singleSubscribeOn, "single.subscribeOn(Schedulers.io())");
        return singleSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchViewModelItems$lambda-0, reason: not valid java name */
    public static final void m779fetchViewModelItems$lambda0(CustomerProfileDataProvider this$0, int i, SingleEmitter emitter) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        BuildersKt__BuildersKt.runBlocking$default(null, new CustomerProfileDataProvider$fetchViewModelItems$single$1$1(this$0, emitter, i, null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CustomerProfileModel convertProfile(SocialProfilesUserEmbedded user, boolean isSelfView, boolean isFollowing) {
        SocialProfilesUserEmbedded.FollowedUsers followedUsers;
        Double totalCount;
        SocialProfilesUserEmbedded.FollowedArtists followedArtists;
        Double totalCount2;
        String name;
        String handle;
        String visibility;
        String playbackVisibility;
        SocialProfilesUserEmbedded.Followers followers;
        Double totalCount3;
        List<SocialProfilesUserEmbedded.Image> images;
        SocialProfilesUserEmbedded.Image image;
        Image image2;
        String url;
        String id;
        String url2;
        SocialProfilesUserEmbedded.Playlists playlists;
        Double totalCount4;
        long jDoubleValue = 0;
        long jDoubleValue2 = ((user == null || (followedUsers = user.getFollowedUsers()) == null || (totalCount = followedUsers.getTotalCount()) == null) ? 0L : (long) totalCount.doubleValue()) + ((user == null || (followedArtists = user.getFollowedArtists()) == null || (totalCount2 = followedArtists.getTotalCount()) == null) ? 0L : (long) totalCount2.doubleValue());
        String str = (user == null || (name = user.getName()) == null) ? "" : name;
        String str2 = (user == null || (handle = user.getHandle()) == null) ? "" : handle;
        String str3 = (user == null || (visibility = user.getVisibility()) == null) ? "PRIVATE" : visibility;
        String str4 = (user == null || (playbackVisibility = user.getPlaybackVisibility()) == null) ? "" : playbackVisibility;
        long jDoubleValue3 = (user == null || (followers = user.getFollowers()) == null || (totalCount3 = followers.getTotalCount()) == null) ? 0L : (long) totalCount3.doubleValue();
        if (user != null && (playlists = user.getPlaylists()) != null && (totalCount4 = playlists.getTotalCount()) != null) {
            jDoubleValue = (long) totalCount4.doubleValue();
        }
        return new CustomerProfileModel(null, null, null, null, str, str2, str3, Long.valueOf(jDoubleValue2), Long.valueOf(jDoubleValue3), Long.valueOf(jDoubleValue), false, (user == null || (images = user.getImages()) == null || (image = (SocialProfilesUserEmbedded.Image) CollectionsKt___CollectionsKt.firstOrNull((List) images)) == null || (image2 = image.getImage()) == null || (url = image2.getUrl()) == null) ? "" : url, 0, isSelfView, (user == null || (id = user.getId()) == null) ? "" : id, null, isFollowing ? 1 : 0, str4, (user == null || (url2 = user.getUrl()) == null) ? "" : url2, 36879, null);
    }

    private final PlaymodeEligibility convertPlaymodeEligibility(Eligibility eligibility) {
        Eligibility.Playback playback;
        Eligibility.Playback playback2;
        String shuffleType = null;
        Boolean canPlayOnDemand = (eligibility == null || (playback = eligibility.getPlayback()) == null) ? null : playback.getCanPlayOnDemand();
        Boolean bool = Boolean.TRUE;
        if (eligibility != null && (playback2 = eligibility.getPlayback()) != null) {
            shuffleType = playback2.getShuffleType();
        }
        return new PlaymodeEligibility(canPlayOnDemand, bool, shuffleType);
    }

    private final BaseViewModel convertPlaylist(SocialProfilesPlaylistEmbedded playlist, boolean isSelfView) {
        SocialProfilesPlaylistEmbedded.Image image;
        Image image2;
        String string;
        SocialProfilesPlaylistEmbedded.Image image3;
        Image image4;
        String title = playlist.getTitle();
        List<SocialProfilesPlaylistEmbedded.Image> images = playlist.getImages();
        String url = null;
        String url2 = (images == null || (image = (SocialProfilesPlaylistEmbedded.Image) CollectionsKt___CollectionsKt.firstOrNull((List) images)) == null || (image2 = image.getImage()) == null) ? null : image2.getUrl();
        Uri userPlaylistUri = ContentPlaybackUtils.INSTANCE.getUserPlaylistUri();
        String id = playlist.getId();
        String str = id == null ? "" : id;
        SocialProfilesPlaylistEmbedded.Eligibility eligibility = playlist.getEligibility();
        PlaymodeEligibility playmodeEligibilityConvertPlaymodeEligibility = convertPlaymodeEligibility(eligibility == null ? null : eligibility.getEligibility());
        Double trackCount = playlist.getTrackCount();
        UserPlaylistMetadata userPlaylistMetadata = new UserPlaylistMetadata(null, userPlaylistUri, null, title, url2, null, Long.valueOf(trackCount == null ? 0L : (long) trackCount.doubleValue()), null, isSelfView, str, playmodeEligibilityConvertPlaymodeEligibility, null, null, false, 14501, null);
        if (isSelfView) {
            string = this.context.getString(Intrinsics.areEqual(playlist.getVisibility(), "PUBLIC") ? R.string.dmusic_edit_profile_status_public_title : R.string.dmusic_edit_profile_status_private_title);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(stringId)");
        } else {
            string = "";
        }
        Double followerCount = playlist.getFollowerCount();
        String followerCountString = followerCount == null ? null : BauhausDetailHeaderTextUtil.INSTANCE.getFollowerCountString(Integer.valueOf((int) followerCount.doubleValue()));
        String title2 = playlist.getTitle();
        if (followerCountString == null) {
            followerCountString = "";
        }
        List<SocialProfilesPlaylistEmbedded.Image> images2 = playlist.getImages();
        if (images2 != null && (image3 = (SocialProfilesPlaylistEmbedded.Image) CollectionsKt___CollectionsKt.firstOrNull((List) images2)) != null && (image4 = image3.getImage()) != null) {
            url = image4.getUrl();
        }
        return new VerticalTileModel(null, title2, followerCountString, string, new ArtworkFrameModel(null, url, 1, null, null, null, userPlaylistMetadata, null, null, null, null, null, 4025, null), null, 1, userPlaylistMetadata, false, null, null, null, null, null, null, null, null, null, null, null, null, 2096929, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ShovelerModel createPlaylistsSection(List<SocialProfilesPlaylistEmbedded> playlists, boolean isSelfView, String profileId) {
        List arrayList;
        if (playlists == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(playlists, 10));
            Iterator<T> it = playlists.iterator();
            while (it.hasNext()) {
                arrayList.add(convertPlaylist((SocialProfilesPlaylistEmbedded) it.next(), isSelfView));
            }
        }
        if (arrayList == null) {
            arrayList = CollectionsKt__CollectionsKt.emptyList();
        }
        List list = arrayList;
        Context context = this.context;
        String string = context == null ? null : context.getString(R.string.dmusic_library_playlists_tab);
        Context context2 = this.context;
        return new ShovelerModel(null, list, 5, null, string, null, context2 != null ? context2.getString(R.string.dmusic_search_card_footer_text) : null, null, Intrinsics.stringPlus("profile/public-playlists/", profileId), false, null, null, null, false, null, 32425, null);
    }
}
