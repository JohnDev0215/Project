package com.dee.app.contacts.interfaces.models.data;

/* JADX INFO: loaded from: classes5.dex */
public class Account {
    private String mapDirectedDefaultActorId;
    private String phoneCountryCode;
    private String phoneCountryCodeForTheActor;
    private String phoneNumber;
    private String phoneNumberForTheActor;
    private Boolean signedInUser;

    public Account() {
    }

    public Account(String str, String str2, Boolean bool, String str3, String str4, String str5) {
        this.phoneNumber = str;
        this.phoneCountryCode = str2;
        this.signedInUser = bool;
        this.mapDirectedDefaultActorId = str3;
        this.phoneNumberForTheActor = str4;
        this.phoneCountryCodeForTheActor = str5;
    }
}
