package com.amazon.identity.auth.device;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class sd {
    public static final String b = "com.amazon.identity.auth.device.sd";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ByteArrayOutputStream f547a = new ByteArrayOutputStream();

    public Document a() {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(this.f547a.toByteArray()));
        } catch (IOException e) {
            Log.e(ga.a(b), "Could not parse xml because of an IOException: " + e.getMessage());
            return null;
        } catch (ParserConfigurationException e2) {
            Log.e(ga.a(b), "Could not parse xml because of parser configuration issue: " + e2.getMessage());
            return null;
        } catch (SAXException e3) {
            String message = e3.getMessage();
            Log.e(ga.a(b), "Could not parse xml because it was invalid: " + message);
            yd.a("RegistrationError:SAXException", new String[0]);
            if (message.contains("Unexpected end of document")) {
                yd.a("RegistrationError:SAXException:UnexpectedEndOfDocument", new String[0]);
            }
            return null;
        }
    }
}
