package javax.activation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Locale;

/* JADX INFO: loaded from: classes7.dex */
public class MimeType implements Externalizable {
    private MimeTypeParameterList parameters;
    private String primaryType;
    private String subType;

    public MimeType() {
        this.primaryType = "application";
        this.subType = "*";
        this.parameters = new MimeTypeParameterList();
    }

    public MimeType(String str) throws MimeTypeParseException {
        parse(str);
    }

    private void parse(String str) throws MimeTypeParseException {
        int iIndexOf = str.indexOf(47);
        int iIndexOf2 = str.indexOf(59);
        if (iIndexOf < 0 && iIndexOf2 < 0) {
            throw new MimeTypeParseException("Unable to find a sub type.");
        }
        if (iIndexOf < 0 && iIndexOf2 >= 0) {
            throw new MimeTypeParseException("Unable to find a sub type.");
        }
        if (iIndexOf >= 0 && iIndexOf2 < 0) {
            String strTrim = str.substring(0, iIndexOf).trim();
            Locale locale = Locale.ENGLISH;
            this.primaryType = strTrim.toLowerCase(locale);
            this.subType = str.substring(iIndexOf + 1).trim().toLowerCase(locale);
            this.parameters = new MimeTypeParameterList();
        } else if (iIndexOf < iIndexOf2) {
            String strTrim2 = str.substring(0, iIndexOf).trim();
            Locale locale2 = Locale.ENGLISH;
            this.primaryType = strTrim2.toLowerCase(locale2);
            this.subType = str.substring(iIndexOf + 1, iIndexOf2).trim().toLowerCase(locale2);
            this.parameters = new MimeTypeParameterList(str.substring(iIndexOf2));
        } else {
            throw new MimeTypeParseException("Unable to find a sub type.");
        }
        if (!isValidToken(this.primaryType)) {
            throw new MimeTypeParseException("Primary type is invalid.");
        }
        if (!isValidToken(this.subType)) {
            throw new MimeTypeParseException("Sub type is invalid.");
        }
    }

    public String toString() {
        return getBaseType() + this.parameters.toString();
    }

    public String getBaseType() {
        return this.primaryType + "/" + this.subType;
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeUTF(toString());
        objectOutput.flush();
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        try {
            parse(objectInput.readUTF());
        } catch (MimeTypeParseException e) {
            throw new IOException(e.toString());
        }
    }

    private static boolean isTokenChar(char c) {
        return c > ' ' && c < 127 && "()<>@,;:/[]?=\\\"".indexOf(c) < 0;
    }

    private boolean isValidToken(String str) {
        int length = str.length();
        if (length <= 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!isTokenChar(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
