package com.spotify.automotive.packagevalidator;

import android.content.res.XmlResourceParser;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.text.Regex;
import org.xmlpull.v1.XmlPullParserException;
import p.el;
import p.pr1;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "");
        int i = 0;
        for (byte b : bArr) {
            i++;
            if (i > 1) {
                sb.append((CharSequence) ":");
            }
            SignatureBasedPackageValidator$Companion$getByteArrayAsString$1 signatureBasedPackageValidator$Companion$getByteArrayAsString$1 = SignatureBasedPackageValidator$Companion$getByteArrayAsString$1.c;
            if (signatureBasedPackageValidator$Companion$getByteArrayAsString$1 != null) {
                sb.append((CharSequence) signatureBasedPackageValidator$Companion$getByteArrayAsString$1.invoke(Byte.valueOf(b)));
            } else {
                sb.append((CharSequence) String.valueOf((int) b));
            }
        }
        sb.append((CharSequence) "");
        String string = sb.toString();
        v03.i(string, "joinTo(StringBuilder(), …ed, transform).toString()");
        return string;
    }

    public static pr1 b(XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException, ParseException {
        String attributeValue = xmlResourceParser.getAttributeValue(null, "package");
        if (attributeValue == null || attributeValue.length() == 0) {
            throw new ParseException("Failed to parse package name.", xmlResourceParser.getLineNumber());
        }
        ArrayList arrayList = new ArrayList();
        int next = xmlResourceParser.next();
        while (next != 3) {
            if (next == 2 && v03.b(xmlResourceParser.getName(), "key")) {
                String strNextText = xmlResourceParser.nextText();
                v03.i(strNextText, "parser.nextText()");
                int length = strNextText.length() - 1;
                int i = 0;
                boolean z = false;
                while (i <= length) {
                    boolean z2 = v03.n(strNextText.charAt(!z ? i : length), 32) <= 0;
                    if (z) {
                        if (!z2) {
                            break;
                        }
                        length--;
                    } else if (z2) {
                        i++;
                    } else {
                        z = true;
                    }
                }
                String upperCase = new Regex(":").b(strNextText.subSequence(i, length + 1).toString(), "").toUpperCase(Locale.ROOT);
                v03.i(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                if (upperCase.length() == 0) {
                    throw new ParseException("Failed to parse key.", xmlResourceParser.getLineNumber());
                }
                arrayList.add(el.d.a(upperCase));
            }
            next = xmlResourceParser.next();
        }
        return new pr1(attributeValue, arrayList);
    }
}
