package ts.car.usb.v1.usb.gadget;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public enum UsbGadgetFunction {
    NONE(0),
    ADB(1),
    ACCESSORY(2),
    MTP(4),
    MIDI(8),
    PTP(16),
    RNDIS(32),
    AUDIO_SOURCE(64),
    IAP(128),
    EAP(256),
    NCM(512);

    private static Map<Long, UsbGadgetFunction> functions;
    private long value;

    static {
        HashMap map = new HashMap();
        functions = map;
        map.put(Long.valueOf(NONE.value), NONE);
        functions.put(Long.valueOf(ADB.value), ADB);
        functions.put(Long.valueOf(ACCESSORY.value), ACCESSORY);
        functions.put(Long.valueOf(MTP.value), MTP);
        functions.put(Long.valueOf(MIDI.value), MIDI);
        functions.put(Long.valueOf(PTP.value), PTP);
        functions.put(Long.valueOf(RNDIS.value), RNDIS);
        functions.put(Long.valueOf(AUDIO_SOURCE.value), AUDIO_SOURCE);
        functions.put(Long.valueOf(IAP.value), IAP);
        functions.put(Long.valueOf(EAP.value), EAP);
        functions.put(Long.valueOf(NCM.value), NCM);
    }

    UsbGadgetFunction(long value) {
        this.value = value;
    }

    public long getValue() {
        return this.value;
    }

    static UsbGadgetFunction clone(long value) {
        UsbGadgetFunction function = functions.get(Long.valueOf(value));
        if (function == null) {
            return function;
        }
        return NONE;
    }
}
