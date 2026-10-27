package spi;

import android.app.PendingIntent;
import android.car.utils.Logs;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media.utils.MediaConstants;
import com.android.car.media.common.playback.PlaybackViewModel;
import com.android.car.ui.R$string;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: PlaybackErrorsHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j8 {
    public static final Map<Integer, Integer> c;
    public final Context a;
    public PlaybackViewModel.e b;

    static {
        HashMap map = new HashMap();
        map.put(1, Integer.valueOf(R$string.error_code_app_error));
        map.put(2, Integer.valueOf(R$string.error_code_not_supported));
        map.put(3, Integer.valueOf(R$string.error_code_authentication_expired));
        map.put(4, Integer.valueOf(R$string.error_code_premium_account_required));
        map.put(5, Integer.valueOf(R$string.error_code_concurrent_stream_limit));
        map.put(6, Integer.valueOf(R$string.error_code_parental_control_restricted));
        map.put(7, Integer.valueOf(R$string.error_code_not_available_in_region));
        map.put(8, Integer.valueOf(R$string.error_code_content_already_playing));
        map.put(9, Integer.valueOf(R$string.error_code_skip_limit_reached));
        map.put(10, Integer.valueOf(R$string.error_code_action_aborted));
        map.put(11, Integer.valueOf(R$string.error_code_end_of_queue));
        c = Collections.unmodifiableMap(map);
    }

    public j8(Context context) {
        this.a = context;
    }

    @Nullable
    public final String a(Context context, @Nullable PlaybackViewModel.e eVar) {
        if (eVar == null) {
            return null;
        }
        if (!TextUtils.isEmpty(eVar.c.getErrorMessage())) {
            return eVar.c.getErrorMessage().toString();
        }
        if (eVar.a() != 0) {
            Integer num = c.get(Integer.valueOf(eVar.a()));
            return context.getString(num != null ? num.intValue() : R$string.default_error_message);
        }
        if (eVar.c() == 7) {
            return context.getString(R$string.default_error_message);
        }
        return null;
    }

    public abstract void b(String str, PendingIntent pendingIntent, String str2, int i);

    public void c(@NonNull String str, PlaybackViewModel.e eVar, boolean z) {
        PlaybackViewModel.e eVar2;
        StringBuilder sbK = c.k("handlePlaybackState(); state change: ");
        PlaybackViewModel.e eVar3 = this.b;
        sbK.append(eVar3 != null ? Integer.valueOf(eVar3.c()) : null);
        sbK.append(" -> ");
        sbK.append(eVar != null ? Integer.valueOf(eVar.c()) : null);
        Logs.w(str, sbK.toString());
        if (eVar == null) {
            this.b = null;
            return;
        }
        String strA = a(this.a, eVar);
        StringBuilder sbO = c.o("Displayed error message: [", strA, "] ");
        sbO.append(eVar.a());
        Logs.w(str, sbO.toString());
        if (z && (eVar2 = this.b) != null && eVar2.c() == eVar.c() && TextUtils.equals(strA, a(this.a, this.b))) {
            Logs.w(str, "Ignore same playback state.");
            return;
        }
        this.b = eVar;
        Bundle extras = eVar.c.getExtras();
        PendingIntent pendingIntent = extras == null ? null : (PendingIntent) extras.getParcelable(MediaConstants.PLAYBACK_STATE_EXTRAS_KEY_ERROR_RESOLUTION_ACTION_INTENT);
        Bundle extras2 = eVar.c.getExtras();
        b(strA, pendingIntent, extras2 != null ? extras2.getString(MediaConstants.PLAYBACK_STATE_EXTRAS_KEY_ERROR_RESOLUTION_ACTION_LABEL) : null, eVar.a());
    }
}
