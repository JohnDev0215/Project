package main.java.com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.core.DeeplinkParser;
import com.amazon.music.destination.parser.ParserUtil;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.amazon.music.destination.CarModeDestination;

/* JADX INFO: loaded from: classes7.dex */
public class CarModeDeeplinkParser implements DeeplinkParser<CarModeDestination> {
    static Logger LOG = Logger.getLogger(CarModeDeeplinkParser.class.getSimpleName());

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$getCarModeAction$1(String str) {
        return str;
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public CarModeDestination parse(Uri uri) {
        return new CarModeDestination(uri, getPresetName(uri), getPresetNumber(uri), getCarModeDestinationType(uri), ParserUtil.getAction(uri), ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return getCarModeUrlRegex();
    }

    private String getCarModeUrlRegex() {
        return "/car-mode/.*";
    }

    private String getPresetName(Uri uri) {
        return uri.getQueryParameter("presetName");
    }

    private Integer getPresetNumber(Uri uri) {
        String queryParameter = uri.getQueryParameter("presetNumber");
        if (queryParameter != null) {
            return Integer.valueOf(queryParameter);
        }
        return null;
    }

    private String getCarModeDestinationType(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        LOG.log(Level.INFO, "Last component - {}", lastPathSegment);
        lastPathSegment.hashCode();
        if (!lastPathSegment.equals("presets")) {
            return !lastPathSegment.equals("exit") ? "ENTER_CARMODE" : "EXIT_CARMODE";
        }
        if (getPresetName(uri) != null) {
            LOG.log(Level.INFO, "Uri is for Car mode preset with presetName - {}", getPresetName(uri));
            return "PLAYBACK_PRESET_NAME";
        }
        if (getPresetNumber(uri) != null) {
            LOG.log(Level.INFO, "Uri is for Car mode preset with presetNumber - {}", getPresetNumber(uri));
            return "PLAYBACK_PRESET_NUMBER";
        }
        String carModeAction = getCarModeAction(uri);
        LOG.log(Level.INFO, "Uri has action - {}", carModeAction);
        return carModeAction.equalsIgnoreCase("next") ? "PLAYBACK_PRESET_NEXT" : carModeAction.equalsIgnoreCase("prev") ? "PLAYBACK_PRESET_PREV" : "ENTER_CARMODE";
    }

    private String getCarModeAction(Uri uri) {
        return (String) Optional.ofNullable(uri).map(new Function() { // from class: main.java.com.amazon.music.destination.parser.CarModeDeeplinkParser$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CarModeDeeplinkParser.lambda$getCarModeAction$0((Uri) obj);
            }
        }).map(new Function() { // from class: main.java.com.amazon.music.destination.parser.CarModeDeeplinkParser$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CarModeDeeplinkParser.lambda$getCarModeAction$1((String) obj);
            }
        }).filter(new Predicate() { // from class: main.java.com.amazon.music.destination.parser.CarModeDeeplinkParser$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CarModeDeeplinkParser.lambda$getCarModeAction$2((String) obj);
            }
        }).orElse(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$getCarModeAction$0(Uri uri) {
        return uri.getQueryParameter("action");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getCarModeAction$2(String str) {
        return !str.isEmpty();
    }
}
