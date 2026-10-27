package com.dee.app.contacts.interfaces.models.data.server;

/* JADX INFO: loaded from: classes5.dex */
public final class ServerAccount {
    private String mapDirectedDefaultActorId;
    private String phoneCountryCode;
    private String phoneNumber;
    private ServerPhoneNumberMetadata phoneNumberMetadataForTheActor;
    private Boolean signedInUser;

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPhoneCountryCode() {
        return this.phoneCountryCode;
    }

    public Boolean getSignedInUser() {
        return this.signedInUser;
    }

    public String getMapDirectedDefaultActorId() {
        return this.mapDirectedDefaultActorId;
    }

    public ServerPhoneNumberMetadata getPhoneNumberMetadataForTheActor() {
        return this.phoneNumberMetadataForTheActor;
    }
}
