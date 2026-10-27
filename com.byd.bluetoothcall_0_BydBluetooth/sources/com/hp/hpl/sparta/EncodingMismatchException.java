package com.hp.hpl.sparta;

/* JADX INFO: loaded from: classes.dex */
public class EncodingMismatchException extends ParseException {
    private String declaredEncoding_;

    EncodingMismatchException(String str, String str2, String str3) {
        char cCharAt = str2.charAt(str2.length() - 1);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("encoding '");
        stringBuffer.append(str2);
        stringBuffer.append("' declared instead of of ");
        stringBuffer.append(str3);
        stringBuffer.append(" as expected");
        super(str, 0, cCharAt, str2, stringBuffer.toString());
        this.declaredEncoding_ = str2;
    }

    String getDeclaredEncoding() {
        return this.declaredEncoding_;
    }
}
