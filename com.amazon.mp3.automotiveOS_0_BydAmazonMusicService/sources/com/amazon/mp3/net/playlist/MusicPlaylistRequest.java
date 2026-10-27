package com.amazon.mp3.net.playlist;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.BaseRequestDispatcher;
import com.amazon.mp3.net.service.ServiceApi;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.net.service.ServiceRequest;
import com.amazon.mp3.playlist.PlaylistConfigurationStorage;
import com.amazon.mp3.playlist.api.MC2PlaylistApi;
import com.amazon.mp3.util.Log;
import com.amazon.music.curate.skyfire.Curate;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public enum MusicPlaylistRequest implements ServiceRequest {
    CreatePlaylist("createPlaylist"),
    RemovePlaylists("removePlaylists"),
    RenamePlaylist("renamePlaylist"),
    UpdatePlaylists("updatePlaylists"),
    GetChangedPlaylistsSinceCheckpoint("getChangedPlaylistsSinceCheckpoint"),
    GetConfiguration("getConfiguration"),
    AddPlaylistToLibraryByAsin("addPlaylistToLibraryByAsin"),
    AppendTracksToPlaylist("appendTracksToPlaylist"),
    GetCatalogPlaylistByAsin("getCatalogPlaylistByAsin"),
    GetPlaylistsByIdV2("getPlaylistsByIdV2"),
    GetPlaylistBySeedEntity("getPlaylistBySeedEntity"),
    GetPersonalizedPlaylists("getPersonalizedPlaylists");

    public static final String TAG = MusicPlaylistRequest.class.getSimpleName();
    private final String mRequestName;
    private BaseRequestDispatcher sDispatcher;

    @Override // com.amazon.mp3.net.service.ServiceRequest
    public ServiceApi getApiVersion() {
        return null;
    }

    MusicPlaylistRequest(final String requestName) {
        this.mRequestName = requestName;
    }

    @Override // com.amazon.mp3.net.service.ServiceRequest
    public String getRequestName() {
        return this.mRequestName;
    }

    public BaseRequestDispatcher getDispatcher() {
        if (this.sDispatcher == null) {
            this.sDispatcher = new MusicPlaylistRequestDispatcher();
        }
        return this.sDispatcher;
    }

    public JSONObject execute() throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        return execute(new JSONObject());
    }

    @Override // com.amazon.mp3.net.service.ServiceRequest
    public JSONObject execute(final JSONObject requestParams) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        if (!equals(GetConfiguration)) {
            checkConfigurationsThreshold(AmazonApplication.getContext());
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        JSONObject jSONObjectDispatch = getDispatcher().dispatch(this, requestParams);
        Log.debugBuildOnly(TAG, "PlaylistRequest request %s return in %dms \n with parameters %s \n Response %s", getRequestName(), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis), requestParams, jSONObjectDispatch);
        String requestName = getRequestName();
        if (CreatePlaylist.getRequestName().equals(requestName) || UpdatePlaylists.getRequestName().equals(requestName) || RemovePlaylists.getRequestName().equals(requestName) || RenamePlaylist.getRequestName().equals(requestName) || AddPlaylistToLibraryByAsin.getRequestName().equals(requestName) || AppendTracksToPlaylist.getRequestName().equals(requestName)) {
            Curate.requestLibrarySync();
        }
        return jSONObjectDispatch;
    }

    private void checkConfigurationsThreshold(Context context) {
        if (System.currentTimeMillis() - PlaylistConfigurationStorage.get(context).getConfigurationsCheckTime() < 86400000) {
            return;
        }
        Log.verbose(TAG, "Threshhold reached...refreshing configurations");
        try {
            new MC2PlaylistApi().getConfiguration(context);
        } catch (AbstractHttpClient.CanceledException e) {
            Log.error(TAG, e.getMessage());
        } catch (AbstractHttpClient.FailedException e2) {
            Log.error(TAG, e2.getMessage());
        } catch (AbstractHttpClient.IncompleteResultException e3) {
            Log.error(TAG, e3.getMessage());
        } catch (AbstractHttpClient.UnexpectedHttpStatusException e4) {
            Log.error(TAG, e4.getMessage());
        } catch (ServiceException e5) {
            Log.error(TAG, e5.getMessage());
        }
    }
}
