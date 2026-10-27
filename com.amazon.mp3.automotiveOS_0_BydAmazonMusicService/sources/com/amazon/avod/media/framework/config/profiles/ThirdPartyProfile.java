package com.amazon.avod.media.framework.config.profiles;

import amazon.android.config.ConfigRegistry;
import amazon.android.config.ConfigType;
import amazon.android.config.internal.StringConfigurationValue;
import com.amazon.avod.media.framework.config.PlaybackConfigKeys;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParser;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ThirdPartyProfile {
    private static final ThirdPartyProfile DEFAULT_INSTANCE;
    private static final List<String> GALAXY_S7_EXYNOS_MODELS;
    private static final String SAMSUNG_NOTE_7_MODEL_LIST;
    private static final String SONY_MODEL_LIST;
    private static final StringConfigurationValue mS7ExynonModelsConfigValue;
    private static final Joiner mSemicolonDelimiterStringJoiner = Joiner.on(';');
    private final Map<ThirdPartyProfileName, String> mProfileMap;

    static {
        String samsungNote7ModelList = getSamsungNote7ModelList();
        SAMSUNG_NOTE_7_MODEL_LIST = samsungNote7ModelList;
        String sonyModelList = getSonyModelList();
        SONY_MODEL_LIST = sonyModelList;
        ImmutableList immutableListOf = ImmutableList.of("samsung SM-G930F", "samsung SM-G930W8", "samsung SM-G930K", "samsung SM-G930L", "samsung SM-G930S", "samsung SM-G930X", "samsung SM-G935F", "samsung SM-G935W8", "samsung SM-G935K", "samsung SM-G935L", "samsung SM-G935S");
        GALAXY_S7_EXYNOS_MODELS = immutableListOf;
        mS7ExynonModelsConfigValue = new StringConfigurationValue(PlaybackConfigKeys.GALAXY_S7_EXYNOS_MODELS_KEY, Joiner.on(";").join(immutableListOf), ConfigRegistry.getInstance().getConfigEditor(ConfigType.SERVER));
        DEFAULT_INSTANCE = new ThirdPartyProfile(ImmutableMap.builder().put(ThirdPartyProfileName.SD, "").put(ThirdPartyProfileName.HD, "").put(ThirdPartyProfileName.HDR, samsungNote7ModelList).put(ThirdPartyProfileName.UHD_HDR, sonyModelList).build());
    }

    public ThirdPartyProfile(@Nonnull Map<ThirdPartyProfileName, String> map) {
        this.mProfileMap = (Map) Preconditions.checkNotNull(map, "profileMap");
    }

    public static ThirdPartyProfile getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public Map<ThirdPartyProfileName, String> getProfileMap() {
        return this.mProfileMap;
    }

    public static class Parser implements JacksonJsonParser<ThirdPartyProfile> {
        private final MapParser<ThirdPartyProfileName, String> mProfileMapParser = MapParser.newParser(EnumParser.newParser(ThirdPartyProfileName.class), new SimpleParsers.StringParser());

        @Override // com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ThirdPartyProfile parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            return new ThirdPartyProfile(this.mProfileMapParser.parse(jsonNode));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ThirdPartyProfile parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            return new ThirdPartyProfile(this.mProfileMapParser.parse(jsonParser));
        }
    }

    @Nonnull
    public static List<String> getSamsungNoteS7ExynosModelList() {
        return Splitter.on(";").splitToList(mS7ExynonModelsConfigValue.getValue());
    }

    @Nonnull
    private static String getSamsungNote7ModelList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("samsung SAMSUNG-SM-N930A");
        arrayList.add("samsung SC-01J");
        arrayList.add("samsung SCV34");
        arrayList.add("samsung SM-N9300");
        arrayList.add("samsung SM-N930F");
        arrayList.add("samsung SM-N930K");
        arrayList.add("samsung SM-N930L");
        arrayList.add("samsung SM-N930P");
        arrayList.add("samsung SM-N930R4");
        arrayList.add("samsung SM-N930R6");
        arrayList.add("samsung SM-N930R7");
        arrayList.add("samsung SM-N930S");
        arrayList.add("samsung SM-N930T");
        arrayList.add("samsung SM-N930U");
        arrayList.add("samsung SM-N930V");
        arrayList.add("samsung SM-N930W8");
        return mSemicolonDelimiterStringJoiner.join(arrayList);
    }

    @Nonnull
    private static String getSonyModelList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("Sony G8141");
        arrayList.add("Sony G8142");
        arrayList.add("Sony SO-04J");
        return mSemicolonDelimiterStringJoiner.join(arrayList);
    }
}
