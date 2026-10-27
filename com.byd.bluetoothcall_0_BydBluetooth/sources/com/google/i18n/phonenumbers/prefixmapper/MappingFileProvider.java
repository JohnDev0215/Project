package com.google.i18n.phonenumbers.prefixmapper;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public class MappingFileProvider implements Externalizable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Map<String, String> f4149b;
    private List<Set<String>> availableLanguages;
    private int[] countryCallingCodes;
    private int numOfEntries = 0;

    static {
        HashMap map = new HashMap();
        map.put("zh_TW", "zh_Hant");
        map.put("zh_HK", "zh_Hant");
        map.put("zh_MO", "zh_Hant");
        f4149b = Collections.unmodifiableMap(map);
    }

    private void a(String str, StringBuilder sb) {
        if (str.length() > 0) {
            sb.append('_');
            sb.append(str);
        }
    }

    private StringBuilder b(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(str);
        a(str2, sb);
        a(str3, sb);
        return sb;
    }

    private String c(Set<String> set, String str, String str2, String str3) {
        String string = b(str, str2, str3).toString();
        String str4 = f4149b.get(string);
        if (str4 != null && set.contains(str4)) {
            return str4;
        }
        if (set.contains(string)) {
            return string;
        }
        if (d(str2, str3)) {
            return set.contains(str) ? str : "";
        }
        if (str2.length() <= 0 || str3.length() <= 0) {
            return "";
        }
        String str5 = str + '_' + str2;
        if (set.contains(str5)) {
            return str5;
        }
        String str6 = str + '_' + str3;
        return set.contains(str6) ? str6 : set.contains(str) ? str : "";
    }

    private boolean d(String str, String str2) {
        return (str.length() == 0 && str2.length() > 0) || (str2.length() == 0 && str.length() > 0);
    }

    String getFileName(int i, String str, String str2, String str3) {
        int iBinarySearch;
        if (str.length() == 0 || (iBinarySearch = Arrays.binarySearch(this.countryCallingCodes, i)) < 0) {
            return "";
        }
        Set<String> set = this.availableLanguages.get(iBinarySearch);
        if (set.size() > 0) {
            String strC = c(set, str, str2, str3);
            if (strC.length() > 0) {
                return i + '_' + strC;
            }
        }
        return "";
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) throws IOException {
        int i = objectInput.readInt();
        this.numOfEntries = i;
        int[] iArr = this.countryCallingCodes;
        if (iArr == null || iArr.length < i) {
            this.countryCallingCodes = new int[i];
        }
        if (this.availableLanguages == null) {
            this.availableLanguages = new ArrayList();
        }
        for (int i2 = 0; i2 < this.numOfEntries; i2++) {
            this.countryCallingCodes[i2] = objectInput.readInt();
            int i3 = objectInput.readInt();
            HashSet hashSet = new HashSet();
            for (int i4 = 0; i4 < i3; i4++) {
                hashSet.add(objectInput.readUTF());
            }
            this.availableLanguages.add(hashSet);
        }
    }

    public void readFileConfigs(SortedMap<Integer, Set<String>> sortedMap) {
        int size = sortedMap.size();
        this.numOfEntries = size;
        this.countryCallingCodes = new int[size];
        this.availableLanguages = new ArrayList(this.numOfEntries);
        Iterator<Integer> it = sortedMap.keySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            this.countryCallingCodes[i] = iIntValue;
            this.availableLanguages.add(new HashSet(sortedMap.get(Integer.valueOf(iIntValue))));
            i++;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.numOfEntries; i++) {
            sb.append(this.countryCallingCodes[i]);
            sb.append('|');
            Iterator it = new TreeSet(this.availableLanguages.get(i)).iterator();
            while (it.hasNext()) {
                sb.append((String) it.next());
                sb.append(',');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeInt(this.numOfEntries);
        for (int i = 0; i < this.numOfEntries; i++) {
            objectOutput.writeInt(this.countryCallingCodes[i]);
            Set<String> set = this.availableLanguages.get(i);
            objectOutput.writeInt(set.size());
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                objectOutput.writeUTF(it.next());
            }
        }
    }
}
