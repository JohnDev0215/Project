package com.amazon.video.sdk.avod.playbackclient.subtitle;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.amazon.avod.util.Preconditions2;
import com.amazon.video.player.R$drawable;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.net.URL;
import java.security.InvalidParameterException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes5.dex */
@Immutable
public class SubtitlePreset {
    public static final SubtitlePreset DEFAULT;
    public static final SubtitlePreset SYSTEM_PRESET_1;
    public static final SubtitlePreset SYSTEM_PRESET_2;
    public static final SubtitlePreset SYSTEM_PRESET_3;
    private final int mBackgroundColor;
    private final float mBackgroundOpacity;
    private final boolean mCustomized;
    private final URL mIconUrl;
    private final int mPlaceholderResId;
    private final String mPresetName;
    private final int mTextColor;
    private final Edge mTextEdge;
    private final Font mTextFont;
    private final float mTextOpacity;
    private final float mTextSize;
    private final Transition mTransition;
    private final int mWindowColor;
    private final float mWindowOpacity;

    public enum Transition {
        NONE,
        POP_ON,
        ROLL_UP,
        PAINT_ON;

        private static final ImmutableMap<String, Transition> TRANSITIONS = (ImmutableMap) Preconditions2.checkFullValueMapping(Transition.class, ImmutableMap.of("none", NONE, "popOn", POP_ON, "rollUp", ROLL_UP, "paintOn", PAINT_ON));

        @Nullable
        public static Transition lookup(String str) {
            if (str != null) {
                ImmutableMap<String, Transition> immutableMap = TRANSITIONS;
                if (immutableMap.containsKey(str)) {
                    return immutableMap.get(str);
                }
            }
            return null;
        }

