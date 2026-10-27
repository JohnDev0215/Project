package com.amazon.music.curate.provider;

import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlaylistProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J(\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/curate/provider/PlaylistProvider;", "", "editPlaylist", "", Environment.PLAYLIST_PATH, "Lcom/amazon/music/curate/model/Playlist;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "setSuggestionData", "suggestionModels", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "maxSuggestions", "", "header", "", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface PlaylistProvider {
    void editPlaylist(Playlist playlist, FragmentActivity activity);

    void setSuggestionData(List<? extends BaseViewModel> suggestionModels, int maxSuggestions, String header);
}
