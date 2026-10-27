package com.amazon.weblab.mobile.service;

/* JADX INFO: loaded from: classes5.dex */
public enum MobileWeblabDomain {
    PROD("production"),
    PILOT("pilot"),
    DEVELOPMENT("development");

    String domainValue;

    MobileWeblabDomain(String str) {
        this.domainValue = str;
    }

    String getDomainValue() {
        return this.domainValue;
    }
}
