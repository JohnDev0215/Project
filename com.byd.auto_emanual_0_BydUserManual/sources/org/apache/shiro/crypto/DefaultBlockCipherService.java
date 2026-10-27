package org.apache.shiro.crypto;

import org.apache.shiro.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class DefaultBlockCipherService extends AbstractSymmetricCipherService {
    private static final int DEFAULT_BLOCK_SIZE = 0;
    private static final int DEFAULT_STREAMING_BLOCK_SIZE = 8;
    private static final String TRANSFORMATION_STRING_DELIMITER = "/";
    private int blockSize;
    private String modeName;
    private String paddingSchemeName;
    private int streamingBlockSize;
    private String streamingModeName;
    private String streamingPaddingSchemeName;
    private String streamingTransformationString;
    private String transformationString;

    public DefaultBlockCipherService(String str) {
        super(str);
        this.modeName = OperationMode.CBC.name();
        this.paddingSchemeName = PaddingScheme.PKCS5.getTransformationName();
        this.blockSize = 0;
        this.streamingModeName = OperationMode.CBC.name();
        this.streamingPaddingSchemeName = PaddingScheme.PKCS5.getTransformationName();
        this.streamingBlockSize = 8;
    }

    public String getModeName() {
        return this.modeName;
    }

    public void setModeName(String str) {
        this.modeName = str;
        this.transformationString = null;
    }

    public void setMode(OperationMode operationMode) {
        setModeName(operationMode.name());
    }

    public String getPaddingSchemeName() {
        return this.paddingSchemeName;
    }

    public void setPaddingSchemeName(String str) {
        this.paddingSchemeName = str;
        this.transformationString = null;
    }

    public void setPaddingScheme(PaddingScheme paddingScheme) {
        setPaddingSchemeName(paddingScheme.getTransformationName());
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public void setBlockSize(int i) {
        this.blockSize = Math.max(0, i);
        this.transformationString = null;
    }

    public String getStreamingModeName() {
        return this.streamingModeName;
    }

    private boolean isModeStreamingCompatible(String str) {
        return (str == null || str.equalsIgnoreCase(OperationMode.ECB.name()) || str.equalsIgnoreCase(OperationMode.NONE.name())) ? false : true;
    }

    public void setStreamingModeName(String str) {
        if (!isModeStreamingCompatible(str)) {
            throw new IllegalArgumentException("mode [" + str + "] is not a valid operation mode for block cipher streaming.");
        }
        this.streamingModeName = str;
        this.streamingTransformationString = null;
    }

    public void setStreamingMode(OperationMode operationMode) {
        setStreamingModeName(operationMode.name());
    }

    public String getStreamingPaddingSchemeName() {
        return this.streamingPaddingSchemeName;
    }

    public void setStreamingPaddingSchemeName(String str) {
        this.streamingPaddingSchemeName = str;
        this.streamingTransformationString = null;
    }

    public void setStreamingPaddingScheme(PaddingScheme paddingScheme) {
        setStreamingPaddingSchemeName(paddingScheme.getTransformationName());
    }

    public int getStreamingBlockSize() {
        return this.streamingBlockSize;
    }

    public void setStreamingBlockSize(int i) {
        this.streamingBlockSize = Math.max(0, i);
        this.streamingTransformationString = null;
    }

    @Override // org.apache.shiro.crypto.JcaCipherService
    protected String getTransformationString(boolean z) {
        if (z) {
            if (this.streamingTransformationString == null) {
                this.streamingTransformationString = buildStreamingTransformationString();
            }
            return this.streamingTransformationString;
        }
        if (this.transformationString == null) {
            this.transformationString = buildTransformationString();
        }
        return this.transformationString;
    }

    private String buildTransformationString() {
        return buildTransformationString(getModeName(), getPaddingSchemeName(), getBlockSize());
    }

    private String buildStreamingTransformationString() {
        return buildTransformationString(getStreamingModeName(), getStreamingPaddingSchemeName(), 0);
    }

    private String buildTransformationString(String str, String str2, int i) {
        StringBuilder sb = new StringBuilder(getAlgorithmName());
        if (StringUtils.hasText(str)) {
            sb.append("/").append(str);
        }
        if (i > 0) {
            sb.append(i);
        }
        if (StringUtils.hasText(str2)) {
            sb.append("/").append(str2);
        }
        return sb.toString();
    }

    private boolean isModeInitializationVectorCompatible(String str) {
        return (str == null || str.equalsIgnoreCase(OperationMode.ECB.name()) || str.equalsIgnoreCase(OperationMode.NONE.name())) ? false : true;
    }

    @Override // org.apache.shiro.crypto.JcaCipherService
    protected boolean isGenerateInitializationVectors(boolean z) {
        return z || (super.isGenerateInitializationVectors() && isModeInitializationVectorCompatible(getModeName()));
    }

    @Override // org.apache.shiro.crypto.JcaCipherService
    protected byte[] generateInitializationVector(boolean z) {
        if (z) {
            String streamingModeName = getStreamingModeName();
            if (!isModeInitializationVectorCompatible(streamingModeName)) {
                throw new IllegalStateException("streamingMode attribute value [" + streamingModeName + "] does not support Initialization Vectors.  Ensure the streamingMode value represents an operation mode that is compatible with initialization vectors.");
            }
        } else {
            String modeName = getModeName();
            if (!isModeInitializationVectorCompatible(modeName)) {
                throw new IllegalStateException("mode attribute value [" + modeName + "] does not support Initialization Vectors.  Ensure the mode value represents an operation mode that is compatible with initialization vectors.");
            }
        }
        return super.generateInitializationVector(z);
    }
}
