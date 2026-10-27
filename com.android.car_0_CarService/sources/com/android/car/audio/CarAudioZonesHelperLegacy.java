package com.android.car.audio;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import com.android.car.CarLog;
import com.android.car.R;
import com.android.car.audio.hal.AudioControlWrapperV1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.ToIntFunction;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
class CarAudioZonesHelperLegacy {
    private static final int NO_BUS_FOR_CONTEXT = -1;
    private static final String TAG_CONTEXT = "context";
    private static final String TAG_GROUP = "group";
    private static final String TAG_VOLUME_GROUPS = "volumeGroups";
    private final SparseArray<CarAudioDeviceInfo> mBusToCarAudioDeviceInfo;
    private final CarAudioSettings mCarAudioSettings;
    private final Context mContext;
    private final SparseIntArray mLegacyAudioContextToBus;
    private final int mXmlConfiguration;

    CarAudioZonesHelperLegacy(Context context, int xmlConfiguration, List<CarAudioDeviceInfo> carAudioDeviceInfos, AudioControlWrapperV1 audioControlWrapper, CarAudioSettings carAudioSettings) {
        Objects.requireNonNull(context);
        Objects.requireNonNull(carAudioDeviceInfos);
        Objects.requireNonNull(audioControlWrapper);
        Objects.requireNonNull(carAudioSettings);
        this.mCarAudioSettings = carAudioSettings;
        this.mContext = context;
        this.mXmlConfiguration = xmlConfiguration;
        this.mBusToCarAudioDeviceInfo = generateBusToCarAudioDeviceInfo(carAudioDeviceInfos);
        this.mLegacyAudioContextToBus = loadBusesForLegacyContexts(audioControlWrapper);
    }

    private static SparseIntArray loadBusesForLegacyContexts(AudioControlWrapperV1 audioControlWrapper) {
        SparseIntArray contextToBus = new SparseIntArray();
        for (int legacyContext : CarAudioZonesHelper.LEGACY_CONTEXTS) {
            int bus = audioControlWrapper.getBusForContext(legacyContext);
            validateBusNumber(legacyContext, bus);
            contextToBus.put(legacyContext, bus);
        }
        return contextToBus;
    }

    private static void validateBusNumber(int legacyContext, int bus) {
        if (bus == -1) {
            throw new IllegalArgumentException(String.format("Invalid bus %d was associated with context %s", Integer.valueOf(bus), CarAudioContext.toString(legacyContext)));
        }
    }

    private static SparseArray<CarAudioDeviceInfo> generateBusToCarAudioDeviceInfo(List<CarAudioDeviceInfo> carAudioDeviceInfos) {
        SparseArray<CarAudioDeviceInfo> busToCarAudioDeviceInfo = new SparseArray<>();
        for (CarAudioDeviceInfo carAudioDeviceInfo : carAudioDeviceInfos) {
            int busNumber = parseDeviceAddress(carAudioDeviceInfo.getAddress());
            if (busNumber >= 0) {
                if (busToCarAudioDeviceInfo.get(busNumber) != null) {
                    throw new IllegalArgumentException("Two addresses map to same bus number: " + carAudioDeviceInfo.getAddress() + " and " + busToCarAudioDeviceInfo.get(busNumber).getAddress());
                }
                busToCarAudioDeviceInfo.put(busNumber, carAudioDeviceInfo);
            }
        }
        return busToCarAudioDeviceInfo;
    }

    SparseArray<CarAudioZone> loadAudioZones() {
        CarAudioZone zone = new CarAudioZone(0, "Primary zone");
        for (CarVolumeGroup group : loadVolumeGroups()) {
            zone.addVolumeGroup(group);
            bindContextsForVolumeGroup(group);
        }
        SparseArray<CarAudioZone> carAudioZones = new SparseArray<>();
        carAudioZones.put(0, zone);
        return carAudioZones;
    }

    private void bindContextsForVolumeGroup(CarVolumeGroup group) {
        for (int legacyAudioContext : group.getContexts()) {
            int busNumber = this.mLegacyAudioContextToBus.get(legacyAudioContext);
            CarAudioDeviceInfo info = this.mBusToCarAudioDeviceInfo.get(busNumber);
            group.bind(legacyAudioContext, info);
            if (legacyAudioContext == CarAudioService.DEFAULT_AUDIO_CONTEXT) {
                CarAudioZonesHelper.bindNonLegacyContexts(group, info);
            }
        }
    }

    private List<CarVolumeGroup> loadVolumeGroups() {
        XmlResourceParser parser;
        AttributeSet attrs;
        int type;
        List<CarVolumeGroup> carVolumeGroups = new ArrayList<>();
        try {
            parser = this.mContext.getResources().getXml(this.mXmlConfiguration);
            try {
                attrs = Xml.asAttributeSet(parser);
                do {
                    type = parser.next();
                    if (type == 1) {
                        break;
                    }
                } while (type != 2);
            } finally {
            }
        } catch (Exception e) {
            Log.e(CarLog.TAG_AUDIO, "Error parsing volume groups configuration", e);
        }
        if (!TAG_VOLUME_GROUPS.equals(parser.getName())) {
            throw new IllegalArgumentException("Meta-data does not start with volumeGroups tag");
        }
        int outerDepth = parser.getDepth();
        int id = 0;
        while (true) {
            int type2 = parser.next();
            if (type2 == 1 || (type2 == 3 && parser.getDepth() <= outerDepth)) {
                break;
            }
            if (type2 != 3 && TAG_GROUP.equals(parser.getName())) {
                carVolumeGroups.add(parseVolumeGroup(id, attrs, parser));
                id++;
            }
        }
        if (parser != null) {
            parser.close();
        }
        return carVolumeGroups;
    }

    private CarVolumeGroup parseVolumeGroup(int id, AttributeSet attrs, XmlResourceParser parser) throws XmlPullParserException, IOException {
        List<Integer> contexts = new ArrayList<>();
        int innerDepth = parser.getDepth();
        while (true) {
            int type = parser.next();
            if (type == 1 || (type == 3 && parser.getDepth() <= innerDepth)) {
                break;
            }
            if (type != 3 && TAG_CONTEXT.equals(parser.getName())) {
                TypedArray c = this.mContext.getResources().obtainAttributes(attrs, R.styleable.volumeGroups_context);
                contexts.add(Integer.valueOf(c.getInt(0, -1)));
                c.recycle();
            }
        }
        return new CarVolumeGroup(this.mCarAudioSettings, 0, id, contexts.stream().mapToInt(new ToIntFunction() { // from class: com.android.car.audio.-$$Lambda$CarAudioZonesHelperLegacy$a_qUQniZEZrnCYadb8JWi46Doe8
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Integer) obj).intValue();
            }
        }).filter(new IntPredicate() { // from class: com.android.car.audio.-$$Lambda$CarAudioZonesHelperLegacy$CHnHz6w92bXafaLHQzv5cqhNmbM
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return CarAudioZonesHelperLegacy.lambda$parseVolumeGroup$1(i);
            }
        }).toArray());
    }

    static /* synthetic */ boolean lambda$parseVolumeGroup$1(int i) {
        return i >= 0;
    }

    private static int parseDeviceAddress(String address) {
        String[] words = address.split("_");
        int addressParsed = -1;
        if (words[0].toLowerCase().startsWith("bus")) {
            try {
                addressParsed = Integer.parseInt(words[0].substring(3));
            } catch (NumberFormatException e) {
            }
        }
        if (addressParsed < 0) {
            return -1;
        }
        return addressParsed;
    }
}
