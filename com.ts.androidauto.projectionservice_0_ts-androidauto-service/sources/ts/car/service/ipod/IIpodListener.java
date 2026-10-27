package ts.car.service.ipod;

import java.util.List;
import ts.car.service.ipod.data.IpodDevice;
import ts.car.service.ipod.data.IpodJumpIndexInfo;
import ts.car.service.ipod.data.IpodMediaListInfo;
import ts.car.service.ipod.data.IpodNowPlayingUpdate;
import ts.car.service.ipod.data.IpodTrackInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface IIpodListener {
    default void onConnectStatusChanged(int connectStatus, IpodDevice device) {
    }

    default void onNowPlayingUpdated(IpodNowPlayingUpdate update) {
    }

    default void onPlaybackListUpdated(boolean isAvailable, String[] pids) {
    }

    default void onArtworkInfoUpdated(int artworkSize, byte[] artworkData) {
    }

    default void onRequestTrackInfoSuccess(IpodTrackInfo ipodTrackInfo) {
    }

    default void onRequestTrackInfoFailed(int errorCode) {
    }

    default void onRequestArtworkDataSuccess(int artworkSize, byte[] artworkData) {
    }

    default void onRequestArtworkDataFailed(int errorCode) {
    }

    default void onRequestJumpListSuccess(List<IpodJumpIndexInfo> ipodJumpIndexList) {
    }

    default void onRequestJumpListFailed(int errorCode) {
    }

    default void onRequestSearchMediaListSuccess(IpodMediaListInfo ipodMediaListInfo) {
    }

    default void onRequestSearchMediaListFailed(int errorCode) {
    }
}
