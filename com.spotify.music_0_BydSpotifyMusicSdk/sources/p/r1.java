package p;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class r1 implements vj1 {
    @Deprecated
    public static <T> void addAll(Iterable<T> iterable, Collection<? super T> collection) {
        addAll((Iterable) iterable, (List) collection);
    }

    public static UninitializedMessageException newUninitializedMessageException(wj1 wj1Var) {
        return new UninitializedMessageException();
    }

    public final String a() {
        return "Reading " + getClass().getName() + " from a ByteString threw an IOException (should never happen).";
    }

    public abstract r1 internalMergeFrom(com.google.protobuf.a aVar);

    public boolean mergeDelimitedFrom(InputStream inputStream, rl0 rl0Var) throws IOException {
        int i = inputStream.read();
        if (i == -1) {
            return false;
        }
        mergeFrom(new q1(au.t(i, inputStream), inputStream), rl0Var);
        return true;
    }

    public r1 mergeFrom(au auVar) {
        return mergeFrom(auVar, rl0.a());
    }

    public abstract r1 mergeFrom(au auVar, rl0 rl0Var);

    public abstract r1 mergeFrom(byte[] bArr, int i, int i2);

    public abstract r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var);

    public static <T> void addAll(Iterable<T> iterable, List<? super T> list) {
        Charset charset = w51.a;
        iterable.getClass();
        if (iterable instanceof b91) {
            List listL = ((b91) iterable).l();
            b91 b91Var = (b91) list;
            int size = list.size();
            for (Object obj : listL) {
                if (obj == null) {
                    String str = "Element at index " + (b91Var.size() - size) + " is null.";
                    for (int size2 = b91Var.size() - 1; size2 >= size; size2--) {
                        b91Var.remove(size2);
                    }
                    throw new NullPointerException(str);
                }
                if (obj instanceof ByteString) {
                    b91Var.g((ByteString) obj);
                } else {
                    b91Var.add((String) obj);
                }
            }
            return;
        }
        if (iterable instanceof dx1) {
            list.addAll((Collection) iterable);
            return;
        }
        if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
            ((ArrayList) list).ensureCapacity(((Collection) iterable).size() + list.size());
        }
        int size3 = list.size();
        for (T t : iterable) {
            if (t == null) {
                String str2 = "Element at index " + (list.size() - size3) + " is null.";
                for (int size4 = list.size() - 1; size4 >= size3; size4--) {
                    list.remove(size4);
                }
                throw new NullPointerException(str2);
            }
            list.add(t);
        }
    }

    public r1 mergeFrom(ByteString byteString) throws InvalidProtocolBufferException {
        try {
            au auVarM = byteString.m();
            mergeFrom(auVarM);
            auVarM.a(0);
            return this;
        } catch (InvalidProtocolBufferException e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException(a(), e2);
        }
    }

    public boolean mergeDelimitedFrom(InputStream inputStream) {
        return mergeDelimitedFrom(inputStream, rl0.a());
    }

    public r1 mergeFrom(ByteString byteString, rl0 rl0Var) throws InvalidProtocolBufferException {
        try {
            au auVarM = byteString.m();
            mergeFrom(auVarM, rl0Var);
            auVarM.a(0);
            return this;
        } catch (InvalidProtocolBufferException e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException(a(), e2);
        }
    }

    public r1 mergeFrom(byte[] bArr) {
        return mergeFrom(bArr, 0, bArr.length);
    }

    public r1 mergeFrom(byte[] bArr, rl0 rl0Var) {
        return mergeFrom(bArr, 0, bArr.length, rl0Var);
    }

    public r1 mergeFrom(InputStream inputStream) {
        au auVarG = au.g(inputStream);
        mergeFrom(auVarG);
        auVarG.a(0);
        return this;
    }

    public r1 mergeFrom(InputStream inputStream, rl0 rl0Var) {
        au auVarG = au.g(inputStream);
        mergeFrom(auVarG, rl0Var);
        auVarG.a(0);
        return this;
    }

    public r1 mergeFrom(wj1 wj1Var) {
        if (getDefaultInstanceForType().getClass().isInstance(wj1Var)) {
            return internalMergeFrom((com.google.protobuf.a) wj1Var);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
