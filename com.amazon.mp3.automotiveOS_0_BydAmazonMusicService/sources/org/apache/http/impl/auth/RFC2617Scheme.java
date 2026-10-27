package org.apache.http.impl.auth;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.Consts;
import org.apache.http.HeaderElement;
import org.apache.http.HttpRequest;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

/* JADX INFO: loaded from: classes7.dex */
public abstract class RFC2617Scheme extends AuthSchemeBase implements Serializable {
    private transient Charset credentialsCharset;
    private final Map<String, String> params;

    public RFC2617Scheme(Charset charset) {
        this.params = new HashMap();
        this.credentialsCharset = charset == null ? Consts.ASCII : charset;
    }

    public RFC2617Scheme() {
        this(Consts.ASCII);
    }

    public Charset getCredentialsCharset() {
        Charset charset = this.credentialsCharset;
        return charset != null ? charset : Consts.ASCII;
    }

    String getCredentialsCharset(HttpRequest httpRequest) {
        String str = (String) httpRequest.getParams().getParameter("http.auth.credential-charset");
        return str == null ? getCredentialsCharset().name() : str;
    }

    @Override // org.apache.http.impl.auth.AuthSchemeBase
    protected void parseChallenge(CharArrayBuffer charArrayBuffer, int i, int i2) throws MalformedChallengeException {
        HeaderElement[] elements = BasicHeaderValueParser.INSTANCE.parseElements(charArrayBuffer, new ParserCursor(i, charArrayBuffer.length()));
        this.params.clear();
        for (HeaderElement headerElement : elements) {
            this.params.put(headerElement.getName().toLowerCase(Locale.ROOT), headerElement.getValue());
        }
    }

    protected Map<String, String> getParameters() {
        return this.params;
    }

    public String getParameter(String str) {
        if (str == null) {
            return null;
        }
        return this.params.get(str.toLowerCase(Locale.ROOT));
    }

    @Override // org.apache.http.auth.AuthScheme
    public String getRealm() {
        return getParameter("realm");
    }
}
