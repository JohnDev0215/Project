package com.amazon.mp3.curate.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.provider.PlaylistProvider;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.TextRowItemModel;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: CuratePlaylistProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b,\u0010-J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0016J(\u0010\u0013\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u0010\u0014\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u000e\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0011R\"\u0010\u0018\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\"\u0010\u001e\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R'\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0$8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u001c\u0010+\u001a\n **\u0004\u0018\u00010\u00110\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010\u001f¨\u0006."}, d2 = {"Lcom/amazon/mp3/curate/provider/CuratePlaylistProvider;", "Lcom/amazon/music/curate/provider/PlaylistProvider;", "Lcom/amazon/music/curate/model/Playlist;", Environment.PLAYLIST_PATH, "Landroid/content/Context;", "context", "Landroid/net/Uri;", "getPlaylistTracksUri", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "", "editPlaylist", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "suggestionModels", "", "maxSuggestions", "", "header", "setSuggestionData", "insertSuggestionsIntoTrackTable", "getCappedSuggestions", "asin", "clearSuggestionByAsin", "maximumSuggestions", "I", "getMaximumSuggestions", "()I", "setMaximumSuggestions", "(I)V", "suggestionsHeader", "Ljava/lang/String;", "getSuggestionsHeader", "()Ljava/lang/String;", "setSuggestionsHeader", "(Ljava/lang/String;)V", "Landroidx/lifecycle/MutableLiveData;", "suggestions$delegate", "Lkotlin/Lazy;", "getSuggestions", "()Landroidx/lifecycle/MutableLiveData;", "suggestions", "kotlin.jvm.PlatformType", "TAG", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CuratePlaylistProvider implements PlaylistProvider {
    private int maximumSuggestions = 6;
    private String suggestionsHeader = "Suggestions";

    /* JADX INFO: renamed from: suggestions$delegate, reason: from kotlin metadata */
    private final Lazy suggestions = LazyKt__LazyJVMKt.lazy(new Function0<MutableLiveData<List<? extends BaseViewModel>>>() { // from class: com.amazon.mp3.curate.provider.CuratePlaylistProvider$suggestions$2
        @Override // kotlin.jvm.functions.Function0
        public final MutableLiveData<List<? extends BaseViewModel>> invoke() {
            return new MutableLiveData<>();
        }
    });
    private final String TAG = CuratePlaylistProvider.class.getSimpleName();

    public final int getMaximumSuggestions() {
        return this.maximumSuggestions;
    }

    public final void setMaximumSuggestions(int i) {
        this.maximumSuggestions = i;
    }

    public final MutableLiveData<List<BaseViewModel>> getSuggestions() {
        return (MutableLiveData) this.suggestions.getValue();
    }

    @Override // com.amazon.music.curate.provider.PlaylistProvider
    public void editPlaylist(Playlist playlist, FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(activity, "activity");
        PlaylistUtil.startEditPlaylist(activity, getPlaylistTracksUri(playlist, activity), playlist.getNumSongs() <= 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.music.curate.provider.PlaylistProvider
    public void setSuggestionData(List<? extends BaseViewModel> suggestionModels, int maxSuggestions, String header) {
        Intrinsics.checkNotNullParameter(suggestionModels, "suggestionModels");
        this.maximumSuggestions = maxSuggestions;
        boolean z = false;
        if (!(header == null || StringsKt__StringsJVMKt.isBlank(header))) {
            this.suggestionsHeader = header;
        }
        List<BaseViewModel> value = getSuggestions().getValue();
        if (value != null && value.isEmpty()) {
            z = true;
        }
        if (getSuggestions().getValue() == null || z) {
            getSuggestions().setValue(suggestionModels);
        }
    }

    public final void insertSuggestionsIntoTrackTable(Context context) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        try {
            DbUtil.beginTransaction(writableDatabase);
            List<BaseViewModel> value = getSuggestions().getValue();
            if (value != null) {
                Iterator<T> it = value.iterator();
                while (it.hasNext()) {
                    TrackMetadata trackMetadata = (TrackMetadata) ((VisualRowItemModel) ((BaseViewModel) it.next())).getMetadata();
                    String asin = trackMetadata.getAsin();
                    long jGenerateAlbumId = IdGenerator.generateAlbumId(trackMetadata.getAlbumAsin(), trackMetadata.getAlbumTitle());
                    int value2 = ContentOwnershipStatus.NOT_IN_LIBRARY.getValue();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("luid", asin);
                    contentValues.put("luid_hash", Integer.valueOf(asin == null ? 0 : asin.hashCode()));
                    contentValues.put("asin", asin);
                    contentValues.put("title", trackMetadata.getTitle());
                    contentValues.put("artist", trackMetadata.getArtistTitle());
                    contentValues.put("artist_asin", trackMetadata.getArtistAsin());
                    contentValues.put("album", trackMetadata.getAlbumTitle());
                    contentValues.put("album_asin", trackMetadata.getAlbumAsin());
                    contentValues.put("album_id", Long.valueOf(jGenerateAlbumId));
                    contentValues.put("album_art_id", Long.valueOf(jGenerateAlbumId));
                    contentValues.put("ownership_status", Integer.valueOf(value2));
                    writableDatabase.insertWithOnConflict("Track", null, contentValues, 5);
                }
            }
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public final List<BaseViewModel> getCappedSuggestions() {
        ArrayList arrayList = new ArrayList();
        List<BaseViewModel> value = getSuggestions().getValue();
        if ((value == null || value.isEmpty()) ? false : true) {
            TextRowItemModel textRowItemModel = new TextRowItemModel(null, this.suggestionsHeader, null, null, null, false, 61, null);
            textRowItemModel.setInsetHorizontally(true);
            arrayList.add(textRowItemModel);
        }
        List<BaseViewModel> value2 = getSuggestions().getValue();
        if (value2 != null) {
            Iterator<T> it = value2.iterator();
            while (it.hasNext()) {
                ((VisualRowItemModel) ((BaseViewModel) it.next())).setInsetHorizontally(true);
            }
        }
        List<BaseViewModel> value3 = getSuggestions().getValue();
        if (value3 != null) {
            arrayList.addAll(value3.subList(0, RangesKt___RangesKt.coerceAtMost(getMaximumSuggestions(), value3.size())));
        }
        return arrayList;
    }

    public final void clearSuggestionByAsin(String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        List<BaseViewModel> value = getSuggestions().getValue();
        List mutableList = value == null ? null : CollectionsKt___CollectionsKt.toMutableList((Collection) value);
        if (mutableList == null || mutableList.isEmpty()) {
            return;
        }
        if (mutableList.size() > this.maximumSuggestions) {
            Iterator it = mutableList.iterator();
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                }
                BaseViewModel baseViewModel = (BaseViewModel) it.next();
                if ((baseViewModel instanceof VisualRowItemModel) && StringsKt__StringsJVMKt.equals$default(((TrackMetadata) ((VisualRowItemModel) baseViewModel).getMetadata()).getAsin(), asin, false, 2, null)) {
                    break;
                } else {
                    i++;
                }
            }
            if (i >= 0) {
                Object obj = mutableList.get(this.maximumSuggestions);
                mutableList.set(getMaximumSuggestions(), mutableList.get(i));
                Unit unit = Unit.INSTANCE;
                mutableList.set(i, obj);
            } else {
                Log.d(this.TAG, "Failed to add suggested track. Asin not found.");
                return;
            }
        }
        MutableLiveData<List<BaseViewModel>> suggestions = getSuggestions();
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : mutableList) {
            BaseViewModel baseViewModel2 = (BaseViewModel) obj2;
            if (!((baseViewModel2 instanceof VisualRowItemModel) && StringsKt__StringsJVMKt.equals$default(((TrackMetadata) ((VisualRowItemModel) baseViewModel2).getMetadata()).getAsin(), asin, false, 2, null))) {
                arrayList.add(obj2);
            }
        }
        suggestions.setValue(arrayList);
    }

    private final Uri getPlaylistTracksUri(Playlist playlist, Context context) {
        Uri uri = MediaProvider.UdoPlaylists.Tracks.appendTracksToUri(MediaProvider.UdoPlaylists.getContentUriFromLuid(context, "cirrus", playlist.getId()));
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        return uri;
    }
}
