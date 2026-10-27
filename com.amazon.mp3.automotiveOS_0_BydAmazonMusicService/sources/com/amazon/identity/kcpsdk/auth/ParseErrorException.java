package com.amazon.identity.kcpsdk.auth;

import com.amazon.identity.kcpsdk.common.ParseError;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class ParseErrorException extends Exception {
    private static final long serialVersionUID = 1;
    public final ParseError mError;

    public ParseErrorException(ParseError parseError) {
        this.mError = parseError;
    }
}
