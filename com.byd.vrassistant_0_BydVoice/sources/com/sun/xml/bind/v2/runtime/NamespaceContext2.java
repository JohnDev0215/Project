package com.sun.xml.bind.v2.runtime;

import javax.xml.namespace.NamespaceContext;

/* JADX INFO: loaded from: classes3.dex */
public interface NamespaceContext2 extends NamespaceContext {
    String declareNamespace(String str, String str2, boolean z);

    int force(String str, String str2);
}
