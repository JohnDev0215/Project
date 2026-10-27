package com.facebook.react.animated;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
class TransformAnimatedNode extends AnimatedNode {
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    private final List<TransformConfig> mTransformConfigs;

    private class TransformConfig {
        public String mProperty;

        private TransformConfig() {
        }
    }

    private class AnimatedTransformConfig extends TransformConfig {
        public int mNodeTag;

        private AnimatedTransformConfig() {
            super();
        }
    }

    private class StaticTransformConfig extends TransformConfig {
        public double mValue;

        private StaticTransformConfig() {
            super();
        }
    }

    TransformAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        ReadableArray array = readableMap.getArray("transforms");
        this.mTransformConfigs = new ArrayList(array.size());
        for (int i = 0; i < array.size(); i++) {
            ReadableMap map = array.getMap(i);
            String string = map.getString("property");
            if (map.getString(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE).equals("animated")) {
                AnimatedTransformConfig animatedTransformConfig = new AnimatedTransformConfig();
                animatedTransformConfig.mProperty = string;
                animatedTransformConfig.mNodeTag = map.getInt("nodeTag");
                this.mTransformConfigs.add(animatedTransformConfig);
            } else {
                StaticTransformConfig staticTransformConfig = new StaticTransformConfig();
                staticTransformConfig.mProperty = string;
                staticTransformConfig.mValue = map.getDouble("value");
                this.mTransformConfigs.add(staticTransformConfig);
            }
        }
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
    }

    public void collectViewUpdates(JavaOnlyMap javaOnlyMap) {
        double value;
        ArrayList arrayList = new ArrayList(this.mTransformConfigs.size());
        for (TransformConfig transformConfig : this.mTransformConfigs) {
            if (transformConfig instanceof AnimatedTransformConfig) {
                AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(((AnimatedTransformConfig) transformConfig).mNodeTag);
                if (nodeById == null) {
                    throw new IllegalArgumentException("Mapped style node does not exists");
                }
                if (nodeById instanceof ValueAnimatedNode) {
                    value = ((ValueAnimatedNode) nodeById).getValue();
                } else {
                    throw new IllegalArgumentException("Unsupported type of node used as a transform child node " + nodeById.getClass());
                }
            } else {
                value = ((StaticTransformConfig) transformConfig).mValue;
            }
            arrayList.add(JavaOnlyMap.of(transformConfig.mProperty, Double.valueOf(value)));
        }
        javaOnlyMap.putArray("transform", JavaOnlyArray.from(arrayList));
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public String prettyPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append("TransformAnimatedNode[");
        sb.append(this.mTag);
        sb.append("]: mTransformConfigs: ");
        List<TransformConfig> list = this.mTransformConfigs;
        sb.append(list != null ? list.toString() : "null");
        return sb.toString();
    }
}
