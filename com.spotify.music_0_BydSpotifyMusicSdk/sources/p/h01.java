package p;

import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class h01 extends r1 {
    private final com.google.protobuf.i defaultInstance;
    protected com.google.protobuf.i instance;

    public h01(com.google.protobuf.i iVar) {
        this.defaultInstance = iVar;
        if (iVar.isMutable()) {
            throw new IllegalArgumentException("Default instance must be immutable.");
        }
        this.instance = iVar.newMutableInstance();
    }

    public final com.google.protobuf.i build() {
        com.google.protobuf.i iVarBuildPartial = buildPartial();
        if (iVarBuildPartial.isInitialized()) {
            return iVarBuildPartial;
        }
        throw r1.newUninitializedMessageException(iVarBuildPartial);
    }

    public final h01 clear() {
        if (this.defaultInstance.isMutable()) {
            throw new IllegalArgumentException("Default instance must be immutable.");
        }
        this.instance = this.defaultInstance.newMutableInstance();
        return this;
    }

    public final void copyOnWrite() {
        if (this.instance.isMutable()) {
            return;
        }
        copyOnWriteInternal();
    }

    public void copyOnWriteInternal() {
        com.google.protobuf.i iVarNewMutableInstance = this.defaultInstance.newMutableInstance();
        com.google.protobuf.i iVar = this.instance;
        ry1 ry1Var = ry1.c;
        ry1Var.getClass();
        ry1Var.a(iVarNewMutableInstance.getClass()).a(iVarNewMutableInstance, iVar);
        this.instance = iVarNewMutableInstance;
    }

    @Override // p.yj1
    public com.google.protobuf.i getDefaultInstanceForType() {
        return this.defaultInstance;
    }

    @Override // p.yj1
    public final boolean isInitialized() {
        return com.google.protobuf.i.isInitialized(this.instance, false);
    }

    @Override // p.vj1
    public com.google.protobuf.i buildPartial() {
        if (!this.instance.isMutable()) {
            return this.instance;
        }
        this.instance.makeImmutable();
        return this.instance;
    }

    @Override // 
    public h01 clone() {
        h01 h01VarNewBuilderForType = getDefaultInstanceForType().newBuilderForType();
        h01VarNewBuilderForType.instance = buildPartial();
        return h01VarNewBuilderForType;
    }

    @Override // p.r1
    public h01 internalMergeFrom(com.google.protobuf.i iVar) {
        return mergeFrom(iVar);
    }

    @Override // p.r1
    public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
        return mergeFrom(bArr, 0, i2);
    }

    @Override // p.r1
    public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
        return mergeFrom(bArr, 0, i2, rl0Var);
    }

    public h01 mergeFrom(com.google.protobuf.i iVar) {
        if (getDefaultInstanceForType().equals(iVar)) {
            return this;
        }
        copyOnWrite();
        com.google.protobuf.i iVar2 = this.instance;
        ry1 ry1Var = ry1.c;
        ry1Var.getClass();
        ry1Var.a(iVar2.getClass()).a(iVar2, iVar);
        return this;
    }

    @Override // p.r1
    public h01 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) throws InvalidProtocolBufferException {
        copyOnWrite();
        try {
            ry1.c.b(this.instance).b(this.instance, bArr, i, i + i2, new j13(rl0Var));
            return this;
        } catch (InvalidProtocolBufferException e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
        } catch (IndexOutOfBoundsException unused) {
            throw InvalidProtocolBufferException.j();
        }
    }

    @Override // p.r1
    public h01 mergeFrom(byte[] bArr, int i, int i2) {
        return mergeFrom(bArr, i, i2, rl0.a());
    }

    @Override // p.r1
    public h01 mergeFrom(au auVar, rl0 rl0Var) throws IOException {
        copyOnWrite();
        try {
            k72 k72VarB = ry1.c.b(this.instance);
            com.google.protobuf.i iVar = this.instance;
            com.google.protobuf.e eVar = auVar.d;
            if (eVar == null) {
                eVar = new com.google.protobuf.e(auVar);
            }
            k72VarB.g(iVar, eVar, rl0Var);
            return this;
        } catch (RuntimeException e) {
            if (e.getCause() instanceof IOException) {
                throw ((IOException) e.getCause());
            }
            throw e;
        }
    }
}
