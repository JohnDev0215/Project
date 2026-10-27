package android.graphics;

import android.graphics.ColorSpace;
import libcore.util.NativeAllocationRegistry;

/* JADX INFO: loaded from: classes.dex */
public class RuntimeShader extends Shader {
    private boolean mIsOpaque;
    private long mNativeInstanceRuntimeShaderFactory;
    private byte[] mUniforms;

    private static native long nativeCreate(long j, long j2, byte[] bArr, long j3, boolean z);

    private static native long nativeCreateShaderFactory(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeGetFinalizer();

    private static class NoImagePreloadHolder {
        public static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(RuntimeShader.class.getClassLoader(), RuntimeShader.nativeGetFinalizer());

        private NoImagePreloadHolder() {
        }
    }

    public RuntimeShader(String str, byte[] bArr, boolean z) {
        this(str, bArr, z, ColorSpace.get(ColorSpace.Named.SRGB));
    }

    private RuntimeShader(String str, byte[] bArr, boolean z, ColorSpace colorSpace) {
        super(colorSpace);
        this.mUniforms = bArr;
        this.mIsOpaque = z;
        this.mNativeInstanceRuntimeShaderFactory = nativeCreateShaderFactory(str);
        NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, this.mNativeInstanceRuntimeShaderFactory);
    }

    public void updateUniforms(byte[] bArr) {
        this.mUniforms = bArr;
        discardNativeInstance();
    }

    @Override // android.graphics.Shader
    long createNativeInstance(long j) {
        return nativeCreate(this.mNativeInstanceRuntimeShaderFactory, j, this.mUniforms, colorSpace().getNativeInstance(), this.mIsOpaque);
    }
}
