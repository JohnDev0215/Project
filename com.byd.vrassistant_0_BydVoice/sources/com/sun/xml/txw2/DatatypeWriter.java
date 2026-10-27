package com.sun.xml.txw2;

import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public interface DatatypeWriter<DT> {
    public static final List<DatatypeWriter<?>> BUILTIN = Collections.unmodifiableList(new AbstractList() { // from class: com.sun.xml.txw2.DatatypeWriter.1
        private DatatypeWriter<?>[] BUILTIN_ARRAY = {new DatatypeWriter<String>() { // from class: com.sun.xml.txw2.DatatypeWriter.1.1
            @Override // com.sun.xml.txw2.DatatypeWriter
            public Class<String> getType() {
                return String.class;
            }

            @Override // com.sun.xml.txw2.DatatypeWriter
            public void print(String str, NamespaceResolver namespaceResolver, StringBuilder sb) {
                sb.append(str);
            }
        }, new DatatypeWriter<Integer>() { // from class: com.sun.xml.txw2.DatatypeWriter.1.2
            @Override // com.sun.xml.txw2.DatatypeWriter
            public Class<Integer> getType() {
                return Integer.class;
            }

            @Override // com.sun.xml.txw2.DatatypeWriter
            public void print(Integer num, NamespaceResolver namespaceResolver, StringBuilder sb) {
                sb.append(num);
            }
        }, new DatatypeWriter<Float>() { // from class: com.sun.xml.txw2.DatatypeWriter.1.3
            @Override // com.sun.xml.txw2.DatatypeWriter
            public Class<Float> getType() {
                return Float.class;
            }

            @Override // com.sun.xml.txw2.DatatypeWriter
            public void print(Float f, NamespaceResolver namespaceResolver, StringBuilder sb) {
                sb.append(f);
            }
        }, new DatatypeWriter<Double>() { // from class: com.sun.xml.txw2.DatatypeWriter.1.4
            @Override // com.sun.xml.txw2.DatatypeWriter
            public Class<Double> getType() {
                return Double.class;
            }

            @Override // com.sun.xml.txw2.DatatypeWriter
            public void print(Double d, NamespaceResolver namespaceResolver, StringBuilder sb) {
                sb.append(d);
            }
        }, new DatatypeWriter<QName>() { // from class: com.sun.xml.txw2.DatatypeWriter.1.5
            @Override // com.sun.xml.txw2.DatatypeWriter
            public Class<QName> getType() {
                return QName.class;
            }

            @Override // com.sun.xml.txw2.DatatypeWriter
            public void print(QName qName, NamespaceResolver namespaceResolver, StringBuilder sb) {
                String prefix = namespaceResolver.getPrefix(qName.getNamespaceURI());
                if (prefix.length() != 0) {
                    sb.append(prefix).append(':');
                }
                sb.append(qName.getLocalPart());
            }
        }};

        @Override // java.util.AbstractList, java.util.List
        public DatatypeWriter<?> get(int i) {
            return this.BUILTIN_ARRAY[i];
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.BUILTIN_ARRAY.length;
        }
    });

    Class<DT> getType();

    void print(DT dt, NamespaceResolver namespaceResolver, StringBuilder sb);
}
