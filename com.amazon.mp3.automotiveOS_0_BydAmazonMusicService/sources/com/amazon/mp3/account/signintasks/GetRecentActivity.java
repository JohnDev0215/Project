package com.amazon.mp3.account.signintasks;

import android.content.Context;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.ftu.FTUSettingsUtil;
import com.amazon.mp3.recentlyplayed.RecentsFactory;
import com.amazon.mp3.util.Log;
import com.amazon.music.recents.model.RecentUnfilteredTrackActivityRequest;
import com.amazon.music.signin.SignInTask;
import com.amazon.nimblymusicservice.GetRecentUnfilteredTrackResponse;
import com.amazon.nimblymusicservice.TrackEventList;
import java.util.Calendar;

/* JADX INFO: loaded from: classes3.dex */
public class GetRecentActivity implements SignInTask {
    private static final Calendar EARLIEST_DATE;
    private static final String TAG = "GetRecentActivity";
    private Context mContext;

    static {
        Calendar calendar = Calendar.getInstance();
        EARLIEST_DATE = calendar;
        calendar.set(2010, 0, 0, 0, 0, 0);
    }

    public GetRecentActivity(final Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.music.signin.SignInTask
    public void execute() throws Exception {
        Context context = this.mContext;
        FTUSettingsUtil.setHasNoRecentsHistory(context, hasNoRecentActivity(context));
    }

    @Override // com.amazon.music.signin.SignInTask
    public String getName() {
        return TAG;
    }

    private static boolean hasNoRecentActivity(final Context context) {
        TrackEventList trackEventList;
        try {
            GetRecentUnfilteredTrackResponse recentUnfilteredTrackActivity = RecentsFactory.createRecentsController(context).getRecentUnfilteredTrackActivity(createRecentUnfilteredTrackActivity(context));
            if (recentUnfilteredTrackActivity == null || (trackEventList = recentUnfilteredTrackActivity.getTrackEventList()) == null || trackEventList.getEvents() == null) {
                return false;
            }
            return trackEventList.getEvents().isEmpty();
        } catch (Exception e) {
            Log.debug(TAG, e.getLocalizedMessage(), e);
            return false;
        }
    }

    private static RecentUnfilteredTrackActivityRequest createRecentUnfilteredTrackActivity(final Context context) {
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
        return new RecentUnfilteredTrackActivityRequest.Builder(AccountDetailUtil.getMusicTerritoryOfResidence(), accountCredentialStorage.getDeviceId(), accountCredentialStorage.getDeviceType()).withStartTime(EARLIEST_DATE.getTime()).withEndTime(Calendar.getInstance().getTime()).withFilterMoreThanThirtySeconds(Boolean.TRUE).withFlatten(Boolean.FALSE).withMaxResults(30).build();
    }
}
