package com.amazon.identity.auth.device;

import android.util.Log;
import com.amazon.identity.kcpsdk.common.FIRSErrorType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class ed {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f301a = "com.amazon.identity.auth.device.ed";

    public static dd a(Document document) {
        Element documentElement = document.getDocumentElement();
        if (documentElement == null || !documentElement.getTagName().equals("error")) {
            return null;
        }
        FIRSErrorType fIRSErrorType = FIRSErrorType.FIRSErrorTypeCustomerNotFound;
        if (!e.c(documentElement, fIRSErrorType.getErrorCode())) {
            fIRSErrorType = FIRSErrorType.FIRSErrorTypeDeviceAlreadyRegistered;
            if (!e.c(documentElement, fIRSErrorType.getErrorCode())) {
                fIRSErrorType = FIRSErrorType.FIRSErrorTypeDuplicateAccountName;
                if (!e.c(documentElement, fIRSErrorType.getErrorCode())) {
                    fIRSErrorType = FIRSErrorType.FIRSErrorTypeInternalError;
                    if (!e.c(documentElement, fIRSErrorType.getErrorCode())) {
                        fIRSErrorType = FIRSErrorType.FIRSErrorTypeInvalidAccountFound;
                        if (!e.c(documentElement, fIRSErrorType.getErrorCode())) {
                            fIRSErrorType = FIRSErrorType.FIRSErrorTypeUnrecognized;
                        }
                    }
                }
            }
        }
        Log.w(ga.a(f301a), "FIRSError type=" + fIRSErrorType);
        return new dd(fIRSErrorType);
    }
}
