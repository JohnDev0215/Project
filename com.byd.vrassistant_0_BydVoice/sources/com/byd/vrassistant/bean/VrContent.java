package com.byd.vrassistant.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.common.base.Objects;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class VrContent implements Parcelable {
    public static final Parcelable.Creator<VrContent> CREATOR = new Parcelable.Creator<VrContent>() { // from class: com.byd.vrassistant.bean.VrContent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VrContent createFromParcel(Parcel parcel) {
            return new VrContent(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VrContent[] newArray(int i) {
            return new VrContent[i];
        }
    };
    private static final String TAG = "NEU_VrContent";
    ContentState contentState;
    List<String> hints;
    String input;
    String interactionType;
    boolean isInputValid;
    List<String> rejectList;
    String response;
    List<String> stillCycleList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public VrContent() {
        this.contentState = ContentState.NONE;
        this.rejectList = Arrays.asList("1stRejection", "1stTimeout", "RECOGNITION", "CANCEL");
        this.stillCycleList = Arrays.asList("Timeout", "CANCEL", "RECOGNITION", "LOADING");
    }

    protected VrContent(Parcel parcel) {
        this.contentState = ContentState.NONE;
        this.rejectList = Arrays.asList("1stRejection", "1stTimeout", "RECOGNITION", "CANCEL");
        this.stillCycleList = Arrays.asList("Timeout", "CANCEL", "RECOGNITION", "LOADING");
        this.contentState = (ContentState) parcel.readParcelable(ContentState.class.getClassLoader());
        this.hints = parcel.createStringArrayList();
        this.input = parcel.readString();
        this.response = parcel.readString();
        this.isInputValid = parcel.readInt() == 0;
        this.interactionType = parcel.readString();
    }

    public ContentState getContentState() {
        return this.contentState;
    }

    public List<String> getHints() {
        return this.hints;
    }

    public VrContent setHints(List<String> list) {
        this.hints = list;
        return this;
    }

    public String getInput() {
        return this.input;
    }

    public void setInput(String str) {
        this.input = str;
    }

    public String getResponse() {
        return this.response;
    }

    public VrContent setResponse(String str) {
        this.response = str;
        return this;
    }

    public static VrContent emptyInit() {
        return new VrContent();
    }

    public VrContent addResponse(String str) {
        setResponse(str);
        return this;
    }

    public boolean isInputInvalid() {
        return !this.isInputValid;
    }

    public void setInputValid(boolean z) {
        this.isInputValid = z;
    }

    public void setInteractionType(String str) {
        this.interactionType = str;
    }

    /* JADX INFO: renamed from: com.byd.vrassistant.bean.VrContent$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$byd$vrassistant$bean$ContentState;

        static {
            int[] iArr = new int[ContentState.values().length];
            $SwitchMap$com$byd$vrassistant$bean$ContentState = iArr;
            try {
                iArr[ContentState.VALID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$ContentState[ContentState.INVALID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$ContentState[ContentState.INIT_MSG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$ContentState[ContentState.RESPONSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$ContentState[ContentState.ISSUING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$ContentState[ContentState.HINT_CONTINUE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$ContentState[ContentState.HINT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$bean$ContentState[ContentState.NONE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public VrContent setCurrentState(ContentState contentState) {
        this.contentState = contentState;
        int i = AnonymousClass2.$SwitchMap$com$byd$vrassistant$bean$ContentState[contentState.ordinal()];
        if (i == 1) {
            setInputValid(true);
        } else if (i == 2) {
            setInputValid(false);
        } else if (i == 3) {
            setResponse("");
            setInputValid(false);
        } else if (i != 4) {
            setInput("");
            setResponse("");
            setInputValid(false);
            setInteractionType("");
        }
        return this;
    }

    public VrContent parcelClone() throws Throwable {
        Parcel parcelObtain;
        try {
            parcelObtain = Parcel.obtain();
        } catch (Throwable th) {
            th = th;
            parcelObtain = null;
        }
        try {
            parcelObtain.writeParcelable(this, 0);
            parcelObtain.setDataPosition(0);
            VrContent vrContent = (VrContent) parcelObtain.readParcelable(VrContent.class.getClassLoader());
            if (parcelObtain != null) {
                parcelObtain.recycle();
            }
            return vrContent;
        } catch (Throwable th2) {
            th = th2;
            if (parcelObtain != null) {
                parcelObtain.recycle();
            }
            throw th;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VrContent vrContent = (VrContent) obj;
        if (getContentState() == vrContent.getContentState() && getContentState() == ContentState.ISSUING) {
            return Objects.equal(this.input, vrContent.getInput());
        }
        if (getContentState() == vrContent.getContentState() && getContentState() == ContentState.RESPONSE) {
            return Objects.equal(this.response, vrContent.getResponse());
        }
        return !(getContentState() == vrContent.getContentState() && getContentState() == ContentState.HINT) && getContentState() == vrContent.getContentState();
    }

    public int hashCode() {
        return Objects.hashCode(getContentState(), getInput());
    }

    public String toString() {
        return "VrContent{contentState=" + this.contentState + ", hints=" + this.hints + ", input='" + this.input + "', response='" + this.response + "', isInputValid=" + this.isInputValid + ", interactionType='" + this.interactionType + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.contentState, i);
        parcel.writeStringList(this.hints);
        parcel.writeString(this.input);
        parcel.writeString(this.response);
        parcel.writeInt(!this.isInputValid ? 1 : 0);
        parcel.writeString(this.interactionType);
    }

    public boolean isTimeout() {
        return this.interactionType.contains("Timeout");
    }

    public boolean isRejection() {
        return this.interactionType.contains("Rejection");
    }

    public boolean isCancel() {
        return this.interactionType.contains("Cancel");
    }

    public boolean showBigStill() {
        Log.i(TAG, "showBigStill: " + this.interactionType + ",contentState=" + this.contentState + "isInputInvalid=" + this.isInputValid);
        return this.rejectList.stream().anyMatch(new Predicate() { // from class: com.byd.vrassistant.bean.VrContent$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.m130lambda$showBigStill$0$combydvrassistantbeanVrContent((String) obj);
            }
        }) || getContentState() == ContentState.ISSUING || isInputInvalid();
    }

    /* JADX INFO: renamed from: lambda$showBigStill$0$com-byd-vrassistant-bean-VrContent, reason: not valid java name */
    /* synthetic */ boolean m130lambda$showBigStill$0$combydvrassistantbeanVrContent(String str) {
        return this.interactionType.contains(str);
    }

    public boolean isStillCycle() {
        Log.i(TAG, "isStillCycle: " + this.interactionType);
        return this.interactionType == null || this.stillCycleList.stream().anyMatch(new Predicate() { // from class: com.byd.vrassistant.bean.VrContent$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.m129lambda$isStillCycle$1$combydvrassistantbeanVrContent((String) obj);
            }
        }) || isInputInvalid();
    }

    /* JADX INFO: renamed from: lambda$isStillCycle$1$com-byd-vrassistant-bean-VrContent, reason: not valid java name */
    /* synthetic */ boolean m129lambda$isStillCycle$1$combydvrassistantbeanVrContent(String str) {
        return this.interactionType.contains(str);
    }
}
