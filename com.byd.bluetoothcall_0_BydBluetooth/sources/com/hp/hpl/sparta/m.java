package com.hp.hpl.sparta;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class m {
    public static c a(String str, InputStream inputStream) throws ParseException, IOException {
        a aVar = new a();
        new h(str, inputStream, null, null, aVar);
        return aVar.g();
    }
}
