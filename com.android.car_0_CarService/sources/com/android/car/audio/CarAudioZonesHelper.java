package com.android.car.audio;

import android.media.AudioDeviceAttributes;
import android.media.AudioDeviceInfo;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import androidx.core.app.NotificationCompat;
import com.android.internal.util.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes3.dex */
class CarAudioZonesHelper {
    private static final String ATTR_CONTEXT_NAME = "context";
    private static final String ATTR_DEVICE_ADDRESS = "address";
    private static final String ATTR_IS_PRIMARY = "isPrimary";
    private static final String ATTR_OCCUPANT_ZONE_ID = "occupantZoneId";
    private static final String ATTR_VERSION = "version";
    private static final String ATTR_ZONE_ID = "audioZoneId";
    private static final String ATTR_ZONE_NAME = "name";
    private static final Map<String, Integer> CONTEXT_NAME_MAP;
    private static final int INVALID_VERSION = -1;
    static final int[] LEGACY_CONTEXTS;
    private static final String NAMESPACE = null;
    private static final List<Integer> NON_LEGACY_CONTEXTS;
    private static final SparseIntArray SUPPORTED_VERSIONS;
    private static final int SUPPORTED_VERSION_1 = 1;
    private static final int SUPPORTED_VERSION_2 = 2;
    private static final String TAG_AUDIO_DEVICE = "device";
    private static final String TAG_AUDIO_ZONE = "zone";
    private static final String TAG_AUDIO_ZONES = "zones";
    private static final String TAG_CONTEXT = "context";
    private static final String TAG_INPUT_DEVICE = "inputDevice";
    private static final String TAG_INPUT_DEVICES = "inputDevices";
    private static final String TAG_ROOT = "carAudioConfiguration";
    private static final String TAG_VOLUME_GROUP = "group";
    private static final String TAG_VOLUME_GROUPS = "volumeGroups";
    private final Map<String, CarAudioDeviceInfo> mAddressToCarAudioDeviceInfo;
    private final Map<String, AudioDeviceInfo> mAddressToInputAudioDeviceInfo;
    private final Set<Integer> mAudioZoneIds;
    private final CarAudioSettings mCarAudioSettings;
    private int mCurrentVersion;
    private final Set<String> mInputAudioDevices;
    private final InputStream mInputStream;
    private int mNextSecondaryZoneId;
    private final SparseIntArray mZoneIdToOccupantZoneIdMapping;

    static {
        HashMap map = new HashMap(CarAudioContext.CONTEXTS.length);
        CONTEXT_NAME_MAP = map;
        map.put("music", 1);
        CONTEXT_NAME_MAP.put(NotificationCompat.CATEGORY_NAVIGATION, 2);
        CONTEXT_NAME_MAP.put("voice_command", 3);
        CONTEXT_NAME_MAP.put("call_ring", 4);
        CONTEXT_NAME_MAP.put(NotificationCompat.CATEGORY_CALL, 5);
        CONTEXT_NAME_MAP.put(NotificationCompat.CATEGORY_ALARM, 6);
        CONTEXT_NAME_MAP.put("notification", 7);
        CONTEXT_NAME_MAP.put("system_sound", 8);
        CONTEXT_NAME_MAP.put("emergency", 9);
        CONTEXT_NAME_MAP.put("safety", 10);
        CONTEXT_NAME_MAP.put("vehicle_status", 11);
        CONTEXT_NAME_MAP.put("announcement", 12);
        SparseIntArray sparseIntArray = new SparseIntArray(2);
        SUPPORTED_VERSIONS = sparseIntArray;
        sparseIntArray.put(1, 1);
        SUPPORTED_VERSIONS.put(2, 2);
        LEGACY_CONTEXTS = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        NON_LEGACY_CONTEXTS = new ArrayList(CarAudioContext.CONTEXTS.length - LEGACY_CONTEXTS.length);
        for (int audioContext : CarAudioContext.CONTEXTS) {
            if (!isLegacyContext(audioContext)) {
                NON_LEGACY_CONTEXTS.add(Integer.valueOf(audioContext));
            }
        }
    }

