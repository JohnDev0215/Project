package com.amazon.mp3.bluetooth;

import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import com.visualon.OSMPUtils.voOSType;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import org.objectweb.asm.commons.GeneratorAdapter;

/* JADX INFO: loaded from: classes3.dex */
public class BluetoothKeyHandler {
    private static SparseArray<String> sKeycodeMap;

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        sKeycodeMap = sparseArray;
        sparseArray.put(62, "com.amazon.mp3.playerservicecommand.togglepause");
        sKeycodeMap.put(67, "com.amazon.mp3.playerservicecommand.previous");
        sKeycodeMap.put(voOSType.VOOSMP_PID_PREFERENCE, "com.amazon.mp3.playerservicecommand.previous");
        sKeycodeMap.put(138, "com.amazon.mp3.playerservicecommand.togglepause");
        sKeycodeMap.put(voOSType.VOOSMP_PID_ANALYTICS_EXPORT, "com.amazon.mp3.playerservicecommand.next");
        sKeycodeMap.put(voOSType.VOOSMP_PID_ANALYTICS_EXPORT_GET_PACKET, "com.amazon.mp3.playerservicecommand.mute");
        sKeycodeMap.put(voOSType.VOOSMP_PID_ANALYTICS_EXPORT_LISTENER, "com.amazon.mp3.playerservicecommand.volumedown");
        sKeycodeMap.put(142, "com.amazon.mp3.playerservicecommand.volumeup");
        sKeycodeMap.put(85, "com.amazon.mp3.playerservicecommand.togglepause");
        sKeycodeMap.put(GeneratorAdapter.AND, "com.amazon.mp3.playerservicecommand.play");
        sKeycodeMap.put(WorkQueueKt.MASK, "com.amazon.mp3.playerservicecommand.pause");
        sKeycodeMap.put(86, "com.amazon.mp3.playerservicecommand.stop");
        sKeycodeMap.put(87, "com.amazon.mp3.playerservicecommand.next");
        sKeycodeMap.put(88, "com.amazon.mp3.playerservicecommand.previous");
        sKeycodeMap.put(84, "https://music.amazon.com/search?source=keyCodeSearch");
    }

    public static String getAction(int keyCode) {
        return sKeycodeMap.get(keyCode);
    }

    public static boolean shouldHandleBluetoothKeyEvent(final KeyEvent event, final View currentFocus) {
        return ((event.getFlags() & 2) == 0) && (currentFocus == null || !(currentFocus instanceof EditText));
    }
}
