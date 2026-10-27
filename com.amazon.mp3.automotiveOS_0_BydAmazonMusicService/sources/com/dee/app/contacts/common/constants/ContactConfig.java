package com.dee.app.contacts.common.constants;

/* JADX INFO: loaded from: classes5.dex */
public class ContactConfig {
    public Integer addressRawTypeMaxLength;
    public Integer addressValueMaxLength;
    public Integer maxEmailAddressLength;
    public Integer maxEmailAddressRawTypeLength;
    public Integer maxEmailCount;
    public Integer phoneNumberRawTypeMaxLength;
    public Integer defaultMaxLength = 200;
    public Integer nameMaxLength = 200;
    public Integer companyNameMaxLength = 200;
    public Integer phoneNumberMaxLength = 25;

    public ContactConfig() {
        Integer num = this.defaultMaxLength;
        this.phoneNumberRawTypeMaxLength = num;
        this.addressValueMaxLength = num;
        this.addressRawTypeMaxLength = num;
        this.maxEmailCount = 25;
        Integer num2 = this.defaultMaxLength;
        this.maxEmailAddressLength = num2;
        this.maxEmailAddressRawTypeLength = num2;
    }
}
