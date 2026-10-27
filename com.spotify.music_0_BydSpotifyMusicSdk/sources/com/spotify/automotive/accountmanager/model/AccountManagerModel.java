package com.spotify.automotive.accountmanager.model;

import p.hf2;

/* JADX INFO: loaded from: classes.dex */
public final class AccountManagerModel {
    public final String a;
    public final String b;
    public final String c;
    public final String d = "stored_credentials";

    public AccountManagerModel(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccountManagerModel)) {
            return false;
        }
        AccountManagerModel accountManagerModel = (AccountManagerModel) obj;
        return this.a.equals(accountManagerModel.a) && this.b.equals(accountManagerModel.b) && this.c.equals(accountManagerModel.c) && this.d.equals(accountManagerModel.d);
    }

    public final int hashCode() {
        return this.d.hashCode() + hf2.b(this.c, hf2.b(this.b, this.a.hashCode() * 31, 31), 31);
    }
}
