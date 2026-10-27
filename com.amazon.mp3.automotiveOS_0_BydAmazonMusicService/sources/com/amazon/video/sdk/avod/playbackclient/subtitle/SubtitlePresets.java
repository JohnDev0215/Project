package com.amazon.video.sdk.avod.playbackclient.subtitle;

import android.graphics.Color;
import com.amazon.atvsubtitlepresets.GetPresetsOutput;
import com.amazon.avod.util.DLog;
import com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitlePreset;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
@Immutable
public class SubtitlePresets {
    public static final SubtitlePresets DEFAULT_PRESETS = new SubtitlePresets(ImmutableList.of(SubtitlePreset.DEFAULT, SubtitlePreset.SYSTEM_PRESET_1, SubtitlePreset.SYSTEM_PRESET_2, SubtitlePreset.SYSTEM_PRESET_3));
    private final JSONObject mJsonObject;
    private final ImmutableList<SubtitlePreset> mPresets;

    private SubtitlePresets(@Nonnull ImmutableList<SubtitlePreset> immutableList) {
        this(immutableList, null);
    }

    private SubtitlePresets(@Nonnull ImmutableList<SubtitlePreset> immutableList, @Nullable JSONObject jSONObject) {
        this.mPresets = (ImmutableList) Preconditions.checkNotNull(immutableList);
        this.mJsonObject = jSONObject;
    }

