package com.google.protobuf;

import com.amazon.music.destination.parser.ParserUtil;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes7.dex */
public final class Internal {
    public static final byte[] EMPTY_BYTE_ARRAY;
    public static final ByteBuffer EMPTY_BYTE_BUFFER;
    public static final CodedInputStream EMPTY_CODED_INPUT_STREAM;
    static final Charset US_ASCII = Charset.forName("US-ASCII");
    static final Charset UTF_8 = Charset.forName(ParserUtil.UTF_8);
    static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

    public interface BooleanList extends ProtobufList<Boolean> {
        @Override // 
        ProtobufList<Boolean> mutableCopyWithCapacity(int capacity);
    }

    public interface DoubleList extends ProtobufList<Double> {
        @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
        ProtobufList<Double> mutableCopyWithCapacity(int capacity);
    }

    public interface EnumLite {
        int getNumber();
    }

    public interface EnumLiteMap<T extends EnumLite> {
        T findValueByNumber(int number);
    }

    public interface EnumVerifier {
        boolean isInRange(int number);
    }

    public interface FloatList extends ProtobufList<Float> {
        @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
        ProtobufList<Float> mutableCopyWithCapacity(int capacity);
    }

    public interface IntList extends ProtobufList<Integer> {
        void addInt(int element);

        int getInt(int index);

        @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
        ProtobufList<Integer> mutableCopyWithCapacity(int capacity);

        int setInt(int index, int element);
    }

    public interface LongList extends ProtobufList<Long> {
        @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
        ProtobufList<Long> mutableCopyWithCapacity(int capacity);
    }

    public interface ProtobufList<E> extends List<E>, RandomAccess {
        boolean isModifiable();

        void makeImmutable();

        ProtobufList<E> mutableCopyWithCapacity(int capacity);
    }

    public static int hashBoolean(boolean b) {
        return b ? 1231 : 1237;
    }

    public static int hashLong(long n) {
        return (int) (n ^ (n >>> 32));
    }

    private Internal() {
    }

    static {
        byte[] bArr = new byte[0];
        EMPTY_BYTE_ARRAY = bArr;
        EMPTY_BYTE_BUFFER = ByteBuffer.wrap(bArr);
        EMPTY_CODED_INPUT_STREAM = CodedInputStream.newInstance(bArr);
    }

    static <T> T checkNotNull(T obj) {
        obj.getClass();
        return obj;
    }

    static <T> T checkNotNull(T obj, String message) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(message);
    }

    public static boolean isValidUtf8(byte[] byteArray) {
        return Utf8.isValidUtf8(byteArray);
    }

    public static String toStringUtf8(byte[] bytes) {
        return new String(bytes, UTF_8);
    }

    public static int hashEnum(EnumLite e) {
        return e.getNumber();
    }

    public static int hashEnumList(List<? extends EnumLite> list) {
        Iterator<? extends EnumLite> it = list.iterator();
        int iHashEnum = 1;
        while (it.hasNext()) {
            iHashEnum = (iHashEnum * 31) + hashEnum(it.next());
        }
        return iHashEnum;
    }

    public static int hashCode(byte[] bytes) {
        return hashCode(bytes, 0, bytes.length);
    }

    static int hashCode(byte[] bytes, int offset, int length) {
        int iPartialHash = partialHash(length, bytes, offset, length);
        if (iPartialHash == 0) {
            return 1;
        }
        return iPartialHash;
    }

    static int partialHash(int h, byte[] bytes, int offset, int length) {
        for (int i = offset; i < offset + length; i++) {
            h = (h * 31) + bytes[i];
        }
        return h;
    }

    static Object mergeMessage(Object destination, Object source) {
        return ((MessageLite) destination).toBuilder().mergeFrom((MessageLite) source).buildPartial();
    }

    public static class ListAdapter<F, T> extends AbstractList<T> {
        private final Converter<F, T> converter;
        private final List<F> fromList;

        public interface Converter<F, T> {
            T convert(F from);
        }

        public ListAdapter(List<F> fromList, Converter<F, T> converter) {
            this.fromList = fromList;
            this.converter = converter;
        }

        @Override // java.util.AbstractList, java.util.List
        public T get(int i) {
            return (T) this.converter.convert(this.fromList.get(i));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.fromList.size();
        }
    }
}
