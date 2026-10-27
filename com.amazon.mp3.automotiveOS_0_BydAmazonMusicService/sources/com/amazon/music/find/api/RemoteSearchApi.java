package com.amazon.music.find.api;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.ContentAccessType;
import com.amazon.music.find.api.util.SpellCorrectionsUtil;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.data.database.LibrarySearchDao;
import com.amazon.music.find.model.ExecuteQueryRequest;
import com.amazon.music.find.model.filter.FilterMetadata;
import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.model.search.AlbumSearchItem;
import com.amazon.music.find.model.search.ArtistSearchItem;
import com.amazon.music.find.model.search.ContentOwnershipItem;
import com.amazon.music.find.model.search.GenericSearchResponse;
import com.amazon.music.find.model.search.LiveEventSearchItem;
import com.amazon.music.find.model.search.PlaylistSearchItem;
import com.amazon.music.find.model.search.PlaylistType;
import com.amazon.music.find.model.search.PodcastEpisodeSearchItem;
import com.amazon.music.find.model.search.PodcastShowSearchItem;
import com.amazon.music.find.model.search.SearchAssetType;
import com.amazon.music.find.model.search.SearchContentOwnershipStatus;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.find.model.search.SportsSearchItem;
import com.amazon.music.find.model.search.StationSearchItem;
import com.amazon.music.find.model.search.TrackSearchItem;
import com.amazon.music.find.providers.SearchEntityFactory;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.ExecutionContext;
import com.amazon.music.find.service.SearchServiceException;
import com.amazon.music.find.service.UIConfigurationService;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.models.AssetQuality;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.platform.playback.data.ShuffleType;
import com.amazon.music.search.AdditionalFields;
import com.amazon.music.search.Album;
import com.amazon.music.search.AlbumSearchRequest;
import com.amazon.music.search.Artist;
import com.amazon.music.search.ArtistSearchRequest;
import com.amazon.music.search.AssetQualitiesProvider;
import com.amazon.music.search.CommunityPlaylist;
import com.amazon.music.search.CommunityPlaylistSearchRequest;
import com.amazon.music.search.Competition;
import com.amazon.music.search.EverythingSearchRequest;
import com.amazon.music.search.LiveEvent;
import com.amazon.music.search.PageResult;
import com.amazon.music.search.Playlist;
import com.amazon.music.search.PlaylistSearchRequest;
import com.amazon.music.search.PodcastEpisode;
import com.amazon.music.search.PodcastEpisodeSearchRequest;
import com.amazon.music.search.PodcastShow;
import com.amazon.music.search.PodcastShowSearchRequest;
import com.amazon.music.search.Program;
import com.amazon.music.search.ResultItem;
import com.amazon.music.search.ResultType;
import com.amazon.music.search.Search;
import com.amazon.music.search.SearchResult;
import com.amazon.music.search.SearchService;
import com.amazon.music.search.SearchServiceImpl;
import com.amazon.music.search.SectionType;
import com.amazon.music.search.SpellCorrections;
import com.amazon.music.search.SportsItem;
import com.amazon.music.search.SportsSearchRequest;
import com.amazon.music.search.Station;
import com.amazon.music.search.StationSearchRequest;
import com.amazon.music.search.Track;
import com.amazon.music.search.TrackSearchRequest;
import com.amazon.music.search.Video;
import com.amazon.music.search.VideoPlaylist;
import com.amazon.music.search.VideoPlaylistSearchRequest;
import com.amazon.music.search.VideoSearchRequest;
import com.android.volley.VolleyError;
import com.google.android.gms.actions.SearchIntents;
import com.visualon.OSMPUtils.voMimeTypes;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: RemoteSearchApi.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Ô\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 Á\u00012\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002Á\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J&\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001eH\u0002JF\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002JF\u0010.\u001a\u00020/2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002J\u0016\u00100\u001a\b\u0012\u0004\u0012\u0002010\u001b2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002J6\u00102\u001a\u0002032\u0006\u0010#\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002J\u001c\u00104\u001a\u000e\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u001e052\u0006\u00107\u001a\u00020\u001eH\u0002J\u001e\u00108\u001a\b\u0012\u0004\u0012\u0002090\u001b2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001eH\u0002JF\u0010:\u001a\u00020;2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002JF\u0010<\u001a\u00020=2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002JF\u0010>\u001a\u00020?2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020CH\u0004JF\u0010D\u001a\u00020E2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002JF\u0010F\u001a\u00020G2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002J.\u0010H\u001a\b\u0012\u0004\u0012\u00020I0\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010J\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001eH\u0002JF\u0010K\u001a\u00020L2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002JF\u0010M\u001a\u00020N2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002JF\u0010O\u001a\u00020P2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002J\u001e\u0010Q\u001a\b\u0012\u0004\u0012\u00020R0\u001b2\u000e\u0010S\u001a\n\u0012\u0004\u0012\u00020T\u0018\u00010\u001bH\u0002JD\u0010U\u001a\b\u0012\u0004\u0012\u0002HV0\u001b\"\b\b\u0000\u0010W*\u00020X\"\b\b\u0001\u0010V*\u00020Y2\f\u0010Z\u001a\b\u0012\u0004\u0012\u0002HW0[2\u0012\u0010\\\u001a\u000e\u0012\u0004\u0012\u0002HW\u0012\u0004\u0012\u0002HV0]H\u0002J(\u0010^\u001a\u0004\u0018\u00010_2\b\u0010`\u001a\u0004\u0018\u00010a2\u0006\u0010\u001f\u001a\u00020\u001e2\n\b\u0002\u0010b\u001a\u0004\u0018\u00010_H\u0002J \u0010c\u001a\u00020\u00022\u0006\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020$2\u0006\u0010)\u001a\u00020*H\u0002JK\u0010g\u001a\u00020\u00022\u0006\u0010h\u001a\u00020i2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\b\u0010,\u001a\u0004\u0018\u00010-H\u0004¢\u0006\u0002\u0010jJ\u0010\u0010k\u001a\u00020e2\u0006\u0010B\u001a\u00020CH\u0014JN\u0010l\u001a\u00020m2\u0006\u0010h\u001a\u00020i2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002JN\u0010n\u001a\u00020m2\u0006\u0010h\u001a\u00020i2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002JF\u0010o\u001a\u00020m2\u0006\u0010h\u001a\u00020i2\u0006\u0010#\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010)\u001a\u00020*2\b\u0010(\u001a\u0004\u0018\u00010$2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002JN\u0010p\u001a\u00020m2\u0006\u0010h\u001a\u00020i2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002JN\u0010q\u001a\u00020m2\u0006\u0010h\u001a\u00020i2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002JN\u0010r\u001a\u00020m2\u0006\u0010h\u001a\u00020i2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002JN\u0010s\u001a\u00020m2\u0006\u0010h\u001a\u00020i2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002JN\u0010t\u001a\u00020m2\u0006\u0010h\u001a\u00020i2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002JN\u0010u\u001a\u00020m2\u0006\u0010h\u001a\u00020i2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002JN\u0010v\u001a\u00020m2\u0006\u0010h\u001a\u00020i2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002JN\u0010w\u001a\u00020m2\u0006\u0010h\u001a\u00020i2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010$2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\u0006\u0010,\u001a\u00020-H\u0002J\u0016\u0010x\u001a\b\u0012\u0004\u0012\u00020$0\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J8\u0010y\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010z\u001a\u00020{2\u0006\u0010|\u001a\u00020$2\u0006\u0010)\u001a\u00020*2\u0006\u0010}\u001a\u00020\u001eH\u0002J\u001e\u0010~\u001a\b\u0012\u0004\u0012\u00020$0\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010\u007f\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0002J\u0011\u0010\u0080\u0001\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0002J\u0011\u0010\u0081\u0001\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0002J\t\u0010\u0082\u0001\u001a\u00020\u001eH\u0007J\u0011\u0010\u0083\u0001\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0002J\u0011\u0010\u0084\u0001\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0002J\u0011\u0010\u0085\u0001\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0002J\u0011\u0010\u0086\u0001\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0002J\u0011\u0010\u0087\u0001\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0002J\u0011\u0010\u0088\u0001\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0002J\u0011\u0010\u0089\u0001\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0002J\u0011\u0010\u008a\u0001\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0002J\u0014\u0010\u008b\u0001\u001a\u00030\u008c\u00012\b\u0010\u008d\u0001\u001a\u00030\u008e\u0001H\u0002J%\u0010\u008f\u0001\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020-052\u000e\u0010\u0090\u0001\u001a\t\u0012\u0005\u0012\u00030\u0091\u00010\u001bH\u0002J\u0013\u0010\u0092\u0001\u001a\u00020m2\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001H\u0002J\u0014\u0010\u0095\u0001\u001a\u00030\u0096\u00012\b\u0010\u0097\u0001\u001a\u00030\u0098\u0001H\u0002J\u0014\u0010\u0095\u0001\u001a\u00030\u0096\u00012\b\u0010\u0097\u0001\u001a\u00030\u0099\u0001H\u0002J\u0014\u0010\u009a\u0001\u001a\u00030\u009b\u00012\b\u0010\u009c\u0001\u001a\u00030\u009d\u0001H\u0002J\u0014\u0010\u009e\u0001\u001a\u00030\u009f\u00012\b\u0010 \u0001\u001a\u00030¡\u0001H\u0002J\u0012\u0010¢\u0001\u001a\u00020\u00022\u0007\u0010£\u0001\u001a\u00020\u0002H\u0002J\\\u0010#\u001a\t\u0012\u0004\u0012\u00020\u00020¤\u00012\u0006\u0010h\u001a\u00020i2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\u0007\u0010¥\u0001\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016¢\u0006\u0003\u0010¦\u0001Jq\u0010§\u0001\u001a\t\u0012\u0004\u0012\u00020m0¤\u00012\u0006\u0010h\u001a\u00020i2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010¨\u0001\u001a\u00030©\u00012\b\u0010(\u001a\u0004\u0018\u00010$2\u0007\u0010¥\u0001\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010$2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016¢\u0006\u0003\u0010ª\u0001J\u0011\u0010«\u0001\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0002J\u0014\u0010¬\u0001\u001a\u00030\u00ad\u00012\b\u0010®\u0001\u001a\u00030¯\u0001H\u0002J\u0014\u0010°\u0001\u001a\u00030±\u00012\b\u0010²\u0001\u001a\u00030³\u0001H\u0002J\u0014\u0010´\u0001\u001a\u00030µ\u00012\b\u0010¶\u0001\u001a\u00030·\u0001H\u0002J\u001a\u0010¸\u0001\u001a\u00030¹\u00012\u000e\u0010º\u0001\u001a\t\u0012\u0005\u0012\u00030µ\u00010\u001bH\u0002J\u0014\u0010»\u0001\u001a\u00030µ\u00012\b\u0010¼\u0001\u001a\u00030½\u0001H\u0002J\u0014\u0010¾\u0001\u001a\u00030\u0096\u00012\b\u0010¿\u0001\u001a\u00030À\u0001H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006Â\u0001"}, d2 = {"Lcom/amazon/music/find/api/RemoteSearchApi;", "Lcom/amazon/music/find/api/SearchApi;", "Lcom/amazon/music/find/model/search/GenericSearchResponse;", "searchConfiguration", "Lcom/amazon/music/search/SearchService$Configuration;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "searchEntityFactory", "Lcom/amazon/music/find/providers/SearchEntityFactory;", "uiConfigurationService", "Lcom/amazon/music/find/service/UIConfigurationService;", "librarySearchDao", "Lcom/amazon/music/find/data/database/LibrarySearchDao;", "(Lcom/amazon/music/search/SearchService$Configuration;Lcom/amazon/music/find/providers/SearchFeaturesProvider;Lcom/amazon/music/find/providers/SearchEntityFactory;Lcom/amazon/music/find/service/UIConfigurationService;Lcom/amazon/music/find/data/database/LibrarySearchDao;)V", "search", "Lcom/amazon/music/search/Search;", "searchService", "Lcom/amazon/music/search/SearchService;", "albumFromSearchResult", "Lcom/amazon/music/find/model/search/AlbumSearchItem;", "album", "Lcom/amazon/music/search/Album;", "artistFromSearchResult", "Lcom/amazon/music/find/model/search/ArtistSearchItem;", "artist", "Lcom/amazon/music/search/Artist;", "buildAlbumAdditionalFields", "", "Lcom/amazon/music/search/Album$AdditionalFields;", "isKatana", "", "shouldRequestPlaymodeEligibility", "isLanguageOfPerformanceEnabled", "buildAlbumSearchRequest", "Lcom/amazon/music/search/AlbumSearchRequest;", SearchIntents.EXTRA_QUERY, "", "enableSpellCorrections", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "pageToken", "searchFilterSelections", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", PageUriUtils.QUERYMETADATA_QUERY_PARAM, "pageSize", "", "buildArtistSearchRequest", "Lcom/amazon/music/search/ArtistSearchRequest;", "buildCommunityPlaylistAdditionalFields", "Lcom/amazon/music/search/CommunityPlaylist$AdditionalFields;", "buildCommunityPlaylistSearchRequest", "Lcom/amazon/music/search/CommunityPlaylistSearchRequest;", "buildExplicitMap", "", "Lcom/amazon/music/ContentAccessType;", BrushV2Converter.KEY_IS_EXPLICIT, "buildPlaylistAdditionalFields", "Lcom/amazon/music/search/Playlist$AdditionalFields;", "buildPlaylistSearchRequest", "Lcom/amazon/music/search/PlaylistSearchRequest;", "buildPodcastEpisodeSearchRequest", "Lcom/amazon/music/search/PodcastEpisodeSearchRequest;", "buildPodcastShowSearchRequest", "Lcom/amazon/music/search/PodcastShowSearchRequest;", "buildRequest", "Lcom/amazon/music/search/EverythingSearchRequest;", "queryRequest", "Lcom/amazon/music/find/model/ExecuteQueryRequest;", "buildSportsSearchRequest", "Lcom/amazon/music/search/SportsSearchRequest;", "buildStationSearchRequest", "Lcom/amazon/music/search/StationSearchRequest;", "buildTrackAdditionalFields", "Lcom/amazon/music/search/Track$AdditionalFields;", "shouldRequestTrackRelatedPlaylists", "buildTrackSearchRequest", "Lcom/amazon/music/search/TrackSearchRequest;", "buildVideoPlaylistSearchRequest", "Lcom/amazon/music/search/VideoPlaylistSearchRequest;", "buildVideosSearchRequest", "Lcom/amazon/music/search/VideoSearchRequest;", "convertAssetQuality", "Lcom/amazon/music/models/AssetQuality;", "tenzingAssetQualityList", "Lcom/amazon/music/platform/model/AssetQuality;", "convertEntityPager", "V", "U", "Lcom/amazon/music/search/ResultItem;", "Lcom/amazon/music/find/model/search/SearchItem;", "pageResult", "Lcom/amazon/music/search/PageResult;", "transform", "Lkotlin/Function1;", "convertPlaymodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "searchPlaymodeEligibility", "Lcom/amazon/music/search/PlaymodeEligibility;", "defaultPlaymodeEligibility", "convertSearchResult", "searchResult", "Lcom/amazon/music/search/SearchResult;", "searchTerm", "executeQuery", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "(Lcom/amazon/music/find/service/ExecutionContext;Ljava/lang/String;ZLcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/find/model/filter/SearchFilterSelections;Ljava/lang/String;Ljava/lang/Integer;)Lcom/amazon/music/find/model/search/GenericSearchResponse;", "executeSearch", "executeSeeMoreAlbum", "Lcom/amazon/music/find/model/search/SeeMoreResponse;", "executeSeeMoreArtist", "executeSeeMoreCommunityPlaylist", "executeSeeMorePlaylist", "executeSeeMorePodcastEpisode", "executeSeeMorePodcastShow", "executeSeeMoreSports", "executeSeeMoreStation", "executeSeeMoreTrack", "executeSeeMoreVideo", "executeSeeMoreVideoPlaylist", "getAssetQualities", "getAssetQualitiesForSearchType", "resultType", "Lcom/amazon/music/search/ResultType;", "searchType", "isEverythingSearchRequest", "getAssetQualitiesFromFilterSelections", "isAlbumEnabled", "isArtistEnabled", "isCommunityPlaylistEnabled", "isLiveEventEnabled", "isPlaylistEnabled", "isPodcastEnabled", "isSportEnabled", "isStationEnabled", "isTopHitsEnabled", "isTrackEnabled", "isVideoEnabled", "isVideoPlaylistEnabled", "liveEventFromSearchResult", "Lcom/amazon/music/find/model/search/LiveEventSearchItem;", "liveEvent", "Lcom/amazon/music/search/LiveEvent;", "mapContentOwnershipStatus", "contentOwnershipItems", "Lcom/amazon/music/find/model/search/ContentOwnershipItem;", "noResultsSeeMoreResponse", "sectionType", "Lcom/amazon/music/search/SectionType;", "playlistFromSearchResult", "Lcom/amazon/music/find/model/search/PlaylistSearchItem;", Environment.PLAYLIST_PATH, "Lcom/amazon/music/search/CommunityPlaylist;", "Lcom/amazon/music/search/Playlist;", "podcastEpisodeFromSearchResult", "Lcom/amazon/music/find/model/search/PodcastEpisodeSearchItem;", "podcastEpisode", "Lcom/amazon/music/search/PodcastEpisode;", "podcastShowFromSearchResult", "Lcom/amazon/music/find/model/search/PodcastShowSearchItem;", "podcastShow", "Lcom/amazon/music/search/PodcastShow;", "postProcessResponse", "response", "Lio/reactivex/rxjava3/core/Observable;", "isOffline", "(Lcom/amazon/music/find/service/ExecutionContext;Ljava/lang/String;ZLcom/amazon/music/search/SpellCorrections;ZLcom/amazon/music/find/model/filter/SearchFilterSelections;Ljava/lang/String;Ljava/lang/Integer;)Lio/reactivex/rxjava3/core/Observable;", PageUriUtils.DO_SEE_MORE_VALUE, PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/platform/find/common/EntityType;", "(Lcom/amazon/music/find/service/ExecutionContext;Ljava/lang/String;ZLcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/platform/find/common/EntityType;Ljava/lang/String;ZLcom/amazon/music/find/model/filter/SearchFilterSelections;Ljava/lang/String;Ljava/lang/Integer;)Lio/reactivex/rxjava3/core/Observable;", "shouldSuppressNonTrackAlbum", "sportFromSearchResult", "Lcom/amazon/music/find/model/search/SportsSearchItem;", "sport", "Lcom/amazon/music/search/SportsItem;", "stationFromSearchResult", "Lcom/amazon/music/find/model/search/StationSearchItem;", "station", "Lcom/amazon/music/search/Station;", "trackFromSearchResult", "Lcom/amazon/music/find/model/search/TrackSearchItem;", "track", "Lcom/amazon/music/search/Track;", "updateOwnershipStatus", "", "tracks", "videoFromSearchResult", voMimeTypes.VOBASE_TYPE_VIDEO, "Lcom/amazon/music/search/Video;", "videoPlaylistFromSearchResult", "videoPlaylist", "Lcom/amazon/music/search/VideoPlaylist;", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class RemoteSearchApi implements SearchApi<GenericSearchResponse> {
    public static final String ATMOS_ASSET_QUALITY = "ATMOS";
    private static final int DEFAULT_DISCNUM = 1;
    public static final int DEFAULT_PAGE_SIZE = 25;
    private static final int DEFAULT_TRACKNUM = 1;
    public static final String HD_ASSET_QUALITY = "HD";
    public static final String IMMERSIVE_ASSET_QUALITY = "IMMERSIVE";
    public static final String NOT_ASSIGNED_ASSET_QUALITY = "NOT_ASSIGNED";
    private static final PlaymodeEligibility PLAYMODE_ELIGIBILITY_SHUFFLE_DEFAULT;
    private static final PlaymodeEligibility PLAYMODE_ELIGIBILITY_SHUFFLE_MIXED;
    public static final String RA360_ASSET_QUALITY = "RA360";
    public static final int STATIONS_DEFAULT_PAGE_SIZE = 24;
    public static final String UHD_ASSET_QUALITY = "UHD";
    private static final Logger logger;
    private final LibrarySearchDao librarySearchDao;
    private final Search search;
    private final SearchService.Configuration searchConfiguration;
    private final SearchEntityFactory searchEntityFactory;
    private final SearchFeaturesProvider searchFeaturesProvider;
    private final SearchService searchService;
    private final UIConfigurationService uiConfigurationService;

    /* JADX INFO: compiled from: RemoteSearchApi.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EntityType.values().length];
            iArr[EntityType.ALBUM.ordinal()] = 1;
            iArr[EntityType.ARTIST.ordinal()] = 2;
            iArr[EntityType.PLAYLIST.ordinal()] = 3;
            iArr[EntityType.COMMUNITY_PLAYLIST.ordinal()] = 4;
            iArr[EntityType.PODCAST_EPISODE.ordinal()] = 5;
            iArr[EntityType.PODCAST_SHOW.ordinal()] = 6;
            iArr[EntityType.SPORT.ordinal()] = 7;
            iArr[EntityType.STATION.ordinal()] = 8;
            iArr[EntityType.TRACK.ordinal()] = 9;
            iArr[EntityType.VIDEO.ordinal()] = 10;
            iArr[EntityType.VIDEO_PLAYLIST.ordinal()] = 11;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private final boolean isAlbumEnabled(SearchFilterSelections searchFilterSelections) {
        return true;
    }

    private final boolean isTrackEnabled(SearchFilterSelections searchFilterSelections) {
        return true;
    }

    public RemoteSearchApi(SearchService.Configuration searchConfiguration, SearchFeaturesProvider searchFeaturesProvider, SearchEntityFactory searchEntityFactory, UIConfigurationService uiConfigurationService, LibrarySearchDao librarySearchDao) {
        Intrinsics.checkNotNullParameter(searchConfiguration, "searchConfiguration");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        Intrinsics.checkNotNullParameter(searchEntityFactory, "searchEntityFactory");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(librarySearchDao, "librarySearchDao");
        this.searchConfiguration = searchConfiguration;
        this.searchFeaturesProvider = searchFeaturesProvider;
        this.searchEntityFactory = searchEntityFactory;
        this.uiConfigurationService = uiConfigurationService;
        this.librarySearchDao = librarySearchDao;
        SearchServiceImpl searchServiceImpl = new SearchServiceImpl(searchConfiguration);
        this.searchService = searchServiceImpl;
        this.search = new Search(searchServiceImpl);
    }

    static {
        Boolean bool = Boolean.FALSE;
        Boolean bool2 = Boolean.TRUE;
        PLAYMODE_ELIGIBILITY_SHUFFLE_MIXED = new PlaymodeEligibility(bool, bool2, ShuffleType.MIXED.toString());
        PLAYMODE_ELIGIBILITY_SHUFFLE_DEFAULT = new PlaymodeEligibility(bool, bool2, null);
        logger = LoggerFactory.getLogger(RemoteSearchApi.class.getName());
    }

    @Override // com.amazon.music.find.api.SearchApi
    public Observable<GenericSearchResponse> query(final ExecutionContext executionContext, final String query, final boolean enableSpellCorrections, final SpellCorrections spellCorrections, boolean isOffline, final SearchFilterSelections searchFilterSelections, final String queryMetadata, final Integer pageSize) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        Observable<GenericSearchResponse> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.music.find.api.RemoteSearchApi$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                RemoteSearchApi.m2026query$lambda0(this.f$0, executionContext, query, enableSpellCorrections, spellCorrections, searchFilterSelections, queryMetadata, pageSize, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …)\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: query$lambda-0, reason: not valid java name */
    public static final void m2026query$lambda0(RemoteSearchApi this$0, ExecutionContext executionContext, String query, boolean z, SpellCorrections spellCorrections, SearchFilterSelections searchFilterSelections, String str, Integer num, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        Intrinsics.checkNotNullParameter(query, "$query");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "$searchFilterSelections");
        try {
            observableEmitter.onNext(this$0.executeQuery(executionContext, query, z, spellCorrections, searchFilterSelections, str, num));
            observableEmitter.onComplete();
        } catch (SearchServiceException e) {
            String strStringPlus = Intrinsics.stringPlus("Something went wrong while getting Tenzing search response:", e.getMessage());
            logger.warn(strStringPlus);
            observableEmitter.onError(new SearchServiceException(strStringPlus));
        }
    }

    protected final GenericSearchResponse executeQuery(ExecutionContext executionContext, String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, SearchFilterSelections searchFilterSelections, String queryMetadata, Integer pageSize) throws SearchServiceException {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        try {
            executionContext.markApiRequestStarted();
            SearchResult searchResultExecuteSearch = executeSearch(new ExecuteQueryRequest(query, enableSpellCorrections, spellCorrections, searchFilterSelections, this.searchFeaturesProvider.isKatana(), this.searchFeaturesProvider.isExplicitLanguageFilterEnabled(), this.searchFeaturesProvider.getFilterByExplicitLanguage(), this.searchFeaturesProvider.getFilterByChildFriendlyContent(), this.searchFeaturesProvider.isUpsellToUnlimitedOnTopHitsTurnedOn(), this.searchFeaturesProvider.getSearchContentTier(), isVideoEnabled(searchFilterSelections), isVideoPlaylistEnabled(searchFilterSelections), isPodcastEnabled(searchFilterSelections), isTrackEnabled(searchFilterSelections), isAlbumEnabled(searchFilterSelections), isArtistEnabled(searchFilterSelections), isPlaylistEnabled(searchFilterSelections), isStationEnabled(searchFilterSelections), isSportEnabled(searchFilterSelections), isTopHitsEnabled(searchFilterSelections), isCommunityPlaylistEnabled(searchFilterSelections), isLiveEventEnabled(), this.searchFeaturesProvider.isLanguageOfPerformanceEnabled(), this.searchFeaturesProvider.getShouldRequestRelatedEntities(), this.searchFeaturesProvider.shouldRequestPlaymodeEligibility(EntityType.ALBUM), this.searchFeaturesProvider.shouldRequestPlaymodeEligibility(EntityType.TRACK), this.searchFeaturesProvider.shouldRequestPlaymodeEligibility(EntityType.PLAYLIST), this.searchFeaturesProvider.shouldRequestPlaymodeEligibility(EntityType.COMMUNITY_PLAYLIST), queryMetadata, pageSize));
            executionContext.markApiRequestCompleted();
            return postProcessResponse(convertSearchResult(searchResultExecuteSearch, query, searchFilterSelections));
        } catch (Exception e) {
            throw new SearchServiceException(Intrinsics.stringPlus("Unhandled exception during getting Tenzing search response: ", e.getMessage()));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SearchResult executeSearch(ExecuteQueryRequest queryRequest) throws VolleyError {
        Intrinsics.checkNotNullParameter(queryRequest, "queryRequest");
        SearchResult searchResultSearch = this.search.search(buildRequest(queryRequest));
        Intrinsics.checkNotNullExpressionValue(searchResultSearch, "search.search(request)");
        return searchResultSearch;
    }

    @Override // com.amazon.music.find.api.SearchApi
    public Observable<SeeMoreResponse> seeMore(final ExecutionContext executionContext, final String query, final boolean enableSpellCorrections, final SpellCorrections spellCorrections, final EntityType entityType, final String pageToken, boolean isOffline, final SearchFilterSelections searchFilterSelections, final String queryMetadata, final Integer pageSize) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        Observable<SeeMoreResponse> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.music.find.api.RemoteSearchApi$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                RemoteSearchApi.m2027seeMore$lambda1(pageSize, entityType, this, executionContext, query, enableSpellCorrections, spellCorrections, pageToken, searchFilterSelections, queryMetadata, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …)\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: seeMore$lambda-1, reason: not valid java name */
    public static final void m2027seeMore$lambda1(Integer num, EntityType entityType, RemoteSearchApi this$0, ExecutionContext executionContext, String query, boolean z, SpellCorrections spellCorrections, String str, SearchFilterSelections searchFilterSelections, String str2, ObservableEmitter observableEmitter) {
        int iIntValue;
        SeeMoreResponse seeMoreResponseExecuteSeeMoreAlbum;
        Intrinsics.checkNotNullParameter(entityType, "$entityType");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        Intrinsics.checkNotNullParameter(query, "$query");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "$searchFilterSelections");
        if (num == null) {
            iIntValue = 25;
        } else {
            try {
                iIntValue = num.intValue();
            } catch (Exception e) {
                String strStringPlus = Intrinsics.stringPlus("Something went wrong while getting Tenzing seeMore response:", e.getMessage());
                logger.warn(strStringPlus);
                observableEmitter.onError(new SearchServiceException(strStringPlus));
                return;
            }
        }
        int i = iIntValue;
        switch (WhenMappings.$EnumSwitchMapping$0[entityType.ordinal()]) {
            case 1:
                seeMoreResponseExecuteSeeMoreAlbum = this$0.executeSeeMoreAlbum(executionContext, query, z, spellCorrections, str, searchFilterSelections, str2, i);
                break;
            case 2:
                seeMoreResponseExecuteSeeMoreAlbum = this$0.executeSeeMoreArtist(executionContext, query, z, spellCorrections, str, searchFilterSelections, str2, i);
                break;
            case 3:
                seeMoreResponseExecuteSeeMoreAlbum = this$0.executeSeeMorePlaylist(executionContext, query, z, spellCorrections, str, searchFilterSelections, str2, i);
                break;
            case 4:
                seeMoreResponseExecuteSeeMoreAlbum = this$0.executeSeeMoreCommunityPlaylist(executionContext, query, spellCorrections, searchFilterSelections, str, str2, i);
                break;
            case 5:
                seeMoreResponseExecuteSeeMoreAlbum = this$0.executeSeeMorePodcastEpisode(executionContext, query, z, spellCorrections, str, searchFilterSelections, str2, i);
                break;
            case 6:
                seeMoreResponseExecuteSeeMoreAlbum = this$0.executeSeeMorePodcastShow(executionContext, query, z, spellCorrections, str, searchFilterSelections, str2, i);
                break;
            case 7:
                seeMoreResponseExecuteSeeMoreAlbum = this$0.executeSeeMoreSports(executionContext, query, z, spellCorrections, str, searchFilterSelections, str2, i);
                break;
            case 8:
                seeMoreResponseExecuteSeeMoreAlbum = this$0.executeSeeMoreStation(executionContext, query, z, spellCorrections, str, searchFilterSelections, str2, num == null ? 24 : num.intValue());
                break;
            case 9:
                seeMoreResponseExecuteSeeMoreAlbum = this$0.executeSeeMoreTrack(executionContext, query, z, spellCorrections, str, searchFilterSelections, str2, i);
                break;
            case 10:
                seeMoreResponseExecuteSeeMoreAlbum = this$0.executeSeeMoreVideo(executionContext, query, z, spellCorrections, str, searchFilterSelections, str2, i);
                break;
            case 11:
                seeMoreResponseExecuteSeeMoreAlbum = this$0.executeSeeMoreVideoPlaylist(executionContext, query, z, spellCorrections, str, searchFilterSelections, str2, i);
                break;
            default:
                seeMoreResponseExecuteSeeMoreAlbum = new SeeMoreResponse(null, null, null, 7, null);
                break;
        }
        observableEmitter.onNext(seeMoreResponseExecuteSeeMoreAlbum);
        observableEmitter.onComplete();
    }

    private final SeeMoreResponse executeSeeMoreAlbum(ExecutionContext executionContext, String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) throws SearchServiceException {
        if (!isAlbumEnabled(searchFilterSelections)) {
            return noResultsSeeMoreResponse(SectionType.ALBUMS_SECTION);
        }
        try {
            AlbumSearchRequest albumSearchRequestBuildAlbumSearchRequest = buildAlbumSearchRequest(query, enableSpellCorrections, spellCorrections, pageToken, searchFilterSelections, queryMetadata, pageSize);
            executionContext.markApiRequestStarted();
            PageResult pageResultSeeMoreSync = this.search.seeMoreSync(albumSearchRequestBuildAlbumSearchRequest);
            executionContext.markApiRequestCompleted();
            if (pageResultSeeMoreSync == null) {
                return noResultsSeeMoreResponse(SectionType.ALBUMS_SECTION);
            }
            return new SeeMoreResponse(SectionType.ALBUMS_SECTION, pageResultSeeMoreSync.getPageToken(), convertEntityPager(pageResultSeeMoreSync, new Function1<Album, AlbumSearchItem>() { // from class: com.amazon.music.find.api.RemoteSearchApi$executeSeeMoreAlbum$seeMoreSearchItems$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final AlbumSearchItem invoke(Album resultItem) {
                    Intrinsics.checkNotNullParameter(resultItem, "resultItem");
                    return this.this$0.albumFromSearchResult(resultItem);
                }
            }));
        } catch (Exception e) {
            throw new SearchServiceException(Intrinsics.stringPlus("Unhandled exception while getting Tenzing seeMore [Album] response: ", e.getMessage()));
        }
    }

    private final SeeMoreResponse executeSeeMoreArtist(ExecutionContext executionContext, String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) throws SearchServiceException {
        if (!isArtistEnabled(searchFilterSelections)) {
            return noResultsSeeMoreResponse(SectionType.ARTISTS_SECTION);
        }
        try {
            ArtistSearchRequest artistSearchRequestBuildArtistSearchRequest = buildArtistSearchRequest(query, enableSpellCorrections, spellCorrections, pageToken, searchFilterSelections, queryMetadata, pageSize);
            executionContext.markApiRequestStarted();
            PageResult pageResultSeeMoreSync = this.search.seeMoreSync(artistSearchRequestBuildArtistSearchRequest);
            executionContext.markApiRequestCompleted();
            if (pageResultSeeMoreSync == null) {
                return noResultsSeeMoreResponse(SectionType.ARTISTS_SECTION);
            }
            return new SeeMoreResponse(SectionType.ARTISTS_SECTION, pageResultSeeMoreSync.getPageToken(), convertEntityPager(pageResultSeeMoreSync, new Function1<Artist, ArtistSearchItem>() { // from class: com.amazon.music.find.api.RemoteSearchApi$executeSeeMoreArtist$seeMoreSearchItems$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final ArtistSearchItem invoke(Artist resultItem) {
                    Intrinsics.checkNotNullParameter(resultItem, "resultItem");
                    return this.this$0.artistFromSearchResult(resultItem);
                }
            }));
        } catch (Exception e) {
            throw new SearchServiceException(Intrinsics.stringPlus("Unhandled exception while getting Tenzing seeMore [Artist] response: ", e.getMessage()));
        }
    }

    private final SeeMoreResponse executeSeeMoreCommunityPlaylist(ExecutionContext executionContext, String query, SpellCorrections spellCorrections, SearchFilterSelections searchFilterSelections, String pageToken, String queryMetadata, int pageSize) throws SearchServiceException {
        if (!isCommunityPlaylistEnabled(searchFilterSelections)) {
            return noResultsSeeMoreResponse(SectionType.COMMUNITY_PLAYLIST_SECTION);
        }
        try {
            CommunityPlaylistSearchRequest communityPlaylistSearchRequestBuildCommunityPlaylistSearchRequest = buildCommunityPlaylistSearchRequest(query, spellCorrections, pageToken, queryMetadata, pageSize);
            executionContext.markApiRequestStarted();
            PageResult pageResultSeeMoreSync = this.search.seeMoreSync(communityPlaylistSearchRequestBuildCommunityPlaylistSearchRequest);
            executionContext.markApiRequestCompleted();
            if (pageResultSeeMoreSync == null) {
                return noResultsSeeMoreResponse(SectionType.COMMUNITY_PLAYLIST_SECTION);
            }
            return new SeeMoreResponse(SectionType.COMMUNITY_PLAYLIST_SECTION, pageResultSeeMoreSync.getPageToken(), convertEntityPager(pageResultSeeMoreSync, new Function1<CommunityPlaylist, PlaylistSearchItem>() { // from class: com.amazon.music.find.api.RemoteSearchApi$executeSeeMoreCommunityPlaylist$seeMoreSearchItems$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final PlaylistSearchItem invoke(CommunityPlaylist resultItem) {
                    Intrinsics.checkNotNullParameter(resultItem, "resultItem");
                    return this.this$0.playlistFromSearchResult(resultItem);
                }
            }));
        } catch (Exception e) {
            throw new SearchServiceException(Intrinsics.stringPlus("Unhandled exception while getting Tenzing seeMore [Playlist] response: ", e.getMessage()));
        }
    }

    private final SeeMoreResponse executeSeeMorePlaylist(ExecutionContext executionContext, String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) throws SearchServiceException {
        if (!isPlaylistEnabled(searchFilterSelections)) {
            return noResultsSeeMoreResponse(SectionType.PLAYLISTS_SECTION);
        }
        try {
            PlaylistSearchRequest playlistSearchRequestBuildPlaylistSearchRequest = buildPlaylistSearchRequest(query, enableSpellCorrections, spellCorrections, pageToken, searchFilterSelections, queryMetadata, pageSize);
            executionContext.markApiRequestStarted();
            PageResult pageResultSeeMoreSync = this.search.seeMoreSync(playlistSearchRequestBuildPlaylistSearchRequest);
            executionContext.markApiRequestCompleted();
            if (pageResultSeeMoreSync == null) {
                return noResultsSeeMoreResponse(SectionType.PLAYLISTS_SECTION);
            }
            return new SeeMoreResponse(SectionType.PLAYLISTS_SECTION, pageResultSeeMoreSync.getPageToken(), convertEntityPager(pageResultSeeMoreSync, new Function1<Playlist, PlaylistSearchItem>() { // from class: com.amazon.music.find.api.RemoteSearchApi$executeSeeMorePlaylist$seeMoreSearchItems$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final PlaylistSearchItem invoke(Playlist resultItem) {
                    Intrinsics.checkNotNullParameter(resultItem, "resultItem");
                    return this.this$0.playlistFromSearchResult(resultItem);
                }
            }));
        } catch (Exception e) {
            throw new SearchServiceException(Intrinsics.stringPlus("Unhandled exception while getting Tenzing seeMore [Playlist] response: ", e.getMessage()));
        }
    }

    private final SeeMoreResponse executeSeeMorePodcastEpisode(ExecutionContext executionContext, String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) throws SearchServiceException {
        if (!isPodcastEnabled(searchFilterSelections)) {
            return noResultsSeeMoreResponse(SectionType.PODCAST_EPISODE_SECTION);
        }
        try {
            PodcastEpisodeSearchRequest podcastEpisodeSearchRequestBuildPodcastEpisodeSearchRequest = buildPodcastEpisodeSearchRequest(query, enableSpellCorrections, spellCorrections, pageToken, searchFilterSelections, queryMetadata, pageSize);
            executionContext.markApiRequestStarted();
            PageResult pageResultSeeMoreSync = this.search.seeMoreSync(podcastEpisodeSearchRequestBuildPodcastEpisodeSearchRequest);
            executionContext.markApiRequestCompleted();
            if (pageResultSeeMoreSync == null) {
                return noResultsSeeMoreResponse(SectionType.PODCAST_EPISODE_SECTION);
            }
            return new SeeMoreResponse(SectionType.PODCAST_EPISODE_SECTION, pageResultSeeMoreSync.getPageToken(), convertEntityPager(pageResultSeeMoreSync, new Function1<PodcastEpisode, PodcastEpisodeSearchItem>() { // from class: com.amazon.music.find.api.RemoteSearchApi$executeSeeMorePodcastEpisode$seeMoreSearchItems$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final PodcastEpisodeSearchItem invoke(PodcastEpisode resultItem) {
                    Intrinsics.checkNotNullParameter(resultItem, "resultItem");
                    return this.this$0.podcastEpisodeFromSearchResult(resultItem);
                }
            }));
        } catch (Exception e) {
            throw new SearchServiceException(Intrinsics.stringPlus("Unhandled exception while getting Tenzing seeMore [Podcast Episode] response: ", e.getMessage()));
        }
    }

    private final SeeMoreResponse executeSeeMorePodcastShow(ExecutionContext executionContext, String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) throws SearchServiceException {
        if (!isPodcastEnabled(searchFilterSelections)) {
            return noResultsSeeMoreResponse(SectionType.PODCAST_SHOW_SECTION);
        }
        try {
            PodcastShowSearchRequest podcastShowSearchRequestBuildPodcastShowSearchRequest = buildPodcastShowSearchRequest(query, enableSpellCorrections, spellCorrections, pageToken, searchFilterSelections, queryMetadata, pageSize);
            executionContext.markApiRequestStarted();
            PageResult pageResultSeeMoreSync = this.search.seeMoreSync(podcastShowSearchRequestBuildPodcastShowSearchRequest);
            executionContext.markApiRequestCompleted();
            if (pageResultSeeMoreSync == null) {
                return noResultsSeeMoreResponse(SectionType.PODCAST_SHOW_SECTION);
            }
            return new SeeMoreResponse(SectionType.PODCAST_SHOW_SECTION, pageResultSeeMoreSync.getPageToken(), convertEntityPager(pageResultSeeMoreSync, new Function1<PodcastShow, PodcastShowSearchItem>() { // from class: com.amazon.music.find.api.RemoteSearchApi$executeSeeMorePodcastShow$seeMoreSearchItems$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final PodcastShowSearchItem invoke(PodcastShow resultItem) {
                    Intrinsics.checkNotNullParameter(resultItem, "resultItem");
                    return this.this$0.podcastShowFromSearchResult(resultItem);
                }
            }));
        } catch (Exception e) {
            throw new SearchServiceException(Intrinsics.stringPlus("Unhandled exception while getting Tenzing seeMore [Podcast Show] response: ", e.getMessage()));
        }
    }

    private final SeeMoreResponse executeSeeMoreSports(ExecutionContext executionContext, String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) throws SearchServiceException {
        if (!isSportEnabled(searchFilterSelections)) {
            return noResultsSeeMoreResponse(SectionType.SOCCER_SECTION);
        }
        try {
            SportsSearchRequest sportsSearchRequestBuildSportsSearchRequest = buildSportsSearchRequest(query, enableSpellCorrections, spellCorrections, pageToken, searchFilterSelections, queryMetadata, pageSize);
            executionContext.markApiRequestStarted();
            PageResult seeMoreResult = this.search.seeMoreSync(sportsSearchRequestBuildSportsSearchRequest);
            executionContext.markApiRequestCompleted();
            Intrinsics.checkNotNullExpressionValue(seeMoreResult, "seeMoreResult");
            return new SeeMoreResponse(SectionType.SOCCER_SECTION, seeMoreResult.getPageToken(), convertEntityPager(seeMoreResult, new Function1<SportsItem, SportsSearchItem>() { // from class: com.amazon.music.find.api.RemoteSearchApi$executeSeeMoreSports$seeMoreSearchItems$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final SportsSearchItem invoke(SportsItem resultItem) {
                    Intrinsics.checkNotNullParameter(resultItem, "resultItem");
                    return this.this$0.sportFromSearchResult(resultItem);
                }
            }));
        } catch (Exception e) {
            throw new SearchServiceException(Intrinsics.stringPlus("Unhandled exception while getting Tenzing seeMore [Sports] response: ", e.getMessage()));
        }
    }

    private final SeeMoreResponse executeSeeMoreStation(ExecutionContext executionContext, String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) throws SearchServiceException {
        if (!isStationEnabled(searchFilterSelections)) {
            return noResultsSeeMoreResponse(SectionType.STATIONS_SECTION);
        }
        try {
            StationSearchRequest stationSearchRequestBuildStationSearchRequest = buildStationSearchRequest(query, enableSpellCorrections, spellCorrections, pageToken, searchFilterSelections, queryMetadata, pageSize);
            executionContext.markApiRequestStarted();
            PageResult pageResultSeeMoreSync = this.search.seeMoreSync(stationSearchRequestBuildStationSearchRequest);
            executionContext.markApiRequestCompleted();
            if (pageResultSeeMoreSync == null) {
                return noResultsSeeMoreResponse(SectionType.STATIONS_SECTION);
            }
            return new SeeMoreResponse(SectionType.STATIONS_SECTION, pageResultSeeMoreSync.getPageToken(), convertEntityPager(pageResultSeeMoreSync, new Function1<Station, StationSearchItem>() { // from class: com.amazon.music.find.api.RemoteSearchApi$executeSeeMoreStation$seeMoreSearchItems$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final StationSearchItem invoke(Station resultItem) {
                    Intrinsics.checkNotNullParameter(resultItem, "resultItem");
                    return this.this$0.stationFromSearchResult(resultItem);
                }
            }));
        } catch (Exception e) {
            throw new SearchServiceException(Intrinsics.stringPlus("Unhandled exception while getting Tenzing seeMore [Station] response: ", e.getMessage()));
        }
    }

    private final SeeMoreResponse executeSeeMoreTrack(ExecutionContext executionContext, String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) throws SearchServiceException {
        if (!isTrackEnabled(searchFilterSelections)) {
            return noResultsSeeMoreResponse(SectionType.TRACKS_SECTION);
        }
        try {
            TrackSearchRequest trackSearchRequestBuildTrackSearchRequest = buildTrackSearchRequest(query, enableSpellCorrections, spellCorrections, pageToken, searchFilterSelections, queryMetadata, pageSize);
            executionContext.markApiRequestStarted();
            PageResult pageResultSeeMoreSync = this.search.seeMoreSync(trackSearchRequestBuildTrackSearchRequest);
            executionContext.markApiRequestCompleted();
            if (pageResultSeeMoreSync == null) {
                return noResultsSeeMoreResponse(SectionType.TRACKS_SECTION);
            }
            List<? extends TrackSearchItem> listConvertEntityPager = convertEntityPager(pageResultSeeMoreSync, new Function1<Track, TrackSearchItem>() { // from class: com.amazon.music.find.api.RemoteSearchApi$executeSeeMoreTrack$seeMoreSearchItems$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final TrackSearchItem invoke(Track resultItem) {
                    Intrinsics.checkNotNullParameter(resultItem, "resultItem");
                    return this.this$0.trackFromSearchResult(resultItem);
                }
            });
            updateOwnershipStatus(listConvertEntityPager);
            return new SeeMoreResponse(SectionType.TRACKS_SECTION, pageResultSeeMoreSync.getPageToken(), listConvertEntityPager);
        } catch (Exception e) {
            throw new SearchServiceException(Intrinsics.stringPlus("Unhandled exception while getting Tenzing seeMore [Track] response: ", e.getMessage()));
        }
    }

    private final SeeMoreResponse executeSeeMoreVideo(ExecutionContext executionContext, String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) throws SearchServiceException {
        if (!isVideoEnabled(searchFilterSelections)) {
            return noResultsSeeMoreResponse(SectionType.VIDEO_SECTION);
        }
        try {
            VideoSearchRequest videoSearchRequestBuildVideosSearchRequest = buildVideosSearchRequest(query, enableSpellCorrections, spellCorrections, pageToken, searchFilterSelections, queryMetadata, pageSize);
            executionContext.markApiRequestStarted();
            PageResult pageResultSeeMoreSync = this.search.seeMoreSync(videoSearchRequestBuildVideosSearchRequest);
            executionContext.markApiRequestCompleted();
            if (pageResultSeeMoreSync == null) {
                return noResultsSeeMoreResponse(SectionType.VIDEO_SECTION);
            }
            return new SeeMoreResponse(SectionType.VIDEO_SECTION, pageResultSeeMoreSync.getPageToken(), convertEntityPager(pageResultSeeMoreSync, new Function1<Video, TrackSearchItem>() { // from class: com.amazon.music.find.api.RemoteSearchApi$executeSeeMoreVideo$seeMoreSearchItems$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final TrackSearchItem invoke(Video resultItem) {
                    Intrinsics.checkNotNullParameter(resultItem, "resultItem");
                    return this.this$0.videoFromSearchResult(resultItem);
                }
            }));
        } catch (Exception e) {
            throw new SearchServiceException(Intrinsics.stringPlus("Unhandled exception while getting Tenzing seeMore [Video] response: ", e.getMessage()));
        }
    }

    private final SeeMoreResponse executeSeeMoreVideoPlaylist(ExecutionContext executionContext, String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) throws SearchServiceException {
        if (!isVideoPlaylistEnabled(searchFilterSelections)) {
            return noResultsSeeMoreResponse(SectionType.VIDEO_PLAYLIST_SECTION);
        }
        try {
            VideoPlaylistSearchRequest videoPlaylistSearchRequestBuildVideoPlaylistSearchRequest = buildVideoPlaylistSearchRequest(query, enableSpellCorrections, spellCorrections, pageToken, searchFilterSelections, queryMetadata, pageSize);
            executionContext.markApiRequestStarted();
            PageResult pageResultSeeMoreSync = this.search.seeMoreSync(videoPlaylistSearchRequestBuildVideoPlaylistSearchRequest);
            executionContext.markApiRequestCompleted();
            if (pageResultSeeMoreSync == null) {
                return noResultsSeeMoreResponse(SectionType.VIDEO_PLAYLIST_SECTION);
            }
            return new SeeMoreResponse(SectionType.VIDEO_PLAYLIST_SECTION, pageResultSeeMoreSync.getPageToken(), convertEntityPager(pageResultSeeMoreSync, new Function1<VideoPlaylist, PlaylistSearchItem>() { // from class: com.amazon.music.find.api.RemoteSearchApi$executeSeeMoreVideoPlaylist$seeMoreSearchItems$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final PlaylistSearchItem invoke(VideoPlaylist resultItem) {
                    Intrinsics.checkNotNullParameter(resultItem, "resultItem");
                    return this.this$0.videoPlaylistFromSearchResult(resultItem);
                }
            }));
        } catch (Exception e) {
            throw new SearchServiceException(Intrinsics.stringPlus("Unhandled exception while getting Tenzing seeMore [Video Playlist] response: ", e.getMessage()));
        }
    }

    private final SeeMoreResponse noResultsSeeMoreResponse(SectionType sectionType) {
        return new SeeMoreResponse(sectionType, null, CollectionsKt__CollectionsKt.emptyList());
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected final EverythingSearchRequest buildRequest(final ExecuteQueryRequest queryRequest) {
        Intrinsics.checkNotNullParameter(queryRequest, "queryRequest");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        List<Album.AdditionalFields> listBuildAlbumAdditionalFields = buildAlbumAdditionalFields(queryRequest.isKatana(), queryRequest.getShouldRequestAlbumPlaymodeEligibility(), queryRequest.isLanguageOfPerformanceEnabled());
        List<Playlist.AdditionalFields> listBuildPlaylistAdditionalFields = buildPlaylistAdditionalFields(queryRequest.getShouldRequestPlayListPlaymodeEligibility(), queryRequest.isLanguageOfPerformanceEnabled());
        List<CommunityPlaylist.AdditionalFields> listBuildCommunityPlaylistAdditionalFields = buildCommunityPlaylistAdditionalFields(queryRequest.getShouldRequestCommunityPlayListPlaymodeEligibility());
        List<Track.AdditionalFields> listBuildTrackAdditionalFields = buildTrackAdditionalFields(queryRequest.isKatana(), queryRequest.getShouldRequestTrackPlaymodeEligibility(), queryRequest.getShouldRequestTrackRelatedPlaylists(), queryRequest.isLanguageOfPerformanceEnabled());
        arrayList.add(PodcastShow.AdditionalFields.Image);
        arrayList.add(PodcastShow.AdditionalFields.Id);
        arrayList.add(PodcastShow.AdditionalFields.TitleGlobal);
        arrayList.add(PodcastShow.AdditionalFields.Provider);
        arrayList2.add(PodcastEpisode.AdditionalFields.Image);
        arrayList2.add(PodcastEpisode.AdditionalFields.Id);
        arrayList2.add(PodcastEpisode.AdditionalFields.TitleGlobal);
        arrayList2.add(PodcastEpisode.AdditionalFields.ShowTitleGlobal);
        AssetQualitiesProvider assetQualitiesProvider = new AssetQualitiesProvider() { // from class: com.amazon.music.find.api.RemoteSearchApi$$ExternalSyntheticLambda1
            @Override // com.amazon.music.search.AssetQualitiesProvider
            public final List getAssetQualities(ResultType resultType, String str) {
                return RemoteSearchApi.m2025buildRequest$lambda2(this.f$0, queryRequest, resultType, str);
            }
        };
        EverythingSearchRequest.Builder builder = new EverythingSearchRequest.Builder(queryRequest.getSearchTerm());
        Object[] array = listBuildAlbumAdditionalFields.toArray(new Album.AdditionalFields[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        Album.AdditionalFields[] additionalFieldsArr = (Album.AdditionalFields[]) array;
        EverythingSearchRequest.Builder builderWithAdditionalFields = builder.withAdditionalFields((Album.AdditionalFields[]) Arrays.copyOf(additionalFieldsArr, additionalFieldsArr.length)).withAdditionalFields(Artist.AdditionalFields.Image).withAdditionalFields(Competition.AdditionalFields.Image);
        Object[] array2 = listBuildPlaylistAdditionalFields.toArray(new Playlist.AdditionalFields[0]);
        if (array2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        Playlist.AdditionalFields[] additionalFieldsArr2 = (Playlist.AdditionalFields[]) array2;
        EverythingSearchRequest.Builder builderWithAdditionalFields2 = builderWithAdditionalFields.withAdditionalFields((Playlist.AdditionalFields[]) Arrays.copyOf(additionalFieldsArr2, additionalFieldsArr2.length));
        Object[] array3 = listBuildCommunityPlaylistAdditionalFields.toArray(new CommunityPlaylist.AdditionalFields[0]);
        if (array3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        CommunityPlaylist.AdditionalFields[] additionalFieldsArr3 = (CommunityPlaylist.AdditionalFields[]) array3;
        EverythingSearchRequest.Builder builderWithAdditionalFields3 = builderWithAdditionalFields2.withAdditionalFields((CommunityPlaylist.AdditionalFields[]) Arrays.copyOf(additionalFieldsArr3, additionalFieldsArr3.length)).withAdditionalFields(Program.AdditionalFields.Image).withAdditionalFields(Station.AdditionalFields.Image);
        Object[] array4 = listBuildTrackAdditionalFields.toArray(new Track.AdditionalFields[0]);
        if (array4 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        Track.AdditionalFields[] additionalFieldsArr4 = (Track.AdditionalFields[]) array4;
        EverythingSearchRequest.Builder builderWithAdditionalFields4 = builderWithAdditionalFields3.withAdditionalFields((Track.AdditionalFields[]) Arrays.copyOf(additionalFieldsArr4, additionalFieldsArr4.length)).withAdditionalFields(Video.AdditionalFields.Asin, Video.AdditionalFields.Title, Video.AdditionalFields.Image).withAdditionalFields(VideoPlaylist.AdditionalFields.Asin, VideoPlaylist.AdditionalFields.Title, VideoPlaylist.AdditionalFields.Image);
        Integer pageSize = queryRequest.getPageSize();
        TSearchRequest tsearchrequestBuild = ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) ((EverythingSearchRequest.Builder) builderWithAdditionalFields4.withPageSize(pageSize == null ? 25 : pageSize.intValue())).withSpellCorrections(queryRequest.getSpellCorrections()).setIsExplicitFilterEnabled(queryRequest.isExplicitLanguageFilterEnabled())).withExplicitLanguageFilter(queryRequest.getFilterByExplicitLanguage())).withChildFriendlyContentFilter(queryRequest.getFilterByChildFriendlyContent())).withAssetQualitiesProvider(assetQualitiesProvider).withSearchContentTier(queryRequest.getSearchContentTier())).withTracks(queryRequest.isTrackEnabled())).withAlbums(queryRequest.isAlbumEnabled())).withArtists(queryRequest.isArtistEnabled())).withPlaylists(queryRequest.isPlaylistEnabled())).withStations(queryRequest.isStationEnabled())).withSports(queryRequest.isSportEnabled())).withVideos(queryRequest.isVideoEnabled())).withVideoPlaylists(queryRequest.isVideoPlaylistEnabled())).withPodcasts(queryRequest.isPodcastEnabled())).withTopHits(queryRequest.isTopHitsEnabled())).withCommunityPlayLists(queryRequest.isCommunityPlaylistEnabled())).withLiveEvents(queryRequest.isLiveEventEnabled())).withUpsellToUnlimitedOnTopHitsTurnedOn(queryRequest.isUpsellToUnlimitedOnTopHitsTurnedOn())).withLibrary(false)).withQueryMetadata(queryRequest.getQueryMetadata())).build();
        Intrinsics.checkNotNullExpressionValue(tsearchrequestBuild, "builder.build()");
        return (EverythingSearchRequest) tsearchrequestBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: buildRequest$lambda-2, reason: not valid java name */
    public static final List m2025buildRequest$lambda2(RemoteSearchApi this$0, ExecuteQueryRequest queryRequest, ResultType resultType, String searchType) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(queryRequest, "$queryRequest");
        boolean zIsKatana = queryRequest.isKatana();
        Intrinsics.checkNotNullExpressionValue(resultType, "resultType");
        Intrinsics.checkNotNullExpressionValue(searchType, "searchType");
        return this$0.getAssetQualitiesForSearchType(zIsKatana, resultType, searchType, queryRequest.getSearchFilterSelections(), true);
    }

    private final List<Album.AdditionalFields> buildAlbumAdditionalFields(boolean isKatana, boolean shouldRequestPlaymodeEligibility, boolean isLanguageOfPerformanceEnabled) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Album.AdditionalFields.Image);
        if (isKatana) {
            arrayList.add(Album.AdditionalFields.ContentEncoding);
        }
        if (shouldRequestPlaymodeEligibility) {
            arrayList.add(Album.AdditionalFields.PlaymodeEligibilityOnDemand);
            arrayList.add(Album.AdditionalFields.PlaymodeEligibilitySFA);
            arrayList.add(Album.AdditionalFields.PlaymodeEligibilityShuffleType);
        }
        if (isLanguageOfPerformanceEnabled) {
            arrayList.add(Album.AdditionalFields.LanguageOfPerformance);
        }
        return arrayList;
    }

    private final List<Playlist.AdditionalFields> buildPlaylistAdditionalFields(boolean shouldRequestPlaymodeEligibility, boolean isLanguageOfPerformanceEnabled) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Playlist.AdditionalFields.Image);
        if (shouldRequestPlaymodeEligibility) {
            arrayList.add(Playlist.AdditionalFields.PlaymodeEligibilityOnDemand);
            arrayList.add(Playlist.AdditionalFields.PlaymodeEligibilitySFA);
            arrayList.add(Playlist.AdditionalFields.PlaymodeEligibilityShuffleType);
        }
        if (isLanguageOfPerformanceEnabled) {
            arrayList.add(Playlist.AdditionalFields.LanguageOfPerformance);
        }
        return arrayList;
    }

    private final List<CommunityPlaylist.AdditionalFields> buildCommunityPlaylistAdditionalFields(boolean shouldRequestPlaymodeEligibility) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(CommunityPlaylist.AdditionalFields.Image);
        arrayList.add(CommunityPlaylist.AdditionalFields.TrackCount);
        arrayList.add(CommunityPlaylist.AdditionalFields.Curator);
        arrayList.add(CommunityPlaylist.AdditionalFields.Title);
        arrayList.add(CommunityPlaylist.AdditionalFields.PlaylistId);
        arrayList.add(CommunityPlaylist.AdditionalFields.ContentTier);
        if (shouldRequestPlaymodeEligibility) {
            arrayList.add(CommunityPlaylist.AdditionalFields.PlaymodeEligibilityOnDemand);
            arrayList.add(CommunityPlaylist.AdditionalFields.PlaymodeEligibilitySFA);
            arrayList.add(CommunityPlaylist.AdditionalFields.PlaymodeEligibilityShuffleType);
        }
        return arrayList;
    }

    private final List<Track.AdditionalFields> buildTrackAdditionalFields(boolean isKatana, boolean shouldRequestPlaymodeEligibility, boolean shouldRequestTrackRelatedPlaylists, boolean isLanguageOfPerformanceEnabled) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Track.AdditionalFields.Image);
        if (isKatana) {
            arrayList.add(Track.AdditionalFields.ContentEncoding);
        }
        if (shouldRequestPlaymodeEligibility) {
            arrayList.add(Track.AdditionalFields.PlaymodeEligibilityOnDemand);
            arrayList.add(Track.AdditionalFields.PlaymodeEligibilitySFA);
            arrayList.add(Track.AdditionalFields.PlaymodeEligibilityShuffleType);
        }
        if (shouldRequestTrackRelatedPlaylists) {
            arrayList.add(Track.AdditionalFields.RelatedPlaylistAsin);
            arrayList.add(Track.AdditionalFields.RelatedPlaylistTitle);
            arrayList.add(Track.AdditionalFields.RelatedPlaylistArtOriginal);
            arrayList.add(Track.AdditionalFields.RelatedPlaylistEntityRelation);
            arrayList.add(Track.AdditionalFields.RelatedPlaylistPlayListId);
            arrayList.add(Track.AdditionalFields.RelatedPlaylistBlockRef);
            if (shouldRequestPlaymodeEligibility) {
                arrayList.add(Track.AdditionalFields.RelatedPlaylistPlaymodeEligibilityOnDemand);
                arrayList.add(Track.AdditionalFields.RelatedPlaylistPlaymodeEligibilitySFA);
                arrayList.add(Track.AdditionalFields.RelatedPlaylistPlaymodeEligibilityShuffleType);
            }
        }
        if (isLanguageOfPerformanceEnabled) {
            arrayList.add(Track.AdditionalFields.LanguageOfPerformance);
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final AlbumSearchRequest buildAlbumSearchRequest(String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) {
        List<Album.AdditionalFields> listBuildAlbumAdditionalFields = buildAlbumAdditionalFields(this.searchFeaturesProvider.isKatana(), this.searchFeaturesProvider.shouldRequestPlaymodeEligibility(EntityType.ALBUM), this.searchFeaturesProvider.isLanguageOfPerformanceEnabled());
        ResultType resultType = ResultType.Catalog;
        AlbumSearchRequest.Builder builderWithSpellCorrections = new AlbumSearchRequest.Builder(query, resultType).withSpellCorrections(spellCorrections);
        Object[] array = listBuildAlbumAdditionalFields.toArray(new Album.AdditionalFields[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        Album.AdditionalFields[] additionalFieldsArr = (Album.AdditionalFields[]) array;
        AlbumSearchRequest albumSearchRequestBuild = ((AlbumSearchRequest.Builder) ((AlbumSearchRequest.Builder) ((AlbumSearchRequest.Builder) ((AlbumSearchRequest.Builder) builderWithSpellCorrections.withAdditionalFields((AdditionalFields[]) Arrays.copyOf(additionalFieldsArr, additionalFieldsArr.length)).setAssetQualities(getAssetQualitiesForSearchType(this.searchFeaturesProvider.isKatana(), resultType, "album", searchFilterSelections, false))).withSearchContentTier(this.searchFeaturesProvider.getSearchContentTier())).withPageToken(pageToken).withExplicitLanguageFilter(this.searchFeaturesProvider.getFilterByExplicitLanguage()).withChildFriendlyContentFilter(this.searchFeaturesProvider.getFilterByChildFriendlyContent()).withQueryMetadata(queryMetadata)).withPageSize(pageSize)).build();
        Intrinsics.checkNotNullExpressionValue(albumSearchRequestBuild, "builder.build()");
        return albumSearchRequestBuild;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ArtistSearchRequest buildArtistSearchRequest(String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) {
        ResultType resultType = ResultType.Catalog;
        ArtistSearchRequest artistSearchRequestBuild = ((ArtistSearchRequest.Builder) ((ArtistSearchRequest.Builder) ((ArtistSearchRequest.Builder) ((ArtistSearchRequest.Builder) new ArtistSearchRequest.Builder(query, resultType).withSpellCorrections(spellCorrections).withAdditionalFields(Artist.AdditionalFields.Image).setAssetQualities(getAssetQualitiesForSearchType(this.searchFeaturesProvider.isKatana(), resultType, "artist", searchFilterSelections, false))).withSearchContentTier(this.searchFeaturesProvider.getSearchContentTier())).withPageToken(pageToken).withQueryMetadata(queryMetadata)).withPageSize(pageSize)).build();
        Intrinsics.checkNotNullExpressionValue(artistSearchRequestBuild, "builder.build()");
        return artistSearchRequestBuild;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final PlaylistSearchRequest buildPlaylistSearchRequest(String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) {
        List<Playlist.AdditionalFields> listBuildPlaylistAdditionalFields = buildPlaylistAdditionalFields(this.searchFeaturesProvider.shouldRequestPlaymodeEligibility(EntityType.PLAYLIST), this.searchFeaturesProvider.isLanguageOfPerformanceEnabled());
        ResultType resultType = ResultType.Catalog;
        PlaylistSearchRequest.Builder builderWithSpellCorrections = new PlaylistSearchRequest.Builder(query, resultType).withSpellCorrections(spellCorrections);
        Object[] array = listBuildPlaylistAdditionalFields.toArray(new Playlist.AdditionalFields[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        Playlist.AdditionalFields[] additionalFieldsArr = (Playlist.AdditionalFields[]) array;
        PlaylistSearchRequest playlistSearchRequestBuild = ((PlaylistSearchRequest.Builder) ((PlaylistSearchRequest.Builder) ((PlaylistSearchRequest.Builder) ((PlaylistSearchRequest.Builder) builderWithSpellCorrections.withAdditionalFields((AdditionalFields[]) Arrays.copyOf(additionalFieldsArr, additionalFieldsArr.length)).setAssetQualities(getAssetQualitiesForSearchType(this.searchFeaturesProvider.isKatana(), resultType, Environment.PLAYLIST_PATH, searchFilterSelections, false))).withSearchContentTier(this.searchFeaturesProvider.getSearchContentTier())).withPageToken(pageToken).withQueryMetadata(queryMetadata)).withPageSize(pageSize)).build();
        Intrinsics.checkNotNullExpressionValue(playlistSearchRequestBuild, "builder.build()");
        return playlistSearchRequestBuild;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final CommunityPlaylistSearchRequest buildCommunityPlaylistSearchRequest(String query, SpellCorrections spellCorrections, String pageToken, String queryMetadata, int pageSize) {
        List<CommunityPlaylist.AdditionalFields> listBuildCommunityPlaylistAdditionalFields = buildCommunityPlaylistAdditionalFields(this.searchFeaturesProvider.shouldRequestPlaymodeEligibility(EntityType.COMMUNITY_PLAYLIST));
        CommunityPlaylistSearchRequest.Builder builderWithSpellCorrections = new CommunityPlaylistSearchRequest.Builder(query, ResultType.Community).withSpellCorrections(spellCorrections);
        Object[] array = listBuildCommunityPlaylistAdditionalFields.toArray(new CommunityPlaylist.AdditionalFields[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        CommunityPlaylist.AdditionalFields[] additionalFieldsArr = (CommunityPlaylist.AdditionalFields[]) array;
        CommunityPlaylistSearchRequest communityPlaylistSearchRequestBuild = ((CommunityPlaylistSearchRequest.Builder) ((CommunityPlaylistSearchRequest.Builder) ((CommunityPlaylistSearchRequest.Builder) builderWithSpellCorrections.withAdditionalFields((AdditionalFields[]) Arrays.copyOf(additionalFieldsArr, additionalFieldsArr.length)).withSearchContentTier(this.searchFeaturesProvider.getSearchContentTier())).withPageToken(pageToken).withQueryMetadata(queryMetadata)).withPageSize(pageSize)).build();
        Intrinsics.checkNotNullExpressionValue(communityPlaylistSearchRequestBuild, "builder.build()");
        return communityPlaylistSearchRequestBuild;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final PodcastEpisodeSearchRequest buildPodcastEpisodeSearchRequest(String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) {
        List listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new PodcastEpisode.AdditionalFields[]{PodcastEpisode.AdditionalFields.Image, PodcastEpisode.AdditionalFields.TitleGlobal, PodcastEpisode.AdditionalFields.ShowTitleGlobal});
        PodcastEpisodeSearchRequest.Builder builderWithSpellCorrections = new PodcastEpisodeSearchRequest.Builder(query, ResultType.Podcast_Catalog).withSpellCorrections(spellCorrections);
        Object[] array = listListOf.toArray(new PodcastEpisode.AdditionalFields[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        PodcastEpisode.AdditionalFields[] additionalFieldsArr = (PodcastEpisode.AdditionalFields[]) array;
        PodcastEpisodeSearchRequest podcastEpisodeSearchRequestBuild = ((PodcastEpisodeSearchRequest.Builder) ((PodcastEpisodeSearchRequest.Builder) ((PodcastEpisodeSearchRequest.Builder) ((PodcastEpisodeSearchRequest.Builder) ((PodcastEpisodeSearchRequest.Builder) builderWithSpellCorrections.withAdditionalFields((AdditionalFields[]) Arrays.copyOf(additionalFieldsArr, additionalFieldsArr.length)).withPodcasts(true)).setAssetQualities(getAssetQualitiesForSearchType(this.searchFeaturesProvider.isKatana(), ResultType.Catalog, "episode", searchFilterSelections, false))).withSearchContentTier(this.searchFeaturesProvider.getSearchContentTier())).withPageToken(pageToken).withQueryMetadata(queryMetadata)).withPageSize(pageSize)).build();
        Intrinsics.checkNotNullExpressionValue(podcastEpisodeSearchRequestBuild, "builder.build()");
        return podcastEpisodeSearchRequestBuild;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final PodcastShowSearchRequest buildPodcastShowSearchRequest(String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) {
        List listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new PodcastShow.AdditionalFields[]{PodcastShow.AdditionalFields.Image, PodcastShow.AdditionalFields.TitleGlobal, PodcastShow.AdditionalFields.Provider});
        PodcastShowSearchRequest.Builder builderWithSpellCorrections = new PodcastShowSearchRequest.Builder(query, ResultType.Podcast_Catalog).withSpellCorrections(spellCorrections);
        Object[] array = listListOf.toArray(new PodcastShow.AdditionalFields[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        PodcastShow.AdditionalFields[] additionalFieldsArr = (PodcastShow.AdditionalFields[]) array;
        PodcastShowSearchRequest podcastShowSearchRequestBuild = ((PodcastShowSearchRequest.Builder) ((PodcastShowSearchRequest.Builder) ((PodcastShowSearchRequest.Builder) ((PodcastShowSearchRequest.Builder) ((PodcastShowSearchRequest.Builder) builderWithSpellCorrections.withAdditionalFields((AdditionalFields[]) Arrays.copyOf(additionalFieldsArr, additionalFieldsArr.length)).withPodcasts(true)).setAssetQualities(getAssetQualitiesForSearchType(this.searchFeaturesProvider.isKatana(), ResultType.Catalog, "show", searchFilterSelections, false))).withSearchContentTier(this.searchFeaturesProvider.getSearchContentTier())).withPageToken(pageToken).withQueryMetadata(queryMetadata)).withPageSize(pageSize)).build();
        Intrinsics.checkNotNullExpressionValue(podcastShowSearchRequestBuild, "builder.build()");
        return podcastShowSearchRequestBuild;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final SportsSearchRequest buildSportsSearchRequest(String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) {
        SportsSearchRequest sportsSearchRequestBuild = ((SportsSearchRequest.Builder) ((SportsSearchRequest.Builder) ((SportsSearchRequest.Builder) ((SportsSearchRequest.Builder) new SportsSearchRequest.Builder(query).withSpellCorrections(spellCorrections).withAdditionalFields(Program.AdditionalFields.Image).withAdditionalFields(Competition.AdditionalFields.Image).setAssetQualities(getAssetQualitiesForSearchType(this.searchFeaturesProvider.isKatana(), ResultType.Catalog, "sports", searchFilterSelections, false))).withSearchContentTier(this.searchFeaturesProvider.getSearchContentTier())).withPageToken(pageToken).withQueryMetadata(queryMetadata)).withPageSize(pageSize)).build();
        Intrinsics.checkNotNullExpressionValue(sportsSearchRequestBuild, "builder.build()");
        return sportsSearchRequestBuild;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final StationSearchRequest buildStationSearchRequest(String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) {
        StationSearchRequest stationSearchRequestBuild = ((StationSearchRequest.Builder) ((StationSearchRequest.Builder) ((StationSearchRequest.Builder) ((StationSearchRequest.Builder) new StationSearchRequest.Builder(query).withSpellCorrections(spellCorrections).withAdditionalFields(Station.AdditionalFields.Image).setAssetQualities(getAssetQualitiesForSearchType(this.searchFeaturesProvider.isKatana(), ResultType.Catalog, "station", searchFilterSelections, false))).withSearchContentTier(this.searchFeaturesProvider.getSearchContentTier())).withPageToken(pageToken).withQueryMetadata(queryMetadata)).withPageSize(pageSize)).build();
        Intrinsics.checkNotNullExpressionValue(stationSearchRequestBuild, "builder.build()");
        return stationSearchRequestBuild;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final TrackSearchRequest buildTrackSearchRequest(String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) {
        List<Track.AdditionalFields> listBuildTrackAdditionalFields = buildTrackAdditionalFields(this.searchFeaturesProvider.isKatana(), this.searchFeaturesProvider.shouldRequestPlaymodeEligibility(EntityType.TRACK), this.searchFeaturesProvider.getShouldRequestRelatedEntities(), this.searchFeaturesProvider.isLanguageOfPerformanceEnabled());
        ResultType resultType = ResultType.Catalog;
        TrackSearchRequest.Builder builderWithSpellCorrections = new TrackSearchRequest.Builder(query, resultType).withSpellCorrections(spellCorrections);
        Object[] array = listBuildTrackAdditionalFields.toArray(new Track.AdditionalFields[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        Track.AdditionalFields[] additionalFieldsArr = (Track.AdditionalFields[]) array;
        TrackSearchRequest trackSearchRequestBuild = ((TrackSearchRequest.Builder) ((TrackSearchRequest.Builder) ((TrackSearchRequest.Builder) ((TrackSearchRequest.Builder) builderWithSpellCorrections.withAdditionalFields((AdditionalFields[]) Arrays.copyOf(additionalFieldsArr, additionalFieldsArr.length)).setAssetQualities(getAssetQualitiesForSearchType(this.searchFeaturesProvider.isKatana(), resultType, "track", searchFilterSelections, false))).withSearchContentTier(this.searchFeaturesProvider.getSearchContentTier())).withPageToken(pageToken).withExplicitLanguageFilter(this.searchFeaturesProvider.getFilterByExplicitLanguage()).withChildFriendlyContentFilter(this.searchFeaturesProvider.getFilterByChildFriendlyContent()).withQueryMetadata(queryMetadata)).withPageSize(pageSize)).build();
        Intrinsics.checkNotNullExpressionValue(trackSearchRequestBuild, "builder.build()");
        return trackSearchRequestBuild;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final VideoSearchRequest buildVideosSearchRequest(String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) {
        List listListOf = CollectionsKt__CollectionsJVMKt.listOf(Video.AdditionalFields.Image);
        ResultType resultType = ResultType.Catalog;
        VideoSearchRequest.Builder builderWithSpellCorrections = new VideoSearchRequest.Builder(query, resultType).withSpellCorrections(spellCorrections);
        Object[] array = listListOf.toArray(new Video.AdditionalFields[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        Video.AdditionalFields[] additionalFieldsArr = (Video.AdditionalFields[]) array;
        VideoSearchRequest videoSearchRequestBuild = ((VideoSearchRequest.Builder) ((VideoSearchRequest.Builder) ((VideoSearchRequest.Builder) ((VideoSearchRequest.Builder) builderWithSpellCorrections.withAdditionalFields((AdditionalFields[]) Arrays.copyOf(additionalFieldsArr, additionalFieldsArr.length)).setAssetQualities(getAssetQualitiesForSearchType(this.searchFeaturesProvider.isKatana(), resultType, voMimeTypes.VOBASE_TYPE_VIDEO, searchFilterSelections, false))).withSearchContentTier(this.searchFeaturesProvider.getSearchContentTier())).withPageToken(pageToken).withQueryMetadata(queryMetadata)).withPageSize(pageSize)).build();
        Intrinsics.checkNotNullExpressionValue(videoSearchRequestBuild, "builder.build()");
        return videoSearchRequestBuild;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final VideoPlaylistSearchRequest buildVideoPlaylistSearchRequest(String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, String pageToken, SearchFilterSelections searchFilterSelections, String queryMetadata, int pageSize) {
        ResultType resultType = ResultType.Catalog;
        VideoPlaylistSearchRequest videoPlaylistSearchRequestBuild = ((VideoPlaylistSearchRequest.Builder) ((VideoPlaylistSearchRequest.Builder) ((VideoPlaylistSearchRequest.Builder) ((VideoPlaylistSearchRequest.Builder) new VideoPlaylistSearchRequest.Builder(query, resultType).withSpellCorrections(spellCorrections).withAdditionalFields(VideoPlaylist.AdditionalFields.Image).setAssetQualities(getAssetQualitiesForSearchType(this.searchFeaturesProvider.isKatana(), resultType, "video_playlist", searchFilterSelections, false))).withSearchContentTier(this.searchFeaturesProvider.getSearchContentTier())).withPageToken(pageToken).withQueryMetadata(queryMetadata)).withPageSize(pageSize)).build();
        Intrinsics.checkNotNullExpressionValue(videoPlaylistSearchRequestBuild, "builder.build()");
        return videoPlaylistSearchRequestBuild;
    }

    private final GenericSearchResponse convertSearchResult(SearchResult searchResult, String searchTerm, SearchFilterSelections searchFilterSelections) {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        ArrayList arrayList4;
        ArrayList arrayList5;
        Iterator it;
        ArrayList arrayList6;
        Object objValueOf;
        Collection<Station> page;
        Collection<Track> page2;
        Collection<Video> page3;
        ArrayList arrayList7;
        Collection<VideoPlaylist> page4;
        PageResult<SportsItem> sports;
        Collection<SportsItem> page5;
        Collection<PodcastShow> page6;
        Collection<PodcastEpisode> page7;
        Collection<CommunityPlaylist> page8;
        Collection<Playlist> page9;
        Collection<Artist> page10;
        Collection<Album> page11;
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
        ArrayList arrayList10 = new ArrayList();
        ArrayList arrayList11 = new ArrayList();
        ArrayList arrayList12 = new ArrayList();
        ArrayList arrayList13 = new ArrayList();
        ArrayList arrayList14 = new ArrayList();
        ArrayList arrayList15 = new ArrayList();
        ArrayList arrayList16 = new ArrayList();
        ArrayList arrayList17 = new ArrayList();
        ArrayList arrayList18 = new ArrayList();
        ArrayList arrayList19 = new ArrayList();
        boolean zIsBundesligaAccessible = this.searchFeaturesProvider.isBundesligaAccessible();
        PageResult<ResultItem> topHits = searchResult.getTopHits();
        String str = "it";
        if (topHits == null) {
            arrayList3 = arrayList14;
            arrayList5 = arrayList15;
            arrayList4 = arrayList16;
            arrayList2 = arrayList17;
            arrayList = arrayList18;
        } else {
            arrayList = arrayList18;
            Collection page12 = topHits.getPage();
            if (page12 == null) {
                arrayList3 = arrayList14;
                arrayList5 = arrayList15;
                arrayList4 = arrayList16;
                arrayList2 = arrayList17;
            } else {
                arrayList2 = arrayList17;
                arrayList3 = arrayList14;
                arrayList4 = arrayList16;
                ArrayList arrayList20 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(page12, 10));
                Iterator it2 = page12.iterator();
                while (it2.hasNext()) {
                    ResultItem it3 = (ResultItem) it2.next();
                    if (it3 instanceof Album) {
                        Intrinsics.checkNotNullExpressionValue(it3, "it");
                        objValueOf = Boolean.valueOf(arrayList8.add(albumFromSearchResult((Album) it3)));
                    } else if (it3 instanceof Artist) {
                        Intrinsics.checkNotNullExpressionValue(it3, "it");
                        objValueOf = Boolean.valueOf(arrayList8.add(artistFromSearchResult((Artist) it3)));
                    } else if (it3 instanceof Playlist) {
                        Intrinsics.checkNotNullExpressionValue(it3, "it");
                        objValueOf = Boolean.valueOf(arrayList8.add(playlistFromSearchResult((Playlist) it3)));
                    } else if (it3 instanceof PodcastEpisode) {
                        Intrinsics.checkNotNullExpressionValue(it3, "it");
                        objValueOf = Boolean.valueOf(arrayList8.add(podcastEpisodeFromSearchResult((PodcastEpisode) it3)));
                    } else if (it3 instanceof PodcastShow) {
                        Intrinsics.checkNotNullExpressionValue(it3, "it");
                        objValueOf = Boolean.valueOf(arrayList8.add(podcastShowFromSearchResult((PodcastShow) it3)));
                    } else if (it3 instanceof SportsItem) {
                        Intrinsics.checkNotNullExpressionValue(it3, "it");
                        objValueOf = Boolean.valueOf(arrayList8.add(sportFromSearchResult((SportsItem) it3)));
                    } else if (it3 instanceof Station) {
                        Intrinsics.checkNotNullExpressionValue(it3, "it");
                        objValueOf = Boolean.valueOf(arrayList8.add(stationFromSearchResult((Station) it3)));
                    } else if (it3 instanceof Track) {
                        Intrinsics.checkNotNullExpressionValue(it3, "it");
                        objValueOf = Boolean.valueOf(arrayList8.add(trackFromSearchResult((Track) it3)));
                    } else if (it3 instanceof Video) {
                        Intrinsics.checkNotNullExpressionValue(it3, "it");
                        objValueOf = Boolean.valueOf(arrayList8.add(videoFromSearchResult((Video) it3)));
                    } else if (it3 instanceof VideoPlaylist) {
                        Intrinsics.checkNotNullExpressionValue(it3, "it");
                        objValueOf = Boolean.valueOf(arrayList8.add(videoPlaylistFromSearchResult((VideoPlaylist) it3)));
                    } else if (it3 instanceof LiveEvent) {
                        Intrinsics.checkNotNullExpressionValue(it3, "it");
                        objValueOf = Boolean.valueOf(arrayList8.add(liveEventFromSearchResult((LiveEvent) it3)));
                    } else {
                        Logger logger2 = logger;
                        it = it2;
                        StringBuilder sb = new StringBuilder();
                        arrayList6 = arrayList15;
                        sb.append("will not add unknown entity ");
                        sb.append(it3);
                        sb.append(" to top hits");
                        logger2.warn(sb.toString());
                        objValueOf = Unit.INSTANCE;
                        arrayList20.add(objValueOf);
                        it2 = it;
                        arrayList15 = arrayList6;
                    }
                    it = it2;
                    arrayList6 = arrayList15;
                    arrayList20.add(objValueOf);
                    it2 = it;
                    arrayList15 = arrayList6;
                }
                arrayList5 = arrayList15;
            }
        }
        ArrayList arrayList21 = new ArrayList();
        for (Object obj : arrayList8) {
            if (obj instanceof TrackSearchItem) {
                arrayList21.add(obj);
            }
        }
        updateOwnershipStatus(arrayList21);
        PageResult<Album> albums = searchResult.getAlbums(ResultType.Catalog);
        if (albums != null && (page11 = albums.getPage()) != null) {
            ArrayList arrayList22 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(page11, 10));
            for (Album it4 : page11) {
                Intrinsics.checkNotNullExpressionValue(it4, "it");
                arrayList22.add(Boolean.valueOf(arrayList9.add(albumFromSearchResult(it4))));
            }
        }
        PageResult<Artist> artists = searchResult.getArtists(ResultType.Catalog);
        if (artists != null && (page10 = artists.getPage()) != null) {
            ArrayList arrayList23 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(page10, 10));
            for (Artist it5 : page10) {
                Intrinsics.checkNotNullExpressionValue(it5, "it");
                arrayList23.add(Boolean.valueOf(arrayList10.add(artistFromSearchResult(it5))));
            }
        }
        PageResult<Playlist> playlists = searchResult.getPlaylists(ResultType.Catalog);
        if (playlists != null && (page9 = playlists.getPage()) != null) {
            ArrayList arrayList24 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(page9, 10));
            for (Playlist it6 : page9) {
                Intrinsics.checkNotNullExpressionValue(it6, "it");
                arrayList24.add(Boolean.valueOf(arrayList11.add(playlistFromSearchResult(it6))));
            }
        }
        PageResult<CommunityPlaylist> communityPlaylists = searchResult.getCommunityPlaylists(ResultType.Community);
        if (communityPlaylists != null && (page8 = communityPlaylists.getPage()) != null) {
            ArrayList arrayList25 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(page8, 10));
            for (CommunityPlaylist it7 : page8) {
                Intrinsics.checkNotNullExpressionValue(it7, "it");
                arrayList25.add(Boolean.valueOf(arrayList19.add(playlistFromSearchResult(it7))));
            }
        }
        PageResult<PodcastEpisode> podcastEpisodes = searchResult.getPodcastEpisodes(ResultType.Podcast_Catalog);
        if (podcastEpisodes != null && (page7 = podcastEpisodes.getPage()) != null) {
            ArrayList arrayList26 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(page7, 10));
            for (PodcastEpisode it8 : page7) {
                Intrinsics.checkNotNullExpressionValue(it8, "it");
                arrayList26.add(Boolean.valueOf(arrayList12.add(podcastEpisodeFromSearchResult(it8))));
            }
        }
        PageResult<PodcastShow> podcastShows = searchResult.getPodcastShows(ResultType.Podcast_Catalog);
        if (podcastShows != null && (page6 = podcastShows.getPage()) != null) {
            ArrayList arrayList27 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(page6, 10));
            for (PodcastShow it9 : page6) {
                Intrinsics.checkNotNullExpressionValue(it9, "it");
                arrayList27.add(Boolean.valueOf(arrayList13.add(podcastShowFromSearchResult(it9))));
            }
        }
        if (zIsBundesligaAccessible && (sports = searchResult.getSports()) != null && (page5 = sports.getPage()) != null) {
            ArrayList arrayList28 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(page5, 10));
            for (SportsItem it10 : page5) {
                Intrinsics.checkNotNullExpressionValue(it10, "it");
                arrayList28.add(Boolean.valueOf(arrayList5.add(sportFromSearchResult(it10))));
            }
        }
        ArrayList arrayList29 = arrayList5;
        PageResult<Station> stations = searchResult.getStations();
        if (stations != null && (page = stations.getPage()) != null) {
            ArrayList arrayList30 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(page, 10));
            for (Station it11 : page) {
                Intrinsics.checkNotNullExpressionValue(it11, "it");
                arrayList30.add(Boolean.valueOf(arrayList3.add(stationFromSearchResult(it11))));
            }
        }
        ArrayList arrayList31 = arrayList3;
        PageResult<Track> tracks = searchResult.getTracks(ResultType.Catalog);
        if (tracks != null && (page2 = tracks.getPage()) != null) {
            ArrayList arrayList32 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(page2, 10));
            for (Track it12 : page2) {
                Intrinsics.checkNotNullExpressionValue(it12, "it");
                arrayList32.add(Boolean.valueOf(arrayList4.add(trackFromSearchResult(it12))));
            }
        }
        ArrayList arrayList33 = arrayList4;
        updateOwnershipStatus(arrayList33);
        PageResult<Video> videos = searchResult.getVideos(ResultType.Catalog);
        if (videos == null || (page3 = videos.getPage()) == null) {
            arrayList7 = arrayList19;
        } else {
            arrayList7 = arrayList19;
            ArrayList arrayList34 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(page3, 10));
            for (Video it13 : page3) {
                Intrinsics.checkNotNullExpressionValue(it13, "it");
                arrayList34.add(Boolean.valueOf(arrayList2.add(videoFromSearchResult(it13))));
            }
        }
        ArrayList arrayList35 = arrayList2;
        PageResult<VideoPlaylist> videoPlaylists = searchResult.getVideoPlaylists(ResultType.Catalog);
        if (videoPlaylists != null && (page4 = videoPlaylists.getPage()) != null) {
            ArrayList arrayList36 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(page4, 10));
            for (VideoPlaylist videoPlaylist : page4) {
                Intrinsics.checkNotNullExpressionValue(videoPlaylist, str);
                arrayList36.add(Boolean.valueOf(arrayList.add(videoPlaylistFromSearchResult(videoPlaylist))));
                str = str;
            }
        }
        ArrayList arrayList37 = arrayList;
        GenericSearchResponse genericSearchResponse = new GenericSearchResponse();
        genericSearchResponse.setOriginalQuery(searchTerm);
        genericSearchResponse.setFilterMetadata(UIConfigurationService.DefaultImpls.getFilterMetadata$default(this.uiConfigurationService, false, 1, null));
        genericSearchResponse.setSectionOrdering(searchResult.getCatalogSectionOrder());
        SpellCorrections spellCorrections = searchResult.getSpellCorrections(searchTerm);
        genericSearchResponse.setSpellCorrections(spellCorrections);
        genericSearchResponse.setExecutedTerm(SpellCorrectionsUtil.INSTANCE.getExecutedTerm(searchTerm, spellCorrections));
        genericSearchResponse.setTopHitItems(CollectionsKt___CollectionsKt.toList(arrayList8));
        genericSearchResponse.setAlbums(CollectionsKt___CollectionsKt.toList(arrayList9));
        genericSearchResponse.setArtists(CollectionsKt___CollectionsKt.toList(arrayList10));
        genericSearchResponse.setPlaylists(CollectionsKt___CollectionsKt.toList(arrayList11));
        genericSearchResponse.setPodcastEpisodes(CollectionsKt___CollectionsKt.toList(arrayList12));
        genericSearchResponse.setPodcastShows(CollectionsKt___CollectionsKt.toList(arrayList13));
        genericSearchResponse.setSportsItems(CollectionsKt___CollectionsKt.toList(arrayList29));
        genericSearchResponse.setStations(CollectionsKt___CollectionsKt.toList(arrayList31));
        genericSearchResponse.setTracks(CollectionsKt___CollectionsKt.toList(arrayList33));
        genericSearchResponse.setVideos(CollectionsKt___CollectionsKt.toList(arrayList35));
        genericSearchResponse.setVideoPlaylists(CollectionsKt___CollectionsKt.toList(arrayList37));
        genericSearchResponse.setCommunityPlaylists(CollectionsKt___CollectionsKt.toList(arrayList7));
        genericSearchResponse.setTopHitsBlockRef(searchResult.getTopHitsContainerBlockRef());
        ResultType resultType = ResultType.Catalog;
        genericSearchResponse.setAlbumsBlockRef(searchResult.getAlbumContainerBlockRef(resultType));
        genericSearchResponse.setArtistsBlockRef(searchResult.getArtistContainerBlockRef(resultType));
        genericSearchResponse.setPlaylistsBlockRef(searchResult.getPlaylistContainerBlockRef(resultType));
        ResultType resultType2 = ResultType.Podcast_Catalog;
        genericSearchResponse.setPodcastEpisodesBlockRef(searchResult.getPodcastEpisodesContainerBlockRef(resultType2));
        genericSearchResponse.setPodcastShowsBlockRef(searchResult.getPodcastShowsContainerBlockRef(resultType2));
        genericSearchResponse.setSportsBlockRef(searchResult.getSportsContainerBlockRef());
        genericSearchResponse.setStationsBlockRef(searchResult.getStationContainerBlockRef());
        genericSearchResponse.setTracksBlockRef(searchResult.getTrackContainerBlockRef(resultType));
        genericSearchResponse.setVideosBlockRef(searchResult.getVideosContainerBlockRef(resultType));
        genericSearchResponse.setVideoPlaylistsBlockRef(searchResult.getVideoPlaylistsContainerBlockRef(resultType));
        genericSearchResponse.setCommunityPlaylistsBlockRef(searchResult.getCommunityPlaylistContainerBlockRef(ResultType.Community));
        return genericSearchResponse;
    }

    private final void updateOwnershipStatus(List<? extends TrackSearchItem> tracks) {
        Integer num;
        LibrarySearchDao librarySearchDao = this.librarySearchDao;
        List<Long> listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = tracks.iterator();
        while (it.hasNext()) {
            String asin = ((TrackSearchItem) it.next()).getAsin();
            if (asin != null) {
                arrayList.add(asin);
            }
        }
        Map<String, Integer> mapMapContentOwnershipStatus = mapContentOwnershipStatus(librarySearchDao.requestState(listEmptyList, arrayList, false, null));
        for (TrackSearchItem trackSearchItem : tracks) {
            if (mapMapContentOwnershipStatus.containsKey(trackSearchItem.getAsin()) && (num = mapMapContentOwnershipStatus.get(trackSearchItem.getAsin())) != null) {
                trackSearchItem.setOwnershipStatus(num.intValue());
            }
        }
    }

    private final Map<String, Integer> mapContentOwnershipStatus(List<ContentOwnershipItem> contentOwnershipItems) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (ContentOwnershipItem contentOwnershipItem : contentOwnershipItems) {
            if (contentOwnershipItem.getAsin() != null && contentOwnershipItem.getOwnershipStatus() != null) {
                linkedHashMap.put(contentOwnershipItem.getAsin(), contentOwnershipItem.getOwnershipStatus());
            }
        }
        return linkedHashMap;
    }

    private final <U extends ResultItem, V extends SearchItem> List<V> convertEntityPager(PageResult<U> pageResult, Function1<? super U, ? extends V> transform) throws SearchServiceException {
        ArrayList arrayList = new ArrayList();
        if (pageResult != null) {
            try {
                Collection<ResultItem> page = pageResult.getPage();
                if (page != null) {
                    ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(page, 10));
                    for (ResultItem it : page) {
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        arrayList2.add(Boolean.valueOf(arrayList.add(transform.invoke(it))));
                    }
                }
            } catch (Exception e) {
                throw new SearchServiceException(Intrinsics.stringPlus("Unhandled exception while iterating through Pager: ", e.getMessage()));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AlbumSearchItem albumFromSearchResult(Album album) {
        AlbumSearchItem albumSearchItemCreateAlbumSearchItem = this.searchEntityFactory.createAlbumSearchItem();
        albumSearchItemCreateAlbumSearchItem.setTitle(album.getTitle());
        albumSearchItemCreateAlbumSearchItem.setAsin(album.getAsin());
        albumSearchItemCreateAlbumSearchItem.setArtworkUri(album.getImage());
        albumSearchItemCreateAlbumSearchItem.setArtist(album.getArtistName());
        albumSearchItemCreateAlbumSearchItem.setArtistAsin(album.getArtistAsin());
        albumSearchItemCreateAlbumSearchItem.setBlockRef(album.getBlockRef());
        albumSearchItemCreateAlbumSearchItem.setContentInfoMap(album.getContentInfo());
        albumSearchItemCreateAlbumSearchItem.setContentEncoding(album.getContentEncoding());
        albumSearchItemCreateAlbumSearchItem.setContentAccessSet(album.getContentAccess());
        albumSearchItemCreateAlbumSearchItem.setPlaymodeEligibility(convertPlaymodeEligibility(album.getPlaymodeEligibility(), this.searchFeaturesProvider.shouldRequestPlaymodeEligibility(EntityType.ALBUM), PLAYMODE_ELIGIBILITY_SHUFFLE_MIXED));
        albumSearchItemCreateAlbumSearchItem.setLanguageOfPerformances(album.getLanguageOfPerformances());
        Set<ContentAccessType> contentAccess = album.getContentAccess();
        if (contentAccess != null) {
            albumSearchItemCreateAlbumSearchItem.setCatalogStatus(contentAccess.contains(ContentAccessType.PRIME), contentAccess.contains(ContentAccessType.HAWKFIRE));
        }
        return albumSearchItemCreateAlbumSearchItem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ArtistSearchItem artistFromSearchResult(Artist artist) {
        ArtistSearchItem artistSearchItemCreateArtistSearchItem = this.searchEntityFactory.createArtistSearchItem();
        artistSearchItemCreateArtistSearchItem.setTitle(artist.getName());
        artistSearchItemCreateArtistSearchItem.setAsin(artist.getAsin());
        artistSearchItemCreateArtistSearchItem.setContributorAsin(artist.getAsin());
        artistSearchItemCreateArtistSearchItem.setTrackCount(0);
        artistSearchItemCreateArtistSearchItem.setAlbumCount(0);
        artistSearchItemCreateArtistSearchItem.setArtworkUri(artist.getImage());
        artistSearchItemCreateArtistSearchItem.setBlockRef(artist.getBlockRef());
        artistSearchItemCreateArtistSearchItem.setContentInfoMap(artist.getContentInfo());
        artistSearchItemCreateArtistSearchItem.setContentAccessSet(artist.getContentAccess());
        Set<ContentAccessType> contentAccess = artist.getContentAccess();
        if (contentAccess != null) {
            artistSearchItemCreateArtistSearchItem.setCatalogStatus(contentAccess.contains(ContentAccessType.PRIME));
        }
        artistSearchItemCreateArtistSearchItem.setOwnershipStatus(SearchContentOwnershipStatus.ADDED);
        return artistSearchItemCreateArtistSearchItem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PlaylistSearchItem playlistFromSearchResult(CommunityPlaylist playlist) {
        PlaylistSearchItem playlistSearchItemCreatePlaylistSearchItem = this.searchEntityFactory.createPlaylistSearchItem();
        playlistSearchItemCreatePlaylistSearchItem.setTitle(playlist.getTitle());
        playlistSearchItemCreatePlaylistSearchItem.setPlaylistId(playlist.getPlaylistId());
        playlistSearchItemCreatePlaylistSearchItem.setArtworkUri(playlist.getImage());
        playlistSearchItemCreatePlaylistSearchItem.setDescription("");
        playlistSearchItemCreatePlaylistSearchItem.setAuthors(CollectionsKt__CollectionsKt.emptyList());
        playlistSearchItemCreatePlaylistSearchItem.setTrackCount(playlist.getTrackCount());
        playlistSearchItemCreatePlaylistSearchItem.setCurator(playlist.getCurator());
        playlistSearchItemCreatePlaylistSearchItem.setBlockRef(playlist.getBlockRef());
        playlistSearchItemCreatePlaylistSearchItem.setContentInfoMap(playlist.getContentInfo());
        playlistSearchItemCreatePlaylistSearchItem.setContentAccessSet(playlist.getContentAccess());
        playlistSearchItemCreatePlaylistSearchItem.setPlaylistType(PlaylistType.COMMUNITY_PLAYLIST);
        playlistSearchItemCreatePlaylistSearchItem.setPlaymodeEligibility(convertPlaymodeEligibility(playlist.getPlaymodeEligibility(), this.searchFeaturesProvider.shouldRequestPlaymodeEligibility(EntityType.COMMUNITY_PLAYLIST), PLAYMODE_ELIGIBILITY_SHUFFLE_MIXED));
        Set<ContentAccessType> contentAccess = playlist.getContentAccess();
        if (contentAccess != null) {
            playlistSearchItemCreatePlaylistSearchItem.setCatalogStatus(contentAccess.contains(ContentAccessType.PRIME), contentAccess.contains(ContentAccessType.SONIC_RUSH), contentAccess.contains(ContentAccessType.HAWKFIRE), contentAccess.contains(ContentAccessType.NIGHTWING), contentAccess.contains(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE));
        }
        return playlistSearchItemCreatePlaylistSearchItem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PlaylistSearchItem playlistFromSearchResult(Playlist playlist) {
        PlaylistSearchItem playlistSearchItemCreatePlaylistSearchItem = this.searchEntityFactory.createPlaylistSearchItem();
        playlistSearchItemCreatePlaylistSearchItem.setTitle(playlist.getTitle());
        playlistSearchItemCreatePlaylistSearchItem.setAsin(playlist.getAsin());
        playlistSearchItemCreatePlaylistSearchItem.setArtworkUri(playlist.getImage());
        playlistSearchItemCreatePlaylistSearchItem.setDescription("");
        playlistSearchItemCreatePlaylistSearchItem.setRating(0.0f);
        playlistSearchItemCreatePlaylistSearchItem.setNumReviews(0);
        playlistSearchItemCreatePlaylistSearchItem.setAuthors(CollectionsKt__CollectionsKt.emptyList());
        playlistSearchItemCreatePlaylistSearchItem.setBlockRef(playlist.getBlockRef());
        playlistSearchItemCreatePlaylistSearchItem.setContentInfoMap(playlist.getContentInfo());
        playlistSearchItemCreatePlaylistSearchItem.setContentAccessSet(playlist.getContentAccess());
        playlistSearchItemCreatePlaylistSearchItem.setPlaylistType(PlaylistType.PRIME_PLAYLIST);
        playlistSearchItemCreatePlaylistSearchItem.setPlaymodeEligibility(convertPlaymodeEligibility(playlist.getPlaymodeEligibility(), this.searchFeaturesProvider.shouldRequestPlaymodeEligibility(EntityType.PLAYLIST), PLAYMODE_ELIGIBILITY_SHUFFLE_MIXED));
        playlistSearchItemCreatePlaylistSearchItem.setLanguageOfPerformances(playlist.getLanguageOfPerformances());
        Set<ContentAccessType> contentAccess = playlist.getContentAccess();
        if (contentAccess != null) {
            playlistSearchItemCreatePlaylistSearchItem.setCatalogStatus(contentAccess.contains(ContentAccessType.PRIME), contentAccess.contains(ContentAccessType.SONIC_RUSH), contentAccess.contains(ContentAccessType.HAWKFIRE), contentAccess.contains(ContentAccessType.NIGHTWING), contentAccess.contains(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE));
        }
        return playlistSearchItemCreatePlaylistSearchItem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PodcastShowSearchItem podcastShowFromSearchResult(PodcastShow podcastShow) {
        PodcastShowSearchItem podcastShowSearchItemCreatePodcastShowSearchItem = this.searchEntityFactory.createPodcastShowSearchItem();
        podcastShowSearchItemCreatePodcastShowSearchItem.setPodcastShowId(podcastShow.getId());
        podcastShowSearchItemCreatePodcastShowSearchItem.setTitle(podcastShow.getTitle());
        podcastShowSearchItemCreatePodcastShowSearchItem.setSubtitle(podcastShow.getSubtitle());
        podcastShowSearchItemCreatePodcastShowSearchItem.setContentInfoMap(podcastShow.getContentInfo());
        podcastShowSearchItemCreatePodcastShowSearchItem.setArtworkUri(podcastShow.getImage());
        podcastShowSearchItemCreatePodcastShowSearchItem.setBlockRef(podcastShow.getBlockRef());
        podcastShowSearchItemCreatePodcastShowSearchItem.setExplicit(podcastShow.isExplicit());
        podcastShowSearchItemCreatePodcastShowSearchItem.setContentAccessSet(podcastShow.getContentAccess());
        podcastShowSearchItemCreatePodcastShowSearchItem.setVariantId(podcastShow.getVariantId());
        podcastShowSearchItemCreatePodcastShowSearchItem.setTierBenefits(podcastShow.getTierBenefits());
        return podcastShowSearchItemCreatePodcastShowSearchItem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PodcastEpisodeSearchItem podcastEpisodeFromSearchResult(PodcastEpisode podcastEpisode) {
        PodcastEpisodeSearchItem podcastEpisodeSearchItemCreatePodcastEpisodeSearchItem = this.searchEntityFactory.createPodcastEpisodeSearchItem();
        podcastEpisodeSearchItemCreatePodcastEpisodeSearchItem.setPodcastEpisodeId(podcastEpisode.getId());
        podcastEpisodeSearchItemCreatePodcastEpisodeSearchItem.setPodcastShowId(podcastEpisode.getPodcastId());
        podcastEpisodeSearchItemCreatePodcastEpisodeSearchItem.setTitle(podcastEpisode.getTitle());
        podcastEpisodeSearchItemCreatePodcastEpisodeSearchItem.setSubtitle(podcastEpisode.getSubtitle());
        podcastEpisodeSearchItemCreatePodcastEpisodeSearchItem.setContentInfoMap(podcastEpisode.getContentInfo());
        podcastEpisodeSearchItemCreatePodcastEpisodeSearchItem.setArtworkUri(podcastEpisode.getImage());
        podcastEpisodeSearchItemCreatePodcastEpisodeSearchItem.setBlockRef(podcastEpisode.getBlockRef());
        podcastEpisodeSearchItemCreatePodcastEpisodeSearchItem.setExplicit(podcastEpisode.isExplicit());
        podcastEpisodeSearchItemCreatePodcastEpisodeSearchItem.setPodcastEpisodeVariantId(podcastEpisode.getVariantId());
        podcastEpisodeSearchItemCreatePodcastEpisodeSearchItem.setPodcastShowVariantId(podcastEpisode.getPodcastVariantId());
        podcastEpisodeSearchItemCreatePodcastEpisodeSearchItem.setContentAccessSet(podcastEpisode.getContentAccess());
        podcastEpisodeSearchItemCreatePodcastEpisodeSearchItem.setPodcastEpisodeTierBenefits(podcastEpisode.getTierBenefits());
        podcastEpisodeSearchItemCreatePodcastEpisodeSearchItem.setPodcastShowTierBenefits(podcastEpisode.getShowTierBenefits());
        podcastEpisodeSearchItemCreatePodcastEpisodeSearchItem.setAvailabilityDate(podcastEpisode.getAvailabilityDisplayDate());
        podcastEpisodeSearchItemCreatePodcastEpisodeSearchItem.setPublishDate(podcastEpisode.getPublishDate());
        return podcastEpisodeSearchItemCreatePodcastEpisodeSearchItem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SportsSearchItem sportFromSearchResult(SportsItem sport) {
        String string;
        Uri uri;
        String str;
        String image;
        String title;
        SportsSearchItem sportsSearchItemCreateSoccerSearchItem = this.searchEntityFactory.createSoccerSearchItem();
        string = "";
        if (sport instanceof Program) {
            Program program = (Program) sport;
            title = program.getTitle();
            string = program.getStartTime() != null ? program.getStartTime().toString() : "";
            image = program.getImage();
            uri = Uri.parse(program.getLink());
        } else if (sport instanceof Competition) {
            Competition competition = (Competition) sport;
            title = competition.getTitle();
            image = competition.getImage();
            uri = Uri.parse(competition.getLink());
        } else {
            uri = null;
            str = "";
            image = str;
            sportsSearchItemCreateSoccerSearchItem.setTitle(string);
            sportsSearchItemCreateSoccerSearchItem.setSubtitle(str);
            sportsSearchItemCreateSoccerSearchItem.setArtworkUri(image);
            sportsSearchItemCreateSoccerSearchItem.setDeeplink(uri);
            sportsSearchItemCreateSoccerSearchItem.setBlockRef(sport.getBlockRef());
            sportsSearchItemCreateSoccerSearchItem.setContentInfoMap(sport.getContentInfo());
            return sportsSearchItemCreateSoccerSearchItem;
        }
        String str2 = title;
        str = string;
        string = str2;
        sportsSearchItemCreateSoccerSearchItem.setTitle(string);
        sportsSearchItemCreateSoccerSearchItem.setSubtitle(str);
        sportsSearchItemCreateSoccerSearchItem.setArtworkUri(image);
        sportsSearchItemCreateSoccerSearchItem.setDeeplink(uri);
        sportsSearchItemCreateSoccerSearchItem.setBlockRef(sport.getBlockRef());
        sportsSearchItemCreateSoccerSearchItem.setContentInfoMap(sport.getContentInfo());
        return sportsSearchItemCreateSoccerSearchItem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final StationSearchItem stationFromSearchResult(Station station) {
        StationSearchItem stationSearchItemCreateStationSearchItem = this.searchEntityFactory.createStationSearchItem();
        stationSearchItemCreateStationSearchItem.setTitle(station.getTitle());
        stationSearchItemCreateStationSearchItem.setArtworkUri(station.getImage());
        stationSearchItemCreateStationSearchItem.setKey(station.getStationKey());
        stationSearchItemCreateStationSearchItem.setBlockRef(station.getBlockRef());
        stationSearchItemCreateStationSearchItem.setContentInfoMap(station.getContentInfo());
        stationSearchItemCreateStationSearchItem.setContentAccessSet(station.getContentAccess());
        stationSearchItemCreateStationSearchItem.setExplicitMap(buildExplicitMap(station.isExplicit()));
        stationSearchItemCreateStationSearchItem.setExplicit(station.isExplicit());
        Set<ContentAccessType> contentAccess = station.getContentAccess();
        Intrinsics.checkNotNullExpressionValue(contentAccess, "station.contentAccess");
        stationSearchItemCreateStationSearchItem.setCatalogStatus(contentAccess);
        return stationSearchItemCreateStationSearchItem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TrackSearchItem trackFromSearchResult(Track track) {
        TrackSearchItem trackSearchItemCreateTrackSearchItem = this.searchEntityFactory.createTrackSearchItem();
        trackSearchItemCreateTrackSearchItem.setTitle(track.getTitle());
        trackSearchItemCreateTrackSearchItem.setAsin(track.getAsin());
        trackSearchItemCreateTrackSearchItem.setArtworkUri(track.getImage());
        trackSearchItemCreateTrackSearchItem.setArtist(track.getArtistName());
        trackSearchItemCreateTrackSearchItem.setArtistAsin(track.getArtistAsin());
        trackSearchItemCreateTrackSearchItem.setAlbum(track.getAlbumName());
        trackSearchItemCreateTrackSearchItem.setAlbumAsin(track.getAlbumAsin());
        trackSearchItemCreateTrackSearchItem.setAlbumArtist(track.getAlbumArtistName());
        trackSearchItemCreateTrackSearchItem.setBlockRef(track.getBlockRef());
        int durationInSeconds = track.getDurationInSeconds();
        if (durationInSeconds == null) {
            durationInSeconds = 0;
        }
        trackSearchItemCreateTrackSearchItem.setDuration(durationInSeconds);
        Integer trackNumber = track.getTrackNumber();
        trackSearchItemCreateTrackSearchItem.setTrackNum(trackNumber == null ? 1 : trackNumber.intValue());
        Integer discNumber = track.getDiscNumber();
        trackSearchItemCreateTrackSearchItem.setDiscNum(discNumber != null ? discNumber.intValue() : 1);
        trackSearchItemCreateTrackSearchItem.setExplicit(track.isExplicit());
        trackSearchItemCreateTrackSearchItem.setContentInfoMap(track.getContentInfo());
        trackSearchItemCreateTrackSearchItem.setContentEncoding(track.getContentEncoding());
        trackSearchItemCreateTrackSearchItem.setContentAccessSet(track.getContentAccess());
        trackSearchItemCreateTrackSearchItem.setPlaymodeEligibility(convertPlaymodeEligibility(track.getPlaymodeEligibility(), this.searchFeaturesProvider.shouldRequestPlaymodeEligibility(EntityType.TRACK), PLAYMODE_ELIGIBILITY_SHUFFLE_DEFAULT));
        trackSearchItemCreateTrackSearchItem.setRelatedPlaylists(track.getRelatedPlaylists());
        trackSearchItemCreateTrackSearchItem.setLanguageOfPerformances(track.getLanguageOfPerformances());
        if (track.getContentAccess() != null) {
            trackSearchItemCreateTrackSearchItem.setCatalogStatus(track.getContentAccess().contains(ContentAccessType.PRIME), track.getContentAccess().contains(ContentAccessType.HAWKFIRE));
        }
        return trackSearchItemCreateTrackSearchItem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TrackSearchItem videoFromSearchResult(Video video) {
        TrackSearchItem trackSearchItemCreateVideoSearchItem = this.searchEntityFactory.createVideoSearchItem();
        trackSearchItemCreateVideoSearchItem.setTitle(video.getTitle());
        trackSearchItemCreateVideoSearchItem.setAsin(video.getAsin());
        trackSearchItemCreateVideoSearchItem.setArtworkUri(video.getImage());
        trackSearchItemCreateVideoSearchItem.setArtist(video.getArtistName());
        trackSearchItemCreateVideoSearchItem.setArtistAsin(video.getArtistAsin());
        trackSearchItemCreateVideoSearchItem.setAlbum(video.getAlbumName());
        trackSearchItemCreateVideoSearchItem.setAlbumAsin(video.getAlbumAsin());
        trackSearchItemCreateVideoSearchItem.setAlbumArtist(video.getAlbumArtistName());
        trackSearchItemCreateVideoSearchItem.setBlockRef(video.getBlockRef());
        int durationInSeconds = video.getDurationInSeconds();
        if (durationInSeconds == null) {
            durationInSeconds = 0;
        }
        trackSearchItemCreateVideoSearchItem.setDuration(durationInSeconds);
        Integer trackNumber = video.getTrackNumber();
        trackSearchItemCreateVideoSearchItem.setTrackNum(trackNumber == null ? 1 : trackNumber.intValue());
        Integer discNumber = video.getDiscNumber();
        trackSearchItemCreateVideoSearchItem.setDiscNum(discNumber == null ? 1 : discNumber.intValue());
        trackSearchItemCreateVideoSearchItem.setExplicit(true);
        SearchAssetType searchAssetType = SearchAssetType.VIDEO;
        trackSearchItemCreateVideoSearchItem.setSearchAssetType(searchAssetType);
        trackSearchItemCreateVideoSearchItem.setContentInfoMap(video.getContentInfo());
        trackSearchItemCreateVideoSearchItem.setAssetQualities(convertAssetQuality(video.getAssetQuality()));
        trackSearchItemCreateVideoSearchItem.setContentAccessSet(video.getContentAccess());
        Set<ContentAccessType> contentAccess = video.getContentAccess();
        if (contentAccess != null) {
            trackSearchItemCreateVideoSearchItem.setCatalogStatus(contentAccess.contains(ContentAccessType.PRIME), contentAccess.contains(ContentAccessType.HAWKFIRE));
        }
        trackSearchItemCreateVideoSearchItem.setAssetType(searchAssetType);
        return trackSearchItemCreateVideoSearchItem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PlaylistSearchItem videoPlaylistFromSearchResult(VideoPlaylist videoPlaylist) {
        PlaylistSearchItem playlistSearchItemCreateVideoPlaylistSearchItem = this.searchEntityFactory.createVideoPlaylistSearchItem();
        playlistSearchItemCreateVideoPlaylistSearchItem.setTitle(videoPlaylist.getTitle());
        playlistSearchItemCreateVideoPlaylistSearchItem.setAsin(videoPlaylist.getAsin());
        playlistSearchItemCreateVideoPlaylistSearchItem.setArtworkUri(videoPlaylist.getImage());
        playlistSearchItemCreateVideoPlaylistSearchItem.setDescription("");
        playlistSearchItemCreateVideoPlaylistSearchItem.setRating(0.0f);
        playlistSearchItemCreateVideoPlaylistSearchItem.setNumReviews(0);
        playlistSearchItemCreateVideoPlaylistSearchItem.setAuthors(CollectionsKt__CollectionsKt.emptyList());
        playlistSearchItemCreateVideoPlaylistSearchItem.setBlockRef(videoPlaylist.getBlockRef());
        SearchAssetType searchAssetType = SearchAssetType.VIDEO;
        playlistSearchItemCreateVideoPlaylistSearchItem.setSearchAssetType(searchAssetType);
        playlistSearchItemCreateVideoPlaylistSearchItem.setContentInfoMap(videoPlaylist.getContentInfo());
        playlistSearchItemCreateVideoPlaylistSearchItem.setPlaylistType(PlaylistType.PRIME_PLAYLIST);
        playlistSearchItemCreateVideoPlaylistSearchItem.setAssetType(searchAssetType);
        Set<ContentAccessType> contentAccess = videoPlaylist.getContentAccess();
        if (contentAccess != null) {
            playlistSearchItemCreateVideoPlaylistSearchItem.setCatalogStatus(contentAccess.contains(ContentAccessType.PRIME), contentAccess.contains(ContentAccessType.SONIC_RUSH), contentAccess.contains(ContentAccessType.HAWKFIRE), contentAccess.contains(ContentAccessType.NIGHTWING), contentAccess.contains(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE));
        }
        return playlistSearchItemCreateVideoPlaylistSearchItem;
    }

    private final LiveEventSearchItem liveEventFromSearchResult(LiveEvent liveEvent) {
        String image = liveEvent.getImage();
        String blockRef = liveEvent.getBlockRef();
        String id = liveEvent.getId();
        Date startDate = liveEvent.getStartDate();
        String title = liveEvent.getTitle();
        Map<String, String> contentInfo = liveEvent.getContentInfo();
        Intrinsics.checkNotNullExpressionValue(blockRef, "blockRef");
        Intrinsics.checkNotNullExpressionValue(id, "id");
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        Intrinsics.checkNotNullExpressionValue(title, "title");
        return new LiveEventSearchItem(image, blockRef, contentInfo, id, startDate, title);
    }

    private final Map<ContentAccessType, Boolean> buildExplicitMap(boolean isExplicit) {
        return MapsKt__MapsKt.mapOf(TuplesKt.to(ContentAccessType.HAWKFIRE, Boolean.valueOf(isExplicit)), TuplesKt.to(ContentAccessType.PRIME, Boolean.valueOf(isExplicit)), TuplesKt.to(ContentAccessType.NIGHTWING, Boolean.valueOf(isExplicit)));
    }

    private final GenericSearchResponse postProcessResponse(GenericSearchResponse response) {
        Object next;
        for (ArtistSearchItem artistSearchItem : response.getArtists()) {
            if (TextUtils.isEmpty(artistSearchItem.getArtworkUri())) {
                Iterator<T> it = response.getTracks().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    if (TextUtils.equals(((TrackSearchItem) next).getAsin(), artistSearchItem.getAsin())) {
                        break;
                    }
                }
                TrackSearchItem trackSearchItem = (TrackSearchItem) next;
                if (trackSearchItem != null) {
                    artistSearchItem.setArtworkUri(trackSearchItem.getArtworkUri());
                }
            }
        }
        return response;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.util.List<java.lang.String> getAssetQualitiesForSearchType(boolean r3, com.amazon.music.search.ResultType r4, java.lang.String r5, com.amazon.music.find.model.filter.SearchFilterSelections r6, boolean r7) {
        /*
            r2 = this;
            com.amazon.music.search.ResultType r0 = com.amazon.music.search.ResultType.Catalog
            r1 = 0
            if (r4 != r0) goto L8c
            int r4 = r5.hashCode()
            switch(r4) {
                case -1897135820: goto L7b;
                case -1544438277: goto L6d;
                case -1409097913: goto L64;
                case -966368278: goto L5a;
                case -895760513: goto L50;
                case 3529469: goto L46;
                case 92896879: goto L38;
                case 110621003: goto L2e;
                case 112202875: goto L24;
                case 282818838: goto L19;
                case 1879474642: goto Le;
                default: goto Lc;
            }
        Lc:
            goto L8c
        Le:
            java.lang.String r4 = "playlist"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L76
            goto L8c
        L19:
            java.lang.String r4 = "video_playlist"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L76
            goto L8c
        L24:
            java.lang.String r4 = "video"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L85
            goto L8c
        L2e:
            java.lang.String r4 = "track"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L41
            goto L8c
        L38:
            java.lang.String r4 = "album"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L41
            goto L8c
        L41:
            java.util.List r1 = r2.getAssetQualitiesFromFilterSelections(r3, r6)
            goto L8c
        L46:
            java.lang.String r4 = "show"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L76
            goto L8c
        L50:
            java.lang.String r4 = "sports"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L85
            goto L8c
        L5a:
            java.lang.String r4 = "top_hits"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L76
            goto L8c
        L64:
            java.lang.String r4 = "artist"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L85
            goto L8c
        L6d:
            java.lang.String r4 = "episode"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L76
            goto L8c
        L76:
            java.util.List r1 = r2.getAssetQualities(r3)
            goto L8c
        L7b:
            java.lang.String r4 = "station"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L85
            goto L8c
        L85:
            if (r7 == 0) goto L88
            goto L8c
        L88:
            java.util.List r1 = r2.getAssetQualities(r3)
        L8c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.find.api.RemoteSearchApi.getAssetQualitiesForSearchType(boolean, com.amazon.music.search.ResultType, java.lang.String, com.amazon.music.find.model.filter.SearchFilterSelections, boolean):java.util.List");
    }

    private final List<String> getAssetQualitiesFromFilterSelections(boolean isKatana, SearchFilterSelections searchFilterSelections) {
        if (isKatana) {
            ArrayList arrayList = new ArrayList();
            if (searchFilterSelections.containsSelection(FilterMetadata.UHD_FILTER_ID)) {
                arrayList.add(UHD_ASSET_QUALITY);
            }
            if (searchFilterSelections.containsSelection(FilterMetadata.DOLBY_ATMOS_FILTER_ID)) {
                arrayList.add(ATMOS_ASSET_QUALITY);
            }
            if (!searchFilterSelections.containsSelection(FilterMetadata.SONY_360_FILTER_ID)) {
                return arrayList;
            }
            arrayList.add(RA360_ASSET_QUALITY);
            return arrayList;
        }
        return CollectionsKt__CollectionsKt.emptyList();
    }

    private final List<String> getAssetQualities(boolean isKatana) {
        ArrayList arrayList = new ArrayList();
        if (isKatana) {
            arrayList.add(NOT_ASSIGNED_ASSET_QUALITY);
            arrayList.add(HD_ASSET_QUALITY);
            arrayList.add(UHD_ASSET_QUALITY);
            arrayList.add(IMMERSIVE_ASSET_QUALITY);
        }
        return arrayList;
    }

    private final List<AssetQuality> convertAssetQuality(List<? extends com.amazon.music.platform.model.AssetQuality> tenzingAssetQualityList) {
        if (tenzingAssetQualityList != null) {
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tenzingAssetQualityList, 10));
            for (com.amazon.music.platform.model.AssetQuality assetQuality : tenzingAssetQualityList) {
                String playbackId = assetQuality.getPlaybackId();
                Intrinsics.checkNotNullExpressionValue(playbackId, "item.playbackId");
                String quality = assetQuality.getQuality();
                Intrinsics.checkNotNullExpressionValue(quality, "item.quality");
                Integer rank = assetQuality.getRank();
                Intrinsics.checkNotNullExpressionValue(rank, "item.rank");
                arrayList.add(new AssetQuality(playbackId, quality, rank.intValue()));
            }
            return arrayList;
        }
        return CollectionsKt__CollectionsKt.emptyList();
    }

    static /* synthetic */ PlaymodeEligibility convertPlaymodeEligibility$default(RemoteSearchApi remoteSearchApi, com.amazon.music.search.PlaymodeEligibility playmodeEligibility, boolean z, PlaymodeEligibility playmodeEligibility2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: convertPlaymodeEligibility");
        }
        if ((i & 4) != 0) {
            playmodeEligibility2 = null;
        }
        return remoteSearchApi.convertPlaymodeEligibility(playmodeEligibility, z, playmodeEligibility2);
    }

    private final PlaymodeEligibility convertPlaymodeEligibility(com.amazon.music.search.PlaymodeEligibility searchPlaymodeEligibility, boolean shouldRequestPlaymodeEligibility, PlaymodeEligibility defaultPlaymodeEligibility) {
        if (shouldRequestPlaymodeEligibility) {
            return searchPlaymodeEligibility == null ? defaultPlaymodeEligibility : new PlaymodeEligibility(searchPlaymodeEligibility.isOnDemandPlayable(), searchPlaymodeEligibility.isStationFromAnythingPlayable(), searchPlaymodeEligibility.getShuffleType());
        }
        return null;
    }

    private final boolean isPodcastEnabled(SearchFilterSelections searchFilterSelections) {
        return (!this.searchFeaturesProvider.isPodcastEnabled() || this.searchFeaturesProvider.isChildDirected() || shouldSuppressNonTrackAlbum(searchFilterSelections)) ? false : true;
    }

    private final boolean isVideoEnabled(SearchFilterSelections searchFilterSelections) {
        return (!this.searchFeaturesProvider.isVideosEnabled() || this.searchFeaturesProvider.isChildDirected() || shouldSuppressNonTrackAlbum(searchFilterSelections) || this.searchFeaturesProvider.isInCarMode()) ? false : true;
    }

    private final boolean isVideoPlaylistEnabled(SearchFilterSelections searchFilterSelections) {
        return (!this.searchFeaturesProvider.isVideoPlaylistEnabled() || this.searchFeaturesProvider.isChildDirected() || shouldSuppressNonTrackAlbum(searchFilterSelections) || this.searchFeaturesProvider.isInCarMode()) ? false : true;
    }

    private final boolean isArtistEnabled(SearchFilterSelections searchFilterSelections) {
        return (this.searchFeaturesProvider.isChildDirected() || shouldSuppressNonTrackAlbum(searchFilterSelections)) ? false : true;
    }

    private final boolean isPlaylistEnabled(SearchFilterSelections searchFilterSelections) {
        return !shouldSuppressNonTrackAlbum(searchFilterSelections);
    }

    private final boolean isStationEnabled(SearchFilterSelections searchFilterSelections) {
        return !shouldSuppressNonTrackAlbum(searchFilterSelections);
    }

    private final boolean isSportEnabled(SearchFilterSelections searchFilterSelections) {
        return !shouldSuppressNonTrackAlbum(searchFilterSelections);
    }

    private final boolean isTopHitsEnabled(SearchFilterSelections searchFilterSelections) {
        return !shouldSuppressNonTrackAlbum(searchFilterSelections);
    }

    private final boolean isCommunityPlaylistEnabled(SearchFilterSelections searchFilterSelections) {
        return (!this.searchFeaturesProvider.isCommunityPlaylistEnabled() || this.searchFeaturesProvider.isChildDirected() || shouldSuppressNonTrackAlbum(searchFilterSelections)) ? false : true;
    }

    @VisibleForTesting
    public final boolean isLiveEventEnabled() {
        return (!this.searchFeaturesProvider.isLiveEventEnabled() || this.searchFeaturesProvider.isChildDirected() || this.searchFeaturesProvider.isExplicitLanguageFilterEnabled()) ? false : true;
    }

    private final boolean shouldSuppressNonTrackAlbum(SearchFilterSelections searchFilterSelections) {
        return this.searchFeaturesProvider.getShouldSuppressIvyCatalogNonTrackAlbumFiltering() && searchFilterSelections.hasAudioQualityFilters();
    }
}
