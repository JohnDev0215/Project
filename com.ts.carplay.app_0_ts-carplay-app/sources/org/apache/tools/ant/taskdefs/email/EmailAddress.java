package org.apache.tools.ant.taskdefs.email;

/* JADX INFO: loaded from: classes.dex */
public class EmailAddress {
    private String address;
    private String name;

    public EmailAddress() {
    }

    public EmailAddress(String email) {
        int len = email.length();
        if (len > 9 && ((email.charAt(0) == '<' || email.charAt(1) == '<') && (email.charAt(len - 1) == '>' || email.charAt(len - 2) == '>'))) {
            this.address = trim(email, true);
            return;
        }
        int paramDepth = 0;
        int start = 0;
        int end = 0;
        int nStart = 0;
        int nEnd = 0;
        for (int i = 0; i < len; i++) {
            char c = email.charAt(i);
            if (c == '(') {
                paramDepth++;
                if (start == 0) {
                    end = i;
                    nStart = i + 1;
                }
            } else if (c == ')') {
                paramDepth--;
                if (end == 0) {
                    start = i + 1;
                    nEnd = i;
                }
            } else if (paramDepth == 0 && c == '<') {
                nEnd = start == 0 ? i : nEnd;
                start = i + 1;
            } else if (paramDepth == 0 && c == '>' && (end = i) != len - 1) {
                nStart = i + 1;
            }
        }
        end = end == 0 ? len : end;
        nEnd = nEnd == 0 ? len : nEnd;
        this.address = trim(email.substring(start, end), true);
        String strTrim = trim(email.substring(nStart, nEnd), false);
        this.name = strTrim;
        if (strTrim.length() + this.address.length() > len) {
            this.name = null;
        }
    }

    private String trim(String t, boolean trimAngleBrackets) {
        boolean trim;
        int start = 0;
        int end = t.length();
        do {
            trim = false;
            if (t.charAt(end - 1) == ')' || ((t.charAt(end - 1) == '>' && trimAngleBrackets) || ((t.charAt(end - 1) == '\"' && t.charAt(end - 2) != '\\') || t.charAt(end - 1) <= ' '))) {
                trim = true;
                end--;
            }
            if (t.charAt(start) == '(' || ((t.charAt(start) == '<' && trimAngleBrackets) || t.charAt(start) == '\"' || t.charAt(start) <= ' ')) {
                trim = true;
                start++;
            }
        } while (trim);
        return t.substring(start, end);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        if (this.name == null) {
            return this.address;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.name);
        stringBuffer.append(" <");
        stringBuffer.append(this.address);
        stringBuffer.append(">");
        return stringBuffer.toString();
    }

    public String getAddress() {
        return this.address;
    }

    public String getName() {
        return this.name;
    }
}
