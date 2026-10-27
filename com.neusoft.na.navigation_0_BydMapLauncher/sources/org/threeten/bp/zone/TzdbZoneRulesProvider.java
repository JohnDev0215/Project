package org.threeten.bp.zone;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StreamCorruptedException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.threeten.bp.jdk8.Jdk8Methods;

/* JADX INFO: loaded from: classes4.dex */
public final class TzdbZoneRulesProvider extends ZoneRulesProvider {
    private List<String> regionIds;
    private final ConcurrentNavigableMap<String, Version> versions = new ConcurrentSkipListMap();
    private Set<String> loadedUrls = new CopyOnWriteArraySet();

    public String toString() {
        return "TZDB";
    }

    public TzdbZoneRulesProvider() {
        if (!load(ZoneRulesProvider.class.getClassLoader())) {
            throw new ZoneRulesException("No time-zone rules found for 'TZDB'");
        }
    }

    public TzdbZoneRulesProvider(URL url) {
        try {
            if (load(url)) {
            } else {
                throw new ZoneRulesException("No time-zone rules found: " + url);
            }
        } catch (Exception e) {
            throw new ZoneRulesException("Unable to load TZDB time-zone rules: " + url, e);
        }
    }

    public TzdbZoneRulesProvider(InputStream inputStream) {
        try {
            load(inputStream);
        } catch (Exception e) {
            throw new ZoneRulesException("Unable to load TZDB time-zone rules", e);
        }
    }

    @Override // org.threeten.bp.zone.ZoneRulesProvider
    protected Set<String> provideZoneIds() {
        return new HashSet(this.regionIds);
    }

    @Override // org.threeten.bp.zone.ZoneRulesProvider
    protected ZoneRules provideRules(String str, boolean z) {
        Jdk8Methods.requireNonNull(str, "zoneId");
        ZoneRules rules = this.versions.lastEntry().getValue().getRules(str);
        if (rules != null) {
            return rules;
        }
        throw new ZoneRulesException("Unknown time-zone ID: " + str);
    }

    @Override // org.threeten.bp.zone.ZoneRulesProvider
    protected NavigableMap<String, ZoneRules> provideVersions(String str) {
        TreeMap treeMap = new TreeMap();
        for (Version version : this.versions.values()) {
            ZoneRules rules = version.getRules(str);
            if (rules != null) {
                treeMap.put(version.versionId, rules);
            }
        }
        return treeMap;
    }

    private boolean load(ClassLoader classLoader) {
        URL url = null;
        try {
            Enumeration<URL> resources = classLoader.getResources("org/threeten/bp/TZDB.dat");
            boolean zLoad = false;
            while (resources.hasMoreElements()) {
                URL urlNextElement = resources.nextElement();
                try {
                    zLoad |= load(urlNextElement);
                    url = urlNextElement;
                } catch (Exception e) {
                    e = e;
                    url = urlNextElement;
                    throw new ZoneRulesException("Unable to load TZDB time-zone rules: " + url, e);
                }
            }
            return zLoad;
        } catch (Exception e2) {
            e = e2;
        }
    }

    private boolean load(URL url) throws Throwable {
        InputStream inputStreamOpenStream;
        boolean zLoad = false;
        if (this.loadedUrls.add(url.toExternalForm())) {
            try {
                inputStreamOpenStream = url.openStream();
            } catch (Throwable th) {
                th = th;
                inputStreamOpenStream = null;
            }
            try {
                zLoad = false | load(inputStreamOpenStream);
                if (inputStreamOpenStream != null) {
                    inputStreamOpenStream.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (inputStreamOpenStream != null) {
                    inputStreamOpenStream.close();
                }
                throw th;
            }
        }
        return zLoad;
    }

    private boolean load(InputStream inputStream) throws IOException {
        boolean z = false;
        for (Version version : loadData(inputStream)) {
            Version versionPutIfAbsent = this.versions.putIfAbsent(version.versionId, version);
            if (versionPutIfAbsent != null && !versionPutIfAbsent.versionId.equals(version.versionId)) {
                throw new ZoneRulesException("Data already loaded for TZDB time-zone rules version: " + version.versionId);
            }
            z = true;
        }
        return z;
    }

    private Iterable<Version> loadData(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        if (dataInputStream.readByte() != 1) {
            throw new StreamCorruptedException("File format not recognised");
        }
        if (!"TZDB".equals(dataInputStream.readUTF())) {
            throw new StreamCorruptedException("File format not recognised");
        }
        int i = dataInputStream.readShort();
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            strArr[i2] = dataInputStream.readUTF();
        }
        int i3 = dataInputStream.readShort();
        String[] strArr2 = new String[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            strArr2[i4] = dataInputStream.readUTF();
        }
        this.regionIds = Arrays.asList(strArr2);
        int i5 = dataInputStream.readShort();
        Object[] objArr = new Object[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            byte[] bArr = new byte[dataInputStream.readShort()];
            dataInputStream.readFully(bArr);
            objArr[i6] = bArr;
        }
        AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(objArr);
        HashSet hashSet = new HashSet(i);
        for (int i7 = 0; i7 < i; i7++) {
            int i8 = dataInputStream.readShort();
            String[] strArr3 = new String[i8];
            short[] sArr = new short[i8];
            for (int i9 = 0; i9 < i8; i9++) {
                strArr3[i9] = strArr2[dataInputStream.readShort()];
                sArr[i9] = dataInputStream.readShort();
            }
            hashSet.add(new Version(strArr[i7], strArr3, sArr, atomicReferenceArray));
        }
        return hashSet;
    }

    static class Version {
        private final String[] regionArray;
        private final AtomicReferenceArray<Object> ruleData;
        private final short[] ruleIndices;
        private final String versionId;

        Version(String str, String[] strArr, short[] sArr, AtomicReferenceArray<Object> atomicReferenceArray) {
            this.ruleData = atomicReferenceArray;
            this.versionId = str;
            this.regionArray = strArr;
            this.ruleIndices = sArr;
        }

        ZoneRules getRules(String str) {
            int iBinarySearch = Arrays.binarySearch(this.regionArray, str);
            if (iBinarySearch < 0) {
                return null;
            }
            try {
                return createRule(this.ruleIndices[iBinarySearch]);
            } catch (Exception e) {
                throw new ZoneRulesException("Invalid binary time-zone data: TZDB:" + str + ", version: " + this.versionId, e);
            }
        }

        ZoneRules createRule(short s) throws Exception {
            Object obj = this.ruleData.get(s);
            if (obj instanceof byte[]) {
                obj = Ser.read(new DataInputStream(new ByteArrayInputStream((byte[]) obj)));
                this.ruleData.set(s, obj);
            }
            return (ZoneRules) obj;
        }

        public String toString() {
            return this.versionId;
        }
    }
}