        @Nonnull
        public static String lookupKey(Transition transition) {
            UnmodifiableIterator<String> it = TRANSITIONS.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (transition == TRANSITIONS.get(next)) {
                    return next;
                }
            }
            throw new InvalidParameterException("can not find this Transition value in the map, must be code error!");
        }
    }

    public enum Edge {
        NONE,
        RAISED,
        DEPRESSED,
        UNIFORM,
        DROP_SHADOW;

        private static final ImmutableMap<String, Edge> EDGES;

        static {
            Edge edge = NONE;
            Edge edge2 = RAISED;
            Edge edge3 = DEPRESSED;
            Edge edge4 = UNIFORM;
            EDGES = (ImmutableMap) Preconditions2.checkFullValueMapping(Edge.class, ImmutableMap.builder().put("no", edge).put("raised", edge2).put("depressed", edge3).put("uniform", edge4).put("shadow", DROP_SHADOW).build());
        }

        @Nullable
        public static Edge lookup(String str) {
            if (str != null) {
                ImmutableMap<String, Edge> immutableMap = EDGES;
                if (immutableMap.containsKey(str)) {
                    return immutableMap.get(str);
                }
            }
            return null;
        }

        @Nonnull
        public static String lookupKey(Edge edge) {
            UnmodifiableIterator<String> it = EDGES.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (edge == EDGES.get(next)) {
                    return next;
                }
            }
            throw new InvalidParameterException("can not find this Edge value in the map, must be code error!");
        }
    }

    public enum Font {
        UNDEFINED,
        MONOSPACE_SERIF,
        PROPORTIONAL_SERIF,
        MONOSPACE_SANS_SERIF,
        PROPORTIONAL_SANS_SERIF,
        CASUAL,
        CURSIVE,
        SMALL_CAPITALS;

        private static final ImmutableMap<String, Font> FONTS;

        static {
            Font font = UNDEFINED;
            Font font2 = MONOSPACE_SERIF;
            Font font3 = PROPORTIONAL_SERIF;
            Font font4 = MONOSPACE_SANS_SERIF;
            Font font5 = PROPORTIONAL_SANS_SERIF;
            Font font6 = CASUAL;
            Font font7 = CURSIVE;
            FONTS = (ImmutableMap) Preconditions2.checkFullValueMapping(Font.class, ImmutableMap.builder().put("default", font).put("monospaceSerif", font2).put("proportionalSerif", font3).put("monospaceSansSerif", font4).put("proportionalSansSerif", font5).put("casual", font6).put("cursive", font7).put("smallCapitals", SMALL_CAPITALS).build());
        }

        @Nullable
        public static Font lookup(String str) {
            if (str != null) {
                ImmutableMap<String, Font> immutableMap = FONTS;
                if (immutableMap.containsKey(str)) {
                    return immutableMap.get(str);
                }
            }
            return null;
        }

        @Nonnull
        public static String lookupKey(Font font) {
            UnmodifiableIterator<String> it = FONTS.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (font == FONTS.get(next)) {
                    return next;
                }
            }
            throw new InvalidParameterException("can not find this Font value in the map, must be code error!");
        }
    }

    static {
        Builder presetName = Builder.newBuilder().setPresetName("default");
        Transition transition = Transition.POP_ON;
        Builder transition2 = presetName.setTransition(transition);
        Font font = Font.PROPORTIONAL_SERIF;
        Builder textFont = transition2.setTextFont(font);
        Edge edge = Edge.NONE;
        DEFAULT = textFont.setTextEdge(edge).setTextColor(-1).setTextOpacity(1.0f).setTextSize(1.0f).setBackgroundColor(-7829368).setBackgroundOpacity(0.5f).setWindowColor(-7829368).setWindowOpacity(0.0f).setIsCustomized(false).setIconUrl(null).build();
        SYSTEM_PRESET_1 = Builder.newBuilder().setPresetName("preset1").setTransition(transition).setTextFont(font).setTextEdge(edge).setTextColor(-1).setTextOpacity(1.0f).setTextSize(1.0f).setBackgroundColor(-12303292).setBackgroundOpacity(1.0f).setWindowColor(-12303292).setWindowOpacity(0.0f).setIsCustomized(false).setIconUrl(null).build();
        SYSTEM_PRESET_2 = Builder.newBuilder().setPresetName("preset2").setTransition(transition).setTextFont(font).setTextEdge(edge).setTextColor(InputDeviceCompat.SOURCE_ANY).setTextOpacity(1.0f).setTextSize(1.0f).setBackgroundColor(ViewCompat.MEASURED_STATE_MASK).setBackgroundOpacity(1.0f).setWindowColor(ViewCompat.MEASURED_STATE_MASK).setWindowOpacity(0.0f).setIsCustomized(false).setIconUrl(null).build();
        SYSTEM_PRESET_3 = Builder.newBuilder().setPresetName("preset3").setTransition(transition).setTextFont(font).setTextEdge(edge).setTextColor(ViewCompat.MEASURED_STATE_MASK).setTextOpacity(1.0f).setTextSize(1.0f).setBackgroundColor(-1).setBackgroundOpacity(0.0f).setWindowColor(-1).setWindowOpacity(0.0f).setIsCustomized(false).setIconUrl(null).build();
    }

    private SubtitlePreset(@Nonnull String str, @Nonnull Transition transition, @Nonnull Font font, @Nonnull Edge edge, int i, float f, float f2, int i2, float f3, int i3, float f4, boolean z, @Nullable URL url, int i4) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "Preset Name cannot be null or empty");
        this.mPresetName = str;
        this.mTextFont = (Font) Preconditions.checkNotNull(font, "Preset Font cannot be null");
        this.mTextEdge = (Edge) Preconditions.checkNotNull(edge, "Preset Edge cannot be null");
        this.mTransition = (Transition) Preconditions.checkNotNull(transition, "Preset Transition cannot be null");
        Preconditions.checkArgument(f >= 0.0f && f <= 1.0f, "Text Opacity is not between 0 and 1");
        Preconditions.checkArgument(f3 >= 0.0f && f3 <= 1.0f, "Background Opacity is not between 0 and 1");
        Preconditions.checkArgument(f4 >= 0.0f && f4 <= 1.0f, "Window Opacity is not between 0 and 1");
        Preconditions.checkArgument(f2 > 0.0f, "Text size is a multiplier, and must be positive");
        this.mTextColor = i;
        this.mTextOpacity = f;
        this.mTextSize = f2;
        this.mBackgroundColor = i2;
        this.mBackgroundOpacity = f3;
        this.mWindowColor = i3;
        this.mWindowOpacity = f4;
        this.mCustomized = z;
        this.mIconUrl = url;
        this.mPlaceholderResId = i4;
    }

    @Nonnull
    public String getPresetName() {
        return this.mPresetName;
    }

    @Nonnull
    public Transition getTransition() {
        return this.mTransition;
    }

    @Nonnull
    public Font getTextFont() {
        return this.mTextFont;
    }

    @Nonnull
    public Edge getTextEdge() {
        return this.mTextEdge;
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public float getTextOpacity() {
        return this.mTextOpacity;
    }

    public float getTextSize() {
        return this.mTextSize;
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public float getBackgroundOpacity() {
        return this.mBackgroundOpacity;
    }

    public int getWindowColor() {
        return this.mWindowColor;
    }

    public float getWindowOpacity() {
        return this.mWindowOpacity;
    }

    public boolean getIsCustomized() {
        return this.mCustomized;
    }

    @Nullable
    public URL getIconUrl() {
        return this.mIconUrl;
    }

    public int getPlaceholderResId() {
        return this.mPlaceholderResId;
    }

    public String getNormalizedIconFilename() {
        String string = this.mIconUrl.toString();
        return String.format("%s_%s.%s", this.mPresetName, "icon", string.substring(string.lastIndexOf(46) + 1));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mPresetName", this.mPresetName).add("mTextColor", this.mTextColor).add("mTextOpacity", this.mTextOpacity).add("mTextSize", this.mTextSize).add("mTextFont", this.mTextFont).add("mTextEdge", this.mTextEdge).add("mBackgroundColor", this.mBackgroundColor).add("mBackgroundOpacity", this.mBackgroundOpacity).add("mTransition", this.mTransition).add("mWindowColor", this.mWindowColor).add("mWindowOpacity", this.mWindowOpacity).add("mCustomized", this.mCustomized).add("mIconUrl", this.mIconUrl).add("mPlaceholderResId", this.mPlaceholderResId).toString();
    }

    public static class Builder {
        private String mPresetName;
        private int mTextColor = -1;
        private float mTextOpacity = 1.0f;
        private float mTextSize = 1.0f;
        private Font mTextFont = Font.PROPORTIONAL_SERIF;
        private Edge mTextEdge = Edge.NONE;
        private int mBackgroundColor = -7829368;
        private float mBackgroundOpacity = 0.5f;
        private Transition mTransition = Transition.POP_ON;
        private int mWindowColor = -7829368;
        private float mWindowOpacity = 0.0f;
        private boolean mCustomized = false;
        private URL mIconUrl = null;
        private int mPlaceholderResId = R$drawable.subtitle_preset_placeholder_default;

        private Builder() {
        }

        @Nonnull
        public static Builder newBuilder() {
            return new Builder();
        }

        @Nonnull
        public Builder setPresetName(@Nonnull String str) {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "Preset Name cannot be null or empty");
            this.mPresetName = str;
            return this;
        }

        @Nonnull
        public Builder setTransition(@Nullable Transition transition) {
            if (transition != null) {
                this.mTransition = transition;
            }
            return this;
        }

        @Nonnull
        public Builder setTextFont(@Nullable Font font) {
            if (font != null) {
                this.mTextFont = font;
            }
            return this;
        }

        @Nonnull
        public Builder setTextEdge(@Nullable Edge edge) {
            if (edge != null) {
                this.mTextEdge = edge;
            }
            return this;
        }

        @Nonnull
        public Builder setTextColor(int i) {
            this.mTextColor = i;
            return this;
        }

        @Nonnull
        public Builder setTextOpacity(float f) {
            if (f >= 0.0f && f <= 1.0f) {
                this.mTextOpacity = f;
            }
            return this;
        }

        @Nonnull
        public Builder setTextSize(float f) {
            if (f > 0.0f) {
                this.mTextSize = f;
            }
            return this;
        }

        @Nonnull
        public Builder setBackgroundColor(int i) {
            this.mBackgroundColor = i;
            return this;
        }

        @Nonnull
        public Builder setBackgroundOpacity(float f) {
            if (f >= 0.0f && f <= 1.0f) {
                this.mBackgroundOpacity = f;
            }
            return this;
        }

        @Nonnull
        public Builder setWindowColor(int i) {
            this.mWindowColor = i;
            return this;
        }

        @Nonnull
        public Builder setWindowOpacity(float f) {
            if (f >= 0.0f && f <= 1.0f) {
                this.mWindowOpacity = f;
            }
            return this;
        }

        @Nonnull
        public Builder setIsCustomized(boolean z) {
            this.mCustomized = z;
            return this;
        }

        @Nonnull
        public Builder setIconUrl(@Nullable URL url) {
            this.mIconUrl = url;
            return this;
        }

        @Nonnull
        public SubtitlePreset build() {
            return new SubtitlePreset(this.mPresetName, this.mTransition, this.mTextFont, this.mTextEdge, this.mTextColor, this.mTextOpacity, this.mTextSize, this.mBackgroundColor, this.mBackgroundOpacity, this.mWindowColor, this.mWindowOpacity, this.mCustomized, this.mIconUrl, this.mPlaceholderResId);
        }
    }
}
