package com.amazon.mp3.platform.providers.library;

import android.content.Context;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemType;
import com.amazon.music.platform.data.entity.SingleType;
import com.amazon.music.platform.data.library.LibraryUseCase;
import com.amazon.music.platform.providers.LibraryProvider;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\t\u001a\u00020\n\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u000b0\bH\u0016J\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u0002H\u000b\u0018\u00010\b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u0007H\u0016R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/platform/providers/library/LibraryProviderImpl;", "Lcom/amazon/music/platform/providers/LibraryProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mappings", "", "Lcom/amazon/music/platform/data/entity/EntityItemType;", "Lcom/amazon/music/platform/data/library/LibraryUseCase;", "addInteractor", "", "T", "Lcom/amazon/music/platform/data/entity/EntityItem;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "interactor", "getInteractor", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class LibraryProviderImpl implements LibraryProvider {
    private final Map<EntityItemType, LibraryUseCase<?>> mappings;

    public LibraryProviderImpl(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.mappings = new LinkedHashMap();
        TrackInteractor trackInteractor = new TrackInteractor(context, null, null, 6, null);
        PlaylistInteractor playlistInteractor = new PlaylistInteractor(context, null, null, 6, null);
        AlbumInteractor albumInteractor = new AlbumInteractor(context, null, 2, null);
        addInteractor(SingleType.Track.INSTANCE, trackInteractor);
        addInteractor(CollectionType.Playlist.INSTANCE, playlistInteractor);
        addInteractor(CollectionType.Album.INSTANCE, albumInteractor);
    }

    public <T extends EntityItem> void addInteractor(EntityItemType type, LibraryUseCase<? extends T> interactor) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(interactor, "interactor");
        this.mappings.put(type, interactor);
    }

    @Override // com.amazon.music.platform.providers.LibraryProvider
    public <T extends EntityItem> LibraryUseCase<T> getInteractor(EntityItemType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return (LibraryUseCase) this.mappings.get(type);
    }
}
