package com.amazon.mp3.library.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.music.views.library.models.CompactHeaderModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryPreferences.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0016\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002J\u0016\u0010\r\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0003\u001a\u00020\u0002J\u0016\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0002J\u0016\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000fJ\u0016\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/library/mylibrary/LibraryPreferences;", "", "Landroid/content/Context;", "context", "Landroid/content/SharedPreferences;", "getPreferences", "", "isLibraryMusicToggleSelected", "", "writeIsLibraryMusicToggleSelected", "readIsLibraryMusicToggleSelected", "Lcom/amazon/music/views/library/models/CompactHeaderModel;", "model", "writeProfileModel", "readProfileModel", "", "clickCount", "writeSongsBannerDismissCount", "readSongsBannerDismissCount", "writeFollowedArtistsBannerDismissCount", "writeLibraryArtistsBannerDismissCount", "readFollowedArtistsBannerDismissCount", "readLibraryArtistsBannerDismissCount", "", "readFollowedArtistsCheckpoint", "readLikedAlbumsCheckpoint", "clear", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryPreferences {
    public static final LibraryPreferences INSTANCE = new LibraryPreferences();

    private LibraryPreferences() {
    }

    public final void writeIsLibraryMusicToggleSelected(Context context, boolean isLibraryMusicToggleSelected) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences.Editor editorEdit = getPreferences(context).edit();
        editorEdit.putBoolean("isLibraryMusicToggleSelected", isLibraryMusicToggleSelected);
        editorEdit.apply();
    }

    public final boolean readIsLibraryMusicToggleSelected(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getPreferences(context).getBoolean("isLibraryMusicToggleSelected", true);
    }

    public final void writeProfileModel(Context context, CompactHeaderModel model) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(model, "model");
        SharedPreferences.Editor editorEdit = getPreferences(context).edit();
        editorEdit.putString("profileHeaderNameKey", model.getPrimaryText());
        editorEdit.putString("profileHeaderTierKey", model.getLabel());
        editorEdit.putString("profileHeaderPictureKey", model.getImageUrl());
        editorEdit.apply();
    }

    public final CompactHeaderModel readProfileModel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences preferences = getPreferences(context);
        String string = preferences.getString("profileHeaderNameKey", null);
        String string2 = preferences.getString("profileHeaderTierKey", null);
        String string3 = preferences.getString("profileHeaderPictureKey", null);
        if (string == null && string2 == null && string3 == null) {
            return null;
        }
        return new CompactHeaderModel(null, string, string2, string3, 1, null);
    }

    public final void writeSongsBannerDismissCount(Context context, int clickCount) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences.Editor editorEdit = getPreferences(context).edit();
        editorEdit.putInt("songsLikesBannerKey", clickCount);
        editorEdit.apply();
    }

    public final int readSongsBannerDismissCount(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getPreferences(context).getInt("songsLikesBannerKey", 0);
    }

    public final void writeFollowedArtistsBannerDismissCount(Context context, int clickCount) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences.Editor editorEdit = getPreferences(context).edit();
        editorEdit.putInt("followedArtistsBannerKey", clickCount);
        editorEdit.apply();
    }

    public final void writeLibraryArtistsBannerDismissCount(Context context, int clickCount) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences.Editor editorEdit = getPreferences(context).edit();
        editorEdit.putInt("libraryArtistsBannerKey", clickCount);
        editorEdit.apply();
    }

    public final int readFollowedArtistsBannerDismissCount(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getPreferences(context).getInt("followedArtistsBannerKey", 0);
    }

    public final int readLibraryArtistsBannerDismissCount(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getPreferences(context).getInt("libraryArtistsBannerKey", 0);
    }

    public final long readFollowedArtistsCheckpoint(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getPreferences(context).getLong("followedArtistsDeltaSyncKey", 0L);
    }

    public final long readLikedAlbumsCheckpoint(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getPreferences(context).getLong("likedAlbumsDeltaSyncKey", 0L);
    }

    public final void clear(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        getPreferences(context).edit().clear().apply();
    }

    private final SharedPreferences getPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.amazon.mp3_MyLibrary", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…    Context.MODE_PRIVATE)");
        return sharedPreferences;
    }
}
