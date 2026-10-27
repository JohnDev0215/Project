package main.java.com.amazon.music.destination;

import android.net.Uri;
import com.amazon.music.destination.core.Destination;
import com.amazon.music.destination.parser.Action;

/* JADX INFO: loaded from: classes7.dex */
public class CarModeDestination extends Destination {
    private final Action action;
    private final String presetName;
    private final Integer presetNumber;
    private final String type;
    private final Uri uri;

    public CarModeDestination(Uri uri, String str, Integer num, String str2, Action action, String str3, String str4) {
        super(str3, str4);
        this.uri = uri;
        this.type = str2;
        this.presetName = str;
        this.presetNumber = num;
        this.action = action;
    }

    public Uri getUri() {
        return this.uri;
    }

    public Integer getPresetNumber() {
        return this.presetNumber;
    }

    public String getCarModeDestinationType() {
        return this.type;
    }
}
