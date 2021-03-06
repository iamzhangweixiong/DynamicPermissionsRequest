package com.zhangwx.dynamicpermissionsrequest;

import android.os.Bundle;
import android.view.View;

import com.zhangwx.dynamicpermissionsrequest.permission.AfterPermissionGranted;
import com.zhangwx.dynamicpermissionsrequest.permission.EasyPermissions;
import com.zhangwx.dynamicpermissionsrequest.permission.PermissionUtils;
import com.zhangwx.dynamicpermissionsrequest.permission.bridge.PermissionRequestBridge;

import java.util.List;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.fab).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //方式一:
        EasyPermissions.requestPermissions(this,
                R.mipmap.ic_launcher,
                "MainActivity",
                getString(R.string.rationale_contact),
                PermissionUtils.REQUEST_CONTACTS_CODE,
                PermissionUtils.PERMISSIONS_CONTACTS_GROUP);

//        checkPermission();
//        checkPermissionWithBridge();
    }

    //方式一：
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        super.onPermissionsGranted(requestCode, perms);
        //do something
    }

    //方式二：通过注解反射回调
    @AfterPermissionGranted(PermissionUtils.REQUEST_LOCATION_CODE)
    private void checkPermission() {
        if (EasyPermissions.hasPermissions(getApplicationContext(), PermissionUtils.PERMISSIONS_LOCATION_GROUP)) {
            //do something
        } else {
            EasyPermissions.requestPermissions(this,
                    R.mipmap.ic_launcher,
                    "MainActivity",
                    getString(R.string.rationale_location),
                    PermissionUtils.REQUEST_LOCATION_CODE,
                    PermissionUtils.PERMISSIONS_LOCATION_GROUP);
        }
    }

    //方式三：(推荐)重启一个Activity用于特殊情况下使用，使用空的Activity用于中转
    private void checkPermissionWithBridge() {
        new PermissionRequestBridge().request(
                true,
                PermissionUtils.REQUEST_CAMERA_CODE,
                PermissionUtils.PERMISSION_CAMERA_GROUP,
                getString(R.string.title_settings_dialog),
                getString(R.string.rationale_camera),
                new PermissionRequestBridge.RequestCallBack() {
                    @Override
                    public void onRequestResult(boolean isGranted) {
                        if (isGranted) {
                            //do something
                        }
                    }
                });
    }
}
