package com.amazonaws.mobileconnectors.iot;

import com.amazonaws.util.Base64;
import com.amazonaws.util.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
final class PEM {
    private static final String BEGIN_MARKER = "-----BEGIN ";

    private PEM() {
    }

    public static PrivateKey readPrivateKey(InputStream inputStream) throws InvalidKeySpecException, IOException {
        for (PEMObject pEMObject : readPEMObjects(inputStream)) {
            if (AnonymousClass1.$SwitchMap$com$amazonaws$mobileconnectors$iot$PEMObjectType[pEMObject.getPEMObjectType().ordinal()] == 1) {
                return RSA.privateKeyFromPKCS1(pEMObject.getDerBytes());
            }
        }
        throw new IllegalArgumentException("Found no private key");
    }

    /* JADX INFO: renamed from: com.amazonaws.mobileconnectors.iot.PEM$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazonaws$mobileconnectors$iot$PEMObjectType;

        static {
            int[] iArr = new int[PEMObjectType.values().length];
            $SwitchMap$com$amazonaws$mobileconnectors$iot$PEMObjectType = iArr;
            try {
                iArr[PEMObjectType.PRIVATE_KEY_PKCS1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static List<PEMObject> readPEMObjects(InputStream inputStream) throws IOException {
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StringUtils.UTF8));
        String strReplace = null;
        boolean z = false;
        StringBuffer stringBuffer = null;
        String strTrim = null;
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return arrayList;
                }
                if (z) {
                    if (line.indexOf(strReplace) != -1) {
                        arrayList.add(new PEMObject(strTrim, Base64.decode(stringBuffer.toString())));
                        z = false;
                    } else {
                        stringBuffer.append(line.trim());
                    }
                } else if (line.indexOf(BEGIN_MARKER) != -1) {
                    z = true;
                    strTrim = line.trim();
                    strReplace = strTrim.replace("BEGIN", "END");
                    stringBuffer = new StringBuffer();
                }
            } finally {
                bufferedReader.close();
            }
        }
    }
}
