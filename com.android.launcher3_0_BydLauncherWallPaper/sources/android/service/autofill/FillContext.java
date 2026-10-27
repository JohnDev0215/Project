package android.service.autofill;

import android.annotation.NonNull;
import android.app.assist.AssistStructure;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.SparseIntArray;
import android.view.autofill.AutofillId;
import android.view.autofill.Helper;
import com.android.internal.util.AnnotationValidations;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes3.dex */
public final class FillContext implements Parcelable {
    public static final Parcelable.Creator<FillContext> CREATOR = new Parcelable.Creator<FillContext>() { // from class: android.service.autofill.FillContext.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FillContext[] newArray(int i) {
            return new FillContext[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FillContext createFromParcel(Parcel parcel) {
            return new FillContext(parcel.readInt(), (AssistStructure) parcel.readTypedObject(AssistStructure.CREATOR), (AutofillId) parcel.readTypedObject(AutofillId.CREATOR));
        }
    };
    private final AutofillId mFocusedId;
    private final int mRequestId;
    private final AssistStructure mStructure;
    private transient ArrayMap<AutofillId, AssistStructure.ViewNode> mViewNodeLookupTable;

    @Deprecated
    private void __metadata() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return !Helper.sDebug ? super.toString() : "FillContext [reqId=" + this.mRequestId + ", focusedId=" + this.mFocusedId + "]";
    }

    public AssistStructure.ViewNode[] findViewNodesByAutofillIds(AutofillId[] autofillIdArr) {
        LinkedList linkedList = new LinkedList();
        AssistStructure.ViewNode[] viewNodeArr = new AssistStructure.ViewNode[autofillIdArr.length];
        SparseIntArray sparseIntArray = new SparseIntArray(autofillIdArr.length);
        for (int i = 0; i < autofillIdArr.length; i++) {
            ArrayMap<AutofillId, AssistStructure.ViewNode> arrayMap = this.mViewNodeLookupTable;
            if (arrayMap != null) {
                int iIndexOfKey = arrayMap.indexOfKey(autofillIdArr[i]);
                if (iIndexOfKey >= 0) {
                    viewNodeArr[i] = this.mViewNodeLookupTable.valueAt(iIndexOfKey);
                } else {
                    sparseIntArray.put(i, 0);
                }
            } else {
                sparseIntArray.put(i, 0);
            }
        }
        int windowNodeCount = this.mStructure.getWindowNodeCount();
        for (int i2 = 0; i2 < windowNodeCount; i2++) {
            linkedList.add(this.mStructure.getWindowNodeAt(i2).getRootViewNode());
        }
        while (sparseIntArray.size() > 0 && !linkedList.isEmpty()) {
            AssistStructure.ViewNode viewNode = (AssistStructure.ViewNode) linkedList.removeFirst();
            int i3 = 0;
            while (true) {
                if (i3 >= sparseIntArray.size()) {
                    break;
                }
                int iKeyAt = sparseIntArray.keyAt(i3);
                AutofillId autofillId = autofillIdArr[iKeyAt];
                if (autofillId.equals(viewNode.getAutofillId())) {
                    viewNodeArr[iKeyAt] = viewNode;
                    if (this.mViewNodeLookupTable == null) {
                        this.mViewNodeLookupTable = new ArrayMap<>(autofillIdArr.length);
                    }
                    this.mViewNodeLookupTable.put(autofillId, viewNode);
                    sparseIntArray.removeAt(i3);
                } else {
                    i3++;
                }
            }
            for (int i4 = 0; i4 < viewNode.getChildCount(); i4++) {
                linkedList.addLast(viewNode.getChildAt(i4));
            }
        }
        for (int i5 = 0; i5 < sparseIntArray.size(); i5++) {
            if (this.mViewNodeLookupTable == null) {
                this.mViewNodeLookupTable = new ArrayMap<>(sparseIntArray.size());
            }
            this.mViewNodeLookupTable.put(autofillIdArr[sparseIntArray.keyAt(i5)], null);
        }
        return viewNodeArr;
    }

    public FillContext(int i, AssistStructure assistStructure, AutofillId autofillId) {
        this.mRequestId = i;
        this.mStructure = assistStructure;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) assistStructure);
        this.mFocusedId = autofillId;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) autofillId);
    }

    public int getRequestId() {
        return this.mRequestId;
    }

    public AssistStructure getStructure() {
        return this.mStructure;
    }

    public AutofillId getFocusedId() {
        return this.mFocusedId;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mRequestId);
        parcel.writeTypedObject(this.mStructure, i);
        parcel.writeTypedObject(this.mFocusedId, i);
    }
}
