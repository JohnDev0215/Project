package com.amazon.avod.playback.sye.listeners;

import com.amazon.music.find.utils.PageUriUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class SyeMessage {
    private final Optional<Filter> mFilterOptional;
    private final Signal mSignal;

    enum Signal {
        CLOSE_PLAYBACK,
        UNKNOWN;

        private static final Map<String, Signal> STR_SIGNAL_MAP = ImmutableMap.builder().put("quit", CLOSE_PLAYBACK).build();

        @JsonCreator
        public static Signal forValue(String str) {
            return (Signal) MoreObjects.firstNonNull(STR_SIGNAL_MAP.get(str), UNKNOWN);
        }
    }

    static class Filter {
        private Optional<String> mVcid;

        public Filter(@JsonProperty("vcid") @Nullable String str) {
            this.mVcid = Optional.fromNullable(str);
        }

        Optional<String> getVcidOptional() {
            return this.mVcid;
        }
    }

    @JsonCreator
    SyeMessage(@JsonProperty("signal") @Nonnull Signal signal, @JsonProperty(PageUriUtils.FILTER_QUERY_PARAM) @Nullable Filter filter) {
        this.mSignal = (Signal) Preconditions.checkNotNull(signal, "signal");
        this.mFilterOptional = Optional.fromNullable(filter);
    }

    @Nonnull
    Signal getSignal() {
        return this.mSignal;
    }

    @Nonnull
    Optional<Filter> getFilterOptional() {
        return this.mFilterOptional;
    }
}
