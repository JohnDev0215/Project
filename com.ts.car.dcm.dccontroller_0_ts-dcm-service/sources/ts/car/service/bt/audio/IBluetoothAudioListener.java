package ts.car.service.bt.audio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface IBluetoothAudioListener {
    default void onAudioFocusStateChanged(int state) {
    }

    default void onConnectionStateChanged(String address, int state) {
    }

    default void onMediaItemListRetrieved(String address, int handle, List<MetadataItem> list) {
    }

    default void onMetadataChanged(String address, MetadataItem metadata) {
    }

    default void onPlayPositionChanged(String address, int position) {
    }

    default void onPlayStateChanged(String address, int state) {
    }

    default void onPlayerAppSettingsChanged(String address, Map<Integer, Integer> settings) {
    }

    default void onPlayerAppSettingValuesList(String address, Map<Integer, ArrayList<Integer>> values) {
    }
}
