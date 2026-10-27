package com.amazon.music.share;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.amazon.music.destination.parser.SharedUserPlaylistDeeplinkParser;
import com.amazon.music.library.IdGenerator;
import com.amazon.music.library.PlaylistLibrary;
import com.amazon.music.library.SociallySharePlaylistRequest;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.ShareProvider;
import com.amazon.musicplaylist.model.CannotSharePlaylistException;
import com.amazon.musicplaylist.model.InvalidBenefitsException;
import com.amazon.musicplaylist.model.PlaylistNotFoundException;
import com.amazon.musicplaylist.model.PlaylistVersionException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class UserPlaylistShareProvider extends ShareProvider {
    private static final String TAG = "UserPlaylistShareProvider";
    private AlertDialog.Builder mAlertBuilder;
    private HashMap<String, Integer> mErrorMap;
    private String mErrorMessageKey;
    private String mPlaylistId;
    private PlaylistLibrary mPlaylistLibrary;
    private final String mPlaylistTitle;
    private String mPlaylistVersion;
    private String mPublicPlaylistId;
    private boolean mRejectForUnshareableTracks;
    private String mShareLink;
    private ShareProviderClientAction mShareProviderClientAction;

    public UserPlaylistShareProvider(Context context, String str, String str2, String str3, String str4, PlaylistLibrary playlistLibrary, ShareProviderClientAction shareProviderClientAction, String str5) {
        super(context, str, R$string.dmusic_context_share_playlist, str5, null);
        this.mErrorMessageKey = "DEFAULT_ERROR";
        this.mPlaylistId = str2;
        this.mPlaylistTitle = str3;
        this.mPlaylistVersion = str4;
        this.mPlaylistLibrary = playlistLibrary;
        this.mShareProviderClientAction = shareProviderClientAction;
        this.mCustomShareSupported = true;
        init();
    }

    private void init() {
        this.mRejectForUnshareableTracks = true;
        HashMap<String, Integer> map = new HashMap<>();
        this.mErrorMap = map;
        map.put("CONTAINS_TOO_MANY_TRACKS", Integer.valueOf(R$string.dmusic_playlist_error_too_many_tracks));
        this.mErrorMap.put("CONTAINS_UNSHAREABLE_TRACKS", Integer.valueOf(R$string.dmusic_playlist_error_unshareable_tracks));
        this.mErrorMap.put("NEED_MORE_TRACKS", Integer.valueOf(R$string.dmusic_playlist_error_need_more_tracks));
        this.mErrorMap.put("INVALID_BENEFITS", Integer.valueOf(R$string.dmusic_playlist_error_invalid_benefits));
        this.mErrorMap.put("DEFAULT_ERROR", Integer.valueOf(R$string.dmusic_playlist_error_default));
        this.mErrorMap.put("LIBRARY_NEEDS_SYNC", Integer.valueOf(R$string.dmusic_playlist_error_library_needs_sync));
    }

    @Override // com.amazon.music.share.ShareProvider
    protected Uri getLink() {
        String str = this.mShareLink;
        return appendRefMarker(getBaseUrl().buildUpon().appendPath("user-playlists").appendPath(str.substring(str.lastIndexOf(47) + 1)).build());
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getMessage() {
        return String.format(getString(R$string.dmusic_message_share_user_playlist), this.mPlaylistTitle, getLink());
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubject() {
        return String.format(getString(R$string.dmusic_subject_share_user_playlist), this.mPlaylistTitle);
    }

    @Override // com.amazon.music.share.ShareProvider
    protected EntityIdType getEntityIdType() {
        return EntityIdType.SHARED_PLAYLIST_ID;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityId() {
        return this.mPublicPlaylistId;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getPrivateEntityId() {
        return this.mPlaylistId;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected ShareProvider.ShareProviderType getShareProviderType() {
        return ShareProvider.ShareProviderType.USER_PLAYLIST;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubtitle() {
        return getString(R$string.dmusic_subtitle_share_playlist);
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getTitle() {
        return this.mPlaylistTitle;
    }

    @Override // com.amazon.music.share.ShareProvider
    public void startShare() {
        new AsyncTask<Void, Void, Void>() { // from class: com.amazon.music.share.UserPlaylistShareProvider.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                UserPlaylistShareProvider.this.sociallySharePlaylist();
                return null;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void r2) {
                if (UserPlaylistShareProvider.this.mAlertBuilder != null) {
                    UserPlaylistShareProvider.this.mAlertBuilder.show();
                    UserPlaylistShareProvider.this.mAlertBuilder = null;
                }
            }
        }.execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sociallySharePlaylist() {
        try {
            this.mShareLink = this.mPlaylistLibrary.sociallySharePlaylist(getSociallySharePlaylistRequest()).getUrl().toString();
            setPublicPlaylistId();
            super.startShare();
        } catch (Exception e) {
            handleException(e);
        }
    }

    private SociallySharePlaylistRequest getSociallySharePlaylistRequest() {
        SociallySharePlaylistRequest.Builder builderWithOwnedPlaylist = this.mPlaylistLibrary.getSociallyShareRequestBuilder().withOwnedPlaylist(IdGenerator.generateOwnedPlaylistId(this.mPlaylistId), this.mPlaylistVersion);
        if (this.mRejectForUnshareableTracks) {
            builderWithOwnedPlaylist.rejectForUnshareableTracks();
        }
        return builderWithOwnedPlaylist.build();
    }

    private void handleException(Exception exc) {
        String str = TAG;
        Log.w(str, "SociallySharePlaylistException: " + exc.getMessage());
        Log.w(str, "SociallyShareRequest: playlistId = " + this.mPlaylistId + " playlistTitle = " + this.mPlaylistTitle + " playlistVersion = " + this.mPlaylistVersion);
        AlertDialog.Builder baseAlertDialog = getBaseAlertDialog(getErrorMessageFromMap("DEFAULT_ERROR"));
        int i = R$string.dmusic_context_share_ok;
        this.mAlertBuilder = baseAlertDialog.setPositiveButton(i, (DialogInterface.OnClickListener) null);
        if (exc instanceof CannotSharePlaylistException) {
            try {
                handleCannotSharePlaylistException((List) ((Map) new ObjectMapper().readValue(exc.getMessage(), Map.class)).get("sharingErrors"));
                return;
            } catch (IOException e) {
                Log.e(TAG, "SociallySharePlaylist ObjectMapper error: " + e.getMessage(), e);
                return;
            }
        }
        if (exc instanceof InvalidBenefitsException) {
            this.mAlertBuilder = getBaseAlertDialog(getErrorMessageFromMap("INVALID_BENEFITS")).setPositiveButton(i, (DialogInterface.OnClickListener) null);
            return;
        }
        if (exc instanceof PlaylistVersionException) {
            this.mAlertBuilder = getBaseAlertDialog(getErrorMessageFromMap("LIBRARY_NEEDS_SYNC")).setPositiveButton(i, (DialogInterface.OnClickListener) null);
            this.mShareProviderClientAction.startUserPlaylistsSync(getContext());
        } else {
            if (exc instanceof PlaylistNotFoundException) {
                return;
            }
            Log.e(str, "Unknown SociallySharePlaylist errorType: ", exc);
            this.mAlertBuilder = getBaseAlertDialog(getErrorMessageFromMap("DEFAULT_ERROR")).setPositiveButton(i, (DialogInterface.OnClickListener) null);
        }
    }

    private void handleCannotSharePlaylistException(List<String> list) {
        if (list == null || list.size() < 1) {
            this.mAlertBuilder = getBaseAlertDialog(getErrorMessageFromMap("DEFAULT_ERROR")).setPositiveButton(R$string.dmusic_context_share_ok, (DialogInterface.OnClickListener) null);
            return;
        }
        String str = list.get(0);
        if (list.size() == 1 && "CONTAINS_UNSHAREABLE_TRACKS".equals(str)) {
            this.mAlertBuilder = getBaseAlertDialog(getErrorMessageFromMap(str)).setPositiveButton(R$string.dmusic_context_share, new DialogInterface.OnClickListener() { // from class: com.amazon.music.share.UserPlaylistShareProvider.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    UserPlaylistShareProvider.this.mRejectForUnshareableTracks = false;
                    UserPlaylistShareProvider.this.startShare();
                }
            }).setNegativeButton(R$string.dmusic_context_share_cancel, (DialogInterface.OnClickListener) null);
        } else {
            list.remove("CONTAINS_UNSHAREABLE_TRACKS");
            this.mAlertBuilder = getBaseAlertDialog(getErrorMessageFromMap(list.get(0))).setPositiveButton(R$string.dmusic_context_share_ok, (DialogInterface.OnClickListener) null);
        }
    }

    private AlertDialog.Builder getBaseAlertDialog(int i) {
        return new AlertDialog.Builder(getContext()).setTitle(R$string.dmusic_context_share_playlist).setMessage(i);
    }

    private int getErrorMessageFromMap(String str) {
        if (this.mErrorMap.containsKey(str)) {
            this.mErrorMessageKey = str;
        }
        return this.mErrorMap.get(this.mErrorMessageKey).intValue();
    }

    private void setPublicPlaylistId() {
        this.mPublicPlaylistId = new SharedUserPlaylistDeeplinkParser().parse(Uri.parse(this.mShareLink)).getPlaylistId();
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityTypeString() {
        return getString(R$string.dmusic_context_share_content_type_user_playlist);
    }
}
