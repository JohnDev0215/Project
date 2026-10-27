package com.amazon.mp3.view.refinements;

import Podcast.SaveInterface.v1_0.Sort;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.StringRes;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.refinement.sort.SortOrder;
import com.amazon.music.find.api.RemoteSearchApi;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: RefinementFilterType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001c\b\u0086\u0001\u0018\u0000 .2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001.B\u0011\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0006\u0010\t\u001a\u00020\nJ\n\u0010\u000b\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\bJ\n\u0010\u000f\u001a\u0004\u0018\u00010\bH\u0002J$\u0010\u0010\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\rJ\u0006\u0010\u0015\u001a\u00020\rJ\u0006\u0010\u0016\u001a\u00020\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-¨\u0006/"}, d2 = {"Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "", "displayName", "", "(Ljava/lang/String;II)V", "getDisplayName", "()I", "ascendingSortLabel", "", "defaultSortOrder", "Lcom/amazon/mp3/library/refinement/sort/SortOrder;", "descendingSortLabel", "equalsName", "", "name", "getDefaultSortOrderLabel", "getSubtitleText", "recentsPlaylistSortingFeatureEnabled", "context", "Landroid/content/Context;", "isDefaultFilter", "isSortType", "viewId", "ULTRA_HD", RemoteSearchApi.ATMOS_ASSET_QUALITY, RemoteSearchApi.RA360_ASSET_QUALITY, "LIBRARY", "MUSIC_LIBRARY", "LYRICS", "DOWNLOADED", "PURCHASED", "UPLOADED", "ALBUM_NAME", "ARTIST_NAME", Sort.DATE_ADDED, "DURATION", "POPULARITY", "RELEASE_DATE", "TITLE", "FOLLOWING", "BY_ME", "LAST_MODIFIED", "LAST_TOUCHED", "LANGUAGES", "SORT", "AUDIO_QUALITY", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum RefinementFilterType {
    ULTRA_HD(R.string.dmusic_ultra_hd),
    ATMOS(R.string.dmusic_atmos),
    RA360(R.string.dmusic_ra360),
    LIBRARY(R.string.dmusic_library),
    MUSIC_LIBRARY(R.string.music_library_filter),
    LYRICS(R.string.dmusic_lyrics),
    DOWNLOADED(R.string.dmusic_downloaded),
    PURCHASED(R.string.dmusic_purchased),
    UPLOADED(R.string.dmusic_uploaded),
    ALBUM_NAME(R.string.dmusic_album_name),
    ARTIST_NAME(R.string.dmusic_artist_name),
    DATE_ADDED(R.string.dmusic_date_added),
    DURATION(R.string.dmusic_duration),
    POPULARITY(R.string.dmusic_popularity),
    RELEASE_DATE(R.string.dmusic_release_date),
    TITLE(R.string.dmusic_title),
    FOLLOWING(R.string.dmusic_following),
    BY_ME(R.string.dmusic_by_me),
    LAST_MODIFIED(R.string.dmusic_last_modified),
    LAST_TOUCHED(R.string.dmusic_last_touched),
    LANGUAGES(R.string.dmusic_language_singular_plural),
    SORT(R.string.dmusic_sort),
    AUDIO_QUALITY(R.string.showcaseview_audioquality_title);


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int displayName;

    /* JADX INFO: compiled from: RefinementFilterType.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[RefinementFilterType.values().length];
            iArr[RefinementFilterType.ULTRA_HD.ordinal()] = 1;
            iArr[RefinementFilterType.ATMOS.ordinal()] = 2;
            iArr[RefinementFilterType.RA360.ordinal()] = 3;
            iArr[RefinementFilterType.LIBRARY.ordinal()] = 4;
            iArr[RefinementFilterType.MUSIC_LIBRARY.ordinal()] = 5;
            iArr[RefinementFilterType.LYRICS.ordinal()] = 6;
            iArr[RefinementFilterType.DOWNLOADED.ordinal()] = 7;
            iArr[RefinementFilterType.PURCHASED.ordinal()] = 8;
            iArr[RefinementFilterType.UPLOADED.ordinal()] = 9;
            iArr[RefinementFilterType.ALBUM_NAME.ordinal()] = 10;
            iArr[RefinementFilterType.ARTIST_NAME.ordinal()] = 11;
            iArr[RefinementFilterType.DATE_ADDED.ordinal()] = 12;
            iArr[RefinementFilterType.DURATION.ordinal()] = 13;
            iArr[RefinementFilterType.POPULARITY.ordinal()] = 14;
            iArr[RefinementFilterType.RELEASE_DATE.ordinal()] = 15;
            iArr[RefinementFilterType.TITLE.ordinal()] = 16;
            iArr[RefinementFilterType.LANGUAGES.ordinal()] = 17;
            iArr[RefinementFilterType.SORT.ordinal()] = 18;
            iArr[RefinementFilterType.FOLLOWING.ordinal()] = 19;
            iArr[RefinementFilterType.BY_ME.ordinal()] = 20;
            iArr[RefinementFilterType.LAST_TOUCHED.ordinal()] = 21;
            iArr[RefinementFilterType.LAST_MODIFIED.ordinal()] = 22;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[SortOrder.values().length];
            iArr2[SortOrder.ASCENDING.ordinal()] = 1;
            iArr2[SortOrder.DESCENDING.ordinal()] = 2;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    @JvmStatic
    public static final String getDisplayName(RefinementFilterType refinementFilterType) {
        return INSTANCE.getDisplayName(refinementFilterType);
    }

    @JvmStatic
    public static final Set<RefinementFilterType> getEnabledRefinementFilters(Bundle bundle, RefinementFilterType refinementFilterType) {
        return INSTANCE.getEnabledRefinementFilters(bundle, refinementFilterType);
    }

    @JvmStatic
    public static final void withRefinementFiltersEnabled(Intent intent, RefinementFilterType... refinementFilterTypeArr) {
        INSTANCE.withRefinementFiltersEnabled(intent, refinementFilterTypeArr);
    }

    RefinementFilterType(@StringRes int i) {
        this.displayName = i;
    }

    public final int getDisplayName() {
        return this.displayName;
    }

    /* JADX INFO: compiled from: RefinementFilterType.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\"\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0007J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\u0004J)\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0013\"\u00020\u0006H\u0007¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/view/refinements/RefinementFilterType$Companion;", "", "()V", "getDisplayName", "", "refinementFilterType", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "getEnabledRefinementFilters", "", "bundle", "Landroid/os/Bundle;", "defaultSortFilterType", "getRefinementTypeFromDisplayName", "name", "withRefinementFiltersEnabled", "", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "refinementFilters", "", "(Landroid/content/Intent;[Lcom/amazon/mp3/view/refinements/RefinementFilterType;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RefinementFilterType getRefinementTypeFromDisplayName(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            RefinementFilterType[] refinementFilterTypeArrValues = RefinementFilterType.values();
            int length = refinementFilterTypeArrValues.length;
            int i = 0;
            while (i < length) {
                RefinementFilterType refinementFilterType = refinementFilterTypeArrValues[i];
                i++;
                if (StringsKt__StringsJVMKt.equals(getDisplayName(refinementFilterType), name, true)) {
                    return refinementFilterType;
                }
            }
            return null;
        }

        @JvmStatic
        public final String getDisplayName(RefinementFilterType refinementFilterType) {
            Intrinsics.checkNotNullParameter(refinementFilterType, "refinementFilterType");
            String string = AmazonApplication.getContext().getString(refinementFilterType.getDisplayName());
            Intrinsics.checkNotNullExpressionValue(string, "getContext().getString(r…ntFilterType.displayName)");
            return string;
        }

        @JvmStatic
        public final void withRefinementFiltersEnabled(Intent intent, RefinementFilterType... refinementFilters) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            Intrinsics.checkNotNullParameter(refinementFilters, "refinementFilters");
            int length = refinementFilters.length;
            int i = 0;
            while (i < length) {
                RefinementFilterType refinementFilterType = refinementFilters[i];
                i++;
                intent.putExtra(getDisplayName(refinementFilterType), true);
            }
        }

        public static /* synthetic */ Set getEnabledRefinementFilters$default(Companion companion, Bundle bundle, RefinementFilterType refinementFilterType, int i, Object obj) {
            if ((i & 2) != 0) {
                refinementFilterType = null;
            }
            return companion.getEnabledRefinementFilters(bundle, refinementFilterType);
        }

        @JvmStatic
        public final Set<RefinementFilterType> getEnabledRefinementFilters(Bundle bundle, RefinementFilterType defaultSortFilterType) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            RefinementFilterType[] refinementFilterTypeArrValues = RefinementFilterType.values();
            int length = refinementFilterTypeArrValues.length;
            int i = 0;
            while (i < length) {
                RefinementFilterType refinementFilterType = refinementFilterTypeArrValues[i];
                i++;
                if (bundle.getBoolean(RefinementFilterType.INSTANCE.getDisplayName(refinementFilterType))) {
                    linkedHashSet.add(refinementFilterType);
                }
            }
            if (defaultSortFilterType != null) {
                linkedHashSet.add(defaultSortFilterType);
            }
            return linkedHashSet;
        }
    }

    public final int viewId() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return R.id.ultra_hd_refinement;
            case 2:
                return R.id.atmos_refinement;
            case 3:
                return R.id.ra_360_refinement;
            case 4:
                return R.id.library_refinement;
            case 5:
                return R.id.music_library_refinement;
            case 6:
                return R.id.lyrics_refinement;
            case 7:
                return R.id.downloaded_refinement;
            case 8:
                return R.id.purchased_refinement;
            case 9:
                return R.id.uploaded_refinement;
            case 10:
                return R.id.album_name_refinement;
            case 11:
                return R.id.artist_name_refinement;
            case 12:
                return R.id.date_added_refinement;
            case 13:
                return R.id.duration_refinement;
            case 14:
                return R.id.popularity_refinement;
            case 15:
                return R.id.release_date_refinement;
            case 16:
                return R.id.title_refinement;
            case 17:
                return R.id.languages_refinement;
            case 18:
                return R.id.sort_refinement;
            case 19:
                return R.id.following_refinement;
            case 20:
                return R.id.by_me_refinement;
            case 21:
                return R.id.date_last_touched_refinement;
            case 22:
                return R.id.date_last_modified_refinement;
            default:
                return R.id.audio_quality_refinement;
        }
    }

    private final String ascendingSortLabel() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i != 21 && i != 22) {
            switch (i) {
                case 10:
                    return AmazonApplication.getContext().getString(R.string.dmusic_album_name_asc);
                case 11:
                    return AmazonApplication.getContext().getString(R.string.dmusic_artist_name_asc);
                case 12:
                    break;
                case 13:
                    return AmazonApplication.getContext().getString(R.string.dmusic_duration_asc);
                case 14:
                    return AmazonApplication.getContext().getString(R.string.dmusic_popularity_asc);
                case 15:
                    return AmazonApplication.getContext().getString(R.string.dmusic_release_date_asc);
                case 16:
                    return AmazonApplication.getContext().getString(R.string.dmusic_title_asc);
                default:
                    return null;
            }
        }
        return AmazonApplication.getContext().getString(R.string.dmusic_date_added_asc);
    }

    private final String descendingSortLabel() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i != 21 && i != 22) {
            switch (i) {
                case 10:
                    return AmazonApplication.getContext().getString(R.string.dmusic_album_name_dec);
                case 11:
                    return AmazonApplication.getContext().getString(R.string.dmusic_artist_name_dec);
                case 12:
                    break;
                case 13:
                    return AmazonApplication.getContext().getString(R.string.dmusic_duration_asc);
                case 14:
                    return AmazonApplication.getContext().getString(R.string.dmusic_popularity_dec);
                case 15:
                    return AmazonApplication.getContext().getString(R.string.dmusic_release_date_dec);
                case 16:
                    return AmazonApplication.getContext().getString(R.string.dmusic_title_dec);
                default:
                    return null;
            }
        }
        return AmazonApplication.getContext().getString(R.string.dmusic_date_added_dec);
    }

    public final SortOrder defaultSortOrder() throws Exception {
        if (!isSortType()) {
            throw new Exception("Non-sort refinements don't have a default sort order");
        }
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 12 || i == 14 || i == 15 || i == 21 || i == 22) {
            return SortOrder.DESCENDING;
        }
        return SortOrder.ASCENDING;
    }

    public final boolean isSortType() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i != 8 && i != 21 && i != 22) {
            switch (i) {
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private final String getDefaultSortOrderLabel() throws Exception {
        if (!isSortType()) {
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$1[defaultSortOrder().ordinal()];
        if (i == 1) {
            return ascendingSortLabel();
        }
        if (i == 2) {
            return descendingSortLabel();
        }
        throw new NoWhenBranchMatchedException();
    }

    public static /* synthetic */ String getSubtitleText$default(RefinementFilterType refinementFilterType, boolean z, Context context, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getSubtitleText");
        }
        if ((i & 1) != 0) {
            z = AmazonApplication.getCapabilities().isPlaylistsSortingEnabled();
        }
        if ((i & 2) != 0) {
            context = AmazonApplication.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext()");
        }
        return refinementFilterType.getSubtitleText(z, context, z2);
    }

    public final String getSubtitleText(boolean recentsPlaylistSortingFeatureEnabled, Context context, boolean isDefaultFilter) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!recentsPlaylistSortingFeatureEnabled) {
            return getDefaultSortOrderLabel();
        }
        if (isDefaultFilter) {
            return context.getString(R.string.dmusic_default);
        }
        return null;
    }

    public final boolean equalsName(String name) {
        return StringsKt__StringsJVMKt.equals(name(), name, true);
    }
}
