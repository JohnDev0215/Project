package com.amazon.mp3.fragment.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.catalog.fragment.ArtistDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.AtmosDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.BrushAtmosFragment;
import com.amazon.mp3.catalog.fragment.BrushFragment;
import com.amazon.mp3.catalog.fragment.BrushPlaylistListFragment;
import com.amazon.mp3.catalog.fragment.BrushRA360Fragment;
import com.amazon.mp3.catalog.fragment.ChartsDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.FindBrushFragment;
import com.amazon.mp3.catalog.fragment.GenreDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.MusicHomeBrushFragment;
import com.amazon.mp3.catalog.fragment.NewReleasesDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.PlayListsDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.RA360DetailBrushFragment;
import com.amazon.mp3.catalog.fragment.ThreeDDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.UhdDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.VideoLandingPageBrushFragment;
import com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment;
import com.amazon.mp3.dmsfcore.providers.DMSFNavigationProvider;
import com.amazon.mp3.library.fragment.ArtistDetailsFragment;
import com.amazon.mp3.navigation.DeeplinkIntentKey;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.music.destination.parser.Action;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: BrushFragmentNavigation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000e\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001fB\t\b\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J$\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\bH\u0007J\u001c\u0010\u000f\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0007J(\u0010\u000f\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\bH\u0002J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\bH\u0002J\u001c\u0010\u0016\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\bH\u0002J&\u0010\u0019\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\bH\u0002R\u0014\u0010\u001a\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001b¨\u0006 "}, d2 = {"Lcom/amazon/mp3/fragment/navigation/BrushFragmentNavigation;", "", "Landroid/content/Context;", "context", "", "navigateToHome", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "", "artistTitle", "artistAsin", "navigateToArtistDetails", "uri", "Landroid/os/Bundle;", "bundle", "generateUri", "param1", "param2", "", "isGenreBasedUri", "isArtistUri", "asin", "generateUriWithAsin", "genreId", "widgetId", "generateUriWithGenreIdAndWidgetId", "TAG", "Ljava/lang/String;", "TOOLBAR_TITLE_KEY", "<init>", "()V", "Builder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushFragmentNavigation {
    public static final BrushFragmentNavigation INSTANCE = new BrushFragmentNavigation();
    private static final String TAG;

    @JvmField
    public static final String TOOLBAR_TITLE_KEY;

    private BrushFragmentNavigation() {
    }

    static {
        String simpleName = BrushFragmentNavigation.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "BrushFragmentNavigation::class.java.simpleName");
        TAG = simpleName;
        TOOLBAR_TITLE_KEY = Intrinsics.stringPlus(simpleName, "_toolbar_title");
    }

    @JvmStatic
    public static final void navigateToHome(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        context.startActivity(PrimeActivityFactory.getHomeIntent(context));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmStatic
    public static final void navigateToArtistDetails(FragmentActivity activity, String artistTitle, String artistAsin) {
        Fragment fragmentNewInstance;
        Fragment fragment;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Context context = AmazonApplication.getApplication().getApplicationContext();
        NavigationProvider navigationProvider = activity instanceof NowPlayingFragmentActivity ? (NavigationProvider) activity : (MusicHomeActivity) activity;
        LibraryArtistDetailFragment.Companion companion = LibraryArtistDetailFragment.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(context, "context");
        Fragment fragment2 = null;
        if (companion.shouldDisplayBauhausLibraryPage(context, (String) null)) {
            Bundle bundle = new Bundle();
            bundle.putString("ARTIST_NAME", artistTitle);
            bundle.putString("ARTIST_ASIN", artistAsin);
            fragmentNewInstance = LibraryArtistDetailFragment.Companion.newInstance$default(companion, bundle, null, 2, null);
            if (fragmentNewInstance == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fragment");
                fragment = null;
            } else {
                fragment = fragmentNewInstance;
            }
            navigationProvider.changeScreenFragment(fragment, true, false, false);
        } else if (ConnectivityUtil.hasAnyInternetConnection(context) && AmazonApplication.getCapabilities().shouldBrowseBeSupported() && !TextUtils.isEmpty(artistAsin)) {
            long jGenerateArtistId = IdGenerator.generateArtistId(artistTitle);
            if (ConnectivityUtil.hasAnyInternetConnection(activity) && DMSFNavigationProvider.Companion.openArtistDetailPage$default(DMSFNavigationProvider.INSTANCE, artistAsin, Long.valueOf(jGenerateArtistId), null, null, 12, null)) {
                return;
            } else {
                fragmentNewInstance = new Builder("artist").withTitle(artistTitle).withAsin(artistAsin).createFragment();
            }
        } else {
            fragmentNewInstance = ArtistDetailsFragment.newInstance(activity, artistTitle, LastViewedScreenUtil.getLastViewedSource(activity).toCirrusMediaSource());
            Intrinsics.checkNotNullExpressionValue(fragmentNewInstance, "newInstance(activity, artistTitle, source)");
        }
        if (fragmentNewInstance == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
        } else {
            fragment2 = fragmentNewInstance;
        }
        navigationProvider.changeScreenFragment(fragment2, true, false, false);
    }

    @JvmStatic
    public static final String generateUri(String uri, Bundle bundle) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        BrushFragmentNavigation brushFragmentNavigation = INSTANCE;
        if (brushFragmentNavigation.isArtistUri(uri)) {
            return brushFragmentNavigation.generateUriWithAsin(uri, bundle == null ? null : bundle.getString("brush_asin_extra"));
        }
        return uri;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String generateUri(String uri, String param1, String param2) {
        return isArtistUri(uri) ? generateUriWithAsin(uri, param1) : isGenreBasedUri(uri) ? generateUriWithGenreIdAndWidgetId(uri, param1, param2) : uri;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isGenreBasedUri(String uri) {
        return StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "new", false, 2, (Object) null) || StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "popular", false, 2, (Object) null) || StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "playlists", false, 2, (Object) null) || StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) ParserUtil.GENRE_SEGMENT_NAME, false, 2, (Object) null);
    }

    private final boolean isArtistUri(String uri) {
        return StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "artist", false, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String generateUriWithAsin(String uri, String asin) {
        if (asin == null || StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) asin, false, 2, (Object) null)) {
            return uri;
        }
        return uri + "/" + asin;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String generateUriWithGenreIdAndWidgetId(String uri, String genreId, String widgetId) {
        if (genreId != null && !StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) genreId, false, 2, (Object) null)) {
            StringBuilder sb = new StringBuilder(uri);
            sb.append("/");
            sb.append(genreId);
            if (widgetId != null && !StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) widgetId, false, 2, (Object) null)) {
                sb.append("/");
                sb.append(widgetId);
            }
            return sb.toString();
        }
        if (widgetId == null || StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) widgetId, false, 2, (Object) null)) {
            return uri;
        }
        return uri + "/all/" + widgetId;
    }

    /* JADX INFO: compiled from: BrushFragmentNavigation.kt */
    @Metadata(bv = {}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010*\u001a\u00020\u0005¢\u0006\u0004\b7\u00108J\u0010\u0010\u0004\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u0007\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\t\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u000b\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\r\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0010\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010\u0012\u001a\u00020\u00002\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0014\u001a\u00020\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0016\u001a\u00020\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0018\u001a\u00020\u00002\b\u0010\u0017\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u001b\u001a\u00020\u00002\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019J:\u0010 \u001a\u00020\u000022\u0010\u001f\u001a.\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u001d\u0018\u00010\u001cj\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u001d\u0018\u0001`\u001eJ\u000e\u0010$\u001a\u00020#2\u0006\u0010\"\u001a\u00020!J\u000e\u0010'\u001a\u00020!2\u0006\u0010&\u001a\u00020%J\u0006\u0010)\u001a\u00020(R\u0017\u0010*\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010.R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0006\u0010+R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010+R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010+R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010+R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010/R\u0018\u00100\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u0010+R\u0018\u00101\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u0010+R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010+R\u0016\u00103\u001a\u0002028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00104R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010+RB\u0010\u001f\u001a.\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u001d\u0018\u00010\u001cj\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u001d\u0018\u0001`\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u00105R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u00106¨\u00069"}, d2 = {"Lcom/amazon/mp3/fragment/navigation/BrushFragmentNavigation$Builder;", "", "Landroid/os/Bundle;", "bundle", "withBundle", "", "title", "withTitle", "asin", "withAsin", "genreId", "withGenreId", "token", "withToken", "Lcom/amazon/music/destination/parser/Action;", "action", "withAction", "refMarker", "withRefMarker", "associateTag", "withAssociateTag", "profileId", "withProfileId", "widgetId", "withWidgetId", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "facetedNavHeader", "withFacetedNavigationHeader", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "refinementLists", "withRefinementLists", "Landroid/content/Intent;", MAPAccountManager.KEY_INTENT, "", "updateIntent", "Landroid/content/Context;", "context", "createIntent", "Lcom/amazon/mp3/catalog/fragment/BrushFragment;", "createFragment", "uri", "Ljava/lang/String;", "getUri", "()Ljava/lang/String;", "Landroid/os/Bundle;", "Lcom/amazon/music/destination/parser/Action;", "ref", ParserUtil.TAG_QUERY_PARAM_NAME, "", "isFromSearch", "Z", "Ljava/util/HashMap;", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "<init>", "(Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Builder {
        private Action action;
        private String asin;
        private Bundle bundle;
        private ExposedRefinementListModel facetedNavHeader;
        private String genreId;
        private boolean isFromSearch;
        private String profileId;
        private String ref;
        private HashMap<String, List<String>> refinementLists;
        private String tag;
        private String title;
        private String token;
        private final String uri;
        private String widgetId;

        public Builder(String uri) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            this.uri = uri;
        }

        public final Builder withBundle(Bundle bundle) {
            this.bundle = bundle;
            return this;
        }

        public final Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public final Builder withAsin(String asin) {
            this.asin = asin;
            return this;
        }

        public final Builder withGenreId(String genreId) {
            this.genreId = genreId;
            return this;
        }

        public final Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public final Builder withAction(Action action) {
            this.action = action;
            return this;
        }

        public final Builder withRefMarker(String refMarker) {
            this.ref = refMarker;
            return this;
        }

        public final Builder withAssociateTag(String associateTag) {
            this.tag = associateTag;
            return this;
        }

        public final Builder withProfileId(String profileId) {
            this.profileId = profileId;
            return this;
        }

        public final Builder withWidgetId(String widgetId) {
            this.widgetId = widgetId;
            return this;
        }

        public final Builder withFacetedNavigationHeader(ExposedRefinementListModel facetedNavHeader) {
            this.facetedNavHeader = facetedNavHeader;
            return this;
        }

        public final Builder withRefinementLists(HashMap<String, List<String>> refinementLists) {
            this.refinementLists = refinementLists;
            return this;
        }

        public final void updateIntent(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            String simpleName = (!StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "public-playlists", false, 2, (Object) null) ? PlayListsDetailBrushFragment.class : BrushPlaylistListFragment.class).getSimpleName();
            if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "artist", false, 2, (Object) null)) {
                intent.putExtra("com.amazon.mp3.fragment.extra", ArtistDetailBrushFragment.class.getSimpleName());
            } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) ParserUtil.GENRE_SEGMENT_NAME, false, 2, (Object) null)) {
                intent.putExtra("com.amazon.mp3.fragment.extra", GenreDetailBrushFragment.class.getSimpleName());
            } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "3d", false, 2, (Object) null)) {
                intent.putExtra("com.amazon.mp3.fragment.extra", ThreeDDetailBrushFragment.class.getSimpleName());
            } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "uhd", false, 2, (Object) null)) {
                intent.putExtra("com.amazon.mp3.fragment.extra", UhdDetailBrushFragment.class.getSimpleName());
            } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "find", false, 2, (Object) null)) {
                intent.putExtra("com.amazon.mp3.fragment.extra", FindBrushFragment.class.getSimpleName());
            } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "playlists", false, 2, (Object) null)) {
                intent.putExtra("com.amazon.mp3.fragment.extra", simpleName);
            } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "popular", false, 2, (Object) null)) {
                intent.putExtra("com.amazon.mp3.fragment.extra", ChartsDetailBrushFragment.class.getSimpleName());
            } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "new", false, 2, (Object) null)) {
                intent.putExtra("com.amazon.mp3.fragment.extra", NewReleasesDetailBrushFragment.class.getSimpleName());
            } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "videos", false, 2, (Object) null)) {
                intent.putExtra("com.amazon.mp3.fragment.extra", VideoLandingPageBrushFragment.class.getSimpleName());
            } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "ra360", false, 2, (Object) null)) {
                intent.putExtra("com.amazon.mp3.fragment.extra", RA360DetailBrushFragment.class.getSimpleName());
            } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "atmos", false, 2, (Object) null)) {
                intent.putExtra("com.amazon.mp3.fragment.extra", AtmosDetailBrushFragment.class.getSimpleName());
            } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "home-music", false, 2, (Object) null)) {
                intent.putExtra("com.amazon.mp3.fragment.extra", MusicHomeBrushFragment.class.getSimpleName());
            } else {
                intent.putExtra("com.amazon.mp3.fragment.extra", BrushFragment.class.getSimpleName());
            }
            BrushFragmentNavigation brushFragmentNavigation = BrushFragmentNavigation.INSTANCE;
            if (brushFragmentNavigation.isGenreBasedUri(this.uri)) {
                intent.putExtra("brush_uri_extra", brushFragmentNavigation.generateUriWithGenreIdAndWidgetId(this.uri, this.genreId, this.widgetId));
            } else {
                intent.putExtra("brush_uri_extra", brushFragmentNavigation.generateUriWithAsin(this.uri, this.asin));
            }
            intent.putExtra("brush_asin_extra", this.asin);
            intent.putExtra("brush_title_extra", this.title);
            intent.putExtra("brush_genre_id_extra", this.genreId);
            intent.putExtra("brush_widget_id_extra", this.widgetId);
            intent.putExtra("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION", this.action);
            intent.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), this.ref);
            intent.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), this.tag);
            intent.putExtra("brush_profileid_extra", this.profileId);
        }

        public final Intent createIntent(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = MusicHomeActivity.getStartIntent(context);
            Intrinsics.checkNotNullExpressionValue(intent, "intent");
            updateIntent(intent);
            return intent;
        }

        public final BrushFragment createFragment() {
            Bundle bundle = this.bundle;
            if (bundle == null) {
                bundle = new Bundle();
            }
            String str = this.asin;
            if (str != null) {
                bundle.putString("brush_asin_extra", str);
            }
            String str2 = this.genreId;
            if (str2 != null) {
                bundle.putString("brush_genre_id_extra", str2);
            }
            String str3 = this.title;
            if (str3 != null) {
                bundle.putString("brush_title_extra", str3);
            }
            String str4 = this.token;
            if (str4 != null) {
                bundle.putString("brush_nextpagetoken_extra", str4);
            }
            String str5 = this.widgetId;
            if (str5 != null) {
                bundle.putString("brush_widget_id_extra", str5);
            }
            bundle.putBoolean("navigation_source_extra", this.isFromSearch);
            BrushFragment playListsDetailBrushFragment = !StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "public-playlists", false, 2, (Object) null) ? new PlayListsDetailBrushFragment() : new BrushPlaylistListFragment();
            BrushFragmentNavigation brushFragmentNavigation = BrushFragmentNavigation.INSTANCE;
            String string = brushFragmentNavigation.isGenreBasedUri(this.uri) ? bundle.getString("brush_genre_id_extra") : bundle.getString("brush_asin_extra");
            bundle.putString("brush_uri_extra", brushFragmentNavigation.generateUri(this.uri, string, bundle.getString("brush_widget_id_extra")));
            if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "artist", false, 2, (Object) null)) {
                playListsDetailBrushFragment = new ArtistDetailBrushFragment();
                bundle.putBoolean("see_more", string == null);
            } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "facet", false, 2, (Object) null)) {
                playListsDetailBrushFragment = new BrushFragment();
            } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) ParserUtil.GENRE_SEGMENT_NAME, false, 2, (Object) null)) {
                playListsDetailBrushFragment = new GenreDetailBrushFragment();
            } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "3d", false, 2, (Object) null)) {
                playListsDetailBrushFragment = new ThreeDDetailBrushFragment();
            } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "uhd", false, 2, (Object) null)) {
                playListsDetailBrushFragment = new UhdDetailBrushFragment();
            } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "find", false, 2, (Object) null)) {
                playListsDetailBrushFragment = new FindBrushFragment();
            } else if (!StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "playlists", false, 2, (Object) null)) {
                if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "videos", false, 2, (Object) null)) {
                    playListsDetailBrushFragment = new VideoLandingPageBrushFragment();
                } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "ra360", false, 2, (Object) null)) {
                    playListsDetailBrushFragment = new BrushRA360Fragment();
                } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "atmos", false, 2, (Object) null)) {
                    playListsDetailBrushFragment = new BrushAtmosFragment();
                } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "popular", false, 2, (Object) null)) {
                    playListsDetailBrushFragment = new ChartsDetailBrushFragment();
                } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "new", false, 2, (Object) null)) {
                    playListsDetailBrushFragment = new NewReleasesDetailBrushFragment();
                } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "ra360", false, 2, (Object) null)) {
                    playListsDetailBrushFragment = new RA360DetailBrushFragment();
                } else if (StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "atmos", false, 2, (Object) null)) {
                    playListsDetailBrushFragment = new AtmosDetailBrushFragment();
                } else {
                    playListsDetailBrushFragment = StringsKt__StringsKt.contains$default((CharSequence) this.uri, (CharSequence) "home-music", false, 2, (Object) null) ? new MusicHomeBrushFragment() : new BrushFragment();
                }
            }
            playListsDetailBrushFragment.setArguments(bundle);
            HashMap<String, List<String>> map = this.refinementLists;
            if (map == null) {
                map = new HashMap<>();
            }
            this.refinementLists = map;
            playListsDetailBrushFragment.setRefinementLists(map);
            ExposedRefinementListModel exposedRefinementListModel = this.facetedNavHeader;
            if (exposedRefinementListModel != null) {
                playListsDetailBrushFragment.setFacetedNavigationHeader(exposedRefinementListModel);
            }
            return playListsDetailBrushFragment;
        }
    }
}
