package com.amazon.ion.impl.lite;

import com.amazon.ion.IonLob;
import com.amazon.ion.impl._Private_IonValue;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.zip.CRC32;

/* JADX INFO: loaded from: classes3.dex */
abstract class IonLobLite extends IonValueLite implements IonLob {
    private byte[] _lob_value;

    @Override // com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public abstract IonLobLite mo103clone();

    protected IonLobLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    IonLobLite(IonLobLite ionLobLite, IonContext ionContext) {
        super(ionLobLite, ionContext);
        byte[] bArr = ionLobLite._lob_value;
        if (bArr != null) {
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            this._lob_value = bArr2;
            System.arraycopy(ionLobLite._lob_value, 0, bArr2, 0, length);
        }
    }

    protected int lobHashCode(int i, _Private_IonValue.SymbolTableProvider symbolTableProvider) {
        if (!isNullValue()) {
            CRC32 crc32 = new CRC32();
            crc32.update(getBytes());
            i ^= (int) crc32.getValue();
        }
        return hashTypeAnnotations(i, symbolTableProvider);
    }

    protected final void copyBytesFrom(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            this._lob_value = null;
            _isNullValue(true);
            return;
        }
        byte[] bArr2 = this._lob_value;
        if (bArr2 == null || bArr2.length != i2) {
            this._lob_value = new byte[i2];
        }
        System.arraycopy(bArr, i, this._lob_value, 0, i2);
        _isNullValue(false);
    }

    protected byte[] getBytesNoCopy() {
        return this._lob_value;
    }

    @Override // com.amazon.ion.IonLob
    public final InputStream newInputStream() {
        if (_isNullValue()) {
            return null;
        }
        return new ByteArrayInputStream(this._lob_value);
    }

    public final byte[] getBytes() {
        if (_isNullValue()) {
            return null;
        }
        return (byte[]) this._lob_value.clone();
    }

    public final void setBytes(byte[] bArr, int i, int i2) {
        checkForLock();
        copyBytesFrom(bArr, i, i2);
    }

    @Override // com.amazon.ion.IonLob
    public final int byteSize() {
        validateThisNotNull();
        return this._lob_value.length;
    }
}
