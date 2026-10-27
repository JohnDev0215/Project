package com.spotify.cosmos.routercallback;

import android.os.Handler;
import com.spotify.cosmos.cosmos.Response;
import com.spotify.cosmos.parsers.ResponseParser;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class DelegableParsingCallbackReceiver<T> extends ParsingCallbackReceiver<T> {
    private final ResponseParser<T> parser;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DelegableParsingCallbackReceiver(Handler handler, ResponseParser<T> responseParser) {
        super(handler);
        v03.j(responseParser, "parser");
        this.parser = responseParser;
    }

    @Override // com.spotify.cosmos.routercallback.ParsingCallbackReceiver
    public T parseResponse(Response response) {
        v03.j(response, "response");
        return this.parser.parseResponse(response);
    }
}
