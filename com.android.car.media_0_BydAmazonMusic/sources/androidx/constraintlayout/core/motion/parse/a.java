package androidx.constraintlayout.core.motion.parse;

import androidx.constraintlayout.core.motion.parse.KeyParser;
import androidx.constraintlayout.core.motion.utils.TypedValues;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements KeyParser.Ids, KeyParser.DataType {
    public static final /* synthetic */ a a = new a();
    public static final /* synthetic */ a b = new a();

    @Override // androidx.constraintlayout.core.motion.parse.KeyParser.DataType
    public final int get(int i) {
        return TypedValues.Attributes.getType(i);
    }

    @Override // androidx.constraintlayout.core.motion.parse.KeyParser.Ids
    public final int get(String str) {
        return TypedValues.Attributes.getId(str);
    }
}
