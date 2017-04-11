package com.zhangwx.dynamicpermissionsrequest;

import android.os.Bundle;

import com.zhangwx.dynamicpermissionsrequest.permission.AfterPermissionGranted;
import com.zhangwx.dynamicpermissionsrequest.permission.EasyPermissions;
import com.zhangwx.dynamicpermissionsrequest.permission.PermissionUtils;

import static com.zhangwx.dynamicpermissionsrequest.permission.PermissionUtils.REQUEST_LOCATION_CODE;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
    }

    @AfterPermissionGranted(REQUEST_LOCATION_CODE)
    private void checkPermission() {
        if (EasyPermissions.hasPermissions(getApplicationContext(), PermissionUtils.PERMISSION_LOCATION_GROUP)) {
            //do something
        } else {
            EasyPermissions.requestPermissions(this,
                    R.mipmap.ic_launcher,
                    "RequestLocationPermissions",
                    getString(R.string.action_settings),
                    REQUEST_LOCATION_CODE,
                    PermissionUtils.PERMISSION_MICROPHONE_GROUP);
        }
    }

}
