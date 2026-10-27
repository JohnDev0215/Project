package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.v2.util.QNameMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public final class NameBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Map<String, Integer> uriIndexMap = new HashMap();
    private Set<String> nonDefaultableNsUris = new HashSet();
    private Map<String, Integer> localNameIndexMap = new HashMap();
    private QNameMap<Integer> elementQNameIndexMap = new QNameMap<>();
    private QNameMap<Integer> attributeQNameIndexMap = new QNameMap<>();

    public Name createElementName(QName qName) {
        return createElementName(qName.getNamespaceURI(), qName.getLocalPart());
    }

    public Name createElementName(String str, String str2) {
        return createName(str, str2, false, this.elementQNameIndexMap);
    }

    public Name createAttributeName(QName qName) {
        return createAttributeName(qName.getNamespaceURI(), qName.getLocalPart());
    }

    public Name createAttributeName(String str, String str2) {
        if (str.length() == 0) {
            return new Name(allocIndex(this.attributeQNameIndexMap, "", str2), -1, str, allocIndex(this.localNameIndexMap, str2), str2, true);
        }
        this.nonDefaultableNsUris.add(str);
        return createName(str, str2, true, this.attributeQNameIndexMap);
    }

    private Name createName(String str, String str2, boolean z, QNameMap<Integer> qNameMap) {
        return new Name(allocIndex(qNameMap, str, str2), allocIndex(this.uriIndexMap, str), str, allocIndex(this.localNameIndexMap, str2), str2, z);
    }

    private int allocIndex(Map<String, Integer> map, String str) {
        Integer numValueOf = map.get(str);
        if (numValueOf == null) {
            numValueOf = Integer.valueOf(map.size());
            map.put(str, numValueOf);
        }
        return numValueOf.intValue();
    }

    private int allocIndex(QNameMap<Integer> qNameMap, String str, String str2) {
        Integer numValueOf = qNameMap.get(str, str2);
        if (numValueOf == null) {
            numValueOf = Integer.valueOf(qNameMap.size());
            qNameMap.put(str, str2, numValueOf);
        }
        return numValueOf.intValue();
    }

    public NameList conclude() {
        boolean[] zArr = new boolean[this.uriIndexMap.size()];
        for (Map.Entry<String, Integer> entry : this.uriIndexMap.entrySet()) {
            zArr[entry.getValue().intValue()] = this.nonDefaultableNsUris.contains(entry.getKey());
        }
        NameList nameList = new NameList(list(this.uriIndexMap), zArr, list(this.localNameIndexMap), this.elementQNameIndexMap.size(), this.attributeQNameIndexMap.size());
        this.uriIndexMap = null;
        this.localNameIndexMap = null;
        return nameList;
    }

    private String[] list(Map<String, Integer> map) {
        String[] strArr = new String[map.size()];
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            strArr[entry.getValue().intValue()] = entry.getKey();
        }
        return strArr;
    }
}
