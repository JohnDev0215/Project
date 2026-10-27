package com.fasterxml.jackson.datatype.joda;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/* JADX INFO: loaded from: classes6.dex */
public class JodaMapper extends ObjectMapper {
    private static final long serialVersionUID = 1;

    public JodaMapper() {
        registerModule(new JodaModule());
    }

    public boolean getWriteDatesAsTimestamps() {
        return getSerializationConfig().isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public void setWriteDatesAsTimestamps(boolean z) {
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, z);
    }
}
