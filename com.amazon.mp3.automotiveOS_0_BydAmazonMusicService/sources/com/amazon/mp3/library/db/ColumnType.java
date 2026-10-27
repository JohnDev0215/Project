package com.amazon.mp3.library.db;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ColumnType.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\n\u000b\f\rB\u0011\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0003\u001a\u00020\u0002H\u0016R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0004\u000e\u000f\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/library/db/ColumnType;", "", "", "toString", "typeString", "Ljava/lang/String;", "getTypeString", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "DecimalType", "IntegerType", "NumberType", "TextType", "Lcom/amazon/mp3/library/db/ColumnType$NumberType;", "Lcom/amazon/mp3/library/db/ColumnType$DecimalType;", "Lcom/amazon/mp3/library/db/ColumnType$IntegerType;", "Lcom/amazon/mp3/library/db/ColumnType$TextType;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public abstract class ColumnType {
    private final String typeString;

    public /* synthetic */ ColumnType(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    /* JADX INFO: compiled from: ColumnType.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/library/db/ColumnType$NumberType;", "Lcom/amazon/mp3/library/db/ColumnType;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class NumberType extends ColumnType {
        public static final NumberType INSTANCE = new NumberType();

        private NumberType() {
            super("NUMBER", null);
        }
    }

    private ColumnType(String str) {
        this.typeString = str;
    }

    /* JADX INFO: compiled from: ColumnType.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/library/db/ColumnType$DecimalType;", "Lcom/amazon/mp3/library/db/ColumnType;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class DecimalType extends ColumnType {
        public static final DecimalType INSTANCE = new DecimalType();

        private DecimalType() {
            super("DECIMAL", null);
        }
    }

    /* JADX INFO: compiled from: ColumnType.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/library/db/ColumnType$IntegerType;", "Lcom/amazon/mp3/library/db/ColumnType;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class IntegerType extends ColumnType {
        public static final IntegerType INSTANCE = new IntegerType();

        private IntegerType() {
            super("INTEGER", null);
        }
    }

    /* JADX INFO: compiled from: ColumnType.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/library/db/ColumnType$TextType;", "Lcom/amazon/mp3/library/db/ColumnType;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class TextType extends ColumnType {
        public static final TextType INSTANCE = new TextType();

        private TextType() {
            super("TEXT", null);
        }
    }

    /* JADX INFO: renamed from: toString, reason: from getter */
    public String getTypeString() {
        return this.typeString;
    }
}
