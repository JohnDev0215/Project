package cerence.ark.assistant.framework.prompter;

import android.os.Parcel;
import android.os.Parcelable;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.Utils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public enum PrompterError implements Parcelable {
    PROMPT_ARGUMENT_INVALID,
    PROMPT_STATE_INVALID,
    PROMPT_ALLOCATION_FAILURE,
    INPUT_INVALID,
    PROMPTER_DESTROYED,
    INVALID_VOICE,
    CLOUD_CONNECTION_FAILED,
    OTHER_ERROR;

    public static final Parcelable.Creator<PrompterError> CREATOR = new Parcelable.Creator<PrompterError>() { // from class: cerence.ark.assistant.framework.prompter.PrompterError.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PrompterError createFromParcel(Parcel parcel) {
            return PrompterError.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PrompterError[] newArray(int i) {
            return new PrompterError[i];
        }
    };
    private static final Map<String, PrompterError> ERROR_MAP = new HashMap(16);

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    static {
        for (PrompterError prompterError : values()) {
            ERROR_MAP.put(prompterError.toString(), prompterError);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }

    public static PrompterError fromString(String str) {
        PrompterError prompterError = ERROR_MAP.get(str.toUpperCase(Locale.US));
        if (prompterError != null) {
            return prompterError;
        }
        Logger.warn("PrompterError", Utils.format("fromString: error code not mapping PrompterError enum, value = %s", str));
        return OTHER_ERROR;
    }
}
