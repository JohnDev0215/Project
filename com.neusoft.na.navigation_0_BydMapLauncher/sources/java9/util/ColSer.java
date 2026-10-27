package java9.util;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java9.util.ImmutableCollections;

/* JADX INFO: compiled from: ImmutableCollections.java */
/* JADX INFO: loaded from: classes3.dex */
final class ColSer implements Serializable {
    static final int IMM_LIST = 1;
    static final int IMM_MAP = 3;
    static final int IMM_SET = 2;
    private static final long serialVersionUID = 6309168927139932177L;
    private transient Object[] array;
    private final int tag;

    ColSer(int i, Object... objArr) {
        this.tag = i;
        this.array = objArr;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        int i = objectInputStream.readInt();
        if (i < 0) {
            throw new InvalidObjectException("negative length " + i);
        }
        Object[] objArr = new Object[i];
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = objectInputStream.readObject();
        }
        this.array = objArr;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.array.length);
        int i = 0;
        while (true) {
            Object[] objArr = this.array;
            if (i >= objArr.length) {
                return;
            }
            objectOutputStream.writeObject(objArr[i]);
            i++;
        }
    }

    private Object readResolve() throws ObjectStreamException {
        try {
            Object[] objArr = this.array;
            if (objArr == null) {
                throw new InvalidObjectException("null array");
            }
            int i = this.tag & 255;
            if (i == 1) {
                return Lists.of(objArr);
            }
            if (i == 2) {
                return Sets.of(objArr);
            }
            if (i == 3) {
                if (objArr.length == 0) {
                    return ImmutableCollections.MapN.EMPTY_MAP;
                }
                if (objArr.length == 2) {
                    Object[] objArr2 = this.array;
                    return new ImmutableCollections.Map1(objArr2[0], objArr2[1]);
                }
                return new ImmutableCollections.MapN(this.array);
            }
            throw new InvalidObjectException(String.format("invalid flags 0x%x", Integer.valueOf(this.tag)));
        } catch (IllegalArgumentException e) {
            throw ioe(e);
        } catch (NullPointerException e2) {
            throw ioe(e2);
        }
    }

    private static InvalidObjectException ioe(RuntimeException runtimeException) {
        InvalidObjectException invalidObjectException = new InvalidObjectException("invalid object");
        invalidObjectException.initCause(runtimeException);
        return invalidObjectException;
    }
}
