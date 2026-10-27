package kotlin.enums;

import p.fe0;
import p.py0;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final fe0 a(final Enum[] enumArr) {
        EnumEntriesList enumEntriesList = new EnumEntriesList(new py0() { // from class: kotlin.enums.EnumEntriesKt$enumEntries$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // p.py0
            public final Object invoke() {
                return enumArr;
            }
        });
        int length = enumEntriesList.b().length;
        return enumEntriesList;
    }
}
