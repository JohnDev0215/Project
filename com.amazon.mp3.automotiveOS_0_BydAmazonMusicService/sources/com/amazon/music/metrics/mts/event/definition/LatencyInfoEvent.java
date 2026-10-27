package com.amazon.music.metrics.mts.event.definition;

import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyDuration;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class LatencyInfoEvent extends MTSEvent {
    private static final Logger logger = LoggerFactory.getLogger(LatencyInfoEvent.class.getSimpleName());
    private static final JsonSerializer<List<LatencyDuration>> serializer = new JsonSerializer<List<LatencyDuration>>() { // from class: com.amazon.music.metrics.mts.event.definition.LatencyInfoEvent.1
        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(List<LatencyDuration> list, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonArray jsonArray = new JsonArray();
            for (LatencyDuration latencyDuration : list) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("latencyPiece", latencyDuration.getLatencyLeg().getLatencyTrackingLeg());
                jsonObject.addProperty("durationMilliseconds", Long.valueOf(latencyDuration.getDuration()));
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        }
    };

    private LatencyInfoEvent(long j, String str, String str2, String str3, String str4) {
        super("latencyInfo", 3L);
        addAttribute("durationMilliseconds", j);
        addAttribute("latencyCode", str + str2 + "To" + str3 + str4);
    }

    private LatencyInfoEvent(String str, String str2, String str3, String str4, String str5, List<LatencyDuration> list) {
        super("latencyInfo", 1L);
        addAttribute("latencyCode", str);
        addAttribute("durationMilliseconds", str4);
        addAttribute("latencyOperation", str2);
        addAttribute("latencySubtype", str3);
        if (str5 != null) {
            addAttribute("networkType", str5);
        }
        String strConvertPieceDurationsToJsonString = convertPieceDurationsToJsonString(list);
        addAttribute("pieceDurations", strConvertPieceDurationsToJsonString);
        logger.debug("Serializing durations for latencyInfoEvent " + str3 + " as: " + strConvertPieceDurationsToJsonString);
    }

    private String convertPieceDurationsToJsonString(List<LatencyDuration> list) {
        Type type = new TypeToken<List<LatencyDuration>>() { // from class: com.amazon.music.metrics.mts.event.definition.LatencyInfoEvent.2
        }.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(type, serializer);
        return gsonBuilder.create().toJson(list, type);
    }

    public static Builder builder(long j, String str, String str2, String str3) {
        return new Builder(j, str, str2, str3);
    }

    public static Builder builder(String str, String str2, String str3, String str4, List<LatencyDuration> list) {
        return new Builder(list, str, str2, str3, str4);
    }

    public static class Builder {
        private String durationMs;
        private String from;
        private final Boolean isPageLatencyEvent;
        private long latency;
        private String latencyCode;
        private List<LatencyDuration> latencyDurations;
        private String networkType;
        private String operation;
        private String optionalAdditionalString;
        private String subType;
        private String to;
        private String useCaseName;

        private Builder(List<LatencyDuration> list, String str, String str2, String str3, String str4) {
            this.optionalAdditionalString = "";
            this.networkType = null;
            if (list == null) {
                this.latencyDurations = new ArrayList();
            } else {
                this.latencyDurations = list;
            }
            this.latencyCode = str;
            this.operation = str2;
            this.subType = str3;
            this.durationMs = str4;
            this.isPageLatencyEvent = Boolean.TRUE;
        }

        private Builder(long j, String str, String str2, String str3) {
            this.optionalAdditionalString = "";
            this.networkType = null;
            this.useCaseName = str;
            this.from = str2;
            this.to = str3;
            this.latency = j;
            this.isPageLatencyEvent = Boolean.FALSE;
        }

        public Builder withActiveNetworkType(Boolean bool) {
            if (bool.booleanValue()) {
                this.networkType = "WiFi";
            } else {
                this.networkType = "Cellular";
            }
            return this;
        }

        public LatencyInfoEvent build() {
            if (this.isPageLatencyEvent.booleanValue()) {
                return new LatencyInfoEvent(this.latencyCode, this.operation, this.subType, this.durationMs, this.networkType, this.latencyDurations);
            }
            return new LatencyInfoEvent(this.latency, this.useCaseName, this.from, this.to, this.optionalAdditionalString);
        }
    }
}
