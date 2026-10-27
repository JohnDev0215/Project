package com.byd.music.utils;

import com.blankj.utilcode.util.Utils;
import com.byd.music.R;
import com.byd.music.fragment.bluetooth.BluetoothViewModel;

/* JADX INFO: loaded from: classes5.dex */
public class WidgetHintTextUtil {
    public static String getHintMusic(int i) {
        if (i != 6) {
            if (i == 9) {
                return Utils.getApp().getString(R.string.widget_no_radio_hint);
            }
        } else if (!BluetoothViewModel.isA2DPConnected.get()) {
            return Utils.getApp().getString(R.string.widget_no_bt_connect_music_hint);
        }
        return Utils.getApp().getString(R.string.widget_no_music_hint);
    }

    public static String getHintArtist(int i) {
        if (i != 6) {
            if (i == 9) {
                return Utils.getApp().getString(R.string.widget_no_radio_artist_hint);
            }
        } else if (!BluetoothViewModel.isA2DPConnected.get()) {
            return Utils.getApp().getString(R.string.widget_no_bt_connect_artist_hint);
        }
        return Utils.getApp().getString(R.string.widget_no_artist_hint);
    }
}
