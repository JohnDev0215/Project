package com.amazon.music.find.converter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import androidx.core.os.EnvironmentCompat;
import androidx.core.view.PointerIconCompat;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.ContentAccessType;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.R;
import com.amazon.music.find.converter.model.FindImage;
import com.amazon.music.find.converter.model.MusicSearchAlbum;
import com.amazon.music.find.converter.model.MusicSearchArtist;
import com.amazon.music.find.converter.model.MusicSearchCommunityPlaylist;
import com.amazon.music.find.converter.model.MusicSearchFeatureBarker;
import com.amazon.music.find.converter.model.MusicSearchLiveEvent;
import com.amazon.music.find.converter.model.MusicSearchPlaylist;
import com.amazon.music.find.converter.model.MusicSearchPodcastEpisode;
import com.amazon.music.find.converter.model.MusicSearchPodcastShow;
import com.amazon.music.find.converter.model.MusicSearchQuery;
import com.amazon.music.find.converter.model.MusicSearchStation;
import com.amazon.music.find.converter.model.MusicSearchTrack;
import com.amazon.music.find.converter.model.MusicSearchUserPlaylist;
import com.amazon.music.find.converter.model.SearchResponseMessageBrushV2;
import com.amazon.music.find.converter.transformation.BitmapTransformationUtil;
import com.amazon.music.find.model.SearchSuggestion;
import com.amazon.music.find.model.SearchSuggestionEntity;
import com.amazon.music.find.model.search.AlbumSearchItem;
import com.amazon.music.find.model.search.ArtistSearchItem;
import com.amazon.music.find.model.search.LiveEventSearchItem;
import com.amazon.music.find.model.search.PlaylistSearchItem;
import com.amazon.music.find.model.search.PlaylistType;
import com.amazon.music.find.model.search.PodcastEpisodeSearchItem;
import com.amazon.music.find.model.search.PodcastShowSearchItem;
import com.amazon.music.find.model.search.SearchAssetType;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.find.model.search.SportsSearchItem;
import com.amazon.music.find.model.search.StationSearchItem;
import com.amazon.music.find.model.search.TrackSearchItem;
import com.amazon.music.find.providers.CacheImageProvider;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.utils.ContentTierUtils;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.find.utils.DeeplinksUtils;
import com.amazon.music.find.utils.LanguageOfPerformanceUtils;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.find.utils.ParentalControlsPerContentTierUtils;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.find.common.AssetQuality;
import com.amazon.music.platform.find.common.ContentTier;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.platform.find.common.ParentalControlItem;
import com.amazon.music.platform.find.common.ParentalControls;
import com.amazon.music.platform.find.common.ParentalControlsPerContentTier;
import com.amazon.music.platform.find.service.common.EntityProperties;
import com.amazon.music.platform.find.service.common.SearchEntity;
import com.amazon.music.platform.find.service.history.SearchHistoryEntity;
import com.amazon.music.platform.find.service.history.SearchHistoryItem;
import com.amazon.music.platform.find.service.history.SearchHistoryQuery;
import com.amazon.music.search.RelatedPlaylist;
import com.amazon.music.search.SpellCorrections;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Artist;
import com.amazon.musicensembleservice.brush.CommunityPlaylist;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.FeaturedBarker;
import com.amazon.musicensembleservice.brush.Genre;
import com.amazon.musicensembleservice.brush.Image;
import com.amazon.musicensembleservice.brush.NavigationEntity;
import com.amazon.musicensembleservice.brush.PageResponse;
import com.amazon.musicensembleservice.brush.Playlist;
import com.amazon.musicensembleservice.brush.Station;
import com.amazon.musicensembleservice.brush.Track;
import com.amazon.musicensembleservice.brush.UserPlaylist;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: BrushV2Converter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000ú\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 Ï\u0001*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0002Ï\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ»\u0001\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010\u001e\u001a\u00020\u00182\b\b\u0002\u0010\u001f\u001a\u00020\u00182\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u001a2\b\b\u0002\u0010\"\u001a\u00020\u00182\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u001aH\u0002¢\u0006\u0002\u0010&J}\u0010'\u001a\u00020(2\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00182\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010*\u001a\u0004\u0018\u00010+2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010+2\b\b\u0002\u0010\"\u001a\u00020\u0018H\u0002¢\u0006\u0002\u0010-J\u0012\u0010.\u001a\u00020\u00112\b\u0010\u001c\u001a\u0004\u0018\u00010\u0011H\u0002J~\u0010/\u001a\u0002002\u0006\u0010\u0010\u001a\u00020\u00112\b\u00101\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u00103\u001a\u00020+2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00182\b\b\u0002\u0010\"\u001a\u00020\u00182\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$H\u0002J\u001a\u00105\u001a\u00020\u00112\u0006\u00106\u001a\u00020\u00112\b\u00107\u001a\u0004\u0018\u00010\u0011H\u0014JD\u00108\u001a\u0002092\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u0018H\u0002J#\u0010:\u001a\u0004\u0018\u00010\u00112\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010+H\u0002¢\u0006\u0002\u0010;J@\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\"\u001a\u00020\u00182\b\b\u0002\u0010\u001f\u001a\u00020\u0018H\u0002J\u001a\u0010?\u001a\u0004\u0018\u00010=2\u0006\u0010@\u001a\u00020A2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u001a\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\u00112\b\u0010\u001c\u001a\u0004\u0018\u00010\u0011H\u0002J\u0017\u0010E\u001a\u00020F2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002¢\u0006\u0002\u0010GJ\u009f\u0001\u0010H\u001a\u00020I2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010J\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u001e\u001a\u00020\u00182\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00112\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00112\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00110\u001a2\b\b\u0002\u0010\u001f\u001a\u00020\u00182\b\b\u0002\u0010\"\u001a\u00020\u00182\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u001aH\u0002¢\u0006\u0002\u0010LJä\u0001\u0010M\u001a\u00020=2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010N\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00182\b\b\u0002\u0010\"\u001a\u00020\u00182\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010P\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\u00112\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a2\u001c\b\u0002\u0010S\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001a\u0018\u00010T2\u001c\b\u0002\u0010U\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001a\u0018\u00010T2\u0016\b\u0002\u0010V\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020W\u0018\u00010T2\n\b\u0002\u0010X\u001a\u0004\u0018\u00010WH\u0014Jd\u0010M\u001a\u00020=2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010N\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010Y\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00182\b\b\u0002\u0010\"\u001a\u00020\u00182\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010\u0011H\u0014J\u008a\u0001\u0010[\u001a\u00020=2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\\\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00182\b\b\u0002\u0010\"\u001a\u00020\u00182\n\b\u0002\u0010P\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010]\u001a\u0004\u0018\u00010\u00112\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a2\u001c\b\u0002\u0010^\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001a\u0018\u00010TH\u0014JX\u0010[\u001a\u00020=2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\\\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010Y\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00182\b\b\u0002\u0010\"\u001a\u00020\u00182\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010\u0011H\u0014J\u001a\u0010_\u001a\u0004\u0018\u00010=2\u0006\u0010D\u001a\u00020\u00112\u0006\u0010`\u001a\u00020+H\u0002Jo\u0010a\u001a\u0004\u0018\u00010\u00112\u0006\u0010b\u001a\u00020c2\n\b\u0002\u0010d\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00182\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010*\u001a\u0004\u0018\u00010+2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010+2\u0010\b\u0002\u0010e\u001a\n\u0012\u0004\u0012\u00020f\u0018\u00010\u001a2\n\b\u0002\u0010g\u001a\u0004\u0018\u00010+H\u0014¢\u0006\u0002\u0010hJ8\u0010i\u001a\u00020j2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010Y\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u0018H\u0002JX\u0010k\u001a\u00020l2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010m\u001a\u0004\u0018\u00010n2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00182\b\b\u0002\u0010\"\u001a\u00020\u0018H\u0002J\u009b\u0002\u0010o\u001a\u00020p2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010J\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u00112\b\u0010q\u001a\u0004\u0018\u00010\u00112\b\u0010r\u001a\u0004\u0018\u00010\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010\u001e\u001a\u00020\u00182\n\b\u0002\u0010s\u001a\u0004\u0018\u00010t2\n\b\u0002\u0010u\u001a\u0004\u0018\u00010t2\b\b\u0002\u0010v\u001a\u00020\u00112\u0010\b\u0002\u0010w\u001a\n\u0012\u0004\u0012\u00020x\u0018\u00010\u001a2\b\b\u0002\u0010\u001f\u001a\u00020\u00182\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u001a2\b\b\u0002\u0010\"\u001a\u00020\u00182\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\u0010\b\u0002\u0010e\u001a\n\u0012\u0004\u0012\u00020f\u0018\u00010\u001a2\n\b\u0002\u0010g\u001a\u0004\u0018\u00010+2\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u001aH\u0002¢\u0006\u0002\u0010yJC\u0010z\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010d\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00182\u0010\b\u0002\u0010e\u001a\n\u0012\u0004\u0012\u00020f\u0018\u00010\u001a2\n\b\u0002\u0010g\u001a\u0004\u0018\u00010+H\u0002¢\u0006\u0002\u0010{Jz\u0010|\u001a\u00020}2\u0006\u0010\u0010\u001a\u00020\u00112\b\u00102\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u00103\u001a\u00020+2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00112\u000e\b\u0002\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00110\u001a2\b\b\u0002\u0010\u001f\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002J(\u0010~\u001a\u00020\u007f2\u0007\u0010\u0080\u0001\u001a\u00028\u00002\u0006\u0010\"\u001a\u00020\u00182\u0007\u0010\u0081\u0001\u001a\u00020\u0018H\u0016¢\u0006\u0003\u0010\u0082\u0001J\u0017\u0010~\u001a\u00020\u007f2\r\u0010\u0083\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aH\u0016J\"\u0010\u0084\u0001\u001a\n\u0012\u0004\u0012\u00020x\u0018\u00010\u001a2\u000f\u0010w\u001a\u000b\u0012\u0005\u0012\u00030\u0085\u0001\u0018\u00010\u001aH\u0002J\"\u0010\u0086\u0001\u001a\n\u0012\u0004\u0012\u00020x\u0018\u00010\u001a2\u000f\u0010w\u001a\u000b\u0012\u0005\u0012\u00030\u0087\u0001\u0018\u00010\u001aH\u0002J\u0011\u0010\u0088\u0001\u001a\u00020\u00112\u0006\u0010 \u001a\u00020!H\u0002J\u001e\u0010\u0089\u0001\u001a\u00020\u007f2\u0007\u0010\u008a\u0001\u001a\u00020\u00112\n\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u0001H\u0016J\u0016\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u00012\b\u0010#\u001a\u0004\u0018\u00010$H\u0002J/\u0010\u008f\u0001\u001a\b\u0012\u0004\u0012\u00020=0\u001a2\u000e\u0010\u0090\u0001\u001a\t\u0012\u0005\u0012\u00030\u0091\u00010\u001a2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0004J\u001f\u0010\u0092\u0001\u001a\b\u0012\u0004\u0012\u00020=0\u001a2\u000e\u0010\u0093\u0001\u001a\t\u0012\u0005\u0012\u00030\u0094\u00010\u001aH\u0004J%\u0010\u0095\u0001\u001a\u0004\u0018\u00010=2\b\u0010\u0096\u0001\u001a\u00030\u0097\u00012\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0004J/\u0010\u0098\u0001\u001a\b\u0012\u0004\u0012\u00020=0\u001a2\u000e\u0010\u0099\u0001\u001a\t\u0012\u0005\u0012\u00030\u0097\u00010\u001a2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0004J%\u0010\u009a\u0001\u001a\u0004\u0018\u00010=2\b\u0010\u009b\u0001\u001a\u00030\u009c\u00012\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0004J/\u0010\u009d\u0001\u001a\b\u0012\u0004\u0012\u00020=0\u001a2\u000e\u0010\u009e\u0001\u001a\t\u0012\u0005\u0012\u00030\u009c\u00010\u001a2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0004J%\u0010\u009f\u0001\u001a\u0004\u0018\u00010=2\b\u0010 \u0001\u001a\u00030¡\u00012\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0004J%\u0010¢\u0001\u001a\u0004\u0018\u00010=2\b\u0010£\u0001\u001a\u00030¤\u00012\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0004J/\u0010¥\u0001\u001a\b\u0012\u0004\u0012\u00020=0\u001a2\u000e\u0010¦\u0001\u001a\t\u0012\u0005\u0012\u00030¤\u00010\u001a2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0004J%\u0010§\u0001\u001a\u0004\u0018\u00010=2\b\u0010¨\u0001\u001a\u00030©\u00012\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0004J/\u0010ª\u0001\u001a\b\u0012\u0004\u0012\u00020=0\u001a2\u000e\u0010«\u0001\u001a\t\u0012\u0005\u0012\u00030©\u00010\u001a2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0004J%\u0010¬\u0001\u001a\u0004\u0018\u00010=2\b\u0010\u00ad\u0001\u001a\u00030®\u00012\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0004J/\u0010¯\u0001\u001a\b\u0012\u0004\u0012\u00020=0\u001a2\u000e\u0010°\u0001\u001a\t\u0012\u0005\u0012\u00030®\u00010\u001a2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0004J%\u0010±\u0001\u001a\u0004\u0018\u00010=2\b\u0010²\u0001\u001a\u00030³\u00012\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0004J/\u0010´\u0001\u001a\b\u0012\u0004\u0012\u00020=0\u001a2\u000e\u0010µ\u0001\u001a\t\u0012\u0005\u0012\u00030³\u00010\u001a2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0004J7\u0010¶\u0001\u001a\u0004\u0018\u00010=2\b\u0010·\u0001\u001a\u00030¸\u00012\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u00182\n\b\u0002\u0010`\u001a\u0004\u0018\u00010+H\u0004¢\u0006\u0003\u0010¹\u0001J/\u0010º\u0001\u001a\b\u0012\u0004\u0012\u00020=0\u001a2\u000e\u0010»\u0001\u001a\t\u0012\u0005\u0012\u00030¸\u00010\u001a2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0004J\u001d\u0010¼\u0001\u001a\u0004\u0018\u00010=2\b\u0010½\u0001\u001a\u00030¾\u00012\u0006\u0010\u001f\u001a\u00020\u0018H\u0004J'\u0010¿\u0001\u001a\b\u0012\u0004\u0012\u00020=0\u001a2\u000e\u0010À\u0001\u001a\t\u0012\u0005\u0012\u00030¾\u00010\u001a2\u0006\u0010\u001f\u001a\u00020\u0018H\u0004J\u001f\u0010Á\u0001\u001a\b\u0012\u0004\u0012\u00020=0\u001a2\u000e\u0010Â\u0001\u001a\t\u0012\u0005\u0012\u00030Ã\u00010\u001aH\u0004J\u0014\u0010Ä\u0001\u001a\u00020=2\t\u0010Å\u0001\u001a\u0004\u0018\u00010\u0011H\u0004J2\u0010Æ\u0001\u001a\u00020=2\b\u0010\u008b\u0001\u001a\u00030\u008c\u00012\t\u0010Ç\u0001\u001a\u0004\u0018\u00010\u00112\u0007\u0010Å\u0001\u001a\u00020\u00112\t\b\u0002\u0010È\u0001\u001a\u00020\u0018H\u0004J\u001e\u0010É\u0001\u001a\b\u0012\u0004\u0012\u00020C0\u001a2\r\u0010Ê\u0001\u001a\b\u0012\u0004\u0012\u00020\u00110\u001aH\u0004J!\u0010Ë\u0001\u001a\u00030Ì\u00012\b\u0010Í\u0001\u001a\u00030\u0091\u00012\u000b\b\u0002\u0010Î\u0001\u001a\u0004\u0018\u00010\u0011H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0007\u001a\u00020\bX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006Ð\u0001"}, d2 = {"Lcom/amazon/music/find/converter/BrushV2Converter;", "E", "", "context", "Landroid/content/Context;", "cacheImageProvider", "Lcom/amazon/music/find/providers/CacheImageProvider;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Landroid/content/Context;Lcom/amazon/music/find/providers/CacheImageProvider;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "getContext", "()Landroid/content/Context;", "getSearchFeaturesProvider", "()Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "constructAlbumEntity", "Lcom/amazon/musicensembleservice/brush/Album;", "title", "", "id", "thumbnailImage", "Lcom/amazon/musicensembleservice/brush/Image;", "artistName", "artistAsin", "hasExplicitLanguage", "", "contentTiers", "", "Lcom/amazon/music/platform/find/common/ContentTier;", "blockRef", "isOwned", "isInLibrary", "shouldSubtitleShowEntityType", ContextMappingConstants.CONTENT_ENCODING, "Lcom/amazon/music/contentEncoding/ContentEncoding;", "showOrdinal", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "languageOfPerformances", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/musicensembleservice/brush/Image;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;Ljava/lang/String;ZZZLjava/util/List;ZLcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;Ljava/util/List;)Lcom/amazon/musicensembleservice/brush/Album;", "constructArtistEntity", "Lcom/amazon/musicensembleservice/brush/Artist;", PageUriUtils.SOURCE_QUERY_PARAM, "albumCount", "", "trackCount", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/musicensembleservice/brush/Image;Ljava/lang/String;ZLjava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Z)Lcom/amazon/musicensembleservice/brush/Artist;", "constructBlockRef", "constructCommunityPlaylistEntity", "Lcom/amazon/musicensembleservice/brush/CommunityPlaylist;", "curator", MediaTrack.ROLE_DESCRIPTION, "tracksCount", "playlistId", "constructCompactSecondaryTitle", "secondaryTitle", "suffix", "constructGenreEntity", "Lcom/amazon/musicensembleservice/brush/Genre;", "constructLibraryArtistSecondaryTitle", "(Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/String;", "constructLiveEventEntity", "Lcom/amazon/musicensembleservice/brush/Entity;", ContentUtils.KEY_EVENT_ID, "constructMusicEntityForSearchEntity", "searchEntity", "Lcom/amazon/music/platform/find/service/common/SearchEntity;", "constructNavigationEntityForSearchTerm", "Lcom/amazon/musicensembleservice/brush/NavigationEntity;", "searchTerm", "constructParentalControl", "Lcom/amazon/musicensembleservice/brush/ParentalControls;", "(Ljava/lang/Boolean;)Lcom/amazon/musicensembleservice/brush/ParentalControls;", "constructPlaylistEntity", "Lcom/amazon/musicensembleservice/brush/Playlist;", "asin", "contentTypes", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/musicensembleservice/brush/Image;Ljava/lang/Boolean;ZLjava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/List;ZZLcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;Ljava/util/List;)Lcom/amazon/musicensembleservice/brush/Playlist;", "constructPodcastEpisodeEntity", "podcastShowName", "podcastShowId", "catalogId", "podcastEpisodeVariantId", "podcastShowVariantId", "podcastEpisodeTierBenefits", "", "podcastShowTierBenefits", "availabilityDisplayDate", "Ljava/util/Date;", "publishDate", "contentUrl", ContextMappingConstants.ENTITY_ID, "constructPodcastShowEntity", "podcastProviderName", "variantId", "tierBenefits", "constructSearchQueryEntity", "index", "constructSecondaryTitle", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/platform/find/common/EntityType;", MediaTrack.ROLE_SUBTITLE, "relatedPlaylists", "Lcom/amazon/music/search/RelatedPlaylist;", "position", "(Lcom/amazon/music/platform/find/common/EntityType;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Integer;)Ljava/lang/String;", "constructSportEntity", "Lcom/amazon/musicensembleservice/brush/FeaturedBarker;", "constructStationEntity", "Lcom/amazon/musicensembleservice/brush/Station;", "parentalControlsPerContentTier", "Lcom/amazon/music/platform/find/common/ParentalControlsPerContentTier;", "constructTrackEntity", "Lcom/amazon/musicensembleservice/brush/Track;", "albumName", "albumAsin", "trackId", "", "duration", "assetType", "assetQualities", "Lcom/amazon/musicensembleservice/brush/AssetQuality;", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/musicensembleservice/brush/Image;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;Ljava/lang/String;ZZLjava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/util/List;ZLjava/util/List;ZLcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;Ljava/util/List;Ljava/lang/Integer;Ljava/util/List;)Lcom/amazon/musicensembleservice/brush/Track;", "constructTrackSecondaryTitle", "(Ljava/lang/String;ZLjava/util/List;Ljava/lang/Integer;)Ljava/lang/String;", "constructUserPlaylistEntity", "Lcom/amazon/musicensembleservice/brush/UserPlaylist;", "convert", "Lcom/amazon/musicensembleservice/brush/PageResponse;", "data", "showSpellCorrections", "(Ljava/lang/Object;ZZ)Lcom/amazon/musicensembleservice/brush/PageResponse;", "entities", "convertAssetQualitiesFromFindAssetQualities", "Lcom/amazon/music/platform/find/common/AssetQuality;", "convertAssetQualitiesFromSearchAssetQualities", "Lcom/amazon/music/models/AssetQuality;", "convertContentEncoding", "convertEmptyResponse", SearchIntents.EXTRA_QUERY, "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "convertPlaymodeEligibility", "Lcom/amazon/musicensembleservice/PlaymodeEligibility;", "convertTopHitItems", "topHitItems", "Lcom/amazon/music/find/model/search/SearchItem;", "getMusicEntitiesForHistory", "searchHistoryQueries", "Lcom/amazon/music/platform/find/service/history/SearchHistoryItem;", "getMusicEntitiesForSearchAlbum", "album", "Lcom/amazon/music/find/model/search/AlbumSearchItem;", "getMusicEntitiesForSearchAlbums", "albums", "getMusicEntitiesForSearchArtist", "artist", "Lcom/amazon/music/find/model/search/ArtistSearchItem;", "getMusicEntitiesForSearchArtists", "artists", "getMusicEntitiesForSearchLiveEvent", "liveEventItem", "Lcom/amazon/music/find/model/search/LiveEventSearchItem;", "getMusicEntitiesForSearchPlaylist", Environment.PLAYLIST_PATH, "Lcom/amazon/music/find/model/search/PlaylistSearchItem;", "getMusicEntitiesForSearchPlaylists", "playlists", "getMusicEntitiesForSearchPodcastEpisode", "podcastEpisode", "Lcom/amazon/music/find/model/search/PodcastEpisodeSearchItem;", "getMusicEntitiesForSearchPodcastEpisodes", "podcastEpisodes", "getMusicEntitiesForSearchPodcastShow", "podcastShow", "Lcom/amazon/music/find/model/search/PodcastShowSearchItem;", "getMusicEntitiesForSearchPodcastShows", DeeplinksUtils.PODCASTS_PATH_SEGMENT, "getMusicEntitiesForSearchStation", "station", "Lcom/amazon/music/find/model/search/StationSearchItem;", "getMusicEntitiesForSearchStations", "stations", "getMusicEntitiesForSearchTrack", "track", "Lcom/amazon/music/find/model/search/TrackSearchItem;", "(Lcom/amazon/music/find/model/search/TrackSearchItem;ZZLjava/lang/Integer;)Lcom/amazon/musicensembleservice/brush/Entity;", "getMusicEntitiesForSearchTracks", "tracks", "getMusicEntitiesForSportsItem", "sportsItem", "Lcom/amazon/music/find/model/search/SportsSearchItem;", "getMusicEntitiesForSportsItems", "sportsItems", "getMusicEntitiesForSuggestions", "searchSuggestions", "Lcom/amazon/music/find/model/SearchSuggestion;", "getMusicEntityForNoResults", "originalQuery", "getMusicEntityForSpellCorrections", "executedTerm", "shouldCenterMessage", "getNavigationEntities", "searchTerms", "getThumbnail", "Lcom/amazon/music/find/converter/model/FindImage;", "searchItem", "defaultArtworkUri", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BrushV2Converter<E> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String JAPAN_LOCALE = "ja";
    public static final String KEY_IS_EXPLICIT = "isExplicit";
    public static final String KEY_SUBTITLE_CONTAINS_ENTITY_TYPE = "subtitleContainsEntityType";
    public static final String SEARCH_QUERY_ICON_URL = "https://m.media-amazon.com/images/G/01/music-find/mobile/default/48x48/dmfind-bsh-mobile-default-48x48-1235-asdfj.png";
    private static final String SPACE = " ";
    private static final String TEXT_DIVIDER = "•";
    private final CacheImageProvider cacheImageProvider;
    private final Context context;
    private final SearchFeaturesProvider searchFeaturesProvider;

    /* JADX INFO: compiled from: BrushV2Converter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[PlaylistType.values().length];
            iArr[PlaylistType.PRIME_PLAYLIST.ordinal()] = 1;
            iArr[PlaylistType.COMMUNITY_PLAYLIST.ordinal()] = 2;
            iArr[PlaylistType.USER_PLAYLIST.ordinal()] = 3;
            iArr[PlaylistType.SHARED_USER_PLAYLIST.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[EntityType.values().length];
            iArr2[EntityType.GENRE.ordinal()] = 1;
            iArr2[EntityType.ALBUM.ordinal()] = 2;
            iArr2[EntityType.ARTIST.ordinal()] = 3;
            iArr2[EntityType.TRACK.ordinal()] = 4;
            iArr2[EntityType.STATION.ordinal()] = 5;
            iArr2[EntityType.PLAYLIST.ordinal()] = 6;
            iArr2[EntityType.VIDEO_PLAYLIST.ordinal()] = 7;
            iArr2[EntityType.COMMUNITY_PLAYLIST.ordinal()] = 8;
            iArr2[EntityType.PODCAST_SHOW.ordinal()] = 9;
            iArr2[EntityType.PODCAST_EPISODE.ordinal()] = 10;
            iArr2[EntityType.VIDEO.ordinal()] = 11;
            iArr2[EntityType.SPORT.ordinal()] = 12;
            iArr2[EntityType.LIVE_EVENT.ordinal()] = 13;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[ContentEncoding.values().length];
            iArr3[ContentEncoding.HD.ordinal()] = 1;
            iArr3[ContentEncoding.UHD.ordinal()] = 2;
            iArr3[ContentEncoding.IMMERSIVE.ordinal()] = 3;
            iArr3[ContentEncoding.SONY_360.ordinal()] = 4;
            iArr3[ContentEncoding.DOLBY_ATMOS.ordinal()] = 5;
            iArr3[ContentEncoding.UNKNOWN.ordinal()] = 6;
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    private final String constructBlockRef(String blockRef) {
        return blockRef == null ? "" : blockRef;
    }

    public BrushV2Converter(Context context, CacheImageProvider cacheImageProvider, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cacheImageProvider, "cacheImageProvider");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        this.context = context;
        this.cacheImageProvider = cacheImageProvider;
        this.searchFeaturesProvider = searchFeaturesProvider;
    }

    protected final Context getContext() {
        return this.context;
    }

    protected final SearchFeaturesProvider getSearchFeaturesProvider() {
        return this.searchFeaturesProvider;
    }

    /* JADX INFO: compiled from: BrushV2Converter.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/find/converter/BrushV2Converter$Companion;", "", "()V", "JAPAN_LOCALE", "", "KEY_IS_EXPLICIT", "KEY_SUBTITLE_CONTAINS_ENTITY_TYPE", "SEARCH_QUERY_ICON_URL", "SPACE", "TEXT_DIVIDER", "convertAssetQualities", "", "Lcom/amazon/music/platform/find/common/AssetQuality;", "assetQualities", "Lcom/amazon/music/models/AssetQuality;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<AssetQuality> convertAssetQualities(List<com.amazon.music.models.AssetQuality> assetQualities) {
            if (assetQualities == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(assetQualities, 10));
            for (com.amazon.music.models.AssetQuality assetQuality : assetQualities) {
                arrayList.add(new AssetQuality(assetQuality.getPid(), assetQuality.getQuality()));
            }
            return arrayList;
        }
    }

    public PageResponse convert(List<? extends E> entities) {
        Intrinsics.checkNotNullParameter(entities, "entities");
        return new PageResponse();
    }

    public PageResponse convert(E data, boolean showOrdinal, boolean showSpellCorrections) {
        return new PageResponse();
    }

    public PageResponse convertEmptyResponse(String query, SpellCorrections spellCorrections) {
        Intrinsics.checkNotNullParameter(query, "query");
        return new PageResponse();
    }

    private final NavigationEntity constructNavigationEntityForSearchTerm(String searchTerm, String blockRef) {
        NavigationEntity navigationEntity = new NavigationEntity();
        navigationEntity.setTitle(searchTerm);
        navigationEntity.setTarget(searchTerm);
        navigationEntity.setBlockRef(blockRef);
        return navigationEntity;
    }

    protected final Entity getMusicEntitiesForSearchArtist(ArtistSearchItem artist, boolean shouldSubtitleShowEntityType, boolean showOrdinal) {
        List<ContentTier> listConvertFromContentAccessTypeSet;
        Intrinsics.checkNotNullParameter(artist, "artist");
        String title = artist.getTitle();
        String asin = artist.getAsin();
        BitmapDrawable bitmapDrawable = null;
        if (title == null || asin == null) {
            return null;
        }
        if (artist.getArtworkUri() == null && artist.getArtworkId() != null) {
            Resources resources = this.context.getResources();
            BitmapTransformationUtil bitmapTransformationUtil = BitmapTransformationUtil.INSTANCE;
            Bitmap bitmap = ((BitmapDrawable) this.cacheImageProvider.getCacheImage(artist, this.context.getResources().getDimensionPixelSize(R.dimen.artwork_default_dimen))).getBitmap();
            Intrinsics.checkNotNullExpressionValue(bitmap, "cacheImageProvider.getCa…as BitmapDrawable).bitmap");
            bitmapDrawable = new BitmapDrawable(resources, bitmapTransformationUtil.transform(bitmap));
        }
        FindImage findImage = new FindImage(bitmapDrawable);
        findImage.setUrl(artist.getArtworkUri());
        findImage.setType("ORIGINAL_ART");
        Set<ContentAccessType> contentAccessSet = artist.getContentAccessSet();
        String blockRef = artist.getBlockRef();
        if (contentAccessSet == null) {
            listConvertFromContentAccessTypeSet = CollectionsKt__CollectionsKt.emptyList();
        } else {
            listConvertFromContentAccessTypeSet = ContentTierUtils.INSTANCE.convertFromContentAccessTypeSet(contentAccessSet);
        }
        return constructArtistEntity(title, asin, findImage, blockRef, shouldSubtitleShowEntityType, listConvertFromContentAccessTypeSet, artist.getSource(), Integer.valueOf(artist.getAlbumCount()), Integer.valueOf(artist.getTrackCount()), showOrdinal);
    }

    protected final Entity getMusicEntitiesForSearchStation(StationSearchItem station, boolean shouldSubtitleShowEntityType, boolean showOrdinal) {
        List<ContentTier> listConvertFromContentAccessTypeSet;
        Intrinsics.checkNotNullParameter(station, "station");
        String title = station.getTitle();
        String key = station.getKey();
        Set<ContentAccessType> contentAccessSet = station.getContentAccessSet();
        Map<ContentAccessType, Boolean> explicitMap = station.getExplicitMap();
        if (title == null || key == null) {
            return null;
        }
        Image image = new Image();
        image.setUrl(station.getArtworkUri());
        image.setType("ORIGINAL_ART");
        String blockRef = station.getBlockRef();
        if (contentAccessSet == null) {
            listConvertFromContentAccessTypeSet = CollectionsKt__CollectionsKt.emptyList();
        } else {
            listConvertFromContentAccessTypeSet = ContentTierUtils.INSTANCE.convertFromContentAccessTypeSet(contentAccessSet);
        }
        return constructStationEntity(title, key, image, explicitMap == null ? null : ParentalControlsPerContentTierUtils.INSTANCE.convertContentAccessTypeExplicitMap(explicitMap), listConvertFromContentAccessTypeSet, blockRef, shouldSubtitleShowEntityType, showOrdinal);
    }

    protected final Entity getMusicEntitiesForSearchPlaylist(PlaylistSearchItem playlist, boolean shouldSubtitleShowEntityType, boolean showOrdinal) {
        List<ContentTier> listConvertFromContentAccessTypeSet;
        ArrayList arrayList;
        List<ContentTier> listConvertFromContentAccessTypeSet2;
        List<ContentTier> listConvertFromContentAccessTypeSet3;
        List<ContentTier> listConvertFromContentAccessTypeSet4;
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        String title = playlist.getTitle();
        String asin = playlist.getAsin();
        String playlistId = playlist.getPlaylistId();
        Set<ContentAccessType> contentAccessSet = playlist.getContentAccessSet();
        PlaylistType playlistType = playlist.getPlaylistType();
        if (title == null) {
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[playlistType.ordinal()];
        if (i == 1) {
            Uri uriGeneratePrimePlaylistImageUri = this.cacheImageProvider.generatePrimePlaylistImageUri(playlist);
            FindImage thumbnail = getThumbnail(playlist, uriGeneratePrimePlaylistImageUri == null ? null : uriGeneratePrimePlaylistImageUri.toString());
            if (asin == null) {
                return null;
            }
            String blockRef = playlist.getBlockRef();
            if (contentAccessSet == null) {
                listConvertFromContentAccessTypeSet = CollectionsKt__CollectionsKt.emptyList();
            } else {
                listConvertFromContentAccessTypeSet = ContentTierUtils.INSTANCE.convertFromContentAccessTypeSet(contentAccessSet);
            }
            List<ContentTier> list = listConvertFromContentAccessTypeSet;
            boolean isInLibrary = playlist.getIsInLibrary();
            String description = playlist.getDescription();
            List<String> listListOf = CollectionsKt__CollectionsJVMKt.listOf(playlist.getSearchAssetType() != SearchAssetType.VIDEO ? "AUDIO" : "VIDEO");
            PlaymodeEligibility playmodeEligibility = playlist.getPlaymodeEligibility();
            List<String> languageOfPerformances = playlist.getLanguageOfPerformances();
            if (languageOfPerformances == null) {
                arrayList = null;
            } else {
                ArrayList arrayList2 = new ArrayList();
                Iterator<T> it = languageOfPerformances.iterator();
                while (it.hasNext()) {
                    String languageOfPerformanceString = LanguageOfPerformanceUtils.INSTANCE.getLanguageOfPerformanceString(getContext(), (String) it.next());
                    if (languageOfPerformanceString != null) {
                        arrayList2.add(languageOfPerformanceString);
                    }
                }
                arrayList = arrayList2;
            }
            return constructPlaylistEntity(title, asin, thumbnail, Boolean.FALSE, isInLibrary, description, list, blockRef, listListOf, shouldSubtitleShowEntityType, showOrdinal, playmodeEligibility, arrayList);
        }
        if (i == 2) {
            Uri uriGenerateUserPlaylistImageUri = this.cacheImageProvider.generateUserPlaylistImageUri(playlist);
            FindImage thumbnail2 = getThumbnail(playlist, uriGenerateUserPlaylistImageUri != null ? uriGenerateUserPlaylistImageUri.toString() : null);
            String description2 = playlist.getDescription();
            String blockRef2 = playlist.getBlockRef();
            int trackCount = playlist.getTrackCount();
            String curator = playlist.getCurator();
            if (contentAccessSet == null) {
                listConvertFromContentAccessTypeSet2 = CollectionsKt__CollectionsKt.emptyList();
            } else {
                listConvertFromContentAccessTypeSet2 = ContentTierUtils.INSTANCE.convertFromContentAccessTypeSet(contentAccessSet);
            }
            return constructCommunityPlaylistEntity(title, curator, description2, thumbnail2, trackCount, listConvertFromContentAccessTypeSet2, playlistId, blockRef2, shouldSubtitleShowEntityType, showOrdinal, playlist.getPlaymodeEligibility());
        }
        if (i == 3) {
            Uri uriGenerateUserPlaylistImageUri2 = this.cacheImageProvider.generateUserPlaylistImageUri(playlist);
            FindImage thumbnail3 = getThumbnail(playlist, uriGenerateUserPlaylistImageUri2 != null ? uriGenerateUserPlaylistImageUri2.toString() : null);
            String description3 = playlist.getDescription();
            String blockRef3 = playlist.getBlockRef();
            int trackCount2 = playlist.getTrackCount();
            List<String> listListOf2 = CollectionsKt__CollectionsJVMKt.listOf(playlist.getSearchAssetType() != SearchAssetType.VIDEO ? "AUDIO" : "VIDEO");
            if (contentAccessSet == null) {
                listConvertFromContentAccessTypeSet3 = CollectionsKt__CollectionsKt.emptyList();
            } else {
                listConvertFromContentAccessTypeSet3 = ContentTierUtils.INSTANCE.convertFromContentAccessTypeSet(contentAccessSet);
            }
            return constructUserPlaylistEntity(title, description3, thumbnail3, true, trackCount2, playlistId, blockRef3, listListOf2, shouldSubtitleShowEntityType, showOrdinal, listConvertFromContentAccessTypeSet3);
        }
        if (i != 4) {
            throw new NoWhenBranchMatchedException();
        }
        Uri uriGeneratePrimePlaylistImageUri2 = this.cacheImageProvider.generatePrimePlaylistImageUri(playlist);
        FindImage thumbnail4 = getThumbnail(playlist, uriGeneratePrimePlaylistImageUri2 != null ? uriGeneratePrimePlaylistImageUri2.toString() : null);
        String description4 = playlist.getDescription();
        String blockRef4 = playlist.getBlockRef();
        int trackCount3 = playlist.getTrackCount();
        List<String> listListOf3 = CollectionsKt__CollectionsJVMKt.listOf(playlist.getSearchAssetType() != SearchAssetType.VIDEO ? "AUDIO" : "VIDEO");
        if (contentAccessSet == null) {
            listConvertFromContentAccessTypeSet4 = CollectionsKt__CollectionsKt.emptyList();
        } else {
            listConvertFromContentAccessTypeSet4 = ContentTierUtils.INSTANCE.convertFromContentAccessTypeSet(contentAccessSet);
        }
        return constructUserPlaylistEntity(title, description4, thumbnail4, false, trackCount3, playlistId, blockRef4, listListOf3, shouldSubtitleShowEntityType, showOrdinal, listConvertFromContentAccessTypeSet4);
    }

    public static /* synthetic */ Entity getMusicEntitiesForSearchTrack$default(BrushV2Converter brushV2Converter, TrackSearchItem trackSearchItem, boolean z, boolean z2, Integer num, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getMusicEntitiesForSearchTrack");
        }
        if ((i & 8) != 0) {
            num = null;
        }
        return brushV2Converter.getMusicEntitiesForSearchTrack(trackSearchItem, z, z2, num);
    }

    protected final Entity getMusicEntitiesForSearchTrack(TrackSearchItem track, boolean shouldSubtitleShowEntityType, boolean showOrdinal, Integer index) {
        List<ContentTier> listConvertFromContentAccessTypeSet;
        FindImage findImage;
        Long lValueOf;
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(track, "track");
        String title = track.getTitle();
        String asin = track.getAsin();
        Set<ContentAccessType> contentAccessSet = track.getContentAccessSet();
        if (title == null || asin == null) {
            return null;
        }
        FindImage thumbnail$default = getThumbnail$default(this, track, null, 2, null);
        String album = track.getAlbum();
        String albumAsin = track.getAlbumAsin();
        String artist = track.getArtist();
        String artistAsin = track.getArtistAsin();
        boolean isInLibrary = track.getIsInLibrary();
        Long id = track.getId();
        boolean zIsOwned = track.isOwned();
        if (contentAccessSet == null) {
            listConvertFromContentAccessTypeSet = CollectionsKt__CollectionsKt.emptyList();
        } else {
            listConvertFromContentAccessTypeSet = ContentTierUtils.INSTANCE.convertFromContentAccessTypeSet(contentAccessSet);
        }
        List<ContentTier> list = listConvertFromContentAccessTypeSet;
        if (track.getDuration() == null) {
            lValueOf = null;
            findImage = thumbnail$default;
        } else {
            findImage = thumbnail$default;
            lValueOf = Long.valueOf(r3.intValue());
        }
        boolean isExplicit = track.getIsExplicit();
        String blockRef = track.getBlockRef();
        String str = track.getSearchAssetType() == SearchAssetType.VIDEO ? "VIDEO" : "AUDIO";
        List<com.amazon.musicensembleservice.brush.AssetQuality> listConvertAssetQualitiesFromSearchAssetQualities = convertAssetQualitiesFromSearchAssetQualities(track.getAssetQualities());
        List<ContentEncoding> contentEncoding = track.getContentEncoding();
        PlaymodeEligibility playmodeEligibility = track.getPlaymodeEligibility();
        List<RelatedPlaylist> relatedPlaylists = track.getRelatedPlaylists();
        List<String> languageOfPerformances = track.getLanguageOfPerformances();
        if (languageOfPerformances == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList();
            Iterator it = languageOfPerformances.iterator();
            while (it.hasNext()) {
                Iterator it2 = it;
                String languageOfPerformanceString = LanguageOfPerformanceUtils.INSTANCE.getLanguageOfPerformanceString(getContext(), (String) it.next());
                if (languageOfPerformanceString != null) {
                    arrayList2.add(languageOfPerformanceString);
                }
                it = it2;
            }
            arrayList = arrayList2;
        }
        return constructTrackEntity(title, asin, findImage, artist, artistAsin, album, albumAsin, Boolean.valueOf(isExplicit), list, blockRef, zIsOwned, isInLibrary, id, lValueOf, str, listConvertAssetQualitiesFromSearchAssetQualities, shouldSubtitleShowEntityType, contentEncoding, showOrdinal, playmodeEligibility, relatedPlaylists, index, arrayList);
    }

    protected final Entity getMusicEntitiesForSearchAlbum(AlbumSearchItem album, boolean shouldSubtitleShowEntityType, boolean showOrdinal) {
        List<ContentTier> listConvertFromContentAccessTypeSet;
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(album, "album");
        String title = album.getTitle();
        String asin = album.getAsin();
        Set<ContentAccessType> contentAccessSet = album.getContentAccessSet();
        if (title == null || asin == null) {
            return null;
        }
        FindImage thumbnail$default = getThumbnail$default(this, album, null, 2, null);
        String artistAsin = album.getArtistAsin();
        String artist = album.getArtist();
        boolean isInLibrary = album.getIsInLibrary();
        boolean zIsOwned = album.isOwned();
        String blockRef = album.getBlockRef();
        if (contentAccessSet == null) {
            listConvertFromContentAccessTypeSet = CollectionsKt__CollectionsKt.emptyList();
        } else {
            listConvertFromContentAccessTypeSet = ContentTierUtils.INSTANCE.convertFromContentAccessTypeSet(contentAccessSet);
        }
        List<ContentTier> list = listConvertFromContentAccessTypeSet;
        List<ContentEncoding> contentEncoding = album.getContentEncoding();
        PlaymodeEligibility playmodeEligibility = album.getPlaymodeEligibility();
        List<String> languageOfPerformances = album.getLanguageOfPerformances();
        if (languageOfPerformances == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList();
            Iterator<T> it = languageOfPerformances.iterator();
            while (it.hasNext()) {
                String languageOfPerformanceString = LanguageOfPerformanceUtils.INSTANCE.getLanguageOfPerformanceString(getContext(), (String) it.next());
                if (languageOfPerformanceString != null) {
                    arrayList2.add(languageOfPerformanceString);
                }
            }
            arrayList = arrayList2;
        }
        return constructAlbumEntity(title, asin, thumbnail$default, artist, artistAsin, Boolean.FALSE, list, blockRef, zIsOwned, isInLibrary, shouldSubtitleShowEntityType, contentEncoding, showOrdinal, playmodeEligibility, arrayList);
    }

    protected final Entity getMusicEntitiesForSearchPodcastEpisode(PodcastEpisodeSearchItem podcastEpisode, boolean shouldSubtitleShowEntityType, boolean showOrdinal) {
        List<ContentTier> listConvertFromContentAccessTypeSet;
        Intrinsics.checkNotNullParameter(podcastEpisode, "podcastEpisode");
        String title = podcastEpisode.getTitle();
        String subtitle = podcastEpisode.getSubtitle();
        String podcastShowId = podcastEpisode.getPodcastShowId();
        String podcastEpisodeId = podcastEpisode.getPodcastEpisodeId();
        Set<ContentAccessType> contentAccessSet = podcastEpisode.getContentAccessSet();
        if (title == null || podcastEpisodeId == null || podcastShowId == null || subtitle == null) {
            return null;
        }
        Image image = new Image();
        image.setUrl(podcastEpisode.getArtworkUri());
        image.setType("ORIGINAL_ART");
        String blockRef = podcastEpisode.getBlockRef();
        String subtitle2 = podcastEpisode.getSubtitle();
        String podcastEpisodeVariantId = podcastEpisode.getPodcastEpisodeVariantId();
        String podcastShowVariantId = podcastEpisode.getPodcastShowVariantId();
        if (contentAccessSet == null) {
            listConvertFromContentAccessTypeSet = CollectionsKt__CollectionsKt.emptyList();
        } else {
            listConvertFromContentAccessTypeSet = ContentTierUtils.INSTANCE.convertFromContentAccessTypeSet(contentAccessSet);
        }
        return constructPodcastEpisodeEntity(title, subtitle2, image, blockRef, shouldSubtitleShowEntityType, showOrdinal, podcastShowId, podcastEpisodeId, podcastEpisodeVariantId, podcastShowVariantId, listConvertFromContentAccessTypeSet, podcastEpisode.getPodcastEpisodeTierBenefits(), podcastEpisode.getPodcastShowTierBenefits(), podcastEpisode.getAvailabilityDate(), podcastEpisode.getPublishDate());
    }

    protected final Entity getMusicEntitiesForSearchPodcastShow(PodcastShowSearchItem podcastShow, boolean shouldSubtitleShowEntityType, boolean showOrdinal) {
        List<ContentTier> listConvertFromContentAccessTypeSet;
        Intrinsics.checkNotNullParameter(podcastShow, "podcastShow");
        String title = podcastShow.getTitle();
        String podcastShowId = podcastShow.getPodcastShowId();
        Set<ContentAccessType> contentAccessSet = podcastShow.getContentAccessSet();
        if (title == null || podcastShowId == null) {
            return null;
        }
        Image image = new Image();
        image.setUrl(podcastShow.getArtworkUri());
        image.setType("ORIGINAL_ART");
        String blockRef = podcastShow.getBlockRef();
        String subtitle = podcastShow.getSubtitle();
        String variantId = podcastShow.getVariantId();
        if (contentAccessSet == null) {
            listConvertFromContentAccessTypeSet = CollectionsKt__CollectionsKt.emptyList();
        } else {
            listConvertFromContentAccessTypeSet = ContentTierUtils.INSTANCE.convertFromContentAccessTypeSet(contentAccessSet);
        }
        return constructPodcastShowEntity(title, subtitle, image, blockRef, shouldSubtitleShowEntityType, showOrdinal, podcastShowId, variantId, listConvertFromContentAccessTypeSet, podcastShow.getTierBenefits());
    }

    protected final Entity getMusicEntitiesForSportsItem(SportsSearchItem sportsItem, boolean shouldSubtitleShowEntityType) {
        Intrinsics.checkNotNullParameter(sportsItem, "sportsItem");
        String title = sportsItem.getTitle();
        Uri deeplink = sportsItem.getDeeplink();
        if (title == null || deeplink == null) {
            return null;
        }
        Image image = new Image();
        image.setUrl(sportsItem.getArtworkUri());
        image.setType("ORIGINAL_ART");
        return constructSportEntity(title, image, deeplink.toString(), sportsItem.getBlockRef(), shouldSubtitleShowEntityType);
    }

    protected final Entity getMusicEntitiesForSearchLiveEvent(LiveEventSearchItem liveEventItem, boolean shouldSubtitleShowEntityType, boolean showOrdinal) {
        Intrinsics.checkNotNullParameter(liveEventItem, "liveEventItem");
        String eventId = liveEventItem.getEventId();
        String title = liveEventItem.getTitle();
        if (!(eventId.length() > 0)) {
            return null;
        }
        if (!(title.length() > 0)) {
            return null;
        }
        Image image = new Image();
        image.setUrl(liveEventItem.getThumbnailImage(getContext()));
        image.setType("ORIGINAL_ART");
        return constructLiveEventEntity(eventId, title, image, liveEventItem.getBlockRef(), showOrdinal, shouldSubtitleShowEntityType);
    }

    public static /* synthetic */ Entity getMusicEntityForSpellCorrections$default(BrushV2Converter brushV2Converter, SpellCorrections spellCorrections, String str, String str2, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getMusicEntityForSpellCorrections");
        }
        if ((i & 8) != 0) {
            z = false;
        }
        return brushV2Converter.getMusicEntityForSpellCorrections(spellCorrections, str, str2, z);
    }

    protected final Entity getMusicEntityForSpellCorrections(SpellCorrections spellCorrections, String executedTerm, String originalQuery, boolean shouldCenterMessage) {
        String str;
        String str2;
        String str3;
        Intrinsics.checkNotNullParameter(spellCorrections, "spellCorrections");
        String originalQuery2 = originalQuery;
        Intrinsics.checkNotNullParameter(originalQuery2, "originalQuery");
        boolean zHasAutoCorrection = spellCorrections.hasAutoCorrection();
        boolean zHasDidYouMeanCorrection = spellCorrections.hasDidYouMeanCorrection();
        if (executedTerm == null || !zHasAutoCorrection) {
            str = null;
        } else {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = this.context.getResources().getString(R.string.search_results_for);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…tring.search_results_for)");
            str = String.format(string, Arrays.copyOf(new Object[]{executedTerm}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        }
        if (zHasDidYouMeanCorrection) {
            originalQuery2 = spellCorrections.getDidYouMeanCorrectionText();
        }
        String str4 = originalQuery2;
        if (zHasDidYouMeanCorrection) {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String string2 = this.context.getResources().getString(R.string.did_you_mean);
            Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getString(R.string.did_you_mean)");
            str3 = String.format(string2, Arrays.copyOf(new Object[]{str4}, 1));
            Intrinsics.checkNotNullExpressionValue(str3, "format(format, *args)");
        } else if (zHasAutoCorrection) {
            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
            String string3 = this.context.getResources().getString(R.string.search_instead_for);
            Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getStr…tring.search_instead_for)");
            str3 = String.format(string3, Arrays.copyOf(new Object[]{str4}, 1));
            Intrinsics.checkNotNullExpressionValue(str3, "format(format, *args)");
        } else {
            str2 = null;
            String blockRef = spellCorrections.getBlockRef();
            FontStyleKey fontStyleKey = FontStyleKey.PRIMARY;
            FontStyleKey fontStyleKey2 = FontStyleKey.SECONDARY;
            SpacerKey spacerKey = SpacerKey.MICRO;
            Intrinsics.checkNotNullExpressionValue(blockRef, "blockRef");
            return new SearchResponseMessageBrushV2(blockRef, str4, false, spellCorrections, str, str2, fontStyleKey, fontStyleKey2, spacerKey, shouldCenterMessage);
        }
        str2 = str3;
        String blockRef2 = spellCorrections.getBlockRef();
        FontStyleKey fontStyleKey3 = FontStyleKey.PRIMARY;
        FontStyleKey fontStyleKey22 = FontStyleKey.SECONDARY;
        SpacerKey spacerKey2 = SpacerKey.MICRO;
        Intrinsics.checkNotNullExpressionValue(blockRef2, "blockRef");
        return new SearchResponseMessageBrushV2(blockRef2, str4, false, spellCorrections, str, str2, fontStyleKey3, fontStyleKey22, spacerKey2, shouldCenterMessage);
    }

    protected final Entity getMusicEntityForNoResults(String originalQuery) {
        String string = this.context.getResources().getString(R.string.no_results_found_for);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ing.no_results_found_for)");
        if (Intrinsics.areEqual(Locale.getDefault().getLanguage(), JAPAN_LOCALE) || originalQuery == null) {
            originalQuery = "";
        }
        return new SearchResponseMessageBrushV2("", null, true, null, string, originalQuery, FontStyleKey.HEADLINE_4, FontStyleKey.PRIMARY, SpacerKey.SMALL, true);
    }

    private final Entity constructMusicEntityForSearchEntity(SearchEntity searchEntity, String title) {
        ParentalControls parentControls;
        ParentalControls parentControls2;
        ParentalControls parentControls3;
        String podcastEntityVariantId;
        String podcastEntityVariantId2;
        ParentalControls parentControls4;
        Integer durationInMilliseconds;
        Image image = new Image();
        image.setUrl(searchEntity.getImageUrl());
        image.setType("ORIGINAL_ART");
        String entityId = searchEntity.getEntityId();
        EntityProperties entityProperties = searchEntity.getEntityProperties();
        String contentUrl = searchEntity.getContentUrl();
        List<ContentTier> contentTier = entityProperties == null ? null : entityProperties.getContentTier();
        if (contentTier == null) {
            contentTier = CollectionsKt__CollectionsKt.emptyList();
        }
        List<ContentTier> list = contentTier;
        switch (WhenMappings.$EnumSwitchMapping$1[searchEntity.getEntityType().ordinal()]) {
            case 1:
                return constructGenreEntity$default(this, title, entityId, image, list, null, false, 48, null);
            case 2:
                return constructAlbumEntity$default(this, title, entityId, image, entityProperties == null ? null : entityProperties.getArtistName(), entityProperties == null ? null : entityProperties.getArtistAsin(), (entityProperties == null || (parentControls = entityProperties.getParentControls()) == null) ? null : Boolean.valueOf(parentControls.getHasExplicitLanguage()), list, null, false, false, false, null, false, null, null, 32640, null);
            case 3:
                return constructArtistEntity$default(this, title, entityId, image, null, false, null, null, null, null, false, PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, null);
            case 4:
                return constructTrackEntity$default(this, title, entityId, image, entityProperties == null ? null : entityProperties.getArtistName(), entityProperties == null ? null : entityProperties.getArtistAsin(), entityProperties == null ? null : entityProperties.getAlbumName(), entityProperties == null ? null : entityProperties.getAlbumAsin(), (entityProperties == null || (parentControls2 = entityProperties.getParentControls()) == null) ? null : Boolean.valueOf(parentControls2.getHasExplicitLanguage()), list, null, entityProperties == null ? false : Intrinsics.areEqual(entityProperties.getPurchased(), Boolean.TRUE), false, null, null, null, null, false, null, false, new PlaymodeEligibility(entityProperties == null ? null : entityProperties.getIsOnDemand(), entityProperties == null ? null : entityProperties.getIsSfa(), null), null, null, null, 7862784, null);
            case 5:
                return constructStationEntity$default(this, title, entityId, image, entityProperties == null ? null : entityProperties.getParentalControlsPerTier(), list, null, false, false, 224, null);
            case 6:
            case 7:
                return constructPlaylistEntity$default(this, title, entityId, image, (entityProperties == null || (parentControls3 = entityProperties.getParentControls()) == null) ? null : Boolean.valueOf(parentControls3.getHasExplicitLanguage()), false, null, list, null, CollectionsKt__CollectionsJVMKt.listOf(searchEntity.getEntityType() == EntityType.VIDEO_PLAYLIST ? "VIDEO" : "AUDIO"), false, false, null, null, 7856, null);
            case 8:
                return constructCommunityPlaylistEntity$default(this, title, entityProperties == null ? null : entityProperties.getCurator(), null, image, 0, list, entityId, null, false, false, null, 1940, null);
            case 9:
                return constructPodcastShowEntity$default(this, title, entityProperties != null ? entityProperties.getSubTitle() : null, image, contentUrl, null, false, false, (entityProperties == null || (podcastEntityVariantId = entityProperties.getPodcastEntityVariantId()) == null) ? entityId : podcastEntityVariantId, 112, null);
            case 10:
                return constructPodcastEpisodeEntity$default(this, title, entityProperties != null ? entityProperties.getSubTitle() : null, image, contentUrl, null, false, false, null, (entityProperties == null || (podcastEntityVariantId2 = entityProperties.getPodcastEntityVariantId()) == null) ? entityId : podcastEntityVariantId2, 240, null);
            case 11:
                return constructTrackEntity$default(this, title, entityId, image, entityProperties == null ? null : entityProperties.getArtistName(), entityProperties == null ? null : entityProperties.getArtistAsin(), entityProperties == null ? null : entityProperties.getAlbumName(), entityProperties == null ? null : entityProperties.getAlbumAsin(), (entityProperties == null || (parentControls4 = entityProperties.getParentControls()) == null) ? null : Boolean.valueOf(parentControls4.getHasExplicitLanguage()), list, null, false, false, null, (entityProperties == null || (durationInMilliseconds = entityProperties.getDurationInMilliseconds()) == null) ? null : Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(durationInMilliseconds.intValue())), "VIDEO", convertAssetQualitiesFromFindAssetQualities(entityProperties != null ? entityProperties.getAssetQualities() : null), false, null, false, null, null, null, null, 8330752, null);
            default:
                return null;
        }
    }

    private final Entity constructSearchQueryEntity(String searchTerm, int index) {
        MusicSearchQuery musicSearchQuery = new MusicSearchQuery(searchTerm, (int) this.context.getResources().getDimension(R.dimen.artwork_as_icon_compact_size), null, Integer.valueOf(R.drawable.ic_navigation_search_filled));
        musicSearchQuery.setBlockRef(String.valueOf(index));
        return musicSearchQuery;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Artist constructArtistEntity$default(BrushV2Converter brushV2Converter, String str, String str2, Image image, String str3, boolean z, List list, String str4, Integer num, Integer num2, boolean z2, int i, Object obj) {
        if (obj == null) {
            return brushV2Converter.constructArtistEntity(str, str2, (i & 4) != 0 ? null : image, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? true : z, (i & 32) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? null : num, (i & 256) != 0 ? null : num2, (i & 512) != 0 ? false : z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructArtistEntity");
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Track constructTrackEntity$default(BrushV2Converter brushV2Converter, String str, String str2, Image image, String str3, String str4, String str5, String str6, Boolean bool, List list, String str7, boolean z, boolean z2, Long l, Long l2, String str8, List list2, boolean z3, List list3, boolean z4, PlaymodeEligibility playmodeEligibility, List list4, Integer num, List list5, int i, Object obj) {
        if (obj == null) {
            return brushV2Converter.constructTrackEntity(str, str2, image, str3, str4, str5, str6, bool, list, (i & 512) != 0 ? null : str7, (i & 1024) != 0 ? false : z, (i & 2048) != 0 ? false : z2, (i & 4096) != 0 ? null : l, (i & 8192) != 0 ? null : l2, (i & 16384) != 0 ? "AUDIO" : str8, (32768 & i) != 0 ? null : list2, (65536 & i) != 0 ? true : z3, (131072 & i) != 0 ? null : list3, (262144 & i) != 0 ? false : z4, (524288 & i) != 0 ? null : playmodeEligibility, (1048576 & i) != 0 ? null : list4, (2097152 & i) != 0 ? null : num, (i & 4194304) != 0 ? null : list5);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructTrackEntity");
    }

    private final Track constructTrackEntity(String title, String asin, Image thumbnailImage, String artistName, String artistAsin, String albumName, String albumAsin, Boolean hasExplicitLanguage, List<? extends ContentTier> contentTiers, String blockRef, boolean isOwned, boolean isInLibrary, Long trackId, Long duration, String assetType, List<? extends com.amazon.musicensembleservice.brush.AssetQuality> assetQualities, boolean shouldSubtitleShowEntityType, List<? extends ContentEncoding> contentEncoding, boolean showOrdinal, PlaymodeEligibility playmodeEligibility, List<RelatedPlaylist> relatedPlaylists, Integer position, List<String> languageOfPerformances) {
        MusicSearchTrack musicSearchTrack;
        ArrayList arrayList;
        BrushV2Converter<E> brushV2Converter;
        boolean z = false;
        if (playmodeEligibility != null && playmodeEligibility.isOnDemand()) {
            z = true;
        }
        List<RelatedPlaylist> list = z ? null : relatedPlaylists;
        MusicSearchTrack musicSearchTrack2 = new MusicSearchTrack(trackId, false, showOrdinal, list, languageOfPerformances, 2, null);
        musicSearchTrack2.setPrimaryTitle(title);
        musicSearchTrack2.setTitle(title);
        musicSearchTrack2.setSecondaryTitle(constructSecondaryTitle$default(this, Intrinsics.areEqual(assetType, "AUDIO") ? EntityType.TRACK : EntityType.VIDEO, artistName, shouldSubtitleShowEntityType, null, null, null, list, position, 56, null));
        musicSearchTrack2.setAsin(asin);
        musicSearchTrack2.setImage(thumbnailImage);
        musicSearchTrack2.setIsOwned(Boolean.valueOf(isOwned));
        musicSearchTrack2.setIsInLibrary(Boolean.valueOf(isInLibrary));
        musicSearchTrack2.setAssetType(assetType);
        musicSearchTrack2.setAssetQualities(assetQualities);
        musicSearchTrack2.setDuration(duration);
        if (artistName == null || artistAsin == null) {
            musicSearchTrack = musicSearchTrack2;
        } else {
            musicSearchTrack2.setArtists(CollectionsKt__CollectionsJVMKt.listOf(constructArtistEntity$default(this, artistName, artistAsin, null, null, false, null, null, null, null, false, PointerIconCompat.TYPE_GRAB, null)));
            if (albumName == null || albumAsin == null) {
                musicSearchTrack = musicSearchTrack2;
            } else {
                musicSearchTrack = musicSearchTrack2;
                musicSearchTrack.setAlbum(constructAlbumEntity$default(this, albumName, albumAsin, null, artistName, artistAsin, hasExplicitLanguage, null, null, false, false, false, null, false, null, null, 32708, null));
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(contentTiers, 10));
        Iterator<T> it = contentTiers.iterator();
        while (it.hasNext()) {
            arrayList2.add(((ContentTier) it.next()).name());
        }
        musicSearchTrack.setContentTiers(arrayList2);
        if (contentEncoding == null) {
            brushV2Converter = this;
            arrayList = null;
        } else {
            arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(contentEncoding, 10));
            Iterator<T> it2 = contentEncoding.iterator();
            while (it2.hasNext()) {
                arrayList.add(convertContentEncoding((ContentEncoding) it2.next()));
            }
            brushV2Converter = this;
        }
        musicSearchTrack.setContentEncoding(arrayList);
        musicSearchTrack.setBlockRef(brushV2Converter.constructBlockRef(blockRef));
        musicSearchTrack.setParentalControls(brushV2Converter.constructParentalControl(hasExplicitLanguage));
        musicSearchTrack.setPlaymodeEligibility(brushV2Converter.convertPlaymodeEligibility(playmodeEligibility));
        return musicSearchTrack;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Album constructAlbumEntity$default(BrushV2Converter brushV2Converter, String str, String str2, Image image, String str3, String str4, Boolean bool, List list, String str5, boolean z, boolean z2, boolean z3, List list2, boolean z4, PlaymodeEligibility playmodeEligibility, List list3, int i, Object obj) {
        if (obj == null) {
            return brushV2Converter.constructAlbumEntity(str, str2, (i & 4) != 0 ? null : image, str3, str4, bool, (i & 64) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? false : z, (i & 512) != 0 ? false : z2, (i & 1024) != 0 ? true : z3, (i & 2048) != 0 ? null : list2, (i & 4096) != 0 ? false : z4, (i & 8192) != 0 ? null : playmodeEligibility, (i & 16384) != 0 ? null : list3);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructAlbumEntity");
    }

    private final Album constructAlbumEntity(String title, String id, Image thumbnailImage, String artistName, String artistAsin, Boolean hasExplicitLanguage, List<? extends ContentTier> contentTiers, String blockRef, boolean isOwned, boolean isInLibrary, boolean shouldSubtitleShowEntityType, List<? extends ContentEncoding> contentEncoding, boolean showOrdinal, PlaymodeEligibility playmodeEligibility, List<String> languageOfPerformances) {
        ArrayList arrayList;
        MusicSearchAlbum musicSearchAlbum = new MusicSearchAlbum(false, showOrdinal, languageOfPerformances, 1, null);
        musicSearchAlbum.setPrimaryTitle(title);
        musicSearchAlbum.setTitle(title);
        musicSearchAlbum.setSecondaryTitle(constructSecondaryTitle$default(this, EntityType.ALBUM, artistName, shouldSubtitleShowEntityType, null, null, null, null, null, 248, null));
        musicSearchAlbum.setAsin(id);
        musicSearchAlbum.setImage(thumbnailImage);
        musicSearchAlbum.setIsOwned(Boolean.valueOf(isOwned));
        musicSearchAlbum.setIsInLibrary(Boolean.valueOf(isInLibrary));
        if (artistName != null && artistAsin != null) {
            musicSearchAlbum.setArtists(CollectionsKt__CollectionsJVMKt.listOf(constructArtistEntity$default(this, artistName, artistAsin, null, null, false, null, null, null, null, false, PointerIconCompat.TYPE_GRAB, null)));
        }
        musicSearchAlbum.setBlockRef(constructBlockRef(blockRef));
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(contentTiers, 10));
        Iterator<T> it = contentTiers.iterator();
        while (it.hasNext()) {
            arrayList2.add(((ContentTier) it.next()).name());
        }
        musicSearchAlbum.setContentTiers(arrayList2);
        if (contentEncoding == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(contentEncoding, 10));
            Iterator<T> it2 = contentEncoding.iterator();
            while (it2.hasNext()) {
                arrayList.add(convertContentEncoding((ContentEncoding) it2.next()));
            }
        }
        musicSearchAlbum.setContentEncoding(arrayList);
        musicSearchAlbum.setParentalControls(constructParentalControl(hasExplicitLanguage));
        musicSearchAlbum.setPlaymodeEligibility(convertPlaymodeEligibility(playmodeEligibility));
        return musicSearchAlbum;
    }

    private final com.amazon.musicensembleservice.brush.ParentalControls constructParentalControl(Boolean hasExplicitLanguage) {
        com.amazon.musicensembleservice.brush.ParentalControls parentalControls = new com.amazon.musicensembleservice.brush.ParentalControls();
        if (hasExplicitLanguage == null) {
            hasExplicitLanguage = Boolean.TRUE;
        }
        parentalControls.setHasExplicitLanguage(hasExplicitLanguage);
        return parentalControls;
    }

    static /* synthetic */ Station constructStationEntity$default(BrushV2Converter brushV2Converter, String str, String str2, Image image, ParentalControlsPerContentTier parentalControlsPerContentTier, List list, String str3, boolean z, boolean z2, int i, Object obj) {
        if (obj == null) {
            return brushV2Converter.constructStationEntity(str, str2, image, parentalControlsPerContentTier, list, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? true : z, (i & 128) != 0 ? false : z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructStationEntity");
    }

    private final Station constructStationEntity(String title, String id, Image thumbnailImage, ParentalControlsPerContentTier parentalControlsPerContentTier, List<? extends ContentTier> contentTiers, String blockRef, boolean shouldSubtitleShowEntityType, boolean showOrdinal) {
        List<ParentalControlItem> parentalControlItems;
        Map<String, com.amazon.musicensembleservice.brush.ParentalControls> map = null;
        MusicSearchStation musicSearchStation = new MusicSearchStation(false, showOrdinal, 1, null);
        musicSearchStation.setPrimaryTitle(title);
        musicSearchStation.setTitle(title);
        musicSearchStation.setStationKey(id);
        musicSearchStation.setSecondaryTitle(constructSecondaryTitle$default(this, EntityType.STATION, null, shouldSubtitleShowEntityType, null, null, null, null, null, 248, null));
        musicSearchStation.setImage(thumbnailImage);
        musicSearchStation.setBlockRef(constructBlockRef(blockRef));
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(contentTiers, 10));
        Iterator<T> it = contentTiers.iterator();
        while (it.hasNext()) {
            arrayList.add(((ContentTier) it.next()).name());
        }
        musicSearchStation.setContentTiers(arrayList);
        if (parentalControlsPerContentTier != null && (parentalControlItems = parentalControlsPerContentTier.getParentalControlItems()) != null) {
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(parentalControlItems, 10));
            for (ParentalControlItem parentalControlItem : parentalControlItems) {
                arrayList2.add(TuplesKt.to(parentalControlItem.getContentTier().name(), constructParentalControl(Boolean.valueOf(parentalControlItem.getHasExplicitLanguage()))));
            }
            map = MapsKt__MapsKt.toMap(arrayList2);
        }
        musicSearchStation.setParentalControlsPerContentTier(map);
        return musicSearchStation;
    }

    static /* synthetic */ Genre constructGenreEntity$default(BrushV2Converter brushV2Converter, String str, String str2, Image image, List list, String str3, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructGenreEntity");
        }
        if ((i & 16) != 0) {
            str3 = null;
        }
        String str4 = str3;
        if ((i & 32) != 0) {
            z = true;
        }
        return brushV2Converter.constructGenreEntity(str, str2, image, list, str4, z);
    }

    private final Genre constructGenreEntity(String title, String id, Image thumbnailImage, List<? extends ContentTier> contentTiers, String blockRef, boolean shouldSubtitleShowEntityType) {
        Genre genre = new Genre();
        genre.setPrimaryTitle(title);
        genre.setSecondaryTitle(constructSecondaryTitle$default(this, EntityType.GENRE, null, shouldSubtitleShowEntityType, null, null, null, null, null, 248, null));
        genre.setImage(thumbnailImage);
        genre.setBrowseId(id);
        genre.setBlockRef(constructBlockRef(blockRef));
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(contentTiers, 10));
        Iterator<T> it = contentTiers.iterator();
        while (it.hasNext()) {
            arrayList.add(((ContentTier) it.next()).name());
        }
        genre.setContentTiers(arrayList);
        return genre;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Playlist constructPlaylistEntity$default(BrushV2Converter brushV2Converter, String str, String str2, Image image, Boolean bool, boolean z, String str3, List list, String str4, List list2, boolean z2, boolean z3, PlaymodeEligibility playmodeEligibility, List list3, int i, Object obj) {
        if (obj == null) {
            return brushV2Converter.constructPlaylistEntity(str, str2, image, bool, (i & 16) != 0 ? false : z, (i & 32) != 0 ? null : str3, list, (i & 128) != 0 ? null : str4, list2, (i & 512) != 0 ? true : z2, (i & 1024) != 0 ? false : z3, (i & 2048) != 0 ? null : playmodeEligibility, (i & 4096) != 0 ? null : list3);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructPlaylistEntity");
    }

    private final Playlist constructPlaylistEntity(String title, String asin, Image thumbnailImage, Boolean hasExplicitLanguage, boolean isInLibrary, String description, List<? extends ContentTier> contentTiers, String blockRef, List<String> contentTypes, boolean shouldSubtitleShowEntityType, boolean showOrdinal, PlaymodeEligibility playmodeEligibility, List<String> languageOfPerformances) {
        MusicSearchPlaylist musicSearchPlaylist = new MusicSearchPlaylist(false, showOrdinal, languageOfPerformances, 1, null);
        musicSearchPlaylist.setPrimaryTitle(title);
        musicSearchPlaylist.setTitle(title);
        musicSearchPlaylist.setDescription(description);
        musicSearchPlaylist.setIsInLibrary(Boolean.valueOf(isInLibrary));
        musicSearchPlaylist.setAsin(asin);
        musicSearchPlaylist.setContentTypes(contentTypes);
        musicSearchPlaylist.setSecondaryTitle(constructSecondaryTitle$default(this, Intrinsics.areEqual(CollectionsKt___CollectionsKt.first((List) contentTypes), "AUDIO") ? EntityType.PLAYLIST : EntityType.VIDEO_PLAYLIST, null, shouldSubtitleShowEntityType, null, null, null, null, null, 248, null));
        musicSearchPlaylist.setImage(thumbnailImage);
        musicSearchPlaylist.setBlockRef(constructBlockRef(blockRef));
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(contentTiers, 10));
        Iterator<T> it = contentTiers.iterator();
        while (it.hasNext()) {
            arrayList.add(((ContentTier) it.next()).name());
        }
        musicSearchPlaylist.setContentTiers(arrayList);
        musicSearchPlaylist.setParentalControls(constructParentalControl(hasExplicitLanguage));
        musicSearchPlaylist.setPlaymodeEligibility(convertPlaymodeEligibility(playmodeEligibility));
        return musicSearchPlaylist;
    }

    static /* synthetic */ CommunityPlaylist constructCommunityPlaylistEntity$default(BrushV2Converter brushV2Converter, String str, String str2, String str3, Image image, int i, List list, String str4, String str5, boolean z, boolean z2, PlaymodeEligibility playmodeEligibility, int i2, Object obj) {
        if (obj == null) {
            return brushV2Converter.constructCommunityPlaylistEntity(str, str2, (i2 & 4) != 0 ? null : str3, image, (i2 & 16) != 0 ? 0 : i, list, (i2 & 64) != 0 ? null : str4, (i2 & 128) != 0 ? null : str5, (i2 & 256) != 0 ? true : z, (i2 & 512) != 0 ? false : z2, (i2 & 1024) != 0 ? null : playmodeEligibility);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructCommunityPlaylistEntity");
    }

    private final CommunityPlaylist constructCommunityPlaylistEntity(String title, String curator, String description, Image thumbnailImage, int tracksCount, List<? extends ContentTier> contentTiers, String playlistId, String blockRef, boolean shouldSubtitleShowEntityType, boolean showOrdinal, PlaymodeEligibility playmodeEligibility) {
        MusicSearchCommunityPlaylist musicSearchCommunityPlaylist = new MusicSearchCommunityPlaylist(false, showOrdinal, null, 5, null);
        musicSearchCommunityPlaylist.setPrimaryTitle(title);
        musicSearchCommunityPlaylist.setTitle(title);
        musicSearchCommunityPlaylist.setDescription(description);
        musicSearchCommunityPlaylist.setPlaylistId(playlistId);
        musicSearchCommunityPlaylist.setCurator(curator);
        musicSearchCommunityPlaylist.setSecondaryTitle(constructSecondaryTitle$default(this, EntityType.COMMUNITY_PLAYLIST, curator, shouldSubtitleShowEntityType, null, null, null, null, null, 248, null));
        musicSearchCommunityPlaylist.setImages(CollectionsKt__CollectionsJVMKt.listOf(thumbnailImage));
        musicSearchCommunityPlaylist.setBlockRef(constructBlockRef(blockRef));
        musicSearchCommunityPlaylist.setTracksCount(Long.valueOf(tracksCount));
        musicSearchCommunityPlaylist.setPlaymodeEligibility(convertPlaymodeEligibility(playmodeEligibility));
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(contentTiers, 10));
        Iterator<T> it = contentTiers.iterator();
        while (it.hasNext()) {
            arrayList.add(((ContentTier) it.next()).name());
        }
        musicSearchCommunityPlaylist.setContentTiers(arrayList);
        musicSearchCommunityPlaylist.setIsOwned(Boolean.FALSE);
        return musicSearchCommunityPlaylist;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ UserPlaylist constructUserPlaylistEntity$default(BrushV2Converter brushV2Converter, String str, String str2, Image image, boolean z, int i, String str3, String str4, List list, boolean z2, boolean z3, List list2, int i2, Object obj) {
        if (obj == null) {
            return brushV2Converter.constructUserPlaylistEntity(str, str2, image, z, i, (i2 & 32) != 0 ? null : str3, (i2 & 64) != 0 ? null : str4, (i2 & 128) != 0 ? CollectionsKt__CollectionsJVMKt.listOf("AUDIO") : list, (i2 & 256) != 0 ? true : z2, z3, list2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructUserPlaylistEntity");
    }

    private final UserPlaylist constructUserPlaylistEntity(String title, String description, Image thumbnailImage, boolean isOwned, int tracksCount, String playlistId, String blockRef, List<String> contentTypes, boolean shouldSubtitleShowEntityType, boolean showOrdinal, List<? extends ContentTier> contentTiers) {
        MusicSearchUserPlaylist musicSearchUserPlaylist = new MusicSearchUserPlaylist(false, showOrdinal, null, 5, null);
        musicSearchUserPlaylist.setPrimaryTitle(title);
        musicSearchUserPlaylist.setTitle(title);
        musicSearchUserPlaylist.setDescription(description);
        musicSearchUserPlaylist.setPlaylistId(playlistId);
        musicSearchUserPlaylist.setContentTypes(contentTypes);
        musicSearchUserPlaylist.setSecondaryTitle(constructSecondaryTitle$default(this, Intrinsics.areEqual(CollectionsKt___CollectionsKt.first((List) contentTypes), "AUDIO") ? EntityType.PLAYLIST : EntityType.VIDEO_PLAYLIST, null, shouldSubtitleShowEntityType, null, null, null, null, null, 248, null));
        musicSearchUserPlaylist.setImages(CollectionsKt__CollectionsJVMKt.listOf(thumbnailImage));
        musicSearchUserPlaylist.setBlockRef(constructBlockRef(blockRef));
        musicSearchUserPlaylist.setTracksCount(Long.valueOf(tracksCount));
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(contentTiers, 10));
        Iterator<T> it = contentTiers.iterator();
        while (it.hasNext()) {
            arrayList.add(((ContentTier) it.next()).name());
        }
        musicSearchUserPlaylist.setContentTiers(arrayList);
        musicSearchUserPlaylist.setIsOwned(Boolean.valueOf(isOwned));
        return musicSearchUserPlaylist;
    }

    public static /* synthetic */ Entity constructPodcastShowEntity$default(BrushV2Converter brushV2Converter, String str, String str2, Image image, String str3, String str4, boolean z, boolean z2, String str5, int i, Object obj) {
        if (obj == null) {
            return brushV2Converter.constructPodcastShowEntity(str, str2, image, str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? true : z, (i & 64) != 0 ? false : z2, (i & 128) != 0 ? null : str5);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructPodcastShowEntity");
    }

    protected Entity constructPodcastShowEntity(String title, String podcastProviderName, Image thumbnailImage, String contentUrl, String blockRef, boolean shouldSubtitleShowEntityType, boolean showOrdinal, String entityId) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(thumbnailImage, "thumbnailImage");
        MusicSearchFeatureBarker musicSearchFeatureBarker = new MusicSearchFeatureBarker(false, showOrdinal, 1, null);
        musicSearchFeatureBarker.setTitle(title);
        musicSearchFeatureBarker.setSubtitle(constructSecondaryTitle$default(this, EntityType.PODCAST_SHOW, podcastProviderName, shouldSubtitleShowEntityType, null, null, null, null, null, 248, null));
        musicSearchFeatureBarker.setTarget(contentUrl);
        musicSearchFeatureBarker.setImages(CollectionsKt__CollectionsJVMKt.listOf(thumbnailImage));
        musicSearchFeatureBarker.setBlockRef(constructBlockRef(blockRef));
        musicSearchFeatureBarker.setId(entityId);
        return musicSearchFeatureBarker;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Entity constructPodcastShowEntity$default(BrushV2Converter brushV2Converter, String str, String str2, Image image, String str3, boolean z, boolean z2, String str4, String str5, List list, Map map, int i, Object obj) {
        if (obj == null) {
            return brushV2Converter.constructPodcastShowEntity(str, str2, image, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? true : z, (i & 32) != 0 ? false : z2, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list, (i & 512) != 0 ? MapsKt__MapsKt.emptyMap() : map);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructPodcastShowEntity");
    }

    public static /* synthetic */ Entity constructPodcastEpisodeEntity$default(BrushV2Converter brushV2Converter, String str, String str2, Image image, String str3, String str4, boolean z, boolean z2, String str5, String str6, int i, Object obj) {
        if (obj == null) {
            return brushV2Converter.constructPodcastEpisodeEntity(str, str2, image, str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? true : z, (i & 64) != 0 ? false : z2, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? null : str6);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructPodcastEpisodeEntity");
    }

    protected Entity constructPodcastEpisodeEntity(String title, String podcastShowName, Image thumbnailImage, String contentUrl, String blockRef, boolean shouldSubtitleShowEntityType, boolean showOrdinal, String podcastShowId, String entityId) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(thumbnailImage, "thumbnailImage");
        MusicSearchFeatureBarker musicSearchFeatureBarker = new MusicSearchFeatureBarker(false, showOrdinal, 1, null);
        musicSearchFeatureBarker.setTitle(title);
        musicSearchFeatureBarker.setSubtitle(constructSecondaryTitle$default(this, EntityType.PODCAST_EPISODE, podcastShowName, shouldSubtitleShowEntityType, null, null, null, null, null, 248, null));
        musicSearchFeatureBarker.setTarget(contentUrl);
        musicSearchFeatureBarker.setImages(CollectionsKt__CollectionsJVMKt.listOf(thumbnailImage));
        musicSearchFeatureBarker.setBlockRef(constructBlockRef(blockRef));
        musicSearchFeatureBarker.setId(entityId);
        return musicSearchFeatureBarker;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Entity constructPodcastEpisodeEntity$default(BrushV2Converter brushV2Converter, String str, String str2, Image image, String str3, boolean z, boolean z2, String str4, String str5, String str6, String str7, List list, Map map, Map map2, Map map3, Date date, int i, Object obj) {
        if (obj == null) {
            return brushV2Converter.constructPodcastEpisodeEntity(str, str2, image, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? true : z, (i & 32) != 0 ? false : z2, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? null : str6, (i & 512) != 0 ? null : str7, (i & 1024) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list, (i & 2048) != 0 ? MapsKt__MapsKt.emptyMap() : map, (i & 4096) != 0 ? MapsKt__MapsKt.emptyMap() : map2, (i & 8192) != 0 ? MapsKt__MapsKt.emptyMap() : map3, (i & 16384) != 0 ? null : date);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructPodcastEpisodeEntity");
    }

    static /* synthetic */ FeaturedBarker constructSportEntity$default(BrushV2Converter brushV2Converter, String str, Image image, String str2, String str3, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructSportEntity");
        }
        if ((i & 8) != 0) {
            str3 = null;
        }
        String str4 = str3;
        if ((i & 16) != 0) {
            z = true;
        }
        return brushV2Converter.constructSportEntity(str, image, str2, str4, z);
    }

    private final FeaturedBarker constructSportEntity(String title, Image thumbnailImage, String contentUrl, String blockRef, boolean shouldSubtitleShowEntityType) {
        FeaturedBarker featuredBarker = new FeaturedBarker();
        featuredBarker.setTitle(title);
        featuredBarker.setSubtitle(constructSecondaryTitle$default(this, EntityType.SPORT, null, shouldSubtitleShowEntityType, null, null, null, null, null, 248, null));
        featuredBarker.setTarget(contentUrl);
        featuredBarker.setImages(CollectionsKt__CollectionsJVMKt.listOf(thumbnailImage));
        featuredBarker.setBlockRef(constructBlockRef(blockRef));
        return featuredBarker;
    }

    static /* synthetic */ Entity constructLiveEventEntity$default(BrushV2Converter brushV2Converter, String str, String str2, Image image, String str3, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructLiveEventEntity");
        }
        if ((i & 8) != 0) {
            str3 = null;
        }
        String str4 = str3;
        if ((i & 16) != 0) {
            z = false;
        }
        boolean z3 = z;
        if ((i & 32) != 0) {
            z2 = true;
        }
        return brushV2Converter.constructLiveEventEntity(str, str2, image, str4, z3, z2);
    }

    private final Entity constructLiveEventEntity(String eventId, String title, Image thumbnailImage, String blockRef, boolean showOrdinal, boolean shouldSubtitleShowEntityType) {
        MusicSearchLiveEvent musicSearchLiveEvent = new MusicSearchLiveEvent(constructSecondaryTitle$default(this, EntityType.LIVE_EVENT, null, shouldSubtitleShowEntityType, null, null, null, null, null, 248, null), false, showOrdinal, 2, null);
        musicSearchLiveEvent.setId(eventId);
        musicSearchLiveEvent.setTitle(title);
        musicSearchLiveEvent.setImage(thumbnailImage);
        musicSearchLiveEvent.setBlockRef(blockRef);
        return musicSearchLiveEvent;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ String constructSecondaryTitle$default(BrushV2Converter brushV2Converter, EntityType entityType, String str, boolean z, String str2, Integer num, Integer num2, List list, Integer num3, int i, Object obj) {
        if (obj == null) {
            return brushV2Converter.constructSecondaryTitle(entityType, (i & 2) != 0 ? null : str, (i & 4) != 0 ? true : z, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : num, (i & 32) != 0 ? null : num2, (i & 64) != 0 ? null : list, (i & 128) == 0 ? num3 : null);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructSecondaryTitle");
    }

    protected String constructSecondaryTitle(EntityType entityType, String subtitle, boolean shouldSubtitleShowEntityType, String source, Integer albumCount, Integer trackCount, List<RelatedPlaylist> relatedPlaylists, Integer position) {
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        switch (WhenMappings.$EnumSwitchMapping$1[entityType.ordinal()]) {
            case 1:
                if (shouldSubtitleShowEntityType) {
                    return this.context.getResources().getString(R.string.genre);
                }
                return null;
            case 2:
                if (!shouldSubtitleShowEntityType) {
                    return subtitle;
                }
                String string = this.context.getResources().getString(R.string.album);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.album)");
                return constructCompactSecondaryTitle(string, subtitle);
            case 3:
                if (this.searchFeaturesProvider.isLibrarySource(source)) {
                    return constructLibraryArtistSecondaryTitle(albumCount, trackCount);
                }
                if (shouldSubtitleShowEntityType) {
                    return this.context.getResources().getString(R.string.artist);
                }
                return null;
            case 4:
                return constructTrackSecondaryTitle(subtitle, shouldSubtitleShowEntityType, relatedPlaylists, position);
            case 5:
                if (shouldSubtitleShowEntityType) {
                    return this.context.getResources().getString(R.string.station);
                }
                return null;
            case 6:
                if (shouldSubtitleShowEntityType) {
                    return this.context.getResources().getString(R.string.playlist);
                }
                return null;
            case 7:
                if (shouldSubtitleShowEntityType) {
                    return this.context.getResources().getString(R.string.video_playlist);
                }
                return null;
            case 8:
                if (!shouldSubtitleShowEntityType) {
                    return subtitle;
                }
                if (subtitle == null || subtitle.length() == 0) {
                    String string2 = this.context.getResources().getString(R.string.playlist_from_community);
                    Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…                        )");
                    return constructCompactSecondaryTitle(string2, null);
                }
                String string3 = this.context.getResources().getString(R.string.playlist);
                Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getString(R.string.playlist)");
                return constructCompactSecondaryTitle(string3, subtitle);
            case 9:
                if (!shouldSubtitleShowEntityType) {
                    return subtitle;
                }
                String string4 = this.context.getResources().getString(R.string.podcast_show);
                Intrinsics.checkNotNullExpressionValue(string4, "context.resources.getString(R.string.podcast_show)");
                return constructCompactSecondaryTitle(string4, subtitle);
            case 10:
                if (!shouldSubtitleShowEntityType) {
                    return subtitle;
                }
                String string5 = this.context.getResources().getString(R.string.podcast_episode);
                Intrinsics.checkNotNullExpressionValue(string5, "context.resources.getStr…R.string.podcast_episode)");
                return constructCompactSecondaryTitle(string5, subtitle);
            case 11:
                if (!shouldSubtitleShowEntityType) {
                    return subtitle;
                }
                String string6 = this.context.getResources().getString(R.string.video);
                Intrinsics.checkNotNullExpressionValue(string6, "context.resources.getString(R.string.video)");
                return constructCompactSecondaryTitle(string6, subtitle);
            case 12:
                if (shouldSubtitleShowEntityType) {
                    return this.context.getResources().getString(R.string.dmusic_amp_browse_poldi_header);
                }
                return null;
            case 13:
                return shouldSubtitleShowEntityType ? this.context.getResources().getString(R.string.live_event) : subtitle;
            default:
                return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ String constructTrackSecondaryTitle$default(BrushV2Converter brushV2Converter, String str, boolean z, List list, Integer num, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: constructTrackSecondaryTitle");
        }
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            list = null;
        }
        if ((i & 8) != 0) {
            num = null;
        }
        return brushV2Converter.constructTrackSecondaryTitle(str, z, list, num);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x005a, code lost:
    
        if (r11.intValue() == 0) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0086, code lost:
    
        if (r1 == com.amazon.music.search.EntityRelationRole.FEATURED_PLAYLIST) goto L36;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.String constructTrackSecondaryTitle(java.lang.String r8, boolean r9, java.util.List<com.amazon.music.search.RelatedPlaylist> r10, java.lang.Integer r11) {
        /*
            r7 = this;
            android.content.Context r0 = r7.context
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.amazon.music.find.R.string.track
            java.lang.String r0 = r0.getString(r1)
            java.lang.String r1 = "context.resources.getString(R.string.track)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            com.amazon.music.find.api.util.RelatedEntityUtil r1 = com.amazon.music.find.api.util.RelatedEntityUtil.INSTANCE
            com.amazon.music.search.RelatedPlaylist r10 = r1.getRelatedPlaylist(r10)
            r1 = 0
            if (r10 != 0) goto L1c
            r2 = r1
            goto L3f
        L1c:
            kotlin.jvm.internal.StringCompanionObject r2 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            com.amazon.music.find.providers.SearchFeaturesProvider r2 = r7.getSearchFeaturesProvider()
            int r3 = com.amazon.music.find.R.string.featured_in
            java.lang.String r2 = r2.getLocalizedString(r3)
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r5 = 0
            java.lang.String r6 = r10.getTitle()
            r4[r5] = r6
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r4, r3)
            java.lang.String r2 = java.lang.String.format(r2, r3)
            java.lang.String r3 = "format(format, *args)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
        L3f:
            if (r9 == 0) goto L61
            if (r10 == 0) goto L5c
            com.amazon.music.search.EntityRelation r3 = r10.getEntityRelation()
            if (r3 != 0) goto L4b
            r3 = r1
            goto L4f
        L4b:
            com.amazon.music.search.EntityRelationRole r3 = r3.getRole()
        L4f:
            com.amazon.music.search.EntityRelationRole r4 = com.amazon.music.search.EntityRelationRole.FEATURED_PLAYLIST
            if (r3 != r4) goto L61
            if (r11 != 0) goto L56
            goto L61
        L56:
            int r11 = r11.intValue()
            if (r11 != 0) goto L61
        L5c:
            java.lang.String r8 = r7.constructCompactSecondaryTitle(r0, r8)
            goto L99
        L61:
            if (r9 == 0) goto L8d
            if (r10 != 0) goto L67
        L65:
            r11 = r1
            goto L72
        L67:
            com.amazon.music.search.EntityRelation r11 = r10.getEntityRelation()
            if (r11 != 0) goto L6e
            goto L65
        L6e:
            com.amazon.music.search.EntityRelationRole r11 = r11.getRole()
        L72:
            com.amazon.music.search.EntityRelationRole r3 = com.amazon.music.search.EntityRelationRole.RELATED_PLAYLIST
            if (r11 == r3) goto L88
            if (r10 != 0) goto L79
            goto L84
        L79:
            com.amazon.music.search.EntityRelation r11 = r10.getEntityRelation()
            if (r11 != 0) goto L80
            goto L84
        L80:
            com.amazon.music.search.EntityRelationRole r1 = r11.getRole()
        L84:
            com.amazon.music.search.EntityRelationRole r11 = com.amazon.music.search.EntityRelationRole.FEATURED_PLAYLIST
            if (r1 != r11) goto L8d
        L88:
            java.lang.String r8 = r7.constructCompactSecondaryTitle(r0, r2)
            goto L99
        L8d:
            if (r9 != 0) goto L99
            if (r10 == 0) goto L99
            if (r8 == 0) goto L98
            java.lang.String r8 = r7.constructCompactSecondaryTitle(r8, r2)
            goto L99
        L98:
            r8 = r2
        L99:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.find.converter.BrushV2Converter.constructTrackSecondaryTitle(java.lang.String, boolean, java.util.List, java.lang.Integer):java.lang.String");
    }

    private final String constructLibraryArtistSecondaryTitle(Integer albumCount, Integer trackCount) {
        if (albumCount == null || trackCount == null) {
            return null;
        }
        return this.context.getResources().getQuantityString(R.plurals.find_library_albums_count, albumCount.intValue(), albumCount) + ", " + this.context.getResources().getQuantityString(R.plurals.find_library_songs_count, trackCount.intValue(), trackCount);
    }

    private final String convertContentEncoding(ContentEncoding contentEncoding) {
        switch (WhenMappings.$EnumSwitchMapping$2[contentEncoding.ordinal()]) {
            case 1:
                return "hdAvailable";
            case 2:
                return "uhdAvailable";
            case 3:
                return "immersive";
            case 4:
                return "ra360Available";
            case 5:
                return "atmosAvailable";
            case 6:
                return EnvironmentCompat.MEDIA_UNKNOWN;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final com.amazon.musicensembleservice.PlaymodeEligibility convertPlaymodeEligibility(PlaymodeEligibility playmodeEligibility) {
        if (playmodeEligibility == null) {
            return null;
        }
        return com.amazon.musicensembleservice.PlaymodeEligibility.builder().withOnDemand(playmodeEligibility.getIsOnDemandPlayable()).withSfa(playmodeEligibility.getIsStationFromAnythingPlayable()).withShuffleType(playmodeEligibility.getShuffleType()).build();
    }

    protected String constructCompactSecondaryTitle(String secondaryTitle, String suffix) {
        Intrinsics.checkNotNullParameter(secondaryTitle, "secondaryTitle");
        if (suffix == null || suffix.length() == 0) {
            return secondaryTitle;
        }
        if (StringsKt__StringsJVMKt.startsWith$default(suffix, Intrinsics.stringPlus(secondaryTitle, " • "), false, 2, null)) {
            return suffix;
        }
        return secondaryTitle + " • " + ((Object) suffix);
    }

    static /* synthetic */ FindImage getThumbnail$default(BrushV2Converter brushV2Converter, SearchItem searchItem, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getThumbnail");
        }
        if ((i & 2) != 0) {
            str = null;
        }
        return brushV2Converter.getThumbnail(searchItem, str);
    }

    private final FindImage getThumbnail(SearchItem searchItem, String defaultArtworkUri) {
        String artworkUri = searchItem.getArtworkUri();
        if (artworkUri != null) {
            defaultArtworkUri = artworkUri;
        }
        FindImage findImage = new FindImage((defaultArtworkUri != null || searchItem.getArtworkId() == null) ? null : this.cacheImageProvider.getCacheImage(searchItem, this.context.getResources().getDimensionPixelSize(R.dimen.artwork_default_dimen)));
        findImage.setUrl(defaultArtworkUri);
        findImage.setType("ORIGINAL_ART");
        return findImage;
    }

    private final Artist constructArtistEntity(String artistName, String artistAsin, Image thumbnailImage, String blockRef, boolean shouldSubtitleShowEntityType, List<? extends ContentTier> contentTiers, String source, Integer albumCount, Integer trackCount, boolean showOrdinal) {
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(contentTiers, 10));
        Iterator<T> it = contentTiers.iterator();
        while (it.hasNext()) {
            arrayList.add(((ContentTier) it.next()).name());
        }
        MusicSearchArtist musicSearchArtist = new MusicSearchArtist(arrayList, source, false, showOrdinal, 4, null);
        musicSearchArtist.setPrimaryTitle(artistName);
        musicSearchArtist.setName(artistName);
        musicSearchArtist.setAsin(artistAsin);
        musicSearchArtist.setSecondaryTitle(constructSecondaryTitle$default(this, EntityType.ARTIST, null, shouldSubtitleShowEntityType, source, albumCount, trackCount, null, null, 192, null));
        musicSearchArtist.setImage(thumbnailImage);
        musicSearchArtist.setBlockRef(constructBlockRef(blockRef));
        return musicSearchArtist;
    }

    private final List<com.amazon.musicensembleservice.brush.AssetQuality> convertAssetQualitiesFromFindAssetQualities(List<AssetQuality> assetQualities) {
        if (assetQualities == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(assetQualities, 10));
        for (AssetQuality assetQuality : assetQualities) {
            com.amazon.musicensembleservice.brush.AssetQuality assetQuality2 = new com.amazon.musicensembleservice.brush.AssetQuality();
            assetQuality2.setPid(assetQuality.getPlaybackId());
            assetQuality2.setQuality(assetQuality.getQuality());
            arrayList.add(assetQuality2);
        }
        return arrayList;
    }

    private final List<com.amazon.musicensembleservice.brush.AssetQuality> convertAssetQualitiesFromSearchAssetQualities(List<com.amazon.music.models.AssetQuality> assetQualities) {
        if (assetQualities == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(assetQualities, 10));
        for (com.amazon.music.models.AssetQuality assetQuality : assetQualities) {
            com.amazon.musicensembleservice.brush.AssetQuality assetQuality2 = new com.amazon.musicensembleservice.brush.AssetQuality();
            assetQuality2.setPid(assetQuality.getPid());
            assetQuality2.setQuality(assetQuality.getQuality());
            arrayList.add(assetQuality2);
        }
        return arrayList;
    }

    protected Entity constructPodcastEpisodeEntity(String title, String podcastShowName, Image thumbnailImage, String blockRef, boolean shouldSubtitleShowEntityType, boolean showOrdinal, String podcastShowId, String catalogId, String podcastEpisodeVariantId, String podcastShowVariantId, List<? extends ContentTier> contentTiers, Map<String, ? extends List<String>> podcastEpisodeTierBenefits, Map<String, ? extends List<String>> podcastShowTierBenefits, Map<String, ? extends Date> availabilityDisplayDate, Date publishDate) {
        List list;
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(thumbnailImage, "thumbnailImage");
        if (contentTiers == null) {
            list = null;
        } else {
            List arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(contentTiers, 10));
            Iterator<T> it = contentTiers.iterator();
            while (it.hasNext()) {
                arrayList.add(((ContentTier) it.next()).name());
            }
            list = arrayList;
        }
        MusicSearchPodcastEpisode musicSearchPodcastEpisode = new MusicSearchPodcastEpisode(list, podcastEpisodeTierBenefits, podcastShowTierBenefits, availabilityDisplayDate, false, showOrdinal, 16, null);
        musicSearchPodcastEpisode.setCatalogId(catalogId);
        musicSearchPodcastEpisode.setPodcastShowCatalogId(podcastShowId);
        musicSearchPodcastEpisode.setTitle(title);
        musicSearchPodcastEpisode.setSubtitle(constructSecondaryTitle$default(this, EntityType.PODCAST_EPISODE, podcastShowName, shouldSubtitleShowEntityType, null, null, null, null, null, 248, null));
        musicSearchPodcastEpisode.setImage(thumbnailImage);
        musicSearchPodcastEpisode.setBlockRef(constructBlockRef(blockRef));
        musicSearchPodcastEpisode.setPodcastEpisodeVariantId(podcastEpisodeVariantId);
        musicSearchPodcastEpisode.setPodcastShowVariantId(podcastShowVariantId);
        musicSearchPodcastEpisode.setPublishDate(publishDate != null ? publishDate.toString() : null);
        return musicSearchPodcastEpisode;
    }

    protected Entity constructPodcastShowEntity(String title, String podcastProviderName, Image thumbnailImage, String blockRef, boolean shouldSubtitleShowEntityType, boolean showOrdinal, String catalogId, String variantId, List<? extends ContentTier> contentTiers, Map<String, ? extends List<String>> tierBenefits) {
        List list;
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(thumbnailImage, "thumbnailImage");
        if (contentTiers == null) {
            list = null;
        } else {
            List arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(contentTiers, 10));
            Iterator<T> it = contentTiers.iterator();
            while (it.hasNext()) {
                arrayList.add(((ContentTier) it.next()).name());
            }
            list = arrayList;
        }
        MusicSearchPodcastShow musicSearchPodcastShow = new MusicSearchPodcastShow(list, tierBenefits, false, showOrdinal, 4, null);
        musicSearchPodcastShow.setTitle(title);
        musicSearchPodcastShow.setSubtitle(constructSecondaryTitle$default(this, EntityType.PODCAST_SHOW, podcastProviderName, shouldSubtitleShowEntityType, null, null, null, null, null, 248, null));
        musicSearchPodcastShow.setCatalogId(catalogId);
        musicSearchPodcastShow.setBlockRef(constructBlockRef(blockRef));
        musicSearchPodcastShow.setImage(thumbnailImage);
        musicSearchPodcastShow.setPodcastShowVariantId(variantId);
        return musicSearchPodcastShow;
    }

    protected final List<NavigationEntity> getNavigationEntities(List<String> searchTerms) {
        Intrinsics.checkNotNullParameter(searchTerms, "searchTerms");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(searchTerms, 10));
        int i = 0;
        for (Object obj : searchTerms) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(constructNavigationEntityForSearchTerm((String) obj, String.valueOf(i)));
            i = i2;
        }
        return arrayList;
    }

    protected final List<Entity> convertTopHitItems(List<? extends SearchItem> topHitItems, boolean shouldSubtitleShowEntityType, boolean showOrdinal) {
        Entity musicEntitiesForSearchLiveEvent;
        Intrinsics.checkNotNullParameter(topHitItems, "topHitItems");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : topHitItems) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            SearchItem searchItem = (SearchItem) obj;
            if (searchItem instanceof PodcastShowSearchItem) {
                musicEntitiesForSearchLiveEvent = getMusicEntitiesForSearchPodcastShow((PodcastShowSearchItem) searchItem, shouldSubtitleShowEntityType, showOrdinal);
            } else if (searchItem instanceof PodcastEpisodeSearchItem) {
                musicEntitiesForSearchLiveEvent = getMusicEntitiesForSearchPodcastEpisode((PodcastEpisodeSearchItem) searchItem, shouldSubtitleShowEntityType, showOrdinal);
            } else if (searchItem instanceof TrackSearchItem) {
                musicEntitiesForSearchLiveEvent = getMusicEntitiesForSearchTrack((TrackSearchItem) searchItem, shouldSubtitleShowEntityType, showOrdinal, Integer.valueOf(i));
            } else if (searchItem instanceof PlaylistSearchItem) {
                musicEntitiesForSearchLiveEvent = getMusicEntitiesForSearchPlaylist((PlaylistSearchItem) searchItem, shouldSubtitleShowEntityType, showOrdinal);
            } else if (searchItem instanceof AlbumSearchItem) {
                musicEntitiesForSearchLiveEvent = getMusicEntitiesForSearchAlbum((AlbumSearchItem) searchItem, shouldSubtitleShowEntityType, showOrdinal);
            } else if (searchItem instanceof StationSearchItem) {
                musicEntitiesForSearchLiveEvent = getMusicEntitiesForSearchStation((StationSearchItem) searchItem, shouldSubtitleShowEntityType, showOrdinal);
            } else if (searchItem instanceof ArtistSearchItem) {
                musicEntitiesForSearchLiveEvent = getMusicEntitiesForSearchArtist((ArtistSearchItem) searchItem, shouldSubtitleShowEntityType, showOrdinal);
            } else {
                musicEntitiesForSearchLiveEvent = searchItem instanceof LiveEventSearchItem ? getMusicEntitiesForSearchLiveEvent((LiveEventSearchItem) searchItem, shouldSubtitleShowEntityType, showOrdinal) : null;
            }
            if (musicEntitiesForSearchLiveEvent != null) {
                arrayList.add(musicEntitiesForSearchLiveEvent);
            }
            i = i2;
        }
        return arrayList;
    }

    protected final List<Entity> getMusicEntitiesForHistory(List<? extends SearchHistoryItem> searchHistoryQueries) {
        Entity entityConstructSearchQueryEntity;
        Intrinsics.checkNotNullParameter(searchHistoryQueries, "searchHistoryQueries");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : searchHistoryQueries) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            SearchHistoryItem searchHistoryItem = (SearchHistoryItem) obj;
            if (searchHistoryItem instanceof SearchHistoryEntity) {
                entityConstructSearchQueryEntity = constructMusicEntityForSearchEntity(((SearchHistoryEntity) searchHistoryItem).getSearchEntity(), searchHistoryItem.getSearchQuery());
            } else {
                entityConstructSearchQueryEntity = searchHistoryItem instanceof SearchHistoryQuery ? constructSearchQueryEntity(searchHistoryItem.getSearchQuery(), i) : null;
            }
            if (entityConstructSearchQueryEntity != null) {
                arrayList.add(entityConstructSearchQueryEntity);
            }
            i = i2;
        }
        return arrayList;
    }

    protected final List<Entity> getMusicEntitiesForSuggestions(List<? extends SearchSuggestion> searchSuggestions) {
        Entity entityConstructNavigationEntityForSearchTerm;
        Intrinsics.checkNotNullParameter(searchSuggestions, "searchSuggestions");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : searchSuggestions) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            SearchSuggestion searchSuggestion = (SearchSuggestion) obj;
            if (searchSuggestion instanceof SearchSuggestionEntity) {
                entityConstructNavigationEntityForSearchTerm = constructMusicEntityForSearchEntity(((SearchSuggestionEntity) searchSuggestion).getSearchEntity(), searchSuggestion.getTitle());
            } else {
                String title = searchSuggestion.getTitle();
                String blockRef = searchSuggestion.getBlockRef();
                if (blockRef == null) {
                    blockRef = String.valueOf(i);
                }
                entityConstructNavigationEntityForSearchTerm = constructNavigationEntityForSearchTerm(title, blockRef);
            }
            if (entityConstructNavigationEntityForSearchTerm != null) {
                arrayList.add(entityConstructNavigationEntityForSearchTerm);
            }
            i = i2;
        }
        return arrayList;
    }

    protected final List<Entity> getMusicEntitiesForSearchAlbums(List<? extends AlbumSearchItem> albums, boolean shouldSubtitleShowEntityType, boolean showOrdinal) {
        Intrinsics.checkNotNullParameter(albums, "albums");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = albums.iterator();
        while (it.hasNext()) {
            Entity musicEntitiesForSearchAlbum = getMusicEntitiesForSearchAlbum((AlbumSearchItem) it.next(), shouldSubtitleShowEntityType, showOrdinal);
            if (musicEntitiesForSearchAlbum != null) {
                arrayList.add(musicEntitiesForSearchAlbum);
            }
        }
        return arrayList;
    }

    protected final List<Entity> getMusicEntitiesForSearchArtists(List<? extends ArtistSearchItem> artists, boolean shouldSubtitleShowEntityType, boolean showOrdinal) {
        Intrinsics.checkNotNullParameter(artists, "artists");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = artists.iterator();
        while (it.hasNext()) {
            Entity musicEntitiesForSearchArtist = getMusicEntitiesForSearchArtist((ArtistSearchItem) it.next(), shouldSubtitleShowEntityType, showOrdinal);
            if (musicEntitiesForSearchArtist != null) {
                arrayList.add(musicEntitiesForSearchArtist);
            }
        }
        return arrayList;
    }

    protected final List<Entity> getMusicEntitiesForSearchPlaylists(List<? extends PlaylistSearchItem> playlists, boolean shouldSubtitleShowEntityType, boolean showOrdinal) {
        Intrinsics.checkNotNullParameter(playlists, "playlists");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = playlists.iterator();
        while (it.hasNext()) {
            Entity musicEntitiesForSearchPlaylist = getMusicEntitiesForSearchPlaylist((PlaylistSearchItem) it.next(), shouldSubtitleShowEntityType, showOrdinal);
            if (musicEntitiesForSearchPlaylist != null) {
                arrayList.add(musicEntitiesForSearchPlaylist);
            }
        }
        return arrayList;
    }

    protected final List<Entity> getMusicEntitiesForSearchPodcastEpisodes(List<? extends PodcastEpisodeSearchItem> podcastEpisodes, boolean shouldSubtitleShowEntityType, boolean showOrdinal) {
        Intrinsics.checkNotNullParameter(podcastEpisodes, "podcastEpisodes");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = podcastEpisodes.iterator();
        while (it.hasNext()) {
            Entity musicEntitiesForSearchPodcastEpisode = getMusicEntitiesForSearchPodcastEpisode((PodcastEpisodeSearchItem) it.next(), shouldSubtitleShowEntityType, showOrdinal);
            if (musicEntitiesForSearchPodcastEpisode != null) {
                arrayList.add(musicEntitiesForSearchPodcastEpisode);
            }
        }
        return arrayList;
    }

    protected final List<Entity> getMusicEntitiesForSearchPodcastShows(List<? extends PodcastShowSearchItem> podcasts, boolean shouldSubtitleShowEntityType, boolean showOrdinal) {
        Intrinsics.checkNotNullParameter(podcasts, "podcasts");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = podcasts.iterator();
        while (it.hasNext()) {
            Entity musicEntitiesForSearchPodcastShow = getMusicEntitiesForSearchPodcastShow((PodcastShowSearchItem) it.next(), shouldSubtitleShowEntityType, showOrdinal);
            if (musicEntitiesForSearchPodcastShow != null) {
                arrayList.add(musicEntitiesForSearchPodcastShow);
            }
        }
        return arrayList;
    }

    protected final List<Entity> getMusicEntitiesForSearchStations(List<? extends StationSearchItem> stations, boolean shouldSubtitleShowEntityType, boolean showOrdinal) {
        Intrinsics.checkNotNullParameter(stations, "stations");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = stations.iterator();
        while (it.hasNext()) {
            Entity musicEntitiesForSearchStation = getMusicEntitiesForSearchStation((StationSearchItem) it.next(), shouldSubtitleShowEntityType, showOrdinal);
            if (musicEntitiesForSearchStation != null) {
                arrayList.add(musicEntitiesForSearchStation);
            }
        }
        return arrayList;
    }

    protected final List<Entity> getMusicEntitiesForSearchTracks(List<? extends TrackSearchItem> tracks, boolean shouldSubtitleShowEntityType, boolean showOrdinal) {
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = tracks.iterator();
        while (it.hasNext()) {
            Entity musicEntitiesForSearchTrack$default = getMusicEntitiesForSearchTrack$default(this, (TrackSearchItem) it.next(), shouldSubtitleShowEntityType, showOrdinal, null, 8, null);
            if (musicEntitiesForSearchTrack$default != null) {
                arrayList.add(musicEntitiesForSearchTrack$default);
            }
        }
        return arrayList;
    }

    protected final List<Entity> getMusicEntitiesForSportsItems(List<? extends SportsSearchItem> sportsItems, boolean shouldSubtitleShowEntityType) {
        Intrinsics.checkNotNullParameter(sportsItems, "sportsItems");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = sportsItems.iterator();
        while (it.hasNext()) {
            Entity musicEntitiesForSportsItem = getMusicEntitiesForSportsItem((SportsSearchItem) it.next(), shouldSubtitleShowEntityType);
            if (musicEntitiesForSportsItem != null) {
                arrayList.add(musicEntitiesForSportsItem);
            }
        }
        return arrayList;
    }
}
