package com.amazon.avod.content.config;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import amazon.android.config.ServerConfigBase;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.json.JacksonJsonParser;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.MapParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ClusterModelConfig extends ServerConfigBase {
    private final ConfigurationValue<ImmutableMap<String, ImmutableMap<String, ImmutableList<Double>>>> clusterModel;

    private static class SingletonHolder {
        private static final ClusterModelConfig INSTANCE = new ClusterModelConfig();

        private SingletonHolder() {
        }
    }

    public static ClusterModelConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private ClusterModelConfig() {
        this.clusterModel = newJacksonJsonConfigValue("cluster_model", getDefaultClusterModel(), ConfigType.SERVER, new Parser());
    }

    @Nullable
    public ImmutableMap<String, ImmutableMap<String, ImmutableList<Double>>> getClusterModel() {
        return this.clusterModel.getValue();
    }

    private ImmutableMap<String, ImmutableMap<String, ImmutableList<Double>>> getDefaultClusterModel() {
        try {
            JsonParser jsonParserCreateJsonParser = new JsonFactory().createJsonParser("{\n\t\"v0\" : {\n\t\t\"1-v0\" : [20.54,1.46,139.80,79.34,31.53,440.98],\n        \"2-v0\" : [14.31,0.53,91.38,51.87,16.87,321.05],\n        \"3-v0\" : [36.10,0.51,3218.30,77.65,28.38,458.61],\n        \"4-v0\" : [20.21,0.12,2211.16,225.11,54.82,1562.55],\n        \"5-v0\" : [4.17,0.31,24.17,100.92,37.37,529.84],\n        \"6-v0\" : [33.21,1.99,328.22,38.48,14.73,183.70],\n        \"7-v0\" : [11.69,0.28,139.60,124.37,20.98,1827.76],\n        \"8-v0\" : [1.54,0.09,11.71,310.96,56.24,2165.33]\n\t},\n\t\"v3\" : {\n\t\t\"1-v3\" : [94.13,91.88,48.31,28.35],\n        \"2-v3\" : [36.26,21.04,26.47,9.74],\n        \"3-v3\" : [31.44,24.71,48.18,36.23],\n        \"4-v3\" : [20.16,20.61,100.84,131.50],\n        \"5-v3\" : [13.49,8.07,47.65,26.06],\n        \"6-v3\" : [7.70,10.91,331.84,519.86],\n        \"7-v3\" : [4.73,3.17,90.36,73.54],\n        \"8-v3\" : [1.11,0.72,232.24,219.15]\n\t},\n\t\"v4\" : {\n\t    \"1-v4\" : [84.39,80.03,48.23,27.91],\n        \"2-v4\" : [34.09,19.48,26.34,98.40],\n        \"3-v4\" : [28.9,22.22,48.43,38.0],\n        \"4-v4\" : [18.10,18.75,107.76,143.59],\n        \"5-v4\" : [12.24,7.44,51.14,2.85],\n        \"6-v4\" : [6.41,9.17,348.52,540.41],\n        \"7-v4\" : [4.09,2.76,96.31,78.36],\n        \"8-v4\" : [0.98,0.68,271.94,267.9]\n\t}\n}");
            jsonParserCreateJsonParser.nextToken();
            return new Parser().parse(jsonParserCreateJsonParser);
        } catch (JsonContractException | IOException e) {
            DLog.errorf("Invalid Default Cluster Model String %s Exception %s", "{\n\t\"v0\" : {\n\t\t\"1-v0\" : [20.54,1.46,139.80,79.34,31.53,440.98],\n        \"2-v0\" : [14.31,0.53,91.38,51.87,16.87,321.05],\n        \"3-v0\" : [36.10,0.51,3218.30,77.65,28.38,458.61],\n        \"4-v0\" : [20.21,0.12,2211.16,225.11,54.82,1562.55],\n        \"5-v0\" : [4.17,0.31,24.17,100.92,37.37,529.84],\n        \"6-v0\" : [33.21,1.99,328.22,38.48,14.73,183.70],\n        \"7-v0\" : [11.69,0.28,139.60,124.37,20.98,1827.76],\n        \"8-v0\" : [1.54,0.09,11.71,310.96,56.24,2165.33]\n\t},\n\t\"v3\" : {\n\t\t\"1-v3\" : [94.13,91.88,48.31,28.35],\n        \"2-v3\" : [36.26,21.04,26.47,9.74],\n        \"3-v3\" : [31.44,24.71,48.18,36.23],\n        \"4-v3\" : [20.16,20.61,100.84,131.50],\n        \"5-v3\" : [13.49,8.07,47.65,26.06],\n        \"6-v3\" : [7.70,10.91,331.84,519.86],\n        \"7-v3\" : [4.73,3.17,90.36,73.54],\n        \"8-v3\" : [1.11,0.72,232.24,219.15]\n\t},\n\t\"v4\" : {\n\t    \"1-v4\" : [84.39,80.03,48.23,27.91],\n        \"2-v4\" : [34.09,19.48,26.34,98.40],\n        \"3-v4\" : [28.9,22.22,48.43,38.0],\n        \"4-v4\" : [18.10,18.75,107.76,143.59],\n        \"5-v4\" : [12.24,7.44,51.14,2.85],\n        \"6-v4\" : [6.41,9.17,348.52,540.41],\n        \"7-v4\" : [4.09,2.76,96.31,78.36],\n        \"8-v4\" : [0.98,0.68,271.94,267.9]\n\t}\n}", e.getMessage());
            return null;
        }
    }

    static class Parser implements JacksonJsonParser<ImmutableMap<String, ImmutableMap<String, ImmutableList<Double>>>> {
        private final MapParser<String, ImmutableMap<String, ImmutableList<Double>>> mClusterModelParser;

        Parser() {
            SimpleParsers.StringParser stringParser = SimpleParsers.StringParser.INSTANCE;
            this.mClusterModelParser = MapParser.newParser(stringParser, MapParser.newParser(stringParser, ListParser.newParser(SimpleParsers.DoubleParser.INSTANCE)));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public ImmutableMap<String, ImmutableMap<String, ImmutableList<Double>>> parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            return this.mClusterModelParser.parse(jsonNode);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public ImmutableMap<String, ImmutableMap<String, ImmutableList<Double>>> parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            return this.mClusterModelParser.parse(jsonParser);
        }
    }
}