    private static boolean isLegacyContext(int audioContext) {
        return Arrays.binarySearch(LEGACY_CONTEXTS, audioContext) >= 0;
    }

    static void bindNonLegacyContexts(CarVolumeGroup group, CarAudioDeviceInfo info) {
        Iterator<Integer> it = NON_LEGACY_CONTEXTS.iterator();
        while (it.hasNext()) {
            int audioContext = it.next().intValue();
            group.bind(audioContext, info);
        }
    }

    CarAudioZonesHelper(CarAudioSettings carAudioSettings, InputStream inputStream, List<CarAudioDeviceInfo> carAudioDeviceInfos, AudioDeviceInfo[] inputDeviceInfo) {
        Objects.requireNonNull(carAudioSettings);
        this.mCarAudioSettings = carAudioSettings;
        Objects.requireNonNull(inputStream);
        this.mInputStream = inputStream;
        Objects.requireNonNull(carAudioDeviceInfos);
        Objects.requireNonNull(inputDeviceInfo);
        this.mAddressToCarAudioDeviceInfo = generateAddressToInfoMap(carAudioDeviceInfos);
        this.mAddressToInputAudioDeviceInfo = generateAddressToInputAudioDeviceInfoMap(inputDeviceInfo);
        this.mNextSecondaryZoneId = 1;
        this.mZoneIdToOccupantZoneIdMapping = new SparseIntArray();
        this.mAudioZoneIds = new HashSet();
        this.mInputAudioDevices = new HashSet();
    }

    SparseIntArray getCarAudioZoneIdToOccupantZoneIdMapping() {
        return this.mZoneIdToOccupantZoneIdMapping;
    }

    SparseArray<CarAudioZone> loadAudioZones() throws XmlPullParserException, IOException {
        return parseCarAudioZones(this.mInputStream);
    }

