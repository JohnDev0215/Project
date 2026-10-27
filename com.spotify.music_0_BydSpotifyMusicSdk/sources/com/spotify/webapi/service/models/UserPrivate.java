package com.spotify.webapi.service.models;

import java.util.Arrays;
import p.c71;
import p.f60;
import p.l6;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
@l6
public final class UserPrivate extends UserPublic {
    public String birthdate;
    public String country;
    public String email;
    public String product;

    @z61(name = "birthdate")
    public static /* synthetic */ void getBirthdate$annotations() {
    }

    @z61(name = "country")
    public static /* synthetic */ void getCountry$annotations() {
    }

    @z61(name = "email")
    public static /* synthetic */ void getEmail$annotations() {
    }

    @z61(name = "product")
    public static /* synthetic */ void getProduct$annotations() {
    }

    @Override // com.spotify.webapi.service.models.UserPublic
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserPrivate) || !super.equals(obj)) {
            return false;
        }
        UserPrivate userPrivate = (UserPrivate) obj;
        return f60.p(this.birthdate, userPrivate.birthdate) && f60.p(this.country, userPrivate.country) && f60.p(this.email, userPrivate.email) && f60.p(this.product, userPrivate.product);
    }

    @Override // com.spotify.webapi.service.models.UserPublic
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), this.birthdate, this.country, this.email, this.product});
    }
}
