package com.amazon.music.curate.provider;

import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.curate.model.Playlist;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: compiled from: NavigationProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Deprecated(message = "No longer used", replaceWith = @ReplaceWith(expression = "LibraryNavigationProvider", imports = {}))
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0005H&J\b\u0010\n\u001a\u00020\u0003H&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0005H&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/curate/provider/NavigationProvider;", "", "goToAlbum", "", "asin", "", "goToArtist", "goToBrowseHome", "goToEditPlaylist", "id", "goToMyMusic", "goToPlaylist", Environment.PLAYLIST_PATH, "Lcom/amazon/music/curate/model/Playlist;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "goToProfile", "profileId", "goToSimilarMusic", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface NavigationProvider {
    void goToAlbum(String asin);

    void goToArtist(String asin);

    void goToBrowseHome();

    void goToEditPlaylist(String id);

    void goToMyMusic();

    void goToPlaylist(Playlist playlist, FragmentActivity activity);

    void goToProfile(String profileId);

    void goToSimilarMusic(String asin);
}