    private static Map<String, CarAudioDeviceInfo> generateAddressToInfoMap(List<CarAudioDeviceInfo> carAudioDeviceInfos) {
        return (Map) carAudioDeviceInfos.stream().filter(new Predicate() { // from class: com.android.car.audio.-$$Lambda$CarAudioZonesHelper$s14Z-USk6zRxyyGY7iXQ2jwCrEo
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CarAudioZonesHelper.lambda$generateAddressToInfoMap$0((CarAudioDeviceInfo) obj);
            }
        }).collect(Collectors.toMap(new Function() { // from class: com.android.car.audio.-$$Lambda$UdwwofXc0hutt3ka2ru4kt78qbA
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CarAudioDeviceInfo) obj).getAddress();
            }
        }, new Function() { // from class: com.android.car.audio.-$$Lambda$CarAudioZonesHelper$ROow2vJ1w_j61-79-fJbj5pGjKM
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CarAudioZonesHelper.lambda$generateAddressToInfoMap$1((CarAudioDeviceInfo) obj);
            }
        }));
    }

    static /* synthetic */ boolean lambda$generateAddressToInfoMap$0(CarAudioDeviceInfo info) {
        return !TextUtils.isEmpty(info.getAddress());
    }

    static /* synthetic */ CarAudioDeviceInfo lambda$generateAddressToInfoMap$1(CarAudioDeviceInfo info) {
        return info;
    }

    private static Map<String, AudioDeviceInfo> generateAddressToInputAudioDeviceInfoMap(AudioDeviceInfo[] inputAudioDeviceInfos) {
        HashMap<String, AudioDeviceInfo> deviceAddressToInputDeviceMap = new HashMap<>(inputAudioDeviceInfos.length);
        for (AudioDeviceInfo device : inputAudioDeviceInfos) {
            if (device.isSource()) {
                deviceAddressToInputDeviceMap.put(device.getAddress(), device);
            }
        }
        return deviceAddressToInputDeviceMap;
    }

    private SparseArray<CarAudioZone> parseCarAudioZones(InputStream stream) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", NAMESPACE != null);
        parser.setInput(stream, null);
        parser.nextTag();
        parser.require(2, NAMESPACE, TAG_ROOT);
        int versionNumber = Integer.parseInt(parser.getAttributeValue(NAMESPACE, "version"));
        if (SUPPORTED_VERSIONS.get(versionNumber, -1) == -1) {
            throw new IllegalArgumentException("Latest Supported version:2 , got version:" + versionNumber);
        }
        this.mCurrentVersion = versionNumber;
        while (parser.next() != 3) {
            if (parser.getEventType() == 2) {
                if (TAG_AUDIO_ZONES.equals(parser.getName())) {
                    return parseAudioZones(parser);
                }
                skip(parser);
            }
        }
        throw new RuntimeException("zones is missing from configuration");
    }

    private SparseArray<CarAudioZone> parseAudioZones(XmlPullParser parser) throws XmlPullParserException, IOException {
        SparseArray<CarAudioZone> carAudioZones = new SparseArray<>();
        while (parser.next() != 3) {
            if (parser.getEventType() == 2) {
                if (TAG_AUDIO_ZONE.equals(parser.getName())) {
                    CarAudioZone zone = parseAudioZone(parser);
                    verifyOnlyOnePrimaryZone(zone, carAudioZones);
                    carAudioZones.put(zone.getId(), zone);
                } else {
                    skip(parser);
                }
            }
        }
        verifyPrimaryZonePresent(carAudioZones);
        return carAudioZones;
    }

    private void verifyOnlyOnePrimaryZone(CarAudioZone newZone, SparseArray<CarAudioZone> zones) {
        if (newZone.getId() == 0 && zones.contains(0)) {
            throw new RuntimeException("More than one zone parsed with primary audio zone ID: 0");
        }
    }

    private void verifyPrimaryZonePresent(SparseArray<CarAudioZone> zones) {
        if (!zones.contains(0)) {
            throw new RuntimeException("Primary audio zone is required");
        }
    }

    private CarAudioZone parseAudioZone(XmlPullParser parser) throws XmlPullParserException, IOException {
        boolean isPrimary = Boolean.parseBoolean(parser.getAttributeValue(NAMESPACE, ATTR_IS_PRIMARY));
        String zoneName = parser.getAttributeValue(NAMESPACE, "name");
        int audioZoneId = getZoneId(isPrimary, parser);
        parseOccupantZoneId(audioZoneId, parser);
        CarAudioZone zone = new CarAudioZone(audioZoneId, zoneName);
        while (parser.next() != 3) {
            if (parser.getEventType() == 2) {
                if (TAG_VOLUME_GROUPS.equals(parser.getName())) {
                    parseVolumeGroups(parser, zone);
                } else if (TAG_INPUT_DEVICES.equals(parser.getName())) {
                    parseInputAudioDevices(parser, zone);
                } else {
                    skip(parser);
                }
            }
        }
        return zone;
    }

    private int getZoneId(boolean isPrimary, XmlPullParser parser) {
        String audioZoneIdString = parser.getAttributeValue(NAMESPACE, ATTR_ZONE_ID);
        if (isVersionOne()) {
            Preconditions.checkArgument(audioZoneIdString == null, "Invalid audio attribute %s, Please update car audio configurations file to version to 2 to use it.", new Object[]{ATTR_ZONE_ID});
            if (isPrimary) {
                return 0;
            }
            return getNextSecondaryZoneId();
        }
        if (isPrimary && audioZoneIdString == null) {
            return 0;
        }
        Objects.requireNonNull(audioZoneIdString, new Supplier() { // from class: com.android.car.audio.-$$Lambda$CarAudioZonesHelper$7ibk-Xk0IrXU6Z9Dskwx9hOZx1M
            @Override // java.util.function.Supplier
            public final Object get() {
                return CarAudioZonesHelper.lambda$getZoneId$2();
            }
        });
        int zoneId = parsePositiveIntAttribute(ATTR_ZONE_ID, audioZoneIdString);
        if (isPrimary) {
            Preconditions.checkArgument(zoneId == 0, "Primary zone %s must be %d or it can be left empty.", new Object[]{ATTR_ZONE_ID, 0});
        } else {
            Preconditions.checkArgument(zoneId != 0, "%s can only be %d for primary zone.", new Object[]{ATTR_ZONE_ID, 0});
        }
        validateAudioZoneIdIsUnique(zoneId);
        return zoneId;
    }

    static /* synthetic */ String lambda$getZoneId$2() {
        return "Requires audioZoneId for all audio zones.";
    }

    private void parseOccupantZoneId(int audioZoneId, XmlPullParser parser) {
        String occupantZoneIdString = parser.getAttributeValue(NAMESPACE, ATTR_OCCUPANT_ZONE_ID);
        if (isVersionOne()) {
            Preconditions.checkArgument(occupantZoneIdString == null, "Invalid audio attribute %s, Please update car audio configurations file to version to 2 to use it.", new Object[]{ATTR_OCCUPANT_ZONE_ID});
        } else {
            if (occupantZoneIdString == null) {
                return;
            }
            int occupantZoneId = parsePositiveIntAttribute(ATTR_OCCUPANT_ZONE_ID, occupantZoneIdString);
            validateOccupantZoneIdIsUnique(occupantZoneId);
            this.mZoneIdToOccupantZoneIdMapping.put(audioZoneId, occupantZoneId);
        }
    }

    private int parsePositiveIntAttribute(String attribute, String integerString) {
        try {
            return Integer.parseUnsignedInt(integerString);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new IllegalArgumentException(attribute + " must be a positive integer, but was \"" + integerString + "\" instead.", e);
        }
    }

    private void parseInputAudioDevices(XmlPullParser parser, CarAudioZone zone) throws XmlPullParserException, IOException {
        if (isVersionOne()) {
            throw new IllegalStateException("inputDevices are not supported in car_audio_configuration.xml version 1");
        }
        while (parser.next() != 3) {
            if (parser.getEventType() == 2) {
                if (TAG_INPUT_DEVICE.equals(parser.getName())) {
                    String audioDeviceAddress = parser.getAttributeValue(NAMESPACE, ATTR_DEVICE_ADDRESS);
                    validateInputAudioDeviceAddress(audioDeviceAddress);
                    AudioDeviceInfo info = this.mAddressToInputAudioDeviceInfo.get(audioDeviceAddress);
                    Preconditions.checkArgument(info != null, "%s %s of %s does not exist, add input device to audio_policy_configuration.xml.", new Object[]{ATTR_DEVICE_ADDRESS, audioDeviceAddress, TAG_INPUT_DEVICE});
                    zone.addInputAudioDevice(new AudioDeviceAttributes(info));
                }
                skip(parser);
            }
        }
    }

    private void validateInputAudioDeviceAddress(String audioDeviceAddress) {
        Objects.requireNonNull(audioDeviceAddress, new Supplier() { // from class: com.android.car.audio.-$$Lambda$CarAudioZonesHelper$Z8Oe0RE36ZApNSeM4yQfSazVa2U
            @Override // java.util.function.Supplier
            public final Object get() {
                return CarAudioZonesHelper.lambda$validateInputAudioDeviceAddress$3();
            }
        });
        Preconditions.checkArgument(!audioDeviceAddress.isEmpty(), "%s %s attribute can not be empty.", new Object[]{TAG_INPUT_DEVICE, ATTR_DEVICE_ADDRESS});
        if (this.mInputAudioDevices.contains(audioDeviceAddress)) {
            throw new IllegalArgumentException("inputDevice " + audioDeviceAddress + " repeats, " + TAG_INPUT_DEVICES + " can not repeat.");
        }
        this.mInputAudioDevices.add(audioDeviceAddress);
    }

    static /* synthetic */ String lambda$validateInputAudioDeviceAddress$3() {
        return "inputDevice address attribute must be present.";
    }

    private void validateOccupantZoneIdIsUnique(int occupantZoneId) {
        if (this.mZoneIdToOccupantZoneIdMapping.indexOfValue(occupantZoneId) > -1) {
            throw new IllegalArgumentException("occupantZoneId " + occupantZoneId + " is already associated with a zone");
        }
    }

    private void validateAudioZoneIdIsUnique(int audioZoneId) {
        if (this.mAudioZoneIds.contains(Integer.valueOf(audioZoneId))) {
            throw new IllegalArgumentException("audioZoneId " + audioZoneId + " is already associated with a zone");
        }
        this.mAudioZoneIds.add(Integer.valueOf(audioZoneId));
    }

    private void parseVolumeGroups(XmlPullParser parser, CarAudioZone zone) throws XmlPullParserException, IOException {
        int groupId = 0;
        while (parser.next() != 3) {
            if (parser.getEventType() == 2) {
                if (TAG_VOLUME_GROUP.equals(parser.getName())) {
                    zone.addVolumeGroup(parseVolumeGroup(parser, zone.getId(), groupId));
                    groupId++;
                } else {
                    skip(parser);
                }
            }
        }
    }

    private CarVolumeGroup parseVolumeGroup(XmlPullParser parser, int zoneId, int groupId) throws XmlPullParserException, IOException {
        CarVolumeGroup group = new CarVolumeGroup(this.mCarAudioSettings, zoneId, groupId);
        while (parser.next() != 3) {
            if (parser.getEventType() == 2) {
                if (TAG_AUDIO_DEVICE.equals(parser.getName())) {
                    String address = parser.getAttributeValue(NAMESPACE, ATTR_DEVICE_ADDRESS);
                    validateOutputDeviceExist(address);
                    parseVolumeGroupContexts(parser, group, address);
                } else {
                    skip(parser);
                }
            }
        }
        return group;
    }

    private void validateOutputDeviceExist(String address) {
        if (!this.mAddressToCarAudioDeviceInfo.containsKey(address)) {
            throw new IllegalStateException(String.format("Output device address %s does not belong to any configured output device.", address));
        }
    }

    private void parseVolumeGroupContexts(XmlPullParser parser, CarVolumeGroup group, String address) throws XmlPullParserException, IOException {
        while (parser.next() != 3) {
            if (parser.getEventType() == 2) {
                if ("context".equals(parser.getName())) {
                    int carAudioContext = parseCarAudioContext(parser.getAttributeValue(NAMESPACE, "context"));
                    validateCarAudioContextSupport(carAudioContext);
                    CarAudioDeviceInfo info = this.mAddressToCarAudioDeviceInfo.get(address);
                    group.bind(carAudioContext, info);
                    if (isVersionOne() && carAudioContext == CarAudioService.DEFAULT_AUDIO_CONTEXT) {
                        bindNonLegacyContexts(group, info);
                    }
                }
                skip(parser);
            }
        }
    }

    private boolean isVersionOne() {
        return this.mCurrentVersion == 1;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != 2) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            int next = parser.next();
            if (next == 2) {
                depth++;
            } else if (next == 3) {
                depth--;
            }
        }
    }

    private static int parseCarAudioContext(String context) {
        return CONTEXT_NAME_MAP.getOrDefault(context.toLowerCase(), 0).intValue();
    }

    private void validateCarAudioContextSupport(int audioContext) {
        if (isVersionOne() && NON_LEGACY_CONTEXTS.contains(Integer.valueOf(audioContext))) {
            throw new IllegalArgumentException(String.format("Non-legacy audio contexts such as %s are not supported in car_audio_configuration.xml version %d", CarAudioContext.toString(audioContext), 1));
        }
    }

    private int getNextSecondaryZoneId() {
        int zoneId = this.mNextSecondaryZoneId;
        this.mNextSecondaryZoneId++;
        return zoneId;
    }
}
