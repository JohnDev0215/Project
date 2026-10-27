package com.amazon.avod.dash.config.miyagi;

import androidx.core.util.Pair;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsGraph;
import com.amazon.avod.util.DLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class HeuristicsConfigBase {
    private final Map<String, Pair<String, OverrideType>> mConfigValues = Maps.newHashMap();

    enum OverrideType {
        DEBUG(0),
        HEURISTICS_ENGINE(1),
        SESSION_SETTINGS(2),
        NONE(3);

        private final int mPriority;

        OverrideType(int i) {
            this.mPriority = i;
        }

        public int getPriority() {
            return this.mPriority;
        }
    }

    HeuristicsConfigBase(@Nullable Map<String, String> map, @Nullable Map<String, String> map2, @Nonnull Map<String, String> map3) {
        applyOverrides((Map) Preconditions.checkNotNull(map3, "appLoadValues"), OverrideType.NONE);
        applyOverrides(map2, OverrideType.SESSION_SETTINGS);
        applyOverrides(map, OverrideType.DEBUG);
    }

    @Nullable
    private String getStringConfigValue(String str) {
        Pair<String, OverrideType> pair = this.mConfigValues.get(str);
        if (pair != null) {
            return pair.first;
        }
        return null;
    }

    @Nonnull
    protected String getStringConfigValue(String str, @Nonnull String str2) {
        Preconditions.checkNotNull(str2, "defaultValue");
        String stringConfigValue = getStringConfigValue(str);
        return stringConfigValue != null ? stringConfigValue : str2;
    }

    protected boolean getBooleanConfigValue(String str, boolean z) {
        String stringConfigValue = getStringConfigValue(str);
        return stringConfigValue != null ? Boolean.parseBoolean(stringConfigValue) : z;
    }

    protected int getIntegerConfigValue(String str, int i) {
        String stringConfigValue = getStringConfigValue(str);
        if (stringConfigValue != null) {
            try {
                return Integer.parseInt(stringConfigValue);
            } catch (NumberFormatException unused) {
                DLog.warnf("Failed to parse %s to int. Using default value %s", stringConfigValue, Integer.valueOf(i));
            }
        }
        return i;
    }

    protected long getLongConfigValue(String str, long j) {
        String stringConfigValue = getStringConfigValue(str);
        if (stringConfigValue != null) {
            try {
                return Long.parseLong(stringConfigValue);
            } catch (NumberFormatException unused) {
                DLog.warnf("Failed to parse %s to long. Using default value %s", stringConfigValue, Long.valueOf(j));
            }
        }
        return j;
    }

    protected double getDoubleConfigValue(String str, double d) {
        String stringConfigValue = getStringConfigValue(str);
        if (stringConfigValue != null) {
            try {
                return Double.parseDouble(stringConfigValue);
            } catch (NumberFormatException unused) {
                DLog.warnf("Failed to parse %s to double. Using default value %s", stringConfigValue, Double.valueOf(d));
            }
        }
        return d;
    }

    protected float getFloatConfigValue(String str, float f) {
        String stringConfigValue = getStringConfigValue(str);
        if (stringConfigValue != null) {
            try {
                return Float.parseFloat(stringConfigValue);
            } catch (NumberFormatException unused) {
                DLog.warnf("Failed to parse %s to float. Using default value %s", stringConfigValue, Float.valueOf(f));
            }
        }
        return f;
    }

    protected <E extends Enum<E>> E getEnumConfigValue(String str, E e, Class<E> cls) {
        String stringConfigValue = getStringConfigValue(str);
        if (stringConfigValue != null) {
            try {
                return (E) Enum.valueOf(cls, stringConfigValue);
            } catch (NumberFormatException unused) {
                DLog.warnf("Failed to parse %s to enum. Using default value %s", stringConfigValue, e);
            }
        }
        return e;
    }

    protected HeuristicsGraph getHeuristicsGraphConfigValue(String str, double[] dArr, double[] dArr2) {
        String stringConfigValue = getStringConfigValue(str);
        if (stringConfigValue != null) {
            try {
                HeuristicsGraph heuristicsGraphFromString = HeuristicsGraph.parseHeuristicsGraphFromString(stringConfigValue);
                if (heuristicsGraphFromString.getXValues().length > 0) {
                    return heuristicsGraphFromString;
                }
                DLog.warnf("Length of parsing result should be positive. Input str: %s. Using default values", stringConfigValue);
            } catch (IllegalArgumentException unused) {
                DLog.warnf("Failed to parse %s to HeuristicsGraph. Using default values", stringConfigValue);
            }
        }
        return new HeuristicsGraph(dArr, dArr2);
    }

    protected double[][] getHeuristicsPairConfigValue(String str, double[][] dArr) {
        String stringConfigValue = getStringConfigValue(str);
        if (stringConfigValue != null) {
            try {
                double[][] dArr2 = (double[][]) new ObjectMapper().readValue(stringConfigValue, double[][].class);
                Preconditions.checkArgument(dArr2.length > 0, "Incorrect format of input: %s", stringConfigValue);
                Preconditions.checkArgument(dArr2[0].length == 2, "Incorrect format of input: %s", stringConfigValue);
                return dArr2;
            } catch (IOException | IllegalArgumentException e) {
                DLog.warnf("Failed to parse %s to heuristic pair. Using default values. Exception: %s", stringConfigValue, e);
            }
        }
        return dArr;
    }

    protected void applyOverrides(@Nullable Map<String, String> map, @Nonnull OverrideType overrideType) {
        if (map == null) {
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Pair<String, OverrideType> pair = this.mConfigValues.get(entry.getKey());
            if (pair == null || ((OverrideType) Preconditions.checkNotNull(pair.second)).getPriority() >= overrideType.getPriority()) {
                this.mConfigValues.put(entry.getKey(), Pair.create(entry.getValue(), overrideType));
            }
        }
    }
}
