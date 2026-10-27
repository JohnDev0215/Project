package io.gsonfire.gson;

import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public final class DateUnixtimeSecondsTypeAdapter extends DateUnixtimeTypeAdapter {
    public DateUnixtimeSecondsTypeAdapter(boolean z) {
        super(z);
    }

    @Override // io.gsonfire.gson.DateUnixtimeTypeAdapter
    protected long toTimestamp(Date date) {
        return date.getTime() / 1000;
    }

    @Override // io.gsonfire.gson.DateUnixtimeTypeAdapter
    protected Date fromTimestamp(long j) {
        return new Date(j * 1000);
    }
}
