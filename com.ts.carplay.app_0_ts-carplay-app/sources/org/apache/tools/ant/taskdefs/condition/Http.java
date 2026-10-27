package org.apache.tools.ant.taskdefs.condition;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ProjectComponent;

/* JADX INFO: loaded from: classes.dex */
public class Http extends ProjectComponent implements Condition {
    private String spec = null;
    private int errorsBeginAt = 400;

    public void setUrl(String url) {
        this.spec = url;
    }

    public void setErrorsBeginAt(int errorsBeginAt) {
        this.errorsBeginAt = errorsBeginAt;
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() throws BuildException {
        if (this.spec == null) {
            throw new BuildException("No url specified in http condition");
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Checking for ");
        stringBuffer.append(this.spec);
        log(stringBuffer.toString(), 3);
        try {
            URL url = new URL(this.spec);
            try {
                URLConnection conn = url.openConnection();
                if (!(conn instanceof HttpURLConnection)) {
                    return true;
                }
                HttpURLConnection http = (HttpURLConnection) conn;
                int code = http.getResponseCode();
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Result code for ");
                stringBuffer2.append(this.spec);
                stringBuffer2.append(" was ");
                stringBuffer2.append(code);
                log(stringBuffer2.toString(), 3);
                if (code > 0) {
                    if (code < this.errorsBeginAt) {
                        return true;
                    }
                }
                return false;
            } catch (IOException e) {
                return false;
            }
        } catch (MalformedURLException e2) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Badly formed URL: ");
            stringBuffer3.append(this.spec);
            throw new BuildException(stringBuffer3.toString(), e2);
        }
    }
}
