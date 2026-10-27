package com.dee.app.contacts.common.exceptions;

/* JADX INFO: loaded from: classes5.dex */
public class ClientNotSupportedException extends Exception {
    public ClientNotSupportedException(String str) {
        super("Client = " + str + " is not supported by Contacts SDK");
    }
}
