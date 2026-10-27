package com.android.car.settings.users;

import android.app.ActivityManager;
import android.car.Car;
import android.car.user.CarUserManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.settings.R;
import com.android.car.settings.common.BaseFragment;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.ErrorDialog;
import com.android.car.settings.users.AddNewUserTask;
import com.android.internal.util.UserIcons;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes5.dex */
public class UserGridRecyclerView extends RecyclerView {
    private static final String CONFIRM_CREATE_NEW_USER_DIALOG_TAG = "com.android.car.settings.users.ConfirmCreateNewUserDialog";
    private static final String MAX_USERS_LIMIT_REACHED_DIALOG_TAG = "com.android.car.settings.users.MaxUsersLimitReachedDialog";
    private UserAdapter mAdapter;
    private AddNewUserTask mAddNewUserTask;
    private BaseFragment mBaseFragment;
    private Car mCar;
    private CarUserManager mCarUserManager;
    private Context mContext;
    private boolean mEnableAddUserButton;
    private UserIconProvider mUserIconProvider;
    private UserManager mUserManager;
    private final BroadcastReceiver mUserUpdateReceiver;

    public UserGridRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mUserUpdateReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.users.UserGridRecyclerView.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                UserGridRecyclerView.this.onUsersUpdate();
            }
        };
        this.mContext = context;
        this.mUserManager = UserManager.get(context);
        this.mUserIconProvider = new UserIconProvider();
        this.mEnableAddUserButton = true;
        Car carCreateCar = Car.createCar(this.mContext);
        this.mCar = carCreateCar;
        this.mCarUserManager = (CarUserManager) carCreateCar.getCarManager("car_user_service");
        addItemDecoration(new ItemSpacingDecoration(context.getResources().getDimensionPixelSize(R.dimen.user_switcher_vertical_spacing_between_users)));
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        registerForUserEvents();
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unregisterForUserEvents();
        AddNewUserTask addNewUserTask = this.mAddNewUserTask;
        if (addNewUserTask != null) {
            addNewUserTask.cancel(false);
        }
        Car car = this.mCar;
        if (car != null) {
            car.disconnect();
        }
    }

    public void buildAdapter() {
        List<UserRecord> userRecords = createUserRecords(getUsersForUserGrid());
        UserAdapter userAdapter = new UserAdapter(this.mContext, userRecords);
        this.mAdapter = userAdapter;
        super.setAdapter(userAdapter);
    }

    private List<UserRecord> createUserRecords(List<UserInfo> userInfoList) {
        int fgUserId = ActivityManager.getCurrentUser();
        UserHandle fgUserHandle = UserHandle.of(fgUserId);
        List<UserRecord> userRecords = new ArrayList<>();
        if (this.mUserManager.getUserSwitchability(fgUserHandle) != 0) {
            userRecords.add(createForegroundUserRecord());
            return userRecords;
        }
        for (UserInfo userInfo : userInfoList) {
            boolean isForeground = fgUserId == userInfo.id;
            if (isForeground || !userInfo.isGuest()) {
                UserRecord record = new UserRecord(userInfo, isForeground ? 2 : 3);
                userRecords.add(record);
            }
        }
        if (!getCurrentForegroundUserInfo().isGuest()) {
            userRecords.add(createStartGuestUserRecord());
        }
        if (!this.mUserManager.hasUserRestriction("no_add_user", fgUserHandle)) {
            userRecords.add(createAddUserRecord());
        }
        return userRecords;
    }

    private UserRecord createForegroundUserRecord() {
        return new UserRecord(getCurrentForegroundUserInfo(), 2);
    }

    private UserInfo getCurrentForegroundUserInfo() {
        return this.mUserManager.getUserInfo(ActivityManager.getCurrentUser());
    }

    public void enableAddUser() {
        this.mEnableAddUserButton = true;
        onUsersUpdate();
    }

    public void disableAddUser() {
        this.mEnableAddUserButton = false;
        onUsersUpdate();
    }

    private UserRecord createStartGuestUserRecord() {
        return new UserRecord(null, 0);
    }

    private UserRecord createAddUserRecord() {
        return new UserRecord(null, 1);
    }

    public void setFragment(BaseFragment fragment) {
        this.mBaseFragment = fragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUsersUpdate() {
        this.mAdapter.setAddUserRestricted(!this.mEnableAddUserButton);
        this.mAdapter.clearUsers();
        this.mAdapter.updateUsers(createUserRecords(getUsersForUserGrid()));
        this.mAdapter.notifyDataSetChanged();
    }

    private List<UserInfo> getUsersForUserGrid() {
        List<UserInfo> users = UserManager.get(this.mContext).getUsers(true);
        return (List) users.stream().filter(new Predicate() { // from class: com.android.car.settings.users.-$$Lambda$YRHklm_iwxvubsyDL5Bldbm1kyE
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((UserInfo) obj).supportsSwitchToByUser();
            }
        }).collect(Collectors.toList());
    }

    private void registerForUserEvents() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.USER_REMOVED");
        filter.addAction("android.intent.action.USER_ADDED");
        filter.addAction("android.intent.action.USER_INFO_CHANGED");
        filter.addAction("android.intent.action.USER_SWITCHED");
        filter.addAction("android.intent.action.USER_STOPPED");
        filter.addAction("android.intent.action.USER_UNLOCKED");
        this.mContext.registerReceiverAsUser(this.mUserUpdateReceiver, UserHandle.ALL, filter, null, null);
    }

    private void unregisterForUserEvents() {
        this.mContext.unregisterReceiver(this.mUserUpdateReceiver);
    }

    public final class UserAdapter extends RecyclerView.Adapter<UserAdapterViewHolder> implements AddNewUserTask.AddNewUserListener {
        private View mAddUserView;
        private Context mContext;
        private final String mGuestName;
        private boolean mIsAddUserRestricted;
        private String mNewUserName;
        private float mOpacityDisabled;
        private float mOpacityEnabled;
        private final Resources mRes;
        private List<UserRecord> mUsers;
        private final ConfirmationDialogFragment.ConfirmListener mConfirmListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.users.-$$Lambda$UserGridRecyclerView$UserAdapter$ovijSY3RwWb_s7s_H04RL5aZ7Mg
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public final void onConfirm(Bundle bundle) {
                this.f$0.lambda$new$0$UserGridRecyclerView$UserAdapter(bundle);
            }
        };
        private final ConfirmationDialogFragment.RejectListener mRejectListener = new ConfirmationDialogFragment.RejectListener() { // from class: com.android.car.settings.users.-$$Lambda$UserGridRecyclerView$UserAdapter$QILQATOoIDfDcqojaUdKs4OXfFc
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.RejectListener
            public final void onReject(Bundle bundle) {
                this.f$0.lambda$new$1$UserGridRecyclerView$UserAdapter(bundle);
            }
        };

        public /* synthetic */ void lambda$new$0$UserGridRecyclerView$UserAdapter(Bundle arguments) {
            UserGridRecyclerView.this.mAddNewUserTask = new AddNewUserTask(this.mContext, UserGridRecyclerView.this.mCarUserManager, this);
            UserGridRecyclerView.this.mAddNewUserTask.execute(this.mNewUserName);
        }

        public /* synthetic */ void lambda$new$1$UserGridRecyclerView$UserAdapter(Bundle arguments) {
            enableAddView();
        }

        public UserAdapter(Context context, List<UserRecord> users) {
            this.mRes = context.getResources();
            this.mContext = context;
            updateUsers(users);
            this.mGuestName = this.mRes.getString(R.string.user_guest);
            this.mNewUserName = this.mRes.getString(R.string.user_new_user_name);
            this.mOpacityDisabled = this.mRes.getFloat(R.dimen.opacity_disabled);
            this.mOpacityEnabled = this.mRes.getFloat(R.dimen.opacity_enabled);
            resetDialogListeners();
        }

        public void clearUsers() {
            this.mUsers.clear();
        }

        public void updateUsers(List<UserRecord> users) {
            this.mUsers = users;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public UserAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(this.mContext).inflate(R.layout.user_switcher_pod, parent, false);
            view.setAlpha(this.mOpacityEnabled);
            view.bringToFront();
            return new UserAdapterViewHolder(view);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(UserAdapterViewHolder holder, int position) {
            final UserRecord userRecord = this.mUsers.get(position);
            RoundedBitmapDrawable circleIcon = getCircularUserRecordIcon(userRecord);
            holder.mUserAvatarImageView.setImageDrawable(circleIcon);
            holder.mUserNameTextView.setText(getUserRecordName(userRecord));
            holder.mView.setAlpha(this.mOpacityEnabled);
            holder.mFrame.setBackgroundResource(0);
            int i = userRecord.mType;
            if (i == 0) {
                holder.mView.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.settings.users.-$$Lambda$UserGridRecyclerView$UserAdapter$zgmLsIPbMNQIZURR4EjZ5BLilnA
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$onBindViewHolder$3$UserGridRecyclerView$UserAdapter(view);
                    }
                });
                return;
            }
            if (i != 1) {
                if (i == 2) {
                    holder.mFrame.setBackgroundResource(R.drawable.user_avatar_bg_circle);
                    holder.mView.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.settings.users.-$$Lambda$UserGridRecyclerView$UserAdapter$eP12oeogOEBCpltsf1e64TrUKes
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            this.f$0.lambda$onBindViewHolder$2$UserGridRecyclerView$UserAdapter(view);
                        }
                    });
                    return;
                } else {
                    holder.mView.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.settings.users.-$$Lambda$UserGridRecyclerView$UserAdapter$nspi65SIurcUTt0IGNBRjuG-3Sg
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            this.f$0.lambda$onBindViewHolder$6$UserGridRecyclerView$UserAdapter(userRecord, view);
                        }
                    });
                    return;
                }
            }
            if (this.mIsAddUserRestricted) {
                holder.mView.setAlpha(this.mOpacityDisabled);
                holder.mView.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.settings.users.-$$Lambda$UserGridRecyclerView$UserAdapter$H-jj9esoskMY_7tzsDC9VE2YWfE
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$onBindViewHolder$4$UserGridRecyclerView$UserAdapter(view);
                    }
                });
            } else {
                holder.mView.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.settings.users.-$$Lambda$UserGridRecyclerView$UserAdapter$t0mEavEX0OImjTkkHelqeHlfzgA
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$onBindViewHolder$5$UserGridRecyclerView$UserAdapter(view);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onBindViewHolder$2$UserGridRecyclerView$UserAdapter(View v) {
            UserGridRecyclerView.this.mBaseFragment.getActivity().onBackPressed();
        }

        public /* synthetic */ void lambda$onBindViewHolder$3$UserGridRecyclerView$UserAdapter(View v) {
            handleGuestSessionClicked();
        }

        public /* synthetic */ void lambda$onBindViewHolder$4$UserGridRecyclerView$UserAdapter(View v) {
            UserGridRecyclerView.this.mBaseFragment.getFragmentHost().showBlockingMessage();
        }

        public /* synthetic */ void lambda$onBindViewHolder$6$UserGridRecyclerView$UserAdapter(UserRecord userRecord, View v) {
            handleUserSwitch(userRecord.mInfo);
        }

        public void setAddUserRestricted(boolean isAddUserRestricted) {
            this.mIsAddUserRestricted = isAddUserRestricted;
        }

        private void resetDialogListeners() {
            if (UserGridRecyclerView.this.mBaseFragment != null) {
                ConfirmationDialogFragment dialog = (ConfirmationDialogFragment) UserGridRecyclerView.this.mBaseFragment.getFragmentManager().findFragmentByTag(UserGridRecyclerView.CONFIRM_CREATE_NEW_USER_DIALOG_TAG);
                ConfirmationDialogFragment.resetListeners(dialog, this.mConfirmListener, this.mRejectListener, null);
            }
        }

        private void handleUserSwitch(UserInfo userInfo) {
            UserGridRecyclerView.this.mCarUserManager.switchUser(userInfo.id).thenRun(new Runnable() { // from class: com.android.car.settings.users.-$$Lambda$UserGridRecyclerView$UserAdapter$Z9FaXk0FnRBqyczk30sT_qeiI4A
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$handleUserSwitch$7$UserGridRecyclerView$UserAdapter();
                }
            });
        }

        private void handleGuestSessionClicked() {
            UserInfo guest = UserHelper.getInstance(this.mContext).createNewOrFindExistingGuest(this.mContext);
            if (guest != null) {
                UserGridRecyclerView.this.mCarUserManager.switchUser(guest.id).thenRun(new Runnable() { // from class: com.android.car.settings.users.-$$Lambda$UserGridRecyclerView$UserAdapter$0I3gnnQkGj-hN40uzqMME6jW6_U
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$handleGuestSessionClicked$8$UserGridRecyclerView$UserAdapter();
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: handleAddUserClicked, reason: merged with bridge method [inline-methods] */
        public void lambda$onBindViewHolder$5$UserGridRecyclerView$UserAdapter(View addUserView) {
            if (!UserGridRecyclerView.this.mUserManager.canAddMoreUsers()) {
                showMaxUsersLimitReachedDialog();
                return;
            }
            this.mAddUserView = addUserView;
            addUserView.setEnabled(false);
            showConfirmCreateNewUserDialog();
        }

        private void showMaxUsersLimitReachedDialog() {
            ConfirmationDialogFragment dialogFragment = UsersDialogProvider.getMaxUsersLimitReachedDialogFragment(UserGridRecyclerView.this.getContext(), UserHelper.getInstance(this.mContext).getMaxSupportedRealUsers());
            dialogFragment.show(UserGridRecyclerView.this.mBaseFragment.getFragmentManager(), UserGridRecyclerView.MAX_USERS_LIMIT_REACHED_DIALOG_TAG);
        }

        private void showConfirmCreateNewUserDialog() {
            ConfirmationDialogFragment dialogFragment = UsersDialogProvider.getConfirmCreateNewUserDialogFragment(UserGridRecyclerView.this.getContext(), this.mConfirmListener, this.mRejectListener);
            dialogFragment.show(UserGridRecyclerView.this.mBaseFragment.getFragmentManager(), UserGridRecyclerView.CONFIRM_CREATE_NEW_USER_DIALOG_TAG);
        }

        private RoundedBitmapDrawable getCircularUserRecordIcon(UserRecord userRecord) {
            Resources resources = this.mContext.getResources();
            int i = userRecord.mType;
            if (i == 0) {
                RoundedBitmapDrawable circleIcon = UserGridRecyclerView.this.mUserIconProvider.getRoundedGuestDefaultIcon(resources);
                return circleIcon;
            }
            if (i != 1) {
                RoundedBitmapDrawable circleIcon2 = UserGridRecyclerView.this.mUserIconProvider.getRoundedUserIcon(userRecord.mInfo, this.mContext);
                return circleIcon2;
            }
            RoundedBitmapDrawable circleIcon3 = getCircularAddUserIcon();
            return circleIcon3;
        }

        private RoundedBitmapDrawable getCircularAddUserIcon() {
            RoundedBitmapDrawable circleIcon = RoundedBitmapDrawableFactory.create(this.mRes, UserIcons.convertToBitmap(this.mContext.getDrawable(R.drawable.user_add_circle)));
            circleIcon.setCircular(true);
            return circleIcon;
        }

        private String getUserRecordName(UserRecord userRecord) {
            int i = userRecord.mType;
            if (i == 0) {
                String recordName = this.mContext.getString(R.string.start_guest_session);
                return recordName;
            }
            if (i == 1) {
                String recordName2 = this.mContext.getString(R.string.user_add_user_menu);
                return recordName2;
            }
            String recordName3 = userRecord.mInfo.name;
            return recordName3;
        }

        @Override // com.android.car.settings.users.AddNewUserTask.AddNewUserListener
        public void onUserAddedSuccess() {
            enableAddView();
            lambda$handleUserSwitch$7$UserGridRecyclerView$UserAdapter();
        }

        @Override // com.android.car.settings.users.AddNewUserTask.AddNewUserListener
        public void onUserAddedFailure() {
            enableAddView();
            if (UserGridRecyclerView.this.mBaseFragment != null) {
                ErrorDialog.show(UserGridRecyclerView.this.mBaseFragment, R.string.add_user_error_title);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: closeSettingsTask, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public void lambda$handleUserSwitch$7$UserGridRecyclerView$UserAdapter() {
            UserGridRecyclerView.this.mBaseFragment.getActivity().setResult(2, new Intent());
            UserGridRecyclerView.this.mBaseFragment.getActivity().finish();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mUsers.size();
        }

        public class UserAdapterViewHolder extends RecyclerView.ViewHolder {
            public FrameLayout mFrame;
            public ImageView mUserAvatarImageView;
            public TextView mUserNameTextView;
            public View mView;

            public UserAdapterViewHolder(View view) {
                super(view);
                this.mView = view;
                this.mUserAvatarImageView = (ImageView) view.findViewById(R.id.user_avatar);
                this.mUserNameTextView = (TextView) view.findViewById(R.id.user_name);
                this.mFrame = (FrameLayout) view.findViewById(R.id.current_user_frame);
            }
        }

        private void enableAddView() {
            View view = this.mAddUserView;
            if (view != null) {
                view.setEnabled(true);
            }
        }
    }

    public static final class UserRecord {
        public static final int ADD_USER = 1;
        public static final int BACKGROUND_USER = 3;
        public static final int FOREGROUND_USER = 2;
        public static final int START_GUEST = 0;
        public final UserInfo mInfo;
        public final int mType;

        @Retention(RetentionPolicy.SOURCE)
        public @interface UserRecordType {
        }

        public UserRecord(UserInfo userInfo, int recordType) {
            this.mInfo = userInfo;
            this.mType = recordType;
        }
    }

    private static class ItemSpacingDecoration extends RecyclerView.ItemDecoration {
        private int mItemSpacing;

        private ItemSpacingDecoration(int itemSpacing) {
            this.mItemSpacing = itemSpacing;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int position = parent.getChildAdapterPosition(view);
            if (position == state.getItemCount() - 1 && !(parent.getLayoutManager() instanceof GridLayoutManager)) {
                return;
            }
            outRect.bottom = this.mItemSpacing;
        }
    }
}
