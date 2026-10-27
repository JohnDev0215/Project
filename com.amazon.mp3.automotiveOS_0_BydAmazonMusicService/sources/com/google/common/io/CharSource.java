package com.google.common.io;

import java.io.IOException;
import java.io.Reader;

/* JADX INFO: loaded from: classes6.dex */
public abstract class CharSource {
    public abstract Reader openStream() throws IOException;

    protected CharSource() {
    }

    public String read() throws Throwable {
        try {
            return CharStreams.toString((Reader) Closer.create().register(openStream()));
        } finally {
        }
    }
}