    public static SubtitlePresets convertGetPresetsOutput(@Nonnull GetPresetsOutput getPresetsOutput) {
        Preconditions.checkNotNull(getPresetsOutput, "presetOutput");
        if (!getPresetsOutput.presets.isPresent()) {
            return null;
        }
        Builder builderNewBuilder = Builder.newBuilder();
        UnmodifiableIterator<Map.Entry<String, com.amazon.atvsubtitlepresets.SubtitlePreset>> it = getPresetsOutput.presets.get().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, com.amazon.atvsubtitlepresets.SubtitlePreset> next = it.next();
            com.amazon.atvsubtitlepresets.SubtitlePreset value = next.getValue();
            SubtitlePreset.Builder builderNewBuilder2 = SubtitlePreset.Builder.newBuilder();
            builderNewBuilder2.setPresetName(next.getKey());
            if (value.txtColor.isPresent()) {
                builderNewBuilder2.setTextColor(parseColor(value.txtColor.get()));
            }
            if (value.txtOpacity.isPresent()) {
                builderNewBuilder2.setTextOpacity((float) value.txtOpacity.get().doubleValue());
            }
            if (value.txtSize.isPresent()) {
                builderNewBuilder2.setTextSize((float) value.txtSize.get().doubleValue());
            }
            if (value.txtFont.isPresent()) {
                builderNewBuilder2.setTextFont(SubtitlePreset.Font.lookup(value.txtFont.get()));
            }
            if (value.txtEdge.isPresent()) {
                builderNewBuilder2.setTextEdge(SubtitlePreset.Edge.lookup(value.txtEdge.get()));
            }
            if (value.bgColor.isPresent()) {
                builderNewBuilder2.setBackgroundColor(parseColor(value.bgColor.get()));
            }
            if (value.bgOpacity.isPresent()) {
                builderNewBuilder2.setBackgroundOpacity((float) value.bgOpacity.get().doubleValue());
            }
            if (value.transition.isPresent()) {
                builderNewBuilder2.setTransition(SubtitlePreset.Transition.lookup(value.transition.get()));
            }
            if (value.winColor.isPresent()) {
                builderNewBuilder2.setWindowColor(parseColor(value.winColor.get()));
            }
            if (value.winOpacity.isPresent()) {
                builderNewBuilder2.setWindowOpacity((float) value.winOpacity.get().doubleValue());
            }
            if (value.customized.isPresent()) {
                builderNewBuilder2.setIsCustomized(value.customized.get().booleanValue());
            }
            if (value.uri.isPresent()) {
                builderNewBuilder2.setIconUrl(parseURL(value.uri.get()));
            }
            builderNewBuilder.addPreset(builderNewBuilder2.build());
        }
        return builderNewBuilder.build();
    }

    @Nonnull
    public ImmutableList<SubtitlePreset> getPresets() {
        return this.mPresets;
    }

    private static int parseColor(String str) {
        return Color.parseColor(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getHexStringFromColor(int i) {
        return String.format("#%02x%02x%02x%02x", Integer.valueOf(Color.alpha(i)), Integer.valueOf(Color.red(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.blue(i)));
    }

    @Nullable
    private static URL parseURL(@Nonnull String str) {
        Preconditions.checkNotNull(str);
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            DLog.exceptionf(e, "MalformedURLException occurred while parsing the subtitle presets icon uri", new Object[0]);
            return null;
        }
    }

    public static class Builder {
        private static final Comparator<SubtitlePreset> SORT_BY_PRESET_NAME = new Comparator<SubtitlePreset>() { // from class: com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitlePresets.Builder.1
            @Override // java.util.Comparator
            public int compare(SubtitlePreset subtitlePreset, SubtitlePreset subtitlePreset2) {
                if (subtitlePreset.getPresetName() == "default" && subtitlePreset2.getPresetName() == "default") {
                    return 0;
                }
                if (subtitlePreset.getPresetName() == "default") {
                    return -1;
                }
                if (subtitlePreset2.getPresetName() == "default") {
                    return 1;
                }
                return subtitlePreset.getPresetName().compareTo(subtitlePreset2.getPresetName());
            }
        };
        private JSONObject mJsonObject;
        private final List<SubtitlePreset> mPresets = Lists.newArrayListWithCapacity(4);

        private Builder() {
        }

        @Nonnull
        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder addPreset(@Nonnull SubtitlePreset subtitlePreset) {
            this.mPresets.add((SubtitlePreset) Preconditions.checkNotNull(subtitlePreset));
            return this;
        }

        @Nonnull
        public SubtitlePresets build() {
            Preconditions.checkState(this.mPresets.size() == 4);
            Collections.sort(this.mPresets, SORT_BY_PRESET_NAME);
            return new SubtitlePresets(ImmutableList.copyOf((Collection) this.mPresets), this.mJsonObject);
        }
    }

    public static class SubtitlePresetsSerializer extends StdSerializer<SubtitlePresets> {
        public SubtitlePresetsSerializer(Class<SubtitlePresets> cls) {
            super(cls);
        }

        @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
        public void serialize(SubtitlePresets subtitlePresets, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            UnmodifiableIterator<SubtitlePreset> it = subtitlePresets.getPresets().iterator();
            while (it.hasNext()) {
                SubtitlePreset next = it.next();
                jsonGenerator.writeObjectField(next.getPresetName(), next);
            }
            jsonGenerator.writeEndObject();
        }
    }

    public static class SubtitlePresetSerializer extends StdSerializer<SubtitlePreset> {
        public SubtitlePresetSerializer(Class<SubtitlePreset> cls) {
            super(cls);
        }

        @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
        public void serialize(SubtitlePreset subtitlePreset, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("txtColor", SubtitlePresets.getHexStringFromColor(subtitlePreset.getTextColor()));
            jsonGenerator.writeNumberField("txtOpacity", subtitlePreset.getTextOpacity());
            jsonGenerator.writeNumberField("txtSize", subtitlePreset.getTextSize());
            jsonGenerator.writeStringField("txtFont", SubtitlePreset.Font.lookupKey(subtitlePreset.getTextFont()));
            jsonGenerator.writeStringField("txtEdge", SubtitlePreset.Edge.lookupKey(subtitlePreset.getTextEdge()));
            jsonGenerator.writeStringField("bgColor", SubtitlePresets.getHexStringFromColor(subtitlePreset.getBackgroundColor()));
            jsonGenerator.writeNumberField("bgOpacity", subtitlePreset.getBackgroundOpacity());
            jsonGenerator.writeStringField("transition", SubtitlePreset.Transition.lookupKey(subtitlePreset.getTransition()));
            jsonGenerator.writeStringField("winColor", SubtitlePresets.getHexStringFromColor(subtitlePreset.getWindowColor()));
            jsonGenerator.writeNumberField("winOpacity", subtitlePreset.getWindowOpacity());
            jsonGenerator.writeBooleanField("customized", subtitlePreset.getIsCustomized());
            URL iconUrl = subtitlePreset.getIconUrl();
            jsonGenerator.writeStringField("uri", iconUrl == null ? "" : iconUrl.toString());
            jsonGenerator.writeEndObject();
        }
    }
}
