package com.zhangwx.dynamicpermissionsrequest.permission.bridge;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.zhangwx.dynamicpermissionsrequest.MyApplication;

import java.lang.ref.WeakReference;

/**
 * Created by zhangwx on 2017/4/5.
 */

public class PermissionRequestBridge {

    public static final String PERMISSION_CHECK_ACTION = "permission_check_action";
    public static final String KEY_CHECK_RESULT = "key_check_result";
    public static final String KEY_CHECK_PERMISSION_CODE = "key_permission_code";

    private RequestCallBack mRequestCallBack;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (PERMISSION_CHECK_ACTION.equals(intent.getAction())) {
                final boolean isGranted = intent.getBooleanExtra(KEY_CHECK_RESULT, false);
                final int requestCode = intent.getIntExtra(KEY_CHECK_PERMISSION_CODE, 0);
                mRequestCallBack.onRequestResult(isGranted);
            }
        }
    };

    public PermissionRequestBridge() {
        registerBroadcast();
    }

    public void request(boolean needRationale, int requestCode, String[] permissionGroup, String title, String rationale, RequestCallBack callBack) {
        mRequestCallBack = callBack;
        RequestBridgeActivity.startSelf(MyApplication.getContext(), needRationale, requestCode, permissionGroup, title, rationale);
    }

    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(PERMISSION_CHECK_ACTION);
        MyApplication.getContext().registerReceiver(mReceiver, filter);
    }


    public interface RequestCallBack {
        void onRequestResult(boolean isGranted);
    }
}
