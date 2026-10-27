package com.amazon.identity.kcpsdk.common;

import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.md;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class WebResponseParser<T> {
    public static final String f = "com.amazon.identity.kcpsdk.common.WebResponseParser";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f711a;
    public md c;
    public boolean e;
    public ParseError b = ParseError.ParseErrorNoError;
    public WebResponseParserState d = WebResponseParserState.Before_Parse;

    /* JADX INFO: compiled from: DCP */
    public enum WebResponseParserState {
        Before_Parse,
        Begin_Parse,
        Parsing,
        Completed
    }

    public WebResponseParser(String str) {
        this.f711a = str;
    }

    public void a(md mdVar) {
        a(WebResponseParserState.Begin_Parse);
        this.c = mdVar;
        boolean zB = b(mdVar);
        long j = this.c.c;
        if (j < 200 || j >= 300) {
            ga.c(f, "%s: HTTP Error: %d", this.f711a, Long.valueOf(j));
            if (zB) {
                return;
            }
            a(ParseError.ParseErrorHttpError);
        }
    }

    public abstract void a(byte[] bArr, long j);

    public ParseError b(byte[] bArr, long j) {
        a(WebResponseParserState.Parsing);
        if (this.b != ParseError.ParseErrorNoError) {
            ga.b(f, "%s: parseBodyChunk: called after another method returned a parse error.", this.f711a);
            return this.b;
        }
        a(bArr, j);
        if (this.b == ParseError.ParseErrorMalformedBody) {
            ga.c(f, "%s: parseBodyChunk: Malformed response. confirm all received data is being properly passed to the parser, device capabilities are set properly, and no server-side behavior changes have occurred.", this.f711a);
        }
        return this.b;
    }

    public abstract T b();

    public boolean b(md mdVar) {
        return false;
    }

    public abstract void c();

    public boolean d() {
        return this.b != ParseError.ParseErrorHttpError;
    }

    public ParseError a() {
        a(WebResponseParserState.Completed);
        if (this.b != ParseError.ParseErrorNoError) {
            ga.b(f, "%s: endParse: called after another method returned a parse error.", this.f711a);
            return this.b;
        }
        c();
        if (this.b == ParseError.ParseErrorMalformedBody) {
            if (this.e) {
                ga.d(f, "%s: endParse called before parseBodyChunk. Confirm that this is by design.", this.f711a);
            }
            ga.b(f, "%s: endParse: Malformed response. Confirm all received data is being properly passed to the parser, device capabilities are set properly, and no server-side behavior changes have occurred.", this.f711a);
        }
        return this.b;
    }

    public boolean a(ParseError parseError) {
        ParseError parseError2 = this.b;
        if (parseError2 != ParseError.ParseErrorNoError) {
            ga.c(f, "%s: setParseError has been called more than once.  Was %s, Now %s.", this.f711a, parseError2.name(), parseError.name());
        }
        this.b = parseError;
        return true;
    }

    public final void a(WebResponseParserState webResponseParserState) {
        WebResponseParserState webResponseParserState2 = this.d;
        WebResponseParserState webResponseParserState3 = WebResponseParserState.Before_Parse;
        if (webResponseParserState2 != webResponseParserState3 && webResponseParserState == WebResponseParserState.Begin_Parse) {
            ga.b(f, "%s: beginParse has been called more than once.", this.f711a);
            return;
        }
        if (webResponseParserState2 == webResponseParserState3) {
            if (webResponseParserState == WebResponseParserState.Parsing) {
                ga.b(f, "%s: parseBodyChunk called before beginParse", this.f711a);
                return;
            } else if (webResponseParserState == WebResponseParserState.Completed) {
                ga.b(f, "%s: endParse called before beginParse", this.f711a);
                return;
            }
        } else if (webResponseParserState2 == WebResponseParserState.Begin_Parse) {
            if (webResponseParserState == WebResponseParserState.Completed && d()) {
                this.e = true;
                return;
            } else if (webResponseParserState == WebResponseParserState.Parsing && !d()) {
                ga.b(f, "%s: shouldParseBody is false. parseBodyChunk should not be called", this.f711a);
                return;
            }
        } else if (webResponseParserState2 == WebResponseParserState.Completed && webResponseParserState == WebResponseParserState.Parsing) {
            ga.b(f, "%s: parseBodyChunk called after endParse", this.f711a);
            return;
        }
        this.d = webResponseParserState;
    }
}
