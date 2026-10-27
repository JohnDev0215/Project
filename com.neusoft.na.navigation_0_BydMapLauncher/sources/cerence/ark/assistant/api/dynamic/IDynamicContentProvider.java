package cerence.ark.assistant.api.dynamic;

import android.os.CancellationSignal;
import cerence.ark.assistant.api.Activated;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IDynamicContentProvider extends Activated {
    List<App> getApps(CancellationSignal cancellationSignal);

    List<Contact> getContacts(CancellationSignal cancellationSignal);

    List<String> getDynamicContent(String str, CancellationSignal cancellationSignal);

    List<Music> getMusic(CancellationSignal cancellationSignal);

    List<RadioStation> getRadioStations(CancellationSignal cancellationSignal);
}
