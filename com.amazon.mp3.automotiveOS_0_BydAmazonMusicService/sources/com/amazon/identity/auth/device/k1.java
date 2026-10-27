package com.amazon.identity.auth.device;

import android.content.res.XmlResourceParser;
import com.amazon.identity.auth.accounts.InvalidSubAuthenticatorDefinitionException;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class k1 {
    public j1 a(String str, String str2, XmlResourceParser xmlResourceParser) throws InvalidSubAuthenticatorDefinitionException {
        if (str == null || xmlResourceParser == null) {
            throw new IllegalArgumentException("The package name and xml parser parameter cannot be null");
        }
        a aVar = new a();
        aVar.f403a = xmlResourceParser;
        try {
            aVar.a();
            return aVar.c ? new j1(str, str2, aVar.e, aVar.d) : new j1(str, str2, aVar.b);
        } catch (IOException e) {
            throw new InvalidSubAuthenticatorDefinitionException(e);
        } catch (XmlPullParserException e2) {
            throw new InvalidSubAuthenticatorDefinitionException(e2);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public XmlResourceParser f403a;
        public final List<String> b = new ArrayList();
        public boolean c;
        public boolean d;
        public String e;

        public final void a() throws XmlPullParserException, IOException, InvalidSubAuthenticatorDefinitionException {
            a(0);
            a(2);
            a(AccountConstants.SUB_AUTHENTICATOR_ATTRIBUTES_NAME);
            if (!this.f403a.getAttributeValue(null, AccountConstants.SUB_AUTHENTICATOR_ACCOUNT_TYPE_ATTRIBUTE).equals(AccountConstants.AMAZON_ACCOUNT_TYPE)) {
                throw new InvalidSubAuthenticatorDefinitionException();
            }
            String attributeValue = this.f403a.getAttributeValue(null, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            if (attributeValue == null || !attributeValue.equals("DMS")) {
                a(2);
                a(AccountConstants.SUB_AUTHENTICATOR_TOKEN_TYPES_NAME);
                while (this.f403a.nextTag() != 3) {
                    a(AccountConstants.SUB_AUTHENTICATOR_TOKEN_TYPE_NAME);
                    String attributeValue2 = this.f403a.getAttributeValue(null, "name");
                    a(3);
                    this.b.add(attributeValue2);
                }
                a(AccountConstants.SUB_AUTHENTICATOR_TOKEN_TYPES_NAME);
            } else {
                this.c = true;
                while (this.f403a.next() != 3) {
                    String name = this.f403a.getName();
                    a(4);
                    if (name.equals(AccountConstants.SUB_AUTHENTICATOR_DEVICE_TYPE_NAME)) {
                        this.e = this.f403a.getText();
                    } else {
                        if (!name.equals(AccountConstants.SUB_AUTHENTICATOR_MULTIPLE_ACCOUNT_AWARE)) {
                            throw new InvalidSubAuthenticatorDefinitionException();
                        }
                        String text = this.f403a.getText();
                        this.d = "true".equalsIgnoreCase(text) || "yes".equalsIgnoreCase(text) || "1".equalsIgnoreCase(text);
                    }
                    a(3);
                }
            }
            if (!this.f403a.getName().equals(AccountConstants.SUB_AUTHENTICATOR_ATTRIBUTES_NAME)) {
                a(3);
            }
            a(1);
        }

        public final void a(int i) throws XmlPullParserException, IOException, InvalidSubAuthenticatorDefinitionException {
            int iNextTag;
            if (i != 2 && i != 3) {
                iNextTag = this.f403a.next();
            } else {
                iNextTag = this.f403a.nextTag();
            }
            if (iNextTag != i) {
                throw new InvalidSubAuthenticatorDefinitionException();
            }
        }

        public final void a(String str) throws InvalidSubAuthenticatorDefinitionException {
            if (!this.f403a.getName().equals(str)) {
                throw new InvalidSubAuthenticatorDefinitionException();
            }
        }
    }
}
