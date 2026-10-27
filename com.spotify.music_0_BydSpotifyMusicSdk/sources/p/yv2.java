package p;

import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.webapi.service.models.Pager;
import com.spotify.webapi.service.models.Playlist;
import com.spotify.webapi.service.models.PlaylistTrack;
import com.spotify.webapi.service.models.Search;
import com.spotify.webapi.service.models.UserPrivate;
import com.spotify.webapi.service.models.views.View;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface yv2 {
    @sz0("v1/me/shows/contains")
    Single<Boolean[]> a(@ez1("ids") String str);

    @sz0("v1/me/following/contains?type=artist")
    Single<Boolean[]> b(@ez1("ids") String str);

    @sz0("v1/me/albums/contains")
    Single<Boolean[]> c(@ez1("ids") String str);

    @sz0("v1/me")
    Single<UserPrivate> d();

    @hr1("v1/playlists/{playlist_id}/followers")
    Completable e(@ns1("playlist_id") String str);

    @sz0("v1/search")
    Single<Search> f(@ez1(RxProductState.Keys.KEY_TYPE) String str, @ez1("q") String str2, @ez1("limit") String str3, @ez1("best_match") boolean z, @ez1("market") String str4);

    @sz0("v1/views/{id}")
    Single<View> g(@ns1("id") String str, @iz1 Map<String, Object> map);

    @hr1("v1/me/shows")
    Completable h(@ez1(encoded = true, value = "ids") String str);

    @sz0("v1/playlists/{playlist_id}")
    Single<Playlist> i(@ns1("playlist_id") String str, @iz1 Map<String, Object> map);

    @sz0("v1/me/tracks/contains")
    Single<Boolean[]> j(@ez1("ids") String str);

    @hr1("v1/me/episodes")
    Completable k(@ez1("ids") String str);

    @f50("v1/me/following?type=artist")
    Completable l(@ez1("ids") String str);

    @sz0("v1/playlists/{playlist_id}/tracks")
    Single<Pager<PlaylistTrack>> m(@ns1("playlist_id") String str, @iz1 Map<String, Object> map);

    @f50("v1/playlists/{playlist_id}/followers")
    Completable n(@ns1("playlist_id") String str);

    @hr1("v1/me/following?type=artist")
    Completable o(@ez1("ids") String str);

    @hr1("v1/me/tracks")
    Completable p(@ez1("ids") String str);

    @hr1("v1/me/albums")
    Completable q(@ez1("ids") String str);

    @f50("v1/me/episodes")
    Completable r(@ez1("ids") String str);

    @sz0("v1/me/episodes/contains")
    Single<Boolean[]> s(@ez1("ids") String str);

    @f50("v1/me/albums")
    Completable t(@ez1("ids") String str);

    @f50("v1/me/tracks")
    Completable u(@ez1("ids") String str);

    @sz0("v1/playlists/{playlist_id}/followers/contains")
    Single<Boolean[]> v(@ns1("playlist_id") String str, @ez1("ids") String str2);

    @f50("v1/me/shows")
    Completable w(@ez1(encoded = true, value = "ids") String str);
}
