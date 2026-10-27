package com.amazon.mp3.auto.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import androidx.annotation.WorkerThread;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.util.Log;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.music.views.library.models.SimpleHorizontalTileModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: PresetIdentifierHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b.\u0010/J\u001a\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0007J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0007J\"\u0010\r\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0007J\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000eH\u0007J&\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0012H\u0002J \u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00122\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u000eH\u0002J\u0014\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012*\u00020\u000eH\u0002J\u0014\u0010\u0017\u001a\u0004\u0018\u00010\u000e*\b\u0012\u0004\u0012\u00020\b0\u0012H\u0002J\u0016\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00122\u0006\u0010\u0003\u001a\u00020\u0002J\u0016\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00122\u0006\u0010\u0003\u001a\u00020\u0002J\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u000e2\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012J\u0010\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\f\u0010\u001d\u001a\u0004\u0018\u00010\b*\u00020\u001cJ\f\u0010\u001d\u001a\u0004\u0018\u00010\b*\u00020\u001eJ\f\u0010\u001d\u001a\u0004\u0018\u00010\b*\u00020\u001fJ9\u0010'\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\b\u0010!\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020%¢\u0006\u0004\b'\u0010(J)\u0010)\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\b\u0010!\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"¢\u0006\u0004\b)\u0010*R\u001c\u0010,\u001a\n +*\u0004\u0018\u00010\u000e0\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-¨\u00060"}, d2 = {"Lcom/amazon/mp3/auto/storage/PresetIdentifierHelper;", "", "Landroid/content/Context;", "context", "Lkotlinx/coroutines/CoroutineDispatcher;", "dispatcher", "", "initialize", "Lcom/amazon/mp3/auto/storage/PresetIdentifier;", "identifier", "add", "", "initializing", "delete", "", "id", "check", "key", "", "identifiers", "writePreferences", "readPreferences", "toList", "toJsonString", "getPresetIdentifiers", "getDeletedPresetIdentifiers", "assemble", "clear", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "asPresetIdentifier", "Lcom/amazon/music/views/library/models/SimpleHorizontalTileModel;", "Lcom/amazon/mp3/library/item/CatalogContent;", "Landroid/database/Cursor;", "cursor", "", "limit", "idColumn", "Lcom/amazon/mp3/auto/storage/PresetEntityType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "convertCursorToPresetIdentifiers", "(Landroid/database/Cursor;Ljava/lang/Integer;Ljava/lang/String;Lcom/amazon/mp3/auto/storage/PresetEntityType;)Ljava/util/List;", "convertCursorToLibraryPlaylistIdentifiers", "(Landroid/database/Cursor;Ljava/lang/Integer;)Ljava/util/List;", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PresetIdentifierHelper {
    public static final PresetIdentifierHelper INSTANCE = new PresetIdentifierHelper();
    private static final String TAG = PresetIdentifierHelper.class.getSimpleName();

    @JvmStatic
    @JvmOverloads
    public static final void initialize(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        initialize$default(context, null, 2, null);
    }

    private PresetIdentifierHelper() {
    }

    /* JADX INFO: renamed from: com.amazon.mp3.auto.storage.PresetIdentifierHelper$initialize$1, reason: invalid class name */
    /* JADX INFO: compiled from: PresetIdentifierHelper.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.auto.storage.PresetIdentifierHelper$initialize$1", f = "PresetIdentifierHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            SharedPreferences sharedPreferences = this.$context.getSharedPreferences("PresetsPrefs", 0);
            if (sharedPreferences.getBoolean("presets_identifiers_migration_key", false)) {
                Log.debug(PresetIdentifierHelper.TAG, "Presets identifiers have already been initialized");
            } else {
                ArrayList<SimpleHorizontalTileModel> userPresets = CarModePresetsUtility.INSTANCE.getUserPresets(this.$context);
                if (userPresets != null) {
                    Context context = this.$context;
                    Iterator<T> it = userPresets.iterator();
                    while (it.hasNext()) {
                        PresetIdentifier presetIdentifierAsPresetIdentifier = PresetIdentifierHelper.INSTANCE.asPresetIdentifier((SimpleHorizontalTileModel) it.next());
                        if (presetIdentifierAsPresetIdentifier != null) {
                            if (presetIdentifierAsPresetIdentifier.getEntityType() == PresetEntityType.UserPlaylist) {
                                String luid = MediaProvider.UdoPlaylists.getLuid(context, presetIdentifierAsPresetIdentifier.getIdentifier());
                                if (luid == null) {
                                    Log.error(PresetIdentifierHelper.TAG, Intrinsics.stringPlus("Cannot get luid for ", presetIdentifierAsPresetIdentifier.getIdentifier()));
                                } else {
                                    PresetIdentifierHelper.add(context, new PresetIdentifier(luid, presetIdentifierAsPresetIdentifier.getEntityType()));
                                }
                            } else {
                                PresetIdentifierHelper.add(context, presetIdentifierAsPresetIdentifier);
                            }
                        }
                    }
                }
                ArrayList<SimpleHorizontalTileModel> userDeletedPresets = CarModePresetsUtility.INSTANCE.getUserDeletedPresets(this.$context);
                if (userDeletedPresets != null) {
                    Context context2 = this.$context;
                    Iterator<T> it2 = userDeletedPresets.iterator();
                    while (it2.hasNext()) {
                        PresetIdentifier presetIdentifierAsPresetIdentifier2 = PresetIdentifierHelper.INSTANCE.asPresetIdentifier((SimpleHorizontalTileModel) it2.next());
                        if (presetIdentifierAsPresetIdentifier2 != null) {
                            if (presetIdentifierAsPresetIdentifier2.getEntityType() == PresetEntityType.UserPlaylist) {
                                String luid2 = MediaProvider.UdoPlaylists.getLuid(context2, presetIdentifierAsPresetIdentifier2.getIdentifier());
                                if (luid2 == null) {
                                    Log.error(PresetIdentifierHelper.TAG, Intrinsics.stringPlus("Cannot get luid for ", presetIdentifierAsPresetIdentifier2.getIdentifier()));
                                } else {
                                    PresetIdentifierHelper.delete(context2, new PresetIdentifier(luid2, presetIdentifierAsPresetIdentifier2.getEntityType()), true);
                                }
                            } else {
                                PresetIdentifierHelper.delete(context2, presetIdentifierAsPresetIdentifier2, true);
                            }
                        }
                    }
                }
                sharedPreferences.edit().putBoolean("presets_identifiers_migration_key", true).apply();
                Log.debug(PresetIdentifierHelper.TAG, "Migration of user Presets done");
            }
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ void initialize$default(Context context, CoroutineDispatcher coroutineDispatcher, int i, Object obj) {
        if ((i & 2) != 0) {
            coroutineDispatcher = Dispatchers.getIO();
        }
        initialize(context, coroutineDispatcher);
    }

    @JvmStatic
    @JvmOverloads
    public static final void initialize(Context context, CoroutineDispatcher dispatcher) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(dispatcher), null, null, new AnonymousClass1(context, null), 3, null);
    }

    @JvmStatic
    public static final void add(Context context, PresetIdentifier identifier) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        PresetIdentifierHelper presetIdentifierHelper = INSTANCE;
        List<PresetIdentifier> preferences = presetIdentifierHelper.readPreferences(context, "added_presets_identifiers_key");
        List<PresetIdentifier> mutableList = preferences == null ? null : CollectionsKt___CollectionsKt.toMutableList((Collection) preferences);
        if (mutableList == null) {
            mutableList = new ArrayList<>();
        }
        if (mutableList.contains(identifier)) {
            return;
        }
        mutableList.add(identifier);
        presetIdentifierHelper.writePreferences(context, "added_presets_identifiers_key", mutableList);
    }

    public static /* synthetic */ void delete$default(Context context, PresetIdentifier presetIdentifier, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        delete(context, presetIdentifier, z);
    }

    @JvmStatic
    @JvmOverloads
    public static final void delete(Context context, PresetIdentifier identifier, boolean initializing) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        PresetIdentifierHelper presetIdentifierHelper = INSTANCE;
        List<PresetIdentifier> preferences = presetIdentifierHelper.readPreferences(context, "added_presets_identifiers_key");
        List<PresetIdentifier> mutableList = preferences == null ? null : CollectionsKt___CollectionsKt.toMutableList((Collection) preferences);
        if (!initializing) {
            boolean z = false;
            if (mutableList != null && mutableList.contains(identifier)) {
                z = true;
            }
            if (z) {
                mutableList.remove(identifier);
                presetIdentifierHelper.writePreferences(context, "added_presets_identifiers_key", mutableList);
                return;
            }
        }
        List<PresetIdentifier> preferences2 = presetIdentifierHelper.readPreferences(context, "deleted_presets_identifiers_key");
        List<PresetIdentifier> mutableList2 = preferences2 != null ? CollectionsKt___CollectionsKt.toMutableList((Collection) preferences2) : null;
        if (mutableList2 == null) {
            mutableList2 = new ArrayList<>();
        }
        if (mutableList2.contains(identifier)) {
            return;
        }
        mutableList2.add(identifier);
        presetIdentifierHelper.writePreferences(context, "deleted_presets_identifiers_key", mutableList2);
    }

    @JvmStatic
    public static final boolean check(Context context, String id) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(id, "id");
        List<PresetIdentifier> presetIdentifiers = INSTANCE.getPresetIdentifiers(context);
        if (presetIdentifiers == null || presetIdentifiers.isEmpty()) {
            return false;
        }
        Iterator<T> it = presetIdentifiers.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((PresetIdentifier) it.next()).getIdentifier(), id)) {
                return true;
            }
        }
        return false;
    }

    private final void writePreferences(Context context, String key, List<PresetIdentifier> identifiers) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PresetsPrefs", 0);
        String jsonString = toJsonString(identifiers);
        if (jsonString == null) {
            return;
        }
        sharedPreferences.edit().putString(key, jsonString).apply();
    }

    private final List<PresetIdentifier> readPreferences(Context context, String key) {
        String string = context.getSharedPreferences("PresetsPrefs", 0).getString(key, null);
        if (string == null) {
            return null;
        }
        return toList(string);
    }

    public final List<PresetIdentifier> getPresetIdentifiers(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return readPreferences(context, "added_presets_identifiers_key");
    }

    public final List<PresetIdentifier> getDeletedPresetIdentifiers(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return readPreferences(context, "deleted_presets_identifiers_key");
    }

    public final String assemble(List<PresetIdentifier> identifiers) {
        if (identifiers == null || identifiers.isEmpty()) {
            return null;
        }
        return CollectionsKt___CollectionsKt.joinToString$default(identifiers, ",", null, null, 0, null, new Function1<PresetIdentifier, CharSequence>() { // from class: com.amazon.mp3.auto.storage.PresetIdentifierHelper$assemble$ids$1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(PresetIdentifier it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getEntityType() + ':' + it.getIdentifier();
            }
        }, 30, null);
    }

    @WorkerThread
    public final void clear(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        context.getSharedPreferences("PresetsPrefs", 0).edit().remove("added_presets_identifiers_key").remove("deleted_presets_identifiers_key").remove("presets_identifiers_migration_key").commit();
    }

    private final List<PresetIdentifier> toList(String str) {
        try {
            Object objFromJson = new Gson().fromJson(str, new TypeToken<List<? extends PresetIdentifier>>() { // from class: com.amazon.mp3.auto.storage.PresetIdentifierHelper$toList$list$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(objFromJson, "Gson().fromJson(this, ob…etIdentifier>>() {}.type)");
            return (List) objFromJson;
        } catch (Exception unused) {
            Log.error(TAG, "Fail to de-serialize Presets identifiers");
            return null;
        }
    }

    private final String toJsonString(List<PresetIdentifier> list) {
        try {
            return new Gson().toJson(list);
        } catch (Exception unused) {
            Log.error(TAG, "Fail to serialize Presets identifiers");
            return null;
        }
    }

    public final PresetIdentifier asPresetIdentifier(ContentMetadata contentMetadata) {
        String podcastShowCatalogId;
        Intrinsics.checkNotNullParameter(contentMetadata, "<this>");
        if (contentMetadata instanceof TrackMetadata) {
            String asin = ((TrackMetadata) contentMetadata).getAsin();
            if (asin == null) {
                return null;
            }
            return new PresetIdentifier(asin, PresetEntityType.Track);
        }
        if (contentMetadata instanceof AlbumMetadata) {
            String asin2 = ((AlbumMetadata) contentMetadata).getAsin();
            if (asin2 == null) {
                return null;
            }
            return new PresetIdentifier(asin2, PresetEntityType.Album);
        }
        if (contentMetadata instanceof ArtistMetadata) {
            String asin3 = ((ArtistMetadata) contentMetadata).getAsin();
            if (asin3 == null) {
                return null;
            }
            return new PresetIdentifier(asin3, PresetEntityType.Artist);
        }
        if (contentMetadata instanceof PlaylistMetadata) {
            String asin4 = ((PlaylistMetadata) contentMetadata).getAsin();
            if (asin4 == null) {
                return null;
            }
            return new PresetIdentifier(asin4, PresetEntityType.Playlist);
        }
        if (contentMetadata instanceof UserPlaylistMetadata) {
            return new PresetIdentifier(((UserPlaylistMetadata) contentMetadata).getPlaylistId(), PresetEntityType.UserPlaylist);
        }
        if (contentMetadata instanceof StationMetadata) {
            return new PresetIdentifier(((StationMetadata) contentMetadata).getKey(), PresetEntityType.Station);
        }
        if (!(contentMetadata instanceof PodcastMetadata) || (podcastShowCatalogId = ((PodcastMetadata) contentMetadata).getPodcastShowCatalogId()) == null) {
            return null;
        }
        return new PresetIdentifier(podcastShowCatalogId, PresetEntityType.PodcastShow);
    }

    public final PresetIdentifier asPresetIdentifier(SimpleHorizontalTileModel simpleHorizontalTileModel) {
        Intrinsics.checkNotNullParameter(simpleHorizontalTileModel, "<this>");
        return asPresetIdentifier(simpleHorizontalTileModel.getMetadata());
    }

    public final PresetIdentifier asPresetIdentifier(CatalogContent catalogContent) {
        String key;
        Intrinsics.checkNotNullParameter(catalogContent, "<this>");
        if (catalogContent instanceof Album) {
            String asin = ((Album) catalogContent).getAsin();
            if (asin == null) {
                return null;
            }
            return new PresetIdentifier(asin, PresetEntityType.Album);
        }
        if (catalogContent instanceof CatalogPlaylist) {
            CatalogPlaylist catalogPlaylist = (CatalogPlaylist) catalogContent;
            if (PrimePlaylistUtil.isAsin(catalogPlaylist.getAsin())) {
                String asin2 = catalogPlaylist.getAsin();
                if (asin2 == null) {
                    return null;
                }
                return new PresetIdentifier(asin2, PresetEntityType.Playlist);
            }
            String luid = catalogPlaylist.getLuid();
            if (luid == null) {
                return null;
            }
            return new PresetIdentifier(luid, PresetEntityType.UserPlaylist);
        }
        if (catalogContent instanceof Playlist) {
            String luid2 = ((Playlist) catalogContent).getLuid();
            if (luid2 == null) {
                return null;
            }
            return new PresetIdentifier(luid2, PresetEntityType.UserPlaylist);
        }
        if (catalogContent instanceof PrimeAlbum) {
            String asin3 = ((PrimeAlbum) catalogContent).getAsin();
            if (asin3 == null) {
                return null;
            }
            return new PresetIdentifier(asin3, PresetEntityType.Album);
        }
        if (catalogContent instanceof PrimePlaylist) {
            String asin4 = ((PrimePlaylist) catalogContent).getAsin();
            if (asin4 == null) {
                return null;
            }
            return new PresetIdentifier(asin4, PresetEntityType.Playlist);
        }
        if (!(catalogContent instanceof Station) || (key = ((Station) catalogContent).getKey()) == null) {
            return null;
        }
        return new PresetIdentifier(key, PresetEntityType.Station);
    }

    public final List<PresetIdentifier> convertCursorToPresetIdentifiers(Cursor cursor, Integer limit, String idColumn, PresetEntityType type) {
        String string;
        Intrinsics.checkNotNullParameter(idColumn, "idColumn");
        Intrinsics.checkNotNullParameter(type, "type");
        if (cursor == null) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            try {
                if (limit != null && arrayList.size() >= limit.intValue()) {
                    CloseableKt.closeFinally(cursor, null);
                    return arrayList;
                }
                int columnIndex = cursor.getColumnIndex(idColumn);
                if (columnIndex >= 0 && (string = cursor.getString(columnIndex)) != null) {
                    arrayList.add(new PresetIdentifier(string, type));
                }
            } finally {
            }
        }
        CloseableKt.closeFinally(cursor, null);
        return arrayList;
    }

    public final List<PresetIdentifier> convertCursorToLibraryPlaylistIdentifiers(Cursor cursor, Integer limit) {
        String string;
        String string2;
        if (cursor == null) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            try {
                if (limit != null && arrayList.size() >= limit.intValue()) {
                    CloseableKt.closeFinally(cursor, null);
                    return arrayList;
                }
                int columnIndex = cursor.getColumnIndex(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                if (columnIndex >= 0) {
                    if (Intrinsics.areEqual(cursor.getString(columnIndex), "vnd.android.cursor.item/vnd.amazonmp3.udoplaylist")) {
                        int columnIndex2 = cursor.getColumnIndex("luid");
                        if (columnIndex2 >= 0 && (string = cursor.getString(columnIndex2)) != null) {
                            arrayList.add(new PresetIdentifier(string, PresetEntityType.UserPlaylist));
                        }
                    } else {
                        int columnIndex3 = cursor.getColumnIndex("asin");
                        if (columnIndex3 >= 0 && (string2 = cursor.getString(columnIndex3)) != null) {
                            arrayList.add(new PresetIdentifier(string2, PresetEntityType.Playlist));
                        }
                    }
                }
            } finally {
            }
        }
        CloseableKt.closeFinally(cursor, null);
        return arrayList;
    }
}
