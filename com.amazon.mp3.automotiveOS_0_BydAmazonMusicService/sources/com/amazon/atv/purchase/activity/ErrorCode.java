package com.amazon.atv.purchase.activity;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ErrorCode implements NamedEnum {
    CDPDuplicatePurchase("CDP.Purchase.DuplicateOrder"),
    CDPGeoCheckFailure("CDP.Purchase.InvalidGeoCreditCard"),
    CDPInternal("CDP.Internal"),
    CDPIncorrectPin("CDP.Purchase.IncorrectPin"),
    CDPNoAddress("CDP.Purchase.InvalidAccountSettings.NoDefaultBillingAddress"),
    CDPNoPaymentInstrument("CDP.Purchase.InvalidAccountSettings.NoDefaultCC"),
    CDPNotBuyable("CDP.Purchase.AsinNotBuyable"),
    CDPNoEmail("CDP.Purchase.InvalidAccountSettings.NoDefaultEmail");

    private final String strValue;

    ErrorCode(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ErrorCode forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ErrorCode errorCode : values()) {
            if (errorCode.strValue.equals(str)) {
                return errorCode;
            }
        }
        return null;
    }
}
